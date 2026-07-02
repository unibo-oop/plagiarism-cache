package view.operationGUI;

import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import view.WindowStart;
import view.WindowGame;

/**
 * 
 * GUI controller implementation.
 *
 */
public class OperationGUI implements OperationGUIInterface {

    private WindowStart userInterface;
    private final WindowGame windowGame = new WindowGame();
    private final String separator = System.getProperty("line.separator");
    private static final String PATH = System.getProperty("user.home");

    /**
     * Get screen width for a better resolution.
     */
    private static final int SCREENWIDTH = Toolkit.getDefaultToolkit().getScreenSize().width;

    /**
     * Get screen height for a better resolution.
     */
    private static final int SCREENHEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height;

    /**
     * Separator that it's portable.
     */
    public static final String SEP = File.separator;

    /**
     * File with best time.
     */
    public static final String FILE_NAME_RANKING = PATH + SEP + "ranking";

    /**
     * File that shows scores.
     */
    public static final String FILE_NAME_SCORES = PATH + SEP + "scores";

    /**
     * File for times in seconds.
     */
    public static final String FILE_NAME_TIMES = PATH + SEP + "times";

    private String namePlayer;

    @Override
    public final void playButton(final String name) {
        String newName = name;
        if (newName.equals("") || newName.contentEquals(" ")) {
            newName = "default gamer";
        }
        namePlayer = newName;
    }

    @Override
    public final void showScores(final JTextArea out) {
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new FileReader(FILE_NAME_SCORES));
        } catch (FileNotFoundException e2) {
            e2.printStackTrace();
        }
        String line = null;
        try {
            line = reader.readLine();
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        while (line != null) {
            out.append(line);
            out.append(this.separator);
            try {
                line = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            reader.close();
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public final void showLeaderboard(final JTextArea out) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(FILE_NAME_RANKING));
        } catch (FileNotFoundException e2) {
            e2.printStackTrace();
        }
        String line = null;
        try {
            line = reader.readLine();
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        while (line != null) {
            out.append(line);
            out.append(this.separator);
            try {
                line = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            reader.close();
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public final void showInfo() {
        JOptionPane.showMessageDialog(new JFrame(),
                "The game consists in moving the ninja from its initial position to the final destination without being seen by the enemies."
                        + this.separator + "Each enemy has its own ray, when the player collides with it, he loses a life."
                        + this.separator
                        + "When the player loses a life, he becomes invisible to the enemies for two seconds"
                        + this.separator
                        + "The objects on the map can be divided into 2 categories: Powerups and Debuffs."
                        + this.separator + "The player will be considered dead only after losing the last of his 3 lives. "
                        + this.separator
                        + "After completing each level, stars will be awarded to the player based on the time used to complete it."
                        + this.separator
                        + "Levels can be replayed so that the player can improve the score by reaching three stars.",
                "Info", 1);
    }

    @Override
    public final void showHowToPlay() {
        final ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("menu/HowToPlay.png"));
        JOptionPane.showMessageDialog(new JFrame(), icon, "How to play", 1);
    }

    @Override
    public final void showEnemy() {
        final ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("menu/legendEnemyImage.png"));
        JOptionPane.showMessageDialog(new JFrame(), icon, "Enemies", 1);
    }

    @Override
    public final void showLegend() {
        final ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("menu/legendImage.png"));
        JOptionPane.showMessageDialog(new JFrame(), icon, "Static objects", 1);
    }

    @Override
    public final void exitButton() {

        this.windowGame.removeLevel();
        userInterface = new WindowStart();
        getUserInterface().createWindow();
        getUserInterface().setDimensions(SCREENWIDTH / 3, SCREENHEIGHT / 3);
        getUserInterface().show();
    }

    @Override
    public final void goBackButton() {
        userInterface = new WindowStart();
        getUserInterface().createWindow();
        getUserInterface().setDimensions(SCREENWIDTH / 3, SCREENHEIGHT / 3);
        getUserInterface().show();
    }

    @Override
    public final void setName() {
        try (FileWriter fw = new FileWriter(FILE_NAME_SCORES, true);
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter out = new PrintWriter(bw)) {
            out.print("Nickname: " + namePlayer + this.separator);
            out.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * add the level.
     */
    public final void startLevelButton() {
        this.windowGame.addLevel();
    }

    /**
     * 
     * @return windowGame
     */
    public WindowGame getWinGame() {
        return windowGame;
    }

    /**
     * @param name name of the player
     *
     * get the name.
     * 
     */
    public final void setName(final JTextField name) {
        namePlayer = name.getText();
    }

    /**
     * @param name name of the player
     *
     * write the name.
     * 
     */
    public final void writeName(final JTextField name) {
        name.setText(namePlayer);
    }

    /**
     * 
     * @return userInterfaceImpl
     */
    public WindowStart getUserInterface() {
        return userInterface;
    }

}
