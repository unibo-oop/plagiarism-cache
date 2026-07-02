package it.tbt.model.party;

import it.tbt.model.entities.characters.Ally;
import it.tbt.model.entities.characters.skills.Skill;
import it.tbt.model.entities.characters.skills.SkillFactory;
import it.tbt.model.entities.items.Potion;
import it.tbt.model.entities.items.factories.AntidoteFactory;
import it.tbt.model.entities.items.factories.PotionFactory;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Factory that returns different implementations of the IParty interface.
 */
public final class PartyFactory {

    //constant to suppress checkstyle
    private static final int DEFAULT_WIDTH = 50;
    private static final int DEFAULT_HEIGHT = 50;
    private static final int INT_CONST_10 = 10;
    private static final int INT_CONST_20 = 20;
    private static final int INT_CONST_30 = 30;
    private static final int INT_CONST_50 = 50;
    private static final int INT_CONST_70 = 70;
    private static final int INT_CONST_90 = 90;
    private static final int INT_CONST_5000 = 5000;
    private static final String DEFAULT_PARTY_NAME = "PARTY";

    /**
     * Private constructor not to give the possibility to instantiate a utility
     * class.
     */
    private PartyFactory() {
    }

    /**
     * @return a Party object with default settings.
     */
    @SuppressWarnings("MagicNumber")
    public static IParty createDefaultParty() {
        final IParty party = new Party(DEFAULT_PARTY_NAME,
                DEFAULT_WIDTH / 2,
                DEFAULT_HEIGHT / 2,
                DEFAULT_WIDTH,
                DEFAULT_HEIGHT);
        final ArrayList<Ally> allies = new ArrayList<>();
        final ArrayList<Skill> skills = new ArrayList<>(SkillFactory.getFactory().getSkills());

        allies.add(new Ally("Roberto", INT_CONST_50, INT_CONST_50, INT_CONST_50,
                new ArrayList<>(Arrays.asList(skills.get(0)))));
        allies.add(new Ally("Gianfranco", INT_CONST_10, INT_CONST_10, INT_CONST_70,
                new ArrayList<>(Arrays.asList(skills.get(1)))));
        allies.add(new Ally("Caparezza", INT_CONST_30, INT_CONST_90, INT_CONST_20,
                new ArrayList<>(Arrays.asList(skills.get(2)))));
        allies.add(new Ally("Robertino", INT_CONST_20, INT_CONST_20, INT_CONST_20,
                new ArrayList<>(Arrays.asList(skills.get(3)))));
        party.setMembers(allies);

        for (final Potion potion : PotionFactory.getInstance().getItems()) {
            party.addItemToInventory(potion);
            party.addItemToInventory(potion);
        }
        party.addItemToInventory(AntidoteFactory.getInstance().getAntidote());
        party.addCash(INT_CONST_5000);
        return party;
    }
}
