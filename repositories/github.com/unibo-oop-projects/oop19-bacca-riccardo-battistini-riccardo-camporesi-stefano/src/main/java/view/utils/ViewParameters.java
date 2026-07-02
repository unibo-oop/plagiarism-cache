package view.utils;

import java.util.HashMap;
import java.util.Map;

public final class ViewParameters {

    private Map<ControllerVariablesNames, Double> values = new HashMap<>();

    public ViewParameters() {
    }

    /**
     * @return the values
     */
    public Map<ControllerVariablesNames, Double> getValues() {
        return this.values;
    }

    /**
     * @param values the values to set
     */
    public void setValues(final Map<ControllerVariablesNames, Double> values) {
        this.values = values;
    }
}
