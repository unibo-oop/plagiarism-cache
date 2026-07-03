package org.gitgud;

import org.gitgud.application.ApplicationControllerImpl;
import org.gitgud.utils.Log;

/**
 * The entry point of application.
 */
public final class EntryPoint {

    private EntryPoint() {
    }

    /**
     * @param args
     *            nothing
     */
    public static void main(final String[] args) {
        Log.getLogger().info("Starting application..");
        ApplicationControllerImpl.initController();
    }

}
