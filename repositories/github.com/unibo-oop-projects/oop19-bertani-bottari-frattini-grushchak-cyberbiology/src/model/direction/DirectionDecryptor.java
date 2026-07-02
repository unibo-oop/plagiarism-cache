package model.direction;

/**
 * 
 * An interface for decryption of integers into directions.
 *
 */
public interface DirectionDecryptor {
    /**
     * Transform an integer into direction.
     * @param parameter which must be transformed in a direction.
     * @return a direction that corresponds to the parameter.
     */
    static Direction getDirection(final int parameter) {
        if (parameter < 0) {
            return DirectionEnum.getDirection(((parameter % DirectionEnum.SIZE) + DirectionEnum.SIZE) % DirectionEnum.SIZE);
        }
        return DirectionEnum.getDirection(parameter % DirectionEnum.SIZE);
    }
}
