package com.jlearn.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.jlearn.controller.checker.ControllerInputCheck;
import com.jlearn.controller.checker.ControllerInputCheckImpl;
import com.jlearn.controller.fileio.ControllerFileName;
import com.jlearn.controller.fileio.ControllerFolderPaths;
import com.jlearn.controller.fileio.FileManager;
import com.jlearn.controller.fileio.FileManagerImpl;
import com.jlearn.controller.parser.Parser;
import com.jlearn.controller.parser.ParserImpl;
import com.jlearn.controller.sound.ControllerSoudImpl;
import com.jlearn.controller.sound.ControllerSound;
import com.jlearn.controller.timer.ControllerTimer;
import com.jlearn.controller.timer.ControllerTimerImpl;
import com.jlearn.controller.timer.TimerEventImpl;
import com.jlearn.model.checker.UnitChecker;
import com.jlearn.model.checker.UnitCheckerImpl;
import com.jlearn.model.exercises.BooleanExerciseBuilder;
import com.jlearn.model.exercises.Exercise;
import com.jlearn.model.exercises.ExerciseBuilder;
import com.jlearn.model.exercises.StringExerciseBuilder;
import com.jlearn.model.exercises.Unit;
import com.jlearn.model.exercises.UnitImpl;
import com.jlearn.view.ui.UIExercise;
import com.jlearn.view.ui.UITheory;
import com.jlearn.view.utilities.QuestionToView;
import com.jlearn.view.utilities.enums.ExerciseType;
import com.jlearn.view.utilities.enums.NotificationType;
import com.jlearn.view.utilities.enums.NotificationType.Duration;

import eu.hansolo.enzo.notification.NotificationEvent;
import javafx.application.Platform;
import javafx.event.EventHandler;

/**
 * Exercises controller,controls timer and other features.
 */
public final class ControllerExerciseImpl implements ControllerExercise, UpdatableUi {
    private static final String COMPLETE_QUESTION_REGEX = ",";
    private static final String TIMER_THREAD = "Timer Thread";
    private static final Logger LOG = Logger.getLogger(ControllerExerciseImpl.class);
    private static ControllerExercise controllerExObj;

    private final Set<UIExercise> uiExercise;
    private final Set<UITheory> uiTheory;
    private final FileManager filemanager;
    private int actualUnit;
    private final List<ExerciseType> exerciseSequence;
    private final ControllerInputCheck controllerInpCheckIn;
    private Unit unit;
    private final ControllerLogic controllerLogic;
    private final ControllerTimer controllerTimer;
    private final ControllerSound controllerSound;
    private UnitChecker checker;
    private final Parser pars = new ParserImpl();

    private boolean timerThreadStarted;

    /**
     *
     * @return The only instance of {@link ControllerExercise}
     */
    public static ControllerExercise getInstance() {

        synchronized (ControllerExerciseImpl.class) {
            if (controllerExObj == null) {
                controllerExObj = new ControllerExerciseImpl();
            }
        }
        return controllerExObj;
    }

    /**
     * The constructor of {@link ControllerExerciseImpl}.
     *
     */
    private ControllerExerciseImpl() {
        LOG.setLevel(Level.WARN);
        this.controllerSound = new ControllerSoudImpl();
        this.controllerInpCheckIn = ControllerInputCheckImpl.getInstance();
        this.exerciseSequence = new ArrayList<>();
        this.filemanager = FileManagerImpl.getInstance();
        this.controllerLogic = ControllerLogicImpl.getInstance();
        this.actualUnit = 0;
        this.uiExercise = new HashSet<>();
        this.uiTheory = new HashSet<>();
        this.controllerTimer = new ControllerTimerImpl(TIMER_THREAD);
        this.controllerTimer.attacheController(this);

    }

    @Override
    public void attacheExerciseUI(final UIExercise observedUI) {
        this.uiExercise.add(observedUI);
    }

    @Override
    public void attacheTheoryUI(final UITheory observedUI) {
        this.uiTheory.add(observedUI);
    }

    @Override
    public int getActualUnitId() {
        return this.actualUnit;
    }

