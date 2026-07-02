package it.unibo.aknightstale.controllers;

import it.unibo.aknightstale.controllers.entity.CharacterController;
import it.unibo.aknightstale.controllers.entity.EnemiesControllerImpl;
import it.unibo.aknightstale.controllers.entity.ObstacleController;
import it.unibo.aknightstale.controllers.interfaces.Controller;
import it.unibo.aknightstale.controllers.interfaces.GameFinishedController;
import it.unibo.aknightstale.controllers.interfaces.MainMenuController;
import it.unibo.aknightstale.controllers.interfaces.MapController;
import it.unibo.aknightstale.models.entity.Character;
import it.unibo.aknightstale.models.entity.Direction;
import it.unibo.aknightstale.models.entity.EntityType;
import it.unibo.aknightstale.models.entity.ObstacleEntity;
import it.unibo.aknightstale.models.entity.factories.EntityFactory;
import it.unibo.aknightstale.models.entity.factories.EntityFactoryImpl;
import it.unibo.aknightstale.models.map.Spawner;
import it.unibo.aknightstale.models.map.SpawnerImpl;
import it.unibo.aknightstale.utils.BordersImpl;
import it.unibo.aknightstale.utils.CollisionManagerImpl;
import it.unibo.aknightstale.utils.Point2D;
import it.unibo.aknightstale.views.entity.AnimatedEntityView;
import it.unibo.aknightstale.views.entity.Status;
import it.unibo.aknightstale.views.interfaces.GameFinishedView;
import it.unibo.aknightstale.views.interfaces.MapView;
import javafx.util.Pair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;

/**
 * The type Map controller.
 */
public class MapControllerImpl extends BaseController<MapView> implements MapController {


    private Map<Pair<Integer, Integer>, Integer> mapTileNum = new HashMap<>();
    private final List<ObstacleController<Character, AnimatedEntityView>> obstacleControllers = new LinkedList<>();

    private double screenWidth;
    private double screenHeight;
    private final Random random = new Random();

    private EnemiesControllerImpl enemiesController;


    private int killedEnemies;
    private static final int TOTAL_ENEMIES = 20;
    private final EntityFactory factory = new EntityFactoryImpl();

    private CharacterController<? super Character, ? super AnimatedEntityView> player;
    private CollisionManagerImpl collision;

    /**
     * {@inheritDoc}
     */
    @Override
    public void showView() {
        this.collision = new CollisionManagerImpl(factory.getEntityManager().getEntities(), this.screenWidth,
                this.screenHeight);
        factory.getEntityManager().setCollisionManager(collision);

        // check tie screen size and choose the right size tiles
        updateScreenSize();

        // converting map
        readTextMap();

        //adding trees to map
        final Spawner treeSpawner = new SpawnerImpl(getView().getTree(), 30, this.mapTileNum);
        this.mapTileNum = treeSpawner.getMap();

        //binding tiles to entities
        this.bindToEntities();

        //create player
        player = factory.getPlayer(new Point2D(0, 0));
        this.setSpawnPosition(player);

        //create enemies
        this.enemiesController = new EnemiesControllerImpl(TOTAL_ENEMIES, getView(), factory, this);


        super.showView();
        getView().init();

        // recall this method to get the new window size
        updateScreenSize();
        this.drawMap();

    }

