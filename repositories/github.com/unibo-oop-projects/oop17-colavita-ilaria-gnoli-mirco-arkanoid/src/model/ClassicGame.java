package model;

import java.util.ArrayList;
import java.util.List;

import model.entities.Brick;

/**
 * Classe concreta che modella il gioco in modalità CLASSICA. Concretizza {@link AbstractGame}.
 * @author Gnoli Mirco
 *
 */
public class ClassicGame extends AbstractGame {
    /**
     * 
     */
    public ClassicGame() {
        super();
    }

    @Override
    protected final List<Brick> doIfBricksRowIsEmpty(final Brick template) {
        return new ArrayList<>();
    }
}
