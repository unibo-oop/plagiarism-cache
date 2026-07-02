package fargoal.model.entity.player.impl;

import java.util.Random;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import fargoal.commons.api.Position;
import fargoal.controller.input.api.InputComponent;
import fargoal.controller.input.api.KeyboardInputController;
import fargoal.controller.input.api.PlayerInputComponent;
import fargoal.model.commons.Timer;
import fargoal.model.entity.commons.api.Health;
import fargoal.model.entity.commons.impl.HealthImpl;
import fargoal.model.entity.monsters.api.Monster;
import fargoal.model.entity.player.api.Player;
import fargoal.model.events.impl.BattleEvent;
import fargoal.model.events.impl.ReceiveAttackEvent;
import fargoal.model.interactable.pickupable.inside_chest.spell.api.SpellType;
import fargoal.model.interactable.temple.Temple;
import fargoal.model.manager.api.FloorManager;
import fargoal.view.api.RenderFactory;
import fargoal.view.api.Renderer;
import fargoal.view.api.View;
import fargoal.view.impl.InventoryInformationRenderer;
import fargoal.view.impl.PlayerInformationRenderer;
import fargoal.model.entity.player.api.Gold;
import fargoal.model.entity.player.api.Inventory;

/**
 * Manages the player's state, actions, and interactions within the game world.
 * This includes handling movement, combat mechanics, health management,
 * inventory operations, and interactions with other entities.
 * <p>
 * The class also tracks the player's status, such as whether they are engaged
 * in battle, their ability to flee, and the effects of recieved or inflicted damage.
 * </p>
 */
public class PlayerImpl implements Player {

    private static final int REGENERATION_MODIFIER_TEMPLE_AND_SPELL = 5;
    private static final int BASE_REGENERATION_PERIOD = 10_000;
    private static final int NUMBER_OF_A_SIX_FACED_DICES_FACES = 6;
    private static final int MIN_SKILL_REWARD = 1;
    private static final int MAX_SKILL_REWARD = 5;
    private static final int CONST_TO_MAXHEALTH_LEVELUP = 4;
    private static final int MAX_LEVELUP_MAXHEALTH = 15;
    private static final int MIN_LEVELUP_MAXHEALTH = 1;
    private static final int INITIAL_EXPERIENCE_POINTS_REQUIRED = 200;
    private static final int INITIAL_STAT_MAX_COUNTER = 3;
    private static final int DAMAGE_MULTIPLIER = 4;
    private static final int MINIMUM_DAMAGE = 1;
    private static final int INITIAL_LEVEL = 1;
    private static final long MILLIS_BETWEEN_MOVES = 150;

    private final InputComponent input;
    private final KeyboardInputController controller;
    private Position position;
    private Integer level;
    private Integer experiencePoints;
    private Integer experiencePointsRequired;
    private final Health health;
    private Integer skill;
    private final Gold gold;
    private final Inventory inventory;
    private Integer numberOfSlainFoes;
    private final Timer moveTimer;
    private long regenerationTimer;
    private int attackCounter;
    private int deepestDescent;

    private boolean hasSword;
    private boolean isFighting;
    private boolean isAttacked;
    private boolean isVisible;
    private boolean hasLight;
    private boolean isImmune;

    private final PlayerInformationRenderer playerInformationRender;
    private final InventoryInformationRenderer infoRenderer;
    private Renderer render;
    private final FloorManager floorManager;
    private final Random random;

