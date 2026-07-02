package model.ai;

import java.util.*;
import java.util.function.Function;
import model.player.Player;
import model.question.Question;

/*
 * Given a Player allows to get the median question to ask.
 * i.e. the one that allows to delete half of the remaining characters (or the most closest number to the half).
 */
class MedianQuestion implements Function<Player, Question> {

    @Override
    public Question apply(final Player p) {
        final Map<Question, Integer> questionOccurrences = new HashMap<>();
        final Integer halfRemaining = p.getAvailables().size() / 2;
        p.availableQuestions().stream().distinct().forEach(q -> questionOccurrences.put(q, MedianQuestion.countOccurrences(p, q)));
        return questionOccurrences.entrySet().stream()
            .reduce((e1, e2) -> Math.abs(halfRemaining - e1.getValue()) < Math.abs(halfRemaining - e2.getValue()) ? e1 : e2)
            .get().getKey();
    }

    private static Integer countOccurrences(final Player p, final Question q) {
        return (int) p.availableQuestions().stream().filter(question -> question.equals(q)).count();
    }

}
