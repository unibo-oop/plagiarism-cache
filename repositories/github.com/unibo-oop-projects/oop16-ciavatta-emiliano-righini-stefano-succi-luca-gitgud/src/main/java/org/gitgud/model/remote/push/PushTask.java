package org.gitgud.model.remote.push;

import java.util.Arrays;
import java.util.Optional;

import org.gitgud.model.utils.ProgressTask;
import org.gitgud.utils.Log;

/**
*
*/
public enum PushTask implements ProgressTask {

    /**
    *
    */
    UNKNOWN_TASK("Unknown task", "pushing.unknown.task", false);

    private String jgitMessage;
    private String taskKey;
    private boolean hasEndTask;

    PushTask(final String jgitMessage, final String stringKey, final boolean hasEndTask) {
        this.jgitMessage = jgitMessage;
        taskKey = stringKey;
        this.hasEndTask = hasEndTask;
    }

    @Override
    public PushTask getByJgitMessage(final String jgitMessage) {
        final Optional<PushTask> search = Arrays.stream(PushTask.values())
                .filter(pt -> jgitMessage.contains(pt.jgitMessage))
                .findAny();
        if (search.isPresent()) {
            return search.get();
        } else {
            Log.getLogger().warning("Unknown task in ProgressTask: " + jgitMessage);
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
