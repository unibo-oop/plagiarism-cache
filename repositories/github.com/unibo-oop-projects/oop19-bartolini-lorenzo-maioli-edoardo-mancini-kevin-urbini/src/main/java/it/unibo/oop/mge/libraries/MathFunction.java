package it.unibo.oop.mge.libraries;

import java.util.List;
import java.util.Locale;

/**
 * The Enum MathFunction.
 */
public enum MathFunction implements GenericEnum {
    /**
     * Sum two numbers.
     */
    SUM(2) {
        @Override
        protected Double calculate(final List<Double> parameters) {
            return parameters.get(0) + parameters.get(1);
        }
    },
    /**
     * Subtract two numbers.
     */
    SOT(2) {
        @Override
        protected Double calculate(final List<Double> parameters) {
            return parameters.get(0) - parameters.get(1);
        }
    },
    /**
     * Multiply two numbers.
     */
    MUL(2) {
        @Override
        protected Double calculate(final List<Double> parameters) {
            return parameters.get(0) * parameters.get(1);
        }
    },
    /**
     * Divide two numbers.
     */
    DIV(2) {
        @Override
        protected Double calculate(final List<Double> parameters) {
            return parameters.get(0) / parameters.get(1);
        }
    },
    /**
     * Raise a base to a exponent.
     */
    POW(2) {
        @Override
        protected Double calculate(final List<Double> parameters) {
            return Math.pow(parameters.get(0), parameters.get(1));
        }
    },
    /**
     * Raise the 'e' to a exponent.
     */
    EXP(1) {
        @Override
        protected Double calculate(final List<Double> parameters) {
            return Math.exp(parameters.get(0));
        }
    },
    /**
     * Square root.
     */
    SQRT(1) {
        @Override
        protected Double calculate(final List<Double> parameters) {
            return Math.sqrt(parameters.get(0));
        }
    },
    /**
     * Logarithm with base.
     */
    LOG(2) {
        @Override
        protected Double calculate(final List<Double> parameters) {
            return Math.log(parameters.get(1)) / Math.log(parameters.get(0));
        }
    },
    /**
     * Logarithm with base 'e'.
     */
    LN(1) {
        @Override
        protected Double calculate(final List<Double> parameters) {
            return Math.log(parameters.get(0));
        }
    },
    /**
     * Nth root.
     */
    RTN(2) {
        @Override
        protected Double calculate(final List<Double> parameters) {
            return Math.pow(parameters.get(1), 1 / parameters.get(0));
        }
    },
    /**
     * Absolute value.
     */
    ABS(1) {
        @Override
        protected Double calculate(final List<Double> parameters) {
            return Math.abs(parameters.get(0));
        }
    },
    /**
     * Sine function.
     */
    SIN(1) {
        @Override
        protected Double calculate(final List<Double> parameters) {
            return Math.sin(parameters.get(0));
        }
    },
    /**
     * Cosine function.
     */
    COS(1) {
        @Override
        protected Double calculate(final List<Double> parameters) {
            return Math.cos(parameters.get(0));
        }
    },
    /**
     * Arc cosine function.
     */
    ACOS(1) {
        @Override
        protected Double calculate(final List<Double> parameters) {
            return Math.acos(parameters.get(0));
        }
    },
    /**
     * Arc sine function.
     */
    ASIN(1) {
        @Override
        protected Double calculate(final List<Double> parameters) {
            return Math.asin(parameters.get(0));
        }
    },
    /**
     * Tangent function.
     */
    TAN(1) {
        @Override
        protected Double calculate(final List<Double> parameters) {
            return Math.tan(parameters.get(0));
        }
    },
    /**
     * Arc Tangent function.
     */
    ATAN(1) {
        @Override
        protected Double calculate(final List<Double> parameters) {
            return Math.atan(parameters.get(0));
        }
    },
    /**
     * Hyperbolic cosine.
     */
    COSH(1) {
        @Override
        protected Double calculate(final List<Double> parameters) {
            return Math.cosh(parameters.get(0));
        }
    },
    /**
     * Hyperbolic sine.
     */
    SINH(1) {
        @Override
        protected Double calculate(final List<Double> parameters) {
            return Math.sinh(parameters.get(0));
        }
    },
    /**
     * Hyperbolic tangent.
     */
    TANH(1) {
        @Override
        protected Double calculate(final List<Double> parameters) {
            return Math.tanh(parameters.get(0));
        }
    },
    /**
     * Maximum of two numbers.
     */
    MAX(2) {
        @Override
        protected Double calculate(final List<Double> parameters) {
            return Math.max(parameters.get(0), parameters.get(1));
        }
    },
    /**
     * Minimum of two numbers.
     */
    MIN(2) {
        @Override
        protected Double calculate(final List<Double> parameters) {
            return Math.min(parameters.get(0), parameters.get(1));
        }
    },
    /**
     * Sign function : return 1 if the value is positive, return 0 if the value is
     * zero, return -1 if the value is negative.
     */
    SIGN(1) {
        @Override
        protected Double calculate(final List<Double> parameters) {
            return parameters.get(0) > 0 ? 1.0 : parameters.get(0) < 0 ? -1.0 : 0;
        }
    };

    /** The n parameters. */
    private final int nParameters;

    /**
     * Instantiates a new math function.
     *
     * @param nPar is the number of parameters of the MathFunction.
     */
    MathFunction(final int nPar) {
        this.nParameters = nPar;
    }

    /**
     * Throw an illegalAroumentException.
     */
    private void throwIllArgExc() {
        throw new IllegalArgumentException(
                "Error using MathFunction's Enum : bad number of parameters passed in resolve method");
    }

    /**
     * Gets the numbers of the parameters.
     *
     * @return the numbers of the parameters.
     */
    public int getNParameters() {
        return nParameters;
    }

    /**
     * Gets the syntax of the MathFunction.
     *
     * @return the syntax of the MathFunction.
     */
    public String getSyntax() {
        return this.name().toLowerCase(Locale.getDefault());
    }

    /**
     * Resolve the MathFunction.
     *
     * @param list containing the values of the parameters.
     * @return the double.
     */
    public Double resolve(final List<Double> list) {
        if (list.size() != nParameters) {
            this.throwIllArgExc();
            return null;
        } else {
            return calculate(list);
        }
    }

    /**
     * Calculate the result of the MathFunction.
     *
     * @param parameters containing the values of the parameters.
     * @return the double.
     */
    protected abstract Double calculate(List<Double> parameters);
}
