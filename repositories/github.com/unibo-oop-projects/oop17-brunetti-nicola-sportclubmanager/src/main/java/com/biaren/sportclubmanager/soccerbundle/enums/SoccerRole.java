package com.biaren.sportclubmanager.soccerbundle.enums;

import java.util.List;
import java.util.Map;

import com.biaren.sportclubmanager.corebundle.model.enums.AdministrationMemberRole;

import static com.biaren.sportclubmanager.soccerbundle.enums.SoccerRole.SoccerRoleZone.*;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * Represent the player role.
 * @author nbrunetti
 *
 */
public enum SoccerRole {
    P(GOALKEEPER, "CC", "Portiere"),
    DC(DEFENDER, "DC", "Difensore Centrale"),
    SW(DEFENDER, "SW", "Libero"),
    DD(DEFENDER, "DD", "Terzino Destro"),
    DS(DEFENDER, "DS", "Terzino Sinistro"),
    M(MIDFIELDER, "M", "Mediano"),
    CC(MIDFIELDER, "CC", "Centrocampista Centrale"),
    CD(MIDFIELDER, "CD", "Centrocampista Destro"),
    CS(MIDFIELDER, "CS", "Centrocampista Sinistro"),
    CCD(MIDFIELDER, "CCD", "Centrocampista Centrale Destro"),
    CCS(MIDFIELDER, "CCSS", "Centrocampista Centrale Sinistro"),
    TD(FORWARD, "TD", "Ala Destra"),
    TS(FORWARD, "TS", "Ala Sinistra"),
    TC(FORWARD, "TC", "Trequartista"),
    PC(FORWARD, "PC", "Punta Centrale"),
    SPC(FORWARD, "SPC", "Seconda Punta Centrale");

    private static final Map<String, SoccerRole> map = new HashMap<>(values().length, 1);
    
    static {
        for (SoccerRole r : values()) {
            map.put(r.getRoleDescription(), r);
        }
    }
    
    private final SoccerRoleZone z;
    private final String roleDescription;
    private final String tag;
    
    SoccerRole(final SoccerRoleZone z, final String t, final String d) {
        this.z = z;
        this.roleDescription = d;
        this.tag = t;
    }
    
    /**
     * Get the role zone for specific role.
     * @return role zone
     */
    public SoccerRoleZone getRoleZone() {
        return this.z;
    }
    
    /**
     * Get the tag role, simplified version 
     * of the real specific role
     * @return string tag
     */
    public String getTag() {
        return this.tag;
    }
        
    public String getRoleDescription() {
        return this.roleDescription;
    }
    
    public static SoccerRole of(final String s) {
        SoccerRole result = map.get(s);
        if (result == null) {
            throw new IllegalArgumentException("Nome ruolo inesistente: " + s);
        }
        return result;
    }
    
    public enum SoccerRoleZone {
        GOALKEEPER("Portiere"),
        DEFENDER("Difensore"),
        MIDFIELDER("Centrocampista"),
        FORWARD("Attaccante");

        private String soccerRole;

        SoccerRoleZone(final String s) {
            this.soccerRole = s;
        }
        
        public String getSoccerRoleDescription() {
            return this.soccerRole;
        }

        public List<SoccerRole> getRoleByZone() {
            final List<SoccerRole> roleList = new ArrayList<>();
            for (final SoccerRole sr : SoccerRole.values()) {
                if (sr.getRoleZone() == this) {
                    roleList.add(sr);
                }
            }
            return roleList;
        }
    }
}
