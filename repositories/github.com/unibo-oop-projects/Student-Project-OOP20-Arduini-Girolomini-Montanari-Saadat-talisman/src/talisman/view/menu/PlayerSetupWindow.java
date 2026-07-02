package talisman.view.menu;

import java.awt.LayoutManager;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import talisman.model.menu.PlayerInfo;
import talisman.util.GameSetupUtil;

/**
 * The window used to setup a game.
 * 
 * @author Alberto Arduini
 *
 */
public class PlayerSetupWindow extends JFrame {
    private static final long serialVersionUID = 1L;
    private static final String FIRST_PLAYER_FORMAT = "Select character for player %d";
    private static final String CURRENT_PLAYER_FORMAT = "Select character for player %d or press start";
    private static final String LAST_PLAYER_FORMAT = "Press start to begin game";
    private static final int MAX_PLAYERS = PlayerInfo.Character.getCount();

    private final List<PlayerInfo> players;

    private final CharacterSelectionPanel charactersPanel;
    private final JLabel currentPlayerIndexLabel;
    private final PlayersInfoPanel currentPlayersPanel;
    private final JButton startButton;

    /**
     * Creates a new window.
     */
    public PlayerSetupWindow() {
        this.players = new ArrayList<>();

        final LayoutManager layout = new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS);
        this.setLayout(layout);
        this.setResizable(false);

        this.currentPlayerIndexLabel = new JLabel();
        this.add(currentPlayerIndexLabel);

        this.charactersPanel = new CharacterSelectionPanel();
        this.charactersPanel.setSelectedListener((character) -> {
            this.finalizeCurrentPlayer(character);
            this.addPlayer();
        });
        this.add(this.charactersPanel);

        this.currentPlayersPanel = new PlayersInfoPanel();
        this.add(this.currentPlayersPanel);

        this.startButton = new JButton();
        this.startButton.setText("Start game");
        this.startButton.addActionListener(l -> {
            this.setVisible(false);
            GameSetupUtil.getSingleton().setupGame(List.copyOf(this.players)).startGame();
        });
        this.add(this.startButton);

        final JButton backButton = new JButton();
        backButton.setText("Back to menu");
        backButton.addActionListener(l -> this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING)));
        this.add(backButton);

        this.addPlayer();

        this.pack();
    }

    private void finalizeCurrentPlayer(final int character) {
        final PlayerInfo playerInfo = new PlayerInfo(PlayerInfo.Character.values()[character]);
        this.players.add(playerInfo);
        this.charactersPanel.disableCharacter(character);
        this.currentPlayersPanel.addPlayer(playerInfo.getCharacter().getIndex(), Integer.toString(this.players.size()));
        this.pack();
    }

    private void addPlayer() {
        final String text;
        final int playerCount = this.players.size();
        if (playerCount == 0) {
            text = String.format(PlayerSetupWindow.FIRST_PLAYER_FORMAT, playerCount + 1);
            this.startButton.setEnabled(false);
        } else if (playerCount == PlayerSetupWindow.MAX_PLAYERS) {
            text = PlayerSetupWindow.LAST_PLAYER_FORMAT;
            this.startButton.setEnabled(true);
        } else {
            text = String.format(PlayerSetupWindow.CURRENT_PLAYER_FORMAT, playerCount + 1);
            this.startButton.setEnabled(true);
        }
        this.currentPlayerIndexLabel.setText(text);
    }
}
