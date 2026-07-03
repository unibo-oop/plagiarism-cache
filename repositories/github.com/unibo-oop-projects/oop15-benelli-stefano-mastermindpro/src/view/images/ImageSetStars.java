package view.images;

import java.util.Map;
import javax.swing.ImageIcon;
import model.games.Choice;
import model.games.Hint;

/**
 * 
 * @author Stefano Benelli
 * Implementation of Stars Image Set
 */
public class ImageSetStars extends ImageSetBase {

	private static final String ROOT = "/resources/stars/";
	
	@Override
	protected void FillImages(Map<Choice, ImageIcon> images) {
		images.put(Choice.FIRST, createButtonIcon(ROOT + "red.png"));		
		images.put(Choice.SECOND, createButtonIcon(ROOT + "black.png"));
		images.put(Choice.THIRD, createButtonIcon(ROOT + "blue.png"));
		images.put(Choice.FORTH, createButtonIcon(ROOT + "brown.png"));
		images.put(Choice.FIFTH, createButtonIcon(ROOT + "green.png"));
		images.put(Choice.SIXTH, createButtonIcon(ROOT + "orange.png"));
		images.put(Choice.SEVENTH, createButtonIcon(ROOT + "white.png"));
		images.put(Choice.EIGHTH, createButtonIcon(ROOT + "yellow.png"));
		
		images.put(Choice.NONE, createButtonIcon(ROOT + "empty.png"));
	}
	
	@Override
	protected ImageIcon getQMarkImage() {
		return createButtonIcon(ROOT + "questionMark.png");
	}

	@Override
	protected void FillImagesHints(Map<Hint, ImageIcon> images) {
		images.put(Hint.HIT, createHintIcon(ROOT + "hintHit.png"));
		images.put(Hint.EMPTY, createHintIcon(ROOT + "hintEmpty.png"));
		images.put(Hint.WRONGPOS, createHintIcon(ROOT + "hintWrongPos.png"));
	}
}
