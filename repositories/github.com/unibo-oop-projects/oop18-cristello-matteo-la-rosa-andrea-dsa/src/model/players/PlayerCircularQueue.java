package model.players;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Queue;

/**
 * This class manage the list of player that are in game and not yet returned on the boat.
 */

public class PlayerCircularQueue extends ArrayList<Player> implements Queue<Player> {

	
	private static final long serialVersionUID = 1L;
	private final Integer maxSize;
	
	/**
	 * Constructor of the class 
	 * 
	 * @param maxSize
	 *                 the maximum size of accptable element of the player circular queue.
	 */
	
	public PlayerCircularQueue(final Integer maxSize) {
		super();
		this.maxSize = maxSize;
	}
	
	/**
	 * return the head element of the PlayerCircularQueue
	 */
    @Override
    public Player element() {
        if(this.isEmpty()) {
        	throw new NoSuchElementException();
        }
        return this.get(0);
    }
    
    /**
     * add the gived player to the PlayerCircularQueue.
     */

    @Override
    public boolean offer(final Player player) throws NoSuchElementException {
        if(this.size() >= this.maxSize) {
        	return false;
        }else {
        	this.add(player);
        	return true;
        }
        
    }

    /**
     * return the head of the queue (can return null)
     */
    @Override
    public Player peek() {
        if(this.isEmpty()) {
        	return null;
        }else {
        	return this.get(0);
        }
    }

    /**
     * return and remove the head of the PlayerCircularQueue and add it at the end of the queue.
     */
    @Override
    public Player poll() {
        final Player tmpPlayer;
        if(this.isEmpty()) {
        	return null;
        }else {
        	tmpPlayer = this.get(0);
        	this.remove(0);
        	this.offer(tmpPlayer);
        	return tmpPlayer;
        }
        
    }

    /**
     * remove from the queue the first element
     */
    @Override
    public Player remove() throws NoSuchElementException {
        if(this.isEmpty()) {
        	throw new NoSuchElementException();
        }
        return this.poll();
    }
    
    /**
     * remove the specific player from the queue and return it 
     * @param player
     *               the player to remove
     * @return the removed player
     * @throws NoSuchElementException 
     * 	                              it's throwed if the PlayerCircularQueue is empty when call.
     */
    public Player removeFromQueue(final Player player) throws NoSuchElementException {
        if(this.isEmpty()) {
        	throw new NoSuchElementException();
        	
        }
        final Player tmpPlayer = player;
        this.remove(player);
        return tmpPlayer;
    }
}
