package com.jlearn.controller.fileio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collection;

import com.jlearn.model.statistics.UserStatistics;
import com.jlearn.model.users.User;
import com.jlearn.view.ui.UIStatistics;
import com.sun.org.glassfish.external.statistics.Statistic;

/**
 * This part of controller is for read and serialize {@link User} and {@link Statistic}.
 */
public interface ControllerIOOobject {

    /**
     * Only for clean code, Representing a generic IO {@link FileNotFoundException}.
     *
     * @param collection
     *            Patter observer for Update view.
     */
    void fileNotFoundExp(Collection<UIStatistics> collection);

    /**
     * Deserialize {@link UserStatistics} file.
     *
     * @param nickname
     *            For identify the apropryate file.
     * @return {@link UserStatistics} deserialized.
     */
    UserStatistics readStatsFile(String nickname);

    /**
     * Give the BufferedReader of the User file.
     *
     *
     * @param nickname
     *            the name of the user to read.
     * @return {@link User}, return <code>null</code> if IO error occur.
     */
    User readUserFile(String nickname);

    /**
     * Only for clean code, Representing a generic IO {@link IOException}.
     *
     * @param collection
     *            Patter observer for Update view.
     */
    void strangeExp(Collection<UIStatistics> collection);

    /**
     * Only for clean code, Representing a generic IO {@link Exception}.
     *
     * @param collection
     *            Patter observer for Update view.
     */
    void strangeIOExp(Collection<UIStatistics> collection);

    /**
     * Serialize the {@link UserStatistics} given. Inside standard JLearn folder.
     *
     * @param stats
     *            the stats to serialize.
     * @param nickname
     *            The nickname of {@link User}.
     */
    void writeStatsFile(UserStatistics stats, String nickname);

    /**
     * Serialize the {@link User} given.
     *
     * @param user
     *            the user.
     */
    void writeUserFile(User user);

}