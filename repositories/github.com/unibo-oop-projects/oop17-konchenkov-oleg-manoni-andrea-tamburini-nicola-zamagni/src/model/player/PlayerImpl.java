package model.player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import controller.matchmanager.GameModeType;
import javafx.util.Pair;
import model.collidable.tank.Tank;
import model.collidable.tank.TankImpl;
import model.match.ScoreManager;
import model.match.ScoreManagerImpl;
import model.projectile.ProjectileType;

/**
 *
 * @author Oleg
 *
 */
public final class PlayerImpl implements Player {

    private final String name;
    private final List<Pair<ProjectileType, Integer>> inventory;
    private boolean alive;
    private final Tank tank;
    private final ScoreManager scoreManager;
    private ProjectileType lastShotProjectyle;
    private List<Integer> allowedAngles;

    /**
     *
     * @param name
     *            name
     * @param tank
     *            tank
     * @param inventory
     *            inventory
     * @param scoreManager
     *            score manager
     */
    public PlayerImpl(final String name, final Tank tank, final List<Pair<ProjectileType, Integer>> inventory,
            final ScoreManager scoreManager) {
        super();
        this.name = name;
        this.tank = new TankImpl(tank);
        this.inventory = inventory.stream().collect(Collectors.toList());
        alive = true;
        this.scoreManager = new ScoreManagerImpl(scoreManager);
        lastShotProjectyle = ProjectileType.EXPLOSIVE;
        allowedAngles = new ArrayList<>();
    }

    /**
     *
     * Copy Constructor.
     *
     * @param player
     *            player
     */
    public PlayerImpl(final Player player) {
        this(player.getName(), player.getTank(), player.getInventory(), player.getScoreManager());
    }

    @Override
    public Tank getTank() {
        return tank;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public ProjectileType getLastShotProjectile() {
        return lastShotProjectyle;
    }

    @Override
    public ScoreManager getScoreManager() {
        return scoreManager;
    }

    @Override
    public List<Integer> getAllowedAngles() {
        return Collections.unmodifiableList(allowedAngles);
    }

    @Override
    public List<Pair<ProjectileType, Integer>> getInventory() {
        return Collections
                .unmodifiableList(inventory.stream().filter(i -> i.getValue() > 0).collect(Collectors.toList()));
    }

    @Override
    public boolean isAlive() {
        return alive;
    }

    @Override
    public void setAllowedAngles(final List<Integer> allowedAngles) {
        this.allowedAngles = allowedAngles;
    }

    @Override
    public void setAlive(final boolean alive) {
        this.alive = alive;
    }

    @Override
    public void addProjectile(final boolean specialProjectyle, final GameModeType gameMode) {
        if (gameMode.equals(GameModeType.MATCH)) {
            final List<ProjectileType> projList = new ArrayList<>();
            if (specialProjectyle) {
                /* add special projectile to inventory */
                projList.add(ProjectileType.CLUSTER_CLUSTER_BOUNCY);
                projList.add(ProjectileType.CLUSTER_CLUSTER_EXPLOSIVE);
                projList.add(ProjectileType.CLUSTER_BOUNCY);
            } else {
                projList.add(ProjectileType.BOUNCY);
                projList.add(ProjectileType.CLUSTER_EXPLOSIVE);
                projList.add(ProjectileType.FRAG);
            }
            Collections.shuffle(projList);

            final List<Pair<ProjectileType, Integer>> list = inventory.stream()
                    .filter(i -> i.getKey().equals(projList.get(0))).collect(Collectors.toList());
            inventory.set(inventory.indexOf(list.get(0)), new Pair<>(projList.get(0), list.get(0).getValue() + 1));
        }
    }

    @Override
    public void removeProjectile(final ProjectileType projectieType, final GameModeType gameMode) {
        /* remove the shoot projectile if it's not explosive */
        if (!projectieType.equals(ProjectileType.EXPLOSIVE) && gameMode.equals(GameModeType.MATCH)) {
            final Pair<ProjectileType, Integer> p = inventory.get(inventory.indexOf(inventory.stream()
                    .filter(i -> i.getKey().equals(projectieType)).collect(Collectors.toList()).get(0)));
            inventory.set(inventory.indexOf(p), new Pair<>(p.getKey(), p.getValue() - 1));
            if (p.getValue() - 1 == 0) {
                lastShotProjectyle = ProjectileType.EXPLOSIVE;
            } else {
                lastShotProjectyle = projectieType;
            }
        } else if (gameMode.equals(GameModeType.DEMO)) {
            lastShotProjectyle = projectieType;
        }
    }
}
