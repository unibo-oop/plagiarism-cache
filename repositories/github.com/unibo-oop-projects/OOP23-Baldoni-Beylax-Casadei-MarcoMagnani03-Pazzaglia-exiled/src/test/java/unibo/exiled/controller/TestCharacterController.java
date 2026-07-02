package unibo.exiled.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import unibo.exiled.model.character.CharacterModel;
import unibo.exiled.model.character.GameCharacter;
import unibo.exiled.model.character.GameCharacterImpl;
import unibo.exiled.model.character.attributes.AttributeIdentifier;
import unibo.exiled.model.character.enemy.EnemyCollection;
import unibo.exiled.model.character.enemy.EnemyCollectionImpl;
import unibo.exiled.model.character.enemy.boss.FireBossEnemy;
import unibo.exiled.model.character.enemy.factory.EnemyFactoryImpl;
import unibo.exiled.model.move.MagicMove;
import unibo.exiled.utilities.ElementalType;
import unibo.exiled.utilities.Position;

import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Tests the character controller implementation.
 */
class TestCharacterController {

    private CharacterControllerImpl controller;
    private CharacterModel mockedModel;

    @BeforeEach
    void initialize() {
        this.mockedModel = Mockito.mock(CharacterModel.class);
        controller = new CharacterControllerImpl(mockedModel);
    }

    @Test
    void testGetImagePathOfCharacter() {
        final String folderPath = "path/to/folder";
        final String name = "CharacterName";
        final List<String> expected = List.of(
                folderPath,
                "/" + name.toLowerCase(Locale.ROOT) + "_up",
                "/" + name.toLowerCase(Locale.ROOT) + "_down",
                "/" + name.toLowerCase(Locale.ROOT) + "_left",
                "/" + name.toLowerCase(Locale.ROOT) + "_right");
        assertEquals(expected, controller.getImagePathOfCharacter(folderPath, name));
    }

    @Test
    void testGetPlayerHealth() {
        final double health = 100.0;
        Mockito.when(mockedModel.getPlayerAttributeOf(AttributeIdentifier.HEALTH)).thenReturn(health);
        assertEquals(health, controller.getPlayerHealth());
    }

    @Test
    void testGetPlayerHealthCap() {
        final double healthCap = 200.0;
        Mockito.when(mockedModel.getPlayerAttributeOf(AttributeIdentifier.HEALTHCAP)).thenReturn(healthCap);
        assertEquals(healthCap, controller.getPlayerHealthCap());
    }

    @Test
    void testGetPlayerLevel() {
        final int level = 5;
        Mockito.when(mockedModel.getPlayerLevel()).thenReturn(level);
        assertEquals(level, controller.getPlayerLevel());
    }

    @Test
    void testGetPlayerCurrentExperience() {
        final int experience = 50;
        Mockito.when(mockedModel.getPlayerCurrentExperience()).thenReturn(experience);
        assertEquals(experience, controller.getPlayerCurrentExperience());
    }

    @Test
    void testAddPlayerExperience() {
        final int experience = 50;
        Mockito.doNothing().when(mockedModel).addPlayerExperience(experience);
        controller.addPlayerExperience(experience);
        Mockito.verify(mockedModel, Mockito.times(1)).addPlayerExperience(experience);
    }

    @Test
    void testGetPlayerExperienceCap() {
        final int experienceCap = 100;
        Mockito.when(mockedModel.getPlayerExperienceCap()).thenReturn(experienceCap);
        assertEquals(experienceCap, controller.getPlayerExperienceCap());
    }

    @Test
    void testGetPlayerClassName() {
        final ElementalType playerType = ElementalType.FIRE;
        Mockito.when(mockedModel.getPlayerClass()).thenReturn(playerType);
        assertEquals(playerType.getName(), controller.getPlayerClassName());
    }

    @Test
    void testGetCharacterSpecsFromPositionSuccess() {
        final double fakeHealth = 105.2d;
        final double fakeHealthCap = fakeHealth * 2;
        final ElementalType fakeClass = ElementalType.WATER;
        final Position fakePosition = new Position(30, 30);
        final GameCharacter fakeCharacter = Mockito.mock(GameCharacterImpl.class);
        Mockito.when(mockedModel.getCharacterFromPosition(fakePosition)).thenReturn(Optional.of(fakeCharacter));
        Mockito.when(fakeCharacter.getHealth()).thenReturn(fakeHealth);
        Mockito.when(fakeCharacter.getHealthCap()).thenReturn(fakeHealth * 2);
        Mockito.when(fakeCharacter.spriteIsMoving()).thenReturn(true);
        Mockito.when(fakeCharacter.getType()).thenReturn(fakeClass);
        assertEquals(fakeHealth, controller.getCharacterHealthFromPosition(fakePosition));
        assertEquals(fakeHealthCap, controller.getCharacterHealthCapFromPosition(fakePosition));
        assertTrue(controller.getIfCharacterInPositionIsMoving(fakePosition));
        assertEquals(fakeClass.getName(), controller.getCharacterClassNameFromPosition(fakePosition));
    }

