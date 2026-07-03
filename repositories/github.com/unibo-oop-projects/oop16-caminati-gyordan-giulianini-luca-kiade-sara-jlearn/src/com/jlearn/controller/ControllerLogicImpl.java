package com.jlearn.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.github.sarxos.webcam.Webcam;
import com.jlearn.controller.checker.ControllerInputCheck;
import com.jlearn.controller.checker.ControllerInputCheckImpl;
import com.jlearn.controller.fileio.ControllerFileName;
import com.jlearn.controller.fileio.ControllerFolderPaths;
import com.jlearn.controller.fileio.ControllerIOObjectImpl;
import com.jlearn.controller.fileio.ControllerIOOobject;
import com.jlearn.controller.fileio.FileManagerImpl;
import com.jlearn.model.checker.CheckLog;
import com.jlearn.model.checker.Checker.Result;
import com.jlearn.model.checker.UnitChecker;
import com.jlearn.model.statistics.Statistics;
import com.jlearn.model.statistics.StatisticsImpl;
import com.jlearn.model.statistics.UserStatistics;
import com.jlearn.model.statistics.UserStatisticsImpl;
import com.jlearn.model.users.User;
import com.jlearn.model.users.UserImpl;
import com.jlearn.model.utilities.Levels;
import com.jlearn.model.utilities.Pair;
import com.jlearn.view.ui.UIMenu;
import com.jlearn.view.ui.UIStatistics;
import com.jlearn.view.utilities.enums.NotificationType;
import com.jlearn.view.utilities.enums.NotificationType.Duration;

import eu.hansolo.enzo.notification.NotificationEvent;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.image.Image;

/**
 * Class that manages all the logic of the application.
 */
public final class ControllerLogicImpl implements ControllerLogic {
    private Levels                     actualDiff;
    private UserStatistics             actualStats;
    private final ControllerIOOobject controllerIO;
    private final Set<UIStatistics>    uisStatistic;
    private final ControllerInputCheck controllerInpCheckIn;
    private final Set<UIMenu>          uisMenu;
    private final User                 defaultUser = new UserImpl("NO", "USER", "", 0, "", "");
    private Image                      im;
    private User                       actualUser;
    private static final Logger        LOG         = Logger.getLogger(ControllerLogicImpl.class);

    private static ControllerLogic controllerLogicObj;

    /**
     * @return The only instance of {@link ControllerLogicImpl}
     */
    public static ControllerLogic getInstance() {

        synchronized (ControllerLogicImpl.class) {
            if (controllerLogicObj == null) {

                controllerLogicObj = new ControllerLogicImpl();
            }
        }
        return controllerLogicObj;
    }

    /**
     * The constructor.
     */
    private ControllerLogicImpl() {

        this.controllerInpCheckIn = ControllerInputCheckImpl.getInstance();
        LOG.setLevel(Level.WARN);
        FileManagerImpl.constructDirApp();
        this.actualUser = null;
        this.actualDiff = Levels.EASY;
        this.uisStatistic = new HashSet<>();
        this.uisMenu = new HashSet<>();
        this.controllerIO = ControllerIOObjectImpl.getInstance(this.uisStatistic);
    }

    @Override
    public void attacheMenuUI(final UIMenu observedUI) {
        this.uisMenu.add(observedUI);
        this.readAllUser(FileManagerImpl.getDefPathUser()
                .toString());
    }

    @Override
    public void attacheStatisticsUI(final UIStatistics observedUI) {
        this.uisStatistic.add(observedUI);
    }

    private void changeListUser(final String nickname, final Image im) {
        Platform.runLater(() -> {
            this.uisMenu.forEach((jfxcontroller) -> jfxcontroller.addPlayerListView(nickname, im));
        });

    }

    private void changeViewImage(final Image img) {
        Platform.runLater(() -> {
            this.uisStatistic.forEach((jfxcontroller) -> jfxcontroller.setProfileImageView(img));
            this.uisMenu.forEach((jfxcontroller) -> jfxcontroller.setProfileImageView(img));
        });
    }

    @Override
    public boolean checkUser() {
        return this.actualUser != null;
    }

