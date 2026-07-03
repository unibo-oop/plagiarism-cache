package cube.entity.mob;

import java.awt.Graphics;

import cube.Game;
import cube.Handler;
import cube.Id;
import cube.entity.Entity;
import cube.states.PlayerState;
import cube.tile.Tile;

public class Player extends Entity {
	
	private PlayerState state;

	public Player(int x, int y, int width, int height, boolean solid, Id id,
			Handler handler) {
		super(x, y, width, height, solid, id, handler);
		
		state = PlayerState.uncool;
	}
	
	/**
	 * A seconda dello stato in cui mi trovo (uncool/cool) disegno il cubo
	 * caricando l'immagine adeguata
	 *
	 * @param Graphics g
	 * 
	 */
	public void render(Graphics g) {
		if(state == PlayerState.uncool){
			g.drawImage(Game.player.getBufferedImage(), x, y, width, height, null);
		} else if(state == PlayerState.cool){
			g.drawImage(Game.playerCool.getBufferedImage(), x, y, width, height, null);
		}
		
	}
	
	/**
	 * Gestisce il cubo nel suo ambiente di gioco, gestendo i comportamenti 
	 * nelle diverse condizioni che si possono trovare. 
	 * Es: Morte se tocca il mostro oppure cool se tocca il potenziamento.
	 *
	 */
	public void tick() {
		x+=velX;
		y+=velY;
		
		for(int i=0; i<handler.tile.size(); i++){
			Tile t = handler.tile.get(i);
			if(t.isSolid()){
				if(getBoundsTop().intersects(t.getBounds()) && t.getId()!=Id.coin){
					setVelY(0);
					if(jumping) {
						jumping = false;
						gravity = 0.8;
						falling = true;
					}
				}
				if(getBoundsBottom().intersects(t.getBounds()) && t.getId()!=Id.coin){
					setVelY(0);
					if(falling) falling = false;
 				}else if(!falling&&!jumping){
 						falling = true;
 						gravity = 0.8;
 				}
				if(getBoundsLeft().intersects(t.getBounds()) && t.getId()!=Id.coin){
					setVelX(0);
					x=t.getX()+t.width;
				}	
				if(getBoundsRight().intersects(t.getBounds()) && t.getId()!=Id.coin){
					setVelX(0);
					x=t.getX()-t.width;
				}
				if(getBounds().intersects(t.getBounds()) && t.getId()==Id.coin) {
					Game.coins++;
					Game.coinTone.play();
					t.die();
				}
				
				if(getBounds().intersects(t.getBounds())){
					if(t.getId()==Id.flag){
						//Game.themeTone.stop();
						Game.switchLevel();
					}
				}
			}
		}		
		
		for(int i=0; i<handler.entity.size(); i++){
			Entity e=handler.entity.get(i);
			
			if(e.getId()==Id.mushroom){
				if(getBounds().intersects(e.getBounds())){
					
					if(state==PlayerState.uncool) state=PlayerState.cool;
					Game.superCoolTone.play();
 					e.die();
				}
			} else if(e.getId()==Id.goomba){
				if(getBoundsBottom().intersects(e.getBoundsTop())){
					e.die();
					Game.killTone.play();
					if(Game.level==2){
						win();
					}
				}else if(getBounds().intersects(e.getBounds())){
					if(state==PlayerState.cool){
						state=PlayerState.uncool;
						x+=2;
						y+=2;
					}
					else if(state==PlayerState.uncool){
						die();
					}
				}
			}
		}
		
		if(jumping){
			gravity-=0.17;
			setVelY((int)-gravity);
			if(gravity<=0.5){
				jumping=false;
				falling=true;
			}
		}
		if(falling){
			gravity+=0.17;
			setVelY((int)gravity);
		}
		
	}
	
}
