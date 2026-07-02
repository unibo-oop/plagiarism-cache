package controller.matchmanager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

import controller.Controller;
import controller.coloradapter.ColorAdapter;
import javafx.scene.paint.Color;
import javafx.util.Pair;
import model.Model;
import model.ModelImpl;
import model.battlefield.Fluid;
import model.battlefield.Location;
import model.collidable.tank.TankImpl;
import model.collidable.tank.shape.TankShapeImpl;
import model.collidable.terrain.Terrain;
import model.collidable.terrain.TerrainImpl;
import model.match.ScoreManagerImpl;
import model.physics.particle.environment.Environment;
import model.physics.particle.environment.GravityOnlyEnvironment;
import model.physics.particle.environment.QuadraticDragEnvironment;
import model.player.Player;
import model.player.PlayerImpl;
import model.projectile.ProjectileType;
import view.View;

/**
 *
 * @author Oleg
 *
 */
public final class BasicGameManagerCreator implements GameManagerCreator {

    private static final double SLOT_AMPLITUDE_FACTOR = 3.0;
    private static final long SEED = (long) (1241334210 * Math.random());
    private static final int DEMO_SLOTS = 10;
    private static final int SAMPLING = 5;
    private static final int TANK_SCALE_FACTOR = 2;
    private static final int LOCATIONS = 0;
    private static final int FLUIDS = 0;

    private Environment environment;
    private Terrain terrain;
    private List<Player> players;

    private Model model;
    private List<Integer> slots;

    @Override
    public GameManager createGameManager(final GameModeType gameModeType, final Controller controller,
            final View view) {

        if (gameModeType == GameModeType.DEMO) {

            final Location location = (Location) controller.getLocationsJSON().getLocationList().get(LOCATIONS);
            final Fluid fluid = (Fluid) location.getChild().get(FLUIDS);
            /* environment creation */
            if (Double.compare(fluid.getDensity(), 0.0) == 0) {
                environment = new GravityOnlyEnvironment.Builder().setGravitationalAcceleration(location.getGravity())
                        .build();
                view.getSceneMainController().setWindTurn(0.0);
            } else {
                final double fluidSpeed = getFluidSpeed(fluid.getMaxFluidSpeed());
                environment = new QuadraticDragEnvironment.Builder().setGravitationalAcceleration(location.getGravity())
                        .setFluidDensity(fluid.getDensity()).setFluidVelocity(new Vector2D(fluidSpeed, 0)).build();
                view.getSceneMainController().setWindTurn(fluidSpeed);
            }
            /* terrain creation */
            terrain = new TerrainImpl.Builder().setHeight(location.getHeigth()).setWidth(location.getWidth())
                    .setWaveLenght(location.getWavelength()).setGravitationalAcceleration(location.getGravity())
                    .setSeed(SEED).setSampling(SAMPLING).build();
            /* player creation */
            players = new ArrayList<>();
            players.add(new PlayerImpl("DEMO",
                    new TankImpl.Builder().setTankColor(new ColorAdapter(Color.RED).getTankColor())
                            .setTankShape(new TankShapeImpl(controller.getTankShapeJSON().getOutlinePoints(),
                                    controller.getTankShapeJSON().getTurretPoints(),
                                    controller.getTankShapeJSON().getExplosionRadius()))
                            .setScaleFactor(TANK_SCALE_FACTOR)
                            .setPosition(tankSpawnPosition(new Random().nextInt(DEMO_SLOTS), DEMO_SLOTS, location))
                            .setGravitationalAcceleration(location.getGravity()).build(),
                    createInventory(gameModeType), new ScoreManagerImpl()));
            players.get(0).getTank().setElevationAngle(Math.PI / 2.0);
            /* model creation */
            model = new ModelImpl(environment, terrain, players);
            /* for renderer scale factor */
            view.getRenderer().setTerrainWidth(location.getWidth());
            return new DemoGameManager(model, view, controller);

        } else if (gameModeType == GameModeType.MATCH) {
            /* environment creation from view data */
            if (Double.compare(view.getSceneMainController().getFluid().getDensity(), 0.0) == 0) {
                environment = new GravityOnlyEnvironment.Builder()
                        .setGravitationalAcceleration(view.getSceneMainController().getLocation().getGravity()).build();
                view.getSceneMainController().setWindTurn(0.0);
            } else {
                final double fluidSpeed = view.getSceneMainController().isFluidStationary() ? 0.0
                        : getFluidSpeed(view.getSceneMainController().getFluid().getMaxFluidSpeed());
                environment = new QuadraticDragEnvironment.Builder()
                        .setGravitationalAcceleration(view.getSceneMainController().getLocation().getGravity())
                        .setFluidDensity(view.getSceneMainController().getFluid().getDensity())
                        .setFluidVelocity(new Vector2D(fluidSpeed, 0)).build();
                view.getSceneMainController().setWindTurn(fluidSpeed);
            }
            /* terrain creation from view data */
            terrain = new TerrainImpl.Builder().setHeight(view.getSceneMainController().getLocation().getHeigth())
                    .setWidth(view.getSceneMainController().getLocation().getWidth())
                    .setWaveLenght(view.getSceneMainController().getLocation().getWavelength())
                    .setGravitationalAcceleration(view.getSceneMainController().getLocation().getGravity())
                    .setSeed(view.getSceneMainController().getSeed()).setSampling(SAMPLING).build();
            slots = IntStream.range(0, view.getSceneMainController().getPlayersList().size()).mapToObj(i -> i)
                    .collect(Collectors.toList());
            Collections.shuffle(slots);
            /* players creation from view data */
            players = new ArrayList<>();
            players = IntStream.range(0, slots.size()).mapToObj(i -> new PlayerImpl(
                    view.getSceneMainController().getPlayersList().get(i).getKey(),
                    new TankImpl.Builder()
                            .setTankColor(
                                    new ColorAdapter(view.getSceneMainController().getPlayersList().get(i).getValue())
                                            .getTankColor())
                            .setTankShape(new TankShapeImpl(controller.getTankShapeJSON().getOutlinePoints(),
                                    controller.getTankShapeJSON().getTurretPoints(),
                                    controller.getTankShapeJSON().getExplosionRadius()))
                            .setScaleFactor(TANK_SCALE_FACTOR)
                            .setPosition(tankSpawnPosition(slots.get(i), slots.size(),
                                    view.getSceneMainController().getLocation()))
                            .setGravitationalAcceleration(view.getSceneMainController().getLocation().getGravity())
                            .build(),
                    createInventory(gameModeType), new ScoreManagerImpl())).collect(Collectors.toList());
            players.forEach(p -> p.getTank().setElevationAngle(Math.PI / 2.0));
            /* model creation */
            model = new ModelImpl(environment, terrain, players);
            /* for renderer scale factor */
            view.getRenderer().setTerrainWidth(view.getSceneMainController().getLocation().getWidth());
            return new ClassicGameManager(model, view, controller);
        } else {
            throw new IllegalArgumentException("Invalid Game type");
        }

    }

