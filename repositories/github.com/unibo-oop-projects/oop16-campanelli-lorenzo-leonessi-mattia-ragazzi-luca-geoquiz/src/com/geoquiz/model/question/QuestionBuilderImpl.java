package com.geoquiz.model.question;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import com.geoquiz.model.question.Question.Builder;

class QuestionBuilderImpl implements Question.Builder {

    private String question;
    private final Set<String> answers;
    private String correctAnswer;
    private boolean built;

    QuestionBuilderImpl() {
        this.answers = new HashSet<>();
    }

    @Override
    public Builder addQuestion(final String question) {
        Objects.requireNonNull(question);
        this.question = question;
        return this;
    }

    @Override
    public Builder addAnswer(final String answer) {
        Objects.requireNonNull(answer);
        this.answers.add(answer);
        return this;
    }

    @Override
    public Builder addCorrectAnswer(final String correctAnswer) {
        Objects.requireNonNull(correctAnswer);
        if (!this.answers.contains(correctAnswer)) {
            throw new IllegalArgumentException("The provided answer does not match any answer in the set");
        }
        this.correctAnswer = correctAnswer;
        return null;
    }

    @Override
    public Question build() {
        if (this.question == null || this.answers == null || this.correctAnswer == null) {
            throw new IllegalStateException("Can't build. Some required fields are null.");
        }
        if (this.built) {
            throw new IllegalStateException("This object was already created.");
        }
        this.built = true;
        return new QuestionImpl(this.question, this.answers, this.correctAnswer);
    }

    public int getAnswersSetSize() {
        return answers.size();
    }

}
