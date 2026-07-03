package org.gitgud.application.stage;

import java.util.Set;

import org.gitgud.model.utils.ChangeType;
import org.gitgud.utils.Pair;

interface StagingAreaView {

    void attachController(StagingAreaController ctrl);

    void setUntracked(Set<Pair<String, ChangeType>> untracked);

    void setNotStaged(Set<Pair<String, ChangeType>> notStaged);

    void setStaged(Set<Pair<String, ChangeType>> staged);

    void setLabelsLength();
}
