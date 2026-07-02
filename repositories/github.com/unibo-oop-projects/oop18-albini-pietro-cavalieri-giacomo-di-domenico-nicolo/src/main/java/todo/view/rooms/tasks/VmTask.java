package todo.view.rooms.tasks;

import todo.controller.ExecutionController;
import todo.controller.RoomController;
import todo.view.rooms.AnimationsSpeed;
import todo.view.rooms.RoomEventListener;
import todo.view.screens.RoomScreen;

public class VmTask extends AbstractRoomTask {
    public VmTask(final RoomController roomController, final RoomScreen roomScreen,
            final AnimationsSpeed animationsSpeed, final ExecutionController execution,
            final RoomEventListener roomEventLister) {
        super(roomController, roomScreen, animationsSpeed, execution, roomEventLister);
    }

    @Override
    public boolean isDone() {
        return this.execution.hasFinished() && areAllPendingTasksDone();
    }

    @Override
    protected boolean canGo() {
        return areAllPendingTasksDone();
    }

    @Override
    protected void onAfterTick() {
    }
}
