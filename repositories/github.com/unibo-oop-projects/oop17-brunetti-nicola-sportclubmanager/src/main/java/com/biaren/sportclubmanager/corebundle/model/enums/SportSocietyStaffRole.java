package com.biaren.sportclubmanager.corebundle.model.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * Represent the exact role on staff.
 * 
 * @author nbrunetti
 *
 */
public enum SportSocietyStaffRole {
    COACH("Allenatore"),
    VICECOACH("Allenatore in Seconda"),
    FITNESS_COACH("Preparatore Atletico"),
    TECHNICAL_ASSISTANT("Collaboratore Tecnico"),
    HEAD_MEDICAL_STAFF("Responsabile Settore Medico"),
    SQUAD_DOCTOR("Medico Squadra"),
    HEAD_PHYSIOTHERAPIST("Responsabile Fisioterapisti"),
    SQUAD_PHISYOTHERAPIST("Fisioterapista Squadra"),
    ACCOMPANYING_MANAGER("Dirigente Accompagnatore");
	
    private static final Map<String, SportSocietyStaffRole> map = new HashMap<>(values().length, 1);
    
    static {
        for (SportSocietyStaffRole r : values()) {
            map.put(r.getRoleDescription(), r);
        }
    }
    
    private String roleDescription;

    SportSocietyStaffRole(final String s) {
        this.roleDescription = s;
    }
    
    /**
     * Get staff role on team description.
     * 
     * @return staff role
     */
    public String getRoleDescription() {
		return this.roleDescription;
    }
    
    /**
     * Used to get Enum from String.
     * @param s string field associate to Enum to search
     * @return corret Enum corresponding to string field
     */
    public static SportSocietyStaffRole of(final String s) {
        SportSocietyStaffRole result = map.get(s);
        if (result == null) {
            throw new IllegalArgumentException("Nome ruolo inesistente: " + s);
        }
        return result;
    }
	
}
