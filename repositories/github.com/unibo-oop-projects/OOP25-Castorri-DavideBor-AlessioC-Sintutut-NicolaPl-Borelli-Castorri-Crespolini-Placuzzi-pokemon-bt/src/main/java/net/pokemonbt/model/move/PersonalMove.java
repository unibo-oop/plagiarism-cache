package net.pokemonbt.model.move;

import java.io.Serial;
import java.util.List;
import java.util.Objects;

import net.pokemonbt.model.pokemon.PokeType;
import net.pokemonbt.model.pokemon.Pokemon;
import net.pokemonbt.model.pokemon.components.MoveComponent;

/**
 * This class wraps a {@link Move} and adds values to make it specific
 * to the {@link Pokemon} that can use it.
 */
public class PersonalMove implements Move {
    @Serial
    private static final long serialVersionUID = 1L;

    private final Move move;
    private int pp;
    private boolean isAvailable;

    /**
     * @param move A {@link Move} already correctly created and ready to be wrapped.
     */
    public PersonalMove(final Move move) {
        this(move, move.getMaxPP(), true);
    }

    /**
     * @param move A {@link PersonalMove} to be copied.
     */
    public PersonalMove(final PersonalMove move) {
        this(move.move, move.currentPP(), move.isAvailable());
    }

    /**
     * @param move A {@link Move} already correctly created and ready to be wrapped.
     * @param currentPP An {@link Integer} value to set the current PP's capped to
     *      the max value from the {@link Move}.
     * @param availability A {@link Boolean} to indicate if this object can be used.
     */
    public PersonalMove(final Move move, final int currentPP, final boolean availability) {
        Objects.requireNonNull(move);
        this.move = Move.copyOf(move);

        this.pp = Integer.min(currentPP, move.getMaxPP());
        this.isAvailable = availability;
    }

    /**
     * @return The current PPs of this {@link Move}.
     */
    public int currentPP() {
        return this.pp;
    }

    /**
     * @return 'true' when this {@link Move} is usable.
     */
    public boolean isAvailable() {
        return this.isAvailable;
    }

    /**
     * @param available The new value.
     * @throws NullPointerException if it is 'null'.
     */
    public void changeAvailability(final boolean available) {
        Objects.requireNonNull(available);
        if (this.pp != 0) {
            this.isAvailable = available;
        }
    }

    /**
     * @param user {@inheritDoc}
     * @param opponent {@inheritDoc}
     * @return {@inheritDoc}
     */
    @Override
    public boolean use(final Pokemon user, final Pokemon opponent) {

        if (this.pp != 0) {
            final boolean success = move.use(user, opponent);

            // The PP is consumed only if the move is being chosen by the player.
            if (user.getMoveComponent().isMoveChoosable()) {
                this.pp--;
                if (this.pp == 0) {
                    this.isAvailable = false;
                }
            }
            return success;
        }
        return false;
    }

    /**
     * @return {@inheritDoc}
     */
    @Override
    public PokeType getType() {
        return this.move.getType();
    }

    /**
     * @return {@inheritDoc}
     */
    @Override
    public MoveCategory getCategory() {
        return this.move.getCategory();
    }

    /**
     * @return {@inheritDoc}
     */
    @Override
    public List<SubCategory> getSubcategory() {
        return this.move.getSubcategory();
    }

    /**
     * @return {@inheritDoc}
     */
    @Override
    public String getID() {
        return this.move.getID();
    }

    /**
     * @return {@inheritDoc}
     */
    @Override
    public String getDescription() {
        return this.move.getDescription();
    }

    /**
     * @return {@inheritDoc}
     */
    @Override
    public boolean isContact() {
        return this.move.isContact();
    }

    /**
     * @return {@inheritDoc}
     */
    @Override
    public int getPriority() {
        return this.move.getPriority();
    }

    /**
     * @return {@inheritDoc}
     */
    @Override
    public int getMaxPP() {
        return this.move.getMaxPP();
    }

    /**
     * @return {@inheritDoc}
     */
    @Override
    public int getPower() {
        return this.move.getPower();
    }

    /**
     * @return {@inheritDoc}
     */
    @Override
    public float getAccuracy() {
        return this.move.getAccuracy();
    }

    /**
     * @return {@inheritDoc}
     */
    @Override
    public boolean isSubcathegory(final SubCategory sub) {
        return this.move.isSubcathegory(sub);
    }

    /**
     * @return {@inheritDoc}
     */
    @Override
    public String getDisplayName() {
        return this.move.getDisplayName();
    }

    /**
     * {@inheritDoc}
     * 
     * @return {@inheritDoc}
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((move == null) ? 0 : move.hashCode());
        result = prime * result + pp;
        return result;
    }

    /**
     * {@inheritDoc}
     * 
     * @param obj {@inheritDoc}
     * @return {@inheritDoc}
     */
    @Override
    public boolean equals(final Object obj) {
        if (Objects.isNull(this) || Objects.isNull(obj) || getClass() != obj.getClass()) {
            return false;
        }

        final PersonalMove other = (PersonalMove) obj;
        Objects.requireNonNull(this.move);
        Objects.requireNonNull(other.move);

        return Objects.equals(this.pp, other.pp)
        && Objects.equals(this.move, other.move);
    }

    /**
     * @return A {@link String} explaining the parameters of the {@link Move} and
     *      it's status in the current {@link MoveComponent}.
     */
    @Override
    public String toString() {
        final String eol = "\n";
        final String availableString = (
            this.isAvailable
                ? "| It is currently AVAILABLE |"
                : "\\ It is currently DISABLED /"
        ).concat(eol);

        return move.toString()
        .concat("Current PP: ").concat(Integer.toString(this.pp)).concat(eol)
        .concat(availableString);
    }
}
