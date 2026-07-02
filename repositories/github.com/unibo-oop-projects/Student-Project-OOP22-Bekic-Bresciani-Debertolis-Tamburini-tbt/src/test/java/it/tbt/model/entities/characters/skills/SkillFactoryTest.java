package it.tbt.model.entities.characters.skills;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.logging.Logger;

import org.junit.jupiter.api.Test;

import it.tbt.controller.SimpleLogger;

class SkillFactoryTest {
    private final Logger logger = SimpleLogger.getLogger("SkillFactoryTest");

    @Test
    void testSkillFactory() {
        final SkillFactory skillFactory = SkillFactory.getFactory();
        final SkillFactory skillFactory2 = SkillFactory.getFactory();

        assertEquals(skillFactory, skillFactory2);
        assertFalse(skillFactory.getSkills().isEmpty());
        for (final Skill skill : skillFactory.getSkills()) {
            logger.info(
                skill.toString()
                + ", Name: " + skill.getName()
                + ", Attack Multiplier: " + skill.getAttackMultiplier()
                + ", Cooldown: " + skill.getCooldown()
                + ", Possible Status: " + skill.getPossibleStatus()
                + ", Increased Probability of Critical: " + skill.isIncProbCritical()
            );
        }
    }
}
