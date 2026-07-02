package controller.ballscontroller;

public abstract class PauseThread extends Thread implements Pause {

    private volatile boolean pause = false;
    private volatile boolean isOver = true;

    @Override
    public synchronized void start() {
        this.isOver = false;
        if (!this.isAlive()) {
            super.start();
        } else {
            this.notifyAll();
        }
    }


    @Override
    public synchronized void restart() {
        if (!this.isOver) {
            this.pause = false;
            this.notifyAll();
        }
    }

    @Override
    public void pause() {
        if (!this.isOver) {
            this.pause = true;
            this.interrupt();
        }
    }

    @Override
    public synchronized boolean isPause() {
        return this.pause;
    }

    @Override
    public boolean isOver() {
        return isOver;
    }

    /**
     * set the params for the end (not definitive) for the thread, put it in pause until start
     */
    protected synchronized void over() {
        this.isOver = true;
        while (this.isOver) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
