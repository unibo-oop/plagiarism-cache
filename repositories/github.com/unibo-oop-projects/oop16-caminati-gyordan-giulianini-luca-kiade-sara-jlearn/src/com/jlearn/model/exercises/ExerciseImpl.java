package com.jlearn.model.exercises;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.jlearn.model.utilities.Pair;
import com.jlearn.view.utilities.enums.ExerciseType;

/**
 *
 * The implementation of Exercise interface.
 *
 * @param <X>
 *            The answers type.
 *
 */
public class ExerciseImpl<X> implements Exercise<X> {

    private final List<Pair<String, List<X>>> questAndAns;
    private final ExerciseType type;
    private static final Logger LOG = Logger.getLogger(ExerciseImpl.class);

    /**
     *
     * @param questAndAns
     *            a list of Pairs having the question as X value and a list of related answers as Y value
     * @param type
     *            the exercise type
     */
    public ExerciseImpl(final List<Pair<String, List<X>>> questAndAns, final ExerciseType type) {
        LOG.setLevel(Level.WARN);
        this.questAndAns = questAndAns;
        this.type = type;
        LOG.info("Exercice initialized");
    }

    @Override
    public List<String> getQuestions() {

        return this.questAndAns.stream().map(x -> x.getX()).collect(Collectors.toList());

    }

    @Override
    public List<X> getFlatAnswers() {
        final List<X> flatAns = new ArrayList<>();
        this.questAndAns.stream().map(x -> x.getY()).forEach(x -> flatAns.addAll(x));
        return flatAns;
    }

    @Override
    public List<List<X>> getAnswers() {
        return this.questAndAns.stream().map(x -> x.getY()).collect(Collectors.toList());

    }

    @Override
    public int getNumAnswers() {
        return this.getFlatAnswers().size();
    }

    @Override
    public List<X> getAnswer(final String question) {
        if (!this.getQuestions().contains(question)) {
            LOG.warn("Can not retrieve the answer to this question, because this question doesn't exist");
            throw new IllegalArgumentException("The entered question doesn't exist.");
        }
        return this.questAndAns.stream().filter(x -> x.getX().equals(question)).findAny().get().getY();

    }

    @Override
    public ExerciseType getType() {
        return this.type;
    }

    @Override
    public List<Pair<String, List<X>>> getQuestionsAndAnswers() {
        return this.questAndAns;
    }

    @Override
    public List<Integer> getNumAnswersForEachQuestion() {
        return this.getAnswers().stream().map(x -> x.size()).collect(Collectors.toList());

    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = (prime * result) + ((this.questAndAns == null) ? 0 : this.questAndAns.hashCode());
        result = (prime * result) + ((this.type == null) ? 0 : this.type.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        final ExerciseImpl<?> other = (ExerciseImpl<?>) obj;
        if (this.questAndAns == null) {
            if (other.questAndAns != null) {
                return false;
            }
        } else if (!this.questAndAns.equals(other.questAndAns)) {
            return false;
        }

        return this.type == other.type;
    }

    @Override
    public String toString() {
        return "ExerciseImpl [questAndAns=" + this.questAndAns + ", type=" + this.type + "]";
    }

}
