package moves.damagingmove.physical.amount;

import battle_arena.BattleArena;
import moves.damagingmove.physical.PhysicalDamagingMove;
import pokemon.Pokemon;
import types.Type;

public abstract class AmountPhysicalDamagingMove extends PhysicalDamagingMove{
    
    private int amountDamage;

    public AmountPhysicalDamagingMove(String name, String description, Type moveType, double moveAccuracy, 
                                     int minPP, int priority, int amountDamage) {
        super(name, description, 999, moveType, moveAccuracy, 0, minPP, priority);      
        this.amountDamage = amountDamage;
    }
    
    @Override
    public void getDamage(Pokemon user, Pokemon target, BattleArena battleArena) {
        if(!this.hasMoveFailed(user, target, battleArena)){
        	if(!target.isProtecting){
        		user.isAttacking = true;
                boolean effective = true;
                for(Type type : target.getType()){
                    if(type.isImmuneTo(this.getMoveType())){
                        effective = false;
                    }
                }
                if(effective){
                	target.takeDamage(this.amountDamage, this.hasRecoil());
                	this.setLastDamageDone(amountDamage);                            
	            	if(target.isFainted()){
	            		//controls
	            	}
                }
        	}
        	else{
        		//protect
        	}
        }
    }
    
    public int getAmount(){
        return this.amountDamage;
    }
    
    public void setAmount(double newAmount){
        this.amountDamage = (int)(newAmount);
    }


}
