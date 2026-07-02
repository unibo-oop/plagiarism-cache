package it.unibo.shoot.model;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

import it.unibo.shoot.GameObjects.Crate;
import it.unibo.shoot.loader.SpriteSheet;
/**
 * Il boss, ha statistiche più elevate ed è facilmente visibile nella mappa, lascia un {@link Crate} alla morte
 */
public class Boss extends Enemy {

    private static final int BOSSWH = 64;
    private static final int BOSS_BORDER = 4;
    private static final int BOSS_BIG_BOUNDS = BOSSWH + BOSS_BORDER * 2;
    private final Random r = new Random();
    private static final int SKIN_COUNT = 3;
    private final BossSpawner bossSpawner;
    private final BufferedImage crateImage;

    /**
     * 
     * @param x                 coordinata X del mondo di gioco
     * @param y                 coordinata Y del mondo di gioco
     * @param id                identificatore del tipo di oggetto
     * @param ss                lo {@link SpriteSheet} con gli sprite del nemico
     * @param handler           l' {@link Handler} che gestisce gli oggetti
     * @param levelManager      il {@link LevelManager} che assegna XP al giocatore
     * @param crateImage        la {@link BufferedImage} della cassa che spawna quando il boss viene ucciso
     * @param bossSpawner       il {@link BossSpawner} che viene notificato quando il boss muore
     */
    public Boss(int x, int y, ID id, SpriteSheet ss, Handler handler, LevelManager levelManager, BufferedImage crateImage, BossSpawner bossSpawner) {
        super(x, y, id, ss, handler, 1.5f, levelManager);
        this.COL_OFFSET = r.nextInt(SKIN_COUNT) * 3;
        this.hp = 300;
        this.damage = 40;
        this.xpValue = 100;
        this.renderSize = BOSSWH;
        this.crateImage = crateImage;
        this.bossSpawner = bossSpawner;
    }

    @Override
    protected void bossDeath() {
        handler.addObject(new Crate(x, y, ID.Crate, crateImage));
        bossSpawner.onBossDeath();
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, BOSSWH, BOSSWH);
    }

    @Override
    public Rectangle getBoundsBig() {
        return new Rectangle(x - BOSS_BORDER, y - BOSS_BORDER, BOSS_BIG_BOUNDS, BOSS_BIG_BOUNDS);
    }
}