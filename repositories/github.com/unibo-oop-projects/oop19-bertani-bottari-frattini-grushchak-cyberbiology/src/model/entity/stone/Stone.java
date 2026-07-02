package model.entity.stone;

import model.entity.AbstractEntity;
import model.entity.Entity;
import model.entity.EntityTypeNameEnum;

public class Stone extends AbstractEntity implements Entity {

    public Stone(final int x, final int y) {
        super(x, y);
    }

    @Override
    public EntityTypeNameEnum getEntityType() {
        return EntityTypeNameEnum.STONE;
    }
}
