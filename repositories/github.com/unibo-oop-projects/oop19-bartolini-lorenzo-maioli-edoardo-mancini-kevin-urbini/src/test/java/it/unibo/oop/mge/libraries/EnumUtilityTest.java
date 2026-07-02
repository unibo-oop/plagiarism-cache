package it.unibo.oop.mge.libraries;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.util.Arrays;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

public class EnumUtilityTest {
    @Test
    void getSyntaxListTest() {
        assertEquals(Arrays.asList(MathFunction.values()).stream().map(i -> i.getSyntax()).collect(Collectors.toList()),
                new EnumUtilityImpl<MathFunction>(MathFunction.class).getSyntaxList());
        assertEquals(Arrays.asList(Constant.values()).stream().map(i -> i.getSyntax()).collect(Collectors.toList()),
                new EnumUtilityImpl<Constant>(Constant.class).getSyntaxList());
        assertEquals(Arrays.asList(Variable.values()).stream().map(i -> i.getSyntax()).collect(Collectors.toList()),
                new EnumUtilityImpl<Variable>(Variable.class).getSyntaxList());
        assertEquals(Arrays.asList(Digits.values()).stream().map(i -> i.getSyntax()).collect(Collectors.toList()),
                new EnumUtilityImpl<Digits>(Digits.class).getSyntaxList());
        assertEquals(Arrays.asList(Properties.values()).stream().map(i -> i.getSyntax()).collect(Collectors.toList()),
                new EnumUtilityImpl<Properties>(Properties.class).getSyntaxList());
        assertEquals(Arrays.asList(Punctuation.values()).stream().map(i -> i.getSyntax()).collect(Collectors.toList()),
                new EnumUtilityImpl<Punctuation>(Punctuation.class).getSyntaxList());
    }

    @Test
    void getElementTest() {
        Arrays.asList(MathFunction.values()).stream().collect(Collectors.toMap(i -> i, i -> i.getSyntax())).forEach(
                (a, b) -> assertEquals(a, new EnumUtilityImpl<MathFunction>(MathFunction.class).getElement(b)));
        Arrays.asList(Constant.values()).stream().collect(Collectors.toMap(i -> i, i -> i.getSyntax()))
                .forEach((a, b) -> assertEquals(a, new EnumUtilityImpl<Constant>(Constant.class).getElement(b)));
        Arrays.asList(Variable.values()).stream().collect(Collectors.toMap(i -> i, i -> i.getSyntax()))
                .forEach((a, b) -> assertEquals(a, new EnumUtilityImpl<Variable>(Variable.class).getElement(b)));
        Arrays.asList(Digits.values()).stream().collect(Collectors.toMap(i -> i, i -> i.getSyntax()))
                .forEach((a, b) -> assertEquals(a, new EnumUtilityImpl<Digits>(Digits.class).getElement(b)));
        Arrays.asList(Properties.values()).stream().collect(Collectors.toMap(i -> i, i -> i.getSyntax()))
                .forEach((a, b) -> assertEquals(a, new EnumUtilityImpl<Properties>(Properties.class).getElement(b)));
        Arrays.asList(Punctuation.values()).stream().collect(Collectors.toMap(i -> i, i -> i.getSyntax()))
                .forEach((a, b) -> assertEquals(a, new EnumUtilityImpl<Punctuation>(Punctuation.class).getElement(b)));
    }

    @Test
    void containsTest() {
        Arrays.asList(MathFunction.values()).stream().map(i -> i.getSyntax())
                .forEach(a -> assertTrue(new EnumUtilityImpl<MathFunction>(MathFunction.class).enumContains(a)));
        Arrays.asList(Constant.values()).stream().map(i -> i.getSyntax())
                .forEach(a -> assertTrue(new EnumUtilityImpl<Constant>(Constant.class).enumContains(a)));
        Arrays.asList(Variable.values()).stream().map(i -> i.getSyntax())
                .forEach(a -> assertTrue(new EnumUtilityImpl<Variable>(Variable.class).enumContains(a)));
        Arrays.asList(Digits.values()).stream().map(i -> i.getSyntax())
                .forEach(a -> assertTrue(new EnumUtilityImpl<Digits>(Digits.class).enumContains(a)));
        Arrays.asList(Properties.values()).stream().map(i -> i.getSyntax())
                .forEach(a -> assertTrue(new EnumUtilityImpl<Properties>(Properties.class).enumContains(a)));
        Arrays.asList(Punctuation.values()).stream().map(i -> i.getSyntax())
                .forEach(a -> assertTrue(new EnumUtilityImpl<Punctuation>(Punctuation.class).enumContains(a)));
    }
}
