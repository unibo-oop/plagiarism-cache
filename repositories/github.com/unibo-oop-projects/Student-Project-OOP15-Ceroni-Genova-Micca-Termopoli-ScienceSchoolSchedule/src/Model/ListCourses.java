package Model;

/**
 * This class implements a list of all the courses of the faculty, 
 * formed by the type of the course and the name
 * 
 * @author Francesco Ceroni
 * 
 */

public enum ListCourses {
    
    //First Year 
    ANALISI_MATEMATICA(Type.FIRST_YEAR, "Analisi Matematica"), 
    PROGRAMMAZIONE(Type.FIRST_YEAR, "Programmazione"),
    INGLESE_B1(Type.FIRST_YEAR, "Inglese B1"),
    ALGEBRA_E_GEOMETRIA(Type.FIRST_YEAR, "Algebra e Geometria"),
    ALGORITMI_E_STRUTTURE_DATI(Type.FIRST_YEAR, "Algoritmi e Strutture Dati"),
    ARCHITETTURA_DEGLI_ELABORATORI(Type.FIRST_YEAR, "Architettura degli  Elaboratori"),
   
    //Second Year 
    PROGRAMMAZIONE_AD_OGGETTI(Type.SECOND_YEAR, "Programmazione ad Oggetti"),
    SISTEMI_OPERATIVI(Type.SECOND_YEAR, "Sistemi Operativi"),
    BASI_DI_DATI(Type.SECOND_YEAR, "Basi di Dati"),
    FISICA(Type.SECOND_YEAR, "Fisica"),
    RETI_DI_TELECOMUNICAZIONE(Type.SECOND_YEAR, "Reti di Telecomunicazione"),
    
    //Second Year ENG
    CONTROLLI_AUTOMATICI(Type.SECOND_YEAR_ENG, "Controlli Automatici"),
    ELETTRONICA_DEI_SISTEMI_DIGITALI(Type.SECOND_YEAR_ENG, "Elettronica dei Sistemi Digitali"),
    
    //Second Year SCI
    CALCOLO_COMBINATORIO_E_PROBABILITA(Type.SECOND_YEAR_SCI, "Calcolo Combinatorio e Probabilità"),
    ALGORITMI_NUMERICI(Type.SECOND_YEAR_SCI, "Algoritmi Numerici"),
    
    //Third Year 
    INGEGNERIA_DEL_SOFTWARE(Type.THIRD_YEAR, "Ingegneria del Software"),
    PROGRAMMAZIONE_DI_RETI(Type.THIRD_YEAR, "Programmazione di Reti"),
    TECNOLOGIE_WEB(Type.THIRD_YEAR, "Tecnologie Web"),
    RICERCA_OPERATIVA(Type.THIRD_YEAR, "Ricerca Operativa"),
    
    //Third Year ENG
    INFORMATICA_E_DIRITTO(Type.THIRD_YEAR_ENG, "Informatica e Diritto"),
    ECONOMIA_E_ORGANIZZAZIONE_AZIENDALE(Type.THIRD_YEAR_ENG, "Economia e Organizzazione Aziendale"),
    
    //Third Year SCI
    LABORATORIO_DI_BASI_DI_DATI(Type.THIRD_YEAR_SCI, "Laboratorio di Basi di Dati"),
    
    //Third Year OPT
    COMPUTER_GRAPHICS(Type.THIRD_YEAR_OPT, "Computer Graphics"),
    PROGRAMMAZIONE_DI_SISTEMI_EMBEDDED(Type.THIRD_YEAR_OPT, "Programmazione di Sistemi Embedded"),
    FONDAMENTI_DI_ELABORAZIONE_DI_IMMAGINI(Type.THIRD_YEAR_OPT, "Fondamenti di Elaborazione di Immagini"),
    HIGH_PERFORMANCE_COMPUTING(Type.THIRD_YEAR_OPT, "High Performance Computing"),
    LINGUAGGI_VISUALI_PER_IL_CONTROLLO_DEI_SISTEMI(Type.THIRD_YEAR_OPT, "Linguaggi Visuali per il Controllo dei Sistemi"),
    PROGRAMMAZIONE_DI_APPLICAZIONI_DATA_INTENSIVE(Type.THIRD_YEAR_OPT, "Programmazione di Applicazioni Data Intensive"),
    PROGRAMMAZIONE_DI_SISTEMI_MOBILE(Type.THIRD_YEAR_OPT, "Programmazione di Sistemi Mobile"), 
    SISTEMI_MULTIMEDIALI(Type.THIRD_YEAR_OPT, "Sistemi Multimediali"),
    
