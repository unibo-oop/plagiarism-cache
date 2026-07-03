package hotelmaster.reservations;

/**
 * The implementation of the {@link Client} interface.
 */
public class ClientImpl implements Client {

    private final String nominative;
    private final String country;
    private final DocumentType docType;
    private final String document;
    private final String phoneNumber;

    ClientImpl(final String nominative, final String country, final DocumentType docType, final String document,
            final String phoneNumber) throws IllegalArgumentException {
        if (nominative.isEmpty() || country.isEmpty() || docType == null || document.isEmpty()
                || phoneNumber.isEmpty()) {
            throw new IllegalArgumentException("Cannot have empty fields");
        }
        if (document.length() != docType.getFieldLength()) {
            throw new IllegalArgumentException("Document must have the same length as stated in DocumentType");
        }
        this.nominative = nominative;
        this.country = country;
        this.docType = docType;
        this.document = document;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public int compareTo(final Client o) {
        return this.nominative.compareTo(o.getNominative());
    }

    @Override
    public String getCountry() {
        return this.country;
    }

    @Override
    public String getNominative() {
        return this.nominative;
    }

    @Override
    public DocumentType getDocumentType() {
        return this.docType;
    }

    @Override
    public String getDocument() {
        return this.document;
    }

    @Override
    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((docType == null) ? 0 : docType.hashCode());
        result = prime * result + ((document == null) ? 0 : document.hashCode());
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
        if (!(obj instanceof ClientImpl)) {
            return false;
        }
        final ClientImpl other = (ClientImpl) obj;
        if (docType == null) {
            if (other.docType != null) {
                return false;
            }
        } else if (!docType.equals(other.docType)) {
            return false;
        }
        if (document == null) {
            if (other.document != null) {
                return false;
            }
        } else if (!document.equals(other.document)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return new StringBuilder().append(this.nominative).append(" (").append(this.document).append(")").toString();
    }

}
