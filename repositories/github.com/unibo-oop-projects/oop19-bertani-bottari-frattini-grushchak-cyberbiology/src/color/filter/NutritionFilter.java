package color.filter;

import java.awt.Color;

import model.entity.cell.standard.CellStandard;
import model.entity.cell.standard.obtainable.EnergyTypeEnum;
import model.properties.defaultdata.ViewDefaultDataEnum;

/**
 * Calculates the color of a standard cell based on the type of nutrition,
 * in percentage, which the cell has exploited since it was born.
 * 
 */
public class NutritionFilter extends FilterImpl implements Filter {

    private static final int MAX_RANGE_COLOR = ViewDefaultDataEnum.COLOR_RGB_RANGE.getData().getMaximumValue().intValue();
    private int r;
    private int g;
    private int b;

    /**
     * Calculates the color of the past STANDARD_CELL as a parameter based on the nutrition during the life of the cell.
     * In phase to the percentage nourishment the cell will assume colors tending:
     * RED COLORS-> NOURISHMENT FROM OTHER CELLS
     * GREEN COLORS-> PHOTOSYNTHETIC NOURISHMENT
     * BLUE COLORS-> MINERAL NOURISHMENT
     * @param cellstandard cell whose color you want to know the color
     * @return cell color 
     * 
     */
    @Override
    protected final Color getCellStandardColor(final CellStandard cellstandard) {
        setValue(cellstandard);
        int energyTot = cellstandard.getTotalEnergyGained();
        if (energyTot == 0) {
            return new Color(MAX_RANGE_COLOR, MAX_RANGE_COLOR, MAX_RANGE_COLOR);
        } else {
            return new Color(getValue(r, energyTot), getValue(g, energyTot), getValue(b, energyTot));
        }
    }

    private void setValue(final CellStandard cellstandard) {
        r = cellstandard.getSpecificEnergyGained(EnergyTypeEnum.ALTRUISM) + cellstandard.getSpecificEnergyGained(EnergyTypeEnum.EATING);
        g = cellstandard.getSpecificEnergyGained(EnergyTypeEnum.PHOTOSYNTHESIS);
        b = cellstandard.getSpecificEnergyGained(EnergyTypeEnum.CONVERTING_MINERAL);
    }

    private int getValue(final int val, final int energyTot) {
        return val * MAX_RANGE_COLOR / energyTot;
    }
}
