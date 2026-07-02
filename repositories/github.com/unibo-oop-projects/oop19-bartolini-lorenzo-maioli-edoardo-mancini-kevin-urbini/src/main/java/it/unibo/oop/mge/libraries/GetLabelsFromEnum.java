package it.unibo.oop.mge.libraries;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class GetLabelsFromEnum {
    private static final Map<String, String> OPERATORS = Map.of(MathFunction.SUM.getSyntax(), "+",
            MathFunction.SOT.getSyntax(), "-", MathFunction.MUL.getSyntax(), "*", MathFunction.DIV.getSyntax(), "/");

    private GetLabelsFromEnum() {
    };

    public static List<String> getLabelFromConstants() {
        return new EnumUtilityImpl<Constant>(Constant.class).getSyntaxList();
    }

    public static List<String> getLabelFromMathFunctions() {
        return new EnumUtilityImpl<MathFunction>(MathFunction.class).getSyntaxList().stream()
                .map(i -> OPERATORS.containsKey(i) ? OPERATORS.get(i) : i).collect(Collectors.toList());
    }

    public static List<String> getLabelFromDigits() {
        return new EnumUtilityImpl<Digits>(Digits.class).getSyntaxList();
    }

    public static List<String> getLabelFromPunctuation() {
        return new EnumUtilityImpl<Punctuation>(Punctuation.class).getSyntaxList();
    }

    public static List<String> getLabelFromVariables() {
        return new EnumUtilityImpl<Variable>(Variable.class).getSyntaxList();
    }

    public static List<String> getLabelFromProperties() {
        return new EnumUtilityImpl<Properties>(Properties.class).getSyntaxList();
    }
}
