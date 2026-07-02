package model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

class TestParametizer {

    private static final Integer WAVE_NUM = 5;

    private WaveInfo.Difficulty diff = WaveInfo.Difficulty.EASY;
    private LevelData levelNormal = new LevelDataImpl(diff, WAVE_NUM, 0, 0, false);
    private LevelData levelEndless = new LevelDataImpl(diff, 0, 0, 0, true);
    private LevelParametizerImpl lp;

    @Test
    public void testLevelEasy() {
        System.out.println(">>TEST = Normal Level - Easy");
        lp = new LevelParametizerImpl(diff, WAVE_NUM, false);
        // normal procedure
        WaveInfo newWave = lp.newWave();
        assertEquals(WaveInfo.Difficulty.EASY, newWave.getDifficulty());
        assertEquals(1, newWave.getNumber());
        lp.debugPrintLevelData();
        int hp = newWave.elaborateInitialHP(WAVE_NUM);
        int dmg = newWave.elaborateInitialDMG(WAVE_NUM / WAVE_NUM);
        System.out.println("HP: " + hp + " DMG: " + dmg);
        // exception
        for (int i = 1; i < WAVE_NUM; i++) {
            newWave = lp.newWave();
        }
        assertThrows(NoSuchElementException.class, () -> {
                                                          lp.newWave(); 
                                                         });
        System.out.println(" ");
    }

    @Test
    public void testData() {
        System.out.println(">>TEST = Level Data - Load and Save");
        lp = new LevelParametizerImpl(levelNormal);
        // normal procedure
        WaveInfo newWave = lp.newWave();
        assertEquals(WaveInfo.Difficulty.EASY, newWave.getDifficulty());
        assertEquals(1, newWave.getNumber());
        lp.debugPrintLevelData();
        int hp = newWave.elaborateInitialHP(WAVE_NUM);
        int dmg = newWave.elaborateInitialDMG(WAVE_NUM / WAVE_NUM);
        System.out.println("HP: " + hp + " DMG: " + dmg);
        // contextual elaborated values
        for (int i = 1; i < WAVE_NUM; i++) {
            newWave = lp.newWave();
            hp = newWave.elaborateInitialHP(WAVE_NUM);
            dmg = newWave.elaborateInitialDMG(WAVE_NUM / WAVE_NUM);
            System.out.println("HP: " + hp + " DMG: " + dmg);
        }
        // closing procedure
        levelNormal = lp.closeLevel();
        System.out.println(levelNormal.toString());
        System.out.println(" ");
    }

    @Test
    public void testEndless() {
        System.out.println(">>TEST = Endless Mode");
        lp = new LevelParametizerImpl(levelEndless);
        // normal procedure
        WaveInfo newWave = lp.newWave();
        assertEquals(WaveInfo.Difficulty.EASY, newWave.getDifficulty());
        assertEquals(1, newWave.getNumber());
        lp.debugPrintLevelData();
        int hp = newWave.elaborateInitialHP(WAVE_NUM);
        int dmg = newWave.elaborateInitialDMG(WAVE_NUM / WAVE_NUM);
        System.out.println("HP: " + hp + " DMG: " + dmg);
        // no exception
        for (int i = 1; i < WAVE_NUM * 2; i++) {
            newWave = lp.newWave();
        }
        hp = newWave.elaborateInitialHP(WAVE_NUM);
        dmg = newWave.elaborateInitialDMG(WAVE_NUM / WAVE_NUM);
        System.out.println("HP: " + hp + " DMG: " + dmg);
        // closing procedure
        levelNormal = lp.closeLevel();
        System.out.println(levelNormal.toString());
        System.out.println(" ");
    }

}
