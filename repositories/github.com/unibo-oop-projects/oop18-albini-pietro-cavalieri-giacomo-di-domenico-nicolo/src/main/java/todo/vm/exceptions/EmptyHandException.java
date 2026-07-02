package todo.vm.exceptions;

public class EmptyHandException extends VmRuntimeException {
    private static final long serialVersionUID = -8120785508084093955L;

    @Override
    public String toString() {
        return "There is nothing in the hand!";
    }
}
