package it.tbt.model.entities.characters.skills;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import it.tbt.controller.resources.ConfigManager;

/**
 * Skill Factory.
 */
public final class SkillFactory {
    private final Set<Skill> skills;

    /**
     * Inner class, for lazy initialization.
     */
    private static class LazySkillFactory {
        private static final SkillFactory SKILL_FACTORY = new SkillFactory();
    }

    private SkillFactory() {
        skills = new HashSet<>();

        final Optional<Skill[]> optSkills = ConfigManager.parseJsonConfig(
            "tbt/entities/skills.json",
            Skill[].class
        );
        if (optSkills.isEmpty()) {
            throw new IllegalStateException("Failed loading skills from json");
        } else {
            for (final Skill skill : optSkills.get()) {
                skills.add(skill);
            }
        }
    }

    /**
     * Return the SkillFactory.
     * skillFactory is singleton
     * @return SkillFactory
     */
    public static SkillFactory getFactory() {
        return LazySkillFactory.SKILL_FACTORY;
    }

    /**
     * Get a set of possible skills.
     * @return Set of Skill
     */
    public Set<Skill> getSkills() {
        return Set.copyOf(skills);
    }
}
