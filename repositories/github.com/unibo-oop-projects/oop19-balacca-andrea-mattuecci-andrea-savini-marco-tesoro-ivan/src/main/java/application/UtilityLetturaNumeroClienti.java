package application;

import java.io.*;
import java.util.*;

public class UtilityLetturaNumeroClienti {
    
    Hotel hotel;
    
    public UtilityLetturaNumeroClienti(Hotel hotel) {
        this.hotel = hotel;
    }
    //colazione
    public int getnColazioneAdulti(Date data) {
        for (Map.Entry<Date,Integer[]> map : hotel.getnColazioneClientiGiornaliero().entrySet()) {
            if(map.getKey().equals(data)) {
                return map.getValue()[0];
            }
        }
        return 0;
    }
    
    public int getnColazioneBambini(Date data) {
        for (Map.Entry<Date,Integer[]> map : hotel.getnColazioneClientiGiornaliero().entrySet()) {
            if(map.getKey().equals(data)) {
                return map.getValue()[1];
            }
        }
        return 0;
    }
    //pranzo
    public int getnPranzoAdulti(Date data) {
        for (Map.Entry<Date,Integer[]> map : hotel.getnPranzoClientiGiornaliero().entrySet()) {
            if(map.getKey().equals(data)) {
                return map.getValue()[0];
            }
        }
        return 0;
    }
    
    public int getnPranzoBambini(Date data) {
        for (Map.Entry<Date,Integer[]> map : hotel.getnPranzoClientiGiornaliero().entrySet()) {
            if(map.getKey().equals(data)) {
                return map.getValue()[1];
            }
        }
        return 0;
    }
    //cena
    public int getnCenaAdulti(Date data) {
        for (Map.Entry<Date,Integer[]> map : hotel.getnCenaClientiGiornaliero().entrySet()) {
            if(map.getKey().equals(data)) {
                return map.getValue()[0];
            }
        }
        return 0;
    }
    
    public int getnCenaBambini(Date data) {
        for (Map.Entry<Date,Integer[]> map : hotel.getnCenaClientiGiornaliero().entrySet()) {
            if(map.getKey().equals(data)) {
                return map.getValue()[1];
            }
        }
        return 0;
    }
}
