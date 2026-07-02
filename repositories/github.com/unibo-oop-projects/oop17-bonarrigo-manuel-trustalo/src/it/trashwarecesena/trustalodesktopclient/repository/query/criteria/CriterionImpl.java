package it.trashwarecesena.trustalodesktopclient.repository.query.criteria;

import java.util.Objects;
import java.util.Optional;

import it.trashwarecesena.trustalodesktopclient.repository.metamapping.MetamappingKnowledge;
import it.trashwarecesena.trustalodesktopclient.utils.ErrorString;
import it.trashwarecesena.trustalodesktopclient.utils.ExtendedObjects;

/**
 * A concrete implementation of the {@link Criterion} interface. It exposes several factory methods over the 
 * respective constructors to prevent the syntax of the Criterion to be adulterated. 
 * @author Manuel Bonarrigo
 *
 */
public final class CriterionImpl implements Criterion {

    private final Operator operator;
    private final Optional<String> selector;
    private final Optional<Object> value;

    private CriterionImpl(final Operator operator, final String selector, final Object value) {
        this.operator = Objects.requireNonNull(operator);
        this.selector = Optional.ofNullable(selector);
        this.value = Optional.ofNullable(value);
    }

    @Override
    public int compareTo(final Criterion o) {
        return this.operator.compareTo(o.getOperator());
    }

    /**
     * Create a Criterion to express the semantic of selecting all the existent occurrences of whatever the client is 
     * looking for.
     * @return A fully instantiated Criterion, expressing the desire for the whole mapping of something.
     */
    public static Criterion all() {
        return new CriterionImpl(Operator.ALL, null, null);
    }

    /**
     * Create a Criterion to express the semantic of selecting only occurrences with a mapped null value.
     * @param selector 
     *                  the name of the domain model's getter the result is expected to be {@code null}
     * @return A fully instantiated Criterion, expressing the desire for the portion of the mapping with a null value
     */
    public static Criterion withNull(final String selector) {
        return new CriterionImpl(Operator.IS_NULL, ExtendedObjects.requireNonEmpty(
                Objects.requireNonNull(selector, ErrorString.NO_NULL), ErrorString.EMPTY_STRING), null);
    }

    /**
     * Create a Criterion to express the semantic of selecting only occurrences with a value equal to the specified one.
     * The value parameter will be checked to assert it is of a meaningful type. 
     * @param selector 
     *                  the name of the domain model's getter the result is expected to be equals to
     * @param value
     *                  the value which is being looked for
     * @return A fully instantiated Criterion, expressing the desire for the portion of the mapping with a null value
     */
    public static Criterion equality(final String selector, final Object value) {
        return Objects.nonNull(value)
                ? new CriterionImpl(Operator.EQUALS, ExtendedObjects.requireNonEmpty(
                        Objects.requireNonNull(selector, ErrorString.NO_NULL), ErrorString.EMPTY_STRING), value)
                : withNull(selector);
    }

    /**
     * Create a Criterion to express the semantic of selecting only occurrences with a value which contains the 
     * specified one.
     * The value parameter will be checked to assert it is of a meaningful type. 
     * @param selector 
     *                  the name of the domain model's getter the result is expected to be equals to
     * @param value
     *                  the value which is being looked for
     * @return A fully instantiated Criterion, expressing the desire for the portion of the mapping with a null value
     */
    public static Criterion match(final String selector, final Object value) {
        return Objects.nonNull(value)
                ? new CriterionImpl(Operator.MATCH, ExtendedObjects.requireNonEmpty(
                        Objects.requireNonNull(selector, ErrorString.NO_NULL), ErrorString.EMPTY_STRING), value)
                : withNull(selector);
    }

    @Override
    public boolean isSelective() {
        return this.operator.isSelective();
    }

    @Override
    public Operator getOperator() {
        return this.operator;
    }

    @Override
    public Optional<String> getSelector() {
        return this.selector;
    }

    @Override
    public Optional<Object> getValue() {
        return this.value;
    }

    @Override
    public Optional<Class<?>> getValueHandler() {
        if (this.value.isPresent()) {
            if (MetamappingKnowledge.isMetamappingAvailable(this.value.get().getClass())) {
                return MetamappingKnowledge.discoverDomainModelInterfaceImplemented(this.value.get().getClass());
            } else {
                return Optional.of(this.value.get().getClass());
            }
        } else {
            return Optional.empty();
        }
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((operator == null) ? 0 : operator.hashCode());
        result = prime * result + ((selector == null) ? 0 : selector.hashCode());
        result = prime * result + ((value == null) ? 0 : value.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CriterionImpl other = (CriterionImpl) obj;
        if (operator != other.operator) {
            return false;
        }
        if (selector == null) {
            if (other.selector != null) {
                return false;
            }
        } else if (!selector.equals(other.selector)) {
            return false;
        }
        if (value == null) {
            if (other.value != null) {
                return false;
            }
        } else if (!value.equals(other.value)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CriterionImpl [operator=" + operator + ", selector=" + selector.orElse("empty") + ", value=" 
                + value.orElse("empty") + "]";
    }
}
