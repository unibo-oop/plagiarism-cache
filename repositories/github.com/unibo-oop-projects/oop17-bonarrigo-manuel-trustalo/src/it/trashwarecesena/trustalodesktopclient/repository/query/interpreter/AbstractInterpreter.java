package it.trashwarecesena.trustalodesktopclient.repository.query.interpreter;

import java.lang.reflect.InvocationTargetException;
import java.util.Optional;
import java.util.Set;

import it.trashwarecesena.trustalodesktopclient.repository.metamapping.MetamappingKnowledge;
import it.trashwarecesena.trustalodesktopclient.repository.query.criteria.CriteriaImpl;
import it.trashwarecesena.trustalodesktopclient.repository.query.criteria.Criterion;
import it.trashwarecesena.trustalodesktopclient.repository.query.criteria.CriterionImpl;
import it.trashwarecesena.trustalodesktopclient.repository.query.criteria.Operator;
import it.trashwarecesena.trustalodesktopclient.repository.query.criteria.QueryObject;
import it.trashwarecesena.trustalodesktopclient.repository.query.criteria.QueryObjectImpl;
import it.trashwarecesena.trustalodesktopclient.utils.ErrorString;

/**
 * An abstraction over the {@link Interpreter} interface to provide common
 * utility methods to any subclass.
 * 
 * @author Manuel Bonarrigo
 *
 */
public abstract class AbstractInterpreter implements Interpreter {

    /**
     * Extract a QueryObject out of a Criterion, to enable further translation from
     * subclasses.
     * 
     * @param criterion
     *            the {@link Criterion} to base the construction of a new
     *            {@link QueryObject} onto
     * @return a fully operative QueryObject based on the single Criterion
     */
    protected final QueryObject extractSubQuery(final Criterion criterion) {
        final Class<?> criterionHandler = criterion.getValueHandler().get();
        final Set<String> possibleSelectors = MetamappingKnowledge.getAvailableSelectors(criterionHandler);
        final CriteriaImpl.Builder builder = new CriteriaImpl.Builder();
        for (final String s : possibleSelectors) {
            try {
                final Object o = criterionHandler.getMethod(s, new Class<?>[0]).invoke(criterion.getValue().get(),
                        new Object[0]);
                switch (criterion.getOperator()) {
                    case ALL:
                        throw new IllegalStateException(ErrorString.BUG_REPORTING);
                    case EQUALS:
                        if (o.getClass().equals(Optional.class)) {
                            try {
                                builder.addCriterion(CriterionImpl.equality(s, Optional.class.cast(o).get()));
                            } catch (Exception e) {
                                continue;
                            }
                        } else {
                            builder.addCriterion(CriterionImpl.equality(s, o));
                        }
                        break;
                    case IS_NULL:
                        throw new IllegalStateException(ErrorString.BUG_REPORTING);
                    case MATCH:
                        if (o.getClass().equals(Optional.class)) {
                            try {
                                builder.addCriterion(CriterionImpl.match(s, Optional.class.cast(o).get()));
                            } catch (Exception e) {
                                continue;
                            }
                        } else {
                            builder.addCriterion(CriterionImpl.match(s, o));
                        }
                        break;
                    default:
                        throw new IllegalStateException(ErrorString.BUG_REPORTING);
                    }
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
                    | NoSuchMethodException | SecurityException e) {
                e.printStackTrace();
                continue;
            }
        }
        return new QueryObjectImpl(criterionHandler, builder.build());
    }

    /**
     * Tells if the {@link Criterion} has any complex meaning to evaluate between
     * translation.
     * 
     * @param c
     *            the Criterion to be evaluated
     * @return true if the Criterion needs to be evaluated further before
     *         translating, false otherwise
     */
    protected boolean isValidCriterion(final Criterion c) {
        return (c.getSelector().isPresent()
                    && c.getValueHandler().isPresent()
                    && c.getValue().isPresent()) 
                || c.getOperator().equals(Operator.IS_NULL);
    }
}
