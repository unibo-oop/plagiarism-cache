package com.game.support;

import java.awt.Font;
public class Fonts
{
	public Font newFont(int size){
	Font f = null;
		{
	try{
		f = Font.createFont(Font.TRUETYPE_FONT, this.getClass().getClassLoader().getResourceAsStream("Pixeled.ttf")).deriveFont(Font.PLAIN, size);
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
	return f;
	}
}
