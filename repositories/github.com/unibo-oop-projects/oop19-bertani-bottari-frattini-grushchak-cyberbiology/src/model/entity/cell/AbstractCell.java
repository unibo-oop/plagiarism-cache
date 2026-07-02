package model.entity.cell;

import model.entity.AbstractEntity;
import model.entity.EntityTypeNameEnum;

public abstract class AbstractCell extends AbstractEntity implements Cell {
    protected AbstractCell(final int x, final int y) {
        super(x, y);
    }
    @Override
    public final EntityTypeNameEnum getEntityType() {
        return EntityTypeNameEnum.CELL;
    }
    /**
     * @return the cellTypeName
     */
    public abstract CellTypeNameEnum getCellTypeName();
    /**
     * a standard setter.
     * @param x
     */
    public void setX(final int x) {
        super.setX(x);
    }
    /**
     * a standard setter.
     * @param y
     */
    public void setY(final int y) {
        super.setY(y);
    }
}
