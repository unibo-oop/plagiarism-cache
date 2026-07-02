package clashclass.saveload;

import clashclass.village.Village;

/**
 * Interface for encoding Village objects to CSV String format.
 */
public interface VillageEncoder {
    /**
     * Encodes a Village object into a CSV String representation.
     *
     * @param village the village to encode
     *
     * @return the encoded CSV String representation
     */
    String encode(Village village);

    /**
     * Gets the header of the file.
     *
     * @return the header of the file
     */
    String getHeader();
}

