package bzzbomber.model;

import bzzbomber.model.entities.BzzBomber;

/**
 * avert the Hud that it must changed his value and his field.
 */
public interface EventListener {

    /**
     * it alerts that the number of seconds is changed.
     * 
     * @param ev
     *            a TimerEvent that knows the value of field second.
     */
    void counterChanged(TimerEvent ev);

    /**
     * it alerts that the number of monster is changed.
     * 
     * @param hero
     *            the hero of the play who knows the number of monster that he
     *            killed.
     */
    void monsterChanged(BzzBomber hero);

    /**
     * it alerts that the number of life of the hero is changed.
     * 
     * @param hero
     *            the hero of the play who knows his number of his life.
     */
    void lifeChanged(BzzBomber hero);

}
