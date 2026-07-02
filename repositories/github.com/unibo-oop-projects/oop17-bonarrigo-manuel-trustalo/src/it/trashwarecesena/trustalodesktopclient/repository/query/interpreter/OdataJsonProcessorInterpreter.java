package it.trashwarecesena.trustalodesktopclient.repository.query.interpreter;

import java.util.List;

import it.trashwarecesena.trustalodesktopclient.repository.metamapping.MetamappingKnowledge;
import it.trashwarecesena.trustalodesktopclient.repository.query.criteria.Criterion;
import it.trashwarecesena.trustalodesktopclient.repository.query.criteria.QueryObject;
import it.trashwarecesena.trustalodesktopclient.utils.ErrorString;

/**
 * An implementation of the {@link Interpreter} interface thought to work upon
 * the https://odata.intel.com json.
 * 
 * @author Manuel Bonarrigo
 *
 */
public final class OdataJsonProcessorInterpreter extends AbstractInterpreter {
    @Override
    public String translate(final QueryObject query) {
        final List<Criterion> criteria = query.getCriterionList();
        criteria.sort(null);
        String jsonQuery = "/API/v1_0/Products/Processors()?&$select=ProductId,ProcessorNumber,CacheKB,ClockSpeedMhz,"
                + "Instruction"
                + "Set,CacheType,Cache,ProductName&$filter=";
        boolean firstPass = true;
        for (final Criterion c : criteria) {
            jsonQuery += (firstPass ? "" : "+and+");
            firstPass = false;
            switch (c.getOperator()) {
            case ALL:
                throw new UnsupportedOperationException("This kind of operation is not allowed on the Odata domain");
            case EQUALS:
                jsonQuery += extractFieldName(query.getDesiredHandler(), c.getSelector()
                                .orElseThrow(() -> new IllegalStateException(ErrorString.BUG_REPORTING)));
                jsonQuery += "+eq+";
                jsonQuery += isApixEnclosedParameter(c) ? "'" : "";
                jsonQuery += c.getValue().orElseThrow(() -> new IllegalStateException(ErrorString.BUG_REPORTING));
                jsonQuery += isApixEnclosedParameter(c) ? "'" : "";
                break;
            case IS_NULL:
                jsonQuery += extractFieldName(query.getDesiredHandler(), c.getSelector()
                        .orElseThrow(() -> new IllegalStateException(ErrorString.BUG_REPORTING)));
                jsonQuery += "+eq+null";
                break;
            case MATCH:
                if (!(isApixEnclosedParameter(c))) {
                    throw new IllegalStateException("OData domain only allows matching query over String");
                }
                jsonQuery += "substringof(";
                jsonQuery += "'";
                jsonQuery += c.getValue().orElseThrow(() -> new IllegalStateException(ErrorString.BUG_REPORTING));
                jsonQuery += "',";
                jsonQuery += extractFieldName(query.getDesiredHandler(), c.getSelector()
                        .orElseThrow(() -> new IllegalStateException(ErrorString.BUG_REPORTING)));
                jsonQuery += ")";
                break;
            default:
                break;
            }
        }
        jsonQuery = jsonQuery.replaceAll("\\s+", " ");
        jsonQuery = jsonQuery.replaceAll(" ", "+");
        return jsonQuery + "&$format=json";
    }

    private boolean isApixEnclosedParameter(final Criterion c) {
        return c.getValueHandler().get().equals(String.class);
    }

    private String extractFieldName(final Class<?> klass, final String getterName) {
        return MetamappingKnowledge.getMappedFieldName(klass, getterName)
                .orElseThrow(() -> new IllegalArgumentException());
    }
}
