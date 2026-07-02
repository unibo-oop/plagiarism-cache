package it.trashwarecesena.trustalodesktopclient.model.otherdevices.concreteness;

import java.util.Objects;
import java.util.Optional;

import it.trashwarecesena.trustalodesktopclient.model.devices.DigitalInformationUnit;
import it.trashwarecesena.trustalodesktopclient.model.devices.GenericDevice;
import it.trashwarecesena.trustalodesktopclient.model.otherdevices.FrequencyUnit;
import it.trashwarecesena.trustalodesktopclient.model.otherdevices.InstructionSet;
import it.trashwarecesena.trustalodesktopclient.model.otherdevices.Processor;
import it.trashwarecesena.trustalodesktopclient.utils.ErrorString;
import it.trashwarecesena.trustalodesktopclient.utils.ExtendedObjects;

/**
 * An implementation of the {@link Processor} interface.
 * 
 * @author Manuel Bonarrigo
 *
 */
public final class ProcessorImpl implements Processor {

    private final GenericDevice device;
    private final Float frequency;
    private final FrequencyUnit frequencyUnit;
    private final InstructionSet set;
    private final Optional<Integer> l3CacheAmount;
    private final Optional<DigitalInformationUnit> l3CacheUnit;

    private ProcessorImpl(final GenericDevice device, final Float frequency, final FrequencyUnit frequencyUnit, 
            final InstructionSet set, final Integer l3CacheAmount, final DigitalInformationUnit l3CacheUnit) {
        this.device = Objects.requireNonNull(device, "The GenericDevice" + ErrorString.CUSTOM_NULL);
        this.frequency = ExtendedObjects.requirePositive(
                Objects.requireNonNull(frequency, "The Frequency" + ErrorString.CUSTOM_NULL),
                    "The float value" + ErrorString.ONLY_POSITIVE);
        this.frequencyUnit = Objects.requireNonNull(
                frequencyUnit, "The DigitalInformationUnit" + ErrorString.CUSTOM_NULL);
        this.set = Objects.requireNonNull(set, "The InstructionSet" + ErrorString.CUSTOM_NULL);
        this.l3CacheAmount = Optional.ofNullable(ExtendedObjects.requirePositive(l3CacheAmount,
                "The cache amount" + ErrorString.ONLY_POSITIVE));
        this.l3CacheUnit = Optional.ofNullable(l3CacheUnit);
        if ((this.l3CacheAmount.isPresent() != this.l3CacheUnit.isPresent())) {
            throw new IllegalArgumentException("The L3CacheAmount and the L3CacheUnit must be both present, "
                    + "or both absent");
        }
    }

    @Override
    public GenericDevice getGenericDevice() {
        return this.device;
    }

    @Override
    public Float getFrequency() {
        return this.frequency;
    }

    @Override
    public FrequencyUnit getFrequencyUnit() {
        return this.frequencyUnit;
    }

    @Override
    public InstructionSet getInstructionSet() {
        return this.set;
    }

    @Override
    public Optional<Integer> getL3CacheAmount() {
        return this.l3CacheAmount;
    }

