package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import controller.GameWindowControllerImpl;
import controller.PlumberControllerImpl;
import model.pkglevels.GameWindowModelImpl;
import model.pkglevels.ImageLoaderImpl;
import model.pkgplayer.Player;

import javax.swing.JButton;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

/**
 * GameWindow View, implements Observer so it can be notified by the model.
 * 
 * 
 *
 */
public class GameWindow extends JFrame implements Observer {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private final Map<JButton, Integer> levelMap = new HashMap<>();
    private final Map<JButton, Integer> customLevelMap = new HashMap<>();

    private final Player p1;
    private final List<Integer> fixedLevelNumbers = new ArrayList<>();
    private final List<Integer> customLevelNumbers = new ArrayList<>();

    private JPanel customLevelPanel;
    private JLabel userBestScore;
    private final JButton logOut;
    private final GameWindowControllerImpl contr = GameWindowControllerImpl.getSingleton();

    /**
     * Class constructor.
     * 
     * @param player
     *            current player
     */
    public GameWindow(final Player player) {
        p1 = player;
        contr.addItems(new GameWindowModelImpl(p1), this);
        this.setSize(700, 700);
        final JPanel mainPanel = new JPanel(new BorderLayout());
        final JPanel southPanel = new JPanel(new BorderLayout());
        final JPanel northPanel = new JPanel(new BorderLayout());
        final JLabel customLabel = new JLabel("CUSTOM LEVELS");
        final JLabel standardLabel = new JLabel("STANDARD LEVELS");
        final JPanel userPanel = new JPanel(new FlowLayout());
        final JPanel centralPanel = new JPanel(new BorderLayout());
        final JPanel customP = new JPanel(new FlowLayout());

        final JPanel levelsPanel = new JPanel(new FlowLayout());
        customLevelPanel = new JPanel(new FlowLayout());
        final JLabel usernameLbl = new JLabel("User: " + player.getName());
        userBestScore = new JLabel("     Best score:  " + p1.getBestScore());

        final JButton createCustom = new JButton("Create custom level");
        logOut = new JButton("logout");
        customP.add(createCustom);
        this.getContentPane().add(mainPanel);
        contr.createMenu(levelsPanel, levelMap, fixedLevelNumbers, ImageLoaderImpl.getLoaderInstance().getFixedFile(),
                false);
        northPanel.add(standardLabel, BorderLayout.NORTH);
        northPanel.add(levelsPanel, BorderLayout.SOUTH);
        southPanel.add(customLabel, BorderLayout.NORTH);
        southPanel.add(customLevelPanel, BorderLayout.SOUTH);

        centralPanel.add(northPanel, BorderLayout.NORTH);

        centralPanel.add(southPanel, BorderLayout.SOUTH);

        userPanel.add(usernameLbl);
        userPanel.add(userBestScore);

        userPanel.add(logOut);
        mainPanel.add(userPanel, BorderLayout.NORTH);
        mainPanel.add(centralPanel, BorderLayout.CENTER);
        mainPanel.add(customP, BorderLayout.SOUTH);
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(final WindowEvent e) {

                logOut.doClick();

            }
        });

        createCustom.addActionListener(contr.customLevelListener());
        logOut.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {

                contr.closeWindow();

            }
        });

        this.getContentPane().add(mainPanel);
        this.setEnabled(true);
        this.setVisible(true);
        PlumberControllerImpl.centreWindow(this);
        contr.createMenu(customLevelPanel, customLevelMap, customLevelNumbers,
                ImageLoaderImpl.getLoaderInstance().getCustomFIle(), true);

    }

    /**
     * Method used to update the custom level list in the custom panel.
     * 
     */
    public void updateCustomPanel() {
        contr.createMenu(customLevelPanel, customLevelMap, customLevelNumbers,
                ImageLoaderImpl.getLoaderInstance().getCustomFIle(), true);
    }

    @Override
    public void update(final Observable o, final Object arg) {

        userBestScore.setText("     Best score:  " + p1.getBestScore());
        SwingUtilities.updateComponentTreeUI(this);
    }

}
