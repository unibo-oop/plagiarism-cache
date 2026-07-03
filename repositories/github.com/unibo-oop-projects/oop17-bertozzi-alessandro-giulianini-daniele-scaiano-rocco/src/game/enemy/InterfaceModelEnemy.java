package game.enemy;

/**
 * 
 * A class with methods to check the position of the enemies, to delete the coordinates's enemies,
 * to add a number to the coordinate's enemies {@link ModelEnemy}.
 * 
 */
public interface InterfaceModelEnemy {
    /**
     * Method to check the number.
     * @param n int number to check.
     * @return boolean: true if the number exists in the X coordinates's enemies, false otherwise.
     */
    boolean busyX(int n);
    /**
     * Method to check the number.
     * @param n int number to check.
     * @return boolean: true if the number exists in the Y coordinates's enemies, false otherwise.
     */
    boolean busyY(int n);
    /**
     * Method to delete the list of the class. {@link ModelEnemy}
     */
    void deleteList();
    /**
     * Method to add a number in a list.
     * @param list boolean: true if the number is a X coordinate, false otherwise.
     * @param n int number to add.
     */
    void addNumber(boolean list, int n);
}
