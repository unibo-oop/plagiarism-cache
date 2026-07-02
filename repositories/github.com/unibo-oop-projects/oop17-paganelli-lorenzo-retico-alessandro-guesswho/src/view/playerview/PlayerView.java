package view.playerview;

import java.util.Set;
import model.character.Character;
import model.question.Question;

/**
 * Modeling interface for a human Player Graphic interface.
 */
public interface PlayerView {

    /**
     * Allows to enable/disable the GUI.
     * @param b 
     *              true to enable, false to disable
     */
    void setEnabled(boolean b);

    /**
     * Allows to update the displayed Characters.
     * @param remaining 
     *              the remaining Characters
     */
    void updateCharacters(Set<Character> remaining);

    /**
     * Allows to update the displayed Questions.
     * @param remaining 
     *              the remaining Questions
     */
    void updateQuestions(Set<Question> remaining);

    /**
     * Allows to update the displayed remaining attempts.
     */
    void decreaseAttempts();

    /**
     * Allows to show a Question and get an answer.
     * @throws InterruptedException 
     *              in case of interruption (for instance: opponent's quit).
     * @param question 
     *              the Question.
     * @return the answer
     */
    boolean showQuestion(String question) throws InterruptedException;

    /**
     * Allows to show a message.
     * @throws InterruptedException 
     *              in case of interruption (for instance: opponent's quit).
     * @param message 
     *              the message to show
     */
    void showMessage(String message) throws InterruptedException;

    /**
     * Allows to show an error message, note that this GUI will be closed with this message.
     * The difference between this method and the "close" method is that an error message has
     * the highest priority available and blocks the control flow.
     * @param message 
     *              the message.
     */
    void showErrorMessage(String message);

    /**
     * Allows to close the GUI displaying a message.
     * The difference between this method and the "showErrorMessage" is that this one does not
     * block the control flow.
     * @param message 
     *              the message to show before closing.
     */
    void close(String message);

}
