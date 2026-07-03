package com.jlearn.model.exercises;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.jlearn.view.utilities.enums.ExerciseType;

/**
 *
 * Unit interface implementation.
 *
 *
 */
public class UnitImpl implements Unit {

    private final List<Exercise<?>> exercises;
    private final int unitID;
    private final String title;
    private static final Logger LOG = Logger.getLogger(UnitImpl.class);

    /**
     *
     * The constructor.
     *
     * @param exercises
     *            the exercises this unit is composed of
     * @param unitID
     *            the unit identifier
     * @param title
     *            the unit title
     *
     */
    public UnitImpl(final List<Exercise<?>> exercises, final int unitID, final String title) {
        LOG.setLevel(Level.WARN);
        this.exercises = exercises;
        this.unitID = unitID;
        this.title = title;
        LOG.info("Unit initialized");
    }

    @Override
    public List<Exercise<?>> getExercises() {

        return this.exercises;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = (prime * result) + ((this.exercises == null) ? 0 : this.exercises.hashCode());
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
        final UnitImpl other = (UnitImpl) obj;
        if (this.exercises == null) {
            if (other.exercises != null) {
                return false;
            }
        } else if (!this.exercises.equals(other.exercises)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "UnitImpl [exercises=" + this.exercises + "]";
    }

    @Override
    public List<Exercise<?>> getExercisesByType(final ExerciseType type) {

        return this.exercises.stream().filter(x -> x.getType().equals(type)).collect(Collectors.toList());
    }

    @Override
    public List<List<Integer>> getDetailedNumAnswers() {

        return this.exercises.stream()
                .map(x -> x.getNumAnswersForEachQuestion())
                .collect(Collectors.toList());
    }

    @Override
    public int getUnitID() {

        return this.unitID;
    }

    @Override
    public String getUnitTitle() {
        return this.title;
    }

}
