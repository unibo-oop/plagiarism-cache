package model.util.history;

/**
 * Represents a the contract for a generic history.
 */
public interface History {

    /**
     * @return previous component of history
     */
    Component getPrevious();

    /**
     * @return current component of history
     */
    Component getCurrent();

    /**
     * @return actual index of position in history
     */
    int getCurrentIndex();

    /**
     * @return next component of history
     */
    Component getNext();

    /**
     * @return the first component of history
     */
    Component getOriginal();

    /**
     * @return the last component of history (image to export)
     */
    Component getLast();

    /**
     * @param change is the component that will be add in history. sometimes
     *               addChange makes use of removeComponent.
     * @return History of project
     */
    History addChange(Component change);

    /**
     * @return name of history
     */
    String getNameHistory();

    /**
     * @param nameOfHistory to modified
     * @return history
     */
    History setNameHistory(String nameOfHistory);

    /**
     * @return save status
     */
    Boolean getStatusSave();

    /**
     * @param status : save status
     * @return History of project
     */
    History setStatusSave(Boolean status);

    /**
     * @return if component is not last.
     */
    Boolean livesInThePast();
}
