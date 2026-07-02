package model.entities;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 
 * Functional class for team related stuff.
 *
 */
public final class Teams {
    private Teams() {
    }

    /**
     * Returns the list of alive members in the provided team.
     * 
     * @param team
     *            The provided team
     * @return The list of alive members in the provided team.
     */
    private static List<Hero> getAliveMembersInTeam(final List<Hero> team) {
        return team.stream().filter(h -> h.getStatus().equals(EntityStatus.ALIVE)).collect(Collectors.toList());
    }

    /**
     * Returns whether the provided team is fully exhausted or not (movement AND
     * attack).
     * 
     * @param team
     *            The provided team
     * @return Whether the provided team is fully exhausted or not (movement AND
     *         attack)
     */
    public static boolean isTeamExhausted(final List<Hero> team) {
        return getAliveMembersInTeam(team).stream()
                .filter(h -> h.getAttackStatus().equals(AttackStatus.EXHAUSTED)
                        && h.getMovementStatus().equals(MovementStatus.EXHAUSTED))
                .count() == getAliveMembersInTeam(team).size();
    }

    /**
     * Returns whether the provided team has all of its members dead.
     * 
     * @param team
     *            The provided team
     * @return Whether the provided team has all of its members dead
     */
    public static boolean isTeamDead(final List<Hero> team) {
        return getAliveMembersInTeam(team).stream().count() == 0;
    }

    /**
     * Fully energizes the provided team (replenish all of its heroes from attack
     * and movement exhaustion).
     * 
     * @param team
     *            the provided team
     */
    public static void energizeAllTeam(final List<Hero> team) {
        getAliveMembersInTeam(team).stream().forEach(Entity::energize);
    }
}