    /**
     *
     * @param slotIndex
     *            slot index
     * @param numOfSlots
     *            num of slots
     * @param location
     *            location
     * @return Vector2D
     */
    private Vector2D tankSpawnPosition(final int slotIndex, final int numOfSlots, final Location location) {
        final double slotWidth = location.getWidth() / numOfSlots;
        final double x = (slotIndex + 0.5) * slotWidth + Math.random() * slotWidth / SLOT_AMPLITUDE_FACTOR
                - slotWidth / (SLOT_AMPLITUDE_FACTOR * 2.0);
        return new Vector2D(x, location.getHeigth());
    }

    /**
     *
     * @param maxFluidSpeed
     *            max fluid speed
     * @return fluid speed
     */
    private double getFluidSpeed(final double maxFluidSpeed) {
        final double fluidSpeed = (maxFluidSpeed > 0 ? new Random().nextInt(100 + 1) : 0) * maxFluidSpeed / 100.0;
        return new Random().nextInt(2) > 0 ? fluidSpeed : -fluidSpeed;
    }

    /**
     *
     * @param gamoModeType
     *            game mode type
     * @return inventory
     */
    private List<Pair<ProjectileType, Integer>> createInventory(final GameModeType gamoModeType) {

        final List<Pair<ProjectileType, Integer>> inventory = new ArrayList<>();

        if (gamoModeType.equals(GameModeType.MATCH)) {
            inventory.add(new Pair<>(ProjectileType.EXPLOSIVE, Integer.MAX_VALUE));
            inventory.add(new Pair<>(ProjectileType.BOUNCY, 0));
            inventory.add(new Pair<>(ProjectileType.FRAG, 0));
            inventory.add(new Pair<>(ProjectileType.CLUSTER_EXPLOSIVE, 0));
            inventory.add(new Pair<>(ProjectileType.CLUSTER_BOUNCY, 0));
            inventory.add(new Pair<>(ProjectileType.CLUSTER_CLUSTER_EXPLOSIVE, 0));
            inventory.add(new Pair<>(ProjectileType.CLUSTER_CLUSTER_BOUNCY, 0));
        } else {
            inventory.add(new Pair<>(ProjectileType.EXPLOSIVE, Integer.MAX_VALUE));
            inventory.add(new Pair<>(ProjectileType.BOUNCY, Integer.MAX_VALUE));
            inventory.add(new Pair<>(ProjectileType.FRAG, Integer.MAX_VALUE));
            inventory.add(new Pair<>(ProjectileType.CLUSTER_EXPLOSIVE, Integer.MAX_VALUE));
            inventory.add(new Pair<>(ProjectileType.CLUSTER_BOUNCY, Integer.MAX_VALUE));
            inventory.add(new Pair<>(ProjectileType.CLUSTER_CLUSTER_EXPLOSIVE, Integer.MAX_VALUE));
            inventory.add(new Pair<>(ProjectileType.CLUSTER_CLUSTER_BOUNCY, Integer.MAX_VALUE));
        }
        return inventory;
    }
}
