package hollowmen.model;

import java.util.function.BinaryOperator;

/**
 * This interface represents an object that can modify the value of a {@link Parameter},
 * it holds the strategy for modify
 * @author pigio
 *
 */
public interface Modifier {
	
	public enum Operation {
		ADD((x, y) -> x + y),
		MUL((x, y) -> x * y);
		
		private BinaryOperator<Double> op;
		
		private Operation(BinaryOperator<Double> op) {
			this.op = op;
		}
		
		public BinaryOperator<Double> getOp() {
			return this.op;
		}
		
	}
	
	/**
	 * This method give the target {@code Parameter} and the value
	 * @return {@link Parameter} target
	 */
	public Parameter getParameter();
	
	/**
	 * This method give the strategy for modify the target {@code Parameter}
	 * @return {@link BinaryOperator}{@code <Double>} what operation will modify the target {@link Parameter}
	 */
	public BinaryOperator<Double> getOperation();
	
	/**
	 * This method gives the strategy for revert the modify done to {@code Parameter}
	 * @return {@link BinaryOperator}{@code <Double>} what operation will reverts the {@link Parameter}
	 */
	public BinaryOperator<Double> getReverseOperation();
}
