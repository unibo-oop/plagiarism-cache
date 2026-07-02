package test;

import model.resources.Resource;
import model.resources.ResourceImpl;
import model.resources.ResourceType;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Test;

/**
 * This class is used for testing the Resource model.
 */
@SuppressWarnings("unused")
public class TestResources {

    /**
     * Test the creation of a new Resource with the right parameters.
     */
    @Test public void testResourceCreationOK() {
        try {
                Resource res = new ResourceImpl(ResourceType.ENERGY, 0);
                res = new ResourceImpl(ResourceType.ENERGY, 1);
                res = new ResourceImpl(ResourceType.MONEY, 0);
                res = new ResourceImpl(ResourceType.MONEY, 1);
                res = new ResourceImpl(ResourceType.PEOPLE, 0);
                res = new ResourceImpl(ResourceType.PEOPLE, 1);
                res = new ResourceImpl(ResourceType.WATER, 0);
                res = new ResourceImpl(ResourceType.WATER, 1);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    /**
     * Test the non-creation of a new Energy Resource.
     */
    @Test(expected = IllegalArgumentException.class) public void testEnergyCreationNO() {
       Resource res = new ResourceImpl(ResourceType.ENERGY, -1);
       res = null;
    }

    /**
     * Test the non-creation of a new People Resource.
     */
    @Test(expected = IllegalArgumentException.class) public void testPeopleCreationNO() {
       Resource res = new ResourceImpl(ResourceType.PEOPLE, -1);
       res = null;
    }

    /**
     * Test the non-creation of a new Water Resource.
     */
    @Test(expected = IllegalArgumentException.class) public void testWaterCreationNO() {
       Resource res = new ResourceImpl(ResourceType.WATER, -1);
       res = null;
    }

    /**
     * Test the Operations on an Energy Resource.
     */
    @Test(expected = IllegalArgumentException.class) public void testEnergyOperations() {
       final Resource res = new ResourceImpl(ResourceType.ENERGY, 100);
       assertTrue(res.canBeDecreased(90));
       assertTrue(res.canBeDecreased(100));
       assertFalse(res.canBeDecreased(res.getValue() + 1));
       res.decrease(res.getValue());
       assertEquals(res.getValue().intValue(), 0);
       res.add(10);
       assertEquals(res.getValue().intValue(), 10);
       res.decrease(100);
    }

    /**
     * Test the Operations on a Money Resource.
     */
    @Test public void testMoneyOperations() {
       final Resource res = new ResourceImpl(ResourceType.MONEY, 100);
       assertTrue(res.canBeDecreased(90));
       assertTrue(res.canBeDecreased(100));
       assertFalse(res.canBeDecreased(res.getValue() + 1));
       res.decrease(res.getValue());
       assertEquals(res.getValue().intValue(), 0);
       res.add(1);
       assertEquals(res.getValue().intValue(), 1);
    }

    /**
     * Test the Operations on a People Resource.
     */
    @Test(expected = IllegalArgumentException.class) public void testPeopleOperations() {
       final Resource res = new ResourceImpl(ResourceType.PEOPLE, 100);
       assertTrue(res.canBeDecreased(90));
       assertTrue(res.canBeDecreased(100));
       assertFalse(res.canBeDecreased(res.getValue() + 1));
       res.decrease(res.getValue());
       assertEquals(res.getValue().intValue(), 0);
       res.add(10);
       assertEquals(res.getValue().intValue(), 10);
       res.decrease(100);
    }

    /**
     * Test the Operations on an Water Resource.
     */
    @Test(expected = IllegalArgumentException.class) public void testWaterOperations() {
       final Resource res = new ResourceImpl(ResourceType.ENERGY, 100);
       assertTrue(res.canBeDecreased(90));
       assertTrue(res.canBeDecreased(100));
       assertFalse(res.canBeDecreased(res.getValue() + 1));
       res.decrease(res.getValue());
       assertEquals(res.getValue().intValue(), 0);
       res.add(10);
       assertEquals(res.getValue().intValue(), 10);
       res.decrease(100);
    }
}
