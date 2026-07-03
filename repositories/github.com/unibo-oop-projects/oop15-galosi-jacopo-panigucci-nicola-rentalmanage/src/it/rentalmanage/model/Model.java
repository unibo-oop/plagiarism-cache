package it.rentalmanage.model;

import java.util.*;

/**
 * Created by utente on 26/02/2016.
 */
public class Model implements IModel {

    private final Map<String ,IPerson> personMap;
    private final Map<String,ICar> carMap;
    private final Map<String,IPerson> personHistory;
    private final Map<String,ICar> carHistory;

    public Model(Collection<IPerson> personList,Collection<ICar> carList, Collection<IPerson> personHistoryCol, Collection<ICar> carHistoryCol) {
        if(personList == null || carList == null || personHistoryCol == null || carHistoryCol == null){
            throw new IllegalArgumentException();
        }
        this.personMap = new HashMap<>();
        for (IPerson iPerson : personList) {
            // controllo duplicati
            if(personMap.put(iPerson.getFiscalCode(),iPerson)!= null){
                throw new IllegalArgumentException();
            }
        }
        this.carMap = new HashMap<>();
        for (ICar iCar : carList) {
            if(carMap.put(iCar.getNumberPlate(),iCar)!= null){
                throw new IllegalArgumentException();
            }
        }
        this.personHistory = new HashMap<>();
        for (IPerson iPerson : personHistoryCol) {
            if(personHistory.put(iPerson.getFiscalCode(),iPerson)!= null){
                throw new IllegalArgumentException();
            }
        }
        this.carHistory = new HashMap<>();
        for (ICar car : carHistoryCol) {
            if(carHistory.put(car.getNumberPlate(),car)!= null){
                throw new IllegalArgumentException();
            }
        }

    }

    @Override
    public Map<String, IPerson> getAllPersons() {
        return new HashMap<>(this.personMap);
    }

    @Override
    public Map<String, ICar> getAllCar() {
        return new HashMap<>(this.carMap);

    }

    @Override
    public Map<String, IPerson> getAllPersonsHistory() {
        return new HashMap<>(this.personHistory);
    }

    @Override
    public IPerson getPersonFromFCodeHistory(String fCode) {
        Map<String, IPerson> map = new HashMap<>(getAllPersonsHistory());
        if (!map.containsKey(fCode)){
            throw new IllegalArgumentException();
        }
        return map.get(fCode);
    }

    @Override
    public ICar getCarFromNumPlateHistory(String numberPlate) {
        Map<String,ICar> map = new HashMap<>(getAllCarsHistory());
        if (!map.containsKey(numberPlate)){
            throw new IllegalArgumentException();
        }
        return map.get(numberPlate);
    }

    @Override
    public Map<String, ICar> getAllCarsHistory() {
        return new HashMap<>(this.carHistory);
    }

    @Override
    public void addPerson(IPerson person) {
        if(person == null){
            throw new NullPointerException();
        }
        personMap.put(person.getFiscalCode(),person);
    }

    @Override
    public void addPersonToHistory(IPerson person) {
        if(person == null){
            throw new NullPointerException();
        }
        personHistory.put(person.getFiscalCode(),person);
    }

    @Override
    public void removePerson(IPerson person) {
        if(person == null){
            throw new NullPointerException();
        }
        if(!personMap.containsKey(person.getFiscalCode())){
            throw new IllegalArgumentException();
        }
        personMap.remove(person.getFiscalCode());
    }

    @Override
    public void addCar(ICar car) {
       if(car == null){
            throw new NullPointerException();
        }
        carMap.put(car.getNumberPlate(),car);
    }

    @Override
    public void addCarToHistory(ICar car) {
        if(car == null){
            throw new NullPointerException();
        }
        carHistory.put(car.getNumberPlate(),car);
    }

    @Override
    public void removeCar(ICar car) {
        if(car == null){
            throw new NullPointerException();
        }
        if(!carMap.containsKey(car.getNumberPlate())){
            throw new IllegalArgumentException();
        }
        carMap.remove(car.getNumberPlate());
    }




}
