package view;

import controller.HeroGoControllerLv2;
import javafx.stage.Stage;

public class SecondLevel implements Ilevel{
	
	public SecondLevel() {
		new HeroGoControllerLv2(this.start());
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
