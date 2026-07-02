package iuniversity.test.model.exams;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import iuniversity.model.didactics.Course;
import iuniversity.model.exams.ExamCall;
import iuniversity.model.exams.ExamCall.ExamType;
import iuniversity.model.exams.ExamCallImpl;
import iuniversity.model.exams.ExamReport;
import iuniversity.model.exams.ExamReportImpl;
import iuniversity.model.exams.ExamResult.ExamResultType;
import iuniversity.model.exams.ExamsManager;
import iuniversity.model.exams.ExamsManagerImpl;
import iuniversity.test.SampleTestData;

public final class ExamsManagerTest {

    private static final int MAX_STUDENTS = 2;
    private static final int DAYS_BEFORE_CALL = 1;
    private ExamsManager examManager;
    private final SampleTestData sampleData = new SampleTestData();

    @BeforeEach
    public void init() {
        examManager = new ExamsManagerImpl();
    }

    @Test
    public void testExamCalls() {

        final LocalDate date = LocalDate.now().plusDays(DAYS_BEFORE_CALL);
        final Course analisiMatematica = sampleData.getAnalisiMatematica();
        final ExamType examType = ExamType.WRITTEN;
        final int maximumStudents = MAX_STUDENTS;
        final ExamCall examCall = new ExamCallImpl.Builder().callStart(date).course(analisiMatematica)
                .maximumStudents(maximumStudents).examType(examType).build();
        examManager.addExamCall(date, analisiMatematica, examType, maximumStudents);
        assertEquals(Set.of(examCall), examManager.getExamCalls());
        examManager.removeExamCall(examCall);
        assertEquals(Set.of(), examManager.getExamCalls());
        assertFalse(examManager.alreadyHeld(examCall));
        assertTrue(examManager.registerStudent(examCall, sampleData.getMarioRossi()));
        assertTrue(examManager.registerStudent(examCall, sampleData.getLucaBianchi()));
        /*
         * The maximum number of student is exceeded.
         */
        assertFalse(examManager.registerStudent(examCall, sampleData.getLucianoVerdi()));
        assertTrue(examManager.withdrawStudent(examCall, sampleData.getMarioRossi()));
        /*
         * Now a student can register.
         */
        assertTrue(examManager.registerStudent(examCall, sampleData.getLucianoVerdi()));
        /*
         * The student already withdraw.
         */
        assertFalse(examManager.withdrawStudent(examCall, sampleData.getMarioRossi()));
        /*
         * A particular exam call  can be added once.
         */
        assertThrows(IllegalStateException.class, () -> {
           examManager.addExamCall(date, analisiMatematica, examType, maximumStudents); 
        });
        /*
         * Same date, same course but different type is ok.
         */
        examManager.addExamCall(date, analisiMatematica, ExamType.ORAL, maximumStudents);
    }

    @Test
    void testExamReport() {
        final ExamReport reportMarioRossi = new ExamReportImpl.Builder().course(sampleData.getAnalisiMatematica())
                .student(sampleData.getMarioRossi()).laude(true).build();
        final ExamReport reportLucaBianchi = new ExamReportImpl.Builder().course(sampleData.getAnalisiMatematica())
                .student(sampleData.getLucaBianchi()).laude(true).build();
        final ExamReport failReportMarioRossi = new ExamReportImpl.Builder().course(sampleData.getAnalisiMatematica())
                .student(sampleData.getMarioRossi()).resultType(ExamResultType.WITHDRAWN).build();
        final ExamReport failReportLucaBianchi = new ExamReportImpl.Builder().course(sampleData.getAnalisiMatematica())
                .student(sampleData.getLucaBianchi()).resultType(ExamResultType.WITHDRAWN).build();
        examManager.addExamReport(reportMarioRossi);
        assertEquals(Set.of(reportMarioRossi), examManager.getExamReports());
        /*
         * The student have successfully passed the exam. 
         */
        assertTrue(examManager.alreadyReportedSuccess(reportMarioRossi));
        assertTrue(examManager.alreadyReportedSuccess(sampleData.getMarioRossi(), sampleData.getAnalisiMatematica()));
        /*
         * The student does not have a report.
         */
        assertFalse(examManager.alreadyReportedSuccess(reportLucaBianchi));
        assertFalse(examManager.alreadyReportedSuccess(sampleData.getMarioRossi(), sampleData.getProgrammazione()));
        /*
         * The student already have a report.
         */
        assertThrows(IllegalStateException.class, () -> {
            examManager.addExamReport(failReportMarioRossi);
        });
        /*
         * A student can have more than one exam report for the same course only if he never succeeded.
         */
        examManager.addExamReport(failReportLucaBianchi);
        assertFalse(examManager.alreadyReportedSuccess(sampleData.getLucaBianchi(), sampleData.getAnalisiMatematica()));
        examManager.addExamReport(reportLucaBianchi);
        assertEquals(Set.of(reportMarioRossi, reportLucaBianchi, failReportLucaBianchi), examManager.getExamReports());
    }

}
