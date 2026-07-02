package todo.view.rooms.tasks;

import todo.controller.ExecutionController;
import todo.controller.RoomController;
import todo.view.rooms.AnimationsSpeed;
import todo.view.rooms.RoomEventListener;
import todo.view.screens.RoomScreen;

public class StepTask extends AbstractRoomTask {
    private boolean hasStepped;

    public StepTask(final RoomController roomController, final RoomScreen roomScreen,
            final AnimationsSpeed animationsSpeed, final ExecutionController execution,
            final RoomEventListener roomEventListener) {
        super(roomController, roomScreen, animationsSpeed, execution, roomEventListener);
        this.hasStepped = false;
    }

    @Override
    public boolean isDone() {
        if (!this.execution.isVmAtInstructionBoundary()) {
            this.hasStepped = false;
        }
        return this.hasStepped && areAllPendingTasksDone();
    }

    @Override
    protected boolean canGo() {
        return !this.hasStepped;
    }

    @Override
    protected void onAfterTick() {
        this.hasStepped = true;
    }
}
