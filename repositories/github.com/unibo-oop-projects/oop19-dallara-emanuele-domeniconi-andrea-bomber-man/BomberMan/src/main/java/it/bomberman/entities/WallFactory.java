package it.bomberman.entities;

public interface WallFactory {
	public Wall mapLimitWall(int x, int y, EntityController controller);
	public Wall hardWall(int x, int  y, EntityController controller);
	public Wall simpleWall(int x, int y, EntityController controller);
	public Wall deathWall(int x, int y, EntityController controller);
}
