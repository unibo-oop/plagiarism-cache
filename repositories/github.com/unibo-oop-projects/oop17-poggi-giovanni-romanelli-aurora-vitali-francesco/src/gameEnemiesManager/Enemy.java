package gameEnemiesManager;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class Enemy {
	public abstract void update();
	public abstract void drawEnvironment(Graphics env);
	public abstract Rectangle getBound();
	public abstract boolean isOutOfScreen();
}
