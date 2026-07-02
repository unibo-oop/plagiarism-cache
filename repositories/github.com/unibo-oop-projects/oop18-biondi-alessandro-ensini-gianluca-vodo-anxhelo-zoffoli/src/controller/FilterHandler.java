package controller;

import controller.exceptions.ImageNotInitializedException;
import model.effects.filters.BlackAndWhite;
import model.effects.filters.ColorFilter;
import model.effects.filters.Filter;
import model.effects.filters.GrayScale;
import model.effects.filters.Negative;
import model.effects.filters.Sepia;
import view.MainWindowController;

/**
 * Class to manage all the filters.
 */
public class FilterHandler {

    private Filter filter;
    private int redIntensity;
    private int greenIntensity;
    private int blueIntensity;

    /**
     * Create a new filter handler.
     */
    public FilterHandler() {
        redIntensity = 0;
        greenIntensity = 0;
        blueIntensity = 0;

    }

    /**
     * Apply the Black and White filter.
     */
    public void showBlackAndWhite() {
        this.filter = new BlackAndWhite();
        showFilter();
    }

    /**
     * Apply the Gray Scale filter.
     */
    public void showGrayScale() {
        this.filter = new GrayScale();
        showFilter();
    }

    /**
     * Apply the Negative filter.
     */
    public void showNegative() {
        this.filter = new Negative();
        showFilter();
    }

    /**
     * Apply the Sepia filter.
     */
    public void showSepia() {
        this.filter = new Sepia();
        showFilter();
    }

    /**
     * Show the Red Color filter filter.
     * 
     * @param intensity if the filter
     */
    public void showColorFilterRed(final int intensity) {
        redIntensity = intensity;
        this.filter = new ColorFilter(redIntensity, greenIntensity, blueIntensity);
        showFilter();
    }

    /**
     * Show the Green Color filter filter.
     * 
     * @param intensity if the filter
     */
    public void showColorFilterGreen(final int intensity) {
        greenIntensity = intensity;
        this.filter = new ColorFilter(redIntensity, greenIntensity, blueIntensity);
        showFilter();
    }

    /**
     * Show the Blue Color filter filter.
     * 
     * @param intensity if the filter
     */
    public void showColorFilterBlue(final int intensity) {
        blueIntensity = intensity;
        this.filter = new ColorFilter(redIntensity, greenIntensity, blueIntensity);
        showFilter();
    }

    /**
     * Apply the shown filter adding it to history.
     */
    public void applyFilter() {
        try {
            EffectHandler.addToHistory(MainWindowController.getImage(), this.filter);
        } catch (ImageNotInitializedException e) {
            e.printStackTrace();
        }
    }

    private void showFilter() {
        try {
            EffectHandler.showEffect(this.filter, HistoryHandler.getHistoryHandler().getCurrentImage());
        } catch (ImageNotInitializedException e) {
            doNothing();
        }
    }

    // prevent PMD warning
    private void doNothing() {

    }
}
