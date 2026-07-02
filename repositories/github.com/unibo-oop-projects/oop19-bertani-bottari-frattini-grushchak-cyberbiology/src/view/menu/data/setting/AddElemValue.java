package view.menu.data.setting;

/**
 * Interface that will be implemented by classes to add graphical elements that extend Components 
 * in which a value will be set that can then be returned.
 * @param <Y> type of value returned
 * 
 */
public interface AddElemValue<Y> extends AddElem {

    /**
     * Calculate the value has been set in each graphic element.
     * @return the value set by the implemented element
     */
    Y getValue();
}
