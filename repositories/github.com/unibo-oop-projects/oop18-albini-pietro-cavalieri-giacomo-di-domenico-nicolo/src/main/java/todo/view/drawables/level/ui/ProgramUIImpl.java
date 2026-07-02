package todo.view.drawables.level.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;

import todo.controller.RoomController;
import todo.launcher.LauncherOptions;
import todo.vm.instructions.Instruction;

public class ProgramUIImpl implements ProgramUI {
    private static final float UI_TO_SCREEN_WIDTH = 0.33f;
    private static final float BOUNDS_DROP_ZONE_HEIGHT = 0.5f;
    private static final int DRAG_TIME = 50;

    private final RoomController roomController;
    private final VerticalGroup programGroup;
    private final DragAndDrop dragAndDrop;
    private final ProgramInstructionActorFactory programFactory;
    private final ActorHolder firstDropZone;
    private final ActorHolder lastDropZone;
    private final LabelsGenerator labelsGenerator;
    private final Skin skin;
    private final List<ProgramInstructionActor> programActors;
    private final List<InstructionActor> commandActors;

    public ProgramUIImpl(final RoomController roomController, final Stage stage) {
        this.roomController = roomController;
        // Get the UI width and create the buttons styling and factories
        final float uiWidth = stage.getWidth() * UI_TO_SCREEN_WIDTH;
        final InstructionActorStyling instructionStyling = new InstructionActorStylingImpl();
        this.labelsGenerator = new LabelsGeneratorImpl();
        final InstructionActorFactory templateFactory = new DraggableTemplateInstructionActorFactory(instructionStyling,
                this, uiWidth / 2);
        final List<Integer> addressSpace = Stream.iterate(1, i -> i + 1)
                                                 .limit(roomController.getLevelController()
                                                                      .getLevelMemoryAddresses()
                                                                      .size())
                                                 .collect(Collectors.toList());
        this.programFactory = new ProgramInstructionActorFactoryImpl(instructionStyling, roomController, this,
                uiWidth / 2, addressSpace, () -> this.labelsGenerator.next());
        this.programActors = new ArrayList<>();
        this.commandActors = new ArrayList<>();
        // Make the skin for some trivial textures
        this.skin = new Skin();
        this.skin.add("ui-bg", new Texture(Gdx.files.internal("assets/ui-bg.png")));
        this.skin.add("play", new Texture(Gdx.files.internal("assets/buttons/play.png")));
        this.skin.add("stop", new Texture(Gdx.files.internal("assets/buttons/stop.png")));
        // Make the drag and drop and the delete zone
        this.dragAndDrop = new DragAndDrop();
        this.dragAndDrop.setDragTime(DRAG_TIME);
        final ActorHolder deleteDropZone = new DeleteDropZone(roomController, this);
        // Create the main layout table that takes the whole window;
        // the program UI takes the final third of the window
        final Table table = new Table();
        table.setBounds(0f, 0f, stage.getWidth(), stage.getHeight());
        table.add(deleteDropZone.getActor()).width(stage.getWidth() - uiWidth).height(stage.getHeight()).left().grow();
        // Divide the program UI in two
        final Table innerTable = new Table();
        innerTable.setWidth(uiWidth);
        innerTable.setHeight(stage.getHeight());
        table.add(innerTable).width(uiWidth).height(stage.getHeight()).right().expand();
        innerTable.setBackground(this.skin.getDrawable("ui-bg"));
        // Create the commands group, where all the possible instructions are present
        final Group commandsGroup = new VerticalGroup();
        commandsGroup.setWidth(uiWidth / 2);
        final Set<Class<? extends Instruction>> allowed = roomController.getLevelController()
                                                                        .getLevelAllowedInstructions();
        final List<Class<? extends Instruction>> instructionsOrder = instructionStyling.getInstructionActorOrder()
                                                                                       .stream()
                                                                                       .filter(c -> allowed.contains(c))
                                                                                       .collect(Collectors.toList());
        for (final Class<? extends Instruction> instruction : instructionsOrder) {
            final InstructionActor commandsButton = templateFactory.fromInstructionClass(instruction);
            commandsGroup.addActor(commandsButton.getActor());
            this.commandActors.add(commandsButton);
        }
        final ScrollPane commandsPane = new ScrollPane(commandsGroup);
        innerTable.add(commandsPane).width(innerTable.getWidth() / 2).expand();
        // Create the program group, where the user will drop the instructions to build
        // the program
        this.programGroup = new VerticalGroup();
        this.firstDropZone = new FirstBoundsDropZone(roomController, this, uiWidth / 2,
                stage.getHeight() * BOUNDS_DROP_ZONE_HEIGHT);
        this.lastDropZone = new LastBoundsDropZone(roomController, this, uiWidth / 2,
                stage.getHeight() * BOUNDS_DROP_ZONE_HEIGHT);
        this.programGroup.addActor(this.firstDropZone.getActor());
        this.programGroup.addActor(this.lastDropZone.getActor());
        final ScrollPane programPane = new ScrollPane(this.programGroup);
        innerTable.add(programPane).width(innerTable.getWidth() / 2).expand();

        if (LauncherOptions.getInstance().isDebugModeEnabled()) {
            table.setDebug(true, true);
        }
        stage.addActor(table);
    }

    @Override
    public DragAndDrop getDragAndDrop() {
        return this.dragAndDrop;
    }

    @Override
    public void refresh(final List<Instruction> instructions) {
        this.commandActors.forEach(
                a -> a.getActor()
                      .setTouchable(this.roomController.isPlaying() ? Touchable.disabled : Touchable.enabled));
        // Clear the actors
        this.programActors.clear();
        // Refresh the internal tables to keep track of the labels
        this.programFactory.refreshUsedLabels(Objects.requireNonNull(instructions));
        this.labelsGenerator.setLabelsToSkip(this.programFactory.getUsedLabels());
        this.labelsGenerator.reset();
        // Make the actors
        final List<ProgramInstructionActor> toAdd = instructions.stream()
                                                                .map(i -> this.programFactory.fromInstruction(i))
                                                                .collect(Collectors.toList());
        this.programActors.addAll(toAdd);
        // Add the actors from the container to the program group
        this.programGroup.clear();
        this.programGroup.addActor(this.firstDropZone.getActor());
        this.programActors.forEach(a -> {
            a.getActor().setTouchable(this.roomController.isPlaying() ? Touchable.disabled : Touchable.enabled);
            this.programGroup.addActor(a.getActor());
        });
        this.programGroup.addActor(this.lastDropZone.getActor());
    }

    @Override
    public void dispose() {
        this.skin.dispose();
    }
}
