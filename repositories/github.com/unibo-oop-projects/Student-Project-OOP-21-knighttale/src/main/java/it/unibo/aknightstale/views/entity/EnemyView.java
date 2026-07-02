package it.unibo.aknightstale.views.entity;

public class EnemyView extends CharacterView {

    private static final double WIDTH = 20;
    private static final double HEIGHT = 24;
    private static final String NAME = "enemy";

    public EnemyView() {
        super(Status.WALK, NAME + "/enemy_idle_right.png", WIDTH, HEIGHT, NAME + '/' + NAME);

        setIdleRight(NAME + "/enemy_idle_right.png");
        setIdleLeft(NAME + "/enemy_idle_left.png");
        setIdleUp(NAME + "/enemy_idle_up.png");
        setIdleDown(NAME + "/enemy_idle_down.png");
        setWalkRight(NAME + "/enemy_walk_right.png");
        setWalkLeft(NAME + "/enemy_walk_left.png");
        setWalkUp(NAME + "/enemy_walk_up.png");
        setWalkDown(NAME + "/enemy_walk_down.png");
    }
}
