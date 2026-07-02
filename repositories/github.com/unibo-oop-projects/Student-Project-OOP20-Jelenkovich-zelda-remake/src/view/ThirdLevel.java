package view;

import controller.HeroGoControllerLv3;
import javafx.stage.Stage;

public class ThirdLevel implements Ilevel{

	public ThirdLevel() {
		new HeroGoControllerLv3(this.start());
	}

	@Override
	public Stage start() {
		ViewManager manager = new ViewManager();
    	Stage stage = new Stage();
    	stage = manager.getMainStage();
    	stage.setTitle("Zelda");
    	stage.setResizable(false);
    	stage.show();
    	return stage;
    }
}
