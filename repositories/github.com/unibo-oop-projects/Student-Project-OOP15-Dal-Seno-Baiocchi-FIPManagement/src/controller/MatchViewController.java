package controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;

import javax.swing.JTable;

import model.IModel;
import model.Player;
import model.StatisticModel;
import observer.MatchViewObserver;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * Class which controls the MatchView and allows to save file into
 * an external excel file and adding or 
 * removing statistics
 * @author francesco
 *
 */
public class MatchViewController implements MatchViewObserver {

    private StatisticModel stat;
    private IModel model;
    private List<String> homeValueable;
    private List<String> guestValueable;
    private Set<String> homeId;
    private Set<String> guestId;
    private XSSFCell cell;
    private XSSFCell guestCell;
    private XSSFWorkbook fileWorkbook;
    private ArrayList<String> homeValues;
    private ArrayList<String> guestValues;
    private XSSFSheet fileSheet;
    private int guestHeaderRowIndex;

	public MatchViewController(IModel model, StatisticModel stat) {
		this.model = model;
		this.stat = stat;
		
	}
	
	@Override
	public void saveMatch(JTable homeTable, JTable guestTable,String homeName,String guestName, String path) {
	    
	    //Object necessary for the excel file creation 
	    fileWorkbook = new XSSFWorkbook();
	    fileSheet = fileWorkbook.createSheet();
	   
	    int countRow = 1;

	    TreeMap<String, List<String>> homeTeamData = new TreeMap<>();
	    TreeMap<String, List<String>> guestTeamData = new TreeMap<>();
	    ArrayList<String> header = new ArrayList<>();
	    
	    //Creating the style for the table
            CellStyle homeStyle = fileWorkbook.createCellStyle();
            homeStyle.setFillForegroundColor(IndexedColors.RED.getIndex());
            homeStyle.setFillPattern(CellStyle.ALIGN_FILL);
            
            CellStyle guestStyle = fileWorkbook.createCellStyle();
            guestStyle.setFillForegroundColor(IndexedColors.BLUE.getIndex());
            guestStyle.setFillPattern(CellStyle.ALIGN_FILL);
            
            //Setting the first row with headers
	    for(int k = 0; k <= (homeTable.getColumnCount()-1);k++){
	        header.add(homeTable.getColumnName(k));
	    }
	    homeTeamData.put("0",header);

            //Gettin the home team data 
	    for(int i= 0; i <= (homeTable.getRowCount()-1); i++){
	        homeValues = new ArrayList<>();
	        for(int j = 0; j <= (homeTable.getColumnCount()-1); j++){
	            homeValues.add(homeTable.getValueAt(i, j).toString());
	                homeTeamData.put(""+countRow,homeValues);
	        }
	        countRow++;
	    }
	    
	    countRow = 1;
	    //Setting again the header 
            guestTeamData.put("0", header);
	    // Getting the guest team data 
	    for(int i = 0; i <= (guestTable.getRowCount()-1); i++){
	        guestValues = new ArrayList<>();
	        for(int j = 0; j <= (guestTable.getColumnCount()-1); j++){
	            guestValues.add(guestTable.getValueAt(i, j).toString());
	            guestTeamData.put(""+countRow, guestValues);
	        }
	        countRow++;
	    }
	   
	    
	    homeId = homeTeamData.keySet();
	    // Creating row
	    XSSFRow row;
	    int rowID = 0;
	    for(String key: homeId){
	        row = fileSheet.createRow(rowID++);
	        int cellID = 0;
	        homeValueable = homeTeamData.get(key);
	        // Populating cells
	        for(String o: homeValueable){
	            cell = row.createCell(cellID++);
	            if(rowID == 1){
	                cell.setCellStyle(homeStyle);
	            }
	            cell.setCellValue(o);
	        }
	    }
	    /* Leaving empty row */
	    rowID++;
	    
	    guestHeaderRowIndex = rowID;
	    // Creting row
            guestId = guestTeamData.keySet();
            XSSFRow guestRow;
	    for(String guestKey: guestId){
	        guestRow = fileSheet.createRow(rowID++);
	        int cellID = 0;
	        guestValueable = guestTeamData.get(guestKey);
	        // Populating cells
	        for(String s: guestValueable){
	            guestCell = guestRow.createCell(cellID++);
	            if(rowID == guestHeaderRowIndex+1){
	                guestCell.setCellStyle(guestStyle);
	            }
	            guestCell.setCellValue(s);
	        }  
	    }
	    try{
	        //Saving into a file
	        FileOutputStream fs = new FileOutputStream(path+".xlsx");
	        fileWorkbook.write(fs);
	        fs.close();
	    } catch (IOException ex){
	        
	    }
		Utils.save(model);
	}

	@Override
	public void increasePoints(Player p, int value) {
		stat.getStatistic(p).increasePoints(value);
	}

	@Override
	public void decreasePoints(Player p, int value) {
		stat.getStatistic(p).decreasePoints(value);
	}

	@Override
	public void increaseOffRebounds(Player p) {
		stat.getStatistic(p).increaseOffRebounds();
	}

	@Override
	public void decreaseOffRebounds(Player p) {
		stat.getStatistic(p).decreaseOffRebounds();
	}

	@Override
	public void increaseDefRebounds(Player p) {
		stat.getStatistic(p).increaseDefRebounds();
	}

	@Override
	public void decreaseDefRebounds(Player p) {
		stat.getStatistic(p).decreaseDefRebounds();
	}

	@Override
	public void increseAssists(Player p) {
		stat.getStatistic(p).increseAssists();
	}

	@Override
	public void decreaseAssists(Player p) {
		stat.getStatistic(p).decreaseAssists();
	}

	@Override
	public void increaseBlocks(Player p) {
		stat.getStatistic(p).increaseBlocks();
	}

	@Override
	public void decreaseBlocks(Player p) {
		stat.getStatistic(p).decreaseBlocks();
	}

	@Override
	public void incresePersonalFouls(Player p) {
		stat.getStatistic(p).incresePersonalFouls();
	}

	@Override
	public void decreasePeronsalFouls(Player p) {
		stat.getStatistic(p).decreasePeronsalFouls();
	}

	@Override
	public void increaseTurnovers(Player p) {
		stat.getStatistic(p).increaseTurnovers();
	}

	@Override
	public void decreaseTurnovers(Player p) {
		stat.getStatistic(p).decreaseTurnovers();
	}

	@Override
	public void increaseSteals(Player p) {
		stat.getStatistic(p).increaseSteals();
	}

	@Override
	public void decreaseSteals(Player p) {
		stat.getStatistic(p).decreaseSteals();		
	}

}