    @Override
    public boolean choosenUser(final String nickname) {

        LOG.debug("Utente selezionato : " + nickname);
        final User user = this.controllerIO.readUserFile(nickname);
        this.actualStats = this.controllerIO.readStatsFile(nickname);
        this.actualUser = user;

        if (new File(FileManagerImpl.getDefPathUser() + ControllerFolderPaths.PATH_SEP.getPath() + nickname
                + ControllerFolderPaths.PATH_SEP.getPath()
                + ControllerFileName.SAVE_IMG_NAME.getName()
                + ControllerFileName.IMAGE_EXTENSION.getName()).exists()) {
            try {
                this.im = FileManagerImpl.readImage(nickname);

            } catch (final IOException e) {
                this.controllerIO.strangeIOExp(this.uisStatistic);
                LOG.error("Error load User Image!", e);
                return false;
            }
        } else {
            this.showPopUp("User change", "User changed correctly", Duration.MEDIUM, NotificationType.SUCCESS, null);
            this.im = new Image(FileManagerImpl.getDefPathImag() + ControllerFileName.SAVE_IMG_NAME.getName()
                    + ControllerFileName.IMAGE_EXTENSION.getName());
        }

        this.changeViewImage(this.im);
        this.upViewPersonStats(user, nickname);

        Platform.runLater(() -> this.uisStatistic
                .forEach(
                        (jfxcontroller) -> jfxcontroller.setAchivements((this.actualUser.getReachedUnitID(Levels.EASY)),
                                (this.actualUser.getReachedUnitID(Levels.MEDIUM)),
                                (this.actualUser.getReachedUnitID(Levels.HARD)))));
        return true;
    }

    private void clearListPlayer() {
        Platform.runLater(() -> {
            this.uisMenu.forEach((jfxcontroller) -> jfxcontroller.clearListOfPlayers());
        });

    }

    @Override
    public boolean deletePlayer(final String nickname) {
        try {
            FileManagerImpl.cancelDirectory(new File(FileManagerImpl.getDefPathUser()
                    .toString() + ControllerFolderPaths.PATH_SEP.getPath() + nickname));
            LOG.info("User " + nickname + "deleate");
            this.showPopUp("", "User Delete", Duration.MEDIUM, NotificationType.SUCCESS, null);
        } catch (final IOException e) {
            LOG.error("Can't deleate User", e);
            this.controllerIO.strangeIOExp(this.uisStatistic);
            return false;
        }

        this.clearListPlayer();
        this.readAllUser(FileManagerImpl.getDefPathUser()
                .toString() + ControllerFolderPaths.PATH_SEP.getPath());
        this.changeViewImage(new Image(FileManagerImpl.getDefPathImag() + ControllerFileName.DEFAULT_IMG_NAME.getName()
                + ControllerFileName.IMAGE_EXTENSION.getName()));
        this.upViewPersonStats(this.defaultUser, "");
        this.actualUser = null;
        this.im = null;
        return true;
    }

    @Override
    public Levels getActualDiff() {
        return Levels.valueOf(this.actualDiff.name());
    }

    @Override
    public int getUnitReached(final Levels diff) {
        if (this.checkUser()) {
            return this.actualUser.getReachedUnitID(diff);
        } else {
            return 0;
        }

    }

    @Override
    public void readAllUser(final String path) {
        final File[] folders = new File(path).listFiles(File::isDirectory);
        if (folders != null) {
            for (final File element : folders) {
                this.uisMenu.forEach((jfxcontroller) -> {
                    try {
                        if (new File(
                                element.getAbsolutePath() + ControllerFolderPaths.PATH_SEP.getPath()
                                        + ControllerFileName.SAVE_IMG_NAME.getName()
                                        + ControllerFileName.IMAGE_EXTENSION.getName())
                                                .exists()) {
                            this.changeListUser(element.getName(), FileManagerImpl.readImage(element.getName()));

                        } else {
                            this.changeListUser(element.getName(),
                                    new Image(FileManagerImpl.getDefPathImag()
                                            + ControllerFileName.SAVE_IMG_NAME.getName()
                                            + ControllerFileName.IMAGE_EXTENSION.getName()));
                        }

                    } catch (final FileNotFoundException e) {
                        this.controllerIO.fileNotFoundExp(this.uisStatistic);
                        LOG.error("User Image not found", e);
                    } catch (final IOException e) {
                        this.controllerIO.strangeIOExp(this.uisStatistic);
                        LOG.error("Error Reading User!", e);
                    }
                });
            }
        } else {
            LOG.error("Warning : no directory found in :" + path);
        }
    }

    @Override
    public void saveChanges() {
        this.controllerIO.writeUserFile(this.actualUser);
        this.controllerIO.writeStatsFile(this.actualStats, this.actualUser.getNickname());
    }

    @Override
    public void setMode(final int selectedIndex) {
        if (this.checkUser()) {
            this.actualDiff = Levels.values()[selectedIndex];

        } else {
            this.showSelectUser();
        }
    }

