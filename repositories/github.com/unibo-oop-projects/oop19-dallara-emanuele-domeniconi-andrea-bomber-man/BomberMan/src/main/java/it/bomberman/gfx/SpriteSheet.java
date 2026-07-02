package it.bomberman.gfx;

import java.awt.image.BufferedImage;

public class SpriteSheet {
	
	public String Path;
	public int width;
	public int height;
	public int [] pixel;
	private BufferedImage sheet;
	public SpriteSheet(BufferedImage sheet){
		this.sheet=sheet;
	}
	
	public BufferedImage crop(int x, int y, int width, int height) {
		return sheet.getSubimage(x, y, width, height);
	}
}
