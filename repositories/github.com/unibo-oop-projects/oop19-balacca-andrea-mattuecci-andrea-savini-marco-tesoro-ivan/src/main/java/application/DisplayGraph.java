package application;

import org.jfree.*;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;

import application.Consumi.Cliente;
import application.Consumi.Pasto;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.HeadlessException;
import java.sql.NClob;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Map.Entry;

public class DisplayGraph extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1413769563240621318L;
	Consumi cons;
	Previsioni prev;
	Catena catena;
	Date start, end;
	String[] ID;
	int nClienti;
	
	//consumi
    public DisplayGraph(Consumi cons, Catena catena, Date start, Date end, String ...ID) throws HeadlessException, DateNotFound {
		this.cons = cons;
		this.catena = catena;
		this.start = start;
		this.end = end;
		this.ID = ID;
		initUIConsumi(cons, catena, start, end, ID);
	}
    
    //previsioni
    public DisplayGraph(Previsioni prev, Catena catena, Date start, Date end, int nCliente, String ...ID) throws HeadlessException, DateNotFound, ForecastNotAvailable {
    	this.prev = prev;
		this.catena = catena;
		this.start = start;
		this.end = end;
		this.nClienti = nCliente;
		this.ID = ID;
		initUIPrevisioni(prev, catena, start, end, nClienti, ID);
	}

    private void initUIConsumi(Consumi cons, Catena catena, Date start, Date end, String ...ID) throws DateNotFound {
        XYDataset dataset = createDatasetConsumi(cons, catena, start, end ,ID);
        JFreeChart chart = createChart(dataset, "Consumi");
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        chartPanel.setBackground(Color.white);
        add(chartPanel);

        
        pack();
        setTitle("Grafico consumi");
        setLocationRelativeTo(null);
    }
    private void initUIPrevisioni(Previsioni prev, Catena catena, Date start, Date end, int nCliente, String ...ID) throws DateNotFound, ForecastNotAvailable {
        XYDataset dataset = createDatasetPrevisioni(prev, catena, start, end, nCliente, ID);
        JFreeChart chart = createChart(dataset, "Previsioni");
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        chartPanel.setBackground(Color.white);
        add(chartPanel);

        
        pack();
        setTitle("Grafico previsioni");
        setLocationRelativeTo(null);
       
    }

    private XYDataset createDatasetConsumi(Consumi cons, Catena catena, Date dt1, Date dt2, String ...ID) throws DateNotFound { 
    	TimeSeriesCollection dataset = new TimeSeriesCollection();
    	for (String a: ID) {
			TimeSeries series = new TimeSeries(a);
			DrawGraphImpl dg = new DrawGraphImpl(cons, catena);
			NavigableMap<Date, HashMap<String, Float>> map = new TreeMap<Date, HashMap<String,Float>>(dg.getGraphConsumi(dt1, dt2, a));
			for (Map.Entry<Date, HashMap<String, Float>> m: map.entrySet()) {
				for (Map.Entry<String, Float> h: m.getValue().entrySet()) {
					series.add(new Day(m.getKey()), h.getValue());
				}
		
				
			}
		    dataset.addSeries(series);
    	}
    	return dataset;
    }
    private XYDataset createDatasetPrevisioni(Previsioni prev, Catena catena, Date dt1, Date dt2, int nClienti, String ...ID) throws DateNotFound, ForecastNotAvailable { 
    	TimeSeriesCollection dataset = new TimeSeriesCollection();
    	for (String a: ID) {
			TimeSeries series = new TimeSeries(a);
			DrawGraphImpl dg = new DrawGraphImpl(prev, catena);
			NavigableMap<Date, HashMap<String, Float>> map = new TreeMap<Date, HashMap<String,Float>>(dg.getGraphPrevisioni(dt1, dt2, a, nClienti));
			for (Map.Entry<Date, HashMap<String, Float>> m: map.entrySet()) {
				for (Map.Entry<String, Float> h: m.getValue().entrySet()) {
					series.add(new Day(m.getKey()), h.getValue());
				}	
			}
		    dataset.addSeries(series);
    	}
    	return dataset;
    }

    private JFreeChart createChart(XYDataset dataset, String tipologia) {
        JFreeChart chart = ChartFactory.createXYLineChart(
                null,
                null,
                "Consumo (kg)",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        XYPlot plot = chart.getXYPlot();
        DateAxis dateAxis = new DateAxis();
        dateAxis.setDateFormatOverride(new SimpleDateFormat("dd-MM-yyyy")); 
        plot.setDomainAxis(dateAxis);

        var renderer = new XYLineAndShapeRenderer();
        /*
        renderer.setSeriesPaint(0, Color.RED);
        renderer.setSeriesStroke(0, new BasicStroke(2.0f));
        renderer.setSeriesPaint(0, Color.GREEN);
        renderer.setSeriesStroke(0, new BasicStroke(2.0f));*/
        renderer.setSeriesPaint(0, Color.RED);
        
        renderer.setSeriesPaint(0, Color.GREEN);

        renderer.setSeriesPaint(0, Color.BLACK);

        renderer.setSeriesPaint(0, Color.BLUE);

        renderer.setSeriesPaint(0, Color.CYAN);

        renderer.setSeriesPaint(0, Color.GRAY);
        
        renderer.setSeriesPaint(0, Color.MAGENTA);
        
        renderer.setSeriesPaint(0, Color.ORANGE);
        

        plot.setRenderer(renderer);
        plot.setBackgroundPaint(Color.white);

        plot.setRangeGridlinesVisible(true);
        plot.setRangeGridlinePaint(Color.BLACK);

        plot.setDomainGridlinesVisible(true);
        plot.setDomainGridlinePaint(Color.BLACK);

        chart.getLegend().setFrame(BlockBorder.NONE);

        chart.setTitle(new TextTitle(tipologia + " dal " + new Day(start)+ " al " + new Day(end),
                        new Font("Serif", java.awt.Font.BOLD, 18)
                )
        );
        return chart;
    }
 
    /*
    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {

            var ex = new Test();
            ex.setVisible(true);
        });
    }
    */
}
