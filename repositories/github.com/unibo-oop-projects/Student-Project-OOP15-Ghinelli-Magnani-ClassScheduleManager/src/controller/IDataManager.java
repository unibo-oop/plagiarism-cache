package controller;

import model.SchedulesModel;
import model.interfaces.ISchedulesModel;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JTable;

public interface IDataManager {
    
  /**
   * Method that reads a configuration file.
   * @param model
   *            where the configuration file's informations are copied
   * @throws IOException
   *            if file is incorrect 
   */
  void readConfig(final ISchedulesModel model) throws IOException;
  
  /**
   * Method to save an object on a file.
   * 
   * @param fileName The system-dependent filename.
   * @param model model to be saved on file.
   * @throws IOException {@link ObjectOutputStream#writeObject(Object)}.
   * @author Martina Magnani
   */
  void saveFile(final String fileName, final ISchedulesModel model) throws IOException;
  
  /**
   * Method to load an object on a file.
   * 
   * @param fileName The system-dependent filename.
   * @return Object loaded from a file.
   * @throws IOException construct method of {@link ObjectIntputStream}.
   * @throws ClassNotFoundException {@link ObjectInputStream#readObject()}.
   * @author Martina Magnani
   */
  SchedulesModel openFile(final String fileName) throws IOException, ClassNotFoundException;
  
  /**
   * Method which takes a HSSFWorkbook object and saves it in the home directory of the user.
   * @param workbook The workbook containing the table.
   */
  
  void exportInExcel(final HSSFWorkbook workbook);
}
