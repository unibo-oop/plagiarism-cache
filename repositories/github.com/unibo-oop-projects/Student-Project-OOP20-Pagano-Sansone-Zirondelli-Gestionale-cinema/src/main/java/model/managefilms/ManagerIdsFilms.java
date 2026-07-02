package model.managefilms;

import java.util.Collection;
import java.util.Optional;

public interface ManagerIdsFilms {
    /** 
     * Get new film id.
     * @return film id
     * 
     * */
    int getNewFilmID();
    /** 
     * Get used films ids.
     * @return collection of used ids
     * */
    Collection<Integer> getUsedIDs();
    /** 
     * Get last generated id.
     * @return optional generated id
     * */
    Optional<Integer> getLastGeneratedId();
    /** 
     * Remove film id from container.
     * @param idToDelete id to delete
     * 
     * */
    void removeFilmId(int idToDelete);
}


