package com.jlearn.model.checker;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.jlearn.model.exercises.Exercise;
import com.jlearn.model.exercises.Unit;
import com.jlearn.model.utilities.Levels;

/**
 * UnitChecker implementation.
 */
public class UnitCheckerImpl implements UnitChecker {

    private final Unit unit;
    private final List<CheckLog> logs;
    private final Checker checker;
    private final List<List<Optional<?>>> exAns;
    private final Double rightAnsCoefficient;
    private static final Logger LOG = Logger.getLogger(UnitCheckerImpl.class);

    /**
     * The constructor.
     *
     * @param unit
     *            the unit to check
     * @param exAns
     *            a map of this unit exercises and their related given answers
     * @param timeLeft
     *            seconds left after finishing the whole unit
     * @param level
     *            the unit level, from which depends the given time
     */
    public UnitCheckerImpl(final Unit unit, final List<List<Optional<?>>> exAns, final int timeLeft,
            final Levels level) {
        LOG.setLevel(Level.WARN);
        this.unit = unit;
        this.logs = new ArrayList<>();
        this.checker = new CheckerImpl();
        this.exAns = exAns;
        this.rightAnsCoefficient = (double) ((timeLeft / level.getSecs()) + 1);
        LOG.info("Unit checker initialized");
        this.check();
        LOG.info("End of checking");

    }

    private void check() {

        final List<Exercise<?>> exercises = this.unit.getExercises();

        exercises.forEach(
                x -> this.logs.add(this.checker.check(x, this.exAns.get(exercises.indexOf(x)))));
    }

    @Override
    public int getUnitTotalScore() {
        return this.getUnitScores().stream().mapToInt(x -> x).sum();
    }

    @Override
    public List<Integer> getUnitScores() {

        final List<Double> scores = this.logs.stream()
                .map(x -> ((x.getAnswersNumberByResult(Checker.Result.RIGHT_ANSWER)
                        * Checker.Result.RIGHT_ANSWER.getPoints()) * this.rightAnsCoefficient)
                        + (x.getAnswersNumberByResult(Checker.Result.WRONG_ANSWER)
                                * Checker.Result.WRONG_ANSWER.getPoints())
                        + (x.getAnswersNumberByResult(Checker.Result.NULL_ANSWER)
                                * Checker.Result.NULL_ANSWER.getPoints()))
                .collect(Collectors.toList());
        return scores.stream().map(x -> x.intValue()).collect(Collectors.toList());
    }

    @Override
    public List<CheckLog> getUnitCheckLogs() {
        return this.logs;
    }

    @Override
    public int totalAnswersByResult(final Checker.Result result) {
        return this.logs.stream().mapToInt(x -> x.getAnswersNumberByResult(result)).sum();
    }

}
