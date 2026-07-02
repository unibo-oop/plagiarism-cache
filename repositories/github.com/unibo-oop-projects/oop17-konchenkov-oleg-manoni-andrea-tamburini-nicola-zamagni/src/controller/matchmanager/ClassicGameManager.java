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
import model.collidable.tank.Tank;
import model.player.Player;
import model.projectile.Projectile;
import model.projectile.ProjectileCreatorImpl;
import view.View;

/**
 *
 * @author Oleg
 *
 */
public final class ClassicGameManager implements GameManager {
    /**
     * time step.
     */
    private static final double TIME_STEP = 1.0 / 6.0;
    /**
     * cor tank vs terrain.
     */
    private static final double COR_TANK_VS_TERRAIN = 0.0;
    private static final int INITIAL_POWER = 100;
    private int turn;
    private final Model model;
    private final View view;
    private final TurnManager turnManager;
    private final Controller controller;

    /**
     *
     * @param model
     *            model
     * @param view
     *            view
     * @param controller
     *            controller
     */
    protected ClassicGameManager(final Model model, final View view, final Controller controller) {
        super();
        turn = 0;
        this.model = model;
        this.view = view;
        this.controller = controller;
        turnManager = new TurnManagerImpl(view, model, GameModeType.MATCH);
        turnManager.setGameManger(this);
    }

    @Override
    public void startNewTurn() {
        if (!turnManager.isTurnStarted()
                && getAlivePlayers().stream().map(p -> p.getTank()).allMatch(tank -> tank.isStationary())) {
            model.getPlayers().get(turn).getTank()
                    .setProjectileInitialSpeed(view.getSceneMainController().getGameController().getPowerValue());
            /* Creating a new projectile from user inputs parameters */
            if (!model.getPlayers().get(turn).getInventory()
                    .get(model.getPlayers().get(turn).getInventory()
                            .indexOf(model.getPlayers().get(turn).getInventory().stream()
                                    .filter(i -> i.getKey().equals(view.getSceneMainController().getInventoryValue()))
                                    .collect(Collectors.toList()).get(0)))
                    .getValue().equals(0)) {
                model.addProjectile(createNewProjectile());
                model.getPlayers().get(turn).removeProjectile(view.getSceneMainController().getInventoryValue(),
                        GameModeType.MATCH);
                /* start turn manager */
                turnManager.start();
            } else {
                view.getSceneMainController().enableGameNodes();
            }
        } else {
            controller.update(GameStatus.SET_POWER_OF_SLIDER);
            controller.update(GameStatus.SET_ANGLE_OF_SLIDER);
        }
    }

