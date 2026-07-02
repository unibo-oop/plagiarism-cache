package thatlevelagain.character.enemies;

import thatlevelagain.view.sprite.Mattoni;

/**
 * Class used to verify if an Enemy is situated in the Edge of an Object of the class Mattoni.
 *
 * @author 
 *
 */
public final class CheckerEdge {

    private static final CheckerEdge SINGLETON = new CheckerEdge();

    /**
     * CheckerEdge constructor.
     * @param enemy 
     *          Object of a class that implements EnemyInterface.
     */
    private CheckerEdge() {
        //NOT USED
    }

    /**
     * 
     * @return
     *          the CheckerEdge
     */
    public static CheckerEdge getChecker() {
        return SINGLETON;
    }

    /**
     * @param enemy 
     *          Object of a class that implements EnemyInterface
     * @return 
     *          true if the enemy is in the edge of an Object of class Mattonis
     */
    public boolean check(final EnemyInterface enemy) {
        boolean gTouch = true;
        boolean rTouch = true;
        for (final Mattoni m : enemy.getMap().getMattoni()) {
            if (enemy.getRect().intersects(m.getRectUp())) {
                rTouch = true; 
                break;
            }
            rTouch = false;
        }
        for (final Mattoni m : enemy.getMap().getMattoni()) {
            if (enemy.getRectBottom().intersects(m.getRectUp())) {
                gTouch = true; 
                break;
            }
            gTouch = false;
        }
        return (!rTouch) && gTouch;
    }
}
