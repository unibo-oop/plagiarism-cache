package boxhead.model.level;

import boxhead.model.entities.zombies.ZombieModel;

/*
 * {@link Round} implementation
 */
public class RoundImpl implements Round {
	
	/**
     * Zombie spawn multiplier for every round
     */
    private static final double SPAWN_MULTIPLIER = 1.5;
    /**
     * Zombies to spawn in the first round
     */
    private static final int STARTING_ZOMBIES = 5;
    /**
     * Time to wait until next round start
     */
    private static final int ROUND_BREAK_TIME = 5000;
    
    
    private int roundCounter;
    private boolean roundIsActive;
    private double roundTimer;
    private double timeToStart;
    private final ZombieModel zombies;
    private int zombiesToSpawn;
    
    
    public RoundImpl(final ZombieModel zombies) {
        this.roundCounter = 0;
        this.roundIsActive = false;
        this.roundTimer = System.currentTimeMillis();
        this.timeToStart = 0;
        this.zombies = zombies;
        this.zombiesToSpawn = STARTING_ZOMBIES;

    }
    
    /**
     * {@inheritDoc}
     */
	@Override
	public int getCurrentRound() {
		return this.roundCounter;
	}

	/**
     * {@inheritDoc}
     */
	@Override
	public boolean isRoundActive() {
		return this.roundIsActive;
	}
	
	/*
	 * Increments the round number
	 */
	private void nextRound() {
		this.roundCounter++;
		this.multipliedZombies();
	}
	
	/*
	 * Computes with a multiplier the number of zombies to spawn every round
	 */
	private void multipliedZombies() {
		this.zombiesToSpawn *= SPAWN_MULTIPLIER;
	}

	/**
     * {@inheritDoc}
     */
	@Override
	public double getStartingTime() {
		return this.timeToStart;
	}

	/**
     * {@inheritDoc}
     */
	@Override
	public int getZombiesToSpawn() {
		return this.zombiesToSpawn;
	}
	
	/*
	 * Manage next round start
	 */
	private void roundStart() {
		this.roundIsActive = true;
		this.nextRound();
		this.zombies.setZombiesToSpawn(this.getZombiesToSpawn());
	}
	
	/*
	 * Manage the end of a round
	 */
	private void roundEnd() {
		this.roundTimer = System.currentTimeMillis();
		this.roundIsActive = false;
	}
	
	/**
     * {@inheritDoc}
     */
	@Override
	public void update() {
        if (!roundIsActive) {
            this.timeToStart = ROUND_BREAK_TIME - (System.currentTimeMillis() - roundTimer);
            if (this.timeToStart <= 0) {
                this.roundStart();
            }
        }
        if ((this.zombies.getZombiesCount() == 0) && roundIsActive) {
            this.roundEnd();
        }
	}

}
