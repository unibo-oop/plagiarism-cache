package it.trashwarecesena.trustalodesktopclient.model.people.concreteness;

import it.trashwarecesena.trustalodesktopclient.model.people.AbstractPhysicalPerson;
import it.trashwarecesena.trustalodesktopclient.model.people.PersonCategory;
import it.trashwarecesena.trustalodesktopclient.model.people.PhysicalPerson;

import java.sql.Date;

/**
 * A concrete extension of {@link AbstractPhysicalPerson}. Instantiation is not achieved by a public constructor, but
 * with an inner builder, obtainable by calling {@code new PhysicalPersonImpl.Builder()}.
 * <p>
 * Notice that such a builder does not clean the value of the set parameters after the build is complete, so particular
 * attention should be used if recycling the same builder more than once.
 * <p>
 * It is <b>mandatory</b> to understand that being the PhysicalPersonImpl class part of the
 * {@link it.trashwarecesena.trustalodesktopclient.model.Identifiable Identifiable} family, strict rules do exists about
 * the flow of the information. The Identifiable page of this documentation expresses all the required rules.
 *
 * @author Manuel Bonarrigo
 *
 */
public final class PhysicalPersonImpl extends AbstractPhysicalPerson {

    private PhysicalPersonImpl(final Integer identifier, final String name, final PersonCategory category,
            final String fiscalCode, final Date birthDate, final String birthLocation, final String annotations) {
        super(identifier, name, category, fiscalCode, birthDate, birthLocation, annotations);
    }

