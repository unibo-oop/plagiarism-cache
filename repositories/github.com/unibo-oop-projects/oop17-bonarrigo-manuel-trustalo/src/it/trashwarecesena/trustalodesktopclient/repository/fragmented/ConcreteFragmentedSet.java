package it.trashwarecesena.trustalodesktopclient.repository.fragmented;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import it.trashwarecesena.trustalodesktopclient.utils.ErrorString;

/**
 * An implementation of {@link FragmentedSet}.
 * <p>
 * There is no real pledge over the typesafety of the dynamic cast, since the
 * JVM erasure feature prevents the constructor from inferring such an
 * information at construction-time.
 *
 * @author Manuel Bonarrigo
 */
public final class ConcreteFragmentedSet implements FragmentedSet {

    private final Set<?> set;
    private final Class<?> klass;

    /**
     * Constructs a FragmentedSet based on the {@link Set} parameter and the
     * {@link Class}. Clients should note that since the JVM already applied its
     * erasure to generic types, the Class parameter must be provided accordingly to
     * the <i>set</i> parameter to prevent unexpected behaviors.
     * <p>
     * 
     * @param set
     *            The Set which needs its erasure type to be preserved.
     * @param klass
     *            The Class representing such a type
     */
    public ConcreteFragmentedSet(final Set<?> set, final Class<?> klass) {
        super();
        Objects.requireNonNull(set, "The Set" + ErrorString.CUSTOM_NULL);
        Objects.requireNonNull(klass, "The Class" + ErrorString.CUSTOM_NULL);
        this.set = set;
        this.klass = klass;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <X> Set<X> getUnerasedSet() throws ClassCastException {
        return (Set<X>) set.stream().map(obj -> klass.cast(obj)).collect(Collectors.toSet());
    }

    @SuppressWarnings("unchecked")
    @Override
    public <X> Class<X> getBoundedClass() {
        return (Class<X>) this.klass;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((set == null) ? 0 : set.hashCode());
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
        final ConcreteFragmentedSet other = (ConcreteFragmentedSet) obj;
        if (!klass.equals(other.klass)) {
            return false;
        }
        if (set == null) {
            if (other.set != null) {
                return false;
            }
        } else if (!set.equals(other.set)) {
            return false;
        }
        return true;
    }

}
