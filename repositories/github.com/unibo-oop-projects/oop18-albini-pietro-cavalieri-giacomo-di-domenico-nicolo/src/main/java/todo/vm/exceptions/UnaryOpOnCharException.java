package todo.vm.exceptions;

public class UnaryOpOnCharException extends VmRuntimeException {
    private static final long serialVersionUID = -7019468742389139400L;

    @Override
    public String toString() {
        return "Can't execute unary operations on a char!";
    }
}
