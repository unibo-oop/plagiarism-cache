package model.entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

import model.ModelCostant;
/**
 * Classe concreta factory delle varie {@link IEntity}. Implementa {@link IEntityFactory} 
 * @author Gnoli Mirco
 *
 */
//aggiungere metodo x generare proiettili e power up
//randombrick da sistemare quando ci sono diversi tipi di mattoni
public class EntityFactory implements IEntityFactory {

    private static final int MAX_DELTA_ANGLE = 25;
    private static final int MIN_DELTA_ANGLE = 5;

    @Override
    public final Ball standardBall() {
        //il -1 serve a non prendere l'ultima tipologia di pallina -> quella di fuoco
        Ball b = new BallBuilder()
                .position(ModelCostant.WORLD_WIDTH / 2, ModelCostant.WORLD_HEIGHT / 2)
                .type(BallType.values()[new Random().nextInt(BallType.values().length - 2)])
                .build();
        return b;
    }

    @Override
    public final List<Ball> multipleBall(final Ball b) {
        List<Ball> balls = new ArrayList<>();
        int angle = new Random().nextInt(MAX_DELTA_ANGLE) + MIN_DELTA_ANGLE;
        balls.add(new BallBuilder()
                .position(b.getPosition().getKey(), b.getPosition().getValue())
                .radius(b.getRadius())
                .speed(b.getSpeed())
                .angle(b.getAngle() + angle)
                .type(b.getType())
                .build());

        balls.add(new BallBuilder()
                .position(b.getPosition().getKey(), b.getPosition().getValue())
                .radius(b.getRadius())
                .speed(b.getSpeed())
                .angle(b.getAngle() - angle)
                .type(b.getType())
                .build());
        return balls;
    }

    @Override
    public final List<Wall> standardWalls() {
        List<Wall> walls = new ArrayList<>();
        walls.add(new Wall(0, 0, 0, ModelCostant.WORLD_HEIGHT));
        walls.add(new Wall(0, 0, ModelCostant.WORLD_WIDTH, 0));
        walls.add(new Wall(ModelCostant.WORLD_WIDTH, 0, ModelCostant.WORLD_WIDTH, ModelCostant.WORLD_HEIGHT));

        //parete inferiore, utile per i test
        walls.add(new Wall(0, ModelCostant.WORLD_HEIGHT, ModelCostant.WORLD_WIDTH, ModelCostant.WORLD_HEIGHT));
        return walls;
    }

    private Brick randomBrick() {
        /*Brick rndBrick = BrickEnum.values().get(new Random().nextInt(BrickEnum.values().length));*/
        Brick b = new Brick(ModelCostant.DEFAULT_BRICK_WIDTH, ModelCostant.DEFAULT_BRICK_HEIGHT);

        return b;
    }

    @Override
    public final List<Brick> randomBrickRow() {
        List<Brick> rowBrick = new ArrayList<>();
        for (int i = 0; i < ModelCostant.NUMBER_OF_COLUMN; i++) {
            Brick b = randomBrick();
            b.setPosition((int) (i + i * b.getWidth()), 0);
            rowBrick.add(b);
        }
        return rowBrick;
    }

    @Override
    public final Map<Integer, List<Brick>> randomBrickMap() {
        Map<Integer, List<Brick>> mapBrick = new HashMap<>();
        for (int i = 0; i < ModelCostant.NUMBER_OF_ROW; i++) {
            List<Brick> list = randomBrickRow();
            for (Brick b : list) {
                b.setPosition(b.getPosition().getKey(), (int) (i + i * b.getHeight()));
            }
             mapBrick.put(i, list);
        }
        return mapBrick;
    }

    @Override
    public final Bar standardBar() {
        final int barXPos = (ModelCostant.WORLD_WIDTH - ModelCostant.DEFAULT_BAR_WIDTH) / 2;
        final int barYPos = ModelCostant.WORLD_HEIGHT - ModelCostant.DEFAULT_BAR_HEIGHT - ModelCostant.DEFAULT_OFFSET_FROM_WALL;
        return new Bar(barXPos, barYPos, ModelCostant.DEFAULT_BAR_WIDTH, ModelCostant.DEFAULT_BAR_HEIGHT, ModelCostant.DEFAULT_BAR_ARC, ModelCostant.DEFAULT_BAR_ARC);
    }

    @Override
    public final Optional<PowerUp> randomPowerUp(final Brick brickGenerator) {
        if (toBeGenerate()) {
            return Optional.of(new PowerUp(brickGenerator.getPosition(), (PowerUpType.values()[new Random().nextInt(PowerUpType.values().length)])));
        }
        return Optional.empty();
    }

    private boolean toBeGenerate() {
        return new Random().nextInt(100) < (ModelCostant.POWERUP_SPAWN_PROBABILITY * 100);
    }

}
