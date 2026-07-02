package com.biaren.sportclubmanager.utility.enums;

/**
 * Json file name
 * @author nbrunetti
 *
 */
public enum JsonFileName {
    USERS("users.json"),
    PLAYERS("players.json"),
    STAFF("staff.json"),
    ADMINISTRATION("administration.json"),
    EMPLOYEES("employees.json"),
    FACILITIES("facilities.json"),
    SPONSOR("sponsor.json"),
    MATCHES("matches.json");
    
    private final String name;
    
    private JsonFileName(final String s) {
        this.name = s;
    }
    
    /**
     * Get name with \\ ready for path
     * @return part of path
     */
    public String getNameForPath() {
        return "\\" + this.name;
    }
    
    /**
     * Get file name
     * @return file name
     */
    public String getFileName() {
        return this.name();
    }
    
}