    private void showPopUp(final String title, final String message, final NotificationType.Duration secondsDuration,
            final NotificationType notiType, final EventHandler<NotificationEvent> ev) {
        Platform.runLater(() -> {
            this.uisStatistic.forEach((jfxcontroller) -> jfxcontroller.showNotificationPopup(title, message,
                    secondsDuration, notiType, ev));
        });

    }

    private void showSelectUser() {
        this.showPopUp("Info", "select one User", Duration.MEDIUM, NotificationType.INFO, null);
    }

    @Override
    public boolean signUser(final User user) {

        if (this.controllerInpCheckIn.checkUserInput(user, this.uisStatistic)) {

            try {
                FileManagerImpl.createUserFolder(user.getNickname());
                this.actualUser = user;

                this.controllerIO.writeUserFile(user);

                this.controllerIO.writeStatsFile(
                        new UserStatisticsImpl(this.actualUser.getNickname(), new ArrayList<Statistics>()),
                        this.actualUser.getNickname());
                this.actualStats = this.controllerIO.readStatsFile(this.actualUser.getNickname());
                this.upViewPersonStats(user, this.actualUser.getNickname());

                this.changeListUser(this.actualUser.getNickname(),
                        new Image(FileManagerImpl.getDefPathImag() + ControllerFileName.SAVE_IMG_NAME.getName()
                                + ControllerFileName.IMAGE_EXTENSION.getName()));
                this.changeViewImage(
                        new Image(FileManagerImpl.getDefPathImag() + ControllerFileName.SAVE_IMG_NAME.getName()
                                + ControllerFileName.IMAGE_EXTENSION.getName()));
                this.showPopUp("Accept", "User registered", Duration.MEDIUM, NotificationType.SUCCESS, null);
                LOG.info("User correct singin.");

            } catch (final FileAlreadyExistsException e) {
                this.showPopUp("Error", "NickName alredy used change it!", Duration.MEDIUM, NotificationType.WARNING,
                        null);
                return false;
            } catch (final IOException e) {
                this.controllerIO.strangeIOExp(this.uisStatistic);
                LOG.error("I/O exeption", e);
                return false;
            }
        } else {
            return false;
        }
        return true;
    }

    @Override
    public void updateLineChart(final int level) {

        if ((level == -1)) {
            return;
        }

        if (this.checkUser()) {
            final List<Pair<String, Integer>> temp = new ArrayList<>();

            if ((this.actualUser.getReachedUnitID(Levels.EASY) > level)
                    || (this.actualUser.getReachedUnitID(Levels.MEDIUM) > level)
                    || (this.actualUser.getReachedUnitID(Levels.HARD) > level)) {

                this.actualStats.getLevelsTopScore(level).forEach((diff, score) -> {
                    temp.add(new Pair<>(diff.getName(), score));
                });
                Platform.runLater(() -> this.uisStatistic
                        .forEach((jfxcontroller) -> jfxcontroller.updateLineChart(temp,
                                FileManagerImpl.getAllUnitName().get(level))));
            } else {
                LOG.warn("update linechart Wrong input LELEL EMPTY:");
                this.showPopUp("", "Never Play this level", Duration.MEDIUM, NotificationType.INFO, null);
            }

        } else {
            this.showSelectUser();
        }

    }

    @Override
    public void updateModalityStats() {

        if (this.checkUser()) {

            final List<Pair<String, List<Pair<String, Integer>>>> series = new ArrayList<>();

            for (final Levels diff : Levels.values()) {

                final List<Pair<String, Integer>> temp = new ArrayList<>();
                this.actualStats.getUnitsTopScore(diff).forEach((unitID, score) -> {
                    temp.add(new Pair<>(FileManagerImpl.getAllUnitName().get(unitID), score));
                });
                series.add(new Pair<>(diff.getName(), temp));
            }

            Platform.runLater(
                    () -> this.uisStatistic.forEach((jfxcontroller) -> jfxcontroller.updateBarChartModality(series)));

        } else {
            this.showSelectUser();
        }

    }

    @Override

    public void updatePieChart(final int level, final int modality) {
        if ((level == -1) || (modality == -1)) {
            return;
        }

        if (this.checkUser()) {
            if (this.actualUser.getReachedUnitID(Levels.values()[modality]) > level) {
                final List<Pair<String, Integer>> temp = new ArrayList<>();

                this.actualStats.getUnitRates(level, Levels.values()[modality]).forEach((type, score) -> {
                    temp.add(new Pair<>(type.getName() + " :" + score, score));
                });

                Platform.runLater(
                        () -> this.uisStatistic.forEach((jfxcontroller) -> jfxcontroller.updatePieChart(temp)));

            } else {
                LOG.warn("update piechart Wrong input LELEL EMPTY:");
                this.showPopUp("", "Never Play this level", Duration.MEDIUM, NotificationType.INFO, null);
            }

        } else {
            this.showSelectUser();
        }

    }

