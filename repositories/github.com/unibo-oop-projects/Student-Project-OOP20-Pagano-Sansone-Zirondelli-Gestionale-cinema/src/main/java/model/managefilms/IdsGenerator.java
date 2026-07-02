package model.managefilms;

import java.util.Optional;
/** 
 * Ids Generator generates new ids.
 * */
public interface IdsGenerator {
    /** 
     * Get new id.
     * @return id 
     * */
    int getNewId();
    /** 
     * Get last generated id.
     * @return optional integer with last generated id
     * */
    Optional<Integer> getLastGeneratedId();

}
