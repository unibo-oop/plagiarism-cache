package it.bomberman.collisions;

public interface ICollidable {
	public Vector2 getPosition();
	public Body getBody();
	//servono diversi offset per ogni Shape? forse una classe ad hoc 
	//invece di un Set<Shae>?
	
	public boolean shouldCollide(ICollidable collidable);
	public void collision(ICollidable collidable);
	public void dispose();
//	public void collision(Player player);
//	public void collision(Bomb bomb);
//	public void collision(Wall wall);
//	public void collision(Explosion explosion);
	//public void collision(DeathWall dw);
	//public void collision(HardWall hw);
	
}
