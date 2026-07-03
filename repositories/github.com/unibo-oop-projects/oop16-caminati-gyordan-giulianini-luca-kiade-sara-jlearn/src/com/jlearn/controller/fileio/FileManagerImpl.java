package com.jlearn.controller.fileio;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import javax.imageio.ImageIO;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.github.sarxos.webcam.Webcam;
import com.jlearn.model.exercises.Unit;
import com.jlearn.model.users.User;
import com.jlearn.view.utilities.enums.SoundFX;

import javafx.scene.image.Image;

/**
 * Implement ControllerfileIOImpl interface whit same function.
 */
public final class FileManagerImpl implements FileManager {

    private static FileManager fileManagerObj;

    private static final Logger LOG = Logger.getLogger(FileManagerImpl.class);

    private FileManagerImpl() {
        LOG.setLevel(Level.WARN);
    }

    /**
     * Try to cancel directory whit all inside file.
     *
     * @param dir
     *            the {@link File} dir want deleate.
     * @throws IOException
     *             if fails.
     */
    public static void cancelDirectory(final File dir) throws IOException {

        final File[] fileInsideDir = dir.listFiles();
        if (fileInsideDir != null) {
            for (final File file : fileInsideDir) {
                if (!file.delete()) {
                    throw new IOException();
                }
            }
        }
        if (!dir.delete()) {
            throw new IOException();
        }
    }

    /**
     * Prepare The standard dir JLearn in user.
     */
    public static void constructDirApp() {

        try {

            try {
                Files.createDirectory(getDefPath());
            } catch (final FileAlreadyExistsException e) {
                fileExist(e);
            }

            try {
                Files.createDirectory(getDefPathUser());
            } catch (final FileAlreadyExistsException e) {
                fileExist(e);
            }

        } catch (final IOException e) {
            LOG.error("Strange error occur! : ", e);
        }
    }

