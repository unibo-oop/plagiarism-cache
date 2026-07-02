package it.trashwarecesena.trustalodesktopclient.repository.query.criteria;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import it.trashwarecesena.trustalodesktopclient.repository.metamapping.MetamappingKnowledge;
import it.trashwarecesena.trustalodesktopclient.utils.ErrorString;

/**
 * A concretion over the {@link QueryObject} interface.
 * <p>
 * Given the crucial role this class performs into the system, heavy checks are
 * performed against the parameters passed as input.
 * 
 * @author Manuel Bonarrigo
 *
 */
public final class QueryObjectImpl implements QueryObject {

    private final Class<?> handler;
    private final Criteria selectors;

    /**
     * Constructs a QueryObject to fetch a {@link java.util.Collection} of a particular domain
     * model object.
     * 
     * @param handler
     *            which kind of object this QueryObject is expected to fetch
     * @param selectors
     *            a {@link Criteria} containing the required informations expressed
     *            as {@link Criterion}
     * @throws IllegalArgumentException
     *             if the handler parameter is found to not be part of the domain
     *             model, or if the selectors parameter is found to be built upon
     *             invalid {@link Criterion}
     */
    public QueryObjectImpl(final Class<?> handler, final Criteria selectors) {
        final Class<?> legalInterface = MetamappingKnowledge.discoverDomainModelInterfaceImplemented(handler)
                .orElseThrow(() -> new IllegalArgumentException("The provided class is not part of the domain model"));
        checkCriteriaValidity(legalInterface, selectors);
        this.handler = legalInterface;
        this.selectors = selectors;
    }

    @Override
    public Class<?> getDesiredHandler() {
        return this.handler;
    }

    @Override
    public List<Criterion> getCriterionList() {
        return new ArrayList<>(this.selectors.getCriterionAsSet());
    }

    private void checkCriteriaValidity(final Class<?> handler, final Criteria selectors) {
        for (final Criterion c : selectors.getCriterionAsSet()) {
            if (c.isSelective()) {
                assertLegalSelector(handler, c.getSelector().get());
            }
            if (isFullParametersCriterion(c)) {
                assertLegalSelectorAndValueTypeCombination(handler, c.getSelector().get(), c.getValueHandler());
                assertLegalValue(handler, c.getSelector().get(), c.getValue());
            }
        }
    }

    private void assertLegalValue(final Class<?> handler, final String getterName, final Optional<?> value) {
        try {
            if ((!Optional.class.isAssignableFrom(handler.getMethod(getterName, new Class[0]).getReturnType())) 
                    && (!value.isPresent())) {
                throw new IllegalArgumentException("The method " + getterName + "() from the " + handler.getSimpleName() 
                    + "class would never return null. Insert a valid value instead.");
            }
        } catch (NoSuchMethodException | SecurityException e) {
            throw new IllegalStateException(ErrorString.BUG_REPORTING);
        }
    }

    private void assertLegalSelector(final Class<?> handler, final String getterName) {
        if (!MetamappingKnowledge.isLegalSelector(handler, getterName)) { 
            throw new IllegalArgumentException("A " + getterName + " selector does not seem to exist for the " 
                    + handler.getSimpleName() + " type");
        }
    }

    private boolean isFullParametersCriterion(final Criterion c) {
        return c.getOperator().equals(Operator.EQUALS) || c.getOperator().equals(Operator.MATCH);
    }

    private void assertLegalSelectorAndValueTypeCombination(final Class<?> klass, final String getterName, 
                                                            final Optional<Class<?>> selecteeValue) {
        if (selecteeValue.isPresent() 
                && !MetamappingKnowledge.isLegalSelectorAndValueTypeCombination(klass, getterName, selecteeValue)) {
                throw new IllegalArgumentException("There is no mapping between " + getterName + "and a value of type " 
                        + selecteeValue.get().getSimpleName());
        } 
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((handler == null) ? 0 : handler.hashCode());
        result = prime * result + ((selectors == null) ? 0 : selectors.hashCode());
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
        final QueryObjectImpl other = (QueryObjectImpl) obj;
        if (handler == null) {
            if (other.handler != null) {
                return false;
            }
        } else if (!handler.equals(other.handler)) {
            return false;
        }
        if (selectors == null) {
            if (other.selectors != null) {
                return false;
            }
        } else if (!selectors.equals(other.selectors)) {
            return false;
        }
        return true;
    }
}