    /**
     * Constructs a new player instance with the specified dependencies.
     * 
     * This constructor initializes the player's attributes, inventory, and UI renderers.
     * It also sets up the player's initial health, skill and experience points.
     * Additionally, it starts the passive regeneration system.
     * 
     * @param floorManager - The floor manager that handles the game environment and events.
     * @param controller - The keyboard input controller to manage player input.
     * @param view - The view that needs to know where to print informations. 
     */
    @SuppressFBWarnings(
        value = {"EI_EXPOSE_REP2"},
        justification = "The class that calls this constructor doesn't maintain"
        + " references to the given elements."
        + "The class also needs to work on the same manager as the one given"
        + "so if the one given changes the reference also needs to change"
    )
    public PlayerImpl(final FloorManager floorManager,
        final KeyboardInputController controller,
        final View view) {

        this.random = new Random();
        this.floorManager = floorManager;
        this.input = new PlayerInputComponent();
        this.controller = controller;
        this.level = INITIAL_LEVEL;
        this.experiencePoints = 0;
        this.experiencePointsRequired = INITIAL_EXPERIENCE_POINTS_REQUIRED;
        this.health = new HealthImpl(this.generateInitialStat());
        this.skill = generateInitialStat();
        this.gold = new GoldImpl();
        this.inventory = new InventoryImpl(floorManager);
        this.numberOfSlainFoes = 0;
        this.hasSword = false;
        this.isFighting = false;
        this.isAttacked = false;
        this.isVisible = true;
        this.isImmune = false;
        this.hasLight = false;
        this.deepestDescent = 0;
        this.playerInformationRender = new PlayerInformationRenderer(view, floorManager);
        this.infoRenderer = new InventoryInformationRenderer(view, inventory);
        this.setRender(floorManager.getRenderFactory());
        this.moveTimer = new Timer();
        this.regenerationTimer = System.currentTimeMillis();
    }

    private void setRender(final RenderFactory renderfactory) {
        this.render = renderfactory.playerRenderer(this);
    }

    /**{@inheritDoc} */
    @Override
    public Integer getDeepestDescent() {
        if (floorManager.getFloorLevel() > this.deepestDescent) {
            this.deepestDescent = floorManager.getFloorLevel();
        }
        return this.deepestDescent;
    }

    /**
     * Sets the deepest floor level the player has reached.
     * <p>
     * This method updates the deepest descent value with the provided floor level.
     * It should be used cautiously to ensure consistency with the actual game state.
     * </p>
     * 
     * @param newDeepest - The new deepest floor level to set.
     */
    public void setDeepestDescent(final Integer newDeepest) {
        this.deepestDescent = newDeepest;
    }

    /**{@inheritDoc} */
    @Override
    public void move(final Position newPosition) {
        this.position = newPosition;
    }

    /**
     * Generates the initial stat value by rolling a six-sided die (d6) multiple times.
     * The total value is determined by summing up a series of random d6 rolls.
     * 
     * @return The computed initial stat value as an {@link Integer}.
     */
    private Integer generateInitialStat() {
        Integer stat = 0;
        Integer d6;
        for (int i = 0; i <= INITIAL_STAT_MAX_COUNTER; i++) {
            d6 = this.random.nextInt(1, NUMBER_OF_A_SIX_FACED_DICES_FACES);
            stat = stat + d6;
        }
        return stat;
    }

    /**{@inheritDoc}*/
    @Override
    public void setPlayerSkill(final Integer skill) {
        if (skill == null || skill < 0) {
            throw new IllegalArgumentException("The skill cannot be set to a null or negative value.");
        } else {
            this.skill = skill;
        }
    }

    /**{@inheritDoc}*/
    @Override
    public void increasePlayerSkill(final Integer amount) {
        if (amount == null) {
            throw new IllegalArgumentException("The skill cannot be increased of a null value.");
        } else if (amount < 0) {
            throw new IllegalArgumentException("The skill cannot be decreased with this method.");
        } else {
            this.skill = this.skill + amount;
        }
    }

    /**
     * Checks if the player has enough experience points to level up.
     * The player is considered ready to level up if their current experience points
     * are greater than or equal to the experience points required for the next level.
     * 
     * @return {@code true} if the player has enough experience points to level up,
     *         {@code false} otherwise.
     */
    private boolean isLevellingUp() {
        return this.getExperiencePoints() >= this.getExperiencePointsRequired();
    }

    /**{@inheritDoc}*/
    @Override
    public boolean levelUp() {
        if (!this.isLevellingUp()) {
            return false;
        } else {
            this.level++;
            this.health.setMaxHealth(this.health.getMaxHealth() 
                                        + this.random.nextInt(MIN_LEVELUP_MAXHEALTH, MAX_LEVELUP_MAXHEALTH)
                                        + CONST_TO_MAXHEALTH_LEVELUP);
            this.increasePlayerSkill(this.random.nextInt(1, 10));
            this.experiencePointsRequired = this.experiencePointsRequired * 2;
            return true;
        }
    }

    /** {@inheritDoc} */
    @Override
    public void render() {
        this.render.render();
        this.playerInformationRender.render();
        this.infoRenderer.render();
    }

