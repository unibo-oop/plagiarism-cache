package view;

import static view.ViewUtils.getLastVista;
import static view.ViewUtils.setVista;

import javafx.scene.Scene;

public class Vista {
	
	public void goBack(Scene scene) {
		setVista(scene, getLastVista(), false);
	}

}
