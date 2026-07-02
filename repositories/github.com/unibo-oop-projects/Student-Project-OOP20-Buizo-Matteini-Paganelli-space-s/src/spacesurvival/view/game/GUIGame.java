package spacesurvival.view.game;

import spacesurvival.model.World;
import spacesurvival.model.gameobject.takeable.ammo.AmmoType;
import spacesurvival.utilities.LinkActionGUI;
import spacesurvival.view.GUI;
import spacesurvival.view.game.utilities.PanelEntityGame;
import spacesurvival.view.utilities.GraphicsText;

import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.KeyListener;
import java.util.List;

/**
 * Interface that implements the game functionalities.
 */
public interface GUIGame extends GUI, GraphicsText {

    /**
     * Set max life's ship a jprogressBar.
     * @param maxLife of spacheship.
     */
    void setMaxLifeShip(int maxLife);

    /**
     * Set max life's boss a jprogressBar.
     * @param maxBoss of boss.
     */
    void setMaxLifeBoss(int maxBoss);

    /**
     * Set bullet of HUD.
     * @param ammoType is path of bullet's HUD.
     */
    void setBulletHUD(AmmoType ammoType);

    /**
     * Set state of visible of JprogressBar's boss.
     * @param visible
     */
    void setVisibleLifeBarBoss(boolean visible);

    /**
     * Set text timer.
     * @param timer text.
     */
    void setTextTimer(String timer);

    /**
     * Set bounds from rectangle.
     * @param rectangle
     */
    void setBoundsGame(Rectangle rectangle);

    /**
     * Sets menu links to other GUIs.
     * @param mainAction is connection of the current GUI.
     * @param linksAction is connection of the current GUI.
     */
    void setBtnActions(LinkActionGUI mainAction, List<LinkActionGUI> linksAction);

    /**
     * Get panelEntity for draw gameObject.
     * @return JpanelEntity.
     */
    PanelEntityGame getPanelEntity();

    /**
     * Set name's player.
     * @param namePlayer is text name's player.
     */
    void setNamePlayer(String namePlayer);

    /**
     * Set world for game.
     * @param world is space of game.
     */
    void setWorld(World world);

    /**
     * Repaint all Game object.
     */
    void repaintGameObjects();

    /**
     * Add keyListener a JFrame.
     * @param keyListener is Keylistener.
     */
    void addKeyListenerSpaceShip(KeyListener keyListener);

    /**
     * Set text of score.
     * @param score is a value of score.
     */
    void setScore(long score);

    /**
     * Set text round.
     * @param round is a text of round.
     */
    void setRound(int round);

    /**
     * Set text of count enemies.
     * @param count is number of enemies.
     */
    void setNEnemies(int count);

    /**
     * Set n° heart of spaceship.
     * @param nHeart is n° heart's spaceship.
     */
    void setNHeart(int nHeart);

    /**
     * Set value of life's spaceship.
     * @param lifeShip is life of spaceship.
     */
    void setLifeShip(int lifeShip);

    /**
     * Set value of life's boss.
     * @param lifeBoss  is life of boss.
     */
    void setLifeBoss(int lifeBoss);

    /**
     * Set font JprogressBars.
     * @param font for all JProgressBar.
     */
    void setFontLifeBars(Font font);

    /**
     * Set color background of Jprogressbars's spaceship. 
     * @param color for progressBar.
     */
    void setBackgroundLifeBars(Color color);
}
