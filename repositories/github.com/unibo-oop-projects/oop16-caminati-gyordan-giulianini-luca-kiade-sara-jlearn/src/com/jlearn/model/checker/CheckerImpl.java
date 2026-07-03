package com.jlearn.model.checker;

import java.util.List;
import java.util.Optional;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.jlearn.model.exercises.Exercise;

/**
 *
 * An implementation of Checker interface.
 *
 */
public class CheckerImpl implements Checker {

    private static final Logger LOG = Logger.getLogger(CheckerImpl.class);

    /**
     * Build Checker.
     */
    public CheckerImpl() {
        LOG.setLevel(Level.WARN);
    }

    @Override
    public CheckLog check(final Exercise<?> ex, final List<Optional<?>> givenAnswers) {

        if (givenAnswers.size() != ex.getNumAnswers()) {

            LOG.warn("Ivalid given answers number");
            throw new IllegalArgumentException("Ivalid given answers. "
                    + "The number of given answers must be the same as the Exercise answers. "
                    + "No matter if they're null, they must be present");

        }

        final Optional<?> oneAns = givenAnswers.stream()
                .filter(x -> x.isPresent())
                .findAny()
                .orElse(Optional.empty());

        if (oneAns.isPresent() && (oneAns.get().getClass() != ex.getFlatAnswers().get(0).getClass())) {
            LOG.warn("invalid answers type");
            throw new IllegalArgumentException(
                    "The given answers type doesn't match the exercise answers type");

        }

        final CheckLogBuilder logB = new CheckLogBuilderImpl();
        for (int i = 0; i < ex.getNumAnswers(); i++) {
            if (givenAnswers.get(i).equals(Optional.empty())) {
                logB.registerAnswer(i, Checker.Result.NULL_ANSWER);
                LOG.info("Registered a null answer");
            } else if (givenAnswers.get(i).get().equals(ex.getFlatAnswers().get(i))) {
                logB.registerAnswer(i, Checker.Result.RIGHT_ANSWER);
                LOG.info("Registered a right answer");
            } else {
                logB.registerAnswer(i, Checker.Result.WRONG_ANSWER);
                LOG.info("Registered a wrong answer");

            }

        }

        return logB.build();

    }

    @Override
    public int getMaxBasicScore(final Exercise<?> ex) {

        return ex.getNumAnswers() * Checker.Result.RIGHT_ANSWER.getPoints();
    }

}
