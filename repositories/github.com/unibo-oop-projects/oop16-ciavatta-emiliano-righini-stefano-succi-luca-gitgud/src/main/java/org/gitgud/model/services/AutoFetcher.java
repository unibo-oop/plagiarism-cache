package org.gitgud.model.services;

import java.util.Set;

import org.gitgud.model.Model;
import org.gitgud.model.remote.RemoteModel;
import org.gitgud.model.remote.fetch.FetchParamBuilder;
import org.gitgud.model.utils.CommandStatus;
import org.gitgud.utils.Log;

/**
 * Used to check if there are fetch updates.
 */
public class AutoFetcher implements Fetcher {

    private static final int MINUTES_TO_MILLISECONDS = 60000;
    private static final int FETCH_DEFAULT_INTERVAL = 1;

    private static int anotherInstanceLive;

    private final RemoteModel remoteModel;
    private final Thread thread;
    private volatile boolean stopped;
    private volatile boolean paused;
    private int interval;

    /**
     * @param modal
     *            the application model
     */
    public AutoFetcher(final Model modal) {
        if (anotherInstanceLive > 0) {
            throw new IllegalStateException(
                    "Only one AutoFetcher is permitted. Close the other fetcher before create a new fetcher.");
        }

        remoteModel = modal.getRemoteModel();
        thread = new Thread(createFetcherRunnable(), "AutoFetcher Thread");

        setInterval(FETCH_DEFAULT_INTERVAL);
    }

    @Override
    public int getInterval() {
        return interval;
    }

    @Override
    public void setInterval(final int interval) {
        this.interval = interval;
    }

    @Override
    public void startFetching() {
        anotherInstanceLive++;
        thread.start();
    }

    @Override
    public void stopFetching() {
        stopped = true;
        anotherInstanceLive--;

        if (paused) {
            thread.interrupt();
        }
    }

    private Runnable createFetcherRunnable() {
        return () -> {
            while (!stopped) {
                if (interval <= 0) {
                    paused = true;
                    try {
                        Thread.sleep(MINUTES_TO_MILLISECONDS);
                        paused = false;
                    } catch (final Exception e) {
                        paused = false;
                    }
                    continue;
                }

                final Set<String> remotes = remoteModel.getOperations().getRemotes().keySet();
                remotes.forEach(remoteName -> {
                    final FetchParamBuilder fetchParamBuilder = FetchParamBuilder.createFetchParamBuilder();
                    fetchParamBuilder.checkValidity(false).isDryRun(false).remoteName(remoteName);

                    final CommandStatus status = remoteModel.getActions().fetch(fetchParamBuilder.build());
                    if (status == CommandStatus.ERROR) {
                        Log.getLogger()
                                .warning("Can't fetch remote '" + remoteName + "'. Retry in " + interval + " minute");
                    }
                });

                if (stopped) {
                    return;
                }

                paused = true;
                try {
                    Thread.sleep(interval * MINUTES_TO_MILLISECONDS);
                    paused = false;
                } catch (final Exception e) {
                    paused = false;
                }
            }
        };
    }

}
