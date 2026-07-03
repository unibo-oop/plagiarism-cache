package it.lttply.model.domain;

import java.util.Set;

/**
 * This interface provides the base to build a class that represents the credits
 * of a {@link Movie} and {@link TVSerie}.
 */
public interface Credits {
    /**
     * 
     * @return the {@link Person} who created the {@link Movie} and
     *         {@link TVSerie}.
     */
    Person getCreator();

    /**
     * 
     * @return a set of {@link Person} who played in the {@link Movie} and
     *         {@link TVSerie}.
     */
    Set<Person> getCast();
}
