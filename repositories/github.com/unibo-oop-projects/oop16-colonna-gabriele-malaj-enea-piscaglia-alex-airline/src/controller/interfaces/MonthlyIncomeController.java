package controller.interfaces;

import java.util.Map;

/**
 * MonthlyIncomeController interface.
 */
public interface MonthlyIncomeController {

    /**
     * 
     * @return a map containing months and respective income.
     */
    Map<String, Double> createMap();

}