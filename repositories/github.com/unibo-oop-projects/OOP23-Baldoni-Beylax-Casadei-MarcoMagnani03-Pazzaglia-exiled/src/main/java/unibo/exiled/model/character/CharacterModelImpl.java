package unibo.exiled.model.character;

import java.util.Optional;
import java.util.Random;
import java.util.Set;

import unibo.exiled.model.move.MagicMove;
import unibo.exiled.utilities.ConstantsAndResourceLoader;
import unibo.exiled.model.character.attributes.AdditiveAttribute;
import unibo.exiled.model.character.attributes.Attribute;
import unibo.exiled.model.character.attributes.AttributeIdentifier;
import unibo.exiled.model.character.attributes.CombinedAttribute;
import unibo.exiled.model.character.attributes.MultiplierAttribute;
import unibo.exiled.model.character.enemy.boss.BossEnemy;
import unibo.exiled.model.character.enemy.Enemy;
import unibo.exiled.model.character.enemy.EnemyCollection;
import unibo.exiled.model.character.enemy.EnemyCollectionImpl;
import unibo.exiled.model.character.enemy.factory.EnemyFactory;
import unibo.exiled.model.character.enemy.factory.EnemyFactoryImpl;
import unibo.exiled.model.character.player.Player;
import unibo.exiled.model.character.player.PlayerImpl;
import unibo.exiled.model.game.GameModel;
import unibo.exiled.model.map.CellType;
import unibo.exiled.model.move.MoveSet;
import unibo.exiled.model.move.Moves;
import unibo.exiled.utilities.Direction;
import unibo.exiled.utilities.ElementalType;
import unibo.exiled.utilities.Position;
import unibo.exiled.utilities.Positions;

import javax.annotation.concurrent.Immutable;

/**
 * The character model implementation.
 */
@Immutable
public final class CharacterModelImpl implements CharacterModel {
    private static final Random RANDOM = new Random();
    private static final int RANGE_PLAYER_ENEMY = 2;

    private final GameModel model;
    private final EnemyCollection enemyCollection;
    private final Player player;

    /**
     * The constructor of the character model.
     *
     * @param model The main model of the game.
     */
    public CharacterModelImpl(final GameModel model) {
        this.model = model;

        //Player initialization.
        this.player = new PlayerImpl(ConstantsAndResourceLoader.PLAYER_EXPERIENCE_CAP,
                ConstantsAndResourceLoader.PLAYER_ATTRIBUTE_INCREASE_BOUND,
                ConstantsAndResourceLoader.PLAYER_MOVES_LEARNING_INTERVAL);
        this.player.move(new Position(model.getMapModel().getSize() / 2,
                model.getMapModel().getSize() / 2));

        this.enemyCollection = new EnemyCollectionImpl();
        this.enemyInitialization();

    }

    private void enemyInitialization() {
        Position newEnemyPosition;
        final EnemyFactory factory = new EnemyFactoryImpl();
        for (int i = 0; i < ConstantsAndResourceLoader.NUM_ENEMIES; i++) {
            final Enemy newEnemy = factory.createRandom();
            do {
                newEnemyPosition = new Position(RANDOM.nextInt(model.getMapModel().getSize()),
                        RANDOM.nextInt(model.getMapModel().getSize()));
            } while (!isCellEmpty(newEnemyPosition)
                    || this.model.getMapModel().isCornerOfMap(newEnemyPosition)
                    || !(this.model.getMapModel().getCellType(newEnemyPosition)
                    .getAssociatedType().equals(newEnemy.getType())));
            newEnemy.move(newEnemyPosition);
            this.enemyCollection.addEnemy(newEnemy);
        }
        // Boss creation
        final Enemy bossWater = factory.createWaterBoss();
        bossWater.move(this.model.getMapModel().getCornerOfType(CellType.SWAMP));
        this.enemyCollection.addEnemy(bossWater);
        final Enemy bossFire = factory.createFireBoss();
        bossFire.move(this.model.getMapModel().getCornerOfType(CellType.VOLCANO));
        this.enemyCollection.addEnemy(bossFire);
        final Enemy bossBolt = factory.createBoltBoss();
        bossBolt.move(this.model.getMapModel().getCornerOfType(CellType.STORM));
        this.enemyCollection.addEnemy(bossBolt);
        final Enemy bossGrass = factory.createGrassBoss();
        bossGrass.move(this.model.getMapModel().getCornerOfType(CellType.FOREST));
        this.enemyCollection.addEnemy(bossGrass);
    }

    private boolean isCellEmpty(final Position position) {
        if (!this.model.getMapModel().isInBoundaries(position)) {
            return false;
        }
        return !getPlayerPosition().equals(position) && getEnemies()
                .flatMap(enemies -> enemies.getEnemyFromPosition(position)).isEmpty();
    }

    @Override
    public void movePlayer(final Direction dir) {
        this.player.setLastDirection(dir);
        final Position currentPlayerPosition = this.player.getPosition();
        if (model.getMapModel().isInBoundaries(Positions.sum(currentPlayerPosition, dir.getPosition()))) {
            this.player.move(Positions.sum(currentPlayerPosition, dir.getPosition()));
        }
    }