    @Override
    public List<Integer> getViewModule(final Map<ExerciseType, List<?>> mappaOut) {
        final List<Integer> answerErrorPos = new ArrayList<>();
        final List<List<Optional<?>>> listAns = new ArrayList<>();
        final List<String[]> listUserAnsw = new ArrayList<>();
        this.pars.exerciseSequence().forEach(exTipe -> {

            switch (exTipe) {
            case AUDIO:

                listAns.add(mappaOut.get(exTipe)
                        .stream()
                        .map(answ -> Optional.of((Boolean) answ))
                        .collect(Collectors.toList()));
                break;

            case TRUE_FALSE:

                listAns.add(
                        mappaOut.get(exTipe)
                                .stream()
                                .map(answ -> Optional.of((Boolean) answ))
                                .collect(Collectors.toList()));
                break;

            case MULTI:

                listAns.add(
                        mappaOut.get(exTipe)
                                .stream()
                                .map(answ -> answ == null
                                        ? Optional.empty()
                                        : Optional.of(answ))
                                .collect(Collectors.toList()));
                break;

            case COMPLETE:

                listUserAnsw.addAll(mappaOut.get(exTipe)
                        .stream()
                        .map(answ -> ((String) answ).split(COMPLETE_QUESTION_REGEX))
                        .collect(Collectors.toList()));

                final List<Optional<?>> lisSplittedAnsw = new ArrayList<>();

                for (final String[] arrAnsw : listUserAnsw) {
                    for (final String splittedAnsw : arrAnsw) {
                        lisSplittedAnsw.add(
                                splittedAnsw.trim().equals("") ? Optional.empty() : Optional.of(splittedAnsw.trim()));
                    }
                }

                listAns.add(lisSplittedAnsw);

                break;

            default:
                LOG.warn("Enter in switch defaul case Possible Error");
                break;
            }

        });

        if (this.controllerInpCheckIn.checkInputView(listUserAnsw, answerErrorPos, this.unit)) {

            this.notifyBack();

            this.checker = new UnitCheckerImpl(this.unit, listAns,
                    this.controllerTimer.timeLeft(),
                    this.controllerLogic.getActualDiff());
            this.controllerLogic.updateStats(this.actualUnit, this.checker);
            this.controllerLogic.saveChanges();

        } else {
            LOG.debug("Wrong input exercice form user");
            this.showPopUp("Complete section error", "Enter the correct number of answers", Duration.LONG,
                    NotificationType.WARNING, null);
        }
        return Collections.unmodifiableList(answerErrorPos);
    }

    private void initAudio(final int selectedIndex) throws URISyntaxException {
        this.controllerSound.changeTrack(this.filemanager.getPathAud(String.valueOf(selectedIndex)));

    }

    private UnitImpl initUnit(final Parser pars) {

        final ExerciseBuilder<String> complBuilder = new StringExerciseBuilder(ExerciseType.COMPLETE);
        final ExerciseBuilder<String> multiBuilder = new StringExerciseBuilder(ExerciseType.MULTI);
        final ExerciseBuilder<Boolean> trufalBuilder = new BooleanExerciseBuilder(ExerciseType.TRUE_FALSE);
        final ExerciseBuilder<Boolean> audBuilder = new BooleanExerciseBuilder(ExerciseType.AUDIO);
        final List<Exercise<?>> listEs = new ArrayList<>();

        this.exerciseSequence.clear();
        pars.resetParserIterator();

        pars.forEachRemaining(exercise -> {
            final ExerciseType ex = exercise.getX();
            this.exerciseSequence.add(ex);
            switch (ex) {
            case AUDIO:
                exercise.getY().forEach(pair -> {
                    audBuilder.addQuestion(pair.getX(),
                            pair.getY()
                                    .stream()
                                    .map(answer -> answer.equals("F") ? false : true)
                                    .collect(Collectors.toList()));
                });
                listEs.add(audBuilder.build());
                break;

            case COMPLETE:

                exercise.getY().forEach(pair -> {
                    complBuilder.addQuestion(pair.getX(), pair.getY());
                });

                listEs.add(complBuilder.build());
                break;

            case MULTI:

                exercise.getY().forEach(pair -> {
                    multiBuilder.addQuestion(pair.getX(), pair.getY());
                });

                listEs.add(multiBuilder.build());
                break;
            case TRUE_FALSE:

                exercise.getY().forEach(pair -> {
                    trufalBuilder.addQuestion(pair.getX(),
                            pair.getY()
                                    .stream()
                                    .map(answer -> answer.equals("F") ? false : true)
                                    .collect(Collectors.toList()));
                });
                listEs.add(trufalBuilder.build());
                break;
            default:
                LOG.warn("Enter in switch defaul case Possible Error");
                break;
            }

        });

        return new UnitImpl(listEs, this.actualUnit, pars.getUnitName());

    }

