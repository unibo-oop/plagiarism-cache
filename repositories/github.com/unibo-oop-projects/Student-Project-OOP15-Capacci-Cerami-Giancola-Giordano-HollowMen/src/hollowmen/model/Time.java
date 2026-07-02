package hollowmen.model;

import java.util.function.Consumer;

/**
 * This interface represents the time inside the game,
 * since there are many time-dependent-event internally <br>
 * This time is updated each time adding the <b>deltaTime</b> each time {@code update(long deltaTime)}
 * is called in {@link Dungeon}
 * @author pigio
 *
 */
public interface Time  extends LimitedCounter{

	/**
	 * This method apply the <b>action</b> on the
	 * <b>subj</b> after <b>duration</b> in seconds
	 * 
	 * @param subj who will be consumed by the action
	 * @param duration after this time express in seconds
	 * @param action the action
	 */
	public <T> void register(T subj, double duration, Consumer<T> action);
}
