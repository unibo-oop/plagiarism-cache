package com.PsyKoMariLabs.PortalJumper.client;

import com.PsyKoMariLabs.PortalJumper.PJGame;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;

public class GwtLauncher extends GwtApplication {
	@Override
	public GwtApplicationConfiguration getConfig () {
		GwtApplicationConfiguration cfg = new GwtApplicationConfiguration(1080/4, 1020/4);
		return cfg;
	}

	@Override
	public ApplicationListener getApplicationListener () {
		return new PJGame();
	}
}