package it.unibo.oop.mge.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;
import it.unibo.oop.mge.libraries.Pair;

public class BracketsResolver extends StringDecorator {
    private ResolveOperator funcRewritten;
    private Pair<Integer, Integer> numBrackets;
    private String fstring;
    private List<Pair<Integer, Integer>> bracketsPlace = new ArrayList<>();
    private Pair<Integer, Integer> currentPosPar = null;

    public BracketsResolver(final Object str) {
        super(str);
        this.fstring = str.toString();
    }

    private void listAdder() {
        bracketsPlace.clear();
        numBrackets = BracketsUtility.countBrackets(fstring);
        if (numBrackets.getFst() != 0) {
            IntStream.range(0, fstring.length()).filter(i -> fstring.charAt(i) == '(').forEach(i -> bracketsPlace
                    .add(new Pair<Integer, Integer>(i, BracketsUtility.endBracket(fstring.substring(i), i))));
        }
    }

    private Pair<Integer, Integer> findMinAndSkim() {
        Optional<Integer> minDistance = Optional.empty();
        Pair<Integer, Integer> result = null;
        for (int k = 0; k < bracketsPlace.size(); k++) {
            Pair<Integer, Integer> currentBracket = bracketsPlace.get(k);
            if (currentBracket.getFst() != 0 && Character.isLetter(fstring.charAt(currentBracket.getFst() - 1))) {
                bracketsPlace.remove(k);
                k--;
            } else {
                int currentDistance = currentBracket.getSnd() - currentBracket.getFst();
                if (minDistance.isEmpty()) {
                    minDistance = Optional.of(currentDistance);
                    result = currentBracket;
                } else if (currentDistance < minDistance.get()) {
                    minDistance = Optional.of(currentDistance);
                    result = currentBracket;
                }
            }
        }
        return result;
    }

    private String getShortestString() {
        listAdder();
        currentPosPar = findMinAndSkim();
        if (currentPosPar != null) {
            return fstring.substring(currentPosPar.getFst() + 1, currentPosPar.getSnd());
        } else {
            return fstring;
        }
    }

    private String attacher(final String str) {
        if (currentPosPar != null) {
            return fstring.substring(0, currentPosPar.getFst()) + str + fstring.substring(currentPosPar.getSnd() + 1);
        } else {
            return fstring;
        }
    }

    private void checkError() {
        if (!BracketsUtility.checkBrackets(fstring)) {
            throw new java.lang.IllegalArgumentException();
        }
    }

    private String resolveBrackets() {
        checkError();
        funcRewritten = new ResolveOperatorImpl(fstring);
        listAdder();
        while (bracketsPlace.size() != 0) {
            funcRewritten.setString(getShortestString());
            fstring = attacher(funcRewritten.funcRewriter());
        }
        funcRewritten.setString(fstring);
        fstring = funcRewritten.funcRewriter();
        return fstring;
    }

    protected final String stringParser(final String str) {
        return resolveBrackets();
    }

}
