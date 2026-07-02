package controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import model.effects.EffectsClassName;
import model.effects.convolution.Blur;
import model.effects.convolution.BlurType;
import model.effects.convolution.Convolution;
import model.effects.convolution.ConvolutionKernel;
import model.effects.convolution.ConvolveOperation;
import model.effects.convolution.EdgeDetection;
import model.effects.convolution.EdgeDetectionGrade;
import model.effects.convolution.Emboss;
import model.effects.convolution.FastBlur;
import model.effects.convolution.Sharpen;
import model.util.format.Format;
import model.util.format.FormatManager;
import model.util.storage.ConvolutionManager;

/**
 * Handler of the view of convolution.
 */
public class ConvolutionHandler {

    private List<Convolution> convolutionList = new ArrayList<Convolution>();

    /**
     * Apply blur effect.
     * 
     * @param amount        the specified amount of blur
     * @param indexBlurType the index of blur type
     */
    public void applyBlur(final int amount, final int indexBlurType) {
        if (BlurType.values()[indexBlurType] == BlurType.FAST_BLUR) {
            EffectHandler.applyEffect(new FastBlur(amount));
        } else {
            EffectHandler.applyEffect(new Blur(amount, BlurType.values()[indexBlurType]));
        }
    }

    /**
     * Apply edge detection effect.
     * 
     * @param indexEdgeDetectionGrade the index of edge detection type
     */
    public void applyEdgeDetection(final int indexEdgeDetectionGrade) {
        EffectHandler.applyEffect(new EdgeDetection(EdgeDetectionGrade.values()[indexEdgeDetectionGrade]));
    }

    /**
     * Apply sharpen effect.
     */
    public void applySharpen() {
        EffectHandler.applyEffect(new Sharpen());
    }

    /**
     * Apply emboss effect.
     */
    public void applyEmboss() {
        EffectHandler.applyEffect(new Emboss());
    }

    /**
     * Apply personalized convolution effect.
     * 
     * @param indexPersonalizedEffect the index of the personalized effect
     */
    public void applyPersonalizedEffect(final int indexPersonalizedEffect) {
        EffectHandler.applyEffect(convolutionList.get(indexPersonalizedEffect));
    }

    /**
     * Save a new personalized effect.
     * 
     * @param matrix     the kernel matrix to create a convolution effect
     * @param divider    the specified divider to apply to the matrix kernel
     * @param effectName the specified effect name of the convolution effect
     */
    public void addPersonalizedEffect(final float[] matrix, final int divider, final String effectName) {
        final ConvolveOperation convolutionEffect = new ConvolveOperation();
        final String updatedName = EffectsClassName.putNewEffectClassName(effectName, convolutionEffect.getClass());
        convolutionEffect.setKernel(new ConvolutionKernel(3, 3, matrix, divider));
        convolutionEffect.setEdgeCondition(0);
        convolutionEffect.setEffectName(updatedName);

        new ConvolutionManager(updatedName).save(convolutionEffect);
        convolutionList.add(convolutionEffect);
    }

    /**
     * Remove the selected personalized effect.
     * 
     * @param indexEffect the specified personalized effect index
     * @throws IOException if the attempt to delete the file fails
     */
    public void removePersonalizedEffect(final int indexEffect) throws IOException {
        final String tmpName = convolutionList.get(indexEffect).getEffectName();
        new ConvolutionManager(tmpName).remove();
        convolutionList.remove(convolutionList.remove(indexEffect));
        EffectsClassName.removeEffectClassName(tmpName);

    }

    /**
     * initialize the convolution effects saved on drive.
     * 
     * @return a list of effect saved
     */
    public List<String> initializePersonalizedEffect() {
        List<String> result = new ArrayList<String>();

        final File directory = new File(FormatManager.getDefaultConvolutionsPath());

        if (directory.exists() || directory.mkdirs()) {
            // loading personalized effect from drive
            try (Stream<Path> walk = Files.walk(Paths.get(FormatManager.getDefaultConvolutionsPath()))) {
                result = walk.sorted((x, y) -> Long.compare(x.toFile().lastModified(), y.toFile().lastModified()))
                        .map(x -> Optional.ofNullable(x.getFileName())).filter(Optional::isPresent)
                        .map(s -> s.get().toString()).filter(f -> f.endsWith(Format.cnv.toString()))
                        .map(s -> s.substring(0, s.length() - Format.cnv.toString().length()))
                        .collect(Collectors.toList());

                convolutionList = result.stream().map(x -> {
                    try {
                        final ConvolveOperation tmpConvOp = new ConvolutionManager(x).load(ConvolveOperation.class);
                        EffectsClassName.restoreEffect(tmpConvOp.getEffectName(), ConvolveOperation.class);
                        return tmpConvOp;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return new ConvolveOperation();
                }).collect(Collectors.toList());
            } catch (IOException e) {
                e.printStackTrace();
                result = new ArrayList<String>();
            }
        }

        return result;
    }

}
