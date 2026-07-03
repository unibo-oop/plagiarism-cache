package it.rentalmanage.model;

import java.util.*;

/**
 * Created by utente on 23/02/2016.
 */
public class Person  extends Observable implements IPerson {

    private String name;
    private String surname;
    private String address;
    private Date birthDate;
    private String phoneNumber;
    private String fiscalCode;
    private List<DrivingLicence> licencesList;
    private final Collection<IRentedCarPeriod> rentedCarList;

    public Person(Collection<IRentedCarPeriod> carPeriodList, String fiscalCode, String phoneNumber, Date birthDate, String address, String surname, String name, List<DrivingLicence> licencesList) {
        if (carPeriodList == null || fiscalCode == null || phoneNumber == null || birthDate == null || address == null || surname == null || name == null || licencesList == null) {
            throw new NullPointerException();
        }

        this.rentedCarList = carPeriodList;
        this.licencesList = new LinkedList<>(licencesList);
        this.fiscalCode = fiscalCode;
        this.phoneNumber = phoneNumber;
        this.birthDate = birthDate;
        this.address = address;
        this.surname = surname;
        this.name = name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
        this.setChanged();

    }

    @Override
    public void setSurname(String surname) {
        this.surname = surname;
        this.setChanged();
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getSurname() {
        return this.surname;
    }

    @Override
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        this.setChanged();

    }

    @Override
    public String getPhoneNumber() {
        return this.phoneNumber;
    }


    @Override
    public String getFiscalCode() {
        return this.fiscalCode;
    }

    @Override
    public String getAddress() {
        return this.address;
    }

    @Override
    public void setAddress(String address) {
        this.address = address;
        this.setChanged();

    }

    @Override
    public List<DrivingLicence> getDrivingLicense() {
        List<DrivingLicence> list = new LinkedList<>(licencesList);
        return list;

    }

    @Override
    public Date getBirthDate() {
        return this.birthDate;
    }


    @Override
    public Collection<IRentedCarPeriod> getAllRentedCar() {
        return new ArrayList<>(this.rentedCarList);
    }

    @Override
    public void addCar(IRentedCarPeriod carPeriod) {
        if (carPeriod == null) {
            throw new NullPointerException();
        }
        rentedCarList.add(carPeriod);
        this.setChanged();

    }
}

