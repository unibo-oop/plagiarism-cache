package view.graphics_util;

/**
 * Factory interface for creating image loaders for different formats.
 */
public interface ImportImageFactory {

    /**
     * Returns an image loader for PNG format.
     * 
     * @return an ImportImage for PNG files
     */
    ImportImage loaderPNG();

    /**
     * Returns an image loader for JPNG format.
     * 
     * @return an ImportImage for JPNG files
     */
    ImportImage loaderJPNG();

    /**
     * Returns an image loader for JPEG format.
     * 
     * @return an ImportImage for JPEG files
     */
    ImportImage loaderJPEG();

}
