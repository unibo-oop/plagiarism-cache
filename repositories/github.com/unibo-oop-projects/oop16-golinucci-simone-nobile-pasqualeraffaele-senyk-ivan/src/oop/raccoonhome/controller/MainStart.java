package oop.raccoonhome.controller;

import oop.raccoonhome.gui.GUIMain;

/**
 * 
 *
 */
public final class MainStart {

    private MainStart() {
    }

    /**
     * 
     * @param args
     *            Starts GUI
     * @throws Exception
     *             Throws an Exception in case of Fail
     */
    public static void main(final String[] args) throws Exception {
        GUIMain starts = new GUIMain();
        starts.startGUI(args);

    }
}
