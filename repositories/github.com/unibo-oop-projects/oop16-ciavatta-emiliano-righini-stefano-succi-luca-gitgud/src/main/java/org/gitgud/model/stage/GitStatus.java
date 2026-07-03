package org.gitgud.model.stage;

import java.util.Optional;
import java.util.Set;

import org.eclipse.jgit.api.Git;
import org.gitgud.model.utils.ChangeType;
import org.gitgud.utils.Pair;

interface GitStatus {

    Set<Pair<String, ChangeType>> getStatusUntracked(Git git);

    Set<Pair<String, ChangeType>> getStatusNotStaged(Git git);

    Set<Pair<String, ChangeType>> getStatusStaged(Git git);

    Optional<StageError> getError();
}
