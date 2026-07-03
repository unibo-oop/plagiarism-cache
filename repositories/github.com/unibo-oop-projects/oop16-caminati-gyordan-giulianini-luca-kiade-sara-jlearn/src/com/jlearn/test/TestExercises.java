package com.jlearn.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import org.junit.Test;

import com.jlearn.model.exercises.BooleanExerciseBuilder;
import com.jlearn.model.exercises.Exercise;
import com.jlearn.model.exercises.ExerciseBuilder;
import com.jlearn.model.exercises.StringExerciseBuilder;
import com.jlearn.view.utilities.enums.ExerciseType;

/**
 *
 * Exercise methods tester.
 */
public class TestExercises {

    /**
     *
     * Multiple builder test.
     */
    @Test
    public void testMulti() {

        /*Basic use*/

        //Testing the builder
        final ExerciseBuilder<String> multiBuilder = new StringExerciseBuilder(ExerciseType.MULTI);

        System.out.println(Collections.emptyList().size());
        multiBuilder.addQuestion("abcd?", Arrays.asList("alpha beta"));
        multiBuilder.addQuestion("1-2-3?", Arrays.asList("one two three"));
        multiBuilder.addQuestion("to Remove", Arrays.asList("go away"));
        multiBuilder.removeQuestion("to Remove");
        assertFalse(multiBuilder.isBuilt());

        //testing some multiple choice exercise methods
        final Exercise<String> multi = multiBuilder.build();
        assertTrue(multiBuilder.isBuilt());
        assertEquals(Arrays.asList("abcd?", "1-2-3?"), multi.getQuestions());
        assertEquals(Arrays.asList("alpha beta"), multi.getAnswer("abcd?"));
        assertEquals(Arrays.asList("alpha beta", "one two three"), multi.getFlatAnswers());
        assertEquals(2, multi.getNumAnswers());
        assertEquals(ExerciseType.MULTI, multi.getType());

        /*Testing builder exceptions*/

        //can not build twice
        try {
            multiBuilder.build();
            fail("can not build twice. Reset first");
        } catch (final IllegalStateException e) {
            e.printStackTrace();
        } catch (final Exception e) {
            fail("should throw an IllegalStateException, not a  " + e.getClass());
        }

        multiBuilder.reset();
        multiBuilder.addQuestion("higif", Arrays.asList("lalala"));
        assertFalse(multiBuilder.isBuilt());

        //can not add a question having no answer
        try {
            multiBuilder.addQuestion("LoL", Collections.emptyList());
            fail("can not add quest");
        } catch (final IllegalArgumentException e) {
            e.printStackTrace();
        } catch (final Exception e) {
            fail("should throw an IllegalArgumentException, not a  " + e.getClass());
        }

        //can not add a question having more than an answer
        try {
            multiBuilder.addQuestion("why?", Arrays.asList("42", "yes", "uranium"));
            fail("can not add a question having more than an answer");
        } catch (final IllegalArgumentException e) {
            e.printStackTrace();
        } catch (final Exception e) {
            fail("should throw an IllegalArgumentException, instead of a " + e.getClass());
        }

        //can not add a question which has already been registered
        try {
            multiBuilder.addQuestion("higif", Arrays.asList("abc"));
            fail("cannot add an existing question");
        } catch (final IllegalArgumentException e) {
            e.printStackTrace();
        } catch (final Exception e) {
            fail("should throw an IllegalArgumentException, not a  " + e.getClass());
        }

        //can not remove a non-existing question
        try {
            multiBuilder.removeQuestion("lolol");
            fail("cannot remove a non-existing quest!");
        } catch (final IllegalArgumentException e) {
            e.printStackTrace();
        } catch (final Exception e) {
            fail("should throw an IllegalArgumentException, instead of a " + e.getClass());
        }

        multiBuilder.reset();
        assertFalse(multiBuilder.isBuilt());

        //can not build an empty exercise
        try {
            multiBuilder.build();
            fail("cannot build an empty exercise");
        } catch (final IllegalStateException e) {
            e.printStackTrace();
        } catch (final Exception e) {
            fail("should throw an IllegalStateException, instead of a " + e.getClass());
        }

    }

