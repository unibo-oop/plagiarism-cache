package view.playersetup;

import org.apache.commons.lang3.tuple.Triple;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

import javafx.scene.paint.Paint;
import utils.enumerations.Color;
import utils.enumerations.ControlType;

/**
 * 
 * Class that creates a TempPlayer. The player created will be added in {@link PlayersTable}.
 *
 */
public class TempPlayer extends RecursiveTreeObject<TempPlayer> {

    private final String name;
    private final Paint color;
    private final Boolean isAI;
    private final Color colorValue;

    TempPlayer(final String name, final Paint color, final Boolean isAI, final Color value) {
        this.name = name;
        this.color = color;
        this.isAI = isAI;
        this.colorValue = value;
    }

    /**
     * 
     * Gives in output a triple based on {@link name}, {@link colorValue} , {@link ControlType}.
     * 
     * @return
     *          A triple as a representation of a temp user. 
     * 
     */
    public Triple<String, Color, ControlType> toTriple() {
        return Triple.of(getName(), getColorValue(), getIsAI() ? ControlType.AI : ControlType.HUMAN);
    }

    /**
     * 
     * Getter of name.
     * 
     * @return
     *          Player's name.
     * 
     */
    public String getName() {
        return name;
    }

    /**
     * 
     * Getter of color.
     * 
     * @return
     *          Player's color.
     * 
     */
    public Paint getColor() {
        return color;
    }

    /**
     * 
     * Getter of type.
     * 
     * @return
     *          Player's type.
     * 
     */
    public Boolean getIsAI() {
        return isAI;
    }

    /**
     * 
     * Getter of value.
     * 
     * @return
     *          Player's Color value.
     * 
     */
    public Color getColorValue() {
        return colorValue;
    }

}
