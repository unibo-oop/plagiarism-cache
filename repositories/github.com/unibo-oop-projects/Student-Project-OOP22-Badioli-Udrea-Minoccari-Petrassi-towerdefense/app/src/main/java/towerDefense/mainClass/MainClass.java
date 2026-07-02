package towerDefense.mainClass;

import towerDefense.game.impl.GameImpl;
import towerDefense.gameLogic.impl.GameLoop;

public class MainClass {
    public static void main(String[] args) {
        GameImpl game = new GameImpl();
        new GameLoop(game);
    }
}
