package tq2.implementations.input;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import tq2.implementations.Id;
import tq2.implementations.entity.active.actor.*;
import tq2.implementations.level.TQLevel;
import tq2.interfaces.Entity;
import tq2.interfaces.Handler;
import tq2.interfaces.LevelLayer;
import tq2.interfaces.platform.tqgame.TQPlayer;

/**
 * The Class PlatformControls is both a MouseListener and a KeyListener, and contains the controls
 * for the first level of the game Toa's Quest 2.
 * 
 * @author Francesco Gori
 */
public class PlatformControls extends ControlsImpl {
	
	
	/**
	 * Instantiates a new PlatformControls object.
	 *
	 * @param handler the Handler
	 * @param tqLevel the level this object is relative to
	 */
	public PlatformControls(Handler handler, TQLevel tqLevel) {
		super(handler, tqLevel);
	}
	
	/* (non-Javadoc)
	 * @see com.tq2.implementations.input.ControlsImpl#keyPressed(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		super.keyPressed(e);
		Integer key = e.getKeyCode();
		
		if (key == KeyEvent.VK_P)  {
			this.getHandler().pause();
		}
		
		else
		for (int i=0;i<this.getHandler().getLayers().size();i++) {
			LevelLayer l = this.getHandler().getLayers().get(i);
			for (int j=0; j<l.size(); j++) {
				Entity a = l.get(j);
				
				//PLAYER CONTROLS
				if (!this.getHandler().isPaused()) {
				if (a.getId() == Id.player) {
					TQPlayer currentPlayer = (TQPlayer) a;
					
					
					if (this.getLevel().isControlsEnabled()) {
						if (currentPlayer.isMovementEnabled()) {
							switch(key) {
							
							case KeyEvent.VK_SHIFT:
								currentPlayer.setWalking(true);
								break;
								
							case KeyEvent.VK_W:
								currentPlayer.jump();
								
								break;
							case KeyEvent.VK_S:
								currentPlayer.setCrouching(true);
								
								break;
							case KeyEvent.VK_A:
								currentPlayer.setFacing(-1);
								((Tahu)currentPlayer).setRunning(true);

								break;
							case KeyEvent.VK_D:
								currentPlayer.setFacing(1);
								((Tahu)currentPlayer).setRunning(true);
								break;
							}
						}
						switch(key) {
							case KeyEvent.VK_C:
								if (currentPlayer.isFalling()) {
									currentPlayer.setActiveKanohi(3);
								}
								else {
									currentPlayer.setActiveKanohi(1);
								}
								break;
								
							case KeyEvent.VK_M:
								currentPlayer.setActiveKanohi(2);
								break;
							}
	
							switch(key) {
							case KeyEvent.VK_SPACE:
								currentPlayer.shoot();
								break;
							
							case KeyEvent.VK_X:
								currentPlayer.attack();
								break;
							}
						}
					}
				}
			}
		}
	}

	/* (non-Javadoc)
	 * @see com.tq2.implementations.input.ControlsImpl#keyReleased(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyReleased(KeyEvent e) {
		
		Integer key = e.getKeyCode();
		
		for (LevelLayer l:this.getHandler().getLayers()) {
			for (int i=0;i<l.size();i++) {
				Entity a = l.get(i);
				
				if (a.getId() == Id.player
						) {
					TQPlayer currentPlayer = (TQPlayer) a;

					switch(key) {

					case KeyEvent.VK_S:
						currentPlayer.setCrouching(false);
						break;
					case KeyEvent.VK_A:
						((Tahu)currentPlayer).setRunning(false);
						break;
						
					case KeyEvent.VK_D:
						((Tahu)currentPlayer).setRunning(false);
						break;
						
					case KeyEvent.VK_SHIFT:
						currentPlayer.setWalking(false);
						break;

					
					case KeyEvent.VK_C:
						currentPlayer.deactivateKanohi();
						break;
						
					case KeyEvent.VK_M:
						currentPlayer.deactivateKanohi();
						break;
					}
				}
			}
		}
	}

	/* (non-Javadoc)
	 * @see com.tq2.implementations.input.ControlsImpl#keyTyped(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyTyped(KeyEvent arg0) {
		//Not used
	}
	
	/* (non-Javadoc)
	 * @see com.tq2.implementations.input.ControlsImpl#getLevel()
	 */
	public TQLevel getLevel() {
		return (TQLevel) this.level;
	}

	/* (non-Javadoc)
	 * @see com.tq2.implementations.input.ControlsImpl#mouseClicked(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		//Not used
		
	}

	/* (non-Javadoc)
	 * @see com.tq2.implementations.input.ControlsImpl#mouseEntered(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseEntered(MouseEvent e) {
		//Not used
		
	}

	/* (non-Javadoc)
	 * @see com.tq2.implementations.input.ControlsImpl#mouseExited(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseExited(MouseEvent e) {
		//Not used
		
	}

	/* (non-Javadoc)
	 * @see com.tq2.implementations.input.ControlsImpl#mousePressed(java.awt.event.MouseEvent)
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		//Not used
		
	}

	/* (non-Javadoc)
	 * @see com.tq2.implementations.input.ControlsImpl#mouseReleased(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
		//Not used
		
	}

}
