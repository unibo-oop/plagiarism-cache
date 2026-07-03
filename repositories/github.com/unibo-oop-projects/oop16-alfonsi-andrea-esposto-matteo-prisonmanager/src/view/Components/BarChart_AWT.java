package view.Components;
import java.util.Map;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel; 
import org.jfree.chart.JFreeChart; 
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset; 
import org.jfree.data.category.DefaultCategoryDataset; 
import org.jfree.ui.RefineryUtilities; 

/**
 * classe per il bar chart
 */
public class BarChart_AWT extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7145050658657438095L;
	
	Map<Integer,Integer> map=null;
	
	/**
	 * costruttore bar chart
	 * @param map mappa da cui prendere i dati
	 * @param title titolo grafico
	 * @param chartName nome grafico
	 */
	public BarChart_AWT(Map<Integer, Integer> map,String title,String chartName) {
		
		super(title);
		this.map=map;
		JFreeChart barChart = ChartFactory.createBarChart(
		         chartName,           
		         "Anno",            
		         "Numero di prigonieri",            
		         createDataset(),          
		         PlotOrientation.VERTICAL,           
		         true, true, false);
	   ChartPanel chartPanel = new ChartPanel( barChart );        
	   chartPanel.setPreferredSize(new java.awt.Dimension( 560 , 367 ) );        
	   setContentPane( chartPanel ); 
	   this.pack( );        
	   RefineryUtilities.centerFrameOnScreen( this );        
	   this.setVisible( true ); 
	   this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	   
	}
	
	/**
	 * metodo che inserisce i dati nel diagramma
	 * @return il dataset
	 */
	private CategoryDataset createDataset( ){
    
		final String name = "-";        
	
	    final DefaultCategoryDataset dataset = new DefaultCategoryDataset( );  
	    for(Map.Entry<Integer, Integer> e: map.entrySet()){
	    	dataset.addValue(Double.valueOf(e.getValue()), e.getKey(), name);
	    }
	    return dataset; 
	}
	
}