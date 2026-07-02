package todo.view.rooms.tasks;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector2;

import todo.controller.ExecutionController;
import todo.controller.RoomController;
import todo.view.drawables.screens.ResolutionManagerImpl;
import todo.view.entities.Entity;
import todo.view.entities.level.InputBelt;
import todo.view.entities.level.MemoryArea;
import todo.view.entities.level.OutputBelt;
import todo.view.entities.level.Player;
import todo.view.entities.level.ValueBox;
import todo.view.entities.level.ValueBoxImpl;
import todo.view.entities.tasks.LoopableTask;
import todo.view.entities.tasks.LoopableTaskManager;
import todo.view.entities.tasks.common.FloatInterpolateTask;
import todo.view.entities.tasks.common.RunAfterTask;
import todo.view.entities.tasks.common.Vector2InterpolateTask;
import todo.view.rooms.AnimationsSpeed;
import todo.view.rooms.Room;
import todo.view.rooms.RoomEventListener;
import todo.view.screens.RoomScreen;
import todo.vm.Action;
import todo.vm.ActionKind;
import todo.vm.Value;

public abstract class AbstractRoomTask implements LoopableTask {
    private static final float HEIGHT_MOVEMENT_DURATION_MULTIPLIER = 0.25f;
    private static final float UNARY_ANIMATION_JUMP = 32f;
    protected final List<LoopableTaskManager> executingTasks;
    protected final ExecutionController execution;
    private Optional<LoopableTask> playerTask;
    protected final RoomEventListener roomEventListener;
    private final RoomController roomController;
    private final AnimationsSpeed animationsSpeed;
    private final RoomScreen roomScreen;
    private final Room room;
    private final Player player;
    private final float valueBoxSize;
    private final float playerWidth;
    private final Map<ActionKind, Consumer<Action>> animations;

    public AbstractRoomTask(final RoomController roomController, final RoomScreen roomScreen,
            final AnimationsSpeed animationsSpeed, final ExecutionController execution,
            final RoomEventListener roomEventListener) {
        this.playerTask = Optional.empty();
        this.executingTasks = new ArrayList<>();
        this.execution = Objects.requireNonNull(execution);
        this.roomEventListener = Objects.requireNonNull(roomEventListener);
        this.roomController = Objects.requireNonNull(roomController);
        this.roomScreen = Objects.requireNonNull(roomScreen);
        this.animationsSpeed = Objects.requireNonNull(animationsSpeed);
        this.room = this.roomScreen.getRoom();
        this.player = this.room.getPlayer();
        this.valueBoxSize = ResolutionManagerImpl.getInstance()
                                                 .getCurrentAspectRatio()
                                                 .getManifest()
                                                 .getScaledValueBoxSize();
        this.playerWidth = this.roomScreen.getDrawableOf(this.player).getWidth();
        // Define all the supported animations
        this.animations = new EnumMap<>(ActionKind.class);
        this.animations.put(ActionKind.DROP_MAIN_HAND, this::animateDropMainHand);
        this.animations.put(ActionKind.PICK_INPUT, this::animatePickInput);
        this.animations.put(ActionKind.DROP_OUTPUT, this::animateDropOutput);
        this.animations.put(ActionKind.LOCATE_MEMORY_ADDRESS, this::animateLocateMemoryAddress);
        this.animations.put(ActionKind.COPY_FROM, this::animateCopyFrom);
        this.animations.put(ActionKind.COPY_TO, this::animateCopyTo);
        this.animations.put(ActionKind.ADD, this::animateMath);
        this.animations.put(ActionKind.SUB, this::animateMath);
        this.animations.put(ActionKind.INCR, this::animateUnary);
        this.animations.put(ActionKind.DECR, this::animateUnary);
    }

    @Override
    public void onInit() {
    }

    @Override
    public void onTick(final float deltaTime) {
        if (canGo() && !this.execution.hasFinished()) {
            final Action action = this.execution.step();
            if (this.animations.containsKey(action.getKind())) {
                this.animations.get(action.getKind()).accept(action);
            }
            pushFinishedTask();
            onAfterTick();
        }
    }

    @Override
    public void onDestroy() {
    }

    private void animateDropMainHand(final Action action) {
        final ValueBox hand = this.player.getHand().get();
        interpolate(hand.getPosition(), this.player.getPosition().cpy().add(hand.getRelativePosition().x, 0),
                hand::setPosition, this.animationsSpeed.baseSpeed(), Interpolation.bounce);
        call(() -> {
            this.roomScreen.removeEntity(hand);
            this.player.discardHand();
        });
    }

    private void animateDropOutput(final Action action) {
        final OutputBelt belt = this.room.getOutputBelt();
        interpolate(this.player.getPosition(),
                belt.getEndPosition().cpy().add(belt.getHorizontalBoxMargin() - getBoxRelativePositionToPlayer().x, 0),
                anchorHand(this.player::setPosition));
        interpolatePlayerDownUpAndCall(() -> {
            belt.enqueue(this.player.getHand().get());
            this.player.discardHand();
        });
    }

    private void animatePickInput(final Action action) {
        final InputBelt belt = this.room.getInputBelt();
        interpolate(this.player.getPosition(),
                belt.getEndPosition().cpy().add(belt.getHorizontalBoxMargin() - getBoxRelativePositionToPlayer().x, 0),
                this.player::setPosition);
        interpolatePlayerDownUpAndCall(() -> {
            final ValueBox value = belt.poll().get();
            this.player.setHand(value);
            repositionHand();
        });
    }

    private void animateLocateMemoryAddress(final Action action) {
        interpolate(this.player.getPosition(),
                this.room.getMemoryArea()
                         .get()
                         .getCellPosition(action.getMemoryAddress())
                         .cpy()
                         .add(-this.valueBoxSize, 0),
                anchorHand(this.player::setPosition));
    }

