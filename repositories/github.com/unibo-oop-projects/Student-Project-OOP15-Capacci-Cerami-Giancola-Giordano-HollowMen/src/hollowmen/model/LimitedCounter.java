package hollowmen.model;

import hollowmen.model.utils.LowerLimitReachException;
import hollowmen.model.utils.UpperLimitReachException;

/**
 * This interface represents any value that has a upper limit that can't exceed<br>
 * @author pigio
 *
 */
public interface LimitedCounter{
	
	/**
	 * This method give the current value
	 * @return {@code double} value
	 */
	public double getValue();
	
	/**
	 * This method give the limit the value can't exceed
	 * @return {@code double} limit
	 */
	public double getLimit();
	
	/**
	 * This method add <b>value</b> to current value
	 * @param value
	 * @throws IllegalArgumentException If <b>value</b> less than 0
	 * @throws UpperLimitReachException If the limit is reached<br>
	 * NOTE: If currentValue + <b>value</b> would exceed the limit the current value
	 * is set equals to limit
	 */
	public void addToValue(double value) throws IllegalArgumentException, UpperLimitReachException;
	
	/**
	 * This method subtract <b>value</b> to current value
	 * @param value
	 * @throws IllegalArgumentException If <b>value</b> less than 0
	 * @throws LowerLimitReachException If current value sub <b>value</b> would become negative
	 * NOTE: If currentValue - <b>value</b> would drop below 0 the current value
	 * is set equals to 0
	 */
	public void subToValue(double value) throws IllegalArgumentException, LowerLimitReachException;
}
