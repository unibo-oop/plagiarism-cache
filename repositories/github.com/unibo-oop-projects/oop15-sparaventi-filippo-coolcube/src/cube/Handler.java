package cube;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

import cube.entity.Coin;
import cube.entity.Entity;
import cube.entity.mob.Goomba;
import cube.entity.mob.Player;
import cube.entity.powerup.Mushroom;
import cube.tile.Flag;
import cube.tile.Tile;
import cube.tile.Wall;

public class Handler {

	public LinkedList<Entity> entity = new LinkedList<Entity>();
	public LinkedList<Tile> tile = new LinkedList<Tile>();
	
	
	/**
	 * Disegno solo l'area visibile del livello
	 *
	 * @param Graphics g
	 */
	public void render(Graphics g){
		for(int i=0; i<entity.size(); i++){
			Entity e = entity.get(i);
			if(Game.getVisibleArea()!=null && e.getBounds().intersects(Game.getVisibleArea())) e.render(g);
		}
		for(int i=0; i<tile.size(); i++){
			Tile t = tile.get(i);
			if(Game.getVisibleArea()!=null && t.getBounds().intersects(Game.getVisibleArea())) t.render(g);
		}
	}
	
	/**
	 * Gestisco l'area 
	 *
	 */
	public void tick(){
		for(int i=0; i<entity.size(); i++){
			Entity e = entity.get(i);
			e.tick();
		}
		for(int i=0; i<tile.size(); i++){
			Tile t = tile.get(i);
			if(Game.getVisibleArea()!=null && t.getBounds().intersects(Game.getVisibleArea())) t.tick();
		}
	}
	
	/**
	 * Aggiungo l'entità in questione
	 *
	 * @param Entity e
	 */
	public void addEntity(Entity e){
		entity.add(e);
	}
	
	/**
	 * Rimuovo l'entità in questione
	 *
	 * @param Entity e
	 */
	public void removeEntity(Entity e){
		entity.remove(e);
	}
	
	/**
	 * Aggiungo la piastrella in questione
	 *
	 * @param Tile t
	 */
	public void addTile(Tile t){
		tile.add(t);
	}
	
	/**
	 * Rimuovo la piastrella in questione
	 *
	 * @param Tile t
	 */
	public void removetile(Tile t){
		tile.remove(t);
	}
	
	/**
	 * Disegno il livello. Tutto ciò avviene caricando dai file l'immagine livello ed analizzandola.
	 * Dove ho i pixel neri ad esempio creo il muro. Dove ho il puxel rosso creo il potenziamento 
	 * e così via con tutte le entità
	 *
	 * @param BufferedImage level
	 */
	public void createLevel(BufferedImage level){
		int width = level.getWidth();
		int height = level.getHeight();
		
		for(int y=0; y<height; y++){
			for(int x=0; x<width; x++ ){
				int pixel = level.getRGB(x, y);
				
				int red = (pixel >> 16) & 0xff;
				int green = (pixel >> 8) & 0xff;
				int blue = (pixel) & 0xff;
				
				if(red==0&&green==0&&blue==0) addTile(new Wall(x*64, y*64, 64, 64, true , Id.wall, this));
				if(red==0&&green==0&&blue==255) addEntity(new Player(x*64, y*64, 64, 64, false, Id.player, this));
				if(red==255&&green==0&&blue==0) addEntity(new Mushroom(x*64, y*64, 64, 64, true, Id.mushroom, this));
				if(red==255&&green==255&&blue==0) addEntity(new Goomba(x*64, y*64, 64, 64, true, Id.goomba, this));
				if(red==255&&green==0&&blue==255) addTile(new Coin(x*64, y*64, 64, 64, true, Id.coin, this));
				if(red==0&&green==255&&blue==0) addTile(new Flag(x*64, y*64, 64, 64, true, Id.flag, this));
			}
		
		}
	}
	
	/**
	 * Pulisco il livello cancellando tutto
	 *
	 */
	public void clearLevel(){
		entity.clear();
		tile.clear();
	}
	
	
}
