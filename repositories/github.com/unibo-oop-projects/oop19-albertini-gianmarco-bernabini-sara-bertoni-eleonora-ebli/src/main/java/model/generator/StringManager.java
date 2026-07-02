package model.generator;

import java.util.Set;

public interface StringManager {
    /**
     * Method to choose the words appearing in the wave depending on the rules
     * chosen.
     * 
     * @param nShortWord
     *                       number of short words to spawn
     * @param nLongWord
     *                       number of long words to spawn
     * @return all the words
     */
    Set<String> getWords(int nShortWord, int nLongWord);

    /**
     * Method to get the max limit of words that can be potentially spawned in the
     * same wave due to the rules chosen.
     * 
     * @return 
     *          the max number of words
     */
    int getMaxNWord();

}
