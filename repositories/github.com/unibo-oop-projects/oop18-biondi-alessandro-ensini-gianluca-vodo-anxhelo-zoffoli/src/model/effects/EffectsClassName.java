package model.effects;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import model.effects.convolution.Blur;
import model.effects.convolution.BlurType;
import model.effects.convolution.ConvolveOperation;
import model.effects.convolution.FastBlur;
import model.effects.convolution.EdgeDetection;
import model.effects.convolution.EdgeDetectionGrade;
import model.effects.convolution.Emboss;
import model.effects.convolution.Sharpen;
import model.effects.filters.BlackAndWhite;
import model.effects.filters.ColorFilter;
import model.effects.filters.GrayScale;
import model.effects.filters.Negative;
import model.effects.filters.Sepia;
import model.effects.regolations.Brightness;
import model.effects.regolations.Contrast;
import model.effects.regolations.Exsposure;
import model.effects.regolations.Saturation;
import model.effects.regolations.Temperature;

/**
 * Effect's class collectors.
 */
public class EffectsClassName {

    private static final boolean MAP_INSTANTIATED = true;
    private static final boolean MAP_NOT_INSTANTIATED = false;

    private static Map<String, Class<? extends Effect>> map;
    private static boolean mapInstantiated = MAP_NOT_INSTANTIATED;

    /**
     * Use to get effect's class by respectively effect's name. Automatically create
     * a Map of all implemented Effect and preserve it until program will be closed.
     * Is necessary to refresh this map with all new created Effect.
     * 
     * @param effectName effect's name
     * @return respectively effect's class
     */
    public static Class<? extends Effect> getEffectClassName(final String effectName) {
        updateMap();
        return map.get(effectName);
    };

    /**
     * Map start only with default effect in Mustashi loading, than using this
     * method can update Map again (after closing program) to refresh created effect
     * in last session.
     * 
     * @param effectName to update
     * @param className  to update
     */
    public static void restoreEffect(final String effectName, final Class<? extends Effect> className) {
        updateMap();
        map.put(effectName, className);
    }

    /**
     * Use to uptade Map with new Effects.
     * 
     * @param effectName name associated to Effect
     * @param className  name of Effect's Class
     * 
     * @return effect's name updated with index (if necessary) to not overwrite
     *         existing effect with same name and preserve key Map unique
     */
    public static String putNewEffectClassName(final String effectName, final Class<? extends Effect> className) {
        updateMap();
        String tmpName = effectName;
        Integer index = 1;

        while (map.containsKey(tmpName)) {
            if (new EffectsClassName().isIndexYetCreated(tmpName)) {
                tmpName = tmpName.substring(0, tmpName.length() - (2 + Integer.toString(index - 1).length())) + "("
                        + index + ")";
                index++;
            } else {
                tmpName = tmpName + "(1)";
            }
        }
        map.put(tmpName, className);
        return tmpName;
    }

    /**
     * remove created effect.
     * 
     * @param effectName to remove
     */
    public static void removeEffectClassName(final String effectName) {
        map.remove(effectName);
    }

    /**
     * verify if index in file's name is already created.
     * 
     * @param name to verify
     * @return true if name contains index as "( + number + )" at the end of it
     */
    public boolean isIndexYetCreated(final String name) {

        if (name.length() < 3) {
            return false;
        }
        int index = 2;
        char indexChar = name.charAt(name.length() - index);
        String fileIndex = Character.toString(indexChar);
        final char closeBracket = name.charAt(name.length() - 1);
        char openBracket = name.charAt(name.length() - (index + 1));

        while ((name.length() - (index + 2)) > -1 && Character.isDigit(name.charAt((name.length() - (index + 1))))) {
            indexChar = name.charAt(name.length() - (index + 1));
            fileIndex = Character.toString(indexChar) + fileIndex;
            openBracket = name.charAt(name.length() - (index + 2));
            index++;
        }

        return openBracket == '(' && StringUtils.isNumeric(fileIndex) && closeBracket == ')';
    }

    /*
     * Set Map with all implemetend Effect's name and rispectively class value.
     */
    private static void createMap() {
        map = new HashMap<String, Class<? extends Effect>>();
        map.put(new VoidEffect().getEffectName(), VoidEffect.class);
        map.put(new Blur(0, BlurType.BOX_BLUR).getEffectName(), Blur.class);
        map.put(new Blur(0, BlurType.GAUSSIAN_BLUR).getEffectName(), Blur.class);
        map.put(new FastBlur(0).getEffectName(), FastBlur.class);
        map.put(new ConvolveOperation().getEffectName(), ConvolveOperation.class);
        map.put(new EdgeDetection(EdgeDetectionGrade.LOW).getEffectName(), EdgeDetection.class);
        map.put(new Emboss().getEffectName(), Emboss.class);
        map.put(new Sharpen().getEffectName(), Sharpen.class);
        map.put(new BlackAndWhite().getEffectName(), BlackAndWhite.class);
        map.put(new ColorFilter(0, 0, 0).getEffectName(), ColorFilter.class);
        map.put(new GrayScale().getEffectName(), GrayScale.class);
        map.put(new Negative().getEffectName(), Negative.class);
        map.put(new Sepia().getEffectName(), Sepia.class);
        map.put(new Brightness().getEffectName(), Brightness.class);
        map.put(new Contrast().getEffectName(), Contrast.class);
        map.put(new Exsposure().getEffectName(), Exsposure.class);
        map.put(new Saturation().getEffectName(), Saturation.class);
        map.put(new Temperature().getEffectName(), Temperature.class);

        mapInstantiated = MAP_INSTANTIATED;
    }

    /*
     * update map with all default effect in starting Mustashi's session
     */
    private static void updateMap() {
        if (mapInstantiated == MAP_NOT_INSTANTIATED) {
            createMap();
        }
    }
}
