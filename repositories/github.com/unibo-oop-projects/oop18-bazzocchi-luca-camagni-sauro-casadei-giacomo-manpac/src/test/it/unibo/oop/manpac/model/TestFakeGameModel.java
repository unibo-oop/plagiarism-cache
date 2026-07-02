package test.it.unibo.oop.manpac.model;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.stream.IntStream;

import org.junit.Test;

import it.unibo.oop.manpac.model.Action;
import it.unibo.oop.manpac.model.Entity;
import it.unibo.oop.manpac.model.ManpacImpl;
import it.unibo.oop.manpac.model.ModelForController;
import it.unibo.oop.manpac.model.score.PointsImpl;

/**
 * .
 */
public class TestFakeGameModel {

    /**
     * 
     */
    @Test
    public void testGame() {

        final int numberPills = 244;
        int score = 0;
        int highscore = 130;

        final ModelForController model = new ManpacImpl();
        model.setHighScore(new PointsImpl<>(highscore));
        model.setPills(numberPills);

        model.resetGame();

        // Pac-Man mangia 20 pillole ed il suo punteggio sia score che highscore arriva
        // a 200,
        IntStream.range(0, 20).forEach(x -> assertSame(Action.PILL_EATEN, model.pacmanCollision(Entity.PILL)));
        score = 10 * 20;
        highscore = score;
        // controllo score
        assertTrue(score == model.getCurrentScore().getValue());
        assertTrue(highscore == model.getHighScore().getValue());
        System.out.println(
                "score: " + model.getCurrentScore().getValue() + " _ highscore: " + model.getHighScore().getValue());

        // Pac-Man collide su un fantasma e muore
        assertSame(Action.PHANTOM_KILLED_PACMAN, model.pacmanCollision(Entity.BLINKY));
        // controllo vite
        assertTrue(model.getLives() == 2);

        // Pac-Man mangia la powerpill
        assertSame(Action.POWER_PILL_EATEN, model.pacmanCollision(Entity.POWERPILL));
        score += 50;
        highscore = score;

        // Pac_man mangia un fantasma e aumenta lo score di 200
        assertSame(Action.PACMAN_ATE_PHANTOM, model.pacmanCollision(Entity.BLINKY));

        score += 200;
        highscore = score;
        // controllo score
        assertTrue(score == model.getCurrentScore().getValue());
        assertTrue(highscore == model.getHighScore().getValue());

        // finisce l'effetto powerpill
        model.stopPower();

        // Pac-Man collide su un fantasma e muore
        assertSame(Action.PHANTOM_KILLED_PACMAN, model.pacmanCollision(Entity.BLINKY));
        // controllo vite
        assertTrue(model.getLives() == 1);

        // Pac-Man collide su un fantasma e muore
        assertSame(Action.GAME_OVER, model.pacmanCollision(Entity.BLINKY));
        // controllo vite
        assertTrue(model.getLives() == 0);
        // controllo score
        assertTrue(score == model.getCurrentScore().getValue());
        assertTrue(highscore == model.getHighScore().getValue());

        model.resetGame();
        // controllo reset
        assertTrue(model.getLives() == 3);
        assertTrue(model.getCurrentScore().getValue() == 0);
        assertTrue(model.getHighScore().getValue() == highscore);

        // Pac-Man mangia 242 pillole
        IntStream.range(0, numberPills - 2)
                .forEach(x -> assertSame(Action.PILL_EATEN, model.pacmanCollision(Entity.PILL)));

        // si prova a settare un numero diverso di pillole, ma deve dare exception
        try {
            model.setPills(50);
            fail();
        } catch (Exception e) {

        }

        // Pac-Man mangia una pillola
        model.pacmanCollision(Entity.PILL);
        // Pac-Man mangia l'ultima pillola e vince la partita
        assertSame(Action.WIN, model.pacmanCollision(Entity.PILL));

        // punteggio di ogni pillola divorata
        score = 10 * numberPills;
        highscore = score;
        assertTrue(score == model.getCurrentScore().getValue());
        assertTrue(highscore == model.getHighScore().getValue());

        // "nuovo livello"
        model.resetAndContinue();

        // Pac-Man mangia 20 pillole
        IntStream.range(0, 20).forEach(x -> assertSame(Action.PILL_EATEN, model.pacmanCollision(Entity.PILL)));
        score += 10 * 20;
        highscore = score;
        assertTrue(score == model.getCurrentScore().getValue());
        assertTrue(highscore == model.getHighScore().getValue());

    }

}
