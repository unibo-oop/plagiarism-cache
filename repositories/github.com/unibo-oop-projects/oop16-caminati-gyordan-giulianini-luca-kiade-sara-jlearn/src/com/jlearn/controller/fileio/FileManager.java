package com.jlearn.controller.fileio;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.jlearn.view.utilities.enums.SoundFX;

/**
 * Interface for reading and saving exercises and Statistics file.
 */
public interface FileManager {

    /**
     *
     * @return the {@link BufferedReader} of the exerciseCSV {@link File}.
     * @param selectedIndex
     *            Es.csv number.
     * @throws FileNotFoundException
     *             {@link FileNotFoundException} Some error in reading/writing {@link File}
     * @throws IOException
     *             {@link IOException} {@link File} not found!
     *
     */
    BufferedReader getCsvExerciseReader(int selectedIndex) throws FileNotFoundException, IOException;

    /**
     * Return the media whit this num this class is not static for the classloader.
     *
     * @param num
     *            the number of Audiofile.
     * @return The jar file path.
     */
    String getPathAud(String num);

    /**
     * Return the media whit this num this class is not static for the classloader.
     *
     * @param path
     *            one instance of the Enum raoppresent the bacground file path.
     * @return The jar file path.
     */
    String getPathAudFx(SoundFX path);

}
