package testJSONOBJECT;

import it.rentalmanage.model.*;
import it.rentalmanage.model.filemanager.IFileManager;
import it.rentalmanage.model.filemanager.JSonFileManager;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.util.*;


/**
 * Created by utente on 22/02/2016.
 */

public class JSONTest {


    public void stressTestPerson(Collection<IPerson> personList,Collection<ICar> carList,Collection<IPerson> personCollectionHist, Collection<ICar>carCollectionHist) {
        IModel m = new Model(personList, carList, personCollectionHist, carCollectionHist);
        IFileManager manager = new JSonFileManager();
        for (int i = 0; i <= 100; i++) {
            String name = "Mario";
            String surname = "Rossi";
            String fcode = "AAAAAA123456789" + i;
            String address = "Via pluto " + i;
            Date bDate = new Date(13111993);
            String phoneNumber = "123456" + i;
            List<DrivingLicence> licList = new LinkedList<>();
            licList.add(DrivingLicence.B);

            IPerson iPerson = new Person(new LinkedList<IRentedCarPeriod>(), fcode, phoneNumber, bDate, address, surname, name, licList);
            m.addPerson(iPerson);
            manager.writeList(manager.writePersonToJArray(m.getAllPersons().values()), "Person");
        }

    }

    public void stressTestCar(Collection<IPerson> personList,Collection<ICar> carList,Collection<IPerson> personCollectionHist, Collection<ICar>carCollectionHist){
        IModel m = new Model(personList, carList, personCollectionHist, carCollectionHist);
        IFileManager manager = new JSonFileManager();
        for(int i = 0; i<=50;i++){
            String manufactorer = "Fiat";
            String model = "punto";
            boolean rentable= true;
            String numberPlate = "1234AB" + i;
            int rentPrice = 50;
            int insuranceCost = 50;
            Date insuranceExpiring = new Date(16052016);
            int carSeats = 4;
            DrivingLicence requestedLicences = DrivingLicence.B;
            int carTaxCost = 50;
            Date carTaxDate = new Date(16052016);
            int motTestCost = 50;
            Date motTestDate = new Date(16052016);
            Collection<IRentalPeriod> rentalList = new LinkedList<IRentalPeriod>();

            ICar car = new Car(rentalList,motTestDate,motTestCost,carTaxDate,carTaxCost,rentPrice,requestedLicences,carSeats,insuranceExpiring,insuranceCost,numberPlate,manufactorer,model,rentable);
            m.addCar(car);
            manager.writeList(manager.writeCarToJArray(m.getAllCar().values()),"Car");
        }
    }


}
