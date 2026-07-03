package game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import game.SpecialEffect.SpecialEffectType;
import game.buffs.GlobalPowerUp;
import game.buffs.PlayerPowerUp;
import game.enemy.AbstractEnemy;
import game.enemy.Shot;
import game.obstacles.AbstractObstacle;

/**
 * The logic core of the game. This class holds every entities present in the game and is responsible for their state.
 * It updates them and check if there are any collisions. It is also responsible for clearing the dead entities and making levels go on.
 */
public final class GameImpl implements Game {

    /**
     * The width of the area where the entities move.
     */
    public static final int GAMEAREA_WIDTH = 1600;
    /**
     * The height of the area where the entities move.
     */
    public static final int GAMEAREA_HEIGHT = 900;

    private static final int ENEMY_DEAD = 10;
    private static final int LEVEL_CLEARED = 1;

    private GameState state;
    private final GameMode mode;
    private final Player player1;
    private final Optional<Player> player2;
    private final Optional<List<AbstractEnemy>> enemies;
    private final Optional<List<AbstractObstacle>> obstacles;
    private final List<Bullet> bullets;
    private final List<SpecialEffect> effects;
    private final InterfaceLevel level;
    private final Optional<List<Shot>> shots;
    private final List<PlayerPowerUp> playerPowerUps;
    private Optional<GlobalPowerUp> globalPowerUp;
    private int score;
    private boolean freeze;

    /**
     * @param mode the gamemode chosen
     */
    public GameImpl(final GameMode mode) {
        this.effects = new ArrayList<>();
        this.mode = mode;
        this.state = GameState.RUNNING;
        this.bullets = new ArrayList<>();
        this.score = 0;
        this.freeze = false;
        this.player1 = new Player(ID.PLAYER, this);
        this.playerPowerUps = new ArrayList<>();
        this.globalPowerUp = Optional.empty();
        this.obstacles = Optional.of(new ArrayList<>());
        if (this.mode.equals(GameMode.MULTIPLAYER)) {
            this.player2 = Optional.of(new Player(ID.PLAYER2, this));
            this.enemies = Optional.empty();
            this.shots = Optional.empty();
        } else {
            this.player2 = Optional.empty();
            this.shots = Optional.of(new ArrayList<>());
            this.enemies = Optional.of(new ArrayList<>());
        }
        this.level = new Level(this);
    }

    @Override
    public void update() {
        this.playerPowerUps.forEach(pu -> pu.update());
        this.player1.update();
        if (this.mode.equals(GameMode.MULTIPLAYER)) {
            this.player2.get().update();
            this.level.nextLevelMulti();
        }
        if (this.mode.equals(GameMode.SINGLEPLAYER) && !this.freeze) {
            this.enemies.get().forEach(e -> e.update());
            this.obstacles.get().forEach(a -> a.update());
            this.shots.get().forEach(s -> s.update());
        }
        this.bullets.forEach(b -> b.update());
        this.effects.forEach(e -> e.update());
        if (this.mode.equals(GameMode.SINGLEPLAYER) && this.globalPowerUp.isPresent()) {
            this.globalPowerUp.get().update();
        }
    }
 
    /**
     * Passing from a level to another.
     */
    public void nextLevel() {
        if (this.effects.isEmpty() && this.enemies.isPresent() && this.enemies.get().isEmpty()) {
            this.score += (LEVEL_CLEARED * this.level.getLevel() * this.player1.getHealth() / 10);
            this.clearEntitiesLeft();
            this.level.nextLevelSingle();
        }
    }

    private void clearEntitiesLeft() {
        this.obstacles.get().forEach(a -> a.setDead());
        this.bullets.forEach(b -> b.setDead());
        this.shots.get().forEach(s -> s.setDead());
        this.enemies.get().forEach(e -> e.setDead());
        this.effects.forEach(e -> e.setDead());
        this.playerPowerUps.stream().filter(pu -> pu.isActivated()).forEach(pu -> pu.reset());
        this.playerPowerUps.forEach(pu -> pu.setDead());
        if (this.globalPowerUp.isPresent() && this.globalPowerUp.get().isActivated()) {
            this.globalPowerUp.get().reset();
            this.globalPowerUp.get().setDead();
        }
        this.removeDeadEntities();
    }

