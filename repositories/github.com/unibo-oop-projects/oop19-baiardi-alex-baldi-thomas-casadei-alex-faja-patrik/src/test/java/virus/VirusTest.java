package virus;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import model.virus.Virus;
import model.virus.VirusFactoryImpl;

/**
 * Virus testing class.
 */
@TestInstance(Lifecycle.PER_CLASS)
public class VirusTest {
    private static final int QUANTIUM_IN_A_DAY = 24;
    private Virus virus;
    private int incubationPeriodMin;
    private int incubationPeriodmax;
    private double mortality = 2;
    private double infectivity = 3;
    private int recoveryPeoridmin = 2;
    private int recoveryPeoridmax = 10;
    /**
     *  Test the virus value.
     */
    @Test
    public void testVirusValue() {
        final int incubationPeorid = virus.getIncubationPeriod();
        final int recoveryPeorid = virus.getRecoveryPeriod();
        assertEquals(virus.getInfectivity(), infectivity);
        assertEquals(virus.getMortality(), mortality);
        assertTrue(incubationPeorid >= incubationPeriodMin * QUANTIUM_IN_A_DAY && incubationPeorid <= incubationPeriodmax * QUANTIUM_IN_A_DAY);
        assertTrue(recoveryPeorid >= recoveryPeoridmin * QUANTIUM_IN_A_DAY && recoveryPeorid <= recoveryPeoridmax * QUANTIUM_IN_A_DAY);
        for (int i = 0; i < 10; i++) {
             assertEquals(recoveryPeorid, virus.getRecoveryPeriod());
             assertEquals(incubationPeorid, virus.getIncubationPeriod());
        }
    }

    /**
     * 
     */
    @BeforeAll
    public void inizializeVirus() {
    this.incubationPeriodMin = 1;
    this.incubationPeriodmax = 10;
    this.mortality = 2;
    this.infectivity = 3;
    this.recoveryPeoridmin = 2;
    this.recoveryPeoridmax = 10;
       virus = new VirusFactoryImpl(incubationPeriodMin, incubationPeriodmax, infectivity,
                 mortality, recoveryPeoridmin, recoveryPeoridmax).createVirus();
    }
}
