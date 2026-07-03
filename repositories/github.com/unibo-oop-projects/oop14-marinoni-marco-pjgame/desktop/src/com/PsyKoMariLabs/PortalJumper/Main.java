package com.PsyKoMariLabs.PortalJumper;

import com.PsyKoMariLabs.PortalJumper.PJGame;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "Portal Jumper";
		cfg.useGL20 = false;
		cfg.width = 1080 / 2;
		cfg.height = 1920 / 2;
		
		new LwjglApplication(new PJGame(), cfg);
	}
}
