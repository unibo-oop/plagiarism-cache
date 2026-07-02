package controller.ballscontroller.launch;

import element.Vector2D;

public class LaunchControllerThreadNoPause implements LaunchControllerPause {

    private final LaunchControllerPause thread;

    public LaunchControllerThreadNoPause(final LaunchControllerPause thread) {
        this.thread = thread;
    }

    @Override
    public boolean isValidVector(final Vector2D v) {
        return this.thread.isValidVector(v);
    }

    @Override
    public void setVector(final Vector2D direction) {
        this.thread.setVector(direction);
    }

    @Override
    public void launch() {
        this.thread.launch();
    }

    @Override
    public void restart() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void pause() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isPause() {
        return this.thread.isPause();
    }

    @Override
    public boolean isOver() {
        return this.thread.isOver();
    }

}
