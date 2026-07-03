package com.geoquiz.controller.ranking;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

import com.geoquiz.utility.Pair;

 /**
 * This interface models the concept of ranking.
 *
 */
public interface Ranking {

    /** 
     * Gets the instance of the Ranking.
     * @return
     *      the instance of the Ranking.
     */
    static Ranking getInstance() {
        return RankingImpl.getInstance();
    }
     /**
      * This method gets the personal ranking of a user.
      * 
      * @param username
      *            the username of the user whose personal ranking you want to
      *            retrieve.
      * @return a Map<Pair<String, String>, Integer> with user's personal ranking
      *         info.
      * @throws FileNotFoundException
      *                 if something goes wrong reading from file
      * @throws ClassNotFoundException
      *                 if something goes wrong reading from file
      * @throws IOException
      *                 if something goes wrong reading from file
      */
     Map<Pair<String, String>, Integer> getPersonalRanking(String username)
             throws FileNotFoundException, ClassNotFoundException, IOException;

     /**
      * This method gets the global ranking.
      * 
      * @return Map<Pair<String, String>, Pair<String, Integer>> indicating the
      *         global ranking
      * @throws FileNotFoundException
      *                     if something goes wrong reading from file
      * @throws ClassNotFoundException
      *                     if something goes wrong reading from file
      * @throws IOException
      *                 if something goes wrong reading from file
      */
     Map<Pair<String, String>, Pair<String, Integer>> getGlobalRanking()
             throws FileNotFoundException, ClassNotFoundException, IOException;

     /**
      * @return true if ranking file exists, false otherwise.
      */
     boolean isRankingExisting();

     /**
      * This method saves the current quiz results.
      * 
      * @param username
      *            the username of the user whose personal scores you want to update
      * @param mode
      *            the mode to which the score is about
      * @param value
      *            the score got in this quiz
      * @throws FileNotFoundException
      *                         if something goes wrong reading from file
      * @throws ClassNotFoundException
      *                         if something goes wrong reading from file
      * @throws IOException
      *                 if something goes wrong reading from file
      */
     void save(String username, Pair<String, String> mode, Integer value)
             throws FileNotFoundException, ClassNotFoundException, IOException;

     /**This method indicates whether the user ha a personal ranking saved or not.
      * @param username the username to be checked for existence in the ranking.
     * @return true if the user ha a personal ranking saved, false otherwise.
     */
     boolean isUserExisting(String username);
}