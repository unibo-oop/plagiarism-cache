package it.unibo.biscia.core;

interface LevelLoader {
    enum WallType {
        WALL, AREA;
    }

    Level.LevelManaged getFirstLevel();

    Level.LevelManaged getLevel(int cardinal);

    Level.LevelManaged getNextLevel(Level level);
}
