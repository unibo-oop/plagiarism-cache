package it.unibo.oop.bbgmm.Entity.Component;

import it.unibo.oop.bbgmm.Entity.AbstractEntity;
import it.unibo.oop.bbgmm.Utilities.Pair;

public class Alien extends AbstractEntity {


    private static final double WALK_SPEED=5;

    public Alien(final BodyBuilder bodyBuilder, final Pair<Integer, Integer> position){
        super(bodyBuilder
                .setPosition(position)
                .build());

        add(new Feet(WALK_SPEED));

    }
}
