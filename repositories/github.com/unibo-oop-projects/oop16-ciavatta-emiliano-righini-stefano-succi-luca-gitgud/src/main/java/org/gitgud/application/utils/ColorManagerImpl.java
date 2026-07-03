package org.gitgud.application.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import javafx.scene.paint.Color;

/**
 * An implementation of the color manager.
 */
class ColorManagerImpl implements ColorManager {

    private static final List<Color> COLORS;

    static {
        final List<String> colorsHex = Arrays.asList("#ef5350", "#ec407a", "#ab47bc", "#7e57c2", "#5c6bc0", "#42a5f5",
                "#29b6f6", "#26c6da", "#26a69a", "#66bb6a", "#9ccc65", "#d4e157", "#ffee58", "#ffca28", "#ffa726",
                "#ff7043");
        COLORS = colorsHex.stream().map(c -> Color.valueOf(c)).collect(Collectors.toList());
    }

    private final List<Color> currentPalette;
    private final List<Color> usedColors;

    ColorManagerImpl() {
        currentPalette = new ArrayList<>(COLORS);
        usedColors = new ArrayList<>();
        Collections.shuffle(currentPalette, new Random(System.nanoTime()));
    }

    @Override
    public boolean releaseColor(final Color color) {
        if (usedColors.remove(color)) {
            currentPalette.add(color);

            return true;
        }

        return false;
    }

    @Override
    public Color requestColor() {
        if (currentPalette.isEmpty()) {
            throw new IllegalStateException("All colors used");
        }

        final Color color = currentPalette.remove(0);
        usedColors.add(color);

        return color;
    }

}
