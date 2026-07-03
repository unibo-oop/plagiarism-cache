package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import controller.GameWindowControllerImpl;
import controller.PlumberControllerImpl;
import model.pkglevels.ImageLoaderImpl;
import model.pkglevels.Pipe;
import model.pkglevels.PlumberModelImpl;
import model.pkgplayer.Player;
import model.pkgplayer.PlayerImpl;
import view.*;

/**
 * Class that build a normal level.
 * 
 * 
 *
 */
public class GameLevelImpl extends JFrame implements GameLevel {
    private static final long serialVersionUID = 1L;
    private boolean isACustomLevel = false;
    private int updatedWidth = 67, updatedHeight = 64;
    private JButton btn1;
    private Timer timer = null;
    private int righe, colonne;
    private JPanel gameGrid;

    private final PlumberControllerImpl controller = PlumberControllerImpl.getControllerInstance();

    private List<Pipe> allTubes = new ArrayList<>();
    private JLabel lblContMosse;
    private JProgressBar progress;

    private GameWindow gm;

    private int curr;

    /**
     * return the game window.
     * 
     * @return game window view
     */
    public GameWindow getWindow() {
        return this.gm;
    }

    /**
     * Return the customLevel variable.
     * 
     * @return custom variable
     */
    public boolean isCustom() {
        return this.isACustomLevel;
    }

    /**
     * Get the current timer.
     * 
     * @return timer
     */
    public Timer getTimer() {
        return this.timer;
    }

    /**
     * get the pipe list.
     * 
     * @return pipe list
     */
    public List<Pipe> getAllTubes() {
        return this.allTubes;
    }

    @Override
    public JButton getFinish() {
        return this.btn1;
    }

    /**
     * returns the progress bar value.
     * 
     * @return the value
     */
    public int getProgressValue() {
        return this.progress.getValue();
    }

    /**
     * Sets the new value for the progress bar.
     * 
     * @param newValue
     *            value to set
     */
    public void setProgressValue(final int newValue) {
        this.progress.setValue(newValue);
    }

    /**
     * Constructor of the class for a custom level.
     * 
     * @param gameWin
     *            menu window from which the game has been created
     */
    public GameLevelImpl(final GameWindow gameWin) {

        this.isACustomLevel = true;
        this.gm = gameWin;
        setWinListner();
        PlumberControllerImpl.centreWindow(this);
        loadImages();

    }

    /**
     * Constructor for a standard level.
     * 
     * @param player
     *            current player
     * @param game
     *            menù window
     * @param currLvl
     *            current level number
     * @param custom
     *            is a custom level status
     */
    public GameLevelImpl(final Player player, final GameWindow game, final int currLvl, final boolean custom) {

        this.gm = game;
        this.curr = currLvl;

        this.isACustomLevel = custom;
        setWinListner();
        controller.setPlayer(player);
        controller.addModel(new PlumberModelImpl(this, currLvl));

        loadImages();
        if (!isACustomLevel) {

            
            controller.readSolutionsFromFile(ImageLoaderImpl.getLoaderInstance().getFixedFile());
        } else {

            
            controller.readSolutionsFromFile(ImageLoaderImpl.getLoaderInstance().getCustomFIle());
        }

        righe = controller.getLines();

        colonne = controller.getColumns();

        adaptWindowSize(righe, colonne);

        final JPanel mainPanel = new JPanel(new BorderLayout());

        gameGrid = new JPanel(new GridBagLayout());
        final JPanel north = new JPanel(new BorderLayout());
        final JPanel south = new JPanel(new BorderLayout());
        final JPanel labelPanel = new JPanel(new FlowLayout());
        lblContMosse = new JLabel();
        final JLabel lblMosse = new JLabel("N° mosse eseguite: ");
        progress = new JProgressBar();
        progress.setStringPainted(true);
        this.getContentPane().add(BorderLayout.CENTER, gameGrid);

        updateImages(this, righe, colonne);

        final ActionListener progressBarListener = (pb) -> {
            //updates the value of the progress bar
            if (progress.getValue() < 100) {
                progress.setValue(progress.getValue() + 1);

            } else {

               //timeout reached
                controller.getFailure("Time has expired", "Il tempo è scaduto e non hai trovato la soluzione");
            }
        };
        timer = new Timer((1000 * this.curr) / righe, progressBarListener);

        timer.start();
        final GridBagConstraints c = new GridBagConstraints();
        final JButton jb = new JButton();
        setStartAndFinish(jb, ImageLoaderImpl.getLoaderInstance().getStartImage(), 0, 0, c, gameGrid, updatedWidth,
                updatedHeight);
        btn1 = new JButton();
        setStartAndFinish(btn1, ImageLoaderImpl.getLoaderInstance().getEmptyImage(), colonne - 1, righe + 1, c,
                gameGrid, updatedWidth, updatedHeight);

        jb.addActionListener(controller.modelSolutionListener());
        labelPanel.add(lblMosse);
        labelPanel.add(lblContMosse);
        labelPanel.add(progress);
        north.add(labelPanel, BorderLayout.CENTER);

        controller.createLevel(righe, colonne, gameGrid, updatedWidth, updatedHeight);
        mainPanel.add(north, BorderLayout.NORTH);
        mainPanel.add(gameGrid, BorderLayout.CENTER);
        mainPanel.add(south, BorderLayout.SOUTH);

        controller.initializeLevel();
        this.getContentPane().add(mainPanel);
        setWinListner();
        this.setVisible(true);
        PlumberControllerImpl.centreWindow(this);

    }

