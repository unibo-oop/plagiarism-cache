package com.jlearn.controller.fileio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collection;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.jlearn.controller.ControllerExercise;
import com.jlearn.model.statistics.UserStatistics;
import com.jlearn.model.users.User;
import com.jlearn.view.ui.UIStatistics;
import com.jlearn.view.utilities.enums.NotificationType;
import com.jlearn.view.utilities.enums.NotificationType.Duration;

import javafx.application.Platform;

/**
 * One of the Possible Implementation of {@link ControllerIOOobject}.
 */
public final class ControllerIOObjectImpl implements ControllerIOOobject {
    private final Collection<UIStatistics> collUi;

    private static ControllerIOOobject controllerIO;
    private static final Logger LOG = Logger.getLogger(ControllerIOObjectImpl.class);

    /**
     * @return The only instance of {@link ControllerExercise}
     * @param collection
     *            The pattern observer for Update {@link UIStatistics}.
     */
    public static ControllerIOOobject getInstance(final Collection<UIStatistics> collection) {

        synchronized (ControllerIOObjectImpl.class) {
            if (controllerIO == null) {

                controllerIO = new ControllerIOObjectImpl(collection);
            }
        }
        return controllerIO;
    }

    private ControllerIOObjectImpl(final Collection<UIStatistics> collection) {
        LOG.setLevel(Level.WARN);
        this.collUi = collection;
    }

    @Override
    public void fileNotFoundExp(final Collection<UIStatistics> collection) {
        Platform.runLater(() -> {
            this.collUi.forEach((jfxcontroller) -> jfxcontroller.showNotificationPopup("I/O Error see log",
                    "Error File not found", Duration.VERY_VERY_LONG, NotificationType.ERROR,
                    null));
        });
    }

    @Override
    public UserStatistics readStatsFile(final String nickname) {

        try (ObjectInputStream obj = FileManagerImpl.getStatsInpObj(nickname)) {
            return (UserStatistics) obj.readObject();
        } catch (final FileNotFoundException e) {
            this.fileNotFoundExp(this.collUi);
            LOG.error("File not found Error!", e);
        } catch (final ClassNotFoundException e) {
            this.strangeExp(this.collUi);
            LOG.error("Class not found Error!", e);
        } catch (final IOException e) {
            this.strangeIOExp(this.collUi);
            LOG.error("File not Found!", e);
        }
        return null;
    }

    @Override
    public User readUserFile(final String nickname) {
        try (ObjectInputStream obj = FileManagerImpl.getUserInpObj(nickname)) {
            return (User) obj.readObject();
        } catch (final FileNotFoundException e) {
            this.fileNotFoundExp(this.collUi);
            LOG.error("File not found Error!", e);
        } catch (final ClassNotFoundException e) {
            this.strangeExp(this.collUi);
            LOG.error("Class not found Error!", e);
        } catch (final IOException e) {
            this.strangeIOExp(this.collUi);
            LOG.error("File not Found!", e);
        }
        return null;
    }

    @Override
    public void strangeExp(final Collection<UIStatistics> collection) {
        Platform.runLater(() -> {
            this.collUi.forEach((jfxcontroller) -> jfxcontroller.showNotificationPopup("I/O Error, see log",
                    "Some I/O Error occured :", Duration.VERY_VERY_LONG,
                    NotificationType.ERROR, null));
        });
    }

    @Override
    public void strangeIOExp(final Collection<UIStatistics> collection) {
        Platform.runLater(() -> {
            this.collUi.forEach((jfxcontroller) -> jfxcontroller.showNotificationPopup("Error, see log.",
                    "Some Error occured :", Duration.VERY_VERY_LONG, NotificationType.ERROR,
                    null));
        });
    }

    @Override
    public void writeStatsFile(final UserStatistics stats, final String nickname) {
        try (ObjectOutputStream obj = FileManagerImpl.getStatsOutObj(nickname)) {
            obj.writeObject(stats);
        } catch (final IOException e) {
            this.strangeIOExp(this.collUi);
            LOG.error("Error I/O serialize Stats ", e);
        }
    }

    @Override
    public void writeUserFile(final User user) {
        try (ObjectOutputStream obj = FileManagerImpl.getUserOutObj(user.getNickname())) {
            obj.writeObject(user);
        } catch (final IOException e) {
            this.strangeIOExp(this.collUi);
            LOG.error("Error I/O serialize User ", e);
        }
    }

}
