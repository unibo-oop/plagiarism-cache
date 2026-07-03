package view.images;

import java.awt.Image;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.swing.ImageIcon;

import model.games.*;

/**
 * 
 * @author Stefano Benelli
 * Base implementation of Image Set
 */
public abstract class ImageSetBase implements ImageSet {
	
	private static final int BTN_WIDTH = 30;
	private static final int BTN_HEIGHT = 30;

	private static final int HINT_WIDTH = 20;
	private static final int HINT_HEIGHT = 20;
	
	private Map<Choice, ImageIcon> images;
	private Map<Hint, ImageIcon> imagesHints;
	private Optional<ImageIcon> qMarkImage;
	private boolean initalized;
	private boolean initalizedHints;

	protected ImageSetBase() {
		initalized = false;
		initalizedHints = false;
		images = new HashMap<Choice, ImageIcon>();
		imagesHints = new HashMap<Hint, ImageIcon>();
		qMarkImage = Optional.empty();
	}
	
	protected abstract void FillImages(Map<Choice, ImageIcon> images);
	protected abstract void FillImagesHints(Map<Hint, ImageIcon> images);
	protected abstract ImageIcon getQMarkImage();
	
	protected ImageIcon createButtonIcon(String imageFilePath) {
		return this.createImageIcon(imageFilePath, BTN_WIDTH, BTN_HEIGHT);
	}
	
	protected ImageIcon createHintIcon(String imageFilePath) {
		return this.createImageIcon(imageFilePath, HINT_WIDTH, HINT_HEIGHT);
	}

	/*
	 * this method returns an ImageIcon object with the Icon specified by imageFilePath
	 * with the correct size
	 */
	private ImageIcon createImageIcon(String imageFilePath, int width, int height) {
		ImageIcon icon = new ImageIcon(getClass().getResource(imageFilePath));
	
		Image img = icon.getImage() ;  
		img = img.getScaledInstance( width, height,  java.awt.Image.SCALE_SMOOTH ) ;  
		
		return new ImageIcon(img);
	}
	
	public ImageIcon getImage(Choice choice) {
		if(!initalized) {
			FillImages(images);
			initalized=true;
		}
		return images.get(choice);
	}

	public ImageIcon getImage(Hint hint) {
		if(!initalizedHints) {
			FillImagesHints(imagesHints);
			initalizedHints=true;
		}
		return imagesHints.get(hint);
	}
	
	@Override
	public ImageIcon getQuestionMarkImage() {
		if(!qMarkImage.isPresent()) {
			qMarkImage = Optional.of(getQMarkImage());
		}
		return qMarkImage.get();
	}
}
