package it.rentalmanage.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 * Created by utente on 23/02/2016.
 */
public class Car implements ICar {

    private String manufactorer;
    private String model;
    private boolean rentable;
    private String numberPlate;
    private int rentPrice;
    private int insuranceCost;
    private Date insuranceExpiring;
    private int carSeats;
    private DrivingLicence requestedLicences;
    private int carTaxCost;
    private Date carTaxDate;
    private int motTestCost;
    private Date motTestDate;
    private final Collection<IRentalPeriod> rentalList;

    public Car(Collection<IRentalPeriod> rentalList, Date motTestDate, int motTestCost, Date carTaxDate, int carTaxCost,
               int rentPrice, DrivingLicence requestedLicences, int carSeats, Date insuranceExpiring, int insuranceCost, String numberPlate,
               String manufactorer, String model, boolean rentable) {
        if( carSeats == 0 || insuranceCost == 0 || motTestCost == 0 || rentPrice == 0){
            throw  new IllegalArgumentException();
        }
        if(rentalList == null || motTestDate == null || carTaxDate == null || requestedLicences == null || insuranceExpiring ==null
                || numberPlate == null || manufactorer == null){
            throw new NullPointerException();
        }

        this.rentalList = new ArrayList<>(rentalList);
        this.motTestDate = motTestDate;
        this.motTestCost = motTestCost;
        this.carTaxDate = carTaxDate;
        this.carTaxCost = carTaxCost;
        this.requestedLicences = requestedLicences;
        this.carSeats = carSeats;
        this.insuranceExpiring = insuranceExpiring;
        this.insuranceCost = insuranceCost;
        this.numberPlate = numberPlate;
        this.manufactorer = manufactorer;
        this.rentPrice=rentPrice;
        this.model = model;
        this.rentable = rentable;
    }

    @Override
    public void setManufactorer(String manufactorer) {
         this.manufactorer = manufactorer;
    }

    @Override
    public String getManufactorer() {
        return this.manufactorer;
    }

    @Override
    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public String getModel() {
        return this.model;
    }

    @Override
    public boolean isRentable() {
        return this.rentable;
    }

    @Override
    public void setRentability(boolean rentable) {
        this.rentable = rentable;
    }

    @Override
    public void setRentPrice(int rentPrice) {
        this.rentPrice= rentPrice;
    }

    @Override
    public int getRentPrice() {
        return this.rentPrice;
    }

    @Override
    public String getNumberPlate() {
        return this.numberPlate = numberPlate;
    }

    @Override
    public int getInsuranceCost() {
        return this.insuranceCost;
    }

    @Override
    public void setInsuranceCost(int insuranceCost) {
        this.insuranceCost = insuranceCost;
    }

    @Override
    public Date getInsuranceExpiring() {
        return this.insuranceExpiring;
    }

    @Override
    public void setInsuranceExpiring(Date insuranceExpiring) {
        this.insuranceExpiring = insuranceExpiring;
    }

    @Override
    public int getCarSeats() {
        return this.carSeats;
    }

    @Override
    public DrivingLicence getRequestedLicense() {
        return this.requestedLicences;
    }

    @Override
    public void setRequestedLicence(DrivingLicence drivingLicence) {
        this.requestedLicences = drivingLicence;
    }

    @Override
    public int getCarTaxCost() {
        return this.carTaxCost;
    }

    @Override
    public void setCarTaxCost(int carTaxCost) {
        this.carTaxCost = carTaxCost;
    }

    @Override
    public Date getCarTaxDate() {
        return this.carTaxDate;
    }

    @Override
    public void setCarTaxDate(Date carTaxDate) {
        this.carTaxDate = carTaxDate;
    }

    @Override
    public int getMotTestCost() {
        return this.motTestCost;
    }

    @Override
    public void setMotTestCost(int motTestCost) {
        this.motTestCost = motTestCost;
    }

    @Override
    public Date getMotTestDate() {
        return this.motTestDate;
    }

    @Override
    public void setMotTestDate(Date motTestDate) {
        this.motTestDate = motTestDate;
    }

    @Override
    public Collection<IRentalPeriod> getAllTenant() {
        return new ArrayList<>(this.rentalList);
    }

    @Override
    public void addTenant(IRentalPeriod personPeriod) {
        if (personPeriod == null) {
            throw new NullPointerException();
        }
        rentalList.add(personPeriod);
    }

    @Override
    public String toString(){
        return new String("Manufactorer " + this.manufactorer+ " model " + this.model);
    }
}
