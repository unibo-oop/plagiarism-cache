package com.jlearn.controller.checker;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.jlearn.controller.ControllerExerciseImpl;
import com.jlearn.model.checker.UnitChecker;
import com.jlearn.model.exercises.Unit;
import com.jlearn.model.users.User;
import com.jlearn.view.ui.UIStatistics;
import com.jlearn.view.utilities.enums.ExerciseType;
import com.jlearn.view.utilities.enums.NotificationType;
import com.jlearn.view.utilities.enums.NotificationType.Duration;

import javafx.application.Platform;

/**
 * Check input form view to controller.
 *
 */
public final class ControllerInputCheckImpl implements ControllerInputCheck {

    private static final int    MAX_AGE          = 120;
    private static final int    MIN_AGE          = 1;
    private static final int    INPUT_MAX_LENGTH = 30;
    private static final int    NUMBER_LENGTH    = 10;
    private static final String CHECK_INP_STR    = "[a-zA-Z.?]*";
    private static final String ERROR_STR        = "Error ";

    private static ControllerInputCheck controllerInpCheckIn;

    /**
     *
     * @return The only instance of {@link ControllerInputCheckImpl}
     */
    public static ControllerInputCheck getInstance() {

        synchronized (ControllerExerciseImpl.class) {
            if (controllerInpCheckIn == null) {

                controllerInpCheckIn = new ControllerInputCheckImpl();
            }
        }
        return controllerInpCheckIn;
    }

    private ControllerInputCheckImpl() {
    }

    @Override
    public boolean checkInputView(final List<String[]> listUserAnsw,
            final List<Integer> answerErrorPos, final Unit unit) {

        final List<?> listAnsw = unit.getExercisesByType(ExerciseType.COMPLETE).get(0).getAnswers();

        for (int i = 0; i < listAnsw.size(); i++) {
            if (((List<?>) listAnsw.get(i)).size() != listUserAnsw.get(i).length) {
                answerErrorPos.add(i);
            }

        }
        return answerErrorPos.isEmpty();

    }

    @Override
    public Boolean checkUserInput(final User user, final Collection<UIStatistics> ser) {

        return this.checkNickNam(user, ser) && this.checkAge(user, ser) && this.checkEmail(user, ser)
                && this.checkPhone(user, ser)
                && this.checkSurNam(user, ser) && this.checkName(user, ser);

    }

    @Override
    public List<List<Integer>> getWrongAnswerPosition(final Unit unit, final UnitChecker checker,
            final List<ExerciseType> exerciseSequence) {
        final List<Integer> listAnsw = unit.getExercisesByType(ExerciseType.COMPLETE)
                .get(0)
                .getNumAnswersForEachQuestion();

        final List<List<Integer>> listExercisePositionError = new ArrayList<>();

        checker.getUnitCheckLogs().forEach(listWrongWord -> {
            listExercisePositionError
                    .add(listWrongWord.getAnswersIDsByResult(com.jlearn.model.checker.Checker.Result.WRONG_ANSWER));

        });

        final List<Integer> lis = new ArrayList<>();
        listExercisePositionError.get(exerciseSequence.indexOf(ExerciseType.COMPLETE)).forEach(wrongWord -> {
            int pos = 0;
            int val = 0;
            while (pos < listAnsw.size()) {
                val += listAnsw.get(pos);
                if ((val > wrongWord) && !lis.contains(pos)) {

                    lis.add(pos);
                    break;

                }
                pos++;
            }
        });

        listExercisePositionError.set(exerciseSequence.indexOf(ExerciseType.COMPLETE), lis);

        return listExercisePositionError;
    }

    private void informUis1(final Collection<UIStatistics> ser, final String str) {
        if ((ser != null) && (!ser.isEmpty())) {
            Platform.runLater(() -> {
                ser.forEach(
                        (jfxcontroller) -> jfxcontroller.showNotificationPopup(ERROR_STR + str,
                                "Less than 30 letters, and no special char",
                                Duration.MEDIUM,
                                NotificationType.WARNING,
                                null));
            });
        }
    }

