package view.settings;

import java.util.Objects;
import java.util.Optional;

import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;

/**
 * Implements a default manager for screen sizes using singleton.
 */
public final class ScreenSettings {

    private static final double WIDTHPERCENT = 100;
    private static final double HEIGHPERCENT = 100;
    private static final double PERCENTFACTOR = 100;
    private static final double PROPHEIGHT = 9;
    private static final double PROPWIDTH = 16;
    private static ScreenSettings settings;
    private Optional<Double> height = Optional.empty();
    private Optional<Double> width = Optional.empty();

    private ScreenSettings() {
    }

    /**
     * @return the ScreenSettingsImpl singleton
     */
    public static ScreenSettings getSettings() {
        if (Objects.isNull(settings)) {
            settings = new ScreenSettings();
        }
        return settings;
    }

    /**
     * It gets the default size for the height.
     * 
     * @return a double which is the height size
     */
    public double getDefaultSizeHeight() {
        if (!this.height.isPresent()) {
            this.scaleScreen();
        }
        return this.height.get();
    }

    /**
     * It gets the default size for the width.
     * 
     * @return a double which is the width size
     */
    public double getDefaultSizeWidth() {
        if (!this.width.isPresent()) {
            this.scaleScreen();
        }
        return this.width.get();
    }

    private Rectangle2D getDimentions() {
        return Screen.getPrimary().getVisualBounds();
    }

    private void scaleScreen() {
        double currentWidth = this.getDimentions().getWidth();
        double currentHeight = this.getDimentions().getHeight();

        // if width > height
        if (Double.compare(currentWidth, currentHeight) > 0) {
            currentHeight = currentHeight * HEIGHPERCENT / PERCENTFACTOR;
            currentWidth = (PROPWIDTH * currentHeight) / PROPHEIGHT;
        } else {
            currentWidth = currentWidth * WIDTHPERCENT / PERCENTFACTOR;
            currentHeight = (PROPHEIGHT * currentWidth) / PROPWIDTH;
        }
        this.height = Optional.of(currentHeight);
        this.width = Optional.of(currentWidth);
    }

}