    private void animateCopyFrom(final Action action) {
        interpolatePlayerDownUpAndCall(() -> {
            final ValueBox copy = this.room.getMemoryArea().get().getValueBox(action.getMemoryAddress()).get().copy();
            this.player.setHand(registerEntity(copy));
            repositionHand();
        });
    }

    private void animateCopyTo(final Action action) {
        final MemoryArea memoryArea = this.room.getMemoryArea().get();
        interpolatePlayerDownUpAndCall(() -> {
            final Optional<ValueBox> old = memoryArea.getValueBox(action.getMemoryAddress());
            final ValueBox copy = this.player.getHand().get().copy();
            memoryArea.setValueBox(action.getMemoryAddress(), registerEntity(copy));
            old.ifPresent(box -> this.roomScreen.removeEntity(box));
        });
    }

    private void animateMath(final Action action) {
        final float high = this.player.getHeight() + this.valueBoxSize;
        interpolatePlayerHeight(this.player.getHeight(), high);
        interpolatePlayerHeight(high, this.valueBoxSize);
        call(() -> {
            final Value newValue = this.roomController.getExecutionController().get().getMainHandContent();
            final ValueBox newBox = new ValueBoxImpl.Builder().value(newValue).build();
            final Optional<ValueBox> old = this.player.getHand();
            this.player.setHand(registerEntity(newBox.copy()));
            old.ifPresent(box -> this.roomScreen.removeEntity(box));
            repositionHand();
        });
        interpolatePlayerHeight(this.valueBoxSize, this.player.getHeight());
    }

    private void animateUnary(final Action action) {
        final ValueBox box = this.room.getMemoryArea().get().getValueBox(action.getMemoryAddress()).get();
        final Vector2 high = box.getPosition().cpy().add(0, UNARY_ANIMATION_JUMP);
        interpolate(box.getPosition(), high, box::setPosition, HEIGHT_MOVEMENT_DURATION_MULTIPLIER);
        call(() -> {
            final Value newValue = this.roomController.getExecutionController()
                                                      .get()
                                                      .getMemoryAddressContent(action.getMemoryAddress());
            box.setValue(newValue);
        });
        interpolate(high, box.getPosition(), box::setPosition, HEIGHT_MOVEMENT_DURATION_MULTIPLIER);
    }

    private void interpolate(final Vector2 start, final Vector2 end, final Consumer<Vector2> updatePosition) {
        interpolate(start, end, updatePosition, 1f, Interpolation.linear);
    }

    private void interpolate(final Vector2 start, final Vector2 end, final Consumer<Vector2> updatePosition,
            final float speedMultiplier) {
        interpolate(start, end, updatePosition, speedMultiplier, Interpolation.linear);
    }

    private void interpolate(final Vector2 start, final Vector2 end, final Consumer<Vector2> updatePosition,
            final float speedMultiplier, final Interpolation interpolation) {
        if (start.equals(end)) {
            // Avoid animating an interpolation to the same coordinates
            return;
        }
        pushFinishedTask();
        this.playerTask = Optional.of(new Vector2InterpolateTask(start, end, updatePosition,
                this.animationsSpeed.baseSpeed() * speedMultiplier, interpolation));
    }

    private void interpolatePlayerHeight(final float start, final float end) {
        pushFinishedTask();
        this.playerTask = Optional.of(new FloatInterpolateTask(start, end, anchorHand(this.player::setHeight),
                this.animationsSpeed.baseSpeed() * HEIGHT_MOVEMENT_DURATION_MULTIPLIER, Interpolation.linear));
    }

    private void interpolatePlayerDownUpAndCall(final Runnable runnable) {
        interpolatePlayerHeight(this.player.getHeight(), this.valueBoxSize);
        call(runnable);
        interpolatePlayerHeight(this.valueBoxSize, this.player.getHeight());
    }

    private void call(final Runnable runnable) {
        if (this.playerTask.isPresent()) {
            this.playerTask = Optional.of(new RunAfterTask(this.playerTask.get(), runnable));
        } else {
            // There is no point in scheduling this after a task if no task has been set.
            runnable.run();
        }
    }

    private <T> Consumer<T> anchorHand(final Consumer<T> toDecorate) {
        return pos -> {
            toDecorate.accept(pos);
            repositionHand();
        };
    }

    private void repositionHand() {
        this.player.getHand().ifPresent(hand -> {
            hand.setParent(this.player);
            hand.setRelativePosition(getBoxRelativePositionToPlayer());
        });
    }

    private Vector2 getBoxRelativePositionToPlayer() {
        return new Vector2((this.playerWidth - this.valueBoxSize) / 2, this.player.getHeight() - this.valueBoxSize);
    }

    private <T extends Entity> T registerEntity(final T entity) {
        this.roomScreen.addEntities(entity);
        return entity;
    }

    private void pushFinishedTask() {
        if (this.playerTask.isPresent()) {
            final LoopableTaskManager manager = this.player.getLoopableTaskManager();
            manager.add(this.playerTask.get());
            if (!this.executingTasks.contains(manager)) {
                this.executingTasks.add(manager);
            }
            this.playerTask = Optional.empty();
        }
    }

    @Override
    public abstract boolean isDone();

    protected abstract boolean canGo();

    protected abstract void onAfterTick();

    protected boolean areAllPendingTasksDone() {
        if (this.executingTasks.stream().allMatch(task -> task.getTasksCount() == 0)) {
            this.executingTasks.clear();
            this.roomEventListener.animationsCompleted();
            return true;
        }
        return false;
    }
}
