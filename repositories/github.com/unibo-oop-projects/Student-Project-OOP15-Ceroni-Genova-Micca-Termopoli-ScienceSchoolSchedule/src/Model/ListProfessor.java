package Model;

import java.util.ArrayList;
import java.util.List;

/**
 * This class implements a list of all the professors of the faculty,
 * formed by the name, surname and from the courses taught by them
 * 
 * @author Francesco Ceroni
 * 
 */

public enum ListProfessor {
    
    ALBANO("Albano", "Paolo", ListCourses.ANALISI_MATEMATICA),
    BEVILACQUA("Bevilacqua", "Alessandro", ListCourses.TECNICHE_AVANZATE_PER_L_ANALISI_DELLE_IMMAGINI_E_VISIONE),
    BOSCHETTI("Boschetti", "Marco Antonio", ListCourses.PROJECT_MANAGEMENT),
    BRAVETTI("Bravetti", "Mario", ListCourses.LINGUAGGI_DI_PROGRAMMAZIONE_E_MODELLI_COMPUTAZIONALI),
    CALLEGATI("Callegati", "Franco", ListCourses.RETI_DI_TELECOMUNICAZIONE, ListCourses.INSTRADAMENTO_E_TRASPORTO_IN_INTERNET),
    CAMPARI("Campari", "Enrico Gianfranco", ListCourses.FISICA),
    CAMPI("Campi", "Aldo", ListCourses.PROGRAMMAZIONE_DI_RETI),
    CANTARINI("Cantarini", "Nicoletta", ListCourses.ALGEBRA_E_GEOMETRIA),
    CAPPELLI("Cappelli", "Raffaele", ListCourses.ARCHITETTURA_DEGLI_ELABORATORI, ListCourses.FONDAMENTI_DI_ELABORAZIONE_DI_IMMAGINI),
    CARBONARO("Carbonaro", "Antonella", ListCourses.PROGRAMMAZIONE, ListCourses.WEB_SEMANTICO),
    CASADEI("Casadei", "Matteo", ListCourses.PROGRAMMAZIONE_AD_OGGETTI),
    CASELLI("Caselli", "Fabrizio", ListCourses.CALCOLO_COMBINATORIO_E_PROBABILITA),
    CEVENINI("Cevenini", "Claudia", ListCourses.INFORMATICA_E_DIRITTO),
    CODELUPPI("Codeluppi", "Rossano", ListCourses.ELETTRONICA_DEI_SISTEMI_DIGITALI),
    D_ANGELO("D'Angelo", "Gabriele", ListCourses.PROGRAMMAZIONE_DI_RETI, ListCourses.SICUREZZA_DELLE_RETI),
    DI_LENA("Di Lena", "Pietro", ListCourses.PROGRAMMAZIONE, ListCourses.ALGORITMI_E_STRUTTURE_DATI),
    DOMENICONI("Domeniconi", "Giacomo", ListCourses.PROGRAMMAZIONE_DI_APPLICAZIONI_DATA_INTENSIVE),
    FERRETTI("Ferretti", "Stefano", ListCourses.SISTEMI_MULTIMEDIALI),
    FOCACCI("Focacci", "Antonio", ListCourses.ECONOMIA_E_ORGANIZZAZIONE_AZIENDALE),
    FRANCO("Franco", "Annalisa", ListCourses.BASI_DI_DATI, ListCourses.SMART_CITY_E_TECNOLOGIE_MOBILI, ListCourses.VISIONE_ARTIFICIALE_E_RICONOSCIMENTO),
    GHINI("Ghini", "Vittorio", ListCourses.SISTEMI_OPERATIVI),
    GOLFARELLI("Golfarelli", "Matteo", ListCourses.LABORATORIO_DI_BASI_DI_DATI, ListCourses.SISTEMI_INFORMATIVI, ListCourses.DATA_MINING),
    LAZZARO("Lazzaro", "Damiana", ListCourses.ALGORITMI_NUMERICI, ListCourses.COMPUTER_GRAPHICS),
    LEVI("Levi", "Giuseppe", ListCourses.HIGH_PERFORMANCE_COMPUTING, ListCourses.LINGUAGGI_VISUALI_PER_IL_CONTROLLO_DEI_SISTEMI),
    LUMINI("Lumini", "Alessandra", ListCourses.LABORATORIO_DI_BASI_DI_DATI, ListCourses.BUSINESS_INTELLIGENCE),
    MAIO("Maio", "Dario", ListCourses.BASI_DI_DATI, ListCourses.SMART_CITY_E_TECNOLOGIE_MOBILI),
    MALTONI("Maltoni", "Davide", ListCourses.ARCHITETTURA_DEGLI_ELABORATORI, ListCourses.VISIONE_ARTIFICIALE_E_RICONOSCIMENTO),
    MANIEZZO("Maniezzo", "Vittorio", ListCourses.ALGORITMI_E_STRUTTURE_DATI, ListCourses.INTELLIGENZA_ARTIFICIALE, ListCourses.SISTEMI_DI_SUPPORTO_ALLE_DECISIONI),
    MARGARA("Margara", "Luciano", ListCourses.ALGORITMI_E_STRUTTURE_DATI),
    MIMMO("Mimmo", "Nicola", ListCourses.ELETTRONICA_DEI_SISTEMI_DIGITALI),
    MINGOZZI("Mingozzi", "Aristide", ListCourses.RICERCA_OPERATIVA, ListCourses.METODI_ED_ALGORITMI_DI_OTTIMIZZAZIONE_PER_IL_PROBLEM_SOLVING),
    MIRRI("Mirri", "Silvia", ListCourses.APPLICAZIONI_E_SERVIZI_WEB),
    MORIGI("Morigi", "Serena", ListCourses.ALGORITMI_NUMERICI),
    MORO("Moro", "Gianluca", ListCourses.PROGRAMMAZIONE_DI_APPLICAZIONI_DATA_INTENSIVE, ListCourses.DATA_MINING),
    NATALI("Natali", "Antonio", ListCourses.INGEGNERIA_DEI_SISTEMI_SOFTWARE, ListCourses.ATTIVITA_PROPEDEUTICA_ALLA_PROVA_FINALE),
    OMICINI("Omicini", "Andrea", ListCourses.SISTEMI_DISTRIBUITI, ListCourses.SISTEMI_AUTONOMI),
    PALLI("Palli", "Gianluca", ListCourses.CONTROLLI_AUTOMATICI),
    PASQUINI("Pasquini", "Luca", ListCourses.FISICA),
    PIANINI("Pianini", "Danilo", ListCourses.INGEGNERIA_DEI_SISTEMI_SOFTWARE_ADATTATIVI_COMPLESSI),
    PRANDI("Prandi", "Catia", ListCourses.APPLICAZIONI_E_SERVIZI_WEB),
    RAVAIOLI("Ravaioli", "Mirko", ListCourses.PROGRAMMAZIONE_DI_SISTEMI_MOBILE),
    RICCI("Ricci", "Alessandro", ListCourses.PROGRAMMAZIONE_DI_SISTEMI_EMBEDDED, ListCourses.PROGRAMMAZIONE_AVANZATA_E_PARADIGMI, ListCourses.INGEGNERIA_DEI_SISTEMI_SOFTWARE_ADATTATIVI_COMPLESSI),
    RIZZI("Rizzi", "Stefano", ListCourses.INGEGNERIA_DEL_SOFTWARE, ListCourses.BUSINESS_INTELLIGENCE),
    ROLI("Roli", "Andrea", ListCourses.SISTEMI_INTELLIGENTI_ROBOTICI),
    SALOMONI("Salomoni", "Paola", ListCourses.TECNOLOGIE_WEB, ListCourses.SISTEMI_MULTIMEDIALI),
    STRAPPAVECCIA("Strappaveccia", "Francesco", ListCourses.PROGRAMMAZIONE),
    VIROLI("Viroli", "Mirko", ListCourses.PROGRAMMAZIONE_AD_OGGETTI,  ListCourses.INGEGNERIA_DEI_SISTEMI_SOFTWARE_ADATTATIVI_COMPLESSI),
    C_L_A("Cla", "Cla", ListCourses.INGLESE_B1, ListCourses.INGLESE_B2);  
    
