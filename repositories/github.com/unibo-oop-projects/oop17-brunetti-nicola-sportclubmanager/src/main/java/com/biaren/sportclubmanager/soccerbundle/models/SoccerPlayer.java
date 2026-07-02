package com.biaren.sportclubmanager.soccerbundle.models;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import com.biaren.sportclubmanager.corebundle.model.ClubMember;
import com.biaren.sportclubmanager.corebundle.model.interfaces.Player;
import com.biaren.sportclubmanager.soccerbundle.enums.SoccerRole;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

//PLAYER DOVEVA ESSERE UTILIZZATA COME INTERFACCIA COMUNE PER QUALSIASI TIPO DI GIOCATORE, DIFFERENZIATO POI
//DALLA SERIE DI RUOLI SPECIFICI PER LO SPORT. PER TERMINE MONTE ORE HO DOVUTO RINUNCIARE.

/**
 * Represent a Soccer Player. Extends {@link ClubMember} and implements {@link Player}
 * @author nbrunetti
 *
 */
@JsonDeserialize(builder = SoccerPlayer.Builder.class)
public class SoccerPlayer extends ClubMember implements Player<SoccerRole> {
    
    private final int soccerPlayerId; 
    private final double height;
    private final double weight;
    private final SoccerRole mainRole;
//    private final Set<SoccerRole> roles;
    
    /**
     * Builds a new {@link SoccerPlayer}
     * @author nbrunetti
     *
     */
    @JsonPOJOBuilder(buildMethodName = "build", withPrefix = "")
    public static class Builder extends ClubMember.Builder<Builder> {
        private static AtomicInteger soccerPlayerSequence = new AtomicInteger(0);
        private int soccerPlayerId; 
        private double height;
        private double weight;
        private SoccerRole mainRole;
//        private Set<SoccerRole> roles;
        
        public Builder() {
            this.soccerPlayerId = soccerPlayerSequence.incrementAndGet();   
        }
        
        public Builder soccerPlayerId(final int i) {
            this.soccerPlayerId = i;
            return this;
        }
        
        public Builder height(final double height) {
            this.height = height;
            return getThis();
        }
        
        public Builder weight(final double weight) {
            this.weight = weight;
            return getThis();
        }
        
        public Builder mainRole(final SoccerRole role) {
            this.mainRole = role;
            return getThis();
        }
        
//        public Builder roles(final Set<SoccerRole> roles) {
//            this.roles = roles;
//            return getThis();
//        }
      
        protected Builder getThis() {
            return this;
        }
        
        public SoccerPlayer build() {
            return new SoccerPlayer(this);
        }
    }
    
    protected SoccerPlayer(final Builder builder) {
        super(builder);
        this.soccerPlayerId = builder.soccerPlayerId;
        this.height = builder.height;
        this.weight = builder.weight;
        this.mainRole = builder.mainRole;
//        this.roles = builder.roles;
    }

    @Override
    public double getHeight() {
        return this.height;
    }

    @Override
    public double getWeight() {
        return this.weight;
    }

    @Override
    public SoccerRole getMainRole() {
        return this.mainRole;
    }

//    @Override
//    public List<SoccerRole> getPlayerRoles() {
//        return new ArrayList<>(this.roles);
//    }
    
    /**
     * Get players's id
     * @return players's id
     */
    public int getSoccerPlayerId() {
        return this.soccerPlayerId;
    }
    
    /**
     * Get full name of player: name surname
     * @return get full name
     */
    public String getNameSurname() {
        return this.name + " " + surname;
    }
    
    /**
     * Get a list with all "name surname" of players of list
     * @param list of {@link SoccerPlayer}
     * @return {@link List} of strings as "name surname" of all players in list
     */
    public static List<String> getSoccerPlayersNamesList(final List<SoccerPlayer> list) {
        return list.stream()
                .map(e -> e.getNameSurname())
                .collect(Collectors.toList());
    }
    
    /**
     * Get the correct player object from list that matches name and surname
     * @param list of {@link SoccerPlayer}
     * @param name to match
     * @param surname to match
     * @return {@link SoccerPlayer} or throw {@link IllegalArgumentException}
     */
    public static SoccerPlayer getPlayerFromName(final List<SoccerPlayer> list, final String name, final String surname) {
        return list.stream()
                .filter(e -> e.getName().equals(name) && e.getSurname().equals(surname))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Entità non trovata"));
    }
    
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "SoccerPlayer [name=" + name + ", surname=" + surname + ", birthDate=" + birthDate + ", birthPlace="
                + birthPlace + ", nationality=" + nationality + ", residenceDistrict=" + residenceDistrict
                + ", residenceAddress=" + residenceAddress + ", residenceCity=" + residenceCity + ", residenceNation="
                + residenceNation + ", fiscalCode=" + fiscalCode + ", soccerPlayerId=" + soccerPlayerId + ", height="
                + height + ", weight=" + weight + ", mainRole=" + mainRole + "]";
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return super.hashCode();
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
