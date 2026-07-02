package unibo.exiled.controller;

import java.util.Optional;
import java.util.Random;
import java.util.Set;

import javax.annotation.concurrent.Immutable;
import javax.swing.Timer;

import unibo.exiled.model.character.GameCharacter;
import unibo.exiled.model.character.attributes.AttributeIdentifier;
import unibo.exiled.model.character.attributes.MultiplierAttribute;
import unibo.exiled.model.character.enemy.Enemy;
import unibo.exiled.model.character.player.Player;
import unibo.exiled.model.combat.CombatModel;
import unibo.exiled.model.combat.CombatStatus;
import unibo.exiled.model.item.Item;
import unibo.exiled.model.move.MagicMove;
import unibo.exiled.utilities.ConstantsAndResourceLoader;
import unibo.exiled.utilities.ElementalType;
import unibo.exiled.utilities.Position;
import unibo.exiled.view.CombatView;

/**
 * Implementation of CombatController interface.
 */
@Immutable
public final class CombatControllerImpl implements CombatController {
    private static final Random RANDOM = new Random();
    private static final Integer CONSOLE_DISPLAY_TIME = 3000;
    private static final Integer IN_BETWEEN_ATTACKS_DELAY = 1500;

    private final CombatModel model;
    private final ConsoleArea consoleArea;

    /**
     * Constructor of CombatControllerImpl.
     *
     * @param model the combat model.
     */
    public CombatControllerImpl(final CombatModel model) {
        this.model = model;
        this.consoleArea = new ConsoleArea();
    }

    @Override
    public void initializeCombat(final Position combatPosition) {
        this.model.newCombat();
        this.setLastMoveLabel("");
        this.setAttackerModifierLabel("");
        this.setDefenderModifierLabel("");
        this.setMoveDescription("");
    }

    @Override
    public String getEnemyName() {
        return this.model.getEnemyName();
    }

    @Override
    public double getEnemyHealth() {
        return this.model.getEnemyHealth();
    }

    @Override
    public double getEnemyHealthCap() {
        return this.model.getEnemyHealthCap();
    }

    @Override
    public String getEnemyClassName() {
        return this.model.getEnemyClassName();
    }

    @Override
    public String getLastMoveLabel() {
        return this.consoleArea.getLastMoveLabel();
    }

    private void setLastMoveLabel(final String label) {
        this.consoleArea.setLastMoveLabel(label);
    }

    @Override
    public String getAttackerModifierLabel() {
        return this.consoleArea.getAttackerModifierLabel();
    }

    private void setAttackerModifierLabel(final String label) {
        this.consoleArea.setAttackerModifierLabel(label);
    }

    @Override
    public String getDefenderModifierLabel() {
        return this.consoleArea.getDefenderModifierLabel();
    }

    private void setDefenderModifierLabel(final String label) {
        this.consoleArea.setDefenderModifierLabel(label);
    }

    @Override
    public String getMoveDescription() {
        return this.consoleArea.getMoveDescription();
    }

    private void setMoveDescription(final String description) {
        this.consoleArea.setMoveDescription(description);
    }

    @Override
    public CombatStatus getCombatStatus() {
        return this.model.getCombatStatus();
    }

    /**
     * Returns the multiplier for the attack based on the move type and the defender
     * type.
     *
     * @param move     the move performed by the attacker.
     * @param defender the defender.
     * @return the multiplier for the attack based on the move type and the defender
     *         type.
     */
    private double getAttackModifierBasedOnType(final MagicMove move, final GameCharacter defender) {
        final ElementalType moveType = move.getType();
        final ElementalType defenderType = defender.getType();
        if (moveType.isStrongAgainst(defenderType)) {
            return ConstantsAndResourceLoader.ATTACK_MODIFIER_EFFECTIVE;
        } else if (defenderType.isStrongAgainst(moveType)) {
            return ConstantsAndResourceLoader.ATTACK_MODIFIER_INEFFECTIVE;
        } else {
            return ConstantsAndResourceLoader.NEUTRAL_MODIFIER;
        }
    }

    private String getEnemyRandomMoveName() {
        final Set<MagicMove> moves = this.model.getEnemyMoves();
        final int randomIndex = RANDOM.nextInt(moves.size());
        int i = 0;
        for (final MagicMove magicMove : moves) {
            if (i == randomIndex) {
                return magicMove.name();
            }
            i++;
        }
        return moves.stream().findFirst().get().name();
    }

    @Override
    public boolean needsPlayerToChangeMove() {
        return this.model.needsPlayerToChangeMove();
    }

