package com.biaren.sportclubmanager.corebundle.model.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * Enum that represent the Society Member Role Name.
 * 
 * @author nbrunetti
 *
 */
public enum AdministrationMemberRole {
    PRESIDENT("Presidente"),
    VICEPRESIDENT("Vice Presidente"),
    AD("Amministratore Delegato"),
    DG("Direttore Generale"),
    DS("Direttore Sportivo"),
    CA("Consiglio di Amministrazione");
    
    private static final Map<String, AdministrationMemberRole> map = new HashMap<>(values().length, 1);
    
    static {
        for (AdministrationMemberRole r : values()) {
            map.put(r.getRoleDescription(), r);
        }
    }
    
    private String roleDescription;
    
    AdministrationMemberRole(final String s) {
        this.roleDescription = s;
    }
    /**
     * Get the role description.
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
    public static AdministrationMemberRole of(final String s) {
        AdministrationMemberRole result = map.get(s);
        if (result == null) {
            throw new IllegalArgumentException("Nome ruolo inesistente: " + s);
        }
        return result;
    }
}
