package spacesurvival.view.game.concrete;

import spacesurvival.model.World;
import spacesurvival.model.gameobject.takeable.ammo.AmmoType;
import spacesurvival.utilities.LinkActionGUI;
import spacesurvival.view.AbstractGUI;
import spacesurvival.view.game.GUIGame;
import spacesurvival.view.game.utilities.BtnPauseLinkAction;
import spacesurvival.view.game.utilities.Bullet;
import spacesurvival.view.game.utilities.CounterEnemies;
import spacesurvival.view.game.utilities.Heart;
import spacesurvival.view.game.utilities.LifeBar;
import spacesurvival.view.game.utilities.PanelBulletGame;
import spacesurvival.view.game.utilities.PanelEntityGame;
import spacesurvival.view.game.utilities.RoundTimer;
import spacesurvival.view.game.utilities.Score;
import spacesurvival.view.game.utilities.logiccolor.LogicColorBoss;
import spacesurvival.view.game.utilities.logiccolor.LogicColorShip;
import spacesurvival.view.utilities.ButtonLink;

import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.KeyListener;
import java.util.List;

/**
 * Implement every element the game GUI must have.
 */
public class ConcreteGameGUI extends AbstractGUI implements GUIGame {
    private static final long serialVersionUID = 1L;

    private final Score score;
    private final RoundTimer roundTimer;
    private final BtnPauseLinkAction btnPause;
    private final CounterEnemies counterEnemies;

    private final Heart heartLife;
    private final LifeBar lifeShip;
    private final LifeBar lifeBoss;
    private final Bullet bullet;

    private final PanelEntityGame entityPanel;
    private final PanelBulletGame bulletPanel;

    /**
     * Constructor of all game GUI items.
     */
    public ConcreteGameGUI() {
        super();
        this.entityPanel = new PanelEntityGame();
        this.bulletPanel = new PanelBulletGame();
        this.lifeBoss = new LifeBar(new LogicColorBoss());
        this.lifeShip = new LifeBar(new LogicColorShip());
        this.score = new Score();
        this.heartLife = new Heart();
        this.bullet = new Bullet();
        this.roundTimer = new RoundTimer();
        this.btnPause = new BtnPauseLinkAction();
        this.counterEnemies = new CounterEnemies();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void setBoundsGame(final Rectangle screen) {
        super.setBounds(screen);
        this.entityPanel.setBounds(screen);
        this.bulletPanel.setBounds(screen);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public final List<ButtonLink> getBtnActionLinks() {
        return List.of(this.btnPause);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void setTextTimer(final String timer) {
        this.roundTimer.setTimer(timer);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void setBtnActions(final LinkActionGUI mainAction, final List<LinkActionGUI> linksID) {
        for (final LinkActionGUI linkActionGUI : linksID) {
            this.btnPause.setCurrentLink(mainAction);
            this.btnPause.setNextLink(linkActionGUI);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final PanelEntityGame getPanelEntity() {
        return this.entityPanel;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void setWorld(final World world) {
        this.entityPanel.setWorld(world);
        this.bulletPanel.setWorld(world);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void addKeyListenerSpaceShip(final KeyListener keyListener) {
        this.addKeyListener(keyListener);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void repaintGameObjects() {
        this.entityPanel.repaint();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setVisibleLifeBarBoss(final boolean visible) {
        this.lifeBoss.setVisible(visible); 
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void setScore(final long score) {
        this.score.setScore(score);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void setRound(final int round) {
        this.roundTimer.setRound(round);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void setNEnemies(final int count) {
        this.counterEnemies.setCounter(count);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void setNHeart(final int nHeart) {
        this.heartLife.setnHeart(nHeart);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void setLifeShip(final int lifeShip) {
        this.lifeShip.setLife(lifeShip);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void setLifeBoss(final int lifeShip) {
        this.lifeBoss.setLife(lifeShip);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setFontGUI(final Font font) {
        this.score.setFontAll(font);
        this.roundTimer.setFontAll(font);
        this.counterEnemies.setFontAll(font);
        this.heartLife.setFontAll(font);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setFontLifeBars(final Font font) {
        this.lifeShip.setFont(font);
        this.lifeBoss.setFont(font);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setForegroundGUI(final Color color) {
        this.score.setForegroundAll(color);
        this.roundTimer.setForegroundAll(color);
        this.counterEnemies.setForegroundAll(color);
        this.heartLife.setForegroundAll(color);
        this.lifeShip.setForeground(color);
        this.lifeBoss.setForeground(color);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setBackgroundLifeBars(final Color color) {
        this.lifeShip.setBackground(color);
        this.lifeBoss.setBackground(color);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setNamePlayer(final String namePlayer) {
        this.score.setNamePlayer(namePlayer);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setMaxLifeShip(final int maxLife) {
        this.lifeShip.setMaximum(maxLife);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setMaxLifeBoss(final int maxLife) {
        this.lifeBoss.setMaximum(maxLife);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setBulletHUD(final AmmoType ammoType) {
        this.bullet.setAmmoTypeImage(ammoType.getImagePath());
        this.bullet.setBulletImage(ammoType.getBulletHud());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setFontTitleGUI(final Font font) {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setTitleGUI(final String title) {
    }

    /**
     * Get LifeBar of spaceShip.
     * @return LifeBar.
     */
    public LifeBar getLifeBoss() {
        return this.lifeBoss;
    }

    /**
     * Get LifeBar of boss.
     * @return LifeBar.
     */
    public LifeBar getLifeShip() {
        return this.lifeShip;
    }

    /**
     * Get score component.
     * @return Score.
     */
    public Score getScore() {
        return this.score;
    }

    /**
     * Get Heart of spaceship.
     * @return Heart of spaceship.
     */
    public Heart getHeartLife() {
        return this.heartLife;
    }

    /**
     * Get Bullet of spaceship for HUD.
     * @return Bullet of spaceship for HUD..
     */
    public Bullet getBullet() {
        return this.bullet;
    }

    /**
     * Get RoundTimer for HUD.
     * @return RoundTimer for HUD.
     */
    public RoundTimer getRoundTimer() {
        return this.roundTimer;
    }

    /**
     * Get CounterEnemies for HUD.
     * @return CounterEnemies for HUD.
     */
    public CounterEnemies getCounterEnemies() {
        return this.counterEnemies;
    }

    /**
     * Get BtnPauseID of pause.
     * @return BtnPauseLinkAction for pause.
     */
    public BtnPauseLinkAction getBtnPause() {
        return btnPause;
    }
}