    @Override
    public Optional<DigitalInformationUnit> getL3CacheInformationUnit() {
        return this.l3CacheUnit;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((device == null) ? 0 : device.hashCode());
        result = prime * result + ((frequency == null) ? 0 : frequency.hashCode());
        result = prime * result + ((frequencyUnit == null) ? 0 : frequencyUnit.hashCode());
        result = prime * result + ((l3CacheAmount == null) ? 0 : l3CacheAmount.hashCode());
        result = prime * result + ((l3CacheUnit == null) ? 0 : l3CacheUnit.hashCode());
        result = prime * result + ((set == null) ? 0 : set.hashCode());
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
        final ProcessorImpl other = (ProcessorImpl) obj;
        if (device == null) {
            if (other.device != null) {
                return false;
            }
        } else if (!device.equals(other.device)) {
            return false;
        }
        if (frequency == null) {
            if (other.frequency != null) {
                return false;
            }
        } else if (!frequency.equals(other.frequency)) {
            return false;
        }
        if (frequencyUnit == null) {
            if (other.frequencyUnit != null) {
                return false;
            }
        } else if (!frequencyUnit.equals(other.frequencyUnit)) {
            return false;
        }
        if (l3CacheAmount == null) {
            if (other.l3CacheAmount != null) {
                return false;
            }
        } else if (!l3CacheAmount.equals(other.l3CacheAmount)) {
            return false;
        }
        if (l3CacheUnit == null) {
            if (other.l3CacheUnit != null) {
                return false;
            }
        } else if (!l3CacheUnit.equals(other.l3CacheUnit)) {
            return false;
        }
        if (set == null) {
            if (other.set != null) {
                return false;
            }
        } else if (!set.equals(other.set)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ProcessorImpl [device=" + device + ", frequency=" + frequency + ", frequencyUnit=" + frequencyUnit
                + ", set=" + set + ", l3CacheAmount=" + l3CacheAmount + ", l3CacheUnit=" + l3CacheUnit + "]";
    }

    /**
     * A builder to instantiate a Processor through fluent and atomic setting of
     * the parameters.
     * 
     * @author Manuel Bonarrigo
     *
     */
    public static final class Builder {

        private GenericDevice builderDevice;
        private Float builderFrequency;
        private FrequencyUnit builderFrequencyUnit;
        private InstructionSet builderSet;
        private Integer builderL3CacheAmount;
        private DigitalInformationUnit builderL3CacheUnit;

        /**
         * Initialize the refined field of a Processor.
         * <p>
         * The usage of this method is mandatory to the means of the instantiation.
         * <p>
         * Notice that the real instantiation and consequent setting of the field won't
         * happen until the call of the {@code build()} method from this same class
         * <p>
         * It is <b>mandatory</b> to understand that being the GenericDevice interface
         * part of the
         * {@link it.trashwarecesena.trustalodesktopclient.model.Identifiable
         * Identifiable} family, strict rules do exists about the flow of the
         * information. The Identifiable page of this Javadoc expresses all the required
         * rules.
         * 
         * @param device
         *            the {@link GenericDevice} containing the more generic information
         *            about this processor
         * @return the instance of this same Builder, to achieve fluence.
         */
        public Builder device(final GenericDevice device) {
            this.builderDevice = device;
            return this;
        }

        /**
         * Initialize the frequency field of a Processor.
         * <p>
         * The usage of this method is mandatory to the means of the instantiation.
         * <p>
         * Notice that the real instantiation and consequent setting of the field won't
         * happen until the call of the {@code build()} method from this same class
         * 
         * @param frequency
         *            the {@link Float} representing the clock speed of the processor.
         * @return the instance of this same Builder, to achieve fluence.
         */
        public Builder frequency(final Float frequency) {
            this.builderFrequency = frequency;
            return this;
        }

        /**
         * Initialize the frequency unit field of a Processor.
         * <p>
         * The usage of this method is mandatory to the means of the instantiation.
         * <p>
         * Notice that the real instantiation and consequent setting of the field won't
         * happen until the call of the {@code build()} method from this same class
         * 
         * @param frequencyUnit
         *            the {@link FrequencyUnit} representing the unit of measure of the
         *            processor clock speed.
         * @return the instance of this same Builder, to achieve fluence.
         */
        public Builder frequencyUnit(final FrequencyUnit frequencyUnit) {
            this.builderFrequencyUnit = frequencyUnit;
            return this;
        }

        /**
         * Initialize the instruction set field of a Processor.
         * <p>
         * The usage of this method is mandatory to the means of the instantiation.
         * <p>
         * Notice that the real instantiation and consequent setting of the field won't
         * happen until the call of the {@code build()} method from this same class
         * 
         * @param instructionSet
         *            the {@link InstructionSet} representing the instruction set
         *            architecture of the processor
         * @return the instance of this same Builder, to achieve fluence.
         */
        public Builder instructionSet(final InstructionSet instructionSet) {
            this.builderSet = instructionSet;
            return this;
        }

        /**
         * Initialize the L3 cache amount field of a Processor.
         * <p>
         * The usage of this method is optional to the means of the instantiation.
         * <p>
         * Notice that the real instantiation and consequent setting of the field won't
         * happen until the call of the {@code build()} method from this same class
         * 
         * @param cacheAmount
         *            the {@link Integer} representing the amount of L3 cache given to
         *            this processor.
         * @return the instance of this same Builder, to achieve fluence.
         */
        public Builder l3CacheAmount(final Integer cacheAmount) {
            this.builderL3CacheAmount = cacheAmount;
            return this;
        }

        /**
         * Initialize the digital information unit field of a Processor.
         * <p>
         * The usage of this method is optional to the means of the instantiation.
         * <p>
         * Notice that the real instantiation and consequent setting of the field won't
         * happen until the call of the {@code build()} method from this same class
         * 
         * @param unit
         *            the {@link DigitalInformationUnit} representing the representing
         *            the unit of measure of the amount of L3 cache amount.
         * @return the instance of this same Builder, to achieve fluence.
         */
        public Builder l3CacheUnit(final DigitalInformationUnit unit) {
            this.builderL3CacheUnit = unit;
            return this;
        }

        /**
         * Instantiate and return a Processor with the dynamically set values.
         * 
         * @return a fully instantiated Processor
         * @throws NullPointerException
         *             if any of the mandatory parameter is {@code null}
         * @throws IllegalArgumentException
         *             if any of the string related parameters are found to be
         *             <i>empty</i>
         * @see {@link it.trashwarecesena.trustalodesktopclient.utils.ExtendedObjects#requireNonEmpty(String)
         *      ExtendedObjects.requireNonEmpty(String, String)}
         */
        public ProcessorImpl build() {
            return new ProcessorImpl(builderDevice, builderFrequency, builderFrequencyUnit, builderSet, 
                    builderL3CacheAmount, builderL3CacheUnit);
        }
    }
}