    @Override
    public void tankDeploy() {
        final AnimationTimer t = new AnimationTimer() {

            @Override
            public void handle(final long now) {
                view.getSceneMainController().setNameTurn(model.getPlayers().get(turn).getName(),
                        new ColorAdapter(model.getPlayers().get(turn).getTank().getColor()).getFxColor());
                getAllTank().forEach(t -> t.update(TIME_STEP));
                getAllTank().stream()
                        .filter(t -> model.getTerrain().getPolycollider().isIntersected(t.getPreviousPosition(),
                                t.getPosition(), t.getVelocity()))
                        .forEach(t -> t.collide(model.getTerrain().getPolycollider(), TIME_STEP, COR_TANK_VS_TERRAIN));
                /* check if all tanks collide whit terrain and sets their turrets angles */
                if (model.getPlayers().stream().map(p -> p.getTank()).allMatch(tank -> tank.isStationary())) {
                    model.getPlayers().forEach(p -> {
                        findAllowedAngles(model.getPlayers().indexOf(p));
                        p.getTank().setElevationAngle(Math.toRadians(
                                p.getAllowedAngles().get(new Random().nextInt(p.getAllowedAngles().size()))));
                        p.getTank().setProjectileInitialSpeed(new Random().nextInt(INITIAL_POWER));
                        nextTurn();
                    });
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
        if (model.getPlayers().get(turn).getAllowedAngles().stream().anyMatch(
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
        getAlivePlayers().stream().filter(p -> !p.getTank().isDestroyed()).forEach(p -> {
            findAllowedAngles(model.getPlayers().indexOf(p));
            if (!p.getAllowedAngles().stream()
                    .anyMatch(i -> i == (int) Math.round(Math.toDegrees(p.getTank().getElevationAngle())))) {
                p.getTank().setElevationAngle(
                        Math.toRadians(p.getAllowedAngles().get(new Random().nextInt(p.getAllowedAngles().size()))));
                renderTerrainTanks();
            }
        });
    }

    @Override
    public void isGameEnded() {
        if (getAlivePlayers().size() == 1 || model.getPlayers().stream().allMatch(p -> !p.isAlive())) {
            controller.update(GameStatus.GAMEOVER);
        } else {
            nextTurn();
            controller.update(GameStatus.SET_COMBO_INVENTORY);
            controller.update(GameStatus.SET_POWER_OF_SLIDER);
            controller.update(GameStatus.SET_ANGLE_OF_SLIDER);
            view.getSceneMainController().setNameTurn(model.getPlayers().get(turn).getName(),
                    new ColorAdapter(model.getPlayers().get(turn).getTank().getColor()).getFxColor());
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
     * increments the current turn, skips the dead players turn.
     */
    private void nextTurn() {
        turn = (turn + 1) % model.getPlayers().size();
        if (!model.getPlayers().get(turn).isAlive()) {
            nextTurn();
        }
    }

    /**
     * Creates a new projectile.
     *
     * @return projectile
     */
    private Projectile createNewProjectile() {
        final double maxInitialSpeed = view.getSceneMainController().getFluid().getMaxProjectileInitialSpeed();
        return new ProjectileCreatorImpl()
                .createProjectile(
                        model.getPlayers().get(turn).getInventory().get(model.getPlayers().get(turn).getInventory()
                                .indexOf(model.getPlayers().get(turn).getInventory().stream().filter(
                                        i -> i.getKey().equals(view.getSceneMainController().getInventoryValue()))
                                        .collect(Collectors.toList()).get(0)))
                                .getKey(),
                        model.getPlayers().get(turn).getTank().getTurretMuzzle(),
                        new Vector2D(Math.cos(model.getPlayers().get(turn).getTank().getElevationAngle()),
                                Math.sin(model.getPlayers().get(turn).getTank().getElevationAngle())).scalarMultiply(
                                        view.getSceneMainController().getPowerValuePerc() * maxInitialSpeed / 100.0),
                        model.getEnviroment());
    }

    /**
     * finds the angles allowed in order to set tanks' muzzles.
     */
    private void findAllowedAngles(final int playersTurn) {
        final double tankAngle = model.getPlayers().get(playersTurn).getTank().getElevationAngle();
        final List<Integer> allowedAngles = new ArrayList<>();
        IntStream.rangeClosed(0, 180).forEach(i -> {
            model.getPlayers().get(playersTurn).getTank().setElevationAngle(Math.toRadians(i));
            if (!model.getTerrain().getPolycollider().isIntersected(
                    model.getPlayers().get(playersTurn).getTank().getTurretPoints().get(0),
                    model.getPlayers().get(playersTurn).getTank().getTurretMuzzle(), new Vector2D(0, 0))) {
                allowedAngles.add(i);
            }
        });
        model.getPlayers().get(playersTurn).getTank().setElevationAngle(tankAngle);
        Collections.shuffle(allowedAngles);
        model.getPlayers().get(playersTurn).setAllowedAngles(allowedAngles);
    }

    /**
     *
     * @return list of all players' tanks
     */
    private List<Tank> getAllTank() {
        return model.getPlayers().stream().map(p -> p.getTank()).collect(Collectors.toList());
    }

    /**
     *
     * @return list of alive players
     */
    private List<Player> getAlivePlayers() {
        return model.getPlayers().stream().filter(p -> p.isAlive()).collect(Collectors.toList());
    }

    /**
     * clear screan and render terrain and tanks.
     */
    private void renderTerrainTanks() {
        view.getRenderer().clearScreen();
        view.getRenderer().renderTerrain(model.getTerrain());
        view.getRenderer()
                .renderTankList(getAllTank().stream().filter(t -> !t.isDestroyed()).collect(Collectors.toList()));
    }
}
