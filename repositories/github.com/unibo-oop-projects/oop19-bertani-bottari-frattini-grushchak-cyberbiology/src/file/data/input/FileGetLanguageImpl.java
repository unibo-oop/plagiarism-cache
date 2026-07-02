package file.data.input;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;

import data.Languages;

/**
 * Classes that manage the loading of the language set by the user from specific files.
 * 
 */
public class FileGetLanguageImpl implements FileGetLanguage {
    private static final URL FILE_LANGUAGE_URL = ClassLoader.getSystemResource("data/data.language.txt");
    private Languages language;

    @Override
    public final Languages getLanguage() {
        try (DataInputStream dstream = new DataInputStream(
                new BufferedInputStream(
                        new FileInputStream(new File(FILE_LANGUAGE_URL.getFile()))));) {
            language = Languages.getEnum(dstream.readInt()); 
            new FileSetImpl().addtoFile(language);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return language;
    }
}
