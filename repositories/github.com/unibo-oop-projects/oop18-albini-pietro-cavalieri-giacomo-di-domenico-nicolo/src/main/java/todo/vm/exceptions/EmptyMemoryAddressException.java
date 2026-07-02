package todo.vm.exceptions;

public class EmptyMemoryAddressException extends VmRuntimeException {
    private static final long serialVersionUID = -2930357338277318560L;

    @Override
    public String toString() {
        return "Empty memory address!";
    }
}
