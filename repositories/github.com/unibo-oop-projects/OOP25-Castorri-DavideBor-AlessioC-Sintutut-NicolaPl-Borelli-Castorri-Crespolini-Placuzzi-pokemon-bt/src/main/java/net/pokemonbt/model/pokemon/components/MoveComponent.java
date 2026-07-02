package net.pokemonbt.model.pokemon.components;

import java.io.Serial;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import net.pokemonbt.model.move.Move;
import net.pokemonbt.model.move.PersonalMove;
import net.pokemonbt.model.pokemon.Pokemon;
import net.pokemonbt.utility.Clone;

/**
 * {@link Pokemon} component that handles move
 * execution when it's the Pokemon's turn.
 */
public final class MoveComponent extends AbstractPokeComponent implements Clone<MoveComponent> {
    @Serial
    private static final long serialVersionUID = 1L;

    private static final String FINAL_MOVE = "struggle"; //This move is usable only when other moves aren't available.

    private final Map<String, PersonalMove> moves;
    private String lastMoveId;
    private String forcedId;

    /**
     * Don't create this component by itself, only
     *      use it inside {@link Pokemon} constructor.
     */
    public MoveComponent() {
        this(Set.of(), "", "");
    }

    /**
     * Don't create this component by itself, only
     *      use it inside {@link Pokemon} constructor.
     * 
     * @param moves All moves the {@link Pokemon} may need throughout the battle.
     * @param lastMoveUsedID The ID of a {@link Move}: to make the component think
     *      the user already used one.
     * @param forcedMoveID The ID of a {@link Move}: to make the component force that
     *      {@link Move}'s use. 
     */
    public MoveComponent(
        final Set<? extends Move> moves,
        final String lastMoveUsedID,
        final String forcedMoveID
    ) {
        this.moves = new LinkedHashMap<>();
        this.setMoveSet(moves);

        if (
            this.moves.containsKey(lastMoveUsedID)
            || lastMoveUsedID.isBlank()
        ) {
            this.lastMoveId = lastMoveUsedID;
        }

        if (
            this.moves.containsKey(forcedMoveID)
            || lastMoveUsedID.isBlank()
        ) {
            this.forcedId = forcedMoveID;
        }
    }

    /**
     * @param moveset All moves the {@link Pokemon} may need throughout the battle.
     */
    public void setMoveSet(final Collection<? extends Move> moveset) {
        if (this.moves.isEmpty()) {
            for (final var move : Objects.requireNonNull(moveset)) {
                if (PersonalMove.class.isInstance(move)) {
                    this.moves.put(move.getID(), new PersonalMove(PersonalMove.class.cast(move)));
                } else {
                    this.moves.put(move.getID(), new PersonalMove(move));
                }
            }
        }
    }

    /**
     * Uses the move chosen by the Player.
     * 
     * @param id From the move.
     * @param opponent The {@link Pokemon} that the selected {@link Move} is being used against.
     * @return 'true' on a successful use, 'false' if the move is not available.
     * @throws IllegalStateException When all moves are missing.
     */
    public boolean useMove(final String id, final Pokemon opponent) {
        if (this.moves.isEmpty()) {
            throw new IllegalStateException("Moves are missing. Set them with the appropriate method before trying to use one.");
        }

        final Optional<PersonalMove> selection = Optional.of(this.moves.get(id));

        if (selection.isPresent() && selection.get().isAvailable()) {
            final boolean success = selection.get()
            .use(this.getPokeParent(), opponent);

            this.lastMoveId = id;
            return success;
        } else {
            throw new IllegalArgumentException(
                "\"".concat(id).concat("\" is not part of ") 
                .concat(this.getPokeParent().getName())
                .concat("'s moveset, or it should not be available for selection.")
            );
        }
    }

    /**
     * @return A {@link Set} containing the moves.
     *      When the conditions are met returns only "Struggle".
     */
    public Set<PersonalMove> getMoveSet() {
        return Collections.unmodifiableSet(
            new LinkedHashSet<>(this.getMoveMap().values())
        );
    }

    /**
     * @return A {@link Map} of the current moveset
     *      with the 'id' as the Key, and the move as value.
     */
    public Map<String, PersonalMove> getMoveMap() {

        final var output = new LinkedHashMap<>(this.moves);

        if (
            this.moves.values()
            .stream()
            .filter(m -> !FINAL_MOVE.equals(m.getID()))
            .anyMatch(PersonalMove::isAvailable)
        ) {
            output.remove(FINAL_MOVE);
        } else {
            for (final var m : this.moves.entrySet()) {
                if (!FINAL_MOVE.equals(m.getKey())) {
                    output.remove(m.getKey());
                }
            }
        }
        return Collections.unmodifiableMap(output);
    }