    //Fourth Year
    INGLESE_B2(Type.FIRST_YEAR, "Inglese B2"), 
    LINGUAGGI_DI_PROGRAMMAZIONE_E_MODELLI_COMPUTAZIONALI(Type.FOURTH_YEAR, "Linguaggi di Programmazione e Modelli Computazionali"),
    SISTEMI_INFORMATIVI(Type.FOURTH_YEAR, "Sistemi Informativi"),
    BUSINESS_INTELLIGENCE(Type.FOURTH_YEAR, "Business Intelligence"),
    INTELLIGENZA_ARTIFICIALE(Type.FOURTH_YEAR, "Intelligenza Artificiale"),
    PROGRAMMAZIONE_AVANZATA_E_PARADIGMI(Type.FOURTH_YEAR, "Programmazione Avanzata e Paradigmi"),
    SICUREZZA_DELLE_RETI(Type.FOURTH_YEAR, "Sicurezza delle Reti"),
    SISTEMI_DISTRIBUITI(Type.FOURTH_YEAR, "Sistemi Distribuiti"),
    
    //Fifth Year
    INGEGNERIA_DEI_SISTEMI_SOFTWARE(Type.FIFTH_YEAR, "Ingegneria dei Sistemi Software"),
    APPLICAZIONI_E_SERVIZI_WEB(Type.FIFTH_YEAR, "Applicazioni e Servizi Web"),
    ATTIVITA_PROPEDEUTICA_ALLA_PROVA_FINALE(Type.FIFTH_YEAR, "Attività Propedeutica alla Prova Finale"),
    
    //Fifth Year Opt
    INSTRADAMENTO_E_TRASPORTO_IN_INTERNET(Type.FIFTH_YEAR_OPT,  "Instradamento e Trasporto in Internet"),
    METODI_ED_ALGORITMI_DI_OTTIMIZZAZIONE_PER_IL_PROBLEM_SOLVING(Type.FIFTH_YEAR_OPT, "Metodi ed Algoritmi di Ottimizzazione per il Problem Solving"),
    DATA_MINING(Type.FIFTH_YEAR_OPT, "Data Mining"),
    SISTEMI_AUTONOMI(Type.FIFTH_YEAR_OPT, "Sistemi Autonomi"),
    SISTEMI_DI_SUPPORTO_ALLE_DECISIONI(Type.FIFTH_YEAR_OPT, "Sistemi di Supporto alle Decisioni"),
    SMART_CITY_E_TECNOLOGIE_MOBILI(Type.FIFTH_YEAR_OPT, "Smart City e Tecnologie Mobili"),
    VISIONE_ARTIFICIALE_E_RICONOSCIMENTO(Type.FIFTH_YEAR_OPT, "Visione Artificiale e Riconoscimento"),
    INGEGNERIA_DEI_SISTEMI_SOFTWARE_ADATTATIVI_COMPLESSI(Type.FIFTH_YEAR_OPT, "Ingegneria dei Sistemi Software Adattativi Complessi"),
    PROJECT_MANAGEMENT(Type.FIFTH_YEAR_OPT, "Project Management"),
    SISTEMI_INTELLIGENTI_ROBOTICI(Type.FIFTH_YEAR_OPT, "Sistemi Intelligenti Robotici"),
    TECNICHE_AVANZATE_PER_L_ANALISI_DELLE_IMMAGINI_E_VISIONE(Type.FIFTH_YEAR_OPT, "Tecniche Avanzate per l'Analisi delle Immagini e Visione"),
    WEB_SEMANTICO(Type.FIFTH_YEAR_OPT, "Web Semantico");

    private final Type type;
    private final String value;

    private ListCourses(Type type, String value) {
        this.type = type;
        this.value = value;
    }

    public Type getType() {
        return this.type;
    }

    public String getValue(){
        return this.value;
    }
}
