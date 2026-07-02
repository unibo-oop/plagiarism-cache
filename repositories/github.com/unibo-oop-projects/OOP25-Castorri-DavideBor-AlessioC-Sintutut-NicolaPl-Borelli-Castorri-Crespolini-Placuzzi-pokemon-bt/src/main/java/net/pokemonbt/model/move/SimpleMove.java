package net.pokemonbt.model.move;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import net.pokemonbt.model.move.components.MoveBehaviour;
import net.pokemonbt.model.pokemon.PokeType;
import net.pokemonbt.model.pokemon.Pokemon;
import net.pokemonbt.model.pokemon.components.MoveComponent;

/**
 * Basic implementetion of a {@link Move}, it is initialized once, and then copied
 * in the {@link MoveComponent} of a {@link Pokemon}.
 */
public final class SimpleMove implements Move {
    @Serial
    private static final long serialVersionUID = 1L;

    private final MoveBehaviour behaviourType;
    private final MoveCategory category;
    private final List<SubCategory> subCategory;
    private final PokeType type;

    private final String name;
    private final String id;
    private final String description;
    private final boolean contact;

    private final int power;
    private final int priority;
    private final float accuracy;
    private final int maxPP;

    private SimpleMove(
        final MoveBehaviour behaviourType,
        final MoveCategory category,
        final List<SubCategory> subCategory,
        final PokeType type,
        final String name,
        final String id,
        final String description,
        final boolean contact,
        final int maxPP,
        final int power,
        final int priority,
        final float accuracy
    ) {
        this.category = category;
        this.subCategory = List.copyOf(subCategory);
        this.type = type;
        this.name = name;
        this.id = id;
        this.description = description;
        this.contact = contact;
        this.power = power;
        this.priority = priority;
        this.accuracy = accuracy;
        this.behaviourType = behaviourType;
        this.maxPP = maxPP;
    }

