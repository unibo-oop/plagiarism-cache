package hotelmaster.reservations;

import hotelmaster.structure.Hotel;

/**
 * A type of document in a {@link Hotel}.
 */
public class DocumentType {

    private final String description;
    private final int fieldLength;

    /**
     * Creates a new {@link DocumentType} with the given description and field
     * length.
     * 
     * @param description
     *            a string description of this {@link DocumentType}
     * @param fieldLength
     *            the required amount of characters for this
     *            {@link DocumentType}
     * @throws IllegalArgumentException
     *             the given arguments are not valid
     */
    public DocumentType(final String description, final int fieldLength) throws IllegalArgumentException {
        if (description.isEmpty() || fieldLength < 1) {
            throw new IllegalArgumentException("Invalid arguments for DocumentType");
        }
        this.description = description;
        this.fieldLength = fieldLength;
    }

    /**
     * Returns the required amount of characters for this type of
     * {@link DocumentType}.
     * 
     * @return the amount of characters
     */
    public int getFieldLength() {
        return this.fieldLength;
    }

    /**
     * Returns the description of the {@link DocumentType}.
     * 
     * @return the description string
     */
    public String getDescription() {
        return this.description;
    }

    @Override
    public String toString() {
        return new StringBuilder().append(this.description).append(" (max. ").append(this.fieldLength).append(")")
                .toString();
    }
}
