package org.gitgud.application.utils;

class ApplicationPropertyImpl implements ApplicationProperty {

    private String mergeMode = "fastforward-ifpossible";
    private final ColorManager remoteColorManager;

    ApplicationPropertyImpl() {
        remoteColorManager = new ColorManagerImpl();
    }

    @Override
    public String getMergeMode() {
        return mergeMode;
    }

    @Override
    public ColorManager getRemoteColorManager() {
        return remoteColorManager;
    }

    @Override
    public void setMergeMode(final String mergeMode) {
        this.mergeMode = mergeMode;
    }

}