    @Override
    public void setStartAndFinish(final JButton button, final ImageIcon img, final int lines, final int columns,
            final GridBagConstraints gbc, final JPanel p, final int newX, final int newY) {

        button.setIcon(ImageLoaderImpl.resizeIcon(img, newX, newY));

        gbc.fill = GridBagConstraints.HORIZONTAL;
        button.setPreferredSize(new Dimension(newX, newY));

        gbc.weightx = 0;

        gbc.gridx = lines;
        gbc.gridy = columns;
        p.add(button, gbc);

    }

    void adaptWindowSize(final int l, final int c) {
        if (l > c) {
            this.setSize(Toolkit.getDefaultToolkit().getScreenSize());

        } else {
            this.setSize(600, 600);
        }
    }

    @Override
    public void loadImages() {

        this.allTubes = ImageLoaderImpl.getLoaderInstance().getAllTubes();

    }

    @Override
    public void setWinListner() {
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(final WindowEvent e) {
                
                closeWindow();

            }
        });

    }

    @Override
    public void closeWindow() {
        this.dispose();
        if (!isACustomLevel) {
            this.timer.stop();
        }
        GameWindowControllerImpl.getSingleton().enableWindow();
    }

    /**
     * Method used to update the label content.
     * 
     * @param s
     *            new string to update
     */
    public void update(final String s) {
        lblContMosse.setText(" " + s);
    }

    @Override
    public void updateBtnIcon(final JButton b, final ImageIcon i) {
        b.setIcon(i);
        SwingUtilities.updateComponentTreeUI(this);
    }

    @Override
    public Pipe getTube(final String tubeType) {
        // TODO Auto-generated method stub

        return ImageLoaderImpl.getLoaderInstance().getTube(tubeType);
    }

    /**
     * Method called if the grid is bigger than the Panel, it resize all the
     * images and. call again the loader to set the new dimension to all the
     * images
     * 
     * @param j
     *            frame
     * @param lines
     *            grid lines
     * @param columns
     *            grdi columns
     */
    public void updateImages(final JFrame j, final int lines, final int columns) {
        if (lines > 7 || columns > 7) {
            //55,47
            updatedWidth = (int) (j.getWidth()/(lines+2));
            updatedHeight =  (int) (j.getHeight()/columns+2)-20;
            // updatedHeight = (int) (j.getHeight()/columns+2)-14;
            // System.out.println(updatedHeight);

            ImageLoaderImpl.getLoaderInstance().loadImages(updatedWidth, updatedHeight);
            this.loadImages();
            
        }
    }

    /*@Override
    public void update(Observable arg0, Object arg1) {
        int joptionResult;
        if ((int) arg1 == 0) {

            joptionResult = JOptionPane.showOptionDialog(this, "Hai raggiunto il massimo dei livelli, torna al menù",
                    "max level", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null,
                    new Object[] { "Quit to menu", "Show Level Stats" }, null);
            switch (joptionResult) {
            case 0:
                
                controller.enableView();
                
                this.dispose();
                break;
            case 1:
                this.setEnabled(false);
                new LevelStats(this.curr, this.isCustom(), "", "");
                break;
            default:
                update(arg0, (Object) 0);

            }
        }

    }*/
    /**Show the message.
     * 
     * @param message to show
     * @param title of the window
     * @param buttonMessages button names
     * @return the chosen option
     */
    public int getJOptionResult(final String message, final String title, final String[] buttonMessages) {
        if (buttonMessages.length == 2) {
            return JOptionPane.showOptionDialog(this, title, message, JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.PLAIN_MESSAGE, null, new Object[] { buttonMessages[0], buttonMessages[1] }, null);
        } else {
            return JOptionPane.showOptionDialog(this, title, message, JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.PLAIN_MESSAGE, null,
                    new Object[] { buttonMessages[0], buttonMessages[1], buttonMessages[2] }, null);
        }

    }

}
