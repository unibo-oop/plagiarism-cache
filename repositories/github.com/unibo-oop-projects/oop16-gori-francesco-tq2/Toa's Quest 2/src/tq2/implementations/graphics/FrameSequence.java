package tq2.implementations.graphics;

import java.util.LinkedList;

/**
 * The Class FrameSequence contains a lists of frames and a value that represents the number
 * of updates the each frame of the animations will last.
 * 
 * @author Francesco Gori
 */

public class FrameSequence {
	
	/** The number of updates each frame of the animation will last. */
	public Integer speed;	
	
	/** The list of frames. */
	public LinkedList<Frame> frames;
	
	/** The index of the frame to return to once reached the end of the sequence. */
	public Integer loop;

	/**
	 * Instantiates a new frame sequence.
	 *
	 * @param frames the frames
	 * @param speed the speed
	 * @param loop the loop
	 */
	public FrameSequence (LinkedList<Frame> frames, Integer speed, Integer loop) {
		this.speed = speed;
		this.frames = frames;
		this.loop = loop;
	}
	
	/**
	 * Returns the number of frames contained in this object.
	 *
	 * @return the number of frames
	 */
	public Integer size() {
		return this.frames.size();
	}
	
	/**
	 * Returns the frame corresponding to the specified index.
	 *
	 * @param index the index
	 * @return the corresponding frame
	 */
	public Frame get(Integer index) {
		return this.frames.get(index);
	}
	
	/**
	 * Returns the number of updates each frame lasts.
	 *
	 * @return the number of updates each frame lasts.
	 */
	public Integer getSpeed() {
		return this.speed;
	}
	
	/**
	 * Returns the width of the first frame of the sequence.
	 *
	 * @return the width of the first frame
	 */
	public Integer getWidth() {
		return this.frames.get(0).getWidth();
	}
	
	/**
	 * Returns the height of the first frame of the sequence.
	 *
	 * @return the height of the first frame
	 */
	public Integer getHeight() {
		return this.frames.get(0).getHeight();
	}
	
	/**
	 * Returns the index to return to after the end of the sequence has been reached.
	 *
	 * @return the index to return to
	 */
	public Integer getLoop() {
		return this.loop;
	}
}


