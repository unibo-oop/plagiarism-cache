package com.jlearn.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.Test;

import com.jlearn.model.checker.CheckLog;
import com.jlearn.model.checker.Checker;
import com.jlearn.model.checker.UnitChecker;
import com.jlearn.model.checker.UnitCheckerImpl;
import com.jlearn.model.checker.Checker.Result;
import com.jlearn.model.exercises.BooleanExerciseBuilder;
import com.jlearn.model.exercises.Exercise;
import com.jlearn.model.exercises.ExerciseBuilder;
import com.jlearn.model.exercises.StringExerciseBuilder;
import com.jlearn.model.exercises.Unit;
import com.jlearn.model.exercises.UnitImpl;
import com.jlearn.model.utilities.Levels;
import com.jlearn.view.utilities.enums.ExerciseType;

/**
 * Checker tester.
 */
public class TestChecker {

    private Unit initUnit() {
        final List<Exercise<?>> exercises = new ArrayList<>();

        // Create and add a multiple choice exercise
        final ExerciseBuilder<String> multiBuilder = new StringExerciseBuilder(ExerciseType.MULTI);
        multiBuilder.addQuestion("monkey", Arrays.asList("banana"));
        multiBuilder.addQuestion("ringo", Arrays.asList("pingu"));
        multiBuilder.addQuestion("ponzio", Arrays.asList("pilates"));

        exercises.add(multiBuilder.build());

        // Create and add a trueFalse Exercise
        final ExerciseBuilder<Boolean> tfBuilder = new BooleanExerciseBuilder(ExerciseType.TRUE_FALSE);
        tfBuilder.addQuestion("q1", Arrays.asList(true));
        tfBuilder.addQuestion("q2", Arrays.asList(false));
        tfBuilder.addQuestion("q3", Arrays.asList(true));

        exercises.add(tfBuilder.build());

        // Create and add a complete exercise
        final ExerciseBuilder<String> completeBuilder = new StringExerciseBuilder(ExerciseType.COMPLETE);
        completeBuilder.addQuestion("avengers", Arrays.asList("Thor", "Hulk", "Ugo"));
        completeBuilder.addQuestion("goku", Arrays.asList("Kamehameha"));
        completeBuilder.addQuestion("numbers", Arrays.asList("ein", "zwei", "polizei"));

        exercises.add(completeBuilder.build());

        // Create unit
        return new UnitImpl(exercises, 1, "title");

    }

    /**
     * Basic Tester.
     */
    @Test

    public void basicTest() {

        final Unit unit = this.initUnit();

        // Create answers to unit.

        final List<List<Optional<?>>> unitAnswers = new ArrayList<>();

        // add Answers to exercise 0 (multiple choice exercise)
        unitAnswers.add(Arrays.asList(Optional.of("banana"), Optional.of("pilates"), Optional.of("fuffa"))); // answer 0
                                                                                                             // correct,
                                                                                                             // others
                                                                                                             // incorrect

        // add Answers to exercise 1 (true/false)
        unitAnswers.add(Arrays.asList(Optional.of(false), Optional.of(false), Optional.of(true))); // answer 0
                                                                                                   // incorrect, others
                                                                                                   // correct

        // add Answers to exercise 2 (complete)
        unitAnswers.add(Arrays.asList(Optional.of("Thor"), Optional.of("Hulk"), Optional.of("b"), Optional.of("kiki"),
                Optional.empty(), Optional.of("zwei"), Optional.of("polizei"))); // answers 2,3 incorrect, 4 null,
                                                                                 // others correct

        // Create unit checker
        final UnitChecker uCheck = new UnitCheckerImpl(unit, unitAnswers, 0, Levels.EASY);

        // Test total answers by result
        final int rightNum = 7;
        final int wrongNum = 5;
        final int nullNum = 1;

        assertEquals(rightNum, uCheck.totalAnswersByResult(Result.RIGHT_ANSWER));
        assertEquals(wrongNum, uCheck.totalAnswersByResult(Result.WRONG_ANSWER));
        assertEquals(nullNum, uCheck.totalAnswersByResult(Result.NULL_ANSWER));

        // Test unit total score
        final int expectedScore = (7 * Checker.Result.RIGHT_ANSWER.getPoints())
                + (5 * Checker.Result.WRONG_ANSWER.getPoints()) + (1 * Checker.Result.NULL_ANSWER.getPoints());
        assertEquals(expectedScore, uCheck.getUnitTotalScore());

        // Test unit scores
        final int ex0Score = (1 * Result.RIGHT_ANSWER.getPoints())
                + (2 * Result.WRONG_ANSWER.getPoints());
        final int ex1Score = (2 * Result.RIGHT_ANSWER.getPoints())
                + (1 * Result.WRONG_ANSWER.getPoints());
        final int ex2Score = (4 * Result.RIGHT_ANSWER.getPoints())
                + (2 * Result.WRONG_ANSWER.getPoints())
                + (1 * Result.NULL_ANSWER.getPoints());

        assertEquals(Arrays.asList(ex0Score, ex1Score, ex2Score), uCheck.getUnitScores());

        // Test CheckLogs
        final List<CheckLog> logs = uCheck.getUnitCheckLogs();

        assertEquals(rightNum, logs.stream().mapToInt(x -> x.getAnswersNumberByResult(Result.RIGHT_ANSWER)).sum());
        assertEquals(wrongNum, logs.stream().mapToInt(x -> x.getAnswersNumberByResult(Result.WRONG_ANSWER)).sum());
        assertEquals(nullNum, logs.stream().mapToInt(x -> x.getAnswersNumberByResult(Result.NULL_ANSWER)).sum());

        final List<Integer> iDs = new ArrayList<>();
        logs.stream().forEach(x -> iDs.addAll(x.getAnswersIDsByResult(Result.RIGHT_ANSWER)));

        final List<Integer> expectedIDs = new ArrayList<>(Arrays.asList(0, 1, 2, 0, 1, 5, 6));

        assertEquals(expectedIDs, iDs);

        iDs.clear();
        expectedIDs.clear();
        expectedIDs.addAll(Arrays.asList(4));
        logs.stream().forEach(x -> iDs.addAll(x.getAnswersIDsByResult(Result.NULL_ANSWER)));
        assertEquals(expectedIDs, iDs);

        iDs.clear();
        expectedIDs.clear();
        expectedIDs.addAll(Arrays.asList(1, 2, 0, 2, 3));
        logs.stream().forEach(x -> iDs.addAll(x.getAnswersIDsByResult(Result.WRONG_ANSWER)));
        assertEquals(expectedIDs, iDs);
    }

