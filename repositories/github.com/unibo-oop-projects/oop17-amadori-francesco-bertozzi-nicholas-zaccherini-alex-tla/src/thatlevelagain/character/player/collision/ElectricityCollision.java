package thatlevelagain.character.player.collision;

import java.util.List;

import thatlevelagain.character.player.Player;
import thatlevelagain.view.sprite.Scossa;

/**
 * 
 */
public final class ElectricityCollision {


    private ElectricityCollision() {
    }

    /**
     * 
     * @param player
     *          Object of class Player.
     * @param listElectricity
     *          Object of calss Scossa.
     */
    public static void intersection(final Player player, final List<Scossa> listElectricity) {
        listElectricity.forEach(shock -> {
            if (player.getBounds().intersects(shock.getBounds())) {
                player.restartLevel();
            }
        });
    }

}
