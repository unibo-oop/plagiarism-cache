package model.entities;

import model.common.Dimension;
import model.common.DimensionImpl;

public interface Block extends GameEntity {

    Dimension DEFALULT_BLOCK_DIMENSION = new DimensionImpl(1, 1);
    Dimension BASE_BLOCK_DIMENSION = new DimensionImpl(2, 2);

    Type getType();

    enum Type {
        WALL(), IRON(), GRASS(), WATER(), ICE(), BASE(BASE_BLOCK_DIMENSION);

        private final Dimension dimension;

        private Type() {
            this.dimension = DEFALULT_BLOCK_DIMENSION;
        }

        private Type(Dimension dimension) {
            this.dimension = dimension;
        }

        Dimension getDimension() {
            return dimension;
        }
    }

}
