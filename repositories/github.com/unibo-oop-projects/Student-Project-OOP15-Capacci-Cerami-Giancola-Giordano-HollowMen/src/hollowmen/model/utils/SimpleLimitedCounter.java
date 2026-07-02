package hollowmen.model.utils;

import hollowmen.model.LimitedCounter;
import hollowmen.utilities.ExceptionThrower;

public class SimpleLimitedCounter implements LimitedCounter{

	private double value;
	private double limit;
	
	public SimpleLimitedCounter(double startValue, double limit) {
		this.value = startValue;
		this.limit = limit;
	}
	
	@Override
	public double getValue() {
		return this.value;
	}

	@Override
	public double getLimit() {
		return this.limit;
	}

	@Override
	public void addToValue(double value) throws IllegalArgumentException, UpperLimitReachException {
		ExceptionThrower.checkIllegalArgument(value, v -> v < 0);
		this.value = this.value + value;
		if(this.value > this.limit) {
			this.value = this.limit;
			throw new UpperLimitReachException();
		}
	}

	@Override
	public void subToValue(double value) throws IllegalArgumentException, LowerLimitReachException {
		ExceptionThrower.checkIllegalArgument(value, v -> v < 0);
		this.value = this.value - value;
		if(this.value < 0) {
			this.value = 0;
			throw new LowerLimitReachException();
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(limit);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(value);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SimpleLimitedCounter other = (SimpleLimitedCounter) obj;
		if (Double.doubleToLongBits(limit) != Double.doubleToLongBits(other.limit))
			return false;
		if (Double.doubleToLongBits(value) != Double.doubleToLongBits(other.value))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "value=" + value + ", limit=" + limit;
	}

	
	
}
