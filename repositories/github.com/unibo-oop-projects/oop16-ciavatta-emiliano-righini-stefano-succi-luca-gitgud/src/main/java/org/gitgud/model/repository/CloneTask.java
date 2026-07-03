package org.gitgud.model.repository;

import java.util.Arrays;
import java.util.Optional;

import org.gitgud.model.utils.ProgressTask;

/**
 *
 */
public enum CloneTask implements ProgressTask {

    /**
     *
     */
    COUNTING_OBJECTS("remote: Counting objects", "cloning.counting.objects", false),
    /**
    *
    */
    COMPRESSING_OBJECTS("remote: Compressing objects", "cloning.compressing.objects", false),
    /**
    *
    */
    REVEIVING_OBJECTS("Receiving objects", "cloning.reveiving.objects", true),
    /**
    *
    */
    RESOLVING_DELTAS("Resolving deltas", "cloning.resolving.deltas", true),
    /**
    *
    */
    UPDATING_REFERENCES("Updating references", "cloning.updating.references", false),
    /**
    *
    */
    UNKNOWN_TASK("Unknown task", "cloning.unknown.task", false);

    private String jgitMessage;
    private String taskKey;
    private boolean hasEndTask;

    CloneTask(final String jgitMessage, final String stringKey, final boolean hasEndTask) {
        this.jgitMessage = jgitMessage;
        taskKey = stringKey;
        this.hasEndTask = hasEndTask;
    }

    @Override
    public CloneTask getByJgitMessage(final String jgitMessage) {
        final Optional<CloneTask> search = Arrays.stream(CloneTask.values())
                .filter(pt -> jgitMessage.contains(pt.jgitMessage))
                .findAny();
        if (search.isPresent()) {
            return search.get();
        } else {
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
