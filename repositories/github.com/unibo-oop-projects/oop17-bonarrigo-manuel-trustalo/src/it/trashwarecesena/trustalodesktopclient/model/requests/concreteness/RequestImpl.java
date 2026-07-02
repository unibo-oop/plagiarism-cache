package it.trashwarecesena.trustalodesktopclient.model.requests.concreteness;

import java.net.URL;
import java.sql.Date;
import java.util.Objects;
import java.util.Optional;

import it.trashwarecesena.trustalodesktopclient.model.people.Person;
import it.trashwarecesena.trustalodesktopclient.model.people.TrashwareWorker;
import it.trashwarecesena.trustalodesktopclient.model.requests.Request;
import it.trashwarecesena.trustalodesktopclient.model.requests.RequestProgress;
import it.trashwarecesena.trustalodesktopclient.utils.ErrorString;
import it.trashwarecesena.trustalodesktopclient.utils.ExtendedObjects;

/**
 * An implementation of the {@link Request} interface.
 * <p>
 * It is <b>mandatory</b> to understand that being the Request interface part of
 * the {@link it.trashwarecesena.trustalodesktopclient.model.Identifiable
 * Identifiable} family, strict rules do exists about the flow of the
 * information. The Identifiable page of this Javadoc expresses all the required
 * rules.
 * 
 * @author Manuel Bonarrigo
 *
 */
public final class RequestImpl implements Request {

    private final Optional<Integer> identifier;
    private final Person applicant;
    private final Date creationDate;
    private final RequestProgress progress;
    private final Date lastUpdate;
    private final TrashwareWorker lastCommitter;
    private final Optional<Person> referee;
    private final Optional<Person> signer;
    private final Optional<URL> trelloLink;
    private final Optional<String> annotations;
    private final Integer priority;

    private RequestImpl(final Integer identifier, final Person applicant, final Date creationDate,
            final RequestProgress progress, final Date lastUpdate, final TrashwareWorker lastCommitter, 
            final Person referee, final Person signer, final URL trelloLink, final String annotations, 
            final Integer priority) {
        this.identifier = 
                Optional.ofNullable(
                        ExtendedObjects.requirePositive(identifier, "The identifier" + ErrorString.ONLY_POSITIVE));
        this.applicant = Objects.requireNonNull(applicant, "The applicant" + ErrorString.CUSTOM_NULL);
        this.creationDate = Objects.requireNonNull(creationDate, "The creationDate" + ErrorString.CUSTOM_NULL);
        this.progress = Objects.requireNonNull(progress, "The progress" + ErrorString.CUSTOM_NULL);
        this.lastUpdate = Objects.requireNonNull(lastUpdate, "The lastUpdate" + ErrorString.CUSTOM_NULL);
        this.lastCommitter = Objects.requireNonNull(lastCommitter, "The lastCommitter" + ErrorString.CUSTOM_NULL);
        this.referee = Optional.ofNullable(referee);
        this.signer = Optional.ofNullable(signer);
        this.trelloLink = Optional.ofNullable(trelloLink);
        this.annotations = Optional.ofNullable(ExtendedObjects.requireNonEmpty(annotations, ErrorString.EMPTY_STRING));
        this.priority = ExtendedObjects.requirePositive(Objects.requireNonNull(priority, "The Integer" 
                + ErrorString.CUSTOM_NULL), "The priority" + ErrorString.ONLY_POSITIVE);
    }

    @Override
    public Optional<Integer> getNumericIdentifier() {
        return this.identifier;
    }

    @Override
    public Person getApplicant() {
        return this.applicant;
    }

    @Override
    public Date getCreationDate() {
        return this.creationDate;
    }

    @Override
    public RequestProgress getRequestProgress() {
        return this.progress;
    }

    @Override
    public Date getLastUpdate() {
        return this.lastUpdate;
    }

    @Override
    public TrashwareWorker getLastCommitter() {
        return this.lastCommitter;
    }

    @Override
    public Optional<Person> getReferee() {
        return this.referee;
    }