    /**{@inheritDoc}*/
    @Override
    public final Position getPosition() {
        return this.position;
    }

    /**{@inheritDoc}*/
    @Override
    public Integer getLevel() {
        return this.level;
    }

    /**{@inheritDoc}*/
    @Override
    public Integer getExperiencePoints() {
        return this.experiencePoints;
    }

    /**{@inheritDoc}*/
    @Override
    public Integer getExperiencePointsRequired() {
        return this.experiencePointsRequired;
    }

    /**{@inheritDoc}*/
    @Override
    public void addExperiencePoints(final Integer experiencePointsToAdd) {
        if (experiencePointsToAdd == null || experiencePointsToAdd < 0) {
            throw new IllegalArgumentException("The amount of experience points to add cannot be null nor negative.");
        } else {
            this.experiencePoints += experiencePointsToAdd;
        }
    }

    /**{@inheritDoc}*/
    @Override
    public void increaseHealth(final Integer amount) {
        this.health.increaseHealth(amount);
    }

    /**{@inheritDoc}*/
    @Override
    public void decreaseHealth(final Integer amount) {
        this.health.decreaseHealth(amount);
    }

    /**{@inheritDoc}*/
    @Override
    public Integer getCurrentHealth() {
        return this.health.getCurrentHealth();
    }

    /**{@inheritDoc}*/
    @Override
    public Integer getMaxHealth() {
        return this.health.getMaxHealth();
    }

    /**{@inheritDoc}*/
    @Override
    public void setHealth(final Integer amount) {
        this.health.setHealth(amount);
    }

    /**{@inheritDoc}*/
    @Override
    public boolean isHealthy() {
        return this.health.isHealthy();
    }

    /**{@inheritDoc}*/
    @Override
    public Integer getSkill() {
        return this.skill;
    }

    /**{@inheritDoc}*/
    @Override
    public Integer getCurrentGold() {
        return this.gold.getCurrentGold();
    }

    /**{@inheritDoc}*/
    @Override
    public Integer getMaxGoldCapacity() {
        return this.gold.getMaxCapacity();
    }

    /**{@inheritDoc}*/
    @SuppressFBWarnings(
        value = {"EI"},
        justification = "Need to return the current inventory value and not a copy, "
         + "because could be made some operations on it."
         + "All methods to work on inventory are inside the object inventory"
         + " to mantain code readable and not confusing"
    )
    @Override
    public Inventory getInventory() {
        return this.inventory;
    }

    /**{@inheritDoc}*/
    @Override
    public Integer getNumberOfSlainFoes() {
        return this.numberOfSlainFoes;
    }

    /**{@inheritDoc}*/
    @SuppressFBWarnings(
        value = {"EI_EXPOSE_REP"},
        justification = "It needs the reference to the object"
        + " because it needs to work on the object and modify it."
    )
    @Override
    public Gold getPlayerGold() {
        return this.gold;
    }

    /**{@inheritDoc}*/
    @Override
    public String getTag() {
        return "PLAYER";
    }

    /**{@inheritDoc}*/
    @Override
    public boolean hasSword() {
        return this.hasSword;
    }

    /**
     * Checks if the player is currently engaged in combat.
     * This method returns {@code true} if the player is in a fight, otherwise {@code false}.
     * 
     * @return {@code true} if the player is fighting, {@code false} otherwise.
     */
    @Override
    public boolean isFighting() {
        return this.isFighting;
    }

    /**
     * Checks if the player has been attacked.
     * This method returns {@code true} if the player has been attacked, otherwise {@code false}.
     * 
     * @return {@code true} if the player has been attacked, {@code false} otherwise.
     */
    public boolean isAttacked() {
        return this.isAttacked;
    }

    /**{@inheritDoc}*/
    @Override
    public void setIsAttacked(final boolean value) {
        this.isAttacked = value;
    }

    /**{@inheritDoc}*/
    @Override
    public void setHasSword(final boolean condition) {
        this.hasSword = condition;
    }

    /**{@inheritDoc} */
    @Override
    public boolean isVisible() {
        return this.isVisible;
    }

    /**{@inheritDoc} */
    @Override
    public void setIsVisible(final boolean condition) {
        this.isVisible = condition;
    }

    /**{@inheritDoc} */
    @Override
    public boolean hasLight() {
        return this.hasLight;
    }

