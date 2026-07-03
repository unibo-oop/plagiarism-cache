package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * This class represent the user's to do.
 */

public class ToDoImpl implements ToDo {
    private static final long serialVersionUID = 7394463841452745084L;

    private final List<Task> todo = new ArrayList<>();

    @Override
    public void addTask(final Task task) throws IllegalArgumentException {
        Objects.requireNonNull(task);
        if (this.todo.contains(task)) {
            throw new IllegalArgumentException("Task already saved in to do");
        }
        this.todo.add(task);
    }

    @Override
    public void removeTask(final Task task) throws IllegalArgumentException {
        if (!this.todo.remove(task)) {
            throw new IllegalArgumentException("Task not registered in to do");
        }
    }

    @Override
    public void setTaskComplete(final int index) throws IllegalArgumentException {
        try {
            this.checkIndex(index);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Unable to set task completed", e);
        }
        todo.get(index).setCompleted(true);
    }

    @Override
    public void setTaskIncomplete(final int index) throws IllegalArgumentException {
        try {
            this.checkIndex(index);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Unable to set task not completed", e);
        }
        todo.get(index).setCompleted(false);
    }

    @Override
    public List<Task> getTodo() {
        return todo.stream().collect(Collectors.toList());
    }

    /**
     * Checks if the input index is in the todo list.
     * 
     * @param index
     *            index to check
     * @throws IllegalArgumentException
     *             if the task's index in not in the list
     */
    private void checkIndex(final int index) throws IllegalArgumentException {
        if (todo.size() < index) {
            throw new IllegalArgumentException("The index is not in the list");
        }
    }
}
