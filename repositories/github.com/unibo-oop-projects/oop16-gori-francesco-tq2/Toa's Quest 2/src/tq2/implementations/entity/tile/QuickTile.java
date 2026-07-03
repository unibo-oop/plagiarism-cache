package tq2.implementations.entity.tile;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;

import javax.imageio.ImageIO;

import tq2.implementations.Id;
import tq2.implementations.graphics.AnimationImpl;
import tq2.implementations.graphics.Frame;
import tq2.implementations.graphics.FrameSequence;
import tq2.interfaces.Handler;
import tq2.interfaces.LevelLayer;

/**
 * Given the path to an image, the class QuickTile will create a tile using that resource. The size will automatically fit the image.
 * It's useful for background tiles or object that don't require having their own id.
 * 
 * @author Francesco Gori
 */
public class QuickTile extends Tile {

	/**
	 * Instantiates a new QuickTile.
	 *
	 * @param x the X coordinate of the object
	 * @param y the Y coordinate of the object
	 * @param pathToImage the path to the image
	 * @param solid whether the object is solid
	 * @param enabled whether the object is enabled
	 * @param scaleX the scale of the object along the X axis
	 * @param scaleY the scale of the object along the Y axis
	 * @param alpha the alpha value of the object
	 * @param handler the Handler
	 * @param layer the layer this object resides in
	 */
	public QuickTile(
			Integer x, Integer y,
			String pathToImage,
			Boolean solid, Boolean enabled,
			Double scaleX, Double scaleY,
			Float alpha,
			Handler handler, LevelLayer layer) {
		super(x, y, 0, 0, 1, solid, enabled, scaleX, scaleY, 0.0, 0.0,
				alpha, handler, layer);
		
		this.id = Id.quickTile;
		
		BufferedImage image = null;
		
		
		try {
			image = ImageIO.read(getClass().getResource(pathToImage));
			this.width = (image.getWidth());
			this.height = (image.getHeight());
			this.setY(this.getY()-this.getHeight());
			
			LinkedList<Frame>list = new LinkedList<Frame>();
			list.add(new Frame(image));
			this.animations.put("Idle", new AnimationImpl(new FrameSequence(list, 60, 0)));
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("Image " + pathToImage + " not found.");
		}

	}
	
	/**
	 * Instantiates a new QuickTile.
	 *
	 * @param x the X coordinate of the object
	 * @param y the Y coordinate of the object
	 * @param pathToImage the path to the image
	 * @param solid whether the object is solid
	 * @param handler the Handler
	 * @param layer the layer this object resides in
	 */
	public QuickTile (
		Integer x, Integer y,
		String pathToImage,
		Boolean solid,
		Handler handler, LevelLayer layer) {
		
		this(x, y, pathToImage, solid, true, 1.0, 1.0, 1.0f, handler, layer);
	}
}
