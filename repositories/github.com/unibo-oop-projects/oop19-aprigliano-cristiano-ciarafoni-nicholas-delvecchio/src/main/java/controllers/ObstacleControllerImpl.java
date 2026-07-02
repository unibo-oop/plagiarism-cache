package controllers;

import javafx.scene.Node;
import model.obstacle.Obstacle;
import model.obstacle.ObstacleImpl;
import view.ObstacleView;
import view.ObstacleViewImpl;

import java.io.*;
import java.util.List;
import java.util.Random;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.stream.Collectors;
import com.google.gson.Gson;

/**
 * Implementation of the ObstacleController Interface, it handles the creation of the Obstacles.
 */
public final class ObstacleControllerImpl implements ObstacleController {

    private static final double FIRST_OBSTACLE_POSITION = 500.0; //first position of obstacle
    private static final int OBSTACLES = 100; //number of obstacles
    private static final double MULTIPLICATION = 2.5;
    private static final double RAND_CACTUS = 0.30;
    private static final double RAND_FLOWER = 0.60;
    private static final double RAND_MUSHROOM = 0.90;
    private static final int MIN_RAND = 100;
    private static final int MAX_RAND = 600;
    private final List<Obstacle> obstacleList;
    private final List<ObstacleView> l;
    private final Gson gson = new Gson();
    private final FloorController floorController;
    private final MarioController marioController;

    public ObstacleControllerImpl() {
        this.obstacleList = new ArrayList<>();
        this.floorController = new FloorControllerImpl();
        this.marioController = new MarioControllerImpl();
        this.l = new LinkedList<>();
    }

    @Override
    public List<ObstacleView> startObstacleView() {
        for (int i = 0; i < OBSTACLES; i++) {   //This For loop allows me to generate a Random Obstacle based on Math.Random
            Obstacle obstacle;
            double random = Math.random();
            if (random < RAND_CACTUS) {
                obstacle = this.generateCactus();
            } else if (random >= RAND_CACTUS && random < RAND_FLOWER) {
                obstacle = this.generateFlower();
            } else if (random >= RAND_FLOWER && random < RAND_MUSHROOM) {
                obstacle = this.generateMushroom();
            } else {
                obstacle = this.generatePowerUpMushroom();
            }
            if (i == 0) {
                obstacle.setPosX(FIRST_OBSTACLE_POSITION);
            } else {
                Obstacle o = obstacleList.get(i - 1);
                obstacle.setPosX(o.getPosX() + this.generateRandomSpace());
            }
            obstacle.setPosY(floorController.getFloorView().getFloor().getY() - obstacle.getHeight());
            this.obstacleList.add(obstacle); //Add the Obstacle to a List
        }

        return this.obstacleList.stream().map((element) -> { //Stream everything into a list of Obstacles
            ObstacleView img = new ObstacleViewImpl();
            img.setImg(element.getImagePath());
            img.setObstacleDimension(element.getWidth(), element.getHeight());
            img.setObstaclePosition(element.getPosX(), element.getPosY());
            return img;
        }).collect(Collectors.toList());
    }

    @Override
    public List<ObstacleView> getObstacleList() {
        return this.l;
    }

    @Override
    public List<Node> getNodeList() {
        l.addAll(this.startObstacleView());
        List<Node> list = new LinkedList<>();
        for (ObstacleView o : l) {
            list.add(o.getObstacle());
        }
        return list;
    }

    @Override
    public void moveObstacle(final double speed) { //This method is responsible for the movement speed of the obstacle
        ObstacleView newObstacle = null;
        for (ObstacleView n : l) {
            n.updatePos(speed);
            if (n.getObstacle().getX() + n.getObstacle().getWidth() < 0) {
                n.getObstacle().setX(this.l.get(OBSTACLES - 1).getObstacle().getX() + generateRandomSpace());
                newObstacle = n;
            }
        }
        if (newObstacle != null) {
            this.l.remove(this.l.get(0));
            this.l.add(newObstacle);
        }
    }


    public Obstacle generateMushroom() {
        ObstacleImpl mushroom = null;
        try {
            Reader reader = new InputStreamReader(getClass().getResourceAsStream("/json/obstacleMushroom.json")); //Create a reader
            mushroom = gson.fromJson(reader, ObstacleImpl.class); //Convert JSON string to ObstacleImpl class
            reader.close(); //Close reader
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mushroom; //Return the Obstacle Mushroom
    }

    public Obstacle generateFlower() {
        ObstacleImpl flower = null;
        try {
            Reader reader = new InputStreamReader(getClass().getResourceAsStream("/json/obstacleFlower.json")); //Create a reader
            flower = gson.fromJson(reader, ObstacleImpl.class); //Convert JSON string to ObstacleImpl class
            reader.close(); //Close reader
        } catch (IOException e) {
            e.printStackTrace();
        }
        return flower; //Return the Obstacle Flower
    }

    public Obstacle generateCactus() {
        ObstacleImpl cactus = null;
        try {
            Reader reader = new InputStreamReader(getClass().getResourceAsStream("/json/obstacleCactus.json")); //Create a reader
            cactus = gson.fromJson(reader, ObstacleImpl.class); //Convert JSON string to ObstacleImpl class
            reader.close(); //Close reader
        } catch (IOException e) {
            e.printStackTrace();
        }
        return cactus; //Return the Obstacle Cactus
    }

    @Override
    public Obstacle generatePowerUpMushroom() {
        ObstacleImpl mushroomPower = null;
        try {
            Reader reader = new InputStreamReader(getClass().getResourceAsStream("/json/powerMushroom.json")); //Create a reader
            mushroomPower = gson.fromJson(reader, ObstacleImpl.class); //Convert JSON string to ObstacleImpl class
            reader.close(); //Close reader
        } catch (IOException e) {
            e.printStackTrace();
        }
        mushroomPower.setPowerUp(true);
        return mushroomPower; //Return the Power Up Mushroom
    }

    /**
     * This method generates a random number which will be used to set a random space between obstacles.
     * @return a random number
     */
    private double generateRandomSpace() {
        Random random = new Random();
        return (this.marioController.getMarioView().getMario().getWidth() * MULTIPLICATION) + random.nextInt(MAX_RAND - MIN_RAND);
    }
}
