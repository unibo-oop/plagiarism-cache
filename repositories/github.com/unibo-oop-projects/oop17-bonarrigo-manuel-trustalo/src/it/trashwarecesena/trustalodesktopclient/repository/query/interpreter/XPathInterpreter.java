package it.trashwarecesena.trustalodesktopclient.repository.query.interpreter;

import it.trashwarecesena.trustalodesktopclient.repository.query.criteria.QueryObject;

/**
 * An implementation of the {@link Interpreter} interface thought to be able to
 * translate into the XPath "language".
 * 
 * @author Manuel Bonarrigo
 *
 */
public final class XPathInterpreter extends AbstractInterpreter {

    @Override
    public String translate(final QueryObject query) {
        return translateToConcreteXPath();
    }

    private String translateToConcreteXPath() {
        return "*";
    }

//    private String translateToXPath(final QueryObject query) {
//        final Class<?> handler = query.getDesiredHandler();
//        final List<Criterion> criteria = query.getCriterionList();
//        criteria.sort(null);
//        String xPathQuery = "";
//        for (final Criterion c : criteria) {
//            switch (c.getOperator()) {
//                case ALL:
//                    xPathQuery = "*";
//                    break;
//                case EQUALS:
//                    if (isValidCriterion(c)) {
//                        if (MetamappingKnowledge.isMetamappingAvailable(c.getValueHandler().get())) {
//                            sqlQuery += translateToMariaDbSql(extractSubQuery(c), 
//                                            MetamappingKnowledge.getMappedEntityIdentifierField(
//                                                    c.getValueHandler().get()).get());
//                        } else {
//
//                        }
//                    }
//                    break;
//                case IS_NULL:
//                    break;
//                case MATCH:
//                    if (isValidCriterion(c)) {
//                        if (MetamappingKnowledge.isMetamappingAvailable(c.getValueHandler().get())) {
//                            sqlQuery += translateToMariaDbSql(extractSubQuery(c), 
//                                            MetamappingKnowledge.getMappedEntityIdentifierField(
//                                                c.getValueHandler().get()).get());
//                        } else {
//
//                        }
//                    }
//                    break;
//                default:
//                    break;
//                }
//            if (c.getOperator().equals(Operator.ALL)) {
//                break;
//            }
//        }
//        System.out.println(xPathQuery);
//        return xPathQuery;
//    }

}
