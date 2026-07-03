package controller;

import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JPanel;

import model.pkglevels.GameWindowModelImpl;
import view.GameWindow;

/**
 * GameWindow controller class.
 */
public class GameWindowControllerImpl implements GameWindowController {
    private GameWindow gw;
    private GameWindowModelImpl model;
    private static final GameWindowControllerImpl SINGLETON = new GameWindowControllerImpl();

    /**
     * 
     * @return the private instance of the class
     */

    public static GameWindowControllerImpl getSingleton() {
        return SINGLETON;
    }

    @Override
    public void addItems(final GameWindowModelImpl m, final GameWindow g) {
        this.model = m;
        this.gw = g;
        model.addObserver(gw);
    }

    @Override
    public GameWindow getView() {
        return this.gw;
    }

    @Override
    public ActionListener customLevelListener() {
        return model.customLevelClicked();
    }

    @Override
    public void createMenu(final JPanel inputPanel, final Map<JButton, Integer> inputMap,
            final List<Integer> inputLevelList, final File inputFile, final boolean isCustom) {
        model.createLevelsButton(inputPanel, inputMap, inputLevelList, inputFile, isCustom);

    }

    @Override
    public void enableWindow() {
        model.enableWindow();
    }

    @Override
    public GameWindowModelImpl getModel() {
        return this.model;
    }

    @Override
    public void closeWindow() {
        this.model.closeWindow();
    }

}
