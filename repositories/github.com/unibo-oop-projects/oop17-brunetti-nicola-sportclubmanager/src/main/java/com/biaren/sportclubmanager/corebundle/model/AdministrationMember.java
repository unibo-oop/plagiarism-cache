package com.biaren.sportclubmanager.corebundle.model;

import java.util.concurrent.atomic.AtomicInteger;
import com.biaren.sportclubmanager.corebundle.model.enums.AdministrationMemberRole;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

/**
 * Represent a member of the Administration of the Society.
 * @author nbrunetti
 *
 */
@JsonDeserialize(builder = AdministrationMember.Builder.class)
public class AdministrationMember extends ClubMember {

    private final int administrationMemberId;
    private final AdministrationMemberRole administrationMemberRole;
    
    /**
     * Builds a new {@link AdministrationMember}.
     * @author nbrunetti
     *
     */
    @JsonPOJOBuilder(buildMethodName = "build", withPrefix = "")
    public static class Builder extends ClubMember.Builder<Builder> {
        private static AtomicInteger administrationSequence = new AtomicInteger(0);
        private int administrationMemberId;
        private AdministrationMemberRole administrationMemberRole;
        
        public Builder() {
            this.administrationMemberId = administrationSequence.incrementAndGet();
        }
        
        public Builder administrationMemberId(final int i) {
            this.administrationMemberId = i;
            return this;
        }
        
        public Builder administrationMemberRole(final AdministrationMemberRole role) {
            this.administrationMemberRole = role;
            return this;
        }
       
        protected Builder getThis() {
            return this;
        }
        
        @Override
        public AdministrationMember build() {
            return new AdministrationMember(this);
        }
    }
    
    protected AdministrationMember(Builder builder) {
        super(builder);
        this.administrationMemberId = builder.administrationMemberId;
        this.administrationMemberRole = builder.administrationMemberRole;
    }

    /**
     * Get administration's member role.
     * @return {@link AdministrationMemberRole} administration's member role
     */
    public AdministrationMemberRole getAdministrationMemberRole() {
        return this.administrationMemberRole;
    }
    
    /**
     * Get administration's member id
     * @return administration's member id
     */
    public int getAdministrationMemberId() {
        return this.administrationMemberId;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "AdministrationMember [name=" + name + ", surname=" + surname + ", birthDate=" + birthDate
                + ", birthPlace=" + birthPlace + ", nationality=" + nationality + ", residenceDistrict="
                + residenceDistrict + ", residenceAddress=" + residenceAddress + ", residenceCity=" + residenceCity
                + ", residenceNation=" + residenceNation + ", fiscalCode=" + fiscalCode + ", administrationMemberId="
                + administrationMemberId + ", administrationMemberRole=" + administrationMemberRole + "]";
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + administrationMemberId;
        result = prime * result + ((administrationMemberRole == null) ? 0 : administrationMemberRole.hashCode());
        return result;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        AdministrationMember other = (AdministrationMember) obj;
        if (administrationMemberId != other.administrationMemberId)
            return false;
        if (administrationMemberRole != other.administrationMemberRole)
            return false;
        return true;
    }
}