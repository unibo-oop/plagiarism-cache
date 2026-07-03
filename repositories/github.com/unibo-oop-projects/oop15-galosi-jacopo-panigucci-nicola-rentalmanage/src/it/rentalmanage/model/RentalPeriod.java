package it.rentalmanage.model;

import java.util.Date;
import java.util.List;

/**
 * Created by utente on 24/02/2016.
 */
public class RentalPeriod implements IRentalPeriod {

    String FCode;
    Date startDate;
    Date endDate;

    public RentalPeriod(String FCode, Date startDate, Date endDate) {
        if(FCode == null || startDate == null){
            throw  new NullPointerException();
        }
        if (endDate != null) {
            if(endDate.getTime() < startDate.getTime()){
                throw new IllegalArgumentException();
            }
        }
        this.FCode = FCode;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public String getFCode() {
        return FCode;
    }

    @Override
    public Date getStartDate() {

        return this.startDate;
    }

    @Override
    public void setStartDate(Date startDate) {
        if(startDate == null){
            throw new NullPointerException();
        }
        this.startDate = startDate;
    }

    @Override
    public Date getEndDate() {
        return this.endDate;
    }

    @Override
    public void setEndDate(Date endDate) {
        if (endDate != null) {
            if(endDate.getTime() < this.startDate.getTime()){
                throw new IllegalArgumentException();
            }
        }
        this.endDate = endDate;
    }

}
