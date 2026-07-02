package view.controllers.minigames.colorgama;

import model.minigames.colorgama.ColorMethod;
import model.minigames.colorgama.ColorValuesIterator;
import view.controllers.Area;

/**
 * The view interface of ColorGama minigame.
 *
 */
@Area(trainingArea = "Visual Skill")
public interface ColorGamaFx {

    /**
     * Fill a grid with buttons.
     *
     * @param size
     *          the grid size
     */
    void initGrid(int size);

    /**
     * Set the iterator of color values.
     * 
     * @param iterator 
     *          the color iterator
     * @param method
     *          the method to represent colors
     */
    void setColorIterator(ColorValuesIterator iterator, ColorMethod method);

    /**
     * Render the next level.
     *
     * @param questionSentence
     *          the sentence of the question to show
     */
    void renderNext(String questionSentence);
 
    /**
     * Shows the color indicated in the question. 
     */
    void showQuestionColor();

    /**
     * Hide the color indicated in the question. 
     */
    void hideQuestionColor();

    /**
     * Show the image.
     *
     * @param check
     *          check for right or wrong answer
     */
    void showImage(boolean check);
}
