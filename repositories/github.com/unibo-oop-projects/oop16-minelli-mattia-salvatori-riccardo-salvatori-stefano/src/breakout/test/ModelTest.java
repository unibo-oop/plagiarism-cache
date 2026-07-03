package breakout.test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;

import java.util.Arrays;
import org.junit.Test;

import breakout.controller.levels.ClassicLevel;
import breakout.model.AdvancedMode;
import breakout.model.ClassicMode;
import breakout.model.GameStatus;
import breakout.model.Model;
import breakout.model.entities.Ball;
import breakout.model.entities.BrickType;
import breakout.model.entities.Paddle;
import breakout.model.entities.PowerUp;
import breakout.model.entities.PowerUpEffect;
import breakout.model.levels.Grid;
import breakout.model.levels.LevelBuilder;
import breakout.model.levels.LevelImpl;
import breakout.view.graphics.Colors;
import javafx.util.Pair;

public class ModelTest {

    @Test
    public void classicModeTest() {
        // Crea il livello classico con 2 righe: rossa e arancione
        final ClassicMode model = Model.createClassicGame(ClassicLevel.getClassicLevel());
        final Ball ball = model.getBalls().get(0);
        final Paddle paddle = model.getPaddle();
        model.start();

        ball.setVelocity(0, -1);
        paddle.setPosition(551, paddle.getPosition().getY());

        // Colpisce il primo mattoncino
        model.updateAll(155);
        model.checkCollisions();
        model.updateAll(1);
        for (int i = 0; i < 5; i++) {
            ball.setVelocity(0, -1);
            model.updateAll(27);
            model.checkCollisions();
            model.updateAll(1);
            if (i == 3) {
                // Dopo 4 rimbalzi la palla si velocizza
                assertSame(Double.compare(ball.getSpeed(), 1.25), 0);
            }
        }
        ball.setVelocity(0, -1);
        final double length = paddle.getWidth();
        // colpisce il muro
        model.updateAll(50);
        model.checkCollisions();
        model.updateAll(1);
        // la barretta si dimezza
        assertTrue(paddle.getWidth() <= length - length / 4);

    }

    @Test
    public void advancedModeTest() {
        // Crea un livello con 2 mattoncini in posizione centrale
        final LevelBuilder b = new LevelBuilder();
        final Grid<Pair<BrickType, Colors>> grid = new Grid<>(12, 20);
        grid.add(0, 10, new Pair<BrickType, Colors>(BrickType.SIMPLE_ADVANCED, null));
        grid.add(0, 11, new Pair<BrickType, Colors>(BrickType.SIMPLE_ADVANCED, null));
        b.list(grid);
        b.spawnProb(100);
        final LevelImpl level = b.build();

        // Crea una partita
        final AdvancedMode model = Model.createAdvancedGame(Arrays.asList(level));
        final Ball mainBall = model.getBalls().get(0);
        final Paddle paddle = model.getPaddle();
        mainBall.setVelocity(0, -1); // Cambio la velocità per facilitare i
                                     // conti
        mainBall.setPosition(645, 300);
        model.start();

        // Test
        model.updateAll(280);

        model.checkCollisions();
        model.updateAll(1);

        // La palla ha distrutto un mattoncino quindi deve spawnare un PowerUp
        assertSame(model.getPowerUps().size(), 1);

        // Il punteggio deve aumentare
        assertSame(model.getScore(), 5);
        paddle.setPosition(1, paddle.getPosition().getY());
        final int life = model.getLife();
        model.updateAll(700);
        // La palla è caduta
        assertTrue(model.getGameStatus().equals(GameStatus.Dead));
        assertTrue(model.getPowerUps().isEmpty());
        // il giocatore ha perso una vita
        assertSame(model.getLife(), life - 1);

        model.start();
        model.updateAll(700);
        // il giocatore ha perso 2 vite
        assertSame(model.getLife(), life - 2);

        model.start();
        model.updateAll(700);
        // il giocatore ha perso 3 vite
        assertSame(model.getLife(), life - 3);
        // la partita è finita
        assertTrue(model.getGameStatus().equals(GameStatus.Over));
    }

