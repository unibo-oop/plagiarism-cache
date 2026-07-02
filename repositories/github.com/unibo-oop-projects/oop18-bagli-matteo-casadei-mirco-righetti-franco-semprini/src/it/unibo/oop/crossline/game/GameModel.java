package it.unibo.oop.crossline.game;

import java.util.Observer;
import java.util.Set;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.physics.box2d.World;
import it.unibo.oop.crossline.game.actor.player.Player;
import it.unibo.oop.crossline.game.actor.robot.Robot;
import it.unibo.oop.crossline.game.wave.Wave;

public interface GameModel {

    void addObserver(Observer observer);

    World getWorld();

    TiledMap getTiledMap();

    Wave getCurrentWave();

    Player getPlayer();

    void update();

    boolean shouldExit();

}
