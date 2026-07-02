package view.mainmenu;

import java.io.IOException;

import constants.GameConstants;
import javafx.scene.image.ImageView;

public class LayoutImages {
	
	private final static double buttonSize = GameConstants.buttonSize;
	private static final double width = GameConstants.gameWidth;
    private static final double height = GameConstants.gameHeight;
    
	public LayoutImages() throws IOException {
		
	}
	
	public ImageView getimgv1() throws IOException {
		
		ImageLoader imgv1 = new ImageLoader("res/logo.png");
		imgv1.getImage().setFitWidth(buttonSize*13);
		imgv1.getImage().setFitHeight(buttonSize*5);
		imgv1.getImage().setTranslateX(buttonSize*13);
		imgv1.getImage().setTranslateY(buttonSize*2);
		return imgv1.getImage();
	}
	
	public ImageView getImgv2() throws IOException {
		
		ImageLoader imgv2 = new ImageLoader("res/giorno.jpg");
		imgv2.getImage().setFitWidth(buttonSize*39);
		imgv2.getImage().setFitHeight(buttonSize*23);
		imgv2.getImage().setTranslateX(buttonSize*1.45);
		return imgv2.getImage();
	}
	
	public ImageView getImgv3() throws IOException {
		
		ImageLoader imgv3 = new ImageLoader("res/jotaro.png");
		imgv3.getImage().setFitWidth(buttonSize*39);
		imgv3.getImage().setFitHeight(buttonSize*23);
		imgv3.getImage().setTranslateX(buttonSize*1.45);
		return imgv3.getImage();
	}
	
	public ImageView getImgv() throws IOException {
		
		ImageLoader imgv = new ImageLoader("res/62266.jpg");
		imgv.getImage().setFitWidth(width+buttonSize/2);
		imgv.getImage().setFitHeight(height+buttonSize/2);
		return imgv.getImage();
		
	}
	
	public ImageView getImgv7() throws IOException {
		
		ImageLoader imgv7 = new ImageLoader("res/sound.jpg");
		imgv7.getImage().setFitWidth(buttonSize*39);
		imgv7.getImage().setFitHeight(buttonSize*23);
		imgv7.getImage().setTranslateX(buttonSize*1.45);
		return imgv7.getImage();
		
	}
}
