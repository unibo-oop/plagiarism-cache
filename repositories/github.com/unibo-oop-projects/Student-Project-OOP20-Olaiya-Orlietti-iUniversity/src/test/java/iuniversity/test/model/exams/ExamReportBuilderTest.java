package iuniversity.test.model.exams;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import iuniversity.model.didactics.Course;
import iuniversity.model.exams.ExamReport;
import iuniversity.model.exams.ExamReportBuilder;
import iuniversity.model.exams.ExamReportImpl;
import iuniversity.model.exams.ExamResult.ExamResultType;
import iuniversity.model.user.Student;
import iuniversity.test.SampleTestData;

public final class ExamReportBuilderTest {

    private static final int SUFFICIENT_RESULT = 27;
    private static final int INSUFFICIENT_RESULT = 15;

    private final SampleTestData sampleData = new SampleTestData();
    private final Student marioRossi = sampleData.getMarioRossi();
    private final Course analisiMatematica = sampleData.getAnalisiMatematica();
    private ExamReportBuilder builder;

    @BeforeEach
    public void init() {
        builder = new ExamReportImpl.Builder();
    }

    @Test
    public void testBuildExamReport() {
        final ExamReport report = builder.student(marioRossi).course(analisiMatematica).result(SUFFICIENT_RESULT)
                .resultType(ExamResultType.SUCCEDED).build();
        assertEquals(marioRossi, report.getStudent());
        assertEquals(analisiMatematica, report.getCourse());
        assertEquals(ExamResultType.SUCCEDED, report.getResult().getResultType());
        assertTrue(report.getResult().getResult().isPresent());
    }

    @Test
    public void testCumLaudeExamReport() {
        final ExamReport report = builder.student(marioRossi).course(analisiMatematica).laude(true).build();
        assertEquals(marioRossi, report.getStudent());
        assertEquals(analisiMatematica, report.getCourse());
        assertEquals(ExamResultType.SUCCEDED, report.getResult().getResultType());
        assertTrue(report.getResult().cumLaude());
    }

    @Test
    public void testBuildWithoutSettings() {
        assertThrows(IllegalStateException.class, () -> {
            builder.build();
        });
    }

    @Test
    public void testBuildWithPartialInformationOne() {
        assertThrows(IllegalStateException.class, () -> {
            builder.student(marioRossi).build();
        });
    }

    @Test
    public void testBuildWithPartialInformationTwo() {
        assertThrows(IllegalStateException.class, () -> {
            builder.course(analisiMatematica).build();
        });
    }

    @Test
    public void testBuildWithPartialInformationThree() {
        assertThrows(IllegalStateException.class, () -> {
            builder.student(marioRossi).course(analisiMatematica).result(INSUFFICIENT_RESULT).build();
        });
    }

    @Test
    public void testBuildWithPartialInformationFour() {
        assertThrows(IllegalStateException.class, () -> {
            builder.student(marioRossi).course(analisiMatematica).resultType(ExamResultType.SUCCEDED).build();
        });
    }

    @Test
    public void testInvalidSuccessResult() {
        assertThrows(IllegalArgumentException.class, () -> {
            builder.student(marioRossi).course(analisiMatematica).resultType(ExamResultType.SUCCEDED)
                    .result(INSUFFICIENT_RESULT).build();
        });
    }

    @Test
    public void testInvalidDeclinedResult() {
        assertThrows(IllegalArgumentException.class, () -> {
            builder.student(marioRossi).course(analisiMatematica).resultType(ExamResultType.DECLINED)
                    .result(INSUFFICIENT_RESULT).build();
        });
    }

    @Test
    public void testInvalidFailedResult() {
        assertThrows(IllegalArgumentException.class, () -> {
            builder.student(marioRossi).course(analisiMatematica).resultType(ExamResultType.FAILED)
                    .result(SUFFICIENT_RESULT).build();
        });
    }
}