    @Override
    public Optional<Person> getSigner() {
        return this.signer;
    }

    @Override
    public Optional<URL> getTrelloLink() {
        return this.trelloLink;
    }

    @Override
    public Optional<String> getAnnotations() {
        return this.annotations;
    }

    @Override
    public Integer getPriority() {
        return this.priority;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + identifier.hashCode();
        if (identifier.isPresent()) {
            result = prime * result + ((annotations == null) ? 0 : annotations.hashCode());
            result = prime * result + ((applicant == null) ? 0 : applicant.hashCode());
            result = prime * result + ((creationDate == null) ? 0 : creationDate.hashCode());
            result = prime * result + ((lastCommitter == null) ? 0 : lastCommitter.hashCode());
            result = prime * result + ((lastUpdate == null) ? 0 : lastUpdate.hashCode());
            result = prime * result + ((priority == null) ? 0 : priority.hashCode());
            result = prime * result + ((progress == null) ? 0 : progress.hashCode());
            result = prime * result + ((referee == null) ? 0 : referee.hashCode());
            result = prime * result + ((signer == null) ? 0 : signer.hashCode());
            result = prime * result + ((trelloLink == null) ? 0 : trelloLink.hashCode());
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
        final RequestImpl other = (RequestImpl) obj;
        if (!(getNumericIdentifier().isPresent() && (other.getNumericIdentifier().isPresent()))) {
            return false;
        }
        if (!(getNumericIdentifier().get().equals(other.getNumericIdentifier().get()))) {
            return false;
        }
        if (!(annotations.equals(other.annotations))) {
            return false;
        }
        if (!(applicant.equals(other.applicant))) {
            return false;
        }
        if (!(creationDate.equals(other.creationDate))) {
            return false;
        }
        if (!(lastCommitter.equals(other.lastCommitter))) {
            return false;
        }
        if (!(lastUpdate.equals(other.lastUpdate))) {
            return false;
        }
        if (!(priority.equals(other.priority))) {
            return false;
        }
        if (!(progress.equals(other.progress))) {
            return false;
        }
        if (!(referee.equals(other.referee))) {
            return false;
        }
        if (!(signer.equals(other.signer))) {
            return false;
        }
        if (!(trelloLink.equals(other.trelloLink))) { // NOPMD by Manuel Bonarrigo on 7/6/18 5:56 PM This is a subtle
                                                      // equals,
                                                      //and I need and want it as much readable as I possibly can.
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "RequestImpl [identifier=" + identifier + ", applicant=" + applicant + ", creationDate=" + creationDate
                + ", progress=" + progress + ", lastUpdate=" + lastUpdate + ", lastCommitter=" + lastCommitter
                + ", referee=" + referee.orElse(null) + ", signer=" + signer.orElse(null) + ", trelloLink=" 
                + trelloLink.orElse(null) + ", annotations=" + annotations.orElse(null) + ", priority=" 
                + priority + "]";
    }

    /**
     * A builder to instantiate a RequestImpl through fluent and atomic setting of
     * the parameters.
     * 
     * @author Manuel Bonarrigo
     *
     */
    public static final class Builder {

        private Integer identifierBuilder;
        private Person applicantBuilder;
        private Date creationDateBuilder;
        private RequestProgress progressBuilder;
        private Date lastUpdateBuilder;
        private TrashwareWorker lastCommitterBuilder;
        private Person refereeBuilder;
        private Person signerBuilder;
        private URL trelloLinkBuilder;
        private String annotationsBuilder;
        private Integer priorityBuilder;

        /**
         * Basic constructor.
         */
        public Builder() {
            this.priorityBuilder = Integer.valueOf(0);
        }

        /**
         * Initialize the identifier field of a Request.
         * <p>
         * The usage of this method is optional to the means of the instantiation.
         * <p>
         * Notice that the real instantiation and consequent setting of the field won't
         * happen until the call of the {@code build()} method from this same class
         * <p>
         * It is <b>mandatory</b> to understand that being the RequestImpl class part
         * of the {@link it.trashwarecesena.trustalodesktopclient.model.Identifiable
         * Identifiable} family, strict rules do exists about the flow of the
         * information. The Identifiable page of this Javadoc expresses all the required
         * rules.
         * 
         * @param identifier
         *            an {@link Integer} for this instance of Request to be
         *            identified with.
         * @return the instance of this same Builder, to achieve fluence.
         * @see {@link it.trashwarecesena.trustalodesktopclient.model.Identifiable
         *      Identifiable}
         */
        public Builder identifier(final Integer identifier) {
            this.identifierBuilder = identifier;
            return this;
        }

        /**
         * Initialize the applicant field of a Request.
         * <p>
         * The usage of this method is mandatory to the means of the instantiation.
         * <p>
         * Notice that the real instantiation and consequent setting of the field won't
         * happen until the call of the {@code build()} method from this same class
         * <p>
         * It is <b>mandatory</b> to understand that being the Person interface part of
         * the {@link it.trashwarecesena.trustalodesktopclient.model.Identifiable
         * Identifiable} family, strict rules do exists about the flow of the
         * information. The Identifiable page of this Javadoc expresses all the required
         * rules.
         * 
         * @param applicant
         *            a {@link Person} for this instance of Request to be associated
         *            with.
         * @return the instance of this same Builder, to achieve fluence.
         * @see {@link it.trashwarecesena.trustalodesktopclient.model.Identifiable
         *      Identifiable}
         */
        public Builder applicant(final Person applicant) {
            this.applicantBuilder = applicant;
            return this;
        }

        /**
         * Initialize the creation date field of a Request.
         * <p>
         * The usage of this method is mandatory to the means of the instantiation.
         * <p>
         * Notice that the real instantiation and consequent setting of the field won't
         * happen until the call of the {@code build()} method from this same class
         * 
         * @param creationDate
         *            a {@link Date} representing the moment when the Request is built.
         * @return the instance of this same Builder, to achieve fluence.
         */
        public Builder creationDate(final Date creationDate) {
            this.creationDateBuilder = creationDate;
            return this;
        }

        /**
         * Initialize the progress field of a Request.
         * <p>
         * The usage of this method is mandatory to the means of the instantiation.
         * <p>
         * Notice that the real instantiation and consequent setting of the field won't
         * happen until the call of the {@code build()} method from this same class
         * 
         * @param progress
         *            a {@link RequestProgress} the state of progress of the request.
         * @return the instance of this same Builder, to achieve fluence.
         */
        public Builder progress(final RequestProgress progress) {
            this.progressBuilder = progress;
            return this;
        }

        /**
         * Initialize the last update date field of a Request.
         * <p>
         * The usage of this method is mandatory to the means of the instantiation.
         * <p>
         * Notice that the real instantiation and consequent setting of the field won't
         * happen until the call of the {@code build()} method from this same class
         * 
         * @param lastUpdate
         *            a {@link Date} representing the moment when this Request received
         *            the last update.
         * @return the instance of this same Builder, to achieve fluence.
         */
        public Builder lastUpdate(final Date lastUpdate) {
            this.lastUpdateBuilder = lastUpdate;
            return this;
        }

        /**
         * Initialize the last committer field of a Request.
         * <p>
         * The usage of this method is mandatory to the means of the instantiation.
         * <p>
         * Notice that the real instantiation and consequent setting of the field won't
         * happen until the call of the {@code build()} method from this same class
         * 
         * @param lastCommitter
         *            a {@link TrashwareWorker} representing the worker who gave tha
         *            last update to the Request
         * @return the instance of this same Builder, to achieve fluence.
         */
        public Builder lastCommitter(final TrashwareWorker lastCommitter) {
            this.lastCommitterBuilder = lastCommitter;
            return this;
        }

        /**
         * Initialize the referee field of a Request.
         * <p>
         * The usage of this method is optional to the means of the instantiation.
         * <p>
         * Notice that the real instantiation and consequent setting of the field won't
         * happen until the call of the {@code build()} method from this same class
         * <p>
         * It is <b>mandatory</b> to understand that being the Person interface part of
         * the {@link it.trashwarecesena.trustalodesktopclient.model.Identifiable
         * Identifiable} family, strict rules do exists about the flow of the
         * information. The Identifiable page of this Javadoc expresses all the required
         * rules.
         * 
         * @param referee
         *            a {@link Person} to be the referee for this Request.
         * @return the instance of this same Builder, to achieve fluence.
         * @see {@link it.trashwarecesena.trustalodesktopclient.model.Identifiable
         *      Identifiable}
         */
        public Builder referee(final Person referee) {
            this.refereeBuilder = referee;
            return this;
        }

        /**
         * Initialize the signer field of a Request.
         * <p>
         * The usage of this method is optional to the means of the instantiation.
         * <p>
         * Notice that the real instantiation and consequent setting of the field won't
         * happen until the call of the {@code build()} method from this same class
         * <p>
         * It is <b>mandatory</b> to understand that being the Person interface part of
         * the {@link it.trashwarecesena.trustalodesktopclient.model.Identifiable
         * Identifiable} family, strict rules do exists about the flow of the
         * information. The Identifiable page of this Javadoc expresses all the required
         * rules.
         * 
         * @param signer
         *            a {@link Person} to be the signer of this Request.
         * @return the instance of this same Builder, to achieve fluence.
         * @see {@link it.trashwarecesena.trustalodesktopclient.model.Identifiable
         *      Identifiable}
         */
        public Builder signer(final Person signer) {
            this.signerBuilder = signer;
            return this;
        }

        /**
         * Initialize the trello link field of a Request.
         * <p>
         * The usage of this method is optional to the means of the instantiation.
         * <p>
         * Notice that the real instantiation and consequent setting of the field won't
         * happen until the call of the {@code build()} method from this same class
         * 
         * @param trelloLink
         *            a {@link URL} representing the Trello link used for further
         *            organization of the Request
         * @return the instance of this same Builder, to achieve fluence.
         */
        public Builder trelloLink(final URL trelloLink) {
            this.trelloLinkBuilder = trelloLink;
            return this;
        }

        /**
         * Initialize the annotations field of a Request.
         * <p>
         * The usage of this method is optional to the means of the instantiation.
         * <p>
         * Notice that the real instantiation and consequent setting of the field won't
         * happen until the call of the {@code build()} method from this same class
         * 
         * @param annotations
         *            a {@link String} representing any off-the-schema information
         *            regarding this request
         * @return the instance of this same Builder, to achieve fluence.
         */
        public Builder annotations(final String annotations) {
            this.annotationsBuilder = annotations;
            return this;
        }

        /**
         * Initialize the priority field of a Request.
         * <p>
         * The usage of this method is mandatory to the means of the instantiation.
         * <p>
         * Notice that the real instantiation and consequent setting of the field won't
         * happen until the call of the {@code build()} method from this same class
         * 
         * @param priority
         *            a {@link Integer} representing the priority of the Request
         * @return the instance of this same Builder, to achieve fluence.
         */
        public Builder priority(final Integer priority) {
            this.priorityBuilder = priority;
            return this;
        }

        /**
         * Instantiate and return a Request with the dynamically set values.
         * 
         * @return a fully instantiated Request
         * @throws NullPointerException
         *             if any of the mandatory parameter is {@code null}
         * @throws IllegalArgumentException
         *             if any of the string related parameters are found to be
         *             <i>empty</i>
         * @see {@link it.trashwarecesena.trustalodesktopclient.utils.ExtendedObjects#requireNonEmpty(String)
         *      ExtendedObjects.requireNonEmpty(String, String)}
         */
        public RequestImpl build() {
            return new RequestImpl(identifierBuilder, applicantBuilder, creationDateBuilder, progressBuilder, 
                    lastUpdateBuilder, lastCommitterBuilder, refereeBuilder, signerBuilder, trelloLinkBuilder,
                    annotationsBuilder, priorityBuilder);
        }
    }

}
