package it.unibo.shoot.model;

import it.unibo.shoot.loader.SpriteSheet;

public class Enemy1 extends Enemy{

    /**
     * 
     * @param x             coordinata X del mondo di gioco
     * @param y             coordinata Y del mondo di gioco
     * @param id            identificatore del tipo di oggetto
     * @param ss            lo {@link SpriteSheet} con gli sprite del nemico
     * @param handler       l' {@link Handler} che gestisce gli oggetti
     * @param levelManager  il {@link LevelManager} che assegna XP al giocatore
     */
    public Enemy1(int x, int y, ID id, SpriteSheet ss, Handler handler, LevelManager levelManager) {
        super(x, y, id, ss, handler, 3.0f, levelManager);
        this.hp = 100;
        }
}

//il nemico "base" con vita impostata a 100 e danni e xp base presenti in Enemy.java
//danno = 10 = xp
