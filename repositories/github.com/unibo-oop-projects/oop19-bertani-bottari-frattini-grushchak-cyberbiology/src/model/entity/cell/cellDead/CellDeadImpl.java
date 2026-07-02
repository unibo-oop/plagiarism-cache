package model.entity.cell.cellDead;

import model.entity.cell.AbstractCell;
import model.entity.cell.CellTypeNameEnum;

public class CellDeadImpl extends AbstractCell implements CellDead {

    public CellDeadImpl(final int x, final int y) {
        super(x, y);
    }

    @Override
    public final CellTypeNameEnum getCellTypeName() {
        return CellTypeNameEnum.CELL_DEAD;
    }

    @Override
    public final void setX(final int x) {
        super.setX(x);
    }

    @Override
    public final void setY(final int y) {
        super.setY(y);
    }

}
