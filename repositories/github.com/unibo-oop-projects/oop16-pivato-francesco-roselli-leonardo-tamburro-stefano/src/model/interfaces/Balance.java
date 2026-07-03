package model.interfaces;

import java.time.LocalDate;
import java.time.Month;
import java.util.Map;

import model.Pair;
import model.enumerations.OperationType;
import model.enumerations.Status;

public interface Balance {

	public Map<Pair<Integer, Month>, Integer> getMonthlyInscriptions();

	public Status sellProduct(LocalDate date, Product product, int quantity);

	public double getCourrentBalance();

	public Map<Month, Double> getProfitByYear(int year);

	public Map<Month, Integer> getInscriptionsByYear(int year);

	public Map<LocalDate, Map<OperationType, Double>> getFinancialOperation();

	public Map<Pair<Integer, Month>, Double> getMonthlyRevenue();

	public Map<Pair<Integer, Month>, Double> getMonthlyExpenses();



}
