package data;

/**
 * Interface that manages the setting of the translation of the program in the chosen language.
 *
 */
public interface LanguageSet {

    /**
     * Sets the bundle of the language chosen by the user as the translation language of the program.
     * @param value value for the language you want to set 
     * @throws IllegalArgumentException if the value does not correspond to any language enum Languages
    */
    void setcurrentbundle(int value);

}
