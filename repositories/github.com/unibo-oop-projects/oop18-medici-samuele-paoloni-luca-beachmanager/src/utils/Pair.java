package utils;

/**
 * 
 * @author Samuele Medici, samuele.medici2@studio.unibo.it ( Mat. 0000718877 )
 *
 * @param <T1>
 * @param <T2>
 */
public class Pair<T1, T2> {
	
	private T1 first;
	private T2 second;
	/**
	 * Costruttore per la classe Pair
	 * 
	 * @param first
	 * @param second
	 */
	public Pair(T1 first, T2 second) {
		this.first = first;
		this.second = second;
	}
	
	/**
	 * @return first
	 */
	public T1 getFirst() {
		return first;
	}
	public void setFirst(T1 first) {
		this.first = first;
	}
	public T2 getSecond() {
		return second;
	}
	public void setSecond(T2 second) {
		this.second = second;
	}

}
