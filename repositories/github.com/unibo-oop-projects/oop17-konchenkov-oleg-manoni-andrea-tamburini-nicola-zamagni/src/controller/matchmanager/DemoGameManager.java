package controller.matchmanager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

import controller.Controller;
import controller.GameStatus;
import controller.coloradapter.ColorAdapter;
import javafx.animation.AnimationTimer;
import model.Model;
import model.battlefield.Fluid;
import model.battlefield.Location;
import model.collidable.tank.Tank;
import model.projectile.Projectile;
import model.projectile.ProjectileCreatorImpl;
import view.View;

/**
 *
 * @author Oleg
 *
 */
public final class DemoGameManager implements GameManager {
    /**
     * time step.
     */
    private static final double TIME_STEP = 1.0 / 6.0;
    /**
     * cor tank vs terrain.
     */
    private static final double COR_TANK_VS_TERRAIN = 0.0;
    private static final int INITIAL_POWER = 100;
    private final int turn;
    private final Model model;
    private final View view;
    private final TurnManager turnManager;
    private final Controller controller;
    private int suicideCounter;

    /**
     *
     * @param model
     *            model
     * @param view
     *            view
     * @param controller
     *            controller
     */
    protected DemoGameManager(final Model model, final View view, final Controller controller) {
        super();
        turn = 0;
        this.model = model;
        this.view = view;
        this.controller = controller;
        turnManager = new TurnManagerImpl(view, model, GameModeType.DEMO);
        turnManager.setGameManger(this);
        suicideCounter = 0;
    }

    @Override
    public void startNewTurn() {
        if (!turnManager.isTurnStarted() && getTank().isStationary()) {
            getTank().setProjectileInitialSpeed(view.getSceneMainController().getGameController().getPowerValue());
            /* Creating a new projectile from user inputs parameters */
            model.addProjectile(createNewProjectile());
            turnManager.start();
            controller.update(GameStatus.SET_COMBO_INVENTORY);
            controller.update(GameStatus.SET_POWER_OF_SLIDER);
            controller.update(GameStatus.SET_ANGLE_OF_SLIDER);
        } else {
            view.getSceneMainController().enableGameNodes();
        }
        controller.update(GameStatus.SET_POWER_OF_SLIDER);
        controller.update(GameStatus.SET_ANGLE_OF_SLIDER);
    }

    @Override
    public void tankDeploy() {
        final AnimationTimer t = new AnimationTimer() {

            @Override
            public void handle(final long now) {
                view.getSceneMainController().setNameTurn(model.getPlayers().get(turn).getName(),
                        new ColorAdapter(model.getPlayers().get(turn).getTank().getColor()).getFxColor());
                if (suicideCounter != 0) {
                    getTank().setFalling();
                }
                getTank().update(TIME_STEP);
                if (model.getTerrain().getPolycollider().isIntersected(getTank().getPreviousPosition(),
                        getTank().getPosition(), getTank().getVelocity())) {
                    getTank().collide(model.getTerrain().getPolycollider(), TIME_STEP, COR_TANK_VS_TERRAIN);
                }
                if (getTank().isStationary()) {
                    findAllowedAngles();
                    getTank().setElevationAngle(Math.toRadians(model.getPlayers().get(0).getAllowedAngles()
                            .get(new Random().nextInt(model.getPlayers().get(0).getAllowedAngles().size()))));
                    getTank().setProjectileInitialSpeed(new Random().nextInt(INITIAL_POWER));
                    controller.update(GameStatus.SET_COMBO_INVENTORY);
                    controller.update(GameStatus.SET_POWER_OF_SLIDER);
                    controller.update(GameStatus.SET_ANGLE_OF_SLIDER);
                    view.getSceneMainController().enableGameNodes();
                    stop();
                }
                renderTerrainTanks();
            }
        };
        t.start();
    }

    @Override
    public void setTurrenAngle() {
        findAllowedAngles();
        if (model.getPlayers().get(0).getAllowedAngles().stream().anyMatch(
                i -> Double.compare(i, view.getSceneMainController().getGameController().getAngleValue()) == 0)) {
            model.getPlayers().get(turn).getTank().setElevationAngle(
                    Math.toRadians(view.getSceneMainController().getGameController().getAngleValue()));
            renderTerrainTanks();
        } else {
            controller.update(GameStatus.SET_ANGLE_OF_SLIDER);
        }

    }

    @Override
    public void randomizeAngleIfProhibited() {
        findAllowedAngles();
        if (!model.getPlayers().get(0).getAllowedAngles().stream()
                .anyMatch(i -> i == (int) Math.round(Math.toDegrees(getTank().getElevationAngle())))) {
            final Random random = new Random();
            getTank().setElevationAngle(Math.toRadians(model.getPlayers().get(0).getAllowedAngles()
                    .get(random.nextInt(model.getPlayers().get(0).getAllowedAngles().size()))));
        }
    }

    @Override
    public void isGameEnded() {
        if (!model.getPlayers().get(0).isAlive()) {
            getTank().setDestroyed(false);
            model.getPlayers().get(0).setAlive(true);
            suicideCounter++;
            tankDeploy();
        }
    }

    @Override
    public int getTurn() {
        return turn;
    }

    @Override
    public Model getModel() {
        return model;
    }

    @Override
    public TurnManager getTurnManager() {
        return turnManager;
    }

    @Override
    public void stopTurnManager() {
        turnManager.stop();
    }

    /**
     * Creates a new projectile.
     *
     * @return projectile
     */
    private Projectile createNewProjectile() {
        final Location demoLocation = ((Location) controller.getLocationsJSON().getLocationList().get(0));
        final double maxInitialSpeedDemo = ((Fluid) demoLocation.getChild().get(0)).getMaxProjectileInitialSpeed();
        return new ProjectileCreatorImpl()
                .createProjectile(
                        model.getPlayers().get(turn).getInventory().get(model.getPlayers().get(turn).getInventory()
                                .indexOf(model.getPlayers().get(turn).getInventory().stream().filter(
                                        i -> i.getKey().equals(view.getSceneMainController().getInventoryValue()))
                                        .collect(Collectors.toList()).get(0)))
                                .getKey(),
                        getTank().getTurretMuzzle(),
                        new Vector2D(Math.cos(getTank().getElevationAngle()), Math.sin(getTank().getElevationAngle()))
                                .scalarMultiply(view.getSceneMainController().getPowerValuePerc() * maxInitialSpeedDemo / 100.0),
                        model.getEnviroment());
    }

    /**
     * finds the angles allowed in order to set tanks' muzzles.
     */
    private void findAllowedAngles() {
        final List<Integer> allowedAngles = new ArrayList<>();
        final double tankAngle = getTank().getElevationAngle();
        IntStream.rangeClosed(0, 180).forEach(i -> {
            getTank().setElevationAngle(Math.toRadians(i));
            if (!model.getTerrain().getPolycollider().isIntersected(getTank().getTurretPoints().get(0),
                    getTank().getTurretMuzzle(), new Vector2D(0, 0))) {
                allowedAngles.add(i);
            }
        });
        getTank().setElevationAngle(tankAngle);
        Collections.shuffle(allowedAngles);
        model.getPlayers().get(0).setAllowedAngles(allowedAngles);
    }

    private Tank getTank() {
        return model.getPlayers().get(turn).getTank();
    }

    /**
     * clear screen and render Terrain and Tanks.
     */
    private void renderTerrainTanks() {
        view.getRenderer().clearScreen();
        view.getRenderer().renderTerrain(model.getTerrain());
        view.getRenderer().renderTank(getTank());
    }
}
