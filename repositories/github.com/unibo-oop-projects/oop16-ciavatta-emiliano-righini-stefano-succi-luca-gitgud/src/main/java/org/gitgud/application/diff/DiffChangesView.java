package org.gitgud.application.diff;

import java.util.List;

import org.gitgud.model.utils.ChangeType;
import org.gitgud.utils.Pair;

interface DiffChangesView {

    void attachController(DiffOverviewController ctrl, int id);

    void setChanges(List<Pair<String, ChangeType>> changes);

}
