package it.bomberman.gfx;

import java.awt.image.BufferedImage;

public class Assets {
	// Immagini dello sheet
	private static final int width = 49, height = 49, heightHud = 22;
	public static BufferedImage simpleWall, deathWall, wall;

	// buffer p1
	public static final BufferedImage[] player_d, player_u, player_r, player_l, player_bomb;
	// buffer p2
	public static final BufferedImage[] player_d2, player_u2, player_r2, player_l2, player_bomb2;

	// buffer bomb
	public static final BufferedImage[] bomb;

	public static final BufferedImage[] hud;

	public static final BufferedImage[] explosion;

	public static final BufferedImage[] upgrade;

	static {
		SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/SpAll.png"));
		player_u = new BufferedImage[2];
		player_d = new BufferedImage[2];
		player_r = new BufferedImage[3];
		player_l = new BufferedImage[3];
		player_bomb = new BufferedImage[2];
		player_bomb2 = new BufferedImage[2];

		// player_d[0]= sheet.crop(0, 0, width, height);
		player_d[0] = sheet.crop(width, 0, width, height);
		player_d[1] = sheet.crop(width * 2, 0, width, height);

		// player_u[0]= sheet.crop(width*3, 0, width, height);
		player_u[0] = sheet.crop(width * 4, 0, width, height);
		player_u[1] = sheet.crop(width * 5, 0, width, height);

		player_r[1] = sheet.crop(width * 6, 0, width, height);
		player_r[0] = sheet.crop(width * 7, 0, width, height);
		player_r[2] = sheet.crop(width * 8, 0, width, height);

		player_l[1] = sheet.crop(width * 9, 0, width, height);
		player_l[0] = sheet.crop(width * 10, 0, width, height);
		player_l[2] = sheet.crop(width * 11, 0, width, height);

		player_bomb[1] = sheet.crop(width * 12, 0, width, height);
		player_bomb[0] = sheet.crop(width * 13, 0, width, height);

		// Player 2

		player_u2 = new BufferedImage[2];
		player_d2 = new BufferedImage[2];
		player_r2 = new BufferedImage[3];
		player_l2 = new BufferedImage[3];

		player_d2[0] = sheet.crop(width, height, width, height);
		player_d2[1] = sheet.crop(width * 2, height, width, height);

		// player_u[0]= sheet.crop(width*3, 0, width, height);
		player_u2[0] = sheet.crop(width * 4, height, width, height);
		player_u2[1] = sheet.crop(width * 5, height, width, height);

		player_r2[1] = sheet.crop(width * 6, height, width, height);
		player_r2[0] = sheet.crop(width * 7, height, width, height);
		player_r2[2] = sheet.crop(width * 8, height, width, height);

		player_l2[1] = sheet.crop(width * 9, height, width, height);
		player_l2[0] = sheet.crop(width * 10, height, width, height);
		player_l2[2] = sheet.crop(width * 11, height, width, height);

		player_bomb2[1] = sheet.crop(width * 12, height, width, height);
		player_bomb2[0] = sheet.crop(width * 13, height, width, height);

		// wall=sheet.crop(x, y, width, height);
		// bomb=sheet.crop(x, y, width, height);

		bomb = new BufferedImage[6];
		for (int i = 0; i < bomb.length; i++)
			bomb[i] = sheet.crop(width * i, height * 3, width, height);
		// upgrade=sheet.crop(x, y, width, height);

		hud = new BufferedImage[2];
		hud[0] = sheet.crop(2, 7, heightHud, heightHud);
		hud[1] = sheet.crop(width + 2, height + 7, heightHud, heightHud);

		explosion = new BufferedImage[3];
		explosion[0] = sheet.crop(width * 5, height * 3, width, height);
		explosion[1] = sheet.crop(width * 6, height * 3, width, height);
		explosion[2] = sheet.crop(width * 7, height * 3, width, height);

		simpleWall = sheet.crop(width * 8, height * 3, width, height);
		wall = sheet.crop(width * 9, height * 3, width, height);
		deathWall = sheet.crop(width * 10, height * 3, width, height);

		upgrade = new BufferedImage[4];
		for (int i = 0; i < 4; i++) {
			upgrade[i] = sheet.crop(width * (11 + i), height * 3, width - 2, height);
		}
	}
}
