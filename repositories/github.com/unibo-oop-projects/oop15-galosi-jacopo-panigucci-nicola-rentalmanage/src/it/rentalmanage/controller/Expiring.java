package it.rentalmanage.controller;

import it.rentalmanage.model.ICar;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by nicolapanigucci on 01/05/16.
 */
public class Expiring implements IExpiring{

    private static final String INSURANCE = "Insurance";
    private static final String CARTAX = "Car Tax";
    private static final String MOTTEST = "MOT Test";

    private ICar car;
    private Calendar cal;

    public Expiring(ICar iCar){
        this.car = iCar;
        this.cal = Calendar.getInstance();
        this.cal.setTime(new Date());
        this.cal.add(Calendar.DAY_OF_MONTH, 15);
    }

    @Override
    public List<String> checkExpiring(){
        List<String> exp = new ArrayList<>();

        //scadenza entro 15 giorni
        if (!car.getInsuranceExpiring().after(cal.getTime())){
            exp.add(INSURANCE);
        }
        if (!car.getCarTaxDate().after(cal.getTime())){
            exp.add(CARTAX);
        }
        if (!car.getMotTestDate().after(cal.getTime())){
            exp.add(MOTTEST);
        }

        return exp;
    }

    @Override
    public String getTextInsurance(){
        return INSURANCE;
    }

    @Override
    public String getTextCarTax(){
        return CARTAX;
    }

    @Override
    public String getTextMOTTest(){
        return MOTTEST;
    }
}
