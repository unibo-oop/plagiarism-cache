package todo.view.rooms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.badlogic.gdx.math.Vector2;

import todo.utils.Checks;
import todo.view.drawables.screens.ResolutionManager;
import todo.view.drawables.screens.ResolutionManagerImpl;
import todo.view.drawables.screens.Texture;
import todo.view.drawables.screens.Textures;
import todo.view.entities.level.Floor;
import todo.view.entities.level.FloorImpl;
import todo.view.entities.level.InputBelt;
import todo.view.entities.level.InputBeltImpl;
import todo.view.entities.level.MemoryArea;
import todo.view.entities.level.MemoryAreaImpl;
import todo.view.entities.level.OutputBelt;
import todo.view.entities.level.OutputBeltImpl;
import todo.view.entities.level.Player;
import todo.view.entities.level.PlayerImpl;
import todo.view.entities.level.ValueBox;

public final class RoomImpl implements Room {
    private static final float BELT_BOX_MARGIN = 16f;
    private final Floor floor;
    private final InputBelt inputBelt;
    private final OutputBelt outputBelt;
    private Optional<MemoryArea> memoryArea;
    private final Player player;

    private RoomImpl(final List<ValueBox> input, final int memoryAreaWidth, final int memoryAreaHeight,
            final List<Optional<ValueBox>> values, final AnimationsSpeed animationsSpeed) {
        final ResolutionManager res = ResolutionManagerImpl.getInstance();
        final Textures textures = res.getCurrentAspectRatio().getTextures();
        final Texture floor = textures.getTexture(Textures.FLOOR).scaled();
        final Texture beltInput = textures.getTexture(Textures.BELT_INPUT).scaled();
        final Texture beltOutput = textures.getTexture(Textures.BELT_OUTPUT).scaled();
        final Vector2 playerStartingPosition = beltInput.getFrom().add(beltOutput.getTo()).scl(0.5f);
        final float beltHorizontalMargin = BELT_BOX_MARGIN;
        final float beltVerticalMargin = res.getCurrentAspectRatio().getManifest().getScaledValueBoxSize()
                + BELT_BOX_MARGIN;
        this.player = new PlayerImpl.Builder().position(playerStartingPosition).build();
        this.floor = new FloorImpl.Builder().position(floor.getFrom())
                                            .size(floor.getWidth(), floor.getHeight())
                                            .build();
        this.inputBelt = new InputBeltImpl.Builder().position(beltInput.getFrom())
                                                    .endPosition(beltInput.getTo())
                                                    .valueBoxBoundaries(beltHorizontalMargin, beltVerticalMargin)
                                                    .valueBoxes(input)
                                                    .animationsSpeed(animationsSpeed)
                                                    .build();
        this.outputBelt = new OutputBeltImpl.Builder().position(beltOutput.getFrom())
                                                      .endPosition(beltOutput.getTo())
                                                      .valueBoxBoundaries(beltHorizontalMargin, beltVerticalMargin)
                                                      .animationsSpeed(animationsSpeed)
                                                      .build();
        if (memoryAreaWidth > 0 && memoryAreaHeight > 0) {
            final float tileSize = res.getCurrentAspectRatio().getManifest().getScaledFloorTileSize();
            final float firstTileY = res.getCurrentAspectRatio().getManifest().getFirstTileY() * res.getScaleFactor();
            final float halfTilesWidth = (memoryAreaWidth / 2) * tileSize;
            final float halfTileHeight = (memoryAreaHeight / 2) * tileSize;
            final int tilesCountX = Math.round(floor.getWidth() / tileSize);
            final int tilesCountY = Math.round(floor.getHeight() / tileSize);
            final Vector2 memoryAreaPosition = floor.getFrom()
                                                    .add(tilesCountX / 2 * tileSize - halfTilesWidth,
                                                            tilesCountY / 2 * tileSize + firstTileY - halfTileHeight);
            this.memoryArea = Optional.of(new MemoryAreaImpl.Builder().position(memoryAreaPosition)
                                                                      .size(memoryAreaHeight, memoryAreaWidth)
                                                                      .initialValues(Objects.requireNonNull(values))
                                                                      .build());
        } else {
            this.memoryArea = Optional.empty();
        }
    }

    @Override
    public Floor getFloor() {
        return this.floor;
    }

    @Override
    public InputBelt getInputBelt() {
        return this.inputBelt;
    }

    @Override
    public OutputBelt getOutputBelt() {
        return this.outputBelt;
    }

    @Override
    public Optional<MemoryArea> getMemoryArea() {
        return this.memoryArea;
    }

    @Override
    public Player getPlayer() {
        return this.player;
    }

    public static final class Builder implements RoomBuilder {
        private int memoryAreaWidth;
        private int memoryAreaHeight;
        private final List<ValueBox> currentInitialInput;
        private final List<Optional<ValueBox>> memoryAreaValues;
        private final AnimationsSpeed animationsSpeed;

        public Builder(final AnimationsSpeed animationsSpeed) {
            this.memoryAreaWidth = 0;
            this.memoryAreaHeight = 0;
            this.currentInitialInput = new ArrayList<>();
            this.memoryAreaValues = new ArrayList<>();
            this.animationsSpeed = Objects.requireNonNull(animationsSpeed);
        }

        @Override
        public RoomBuilder initialInput(final ValueBox... boxes) {
            return initialInput(Arrays.asList(boxes));
        }

        @Override
        public RoomBuilder initialInput(final List<ValueBox> boxes) {
            Checks.requireOnIterable(Objects.requireNonNull(boxes), p -> p != null, IllegalArgumentException.class);
            this.currentInitialInput.addAll(boxes);
            return this;
        }

        @Override
        public RoomBuilder memoryArea(final int width, final int height) {
            this.memoryAreaWidth = width;
            this.memoryAreaHeight = height;
            return this;
        }

        @Override
        public RoomBuilder memoryArea(final int width, final int height, final List<Optional<ValueBox>> values) {
            memoryArea(width, height);
            this.memoryAreaValues.addAll(values);
            return this;
        }

        @Override
        public Room build() {
            // Perform sanity checks on memory area width and height
            Checks.require(this.memoryAreaWidth >= 0 && this.memoryAreaHeight >= 0, IllegalArgumentException.class,
                    "Memory area can't be negative");
            Checks.require(this.currentInitialInput.size() > 0, IllegalArgumentException.class, "Input can't be empty");
            return new RoomImpl(this.currentInitialInput, this.memoryAreaWidth, this.memoryAreaHeight,
                    this.memoryAreaValues, this.animationsSpeed);
        }
    }
}
