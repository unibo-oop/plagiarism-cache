package model.util.storage;

import java.io.File;
import java.io.IOException;

import model.effects.convolution.ConvolveOperation;
import model.util.format.Format;
import model.util.format.FormatManager;

/**
 * Represents a manager for the differents convolution effects.
 */
public class ConvolutionManager extends ObjectManagerImpl<ConvolveOperation> {

    private static final String DEF_PATH = FormatManager.getDefaultConvolutionsPath();
    private static final String FORMAT = Format.cnv.toString();

    /**
     * @param effectName the specified effect name used to save the object
     */
    public ConvolutionManager(final String effectName) {
        super(DEF_PATH + effectName + FORMAT);
    }

    /**
     * remove the convolution effect specified in the constructor .
     * 
     * @throws IOException if the attempt to delete the file fails
     */
    public void remove() throws IOException {
        final File file = new File(super.getObjectFilePath());

        if (!file.delete()) {
            throw new IOException();
        }
    }
}
