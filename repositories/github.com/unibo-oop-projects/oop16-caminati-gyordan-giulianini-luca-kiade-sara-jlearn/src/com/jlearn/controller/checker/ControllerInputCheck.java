package com.jlearn.controller.checker;

import java.util.Collection;
import java.util.List;

import com.jlearn.controller.parser.ParserImpl;
import com.jlearn.model.checker.UnitChecker;
import com.jlearn.model.exercises.Unit;
import com.jlearn.model.users.User;
import com.jlearn.view.ui.UIStatistics;
import com.jlearn.view.utilities.enums.ExerciseType;

/**
 * Represent the Part of Controller Used for check input.
 */
public interface ControllerInputCheck {

    /**
     * This method check if user place correctly the answer.
     *
     * @param listUserAnsw
     *            The list Contains all {@link User} answer.
     * @param answerErrorPos
     *            The list contains all position of wrong placed answer.
     *
     * @param unit
     *            The {@link Unit}.
     * @return true if no one answer is bad placed.
     */
    boolean checkInputView(List<String[]> listUserAnsw,
            List<Integer> answerErrorPos, Unit unit);

    /**
     * Check if the {@link User} can be registered.
     *
     * @param user
     *            The {@link User} to check.
     * @param ser
     *            The pattern observer for update all {@link UIStatistics}.
     * @return true if the {@link User} pass the check.
     */
    Boolean checkUserInput(User user, Collection<UIStatistics> ser);

    /**
     * Return all position of the wrong answer.
     *
     * @return the position of wrong answer.
     *
     * @param checker
     *            the {@link UnitChecker} for discover wrong answer.
     * @param exerciseSequence
     *            the exercise sequence read to {@link ParserImpl}.
     * @param unit
     *            the {@link Unit} contain all correct answer.
     */
    List<List<Integer>> getWrongAnswerPosition(Unit unit, UnitChecker checker,
            List<ExerciseType> exerciseSequence);

}