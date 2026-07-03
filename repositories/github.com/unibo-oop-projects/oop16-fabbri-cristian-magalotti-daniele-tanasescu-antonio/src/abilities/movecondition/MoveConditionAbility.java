package abilities.movecondition;

import abilities.Ability;
import battle_arena.BattleArena;
import moves.Move;
import pokemon.Pokemon;

public abstract class MoveConditionAbility extends Ability{
    
	private boolean damageTaken; 
	 
    public MoveConditionAbility(String name, String description) {
        super(name, description);
        this.abilityStartCondition();
    }
    
    //Override solo quando l'abilita' si deve attivare anche prima di fare il danno
    @Override
    public void setNextCondition(Pokemon user, Pokemon target, BattleArena battleArena) {
            if(this.checkMoveCondition(user, target, battleArena)){
                if(damageTaken){
                    this.setIsActivable(true);
                    this.damageTaken = false;  
                }
                else{
                    this.damageTaken = true;
                    this.setIsActivable(false);  
                }
                    
            }
            else{
                this.setIsActivable(false);
            }
    }
    
    
    @Override
    public void abilityStartCondition() {
            this.setIsActivable(false);          
            this.damageTaken = false;
    }
    
    //Condizione per cui si pu� attivare l'abilit� (checkmovecondition)
    @Override
    public void checkForActivation(Pokemon user, Pokemon target, BattleArena battleArena){
        this.setNextCondition(user, target, battleArena);        
        if(this.getIsActivable()){
            this.activateAbility(user, target, battleArena);
            this.setIsActivable(true);
        }
    }
    
    public abstract boolean checkMoveCondition(Pokemon user, Pokemon target, BattleArena battleArena);
    
    protected boolean contactCheck(Move move){
    	return move.isMakingContact();
    }
}