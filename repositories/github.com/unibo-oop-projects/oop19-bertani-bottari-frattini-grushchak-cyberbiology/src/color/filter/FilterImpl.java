package color.filter;

import java.awt.Color;
import java.util.Optional;

import data.DataProgramImpl;
import model.entity.Entity;
import model.entity.cell.Cell;
import model.entity.cell.standard.CellStandard;

/**
 * Graphic class that allows to calculate the colors of the entities in the world 
 * according to the type of entities and their characteristics.
 *
 */
public abstract class FilterImpl implements Filter {

    @Override
    public final Color getColor(final Optional<Entity> square) {
       Color color;
       if (square.isPresent()) {
           color = colorEntity(square.get());
       } else {
           color = ColorProgram.BACKGROUND_COLOR.getColor();
       }
        return color;

    }

    protected final Color colorEntity(final Entity entity) {
        Color entityColor;
        switch (entity.getEntityType()) {
            case CELL: entityColor = cellColor((Cell) entity); break;
            case STONE: entityColor = ColorProgram.STONE_COLOR.getColor(); break;
            default: throw new IllegalArgumentException("TIPO DI ENTITY INESISTENTE");
        }
        return entityColor;
    }

    protected final Color cellColor(final Cell cell) {
        Color cellColor;
        switch (cell.getCellTypeName()) {
            case CELL_STANDARD_ALIVE: 
                cellColor = getCellStandardColor((CellStandard) cell);
                break;
            case CELL_DEAD:
                cellColor = ColorProgram.CELL_DEATH_COLOR.getColor();
                break;
            default: throw new IllegalArgumentException("TIPO DI CELL INESISTENTE");
        }
        return cellColor;
    }

    protected abstract Color getCellStandardColor(CellStandard cellstandard);

}

