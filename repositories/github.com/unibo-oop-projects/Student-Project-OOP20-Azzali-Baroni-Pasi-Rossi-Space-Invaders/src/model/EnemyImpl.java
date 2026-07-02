package model;

import java.util.LinkedList;
import java.util.List;

/**
 * The Class EnemyImpl to check enemies's coordinate, implements {@link Enemy}.
 */
public class EnemyImpl implements Enemy{
	
	/** The list enemy X. */
	private final List<Integer> listEnemyX;
	
	/** The list enemy Y. */
	private final List<Integer> listEnemyY;
	
	/** The hit. */
	private final int hit; 
	
	/**
	 * Constructor of {@link EnemyImpl}
	 *
	 * @param hit hitbox
	 */
	public EnemyImpl(final int hit) {
		this.hit = hit;
		listEnemyX = new LinkedList<>();
		listEnemyY = new LinkedList<>();
	}

	/**
	 * Method of {@link Enemy}.
	 *
	 * @param n to check.
	 * @return true, if successful.
	 */
	@Override
	public boolean tiedupX(final int n) {
		for (final int x : listEnemyX) {
			if (x == n || (x >= n - hit && x <= n + hit)) {
				return true;
			}
		}
		
		return false;		
	}

	/**
	 * Method of {@link Enemy}.
	 *
	 * @param n to check.
	 * @return true, if successful.
	 */
	@Override
	public boolean tiedupY(final int n) {
		for (final int x : listEnemyY) {
			if (x == n || (x >= - hit && x <= n + hit)) {}
			return true;
		}
		return false;
	}

	/**
	 * Delete list.
	 */
	@Override
	public void deleteList() {
		listEnemyX.clear();
		listEnemyY.clear();
		
		// TODO Auto-generated method stub
		
	}

	/**
	 * Method of {@link Enemy}
	 *
	 * @param list list to add the number n to
	 * @param n number to check
	 */
	@Override
	public void addNumber(boolean list, int n) {
		if(list) {
			listEnemyX.add(n);
			}
		else {
			listEnemyY.add(n);
		}
		
		
		// TODO Auto-generated method stub
		
	}
	

	}
	
	