    private void bindToEntities() {
        this.mapTileNum.forEach((position, num) -> {
            // If I have to draw a tile that represent an obstacle, then I'll create an obstacle entity
            if (getView().getTiles().get(num).getEntityType() == EntityType.OBSTACLE) {
                // create obstacle entity and adds it to list
                final double x = position.getValue() * getView().getTileWidth();
                final double y = position.getKey() * getView().getTileHeight();
                final double width = getView().getTileWidth();
                final double height = getView().getTileHeight();
                final var borders = new BordersImpl(x, y, width, height);
                final var obstacleModel = new ObstacleEntity(borders);
                final var obstacleView = getView().getTiles().get(num);
                final var obstacle = new ObstacleController<Character, AnimatedEntityView>(obstacleModel, obstacleView);
                this.obstacleControllers.add(obstacle);
                this.factory.getEntityManager().addEntity(obstacle);
            }
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CharacterController<? extends Character, ? extends AnimatedEntityView> getPlayer() {
        return player;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setIdlePlayer() {
        this.player.getView().setStatus(Status.IDLE);
        this.player.getView().update(this.player.getModel().getDirection());
    }

    private void openGameFinishedScreen() {
        final var controllerView = Controller.of(GameFinishedController.class, GameFinishedView.class).get();
        controllerView.setScore(killedEnemies);
        controllerView.showView();
    }

    /**
     * Gets num col of the map.
     *
     * @return the num col
     */
    public static int getNumCol() {
        return NUM_COL;
    }

    /**
     * Gets num row of the map.
     *
     * @return the num row
     */
    public static int getNumRow() {
        return NUM_ROW;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void drawMap() {
        int row = 0;
        int col = 0;
        int x = 0;
        int y = 0;

        /*Draw lawn*/
        while (col < NUM_COL && row < NUM_ROW) {
            getView().draw(getView().getFloor(), x, y);
            col++;
            x += this.getView().getTileWidth();
            if (col == NUM_COL) {
                x = 0;
                y += this.getView().getTileHeight();
                col = 0;
                row++;
            }
        }
        /*Add obstacles to map*/
        this.obstacleControllers.forEach(c -> {
            getView().draw(c.getView(), c.getModel().getPosition().getX(), c.getModel().getPosition().getY());
        });
    }

    private void readTextMap() {

        int col = 0;
        int row = 0;


        try (BufferedReader br = new BufferedReader(new InputStreamReader(
                Objects.requireNonNull(getView().getClass().getResourceAsStream("map.txt")),
                StandardCharsets.UTF_8
        ))) {
            while (col < NUM_COL && row < NUM_ROW) {

                final String line = br.readLine();

                while (col < NUM_COL) {
                    final List<String> numLine = Arrays.asList(line.split(" "));
                    final int num = Integer.parseInt(numLine.get(col));
                    mapTileNum.put(new Pair<>(row, col), num);
                    col++;
                }

                if (col == NUM_COL) {
                    col = 0;
                    row++;
                }
            }
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateScreenSize() {
        this.screenWidth = getView().getScreenWidth();
        this.screenHeight = getView().getScreenHeight();

        collision.setWidthScreen(this.screenWidth);
        collision.setHeightScreen(this.screenHeight);

        // calculate the new tile's size
        final double tileWidth = Math.ceil(getView().getScreenWidth() / NUM_COL);
        final double tileHeight = Math.ceil(getView().getScreenHeight() / NUM_ROW);

        getView().resizeTiles(tileWidth, tileHeight);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void repositionEntities() {
        final double traslX = getView().getScreenWidth() / this.screenWidth;
        final double traslY = getView().getScreenHeight() / this.screenHeight;

        this.enemiesController.adaptPositionToScreenSize(traslX, traslY);
        this.player.adaptPositionToScreenSize(traslX, traslY);
        // calculate the new tile's size
        final double tileWidth = Math.ceil(getView().getScreenWidth() / NUM_COL);
        final double tileHeight = Math.ceil(getView().getScreenHeight() / NUM_ROW);

        this.obstacleControllers.forEach(c -> {
            c.adaptPositionToScreenSize(traslX, traslY, tileWidth, tileHeight);
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void drawPlayer() {
        final var position = player.getModel().getPosition();
        getView().draw(player.getView(), position.getX(), position.getY());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update() {
        enemiesController.removeDeadEnemies();
        this.killedEnemies = TOTAL_ENEMIES - enemiesController.getNumEnemy();
        if (killedEnemies == TOTAL_ENEMIES || this.player.getModel().getHealth() <= 0) {
            this.openGameFinishedScreen();
            getView().stopGame();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void drawEnemies() {
        enemiesController.drawEnemies(this.collision, this.player);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updatePlayer(final Direction direction) {
        switch (direction) {
            case UP:
                this.player.moveUp();
                break;
            case DOWN:
                this.player.moveDown();
                break;
            case LEFT:
                this.player.moveLeft();
                break;
            case RIGHT:
                this.player.moveRight();
                break;
            default:
                break;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void playerAttack() {
        this.player.attack();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void moveEnemies() {
        this.enemiesController.updateDirection(player.getModel().getPosition());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<CharacterController<? super Character, ? super AnimatedEntityView>> getEnemies() {
        return this.enemiesController.getEnemiesControllers();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setSpawnPosition(final CharacterController<? super Character, ? super AnimatedEntityView> entity) {

        var x = random.nextDouble() * this.screenWidth;
        var y = random.nextDouble() * this.screenHeight;
        entity.getModel().setPosition(new Point2D(x, y));
        while (!this.collision.checkCollision(entity).isEmpty()) {
            x = random.nextDouble() * this.screenWidth;
            y = random.nextDouble() * this.screenHeight;
            entity.getModel().setPosition(new Point2D(x, y));
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void returnToMainMenu() {
        getView().stopGame();
        Controller.of(MainMenuController.class).get().showView();
    }
}
