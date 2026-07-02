package it.unibo.modularcheckers.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import it.unibo.modularcheckers.model.PlayerType;
import it.unibo.modularcheckers.view.components.GameSelection;
import it.unibo.modularcheckers.view.components.PlayerSelection;

/**
 * Start ModularCheckers!
 */
public class StartViewImpl extends View {

    private static final long serialVersionUID = 5037544943800458046L;
    private static final int COLUMNS = 20;

    private final PlayerSelection playerOneSelection;
    private final PlayerSelection playerTwoSelection;
    private final GameSelection gameSelection;
    private final StartViewObservable observer;

    /**
     * Builds the view.
     *
     * @param obs observer of the view, spoiler: is the controller
     */
    public StartViewImpl(final StartViewObservable obs) {
        super();
        gameSelection = new GameSelection();
        playerOneSelection = new PlayerSelection("Player One:");
        playerTwoSelection = new PlayerSelection("Player Two:");
        observer = obs;
        final JPanel base = new JPanel(new GridBagLayout());
        final GridBagConstraints bagConstraints = new GridBagConstraints();

        final JLabel lblTitle = new JLabel("Welcome to ModularCheckers");

        final JButton btnNewGame = new JButton("Start Game!");
        final JButton btnExit = new JButton("Exit");

        // Setup operations;
        this.setTitle("ModularCheckers");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Action Assignment
        btnNewGame.addActionListener(l -> {
            if (playerHaveSameName()) {
                JOptionPane.showMessageDialog(
                        null,
                        "You can't have two players with the same name.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                final Map<String, PlayerType> playerMap = new HashMap<>();
                playerMap.put(this.playerOneSelection.getTextField().getText(), this.playerOneSelection.getPlayerType());
                playerMap.put(this.playerTwoSelection.getTextField().getText(), this.playerTwoSelection.getPlayerType());
                this.observer.insertPlayers(playerMap);
                this.observer.chooseGame(this.gameSelection.getSelected());
                this.observer.newGame();
                this.dispose();
            }
        });
        btnExit.addActionListener(l -> this.observer.exit());

        // Get a Panel and inject all the object created before
        bagConstraints.anchor = GridBagConstraints.PAGE_START;
        bagConstraints.fill = GridBagConstraints.HORIZONTAL;
        bagConstraints.ipady = COLUMNS;
        base.add(lblTitle, bagConstraints);

        bagConstraints.ipady = 0;
        bagConstraints.gridy = 1;
        base.add(gameSelection, bagConstraints);

        bagConstraints.gridy = 2;
        base.add(playerOneSelection, bagConstraints);

        bagConstraints.gridy = 3;
        base.add(playerTwoSelection, bagConstraints);

        bagConstraints.ipady = 10;
        bagConstraints.ipadx = 10;
        bagConstraints.gridy = 4;
        base.add(btnNewGame, bagConstraints);

        bagConstraints.gridy = 5;
        base.add(btnExit, bagConstraints);

        this.add(base);
        this.setFullScreen();
        this.setVisible(true);
    }

    private boolean playerHaveSameName() {
        return this.playerOneSelection.getTextField().getText().equals(this.playerTwoSelection.getTextField().getText());
    }

}
