package model.objects.structures;

import java.util.Optional;

import model.BasicCostImpl;
import model.player.Player;

/**
 * The Level1Capital class extends CapitalImpl and represent the first level of a Capital. 
 */
public class Level1Capital extends CapitalImpl {

    private static final double ENEMY_ATTACK_REDUCTION = 0.80;
    private static final int POPULATION_BOOST = 5;
    private static final String NAME = "Capital level 1";

    /**
     * Level1Capital constructor.
     * @param player is the player that own the capital.
     */
    public Level1Capital(final Optional<Player> player) {
        super(player, NAME, ENEMY_ATTACK_REDUCTION, POPULATION_BOOST,
                new BasicCostImpl());
    }
}
