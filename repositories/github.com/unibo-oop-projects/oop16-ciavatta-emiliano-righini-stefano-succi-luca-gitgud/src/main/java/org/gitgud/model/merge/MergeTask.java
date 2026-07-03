package org.gitgud.model.merge;

import java.util.Arrays;
import java.util.Optional;

import org.gitgud.model.utils.ProgressTask;
import org.gitgud.utils.Log;

/**
*
*/
public enum MergeTask implements ProgressTask {

    /**
     *
     */
    UNKNOWN_TASK("Unknown task", "merging.unknown.task", false);

    private String jgitMessage;
    private String taskKey;
    private boolean hasEndTask;

    MergeTask(final String jgitMessage, final String stringKey, final boolean hasEndTask) {
        this.jgitMessage = jgitMessage;
        taskKey = stringKey;
        this.hasEndTask = hasEndTask;
    }

    @Override
    public MergeTask getByJgitMessage(final String jgitMessage) {
        final Optional<MergeTask> search = Arrays.stream(MergeTask.values())
                .filter(pt -> jgitMessage.contains(pt.jgitMessage)).findAny();
        if (search.isPresent()) {
            return search.get();
        } else {
            Log.getLogger().warning("Unknown task in MergeTask: " + jgitMessage);
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