    /**
     * Problems with input Tester.
     */
    @Test
    public void wrongInputTest() {

        final Unit unit = this.initUnit();

        // Test fewer answers than expected

        final List<List<Optional<?>>> unitAnswers = new ArrayList<>();

        // add fewer answers than requested by the exercise (2 instead of 3)
        unitAnswers.add(Arrays.asList(Optional.of("alpha"), Optional.of("bravo")));

        unitAnswers.add(Arrays.asList(Optional.of(false), Optional.of(false), Optional.of(true)));
        unitAnswers.add(Arrays.asList(Optional.of("charlie"), Optional.of("echo"), Optional.of("bravo"),
                Optional.of("delta"), Optional.of("two"), Optional.of("foxtrot"), Optional.of("asdrubale")));

        // the given answer mustn't be fewer than the expected answers
        try {
            new UnitCheckerImpl(unit, unitAnswers, 0, Levels.EASY);
            fail("invalid given answers");
        } catch (final IllegalArgumentException e) {
            e.printStackTrace();
        } catch (final Exception e) {
            fail("should throw an IllegalArgumentException,not a " + e.getClass());
        }

        unitAnswers.clear();

        // Test more answers than expected

        unitAnswers.add(Arrays.asList(Optional.of("ciquita"), Optional.of("pilato"), Optional.of("fuffa")));
        // add more Answers than expected (4 instead of 3)
        unitAnswers.add(Arrays.asList(Optional.of(false), Optional.of(false), Optional.of(true), Optional.of(true)));
        unitAnswers.add(Arrays.asList(Optional.of("Thortelli"), Optional.of("a"), Optional.of("b"),
                Optional.of("Kamehameha"), Optional.of("zazu"), Optional.of("bubu"), Optional.of("cobra 11")));
        // the given answer mustn't be more than the expected answers
        try {
            new UnitCheckerImpl(unit, unitAnswers, 0, Levels.EASY);
            fail("invalid given answers ");
        } catch (final IllegalArgumentException e) {
            e.printStackTrace();
        } catch (final Exception e) {
            fail("should throw an IllegalArgumentException,not a  " + e.getClass());
        }

        unitAnswers.clear();

        // Test empty answers

        // add empty Answers list to exercise 0 (multiple choice exercise)
        unitAnswers.add(Collections.emptyList());
        unitAnswers.add(Arrays.asList(Optional.of(false), Optional.of(false), Optional.of(true)));
        unitAnswers.add(Arrays.asList(Optional.of("Thortino"), Optional.of("a"), Optional.of("b"),
                Optional.of("Kurochan"), Optional.of("ein"), Optional.of("ein"), Optional.of("cobra 11"))); // answers
                                                                                                            // 1,2,4
                                                                                                            // incorrect,
                                                                                                            // others
                                                                                                            // correct

        // the given answer mustn't be empty
        try {
            new UnitCheckerImpl(unit, unitAnswers, 0, Levels.EASY);
            fail("invalid given answers ");
        } catch (final IllegalArgumentException e) {
            e.printStackTrace();
        } catch (final Exception e) {
            fail("should throw an IllegalArgumentException,not a  " + e.getClass());
        }

        unitAnswers.clear();

        // Test no matching answers type

        // add Answers to exercise 0 (multiple choice exercise)
        unitAnswers.add(Arrays.asList(Optional.of("ciquita"), Optional.of("pilato"), Optional.of("fuffa"))); // answer 0
                                                                                                             // correct,
                                                                                                             // others
                                                                                                             // incorrect

        // add Answers to exercise 1 (true/false)
        unitAnswers.add(Arrays.asList(Optional.of(false), Optional.of(false), Optional.of(true))); // answer 0
                                                                                                   // incorrect, others
                                                                                                   // correct

        // add Answers to exercise 2 (complete)
        unitAnswers.add(Arrays.asList(Optional.of("Thornio"), Optional.of("Hulk"), Optional.of("b"),
                Optional.of("kiki"), Optional.empty(), Optional.of("zwei"))); // answers 1,2,4 incorrect, others correct

        // the given answer must match the exercises answers type
        try {
            new UnitCheckerImpl(unit, unitAnswers, 0, Levels.EASY);
            fail("invalid given answers ");
        } catch (final IllegalArgumentException e) {
            e.printStackTrace();
        } catch (final Exception e) {
            fail("should throw an IllegalArgumentException,not a  " + e.getClass());
        }
    }

}
