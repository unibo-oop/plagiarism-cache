package todo.vm.exceptions;

public class EmptyValueInOutputException extends VmRuntimeException {
    private static final long serialVersionUID = 4619419789386564023L;

    @Override
    public String toString() {
        return "You can't output an empty value!";
    }
}
