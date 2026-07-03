package reset;

import javafx.stage.Stage;

public class ResetPopup implements ResetPopupObserver{

	public ResetPopup() {
		init();
	}

	
	@Override
	public void init() {
		ResetPopupViewImpl.getGameView().start(new Stage());
		
	}
}