    /**
     * @return Empty {@link Optional} when no move has been used yet, last move's 'id' otherwise.
     */
    public Optional<String> getLastMoveId() {
        return this.lastMoveId.isBlank()
            ? Optional.empty()
            : Optional.of(this.lastMoveId);
    }

    /**
     * @return Empty {@link Optional} when no move is being forced, the correct id otherwise.
     */
    public Optional<String> getForcedMove() {
        return this.forcedId.isBlank()
            ? Optional.empty()
            : Optional.of(this.forcedId);
    }

    /**
     * @return 'true' if the {@link Move} is choosable, 'false' otherwise.
     */
    public boolean isMoveChoosable() {
        return this.forcedId.isBlank();
    }

    /**
     * @param id Of a {@link Move} the {@link Pokemon} user is forced to use next turn.
     *      If it is not part of the MoveSet no move will be selected,
     *      and the user will be free to choose.
     * 
     * @return 'true' when a {@link Move} is successfully forced. 'false' otherwise.
     */
    public boolean forceMove(final String id) {
        this.forcedId = this.moves.containsKey(id)
            ? id
            : "";

        if (this.forcedId.isBlank()) {
            this.moves.values()
            .stream()
            .filter(m -> m.currentPP() != 0)
            .forEach(m -> this.changeAvailability(m.getID(), true));
        } else {
            this.moves.values()
            .stream()
            .filter(m -> !m.getID().equals(id))
            .forEach(m -> this.changeAvailability(m.getID(), false));
        }
        return !this.forcedId.isEmpty();
    }

    /**
     * Empties the current 'Forced {@link Move}' which allows the user to 
     * freely choose the next one.
     */
    public void clearForcedMove() {
        this.forceMove("");
    }

    /**
     * Changes the availability of a {@link Move} from the moveset. 
     *      A 'disabled' move cannot be used. If all are 'disabled' then "Struggle"
     *      will be the only choice.
     * 
     * @param id of the move to disable.
     * @param available 'true' to make said move available for selection, 'false'
     *      to disable it.
     */
    public void changeAvailability(final String id, final boolean available) {
        if (
            !Objects.requireNonNull(id).isBlank()
            && this.moves.containsKey(id)
            && !FINAL_MOVE.equals(id)
        ) {
            this.moves.get(id).changeAvailability(available);
        }
    }

    /**
     * This method needs to be used EVERY time occures a "Switch-out" of the 
     * current {@link Pokemon}. It clears the records of forced and used moves, 
     * it without resetting their PPs.
     */
    public void switchOut() {
        this.forcedId = "";
        this.lastMoveId = "";
    }

    /**
     * This method needs to be used EVERY time occures a "Switch-in" of the 
     * current {@link Pokemon}. It enables the moves that still have some PPs.
     */
    public void switchIn() {
        this.moves.values()
        .stream()
        .filter(m -> !m.isAvailable() && m.currentPP() != 0)
        .forEach(m -> m.changeAvailability(true));
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
        result = prime * result + ((moves == null) ? 0 : moves.hashCode());
        result = prime * result + ((lastMoveId == null) ? 0 : lastMoveId.hashCode());
        result = prime * result + ((forcedId == null) ? 0 : forcedId.hashCode());
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
        final MoveComponent other = (MoveComponent) obj;
        Objects.requireNonNull(this.moves);
        Objects.requireNonNull(other.moves);

        return Objects.equals(this.lastMoveId, other.lastMoveId)
        && Objects.equals(this.forcedId, other.forcedId)
        && Objects.equals(this.moves, other.moves);
    }

    /**
     * @return {@inheritDoc}
     */
    @Override
    public MoveComponent copyOf() {
        return copyOf(this);
    }

    /**
     * Creates a new Instance of {@link MoveComponent} from 
     *      another one already existing.
     * 
     * @param component The instance to take the values from.
     * @return A new {@link MoveComponent} with the same state of the one passed.
     */
    public static MoveComponent copyOf(final MoveComponent component) {
        Objects.requireNonNull(component);
        return new MoveComponent(
            Set.copyOf(component.moves.values()),
            component.lastMoveId,
            component.forcedId
        );
    }
}
