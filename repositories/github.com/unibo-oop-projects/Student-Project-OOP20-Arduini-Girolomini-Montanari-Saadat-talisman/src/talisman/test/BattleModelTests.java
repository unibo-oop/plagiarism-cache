package talisman.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import talisman.model.battle.BattleModel;
import talisman.model.battle.BattleModelImpl;

/**
 * Tests the model of the battle.
 * 
 * @author Alice Girolomini
 *
 */
public class BattleModelTests {
    /**
     * Tests the battle model.
     */
    @Test
    public void testBattleModel() {
        //Initialization of the two characters
        int firstCharacterStrength = 3;
        int secondCharacterStrength = 4;
        final BattleModel battle = new BattleModelImpl(firstCharacterStrength, secondCharacterStrength);
        //Checks the state of the battle
        Assertions.assertEquals(false, battle.isEnded());
        //first rolls the die
        battle.diceRoll(1);
        //second rolls the die
        battle.diceRoll(2);
        //adds the roll to the initial score
        int firstCharacterScore = battle.getDiceRoll(1) + firstCharacterStrength;
        int secondCharacterScore = battle.getDiceRoll(2) + secondCharacterStrength;
        battle.compareScore();
        Assertions.assertEquals(firstCharacterScore, battle.getScore(1) + battle.getDiceRoll(1));
        Assertions.assertEquals(secondCharacterScore, battle.getScore(2) + battle.getDiceRoll(2));
        Assertions.assertEquals(true, battle.isEnded());
    }

}
