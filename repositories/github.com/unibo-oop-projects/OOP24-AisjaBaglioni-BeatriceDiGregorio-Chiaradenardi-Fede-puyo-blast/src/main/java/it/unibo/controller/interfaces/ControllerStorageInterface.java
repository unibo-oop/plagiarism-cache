package it.unibo.controller.interfaces;

import it.unibo.view.GameView;

public interface ControllerStorageInterface {
    void addTickListeners();
    void linkGameView(GameView gameView);
    void start();
}
