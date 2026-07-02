package model.generator;

public interface WordGenerator {

    /**
     * This method is used for creating the wave of words to defeat.
     * 
     * @param minX
     *                 the minimum value that can be assumed by the x
     * @param maxX
     *                 the maximum value that can be assumed by the x
     * @param minY
     *                 the value that is assumed by y
     */
    void spawnWords(double minX, double maxX, double minY);

}
