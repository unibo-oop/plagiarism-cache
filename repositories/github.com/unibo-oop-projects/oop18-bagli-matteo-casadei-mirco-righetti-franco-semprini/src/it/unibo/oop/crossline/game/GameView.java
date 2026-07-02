package it.unibo.oop.crossline.game;

import java.util.Set;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.physics.box2d.World;

import it.unibo.oop.crossline.game.actor.player.Player;
import it.unibo.oop.crossline.game.actor.robot.Robot;
import it.unibo.oop.crossline.game.camera.Camera;
import it.unibo.oop.crossline.game.wave.Wave;

public interface GameView {

    Camera getCamera();

    void setTiledMap(TiledMap tiledMap);

    void setWorld(World world);

    void render(float delta);

    void setPlayer(Player player);

    void setEnemy(Wave wave);

    void dispose();

}
