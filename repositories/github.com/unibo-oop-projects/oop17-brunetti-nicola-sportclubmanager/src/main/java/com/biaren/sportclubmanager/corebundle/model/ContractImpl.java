package com.biaren.sportclubmanager.corebundle.model;

import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;
import com.biaren.sportclubmanager.corebundle.model.interfaces.Contract;

// ----------------------------------------------------------------------------------------
//Inutilizzata per sforamento monte ore. Lasciata per eventuale futura implementazione.
//----------------------------------------------------------------------------------------

public class ContractImpl implements Contract {
    
    private final int contractId = contractSequence.incrementAndGet();    
    private static AtomicInteger contractSequence = new AtomicInteger(0);

    private final Date init;
    private final Date end;
    private final double salaryForYear;
    

    protected ContractImpl(final Builder builder) {
        this.init = builder.init;
        this.end = builder.end;
        this.salaryForYear = builder.salaryForYear;
    }
    
    public static class Builder {
        private Date init;
        private Date end;
        private double salaryForYear;
        
        public Builder init(final Date d) {
            this.init = d;
            return this;
        }
        
        public Builder end(final Date d) {
            this.end = d;
            return this;
        }
        
        public Builder salaryForYear(final double d) {
            this.salaryForYear = d;
            return this;
        }
        
        public ContractImpl build() {
            return new ContractImpl(this);
        }
    }

    public Date getInit() {
        return  this.init;
    }

    public Date getEnd() {
        return  this.end;
    }

    public double getSalaryForYear() {
        return  this.salaryForYear;
    }
    
    public final int getContractId() {
        return this.contractId;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "ContractImpl [contractId=" + contractId + ", init=" + init + ", end=" + end + ", salaryForYear="
                + salaryForYear + "]";
    }
}
