package it.unibo.shoot.model;
import java.awt.image.BufferedImage;
import java.util.Random;

import it.unibo.shoot.loader.SpriteSheet;
import it.unibo.shoot.util.Constants;

/**
 * Genera periodicamente un nemico tra i 3 casuali in una posizione dove non è presente un muro
 */
public class Spawner {

    private final Handler handler;
    private final SpriteSheet ss;
    private final Random r = new Random();
    private int timer = 0;
    private static final int SPAWN_TIMER = 100;
    private final BufferedImage map;
    private final LevelManager levelManager;

    /**
     * 
     * @param handler           l' {@link Handler} che gestisce gli oggetti
     * @param ss                lo {@link SpriteSheet} con gli sprite del nemico
     * @param map               la {@link BufferedImage} della mappa
     * @param levelManager      il {@link LevelManager} associato ai nemici generati
     */
    public Spawner(Handler handler, SpriteSheet ss, BufferedImage map, LevelManager levelManager){
        this.handler = handler;
        this.ss = ss;
        this.map = map;
        this.levelManager = levelManager;
    }

    /**
     * Aggiorna il timer e quando genera un nemico si resetta
     */
    public void tick() {
        timer++;
        if(timer >= SPAWN_TIMER){                                      //quando il timer raggiunge il tempo si azzera e fa spawnare un nemico
            timer = 0;
            spawnEnemy();
        }
    }

    /**
     * Cerca un punto per generare il nemico
     * @return array con le coordinate [x, y] dove viene generato il nemico, null altrimenti
     */
    private int[] findSpawn() {
        int x, y;
        int maxAttempts = 30;
        do {
            x = r.nextInt(map.getWidth()) * Constants.TILE_SIZE;                     //fa spawnare un nemico a caso nella mappa, se il punto di spawn non ? sopra un muro
            y = r.nextInt(map.getHeight()) * Constants.TILE_SIZE;
            maxAttempts--;
        } while (isWall(x,y) && maxAttempts > 0);
        return isWall(x, y) ? null : new int[]{x, y};
    }

    /**
     * Genera uno tra {@link Enemy1}, {@link Enemy2} e {@link Enemy3} casualmente nella posizione di {@link #findSpawn()}.
     */
    private void spawnEnemy() {
        int[] pos = findSpawn();
        if (pos == null){
            return;
        }
        int x = pos [0];
        int y = pos [1];

        Enemy[] enemies = {
            new Enemy1(x, y, ID.Enemy, ss, handler, levelManager),
            new Enemy2(x, y, ID.Enemy, ss, handler, levelManager),
            new Enemy3(x, y, ID.Enemy, ss, handler, levelManager)
        };
        handler.addObject(enemies[r.nextInt(3)]);
    }

    /**
     * Controllo se il tile è sopra o vicino a un muro, in modo da non avere una generazione sopra al muro
     * @param x
     * @param y
     * @return
     */
    private boolean isWall(int x, int y){                                                       //serve per lo spawn dei nemici, controlla se il tile ? rosso = muro
        int mapX = x / Constants.TILE_SIZE;
        int mapY = y / Constants.TILE_SIZE;

        for(int dX = -1; dX <= 1; dX++) {
            for(int dY = -1; dY <= 1; dY++){
                int checkX = mapX + dX;
                int checkY = mapY + dY;

                if (checkX < 0 || checkY < 0 || checkX >= map.getWidth() || checkY >= map.getHeight()) {
                    return true;
                }

                int pixel = map.getRGB(checkX, checkY);
                int red   = (pixel >> 16) & 0xff;
                if(red == 255){
                    return true;
                }
            }
        }
        return false;
    }

}