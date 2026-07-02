package view.menu.fair;

public interface ActivityGui {

    /**
     * This method calls the constructor while showing a new dialog to add a new activity.
     */
    void display();

    /**
     * It reverts the text fields in the dialog to initial state. It is called after an exception 
     * either caused by a wrong insertion of the parameters needed, {@link WrongParametersException} 
     * or fields left blank.
     */
    void reset();

}
