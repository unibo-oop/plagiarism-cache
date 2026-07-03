package com.geoquiz.model.question;

import java.util.Collections;
import java.util.Objects;
import java.util.Set;


/**
 * This class is the implementation for the Question interface.
 *
 */
class QuestionImpl implements Question {

    private final String question;
    private final Set<String> answers;
    private final String correctAnswer;

    /**
     * @param question
     *          the question to be asked
     * @param answers
     *          the set of possible answers to be given
     * @param correctAnswer
     *          the correct answer
     */
    //package-private
    QuestionImpl(final String question, final Set<String> answers, final String correctAnswer) {
        this.question = question;
        this.answers = answers;
        this.correctAnswer = correctAnswer;
    }

    @Override
    public String getQuestion() {
        return this.question;
    }

    @Override
    public Set<String> getAnswers() {
        return Collections.unmodifiableSet(this.answers);
    }

    @Override
    public String getCorrectAnswer() {
        return correctAnswer;
    }

    @Override
    public boolean isAnswerCorrect(final String answer) {
        Objects.requireNonNull(answer);
        if (!this.answers.contains(answer)) {
            throw new IllegalArgumentException("The given answer is not among the possible answers");
        }
            return this.correctAnswer.equals(answer);
    }

    @Override
    public String toString() {
        return "QuestionImpl [question=" + question + ", answers=" + answers + ", correctAnswer=" + correctAnswer + "]";
    }

}
