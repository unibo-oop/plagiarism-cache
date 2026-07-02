package view.mainmenu;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ImageLoader {

	private ImageView imgv1 = new ImageView();
	
	public ImageLoader(String directory) throws IOException {
		
		InputStream is = Files.newInputStream(Paths.get(directory));
		Image logo = new Image(is);
		is.close();
		this.imgv1 = new ImageView(logo);
	}
	
	public ImageView getImage() {
		return this.imgv1;
	}
}
