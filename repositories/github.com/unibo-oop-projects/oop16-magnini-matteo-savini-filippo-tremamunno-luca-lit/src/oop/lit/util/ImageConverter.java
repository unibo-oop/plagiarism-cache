package oop.lit.util;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

/**
 * A class used to convert a BufferedImage to something else.
 * If an image was already converted the result will not be recalculated.
 * @param <T>
 *      the type of object resulting from the image conversion. 
 */
public class ImageConverter<T> {
    private final T defaultRes;
    private final Function<? super BufferedImage, T> converterFunction;
    private final Map<BufferedImage, T> conversionRes = new HashMap<>();

    /**
     * @param converterFunction
     *      the function used to convert the buffered image to the desired object.
     * @param defaultRes
     *      the result of the convert method if the provided optional is empty
     */
    protected ImageConverter(final Function<? super BufferedImage, T> converterFunction, final T defaultRes) {
        this.converterFunction = converterFunction;
        this.defaultRes = defaultRes;
    }

    /**
     * Get a Object corresponding to the provided BufferedImage.
     * @param bufferedImage
     *      an Optional containing the image. If the Optional is empty this returns a default value.
     * @return
     *      the object resulting from the conversion function.
     */
    public T convert(final Optional<BufferedImage> bufferedImage) {
        if (!bufferedImage.isPresent()) {
            return this.defaultRes;
        }
        T res = conversionRes.get(bufferedImage.get());
        if (res == null) {
            res = converterFunction.apply(bufferedImage.get());
            conversionRes.put(bufferedImage.get(), res);
        }
        return res;
    }
}
