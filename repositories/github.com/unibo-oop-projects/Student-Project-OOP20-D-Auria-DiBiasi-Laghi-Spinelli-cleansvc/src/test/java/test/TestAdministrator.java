package test;

import java.time.LocalDate;
import javax.swing.JPanel;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import com.github.lgooddatepicker.components.DatePicker;
import controller.AdministratorChartsControllerImpl;
import model.DataChartsImpl;

public class TestAdministrator {
    private final JPanel panel = new JPanel();
    private final XYChart chart = new XYChartBuilder().build();
    private DatePicker dateStart = new DatePicker();
    private DatePicker dateEnd = new DatePicker();
    DataChartsImpl data = new DataChartsImpl();


    @Test
    public void dateControllerDateStartBefore() throws model.DateException {
        
        dateStart.setDate(LocalDate.of(2020, 8,19));
        dateEnd.setDate(LocalDate.of(2020,7,18));
        Assertions.assertThrows(model.DateException.class, ()->{
            new AdministratorChartsControllerImpl().addLine(dateStart, dateEnd, 1, panel,chart);
        }, "Ok");      
    }    /*Ok*/

    @Test
    public void dateControllerTest() {
        dateStart.setDate(LocalDate.of(2020, 8,19));
        dateEnd.setDate(LocalDate.of(2020,8,24));
        Assertions.assertDoesNotThrow(()->{
            new AdministratorChartsControllerImpl().addLine(dateStart, dateEnd, 1, panel,chart);
        });
        
    } /*ok*/
    
    @Test
    public void dateControllerDateEmpty() throws model.DateException{
        System.out.println(dateEnd.getDate() + "" + dateStart.getDate());
        Assertions.assertThrows(model.DateException.class, ()->{
            new AdministratorChartsControllerImpl().addLine(dateStart, dateEnd, 2, panel,chart);
            
        });

    }   /*Ok*/

    @Test
    public void dataChartTest() {
        Assertions.assertTrue(data.buildChartsFromData(LocalDate.now(), LocalDate.of(2021,3, 24),1).size() > 1);
    }/*Ok*/

  
    @Test
    public void entryTest() {
            
           Assertions.assertFalse(data.getEntrate(LocalDate.now(), LocalDate.of(2021,4,18)).isEmpty());
    } /*OK*/
    
    @Test
    public void compareValue() {
        Assertions.assertTrue(data.buildChartsFromData(LocalDate.now(), LocalDate.of(2021,3, 24),1)
                                .equals(data.getTempoLavoro(LocalDate.now(), LocalDate.of(2021, 03, 24))));  
        }/*Ok*/
}
