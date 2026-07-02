package it.unibo.oop.model;

import static it.unibo.oop.utilities.Settings.SCREEN_HEIGHT;
import static it.unibo.oop.utilities.Settings.SCREEN_WIDTH;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import it.unibo.oop.utilities.CharactersSettings;
import it.unibo.oop.utilities.Direction;
import it.unibo.oop.utilities.Position;

public final class GameStateImpl implements GameState {

    private static final int BASIC_DEFAULT = 17;
    private static final int BASIC_SCALE = 4;
    private static final int MONSTERS_DELAY = 130;

    private static final int INVISIBLE_DEFAULT = 3;
    private static final int MONSTER_CAP=50;

    private int monstersCap=0;

    private static final int MAX_COLLECTIBLES = 4;
    private static final int COLLECTIBLES_DELAY = 120;
    
    private static final int BASIC_DISTANCE = (int) (Math.max(SCREEN_WIDTH, SCREEN_HEIGHT)/2 *0.5);
    private static final int INVISIBLE_DISTANCE = (int) (Math.max(SCREEN_WIDTH, SCREEN_HEIGHT)/2 *0.7);

    private int randomCollectiblesDelay = COLLECTIBLES_DELAY;
    private int randomMonstersDelay = MONSTERS_DELAY;

    private static final GameStateImpl SINGLETON = new GameStateImpl();
    private final List<MovableEntity> movableList;
    private final List<AbstractEntity> stableList;
    private Optional<MainCharacter> johnnyCharacter;
    private final Arena gameArena;

    private long updatesNumber;
    private long lastShotFrame;

    /**
     * Constructor that initializes the entire game logic
     */
    private GameStateImpl() {
        this.updatesNumber = 0;
        this.lastShotFrame = 0;
        this.movableList = new ArrayList<>();
        this.stableList = new ArrayList<>();
        this.johnnyCharacter = Optional.empty();
        this.gameArena = Factory.WallFactory.generateArena(SCREEN_HEIGHT, SCREEN_WIDTH);
    }

    /**
     * Singleton function that returns the single instance of the
     * {@link GameStateImpl}
     */
    public static GameStateImpl getInstance() {
        return SINGLETON;
    }

    /**
     * The function that creates the initials {@link Entity} like the
     * {@link MainCharacter} and the {@link Enemy}
     * @param levelNumber The current level to initialize
     */
    public void initialize(final int levelNumber) {
        this.movableList.clear();
        this.stableList.clear();
        this.stableList.addAll(this.gameArena.getBoundsList());
        this.johnnyCharacter = Optional.ofNullable(
                Factory.MainCharacterFactory.generateCentredCharacter(this.getArena().getPlayableRectangle()));
        monstersCap = MONSTER_CAP;
        this.spawnBasicMonsters(BASIC_DEFAULT);
        this.spawnInvisibleMonsters(INVISIBLE_DEFAULT);
    }

    /**
     * A method that creates a defined number of {@link BasicMonster} in N free
     * random position
     * @param number The number of enemies to spawn
     */
    private void spawnBasicMonsters(final int number) {
        BasicMonster tmpMonster;
        Position randomPos;
        long monsterConfilicts;
        boolean distanceCondition;
        this.monstersCap -= number;
        for (int nMonsters = 0; nMonsters < number; nMonsters++) {
            do {
                randomPos = this.gameArena.getPositionInside(CharactersSettings.BASIC_ENEMY);
                tmpMonster = Factory.EnemiesFactory.generateStillBasicEnemy(randomPos.getX(), randomPos.getY());
                final BasicMonster finalMonster = tmpMonster;
                monsterConfilicts = this.movableList.stream().filter(x -> x.intersecate(finalMonster))
                        .filter(x -> x instanceof AbstractEnemy).count();
                distanceCondition = Position.pointsDistance(this.getMainChar().get().getPosition(),
                        tmpMonster.getPosition()) > BASIC_DISTANCE ? false : true;
            } while (monsterConfilicts != 0 || distanceCondition);
            this.addMovableEntity(tmpMonster);
        }
    }

    /**
     * A method that creates a defined number of {@link InvisibleMonster} in N
     * free random position
     * @param number The number of enemies to spawn
     */
    private void spawnInvisibleMonsters(final int number) {
        this.monstersCap -= number;
        InvisibleMonster tmpMonster;
        Position randomPos;
        long monsterConfilicts;
        boolean distanceCondition;

        for (int nMonsters = 0; nMonsters < number; nMonsters++) {
            do {
                randomPos = this.gameArena.getPositionInside(CharactersSettings.INVISIBLE_ENEMY);
                tmpMonster = Factory.EnemiesFactory.generateStillInvisibleEnemy(randomPos.getX(), randomPos.getY());
                final InvisibleMonster finalMonster = tmpMonster;
                monsterConfilicts = this.movableList.stream().filter(x -> x.intersecate(finalMonster))
                        .filter(x -> x instanceof AbstractEnemy).count();
                distanceCondition = Position.pointsDistance(this.getMainChar().get().getPosition(),
                        tmpMonster.getPosition()) > INVISIBLE_DISTANCE ? false : true;
            } while (monsterConfilicts != 0 || distanceCondition);
            this.addMovableEntity(tmpMonster);
        }
    }

    /**
     * Spawns a random {@link HealthBonus} and puts it in the stable
     * {@link List}
     */
    private void spawnRandomHealthCollectable() {
        final Position randomPos = this.getArena().getPositionInside(CharactersSettings.BONUS);
        this.addStableEntity(new HealthBonus(randomPos.getX(), randomPos.getY()));
    }