    @Test
    public void levelChange() {
        final LevelBuilder b = new LevelBuilder();
        // Crea un livello con 1 mattoncino in posizione centrale
        final Grid<Pair<BrickType, Colors>> grid = new Grid<>(12, 20);
        grid.add(0, 10, new Pair<BrickType, Colors>(BrickType.SIMPLE_ADVANCED, null));
        b.list(grid);
        b.name("level1");
        final LevelImpl level = b.build();

        // Crea un altro livello con 2 mattoncini in posizione centrale
        final LevelBuilder b2 = new LevelBuilder();
        final Grid<Pair<BrickType, Colors>> grid2 = new Grid<>(12, 20);
        grid2.add(0, 10, new Pair<BrickType, Colors>(BrickType.SIMPLE_ADVANCED, null));
        grid2.add(0, 11, new Pair<BrickType, Colors>(BrickType.SIMPLE_ADVANCED, null));

        b2.list(grid2);
        b2.name("level2");
        final LevelImpl level2 = b2.build();

        // Crea una partita
        final AdvancedMode model = Model.createAdvancedGame(Arrays.asList(level, level2));
        Ball mainBall = model.getBalls().get(0);
        mainBall.setVelocity(0, -1); // Cambio la velocità per facilitare i
                                     // conti
        mainBall.setPosition(645, 300);
        // allineo la barretta con la palla
        model.getPaddle().setPosition(555, model.getPaddle().getPosition().getY());
        model.start();

        // La palla colpisce il mattoncino del livello 1 e vince il primo
        // livello
        assertTrue(model.getCurrentLevel().equals(level));
        model.updateAll(280);
        model.checkCollisions();
        model.updateAll(1);
        assertTrue(model.getGameStatus().equals(GameStatus.Won));

        // passa a livello 2
        assertTrue(model.getCurrentLevel().equals(level2));
        // parte il livello 2
        mainBall = model.getBalls().get(0);
        mainBall.setVelocity(0, -1); // Cambio la velocità per facilitare i
                                     // conti
        mainBall.setPosition(645, 300);
        // allineo la barretta con la palla
        model.getPaddle().setPosition(555, model.getPaddle().getPosition().getY());
        model.start();
        assertTrue(model.getGameStatus().equals(GameStatus.Running));
        model.updateAll(280);
        model.checkCollisions();
        model.updateAll(1);
        assertSame(model.getBricks().size(), 1);
        // la palla colpisce la barretta
        model.updateAll(540);
        model.checkCollisions();
        // la palla torna su verso l'altro mattoncino e vince anche il livello 2
        model.updateAll(550);
        model.checkCollisions();
        model.updateAll(1);
        assertTrue(model.getGameStatus().equals(GameStatus.Won));
        // Continua a rigiocare l'ultimo livello finchè non finisce tutte le
        // vite
        assertTrue(model.getCurrentLevel().equals(level2));
        assertSame(model.getBricks().size(), 2);
    }

    @Test
    public void powerUpTest() {

        // Crea un livello con 2 mattoncini in posizione centrale
        final LevelBuilder b = new LevelBuilder();
        final Grid<Pair<BrickType, Colors>> grid = new Grid<>(12, 20);
        grid.add(0, 10, new Pair<BrickType, Colors>(BrickType.SIMPLE_ADVANCED, null));
        grid.add(0, 11, new Pair<BrickType, Colors>(BrickType.SIMPLE_ADVANCED, null));
        b.list(grid);
        b.spawnProb(100);
        final LevelImpl level = b.build();

        // Crea una partita
        final AdvancedMode model = Model.createAdvancedGame(Arrays.asList(level));
        final Ball mainBall = model.getBalls().get(0);
        mainBall.setVelocity(0, -1); // Cambio la velocità per facilitare i
                                     // conti
        mainBall.setPosition(645, 300);
        model.start();

        // Test
        model.updateAll(280);

        model.checkCollisions();
        model.updateAll(1);

        // La palla ha distrutto un mattoncino quindi deve spawnare un PowerUp
        assertSame(model.getPowerUps().size(), 1);
        final PowerUp p = model.getPowerUps().get(0);
        p.setVelocity(0, 2);

        // La paddle raccoglie il potenziamento
        model.updateAll(145);
        model.checkCollisions();
        assertFalse(model.getActivePowerUps().isEmpty());

        // Se il power up è temporizzato
        if (PowerUpEffect.getTemporized().contains(p.getEffectType())) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // Dopo 3 secondi il powerUp c'è ancora
            model.updateAll(1);
            assertFalse(model.getActivePowerUps().isEmpty());
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // Dopo 6 secondi il powerUp si toglie
            model.updateAll(1);
            assertTrue(model.getActivePowerUps().isEmpty());
        }

    }

}
