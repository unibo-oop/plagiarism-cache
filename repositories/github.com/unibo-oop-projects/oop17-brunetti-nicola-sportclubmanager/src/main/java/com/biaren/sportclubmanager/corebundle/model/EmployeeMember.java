package com.biaren.sportclubmanager.corebundle.model;

import java.util.concurrent.atomic.AtomicInteger;
import com.biaren.sportclubmanager.corebundle.model.enums.SportSocietyEmployee;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

/**
 * Represent an employee.
 * @author nbrunetti
 *
 */
@JsonDeserialize(builder = EmployeeMember.Builder.class)
public class EmployeeMember extends ClubMember {
    
    private final int employeeMemberId;
    private final SportSocietyEmployee employeeMemberRole;
    
    /**
     * Builds a new {@link EmployeeMember}.
     * @author nbrunetti
     *
     */
    @JsonPOJOBuilder(buildMethodName = "build", withPrefix = "")
    public static class Builder extends ClubMember.Builder<Builder> {
        private int employeeMemberId;
        private static AtomicInteger employeeSequence = new AtomicInteger(0);
        private SportSocietyEmployee employeeMemberRole;
        
        public Builder() {
            this.employeeMemberId = employeeSequence.incrementAndGet();
        }
        
        public Builder employeeMemberId(final int i) {
            this.employeeMemberId = i;
            return this;
        }
        
        public Builder employeeMemberRole(final SportSocietyEmployee role) {
            this.employeeMemberRole = role;
            return this;
        }
        
        protected Builder getThis() {
            return this;
        }
        
        @Override
        public EmployeeMember build() {
            return new EmployeeMember(this);
        }
    }
    
    protected EmployeeMember(Builder builder) {
        super(builder);
        this.employeeMemberId = builder.employeeMemberId;
        this.employeeMemberRole = builder.employeeMemberRole;
    }
    
    public SportSocietyEmployee getEmployeeMemberRole() {
        return this.employeeMemberRole;
    }
    
    public int getEmployeeMemberId() {
        return this.employeeMemberId;
    }
    
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "EmployeeMember [name=" + name + ", surname=" + surname + ", birthDate=" + birthDate + ", birthPlace="
                + birthPlace + ", nationality=" + nationality + ", residenceDistrict=" + residenceDistrict
                + ", residenceAddress=" + residenceAddress + ", residenceCity=" + residenceCity + ", residenceNation="
                + residenceNation + ", fiscalCode=" + fiscalCode + ", employeeMemberId=" + employeeMemberId
                + ", employeeMemberRole=" + employeeMemberRole + "]";
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + employeeMemberId;
        result = prime * result + ((employeeMemberRole == null) ? 0 : employeeMemberRole.hashCode());
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
        EmployeeMember other = (EmployeeMember) obj;
        if (employeeMemberId != other.employeeMemberId)
            return false;
        if (employeeMemberRole != other.employeeMemberRole)
            return false;
        return true;
    }
    
}
