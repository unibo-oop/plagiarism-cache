package todo.view.screens;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;

import todo.controller.RoomController;
import todo.controller.events.EventManager;
import todo.controller.events.ExecutionStateChangedEvent;
import todo.controller.events.GameStateChangedEvent;
import todo.utils.UIConstants;
import todo.view.drawables.Drawable;
import todo.view.drawables.DrawableFactory;
import todo.view.drawables.DrawableFactoryImpl;
import todo.view.drawables.level.ui.ProgramUI;
import todo.view.drawables.level.ui.ProgramUIImpl;
import todo.view.drawables.level.ui.dialogs.DialogResponse;
import todo.view.drawables.level.ui.dialogs.GameDialogImpl;
import todo.view.drawables.level.ui.playback.PlaybackControls;
import todo.view.drawables.level.ui.playback.PlaybackControlsImpl;
import todo.view.drawables.screens.ResolutionManagerImpl;
import todo.view.entities.Entity;
import todo.view.entities.level.ValueBox;
import todo.view.entities.level.ValueBoxImpl;
import todo.view.entities.tasks.LoopableTaskManager;
import todo.view.entities.tasks.QueuedLoopableTaskManager;
import todo.view.menu.MenuObserver;
import todo.view.rooms.AnimationsSpeed;
import todo.view.rooms.AnimationsSpeedImpl;
import todo.view.rooms.Room;
import todo.view.rooms.RoomEventListener;
import todo.view.rooms.RoomEventListenerImpl;
import todo.view.rooms.RoomImpl;
import todo.view.rooms.tasks.StepTask;
import todo.view.rooms.tasks.VmTask;

public final class RoomScreenImpl implements RoomScreen {
    private final RoomController roomController;
    private final AnimationsSpeed animationsSpeed;
    private final RoomEventListener eventListener;
    private final LoopableTaskManager taskManager;
    private final List<Entity> entities;
    private final List<Entity> newEntities;
    private final List<Entity> entitiesToRemove;
    private final SortedMap<Integer, List<Drawable<? extends Entity>>> drawables;
    private final Map<Entity, Drawable<? extends Entity>> entitiesToDrawables;
    private final SpriteBatch batch;
    private final ProgramUI programUI;
    private final PlaybackControls playbackControls;
    private final Stage stage;
    private final DrawableFactory drawableFactory;
    private Room room;

    public RoomScreenImpl(final RoomController roomController, final MenuObserver menuObserver) {
        this.roomController = Objects.requireNonNull(roomController);
        this.eventListener = new RoomEventListenerImpl(roomController, this, Objects.requireNonNull(menuObserver),
                roomController.getLevelController().getEventManager());
        this.entities = new ArrayList<>();
        this.newEntities = new ArrayList<>();
        this.entitiesToRemove = new ArrayList<>();
        this.drawables = new TreeMap<>();
        this.entitiesToDrawables = new HashMap<>();
        this.taskManager = new QueuedLoopableTaskManager();
        // Create the animations speed
        this.animationsSpeed = new AnimationsSpeedImpl(UIConstants.ANIMATIONS_BASE_SECONDS,
                UIConstants.ANIMATIONS_INCREMENTS, UIConstants.ANIMATIONS_MIN_INCREMENTS,
                UIConstants.ANIMATIONS_MAX_INCREMENTS);
        // Create the UI
        this.stage = new Stage(ResolutionManagerImpl.getInstance().getCurrentViewport());
        Gdx.input.setInputProcessor(this.stage);
        this.programUI = new ProgramUIImpl(roomController, this.stage);
        this.playbackControls = new PlaybackControlsImpl(roomController, this, menuObserver, this.animationsSpeed);
        // Make the sprite batch and the drawables factory
        this.batch = new SpriteBatch();
        this.drawableFactory = new DrawableFactoryImpl();
        // Register the events
        final EventManager eventManager = this.roomController.getLevelController().getEventManager();
        eventManager.listen(GameStateChangedEvent.class, this::refreshScreen);
        eventManager.listen(ExecutionStateChangedEvent.class, e -> {
            switch (e) {
                case PLAY:
                    this.taskManager.add(new VmTask(roomController, this, this.animationsSpeed,
                            this.roomController.getExecutionController().get(), this.eventListener));
                    this.eventListener.animationsCompleted();
                    break;
                case STEP:
                    this.taskManager.add(new StepTask(roomController, this, this.animationsSpeed,
                            this.roomController.getExecutionController().get(), this.eventListener));
                    this.eventListener.animationsCompleted();
                    break;
                case STOP:
                    this.taskManager.removeAll();
                    refreshRoom();
                    break;
                default:
                    throw new IllegalArgumentException("Not every enum state is handled");
            }
        });
        // Run the handlers for initialization
        refreshRoom();
        refreshScreen(new GameStateChangedEvent());
    }

