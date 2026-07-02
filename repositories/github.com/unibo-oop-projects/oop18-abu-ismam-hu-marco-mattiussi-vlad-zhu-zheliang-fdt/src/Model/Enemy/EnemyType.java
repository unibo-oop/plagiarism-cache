package model.enemy;

public enum EnemyType {
	
		SIMPLE(100, 5, 20),
		
		TANK(200, 3, 40);
	
	private int health, speed, value;
    EnemyType(int health, int speed, int value){
    	 this.health = health;
         this.speed = speed;
         this.value = value;
    }
    
    public int getHealth() {
        return this.health;
    }

    public int getSpeed() {
    	return this.speed;
    }
    
    public int getValue() {
    	return this.value;
    }
    
   
}
