package iuniversity.test.model.exams;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import com.google.common.base.Optional;

import iuniversity.model.exams.ExamResult;
import iuniversity.model.exams.ExamResultFactory;
import iuniversity.model.exams.ExamResultFactoryImpl;
import iuniversity.model.exams.ExamResult.ExamResultType;

public class ExamResultFactoryTest {

    private static final int MAX_RESULT = 30;
    private static final int SUFFICIENT_RESULT = 27;
    private static final int INSUFFICIENT_RESULT = 12;
    private static final int OVER_RANGE_RESULT = MAX_RESULT + 1;

    private final ExamResultFactory resultFactory = new ExamResultFactoryImpl();

    @Test
    public void testCumLaudeExamResult() {
        final ExamResult examResult = resultFactory.succeededCumLaude();
        assertTrue(examResult.cumLaude());
        assertEquals(Optional.of(MAX_RESULT), examResult.getResult());
        assertEquals(ExamResultType.SUCCEDED, examResult.getResultType());
    }

    @Test
    public void testSuccededExamResult() {
        final ExamResult examResult = resultFactory.succeded(SUFFICIENT_RESULT);
        assertFalse(examResult.cumLaude());
        assertEquals(Optional.of(SUFFICIENT_RESULT), examResult.getResult());
        assertEquals(ExamResultType.SUCCEDED, examResult.getResultType());
    }

    @Test
    public void testFailedExamResult() {
        final ExamResult examResult = resultFactory.failed(INSUFFICIENT_RESULT);
        assertFalse(examResult.cumLaude());
        assertEquals(Optional.of(INSUFFICIENT_RESULT), examResult.getResult());
        assertEquals(ExamResultType.FAILED, examResult.getResultType());
    }

    @Test
    public void testWithdrawnExamResult() {
        final ExamResult examResult = resultFactory.withdrawn();
        assertFalse(examResult.cumLaude());
        assertEquals(Optional.absent(), examResult.getResult());
        assertEquals(ExamResultType.WITHDRAWN, examResult.getResultType());
    }

    @Test
    public void testDeclinedExamResult() {
        final ExamResult examResult = resultFactory.declined(SUFFICIENT_RESULT);
        assertFalse(examResult.cumLaude());
        assertEquals(Optional.of(SUFFICIENT_RESULT), examResult.getResult());
        assertEquals(ExamResultType.DECLINED, examResult.getResultType());
    }

    @Test
    public void testInvalidSuccededExamResult() {
        assertThrows(IllegalArgumentException.class, () -> {
            resultFactory.succeded(INSUFFICIENT_RESULT);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            resultFactory.succeded(OVER_RANGE_RESULT);
        });
    }

    @Test
    public void testInvalidFailedExamResult() {
        assertThrows(IllegalArgumentException.class, () -> {
            resultFactory.failed(SUFFICIENT_RESULT);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            resultFactory.failed(OVER_RANGE_RESULT);
        });
    }

    @Test
    public void testInvalidDeclinedExamResult() {
        assertThrows(IllegalArgumentException.class, () -> {
            resultFactory.declined(INSUFFICIENT_RESULT);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            resultFactory.declined(OVER_RANGE_RESULT);
        });
    }

}