    /**
     * Spawns a random {@link ScoreBonus} and puts it in the stable {@link List}
     */
    private void spawnRandomScoreCollectable() {
        final Position randomPos = this.getArena().getPositionInside(CharactersSettings.BONUS);
        this.addStableEntity(new ScoreBonus(randomPos.getX(), randomPos.getY()));
    }

    /**
     * If an {@link Entity} is dead and it should not be seen it gets removed
     * from the environment
     */
    private void removeDeadEntities() {
        this.stableList.removeAll(this.stableList.stream().filter(x -> x.isDead()).collect(Collectors.toList()));
        this.movableList.removeAll(this.movableList.stream().filter(x -> x.isDead()).collect(Collectors.toList()));
    }

    /**
     * Updates all the positions of the {@link MovableEntity} in the lists.
     * @param newDirection next direction where the {@link MainCharacter} has to move
     * @param isShooting Boolean flag that indicates if the {@link MainCharacter} has to shoot
     */
    public void updatePositions(final Direction newDirection, final boolean isShooting) {
        this.updatesNumber++;
        movableList.stream().forEach(x -> x.update());
        this.updateHeroPos(newDirection, isShooting);
        this.removeDeadEntities();

        final long collectibleCount = this.stableList.stream().filter(x -> x instanceof Collectable).count();
        if (this.updatesNumber % (COLLECTIBLES_DELAY + randomCollectiblesDelay) == 0
                && collectibleCount < MAX_COLLECTIBLES) {
            randomCollectiblesDelay = new Random().nextInt(COLLECTIBLES_DELAY);
            if (new Random().nextInt(3) == 0) {
                this.spawnRandomHealthCollectable();
            } else {
                this.spawnRandomScoreCollectable();
            }
        }
        if (this.updatesNumber % (MONSTERS_DELAY + randomMonstersDelay) == 0 && this.monstersCap >= 0) {
            randomMonstersDelay = new Random().nextInt(MONSTERS_DELAY);
            this.spawnBasicMonsters(BASIC_SCALE);
        }
    }

    /**
     * Adds a {@link Bullet} to the MovableList of {@link MovableEntity}
     * @param newBullet {@link Bullet} to add
     */
    protected void addShoot(final Bullet newBullet) {
        final long deltaTime = this.updatesNumber - this.lastShotFrame;
        if (deltaTime >= 6) {
            this.lastShotFrame = this.updatesNumber;
            this.movableList.add(newBullet);
        }
    }

    /**
     * Updates the {@link MainCharacter} basing the new movement on the keys
     * pressed
     * 
     * @param newDirection next direction where the {@link MainCharacter} has to move
     * @param isShooting Boolean flag that indicates if the {@link MainCharacter} has to shoot
     * 
     */
    protected void updateHeroPos(final Direction newDirection, final boolean isShooting) {
        johnnyCharacter.ifPresent(c -> {
            c.setInput(newDirection, isShooting);
            c.update();
        });
    }

    /**
     * Adds a {@link MovableEntity} to the movableList
     * @param newEntity Entity {@link Movable} to add
     */
    protected void addMovableEntity(final MovableEntity newEntity) {
        this.movableList.add(newEntity);
    }

    /**
     * Add to the stableList the stable entities like {@link HealthBonus} and
     * {@link Wall}
     * @param newEntity Entity {@link AbstractEntity} to add
     */
    public void addStableEntity(final AbstractEntity newEntity) {
        this.stableList.add(newEntity);
    }

    /**
     * Gets a {@link List} of {@link AbstractEntity} that contains the
     * {@link Entity} that can't be moved
     * @return The list of stable objects
     */
    public List<AbstractEntity> getStableList() {
        return new ArrayList<>(this.stableList);
    }

    /**
     * Gets a {@link List} of {@link MovableEntity} that contains the
     * {@link Entity} that can be moved
     * @return The list of {@link Movable} objects
     */
    public List<MovableEntity> getMovableList() {
        return new ArrayList<>(this.movableList);
    }

    /**
     * Getter of the {@link Optional} of the main character
     * @return The {@link Optional} of the {@link MainCharacter}
     */
    public Optional<MainCharacter> getMainChar() {
        return this.johnnyCharacter;
    }

    /**
     * Gets the {@link Arena} of the Game
     * @return The game {@link Arena}
     */
    public Arena getArena() {
        return this.gameArena;
    }

    /**
     * Game ending flag getter
     * @return If the game ended
     */
    public boolean isGameEnded() {
        final boolean noneEnemy = this.movableList.stream().filter(e -> e instanceof Enemy).collect(Collectors.toList())
                .isEmpty();
        return noneEnemy || this.johnnyCharacter.isPresent() && this.johnnyCharacter.get().isDead();
    }

    /**
     * Record setter
     */
    public void checkTopScore() {
        final Score score = this.johnnyCharacter.get().getScore();
        final Record record = RecordImpl.getInstance();
        if (score.compareTo(record.getValue()) >= 0) {
            record.setRecord(score);
        } else {
            record.setValue(RecordImpl.getInstance().getValue());
        }
    }

    /**
     * Getter for the score
     * @return The current {@link MainCharacter} {@link Score}
     */
    public Score getScore() {
        return this.johnnyCharacter.get().getScore();
    }
}