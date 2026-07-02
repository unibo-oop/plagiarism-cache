package view;

import java.awt.Image;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.imageio.ImageIO;

import model.entities.SpecificEntityType;
import model.entitiesutil.GenericEntityType;
import model.entitiesutil.MappedEntity;

public class ImageManagerImpl implements UpdateManager{

	private Image playerImage;
	private final Map<SpecificEntityType, Image> gameImages;
	
	/**
	 * The constructor that add all the images for the playerSkin
	 */
	public ImageManagerImpl(String uriSkin) throws IOException {
		this.gameImages = new HashMap<>();
		this.playerImage = ImageIO.read(ClassLoader.getSystemResource(uriSkin));
	}
	
	/**
	 * The method that resized the image.
	 * @param image
	 * @param width
	 * @param heigth
	 * @return
	 */
	private Optional<Image> resizeImage(Image image, int width, int height) {
		return Optional.of(image.getScaledInstance(width, height, Image.SCALE_DEFAULT));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Image drawEntity(MappedEntity entity) throws IOException {
		
		if(entity.getEntityType().getGenericType().equals(GenericEntityType.PLAYER) &&
				(this.playerImage.getWidth(null) != entity.getWidth() ||
				this.playerImage.getHeight(null) != entity.getHeight())) {
				this.playerImage = this.resizeImage(this.playerImage, entity.getWidth(), entity.getHeight()).get();
				this.gameImages.put(SpecificEntityType.PLAYER_1, this.playerImage);
			}
		if(!this.gameImages.containsKey(entity.getEntityType())) {
				this.gameImages.put(entity.getEntityType(), this.getImage(entity.getEntityType(), 
					entity.getWidth(), entity.getHeight()));
		}
		return this.gameImages.get(entity.getEntityType());
	}
	
	/**
	 * Method that returs the resized image for the entitties
	 * @param e
	 * @param width
	 * @param height
	 * @return
	 * @throws IOException
	 */
	private Image getImage(SpecificEntityType e, int width, int height) throws IOException {
		final StringBuilder stringNameBuilder = new StringBuilder();
		stringNameBuilder.append("image/" + e.toString().toLowerCase() + ".png");
		Image image = ImageIO.read(ClassLoader.getSystemResource(stringNameBuilder.toString()));
		return this.resizeImage(image, width, height).get();
	}

}
