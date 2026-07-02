package snakerunner.model.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import snakerunner.commons.Point2D;
import snakerunner.model.Collectible;
import snakerunner.model.CollectibleType;
import snakerunner.model.Door;
import snakerunner.model.LevelData;
import snakerunner.model.VictoryCondition;

/**
 * The LevelLoader class is responsible for loading level data from a list of strings,
 * which can be read from a file.
 */
public final class LevelLoader {

    private LevelLoader() { 

    }

    /**
     * Loads level data from a list of strings from a file.
     *
     * @param lines the lines read from the level file.
     *
     * @return a LevelData object containing the obstacles and collectibles for the level.
     *
     * @throws IOException if there is an error parsing the level data.
     */
    public static LevelData load(final List<String> lines) throws IOException {

        final Set<Point2D<Integer, Integer>> obstacles = new HashSet<>();
        final List<Collectible> collectibles = new ArrayList<>();
        final List<Door> doors = new ArrayList<>();

        String section = null;

        for (final String raw : lines) {
            final String line = raw.trim();

            if (line.isEmpty() || line.startsWith("#")) {
                continue;
            }

            if ("[Obstacles]".equalsIgnoreCase(line)) {
                section = "obstacles";
                continue;
            }

            if ("[Collectibles]".equalsIgnoreCase(line)) {
                section = "collectibles";
                continue;
            }

            if ("[Doors]".equalsIgnoreCase(line)) { //TEMPORARY FIX, USE TO TEST gameBoardPanel drawDoors()
                section = "doors";
                continue;
            }

            if (section == null) {
                continue;
            }

            final String[] parts = line.split(",");
            final int x = Integer.parseInt(parts[0].trim());
            final int y = Integer.parseInt(parts[1].trim());

            final Point2D<Integer, Integer> p = new Point2D<>(x, y);

            switch (section) {
                case "obstacles" -> obstacles.add(p);
                case "collectibles" -> {
                    final String type = parts[2].trim().toUpperCase(Locale.ROOT);

                    switch (type) {
                        case "FOOD" -> collectibles.add(new FoodImpl(p));

                        case "CLOCK" -> collectibles.add(new Clock(p));

                        case "KEY" -> collectibles.add(new Key(p));

                        case "LIFE_BOOST" -> collectibles.add(new LifeBoost(p));

                        case "FLAG" -> collectibles.add(new Flag(p));

                        case "BOMB" -> collectibles.add(new Bomb(p));

                        default -> throw new IOException("Unknown collectible type: " + type);
                    }
                }
                case "doors" -> //TEMPORARY FIX, USE TO TEST gameBoardPanel drawDoors()
                    doors.add(new Door(x, y));
                default -> {
                }
            }
        }

        VictoryCondition victoryCondition = VictoryCondition.COLLECT_ALL_FOOD;
        for (final Collectible c : collectibles) {
            if (c.getType() == CollectibleType.FLAG) {
                victoryCondition = VictoryCondition.COLLECT_FLAG;
                break;
            }
        }
        return new LevelDataImpl(obstacles, collectibles, doors, victoryCondition);
    }
}
