package view.analysis;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import org.jfree.chart.ChartPanel;
import controller.AnalysisControllerImpl;
import controller.EnvironmentControllerImpl;

public class AnalysisDialog {

    private final JFrame frame = new JFrame();
    private final JPanel analysisPanel = new JPanel();
    private final JMenuBar save = new JMenuBar();
    private final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
    private final int sw = (int) screen.getWidth();
    private final int chartWidth = sw / 2;

    public AnalysisDialog(final AnalysisControllerImpl controller, final EnvironmentControllerImpl envController) {
        analysisPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        try {
            final ChartPanel fairPanel = new ChartPanel(controller.getFairChart());
            fairPanel.setDomainZoomable(true);
            fairPanel.setMaximumDrawWidth(chartWidth);
            analysisPanel.add(fairPanel);
        } catch (NullPointerException e) {
        }
        final ChartPanel profitPanel = new ChartPanel(controller.getProfitChart());
        profitPanel.setDomainZoomable(true);
        profitPanel.setMaximumDrawWidth(chartWidth);
        analysisPanel.add(profitPanel);
        this.save.add(new SaveAnalysisMenu(envController, controller, this).createSaveMenu());

        frame.setJMenuBar(save);
        frame.setTitle("Fun Fair Analysis");
        frame.add(analysisPanel);
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * It disposes this dialog.
     */
    public void dispose() {
        this.frame.dispose();
    }

}
