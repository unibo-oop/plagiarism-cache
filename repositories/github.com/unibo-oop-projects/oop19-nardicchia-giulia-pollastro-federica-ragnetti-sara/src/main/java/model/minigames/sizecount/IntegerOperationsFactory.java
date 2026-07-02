package model.minigames.sizecount;

/**
 * 
 * A factory interface for mathematical operations.
 *
 */
public interface IntegerOperationsFactory {

    /**
     * Generate an addition between random Integer.
     * 
     * @param numOfOperand the number of operands involved in the operation
     * @param bound        the max value that a number can take in the operation
     * @return a {@link IntegerOperation} which is an addiction
     */
    IntegerOperation addiction(int numOfOperand, int bound);

    /**
     * Generate an subtraction between random Integer.
     * 
     * @param numOfOperand the number of operands involved in the operation
     * @param bound        the max value that a number can take in the operation
     * @return a {@link IntegerOperation} which is a subtraction
     */
    IntegerOperation subtraction(int numOfOperand, int bound);

    /**
     * Generate an multiplication between random Integer.
     * 
     * @param numOfOperand the number of operands involved in the operation
     * @param bound        the max value that a number can take in the operation
     * @return a {@link IntegerOperation} which is a multiplication
     */
    IntegerOperation multiplication(int numOfOperand, int bound);

    /**
     * Generate an division between random Integer.
     * 
     * @param numOfOperand the number of operands involved in the operation
     * @param bound        the max value that a number can take in the operation
     * @return a {@link IntegerOperation} which is a division
     */
    IntegerOperation division(int numOfOperand, int bound);

    /**
     * Generate an expression of random {@link IntegerOperation} united by an addiction.
     * 
     * @param numOfOperations the number of random IntegerOperation involved in the
     *                        expression
     * @param numOfOperand    the number of operands involved in each
     *                        {@link IntegerOperation} that compose the expression
     * @param bound           the max value that a number can take in the operation
     * @return a {@link IntegerOperation} which is a sum Expression
     */
    IntegerOperation sumExpression(int numOfOperations, int numOfOperand, int bound);

    /**
     * Generate an expression of random {@link IntegerOperation} united by a subtraction.
     * 
     * @param numOfOperations the number of random {@link IntegerOperation} involved in the
     *                        expression
     * @param numOfOperand    the number of operands involved in each
     *                        IntegerOperation that compose the expression
     * @param bound           the max value that a number can take in the operation
     * @return a {@link IntegerOperation} which is a sum Expression
     */
    IntegerOperation subExpression(int numOfOperations, int numOfOperand, int bound);

}
