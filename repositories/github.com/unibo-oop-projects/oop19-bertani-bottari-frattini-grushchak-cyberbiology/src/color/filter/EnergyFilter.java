package color.filter;

import java.awt.Color;

import data.DataProgramImpl;
import model.entity.cell.standard.CellStandard;

/**
 * Calculates the color of a standard cell according to its age,
 * the hue is set by default or by the user.
 * 
 */
public class EnergyFilter extends FilterImpl implements Filter {

    private static final int MAX_ENERGY = DataProgramImpl.getMaxEnergy();
    private static final float HUE = DataProgramImpl.getHueColor();

    /**
     * Calculates the color of the STANDARD_CELL passed as a parameter based on its energy.
     * Starting from a shade chosen by the user, its saturation decreases as the energy decreases.
     * BRIGHT COLORS-> HIGHER ENERGY
     * OFF COLORS-> LESS ENERGY
     * @param cellstandard standard cell of which you want to know the color
     * @return cell color 
     */
    @Override
    protected final Color getCellStandardColor(final CellStandard cellstandard) {
        return Color.getHSBColor(HUE, saturation(cellstandard.getEnergy()), 1);
    }

    private float saturation(final int energy) {
        return (float) energy / MAX_ENERGY;
    }

}
