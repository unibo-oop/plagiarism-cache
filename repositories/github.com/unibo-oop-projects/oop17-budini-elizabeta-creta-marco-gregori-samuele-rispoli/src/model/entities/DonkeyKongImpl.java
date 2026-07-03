package model.entities;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import controller.GameEngineImpl;

/**
 *
 * An implementation of {@link DonkeyKong}
 */
public class DonkeyKongImpl extends EntityImpl implements StaticEntity, DonkeyKong {

    private final BarrelFactory bf;
    private volatile List<AbstractBarrel> barrelsList;
    private boolean launchingBarrel;
    private final AgentBarrelsCreator barrels;
    private final MovingBarrels barrelsMovement;
    private final static double ZERO = 0.0;
    private final static int MAX_TIME = 2500;
    private final static int STARTING_TIME = 500;
    private final static double STARTING_X_BARREL_POSITION = 75.0;
    private final static double STARTING_Y_BARREL_POSITION = 115.0;
    private final static int BARREL_DIMENSION = 20;
    private final static int DONKEY_SLEEP_TIME = 400;

    public DonkeyKongImpl(final Double x, final Double y, final Dimension dim) {
        super(x, y, dim);
        this.barrels = new AgentBarrelsCreator();
        this.barrelsMovement = new MovingBarrels();
        this.bf = new BarrelFactoryImpl();
        this.barrelsList = new ArrayList<>();
    }

    @Override
    public List<AbstractBarrel> getBarrelsList() {
        return new ArrayList<>(this.barrelsList);
        // TODO change with unmodifiableList
        // return Collections.unmodifiableList(this.barrelsList);
    }

    @Override
    public boolean isLaunchingBarrel() {
        return this.launchingBarrel;
    }

    @Override
    public void startDonkeyKongThreads() {
        barrels.start();
        barrelsMovement.start();
    }

    @Override
    public void stopThreads() {
        this.barrels.stopThread();
        this.barrelsMovement.stopThread();
    }

    /**
     * 
     * An inner class responsible of creating new barrels using a dedicated
     * independent Thread
     *
     */
    private class AgentBarrelsCreator extends Thread {

        private volatile boolean creatingBarrels = true;
        private AbstractBarrel barrel;
        private final Random randomCreationTime = new Random();

        protected AgentBarrelsCreator() {
            super();
        }

        public void run() {

            while (creatingBarrels) {
                this.barrel = DonkeyKongImpl.this.bf.createStandardBarrel(STARTING_X_BARREL_POSITION,
                        STARTING_Y_BARREL_POSITION, new Dimension(BARREL_DIMENSION, BARREL_DIMENSION));
                barrelsList.add(this.barrel);
                this.launchBarrelAndSleep();
                this.barrel = DonkeyKongImpl.this.bf.createClimbingBarrel(STARTING_X_BARREL_POSITION,
                        STARTING_Y_BARREL_POSITION, new Dimension(BARREL_DIMENSION, BARREL_DIMENSION));
                barrelsList.add(this.barrel);
                this.launchBarrelAndSleep();
                this.checkBarrels();
            }
        }

        /* check if a barrel needs to be removed from the list */
        private void checkBarrels() {
            barrelsList = barrelsList.stream().filter(br -> br.getX() > ZERO).collect(Collectors.toList());
        }

        private void launchBarrelAndSleep() {
            DonkeyKongImpl.this.launchingBarrel = true;
            try {
                Thread.sleep(DONKEY_SLEEP_TIME); // sleep to change Sprites of Dk launching barrels
            } catch (InterruptedException ex) {
                this.interrupt();
            }
            DonkeyKongImpl.this.launchingBarrel = false;
            try {
                Thread.sleep(this.randomCreationTime.nextInt(MAX_TIME) + STARTING_TIME);
            } catch (InterruptedException ex) {
                this.interrupt();
            }
        }

        protected void stopThread() {
            this.creatingBarrels = false;
            this.interrupt();
            DonkeyKongImpl.this.barrelsList.clear();
        }
    }

    /**
     * 
     * An inner class responsible of moving each barrels actually created with a
     * dedicated independent Thread
     *
     */
    private class MovingBarrels extends Thread {

        private volatile boolean stopped;

        protected MovingBarrels() {
            super();
        }

        public void run() {
            this.stopped = false;
            while (!stopped) {
                // System.out.println(getBarrelsList().toString());
                try {
                    Thread.sleep(GameEngineImpl.PERIOD);
                } catch (InterruptedException ex) {
                    this.interrupt();
                }
            }
        }

        protected void stopThread() {
            this.stopped = true;
            this.interrupt();
        }

    }

}


