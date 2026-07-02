package iuniversity.model;

import java.util.Optional;

import iuniversity.model.didactics.DidacticsManager;
import iuniversity.model.exams.ExamsManager;
import iuniversity.model.user.Archive;
import iuniversity.model.user.User;

public interface Model {

    /**
     * 
     * @return an optional which is full only if a user is logged
     */
    Optional<User> getLoggedUser();

    /**
     * 
     * @param user the user to be set as current user
     */
    void setCurrentUser(User user);

    /**
     * Unsets the current user.
     */
    void unsetCurrentUser();

    /**
     * 
     * @return the exam manager
     */
    ExamsManager getExamManager();

    /**
     * 
     * @return the didactic manager
     */
    DidacticsManager getDidacticsManager();

    /**
     * 
     * @return the archive manager
     */
    Archive getArchive();
}
