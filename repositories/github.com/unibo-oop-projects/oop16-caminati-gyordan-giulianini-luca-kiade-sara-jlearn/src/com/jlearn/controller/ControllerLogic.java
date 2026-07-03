package com.jlearn.controller;

import com.github.sarxos.webcam.Webcam;
import com.jlearn.model.checker.UnitChecker;
import com.jlearn.model.exercises.Unit;
import com.jlearn.model.users.User;
import com.jlearn.model.utilities.Levels;
import com.jlearn.view.ui.UIMenu;
import com.jlearn.view.ui.UIStatistics;

import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;

/**
 * Interface for {@link UIMenu}, and {@link UIStatistics} Logic.
 *
 */
public interface ControllerLogic {
    /**
     * Attaches the {@link UIMenu} with the observer.
     *
     * @param observedUI
     *            the {@link UIMenu} to change.
     */
    void attacheMenuUI(UIMenu observedUI);

    /**
     * Attaches the {@link UIStatistics} with the observer.
     *
     * @param observedUI
     *            the {@link UIStatistics} to change.
     */
    void attacheStatisticsUI(UIStatistics observedUI);

    /**
     * This method returns if a {@link User} is selected.
     *
     * @return returns <code>true</code> if a {@link User} is selected, returns <code>false</code> otherwise.
     */
    boolean checkUser();

    /**
     * Loads the user's nickname, then updates the view.
     *
     * @param nickname
     *            the {@link String} nickname to choose a {@link User} inside standard dir app.
     *
     * @return <code>true</code> if everything is O.K. and loads the user.
     */
    boolean choosenUser(String nickname);

    /**
     * Deletes the selected {@link User}, and updates controller if everything goes fine.
     *
     * @param string
     *            the {@link String} nickname to delete selected {@link User}.
     * @return <code>true</code> if the player is deleted correctly.
     */
    boolean deletePlayer(String string);

    /**
     * @return the actual selected difficult {@link Levels}.
     */
    Levels getActualDiff();

    /**
     * Returna the last {@link Unit} index reached by actual user.
     *
     * in a {@link Levels}(difficult).
     *
     * @param diff
     *            the {@link Levels} of difficult .
     * @return the index reached by the {@link User}.
     */
    int getUnitReached(Levels diff);

    /**
     *
     * Tries loading all {@link} (image,nickname) and update view.
     *
     * @param path
     *            the {@link User} folder.
     */
    void readAllUser(String path);

    /**
     * Overwrites the old {@link User} with new Stats.
     */
    void saveChanges();

    /**
     * Sets the Modality(difficult).
     *
     * @param selectedIndex
     *            the index. -1 if no selection.
     */
    void setMode(int selectedIndex);

    /**
     * Registers a new {@link User} if the input is right.
     *
     * @param user
     *            the {@link User} to register in app.
     * @return <code>true</code> if is correctly registered.
     */
    boolean signUser(User user);

    /**
     * Updates the {@link LineChart}.
     *
     * @param selectedIndex
     *            the level
     */
    void updateLineChart(int selectedIndex);

    /**
     * Updates the Stats of the Easy Medium Hard mode. Graphic 1.
     */
    void updateModalityStats();

    /**
     * Updates the {@link PieChart}.
     *
     * @param level
     *            the level
     * @param modality
     *            the modality
     */
    void updatePieChart(int level, int modality);

    /**
     *
     * @param check
     *            the {@link UnitChecker}.
     *
     * @param unitID
     *            the {@link Unit} represent unit.
     *
     *
     * 
     */
    void updateStats(int unitID, UnitChecker check);

    /**
     * Gets a picture from {@link Webcam}.
     */
    void webCamPhoto();

}
