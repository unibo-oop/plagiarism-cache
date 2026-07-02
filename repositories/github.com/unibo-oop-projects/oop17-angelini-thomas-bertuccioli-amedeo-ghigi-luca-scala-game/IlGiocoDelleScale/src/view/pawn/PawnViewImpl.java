package view.pawn;


import enumeration.Characters;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import model.pawns.Pawns;
import model.model.ModelImpl;
import enumeration.Characters;


public class PawnViewImpl extends ImageView implements PawnView {

	public static final int STANDARD_DIM = 20;
	private static final double SPACE_BOX = 2.5;
	private final Characters character;
	private final Pawns pawn;
	private static final int START = 0;
	private static int pos;
	
	
	public PawnViewImpl(final Characters character, final Pawns p) {
		super();
		this.pawn = p;
		this.pos = START;
		this.character = character;
		Tooltip.install(this, new Tooltip(character.toString()));
		this.setPreserveRatio(true);
		this.setFitHeight(STANDARD_DIM);
		switch(this.getPawn()) {
		case Baloo:
			this.setImage(new Image(this.getClass().getResourceAsStream("/Pawns/Balooo.png")));
			break;
		case Baghera:
			this.setImage(new Image(this.getClass().getResourceAsStream("/Pawns/BagheraLaPanteraNera.png")));
			break;
		case ShereKhan:
			this.setImage(new Image(this.getClass().getResourceAsStream("/Pawns/shereKhan.png")));
			break;
		case KingLouie:
			this.setImage(new Image(this.getClass().getResourceAsStream("/Pawns/ReLuigi.png")));
			break;
		default:
			this.setImage(new Image(this.getClass().getResourceAsStream("/Pawns/BagheraLaPanteraNera.png")));
			break;
		}
		
	}
	
	
	@Override
	public Characters getPawn() {
		return this.character;
	}
	
	
	public void SetInitPos() {
		this.pos = START;
	}


	@Override
	public int getPosition() {
		return this.pos;
	}

	public void UpDate(int NewPos) {
		
	}

}
