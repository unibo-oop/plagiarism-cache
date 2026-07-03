package com.PsyKoMariLabs.PortalJumper;

import com.PsyKoMariLabs.PJHelper.AssetLoader;
import com.PsyKoMariLabs.screens.SplashScreen;
import com.badlogic.gdx.Game;

public class PJGame extends Game {

	@Override
	public void create() {
		AssetLoader.load();
		setScreen(new SplashScreen(this));
	}

	@Override
	public void dispose() {
		super.dispose();
		AssetLoader.dispose();
	}

}