/**
 *@author Diego
 *
 * Class for setting move directions
 *
 */
package controller;

public enum MoveDirection {
    UP, RIGHT, DOWN, LEFT, TURBO;

	/**
	 *
	 * Method that read the next move direction
	 *
	 */

    public MoveDirection next() {
        int index = ordinal() + 1;

        if (index == values().length) {
            index = 0;
        }

        return values()[index];
    }

    /**
	 *
	 * Method that read the previous move direction
	 *
	 */

    public MoveDirection prev() {
        int index = ordinal() - 1;

        if (index == -1) {
            index = values().length - 1;
        }

        return values()[index];
    }
}
