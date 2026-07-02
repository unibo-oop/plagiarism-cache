package models;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * RandomSpawnStrategy is a class that implements SpawnStrategy and its contracts.
 * It generates each new position using random values, which are all contained inside the boundaries
 * of the game map
 */
public class RandomSpawnStrategy implements SpawnStrategy {

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<Point2D> getSpawnPoints(final int width, final int height, final int numPoints) {
        Random r = new Random();
        Set<Point2D> spawnPoints = new HashSet<>();
        while (spawnPoints.size() < numPoints) {
            Point2D newPos = new Point2D(r.nextInt(width), r.nextInt(height));
            if (!newPos.equals(new Point2D(width / 2, height / 2))) {
                spawnPoints.add(newPos);
            }
        }
        return spawnPoints;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<Point2D> getDoubleSpawnPoints(final int width, final int height, final Set<Point2D> points1, final Set<Point2D> points2) {
        Set<Point2D> allPoints = new HashSet<>();
        allPoints.addAll(points1);
        allPoints.addAll(points2);
        while (allPoints.size() - (points1.size() + points2.size()) != 0) {
            allPoints.addAll(getSpawnPoints(width, height, (points1.size() + points2.size()) - allPoints.size()));
        }
        return allPoints;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean checkNumPoints(final int boardDimension, final int numPoints) {
        return boardDimension > numPoints;
    }

}
