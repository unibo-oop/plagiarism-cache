package bzzbomber.test;

import bzzbomber.model.Model;
import bzzbomber.model.entities.BzzBomber;
import bzzbomber.model.entities.BzzBomberImpl;
import bzzbomber.model.entities.Explosion;
import bzzbomber.model.entities.Insects;
import bzzbomber.model.entities.InsectsImpl;

import bzzbomber.model.utilities.Direction;

import java.awt.Point;

import org.junit.Assert;
import org.junit.Test;

/**
 * Test JUnit for the model entities.
 */

public class TestEntities {

    private static final int LIFE_BOMBER = 2;
    private final Model model = new Model();
    private final Point point = new Point(1, 1);
    private BzzBomber bomberman = new BzzBomberImpl(this.point, this.model);

    /**
     * Test for control that method plantBomb() was executed correctly.
     */
    @Test
    public void testPlantBomb() {
        this.bomberman = new BzzBomberImpl(this.point, this.model);
        this.bomberman.plantBomb(this.model.getAllBombs());

        Assert.assertTrue(this.model.getAllBombs().size() == 1);
        this.bomberman.move(Direction.RIGHT, this.model.getAllBlock(), this.model.getAllBombs(),
                this.model.getAllInsects());
        this.bomberman.plantBomb(this.model.getAllBombs());

        Assert.assertNotSame(this.model.getAllBombs().size(), 2);
        Assert.assertTrue(this.model.getAllBombs().stream().findFirst().get().getPosition().equals(this.point));
    }

    /**
     * Test for control that timer of the explosion was decremented correctly.
     */
    @Test
    public void testExplosion() {
        final Explosion expl = new Explosion(this.point, this.model);
        final int size = this.model.getAllExplosions().size();
        this.model.getCurrentLevel().getEntityManager().addEntity(expl);
        Assert.assertNotEquals(this.model.getAllExplosions().size(), size);
        expl.decrement();
        if (expl.isTimefinish()) {
            this.model.getCurrentLevel().getEntityManager().removeEntity(expl);
        }
        Assert.assertEquals(size, this.model.getAllExplosions().size());
    }

    /**
     * Test life of every @LivingCreature .
     */

    @Test
    public void testLifeLivingCreature() {
        this.bomberman.removeLife();
        Assert.assertEquals(LIFE_BOMBER, this.bomberman.getRemainingLives());
        this.bomberman.removeLife();
        this.bomberman.removeLife();
        Assert.assertFalse(this.bomberman.isAlive());

        final Insects insect = new InsectsImpl(new Point(2, 3), this.model);
        insect.removeLife();
        Assert.assertFalse(insect.isAlive());
    }

}
