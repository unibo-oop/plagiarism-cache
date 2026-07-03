package tests.model;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import org.junit.Test;

import model.aim.AimFactoryImpl;
import model.aim.AimLabel;
import model.aim.DefinedConquerAim;
import model.aim.DestroyAim;
import model.aim.DoubleGarrisonAim;
import model.aim.StateAim;
import model.aim.UndefinedConquerAim;
import model.player.Player;
import model.player.PlayerImpl;
import model.region.Region;
import model.region.RegionImpl;
import utils.enumerations.Color;
import utils.enumerations.ControlType;

/**
 * Test for class AimFactoryImpl.
 */
public class TestAimFactoryImpl {

    private static final int MAX_STATES_AIM = 1;
    private static final int DEFAULT_STATES = 24;
    private static final int DEFAULT_STATES_GARRISON = 18;
    private final Region reg0 = new RegionImpl("TestRegion0", 0);
    private final Region reg1 = new RegionImpl("TestRegion1", 0);
    private final Region reg2 = new RegionImpl("TestRegion2", 0);
    private final Region reg3 = new RegionImpl("TestRegion3", 0);

    /**
     * Test the correctness of AimfactoryImpl initialization and assignAim.
     */
    @Test
    public void testAimfactoryImpl() {
        final Set<Region> definedSet0 = new HashSet<>();
        final Set<Region> definedSet1 = new HashSet<>();
        final Set<Region> definedSet2 = new HashSet<>();
        final Set<Region> definedSet3 = new HashSet<>();
        final Set<Region> undefinedSet0 = new HashSet<>();
        final Set<Region> undefinedSet1 = new HashSet<>();

        definedSet0.addAll(Arrays.asList(reg0, reg1));
        definedSet1.addAll(Arrays.asList(reg0, reg2));
        definedSet2.addAll(Arrays.asList(reg0, reg3));
        definedSet3.addAll(Arrays.asList(reg1, reg2));
        undefinedSet0.addAll(Arrays.asList(reg1, reg3));
        undefinedSet1.addAll(Arrays.asList(reg2, reg3));

        final List<Set<Region>> definedAvailableRegion = new LinkedList<>(
                Arrays.asList(definedSet0, definedSet1, definedSet2, definedSet3));
        final List<Set<Region>> undefinedAvailableRegion = new LinkedList<>(
                Arrays.asList(undefinedSet0, undefinedSet1));
        final AimFactoryImpl aF = new AimFactoryImpl(definedAvailableRegion, undefinedAvailableRegion, DEFAULT_STATES,
                DEFAULT_STATES_GARRISON);

        int nGeneratedAims = (MAX_STATES_AIM + MAX_STATES_AIM + Arrays.asList(Color.values()).size()
                + definedAvailableRegion.size() + undefinedAvailableRegion.size());

        try {
            final Player p = new PlayerImpl("p", Color.BLACK, ControlType.HUMAN, 1);
            p.setAim(aF.assignAim(p));
            fail("this method should raise an exception!");
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            fail("wrong exception thrown");
        }

        try {
            final Player p = new PlayerImpl("p", Color.BLACK, ControlType.HUMAN, 1);
            p.setAim(aF.assignAim(null));
            fail("this method should raise an exception!");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            fail("wrong exception thrown");
        }

        aF.initializeFactory();

        try {
            aF.initializeFactory();
            fail("this method should raise an exception!");
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            fail("wrong exception thrown");
        }

        assertEquals(aF.getAimList().size(), nGeneratedAims);

        while (!aF.getAimList().isEmpty()) {
            final Player p = new PlayerImpl("p", Color.BLACK, ControlType.HUMAN, 1);
            p.setAim(aF.assignAim(p));
            nGeneratedAims--;

            if (p.getAim() instanceof DestroyAim) {
                assertFalse(aF.getAvailableEnemies().contains(((DestroyAim) p.getAim()).getEnemy()));
            } else if (p.getAim() instanceof DefinedConquerAim) {
                assertFalse(
                        aF.getDefinedAvailableRegion().containsAll(((DefinedConquerAim) p.getAim()).getRegionList()));
            } else if (p.getAim() instanceof UndefinedConquerAim) {
                assertFalse(aF.getUndefinedAvailableRegion()
                        .containsAll(((UndefinedConquerAim) p.getAim()).getRegionList()));
            } else if (p.getAim() instanceof StateAim) {
                assertFalse(aF.getAimList().contains(AimLabel.SA));
            } else if (p.getAim() instanceof DoubleGarrisonAim) {
                assertFalse(aF.getAimList().contains(AimLabel.DGA));
            }

            assertEquals(aF.getAimList().size(), nGeneratedAims);
        }
    }
}
