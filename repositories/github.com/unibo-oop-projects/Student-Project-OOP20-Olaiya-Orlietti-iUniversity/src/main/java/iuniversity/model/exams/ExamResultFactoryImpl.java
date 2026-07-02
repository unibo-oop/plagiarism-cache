package iuniversity.model.exams;


import java.io.Serializable;

import com.google.common.base.Optional;

import iuniversity.model.exams.ExamResult.ExamResultType;

/**
 * This class is a factory for examResults.
 *
 */
public class ExamResultFactoryImpl implements ExamResultFactory, Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private static final int MAX_RESULT = 30;
    private static final int SUFFICIENCY = 18;

    private ExamResult makeEvaluation(final ExamResultType resultType, final Optional<Integer> result,
            final boolean cumLaude) {
        if (!result.isPresent() && resultType != ExamResultType.WITHDRAWN) {
            throw new IllegalArgumentException("Only withdrawn results may not have a numeric result");
        } else if (resultType == ExamResultType.WITHDRAWN && result.isPresent()) {
            throw new IllegalArgumentException("Only withdrawn can't have a numeric result");
        } else if (cumLaude && resultType != ExamResultType.SUCCEDED) {
            throw new IllegalArgumentException("Honours may be given only if exam was succeded");
        } else if (cumLaude && result.get() != MAX_RESULT) {
            throw new IllegalArgumentException("Honours may be given only if maximun result is given");
        } else if ((resultType == ExamResultType.SUCCEDED || resultType == ExamResultType.DECLINED)
                && (result.get() < SUFFICIENCY || result.get() > MAX_RESULT)) {
            throw new IllegalArgumentException("An exam is succeded or declinable only if result is sufficient");
        } else if (resultType == ExamResultType.FAILED && (result.get() < 0 || result.get() >= SUFFICIENCY)) {
            throw new IllegalArgumentException("An exam is failed only if result is below sufficiency");
        }
        return new ExamResult() {

            /**
             * 
             */
            private static final long serialVersionUID = 1L;

            @Override
            public ExamResultType getResultType() {
                return resultType;
            }

            @Override
            public boolean cumLaude() {
                return cumLaude;
            }

            @Override
            public Optional<Integer> getResult() {
                return result;
            }

            @Override
            public String toString() {
                return getResultType()
                        + (getResult().isPresent() ? "(" + getResult().get() + (cumLaude() ? "L" : "") + ")" : "");
            }
        };
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ExamResult succeededCumLaude() {
        return makeEvaluation(ExamResultType.SUCCEDED, Optional.of(MAX_RESULT), true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ExamResult succeded(final int result) {
        return makeEvaluation(ExamResultType.SUCCEDED, Optional.of(result), false);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ExamResult failed(final int result) {
        return makeEvaluation(ExamResultType.FAILED, Optional.of(result), false);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ExamResult withdrawn() {
        return makeEvaluation(ExamResultType.WITHDRAWN, Optional.absent(), false);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ExamResult declined(final int result) {
        return makeEvaluation(ExamResultType.DECLINED, Optional.of(result), false);
    }

}
