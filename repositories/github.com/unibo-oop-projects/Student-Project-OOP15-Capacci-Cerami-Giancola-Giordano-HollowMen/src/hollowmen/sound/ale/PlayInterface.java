package hollowmen.sound.ale;

import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * The PlayInterface gives the possibility to transform a string into path and create an InputStream
 * @author Alessia
 *
 */
public interface PlayInterface {
    
    /**
     * The {@code sketchPath} method is used to transform a fileName into an absolute path
     * @param fileName
     * @return
     */
    public String sketchPath(final String fileName);
    
    /**
     * The {@code createInput} method is used to create the stream of the audio starting from the 
     * fileName,creating a BufferedInputStream
     * 
     * @param fileName
     * @return 
     * @throws FileNotFoundException
     */
    public InputStream createInput(final String fileName) throws FileNotFoundException;
}
