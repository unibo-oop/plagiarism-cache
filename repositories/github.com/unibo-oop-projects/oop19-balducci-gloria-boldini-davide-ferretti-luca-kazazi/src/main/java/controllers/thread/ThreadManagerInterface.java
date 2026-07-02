package controllers.thread;

import java.util.List;

public interface ThreadManagerInterface {

    /**
     * @param
     * 
     *                  method to get thread list
     * 
     * @return thread list
     *
     */
    List<Thread> getThreadList();

    /**
     * @param th
     * 
     *                  method to add a thread into thread list
     * 
     * @return boolean
     *
     */
    boolean addThread(Thread th);

    /**
     * @param threadName
     * 
     *                   method to remove a thread from the list by threadName
     * 
     * @return boolean
     *
     */
    boolean removeThread(String threadName);

    /**
     * @param threadName
     * 
     *                   method to get a Thread by name
     * 
     * @return Thread
     */
    Thread getThread(String threadName);

    /**
     * @param
     * 
     *                   method to get level Thread
     * 
     * @return levelThread
     *
     */
    Thread getLevelThread();

}
