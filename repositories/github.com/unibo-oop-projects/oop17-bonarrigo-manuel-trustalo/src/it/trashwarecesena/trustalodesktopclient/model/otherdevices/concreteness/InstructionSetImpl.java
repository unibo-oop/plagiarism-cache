package it.trashwarecesena.trustalodesktopclient.model.otherdevices.concreteness;

import java.util.Objects;

import it.trashwarecesena.trustalodesktopclient.model.otherdevices.InstructionSet;
import it.trashwarecesena.trustalodesktopclient.utils.ErrorString;
import it.trashwarecesena.trustalodesktopclient.utils.ExtendedObjects;

/**
 * An implementation of the {@link InstructionSet} interface.
 * 
 * @author Manuel Bonarrigo
 *
 */
public final class InstructionSetImpl implements InstructionSet {

    private final String name;

    /**
     * Constructs an {@link InstructionSet} with the given name.
     * 
     * @param name
     *            a String representing the name of the instruction set
     *            architecture.
     * @throws NullPointerException
     *             if the name is found to be {@code null}
     * @throws IllegalArgumentException
     *             if the name is found to be <i>empty</i>
     * @see {@link it.trashwarecesena.trustalodesktopclient.utils.ExtendedObjects#requireNonEmpty(String)
     *      ExtendedObjects.requireNonEmpty(String, String)}
     */
    public InstructionSetImpl(final String name) {
        this.name = ExtendedObjects.requireNonEmpty(Objects.requireNonNull(name, "The name" + ErrorString.CUSTOM_NULL),
                ErrorString.EMPTY_STRING);
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final InstructionSetImpl other = (InstructionSetImpl) obj;
        if (name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!name.equals(other.name)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "InstructionSetImpl [name=" + name + "]";
    }
}
