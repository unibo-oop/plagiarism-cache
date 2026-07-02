package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

import controller.EnvironmentControllerImpl;
import view.model.activity.ActivityAlreadyPresentException;
import view.model.activity.ActivityType;
import view.model.activity.ViewActivityBuilder;
import view.model.activity.ViewActivityImpl;

public class ActivityInsertionTest {

    private final EnvironmentControllerImpl controller = new EnvironmentControllerImpl();
    private final ViewActivityImpl act1 = new ViewActivityBuilder("katun", ActivityType.FAIR).capacity(15).build();
    private final ViewActivityImpl act2 = new ViewActivityBuilder("bruco mela", ActivityType.BABYFAIR).capacity(25).build();
    private final ViewActivityImpl act3 = new ViewActivityBuilder("burger king", ActivityType.REST).minPrice(2).maxPrice(30).build();
    private final ViewActivityImpl act4 = new ViewActivityBuilder("pizza pazza", ActivityType.REST).minPrice(10).maxPrice(25).build();
    private final ViewActivityImpl act5 = new ViewActivityBuilder("souvenirs", ActivityType.SHOP).minPrice(1).maxPrice(15).build();

    @Test
    public void testActivityCreation() {
        assertNotEquals("Activities 1 and 2 are not equal", this.act1, this.act2);
        assertNotEquals("Activities 3 and 5 are not equal", this.act3, this.act5);
        assertEquals("Activities 3 and 4 have the same activity type", this.act3.getActivityType(),
                this.act4.getActivityType());
    }

    @Test
    public void testActivityInsertion() {
        try {
            this.controller.addNewActivity(this.act1);
        } catch (ActivityAlreadyPresentException exc) {
            System.out.print(exc.getMessage());
        }
        assertTrue("Activity1 correctly added in controller's list",
                this.controller.getActivityList().contains(this.act1));
        try {
            this.controller.addNewActivity(this.act3);
        } catch (ActivityAlreadyPresentException exc) {
            System.out.print(exc.getMessage());
        }
        assertTrue("Activity3 correctly added in controller's list", this.controller.getActivityList().contains(act3));
        assertThrows(ActivityAlreadyPresentException.class, () -> this.controller.addNewActivity(act3));
        assertNotSame("Activity3 not added, already present!", this.controller.getActivityList().size() == 3);
    }

    @Test
    public void testReset() {
        this.controller.resetActivityLists();
        assertTrue("Activity list correctly emptied", this.controller.getActivityList().isEmpty());
    }
}
