package controller;

import view.ToDoView;
/**
 * 
 *to do controller interface.
 *
 */
public interface ToDoController {
    /**
     * 
     * @return toDoView
     * to do view
     */
    ToDoView getToDoView();
    /**
     * 
     * @param description
     * description
     * @param check
     * check
     */
    void confirmToDo(String description, boolean check);
    /**
     * 
     * @param description
     * description
     * @param check
     * check
     */
    void modifyToDo(String description, boolean check);
    /**
     * 
     * @param description
     * description
     */
    void removeToDo(String description);
    /**
     * 
     * @param index
     * index
     */
    void selectToDo(int index);
}
