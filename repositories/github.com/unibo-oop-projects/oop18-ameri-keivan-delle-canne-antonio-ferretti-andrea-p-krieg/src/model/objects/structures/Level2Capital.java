package model.objects.structures;

import java.util.Optional;

import model.BasicCostImpl;
import model.player.Player;

/**
 * The Level2Capital class extends CapitalImpl and represent the second level of a Capital. This level has an unlock cost.
 */
public class Level2Capital extends CapitalImpl {

    private static final double ENEMY_ATTACK_REDUCTION = 0.60;
    private static final int POPULATION_BOOST = 10;
    private static final String NAME = "Capital level 2";
    private static final int GOLD_COST = 400;
    private static final int WOOD_COST = 400;

    /**
     * Level2Capital constructor.
     * 
     * @param player that own the capital.
     */
    public Level2Capital(final Optional<Player> player) {
        super(player, NAME, ENEMY_ATTACK_REDUCTION, POPULATION_BOOST,
                new BasicCostImpl(Optional.of(GOLD_COST), Optional.of(WOOD_COST), Optional.empty()));
    }
}
