package hollowmen.model.facade;

import java.util.Map;
import java.util.Optional;

public class InformationDealerImpl implements InformationDealer{
	
	private String name;
	private String description;
	private Map<String,Double> statistic;
	private String state;
	private int amount;
	private String slot;
	
	public InformationDealerImpl(String name, String description, Map<String,Double>statistic, String state, int amount, String slot){
		this.name=name;
		this.description=description;
		this.statistic=statistic;
		this.state=state;
		this.amount=amount;
		this.slot=slot;
	}
	
	public String getName() {
		return this.name;
	}

	public String getDescription() {
		return this.description;
	}

	public Optional<Map<String, Double>> getStat() {
		return Optional.of(this.statistic);
	}

	public String getState() {
		return this.state;
	}

	public int getAmount() {
		return this.amount;
	}

	public String getSlot() {
		return this.slot;
	}
	
}