    /**
     * @param user {@inheritDoc}
     * @param opponent {@inheritDoc}
     * @return {@inheritDoc}
     */
    @Override
    public boolean use(final Pokemon user, final Pokemon opponent) {
        return this.behaviourType.apply(user, opponent);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public float getAccuracy() {
        return this.accuracy;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MoveCategory getCategory() {
        return this.category;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getPower() {
        return this.power;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getPriority() {
        return this.priority;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<SubCategory> getSubcategory() {
        return List.copyOf(this.subCategory);
    }

    /**
     * @param sub {@inheritDoc}
     * @return {@inheritDoc}
     */
    @Override
    public boolean isSubcathegory(final SubCategory sub) {
        return this.subCategory.contains(sub);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PokeType getType() {
        return this.type;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDisplayName() {
        return this.name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getID() {
        return this.id;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDescription() {
        return this.description;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isContact() {
        return this.contact;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getMaxPP() {
        return this.maxPP;
    }

    @Override
    public String toString() {
        final String eol = "\n";

        final String text = "Move: ".concat(this.name).concat(eol)
            .concat("Description: \"").concat(this.description).concat("\"".concat(eol))
            .concat(" - ").concat(this.category.categoryType()).concat(" - ").concat(eol)
            .concat("Type: ").concat(this.type.displayAs()).concat(eol);

        final String accuracyString = Float.compare(this.accuracy, 1.0f) > 0
                ? " / cannot miss /"
                : Float.toString(this.accuracy);

        final String contactString = (
            this.contact
                ? "| Makes Contact |"
                : "\\ Does not make Contact /"
            ).concat(eol);

        final String values = "Base power: ".concat(Integer.toString(this.power)).concat(eol)
            .concat("Accuracy: ").concat(accuracyString).concat(eol)
            .concat("Priority: ").concat(Integer.toString(this.priority)).concat(eol)
            .concat("MaxPP: ").concat(Float.toString(this.maxPP)).concat(eol);

            return text.concat(contactString).concat(values);
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
        result = prime * result + ((id == null) ? 0 : id.hashCode());
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

        final SimpleMove other = (SimpleMove) obj;

        return Objects.equals(this.id, other.id)
        && Objects.equals(this.type, other.type);
    }

    /**
     * Static Builder used for an easier construction of the {@link SimpleMove}.
     */
    public static class Builder implements Serializable {
        @Serial
        private static final long serialVersionUID = 1L;

        private static final int MIN_PRIO = -7;
        private static final int MAX_PRIO = 5;
        private static final int MAX_POWER = 250;
        private static final int MAX_PP = 64;
        private static final Float MAX_ACC = 1.1f;

        private MoveBehaviour behaviourType;
        private MoveCategory category = MoveCategory.PHYSICAL;
        private final List<SubCategory> subCategory = new ArrayList<>();
        private PokeType type = PokeType.NONE;

        private String name;
        private String id;
        private String description;
        private Boolean contact = false;

        private int maxPP;
        private int power;
        private int priority;
        private float accuracy = MAX_ACC; //Some moves cannot miss: those have a base acc of 110%.

        /**
         * @param behaviour To set.
         * @return The same instance of this Builder.
         */
        public Builder behaviour(final MoveBehaviour behaviour) {
            this.behaviourType = MoveBehaviour.copyOf(behaviour);
            return this;
        }

        /**
         * @param value To set.
         * @return The same instance of this Builder.
         */
        public Builder category(final MoveCategory value) {
            this.category = value;
            return this;
        }

        /**
         * @param values To set.
         * @return The same instance of this Builder.
         */
        public Builder subCategory(final Collection<SubCategory> values) {
            this.subCategory.addAll(values);
            return this;
        }

        /**
         * @param value To set.
         * @return The same instance of this Builder.
         */
        public Builder type(final PokeType value) {
            this.type = value;
            return this;
        }

        /**
         * @param value To set.
         * @return The same instance of this Builder.
         */
        public Builder name(final String value) {
            this.name = value;
            return this;
        }

        /**
         * @param value To set.
         * @return The same instance of this Builder.
         */
        public Builder description(final String value) {
            this.description = value;
            return this;
        }

        /**
         * @param value To set.
         * @return The same instance of this Builder.
         */
        public Builder id(final String value) {
            this.id = value;
            return this;
        }

        /**
         * @param value To set.
         * @return The same instance of this Builder.
         */
        public Builder contact(final boolean value) {
            this.contact = value;
            return this;
        }

        /**
         * @param value To set.
         * @return The same instance of this Builder.
         */
        public Builder maxPP(final int value) {
            if (value < -1 || value > MAX_PP) {
                throw new IllegalArgumentException("Illegal PP value.");
            }
            this.maxPP = value;
            return this;
        }

        /**
         * @param value To set.
         * @return The same instance of this Builder.
         */
        public Builder power(final int value) {
            if (value < 0 || value > MAX_POWER) {
                throw new IllegalArgumentException("Illegal Power value.");
            }
            this.power = value;
            return this;
        }

        /**
         * @param value To set.
         * @return The same instance of this Builder.
         */
        public Builder priority(final int value) {
            if (value < MIN_PRIO || value > MAX_PRIO) {
                throw new IllegalArgumentException("Illegal Priority value.");
            }
            this.priority = value;
            return this;
        }

        /**
         * @param value To set.
         * @return The same instance of this Builder.
         */
        public Builder accuracy(final float value) {
            if (value < 0 || value > MAX_ACC) {
                throw new IllegalArgumentException("Illegal Accuracy value.");
            }
            this.accuracy = value;
            return this;
        }

        /**
         * @param move to take the values from.
         * @return The same instance of this Builder.
         */
        public Builder prepareCopy(final SimpleMove move) {

            this.behaviour(move.behaviourType)
            .accuracy(move.getAccuracy())
            .category(move.getCategory())
            .contact(move.isContact())
            .description(move.getDescription())
            .id(move.getID())
            .maxPP(move.getMaxPP())
            .name(move.getDisplayName())
            .power(move.getPower())
            .priority(move.getPriority())
            .subCategory(move.getSubcategory())
            .type(move.getType());

            return this;
        }

        /**
         * @return Confirms the parameters and creates the {@link SimpleMove} instance.
         */
        public Move build() {
            return new SimpleMove(
                Objects.requireNonNull(this.behaviourType),
                this.category,
                List.copyOf(this.subCategory),
                this.type,
                this.name,
                Objects.requireNonNull(this.id),
                this.description,
                this.contact,
                Objects.requireNonNull(this.maxPP),
                this.power,
                this.priority,
                this.accuracy
            );
        }
    }
}
