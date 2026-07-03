package org.gitgud.application.about;

interface AboutBoxView {

    void attachController(AboutBoxController ctrl);

    void showLink(String title, String link);
}
