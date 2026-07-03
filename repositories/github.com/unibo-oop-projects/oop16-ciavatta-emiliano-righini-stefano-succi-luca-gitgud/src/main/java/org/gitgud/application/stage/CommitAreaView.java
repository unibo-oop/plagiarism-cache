package org.gitgud.application.stage;

import javafx.scene.paint.Color;

interface CommitAreaView {

    void attachController(CommitAreaController ctrl);

    void clearTaCommit();

    void setCommitter(String name, String email, String initials, Color color);
}
