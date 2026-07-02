package todo.model.level.inputs;

import java.util.Comparator;

import todo.vm.Value;

/**
 * This comparator is used by the {@link InputModifier} to arrange the
 * {@link Value} in their list for substitution. It places all the empty
 * {@link Value} at the beginning of the list. It is not meant to be used for
 * other purposes.
 */
public class EmptyValueComparator implements Comparator<Value> {

    @Override
    public int compare(final Value o1, final Value o2) {
        if (!o1.isPresent() && o2.isPresent()) {
            return -1;
        }
        if (o1.isPresent() && o2.isPresent()) {
            return 0;
        }
        return 1;
    }

}
