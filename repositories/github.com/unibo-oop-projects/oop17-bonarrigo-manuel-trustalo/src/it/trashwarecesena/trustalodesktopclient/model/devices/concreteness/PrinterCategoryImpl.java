package it.trashwarecesena.trustalodesktopclient.model.devices.concreteness;

import java.util.Objects;

import it.trashwarecesena.trustalodesktopclient.model.devices.PrinterCategory;
import it.trashwarecesena.trustalodesktopclient.utils.ErrorString;
import it.trashwarecesena.trustalodesktopclient.utils.ExtendedObjects;

/**
 * An implementation of the {@link PrinterCategory} interface.
 * 
 * @author Manuel Bonarrigo
 *
 */
public final class PrinterCategoryImpl implements PrinterCategory {

    private final String category;

    /**
     * Constructs a {@link PrinterCategory} over the given technology.
     * 
     * @param category
     *            a {@link String} expressing the technology used by a
     *            {@link it.trashwarecesena.trustalodesktopclient.model.devices.Printer Printer}.
     * @throws NullPointerException
     *             if the category is found to be {@code null}
     * @throws IllegalArgumentException
     *             if the category is found to be <i>empty</i>
     * @see {@link it.trashwarecesena.trustalodesktopclient.utils.ExtendedObjects#requireNonEmpty(String)
     *      ExtendedObjects.requireNonEmpty(String, String)}
     */ 
    public PrinterCategoryImpl(final String category) {
        super();
        this.category = ExtendedObjects.requireNonEmpty(Objects.requireNonNull(category, ErrorString.STRING_NULL),
                ErrorString.EMPTY_STRING);
    }

    @Override
    public String getName() {
        return this.category;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((category == null) ? 0 : category.hashCode());
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
        final PrinterCategoryImpl other = (PrinterCategoryImpl) obj;
        if (category == null) {
            if (other.category != null) {
                return false;
            }
        } else if (!category.equals(other.category)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "PrinterCategoryImpl [category=" + category + "]";
    }

}
