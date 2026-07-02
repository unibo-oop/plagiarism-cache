package color.filter;

import java.awt.Color;

import data.DataProgramImpl;
import model.entity.cell.standard.CellStandard;

/**
 * Calculates the color of a standard cell according to its age,
 * the hue is set by default or by the user.
 * 
 */
public class AgeFilter extends FilterImpl implements Filter {

    private static final int MAX_AGE = DataProgramImpl.getMaxAge();
    private static final float HUE = DataProgramImpl.getHueColor();

    /**
     * Calculates the color of the passed STANDARD_CELL as a parameter based on its age.
     * Starting from a shade chosen by the user, its brightness decreases as the cell ages.
     * LIGHT COLORS -> YOUNG CELLS
     * DARK COLORS -> OLD CELLS
     * @param cellstandard standard cell of which you want to know the color
     * @return cell color 
     */
    @Override
    protected final Color getCellStandardColor(final CellStandard cellstandard) {
        return Color.getHSBColor(HUE, 1, brightness(cellstandard.getAge()));
    }

    private float brightness(final int age) {
        return (float) (MAX_AGE - age) / MAX_AGE;
    }

}
