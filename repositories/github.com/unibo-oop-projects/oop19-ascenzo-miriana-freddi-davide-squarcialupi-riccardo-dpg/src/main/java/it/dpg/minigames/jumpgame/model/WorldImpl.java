package it.dpg.minigames.jumpgame.model;

import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.List;

/**
 * Basic implementation of world
 * @author Davide Picchiotti
 * */

public class WorldImpl implements World {
    private static final int HEIGHT = 900;
    private static final int WIDTH = 600;
    private static final int UNIT = WIDTH/24;

    private static final int PLAYER_SIZE = WIDTH/6;
    private static final int PLATFORM_WIDTH = WIDTH/4;
    private static final int PLATFORM_HEIGHT = UNIT;

    private static final int GRAVITY = 1;

    private int score = 0;
    private Player player;
    private PlatformSpawner spawner;
    private boolean gameOver;

    public WorldImpl() {
        gameOver = false;
        player = new Player(PLAYER_SIZE, WIDTH/2 - 2*UNIT, PLAYER_SIZE, GRAVITY, 20);
        spawner = new PlatformSpawnerImpl(WIDTH, PLATFORM_WIDTH, PLATFORM_HEIGHT);
    }

    @Override
    public void update() {
        player.updatePosition();

        if(player.getPosition().getRight() >= HEIGHT/3 && player.getSpeedY() > 0) {
            spawner.getPlatforms().forEach(p -> {
                p.setSpeedY(-player.getSpeedY());
                p.updatePosition();
            });
        }
        spawner.getPlatforms().forEach(this::checkCollisionWithPlatform);

        spawner.updatePlatformsGeneration();

        if(player.getPosition().getRight() <= 0 || player.getPosition().getLeft() <= 0 || player.getPosition().getLeft() + player.getWidth() >= WIDTH) {
            gameOver = true;
        }
    }

    @Override
    public boolean isGameOver() {
        return gameOver;
    }

    @Override
    public Pair<Integer, Integer> getPlayerPosition() {
        return player.getPosition();
    }

    @Override
    public int getPlayerSize() {
        return player.getWidth();
    }

    @Override
    public List<Platform> getPlatforms() {
        return new ArrayList<>(spawner.getPlatforms());
    }

    @Override
    public void setPlayerSpeedX(final int speedX) {
        player.setSpeedX(speedX);
    }

    @Override
    public int getScore() {
        return score;
    }

    @Override
    public int getWidth() {
        return WIDTH;
    }

    @Override
    public int getHeight() {
        return HEIGHT;
    }

    private void checkCollisionWithPlatform(final Platform p) {
        if(p.doesExist()) {
            int playerLeftSide = player.getPosition().getLeft();
            int playerRightSide = player.getPosition().getLeft() + player.getWidth();
            int playerBottomSide = player.getPosition().getRight() - player.getHeight();

            int platformLeftSide = p.getPosition().getLeft();
            int platformRightSide = p.getPosition().getLeft() + p.getWidth();
            int platformTopSide = p.getPosition().getRight();
            int platformBottomSide = p.getPosition().getRight() - p.getHeight();

            if (player.getSpeedY() < 0 &&
                    playerRightSide > platformLeftSide && playerLeftSide < platformRightSide &&
                    playerBottomSide <= platformTopSide && playerBottomSide >= platformBottomSide
            ) {
                player.setSpeedY(18);
                score++;
            }
        }
    }
}
