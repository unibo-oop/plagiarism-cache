package it.rentalmanage.model.filemanager;

import it.rentalmanage.model.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.*;
import java.util.*;

/**
 * Created by utente on 26/02/2016.
 */

public class JSonFileManager implements IFileManager {


    private File file;
    private FileWriter filew;
    private BufferedWriter write;



    @Override
    public void writeList(JSONArray list, String type) {
        String filename = type + "List";
        String pathname =System.getProperty("user.home") + System.getProperty("file.separator")+ "Documents"+ System.getProperty("file.separator")+ "rentalmanagedata"+ System.getProperty("file.separator")+filename + ".txt";
        createJArrFile(pathname, list);
    }

    @Override
    public JSONArray writePersonToJArray(Collection<IPerson> list) {
        if(list == null){
            return new JSONArray();
        }
        JSONArray jArr = new JSONArray();
        Iterator<IPerson> iter = list.iterator();
        while(iter.hasNext()){
            JSONObject personObject = writeIPersonToJSON(iter.next());
            System.out.println(personObject);
            jArr.put(personObject);
        }
        System.out.println(jArr);
        return jArr;
    }

    @Override
    public JSONArray writeCarToJArray(Collection<ICar> list) {
        if(list == null){
            return new JSONArray();
        }
        JSONArray jArr = new JSONArray();
        Iterator<ICar> iter = list.iterator();
        while (iter.hasNext()){
            JSONObject carObject = writeICarToJSON(iter.next());
            jArr.put(carObject);
        }
        return jArr;
    }


    @Override
    public List<IPerson> fromJArrayToIPersonList(JSONArray list) {
        List<JSONObject> jlist = new LinkedList<>();


        for(int i = 0; i< list.length(); i++){
            jlist.add(list.getJSONObject(i));
        }

        List<IPerson> personList = new ArrayList<>();
        Iterator<JSONObject> iter = jlist.listIterator();
        while(iter.hasNext()){
            personList.add(getPerson(iter.next()));
        }
        return personList;
    }

    @Override
    public List<ICar> fromJArrayToICarList(JSONArray list) {
        List<JSONObject> jlist = new LinkedList<>();

        for(int i = 0; i< list.length(); i++){
            jlist.add(list.getJSONObject(i));
        }
        List<ICar> carList = new ArrayList<>();
        Iterator<JSONObject> iter = jlist.listIterator();
        while(iter.hasNext()){
            carList.add(getCar(iter.next()));
        }
        return carList;
    }

    @Override
    public ICar getCar(JSONObject car) {
        String manufactorer = car.getString("Manufactorer");
        String numberPlate = car.getString("Number Plate");
        int rentPrice = car.getInt("Rent Price");
        int insuranceCost = car.getInt("Insurance Cost");
        Long insTimeStamp = car.getLong("Insurance Expiring");
        Date insuranceExpiring = new Date(insTimeStamp);
        int carSeats = car.getInt("Car Seats");
        String requestedLicences = car.getString("Requested Licences");
        int carTaxCost = car.getInt("Car Tax Cost");
        Long carTimeStamp = car.getLong("Car Tax Date");
        Date carTaxDate = new Date(carTimeStamp);
        int motTestCost = car.getInt("Mot Test Cost");
        Long motTimeStamp = car.getLong("Mot Test Date");
        Date motTestDate = new Date(motTimeStamp);
        Collection<IRentalPeriod> rentalList = new ArrayList<>();
        JSONArray rentArray = car.getJSONArray("Rental Period List");
        for(int i = 0; i<rentArray.length(); i++){
            JSONObject obj = rentArray.getJSONObject(i);
            String FCode = obj.getString("Fiscal Code");
            Long startTimeStamp = obj.getLong("Start Date");
            Date startDate = new Date(startTimeStamp);
            Long endTimeStamp = obj.getLong("End Date");
            Date endDate = new Date(endTimeStamp);

            IRentalPeriod personPeriod = new RentalPeriod(FCode,startDate,endDate);
            rentalList.add(personPeriod);
        }
        String model = car.getString("Model");
        boolean rentable = car.getBoolean("Rentable");

        return new Car(rentalList,motTestDate,motTestCost,carTaxDate,carTaxCost,rentPrice,DrivingLicence.valueOf(requestedLicences),carSeats,insuranceExpiring,insuranceCost,numberPlate,manufactorer,model,rentable);
    }

    @Override
    public IPerson getPerson(JSONObject person) {
        String name = person.getString("Name");
        String surname = person.getString("Surname");
        String address = person.getString("Address");
        Long timeStamp = person.getLong("Date");
        Date birthDate = new Date(timeStamp);
        String phoneNumber = person.getString("Phone Number");
        String fiscalCode = person.getString("Fiscal Code");
        JSONArray licArray = person.getJSONArray("Licence List");
        List<DrivingLicence> licencesList = new LinkedList<>();
        for(int i = 0; i< licArray.length(); i++){
            int index = licArray.getInt(i);
            licencesList.add(DrivingLicence.values()[index]);
        }
        JSONArray rentArray = person.getJSONArray("Rented Car List");
        Collection<IRentedCarPeriod> rentedCarPeriods = new ArrayList<>();
        for (int i = 0; i< rentArray.length(); i++) {
            JSONObject obj = rentArray.getJSONObject(i);
            String numberPlate = obj.getString("Number Plate");
            Long startTimeStamp = obj.getLong("Start Date");
            Date startDate = new Date(startTimeStamp);
            Long endTimeStamp = obj.getLong("End Date");
            Date endDate = new Date(endTimeStamp);
            int rentedPrice = obj.getInt("Rented Price");
            IRentedCarPeriod carPeriod = new RentedCarPeriod(numberPlate,startDate,endDate,rentedPrice);
            rentedCarPeriods.add(carPeriod);

        }
        return new Person(rentedCarPeriods,fiscalCode,phoneNumber,birthDate,address,surname,name,licencesList);
    }



