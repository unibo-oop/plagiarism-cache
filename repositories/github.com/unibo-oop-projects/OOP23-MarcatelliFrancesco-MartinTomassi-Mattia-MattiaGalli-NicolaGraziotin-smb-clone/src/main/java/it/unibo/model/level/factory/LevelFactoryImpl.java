package it.unibo.model.level.factory;

import it.unibo.model.level.Level;
import it.unibo.model.level.LevelImpl;

/**
 * The LevelFactoryImpl class is an implementation of the LevelFactory interface.
 * It provides functionality to create Level objects based on the provided TMX (Tile Map XML) data.
 */
public class LevelFactoryImpl implements LevelFactory {

    @Override
    public final Level createLevel(final String tmx) {
        return new LevelImpl(tmx);
    }

}
