package org.gitgud.model.services;

/**
 *
 */
public interface Fetcher {

    /**
     * @return the fetcher interval, in minutes
     */
    int getInterval();

    /**
     * @param interval
     *            the interval, in minutes. 0 if the fetcher should be disabled
     */
    void setInterval(int interval);

    /**
     * Start the fetching task.
     */
    void startFetching();

    /**
     * Stop the fetching task.
     */
    void stopFetching();

}
