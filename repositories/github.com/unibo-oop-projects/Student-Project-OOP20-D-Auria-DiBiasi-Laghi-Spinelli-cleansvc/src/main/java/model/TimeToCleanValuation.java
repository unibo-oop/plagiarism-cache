package model;

import java.time.*;
/**
 * 
 * @author Nico Nize
 *
 */
public class TimeToCleanValuation {
    

    private Integer numFasi;
    private Integer numDipendenti;
    private Integer mqStanza;
    
    public TimeToCleanValuation(Integer numFasi, Integer numDipendeti, Integer mqStanza) {
        this.numFasi = numFasi;
        this.numDipendenti = numDipendeti;
        this.mqStanza = mqStanza;  
    }
    
    public long washValuation() {                     
        Duration timeToRoom =  CleaningTimeEnum.LAVAGGIO.getSeconds().multipliedBy(this.getMqStanza())

                                                                .dividedBy(this.getNumDipendenti());
        return timeToRoom.toMinutes();
    }
    
    public long disinfectionValuation() {
        Duration timeToRoom = CleaningTimeEnum.DISINFEZIONE.getSeconds().multipliedBy(this.getMqStanza())
                                                                   .dividedBy(this.getNumDipendenti());
        return timeToRoom.toMinutes();
    }
    
    public long flushingValuation() {
        Duration timeToRoom = CleaningTimeEnum.RISCIACQUO.getSeconds().multipliedBy(this.getMqStanza())
                                                                   .dividedBy(this.getNumDipendenti());
        return timeToRoom.toMinutes();
    }
    
    public long completeSet() {
        Long completeTime = (this.washValuation() + this.disinfectionValuation() + this.flushingValuation());
        return completeTime;
    }
    
    public int getNumFasi() {
        return numFasi;
    }

    public void setNumFasi(int numFasi) {
        this.numFasi = numFasi;
    }

    public int getNumDipendenti() {
        return numDipendenti;
    }

    public void setNumDipendenti(int numDipendenti) {
        this.numDipendenti = numDipendenti;
    }

    public int getMqStanza() {
        return mqStanza;
    }

    public void setMqStanza(int mqStanza) {
        this.mqStanza = mqStanza;
    }
    
}
