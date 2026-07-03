package com.geoquiz.controller.quiz;

import java.io.InputStream;
import java.util.Optional;
import java.util.Set;

import javax.xml.bind.JAXBException;

import com.geoquiz.model.quiz.BasicMode;
import com.geoquiz.model.quiz.ExtendedMode;
import com.geoquiz.model.quiz.Quiz;
import com.geoquiz.model.quiz.QuizFactory;
import com.geoquiz.utility.ResourceLoader;

/**
 * Implementation of QuizController interface.
 *
 */
public class QuizControllerImpl implements QuizController {

    private static final double FREEZE_TIME = 3000;
    private static final String CLASSIC = "CLASSICA";
    private static final String CHALLENGE = "SFIDA";
    private static final String TRAINING = "ALLENAMENTO";

    private Quiz quiz;

    /**
     * @param type
     *            the type of the quiz.
     * @param mode
     *            the mode of the quiz.
     * @param difficulty
     *            the difficulty of the quiz.
     * @throws JAXBException
     *             exception.
     */
    public QuizControllerImpl(final String type, final String mode, final Optional<String> difficulty)
            throws JAXBException {

        switch (type) {

        case "CAPITALI":
            if (mode.equals(QuizControllerImpl.CLASSIC)) {

                switch (difficulty.get()) {

                case "FACILE":

                    this.quiz = QuizFactory.createCapitalsQuiz(ExtendedMode.EASY);
                    break;

                case "MEDIO":
                    this.quiz = QuizFactory.createCapitalsQuiz(ExtendedMode.MEDIUM);
                    break;

                case "DIFFICILE":
                    this.quiz = QuizFactory.createCapitalsQuiz(ExtendedMode.HARD);
                    break;

                default:
                    break;
                }
            } else if (mode.equals(QuizControllerImpl.CHALLENGE)) {
                this.quiz = QuizFactory.createCapitalsQuiz(BasicMode.CHALLENGE);
            } else if (mode.equals(QuizControllerImpl.TRAINING)) {
                this.quiz = QuizFactory.createCapitalsQuiz(BasicMode.TRAINING);
            }
            break;

        case "MONUMENTI":
            if (mode.equals(QuizControllerImpl.CLASSIC)) {

                switch (difficulty.get()) {

                case "FACILE":
                    this.quiz = QuizFactory.createMonumentsQuiz(ExtendedMode.EASY);
                    break;

                case "MEDIO":
                    this.quiz = QuizFactory.createMonumentsQuiz(ExtendedMode.MEDIUM);
                    break;

                case "DIFFICILE":
                    this.quiz = QuizFactory.createMonumentsQuiz(ExtendedMode.HARD);
                    break;

                default:
                    break;
                }
            } else if (mode.equals(QuizControllerImpl.CHALLENGE)) {
                this.quiz = QuizFactory.createMonumentsQuiz(BasicMode.CHALLENGE);
            } else if (mode.equals(QuizControllerImpl.TRAINING)) {
                this.quiz = QuizFactory.createMonumentsQuiz(BasicMode.TRAINING);
            }
            break;

        case "VALUTE":
            if (mode.equals(QuizControllerImpl.CLASSIC)) {
                this.quiz = QuizFactory.createCurrenciesQuiz(Optional.empty());
            } else if (mode.equals(QuizControllerImpl.CHALLENGE)) {
                this.quiz = QuizFactory.createCurrenciesQuiz(Optional.of(BasicMode.CHALLENGE));
            } else if (mode.equals(QuizControllerImpl.TRAINING)) {
                this.quiz = QuizFactory.createCurrenciesQuiz(Optional.of(BasicMode.TRAINING));
            }
            break;

        case "BANDIERE":
            if (mode.equals(QuizControllerImpl.CLASSIC)) {
                this.quiz = QuizFactory.createFlagsQuiz(Optional.empty());
            } else if (mode.equals(QuizControllerImpl.CHALLENGE)) {
                this.quiz = QuizFactory.createFlagsQuiz(Optional.of(BasicMode.CHALLENGE));
            } else if (mode.equals(QuizControllerImpl.TRAINING)) {
                this.quiz = QuizFactory.createFlagsQuiz(Optional.of(BasicMode.TRAINING));
            }
            break;

        case "CUCINA":
            if (mode.equals(QuizControllerImpl.CLASSIC)) {
                this.quiz = QuizFactory.createTypicalDishesQuiz(Optional.empty());
            } else if (mode.equals(QuizControllerImpl.CHALLENGE)) {
                this.quiz = QuizFactory.createTypicalDishesQuiz(Optional.of(BasicMode.CHALLENGE));
            } else if (mode.equals(QuizControllerImpl.TRAINING)) {
                this.quiz = QuizFactory.createTypicalDishesQuiz(Optional.of(BasicMode.TRAINING));
            }
            break;

        default:
            throw new IllegalArgumentException();

        }
    }

    @Override
    public String showStringQuestion() {
        return this.quiz.getCurrentQuestion().getQuestion();
    }

    @Override
    public InputStream showImageQuestion() {

        return ResourceLoader.loadResourceAsStream("/images/flags/" + this.quiz.getCurrentQuestion().getQuestion());

    }

    @Override
    public void hitAnswer(final Optional<String> answer) {
        this.quiz.hitAnswer(answer);

    }

    @Override
    public void nextQuestion() {
        if (!this.gameOver()) {
            this.quiz.next();
        }

    }

    @Override
    public boolean checkAnswer() {
        return this.quiz.isAnswerCorrect();
    }

    @Override
    public String getCorrectAnswer() {
        return this.quiz.getCorrectAnswer();
    }

    @Override
    public int getRemainingLives() {
        return this.quiz.getRemainingLives();
    }

    @Override
    public boolean gameOver() {
        return this.quiz.gameOver();
    }

    @Override
    public boolean isFreezeAvailable() {
        return this.quiz.isFreezeAvailable();
    }

    @Override
    public boolean isSkipAvailable() {
        return this.quiz.isSkipAvailable();
    }

    @Override
    public boolean is5050Available() {
        return this.quiz.is5050Available();
    }

    @Override
    public double freeze() {
        this.quiz.freeze();
        return QuizControllerImpl.FREEZE_TIME;
    }

    @Override
    public void skip() {
        this.quiz.skip();
    }

    @Override
    public Set<String> use5050() {
        return this.quiz.use5050();
    }

    @Override
    public int getQuestionDuration() {
        return this.quiz.getQuestionDuration();
    }

    @Override
    public void restart() {
        this.quiz.restart();
    }

    @Override
    public Set<String> showAnswers() {
        return this.quiz.getCurrentQuestion().getAnswers();
    }

    @Override
    public int getScore() {
        return this.quiz.getCurrentScore();
    }

}
