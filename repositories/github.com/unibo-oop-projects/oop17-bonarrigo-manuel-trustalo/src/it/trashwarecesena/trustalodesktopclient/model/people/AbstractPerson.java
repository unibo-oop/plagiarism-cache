package it.trashwarecesena.trustalodesktopclient.model.people;

import java.util.Objects;
import java.util.Optional;

import it.trashwarecesena.trustalodesktopclient.utils.ErrorString;
import it.trashwarecesena.trustalodesktopclient.utils.ExtendedObjects;

/**
 * This class provides the basic implementation needed by a {@link Person} of any kind.
 * <p>
 * It is <b>mandatory</b> to understand that being the AbstractPerson class part of the 
 * {@link it.trashwarecesena.trustalodesktopclient.model.Identifiable Identifiable} family, strict rules do exists about
 * the flow of the information.
 * 
 * @author Manuel Bonarrigo
 * @see {@link it.trashwarecesena.trustalodesktopclient.model.Identifiable
 *      Identifiable}
 *
 */
public abstract class AbstractPerson implements Person {

    private final Optional<Integer> identifier;
    private final String name;
    private final PersonCategory category;
    private final Optional<String> fiscalCode;
    private final Optional<String> annotations;

    /**
     * 
     * It is <b>mandatory</b> to understand that being the AbstractPerson class part of the 
     * {@link it.trashwarecesena.trustalodesktopclient.model.Identifiable Identifiable} family, strict rules do exists 
     * about the flow of the information. The Identifiable page of this Javadoc expresses all the required rules.
     * 
     * @param identifier
     *            the number which identifies an instance of Person inside the application. Please refer to the
     *            {@link it.trashwarecesena.trustalodesktopclient.model.Identifiable Identifiable} interface 
     *            documentation to understand what values are the most appropriate in which situation.
     * @param category
     *            the {@link PersonCategory} this person belongs to. Mandatory parameter.
     * @param name
     *            the full name which the person you are creating is used to be referred with. Mandatory parameter.
     * @param fiscalCode
     *            the legal code of identification of the state the {@link Person} come from. Can be {@code null} if 
     *            unknown.
     * @param annotations
     *            any sort of out-of-the-schema information needed to be stored in relation to this {@link Person}. 
     *            Can be {@code null} if unknown.
     * @throws NullPointerException
     *             if the mandatory arguments name and category are {@code null}
     * @throws IllegalArgumentException
     *             if any of the string related parameters are found to be different from null and <i>empty</i>, or the
     *             identifier parameter is not greater than zero, if set.
     * @see {@link it.trashwarecesena.trustalodesktopclient.model.Identifiable
     *      Identifiable}
     * @see {@link it.trashwarecesena.trustalodesktopclient.utils.ExtendedObjects#requireNonEmpty(String)
     *      ExtendedObjects.requireNonEmpty(String, String)}
     * @see {@link it.trashwarecesena.trustalodesktopclient.utils.ExtendedObjects#requireNonEmpty(String)
     *      ExtendedObjects.requirePositive(Integer, String)}
     */
    public AbstractPerson(final Integer identifier, final PersonCategory category, final String name,
            final String fiscalCode, final String annotations) {
        this.name = ExtendedObjects.requireNonEmpty(
                                                    Objects.requireNonNull(name, ErrorString.STRING_NULL),
                                                    ErrorString.EMPTY_STRING);
        this.category = Objects.requireNonNull(category, "The PersonCategory" + ErrorString.CUSTOM_NULL);
        this.identifier = Optional.ofNullable(ExtendedObjects.requirePositive(
                                                                        identifier, 
                                                                        "The identifier" + ErrorString.ONLY_POSITIVE));
        this.fiscalCode = Optional.ofNullable(ExtendedObjects.requireNonEmpty(fiscalCode, ErrorString.EMPTY_STRING));
        this.annotations = Optional.ofNullable(ExtendedObjects.requireNonEmpty(annotations, ErrorString.EMPTY_STRING));
    }

    @Override
    public final Optional<Integer> getNumericIdentifier() {
        return this.identifier;
    }

    @Override
    public final PersonCategory getCategory() {
        return this.category;
    }

    @Override
    public final String getName() {
        return this.name;
    }

    @Override
    public final Optional<String> getFiscalCode() {
        return this.fiscalCode;
    }

    @Override
    public final Optional<String> getAnnotations() {
        return this.annotations;
    }

}
