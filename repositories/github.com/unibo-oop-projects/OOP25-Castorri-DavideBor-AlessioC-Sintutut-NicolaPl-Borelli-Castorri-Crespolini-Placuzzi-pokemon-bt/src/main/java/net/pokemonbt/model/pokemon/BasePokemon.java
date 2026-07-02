package net.pokemonbt.model.pokemon;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import net.pokemonbt.model.battle.DamageInstance;
import net.pokemonbt.model.pokemon.components.StatComponent;
import net.pokemonbt.model.pokemon.components.MoveComponent;
import net.pokemonbt.model.pokemon.components.ConditionComponent;
import net.pokemonbt.model.pokemon.components.ItemComponent;
import net.pokemonbt.utility.Clone;

import java.io.Serial;
import java.util.Objects;

/**
 * Base class for all {@link Pokemon}s, representing those that have no
 * special or particular behaviour.
 */
public class BasePokemon implements Pokemon, Clone<BasePokemon> {
    @Serial
    private static final long serialVersionUID = 1L;
    private static final String SPOTBUGS_EI = "EI_EXPOSE_REP";
    private static final String SPOTBUGS_EI_COMPONENT_MESS = "This value need to be a reference to the original"
            + "component as the architecture needs to call its methods.";

    private final String id;
    private final String displayName;

    private final MoveComponent moveComponent;
    private final StatComponent statComponent;
    private final ConditionComponent conditionComponent;
    private final ItemComponent itemComponent;

    private TeamType teamType;

    /**
     * Do not use this constructor directly. Instead use the resource manager
     * ({@link net.pokemonbt.controller.resources.ResourceManager}) to get all
     * pokemons from a file and take the created pokemons from that.
     *
     * @param id The id of the Pokemon.
     * @param displayName The name of the pokemon displayed to the player in-game.
     * @param moveComponent The {@link MoveComponent} of the Pokemon.
     * @param statComponent The {@link StatComponent} of the Pokemon.
     * @param conditionComponent The {@link ConditionComponent} of the Pokemon.
     * @param itemComponent the {@link ItemComponent} of the Pokemon.
     */
    public BasePokemon(
            final String id,
            final String displayName,
            final MoveComponent moveComponent,
            final StatComponent statComponent,
            final ConditionComponent conditionComponent,
            final ItemComponent itemComponent
    ) {
        Objects.requireNonNull(id);
        Objects.requireNonNull(displayName);
        Objects.requireNonNull(moveComponent);
        Objects.requireNonNull(statComponent);
        Objects.requireNonNull(conditionComponent);
        Objects.requireNonNull(itemComponent);
        if (id.isBlank()) {
            throw new IllegalArgumentException("Pokemon id cannot be empty.");
        }
        if (displayName.isBlank()) {
            throw new IllegalArgumentException("DisplayName cannot be empty.");
        }
        this.id = id;
        this.displayName = displayName;

        this.moveComponent = moveComponent.copyOf();
        this.statComponent = statComponent.copyOf();
        this.conditionComponent = conditionComponent.copyOf();
        this.itemComponent = itemComponent.copyOf();
    }

    /**
     * {@inheritDoc}
     *
     * @param teamType {@inheritDoc}
     */
    @Override
    public void setTeamType(final TeamType teamType) {
        Objects.requireNonNull(teamType);
        this.teamType = teamType;
    }

    /**
     * @return {@inheritDoc}
     */
    @Override
    public TeamType getTeamType() {
        return this.teamType;
    }

    /**
     * @return {@inheritDoc}
     */
    @Override
    public String getID() {
        return this.id;
    }

    /**
     * @return {@inheritDoc}
     */
    @Override
    public String getName() {
        return this.displayName;
    }

    /**
     * {@inheritDoc}
     *
     * @param dmg {@inheritDoc}
     */
    @Override
    public void takeDamage(final DamageInstance dmg) {
        if (!dmg.isBlocked()) {
            this.getStatComponent().removeHealth(dmg.getDamage());
        }
    }

