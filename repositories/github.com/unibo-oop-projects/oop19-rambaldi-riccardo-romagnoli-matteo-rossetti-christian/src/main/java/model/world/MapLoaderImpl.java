package model.world;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import model.obstacle.CircularObstacle;
import model.obstacle.Obstacle;
import model.obstacle.ObstacleBehavior;
import model.obstacle.RectangularObstacle;

import org.apache.commons.lang3.tuple.Pair;

public class MapLoaderImpl implements MapLoader {

    private static final int NUM_GREEN_OBSTACLES = 1;
    private static final int NUM_PURPLE_OBSTACLES = 1;

    @Override
    public final List<Obstacle> loadMap(final String mapChosen) {
        final List<Obstacle> obstaclesList = new LinkedList<>();
        final List<Obstacle> readyObstaclesList = new LinkedList<>();
        final InputStream in = getClass().getResourceAsStream("/maps/" + mapChosen);

        try (BufferedReader r = new BufferedReader(new InputStreamReader(in))) {
            String line = null;
            while ((line = r.readLine()) != null) {
                final String[] obstacleParameters = line.split(",");
                final Double posX = Double.parseDouble(obstacleParameters[1]);
                final Double posY = Double.parseDouble(obstacleParameters[2]);

                if (obstacleParameters[0].equals("C")) {
                    obstaclesList.add(new CircularObstacle(Pair.of(posX, posY)));
                } else {
                    final Double angle = Double.parseDouble(obstacleParameters[3]);
                    obstaclesList.add(new RectangularObstacle(Pair.of(posX, posY), angle));
                }
            }

            in.close();
            this.setObstaclesBehaviour(obstaclesList, readyObstaclesList);
        } catch (IOException exc) {
            exc.printStackTrace();
        }
        return readyObstaclesList;
    }

    private void setObstaclesBehaviour(final List<Obstacle> obstaclesList, final List<Obstacle> readyObstaclesList) {
        final int numRedObstacles = obstaclesList.size() % 2 == 0 ? obstaclesList.size() / 2 : (obstaclesList.size() / 2) + 1;
        assignBehaviour(obstaclesList, readyObstaclesList, ObstacleBehavior.RED, numRedObstacles);
        assignBehaviour(obstaclesList, readyObstaclesList, ObstacleBehavior.GREEN, NUM_GREEN_OBSTACLES);
        assignBehaviour(obstaclesList, readyObstaclesList, ObstacleBehavior.PURPLE, NUM_PURPLE_OBSTACLES);
        readyObstaclesList.addAll(obstaclesList);
    }
    /*
     * Assigns the behavior to a specified obstacle selected randomly from the original list
     * and moves it to a list which contains all obstacles with the correct behaviour.
     */
    private void assignBehaviour(final List<Obstacle> obstaclesList, final List<Obstacle> readyObstaclesList,
                                 final ObstacleBehavior behaviour, final int numObstacles) {
        if (obstaclesList.size() >= numObstacles) {
            for (int i = numObstacles; i > 0; i--) {
                final int n = new Random().nextInt(obstaclesList.size());
                final Obstacle o = obstaclesList.get(n);
                o.setObstacleBehavior(behaviour);
                readyObstaclesList.add(o);
                obstaclesList.remove(o);
            }
        }
    }

}
