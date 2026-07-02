package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import org.knowm.xchart.XYChart;

public class DataChartsImpl implements DataCharts {
     
    private static final String SEP = File.separator;
    private static final String FILE = "Statistics.txt";
    private ArrayList<LocalDate> listToReturn;
    
    public DataChartsImpl() {
        this.listToReturn = new ArrayList<>();
    }


    public List<Double> buildChartsFromData(final LocalDate dateStart, final LocalDate dateEnd, final Integer choice) {       
        try {
                if(choice.equals(DatiDaVisualizzareEnum.TEMPOLAVORO.getIndex())) {
                return this.getTempoLavoro(dateStart, dateEnd);
        }
        
        else if(choice.equals(DatiDaVisualizzareEnum.ENTRATE.getIndex())){
              return this.getEntrate(dateStart, dateEnd);
        }
          
        } catch(IllegalArgumentException e) {
          JOptionPane.showMessageDialog(new JPanel(), "Input non valido");
          }
        return null;
    }
    

    public List<Double> getEntrate(final LocalDate dateStart, final LocalDate dateEnd){
        
        if(!this.listToReturn.isEmpty()) {
            this.listToReturn.clear();
        }
        Map<LocalDate, Double> auxMap = new TreeMap<>();
        List<Double> entryList = new ArrayList<>();
        LocalDate auxDate = null;
        String line = null;
        Double entry;
        try(BufferedReader reader = new BufferedReader(new FileReader(DataChartsImpl.FILE))){
            while( (line = reader.readLine()) != null) {
                auxDate = LocalDate.parse(line.substring(line.indexOf("Date: ")+7, line.indexOf(" Minuti")));
                //System.out.println(auxDate + "from getEntrate");
                if(dateStart.equals(auxDate) || (auxDate.isAfter(dateStart) && auxDate.isBefore(dateEnd)) || auxDate.isEqual(dateEnd)) {
                    entry = Double.parseDouble(line.substring(line.indexOf("Entrate: ")+9));
                    if(auxMap.containsKey(auxDate)) {
                        auxMap.replace(auxDate, auxMap.get(auxDate), auxMap.get(auxDate) + entry);
                    }else{
                            auxMap.put(auxDate, entry);   
                     }
                    /*Debug*/ System.out.println("Aggiunto");
                }
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
        this.listToReturn.addAll(auxMap.keySet());
        entryList.addAll(auxMap.values());
        return entryList;
    }
    
   
    public List<Double> getTempoLavoro(LocalDate dateStart, LocalDate dateEnd){
        Map<LocalDate, Double> auxMap = new TreeMap<>();
        List<Double> timeList = new ArrayList<>();
        LocalDate auxDate = null;
        String line = null;
        Double time; 
        
        if(!this.listToReturn.isEmpty()) {
            this.listToReturn.clear();
        }
        try(BufferedReader reader = new BufferedReader(new FileReader(DataChartsImpl.FILE))){
            
            while( (line = reader.readLine()) != null) {
                auxDate = LocalDate.parse(line.substring(line.indexOf("Date: ")+7, line.indexOf(" Minuti")));
                
                //*Debug*/System.out.println(auxDate + " from getTempoLavoro");
                
                if(dateStart.equals(auxDate) || (auxDate.isAfter(dateStart) && auxDate.isBefore(dateEnd)) || auxDate.isEqual(dateEnd)) {
                    
                    time = Double.parseDouble(line.substring(line.indexOf("Minuti: ")+8, line.indexOf(" Entrate: ")));
                   
                    if(auxMap.containsKey(auxDate)) {
                        auxMap.replace(auxDate, auxMap.get(auxDate), auxMap.get(auxDate) + time);
                    }else{
                            auxMap.put(auxDate, time);   
                     }
                    //* Debug*/ System.out.println("Aggiunto");
                }
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
        
        this.listToReturn.addAll(auxMap.keySet());
        timeList.addAll(auxMap.values());
        return timeList;
    }
    

    @Override
    public List<Date> getDateList() {
        List<Date> successiveDate = new ArrayList<>();
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
        Date date;
        for(LocalDate localDate : this.listToReturn) {
            try {
                date = sdf.parse(new String(localDate.toString()));
                successiveDate.add(date);
            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }        
        return successiveDate;
        }
    
    @Override
    public void deleteLastItem(XYChart chart) {

        List<String> list = new ArrayList<>();
        System.out.println(chart.getSeriesMap().keySet());
        list.addAll(chart.getSeriesMap().keySet().stream().collect(Collectors.toList()));
        list.remove(list.size()-1);
        chart.getSeriesMap().keySet().retainAll(list);
        if(chart.getSeriesMap().isEmpty()) {
            chart.getStyler().setXAxisTicksVisible(false);
            chart.getStyler().setYAxisTicksVisible(false);
        }
    }
    
    public String newLegendString(String dataArr, String dataPar, Integer scelta) {
        String choose = scelta.equals(DatiDaVisualizzareEnum.ENTRATE.getIndex()) ?  DatiDaVisualizzareEnum.ENTRATE.getItemName()
                                                                                 : DatiDaVisualizzareEnum.TEMPOLAVORO.getItemName();
        return new String("Da: " + dataPar + " a: " + dataArr + ", "+ choose);
    }
}