package oop.lit.test.model;
//CHECKSTYLE:OFF
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.awt.image.BufferedImage;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.Test;

import oop.lit.model.Action;
import oop.lit.model.PlayerModel;
import oop.lit.model.elements.AbstractBoardElement;
import oop.lit.model.elements.AbstractGameElement;
import oop.lit.model.elements.BoardElement;
import oop.lit.model.elements.GameElement;
import oop.lit.util.Vector2D;
import oop.lit.util.Vector2DImpl;

/**
 * A class for testing GameElements.
 */
public class TestElements {
    @Test
    public void testAbstractGameElement() {
        final GameElement ge = new GE(Optional.empty());
        assertFalse(ge.getName().isPresent());
        final GameElement ge2 = new GE(Optional.of("ge"));
        assertTrue(ge2.getName().isPresent());
        assertEquals("ge", ge2.getName().get());
    }

    @Test
    public void testAbstractBoardElement() {
        final NotifyCounter counter = new NotifyCounter();
        final BoardElement be = new BE(Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty());
        assertFalse(be.getName().isPresent());
        final BoardElement be2 = new BE(Optional.of("be"), Optional.empty(), Optional.empty(), Optional.empty());
        assertTrue(be2.getName().isPresent());
        assertEquals("be", be2.getName().get());
        be.attach(counter);
        assertEquals(new Vector2DImpl(0,0), be.getPosition());
        be.move(new Vector2DImpl(1,3));
        assertEquals(1, counter.getCount());
        assertEquals(new Vector2DImpl(1,3), be.getPosition());
        be.move(new Vector2DImpl(2,-5));
        assertEquals(2, counter.getCount());
        assertEquals(new Vector2DImpl(3,-2), be.getPosition());
        assertEquals(0, be.getRotation(), 0.001);
        be.rotate(-360*5 + 23.2);
        assertEquals(3, counter.getCount());
        assertEquals(23.2, be.getRotation(), 0.001);
        be.rotate(-25);
        assertEquals(4, counter.getCount());
        assertEquals(358.2, be.getRotation(), 0.001);
        be.scale(2);
        assertEquals(5, counter.getCount());
        assertEquals(2, be.getScale(), 0.001);
        try {
            be.scale(0);
            fail("Scalar can't be <= 0");
        } catch (Exception e) {
            if (!e.getClass().equals(IllegalArgumentException.class)) {
                fail("Wrong exception thrown");
            }
        }
        be.scale(0.1);
        assertEquals(0.2, be.getScale(), 0.001);
        be.clampScale(0.5, 2);
        assertEquals(0.5, be.getScale(), 0.001);
        be.clampScale(0.1, 0.4);
        assertEquals(0.4, be.getScale(), 0.001);
        try {
            be.clampScale(0, 1);
            fail("minScale can't be <= 0");
        } catch (Exception e) {
            if (!e.getClass().equals(IllegalArgumentException.class)) {
                fail("Wrong exception thrown");
            }
        }
        try {
            be.clampScale(1, 0.2);
            fail("maxScale can't be more than minScale");
        } catch (Exception e) {
            if (!e.getClass().equals(IllegalArgumentException.class)) {
                fail("Wrong exception thrown");
            }
        }
        assertEquals(0.4, be.getScale(), 0.001);
        assertEquals(8, counter.getCount());
    }

    public static class GE extends AbstractGameElement {
        /**
         * 
         */
        private static final long serialVersionUID = 6742092939083173293L;
        public GE(final Optional<String> name) {
            super(name);
        }
        @Override
        public List<Action> getActions(final PlayerModel playingPlayer, final List<? extends PlayerModel> turnPlayers) {
            return Collections.emptyList();
        }
        @Override
        public Optional<Action> getMainAction(final PlayerModel playingPlayer, final List<? extends PlayerModel> turnPlayers) {
            return Optional.empty();
        }
        @Override
        public Optional<BufferedImage> getImage() {
            return Optional.empty();
        }
    }

    public static class BE extends AbstractBoardElement {
        /**
         * 
         */
        private static final long serialVersionUID = 6255467631272735725L;

        public BE(final Optional<String> name, final Optional<Vector2D> initialPosition, final Optional<Double> initialScale,
                final Optional<Double> initialRotation) {
            super(name, initialPosition, initialScale, initialRotation);
        }

        @Override
        public boolean canMove(final PlayerModel playingPlayer, final List<? extends PlayerModel> turnPlayers) {
            return false;
        }

        @Override
        public boolean canScale(final PlayerModel playingPlayer, final List<? extends PlayerModel> turnPlayers) {
            return false;
        }

        @Override
        public boolean canRotate(final PlayerModel playingPlayer, final List<? extends PlayerModel> turnPlayers) {
            return false;
        }

        @Override
        public List<Action> getActions(final PlayerModel playingPlayer, final List<? extends PlayerModel> turnPlayers) {
            return Collections.emptyList();
        }

        @Override
        public Optional<Action> getMainAction(final PlayerModel playingPlayer, final List<? extends PlayerModel> turnPlayers) {
            return Optional.empty();
        }

        @Override
        public Optional<BufferedImage> getImage() {
            return Optional.empty();
        }
    }
}
