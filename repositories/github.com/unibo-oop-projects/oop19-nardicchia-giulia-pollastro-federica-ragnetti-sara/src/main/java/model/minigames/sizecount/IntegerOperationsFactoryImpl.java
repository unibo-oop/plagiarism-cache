package model.minigames.sizecount;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * The implementation of the {@link IntegerOperationsFactory}.
 *
 */
public class IntegerOperationsFactoryImpl implements IntegerOperationsFactory {

    private final Random rnd;

    /**
     * Simple constructor of {@link IntegerOperationsFactory}.
     * 
     */
    public IntegerOperationsFactoryImpl() {
        this.rnd = new Random();
    }

    private void checkArguments(final List<Integer> arguments) {
        arguments.forEach(e -> {
            if (e == 0) {
                throw new IllegalStateException();
            }
        });
    }

    private Stream<Integer> generateRandomOperand(final int numOfOperands, final int bound) {
        return IntStream.range(0, numOfOperands).mapToObj(i -> rnd.nextInt(bound + 1));
    }

    /*
     * Method to compute random operand for the division cause the result must be an
     * Integer
     */
    private Stream<Integer> generateRandomOperandDivision(final int numOfOperands, final int bound) {
        final Dividers div = new DividersImpl();
        final List<Integer> result = new LinkedList<>();
        Integer num = this.rnd.nextInt(bound + 1) + 1; // zero is not allowed
        result.add(num);
        for (int i = 0; i < numOfOperands; i++) {
            final List<Integer> dividersList = div.getDividers(num);
            final Integer divider = dividersList.get(this.rnd.nextInt(dividersList.size()));
            result.add(divider);
            num = num / divider;

        }
        return result.stream().limit(numOfOperands);
    }

    private String generateToString(final List<Integer> operands, final String separator) {
        String result = Integer.toString(operands.get(0));
        for (int i = 1; i < operands.size(); i++) {
            result = result.concat(separator).concat(Integer.toString(operands.get(i)));
        }
        return result;
    }

    private List<IntegerOperation> generateRandomOperations(final int numOfOperations, final int numOfOperand,
            final int bound) {
        final List<Operator> operators = Arrays.asList(Operator.values());
        final List<IntegerOperation> list = new LinkedList<>();
        for (int i = numOfOperations; i > 0; i--) {
            final Operator op = operators.get(rnd.nextInt(operators.size()));
            switch (op) {
            case SUM:
                list.add(this.addiction(numOfOperand, bound));
                break;
            case SUB:
                list.add(this.subtraction(numOfOperand, bound));
                break;
            case MUL:
                list.add(this.multiplication(numOfOperand, bound));
                break;
            case DIV:
                list.add(this.division(numOfOperand, bound));
                break;
            default:
                break;
            }
        }
        return list;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IntegerOperation addiction(final int numOfOperands, final int bound) {
        this.checkArguments(List.of(numOfOperands, bound));
        final List<Integer> operands = this.generateRandomOperand(numOfOperands, bound).collect(Collectors.toList());
        return new IntegerOperation() {

            @Override
            public Integer getResult() {
                return operands.stream().mapToInt(e -> e).sum();
            }

            @Override
            public String toString() {
                return generateToString(operands, Operator.SUM.toString());
            }
        };
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IntegerOperation subtraction(final int numOfOperands, final int bound) {
        this.checkArguments(List.of(numOfOperands, bound));
        final List<Integer> operands = this.generateRandomOperand(numOfOperands, bound).collect(Collectors.toList());
        return new IntegerOperation() {

            @Override
            public Integer getResult() {
                return operands.stream().reduce((e1, e2) -> e1 - e2).get();
            }

            @Override
            public String toString() {
                return generateToString(operands, Operator.SUB.toString());
            }
        };
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IntegerOperation multiplication(final int numOfOperands, final int bound) {
        this.checkArguments(List.of(numOfOperands, bound));
        final List<Integer> operands = this.generateRandomOperand(numOfOperands, bound).collect(Collectors.toList());
        return new IntegerOperation() {

            @Override
            public Integer getResult() {
                return operands.stream().reduce((e1, e2) -> e1 * e2).get();
            }

            @Override
            public String toString() {
                return generateToString(operands, Operator.MUL.toString());
            }
        };
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IntegerOperation division(final int numOfOperands, final int bound) {
        this.checkArguments(List.of(numOfOperands, bound));
        final List<Integer> operands = this.generateRandomOperandDivision(numOfOperands, bound)
                .collect(Collectors.toList());
        return new IntegerOperation() {

            @Override
            public Integer getResult() {
                return operands.stream().reduce((e1, e2) -> e1 / e2).get();
            }

            @Override
            public String toString() {
                return generateToString(operands, Operator.DIV.toString());
            }
        };
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IntegerOperation sumExpression(final int numOfOperations, final int numOfOperands, final int bound) {
        this.checkArguments(List.of(numOfOperations, numOfOperands, bound));
        final List<IntegerOperation> operations = this.generateRandomOperations(numOfOperations, numOfOperands, bound);
        return new IntegerOperation() {

            @Override
            public Integer getResult() {
                return operations.stream().mapToInt(e -> e.getResult()).sum();
            }

            @Override
            public String toString() {
                String string = new String("(" + operations.get(0).toString() + ")");
                for (int i = 1; i < operations.size(); i++) {
                    string = string.concat(" + " + "(" + operations.get(i).toString() + ")");
                }
                return string;
            }
        };
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IntegerOperation subExpression(final int numOfOperations, final int numOfOperands, final int bound) {
        this.checkArguments(List.of(numOfOperations, numOfOperands, bound));
        final List<IntegerOperation> operations = this.generateRandomOperations(numOfOperations, numOfOperands, bound);
        return new IntegerOperation() {

            @Override
            public Integer getResult() {
                return operations.stream().map(e -> e.getResult()).reduce((e1, e2) -> e1 - e2).get();
            }

            @Override
            public String toString() {
                String string = new String("(" + operations.get(0).toString() + ")");
                for (int i = 1; i < operations.size(); i++) {
                    string = string.concat(" - " + "(" + operations.get(i).toString() + ")");
                }
                return string;
            }
        };
    }

}
