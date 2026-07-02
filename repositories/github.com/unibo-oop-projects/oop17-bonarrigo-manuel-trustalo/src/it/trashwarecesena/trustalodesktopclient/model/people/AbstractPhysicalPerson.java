package it.trashwarecesena.trustalodesktopclient.model.people;

import java.sql.Date;
import java.util.Optional;

import it.trashwarecesena.trustalodesktopclient.utils.ErrorString;
import it.trashwarecesena.trustalodesktopclient.utils.ExtendedObjects;

/**
 * An abstraction refining the behaviour of an {@link AbstractPerson} to meet the needs of a {@link PhysicalPerson}. 
 * Although this class already provides all the implementation needed by the interfaces it adheres to, there is space
 * for extension, to make the concrete implementor choose his way to initialize all the fields.
 * <p>
 * It is <b>mandatory</b> to understand that being the AbstractPhysicalPerson class part of the
 * {@link it.trashwarecesena.trustalodesktopclient.model.Identifiable Identifiable} family, strict rules do exists about
 * the flow of the information.
 * 
 * @author Manuel Bonarrigo
 *
 */
public abstract class AbstractPhysicalPerson extends AbstractPerson implements PhysicalPerson {

    private final Optional<Date> birthDate;
    private final Optional<String> birthLocation;

    /**
     * 
     * It is <b>mandatory</b> to understand that being the AbstractPhysicalPerson class part of the
     * {@link it.trashwarecesena.trustalodesktopclient.model.Identifiable Identifiable} family, strict rules do exists 
     * about the flow of the information.
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
     * @param birthDate
     *            a {@link Date} holding the date of birth of the person. Can be {@code null} if unknown.
     * @param birthLocation
     *            a {@link String} holding the closest place the person was comfortable to disclose about where he or 
     *            she was born. Can be {@code null} if unknown.
     * @throws NullPointerException
     *             if the mandatory arguments name and category are {@code null}
     * @throws IllegalArgumentException
     *             if any of the string related parameters are found to be different from null and <i>empty</i>, 
     *             or if the identifier parameter has not a value greater than zero
     * @see {@link it.trashwarecesena.trustalodesktopclient.model.Identifiable
     *      Identifiable}
     * @see {@link it.trashwarecesena.trustalodesktopclient.utils.ExtendedObjects#requireNonEmpty(String)
     *      ExtendedObjects.requireNonEmpty(String, String)}
     *@see {@link it.trashwarecesena.trustalodesktopclient.utils.ExtendedObjects#requireNonEmpty(String)
     *      ExtendedObjects.requirePositive(Integer, String)}
     */
    public AbstractPhysicalPerson(final Integer identifier, final String name, final PersonCategory category,
            final String fiscalCode, final Date birthDate, final String birthLocation, final String annotations) {
        super(identifier, category, name, fiscalCode, annotations);
        this.birthDate = Optional.ofNullable(birthDate);
        this.birthLocation = Optional
                .ofNullable(ExtendedObjects.requireNonEmpty(birthLocation, ErrorString.EMPTY_STRING));
    }

    @Override
    public final Optional<Date> getBirthDate() {
        return this.birthDate;
    }

    @Override
    public final Optional<String> getBirthLocation() {
        return this.birthLocation;
    }

}
