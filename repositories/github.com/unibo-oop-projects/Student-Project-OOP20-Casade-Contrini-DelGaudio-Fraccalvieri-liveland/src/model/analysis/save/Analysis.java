package model.analysis.save;

import java.util.List;

/**
 * Interface showing queries the simulation must answer in the end.
 */

public interface Analysis {

    /**
     * @return the textual representation of fair liking
     */
    List<String> fairLiking();

    /**
     * @return the textual analysis showing profits of each profitable activity
     */
    List<String> profit();

    /**
     * @return the textual representation of income coming from tickets sale,
     * divided into different type of tickets (adult, reduced, pass)
     */
    List<String> tickets();

    /**
     * @return the concatenation of the previous analysis items
     */
    List<String> getTextualAnalysis();

    /**
     * @return a brief description of the analysis' implementation
     */
    String getAnalysisDescription();

}
