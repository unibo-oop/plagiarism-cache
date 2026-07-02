package todo.vm.exceptions;

public class BrokenCodegenException extends VmRuntimeException {
    private static final long serialVersionUID = -688917022479818707L;
    private final String message;

    public BrokenCodegenException(final String message) {
        super();
        this.message = message;
    }

    @Override
    public String toString() {
        return this.message;
    }
}
