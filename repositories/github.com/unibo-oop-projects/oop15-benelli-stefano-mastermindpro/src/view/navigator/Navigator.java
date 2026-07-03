package view.navigator;

public interface Navigator {
	public void home();
	public void play();
	public void newGame();
	public void standings();
	public void quit();
	
	enum NavigationItem {
		HOME,
		PLAY,
		STANDINGS,
		GAMECONFIG
	}
}