    @Override
    public JSONObject writeIPersonToJSON(IPerson person) {
        JSONObject personObject = new JSONObject();
        personObject.put("Name", person.getName());
        personObject.put("Surname", person.getSurname());
        personObject.put("Address", person.getAddress());
        personObject.put("Date", person.getBirthDate().getTime());
        personObject.put("Phone Number", person.getPhoneNumber());
        personObject.put("Fiscal Code", person.getFiscalCode());
        JSONArray licenceList = new JSONArray();
        Collection<DrivingLicence> liclist = person.getDrivingLicense();
        Iterator<DrivingLicence> iterator = liclist.iterator();
        while (iterator.hasNext()){

            DrivingLicence l = iterator.next();
            switch (l){
                case A1:
                    licenceList.put(0);
                    break;
                case A2:
                    licenceList.put(1);
                    break;
                case A3:
                    licenceList.put(2);
                    break;
                case B:
                    licenceList.put(3);
                    break;
                case C:
                    licenceList.put(4);
                    break;
                case D:
                    licenceList.put(5);
                    break;
                case BE:
                    licenceList.put(6);
                    break;
                case CE:
                    licenceList.put(7);
                    break;
                case DE:
                    licenceList.put(8);
                    break;
            }

        }
        personObject.put("Licence List", licenceList);
        JSONArray rentedCarList = new JSONArray();
        Iterator<IRentedCarPeriod> iter = person.getAllRentedCar().iterator();
        while (iter.hasNext()){

            IRentedCarPeriod period = iter.next();
            JSONObject rentData = new JSONObject();
            rentData.put("Number Plate", period.getNumberPlate());
            rentData.put("Start Date",period.getStartDate().getTime());
            rentData.put("End Date", period.getEndDate().getTime());
            rentData.put("Rented Price",period.getRentedPrice());
            rentedCarList.put(rentData);


        }

        personObject.put("Rented Car List", rentedCarList);

        return personObject;

    }

    @Override
    public JSONObject writeICarToJSON(ICar car) {
        JSONObject carObject = new JSONObject();
        carObject.put("Manufactorer", car.getManufactorer());
        carObject.put("Number Plate", car.getNumberPlate());
        carObject.put("Rent Price", car.getRentPrice());
        carObject.put("Insurance Cost", car.getInsuranceCost());
        carObject.put("Insurance Expiring", car.getInsuranceExpiring().getTime());
        carObject.put("Car Seats", car.getCarSeats());
        carObject.put("Requested Licences", car.getRequestedLicense());
        carObject.put("Car Tax Cost",car.getCarTaxCost());
        carObject.put("Car Tax Date", car.getCarTaxDate().getTime());
        carObject.put("Mot Test Cost", car.getMotTestCost());
        carObject.put("Mot Test Date", car.getMotTestDate().getTime());
        JSONArray rentalPeriodList = new JSONArray();
        Iterator<IRentalPeriod>  iter = car.getAllTenant().iterator();
        while(iter.hasNext()){
            IRentalPeriod period = iter.next();
            JSONObject rentData = new JSONObject();
            rentData.put("Fiscal Code", period.getFCode());
            rentData.put("Start Date", period.getStartDate().getTime());
            rentData.put("End Date", period.getEndDate().getTime());
            rentalPeriodList.put(rentData);
        }
        carObject.put("Rental Period List", rentalPeriodList);
        carObject.put("Model", car.getModel());
        carObject.put("Rentable", car.isRentable());

        return carObject;

    }


    @Override
    public void createEmptyFile(String fileName) {
        file =new File(System.getProperty("user.home") + System.getProperty("file.separator")+ "Documents"+ System.getProperty("file.separator")+ "rentalmanagedata",fileName +".txt");
        try {
            file.getParentFile().mkdirs();
            filew = new FileWriter(file);
            write = new BufferedWriter(filew);
            write.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public JSONArray writeToJArray(String list) {
        if(list == null){
            return new JSONArray();
        }
        return new JSONArray(list);
    }

    @Override
    public void createJArrFile(String pathname,JSONArray thingToWrite) {
        file = new File(pathname);
        try {
            file.getParentFile().mkdirs();
            filew = new FileWriter(file);
            write = new BufferedWriter(filew);
            write.write(thingToWrite.toString());
            write.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


        @Override
    public String readFile(File file) {
        String currentLine = null;
        try {
            BufferedReader r = new BufferedReader(new InputStreamReader(new FileInputStream((file))));

            if((currentLine = r.readLine())!= null){
                r.close();
                return currentLine;
            } else {
                return null;
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return currentLine;
    }

    @Override
    public File searchFileFromName(String fileName) {
        return new File(System.getProperty("user.home") + System.getProperty("file.separator")+ "Documents"+ System.getProperty("file.separator")+ "rentalmanagedata",fileName +".txt");
    }



}