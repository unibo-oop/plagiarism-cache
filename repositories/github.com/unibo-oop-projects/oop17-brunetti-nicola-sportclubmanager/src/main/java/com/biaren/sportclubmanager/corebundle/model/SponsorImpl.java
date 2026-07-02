package com.biaren.sportclubmanager.corebundle.model;

import java.util.concurrent.atomic.AtomicInteger;
import com.biaren.sportclubmanager.corebundle.model.interfaces.Sponsor;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

//--------------------------------------------------------------------------------------------------------
//Campi commentati inutilizzati per sforamento monte ore. Lasciati per eventuale futura implementazione.
//--------------------------------------------------------------------------------------------------------

/**
 * Represent a Sponsor.
 * @author nbrunetti
 *
 */
@JsonDeserialize(builder = SponsorImpl.Builder.class)
public class SponsorImpl implements Sponsor{

    private final int sponsorId;
    private final String name;
    private final String description;
    private final String logoPath;
//    private final List<Contract> contracts;
    
    protected SponsorImpl(final Builder builder) {
        this.sponsorId = builder.sponsorId;
        this.name = builder.name;
        this.description = builder.description;
        this.logoPath = builder.logoPath;
//        this.contracts = builder.contracts;
    }
    
    /**
     * Builds a {@link SponsorImpl}.
     * @author nbrunetti
     *
     */
    @JsonPOJOBuilder(buildMethodName = "build", withPrefix = "")
    public static class Builder {
        private static AtomicInteger sponsorSequence = new AtomicInteger(0);
        private int sponsorId;    
        private String name;
        private String description;
        private String logoPath;
//        private List<Contract> contracts;
        
        public Builder() {
            this.sponsorId = sponsorSequence.incrementAndGet();
        }
        
        public Builder sponsorId(final int i) {
            this.sponsorId = i;
            return this;
        }
        
        public Builder name(final String s) {
            this.name = s;
            return this;
        }
        
        public Builder description(final String s) {
            this.description = s;
            return this;
        }
        
        public Builder logoPath(final String s) {
            this.logoPath = s;
            return this;
        }
        
//        public Builder contracts(final Collection<Contract> c) {
//            this.contracts = new ArrayList<>(c);
//            return this;
//        }
        
        public SponsorImpl build() {
            return new SponsorImpl(this);
        }
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public String getLogoPath() {
        return this.logoPath;
    }

//    @Override
//    public List<Contract> getContracts() {
//        return new ArrayList<>(this.contracts);
//    }
    
    public int getSponsorId() {
        return this.sponsorId;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "SponsorImpl [sponsorId=" + sponsorId + ", name=" + name + ", description=" + description + ", logoPath="
                + logoPath + "]";
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
//        result = prime * result + ((contracts == null) ? 0 : contracts.hashCode());
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        result = prime * result + ((logoPath == null) ? 0 : logoPath.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + sponsorId;
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
        SponsorImpl other = (SponsorImpl) obj;
//        if (contracts == null) {
//            if (other.contracts != null)
//                return false;
//        } else if (!contracts.equals(other.contracts))
//            return false;
        if (description == null) {
            if (other.description != null)
                return false;
        } else if (!description.equals(other.description))
            return false;
        if (logoPath == null) {
            if (other.logoPath != null)
                return false;
        } else if (!logoPath.equals(other.logoPath))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (sponsorId != other.sponsorId)
            return false;
        return true;
    }

}
