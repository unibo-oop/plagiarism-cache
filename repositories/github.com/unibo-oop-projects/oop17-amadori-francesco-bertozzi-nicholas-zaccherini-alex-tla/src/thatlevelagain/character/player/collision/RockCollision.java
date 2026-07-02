package thatlevelagain.character.player.collision;

import java.util.List;

import thatlevelagain.character.player.Player;
import thatlevelagain.view.sprite.Rock;

/**
 * 
 */
public final class RockCollision {


    private RockCollision() {
    }

    /**
     * 
     * @param player
     *          Object of class Player.
     * @param rockList
     *          Objecy of class Rock.
     */
    public static void intersection(final Player player, final List<Rock> rockList) {
        rockList.forEach(rock -> {
            if (player.getRectRight().intersects(rock.getRectLeft())
                    || player.getRectLeft().intersects(rock.getRectRight())
                        || player.getRectUp().intersects(rock.getRectBottom())
                            || player.getRectBottom().intersects(rock.getRectUp())) {
                player.restartLevel();
            }
            });
    }

}
