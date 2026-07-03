package org.gitgud.application.diff;

import java.util.Map;

import javafx.scene.paint.Color;

interface DiffDetailsView {

    void setAuthor(String name, String initials, Color color);

    void setDate(String date);

    void setHash(String hash, String longHash);

    void setMail(String mail);

    void setMessage(String message);

    void setParents(Map<String, String> parents);

    void setTitle(String title);

}
