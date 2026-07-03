package com.geoquiz.model.quiz;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import com.geoquiz.model.question.Question;

class QuizImpl implements Quiz {

    private static final int MAX_LIVES = 3;
    private static final int QUESTION_DURATION = 10;

    private final List<Question> questionsList;
    private final Iterator<Question> questions;
    private Question currentQuestion;
    private Optional<String> currentAnswer;
    private int currentScore;
    private int numLives;
    private final Optional<? extends Mode> mode;

    private boolean freezeAvailable;
    private boolean skipAvailable;
    private boolean fiftyAvailable;

    QuizImpl(final List<Question> list, final Optional<? extends Mode> mode) {
        Collections.shuffle(list);
        this.questionsList = list;
        this.questions = list.iterator();
        this.currentAnswer = Optional.empty();
        this.currentQuestion = this.questions.next();
        this.mode = mode;
        this.freezeAvailable = true;
        this.skipAvailable = true;
        this.fiftyAvailable = true;

        if (mode.isPresent() && mode.get() == BasicMode.CHALLENGE) {
            numLives = 1;
        } else {
            numLives = MAX_LIVES;
        }
    }

    @Override
    public Question getCurrentQuestion() {
        return this.currentQuestion;
    }

    @Override
    public void hitAnswer(final Optional<String> answer) {
        Objects.requireNonNull(answer);
        this.currentAnswer = answer;
        this.computeScore();
    }

    private void computeScore() {
      if (!mode.equals(Optional.of(BasicMode.TRAINING))) {
          if (!currentAnswer.isPresent() || !this.isAnswerCorrect()) {
              this.numLives--;
          } else {
              this.currentScore++;
          }
      }
    }

    @Override
    public void next() {
        this.checkGameOver();
        this.currentQuestion = questions.next();
    }

    @Override
    public boolean isAnswerCorrect() {
       return this.currentQuestion.isAnswerCorrect(this.currentAnswer.get());
    }

    @Override
    public String getCorrectAnswer() {
        return currentQuestion.getCorrectAnswer();
    }

    @Override
    public int getRemainingLives() {
        return this.mode.equals(Optional.of(BasicMode.TRAINING)) ? MAX_LIVES : numLives;
    }

    @Override
    public boolean gameOver() {
        return !this.questions.hasNext() || numLives <= 0;
    }

    private void checkGameOver() {
        if (this.gameOver()) {
            throw new IllegalStateException("The game is over. You can't call this method.");
        }
    }

    @Override
    public boolean isFreezeAvailable() {
        return freezeAvailable;
    }

    @Override
    public boolean isSkipAvailable() {
        return skipAvailable;
    }

    @Override
    public boolean is5050Available() {
        return fiftyAvailable;
    }

    @Override
    public void freeze() {
        this.freezeAvailable = false;
    }

    @Override
    public void skip() {
       this.skipAvailable = false;
       if (!this.gameOver()) {
           this.next();
       }
    }

    @Override
    public Set<String> use5050() {
            this.fiftyAvailable = false;
            return this.currentQuestion.getAnswers()
                                       .stream()
                                       .filter(e -> !e.equals(currentQuestion.getCorrectAnswer()))
                                       .limit(2)
                                       .collect(Collectors.toSet());
    }

    @Override
    public int getQuestionDuration() {
        return QuizImpl.QUESTION_DURATION;
    }

    @Override
    public QuizImpl restart() {
        return new QuizImpl(this.questionsList, this.mode);
    }

    @Override
    public int getCurrentScore() {
        return this.currentScore;
    }

}
