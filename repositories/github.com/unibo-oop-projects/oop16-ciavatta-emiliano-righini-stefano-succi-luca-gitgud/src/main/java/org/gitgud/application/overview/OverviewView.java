package org.gitgud.application.overview;

import org.gitgud.utils.Pair;

import javafx.scene.paint.Color;

interface OverviewView {

    void addLocalBranch(String branchName);

    void addRemote(String remoteName, Pair<String, String> upstreams, Color color);

    void addRemoteBranch(String remoteName, String branchName);

    void addTag(String tagName);

    void attachController(OverviewController ctrl);

    void resetBranches();

    void resetRemotes();

    void resetTags();

    void setCheckedOutBranch(String branchName);

    void setRepositoryName(String repositoryName);

    void setUpdates(String branchName, Pair<Integer, Integer> updates);

}
