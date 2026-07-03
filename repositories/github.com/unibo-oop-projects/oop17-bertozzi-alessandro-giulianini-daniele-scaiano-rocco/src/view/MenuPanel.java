package view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 * First panel displayed after the game is started.
 * It can be launched in 2 versions: Start menu and Pause menu. It shows buttons to start a new game, visualize best scores
 * realized during the games, instructions on how to play and to close the application plus and extra button to return to 
 * play after the application had been paused by pressing the "P" key. 
 */
public class MenuPanel extends GamePanel {
    private static final long serialVersionUID = 1L;
    private final GamePanel selectionPlayerScreen;
    private final GamePanel highScoresScreen;
    private final MenuType type;

    /**String used to identify a MenuPanel instance and to put it into the JFrame. It is needed as JPanel in
     * View are supposed to be managed by {@link java.awt.CardLayout}. It has to be public because it must be accessed from outside this class.*/
    public static final String TITLE = "MenuStart";
    /**String used to identify a MenuPanelPause instance and to put it into the JFrame. It is needed as JPanel in
     * View are supposed to be managed by {@link java.awt.CardLayout}. It has to be public because it must be accessed from outside this class.*/
    public static final String TITLE_PAUSE = "MenuPause";

    /**Enumeration used outside this class to construct the MenuPanel.*/
    public enum MenuType {
         /** Start menu used after launching the application, pause when it stops.*/
        Start, Pause;
    }

    /**
     * Constructs a menu panel with some feature based on the type specified. The Pause type supplies the menu with
     * a "back to game" button to continue the game from the point where it was suspended by pressing the "P" key.
     * @param view view to which this menu is hang to.
     * @param type type of the menu. Either {@link MenuType#Start} or {@link MenuType#Pause}.
     */
    public MenuPanel(final View view, final MenuType type) {
        super(view, type == MenuType.Start ? MenuPanel.TITLE : MenuPanel.TITLE_PAUSE);
        this.type = type;
        this.selectionPlayerScreen = new SelectionPlayerPanel(view);
        this.highScoresScreen = new HighScoresPanel(view);

        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        this.add(Box.createVerticalGlue());

        final JLabel title = new JLabel("SPACE WAR"); //title
        title.setAlignmentX(CENTER_ALIGNMENT);
        title.setFont(new Font("BankGothic Md BT", Font.BOLD, BUTTON_SIZE.height));
        title.setForeground(Color.WHITE);
        this.add(title);

        this.add(Box.createVerticalGlue());

        if (this.type == MenuType.Pause) {
            this.prepareButton("Back To Game", this).addActionListener(e -> {
                this.getView().getObserver().update(this, ViewState.RESUME);
                this.getView().switchWindow(this, GameWindow.TITLE);
                this.getView().start();
            });
        }
        this.prepareButton("New Game", this).addActionListener(e -> {
        if (this.type == MenuType.Pause) {
                this.getView().getObserver().update(this, ViewState.ABORT);
            } else {
                this.selectionPlayerScreen.display();
            }
        });
        this.prepareButton("Instructions", this).addActionListener(e -> {
            this.displayInstructionPanel();
        });
        if (this.type == MenuType.Start) {
            this.prepareButton("High Scores", this).addActionListener(e -> {
                this.highScoresScreen.display();
            });
        }
        this.prepareButton("Close", this).addActionListener(e -> {
            if (this.type == MenuType.Pause) {
                this.getView().getObserver().update(this, ViewState.ABORT);
            }
            Runtime.getRuntime().exit(0);
        });
        this.add(Box.createVerticalGlue());
    }

    private void displayInstructionPanel() {
        JOptionPane.showMessageDialog(this, "Singleplayer:"
                + "\nPlayer moves by pressing arrow keys. " 
                + "\nPlayer shots by pressing L key."
                + "\n\nMultiplayer:"
                + "\nPlayer 1 moves by pressing arrow keys and shots by pressing L key. " 
                + "\nPlayer 2 moves by pressing W, A, S, D keys and shots by pressing G key."
                + "\n\nYou can also pause by pressing P");
    }

}
