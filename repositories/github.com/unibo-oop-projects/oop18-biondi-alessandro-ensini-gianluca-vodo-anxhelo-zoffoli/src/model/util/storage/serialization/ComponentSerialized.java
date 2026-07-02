package model.util.storage.serialization;

import java.io.IOException;
import java.io.Serializable;
import java.util.Locale;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.imaging.ImageFormats;
import org.apache.commons.imaging.ImageReadException;
import org.apache.commons.imaging.ImageWriteException;
import org.apache.commons.imaging.Imaging;

import com.google.gson.Gson;

import controller.exceptions.EffectClassNameNotFoundException;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

import model.effects.Effect;
import model.effects.EffectsClassName;
import model.util.format.Format;
import model.util.history.Component;
import model.util.history.ComponentImpl;

/**
 * this is a single component's instance that sobstitute Image and Effect fields
 * with a serializable Class.
 */
public class ComponentSerialized implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final transient String PNG_U = Format.png.toString().substring(2).toUpperCase(Locale.getDefault());

    private String serializedImage;
    private String effect;
    private String effectName;

    /**
     * Make component serializable by serialize it's fields. Use this function
     * implies to had correctly updated EffectClassName with all possible
     * instantiable Effect
     * 
     * @param image image to serialize
     * @param efct  effect to serialize
     * @return respectively serialized Component
     */
    public ComponentSerialized makeComponentSerializable(final Image image, final Effect efct) {
        this.effectName = efct.getEffectName();
        try {
            makeEffectSerializable(efct);
        } catch (EffectClassNameNotFoundException e) {
            System.out.println(e.getMessage());
            return null;
        }
        makeImageSerializable(image);
        return this;
    }

    /**
     * Deserialize serialized component by deserialize it's fields. Use this
     * function implies to had correctly updated EffectClassName with all possible
     * 
     * @return deserialized component
     */
    public Component deserializeComponent() {
        try {
            return new ComponentImpl(returnToImage(), returnToEffect());
        } catch (EffectClassNameNotFoundException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    /*
     * Transform Effect in a Gson's String.
     * 
     * effectToSer: effect to serialize
     * 
     * throws EffectClassNameNotFoundException if effect class name not be updated
     * in EffectClassName Map
     */
    private void makeEffectSerializable(final Effect effectToSer) throws EffectClassNameNotFoundException {

        if (EffectsClassName.getEffectClassName(this.effectName) == null) {
            throw new EffectClassNameNotFoundException("Failed to serialize Effect, Class of " + effectName
                    + "'s effect (" + effectToSer.getClass().toString() + ") not uptaded in EffectClassName");
        }

        this.effect = new Gson().toJson(effectToSer);
    }

    /*
     * Transform Gson's String in a Effect instance.
     * 
     * return effect deserialized
     * 
     * throws EffectClassNameNotFoundException if effect class name not be updated
     * in EffectClassName Map
     */
    private Effect returnToEffect() throws EffectClassNameNotFoundException {
        final Class<? extends Effect> tmpClass = EffectsClassName.getEffectClassName(this.effectName);
        if (tmpClass == null) {
            throw new EffectClassNameNotFoundException(
                    "Failed to create Effect, Class of " + effectName + "'s effect not founded in EffectClassName");
        }
        return new Gson().fromJson(effect, tmpClass);
    }

    /*
     * Transform javafx's Image in Base64's String by converting it in a byte array
     * first. Same as in HistoryManager need to work in different way if we are
     * converting a JPEG/JPG image
     * 
     * imgToSer: image to serialize
     */
    private void makeImageSerializable(final Image imgToSer) {

        try {
            this.serializedImage = Base64.encodeBase64String(Imaging
                    .writeImageToBytes(SwingFXUtils.fromFXImage(imgToSer, null), ImageFormats.valueOf(PNG_U), null));

        } catch (IOException | ImageWriteException e) {
            System.out.println("Failed to serialize Image: " + e.getMessage());
        }
    }

    /*
     * Transform Base64's String in a javafx's Image. Same as in HistoryManager need
     * to work in different way to work with all alowed formats
     * 
     * return image deserialized
     */
    private Image returnToImage() {

        try {
            return SwingFXUtils.toFXImage(Imaging.getBufferedImage(Base64.decodeBase64(serializedImage)), null);

        } catch (IOException | ImageReadException e) {
            System.out.println("Failed to deserialize image: " + e.getMessage());
            return null;
        }
    }
}