    /**{@inheritDoc} */
    @Override
    public void setHasLight(final boolean condition) {
        this.hasLight = condition;
    }

    /**
     * Increases the count of enemies defeated by the player.
     * This method increments the number of slain monsters by one.
     */
    public void increaseNumberOfSlainFoes() {
        this.numberOfSlainFoes++;
    }

    /** {@inheritDoc}*/
    @Override
    public void update(final FloorManager floorManager) {
        this.passiveRegeneration();
        if (this.moveTimer.updateTime(floorManager.getTimePassed()) == 0) {
            if (isFighting) {
                if (!isAttacked) {
                    final var pos = this.getPosition();
                    this.input.update(floorManager, this.controller);
                    if (!this.getPosition().equals(pos)) {
                        isFighting = false;
                        floorManager.getMonsters().stream()
                                .filter(Monster::isFighting)
                                .forEach(p -> p.setIsFighting(false));
                    } else {
                        for (final var monster : floorManager.getMonsters()) {
                            if (monster.isFighting()) {
                                this.battle(monster);
                                break;
                            }
                            this.isAttacked = false;
                            this.isFighting = false;
                        }
                    }
                } else {
                    this.battle(floorManager.getMonsters().stream()
                            .filter(Monster::isFighting)
                            .findAny()
                            .get());
                }
            } else {
                this.input.update(floorManager, this.controller);
            }
            this.moveTimer.setTime(MILLIS_BETWEEN_MOVES);
        }
    }

    /** {@inheritDoc}*/
    @Override
    public void setIsImmune(final boolean condition) {
        this.isImmune = condition;
    }

    /** {@inheritDoc}*/
    @Override
    public boolean isImmune() {
        return isImmune;
    }

    /** {@inheritDoc}*/
    @SuppressFBWarnings(
        value = {"NS"},
            justification = "this short circuit logic is studied to be both necessary and not dangerous"
                + "It will not cause errors and the efficiency will not be of impact overall"
    )
    @Override
    public void battle(final Monster monster) {
        if (isAttacked && attackCounter == 0) {
            floorManager.notifyFloorEvent(new ReceiveAttackEvent(monster));
        } else {
            floorManager.notifyFloorEvent(new BattleEvent());
        }
        attackCounter++;
        this.isFighting = true;
        monster.receiveDamage();
        this.receiveDamage(monster);
        if (monster.isDead() | this.isDead()) {
            this.isFighting = false;
            this.isAttacked = false;
            attackCounter = 0;

            this.increasePlayerSkill(this.random.nextInt(MIN_SKILL_REWARD, MAX_SKILL_REWARD));
            final int gainedExp = this.getLevel() * (monster.getSkill() + monster.getMaxHealth());
            this.addExperiencePoints(gainedExp);
            this.levelUp();
            this.increaseNumberOfSlainFoes();
        }
    }


    /**{@inheritDoc}*/
    @Override
    public Integer doDamage(final Monster monster) {
        if (monster == null) {
            throw new IllegalArgumentException("The monster passed to this method can not be null");
        } else {
            final int ratio = this.getSkill() / monster.getSkill();
            return random.nextInt(MINIMUM_DAMAGE, DAMAGE_MULTIPLIER * this.getLevel() * ratio);
        }
    }

    /** {@inheritDoc} */
    @Override
    public void receiveDamage(final Monster monster) {
        if (!this.inventory.getSpellCasted().get(SpellType.SHIELD.getName())) {
            this.health.decreaseHealth(monster.attack());
        } else {
            this.inventory.getSpellCasted().replace(SpellType.SHIELD.getName(), false);
        }
    }

    /**{@inheritDoc}*/
    @Override
    public boolean isDead() {
        final int currentHealth = this.health.getCurrentHealth();
        final int potions = inventory.numberHealingPotions();

        if (!isFighting()) {
            return currentHealth <= 0;
        } else {
            if (currentHealth <= 0 && potions == 0) {
                return true;
            } else if (currentHealth <= 0 && potions > 0) {
                this.inventory.useHealingPotion();
                return this.health.getCurrentHealth() <= 0;
            } else {
                return currentHealth <= 0;
            }
        }
    }

