package hollowmen.utilities;

import java.util.stream.IntStream;

public class RandomSelector {

	private static final float DIGIT = 10^5;
	
	public static <T> T getAnyFrom(T[] vett) {
		int i = (int) (Math.random() * DIGIT) % vett.length;
		return vett[i];
	}
	
	public static int getIntFromRange(int lowerBound, int upperBound) {
		int[] vett = IntStream.rangeClosed(lowerBound, upperBound).toArray();
		int i = (int) (Math.random() * DIGIT) % vett.length;
		return vett[i];
	}
	
	
}
