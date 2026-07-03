package org.gitgud.application.log;

import java.util.List;

import org.gitgud.utils.Pair;

import javafx.scene.paint.Color;

interface LogsView {

    void addColumn();

    void addLine();

    void attachController(LogsDisplayController controller);

    void drawCommit(Pair<Integer, Integer> coordinate, Color color);

    void drawLink(Pair<Integer, Integer> start, Pair<Integer, Integer> end, boolean exiting, Color color);

    void endLogs();

    void resetView();

    void writeNewRecord(int index, String code, String shtMsg, String author, String color, List<String> branches, // NOPMD
            List<String> parents);
}
