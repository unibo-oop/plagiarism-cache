package controller.gameStatusManager;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import controller.GameStatus;

public class GameStatusManagerImpl implements ViewGameStatusManager, ControllerGameStatusManager {

	private final ReentrantLock lock;
	private final Condition condition;

	private GameStatus gameState;

	public GameStatusManagerImpl() {
		this.lock = new ReentrantLock();
		this.condition = this.lock.newCondition();
		this.gameState = GameStatus.STOPPED;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void pause(){
		this.lock.lock();
		try{
			if(!this.gameState.equals(GameStatus.PAUSED) && !this.gameState.equals(GameStatus.STOPPED)) {
				this.gameState = GameStatus.PAUSED;
			}
		}
		finally {
			this.lock.unlock();
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void resume(){
		this.lock.lock();
		try{
			if(this.gameState.equals(GameStatus.PAUSED) && !this.gameState.equals(GameStatus.STOPPED)) {
				this.gameState = GameStatus.RESUMED;
				this.condition.signal();	
			}
		}
		finally {
			this.lock.unlock();
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void restart() {
		this.lock.lock();
		try{
			if(this.gameState.equals(GameStatus.PAUSED) 
					&& !this.gameState.equals(GameStatus.STOPPED)) {
				this.gameState = GameStatus.RESTARTED;
				this.condition.signal();
			}
		}
		finally {
			this.lock.unlock();
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void stop(){
        this.lock.lock();
        try{
        	if(!this.gameState.equals(GameStatus.STOPPED)
        			&& this.gameState.equals(GameStatus.PAUSED)){
            	this.gameState = GameStatus.STOPPED;
            	this.condition.signal();
            }
        }
        finally {
            this.lock.unlock();
        }
    }

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void isGamePaused() {
		 this.lock.lock();
	     try {
	    	 if(this.gameState.equals(GameStatus.PAUSED) && !this.gameState.equals(GameStatus.STOPPED)) {
					try {
						this.condition.await();
					} catch (InterruptedException e) {}
				}
	     }
	     finally {
	    	 this.lock.unlock();
	     }
	}

	/**
	 * {@inheritDoc}
	 */
	public GameStatus getGameStatus() {
		this.lock.lock();
		try {
			return this.gameState;
		}
		finally{
			this.lock.unlock();
		}
	}

    /**
	 * {@inheritDoc}
	 */
    @Override
    public void setResume() {
    	this.lock.lock();
    	try {
    		this.gameState = GameStatus.RUNNING;
    	}
    	finally {
    		this.lock.unlock();
    	}
    }

    /**
   	 * {@inheritDoc}
   	 */
	@Override
	public void setStart() {
		this.lock.lock();
    	try {
    		this.gameState = GameStatus.RUNNING;
    	}
    	finally {
    		this.lock.unlock();
    	}
	}

	/**
   	 * {@inheritDoc}
   	 */
	@Override
	public void setStop() {
		this.lock.lock();
    	try {
    		this.gameState = GameStatus.STOPPED;
    	}
    	finally {
    		this.lock.unlock();
    	}
	}
}
