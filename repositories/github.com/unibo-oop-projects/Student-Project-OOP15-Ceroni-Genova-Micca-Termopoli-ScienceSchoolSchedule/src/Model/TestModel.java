package Model;

import Controller.ControllerWorkers;
import Controller.Reservation;
import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.List;
import Model.ErrorException;
import Model.WarningException;
import org.junit.Test;

/**
 * 
 *@author Francesco Ceroni
 * 
 */


public class TestModel {

    List<Reservation> listReservation = new ArrayList<>();

    /**
     * @throws WarningException
     * @throws ErrorException 
     */
    @Test
    public final void test() throws WarningException, ErrorException {

        final ControllerWorkers cntr = new ControllerWorkers();
        try {
            cntr.addRes(new Reservation(new PersonImpl("Antonella", "Carbonaro"),
                    new CoursesImpl("Programmazione", Type.FIRST_YEAR), Days.TUESDAY, Hours.PERIOD2,
                    new RoomImpl("Aula B")));
            cntr.addRes(new Reservation(new PersonImpl("Mirko", "Viroli"),
                    new CoursesImpl("Programmazione ad Oggetti", Type.SECOND_YEAR), Days.MONDAY, Hours.PERIOD1,
                    new RoomImpl("Aula Magna Vicolo Carbonari")));
            cntr.addRes(new Reservation(new PersonImpl("Rossano", "Codeluppi"),
                    new CoursesImpl("Elettronica dei Sistemi Digitali", Type.SECOND_YEAR_ENG), Days.THURSDAY,
                    Hours.PERIOD3, new RoomImpl("Aula C")));
            cntr.addRes(new Reservation(new PersonImpl("Fabrizio", "Caselli"),
                    new CoursesImpl("Calcolo Combinatorio e Probabilità", Type.SECOND_YEAR_SCI), Days.WEDNESDAY,
                    Hours.PERIOD4, new RoomImpl("Aula A")));
            cntr.addRes(new Reservation(new PersonImpl("Stefano", "Rizzi"),
                    new CoursesImpl("Ingegneria del Software", Type.THIRD_YEAR), Days.FRIDAY, Hours.PERIOD5,
                    new RoomImpl("Aula D")));
            cntr.addRes(new Reservation(new PersonImpl("Claudia", "Cevenini"),
                    new CoursesImpl("Informatica e Diritto", Type.THIRD_YEAR_ENG), Days.MONDAY, Hours.PERIOD6,
                    new RoomImpl("Aula E")));
            cntr.addRes(new Reservation(new PersonImpl("Matteo", "Golfarelli"),
                    new CoursesImpl("Laboratorio di Basi di Dati", Type.THIRD_YEAR_SCI), Days.TUESDAY, Hours.PERIOD7,
                    new RoomImpl("Laboratorio Vela")));
            cntr.addRes(new Reservation(new PersonImpl("Damiana", "Lazzaro"),
                    new CoursesImpl("Computer Graphics", Type.THIRD_YEAR_OPT), Days.THURSDAY, Hours.PERIOD8,
                    new RoomImpl("Laboratorio 2")));
            cntr.addRes(new Reservation(new PersonImpl("Mario", "Bravetti"),
                    new CoursesImpl("Linguaggi di Programmazione e Modelli Computazionali", Type.FOURTH_YEAR), Days.WEDNESDAY, Hours.PERIOD5,
                    new RoomImpl("Laboratorio 2")));
            cntr.addRes(new Reservation(new PersonImpl("Antonio", "Natali"),
                    new CoursesImpl("Attività Propedeutica alla Prova Finale", Type.FIFTH_YEAR), Days.FRIDAY, Hours.PERIOD6,
                    new RoomImpl("Laboratorio 3")));
            cntr.addRes(new Reservation(new PersonImpl("Franco", "Callegati"),
                    new CoursesImpl("Instradamento e Trasporto in Internet", Type.FIFTH_YEAR_OPT), Days.MONDAY, Hours.PERIOD2,
                    new RoomImpl("Laboratorio Vela")));
            

        } catch (ErrorException e) {
            e.printStackTrace();
        }

        try { // Stessa Giorno, Ora, Aula, Prof, Tipologia di Corso
            cntr.addRes(new Reservation(new PersonImpl("Antonella", "Carbonaro"),
                    new CoursesImpl("Programmazione", Type.FIRST_YEAR), Days.TUESDAY, Hours.PERIOD2,
                    new RoomImpl("Aula B")));
        } catch (ErrorException e) {
            e.printStackTrace();
            assertEquals(cntr.getListReservation().size(), 11);

        }

        try { // Stessa tipologia di corso 1°Anno
            cntr.addRes(new Reservation(new PersonImpl("Paolo", "Albano"),
                    new CoursesImpl("Analisi Matematica", Type.FIRST_YEAR), Days.TUESDAY, Hours.PERIOD2,
                    new RoomImpl("Aula A")));
        } catch (ErrorException e) {
            e.printStackTrace();
            assertEquals(cntr.getListReservation().size(), 11);

        }
        
        try { // Stessa tipologia di corso 2°Anno
            cntr.addRes(new Reservation(new PersonImpl("Vittorio", "Ghini"),
                    new CoursesImpl("Sistemi Operativi", Type.SECOND_YEAR), Days.MONDAY, Hours.PERIOD1,
                    new RoomImpl("Aula Magna Vicolo Carbonari")));
        } catch (ErrorException e) {
            e.printStackTrace();
            assertEquals(cntr.getListReservation().size(), 11);
        }
        
        try { // Stessa tipologia di corso 2°Anno ING
            cntr.addRes(new Reservation(new PersonImpl("Gianluca","Palli"),
                    new CoursesImpl("Controlli Automatici",Type.SECOND_YEAR_ENG), Days.THURSDAY,
                    Hours.PERIOD3, new RoomImpl("Aula A")));
        } catch (ErrorException e) {
            e.printStackTrace();
            assertEquals(cntr.getListReservation().size(), 11);
        }
        
        try { // Stessa tipologia di corso 2°Anno SCI
            cntr.addRes(new Reservation(new PersonImpl("Damiana", "Lazzaro"),
                    new CoursesImpl("Algoritmi Numerici",Type.SECOND_YEAR_SCI), Days.WEDNESDAY,
                    Hours.PERIOD4, new RoomImpl("Aula B")));
        } catch (ErrorException e) {
            e.printStackTrace();
            assertEquals(cntr.getListReservation().size(), 11);
        }
        
        try { // Stessa tipologia di corso 3°Anno 
            cntr.addRes(new Reservation(new PersonImpl("Gabriele", "D'Angelo"),
                    new CoursesImpl("Programmazione di Reti", Type.THIRD_YEAR), Days.FRIDAY, Hours.PERIOD5,
                    new RoomImpl("Aula C")));
        } catch (ErrorException e) {
            e.printStackTrace();
            assertEquals(cntr.getListReservation().size(), 11);
        }
        
        try { // Stessa tipologia di corso 3°Anno ING 
            cntr.addRes(new Reservation(new PersonImpl("Antonio", "Focacci"),
                    new CoursesImpl("Economia e Organizzazione Aziendale", Type.THIRD_YEAR_ENG), Days.FRIDAY, Hours.PERIOD5,
                    new RoomImpl("Aula D")));
        } catch (ErrorException e) {
            e.printStackTrace();
            assertEquals(cntr.getListReservation().size(), 11);
        }
        
        try { // Stessa tipologia di corso 3°Anno SCI 
            cntr.addRes(new Reservation(new PersonImpl("Matteo", "Golfarelli"),
                    new CoursesImpl("Laboratorio di Basi di Dati", Type.THIRD_YEAR_SCI), Days.TUESDAY, Hours.PERIOD7,
                    new RoomImpl("Laboratorio 2")));
        } catch (ErrorException e) {
            e.printStackTrace();
            assertEquals(cntr.getListReservation().size(), 11);
        }
        
        try { // Stessa tipologia di corso 3°Anno OPT 
            cntr.addRes(new Reservation(new PersonImpl("Giuseppe","Levi"),
                    new CoursesImpl("High Performance Computing", Type.THIRD_YEAR_OPT), Days.FRIDAY, Hours.PERIOD5,
                    new RoomImpl("Aula A")));
        } catch (ErrorException e) {
            e.printStackTrace();
            assertEquals(cntr.getListReservation().size(), 11);
        }
        
                
        try { // Sovrapposizione tra corsi che non si devono sovrapporre SCI
            cntr.addRes(new Reservation(new PersonImpl("Matteo", "Golfarelli"),
                    new CoursesImpl("Laboratorio di Basi di Dati", Type.THIRD_YEAR_SCI), Days.FRIDAY, Hours.PERIOD5,
                    new RoomImpl("Aula D")));
        } catch (ErrorException e) {
            e.printStackTrace();
            assertEquals(cntr.getListReservation().size(), 11);
        }
        
        try { // Sovrapposizione tra corsi che non si devono sovrapporre ING 
            cntr.addRes(new Reservation(new PersonImpl("Antonio", "Focacci"),
                    new CoursesImpl("Economia e Organizzazione Aziendale", Type.THIRD_YEAR), Days.FRIDAY, Hours.PERIOD5,
                    new RoomImpl("Aula C")));
        } catch (ErrorException e) {
            e.printStackTrace();
            assertEquals(cntr.getListReservation().size(), 11);
        }
        
        try { // Stessa tipologia di corso 3°Anno OPT 
            cntr.addRes(new Reservation(new PersonImpl("Giuseppe","Levi"),
                    new CoursesImpl("High Performance Computing", Type.THIRD_YEAR_OPT), Days.FRIDAY, Hours.PERIOD5,
                    new RoomImpl("Aula A")));
        } catch (ErrorException e) {
            e.printStackTrace();
            assertEquals(cntr.getListReservation().size(), 11);
        }
        
        try { // Stessa tipologia di corso 4°Anno  
            cntr.addRes(new Reservation(new PersonImpl("Stefano", "Rizzi"),
                    new CoursesImpl("Business Intelligence", Type.FOURTH_YEAR), Days.WEDNESDAY, Hours.PERIOD5,
                    new RoomImpl("Laboratorio 3")));
        } catch (ErrorException e) {
            e.printStackTrace();
            assertEquals(cntr.getListReservation().size(), 11);
        }
        
        try { // Stessa tipologia di corso 5°Anno  
            cntr.addRes(new Reservation(new PersonImpl("Catia", "Prandi"),
                    new CoursesImpl("Applicazioni e Servizi Web", Type.FIFTH_YEAR), Days.FRIDAY, Hours.PERIOD6,
                    new RoomImpl("Laboratorio 2")));
        } catch (ErrorException e) {
            e.printStackTrace();
            assertEquals(cntr.getListReservation().size(), 11);
        }
        
        
        try { // Stessa tipologia di corso 5°Anno OPT
            cntr.addRes(new Reservation(new PersonImpl("Andrea","Omicini"),
                    new CoursesImpl("Sistemi Autonomi", Type.FIFTH_YEAR_OPT), Days.MONDAY, Hours.PERIOD2,
                    new RoomImpl("Aula A")));
        } catch (ErrorException e) {
            e.printStackTrace();
            assertEquals(cntr.getListReservation().size(), 11);
        }
        
        
        try { // 6 ore di seguito 
            cntr.addRes(new Reservation(new PersonImpl("Mirko", "Viroli"),
                    new CoursesImpl("Programmazione ad Oggetti", Type.SECOND_YEAR), Days.MONDAY, Hours.PERIOD2,
                    new RoomImpl("Aula Magna Vicolo Carbonari")));
            cntr.addRes(new Reservation(new PersonImpl("Mirko", "Viroli"),
                    new CoursesImpl("Programmazione ad Oggetti", Type.SECOND_YEAR), Days.MONDAY, Hours.PERIOD3,
                    new RoomImpl("Aula Magna Vicolo Carbonari")));
            cntr.addRes(new Reservation(new PersonImpl("Mirko", "Viroli"),
                    new CoursesImpl("Programmazione ad Oggetti", Type.SECOND_YEAR), Days.MONDAY, Hours.PERIOD4,
                    new RoomImpl("Aula Magna Vicolo Carbonari")));
            cntr.addRes(new Reservation(new PersonImpl("Mirko", "Viroli"),
                    new CoursesImpl("Programmazione ad Oggetti", Type.SECOND_YEAR), Days.MONDAY, Hours.PERIOD5,
                    new RoomImpl("Aula Magna Vicolo Carbonari")));    
            cntr.addRes(new Reservation(new PersonImpl("Mirko", "Viroli"),
                    new CoursesImpl("Programmazione ad Oggetti", Type.SECOND_YEAR), Days.MONDAY, Hours.PERIOD6,
                    new RoomImpl("Aula Magna Vicolo Carbonari")));
        } catch (ErrorException e) {
            e.printStackTrace();
            assertEquals(cntr.getListReservation().size(), 16);
        }
        
        
        try { // 6 ore di seguito 
            cntr.addRes(new Reservation(new PersonImpl("Mirko", "Viroli"),
                    new CoursesImpl("Programmazione ad Oggetti", Type.SECOND_YEAR), Days.TUESDAY, Hours.PERIOD2,
                    new RoomImpl("Aula Magna Vicolo Carbonari")));
            cntr.addRes(new Reservation(new PersonImpl("Mirko", "Viroli"),
                    new CoursesImpl("Programmazione ad Oggetti", Type.SECOND_YEAR), Days.THURSDAY, Hours.PERIOD3,
                    new RoomImpl("Aula Magna Vicolo Carbonari")));
            cntr.addRes(new Reservation(new PersonImpl("Mirko", "Viroli"),
                    new CoursesImpl("Programmazione ad Oggetti", Type.SECOND_YEAR), Days.FRIDAY, Hours.PERIOD4,
                    new RoomImpl("Aula Magna Vicolo Carbonari")));
        } catch (ErrorException e) {
            e.printStackTrace();
            assertEquals(cntr.getListReservation().size(), 16);
        }
        
       
        
        
        //Remove 
        /*
        this.listReservation.add(new Reservation(new PersonImpl("Antonella", "Carbonaro"),
                    new CoursesImpl("Programmazione", Type.FIRST_YEAR), Days.TUESDAY, Hours.PERIOD2,
                    new RoomImpl("Aula B")));
        cntr.removeAll(this.listReservation);
        assertEquals(cntr.getListReservation().size(), 18);
        
        this.listReservation.add(new Reservation(new PersonImpl("Fabrizio", "Caselli"),
                new CoursesImpl("Calcolo Combinatorio e Probabilità", Type.SECOND_YEAR_SCI), Days.WEDNESDAY,
                Hours.PERIOD4, new RoomImpl("Aula A")));
        cntr.removeAll(this.listReservation);
        assertEquals(cntr.getListReservation().size(), 17);
        
        this.listReservation.add(new Reservation(new PersonImpl("Stefano", "Rizzi"),
                new CoursesImpl("Ingegneria del Software", Type.THIRD_YEAR), Days.FRIDAY, Hours.PERIOD5,
                new RoomImpl("Aula D")));
        assertEquals(cntr.getListReservation().size(), 16);
        
        this.listReservation.add(new Reservation(new PersonImpl("Claudia", "Cevenini"),
                new CoursesImpl("Informatica e Diritto", Type.THIRD_YEAR_ENG), Days.MONDAY, Hours.PERIOD6,
                new RoomImpl("Aula E")));
        cntr.removeAll(this.listReservation);
        assertEquals(cntr.getListReservation().size(), 15);
        
        this.listReservation.add(new Reservation(new PersonImpl("Mario", "Bravetti"),
                new CoursesImpl("Linguaggi di Programmazione e Modelli Computazionali", Type.FOURTH_YEAR), Days.WEDNESDAY, Hours.PERIOD5,
                new RoomImpl("Laboratorio 2")));
        cntr.removeAll(this.listReservation);
        assertEquals(cntr.getListReservation().size(), 14);
        
    */
        
        
        
        
        
    }

}
