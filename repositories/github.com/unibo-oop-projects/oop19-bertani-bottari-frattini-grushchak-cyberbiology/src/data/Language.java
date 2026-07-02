package data;

import java.util.ResourceBundle;

import file.data.input.FileGetLanguage;
import file.data.input.FileGetLanguageImpl;
import file.data.input.FileSet;
import file.data.input.FileSetImpl;

/**
 * Class that handles the setting of the chosen bundle and the return of a 
 * string related to the set bundle key.
 *
 */
public class Language implements LanguageSet {

    private static ResourceBundle currentbundle = null;
    private static Languages currentLanguage = null;

    @Override
    public final void setcurrentbundle(final int value) {
        FileSet f = new FileSetImpl();
        switch (Languages.getEnum(value)) {
            case ITALIAN: f.addtoFile(Languages.ITALIAN); break;
            case ENGLISH: f.addtoFile(Languages.ENGLISH); break;
            case ESPANOL: f.addtoFile(Languages.ESPANOL); break;
            default: throw new IllegalArgumentException("LINGUA NON SELEZIONATA NON ESISTE");
        }
    }

    public static final String getkeyofbundle(final String key) {
        if (currentbundle == null) {
            setLocal();
        }
        return currentbundle.getString(key);
    }

    private static void setLocal() {
        FileGetLanguage f = new FileGetLanguageImpl();
        setLocale(f.getLanguage());
    }

    public static void setLocale(final Languages l) {
        currentLanguage = l;
        currentbundle = ResourceBundle.getBundle("resource.i18n" + java.io.File.separator + "Bundle", l.getLocale());
   }

    public static final Languages getCurrentLanguage() {
        if (currentLanguage == null) {
            throw new IllegalStateException();
        }
        return currentLanguage;
    }

}

