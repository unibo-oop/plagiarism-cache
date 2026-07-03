package tq2.implementations.graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;

import javax.imageio.ImageIO;

import tq2.interfaces.Animation;

/**
 * The Class Spritesheet.
 * 
 * @author Francesco Gori
 */
public class Spritesheet {
	
	/** The sheet containing the images of the animation. */
	private BufferedImage sheet;
	
	/** The width of the frames. */
	private Integer width;
	
	/** The height of the frames. */
	private Integer height;
	
	/**
	 * Instantiates a new spritesheet.
	 *
	 * @param path the path to the image
	 */
	public Spritesheet(String path) {
		try {
			sheet = ImageIO.read(getClass().getResource(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		this.width = sheet.getWidth();
		this.height = sheet.getHeight();
	}
	
	/**
	 * Extracts an image of the given width and height from the spritesheet.
	 *
	 * @param x the X coordinate where the image starts
	 * @param y the Y coordinate where the image starts
	 * @param width the width of the image
	 * @param height the height of the image
	 * @param offsetX the X offset to assign to the new frame
	 * @param offsetY the Y offset to assign to the new frame
	 * @return the new frame
	 */
	public Frame getFrame(Integer x, Integer y, Integer width, Integer height, Integer offsetX, Integer offsetY) {
		return new Frame (this.sheet.getSubimage(x, y, width, height), offsetX, offsetY);
	}
	
	/**
	 * Returns a sequence of frames of the same dimension.
	 *
	 * @param x the X coordinate where the sequence starts
	 * @param y the Y coordinate where the sequence starts
	 * @param width the width of each frame
	 * @param height the height of each frame
	 * @param offsetX the offset X to assign to each frame
	 * @param offsetY the offset Y to assign to each frame
	 * @param framesNum the number of frames of the sequence
	 * @param speed the speed to assign to the new animation
	 * @param loop the frame to return to once the animation is over
	 * @return the new frame sequence
	 */
	public FrameSequence getFrameSequence (Integer x, Integer y, Integer width, Integer height, Integer offsetX, Integer offsetY, Integer framesNum, Integer speed, Integer loop) {
		
	Integer cursorX = x;
	Integer cursorY = y;
	
	LinkedList<Frame> frameList = new LinkedList<Frame>(); 
	
	for (int i=0; i < framesNum; i++) {
		//At each frame the cursor is moved (from left to right).
		//If the end of the image is reached, the cursor is moved to the start of the next row
		frameList.add(this.getFrame(cursorX, cursorY, width, height, offsetX, offsetY));
		cursorX += width;
		if (cursorX >= this.getWidth()) {
			cursorX = x;
			cursorY += height;
		}
	}

		return new FrameSequence (frameList, speed, loop);
	}
	
	/**
	 * Returns a new animation starting from the specified parameters.
	 *
	 * @param x the X coordinate where to start to extract the frames of the image
	 * @param y the Y coordinate where to start to extract the frames of the image
	 * @param width the width of each frame
	 * @param height the height of each frame
	 * @param offsetX the X offset of each frame
	 * @param offsetY the Y offset of each frame
	 * @param framesNum the number of frames of the animation
	 * @param speed the speed of the animation
	 * @param loop the frame to return to once the animation is over
	 * @return the new animation
	 */
	public Animation getAnim(Integer x, Integer y, Integer width, Integer height, Integer offsetX, Integer offsetY, Integer framesNum, Integer speed, Integer loop) {

		return new AnimationImpl(this.getFrameSequence(x, y, width, height, offsetX, offsetY, framesNum, speed, loop));
	}
	
	
	/**
	 * Returns a new animation starting from the specified parameters.
	 *
	 * @param width the width of each frame
	 * @param height the height of each frame
	 * @param offsetX the X offset of each frame
	 * @param offsetY the Y offset of each frame
	 * @param framesNum the number of frames of the animation
	 * @param speed the speed of the animation
	 * @param loop the frame to return to once the animation is over
	 * @return the new animation
	 */
	public Animation getAnim(Integer width, Integer height, Integer offsetX, Integer offsetY, Integer framesNum, Integer speed, Integer loop) {
		
		return this.getAnim(0, 0, width, height, offsetX, offsetY, framesNum, speed, loop);
	}

	/**
	 * Returns the width of the spritesheet.
	 *
	 * @return the width of the spritesheet
	 */
	public Integer getWidth() {
		return this.width;
	}
	
	/**
	 * Gets the height of the spritesheet.
	 *
	 * @return the heightof the spritesheet
	 */
	public Integer getHeight() {
		return this.height;
	}
}
