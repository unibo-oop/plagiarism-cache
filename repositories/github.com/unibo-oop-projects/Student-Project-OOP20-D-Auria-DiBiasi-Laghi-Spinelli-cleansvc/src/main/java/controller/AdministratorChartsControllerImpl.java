package controller;

import java.time.LocalDate;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import org.knowm.xchart.XYChart;
import com.github.lgooddatepicker.components.DatePicker;
import model.ChartException;
import model.DataChartsImpl;
import model.DateException;


public class AdministratorChartsControllerImpl implements AdministratorChartsController{

    private final ChartException chExc;
    private final DateException dateExc;
    private DataChartsImpl dataChart;
    public AdministratorChartsControllerImpl() {
        this.chExc = new ChartException();
        this.dateExc = new DateException();
        this.dataChart = new DataChartsImpl();
    }

    @Override
    public void addLine(DatePicker dateStart, DatePicker dateEnd, int choice, JPanel panel, XYChart chart) 
            throws DateException {

        final LocalDate dataPartenza, dataArrivo;
        final Integer scelta = choice;

        try {
                if(dateStart.getText().length() == 0 || dateEnd.getText().length() == 0) {
                    this.dateExc.warning(panel);
                    throw this.dateExc;
                }

                dataPartenza = dateStart.getDate();
                dataArrivo = dateEnd.getDate();     
                if(dataArrivo.isBefore(dataPartenza)) {
                        this.dateExc.dateBefore(panel);            
                        throw this.dateExc;
                    }

                List<Double> auxList = this.dataChart.buildChartsFromData(dataPartenza, dataArrivo, scelta);
                chart.addSeries (this.dataChart.newLegendString(dataArrivo.toString(), dataPartenza.toString(), scelta),
                                                            this.dataChart.getDateList(), auxList);
                    chart.getStyler().setXAxisTicksVisible(true);
                    chart.getStyler().setYAxisTicksVisible(true);
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(panel, "Impossibile aggiungere al grafico");
        }
                this.updatePanelChart(panel);
        }

    public void resetChart(XYChart chart, JPanel panel) throws ChartException {       

        if (chart.getSeriesMap().isEmpty()) {
            this.chExc.warning(panel); 
            throw chExc;
        }
        
        chart.getSeriesMap().clear();
        chart.getStyler().setXAxisTicksVisible(false);
        chart.getStyler().setYAxisTicksVisible(false);
        this.updatePanelChart(panel);
    }


    public void deleteLast(XYChart chart, JPanel panel) throws ChartException {
        if (chart.getSeriesMap().isEmpty()) {
            this.chExc.warning(panel); 
            throw chExc;

        }
        this.dataChart.deleteLastItem(chart);
        this.updatePanelChart(panel);
    }

    private void updatePanelChart (JPanel panel) {
        panel.revalidate();
        panel.repaint();   
    }


}
