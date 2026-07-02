package spacesurvival.view.game.utilities.commandlife;

import java.awt.Graphics2D;

import spacesurvival.model.gameobject.fireable.Boss;
import spacesurvival.model.gameobject.fireable.FireEnemy;
import spacesurvival.model.gameobject.main.Asteroid;
import spacesurvival.model.gameobject.main.ChaseEnemy;
import spacesurvival.model.gameobject.main.MainObject;
import spacesurvival.utilities.gameobject.LifeUtils;
import spacesurvival.view.game.utilities.PanelEntityGame;
import spacesurvival.view.game.utilities.commandlife.command.LifeAsteroid;
import spacesurvival.view.game.utilities.commandlife.command.LifeBoss;
import spacesurvival.view.game.utilities.commandlife.command.LifeChase;
import spacesurvival.view.game.utilities.commandlife.command.LifeFireEnemy;
import spacesurvival.view.game.utilities.logiccolor.LogicColor;

/**
 * Implement a command caller to get gameObject life and to draw.
 */
public class CallerLife {
    private final LogicColor logicColor;

    private final CommandLife commandBoss;
    private final CommandLife commandChase;
    private final CommandLife commandFire;
    private final CommandLife commandAsteroid;

    /**
     * Initialize the commands to get size life of gameObject and set logic for to draw color life.
     * @param logicColor
     */
    public CallerLife(final LogicColor logicColor) {
        this.logicColor = logicColor;

        this.commandBoss = new LifeBoss();
        this.commandChase = new LifeChase();
        this.commandFire = new LifeFireEnemy();
        this.commandAsteroid = new LifeAsteroid();
    }

    /**
     * 
     * @param gameObject
     * @param g2d
     */
    public void executeDrawLife(final MainObject gameObject, final Graphics2D g2d) {
        if (gameObject instanceof ChaseEnemy) {
            g2d.setColor(this.logicColor.getColor(LifeUtils.CHASE_ENEMY_LIFE, gameObject.getLife()));
            g2d.fillRect(PanelEntityGame.ANCHOR_X_LIFE_BAR, (int) gameObject.getSize().getHeight() + PanelEntityGame.DIFFERENCE_HEIGHT_LIFE_BAR,
                    this.commandChase.execute(gameObject), PanelEntityGame.HEIGHT_LIFE);

        } else if (gameObject instanceof Asteroid) {
           g2d.setColor(this.logicColor.getColor(LifeUtils.ASTEROID_LIFE, gameObject.getLife()));
            g2d.fillRect(PanelEntityGame.ANCHOR_X_LIFE_BAR, (int) gameObject.getSize().getHeight() + PanelEntityGame.DIFFERENCE_HEIGHT_LIFE_BAR,
                    this.commandAsteroid.execute(gameObject), PanelEntityGame.HEIGHT_LIFE);

        } else if (gameObject instanceof Boss) {
           g2d.setColor(this.logicColor.getColor(LifeUtils.BOSS_LIFE, gameObject.getLife()));
            g2d.fillRect(PanelEntityGame.ANCHOR_X_LIFE_BAR, (int) gameObject.getSize().getHeight() + PanelEntityGame.DIFFERENCE_HEIGHT_LIFE_BAR,
                    this.commandBoss.execute(gameObject), PanelEntityGame.HEIGHT_LIFE);

        } else if (gameObject instanceof FireEnemy) {
           g2d.setColor(this.logicColor.getColor(LifeUtils.FIRE_ENEMY_LIFE, gameObject.getLife()));
            g2d.fillRect(PanelEntityGame.ANCHOR_X_LIFE_BAR, (int) gameObject.getSize().getHeight() + PanelEntityGame.DIFFERENCE_HEIGHT_LIFE_BAR,
                    this.commandFire.execute(gameObject), PanelEntityGame.HEIGHT_LIFE);
        }
    }
}
