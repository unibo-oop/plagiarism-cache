package it.trashwarecesena.trustalodesktopclient.model.immutable.concreteness;

import java.util.Optional;

import it.trashwarecesena.trustalodesktopclient.model.immutable.ImmutableIntelProcessor;
import it.trashwarecesena.trustalodesktopclient.utils.ExtendedObjects;

/**
 * An implementation of the {@link ImmutableIntelProcessor} interface.
 * 
 * @author Manuel Bonarrigo
 *
 */
public final class ImmutableIntelProcessorImpl implements ImmutableIntelProcessor {

    private final Optional<Integer> productId;
    private final Optional<String> processorName;
    private final Optional<Integer> frequencyInMHz;
    private final Optional<String> isa;
    private final Optional<String> cacheType;
    private final Optional<Integer> cacheAmountInKb;
    private final Optional<String> unstructuredInformation;
    private final Optional<String> completeProductName;

    /**
     * Constructs an ImmutableIntelProcessorImpl over the given informations. Since
     * this class is not aimed to be used by a human client, construction is not
     * eased by a fluent builder.
     * 
     * @param productId
     *            the unique identifier given by Intel to this processor
     * @param processorName
     *            the Intel alphanumeric name for the processor. Can be {@code null}
     * @param frequencyInMHz
     *            the frequency of the processor in MHz. Can not be {@code null}
     * @param isa
     *            the instruction architecture set of the processor. Can not be
     *            {@code null}
     * @param cacheType
     *            the cache type available on the processor. Can be {@code null}
     * @param cacheAmountInKb
     *            the amount of cache available on the processor. Can be
     *            {@code null}
     * @param unstructuredInformation
     *            unstructured information about the processor cache. Can be
     *            {@code null}
     * @param completeProductName
     *            the complete name of the processor, as Intel branded it. Can be
     *            {@code null}
     * @throws NullPointerException
     *             if the frequencyInMHz or isa parameters are null.
     * @throws IllegalArgumentException
     *             if the frequencyInMHz parameter is not positive.
     */
    public ImmutableIntelProcessorImpl(final Integer productId, final String processorName, 
            final Integer frequencyInMHz,
            final String isa, final String cacheType, final Integer cacheAmountInKb,
            final String unstructuredInformation, final String completeProductName) {
        this.productId = Optional.ofNullable(productId);
        this.processorName = Optional.ofNullable(processorName);
        this.frequencyInMHz = Optional.ofNullable(ExtendedObjects.requirePositive(frequencyInMHz));
        this.isa = Optional.ofNullable(isa);
        this.cacheType = Optional.ofNullable(cacheType);
        this.cacheAmountInKb = Optional.ofNullable(cacheAmountInKb);
        this.unstructuredInformation = Optional.ofNullable(unstructuredInformation);
        this.completeProductName = Optional.ofNullable(completeProductName);
    }

    @Override
    public Optional<String> getProcessorNumber() {
        return this.processorName;
    }

    @Override
    public Optional<Integer> getFrequencyInMHz() {
        return this.frequencyInMHz;
    }

    @Override
    public Optional<String> getInstructionSet() {
        return this.isa;
    }

    @Override
    public Optional<String> getCacheType() {
        return this.cacheType;
    }

    @Override
    public Optional<Integer> getCacheAmountInKb() {
        return this.cacheAmountInKb;
    }

    @Override
    public Optional<String> getCacheUnstructuredInformation() {
        return this.unstructuredInformation;
    }

    @Override
    public Optional<String> getCompleteProductName() {
        return this.completeProductName;
    }

    @Override
    public Optional<Integer> getProductIdentifier() {
        return this.productId;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((cacheAmountInKb == null) ? 0 : cacheAmountInKb.hashCode());
        result = prime * result + ((cacheType == null) ? 0 : cacheType.hashCode());
        result = prime * result + ((completeProductName == null) ? 0 : completeProductName.hashCode());
        result = prime * result + ((frequencyInMHz == null) ? 0 : frequencyInMHz.hashCode());
        result = prime * result + ((isa == null) ? 0 : isa.hashCode());
        result = prime * result + ((processorName == null) ? 0 : processorName.hashCode());
        result = prime * result + ((productId == null) ? 0 : productId.hashCode());
        result = prime * result + ((unstructuredInformation == null) ? 0 : unstructuredInformation.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ImmutableIntelProcessorImpl other = (ImmutableIntelProcessorImpl) obj;
        if (cacheAmountInKb == null) {
            if (other.cacheAmountInKb != null) {
                return false;
            }
        } else if (!cacheAmountInKb.equals(other.cacheAmountInKb)) {
            return false;
        }
        if (cacheType == null) {
            if (other.cacheType != null) {
                return false;
            }
        } else if (!cacheType.equals(other.cacheType)) {
            return false;
        }
        if (completeProductName == null) {
            if (other.completeProductName != null) {
                return false;
            }
        } else if (!completeProductName.equals(other.completeProductName)) {
            return false;
        }
        if (frequencyInMHz == null) {
            if (other.frequencyInMHz != null) {
                return false;
            }
        } else if (!frequencyInMHz.equals(other.frequencyInMHz)) {
            return false;
        }
        if (isa == null) {
            if (other.isa != null) {
                return false;
            }
        } else if (!isa.equals(other.isa)) {
            return false;
        }
        if (processorName == null) {
            if (other.processorName != null) {
                return false;
            }
        } else if (!processorName.equals(other.processorName)) {
            return false;
        }
        if (productId == null) {
            if (other.productId != null) {
                return false;
            }
        } else if (!productId.equals(other.productId)) {
            return false;
        }
        if (unstructuredInformation == null) {
            if (other.unstructuredInformation != null) {
                return false;
            }
        } else if (!unstructuredInformation.equals(other.unstructuredInformation)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ImmutableIntelProcessorImpl [productId=" + productId + ", processorName=" + processorName
                + ", frequencyInMHz=" + frequencyInMHz + ", isa=" + isa + ", cacheType=" + cacheType
                + ", cacheAmountInKb=" + cacheAmountInKb + ", unstructuredInformation=" + unstructuredInformation
                + ", completeProductName=" + completeProductName + "]";
    }

}
