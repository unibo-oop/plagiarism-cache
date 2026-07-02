package model;

import java.util.List;

import model.entities.Brick;

/**
 * Classe concreta che modella il gioco in modalità SURVIVAL. Concretizza {@link AbstractGame}.
 * @see GameMode
 * 
 * @author Gnoli Mirco
 *
 */
//scalare la riga verso il basso e crearne una nuova sopra
public class SurvivalGame extends AbstractGame {

    @Override
    protected final List<Brick> doIfBricksRowIsEmpty(final Brick template) {
        List<Brick> row = getFactory().randomBrickRow();
        for (final Brick brick: row) {
            brick.setPosition(brick.getMinX(), template.getMinY());
        }
        return row;
    }
}
