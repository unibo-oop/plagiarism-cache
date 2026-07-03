package org.gitgud.model.remote.fetch;

import java.util.Arrays;
import java.util.Optional;

import org.gitgud.model.utils.ProgressTask;
import org.gitgud.utils.Log;

/**
*
*/
public enum FetchTask implements ProgressTask {

    /**
    *
    */
    COUNTING_OBJECTS("remote: Counting objects", "fetching.counting.objects", false),
    /**
    *
    */
    FINDING_SOURCES("remote: Finding sources", "fetching.finding.sources", false),
    /**
    *
    */
    GETTING_SIZES("remote: Getting sizes", "fetching.getting.sizes", false),
    /**
    *
    */
    COMPRESSING_OBJECTS("remote: Compressing objects", "fetching.compressing.objects", false),
    /**
    *
    */
    RECEIVING_OBJECTS("Receiving objects", "fetching.receiving.objects", true),
    /**
    *
    */
    RESOLVING_DELTAS("Resolving deltas", "fetching.resolving.deltas", true),
    /**
    *
    */
    UPDATING_REFERENCES("Updating references", "fetching.updating.references", true),
    /**
     *
     */
    UNKNOWN_TASK("Unknown task", "fetching.unknown.task", false);

    private String jgitMessage;
    private String taskKey;
    private boolean hasEndTask;

    FetchTask(final String jgitMessage, final String stringKey, final boolean hasEndTask) {
        this.jgitMessage = jgitMessage;
        taskKey = stringKey;
        this.hasEndTask = hasEndTask;
    }

    @Override
    public FetchTask getByJgitMessage(final String jgitMessage) {
        final Optional<FetchTask> search = Arrays.stream(FetchTask.values())
                .filter(pt -> jgitMessage.contains(pt.jgitMessage)).findAny();
        if (search.isPresent()) {
            return search.get();
        } else {
            Log.getLogger().warning("Unknown task in FetchTask: " + jgitMessage);
            return UNKNOWN_TASK;
        }
    }

    @Override
    public String getTaskKey() {
        return taskKey;
    }

    @Override
    public ProgressTask getUnknownTask() {
        return UNKNOWN_TASK;
    }

    @Override
    public boolean hasEndTask() {
        return hasEndTask;
    }

}
