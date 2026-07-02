package iuniversity.view.exams;

import java.util.Set;

import iuniversity.controller.exams.BookletController;
import iuniversity.model.exams.ExamReport;
import iuniversity.view.AbstractView;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

/**
 * The student booklet view.
 *
 */
public class BookletViewImpl extends AbstractView implements BookletView {

    @FXML
    private ListView<ExamReport> reportList;

    /**
     * {@inheritDoc}
     */
    @Override
    public void start() {
        ((BookletController) this.getController()).displayReports();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setReports(final Set<ExamReport> reports) {
        this.reportList.getItems().setAll(reports);
    }

}
