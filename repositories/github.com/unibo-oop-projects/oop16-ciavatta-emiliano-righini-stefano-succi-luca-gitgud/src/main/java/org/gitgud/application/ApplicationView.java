package org.gitgud.application;

interface ApplicationView {

    void attachController(ApplicationController ctrl);

    void resetTaskProgress();

    void setTaskProgress(double progress);

}
