package Controller;

/**
 * @author Massimiliano Micca
 *
 */

public interface SaveControllerInterface {

    /**
     * this method is for restore the default data never used.
     */
    void reset();

    /**
     * save a ObjToSave in a file
     * 
     * @param obj
     */
    void save(ObjToSave obj);

    /**
     * get ObjToSave from file
     * 
     * @return
     */
    ObjToSave getObjToSave();

}