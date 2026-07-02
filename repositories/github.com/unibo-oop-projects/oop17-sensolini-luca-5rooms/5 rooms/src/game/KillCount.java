package game;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

public class KillCount extends GameObject implements Listener{
    /**
	 * Counts the number of enemy kills, triggers a win if the goal is met
	 */

	private static ID id = ID.GUI;
	private int goal;

	public KillCount(int x, int y, int goal) {
		super(x, y, id);
		Controller.getInstance().listenDeletion(this, ID.Enemy);
		this.goal = goal;
	}

	@Override
	public void tick() {
		if(goal <= 0) Controller.getInstance().SuccessState();
	}

	@Override
	public void render(Graphics g) {
		g.drawString("Remaining : " + goal, x, y);
	}

	@Override
	public Rectangle getBounds() {
		return null;
	}

	@Override
	public void notification() {
		if(Controller.getInstance().getObject(id).contains(this)) goal--;
	}

}
