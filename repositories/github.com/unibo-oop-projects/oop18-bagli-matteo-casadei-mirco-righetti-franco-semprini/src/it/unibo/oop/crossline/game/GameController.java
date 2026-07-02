package it.unibo.oop.crossline.game;

import com.badlogic.gdx.Screen;

public interface GameController extends Screen {

    GameView getView();

    GameModel getModel();

}
