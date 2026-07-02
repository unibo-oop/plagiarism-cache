package model.util.storage;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Locale;

import javax.imageio.ImageIO;

import org.apache.commons.imaging.ImageFormats;
import org.apache.commons.imaging.ImageReadException;
import org.apache.commons.imaging.ImageWriteException;
import org.apache.commons.imaging.Imaging;
import org.apache.commons.io.FilenameUtils;

import com.google.gson.reflect.TypeToken;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import model.effects.VoidEffect;
import model.util.format.Format;
import model.util.history.History;
import model.util.history.HistoryImpl;
import model.util.storage.serialization.ComponentSerialized;

/**
 * Represents a loader and saver manager for project's
 * {@link model.util.history.History}. Saving actual history status in file
 * system, allows to reload it after program restart. Give the opportunity to
 * export only image with no one information about his changes. Could export
 * image in a different allowed format, see {@link FormatManager} to allowed
 * format list.
 */
public class HistoryManager extends ObjectManagerImpl<List<ComponentSerialized>> {

    private static final String JPEG = Format.jpeg.toString().substring(2);
    private static final String JPEG_U = JPEG.toUpperCase(Locale.getDefault());
    private static final String JPG = Format.jpg.toString().substring(2);
    private static final String JPG_U = JPG.toUpperCase(Locale.getDefault());
    private static final String PNG_U = Format.png.toString().substring(2).toUpperCase(Locale.getDefault());

    /**
     * @see model.util.storage.ObjectManagerImpl#ObjectManagerImpl(java.lang.String)
     * 
     * @param historyPath history absolute path to work with
     */
    public HistoryManager(final String historyPath) {
        super(historyPath);
    }

    /**
     * Save history status into file system.
     * 
     * @see model.util.storage.ObjectManagerImpl#save(java.lang.Object)
     * 
     * @param historyToSave history to save in file system
     */
    public void saveHistory(final History historyToSave) {
        super.save(((HistoryImpl) historyToSave).toHistorySerialized());
    }

    /**
     * Load existing history by file system. Create a new TypeToken
     * (com.google.gson.reflect.TypeToken) using Gson's library to allow
     * serialization of a Collections.
     * 
     * @see model.util.storage.ObjectManagerImpl#load(java.lang.reflect.Type)
     * 
     * @return history instance
     * @throws IOException if failed to load by file system
     */
    public History loadExistingHistory() throws IOException {
        final Type lisType = new TypeToken<List<ComponentSerialized>>() {
        }.getType();

        return new HistoryImpl().toHistory(super.load(lisType));
    }

    /**
     * Load new image by file system. Used to start a new History. Try first loading
     * with Apache's library, this allow to work with tiff image format. If failed
     * with Apache, try creating new javafx's Image
     * 
     * @return javafx Image
     * @throws IOException if failed to load by file system
     */
    public Image loadNewImage() throws IOException {
        final BufferedImage bufImage;
        final File tmpFile = new File(super.getObjectFilePath());

        try {
            bufImage = Imaging.getBufferedImage(tmpFile);
            return SwingFXUtils.toFXImage(bufImage, null);
        } catch (ImageReadException e) {
            // Error encountered in Image deserialization using Apache, try again using
            // javafx's Image
            return new Image(tmpFile.toURI().toString());
        }
    }

    /**
     * get intial image format allowing to launch an eventually save chooser with
     * correct starting image format.
     * 
     * @return inital void effect status of new History,
     */
    public VoidEffect getVoidEffect() {
        return new VoidEffect(FilenameUtils.getExtension(super.getObjectFilePath()));
    }

    /**
     * Save image in his actual status in setted absolute path. Using Apache
     * library, allow to wirte correctly bmp and tiff image format, and by
     * converting image in png format and setting BufferedImage's type as
     * TYPE_INT_RGB, allow to save correctly jpg/jpeg image format
     * 
     * @param image actual image status
     */
    public void exportImage(final Image image) {

        final String ext = FilenameUtils.getExtension(getObjectFilePath()).toUpperCase(Locale.getDefault());
        final BufferedImage tmpBufferedImage;
        final BufferedImage toWrite;
        final File tmpFile = new File(super.getObjectFilePath());

        try {

            if (ext.equals(JPEG_U) || ext.equals(JPG_U)) {
                // overwrite same image file to reset correctly BufferedImage color type
                Imaging.writeImage(SwingFXUtils.fromFXImage(image, null), tmpFile, ImageFormats.valueOf(PNG_U), null);
                tmpBufferedImage = Imaging.getBufferedImage(tmpFile);
                toWrite = new BufferedImage(tmpBufferedImage.getWidth(), tmpBufferedImage.getHeight(),
                        BufferedImage.TYPE_INT_RGB);
                toWrite.createGraphics().drawImage(tmpBufferedImage, 0, 0, null, null);
                ImageIO.write(toWrite, (ext.equals(JPEG_U) ? JPEG : JPG), tmpFile);

            } else {
                Imaging.writeImage(SwingFXUtils.fromFXImage(image, null), tmpFile, ImageFormats.valueOf(ext), null);
            }

        } catch (

        IOException e) {
            System.out.println("Failed to write image into file system: " + e.getMessage());
        } catch (IllegalArgumentException | ImageWriteException e) {
            System.out.println("Not appropriate wirter founded to " + ext + " image format: " + e.getMessage());
        } catch (ImageReadException e) {
            System.out.println("Failed to read temp BufferedImage in conversion of PNG to JPG/JPEG: " + e.getMessage());
        }
    }
}
