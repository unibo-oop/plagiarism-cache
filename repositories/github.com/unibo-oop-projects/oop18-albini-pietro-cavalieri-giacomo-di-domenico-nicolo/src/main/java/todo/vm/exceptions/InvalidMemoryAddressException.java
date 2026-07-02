package todo.vm.exceptions;

public class InvalidMemoryAddressException extends VmRuntimeException {
    private static final long serialVersionUID = -7835792176734995964L;

    @Override
    public String toString() {
        return "The memory address doesn't exist!";
    }
}
