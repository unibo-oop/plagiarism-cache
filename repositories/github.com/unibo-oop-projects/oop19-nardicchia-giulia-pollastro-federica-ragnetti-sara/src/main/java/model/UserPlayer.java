package model;

import model.score.Progress;
import model.score.ProgressImpl;

/**
 * Represent a Player in the software domain.
 * 
 */
public interface UserPlayer extends User {

    /**
     * The user's progress.
     * 
     * @return
     *          progress
     */
    Progress getProgress();

    /**
     * Sets user progress at the registration.
     * 
     * @param progress
     *          the initial progress
     */
    void setProgress(ProgressImpl progress);

}
