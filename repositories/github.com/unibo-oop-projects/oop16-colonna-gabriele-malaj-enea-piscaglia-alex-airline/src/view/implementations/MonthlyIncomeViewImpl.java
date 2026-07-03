package view.implementations;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Map;
import java.util.Map.Entry;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import view.interfaces.MonthlyIncomeView;

public class MonthlyIncomeViewImpl implements MonthlyIncomeView {
	
	private final DefaultCategoryDataset dcd = new DefaultCategoryDataset();
	private final JFreeChart jchart = ChartFactory.
			createBarChart3D("Incassi Mensili", "Mesi", "Incassi", dcd, PlotOrientation.VERTICAL,true, true, false);
	
	private CategoryPlot plot;
	private ChartFrame chartFrame;
	
	public MonthlyIncomeViewImpl(Map<String, Double> map) {

	        for (final Entry<String, Double> e : map.entrySet()) {
                    dcd.setValue(e.getValue(), "Incassi", e.getKey());
	        }

		initializeComponents();
		chartFrameSettings();
		
	}
	
	private void chartFrameSettings() {
		chartFrame.setVisible(true);
		chartFrame.setSize(720, 480);
		chartFrame.setDefaultCloseOperation(ChartFrame.DISPOSE_ON_CLOSE);
		
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension dim = tk.getScreenSize();
		int xPos = (dim.width / 2) - (chartFrame.getWidth() / 2);
		int yPos = (dim.height / 2) - (chartFrame.getHeight() / 2);

		chartFrame.setLocation(xPos, yPos);
		
	}

	private void initializeComponents() {
		chartFrame = new ChartFrame("Incassi Mensili", jchart, true);
		plot = jchart.getCategoryPlot();
		plot.setRangeGridlinePaint(Color.BLACK);
	}

}