    @Override
    public void moveEnemies() {
        Direction rndDirection;

        for (final Enemy enemy : this.enemyCollection) {
            if (!(enemy instanceof BossEnemy)) {
                final Position currentEnemyPosition = enemy.getPosition();
                Position newPosition = currentEnemyPosition;
                if (!isEnemyNearThePlayer(enemy)) {
                    do {
                        rndDirection = Direction.values()[RANDOM.nextInt(4)];
                        newPosition = Positions.sum(currentEnemyPosition, rndDirection.getPosition());
                    } while (!this.model.getMapModel().isInBoundaries(newPosition)
                            && isEnemyInCell(newPosition));
                    enemy.setLastDirection(rndDirection);
                } else {
                    /*
                     * If the enemy and the player are close by a certain range of cells,
                     * then the enemy will try to chase the player.
                     */
                    final Position playerPosition = this.player.getPosition();

                    final int distance = calculateDistance(currentEnemyPosition, playerPosition);

                    // This check is used to ensure that the player and the enemy meet when their
                    // distance is equal to 0.
                    if (distance != 0) {
                        final Direction chaseDirection = calculateChaseDirection(currentEnemyPosition, playerPosition);
                        newPosition = Positions.sum(currentEnemyPosition, chaseDirection.getPosition());
                        enemy.setLastDirection(chaseDirection);
                    }
                }
                if (isEnemyInCell(newPosition)
                        && this.model.getMapModel().getCellType(newPosition)
                        .getAssociatedType().equals(enemy.getType())) {
                    enemy.move(newPosition);
                }
            }
        }
    }

    /**
     * Checks if the position is occupied by another enemy.
     *
     * @param newPosition The new calculated position.
     * @return True if the position is free, false otherwise.
     */
    private boolean isEnemyInCell(final Position newPosition) {
        return this.enemyCollection.getEnemies().stream().noneMatch(enemy -> enemy.getPosition().equals(newPosition));
    }

    /**
     * Calculates the distance between two positions.
     *
     * @param pos1 the first position.
     * @param pos2 the second position.
     * @return the distance between the two positions.
     */
    private int calculateDistance(final Position pos1, final Position pos2) {
        final int deltaX = pos1.x() - pos2.x();
        final int deltaY = pos1.y() - pos2.y();
        return (int) Math.sqrt(deltaX * deltaX + deltaY * deltaY);
    }

    /**
     * Calculates the direction in which an enemy should move to chase the player.
     *
     * @param currentEnemyPosition the current position of the enemy.
     * @param playerPosition       the position of the player.
     * @return the direction in which the enemy should move to chase the player.
     */
    private Direction calculateChaseDirection(final Position currentEnemyPosition, final Position playerPosition) {
        final int deltaX = playerPosition.x() - currentEnemyPosition.x();
        final int deltaY = playerPosition.y() - currentEnemyPosition.y();

        if (Math.abs(deltaX) > Math.abs(deltaY)) {
            return deltaX > 0 ? Direction.EAST : Direction.WEST;
        } else {
            return deltaY > 0 ? Direction.SOUTH : Direction.NORTH;
        }
    }

    @Override
    public Optional<Player> getPlayer() {
        return Optional.of(player);
    }

    @Override
    public Optional<EnemyCollection> getEnemies() {
        return Optional.of(enemyCollection);
    }

    @Override
    public double getPlayerAttributeOf(final AttributeIdentifier id) {
        final Attribute selectedAttribute = this.player.getAttributes().get(id);
        if (selectedAttribute.isModifier() && selectedAttribute.isAdditive()) {
            return ((CombinedAttribute) selectedAttribute).getEvaluated();
        } else if (selectedAttribute.isAdditive()) {
            return ((AdditiveAttribute) selectedAttribute).value();
        } else {
            return ((MultiplierAttribute) selectedAttribute).modifier();
        }
    }

    @Override
    public int getPlayerLevel() {
        return player.getLevel();
    }

    @Override
    public int getPlayerCurrentExperience() {
        return player.getExperience();
    }

    @Override
    public int getPlayerExperienceCap() {
        return player.getCapExperience();
    }

    @Override
    public void addPlayerExperience(final int amount) {
        player.addExperience(amount);
    }

    @Override
    public ElementalType getPlayerClass() {
        return player.getType();
    }

    @Override
    public MoveSet getPlayerMoveSet() {
        return player.getMoveSet();
    }

    @Override
    public Position getPlayerPosition() {
        return player.getPosition();
    }

    @Override
    public boolean needsPlayerToChangeMove() {
        return player.getExceedingMagicMove().isPresent();
    }

    @Override
    public Optional<GameCharacter> getCharacterFromPosition(final Position pos) {
        if (enemyCollection.getEnemyFromPosition(pos).isPresent()) {
            return Optional.of(enemyCollection.getEnemyFromPosition(pos).get());
        } else if (player.getPosition().equals(pos)) {
            return Optional.of(player);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public void removeEnemyFromPosition(final Position position) {
        if (enemyCollection.getEnemyFromPosition(position).isPresent()) {
            this.enemyCollection.removeEnemy(enemyCollection.getEnemyFromPosition(position).get());
        }
    }

    @Override
    public void assignPlayerClass(final ElementalType playerClass) {
        player.setPlayerClass(playerClass);
    }

    /**
     * Checks if the specified enemy is near the player.
     *
     * @param enemy the enemy to check.
     * @return if the enemy is near the player.
     */
    private boolean isEnemyNearThePlayer(final Enemy enemy) {
        final Position playerPosition = this.player.getPosition();
        final Position enemyPosition = enemy.getPosition();
        final int verticalDistance = Math.abs(playerPosition.y() - enemyPosition.y());
        final int horizontalDistance = Math.abs(playerPosition.x() - enemyPosition.x());
        return verticalDistance <= RANGE_PLAYER_ENEMY && horizontalDistance <= RANGE_PLAYER_ENEMY;
    }

    @Override
    public Set<MagicMove> getMagicMoves() {
        return Moves.getAllMagicMoves();
    }
}
