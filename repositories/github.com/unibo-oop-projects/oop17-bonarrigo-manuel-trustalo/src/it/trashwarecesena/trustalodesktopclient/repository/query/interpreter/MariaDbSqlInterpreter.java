package it.trashwarecesena.trustalodesktopclient.repository.query.interpreter;

import java.net.URL;
import java.sql.Date;
import java.util.List;

import it.trashwarecesena.trustalodesktopclient.model.requests.RequestDetail;
import it.trashwarecesena.trustalodesktopclient.repository.metamapping.MetamappingKnowledge;
import it.trashwarecesena.trustalodesktopclient.repository.query.criteria.Criterion;
import it.trashwarecesena.trustalodesktopclient.repository.query.criteria.Operator;
import it.trashwarecesena.trustalodesktopclient.repository.query.criteria.QueryObject;

/**
 * An implementation of the {@link Interpreter} interface thought to work upon
 * the MariaDB SQL.
 * 
 * @author Manuel Bonarrigo
 *
 */
public final class MariaDbSqlInterpreter extends AbstractInterpreter {

    private static final String SELECT = "SELECT ";
    private static final String ALL = "*";
    private static final String FROM = " FROM ";
    private static final String WHERE = " WHERE ";
    private static final String AND = " AND ";
    private static final String EQUALS = " = ";
    private static final String MATCH = " LIKE ";
    private static final String IN = " IN ";
    private static final String IS_NULL = " IS NULL ";

    @Override
    public String translate(final QueryObject query) {
        return translateToMariaDbSql(query, ALL);
    }

    private String translateToMariaDbSql(final QueryObject query, final String selectList) {
        final Class<?> handler = query.getDesiredHandler();
        final boolean detectedAnomalyClass = RequestDetail.class.isAssignableFrom(handler);
        final List<Criterion> criteria = query.getCriterionList();
        criteria.sort(null);
        String sqlQuery = SELECT + selectList + FROM + MetamappingKnowledge.getMappedEntityName(handler).get();
        boolean firstPass = true;
        for (final Criterion c : criteria) {
            if (isValidCriterion(c)) {
                sqlQuery += (firstPass ? WHERE : AND);
                firstPass = false;
            }
            switch (c.getOperator()) {
                case ALL:
                    sqlQuery = SELECT + selectList + FROM + MetamappingKnowledge.getMappedEntityName(handler).get();
                    break;
                case EQUALS:
                    if (isValidCriterion(c)) {
                        final boolean detectedAnomalySelector = detectedAnomalyClass 
                                && c.getSelector().get().equals("getDeviceCategory");
                        if (MetamappingKnowledge.isMetamappingAvailable(c.getValueHandler().get())) {
                            sqlQuery += MetamappingKnowledge.getMappedFieldName(handler, c.getSelector().get()).get();
                            sqlQuery += IN;
                            sqlQuery += "(";
                            if (detectedAnomalySelector) {
                                sqlQuery += translateToMariaDbSql(extractSubQuery(c), "Acronym");
                            } else {
                                sqlQuery += translateToMariaDbSql(extractSubQuery(c), 
                                        MetamappingKnowledge.getMappedEntityIdentifierField(
                                                c.getValueHandler().get()).get());
                            }
                            sqlQuery += ")";
                        } else {
                            sqlQuery += MetamappingKnowledge.getMappedFieldName(handler, c.getSelector().get()).get();
                            sqlQuery += EQUALS;
                            sqlQuery += isApixEnclosedParameter(c) ? "'" : "";
                            sqlQuery += checkBoolean(c.getValue().get());
                            sqlQuery += isApixEnclosedParameter(c) ? "'" : "";
                        }
                    }
                    break;
                case IS_NULL:
                        sqlQuery += MetamappingKnowledge.getMappedFieldName(handler, c.getSelector().get()).get();
                        sqlQuery += IS_NULL;
                    break;
                case MATCH:
                    if (isValidCriterion(c)) {
                        final boolean detectedAnomalySelector = detectedAnomalyClass 
                                && c.getSelector().get().equals("getDeviceCategory");
                        if (MetamappingKnowledge.isMetamappingAvailable(c.getValueHandler().get())) {
                            sqlQuery += MetamappingKnowledge.getMappedFieldName(handler, c.getSelector().get()).get();
                            sqlQuery += IN;
                            sqlQuery += "(";
                            if (detectedAnomalySelector) {
                                sqlQuery += translateToMariaDbSql(extractSubQuery(c), "Acronym");
                            } else {
                                sqlQuery += translateToMariaDbSql(extractSubQuery(c), 
                                        MetamappingKnowledge.getMappedEntityIdentifierField(
                                                c.getValueHandler().get()).get());
                            }
                            sqlQuery += ")";
                        } else {
                            sqlQuery += MetamappingKnowledge.getMappedFieldName(handler, c.getSelector().get()).get();
                            sqlQuery += MATCH;
                            sqlQuery += isApixEnclosedParameter(c) ? "'" : "";
                            sqlQuery += "%";
                            sqlQuery += checkBoolean(c.getValue().get());
                            sqlQuery += "%";
                            sqlQuery += isApixEnclosedParameter(c) ? "'" : "";
                        }
                    }
                    break;
                default:
                    break;
                }
            if (c.getOperator()
                 .equals(Operator.ALL)) {
                break;
            }
        }
        return sqlQuery;
    }

    private Object checkBoolean(final Object o) {
        if (Boolean.class.isAssignableFrom(o.getClass())) {
            return ((Boolean) o) ? 1 : 0;
        }
        return o;
    }

    private boolean isApixEnclosedParameter(final Criterion c) {
        return c.getValueHandler().get().equals(String.class)
               || c.getValueHandler().get().equals(Date.class)
               || c.getValueHandler().get().equals(URL.class);
    }

}
