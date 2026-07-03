package com.jlearn.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.Test;

import com.jlearn.controller.parser.Parser;
import com.jlearn.controller.parser.ParserImpl;
import com.jlearn.model.utilities.Pair;
import com.jlearn.view.utilities.enums.ExerciseType;

/**
 * {@link Parser} tester.
 */
public class ParserTest {

    /**
     * Try to create new parser without init and show if all error is throwed correctly.
     */
    @Test
    public void createAndTestParser() {

        final Parser pars = new ParserImpl();
        try {
            org.junit.Assert.assertTrue(pars.getUnitName().isEmpty());
            org.junit.Assert.assertTrue(pars.getMultiExRisp().isEmpty());
            assertFalse(pars.hasNext());
            pars.next();
            fail("Need trow NoSuchElementException");
        } catch (final NoSuchElementException e) {
            e.printStackTrace();
        }

    }

    /**
     * Reinit Parser and test if get return the all right, i don't apply all next test too long.
     */
    @Test
    public void initAndTestParser() {
        try {
            final Parser pars = new ParserImpl();
            pars.inizializeParser(0);
            org.junit.Assert.assertTrue(pars.hasNext());
            org.junit.Assert.assertEquals(pars.getUnitName(), "Present Simple");

            org.junit.Assert.assertEquals(pars.getMultiExRisp(),
                    new ArrayList<>(Arrays.asList(Arrays.asList("washs", "washes", "wash"),
                            Arrays.asList("get up", "gets up", "get ups"),
                            Arrays.asList("write", "writes", "writs"), Arrays.asList("gos", "go", "goes"),
                            Arrays.asList("laik", "like", "likes"), Arrays.asList("his", "is", "are"),
                            Arrays.asList("am", "ham", "is"),
                            Arrays.asList("don’t play", "don’t plays", "doesn’t play"))));

            org.junit.Assert.assertEquals(pars.exerciseSequence(), new ArrayList<>(Arrays
                    .asList(ExerciseType.TRUE_FALSE, ExerciseType.MULTI, ExerciseType.COMPLETE, ExerciseType.AUDIO)));

            org.junit.Assert.assertEquals(pars.next(),
                    new Pair<ExerciseType, List<Pair<String, List<String>>>>(ExerciseType.TRUE_FALSE,
                            new ArrayList<>(Arrays.asList(
                                    new Pair<String, List<String>>("”The cat is on the table” is a simple present form",
                                            new ArrayList<>(Arrays.asList("T"))),
                                    new Pair<String, List<String>>(
                                            "”He wrote a letter for her” is a simple present form",
                                            new ArrayList<>(Arrays.asList("F"))),
                                    new Pair<String, List<String>>(
                                            "”You aren’t a fireman” is a negative form",
                                            new ArrayList<>(Arrays.asList("T"))),
                                    new Pair<String, List<String>>(
                                            "”Are we friends?” isn’t a question",
                                            new ArrayList<>(Arrays.asList("F"))),
                                    new Pair<String, List<String>>(
                                            "”Don’t lie to me” isn’t a question",
                                            new ArrayList<>(Arrays.asList("T"))),
                                    new Pair<String, List<String>>(
                                            "”Carrol cleans the bathroom” is a correct sentence",
                                            new ArrayList<>(Arrays.asList("T"))),
                                    new Pair<String, List<String>>(
                                            "”It isn’t true that you know nothing” doesn’t mean you know something",
                                            new ArrayList<>(Arrays.asList("F"))),
                                    new Pair<String, List<String>>(
                                            "”Practice make perfect” isn’t a wrong sentence",
                                            new ArrayList<>(Arrays.asList("F")))))));

        } catch (final IOException e) {
            fail("Error IO,control if file exist and is readable.");
            e.printStackTrace();
        }
    }

}