    /**
     * Handles the player's passive health regeneration.
     * <p>
     * This method checks if the player is eligible for regeneration and
     * adjusts the regeneration rate based on active effects and location.
     * The base regeneration period can be reduced if the player is in
     * a temple or has an active regeneration spell. 
     * </p><p>
     * Conditions affecting regeneration:
     * <ul>
     * <li>If the player is fighting, regeneration does not occour</li>
     * <li>If both the regeneration spell is active and the player is in a temple,
     * the regeneration period is reduced to 1/5th of the base rate.</li>
     * <li>If either the regeneration spell is active or the player is in a temple,
     * but not both, the regeneration period is reduced to half.</li>
     * </ul>
     * <p>
     * When the adjusted regeneration period has passed, the player's health
     * increases by 1 and the regeneration timer is reset.
     * </p>
     */
    public final void passiveRegeneration() {

        final long time = System.currentTimeMillis();
        final boolean check = floorManager.getAllElements().stream()
                .filter(p -> p instanceof Temple)
                .findAny()
                .isPresent();

        if (this.isFighting) {
            return;
        }

        final int baseHealingAmount = 1;
        int regenerationPeriod = BASE_REGENERATION_PERIOD;

        if (this.inventory.getSpellCasted().get(SpellType.REGENERATION.getName()) && check && this.isOnTemple()) {
            regenerationPeriod = regenerationPeriod / REGENERATION_MODIFIER_TEMPLE_AND_SPELL;
        } else if ((this.inventory.getSpellCasted().get(SpellType.REGENERATION.getName()) && check && !this.isOnTemple()) 
                    || (!this.inventory.getSpellCasted().get(SpellType.REGENERATION.getName()) && check && this.isOnTemple())) {
            regenerationPeriod = regenerationPeriod / 2;
        }

        if (Math.abs(this.getRegenerationTimer() - time) >= regenerationPeriod) {
            this.health.increaseHealth(baseHealingAmount);
            resetRegenerationTimer();
        }

    }

    /**
     * Resets the regeneration timer to the current system time.
     * <p>
     * This method is called after a successful health regeneration event
     * to ensure that the next regeneration occurs after the appropriate delay.
     * </p>
     */
    private void resetRegenerationTimer() {
        this.regenerationTimer = System.currentTimeMillis();
    }

    /**
     * Retrieves the current regeneration timer value.
     * 
     * @return the timestamp of the last regeneration event in milliseconds.
     */
    private long getRegenerationTimer() {
        return this.regenerationTimer;
    }

    /**
     * Checks if the player is currently at the temple's position.
     * 
     * @return {@code true} if the player is on the temple, {@code false} otherwise.
     */
    private boolean isOnTemple() {
        return this.getPosition().equals(floorManager.getTemple().getPosition());
    }

    /**{@inheritDoc} */
    @Override
    public void useInvisibilitySpell() {
        if (this.getInventory().numberInvisibilitySpells() > 0) {
            this.getInventory().useInvisibilitySpell();
        }
    }

    /**{@inheritDoc} */
    @Override
    public void useTeleportSpell() {
        if (this.getInventory().numberTeleportSpells() > 0) {
            this.getInventory().useTeleportSpell();
        }
    }

    /**{@inheritDoc} */
    @Override
    public void useShieldSpell() {
        if (this.getInventory().numberShieldSpells() > 0) {
            this.getInventory().useShieldSpell();
        }
    }

    /**{@inheritDoc} */
    @Override
    public void useRegenerationSpell() {
        if (this.getInventory().numberRegenerationSpell() > 0) {
            this.getInventory().useRegenerationSpell();
        }
    }

    /**{@inheritDoc} */
    @Override
    public void useDriftSpell() {
        if (this.getInventory().numberDriftSpells() > 0) {
            this.getInventory().useDriftSpell();
        }
    }

    /**{@inheritDoc} */
    @Override
    public void useLightSpell() {
        if (this.getInventory().numberLightSpells() > 0) {
            this.getInventory().useLightSpell();
        }
    }

    /**{@inheritDoc} */
    @Override
    public void useHealingPotion() {
        if (this.getInventory().numberHealingPotions() > 0) {
            this.getInventory().useHealingPotion();
        }
    }

    /**{@inheritDoc} */
    @Override
    public void useBeacon() {
        if (this.getInventory().numberBeacons() > 0) {
            this.getInventory().useBeacon();
        }
    }
}
