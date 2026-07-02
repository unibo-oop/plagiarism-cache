package iuniversity.controller.exams;

import iuniversity.model.exams.ExamCall;

public interface ExamBookingController {

    void displayAvailableExamCalls();

    void bookExamCall(ExamCall examCall);

}
