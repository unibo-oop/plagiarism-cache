package com.biaren.sportclubmanager.corebundle.model.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * Represent the role on the entire society.
 * 
 * @author nbrunetti
 *
 */
public enum SocietyRole {
    ADMINISTRATION("Direttivo"),
    STAFF("Staff"),
    TEAM("Squadra"),
    EMPLOYEE("Addetto Lavoro");
    
    private static final Map<String, SocietyRole> map = new HashMap<>(values().length, 1);
    
    static {
        for (SocietyRole r : values()) {
            map.put(r.getRoleDescription(), r);
        }
    }
    
    private String roleDescription;
    
    SocietyRole(final String s) {
        this.roleDescription = s;
    }
    
    /**
     * Get role on society description.
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
    public static SocietyRole of(final String s) {
        SocietyRole result = map.get(s);
        if (result == null) {
            throw new IllegalArgumentException("Nome ruolo inesistente: " + s);
        }
        return result;
    }

}
