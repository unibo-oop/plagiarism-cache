package com.jlearn.controller.parser;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import com.jlearn.model.utilities.Pair;
import com.jlearn.view.utilities.enums.ExerciseType;

/**
 * Represent an {@link File}.csv parser.
 */
public interface Parser extends Iterator<Pair<ExerciseType, List<Pair<String, List<String>>>>> {

    /**
     * @return {@link ExerciseType} return the list of {@link ExerciseType} for the order.
     */
    List<ExerciseType> exerciseSequence();

    /**
     * Return the possible answer for {@link ExerciseType} Multi.
     *
     * @return {@link List} of {@link String} whit all possible answer into another {@link List}
     */
    List<List<String>> getMultiExRisp();

    /**
     *
     * @return The name of the unit read.
     */
    String getUnitName();

    /**
     *
     * @param selectedIndex
     *            index of the selected exercise.
     * @throws IOException
     *             can't Access find or read csv file.
     */
    void inizializeParser(int selectedIndex) throws IOException;

    /**
     * Reset the Iterator to the starting index whit the same data.
     */
    void resetParserIterator();

}
