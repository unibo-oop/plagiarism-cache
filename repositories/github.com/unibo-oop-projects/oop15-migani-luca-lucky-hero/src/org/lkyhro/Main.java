package org.lkyhro;


import org.lkyhro.gui.InitialGUI;
import org.lkyhro.gui.StartMenu;

/**
 * Created by  Migani Luca on 18/02/2016.
 */
public class Main {
    public static void main(String[] args) {
        InitialGUI initialGUI=new InitialGUI();
        StartMenu startMenu=new StartMenu(initialGUI);
        initialGUI.switchPanel(startMenu.$$$getRootComponent$$$());

    }
}
