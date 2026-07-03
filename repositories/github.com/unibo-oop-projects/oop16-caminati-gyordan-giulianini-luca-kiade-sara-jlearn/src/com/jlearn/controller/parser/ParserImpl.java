package com.jlearn.controller.parser;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.jlearn.controller.fileio.FileManager;
import com.jlearn.controller.fileio.FileManagerImpl;
import com.jlearn.model.utilities.Pair;
import com.jlearn.view.utilities.enums.ExerciseType;

/**
 * An class represent the csv file parser whit pattern iterator.
 */
public class ParserImpl implements Parser {

    private static final String                                              ANSWER_REGEX          = "+";
    private static final String                                              QUESTION_REGEX        = "*";
    private static final char                                                WORD_REGEX            = '#';
    private static final String                                              EXERCISE_DELIMITER    = "----";
    private static final String                                              EXERCISE_DELIMITER_RE = "-";
    private static final Logger                                              LOG                   = Logger
            .getLogger(ParserImpl.class);
    private int                                                              index;
    private String                                                           unitName;
    private final List<List<String>>                                         listaCombo;
    private final List<Pair<ExerciseType, List<Pair<String, List<String>>>>> lisModule;
    private final FileManager                                                fileManager;

    /**
     * Initialize the parser.
     */
    public ParserImpl() {
        LOG.setLevel(Level.WARN);
        this.fileManager = FileManagerImpl.getInstance();
        this.index = 0;
        this.unitName = "";
        this.lisModule = new ArrayList<>();
        this.listaCombo = new ArrayList<>();
    }

    private void clearData() {
        this.lisModule.clear();
        this.listaCombo.clear();
        this.unitName = "";
    }

    @Override
    public List<ExerciseType> exerciseSequence() {
        return this.lisModule.isEmpty() ? new ArrayList<>()
                : this.lisModule.stream().map(pair -> pair.getX()).collect(Collectors.toList());
    }

    @Override

    public List<List<String>> getMultiExRisp() {
        return new ArrayList<>(this.listaCombo);
    }

    @Override
    public String getUnitName() {
        return this.unitName;
    }

    @Override
    public boolean hasNext() {
        return this.lisModule.isEmpty() ? false : this.index < (ExerciseType.values().length);
    }

    @Override
    public void inizializeParser(final int selectedIndex) throws IOException {
        this.clearData();
        this.resetParserIterator();

        try (BufferedReader reader = this.fileManager.getCsvExerciseReader(selectedIndex)) {

            final List<String> listaRisp = new ArrayList<>();
            final List<String> listaDom = new ArrayList<>();
            final List<String> listaRispPoss = new ArrayList<>();
            String dom = null;
            final List<Pair<String, List<String>>> lisExercise = new ArrayList<>();

            for (final CSVRecord record : CSVFormat.DEFAULT.withDelimiter(WORD_REGEX)
                    .parse(reader)) {

                for (final String field : record) {

                    if (field.contains(EXERCISE_DELIMITER)) {

                        if (!listaRisp.isEmpty()) {
                            lisExercise.add(new Pair<String, List<String>>(dom, new ArrayList<>(listaRisp)));
                            listaRisp.clear();
                        }
                        this.lisModule
                                .add(new Pair<>(ExerciseType.valueOf(field.replace(EXERCISE_DELIMITER_RE, "").trim()),
                                        new ArrayList<>(lisExercise)));

                        listaDom.clear();
                        lisExercise.clear();

                    } else {
                        if (field.contains(QUESTION_REGEX)) {

                            if (!listaRisp.isEmpty()) {
                                lisExercise.add(new Pair<String, List<String>>(dom, new ArrayList<>(listaRisp)));
                                listaRisp.clear();
                            }

                            dom = field.replace(QUESTION_REGEX, "")
                                    .trim();

                            if (!listaRispPoss.isEmpty()) {
                                this.listaCombo.add(new ArrayList<>(listaRispPoss));
                                listaRispPoss.clear();
                            }

                        } else if (field.contains(ANSWER_REGEX)) {
                            listaRisp.add(field.replace(ANSWER_REGEX, "")
                                    .trim());
                        } else {
                            listaRispPoss.add(field.trim());
                        }
                    }

                }

            }

        }

        this.unitName = FileManagerImpl.getEsName(selectedIndex);

    }

    @Override
    public Pair<ExerciseType, List<Pair<String, List<String>>>> next() {
        if (!this.hasNext()) {
            LOG.warn("No More element in Parser!");
            throw new NoSuchElementException();
        }

        return new Pair<>(
                this.lisModule.get(this.index).getX(),
                this.lisModule.get(this.index++).getY());

    }

    @Override
    public void resetParserIterator() {
        this.index = 0;
    }

    @Override
    public String toString() {
        return "ParserImpl [index=" + this.index + ", unitName=" + this.unitName + ", listaCombo=" + this.listaCombo
                + ", lisModule="
                + this.lisModule + ", fileManager=" + this.fileManager + "]";
    }

}
