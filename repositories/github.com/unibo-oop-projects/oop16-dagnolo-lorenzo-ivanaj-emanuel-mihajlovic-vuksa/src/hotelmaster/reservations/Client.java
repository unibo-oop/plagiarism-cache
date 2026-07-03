package hotelmaster.reservations;

import hotelmaster.structure.HotelEntity;

/**
 * The ID of a client responsible for a stay.
 */
public interface Client extends Comparable<Client>, HotelEntity {

    /**
     * Returns the nominative given by the {@link Client}.
     * 
     * @return the nominative
     */
    String getNominative();

    /**
     * Returns the residence country of the {@link Client}.
     * 
     * @return the country
     */
    String getCountry();

    /**
     * Returns the type of the document that the {@link Client} used to register
     * the stay.
     * 
     * @return this {@link Client}'s document's type
     */
    DocumentType getDocumentType();

    /**
     * Returns the document of this {@link Client}.
     * 
     * @return this {@link Client}'s document
     */
    String getDocument();

    /**
     * Returns the {@link Client}'s phone number.
     * 
     * @return this {@link Client}'s phone number
     */
    String getPhoneNumber();

    /**
     * Creates a new {@link Client} instance from the given parameters.
     * 
     * @param nominative
     *            the nominative
     * @param country
     *            the residence country of the {@link Client}
     * @param document
     *            the document string
     * @param documentType
     *            the document type
     * @param phoneNumber
     *            the phone number of the {@link Client}
     * 
     * @return the instanced {@link Client}
     * @throws IllegalArgumentException
     *             there must be no empty fields, and the document string's
     *             length must be the same as the documentType.getFieldLength()
     *             value
     */
    @SuppressWarnings("PMD.UseObjectForClearerAPI") // this is the containing
                                                    // object.
    static Client create(final String nominative, final String country, final DocumentType documentType,
            final String document, final String phoneNumber) throws IllegalArgumentException {
        return new ClientImpl(nominative, country, documentType, document, phoneNumber);
    }
}
