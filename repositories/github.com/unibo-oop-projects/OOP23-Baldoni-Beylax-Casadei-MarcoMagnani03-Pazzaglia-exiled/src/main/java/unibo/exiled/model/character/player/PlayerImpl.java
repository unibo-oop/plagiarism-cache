package unibo.exiled.model.character.player;

import java.util.Optional;
import java.util.Random;

import unibo.exiled.model.move.MagicMove;
import unibo.exiled.utilities.ConstantsAndResourceLoader;
import unibo.exiled.model.character.GameCharacterImpl;
import unibo.exiled.model.character.attributes.AttributeFactoryImpl;
import unibo.exiled.model.character.attributes.AttributeIdentifier;
import unibo.exiled.model.item.Inventory;
import unibo.exiled.model.item.InventoryImpl;
import unibo.exiled.model.item.Item;
import unibo.exiled.model.item.utilities.ItemsContainer;
import unibo.exiled.model.move.factory.MoveSetFactoryImpl;
import unibo.exiled.model.move.Moves;
import unibo.exiled.utilities.ElementalType;
import unibo.exiled.utilities.Inventories;
import unibo.exiled.model.item.utilities.ItemType;
import unibo.exiled.model.item.UsableItem;

/**
 * This class represents the implementation of the player in the game.
 */
public final class PlayerImpl extends GameCharacterImpl implements Player {
    private static final Random RANDOM = new Random();
    private static final int NEW_MOVE_TRY = 50;
    private final int attributeIncBound;
    private final Inventory inventory;
    private final int movesLearningInterval;
    private final int maxMovesNumber;
    private int level;
    private int currentExp;
    private int expCap;

    /**
     * Constructs a new player with specified attributes, experience cap, initial
     * experience, and learning intervals.
     *
     * @param experienceCap          The maximum experience points required for a
     *                               level up.
     * @param attributeIncreaseBound The max increment value for each level up.
     * @param movesLearningInterval  The interval at which the player learns new
     *                               magical moves.
     */
    public PlayerImpl(final int experienceCap, final int attributeIncreaseBound,
            final int movesLearningInterval) {
        super(ConstantsAndResourceLoader.PLAYER_NAME, new MoveSetFactoryImpl().defaultNormalMoveSet(),
                ElementalType.NORMAL, new AttributeFactoryImpl().createPlayerAttributes());
        this.inventory = initializeInventory();
        this.expCap = experienceCap;
        this.currentExp = 0;
        this.level = ConstantsAndResourceLoader.PLAYER_STARTING_LEVEL;
        this.attributeIncBound = attributeIncreaseBound;
        this.movesLearningInterval = movesLearningInterval;
        this.maxMovesNumber = ConstantsAndResourceLoader.PLAYER_MOVES_NUMBER;
    }

    /**
     * Initializes the player starting inventory.
     * 
     * @return The starting inventory of the player.
     */
    private Inventory initializeInventory() {
        final Inventory inventory = new InventoryImpl();
        inventory.addItem(ItemsContainer.getRandomItemByType(ItemType.HEALTH).orElseThrow());
        inventory.addItem(ItemsContainer.getRandomItemByType(ItemType.POWERUP).orElseThrow());
        return inventory;
    }

    //GETTER

    @Override
    public int getLevel() {
        return this.level;
    }

    @Override
    public int getExperience() {
        return this.currentExp;
    }

    @Override
    public int getCapExperience() {
        return this.expCap;
    }

    @Override
    public Inventory getInventory() {
        return Inventories.copyOf(this.inventory);
    }

    //SETTER

    @Override
    public void setPlayerClass(final ElementalType playerClassChoice) {
        this.setType(playerClassChoice);
    }

    //OTHERS

    private void learnNewMove() {
        if (shouldLearnNewMove()) {
            final Optional<MagicMove> newMove = getNewMove();
            addNewMoveToPlayer(newMove);
        }
    }

    private boolean shouldLearnNewMove() {
        return this.level % movesLearningInterval == 0;
    }

    private void addNewMoveToPlayer(final Optional<MagicMove> newMove) {
        if (newMove.isPresent()) {
            if (this.getMoveSet().getMagicMoves().size() >= this.maxMovesNumber) {
                this.setExceedingMagicMove(newMove);
            } else {
                this.addMagicMove(newMove.get());
            }
        }
    }

    @Override
    public Optional<MagicMove> getNewMove() {
        int maxAttempts = NEW_MOVE_TRY;
        while (maxAttempts > 0) {
            final Optional<MagicMove> newMove = Moves.getRandomMagicMoveByType(getType(), this.level);

            if (!this.getMoveSet().getMagicMoves().contains(newMove.get())) {
                return newMove;
            }

            maxAttempts--;
        }
        maxAttempts = NEW_MOVE_TRY;
        while (maxAttempts > 0) {
            final Optional<MagicMove> newMove = Optional.of(Moves.getTotallyRandomMove(this.level));

            if (!this.getMoveSet().getMagicMoves().contains(newMove.get())) {
                return newMove;
            }

            maxAttempts--;
        }
        return Optional.empty();
    }

    @Override
    public void addExperience(final int exp) {
        this.currentExp += exp;
        levelUp();
    }

    /**
     * Checks and triggers a level up if the player's experience points exceed the
     * experience cap.
     */
    private void levelUp() {
        while (this.currentExp >= this.expCap) {
            performLevelUp();
        }
    }

    /**
     * Handles the player's level up, updating attributes and learning a new
     * MagicMove.
     */
    private void performLevelUp() {
        this.level++;
        this.currentExp -= expCap;
        this.expCap = calculateNextLevelExperience();
        learnNewMove();
        boostAttributes();
    }

    private int calculateNextLevelExperience() {
        return (int) (this.expCap + this.expCap * ConstantsAndResourceLoader.LEVEL_MODIFIER);
    }

    /**
     * Boosts various attributes of the player upon leveling up.
     */
    private void boostAttributes() {
        this.increaseAttributeModifier(AttributeIdentifier.ATTACK,
                RANDOM.nextInt(attributeIncBound) / ConstantsAndResourceLoader.ATTRIBUTE_INCREMENT_MODULATOR);
        this.increaseAttributeModifier(AttributeIdentifier.DEFENSE,
                RANDOM.nextInt(attributeIncBound) / ConstantsAndResourceLoader.ATTRIBUTE_INCREMENT_MODULATOR);
        this.increaseAttributeValue(AttributeIdentifier.HEALTHCAP, RANDOM.nextInt(attributeIncBound));
    }

    @Override
    public void useItem(final UsableItem item) {
        item.use(this);
        inventory.removeItem(item);
    }

    @Override
    public void addItemToInventory(final Item item) {
        this.inventory.addItem(item);
    }
}