    @Test
    void testCharacterInPositionFailure() {
        final Position fakePosition = new Position(30, 30);
        Mockito.when(mockedModel.getCharacterFromPosition(fakePosition)).thenReturn(Optional.empty());
        assertThrows(IllegalArgumentException.class, () -> controller.getIfCharacterInPositionIsMoving(fakePosition));
        assertThrows(IllegalArgumentException.class, () -> controller.getCharacterClassNameFromPosition(fakePosition));
        assertThrows(IllegalArgumentException.class, () -> controller.getCharacterHealthCapFromPosition(fakePosition));
        assertThrows(IllegalArgumentException.class, () -> controller.getCharacterHealthFromPosition(fakePosition));

    }

    @Test
    void testCheckWin() {
        final EnemyCollection ec = new EnemyCollectionImpl();
        final FireBossEnemy testBoss = new FireBossEnemy("TEST");
        ec.addEnemy(testBoss);
        ec.addEnemy(new EnemyFactoryImpl().createAquaShade());
        Mockito.when(mockedModel.getEnemies()).thenReturn(Optional.of(ec));
        assertFalse(controller.checkWin());
        ec.removeEnemy(testBoss);
        assertTrue(controller.checkWin());
        Mockito.when(mockedModel.getEnemies()).thenReturn(Optional.empty());
        assertTrue(controller.checkWin());
    }

    @Test
    void testMagicMoveNames() {
        final Set<MagicMove> fakeMoves = Set.of(
                MagicMove.TACKLE,
                MagicMove.HEADBUTT,
                MagicMove.FIREBALL
        );
        Mockito.when(mockedModel.getMagicMoves()).thenReturn(fakeMoves);
        assertTrue(List.of(
                MagicMove.TACKLE.name(),
                MagicMove.HEADBUTT.name(),
                MagicMove.FIREBALL.name()
        ).containsAll(controller.getMagicMoveNames()));
    }

    @Test
    void testGetMagicMoveDescription() {
        final Set<MagicMove> fakeMoves = Set.of(
                MagicMove.TACKLE,
                MagicMove.HEADBUTT,
                MagicMove.FIREBALL
        );
        Mockito.when(mockedModel.getMagicMoves()).thenReturn(fakeMoves);
        //Success
        assertEquals(MagicMove.HEADBUTT.getDescription(),
                controller.getMagicMoveDescription(MagicMove.HEADBUTT.name()));
        //Failure
        assertEquals("", controller.getMagicMoveDescription(MagicMove.FLAMEWHIRL.name()));
    }

    @Test
    void testGetMagicMovePower() {
        final Set<MagicMove> fakeMoves = Set.of(
                MagicMove.TACKLE,
                MagicMove.HEADBUTT,
                MagicMove.FIREBALL
        );
        Mockito.when(mockedModel.getMagicMoves()).thenReturn(fakeMoves);
        //Success
        assertEquals(MagicMove.HEADBUTT.getPower(),
                controller.getMagicMoveDamage(MagicMove.HEADBUTT.name()));
        //Failure
        assertThrows(IllegalArgumentException.class, () -> controller.getMagicMoveDamage(
                MagicMove.FLAMEWHIRL.name()));
    }

    @Test
    void testMagicMoveType() {
        final Set<MagicMove> fakeMoves = Set.of(
                MagicMove.TACKLE,
                MagicMove.HEADBUTT,
                MagicMove.FIREBALL
        );
        Mockito.when(mockedModel.getMagicMoves()).thenReturn(fakeMoves);
        //Success
        assertEquals(MagicMove.FIREBALL.getType(),
                controller.getMagicMoveElementalType(MagicMove.FIREBALL.name()));
        //Failure
        assertThrows(IllegalArgumentException.class, () -> controller.getMagicMoveElementalType(
                MagicMove.FLAMEWHIRL.name()));
    }

}
