package view.managestatistics;

import controller.managestatistics.StatisticsController;

public interface StatisticsView {

    /**
     * Show a statistics view.
     */
    void show();

    /**
     * Set Statistics controller observer.
     * @param observer that is Statistics Controller
     */
    void setObserver(StatisticsController observer);

    /**
     * Update view.
     */
    void update();
}
