package javagotchi.view.minigame.component;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Comparator;

import javax.swing.Icon;
import javax.swing.JButton;
import org.apache.commons.lang3.Pair;

import javagotchi.controller.minigame.main.MiniGame;
import javagotchi.utility.Utility;

/**
 * 
 * @author marica
 *
 */
public class GameButton extends JButton implements ActionListener, Comparable<GameButton> {

    private static final long serialVersionUID = 5145531697585249604L;

    private static final Pair<Integer, Integer> DIMENSIONGAME = MiniGame.getFactoryController().getControllerMiniGame()
            .getModel().getGameGrid().getDimensionGame();

    private static final String START_LAB = "START!";
    private Pair<Integer, Integer> coord;
    private boolean isButtonTime;
    private static final Icon ICONTIME = GameButtonIcon.createTimeIcon();
    private static final Icon ICONAVATAR = GameButtonIcon.createAvatarIcon();

    /**
     * Constructor for GameButton.
     * 
     * @param coord
     *            coordinates identifying {@link GameButton}.
     */
    public GameButton(final Pair<Integer, Integer> coord) {
        super();
        this.setBackground(Color.WHITE);
        this.coord = coord;
        this.addActionListener(this);
    }

    @Override
    public final void actionPerformed(final ActionEvent e) {
        Utility.log(this.toString());

        if (this.isRowOfGame()) {
            if (this.isGameButtonStart()) {
                MiniGame.getFactoryController().getControllerMiniGame().startGame();
            }

            if (this.isButtonTime) {
                MiniGame.getFactoryController().getControllerMiniGame().getModel().getTime().addTime();
                Utility.log("Add Time");
            }

            if (this.getBackground().equals(Color.WHITE)) {
                this.setBackground(Color.RED);
                MiniGame.getFactoryController().getControllerMiniGame().endGame();
            } else {
                MiniGame.getFactoryController().getControllerMiniGame().playGame();
            }
        }
    }

    private boolean isRowOfGame() {
        return this.coord.left == MiniGame.getFactoryController().getControllerMiniGame().getModel().getGameGrid()
                .getInitialRow();
    }

    private boolean isGameButtonStart() {
        return this.getText() == START_LAB;
    }

    /**
     * Gets the coordinates of GameButton.
     * 
     * @return Button's coordinates
     */
    public Pair<Integer, Integer> getCoord() {
        return this.coord;
    }

    /**
     * Sets {@link GameButton#coord}.
     * 
     * @param coord
     *            coordinates for {@link GameButton}
     */
    public void setCoord(final Pair<Integer, Integer> coord) {
        this.coord = coord;
    }

    /**
     * Sets the start button text.
     */
    public void setTextStart() {
        if (this.getCoord().left == DIMENSIONGAME.left - 2 && !this.getBackground().equals(Color.white)) {
            this.setText(START_LAB);
        }
    }

    /**
     * Sets a different icon depending on the pressed button.
     */
    public void setIcon() {
        if (this.isButtonTime) {
            this.setIcon(ICONTIME);
        } else {
            this.setIcon(ICONAVATAR);
        }
    }

    /**
     * Resets the {@link GameButton} to default state.
     */
    public void reset() {
        this.setBackground(Color.WHITE);
        this.setIcon(null);
        this.setEnabled(true);
        this.isButtonTime = false;
        if (this.isGameButtonStart()) {
            this.setText("");
        }
    }

    /**
     * Disable {@link GameButton}.
     */
    public void disableGameButtonIfLastRow() {
        if (this.getCoord().left == DIMENSIONGAME.left - 1 && this.isEnabled()) {
            this.setEnabled(false);
        }
    }

    /**
     * Repaint {@link GameButton}.
     */
    public void repaintGameButton() {
        if (MiniGame.getFactoryController().getControllerMiniGame().getModel().isMomentToAddTime()) {
            isButtonTime = true;
        }
        this.setBackground(Utility.generateRandomColor());
        this.setIcon();
    }

    /**
     * Compares {@link GameButton} in order to the coordinates x.
     * 
     * @return comparator for {@link GameButton}
     */
    public static Comparator<? super GameButton> inOrderToRow() {
        return (x, y) -> x.getCoord().left - y.getCoord().left;
    }

    @Override
    public final int compareTo(final GameButton bt) {
        return bt.getCoord().left;
    }

    @Override
    public final String toString() {
        return "Button: [ coord: " + this.getCoord() + ", color: " + this.getBackground() + " ]";
    }

    /**
     * 
     * Class that loads the icons of GameButton.
     * 
     * @author marica
     *
     */
    private static final class GameButtonIcon {

        private static final String PATH_TIME = "/minigame/time.gif";
        private static final int HEIGHT_TIME = 100;
        private static final int WIDTH_TIME = 100;

        private static String pathAvatar;
        private static final int HEIGHT_AVATAR = 80;
        private static final int WIDTH_AVATAR = 80;

        private GameButtonIcon() {
        }

        private static Icon createTimeIcon() {
            return Utility.createIcon(PATH_TIME, WIDTH_TIME, HEIGHT_TIME);
        }

        private static Icon createAvatarIcon() {
            return Utility.createIcon(getPathAvatar(), WIDTH_AVATAR, HEIGHT_AVATAR);
        }

        private static String getPathAvatar() {
            switch (MiniGame.getFactoryController().getControllerMiniGame().getModel().getGotchi().getInformation()
                    .getAvatar()) {
            case FOX:
                pathAvatar = "/javagotchi/fox.gif";
                break;
            case CAT:
                pathAvatar = "/javagotchi/cat.gif";
                break;
            case PANDA:
                pathAvatar = "/javagotchi/panda.gif";
                break;
            default:
                break;
            }
            return pathAvatar;
        }
    }
}