    /**
     *
     * true/false exercise test.
     */
    @Test
    public void testTrueFalseExercise() {
        /*Basic use*/

        //Testing the builder
        final ExerciseBuilder<Boolean> tfBuilder = new BooleanExerciseBuilder(ExerciseType.TRUE_FALSE);

        tfBuilder.addQuestion("abc?", Arrays.asList(true));
        tfBuilder.addQuestion("1234?", Arrays.asList(false));
        tfBuilder.addQuestion("to remove", Arrays.asList(true));
        tfBuilder.removeQuestion("to remove");
        assertFalse(tfBuilder.isBuilt());

        //testing some true/false exercise methods
        final Exercise<Boolean> tf = tfBuilder.build();
        assertTrue(tfBuilder.isBuilt());
        assertEquals(Arrays.asList("abc?", "1234?"), tf.getQuestions());
        assertEquals(Arrays.asList(true), tf.getAnswer("abc?"));
        assertEquals(Arrays.asList(true, false), tf.getFlatAnswers());
        assertEquals(2, tf.getNumAnswers());
        assertEquals(ExerciseType.TRUE_FALSE, tf.getType());

        /*Testing builder exceptions*/

        //can not build twice
        try {
            tfBuilder.build();
            fail("can not build twice. Reset first");
        } catch (final IllegalStateException e) {
            e.printStackTrace();
        } catch (final Exception e) {
            fail("should throw an IllegalStateException, not a  " + e.getClass());
        }

        tfBuilder.reset();
        tfBuilder.addQuestion("higig", Arrays.asList(true));
        assertFalse(tfBuilder.isBuilt());

        //can not add a question having no answer
        try {
            tfBuilder.addQuestion("l0l", new ArrayList<>());
            fail("cannot add a question having no answer");
        } catch (final IllegalArgumentException e) {
            e.printStackTrace();
        } catch (final Exception e) {
            fail("should throw an IllegalArgumentException,not a " + e.getClass());
        }

        //can not add a question having more than an answer
        try {
            tfBuilder.addQuestion("l0l",
                    Arrays.asList(true, false, false, true));
            fail("cannot add a question having more than an answer");
        } catch (final IllegalArgumentException e) {
            e.printStackTrace();
        } catch (final Exception e) {
            fail("should throw an IllegalArgumentException,not a " + e.getClass());
        }

        //can not add a question which has already been registered
        try {
            tfBuilder.addQuestion("higig", Arrays.asList(false));
            fail("cannot add an existing question");
        } catch (final IllegalArgumentException e) {
            e.printStackTrace();
        } catch (final Exception e) {
            fail("should throw a IllegalArgumentException, not a " + e.getClass());
        }

        //can not remove a non-existing question
        try {
            tfBuilder.removeQuestion("lol");
            fail("cannot add an existing quest");
        } catch (final IllegalArgumentException e) {
            e.printStackTrace();
        } catch (final Exception e) {
            fail("should throw a IllegalArgumentException, not a " + e.getClass());
        }

        tfBuilder.reset();
        assertFalse(tfBuilder.isBuilt());

        //can not build an empty exercise
        try {
            tfBuilder.build();
            fail("cannot build an empty exercise");
        } catch (final IllegalStateException e) {
            e.printStackTrace();
        } catch (final Exception e) {
            fail("should throw an IllegalStateException, not a " + e.getClass());
        }

    }

    /**
     *
     * complete exercise test.
     */
    @Test
    public void testCompleteExercise() {
        /*Basic use*/

        //Testing the builder
        final ExerciseBuilder<String> completeBuilder = new StringExerciseBuilder(ExerciseType.COMPLETE);

        completeBuilder.addQuestion("abce?", Arrays.asList("A", "B", "C", "E"));
        completeBuilder.addQuestion("123?", Arrays.asList("E", "B", "A", "F"));
        completeBuilder.addQuestion("toremove", Arrays.asList("rem"));
        completeBuilder.removeQuestion("toremove");
        assertFalse(completeBuilder.isBuilt());

        //testing some complete exercise methods
        final Exercise<String> complete = completeBuilder.build();
        assertTrue(completeBuilder.isBuilt());
        assertEquals(Arrays.asList("abce?", "123?"), complete.getQuestions());
        assertEquals(Arrays.asList("A", "B", "C", "E"), complete.getAnswer("abce?"));
        assertEquals(Arrays.asList("A", "B", "C", "E", "E", "B", "A", "F"), complete.getFlatAnswers());
        assertEquals(8, complete.getNumAnswers());
        assertEquals(ExerciseType.COMPLETE, complete.getType());

        /*Testing builder exceptions*/

        //can not build twice
        try {
            completeBuilder.build();
            fail("can not build twice. Reset first");
        } catch (final IllegalStateException e) {
            e.printStackTrace();
        } catch (final Exception e) {
            fail("should throw an IllegalStateException, not a " + e.getClass());
        }

        completeBuilder.reset();
        completeBuilder.addQuestion("higi", Arrays.asList("gigi", "bubu", "ufu", "qwer"));
        assertFalse(completeBuilder.isBuilt());

        //can not add a question having no answer
        try {
            completeBuilder.addQuestion("lol", Collections.emptyList());
            fail("cannot add quest");
        } catch (final IllegalArgumentException e) {
            e.printStackTrace();
        } catch (final Exception e) {
            fail("should throw an IllegalArgumentException, not a " + e.getClass());
        }

        //can not add a question which has already been registered
        try {
            completeBuilder.addQuestion("higi", Arrays.asList("bubu"));
            fail("cannot add an existing quest");
        } catch (final IllegalArgumentException e) {
            e.printStackTrace();
        } catch (final Exception e) {
            fail("should throw an IllegalArgumentException, not a " + e.getClass());
        }

        //can not remove a non-existing question
        try {
            completeBuilder.removeQuestion("lol");
            fail("cannot add an existing quest");
        } catch (final IllegalArgumentException e) {
            e.printStackTrace();
        } catch (final Exception e) {
            fail("should throw an IllegalArgumentException, not a " + e.getClass());
        }

        completeBuilder.reset();
        assertFalse(completeBuilder.isBuilt());

        //can not build an empty exercise
        try {
            completeBuilder.build();
            fail("cannot build an empty exercise");
        } catch (final IllegalStateException e) {
            e.printStackTrace();
        } catch (final Exception e) {
            fail("should throw an IllegalStateException, not a " + e.getClass());
        }

    }
}
