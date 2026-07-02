package bzzbomber.view.hud;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import bzzbomber.controller.Controller;
import bzzbomber.controller.ControllerInterface;
import bzzbomber.model.Timer;
import bzzbomber.model.TimerEvent;
import bzzbomber.model.EventListener;
import bzzbomber.model.entities.BzzBomber;
import bzzbomber.view.menu.GUIFactory;
import bzzbomber.view.menu.GUIFactoryImpl;

/**
 * 
 * Create a Panel for keep trace of number life, seconds left, monster killed,
 * icon of hero.
 */
public class HudPanel extends JPanel implements ActionListener, EventListener {

    /**
     * serial version UID.
     */
    private static final long serialVersionUID = -4057157155188288825L;

    private final ControllerInterface controller;

    private final JButton pause;
    private final JLabel timerSquare;
    private final JLabel monsterSquare;
    private final JLabel iconAvatar;
    private final List<JLabel> heartSquare;
    private JOptionPane option;
    private JDialog dialog;

    private final Timer timer;
    private final ImageIcon heartIcon;
    private final Box center;

    private int numMonster;

    /**
     * constructor: Create all elements of Hud Panel.
     * 
     * @param timer
     *            Timer to know how the time is finished and to see on GUI.
     * @param controller
     *            the controller of the game.
     */
    public HudPanel(final Timer timer, final ControllerInterface controller) {
        super();

        this.setLayout(new BorderLayout());
        this.setBackground(Color.cyan);
        this.controller = controller;
        this.timer = timer;
        this.numMonster = this.controller.getCurrentLevel().getNumOfInsects();

        this.timerSquare = new JLabel();
        this.timerSquare.setText("        " + timer.getSecond() + "s left    ");
        this.pause = new JButton();
        this.heartSquare = new ArrayList<>();

        this.monsterSquare = new JLabel("\t   Mostri uccisi: " + this.controller.getBomber().getEnemyKilled() + "/"
                + this.numMonster + "\t   ");

        final GUIFactory menu = new GUIFactoryImpl();

        final Image pauseIm = menu.createPathImage("/hud/pausa.png");
        final ImageIcon pauseIcon = new ImageIcon(pauseIm);
        this.pause.setIcon(pauseIcon);

        final Image monster = menu.createPathImage("/hud/insect-icon.png");
        final ImageIcon monsterIcon = new ImageIcon(monster);

        final Image heart = menu.createPathImage("/hud/heart.png");
        this.heartIcon = new ImageIcon(heart);

        this.monsterSquare.setIcon(monsterIcon);

        for (int i = 0; i < Controller.NUM_LIFE_TOT; i++) {
            this.heartSquare.add(new JLabel());
            this.heartSquare.get(i).setIcon(heartIcon);
        }
        this.pause.setEnabled(true);

        final Box right = new Box(BoxLayout.X_AXIS);
        final JPanel panel = new JPanel();
        panel.setBackground(Color.cyan);

        this.center = new Box(BoxLayout.X_AXIS);
        this.center.add(this.timerSquare, BorderLayout.EAST);
        this.center.add(panel);
        for (final JLabel e : this.heartSquare) {
            this.center.add(e);
        }
        this.center.add(panel);

        right.add(this.monsterSquare, BorderLayout.EAST);
        right.add(panel);
        right.add(this.pause, BorderLayout.WEST);
        right.add(panel);

        this.add(center, BorderLayout.CENTER);
        this.add(right, BorderLayout.EAST);

        this.iconAvatar = new JLabel();
        final JPanel panelAvatar = new JPanel();
        panelAvatar.add(iconAvatar);
        this.add(panelAvatar, BorderLayout.WEST);

        this.pause.addActionListener(this);
        this.timer.addListener(this);
        this.setVisible(false);
    }

    @Override
    public final void counterChanged(final TimerEvent ev) {
        SwingUtilities.invokeLater(() -> {
            this.timerSquare.setText("        " + ev.getValue() + "s left    ");
        });
        if (this.timer.getSecond() == 0) {
            this.controller.setGameLose(true);
            System.out.println("-->Il tempo è finito per questo hai perso");
        }
    }