    @Override
    public void updateStats(final int unitID, final UnitChecker check) {

        if ((this.actualUser.getReachedUnitID(Levels.EASY) <= unitID)
                && (this.actualUser.getReachedUnitID(Levels.MEDIUM) <= unitID)
                && (this.actualUser.getReachedUnitID(Levels.HARD) <= unitID)) {
            final Map<Levels, Integer> map = new HashMap<>();
            final Map<Levels, List<CheckLog>> map1 = new HashMap<>();
            final List<Levels> lis = new ArrayList<>(Arrays.asList(Levels.values()));
            lis.remove(lis.indexOf(this.actualDiff));
            lis.forEach(remainingDiff -> {
                map.put(remainingDiff, 0);
                map1.put(this.actualDiff, new ArrayList<CheckLog>());
            });
            this.actualUser.incrementReachedUnit(this.actualDiff);
            map.put(this.actualDiff, check.getUnitTotalScore());

            map1.put(this.actualDiff, check.getUnitCheckLogs());
            this.actualStats.addStatistic(new StatisticsImpl(unitID, map, map1));

        } else {
            if ((this.actualUser.getReachedUnitID(this.actualDiff) <= unitID)) {
                this.actualUser.incrementReachedUnit(this.actualDiff);
            }

            this.actualStats.registerNewScore(unitID, this.actualDiff, check.getUnitTotalScore());
            this.actualStats.registerNewUnitCheckLogs(unitID, this.actualDiff, check.getUnitCheckLogs());
        }

        this.showPopUp("Your Score", "Score:" + check.getUnitTotalScore()
                + System.lineSeparator() + "Correct:" + check.totalAnswersByResult(Result.RIGHT_ANSWER)
                + " Null:" + check.totalAnswersByResult(Result.NULL_ANSWER)
                + " Wrong:" + check.totalAnswersByResult(Result.WRONG_ANSWER), Duration.VERY_LONG,
                NotificationType.SUCCESS, null);
        Platform.runLater(() -> this.uisStatistic
                .forEach(
                        (jfxcontroller) -> jfxcontroller.setAchivements((this.actualUser.getReachedUnitID(Levels.EASY)),
                                (this.actualUser.getReachedUnitID(Levels.MEDIUM)),
                                (this.actualUser.getReachedUnitID(Levels.HARD)))));
    }

    private void upViewPersonStats(final User user, final String nickname) {
        Platform.runLater(() -> {
            this.uisStatistic.forEach((jfxcontroller) -> jfxcontroller.setPersonStatistics(user));
            this.uisMenu.forEach((jfxcontroller) -> jfxcontroller.setProfileLoginNickname(nickname));
        });
    }

    @Override
    public synchronized void webCamPhoto() {

        if (this.actualUser == null) {
            this.showSelectUser();
        } else {
            new Thread(() -> {

                if (Webcam.getDefault() != null) {

                    Platform.runLater(() -> {
                        this.uisMenu
                                .forEach((jfxcontroller) -> jfxcontroller
                                        .setProfileImageView(new Image((ControllerFolderPaths.STD_DIR_DEF_IMG.getPath()
                                                + ControllerFileName.LOADING_GIF_NAME.getName())
                                                + ControllerFileName.GIF_FORMAT.getName())));
                    });
                    if (FileManagerImpl.saveImagefromWebcam(this.actualUser.getNickname())) {

                        try {
                            this.im = FileManagerImpl.readImage(this.actualUser.getNickname());
                        } catch (final FileNotFoundException e) {
                            this.controllerIO.fileNotFoundExp(this.uisStatistic);
                            LOG.error("Image not found", e);

                        } catch (final IOException e) {
                            this.controllerIO.strangeIOExp(this.uisStatistic);
                            LOG.error("I/O exeption image reading", e);
                        }
                        this.changeViewImage(this.im);

                        this.clearListPlayer();
                        this.readAllUser(FileManagerImpl.getDefPathUser()
                                .toString() + ControllerFolderPaths.PATH_SEP.getPath());

                        this.showPopUp("", "Photo taken correctly", Duration.MEDIUM, NotificationType.SUCCESS, null);
                    } else {

                        this.showPopUp("Webcam error", "Error whit webcam see log.", Duration.MEDIUM,
                                NotificationType.ERROR, null);
                        LOG.error("Webcam Error, webcam not found or writer not found.:");
                    }
                } else {
                    this.showPopUp("Webcam error", "Webcam Not found!", Duration.MEDIUM,
                            NotificationType.ERROR, null);
                }
            }).start();

        }

    }

}