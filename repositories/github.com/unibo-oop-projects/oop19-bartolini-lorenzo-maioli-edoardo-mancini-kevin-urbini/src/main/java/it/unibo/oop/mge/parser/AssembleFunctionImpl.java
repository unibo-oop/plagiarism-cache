package it.unibo.oop.mge.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import it.unibo.oop.mge.function.AlgebricFunction;
import it.unibo.oop.mge.function.AlgebricFunctionFactory;
import it.unibo.oop.mge.libraries.Constant;
import it.unibo.oop.mge.libraries.EnumUtilityImpl;
import it.unibo.oop.mge.libraries.MathFunction;
import it.unibo.oop.mge.libraries.Pair;
import it.unibo.oop.mge.libraries.Variable;

public class AssembleFunctionImpl implements AssembleFunction {

    private List<String> getParameters(final String str) {
        int lastVirgola = 0;
        List<String> list = new ArrayList<>();
        Pair<Integer, Integer> parTotali;
        for (int k = 1; k < str.length(); k++) {
            parTotali = BracketsUtility.countBrackets(str.substring(0, k));
            if (str.charAt(k) == ',' && (parTotali.getFst() - parTotali.getSnd()) == 1) {
                list.add(str.substring(lastVirgola + 1, k));
                lastVirgola = k;
            }
        }
        list.add(str.substring(lastVirgola + 1, str.length() - 1));
        return list;
    }

    private boolean checkDigit(final String fstring) {
        return BracketsUtility.countCharacter(fstring, i -> i.equals('.'))
                + BracketsUtility.countCharacter(fstring, i -> Character.isDigit(i)) == fstring.length()
                && BracketsUtility.countCharacter(fstring, i -> i.equals('.')) <= 1;
    }

    private void throwEx() {
        throw new java.lang.IllegalArgumentException();
    }

    public final AlgebricFunction resolveFunction(final String fstring) {
        if (checkDigit(fstring)) {
            return AlgebricFunctionFactory.getValueFunction(Double.valueOf(fstring));
        } else {
            if (BracketsUtility.countCharacter(fstring, i -> i.equals('(')) == 0) {
                if (new EnumUtilityImpl<Variable>(Variable.class).enumContains(fstring)) {
                    return AlgebricFunctionFactory
                            .getParameterFunction(new EnumUtilityImpl<Variable>(Variable.class).getElement(fstring));
                } else {
                    return AlgebricFunctionFactory
                            .getConstantFunction(new EnumUtilityImpl<Constant>(Constant.class).getElement(fstring));
                }
            }
        }
        if (new EnumUtilityImpl<MathFunction>(MathFunction.class).getSyntaxList()
                .contains(fstring.substring(0, fstring.indexOf("(")))
                && (new EnumUtilityImpl<MathFunction>(MathFunction.class)
                        .getElement(fstring.substring(0, fstring.indexOf("(")))
                        .getNParameters() == getParameters(fstring.substring(fstring.indexOf("("))).size())) {
            return AlgebricFunctionFactory.getMathFunction(
                    new EnumUtilityImpl<MathFunction>(MathFunction.class)
                            .getElement(fstring.substring(0, fstring.indexOf("("))),
                    getParameters(fstring.substring(fstring.indexOf("("))).stream().map(i -> resolveFunction(i))
                            .collect(Collectors.toList()));
        } else {
            throwEx();
        }
        return null;
    }
}
