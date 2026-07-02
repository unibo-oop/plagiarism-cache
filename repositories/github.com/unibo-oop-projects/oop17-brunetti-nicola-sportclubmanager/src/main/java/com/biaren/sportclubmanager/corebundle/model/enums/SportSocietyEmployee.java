package com.biaren.sportclubmanager.corebundle.model.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * Enum that represent Employee Roles.
 * 
 * @author Brunetti Nicola
 *
 */
public enum SportSocietyEmployee {
    WAREHOUSEMAN("Magazziniere"),
    GARDENENER("Giardiniere"),
    GUARDIAN("Custode"),
    SECRETARY("Segretario");
    
    private static final Map<String, SportSocietyEmployee> map = new HashMap<>(values().length, 1);
    
    static {
        for (SportSocietyEmployee r : values()) {
            map.put(r.getRoleDescription(), r);
        }
    }
    
    private String roleDescription;

    SportSocietyEmployee(final String s) {
        this.roleDescription = s;
    }
    
    /**
     * Get employee role description.
     * 
     * @return role description
     */
    public String getRoleDescription() {
        return this.roleDescription;
    }
    
    /**
     * Used to get Enum from String.
     * @param s string field associate to Enum to search
     * @return corret Enum corresponding to string field
     */
    public static SportSocietyEmployee of(final String s) {
        SportSocietyEmployee result = map.get(s);
        if (result == null) {
            throw new IllegalArgumentException("Nome ruolo inesistente: " + s);
        }
        return result;
    }
}
