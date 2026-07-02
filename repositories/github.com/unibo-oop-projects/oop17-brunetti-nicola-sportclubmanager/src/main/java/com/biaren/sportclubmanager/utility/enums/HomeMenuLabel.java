package com.biaren.sportclubmanager.utility.enums;

/**
 * Label for menu items
 * @author nbrunetti
 *
 */
public enum HomeMenuLabel {
    HOME_LABEL("Home"),
    PLAYERS_LABEL("Giocatori"),
    STAFF_LABEL("Staff"),
    SOCIETY_LABEL("Società"),
    MATCHES("Partite"),
    STATISTICS("Statistiche"),
    EMPLOYEES_LABEL("Dipendenti"),
    FACILITIES_LABEL("Strutture"),
    SPONSOR_LABEL("Sponsor"),
    CREATE_MATCH_PLAYERS_LIST("Distinta Partita");
    
    private String string;
    
    private HomeMenuLabel(final String name) {
        this.string = name;
    }
    
    /**
     * Get label string
     */
    public String toString() {
        return this.string;
    }
}