    @Override
    public void show() {
    }

    @Override
    public void render(final float delta) {
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
        this.taskManager.tick(delta);
        this.playbackControls.refresh();
        updateEntitiesList();
        this.entities.forEach(e -> e.update(delta));
        this.batch.begin();
        this.drawables.forEach((z, dl) -> dl.forEach(d -> d.onDraw(this.batch, delta)));
        this.batch.end();
        this.stage.act(delta);
        this.stage.draw();
    }

    private void updateEntitiesList() {
        this.newEntities.forEach(e -> {
            this.entities.add(e);
            final Drawable<? extends Entity> drawable = this.drawableFactory.fromEntity(e);
            this.entitiesToDrawables.put(e, drawable);
            this.drawables.putIfAbsent(drawable.getZIndex(), new ArrayList<>());
            this.drawables.get(drawable.getZIndex()).add(drawable);
        });
        this.entitiesToRemove.forEach(e -> {
            this.entities.remove(e);
            final Drawable<? extends Entity> drawable = this.entitiesToDrawables.remove(e);
            if (drawable != null) {
                this.drawables.get(drawable.getZIndex()).remove(drawable);
            }
        });
        this.newEntities.clear();
        this.entitiesToRemove.clear();
    }

    @Override
    public void resize(final int width, final int height) {
        this.stage.setViewport(ResolutionManagerImpl.getInstance().getCurrentViewport());
        this.stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
        this.drawables.forEach((z, dl) -> dl.forEach(d -> d.onDestroy()));
        this.programUI.dispose();
        this.playbackControls.dispose();
    }

    @Override
    public Room getRoom() {
        return this.room;
    }

    @Override
    public Stage getStage() {
        return this.stage;
    }

    @Override
    public ProgramUI getProgramUI() {
        return this.programUI;
    }

    @Override
    public PlaybackControls getPlaybackControls() {
        return this.playbackControls;
    }

    @Override
    public Drawable<? extends Entity> getDrawableOf(final Entity entity) {
        return this.entitiesToDrawables.get(entity);
    }

    @Override
    public void addEntities(final Entity... entities) {
        this.newEntities.addAll(Arrays.asList(entities));
    }

    @Override
    public void removeEntity(final Entity entity) {
        this.entitiesToRemove.add(entity);
    }

    @Override
    public void showDialog(final String text, final Consumer<DialogResponse> onButtonPress,
            final GameDialogImpl.ButtonColor color, final DialogResponse... allowedResponses) {
        new GameDialogImpl(this.stage, text, onButtonPress, color, allowedResponses).show();
    }

    @Override
    public void showLevelDescription() {
        new GameDialogImpl(this.stage, this.roomController.getLevelController().getLevelDescription(), p -> {
            // Do nothing on button press.
        }, GameDialogImpl.ButtonColor.GREEN, DialogResponse.OK).show();
    }

    private void refreshScreen(final GameStateChangedEvent event) {
        this.programUI.refresh(this.roomController.getInstructions());
        this.playbackControls.refresh();
    }

    private void refreshRoom() {
        final List<ValueBox> initialInput = this.roomController.getLevelController()
                .getCurrentInput()
                .stream()
                .filter(val -> val.isPresent())
                .map(val -> new ValueBoxImpl.Builder().value(val).build())
                .collect(Collectors.toList());
        final List<Optional<ValueBox>> initialMemory = this.roomController.getLevelController()
                .getLevelMemoryAddresses()
                .stream()
                .map(val -> val.isPresent() ? new ValueBoxImpl.Builder().value(val).build() : null)
                // This is done in two steps otherwise Java's
                // type inference screws up
                .map(val -> Optional.ofNullable(val))
                .collect(Collectors.toList());
        this.room = new RoomImpl.Builder(this.animationsSpeed)
                .initialInput(initialInput)
                .memoryArea(this.roomController.getLevelController().getLevelMemoryAreaWidth(),
                            this.roomController.getLevelController().getLevelMemoryAreaHeight(), initialMemory)
                .build();
        // Clear everything
        this.entities.clear();
        this.entitiesToDrawables.clear();
        this.drawables.clear();
        // Add the new entities for the new room
        addEntities(this.room.getFloor(), this.room.getInputBelt(), this.room.getOutputBelt(), this.room.getPlayer());
        this.room.getMemoryArea().ifPresent(ma -> addEntities(ma));
        this.room.getMemoryArea().ifPresent(ma -> ma.getValueBoxes().forEach(b -> addEntities(b)));
        this.room.getInputBelt().getValueBoxes().forEach(b -> addEntities(b));
    }
}
