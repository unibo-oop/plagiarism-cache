package controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import model.Task;
import model.TaskImpl;
import view.ToDoView;
import view.ToDoViewImpl;
/**
 * 
 * to do controller.
 *
 */
public final class ToDoControllerImpl implements ToDoController {
    private ToDoViewImpl toDoView;
    private Optional<Task> selectedTask;
    private int ind;
    private static final ToDoController SINGLETON = new ToDoControllerImpl();
    /**
     * update view.
     */
    public void update() {
        this.toDoView = new ToDoViewImpl(this, this.getToDoTable());
        this.toDoView.init();
    }
    private ToDoControllerImpl() {
        this.update();
    }
    /**
     * @param description
     * description
     * @param check
     * check
     */
    public void confirmToDo(final String description, final boolean check) {
        MainControllerImpl.manager.currentUser().getTodo().addTask(new TaskImpl(description));
        this.toDoView.rebuildTable(this.getToDoTable());
    }
    /**
     * @return SINGLETON
     * singleton
     */
    public static ToDoController getInstance() {
        return SINGLETON;
    }
    /**
     * @return toDoView
     * to do view
     */
    @Override
    public ToDoView getToDoView() {
        return this.toDoView;
    }
    /**
     * 
     * @return toDoTable
     * to do table
     */
    public Object[][] getToDoTable() {
        final List<Task> list = MainControllerImpl.manager.currentUser().getTodo().getTodo();
        final Object[][] toDoTable = new Object[list.size()][ToDoViewImpl.getToDoTableTitles().length];
        for (int i = 0; i < list.size(); i++) {
                 final Task td = list.get(i);
                 toDoTable[i][0] = td.getDescription();
                 if (td.isCompleted()) {
                     toDoTable[i][1] = "Completed";
                 } else {
                     toDoTable[i][1] = "Incompleted";
                 }
         }
         return toDoTable;
    }
    @Override
    public void modifyToDo(final String description, final boolean check) {
        this.checkIfSelection();
        if (check) {
            this.selectedTask.ifPresent(g -> {
                MainControllerImpl.manager.currentUser().getTodo().setTaskComplete(this.ind);
        });
        } else {
            this.selectedTask.ifPresent(g -> {
                MainControllerImpl.manager.currentUser().getTodo().setTaskIncomplete(this.ind);
        });
        }
        this.toDoView.rebuildTable(this.getToDoTable());
    }
    @Override
    public void removeToDo(final String description) {
        this.checkIfSelection();
        this.selectedTask.ifPresent(g -> {
            MainControllerImpl.manager.currentUser().getTodo().removeTask(g);
            this.selectedTask = Optional.empty();
            this.toDoView.rebuildTable(this.getToDoTable());
            this.toDoView.showMessage("ToDo successfully removed", this.toDoView);
        });
    }
    /**
     * 
     */
    private void checkIfSelection() {
        if (!this.selectedTask.isPresent()) {
            this.toDoView.showMessage("No to do selected", this.toDoView);
        }
    }
    /**
     * 
     * @return collect
     * collect
     */
    public List<Task> getAllTasks() {
        return MainControllerImpl.manager.currentUser().getTodo().getTodo().stream()
                .sorted((g1, g2) -> g1.getDescription().compareTo(g2.getDescription()))
                .collect(Collectors.toList());
    }
    /**
     * 
     * @param index
     * index
     */
    public void selectToDo(final int index) {
        this.ind = index;
        this.selectedTask = Optional.of(this.getAllTasks().get(index));
    }
}
