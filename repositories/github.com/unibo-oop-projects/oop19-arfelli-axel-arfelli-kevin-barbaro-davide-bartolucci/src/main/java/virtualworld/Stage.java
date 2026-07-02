package virtualworld;

import entity.EntitySpawner;
import entity.PlayerShip;
import entity.UUIDActor;
import entity.UUIDProjectile;

public interface Stage extends Runnable {

    void start();
    void stop();
    boolean isEnded();

    void pause();
    void resume();
    boolean isPaused();

    boolean isReady();
    Wave getWave();
    VirtualMap<UUIDActor, UUIDProjectile> getMap();
    PlayerShip getplayer();
    int getScore();
    
    void setSpawner(EntitySpawner spawner);
    void setlock(Object lock);
}
