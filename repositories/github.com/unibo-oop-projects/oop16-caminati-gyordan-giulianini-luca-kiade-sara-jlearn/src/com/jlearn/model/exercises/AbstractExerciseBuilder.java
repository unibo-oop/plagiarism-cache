package com.jlearn.model.exercises;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.jlearn.model.utilities.Pair;
import com.jlearn.view.utilities.enums.ExerciseType;

/**
 *
 * ExerciseBuilder implementation.
 *
 * @param <X>
 *            answer type
 */
public abstract class AbstractExerciseBuilder<X> implements ExerciseBuilder<X> {

    private final List<Pair<String, List<X>>> questAndAns;
    private boolean built;
    private final ExerciseType type;
    private static final Logger LOG = Logger.getLogger(AbstractExerciseBuilder.class);

    /**
     *
     * The constructor.
     *
     * @param type
     *            the exercise type
     *
     */
    public AbstractExerciseBuilder(final ExerciseType type) {
        LOG.setLevel(Level.WARN);
        this.built = false;
        this.questAndAns = new ArrayList<>();
        this.type = type;
        LOG.info("Exercise Builder initialized");
    }

    @Override
    public void addQuestion(final String quest, final List<X> answers) {

        if (!this.validAnswer(answers)) {
            LOG.warn("The 'answers' argument is not valid");
            throw new IllegalArgumentException("Invalid answer");
        }

        if (this.checkQuestPresence(quest)) {
            LOG.warn("The 'quest' argument is not valid because this question already exists");
            throw new IllegalArgumentException(quest + "This question has already been registered");
        }

        this.questAndAns.add(new Pair<>(quest, answers));
        LOG.info("Question added successfully");
    }

    @Override
    public void removeQuestion(final String quest) {
        if (!this.checkQuestPresence(quest)) {
            LOG.warn("The given quest doesn't exist. Can not remove a non existing question");
            throw new IllegalArgumentException("Unknown quest. Can not remove it");
        }
        final Pair<String, List<X>> questPair = this.questAndAns.stream()
                .filter(x -> x.getX().equals(quest))
                .findAny()
                .get();

        this.questAndAns.remove(this.questAndAns.indexOf(questPair));
        LOG.info("Question removed successfully");

    }

    @Override
    public Exercise<X> build() {

        if (this.built) {
            LOG.warn("Can not build twice. Reset the builder first ");
            throw new IllegalStateException("Can only build once");
        }

        if (this.questAndAns.isEmpty()) {
            LOG.warn("Can not build an empty exercise ");
            throw new IllegalStateException("Can not build an empty exercise");
        }

        this.built = true;

        return new ExerciseImpl<>(this.questAndAns, this.type);
    }

    private boolean checkQuestPresence(final String quest) {
        return this.questAndAns.stream().filter(x -> x.getX().equals(quest)).findAny().isPresent();
    }

    @Override
    public void reset() {
        this.built = false;
        this.questAndAns.clear();
        LOG.info("The builder has been reset successfully");
    }

    @Override
    public boolean isBuilt() {
        return this.built;
    }

    /**
     * checks if the given answer is acceptable.
     *
     * @param answers
     *            answer to check
     *
     * @return true if the answer is valid, false otherwise
     */
    protected boolean validAnswer(final List<X> answers) {
        return !((answers.size() > this.type.getMaxAnswers()) || answers.isEmpty());
    }
}