    @Override
    public int compareTo(final PhysicalPerson o) {
        if (getNumericIdentifier().isPresent()) {
            return o.getNumericIdentifier().isPresent()
                    ? Integer.compare(this.getNumericIdentifier().get(), o.getNumericIdentifier().get())
                    : 1;
        } else {
            return o.getNumericIdentifier().isPresent() ? -1 : 0;
        }
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + getNumericIdentifier().hashCode();
        if (getNumericIdentifier().isPresent()) {
            result = prime * result + getCategory().hashCode();
            result = prime * result + getName().hashCode();
            result = prime * result + getFiscalCode().hashCode();
            result = prime * result + getAnnotations().hashCode();
            result = prime * result + getBirthDate().hashCode();
            result = prime * result + getBirthLocation().hashCode();
        }
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PhysicalPersonImpl other = (PhysicalPersonImpl) obj;
        if (!(getNumericIdentifier().isPresent() && (other.getNumericIdentifier().isPresent()))) {
            return false;
        }
        if (!(getNumericIdentifier().get().equals(other.getNumericIdentifier().get()))) {
            return false;
        }
        if (!(getName().equals(other.getName()))) {
            return false;
        }
        if (!(getFiscalCode().equals(other.getFiscalCode()))) {
            return false;
        }
        if (!(getCategory().equals(other.getCategory()))) {
            return false;
        }
        if (!(getBirthDate().equals(other.getBirthDate()))) {
            return false;
        }
        if (!(getBirthLocation().equals(other.getBirthLocation()))) {
            return false;
        }
        if (!(getAnnotations().equals(other.getAnnotations()))) { // NOPMD by Manuel Bonarrigo on 7/4/18 12:29 AM This
                                                                  // is a subtle equals, and I need and want it as much
                                                                  // readable as I possibly can.
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "PhysicalPersonImpl [identifier=" + getNumericIdentifier().orElse(null) + ", name=" + getName()
                + ", category=" + getCategory() + ", fiscalCode=" + getFiscalCode().orElse(null) + ", birthDate="
                + getBirthDate().orElse(null) + ", birthLocation=" + getBirthLocation().orElse(null) + ", annotations="
                + getAnnotations().orElse(null) + "]";
    }

    /**
     * A builder to instantiate a PhysicalPerson through fluent and atomic setting
     * of the parameters.
     *
     * @author Manuel Bonarrigo
     *
     */
    public static final class Builder {
        private Integer identifierToBuild;
        private String nameToBuild;
        private String fiscalCodeToBuild;
        private final PersonCategory categoryToBuild;
        private Date birthDateToBuild;
        private String birthLocationToBuild;
        private String annotationsToBuild;

        /**
         * Default constructor.
         */
        public Builder() {
            categoryToBuild = new PersonCategoryImpl("Privato");
        }

        /**
         * Initialize the identifier field of a PhysicalPerson.
         * <p>
         * The usage of this method is optional to the means of the instantiation. If set, it has to be a number greater
         * than zero.
         * <p>
         * Notice that the real instantiation and consequent setting of the field won't happen until the call of the
         * {@code build()} method from this same class.
         *
         * It is <b>mandatory</b> to understand that being the PhysicalPersonImpl class part of the
         * {@link it.trashwarecesena.trustalodesktopclient.model.Identifiable Identifiable} family, strict rules do
         * exists about the flow of the information. The Identifiable page of this Javadoc expresses all the required
         * rules.
         *
         * @param identifier
         *            an {@link Integer} for this instance of PhysicalPerson to be
         *            identified with.
         * @return the instance of this same Builder, to achieve fluence.
         * @see {@link it.trashwarecesena.trustalodesktopclient.model.Identifiable
         *      Identifiable}
         */
        public Builder identifier(final Integer identifier) {
            this.identifierToBuild = identifier;
            return this;
        }

        /**
         * Initialize the name field of a PhysicalPerson.
         * <p>
         * The usage of this method is <b>mandatory</b> to the means of the instantiation. It has to not be null.
         * <p>
         * Notice that the real instantiation and consequent setting of the field won't happen until the call of the
         * {@code build()} method from this same class.
         *
         * @param name
         *            a {@link String} expressing the name of this PhysicalPerson.
         * @return the instance of this same Builder, to achieve fluence.
         */
        public Builder name(final String name) {
            this.nameToBuild = name;
            return this;
        }

        /**
         * Initialize the fiscalCode field of a PhysicalPerson.
         * <p>
         * The usage of this method is optional to the means of the instantiation. If set, it can not be an empty
         * String.
         * <p>
         * Notice that the real instantiation and consequent setting of the field won't
         * happen until the call of the {@code build()} method from this same class.
         *
         * @param fiscalCode
         *            a {@link String} expressing the fiscal, legal identifier assigned
         *            to this PhysicalPerson .
         * @return the instance of this same Builder, to achieve fluence.
         */
        public Builder fiscalCode(final String fiscalCode) {
            this.fiscalCodeToBuild = fiscalCode;
            return this;
        }

        /**
         * Initialize the birthDate field of a PhysicalPerson.
         * <p>
         * The usage of this method is optional to the means of the instantiation.
         * <p>
         * Notice that the real instantiation and consequent setting of the field won't
         * happen until the call of the {@code build()} method from this same class.
         *
         * @param birthDate
         *            a {@link Date} expressing the date of birth of the PhysicalPerson.
         * @return the instance of this same Builder, to achieve fluence.
         */
        public Builder birthDate(final Date birthDate) {
            this.birthDateToBuild = birthDate;
            return this;
        }

        /**
         * Initialize the birthLocation field of a PhysicalPerson.
         * <p>
         * The usage of this method is optional to the means of the instantiation. If set, it can not be an empty
         * String.
         * <p>
         * Notice that the real instantiation and consequent setting of the field won't
         * happen until the call of the {@code build()} method from this same class.
         *
         * @param birthLocation
         *            a {@link String} expressing location of birth of this
         *            PhysicalPerson.
         * @return the instance of this same Builder, to achieve fluence.
         */
        public Builder birthLocation(final String birthLocation) {
            this.birthLocationToBuild = birthLocation;
            return this;
        }

        /**
         * Initialize the annotations field of a PhysicalPerson.
         * <p>
         * The usage of this method is optional to the means of the instantiation. If set, it can not be an empty
         * String.
         * <p>
         * Notice that the real instantiation and consequent setting of the field won't
         * happen until the call of the {@code build()} method from this same class.
         *
         * @param annotations
         *            the {@link String} expressing the notes related to this
         *            PhysicalPerson.
         * @return the instance of this same Builder, to achieve fluence.
         */
        public Builder annotations(final String annotations) {
            this.annotationsToBuild = annotations;
            return this;
        }

        /**
         * Instantiate and return a PhysicalPerson with the dynamically set values.
         * <p>
         * It is <b>mandatory</b> to understand that being the PhysicalPersonImpl class part of the
         * {@link it.trashwarecesena.trustalodesktopclient.model.Identifiable Identifiable} family, strict rules do
         * exists about the flow of the information. The Identifiable page of this Javadoc expresses all the required
         * rules.
         *
         * @return a fully instantiated PhysicalPerson
         * @throws NullPointerException
         *             if the mandatory arguments name and category are {@code null}
         * @throws IllegalArgumentException
         *             if any of the string related parameters are found to be different
         *             from null and <i>empty</i>, or if the identifier is not greater than zero, if set.
         * @see {@link it.trashwarecesena.trustalodesktopclient.model.Identifiable
         *      Identifiable}
         * @see {@link it.trashwarecesena.trustalodesktopclient.utils.ExtendedObjects#requireNonEmpty(String)
         *      ExtendedObjects.requireNonEmpty(String, String)}

         * @see {@link it.trashwarecesena.trustalodesktopclient.utils.ExtendedObjects#requireNonEmpty(String)
         *      ExtendedObjects.requirePositive(Integer, String)}
         */
        public PhysicalPersonImpl build() throws IllegalArgumentException {
            return new PhysicalPersonImpl(identifierToBuild, nameToBuild, categoryToBuild, fiscalCodeToBuild,
                    birthDateToBuild, birthLocationToBuild, annotationsToBuild);
        }
    }

}
