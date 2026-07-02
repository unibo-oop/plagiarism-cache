package todo.vm.exceptions;

public class InfiniteLoopException extends VmRuntimeException {
    private static final long serialVersionUID = 8160931918073525108L;

    @Override
    public String toString() {
        return "The program is stuck in an infinite loop";
    }
}
