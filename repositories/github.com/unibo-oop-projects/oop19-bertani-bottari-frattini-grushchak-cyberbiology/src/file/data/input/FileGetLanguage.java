package file.data.input;

import data.Languages;

/**
 * Interface that will be implemented classes that load from file the data inherent to the parameters set by the user.
 * 
 */
public interface FileGetLanguage {

    /**
     * Upload from file and return a Languages enum corresponding to that of the chosen language.
     * @return an enumeration of the language chosen to translate the program
     * @exception FileNotFoundException
     * @exception IOException
     */
    Languages getLanguage();

}
