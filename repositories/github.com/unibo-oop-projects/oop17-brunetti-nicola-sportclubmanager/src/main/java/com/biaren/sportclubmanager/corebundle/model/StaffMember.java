package com.biaren.sportclubmanager.corebundle.model;

import java.util.concurrent.atomic.AtomicInteger;
import com.biaren.sportclubmanager.corebundle.model.enums.SportSocietyStaffRole;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

/**
 * Represent a Staff Member.
 * @author nbrunetti
 *
 */
@JsonDeserialize(builder = StaffMember.Builder.class)
public class StaffMember extends ClubMember {

    private final int staffmemberId;
    private final SportSocietyStaffRole staffMemberRole;
    
    /**
     * Builds a {@link StaffMember}.
     * @author nbrunetti
     *
     */
    @JsonPOJOBuilder(buildMethodName = "build", withPrefix = "")
    public static class Builder extends ClubMember.Builder<Builder> {
        private static AtomicInteger staffSequence = new AtomicInteger(0);
        private int staffMemberId;
        private SportSocietyStaffRole staffMemberRole;
        
        public Builder() {
            this.staffMemberId = staffSequence.incrementAndGet();
        }
        
        public Builder staffMemberId(final int i) {
            this.staffMemberId = i;
            return this;
        }
        
        public Builder staffMemberRole(final SportSocietyStaffRole role) {
            this.staffMemberRole = role;
            return this;
        }
        
        protected Builder getThis() {
            return this;
        }
        
        @Override 
        public StaffMember build() {
            return new StaffMember(this);
        }
    }
    
    protected StaffMember(Builder builder) {
        super(builder);
        this.staffmemberId = builder.staffMemberId;
        this.staffMemberRole = builder.staffMemberRole;
    }
    
    /**
     * Get staff member role.
     * @return role of staff member.
     */
    public SportSocietyStaffRole getStaffMemberRole() {
        return this.staffMemberRole;
    }
    
    /**
     * Get staff member id
     * @return id of staff member
     */
    public int getStaffMemberId() {
        return this.staffmemberId;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "StaffMember [name=" + name + ", surname=" + surname + ", birthDate=" + birthDate + ", birthPlace="
                + birthPlace + ", nationality=" + nationality + ", residenceDistrict=" + residenceDistrict
                + ", residenceAddress=" + residenceAddress + ", residenceCity=" + residenceCity + ", residenceNation="
                + residenceNation + ", fiscalCode=" + fiscalCode + ", staffmemberId=" + staffmemberId
                + ", staffMemberRole=" + staffMemberRole + "]";
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((staffMemberRole == null) ? 0 : staffMemberRole.hashCode());
        result = prime * result + staffmemberId;
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
        StaffMember other = (StaffMember) obj;
        if (staffMemberRole != other.staffMemberRole)
            return false;
        if (staffmemberId != other.staffmemberId)
            return false;
        return true;
    }
}