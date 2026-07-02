package it.unibo.monopoli.model.table;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * This class represent the classic implementation of game's {@link Group}.
 *
 */
public class ClassicGroup implements Group {

    private final List<Ownership> members;
    private final String name;

    /**
     * Constructs an instance of {@link ClassicGroup}s. It needs a name and all
     * the {@link Ownership}s belonging to its.
     * 
     * @param name
     *            - {@link Group}0s name
     * @param members
     *            - {@link Group}'s members
     */
    public ClassicGroup(final String name, final Ownership... members) {
        this.members = new LinkedList<>();
        this.members.addAll(Arrays.asList(members));
        this.name = name;
    }

    @Override
    public List<Ownership> getMembers() {
        return Collections.unmodifiableList(this.members);
    }

    @Override
    public String getName() {
        return this.name;
    }

}
