package tq2.implementations.graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * The Class Frame contains a BufferedImage and two values that represent the X and Y offsets of the image. These values are to
 *  be added to the coordinates of the image before drawing it.
 *  
 *  @author Francesco Gori
 */
public class Frame {
	
	/** The offset of the image along the X axis. */
	private Integer offsetX;
	
	/** The offset of the image along the Y axis. */
	private Integer offsetY;
	
	/** The image contained in this object. */
	public BufferedImage image;
	
	/**
	 * Instantiates a new Frame containing the specified image and the specified X and Y offsets.
	 *
	 * @param image the image
	 * @param offsetX the offset X
	 * @param offsetY the offset Y
	 */
	public Frame (BufferedImage image, Integer offsetX, Integer offsetY) {
		
		this.image = image;
		this.offsetX = offsetX;
		this.offsetY = offsetY;
	}
	
	/**
	 * Instantiates a new Frame containing the specified image. The X and Y offsets will be set to 0.
	 *
	 * @param image the image
	 */
	public Frame (BufferedImage image) {
		this(image, 0, 0);
	}
	
	/**
	 * Instantiates a new Frame importing the image from the given path.
	 *
	 * @param path the path
	 */
	public Frame (String path) {
		try {
			
			BufferedImage image;
			
			image = ImageIO.read(getClass().getResource(path));
			
			this.image = image;
			this.offsetX = 0;
			this.offsetY = 0;
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Returns the image contained in this object.
	 *
	 * @return the buffered image
	 */
	public BufferedImage getBufferedImage() {
		return image;
	}
	
	/**
	 * Returns the width of the image contained in this object.
	 *
	 * @return the width of the image
	 */
	public Integer getWidth() {
		return this.image.getWidth();
	}
	
	/**
	 * Returns the height of the image contained in this object.
	 *
	 * @return the height of the image
	 */
	public Integer getHeight() {
		return this.image.getHeight();
	}
	
	/**
	 * Returns the offset of the image along the X axis.
	 *
	 * @return the X offset
	 */
	public Integer getOffsetX() {
		return this.offsetX;
	}
	
	/**
	 * Returns the offset of the image along the Y axis.
	 *
	 * @return the Y offset
	 */
	public Integer getOffsetY() {
		return this.offsetY;
	}
}
