package model.player;

public interface Player {

	public String getName();
	
	
	public int getHp();
	
	
	public void takeDamage(int damage);
	
	
	public int getCoins();
	
	
	public void incrementCoins(int coins);
	
	
	public int getWave();
	
	
	public void setWave(int wave);

	public void setName(String text);

	
}
