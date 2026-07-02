package model.objects.structures;

import java.util.Optional;

import model.BasicCostImpl;
import model.player.Player;

/**
 * The Level3Capital class extends CapitalImpl and represent the third level of a Capital. This level has an unlock cost.
 */
public class Level3Capital extends CapitalImpl {

    private static final double ENEMY_ATTACK_REDUCTION = 0.50;
    private static final int POPULATION_BOOST = 15;
    private static final String NAME = "Capital level 3";
    private static final int GOLD_COST = 1000;
    private static final int WOOD_COST = 1000;

    /**
     * Level3Capital constructor.
     * 
     * @param player that own the capital.
     */
    public Level3Capital(final Optional<Player> player) {
        super(player, NAME, ENEMY_ATTACK_REDUCTION, POPULATION_BOOST,
                new BasicCostImpl(Optional.of(GOLD_COST), Optional.of(WOOD_COST), Optional.empty()));
    }
}
