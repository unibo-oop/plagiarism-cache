package view.Components;

import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.RefineryUtilities;

/**
 * classe per il grafico a torta
 */
public class PieChart_AWT extends JFrame { 
	
	private static final long serialVersionUID = -1753760388662708023L;
	
	static Map<String,Integer>map=new HashMap<>();
	
	/**
	 * costruttore del grafico
	 * @param title titolo grafico
	 * @param map mappa contenente i dati
	 */
	public PieChart_AWT(String title,Map<String,Integer>map) {
		super( title ); 
	    PieChart_AWT.map=map;
	    setContentPane(createDemoPanel());
	    this.setSize(600, 600);
	    RefineryUtilities.centerFrameOnScreen( this );    
	    this.setVisible( true ); 
	}
	
	/**
	 * metodo che crea il dataset
	 * @return il dataset contenente i dati
	 */
	private static PieDataset createDataset(){
		DefaultPieDataset dataset = new DefaultPieDataset( );
	    for(Map.Entry<String, Integer> e: map.entrySet()){
	    	dataset.setValue(String.valueOf(e.getKey()), e.getValue());
		}
	    return dataset;         
	}
	  
	/**
	 * metodo che crea il grafico
	 * @param dataset dataset con i dati
	 * @return il grafico
	 */
	private static JFreeChart createChart(PieDataset dataset){
	      JFreeChart chart = ChartFactory.createPieChart(      
	    	"Percentuale crimini commessi dai reclusi attuali",  // chart title 
	         dataset,        // data    
	         true,           // include legend   
	         true, 
	         false);
	
	      return chart;
	}
	  
	public static JPanel createDemoPanel(){
		JFreeChart chart = createChart(createDataset( ) );  
	    return new ChartPanel( chart ); 
	}

}
