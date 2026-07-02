package talisman.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import talisman.Controllers;
import talisman.controller.battle.BattleController;
import talisman.controller.battle.BattleControllerImpl;
import talisman.controller.board.TalismanBoardController;
import talisman.controller.character.CharacterControllerImpl;
import talisman.controller.character.CharactersController;
import talisman.model.battle.BattleModel;
import talisman.model.battle.BattleModelImpl;
import talisman.model.battle.BattleState;
import talisman.model.battle.EnemyModel;
import talisman.model.battle.StrengthEnemy;
import talisman.model.character.CharacterModel;
import talisman.model.character.CharacterModelImpl;
import talisman.model.character.PlayerModel;
import talisman.model.character.PlayerModelImpl;
import talisman.model.character.defaultCharacters.CharacterType;
import talisman.model.character.defaultCharacters.TalismanCharacterFactory;
import talisman.test.util.BoardTestUtils;

/**
 * Tests the controllers of the battle.
 * 
 * @author Alice Girolomini
 *
 */
public class BattleControllerTests {
    /**
     * Tests a basic battle between two characters.
     */
    @Test
    public void testBattleController() {
        //Initialization of the two characters
        CharacterModel firstCharacter = new CharacterModelImpl(2, 2, 3, 4, 0, CharacterType.ASSASSIN);
        CharacterModel secondCharacter = new CharacterModelImpl(3, 2, 1, 1, 4, CharacterType.DRUID);
        final BattleModel battle = new BattleModelImpl(firstCharacter.getStrength(), secondCharacter.getStrength());
        final BattleController controller = new BattleControllerImpl(firstCharacter, secondCharacter, battle);
        Assertions.assertEquals(false, battle.isEnded());
        //first rolls the die
        battle.diceRoll(1);
        //second rolls the die
        battle.diceRoll(2);
        //adds the roll to the initial score
        Assertions.assertEquals(controller.requestedAttack(), battle.getScore(1));
        Assertions.assertEquals(controller.requestedAttack(), battle.getScore(2));
        Assertions.assertEquals(true, battle.isEnded());
    }

    /**
     * Tests the character's death.
     */
    @Test
    public void testDeathController() {
        //Initialization of opponents
        createGame();
        CharacterModelImpl character = new CharacterModelImpl(1, 1, 3, 4, 0, CharacterType.DWARF);
        //creates current character
        PlayerModel player3 = new PlayerModelImpl(3, 2, character);
        Controllers.getCharactersController().addPlayer((CharacterModelImpl) player3.getCurrentCharacter());
        Controllers.getCharactersController().setCurrentPlayer(2);
        Controllers.getBoardController().moveCharacterSection(2, 1, 1);
        EnemyModel enemy = new StrengthEnemy(10, "Wild Boar");
        //creates battle
        final BattleModel battle = new BattleModelImpl(character.getStrength(), enemy.getStrength());
        //creates controller
        final BattleController controller = new BattleControllerImpl(character, enemy, battle);
        Assertions.assertEquals(false, battle.isEnded());
        //first rolls the die
        battle.diceRoll(1);
        //second rolls the die
        battle.diceRoll(2);
        //adds the roll to the initial score
        Assertions.assertEquals(controller.requestedAttack(), battle.getScore(1));
        Assertions.assertEquals(controller.requestedAttack(), battle.getScore(2));
        //checks the winner
        Assertions.assertEquals(BattleState.SECOND, controller.getResult());
        //checks the new player's position 
        Assertions.assertEquals(0, Controllers.getBoardController().getCharacterPawn(2).getPositionCell());
        Assertions.assertEquals(0, Controllers.getBoardController().getCharacterPawn(2).getPositionSection());
        //checks the reset of the player's character
        Assertions.assertEquals(TalismanCharacterFactory.createDwarfCharacter().getHealth(), Controllers.getCharactersController().getPlayers().get(2).getCurrentCharacter().getHealth());
    }

    /**
     * Creates the board and other components.
     */
    private void createGame() {
       TalismanBoardController boardController = BoardTestUtils.createController(2, 10, 3);
       Controllers.setBoardController(boardController);
       PlayerModel player1 = new PlayerModelImpl(1, 0, TalismanCharacterFactory.createAssassinCharacter());
       PlayerModel player2 = new PlayerModelImpl(2, 1, TalismanCharacterFactory.createDruidCharacter());
       CharactersController c = new CharacterControllerImpl();
       Controllers.setCharactersController(c);
       Controllers.getCharactersController().addPlayer((CharacterModelImpl) player1.getCurrentCharacter());
       Controllers.getCharactersController().addPlayer((CharacterModelImpl) player2.getCurrentCharacter());
    }
}
