package tq2.interfaces;

import tq2.implementations.graphics.Frame;
import tq2.implementations.graphics.FrameSequence;

/**
 * The Interface Animation.
 * 
 * @author Francesco Gori
 */
public interface Animation {

	/**
	 * Gets the frame sequence relative to the specified direction
	 *
	 * @param facing the direction of the desired frame sequence
	 * @return the frame sequence
	 */
	public abstract FrameSequence getFrameSequence(Integer facing);

	/**
	 * Gets the specified frame from the frame sequence relative to the specified direction.
	 *
	 * @param index the position of the frame in the frame sequence
	 * @param facing the direction
	 * @return the frame
	 */
	public abstract Frame getFrame(Integer index, Integer facing);

	/**
	 * Returns the size of the frame sequence relative to the desired direction.
	 *
	 * @param facing the facing
	 * @return the integer
	 */
	public abstract Integer size(Integer facing);

	/**
	 * Gets the speed of the frame sequence relative to the desired direction.
	 *
	 * @param facing the facing
	 * @return the speed
	 */
	public abstract Integer getSpeed(Integer facing);

	/**
	 * Returns the width of the first frame of the sequence relative to the desired direction.
	 *
	 * @param facing the direction
	 * @return the width
	 */
	public abstract Integer getWidth(Integer facing);

	/**
	 * Gets the height of the first frame of the sequence relative to the desired direction.
	 *
	 * @param facing the direction
	 * @return the height
	 */
	public abstract Integer getHeight(Integer facing);

	/**
	 * Gets the number of times the animation will play before ending.
	 *
	 * @param facing the direction of the desired frame sequence
	 * @return the number of loops
	 */
	public abstract Integer getLoop(Integer facing);

	/**
	 * Checks if the left frame sequence and the right are the same.
	 *
	 * @return if the animation is symmetrical or not
	 */
	public abstract Boolean isSymmetrical();

}