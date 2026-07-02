package it.trashwarecesena.trustalodesktopclient.utils;

import java.util.Objects;

/**
 * An utility class aimed to reduce redundancy in error management messages.
 * 
 * @author Manuel Bonarrigo
 *
 */

public final class ErrorString {

    /**
     * The last part of a message explaining that something can't be null. An
     * initial space is already included.
     */
    public static final String NO_NULL = " can't be null.";

    /**
     * A message about a custom object not allowed to be null. An initial space is
     * already included.
     */
    public static final String CUSTOM_NULL = " reference" + NO_NULL;

    /**
     * A message about a {@link String} which can't be null.
     */
    public static final String STRING_NULL = "String" + CUSTOM_NULL;

    /**
     * The last part of a message explaining that something can't be empty. An
     * initial space is already included.
     */
    public static final String NO_EMPTY = " can't be empty.";

    /**
     * A message about a {@link String} which can't be empty.
     */
    public static final String EMPTY_STRING = "String" + NO_EMPTY;

    /**
     * The last part of a message explaining that something can't be negative. An
     * initial space is already included.
     */
    public static final String NO_NEGATIVE = " can't be negative.";

    /**
     * The last part of a message explaining that something can only be greater than zero. An
     * initial space is already included.
     */
    public static final String ONLY_POSITIVE = " can only be positive.";

    /**
     * A message to be used when an impossible state of the application is indeed being raised.
     */
    public static final String BUG_REPORTING = "This should have not be possible. Please report this bug to the"
                                                + " developers along with a copy of the stack trace";

    private ErrorString() {
    }

    /**
     * A simple builder for any {@link NonExistentReferenceException} message.
     * @param klass which class was being queried.
     * @param field which field was found to be non existent.
     * @return a String with a message explaining what failed.
     */
    public static String buildMissingReferenceExceptionMessage(final Class<?> klass, final String field) {
        return "\n The value provided for the " + ExtendedObjects.requireNonEmpty(Objects.requireNonNull(field)) 
                + " field of the " + Objects.requireNonNull(klass).getSimpleName() + " class was not found in any "
                + "persistence storage"; 
    }

    /**
     * A simple builder for any {@link DuplicateKeyValueException} message.
     * @param causeValue which value was found to be duplicated
     * @param causePlace which Class is responsible for holding the value
     * @return a String with a message explaining what failed.
     */
    public static String buildDuplicateKeyValueExceptionMessage(final Class<?> causePlace, final String causeValue) {
        return "\n The value " + ExtendedObjects.requireNonEmpty(Objects.requireNonNull(causeValue)) + " from the " 
                + causePlace.getSimpleName() + " class is already present in the corrispective persistence storage, "
                        + "and its duplication is forbidden";
    }
}
