package hollowmen.utilities;

import java.util.function.Predicate;

public class ExceptionThrower{

	public static void checkNullPointer(Object o) throws NullPointerException{
		if(o == null) {
			throw new NullPointerException();
		}
	}
	
	public static <T> void checkIllegalState(T obj, Predicate<T> p) throws IllegalStateException{
		if(p.test(obj)) {
			throw new IllegalStateException();
		};
	}
	
	public static <T> void checkIllegalArgument(T obj, Predicate<T> p) throws IllegalArgumentException{
		if(p.test(obj)) {
			throw new IllegalArgumentException();
		};
	}
}
