package it.trashwarecesena.trustalodesktopclient.model.people.concreteness;

import it.trashwarecesena.trustalodesktopclient.model.people.AbstractPerson;
import it.trashwarecesena.trustalodesktopclient.model.people.JuridicalPerson;
import it.trashwarecesena.trustalodesktopclient.model.people.PersonCategory;

/**
 * A concrete extension of {@link AbstractPerson} to achieve the characteristics of a {@link JuridicalPerson}. Instantiation
 * is not achieved by a public constructor, but with an inner builder, obtainable by calling
 * {@code new JuridicalPersonImpl.Builder()}.
 * <p>
 * Notice that such a builder does not clean the value of the set parameters after the build is complete, so particular
 * attention should be used if recycling the same builder more than once.
 * <p>
 * It is <b>mandatory</b> to understand that being the JuridicalPersonImpl class part of the
 * {@link it.trashwarecesena.trustalodesktopclient.model.Identifiable Identifiable} family, strict rules do exists about
 * the flow of the information. The Identifiable page of this documentation expresses all the required rules.
 *
 * @author Manuel Bonarrigo
 *
 */
public final class JuridicalPersonImpl extends AbstractPerson implements JuridicalPerson {

    private JuridicalPersonImpl(final Integer identifier, final PersonCategory category, final String name,
            final String fiscalCode, final String annotations) {
        super(identifier, category, name, fiscalCode, annotations);
    }

    @Override
    public int compareTo(final JuridicalPerson o) {
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
        final JuridicalPersonImpl other = (JuridicalPersonImpl) obj;
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
        if (!(getAnnotations().equals(other.getAnnotations()))) { // NOPMD by Manuel Bonarrigo on 7/4/18 12:29 AM This
                                                                  // is a subtle equals, and I need and want it as much
                                                                  // readable as I possibly can.
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "JuridicalPersonImpl [identifier=" + getNumericIdentifier().orElse(null)
                + ", name=" + getName()
                + ", category=" + getCategory()
                + ", fiscalCode=" + getFiscalCode().orElse(null)
                + ", annotations=" + getAnnotations().orElse(null) + "]";
    }

    /**
     * A builder to instantiate a JuridicalPerson through fluent and atomic setting of the parameters.
     * <p>
     * It is <b>mandatory</b> to understand that being the JuridicalPersonImpl class part of the
     * {@link it.trashwarecesena.trustalodesktopclient.model.Identifiable Identifiable} family, strict rules do exists
     * about the flow of the information. The Identifiable page of this documentation expresses all the required rules.
     *
     * @author Manuel Bonarrigo
     *
     */
    public static final class Builder {
        private Integer toBuildIdentifier;
        private PersonCategory toBuildCategory;
        private String toBuildName;
        private String toBuildFiscalCode;
        private String toBuildAnnotations;

        /**
         * Default constructor.
         */
        public Builder() {
            this.toBuildIdentifier = null;
            this.toBuildCategory = null;
            this.toBuildName = null;
            this.toBuildFiscalCode = null;
            this.toBuildAnnotations = null;
        }

        /**
         * Initialize the identifier field of a JuridicalPerson.
         * <p>
         * The usage of this method is optional to the means of the instantiation.
         * <p>
         * Notice that the real instantiation and consequent setting of the field won't
         * happen until the call of the {@code build()} method from this same class
         * <p>
         * It is <b>mandatory</b> to understand that being the JuridicalPersonImpl class
         * part of the
         * {@link it.trashwarecesena.trustalodesktopclient.model.Identifiable
         * Identifiable} family, strict rules do exists about the flow of the
         * information. The Identifiable page of this Javadoc expresses all the required
         * rules.
         *
         * @param identifier
         *            an {@link Integer} for this instance of JuridicalPerson to be
         *            identified with.
         * @return the instance of this same Builder, to achieve fluence.
         * @see {@link it.trashwarecesena.trustalodesktopclient.model.Identifiable
         *      Identifiable}
         */
        public Builder identifier(final Integer identifier) {
            this.toBuildIdentifier = identifier;
            return this;
        }

        /**
         * Initialize the category field of a JuridicalPerson.
         * <p>
         * The usage of this method is <b>mandatory</b> to the means of the
         * instantiation.
         * <p>
         * Notice that the real instantiation and consequent setting of the field won't
         * happen until the call of the {@code build()} method from this same class
         *
         * @param category
         *            a {@link PersonCategory} which classifies this JuridicalPerson.
         * @return the instance of this same Builder, to achieve fluence.
         */
        public Builder category(final PersonCategory category) {
            this.toBuildCategory = category;
            return this;
        }

        /**
         * Initialize the name field of a JuridicalPerson.
         * <p>
         * The usage of this method is <b>mandatory</b> to the means of the
         * instantiation.
         * <p>
         * Notice that the real instantiation and consequent setting of the field won't
         * happen until the call of the {@code build()} method from this same class.
         *
         * @param name
         *            a {@link String} expressing the name of this JuridicalPerson.
         * @return the instance of this same Builder, to achieve fluence.
         */
        public Builder name(final String name) {
            this.toBuildName = name;
            return this;
        }

        /**
         * Initialize the fiscalCode field of a JuridicalPerson.
         * <p>
         * The usage of this method is optional to the means of the instantiation.
         * <p>
         * Notice that the real instantiation and consequent setting of the field won't
         * happen until the call of the {@code build()} method from this same class.
         *
         * @param fiscalCode
         *            a {@link String} expressing the fiscal, legal identifier assigned
         *            to this JuridicalPerson .
         * @return the instance of this same Builder, to achieve fluence.
         */
        public Builder fiscalCode(final String fiscalCode) {
            this.toBuildFiscalCode = fiscalCode;
            return this;
        }

        /**
         * Initialize the annotations field of a JuridicalPerson.
         * <p>
         * The usage of this method is optional to the means of the
         * instantiation.
         * <p>
         * Notice that the real instantiation and consequent setting of the field won't
         * happen until the call of the {@code build()} method from this same class.
         *
         * @param annotations
         *            the {@link String} expressing the notes related to this
         *            JuridicalPerson.
         * @return the instance of this same Builder, to achieve fluence.
         */
        public Builder annotations(final String annotations) {
            this.toBuildAnnotations = annotations;
            return this;
        }

        /**
         * Instantiate and return a JuridicalPerson with the dynamically set values.
         *
         * It is <b>mandatory</b> to understand that being the JuridicalPersonImpl class
         * part of the
         * {@link it.trashwarecesena.trustalodesktopclient.model.Identifiable
         * Identifiable} family, strict rules do exists about the flow of the
         * information. The Identifiable page of this Javadoc expresses all the required
         * rules.
         *
         * @return a fully instantiated JuridicalPerson
         * @throws NullPointerException
         *             if the mandatory arguments name and category are {@code null}
         * @throws IllegalArgumentException
         *             if any of the string related parameters are found to be different
         *             from null and <i>empty</i>
         * @see {@link it.trashwarecesena.trustalodesktopclient.model.Identifiable
         *      Identifiable}
         * @see {@link it.trashwarecesena.trustalodesktopclient.utils.ExtendedObjects#requireNonEmpty(String)
         *      ExtendedObjects.requireNonEmpty(String, String)}
         */
        public JuridicalPersonImpl build() {
            return new JuridicalPersonImpl(toBuildIdentifier, toBuildCategory, toBuildName, toBuildFiscalCode,
                    toBuildAnnotations);
        }
    }

}
