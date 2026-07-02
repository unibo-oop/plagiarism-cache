package view.level.generator;

import java.io.IOException;

/**
 * Models the loading of map traces.
 */
public interface MapTracesLoader {

    /**
     * This method is called whenever i need to load a new map trace.
     * 
     *  @param fileToReadPath
     *
     *  @return matrix
     *
     *  @throws IOException
     */
    char[][] readFromTxt(String fileToReadPath) throws IOException;
}
