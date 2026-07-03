package org.gitgud.model.services;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

import org.gitgud.events.repository.RepositoryUpdatedListener;
import org.gitgud.exceptions.GitGudUnckeckedException;
import org.gitgud.utils.Utils;

/**
 * Used to check all repository updates from the file system.
 */
public class RepositoryWatcher implements Watcher {

    private static final int POLL_TIMEOUT = 500;
    private static final int THRESHOLD_TIMEOUT = 100;
    private static final int SLEEP_TIME = 100;

    private static int anotherInstanceLive;

    private final List<RepositoryUpdatedListener> listeners;
    private final Thread watcherThread;
    private final Thread notifierThread;
    private final AtomicLong lastRepositoryUpdated;
    private final AtomicLong lastContentUpdated;
    private final AtomicBoolean hashRepositoryUpdates;
    private final AtomicBoolean hashContentUpdates;

    private volatile boolean stopped;

    /**
     * @param repositoryPath
     *            the repository path to watch
     */
    public RepositoryWatcher(final Path repositoryPath) {
        if (anotherInstanceLive > 0) {
            throw new IllegalStateException(
                    "Only one RepositoryContentWatcher is permitted. Close the other watcher before create a new watcher.");
        }

        listeners = new LinkedList<>();
        watcherThread = new Thread(createWatcherRunnable(repositoryPath), "RepositoryContentWatcher Thread");
        notifierThread = new Thread(createNotifierRunnable(), "RepositoryContentNotifier Thread");
        lastRepositoryUpdated = new AtomicLong(System.currentTimeMillis());
        lastContentUpdated = new AtomicLong(System.currentTimeMillis());
        hashRepositoryUpdates = new AtomicBoolean(false);
        hashContentUpdates = new AtomicBoolean(false);
    }

    @Override
    public void addRepositoryUpdatedListener(final RepositoryUpdatedListener listener) {
        Objects.requireNonNull(listener);
        listeners.add(listener);
    }

    @Override
    public void startWatching() {
        anotherInstanceLive++;
        watcherThread.start();
        notifierThread.start();
    }

    @Override
    public void stopWatching() {
        stopped = true;
        anotherInstanceLive--;
    }

    private Runnable createNotifierRunnable() {
        return () -> {
            while (!stopped) {
                if (hashRepositoryUpdates.get()
                        && lastRepositoryUpdated.get() + THRESHOLD_TIMEOUT < System.currentTimeMillis()) {
                    hashRepositoryUpdates.set(false);
                    Utils.doHardWork(() -> listeners.forEach(snl -> snl.onRepositoryUpdated()));
                }

                if (hashContentUpdates.get()
                        && lastContentUpdated.get() + THRESHOLD_TIMEOUT < System.currentTimeMillis()) {
                    hashContentUpdates.set(false);
                    Utils.doHardWork(() -> listeners.forEach(snl -> snl.onContentUpdated()));
                }

                try {
                    Thread.sleep(SLEEP_TIME);
                } catch (final Exception e) {
                    break;
                }
            }
        };
    }

    private Runnable createWatcherRunnable(final Path repositoryPath) {
        return () -> {
            try (WatchService watchService = FileSystems.getDefault().newWatchService()) {
                repositoryPath.register(watchService, StandardWatchEventKinds.ENTRY_CREATE,
                        StandardWatchEventKinds.ENTRY_DELETE, StandardWatchEventKinds.ENTRY_MODIFY);

                while (!stopped) {
                    final WatchKey wk = watchService.poll(POLL_TIMEOUT, TimeUnit.MILLISECONDS);

                    if (stopped || wk == null) {
                        continue;
                    }

                    for (final WatchEvent<?> event : wk.pollEvents()) {
                        final Path path = (Path) event.context();

                        if (path.toString().equals(".git")) {
                            hashRepositoryUpdates.set(true);
                            lastRepositoryUpdated.set(System.currentTimeMillis());
                        } else {
                            hashContentUpdates.set(true);
                            lastContentUpdated.set(System.currentTimeMillis());
                        }
                    }

                    wk.reset();
                }
            } catch (final Exception e) {
                throw new GitGudUnckeckedException("Failed to create file watcher for watching repository");
            }
        };
    }

}
