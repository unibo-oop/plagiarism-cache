package org.gitgud.application.diff;

import org.gitgud.model.utils.ChangeType;

import javafx.scene.image.Image;

interface DiffViewerView {

    void addAddedLine(String line, String lineToNumber);

    void addNewBinaryImage(Image image);

    void addOldBinaryImage(Image image);

    void addRemovedLine(String line, String lineFromNumber);

    void addUnchangedLine(String line, String lineFromNumber, String toLineNumber);

    void attachController(DiffViewerController ctrl);

    void clearDifferences();

    void closeHunk();

    void openHunk(String header);

    void setAdditionalsInfo(String additionalsInfo);

    void setChangeType(ChangeType changeType);

    void setFileName(String fileName);

    void setLines(String linesAdded, String linesRemoved);

}