    @Override
    public void checkCollisions() {
        this.checkForCollisions(Arrays.asList(this.player1), this.obstacles.get().stream().collect(Collectors.toList()));
        if (this.mode.equals(GameMode.MULTIPLAYER)) {
            this.checkForCollisions(Arrays.asList(this.player1), this.bullets.stream().filter(b -> b.getOwner() != this.player1.getID()).collect(Collectors.toList()));
            this.checkForCollisions(Arrays.asList(this.player2.get()), this.bullets.stream().filter(b -> b.getOwner() != this.player2.get().getID()).collect(Collectors.toList()));
            this.checkForCollisions(Arrays.asList(this.player2.get()), this.playerPowerUps.stream().filter(pu -> !pu.isActivated()).collect(Collectors.toList()));
        }
        if (this.mode.equals(GameMode.SINGLEPLAYER)) {
            this.checkForCollisions(Arrays.asList(this.player1), this.enemies.get().stream().collect(Collectors.toList()));
            this.checkForCollisions(this.enemies.get().stream().filter(e -> !e.isDead()).collect(Collectors.toList()), 
                    this.bullets.stream().filter(b -> !b.isDead()).collect(Collectors.toList()));
            this.checkForCollisions(this.obstacles.get().stream().filter(e -> !e.isDead()).collect(Collectors.toList()), 
                    this.bullets.stream().filter(b -> !b.isDead()).collect(Collectors.toList()));
            this.checkForCollisions(Arrays.asList(this.player1), this.shots.get().stream().collect(Collectors.toList()));
            if (this.globalPowerUp.isPresent() && !this.globalPowerUp.get().isActivated() && this.globalPowerUp.get().getHitbox().intersects(this.player1.getHitbox())) {
                this.globalPowerUp.get().setGame(this);
                this.globalPowerUp.get().collide(this.player1);
            }
        }
        this.checkForCollisions(Arrays.asList(this.player1), this.playerPowerUps.stream().filter(pu -> !pu.isActivated()).collect(Collectors.toList()));
        if (this.mode.equals(GameMode.SINGLEPLAYER) && this.player1.isDead()) {
            this.state = GameState.LOST;
        } else if (this.mode.equals(GameMode.SINGLEPLAYER)) {
            this.nextLevel();
        } else if (this.mode.equals(GameMode.MULTIPLAYER) && (this.player1.isDead() || this.player2.get().isDead())) {
            this.state = GameState.ENDED;
        }
        this.removeDeadEntities();
    }

    private void checkForCollisions(final List<Entity> entities1, final List<Entity> entities2) {
        entities1.forEach(e1 -> entities2.stream()
                .filter(e2 -> !e2.isDead())
                .filter(e2 -> e2.getHitbox().intersects(e1.getHitbox()))
                .forEach(e2 -> {
                    if (!e2.getID().equals(ID.POWER_UP)) {
                        e1.collide(e2);
                    }
                    e2.collide(e1);
                }));
    }

    private void removeDeadEntities() {
        if (this.mode.equals(GameMode.SINGLEPLAYER)) {
            final List<AbstractEnemy> deadEnemies = this.enemies.get().stream()
                    .filter(e -> e.isDead())
                    .peek(e -> this.score += (ENEMY_DEAD * this.level.getLevel()))
                    .peek(e -> this.effects.add(new SpecialEffect(ID.EFFECT, e.getPosition(), e.getHitbox(), SpecialEffectType.EXPLOSION)))
                    .collect(Collectors.toList());
            deadEnemies.forEach(e -> this.enemies.get().remove(e));
            final List<AbstractObstacle> deadObstacles = this.obstacles.get().stream().filter(a -> a.isDead()).collect(Collectors.toList());
            deadObstacles.forEach(o -> this.obstacles.get().remove(o));
            this.shots.get().removeIf(s -> s.isDead());
            if (this.globalPowerUp.isPresent() && this.globalPowerUp.get().isDead()) {
                this.globalPowerUp = Optional.empty();
            }
        }
        this.bullets.removeIf(b -> b.isDead());
        this.playerPowerUps.removeIf(pu -> pu.isDead());
        this.effects.removeIf(e -> e.isDead());
    }

    @Override
    public List<GameObject> getEntities() {
        final List<GameObject> temp = new LinkedList<>();
        temp.add(this.player1);
        if (this.player2.isPresent()) {
            temp.add(this.player2.get());
        }
        temp.addAll(this.bullets);
        if (this.enemies.isPresent()) {
            temp.addAll(this.enemies.get());
        }
        if (this.obstacles.isPresent()) {
            temp.addAll(this.obstacles.get());
        }
        if (this.shots.isPresent()) {
            temp.addAll(this.shots.get());
        }
        temp.addAll(this.playerPowerUps);
        temp.addAll(this.effects);
        if (this.globalPowerUp.isPresent()) {
            temp.add(globalPowerUp.get());
        }
        return temp;
    }

    @Override
    public GameMode getMode() {
        return this.mode;
    }

    @Override
    public GameState getState() {
        return this.state;
    }

    @Override
    public int getLevel() {
        if (this.mode.equals(GameMode.SINGLEPLAYER)) {
            return level.getLevel();
        }
        return 0;
    }

    @Override
    public int getScore() {
        return this.score;
    }

    @Override
    public List<Player> getPlayer() {
        final List<Player> players = new ArrayList<>();
        players.add(this.player1);
        if (this.mode.equals(GameMode.MULTIPLAYER)) {
           players.add(this.player2.get());
        }
        return players;
    }

    @Override
    public List<Bullet> getBullets() {
        return this.bullets;
    }

    /**
     * @return the list of enemies in the game
     */
    public Optional<List<AbstractEnemy>> getEnemies() {
        return this.enemies;
    }

    /**
     * @return the list of enemies's shots in the game
     */
    public List<Shot> getShots() {
        if (this.mode.equals(GameMode.SINGLEPLAYER)) {
            return this.shots.get();
        } else {
            return null;
        }
    }

    /**
     * @return the list of obstacles currently in game
     */
    public List<AbstractObstacle> getObstacles() {
        return this.obstacles.get();
    }

    /**
     * @return the list of playerPowerUps
     */
    public List<PlayerPowerUp> getPlayerPowerUps() {
        return this.playerPowerUps;
    }

    /**
     * @param globalPowerUp the powerUp that has spawned
     */
    public void setGlobalPowerUp(final GlobalPowerUp globalPowerUp) {
        this.globalPowerUp = Optional.ofNullable(globalPowerUp);
    }

    /**
     * Sets or unsets freeze.
     */
    public void setFreeze() {
        this.freeze = !this.freeze;
    }
}