    @Override
    public final void lifeChanged(final BzzBomber hero) {

        while (this.heartSquare.size() < hero.getRemainingLives()) {
            final JLabel t = new JLabel();
            t.setIcon(heartIcon);
            this.center.add(t);
            this.heartSquare.add(t);
        }
        if (this.heartSquare.size() > hero.getRemainingLives()) {
            this.center.remove(heartSquare.get(heartSquare.size() - 1));
            this.heartSquare.remove(heartSquare.size() - 1);
        }
        SwingUtilities.invokeLater(() -> {
            this.repaint();
        });
        if (hero.getRemainingLives() == 0) {
            this.controller.setGameLose(true);
            System.out.println("-->Hai finito le vite, per questo hai perso");
        }
    }

    @Override
    public final void monsterChanged(final BzzBomber hero) {
        monsterSquare.setText("Mostri uccisi: " + hero.getEnemyKilled() + "/" + this.numMonster + "\t   ");
        if (hero.getEnemyKilled() == this.numMonster) {
            this.controller.setDoorVisible();
        }
    }

    @Override
    public final void actionPerformed(final ActionEvent ev) {
        final Object src = ev.getSource();
        if (src.equals(this.pause)) {
            this.controller.setGamePause(true);
            this.controller.getViewManager().getMusicClass().musicStop();
            this.option = new JOptionPane("Il gioco è in pausa. Vuoi continuare a giocare?",
                    JOptionPane.QUESTION_MESSAGE, JOptionPane.YES_NO_OPTION);
            this.dialog = option.createDialog(this, "PAUSA");
            this.dialog.setLocationRelativeTo(null);
            this.dialog.setVisible(true);
            if (option.getValue() == null) {
                this.exitGame();
            } else if ((int) option.getValue() == JOptionPane.NO_OPTION) {
                System.out.println("Bye bye, partita terminata dopo PAUSA");
                this.controller.getViewManager().getMusicClass().stopAll();
                this.exitGame();
            } else if ((int) option.getValue() == JOptionPane.YES_OPTION) {
                this.controller.setGamePause(false);
                this.init();
                this.controller.getViewManager().getMusicClass().restart();
            }

        }
    }

    /**
     * This methods is called to start the Timer.
     */
    public void init() {
        pause.setEnabled(true);
    }

    /**
     * show the message dialog, the player is a looser.
     */
    public void showDialogLoose() {
        this.pause.setEnabled(false);
        this.controller.getViewManager().getMusicClass().musicLoose();
        this.option = new JOptionPane("La partita è terminata. Hai perso! \n Vuoi ricominciare una nuova partita?",
                JOptionPane.QUESTION_MESSAGE, JOptionPane.YES_NO_OPTION);
        this.dialog = option.createDialog(this, "LOOSER");
        this.dialog.setLocationRelativeTo(null);
        this.dialog.setVisible(true);
        this.dialog.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        if (option.getValue() == null) {
            this.controller.getViewManager().getMusicClass().musicStop();
            this.exitGame();
        } else if ((int) option.getValue() == JOptionPane.NO_OPTION) {
            System.out.println("Bye bye, partita terminata");
            this.controller.getViewManager().getMusicClass().musicStop();
            this.exitGame();
        } else if ((int) option.getValue() == JOptionPane.YES_OPTION) {
            this.dialog.setVisible(false);
            this.controller.setGameRelaunch(true);
            this.controller.getViewManager().getMusicClass().setMusicOn();

        }
    }

    /**
     * set the Icon that the player has chosen.
     * 
     * @param avatarIcon
     *            Image of the avatar choose.
     */
    public void setAvatarIcon(final ImageIcon avatarIcon) {

        final Image imageScaled = avatarIcon.getImage().getScaledInstance((int) (avatarIcon.getIconWidth() * 0.2),
                (int) (avatarIcon.getIconHeight() * 0.2), Image.SCALE_DEFAULT);
        final ImageIcon imageIconScaled = new ImageIcon(imageScaled);
        iconAvatar.setIcon(imageIconScaled);
        iconAvatar.setBackground(Color.WHITE);

    }

    /**
     * set the number of monster to kill, it's different from different levels.
     * 
     * @param monster
     *            number monster that appear in the game play.
     */
    public void setNumMonster(final int monster) {
        this.numMonster = monster;
    }

    private void exitGame() {
        System.exit(0);
    }
}