     private final String name;
     private final String surname;
     private ListCourses courses = null;
     private ListCourses courses1 = null;
     private ListCourses courses2 = null;
     
     private ListProfessor(String surname, String name, ListCourses courses){   //Professor teaching one course 
         this.surname = surname;
         this.name = name;
         this.courses = courses;
     }
     
     private ListProfessor(String surname, String name, ListCourses courses, ListCourses courses1){     //Professor teaching more courses 
         this.surname = surname;
         this.name = name;
         this.courses = courses;
         this.courses1 = courses1; 
     }
     
     private ListProfessor(String surname, String name, ListCourses courses, ListCourses courses1, ListCourses courses2){       //Professor teaching more courses 
         this.surname = surname;
         this.name = name;
         this.courses = courses;
         this.courses1 = courses1; 
         this.courses2 = courses2; 
     }
     
     public String getName(){
         return this.name;
     }
     
     public String getSurname(){
         return this.surname;
     }
     
     public String toStringCourses() {  
         return this.courses1==null
                 ? ""+this.courses.getValue() 
                 : this.courses2==null 
                 ? this.courses.getValue()+" "+this.courses1.getValue()
                 :this.courses.getValue()+" "+this.courses1.getValue()+" "+this.courses2.getValue();
     }
     
     public List<ListCourses> getCourses() {
         List<ListCourses> set = new ArrayList<>();
         
         set.add(this.courses);
         if(this.courses1!=null)
             set.add(this.courses1);
         if(this.courses2!=null)
             set.add(this.courses2);
         return set;
     }
     
     public String toString(){
         return this.surname+" "+this.name;
     }
}
