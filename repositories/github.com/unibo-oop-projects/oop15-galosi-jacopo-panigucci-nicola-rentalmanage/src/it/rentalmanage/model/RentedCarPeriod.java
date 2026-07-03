package it.rentalmanage.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by utente on 26/02/2016.
 */
public class RentedCarPeriod implements  IRentedCarPeriod{

    private String numberPlate;
    private Date startDate;
    private Date endDate;
    private int rentedPrice;

    public RentedCarPeriod(String numberPlate, Date startDate, Date endDate, int rentedPrice) {
        if(numberPlate == null || startDate == null || endDate == null){
            throw new NullPointerException();
        }
        this.numberPlate = numberPlate;
        this.startDate = startDate;
        this.endDate = endDate;
        this.rentedPrice = rentedPrice;
    }

    @Override
    public String getNumberPlate() {
        return numberPlate;
    }

    @Override
    public void setNumberPlate(String numberPlate) {
        this.numberPlate = numberPlate;
    }

    @Override
    public Date getStartDate() {
        return this.startDate;
    }

    @Override
    public void setStartDate(Date startDate) {
        if(this.startDate == null){
            throw  new NullPointerException();
        }
        this.startDate = startDate;
    }

    @Override
    public Date getEndDate() {
        return this.endDate;
    }

    @Override
    public void setEndDate(Date endDate) {
        if(endDate == null){
            throw  new IllegalArgumentException();
        }
        if(endDate.getTime() < this.startDate.getTime()){
            throw new IllegalArgumentException();
        }
        this.endDate = endDate;
    }

    @Override
    public int getRentedPrice() {
        return this.rentedPrice;
    }

}
