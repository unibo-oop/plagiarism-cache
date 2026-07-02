package it.unibo.oop.mge.parser;

import java.util.function.Function;
import it.unibo.oop.mge.libraries.Pair;

/**
 * This class is an utility for the Parser.
 */
public final class BracketsUtility {

    private BracketsUtility() {
    }

    public static int countCharacter(final String fstring, final Function<Character, Boolean> function) {
        return (int) fstring.chars().mapToObj(i -> (char) i).filter(i -> function.apply(i)).count();
    }

    public static Pair<Integer, Integer> countBrackets(final String str) {
        return new Pair<Integer, Integer>(countCharacter(str, i -> i.equals('(')),
                countCharacter(str, i -> i.equals(')')));
    }

    public static boolean checkBrackets(final String currentString) {
        final Pair<Integer, Integer> numBrackets = countBrackets(currentString);
        return numBrackets.getFst() - numBrackets.getSnd() == 0;
    }

    public static int endBracket(final String str, final int offset) {
        Pair<Integer, Integer> p;
        for (int k = 1; k < str.length(); k++) {
            p = countBrackets(str.substring(0, k + 1));
            if (p.getFst() == p.getSnd()) {
                return k + offset;
            }
        }
        return 0;
    }

}
