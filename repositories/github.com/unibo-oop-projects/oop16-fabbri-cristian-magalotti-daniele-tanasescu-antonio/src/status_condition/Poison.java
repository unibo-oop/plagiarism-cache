package status_condition;

import abilities.Ability;
import abilities.otherconditions.Immunity;
import pokemon.Pokemon;
import types.Steel;
import types.Type;

public class Poison extends StatusCondition{
    
    public static final double DOTPERCENTAGE = 0.125;                   //1/8
    
    private static final String POISONDOT = "is hurt by poison!"; 
    private static final String POISON = "is poisoned";
    private static final String POISONEND = "is freed from its poison!";
    
    public double damage;
    
    public Poison() {
        super(  new Type[]{new types.Poison(), new Steel()},            //types immune to this   
        		new Ability[]{new Immunity()},		                    //abilities that resists to this status condition       
                true,                                                   //has Dot?
                false,                                                  //may prevent attack?
                false,                                                  //alters Stats?
                POISONDOT,                                              //statusDot 
                "",                                                     //statusPrevent         (doesn't have)
                POISONEND);                                             //statusEnd                  
        this.setStatusAlready("poisoned");
    }

    @Override
    public void checkNextTurnActive() {
        this.setNextTurnActive(true);
        
    }

    @Override
    public void getDotDamage(Pokemon ill) {
        damage = ill.getMaxHp()*DOTPERCENTAGE;
        ill.takeDamage(damage, false);
        //messages
        if(ill.isFainted()){
            //messages
        }    
    }

    @Override
    public void getPreventAttack(Pokemon ill) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void getStatAlteration(Pokemon ill) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void exitingStatusAlteration(Pokemon ill) {
        ill.statusCondition = null;
        this.getEndMessage(ill);
        
    }

    @Override
    public String getStatusString() {
        return POISON;
    }

}