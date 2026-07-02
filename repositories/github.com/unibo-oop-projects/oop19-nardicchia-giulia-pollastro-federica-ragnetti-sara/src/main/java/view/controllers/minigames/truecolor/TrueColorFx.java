package view.controllers.minigames.truecolor;

import java.util.List;
import java.util.Map;

import model.minigames.truecolor.Colors;
import model.minigames.truecolor.StatusColor;
import utility.Pair;
import view.controllers.Area;

/**
 * 
 * Interface of TrueColorFx.
 *
 */
@Area(trainingArea = "Attention")
public interface TrueColorFx {

    /**
     * Reload the components of the scene.
     * 
     */
    void next();

    /**
     * Set a random color and a random textColor to buttons.
     * 
     * @param colorMap map with lists of Colors pair
     */
    void setButtons(Map<StatusColor, List<Pair<Colors, Colors>>> colorMap);

    /**
     * Set the number of button.
     * 
     * @param colorMap map with lists of Colors pair
     */
    void setSizeBtnList(Map<StatusColor, List<Pair<Colors, Colors>>> colorMap);

    /**
     * Set a random color to the buttons background.
     * 
     * @param randomColor list of random color belonging to true color list
     */
    void alternativeSetButton(List<Pair<Colors, Colors>> randomColor);

    /**
     * Show the answer coloring the button's border.
     * 
     * @param bool that identify if the answer is correct or not.
     */
    void showAnswer(boolean bool);
}
