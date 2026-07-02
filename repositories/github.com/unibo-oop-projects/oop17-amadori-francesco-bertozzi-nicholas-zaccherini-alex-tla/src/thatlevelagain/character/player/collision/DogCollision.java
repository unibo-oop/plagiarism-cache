package thatlevelagain.character.player.collision;

import thatlevelagain.character.player.Player;
import thatlevelagain.view.sprite.Dog;

/**
 * 
 */
public final class DogCollision {



    private DogCollision() {
    }


    /**
     * 
     * @param player
     *          Object of class Player.
     * @param dog
     *          Object of class Dog.
     */
    public static void intersection(final Player player, final Dog dog) {
        if (player.getBounds().intersects(dog.getBounds())) {
            player.restartLevel();
        }
    }

}
