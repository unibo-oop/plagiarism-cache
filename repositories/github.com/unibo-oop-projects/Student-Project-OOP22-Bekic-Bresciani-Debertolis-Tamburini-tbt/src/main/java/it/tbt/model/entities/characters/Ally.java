package it.tbt.model.entities.characters;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import it.tbt.model.entities.characters.skills.Skill;

/**
 * Generic Ally.
 */
public class Ally extends CharacterImpl {
    private static final long serialVersionUID = 6613096542675407381L;
    private final List<Skill> skills;

    /**
     * Default Constructor.
     * @param name
     * @param health
     * @param attack
     * @param speed
     * @param skills
     */
    public Ally(
        final String name,
        final int health,
        final int attack,
        final int speed,
        final Collection<Skill> skills
    ) {
        super(name, health, attack, speed);
        this.skills = new ArrayList<>(skills);
    }

    /**
     * Default Constructor without skills.
     * @param name
     * @param health
     * @param attack
     * @param speed
     */
    public Ally(
        final String name,
        final int health,
        final int attack,
        final int speed
    ) {
        super(name, health, attack, speed);
        this.skills = new ArrayList<>();
    }

    /**
     * Get a list of the character's skills.
     * @return list of the character's skills
     */
    public List<Skill> getSkills() {
        return List.copyOf(skills);
    }

    /**
     * Add a skill to the character.
     * @param skill
     */
    public void addSkill(final Skill skill) {
        skills.add(skill);
    }
}
