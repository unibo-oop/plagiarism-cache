package model.tower;

public enum TowerType {
	
		BASIC(50, true, 1, 2),
		
		RANGED(100, false, 25, 3),
	
		CANNON(120, false, 50, 2);
	
	private int cost, damage, range;
    private boolean canAttack;
    TowerType(final int towerCost, final boolean canAttack, int damage, int range){
    	 this.cost = towerCost;
         this.canAttack = canAttack;
         this.damage = damage;
         this.range=range;
    }
    
    public int getCost() {
        return this.cost;
    }

    public boolean canAttack() {
        return this.canAttack;
    }
    
    public int getDamage() {
    	return this.damage;
    }
    
    public int getRange() {
    	return this.range;
    }
    
   
}
