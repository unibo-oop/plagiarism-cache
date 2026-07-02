package it.dpg.minigames.jumpgame.model;

/**
 * Class that models a platform for the player to jump on
 * @author Davide Picchiotti
 * */

public class Platform extends AbstractGameObject {

    private final int id;
    private boolean exist = true;

    public Platform(final int x, final int y, final int width, final int height, final int id) {
        super(x, y, width, height);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public boolean doesExist() {
        return exist;
    }

    public void destroy() {
        exist = false;
    }
}
