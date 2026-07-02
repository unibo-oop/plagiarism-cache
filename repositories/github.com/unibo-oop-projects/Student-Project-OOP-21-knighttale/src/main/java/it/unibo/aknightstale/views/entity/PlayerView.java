package it.unibo.aknightstale.views.entity;

public class PlayerView extends CharacterView {

    private static final double WIDTH = 24;
    private static final double HEIGHT = 32;

    private static final String NAME = "player";

    public PlayerView() {
        super(Status.IDLE, NAME + "/player_idle_right.png", WIDTH, HEIGHT, NAME + '/' + NAME);

        setIdleRight(NAME + "/player_idle_right.png");
        setIdleLeft(NAME + "/player_idle_left.png");
        setIdleUp(NAME + "/player_idle_up.png");
        setIdleDown(NAME + "/player_idle_down.png");
        setWalkRight(NAME + "/player_walk_right.png");
        setWalkLeft(NAME + "/player_walk_left.png");
        setWalkUp(NAME + "/player_walk_up.png");
        setWalkDown(NAME + "/player_walk_down.png");
    }

}
