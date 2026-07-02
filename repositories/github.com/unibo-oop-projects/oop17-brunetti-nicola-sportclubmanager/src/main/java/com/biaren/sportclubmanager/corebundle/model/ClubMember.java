package com.biaren.sportclubmanager.corebundle.model;

import java.time.LocalDate;
import com.biaren.sportclubmanager.corebundle.model.interfaces.Person;

/**
 * Represent a ClubMember. Implements {@link Person}.
 * Abstract class to extends and makes specific.
 * @author nbrunetti
 *
 */
public abstract class ClubMember implements Person {
    
    protected final String name;
    protected final String surname;
    protected final LocalDate birthDate;
    protected final String birthPlace;
    protected final String nationality;
    protected final String residenceDistrict;
    protected final String residenceAddress;
    protected final String residenceCity;
    protected final String residenceNation;
    protected final String fiscalCode;
    
    /**
     * Curiosly recurring generic pattern: made to extends and create a specific
     * ClubMember extension.
     * @author nbrunetti
     *
     * @param <B> type of Builder extension
     */
    public static abstract class Builder<B extends Builder<? extends B>> {
        protected String name;
        protected String surname;
        protected LocalDate birthDate;
        protected String birthPlace;
        protected String nationality;
        protected String residenceDistrict;
        protected String residenceAddress;
        protected String residenceCity;
        protected String residenceNation;
        protected String fiscalCode;
        
        public Builder() {
            
        }
        
        public B name(final String name) {
            this.name = name;
            return getThis();
        }
        
        public B surname(final String surname) {
            this.surname = surname;
            return getThis();
        }
        
        public B birthDate(final LocalDate birthDate) {
            this.birthDate = birthDate;
            return getThis();
        }
        
        public B birthPlace(final String birthPlace) {
            this.birthPlace = birthPlace;
            return getThis();
        }
        
        public B nationality(final String nationality) {
            this.nationality = nationality;
            return getThis();
        }
        
        public B residenceDistrict(final String residenceDistrict) {
            this.residenceDistrict = residenceDistrict;
            return getThis();
        }
        
        public B residenceAddress(final String residenceAddress) {
            this.residenceAddress = residenceAddress;
            return getThis();
        }
        
        public B residenceCity(final String residenceCity) {
            this.residenceCity = residenceCity;
            return getThis();
        }
        
        public B residenceNation(final String residenceNation) {
            this.residenceNation = residenceNation;
            return getThis();
        }
        
        public B fiscalCode(final String fiscalCode) {
            this.fiscalCode = fiscalCode;
            return getThis();
        }
        
        public abstract ClubMember build();
        
        protected abstract B getThis();
    }
    
    protected ClubMember(Builder<?> builder) {
        this.name = builder.name;
        this.surname = builder.surname;
        this.birthDate = builder.birthDate;
        this.birthPlace = builder.birthPlace;
        this.residenceAddress = builder.residenceAddress;
        this.residenceDistrict = builder.residenceDistrict;
        this.residenceNation = builder.residenceNation;
        this.residenceCity = builder.residenceCity;
        this.fiscalCode = builder.fiscalCode;
        this.nationality = builder.nationality;
    }
    
    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getSurname() {
        return this.surname;
    }

    @Override
    public LocalDate getBirthDate() {
        return this.birthDate;
    }

    @Override
    public String getBirthPlace() {
        return this.birthPlace;
    }

    @Override
    public String getNationality() {
        return this.nationality;
    }

    @Override
    public String getResidenceAddress() {
        return this.residenceAddress;
    }

    @Override
    public String getResidenceCity() {
        return this.residenceCity;
    }

    @Override
    public String getResidenceDistrict() {
        return this.residenceDistrict;
    }

    @Override
    public String getResidenceNation() {
        return this.residenceDistrict;
    }

    @Override
    public String getFiscalCode() {
        return this.fiscalCode;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "ClubMember [name=" + name + ", surname=" + surname + ", birthDate=" + birthDate + ", birthPlace="
                + birthPlace + ", nationality=" + nationality + ", residenceDistrict=" + residenceDistrict
                + ", residenceAddress=" + residenceAddress + ", residenceCity=" + residenceCity + ", residenceNation="
                + residenceNation + ", fiscalCode=" + fiscalCode + "]";
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((birthDate == null) ? 0 : birthDate.hashCode());
        result = prime * result + ((birthPlace == null) ? 0 : birthPlace.hashCode());
        result = prime * result + ((fiscalCode == null) ? 0 : fiscalCode.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((nationality == null) ? 0 : nationality.hashCode());
        result = prime * result + ((surname == null) ? 0 : surname.hashCode());
        return result;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ClubMember other = (ClubMember) obj;
        if (birthDate == null) {
            if (other.birthDate != null)
                return false;
        } else if (!birthDate.equals(other.birthDate))
            return false;
        if (birthPlace == null) {
            if (other.birthPlace != null)
                return false;
        } else if (!birthPlace.equals(other.birthPlace))
            return false;
        if (fiscalCode == null) {
            if (other.fiscalCode != null)
                return false;
        } else if (!fiscalCode.equals(other.fiscalCode))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (nationality == null) {
            if (other.nationality != null)
                return false;
        } else if (!nationality.equals(other.nationality))
            return false;
        if (surname == null) {
            if (other.surname != null)
                return false;
        } else if (!surname.equals(other.surname))
            return false;
        return true;
    }

 }
