package com.biaren.sportclubmanager.corebundle.model;

import java.util.concurrent.atomic.AtomicInteger;

import com.biaren.sportclubmanager.corebundle.model.interfaces.Facility;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

/**
 * Represent a facility propriety of society.
 * @author nbrunetti
 *
 */
@JsonDeserialize(builder = FacilityImpl.Builder.class)
public class FacilityImpl implements Facility{

    private final int facilityId;
    protected final String name;
    protected final String address;
    protected final String description;
    
    protected FacilityImpl(final Builder builder) {
        this.facilityId = builder.facilityId;
        this.name = builder.name;
        this.address = builder.address;
        this.description = builder.description;
    }
    
    /**
     * Curiosly generic-pattern. Builds a new {@link FacilityImpl}.
     * Allow to extends this to create a more specif Facility, like a 
     * Sport Venue.
     * @author nbrunetti
     *
     * @param <T> of Builder to extend
     */
    @JsonPOJOBuilder(buildMethodName = "build", withPrefix = "")
    public static class Builder<T extends Builder<? extends T>> {
        
        private int facilityId;
        private static AtomicInteger facilitySequence = new AtomicInteger(0);
        private String name;
        private String address;
        private String description;
        
        public Builder() {
            this.facilityId = facilitySequence.incrementAndGet();
        }
        
        public T facilityId(final int id) {
            this.facilityId = id;
            return this.getThis();
        }
        
        public T name(final String s) {
            this.name = s;
            return this.getThis();
        }
        
        public T address(final String s) {
            this.address = s;
            return this.getThis();
        }
        
        public T description(final String s) {
            this.description = s;
            return this.getThis();
        }
        
       @SuppressWarnings("unchecked")
       public T getThis() {
           return (T) this;
       }
        
        public FacilityImpl build() {
            return new FacilityImpl(this);
        }
    }

    public String getName() {
        return this.name;
    }

    public String getAddress() {
        return this.address;
    }

    public String getDescription() {
        return this.description;
    }
    
    public int getFacilityId() {
        return this.facilityId;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "FacilityImpl [facilityId=" + facilityId + ", name=" + name + ", address=" + address + ", description="
                + description + "]";
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((address == null) ? 0 : address.hashCode());
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        result = prime * result + facilityId;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
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
        FacilityImpl other = (FacilityImpl) obj;
        if (address == null) {
            if (other.address != null)
                return false;
        } else if (!address.equals(other.address))
            return false;
        if (description == null) {
            if (other.description != null)
                return false;
        } else if (!description.equals(other.description))
            return false;
        if (facilityId != other.facilityId)
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }
}
