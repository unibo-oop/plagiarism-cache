package controller.file;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import controller.CircuitPlayable;

/**
 * A possible implementation of F1Files interface.
 */
public class F1FilesImpl implements F1Files {

    private static final String SLASH = "/" /*System.getProperty("file.separator")*/;
    private static final String PATH = SLASH + "circuits" + SLASH;
    private static final String MODEL_FILE = SLASH + "Track.txt";
    private static final String VIEW_FILE = ".txt";
    private static final String VIEW_CIRCUIT = SLASH + "circuit.jpg";

    @Override
    public BufferedReader getFileForModel(final CircuitPlayable cir) {
        final InputStream is = this.getClass().getResourceAsStream(PATH + cir.getCity() + MODEL_FILE);
        try {
            return new BufferedReader(new InputStreamReader(is, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            throw new IllegalStateException();
        }
    }

    @Override
    public ViewFile getFileForView(final CircuitPlayable cir) {
        return new BuilderViewFile().circuitImageFile(PATH + cir.getCity() + VIEW_CIRCUIT)
                                    .coordinatesFile(PATH + cir.getCity() + SLASH + cir.getCity() + VIEW_FILE)
                                    .trackFile(PATH + cir.getCity() + MODEL_FILE)
                                    .circuitGame(cir)
                                    .build();
    }

}
