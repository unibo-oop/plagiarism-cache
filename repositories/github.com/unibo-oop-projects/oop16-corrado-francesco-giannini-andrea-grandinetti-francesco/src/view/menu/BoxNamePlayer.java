package view.menu;

import utility.Driver;
import utility.TyreType;

/**
 * Interface for class of view where user insert data.
 *
 */
public interface BoxNamePlayer {

    /**
     * Getter.
     * @return name inserted by user
     */
    String getName();

    /**
     * Getter.
     * @return driver selected by user
     */
    Driver getDriver();

    /**
     * Getter.
     * @return type of tyre for qualifying and race selected by user
     */
    TyreType getTyre();

}