    /**
     * {@inheritDoc}
     *
     * @param amount {@inheritDoc}
     */
    @Override
    public void heal(final int amount) {
        this.getStatComponent().increaseHealth(amount);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    @SuppressFBWarnings(value = SPOTBUGS_EI, justification = SPOTBUGS_EI_COMPONENT_MESS)
    public MoveComponent getMoveComponent() {
        return this.moveComponent;
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    @SuppressFBWarnings(value = SPOTBUGS_EI, justification = SPOTBUGS_EI_COMPONENT_MESS)
    public StatComponent getStatComponent() {
        return this.statComponent;
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    @SuppressFBWarnings(value = SPOTBUGS_EI, justification = SPOTBUGS_EI_COMPONENT_MESS)
    public ConditionComponent getConditionComponent() {
        return this.conditionComponent;
    }

    /**
     * {@inheritDoc}
     * 
     * @return {@inheritDoc}
     */
    @Override
    @SuppressFBWarnings(value = SPOTBUGS_EI, justification = SPOTBUGS_EI_COMPONENT_MESS)
    public ItemComponent getItemComponent() {
        return this.itemComponent;
    }

    /**
     * Converts the Pokemon to a string representation of it, in standard
     * "pokemon showdown" format.
     *
     * @return A string representation of the Pokemon.
     */
    @Override
    public String toString() {
        final String newLine = "\n";
        final StatComponent sc = this.getStatComponent();
        final String displayRow = this.displayName.concat(" (id: \"").concat(this.id).concat("\") ")
                .concat(sc.getPrimaryType().displayAs()).concat("/")
                .concat(sc.getSecondaryType().displayAs()).concat(" ")
                .concat("@ item").concat(newLine);
        final String ability = "Ability: ".concat("ability").concat(newLine);
        final String stats = "Stats: ("
                .concat(" HP: ").concat(String.valueOf(sc.getStat(PokeStatType.HP_MAX)))
                .concat(", Atk: ").concat(String.valueOf(sc.getStat(PokeStatType.ATK)))
                .concat(", Def: ").concat(String.valueOf(sc.getStat(PokeStatType.DEF)))
                .concat(", SpA: ").concat(String.valueOf(sc.getStat(PokeStatType.SPA)))
                .concat(", SpD: ").concat(String.valueOf(sc.getStat(PokeStatType.SPD)))
                .concat(", Spe: ").concat(String.valueOf(sc.getStat(PokeStatType.SPE)))
                .concat(", Eva: ").concat(String.valueOf(sc.getStat(PokeStatType.EVA)))
                .concat(", Acc: ").concat(String.valueOf(sc.getStat(PokeStatType.ACC)))
                .concat(", Wgt: ").concat(String.valueOf(sc.getStat(PokeStatType.WEIGHT)))
                .concat(" )").concat(newLine);
        String moveList = "";
        for (final var move : this.getMoveComponent().getMoveMap().entrySet()) {
            moveList = moveList.concat(" - ")
                    .concat(move.getValue().getDisplayName())
                    .concat(" (").concat(move.getKey()).concat(")")
                    .concat(newLine);
        }
        final String moves = "Moves:".concat(newLine).concat(moveList);
        return displayRow.concat(ability).concat(stats).concat(moves);
    }

    /**
     * @return {@inheritDoc}
     */
    @Override
    public String getDisplayName() {
        return this.displayName;
    }

    /**
     * @return {@inheritDoc}
     */
    @Override
    public String getDescription() {
        return "This is a Pokémon";
    }

    /**
     * @return {@inheritDoc}
     */
    @Override
    public BasePokemon copyOf() {
        final BasePokemon clone = new BasePokemon(
                this.id,
                this.displayName,
                this.moveComponent.copyOf(),
                this.statComponent.copyOf(),
                this.conditionComponent.copyOf(),
                this.itemComponent.copyOf()
        );
        clone.moveComponent.setPokeParent(clone);
        clone.statComponent.setPokeParent(clone);
        clone.conditionComponent.setPokeParent(clone);
        clone.itemComponent.setPokeParent(clone);
        clone.teamType = this.teamType;
        return clone;
    }

    /**
     * {@inheritDoc}
     *
     * @param o {@inheritDoc}
     * @return {@inheritDoc}
     */
    @Override
    public boolean equals(final Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final BasePokemon that = (BasePokemon) o;
        return Objects.equals(this.id, that.id)
                && Objects.equals(this.displayName, that.displayName)
                && Objects.equals(this.moveComponent, that.moveComponent)
                && Objects.equals(this.statComponent, that.statComponent)
                && Objects.equals(this.conditionComponent, that.conditionComponent)
                && Objects.equals(this.itemComponent, that.itemComponent)
                && this.teamType == that.teamType;
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hash(
                this.id,
                this.displayName,
                this.moveComponent,
                this.statComponent,
                this.conditionComponent,
                this.itemComponent,
                this.teamType);
    }
}
