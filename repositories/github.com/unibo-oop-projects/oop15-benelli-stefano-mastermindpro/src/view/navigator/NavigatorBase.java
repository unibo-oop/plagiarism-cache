package view.navigator;

import java.util.HashMap;
import java.util.Map;

public abstract class NavigatorBase implements Navigator {

	private final Map<Navigator.NavigationItem, Navigable> views;
	
	private boolean initialized;
	
	protected NavigatorBase() {
		this.views = new HashMap<Navigator.NavigationItem, Navigable>();
		initialized = false;
	}
	
	protected abstract void FillViews(Map<Navigator.NavigationItem, Navigable> views);
	protected Navigable getView(Navigator.NavigationItem item) {
		return views.get(item);
	}
	
	@Override
	public void home() {
		ChangeView(NavigationItem.HOME);
	}

	@Override
	public void newGame() {
		ChangeView(NavigationItem.GAMECONFIG);
	}

	@Override
	public void play() {
		ChangeView(NavigationItem.PLAY);
	}

	@Override
	public void standings() {
		ChangeView(NavigationItem.STANDINGS);
	}

	@Override
	public void quit() {
		System.exit(0);
	}

	private void ChangeView(NavigationItem newView) {

		if(!initialized) {
			FillViews(this.views);
			initialized=true;
		}

		for (Map.Entry<Navigator.NavigationItem, Navigable> entry : views.entrySet()) {
			entry.getValue().setVisible( entry.getKey() == newView );
		}
	}
}
