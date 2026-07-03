import it.rentalmanage.model.*;
import it.rentalmanage.model.filemanager.IFileManager;
import it.rentalmanage.model.filemanager.JSonFileManager;
import it.rentalmanage.view.MainFrame;
import org.json.JSONArray;
import testJSONOBJECT.JSONTest;

import java.util.*;

/**
 * Created by utente on 22/02/2016.
 */
public class FirstPage {

    public static void main(String [] args){
        IFileManager  fileManager = new JSonFileManager();

        if(!fileManager.searchFileFromName("PersonList").exists()){
            fileManager.createEmptyFile("PersonList");
        }

        if (!fileManager.searchFileFromName("CarList").exists()){
            fileManager.createEmptyFile("CarList");
        }

        if (!fileManager.searchFileFromName("PersonHistoryList").exists()){
            fileManager.createEmptyFile("PersonHistoryList");
        }

        if (!fileManager.searchFileFromName("CarHistoryList").exists()){
            fileManager.createEmptyFile("CarHistoryList");
        }

        String filePersonData = fileManager.readFile(fileManager.searchFileFromName("PersonList"));
        JSONArray jArray = fileManager.writeToJArray(filePersonData);
        List<IPerson> personList = fileManager.fromJArrayToIPersonList(jArray);

        String fileCarData = fileManager.readFile(fileManager.searchFileFromName("CarList"));
        JSONArray jcarArr = fileManager.writeToJArray(fileCarData);
        List<ICar> carList = fileManager.fromJArrayToICarList(jcarArr);

        String filePersonHistory = fileManager.readFile(fileManager.searchFileFromName("PersonHistoryList"));
        JSONArray jPersHistArr = fileManager.writeToJArray(filePersonHistory);
        List<IPerson> personHistory = fileManager.fromJArrayToIPersonList(jPersHistArr);

        String fileCarHistory = fileManager.readFile(fileManager.searchFileFromName("CarHistoryList"));
        JSONArray jCarHistArr = fileManager.writeToJArray(fileCarHistory);
        List<ICar> carHistory = fileManager.fromJArrayToICarList(jCarHistArr);

        JSONTest j = new JSONTest();
        // j.stressTestPerson(personList,carList,personHistory,carHistory);
        //j.stressTestCar(personList,carList,personHistory,carHistory);


        IModel model= new Model(personList,carList,personHistory,carHistory);
        model.getAllPersons();


        new MainFrame(model);
    }
}
