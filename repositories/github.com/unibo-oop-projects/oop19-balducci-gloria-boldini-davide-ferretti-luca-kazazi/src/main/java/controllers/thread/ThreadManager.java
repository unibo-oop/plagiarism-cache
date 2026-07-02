package controllers.thread;

import java.util.LinkedList;
import java.util.List;

public class ThreadManager implements ThreadManagerInterface, Runnable {

    private final List<Thread> threadList;
    private Thread thread;

    /**
     * Constructor for ThreadManager
     */
    public ThreadManager() {
        this.threadList = new LinkedList<>();
        this.thread = new Thread(this, "Thread Manager");
        this.thread.start();
    }

    @Override
    public List<Thread> getThreadList() {
        return this.threadList;
    }

    @Override
    public boolean addThread(final Thread th) {
        return this.threadList.add(th);
    }

    @Override
    public boolean removeThread(final String threadName) {
        return threadList.removeIf(p -> p.getName().equals(threadName));
    }

    @Override
    public Thread getThread(final String threadName) {
        return threadList.stream().filter(p -> p.getName().equals(threadName)).findFirst().get();
    }

    @Override
    public Thread getLevelThread() {
        return threadList.stream().filter(p -> p.getName().equals("Level Thread")).findFirst().get();
    }

    @Override
    public void run() {

    }

}
