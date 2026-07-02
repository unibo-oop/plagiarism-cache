package com.biaren.sportclubmanager.corebundle.model;

import java.util.concurrent.atomic.AtomicInteger;

import com.biaren.sportclubmanager.corebundle.model.interfaces.SportVenue;

//----------------------------------------------------------------------------------------
//Inutilizzata per sforamento monte ore. Lasciata per eventuale futura implementazione.
//----------------------------------------------------------------------------------------

public class SportVenueImpl extends FacilityImpl implements SportVenue {

    private final int sportVenueId = sportVenueSequence.incrementAndGet();    
    private static AtomicInteger sportVenueSequence = new AtomicInteger(0);
    
    private final int totalSeats;
    private final double fieldLength;
    private final double fieldWidth;
    private final boolean isIndoor;
    
    protected SportVenueImpl(final Builder builder) {
        super(builder);
        this.fieldLength = builder.fieldLength;
        this.fieldWidth = builder.fieldWidth;
        this.totalSeats = builder.totalSeats;
        this.isIndoor = builder.isIndoor;
    }
    
    public static class Builder extends FacilityImpl.Builder<Builder> {
        private int totalSeats;
        private double fieldLength;
        private double fieldWidth;
        private boolean isIndoor;
        
        public Builder totalSeats(final int i) {
            this.totalSeats = i;
            return this.getThis();
        }
        
        public Builder fieldLength(final double d) {
            this.fieldLength = d;
            return this.getThis();
        }
        
        public Builder fieldWidth(final double d) {
            this.fieldWidth = d;
            return this.getThis();
        }
        
        public Builder isIndoor(final boolean b) {
            this.isIndoor = b;
            return this.getThis();
        }
        
        @Override
        public Builder getThis() {
            return this;
        }
        
        public SportVenueImpl build() {
            return new SportVenueImpl(this);
        }
    }

    public int getTotalSeats() {
        return this.totalSeats;
    }

    public double getFieldLength() {
        return this.fieldLength;
    }

    public double getFieldWidth() {
        return this.fieldWidth;
    }

    public boolean isIndoor() {
        return this.isIndoor;
    }
    
    public int getSportVenueId() {
        return this.sportVenueId;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "SportVenueImpl [name=" + name + ", address=" + address + ", description=" + description
                + ", totalSeats=" + totalSeats + ", fieldLength=" + fieldLength + ", fieldWidth=" + fieldWidth
                + ", isIndoor=" + isIndoor + "]";
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        long temp;
        temp = Double.doubleToLongBits(fieldLength);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(fieldWidth);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        result = prime * result + (isIndoor ? 1231 : 1237);
        result = prime * result + sportVenueId;
        result = prime * result + totalSeats;
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
        SportVenueImpl other = (SportVenueImpl) obj;
        if (Double.doubleToLongBits(fieldLength) != Double.doubleToLongBits(other.fieldLength))
            return false;
        if (Double.doubleToLongBits(fieldWidth) != Double.doubleToLongBits(other.fieldWidth))
            return false;
        if (isIndoor != other.isIndoor)
            return false;
        if (sportVenueId != other.sportVenueId)
            return false;
        if (totalSeats != other.totalSeats)
            return false;
        return true;
    }
}
