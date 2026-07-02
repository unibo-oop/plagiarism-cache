package view.playerview;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

import controller.command.AskingAction;
import controller.command.GuessingAction;
import model.character.Character;
import model.question.Question;
import utilities.*;
import view.gamedialog.DialogFactory;
import view.viewposition.PositionManager;

import static java.awt.BorderLayout.*;

/**
 * Implementation of PlayerView. 
 */
public class PlayerViewImpl extends JFrame implements PlayerView {

    /***/
    private static final long serialVersionUID = 1L;
    private static final int GAP = 0;

    private final SelectedPanel selectedPanel;
    private final CharactersPanel charactersPanel = new CharactersPanel();
    private final QuestionsPanel questionsPanel;
    private final ActionListener characterSelector;
    private final ActionListener characterGuesser;
    private final Runnable onClose;

    /**
     * Constructor.
     * @param observer
     *                  the observer
     * @param startingAttempts
     *                  the number of starting attempts
     * @param startingQuestions
     *                  the question about starting character
     */
    public PlayerViewImpl(final PlayerViewObserver observer, final Integer startingAttempts, final Set<Question> startingQuestions) {
        super();
        Utilities.requireNonNull(observer, startingAttempts, startingQuestions);
        selectedPanel = new SelectedPanel(startingAttempts);
        questionsPanel = new QuestionsPanel(startingQuestions);
        characterSelector = e -> { 
            charactersPanel.setEnabled(false);
            selectedPanel.setSelected(((JButton) e.getSource()).getText());
            this.changeActionListener();
            observer.select(((JButton) e.getSource()).getText());
        };
        characterGuesser = e -> observer.askOpponent(new GuessingAction(((JButton) e.getSource()).getText()));
        final ActionListener questionAsker = e -> observer.askOpponent(new AskingAction(questionsPanel.getSelectedQuestion()));
        charactersPanel.addActionListener(characterSelector);
        questionsPanel.addActionListener(questionAsker);
        this.setLayout(new BorderLayout(GAP, GAP));
        this.add(NORTH, selectedPanel);
        this.add(CENTER, charactersPanel);
        this.add(SOUTH, questionsPanel);
        this.pack();
        this.setMinimumSize(new Dimension(this.getSize()));
        questionsPanel.setPreferredSize(questionsPanel.getSize());
        this.setLocation(PositionManager.getPoint(this.getSize()));
        onClose = () -> { 
            dispose(); 
            observer.exit(); 
        };
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(final WindowEvent e) throws RuntimeException {
                onClose.run();
            }
        });
        this.setTitle(Messages.TITLE);
        this.setVisible(true);
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setEnabled(final boolean b) {
        charactersPanel.setEnabled(b);
        questionsPanel.setEnabled(b);
    }

    /**
     * @inheritDoc
     */
    @Override
    public void updateCharacters(final Set<Character> remaining) {
        charactersPanel.update(remaining);
    }

    /**
     * @inheritDoc
     */
    @Override
    public void updateQuestions(final Set<Question> remaining) {
        questionsPanel.update(remaining);
    }

    /**
     * @inheritDoc
     */
    public void decreaseAttempts() {
        selectedPanel.decreaseAttempts();
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean showQuestion(final String question) throws InterruptedException {
        return DialogFactory.showQuestionDialog(this, question);
    }

    /**
     * @inheritDoc
     */
    @Override
    public void showMessage(final String message) throws InterruptedException {
        DialogFactory.showMessageDialog(this, message, true);
    }

    /**
     * @param message .
     */
    public void showErrorMessage(final String message) {
        DialogFactory.showErrorDialog(this, message);
        onClose.run();
    }

    /**
     * @inheritDoc
     */
    @Override
    public void close(final String message) {
        Arrays.stream(getWindowListeners()).forEach(l -> removeWindowListener(l));
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        try {
            DialogFactory.showMessageDialog(this, message, false, e -> onClose.run());
        } catch (InterruptedException e) {
        }
    }

    private void changeActionListener() {
        charactersPanel.removeActionListener(characterSelector);
        charactersPanel.addActionListener(characterGuesser);
    }

}
