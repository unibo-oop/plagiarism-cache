package it.unibo.pacman.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.Collections;
import java.util.Set;

import org.junit.Test;

import it.unibo.pacman.controller.entities.EntityController;
import it.unibo.pacman.controller.entities.GhostControllerImpl;
import it.unibo.pacman.controller.entities.MovableController;
import it.unibo.pacman.controller.entities.PacmanController;
import it.unibo.pacman.controller.entities.PacmanControllerImpl;
import it.unibo.pacman.controller.entities.SimpleEntityController;
import it.unibo.pacman.model.entities.EntityFactory;
import it.unibo.pacman.model.entities.EntityFactoryImpl;
import it.unibo.pacman.model.entities.Movable;
import it.unibo.pacman.model.leaderboard.PlayerScoreImpl;
import it.unibo.pacman.model.utilities.Difficulty;
import it.unibo.pacman.model.utilities.Position;
import it.unibo.pacman.view.entities.ViewFactory;
import it.unibo.pacman.view.entities.ViewFactoryImpl;

public class TestPacman {
    @Test
    public void creationTest() {
        final EntityFactory factory = new EntityFactoryImpl();
        assertNotNull(factory);
        final ViewFactory viewFactory = new ViewFactoryImpl();
        assertNotNull(viewFactory);
        final Set<EntityController> set = Collections.singleton(new SimpleEntityController(factory.createWall(new Position(10, 10)),
                viewFactory.getWallView()));
        final Set<MovableController> set1 = Collections.singleton(new GhostControllerImpl(viewFactory.getBlinkyView(), factory.createBlinky(new Position(100, 100), Difficulty.NORMAL), set));
        final PacmanController pacman = new PacmanControllerImpl(factory.createPacMan(new Position(0, 0)), 
                viewFactory.getPacManView(), set, set1, new PlayerScoreImpl());
        assertNotNull(pacman);
    }

    @Test
    public void pacmanMove() {
        final EntityFactory factory = new EntityFactoryImpl();
        assertNotNull(factory);
        final Movable pacman = factory.createPacMan(new Position(10, 10));
        assertNotNull(pacman);
        pacman.setPosition(pacman.nextPosition());
        assertFalse(pacman.getPosition().equals(new Position(10, 10)));
    }
}
