package com.biaren.sportclubmanager.utility.enums;
/**
 * Some text string to use in GUI.
 * @author nbrunetti
 *
 */
public enum TextStrings {
    SPORTCLUBMANAGER_MAIN_TITLE("Sport Club Manager"),
    WELCOME_LABEL("Benvenuto"),
    WELCOMEBACK_LABEL("Bentornato"),
    BEFORE_STARTING_LABEL("Prima di iniziare..."), 
    SUBMIT_LABEL("Invia"),
    REGISTER_LABEL("Registra"),
    REGISTER_NEW_USER("Registra nuovo utente"),
    REGISTER_NEW_ADMIN("Registra nuovo amministratore"),
    M_NEW_LABEL("Nuovo"),
    F_NEW_LABEL("Nuova"),
    ADMINISTRATOR_LABEL("Amministratore"),
    USER_LABEL("Utente"),
    USERNAME_LABEL("Username"),
    PASSWORD_LABEL("Password"),
    PASSWORD_CONFIRM("Conferma la tua password"),
    EMAIL_LABEL("Email"),
    LOGIN_LABEL("Login"),
    LOGOUT_LABEL("Logout"),
    DO_LOGIN_LABEL("Effettua il login"),
    HOME_LABEL("Home"),
    SOCIETY_LABEL("Società"),
    MEMBER_LABEL("Membro"),
    MEMBERS_LABEL("Membri"),
    PLAYER_LABEL("Giocatore"),
    PLAYERS_LABEL("Giocatori"),
    STAFF_LABEL("Staff"),
    FACILITY_LABEL("Struttura"),
    FACILITIES_LABEL("Strutture"),
    EMPLOYEE_LABEL("Dipendente"),
    EMPLOYEES_LABEL("Dipendenti"),
    FAILED_LOGIN("Login fallito. Username o password sbagliato"),
    BUSINESS_NAME("Ragione Sociale"),
    TEAM_NAME("Nome Squadra"),
    FIRST_OPENING_FILENAME("firstOpening.txt"),
    SOCIETY_LOGO_FILE_NAME("society_logo.png"),
    DO_SETUP_FILENAME("setup.txt");
    
    
    private String string;
    
    private TextStrings (String name) {
        this.string = name;
    }

    /**
     * Field string text
     */
    public String toString() {
        return this.string;
    }
    
    /**
     * Field string text dotted
     * @return string text as "text."
     */
    public String getDotted() {
        return this.toString() + ".";
    }
}
