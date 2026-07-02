package casim.model.wator;

import casim.model.abstraction.cell.AbstractCell;

/**
 * A cell of the Wator Automaton.
 */
//Package-private
class WatorCell extends AbstractCell<WatorCellState> {

    private static final int MIN_HEALTH = 0;
    private static final int MAX_HEALTH = 10;
    private static final int PRED_HEAL = 5;
    private static final int PREY_HEAL = 1;

    private int health;
    private boolean moved;

    //Package-private
    WatorCell(final WatorCellState state, final int health) {
        super(state);
        this.health = health;
    }

    /**
     * Returns the health value of the {@link WatorCell}.
     * @return integer value representing the current health
     * of the {@link WatorCell}
     */
    public int getHealth() {
        return this.health;
    }

    /**
     * Sets the current health value of the {@link WatorCell}.
     * 
     * @param health the integer value to be set as
     * the current health value of the {@link WatorCell}.
     * Must be greater or equal to 0 and smaller or equal to 
     * the maximum health value.
     * 
     * @throws IllegalArgumentException if the value given as
     * argument is less than 0 or greater than the maximum health
     * value.
     */
    public void setHealth(final int health) {
        if (health < MIN_HEALTH || health > MAX_HEALTH) {
            throw new IllegalArgumentException("Heath value out of valid limits: MAX: " + MAX_HEALTH
                    + " MIN: " + MIN_HEALTH + " GIVEN: " + health);
        }
        this.health = health;
    }

    /**
     * Returns true if the {@link WatorCell} has
     * reached minimum health value, false otherwise.
     * 
     * @return true if the {@link WatorCell} has run
     * out of health, false otherwise
     */
    public boolean isDead() {
        return this.health == MIN_HEALTH;
    }

    /**
     * Heals the {@link WatorCell} if health isn't at
     * maximum.
     */
    public void heal() {
        if (this.getState().equals(WatorCellState.DEAD)) {
            return;
        }
        this.health += this.getState().equals(WatorCellState.PREY) ? PREY_HEAL : PRED_HEAL;
        final int overflow = this.health > MAX_HEALTH ? this.health - MAX_HEALTH : 0;
        this.health -= overflow;
    }

    /**
     * Diminishes the health of the {@link WatorCell} by
     * 1 if health isn't at minimum.
     * 
     * @throws IllegalStateException if the state of the cell
     *          is {@link WatorCellState#PREY}.
     */
    public void starve() {
        if (this.getState().equals(WatorCellState.PREY)) {
            throw new IllegalStateException(this.getState() + " cell cannot starve.");
        }
        this.health -= this.isDead() ? 0 : 1;
    }

    /**
     * Returns the {@link WatorCell} that the cell calling
     * the method will leave behind if it moves and updates
     * health of calling cell accordingly.
     * 
     * @return a new {@link WatorCell} with the same state as
     * the one calling the method if it can reproduce, dead
     * otherwise.
     */
    public WatorCell reproduce() {
        if (this.health == MAX_HEALTH) {
            switch (this.getState()) {
                case PREY:
                    this.health = MIN_HEALTH + 1;
                    return new WatorCell(this.getState(), MIN_HEALTH + 1);
                case PREDATOR:
                    this.health = MAX_HEALTH / 2;
                    return new WatorCell(this.getState(), MAX_HEALTH / 2);
                default:
                throw new UnsupportedOperationException("The state " + this.getState() + " has no reproduce operation.");
            }
        } else {
            return new WatorCell(WatorCellState.DEAD, MIN_HEALTH);
        }
    }

    /**
     * Returns true if the {@link WatorCell} can move.
     * 
     * @return true if the cell can perform a movement,
     * false otherwise.
     */
    public boolean hasMoved() {
        return this.moved;
    }

    /**
     * Sets the moved state of the {@link WatorCell}.
     */
    public void setMoved() {
        this.moved = true;
    }

    /**
     * Resets the moved state of the {@link WatorCell}.
     */
    public void resetMovement() {
        this.moved = false;
    }

    /**
     * Copies the state, health and moved fields
     * of another {@link WatorCell} into the
     * calling {@link WatorCell}.
     * 
     * @param otherCell the {@link WatorCell} to
     *          copy the fields from.
     */
    public void clone(final WatorCell otherCell) {
        this.setHealth(otherCell.getHealth());
        this.setState(otherCell.getState());
        this.moved = otherCell.hasMoved();
    }

}
