package controller;

import java.awt.Dimension;

import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JPanel;

import model.pkglevels.GameWindowModelImpl;
import model.pkglevels.PlumberModel;
import model.pkglevels.PlumberModelImpl;
import model.pkgplayer.Player;
import view.GameLevel;
import view.GameWindow;

/**
 * PlumberController interface implementation.
 * 
 * 
 *
 */
public final class PlumberControllerImpl implements PlumberController {

    private static final PlumberControllerImpl SINGLETON = new PlumberControllerImpl();

    private PlumberModelImpl pl;
    private Player currentPlayer;

    private final GameWindowControllerImpl contr = GameWindowControllerImpl.getSingleton();

    private PlumberControllerImpl() {

    }

    /**
     * method that returns the current controller instance.
     * 
     * @return controller instance
     */
    public static PlumberControllerImpl getControllerInstance() {
        return PlumberControllerImpl.SINGLETON;
    }

    @Override
    public ActionListener modelSolutionListener() {
        return pl.solutionListener();
    }

    @Override
    public void addModel(final PlumberModel model) {
        this.pl = (PlumberModelImpl) model;
    }

    @Override
    public void setPlayer(final Player p) {
        // this.pl.pl=p;
        currentPlayer = p;
    }

    @Override
    public Player getPlayer() {
        return this.currentPlayer;
    }

    @Override
    public PlumberModel getModel() {
        return this.pl;
    }

    @Override
    public GameWindow getView() {
        return GameWindowControllerImpl.getSingleton().getView();
    }

    @Override
    public void enableView() {
        GameWindowControllerImpl.getSingleton().enableWindow();
    }

    @Override
    public GameLevel getLevel() {
        return this.pl.getGameLev();
    }

    @Override
    public GameWindowModelImpl getGameWindowModel() {
        return contr.getModel();
    }

    @Override
    public void createLevel(final int lines, final int columns, final JPanel panel, final int newX, final int newY) {
        this.getModel().createLevelDesign(lines, columns, panel, newX, newY);
    }

    @Override
    public void getFailure(final String message, final String title) {
        this.getModel().showFailureOptions(title, message);
    }

    @Override
    public void readSolutionsFromFile(final File f) {
        this.getModel().readSolutions(f);
    }

    @Override
    public void initializeLevel() {
        this.getModel().inizializeCountersStatistics();
    }

    @Override
    public int getLines() {
        return this.getModel().getLines();
    }

    @Override
    public int getColumns() {
        return this.getModel().getColumns();
    }

    /**
     * Method that sets the new location of the GUI.
     * 
     * 
     * @param frame
     *            window to center
     */
    public static void centreWindow(final Window frame) {
        final Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        final int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
        final int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
        frame.setLocation(x, y);
    }
}