    @Override
    public void loadPdf(final int level) {

        try {
            final String urlJarResTheory = this.getClass()
                    .getResource(ControllerFolderPaths.STD_DIR_THEORY.getPath()
                            + ControllerFileName.EXERCISE_NAME.getName() + level
                            + ControllerFolderPaths.DEF_JAR_DIR_SEP.getPath()
                            + FileManagerImpl.getEsName(level) + ControllerFileName.THEORY_FILE_EXT.getName())
                    .toURI()
                    .toString();

            Platform.runLater(() -> {

                this.uiTheory.forEach((jfxcontroller) -> {
                    jfxcontroller
                            .showPdf(urlJarResTheory);
                });
            });

        } catch (final URISyntaxException e) {
            LOG.error("Uri Wrong ", e);
        }

    }

    @Override
    public boolean moduleSelected(final int selectedIndex) {

        if (this.controllerLogic.checkUser()) {
            if (this.controllerLogic.getUnitReached(this.controllerLogic.getActualDiff()) >= selectedIndex) {

                final Map<ExerciseType, List<String>> mapViewDom = new HashMap<>();

                LOG.debug("Level: " + selectedIndex + System.getProperty("line.separator") + "Load List");

                try {
                    this.initAudio(selectedIndex);
                    this.pars.inizializeParser(selectedIndex);

                    this.pars.forEachRemaining(exercise -> {
                        mapViewDom.put(exercise.getX(),
                                exercise.getY().stream().map(pair -> pair.getX()).collect(Collectors.toList()));
                    });

                    this.unit = this.initUnit(this.pars);

                    Platform.runLater(() -> {
                        this.uiExercise.forEach((jfxcontroller) -> jfxcontroller
                                .setViewModule(QuestionToView.getQuestionToView(mapViewDom,
                                        this.pars.getMultiExRisp(),
                                        mapViewDom.values().iterator().next().size())));

                    });
                    this.actualUnit = selectedIndex;
                } catch (final FileNotFoundException e) {
                    this.showPopUp("I/O Error", "Error File not found, see log.", Duration.VERY_VERY_LONG,
                            NotificationType.ERROR,
                            null);
                    LOG.error("File csv not found! :", e);
                } catch (final IOException e) {
                    LOG.error("I/O Error! :", e);
                    this.showPopUp("Strange I/O Error", "Some Error occured, see log. ", Duration.VERY_VERY_LONG,
                            NotificationType.ERROR,
                            null);
                } catch (final Exception e) {
                    LOG.error("Strange Error! :", e);
                    this.showPopUp("Strange Error", "Some Error occured, see log.", Duration.VERY_VERY_LONG,
                            NotificationType.ERROR, null);
                }
                if (!this.timerThreadStarted) {
                    this.controllerTimer.startTimer();
                    this.timerThreadStarted = true;
                }
                this.controllerTimer.setTimer(this.controllerLogic.getActualDiff().getSecs());
                this.controllerTimer.resumeTimer();
                return true;
            } else {
                this.showPopUp("Level not reached", "First completes the previous levels", Duration.MEDIUM,
                        NotificationType.INFO, null);
                return false;
            }
        } else {
            this.showPopUp("Error User", "Select one User", Duration.MEDIUM, NotificationType.WARNING, null);
            return false;
        }

    }

    @Override
    public void notifyBack() {
        this.controllerTimer.pauseTimer();
        this.controllerSound.cleanTrack();
        Platform.runLater(() -> {
            this.uiExercise.forEach((jfxcontroller) -> {
                jfxcontroller.resetScreen();
            });
        });
    }

    @Override
    public Boolean pauseAudioExercise() {
        return this.controllerSound.pauseAudioExercise();
    }

    @Override
    public Boolean playAudioExercise() {
        return this.controllerSound.playAudioExercise();
    }

    private void showPopUp(final String title, final String message, final NotificationType.Duration secondsDuration,
            final NotificationType notiType, final EventHandler<NotificationEvent> ev) {
        Platform.runLater(() -> {
            this.uiExercise.forEach((jfxcontroller) -> jfxcontroller.showNotificationPopup(title, message,
                    secondsDuration, notiType, ev));
        });
    }

    @Override
    public Boolean stopAudioExercise() {
        return this.controllerSound.stopAudioExercise();
    }

    @Override
    public void timerChanged(final TimerEventImpl ev) {
        this.uiExercise.forEach((jfxcontroller) -> jfxcontroller.updateTimerView(ev));
        if (ev.getValue() == 0) {
            this.notifyBack();
        }
    }
}
