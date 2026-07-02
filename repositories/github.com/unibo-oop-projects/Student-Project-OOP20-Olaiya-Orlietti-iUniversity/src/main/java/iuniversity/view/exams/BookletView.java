package iuniversity.view.exams;

import java.util.Set;

import iuniversity.model.exams.ExamReport;

public interface BookletView {

    /**
     * 
     * @param reports the reports to be displayed
     */
    void setReports(Set<ExamReport> reports);

}