    @Override
    public void attack(final boolean isPlayerAttacking, final Optional<String> playerMoveName,
            final GameController gameController, final CombatView combatView) {
        this.model.setCombatStatus(CombatStatus.ATTACKING);

        final GameCharacter attacker = isPlayerAttacking ? this.model.getPlayer().get()
                : this.model.getEnemy().get();
        final GameCharacter defender = !isPlayerAttacking ? this.model.getPlayer().get()
                : this.model.getEnemy().get();

        this.setLastMoveLabel(attacker.getName() + " attacking...");
        this.setAttackerModifierLabel("");
        this.setDefenderModifierLabel("");
        this.setMoveDescription("");
        combatView.draw();

        final String moveName = isPlayerAttacking ? playerMoveName.orElse("")
                : getEnemyRandomMoveName();

        final MagicMove move = attacker.getMoveSet()
                .getMagicMoves()
                .stream()
                .filter(m -> m.name().equals(moveName))
                .findFirst()
                .get();

        final double baseDamage = move.getPower();
        final double defenderDefenseModifier = ((MultiplierAttribute) defender.getAttributes()
                .get(AttributeIdentifier.DEFENSE)).modifier();
        final double attackerAttackModifier = ((MultiplierAttribute) attacker.getAttributes()
                .get(AttributeIdentifier.ATTACK))
                .modifier();
        final double moveTypeModifier = attacker.getType().equals(move.getType())
                ? ConstantsAndResourceLoader.ATTACK_SAME_TYPE_OF_CLASS_MODIFIER
                : ConstantsAndResourceLoader.NEUTRAL_MODIFIER;
        final double attackModifierBasedOnType = getAttackModifierBasedOnType(move, defender);
        final double attackModifier = attackerAttackModifier * attackModifierBasedOnType;
        final double damage = baseDamage * attackModifier * moveTypeModifier / defenderDefenseModifier;

        this.setAttackerModifierLabel("Damage multiplier: " + String.format("%.2f", attackModifier));
        this.setDefenderModifierLabel("Defence multiplier: " + String.format("%.2f", defenderDefenseModifier));
        this.setMoveDescription(move.getDescription());

        // Decrease the health of the defender by the damage amount
        defender.decreaseAttributeValue(AttributeIdentifier.HEALTH, damage);

        final boolean hasAttackerWon = defender.getHealth() <= 0;

        if (isPlayerAttacking && hasAttackerWon) {
            // The player killed the enemy
            // Add experience drop from the enemy to the player
            final int experienceDropped = ((Enemy) defender).getDroppedExperience();
            ((Player) attacker).addExperience(experienceDropped);
            gameController.getCharacterController()
                    .removeEnemyFromPosition(this.model.getCombatPosition());

            // Add the item dropped from the enemy
            final Optional<Item> itemDropped = ((Enemy) defender).getHeldItem();
            itemDropped.ifPresent(item -> ((Player) attacker).addItemToInventory(item));
        }

        this.setLastMoveLabel(
                attacker.getName()
                        + " used: "
                        + move.name()
                        + " ("
                        + String.format("%.2f", damage)
                        + ")");

        if (hasAttackerWon) {
            this.setLastMoveLabel(attacker.getName()
                    + " defeated "
                    + defender.getName());
            this.setAttackerModifierLabel("");
            this.setDefenderModifierLabel("");
            this.setMoveDescription("");
            this.model.setCombatStatus(CombatStatus.DEFEATING);
        }

        final Timer attackTimer = new Timer(CONSOLE_DISPLAY_TIME, evt -> {
            combatView.draw();

            if (hasAttackerWon) {
                this.model.setCombatStatus(CombatStatus.DEFEATED);

                final Timer delayTimer = new Timer(IN_BETWEEN_ATTACKS_DELAY, e -> combatView.draw());
                delayTimer.setRepeats(false);
                delayTimer.start();
            } else {
                if (isPlayerAttacking) {
                    final Timer delayTimer = new Timer(IN_BETWEEN_ATTACKS_DELAY, e -> {
                        // Enemy turn to attack
                        this.attack(false, Optional.empty(), gameController, combatView);
                        this.model.setCombatStatus(CombatStatus.IDLE);
                    });
                    delayTimer.setRepeats(false);
                    delayTimer.start();
                }
            }
        });
        attackTimer.setRepeats(false);
        attackTimer.start();
    }

    private static class ConsoleArea {
        private String lastMoveLabel;
        private String attackerModifierLabel;
        private String defenderModifierLabel;
        private String moveDescription;

        ConsoleArea() {
            this.lastMoveLabel = "";
            this.attackerModifierLabel = "";
            this.defenderModifierLabel = "";
            this.moveDescription = "";
        }

        public String getLastMoveLabel() {
            return this.lastMoveLabel;
        }

        public void setLastMoveLabel(final String label) {
            this.lastMoveLabel = label;
        }

        public String getAttackerModifierLabel() {
            return this.attackerModifierLabel;
        }

        public void setAttackerModifierLabel(final String label) {
            this.attackerModifierLabel = label;
        }

        public String getDefenderModifierLabel() {
            return this.defenderModifierLabel;
        }

        public void setDefenderModifierLabel(final String label) {
            this.defenderModifierLabel = label;
        }

        public String getMoveDescription() {
            return this.moveDescription;
        }

        public void setMoveDescription(final String description) {
            this.moveDescription = description;
        }
    }
}