    private void informUis2(final Collection<UIStatistics> ser, final String str) {
        if ((ser != null) && (!ser.isEmpty())) {
            Platform.runLater(() -> {
                ser.forEach(
                        (jfxcontroller) -> jfxcontroller.showNotificationPopup(ERROR_STR + str,
                                "Less than 30 letter,only spec char @",
                                Duration.MEDIUM,
                                NotificationType.WARNING,
                                null));
            });
        }
    }

    private void informUis3(final Collection<UIStatistics> ser, final String str) {
        if ((ser != null) && (!ser.isEmpty())) {
            Platform.runLater(() -> {
                ser.forEach(
                        (jfxcontroller) -> jfxcontroller.showNotificationPopup(ERROR_STR + str,
                                "Age ranging from 1 to 120 years",
                                Duration.MEDIUM,
                                NotificationType.WARNING,
                                null));
            });
        }
    }

    private void informUis4(final Collection<UIStatistics> ser, final String str) {
        if ((ser != null) && (!ser.isEmpty())) {
            Platform.runLater(() -> {
                ser.forEach(
                        (jfxcontroller) -> jfxcontroller.showNotificationPopup(ERROR_STR + str,
                                "Your phone must have 10 numbers!",
                                Duration.MEDIUM,
                                NotificationType.WARNING,
                                null));
            });
        }
    }

    private void informUis5(final Collection<UIStatistics> ser, final String str) {
        if ((ser != null) && (!ser.isEmpty())) {
            Platform.runLater(() -> {
                ser.forEach(
                        (jfxcontroller) -> jfxcontroller.showNotificationPopup(ERROR_STR + str,
                                "Less than 30,No number and spec char",
                                Duration.MEDIUM,
                                NotificationType.WARNING,
                                null));
            });
        }
    }

    private boolean checkName(final User user, final Collection<UIStatistics> ser) {

        if (user.getName().matches(CHECK_INP_STR)
                && ((user.getName().length() < INPUT_MAX_LENGTH))) {
            return true;
        }
        this.informUis5(ser, "Name");

        return false;
    }

    private boolean checkSurNam(final User user, final Collection<UIStatistics> ser) {

        if (user.getSurname().matches(CHECK_INP_STR)
                && ((user.getSurname().length() < INPUT_MAX_LENGTH))) {
            return true;
        }

        this.informUis5(ser, "Surname");

        return false;

    }

    private boolean checkNickNam(final User user, final Collection<UIStatistics> ser) {

        if (!user.getNickname().isEmpty() && ((user.getNickname().length() < INPUT_MAX_LENGTH)
                && user.getNickname().matches("[a-zA-Z.?0-9]*"))) {

            return true;
        }

        this.informUis1(ser, "Nickname");

        return false;

    }

    private boolean checkEmail(final User user, final Collection<UIStatistics> ser) {

        if ((user.getEmail().length() < INPUT_MAX_LENGTH)
                && (user.getEmail().matches("[a-zA-Z.@? ]*"))) {
            return true;
        }

        this.informUis2(ser, "Email");

        return false;

    }

    private boolean checkPhone(final User user, final Collection<UIStatistics> ser) {

        if (user.getTel().isEmpty()) {
            return true;
        } else if ((user.getTel()
                .matches("[0-9]+"))
                && (user.getTel()
                        .length() == NUMBER_LENGTH)) {
            return true;
        }

        this.informUis4(ser, "Phone");

        return false;

    }

    private boolean checkAge(final User user, final Collection<UIStatistics> ser) {

        if ((user.getAge() >= MIN_AGE) && (user.getAge() <= MAX_AGE)) {
            return true;
        }
        this.informUis3(ser, "Age");

        return false;
    }

}
