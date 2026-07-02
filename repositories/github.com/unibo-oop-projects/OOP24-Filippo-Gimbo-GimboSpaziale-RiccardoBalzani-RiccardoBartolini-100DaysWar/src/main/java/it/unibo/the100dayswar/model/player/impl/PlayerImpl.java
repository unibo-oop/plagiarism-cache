package it.unibo.the100dayswar.model.player.impl;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import it.unibo.the100dayswar.commons.utilities.api.ResourceGenerator;
import it.unibo.the100dayswar.commons.utilities.impl.Pair;
import it.unibo.the100dayswar.model.bankaccount.api.BankAccount;
import it.unibo.the100dayswar.model.bankaccount.impl.BankAccountImpl;
import it.unibo.the100dayswar.model.cell.api.Cell;
import it.unibo.the100dayswar.model.cell.impl.CellImpl;
import it.unibo.the100dayswar.model.player.api.Player;
import it.unibo.the100dayswar.model.playeraction.api.GenericPlayerCommand;
import it.unibo.the100dayswar.model.playeraction.impl.MovementUnitCommand;
import it.unibo.the100dayswar.model.playeraction.impl.PurchaseUnitCommand;
import it.unibo.the100dayswar.model.playeraction.impl.UpgradeUnitCommand;
import it.unibo.the100dayswar.model.soldier.api.Soldier;
import it.unibo.the100dayswar.model.tower.api.Tower;
import it.unibo.the100dayswar.model.unit.api.Buyable;
import it.unibo.the100dayswar.model.unit.api.Movable;
import it.unibo.the100dayswar.model.unit.api.Unit;

/**
 * An abstract class that implements the Player interface.
 */
public abstract class PlayerImpl implements Player {
    private static final long serialVersionUID = 1L;

    private final String username;
    private final BankAccount bankAccount;
    private final Set<Unit> units;
    private final Cell spawnPoint;

    /**
     * Constructor for the human player from the given parameters.
     * 
     * @param username   the username of the player
     * @param spawnPoint the spawn point of the player
     */
    public PlayerImpl(final String username, final Cell spawnPoint) {
        this.username = username;
        this.bankAccount = new BankAccountImpl();
        this.units = new HashSet<>();
        this.spawnPoint = new CellImpl(spawnPoint);
    }

    /**
     * Constructor for the human player from the given player.
     * 
     * @param player player to copy
     */
    public PlayerImpl(final Player player) {
        this.username = player.getUsername();
        this.bankAccount = player.getBankAccount();
        this.units = player.getUnits();
        this.spawnPoint = player.getSpawnPoint();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void buyUnit(final Unit unit) {
        final GenericPlayerCommand<Unit> command = new PurchaseUnitCommand();
        command.execute(this, unit);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void upgradeUnit(final Buyable unit) {
        final GenericPlayerCommand<Buyable> command = new UpgradeUnitCommand();
        command.execute(this, unit);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void moveUnit(final Movable unit, final Cell destination) {
        final GenericPlayerCommand<Pair<Movable, Cell>> command = new MovementUnitCommand();
        command.execute(this, new Pair<>(unit, destination));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getUsername() {
        return String.copyValueOf(this.username.toCharArray());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<Unit> getUnits() {
        return new HashSet<>(units);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<Soldier> getSoldiers() {
        return units.stream()
                .filter(u -> u instanceof Soldier)
                .map(u -> (Soldier) u)
                .collect(Collectors.toSet());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<Tower> getTowers() {
        return units.stream()
                .filter(u -> u instanceof Tower)
                .map(u -> (Tower) u)
                .collect(Collectors.toSet());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BankAccount getBankAccount() {
        return new BankAccountImpl(this.bankAccount);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Cell getSpawnPoint() {
        return new CellImpl(this.spawnPoint);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void earnResources(final int amount) {
        this.bankAccount.earn(amount);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addUnit(final Unit unit) {
        this.units.add(unit);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeUnit(final Unit unit) {
        this.units.remove(unit);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void spendResources(final int amount) {
        this.bankAccount.purchase(amount);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(final ResourceGenerator generator) {
        this.earnResources(generator.getAmount());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "Player [username=" + username
                + ", bankAccount=" + bankAccount
                + ", units=" + units
                + ", spawnPoint=" + spawnPoint + "]";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PlayerImpl)) {
            return false;
        }
        final PlayerImpl other = (PlayerImpl) obj;
        return Objects.equals(username, other.username)
                && Objects.equals(spawnPoint, other.spawnPoint);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hash(username, bankAccount, units, spawnPoint);
    }
}
