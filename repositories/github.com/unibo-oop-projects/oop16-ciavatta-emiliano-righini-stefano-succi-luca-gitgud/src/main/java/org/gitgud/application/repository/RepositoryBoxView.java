package org.gitgud.application.repository;

interface RepositoryBoxView {

    void attachController(RepositoryBoxController ctrl);

    void changeSection(String sectorKey);

    void markError(String keyDestination);

    void onError(String message);

    void onSuccess(String message);

    void updateCloningProgressOperation(String operationName);

    void updateCloningProgressPercentage(double progress);

    void writeField(String keyDestination, String value);

}
