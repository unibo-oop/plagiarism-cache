package moves.damagingmove.special.amount;

import battle_arena.BattleArena;
import moves.damagingmove.special.SpecialDamagingMove;
import pokemon.Pokemon;
import types.Type;

public abstract class AmountSpecialDamagingMove extends SpecialDamagingMove{
    
    private int amountDamage;

    public AmountSpecialDamagingMove(String name, String description, Type moveType, double moveAccuracy, 
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
                    if(type != null){
                        if(type.isImmuneTo(this.getMoveType())){
                            effective = false;
                        }
                    }
                }
                if(effective){
		            target.takeDamage(this.amountDamage, this.hasRecoil());
		            this.setLastDamageDone(amountDamage);     
		            battleArena.activePlayer.lastMoveUsed = this;
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

