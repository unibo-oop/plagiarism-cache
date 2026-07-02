package it.dpg.minigames.jumpgame.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Basic implementation of PlatformSpawner
 * @author Davide Picchiotti
 * @see PlatformSpawner
 * */

public class PlatformSpawnerImpl implements PlatformSpawner {

    private List<Platform> platforms;
    private Random random = new Random();

    private long lastTimeSpawn = 0;
    private int id = 1;

    private final int worldWidth;
    private final int platformWidth;
    private final int platformHeight;

    public PlatformSpawnerImpl(final int worldWidth, final int platformWidth, final int platformHeight) {
        this.worldWidth = worldWidth;
        this.platformHeight = platformHeight;
        this.platformWidth = platformWidth;

        platforms = new ArrayList<>();
        platforms.add(new Platform(worldWidth/2 - 3*(worldWidth/24),8*(worldWidth/24), platformWidth, platformHeight, 0));
    }

    @Override
    public List<Platform> getPlatforms() {
        return platforms;
    }

    @Override
    public void updatePlatformsGeneration() {
        long currentTime = System.currentTimeMillis();

        platforms = platforms.stream().filter(Platform::doesExist).collect(Collectors.toList());

        if(platforms.size() >= 3) {
            platforms.stream().filter(Platform::doesExist).collect(Collectors.toList()).get(0).destroy();
        }

        if(currentTime - lastTimeSpawn > 1000) {
            platforms.add(
                    new Platform(
                            random.nextInt(worldWidth - platformWidth),
                            platforms.get(platforms.size()-1).getPosition().getRight() + 200,
                            platformWidth,
                            platformHeight,
                            id
                    )
            );

            id++;

            lastTimeSpawn = currentTime;
        }

    }
}