    /**
     *
     * @param index
     *            The index of exercise.
     * @return null if some error occurred like {@link IOException}.
     *
     */
    public static String getEsName(final int index) {
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        FileManagerImpl.class.getResourceAsStream(ControllerFolderPaths.STD_DIR_ES.getPath()
                                + ControllerFileName.UNIT_NAME_F.getName()
                                + ControllerFileName.UNIT_NAME_F_EXTENSION.getName()),
                        "UTF-8"))) {
            return reader.lines().collect(Collectors.toList()).get(index);
        } catch (final IOException e) {
            LOG.error("Error to acces in jar : ", e);
        }
        return null;
    }

    /**
     * Create.
     *
     * @throws IOException
     *             if fails.
     */
    public static void createStats() throws IOException {
        try {
            Files.createFile(Paths.get(getDefPath()
                    .toString(),
                    ControllerFileName.USER_F.getName() + ControllerFileName.SERIALIZED_FILE_EXTENSION.getName()));
        } catch (final FileAlreadyExistsException e) {
            fileExist(e);
        }
    }

    /**
     * Create new {@link User} folder whit this nickname, inside JLearn def directory.
     *
     * @param nickname
     *            the name of the {@link User} and folder.
     * @throws IOException
     *             if fails.
     */
    public static void createUserFolder(final String nickname) throws IOException {
        Files.createDirectory(Paths.get(getDefPathUser()
                .toString(), nickname));
    }

    private static void fileExist(final FileAlreadyExistsException e) {
        LOG.info(e.getMessage() + "Alredy exists! ");
    }

    private static ObjectInputStream genImputStream(final String path) throws FileNotFoundException, IOException {
        return new ObjectInputStream(new BufferedInputStream(new FileInputStream(path)));

    }

    private static ObjectOutputStream genOutputStream(final String path) throws FileNotFoundException, IOException {
        return new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(path)));

    }

    /**
     * Get all {@link Unit} name.
     *
     * @return return {@link List} contains all {@link Unit} name.
     */
    public static List<String> getAllUnitName() {
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        FileManagerImpl.class.getResourceAsStream(ControllerFolderPaths.STD_DIR_ES.getPath()
                                + ControllerFileName.UNIT_NAME_F.getName()
                                + ControllerFileName.UNIT_NAME_F_EXTENSION.getName()),
                        "UTF-8"))) {
            return reader.lines().collect(Collectors.toList());
        } catch (final IOException e) {
            LOG.error("Error to acces in jar : ", e);
        }
        return null;
    }

    private static java.nio.file.Path getDefPath() {
        return Paths.get(ControllerFolderPaths.HOME.getPath(), ControllerFolderPaths.STD_NAME_DIR_UT.getPath());
    }

    /**
     *
     * @return the default jar directory image.
     */
    public static String getDefPathImag() {
        return ControllerFolderPaths.STD_DIR_DEF_IMG.getPath();
    }

    /**
     *
     * @return The default path of User inside JLearn directory.
     */
    public static java.nio.file.Path getDefPathUser() {
        return Paths.get(getDefPath()
                .toString(), ControllerFolderPaths.DEF_USER_DIR_NAME.getPath());
    }

    /**
     *
     * @return The only instance of {@link FileManagerImpl}.
     */

    public static FileManager getInstance() {

        synchronized (FileManagerImpl.class) {
            if (fileManagerObj == null) {

                fileManagerObj = new FileManagerImpl();
            }
        }
        return fileManagerObj;
    }

    private static String getPathStats(final String nickname) {
        return getDefPathUser() + ControllerFolderPaths.PATH_SEP.getPath() + nickname
                + ControllerFolderPaths.PATH_SEP.getPath()
                + ControllerFileName.STATS_F.getName() + ControllerFileName.SERIALIZED_FILE_EXTENSION.getName();
    }

    private static String getPathUser(final String nickname) {
        return getDefPathUser() + ControllerFolderPaths.PATH_SEP.getPath() + nickname
                + ControllerFolderPaths.PATH_SEP.getPath()
                + ControllerFileName.USER_F.getName() + ControllerFileName.SERIALIZED_FILE_EXTENSION.getName();
    }

    /**
     *
     * @param nickname
     *            of the {@link User} holder stats.
     * @return new {@link ObjectInputStream}.
     *
     * @throws FileNotFoundException
     *             if don't found the specified {@link File}.
     * @throws IOException
     *             if fails.
     */
    public static ObjectInputStream getStatsInpObj(final String nickname) throws FileNotFoundException, IOException {
        return genImputStream(getPathStats(nickname));
    }

    /**
     *
     * @param nickname
     *            of the {@link User} holder stats.
     * @return new {@link ObjectOutputStream}.
     *
     * @throws FileNotFoundException
     *             if don't found the specified {@link File}.
     * @throws IOException
     *             if fails.
     */
    public static ObjectOutputStream getStatsOutObj(final String nickname) throws FileNotFoundException, IOException {
        return genOutputStream(getPathStats(nickname));

    }

    /**
     *
     * @param nickname
     *            of the {@link User} holder stats.
     * @return new {@link ObjectInputStream}.
     *
     * @throws FileNotFoundException
     *             if don't found the specified {@link File}.
     * @throws IOException
     *             if fails.
     */
    public static ObjectInputStream getUserInpObj(final String nickname) throws FileNotFoundException, IOException {
        return genImputStream(getPathUser(nickname));
    }

    /**
     *
     * @param nickname
     *            of the {@link User} holder stats.
     * @return new {@link ObjectOutputStream}.
     *
     * @throws FileNotFoundException
     *             if don't found the specified {@link File}.
     * @throws IOException
     *             if fails.
     */
    public static ObjectOutputStream getUserOutObj(final String nickname) throws FileNotFoundException, IOException {
        return genOutputStream(getPathUser(nickname));
    }

    /**
     *
     * @param nickname
     *            of the {@link User} owner of image.
     * @return new {@link Image} selected.
     * @throws FileNotFoundException
     *             if don't found image.
     * @throws IOException
     *             if fails.
     */
    public static Image readImage(final String nickname) throws FileNotFoundException, IOException {
        try (InputStream inps = new FileInputStream(
                new File(getDefPathUser() + ControllerFolderPaths.PATH_SEP.getPath()
                        + nickname + ControllerFolderPaths.PATH_SEP.getPath()
                        + ControllerFileName.SAVE_IMG_NAME.getName()
                        + ControllerFileName.IMAGE_EXTENSION.getName()))) {
            return new Image(inps);
        }
    }

    /**
     *
     * @param nickName
     *            of the {@link User} owner of image.
     * @return true if the operation going fine.
     */
    public static Boolean saveImagefromWebcam(final String nickName) {
        Boolean result;
        final Webcam webcam = Webcam.getDefault();
        try {

            webcam.open();

            result = ImageIO.write(webcam.getImage(), ControllerFileName.IMAGE_FORMAT.getName(),
                    new File(getDefPathUser() + ControllerFolderPaths.PATH_SEP.getPath() + nickName
                            + ControllerFolderPaths.PATH_SEP.getPath() + ControllerFileName.SAVE_IMG_NAME.getName()
                            + ControllerFileName.IMAGE_EXTENSION.getName()));
        } catch (final Exception e) {
            result = false;
        } finally {
            webcam.close();
        }
        return result;
    }

    @Override
    public BufferedReader getCsvExerciseReader(final int num) throws FileNotFoundException, IOException {
        return new BufferedReader(new InputStreamReader(this.getClass()
                .getResourceAsStream(
                        ControllerFolderPaths.STD_DIR_ES.getPath() + ControllerFileName.EXERCISE_NAME.getName() + num
                                + ControllerFileName.EXERCISE_EXT.getName()),
                "UTF-8"));
    }

    @Override
    public String getPathAud(final String num) {
        return ControllerFolderPaths.STD_DIR_ES_AUD.getPath() + ControllerFileName.EXERCISE_NAME.getName() + num
                + ControllerFileName.AUD_EXT.getName();
    }

    @Override
    public String getPathAudFx(final SoundFX path) {
        return ControllerFolderPaths.STD_DIR_AUD_FX.getPath() + path.getPath();
    }

}
