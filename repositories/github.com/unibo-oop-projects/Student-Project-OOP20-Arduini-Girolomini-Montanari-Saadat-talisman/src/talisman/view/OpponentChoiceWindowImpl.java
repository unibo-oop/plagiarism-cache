package talisman.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import talisman.Controllers;
import talisman.EventEndedListener;
import talisman.controller.battle.BattleController;
import talisman.controller.battle.BattleControllerImpl;
import talisman.model.battle.BattleModel;
import talisman.model.battle.BattleModelImpl;
import talisman.model.character.CharacterModel;
import talisman.util.Pair;

/**
 * Swing implementation of the view used to select an opponent to fight.
 * 
 * @author Alice Girolomini
 *
 */
public class OpponentChoiceWindowImpl extends JFrame implements OpponentChoiceWindow {
    private static final long serialVersionUID = 1L;
    private final List<Pair<Integer, String>> opponents;
    private final JTextField textField;
    private final JButton choiceButton;
    private final EventEndedListener listener;

    /**
     * Creates the window.
     * 
     * @param players             - the list of players in the same cell
     * @param battleEndedListener - a listener to call for when the battle ends.
     */
    public OpponentChoiceWindowImpl(final List<Integer> players, final EventEndedListener battleEndedListener) {
        this.listener = battleEndedListener;
        this.opponents = initializeOpponents(players);
        this.textField = new JTextField(10);
        this.choiceButton = new JButton("Continue");
        final BorderLayout layout = new BorderLayout();
        this.setLayout(layout);
        this.setTitle("Choose your opponent:");
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.getContentPane().add(createTitlePanel(), BorderLayout.NORTH);
        this.getContentPane().add(createTextPanel(), BorderLayout.CENTER);
        this.getContentPane().add(createButtonPanel(), BorderLayout.SOUTH);
        this.pack();
        this.setResizable(false);
        this.setVisible(true);
    }

    /**
     * Creates a panel with the title.
     */
    private JPanel createTitlePanel() {
        JPanel panel = new JPanel();
        JLabel title = new JLabel("Choose one of these opponents: " + listToString(this.opponents));
        title.setForeground(Color.BLACK);
        panel.setBackground(Color.darkGray);
        panel.add(title);
        return panel;
    }

    /**
     * Creates a panel with the text field.
     */
    private JPanel createTextPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(Color.darkGray);
        panel.add(textField);
        return panel;
    }

    /**
     * Creates a panel with the continue button.
     */
    private JPanel createButtonPanel() {
        JPanel panel = new JPanel();
        choiceButton.addActionListener(e -> {
            try {
                if (!textField.getText().equals("")) {
                    if (checkOpponent(Integer.parseInt(textField.getText()))) {
                        startFight(Integer.parseInt(textField.getText()));
                    } else {
                        JOptionPane.showMessageDialog(null, "This opponent is not available!");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "You have to choose one of the opponents before you continue.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "This opponent is not valid!.");
            }
        });
        panel.setBackground(Color.darkGray);
        panel.add(choiceButton);
        return panel;
    }

    /**
     * Puts the integers of the list in a string.
     * 
     * @param list - the list of integers
     */
    private String listToString(final List<Pair<Integer, String>> list) {
        int index = 0;
        List<String> values = list.stream().map(e -> e.getY()).collect(Collectors.toList());
        String strings = values.stream().map(String::valueOf).collect(Collectors.joining(" (" + index++ + "), "));
        strings = strings.concat(" (" + index + "),");
        return strings;
    }

    /**
     * Checks whether the opponent exists.
     * 
     * @param index - the opponents'index
     */
    private boolean checkOpponent(final int index) {
        for (int i = 0; i < this.opponents.size(); i++) {
            if (this.opponents.get(i).getX().equals(index)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Starts the fight.
     */
    private void startFight(final int index) {
        this.setVisible(false);
        CharacterModel firstcharacter = Controllers.getCharactersController().getCurrentPlayer().getCurrentCharacter();
        CharacterModel secondcharacter = Controllers.getCharactersController().getPlayers().get(index)
                .getCurrentCharacter();
        BattleModel battleModel = new BattleModelImpl(firstcharacter.getStrength(), secondcharacter.getStrength());
        BattleController battleController = new BattleControllerImpl(firstcharacter, secondcharacter, battleModel);
        final BattleWindow window = new BattleWindow(battleController);
        window.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(final WindowEvent e) {
                OpponentChoiceWindowImpl.this.notifyBattleEnd();
            }
        });
    }

    /**
     * Puts the player's index and his character type in a list.
     * 
     * @param list - the list
     */
    private List<Pair<Integer, String>> initializeOpponents(final List<Integer> list) {
        List<Pair<Integer, String>> values = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            values.add(new Pair<Integer, String>(list.get(i), Controllers.getCharactersController().getPlayers().get(i)
                    .getCurrentCharacter().getType().toString()));
        }
        return values;
    }

    private void notifyBattleEnd() {
        this.listener.eventEnded();
    }
}
