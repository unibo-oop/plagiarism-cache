package model.virus;

import java.util.Random;

/**
 *Class that model the virus creator.
 */
public class VirusFactoryImpl implements VirusFactory {

    private static final int QUANTUM_IN_A_DAY = 24;
    private final int minIncubationPeriod;
    private final  int maxIncubationPeriod;
    private final double infectivity;
    private final double mortality;
    private final int minRecoveryPeriod;
    private final int maxRecoveryPeriod;

    public VirusFactoryImpl(final int minIncubationPeriod, final int maxIncubationPeriod, final double infectifity, 
            final double mortality, final int minRecoveryPeriod, final int maxRecoveryPeriod) {
        this.minIncubationPeriod = minIncubationPeriod;
        this.maxIncubationPeriod = maxIncubationPeriod;
        this.infectivity = infectifity;
        this.mortality = mortality;
        this.minRecoveryPeriod = minRecoveryPeriod;
        this.maxRecoveryPeriod = maxRecoveryPeriod;
    }

    /**
     *
     */
    @Override
    public Virus createVirus() {

        final Random rand = new Random();
        final int recoveryPeriod = minRecoveryPeriod + rand.nextInt(maxRecoveryPeriod - minRecoveryPeriod + 1);
        final int incubationPeriod = minIncubationPeriod + rand.nextInt(maxIncubationPeriod - minIncubationPeriod + 1);

        return new Virus() {

            @Override
            public int getRecoveryPeriod() {
                return recoveryPeriod * QUANTUM_IN_A_DAY;
            }

            @Override
            public double getMortality() {
                return mortality;
            }

            @Override
            public double getInfectivity() {
                return infectivity;
            }

            @Override
            public int getIncubationPeriod() {
                return incubationPeriod * QUANTUM_IN_A_DAY;
            }

            @Override
            public Virus duplicate() {
                return createVirus();
            }
        };
    }

}
