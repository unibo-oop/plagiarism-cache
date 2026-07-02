package it.trashwarecesena.trustalodesktopclient.model.devices.concreteness;

import java.util.Objects;

import it.trashwarecesena.trustalodesktopclient.model.devices.Color;
import it.trashwarecesena.trustalodesktopclient.utils.ErrorString;

/**
 * A implementation of the {@link Color} interface.
 * 
 * @author Manuel Bonarrigo
 *
 */
public final class ColorImpl implements Color {
    private static final Integer MAXIMUM_HEXADECIMAL_LENGTH = 6;
    private final String color;

    /**
     * Constructs a Color upon the given value.
     * 
     * @param hexadecimalColor
     *            a String representing the wanted color.
     * @throws NullPointerException
     *             if the given String is found to be {@code null}.
     * @throws IllegalArgumentException
     *             if the string is not exactly six valid characters long.
     * @throws NumberFormatException
     *             if the String does not represent a valid nmber for a six digits
     *             hexadecimal number
     */
    public ColorImpl(final String hexadecimalColor) {
        if (Objects.requireNonNull(hexadecimalColor, ErrorString.STRING_NULL).length() != MAXIMUM_HEXADECIMAL_LENGTH) {
            throw new IllegalArgumentException(
                    "The only accepted value length is of " + MAXIMUM_HEXADECIMAL_LENGTH + "characters");
        }
        this.color = normalizeHexadecimalColor(Integer.toHexString(Integer.decode("0x" + hexadecimalColor)));
    }

    private String normalizeHexadecimalColor(final String hexString) {
        String zeros = "";
        for (int i = 0; i < MAXIMUM_HEXADECIMAL_LENGTH - hexString.length(); i++) {
            zeros += "0";
        }
        return zeros + hexString;
    }

    @Override
    public String getColor() {
        return this.color;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((color == null) ? 0 : color.hashCode());
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
        final ColorImpl other = (ColorImpl) obj;
        if (color == null) {
            if (other.color != null) {
                return false;
            }
        } else if (!color.equals(other.color)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ColorImpl [color=" + color + "]";
    }

}
