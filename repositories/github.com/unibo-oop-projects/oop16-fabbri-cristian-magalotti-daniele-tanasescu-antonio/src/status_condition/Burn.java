package status_condition;

import abilities.Ability;
import pokemon.Pokemon;
import types.Fire;
import types.Type;

public class Burn extends StatusCondition{
    
    private static final double DOTPERCENTAGE = 0.0625;                 //1/16
    private static final String BURN = "is burned";
    private static final String BURNMESSAGE = "is hurt by its burn!";
    private static final String BURNEND = "is freed from its burn!";

    public Burn() {
        super(  new Type[]{new Fire()},                                 //types immune to this   
                null,                                                   //abilities that resists to this status condition       
                true,                                                   //has Dot?
                false,                                                  //may prevent attack?
                true,                                                   //alters Stats?
                BURNMESSAGE,                                            //statusDot   
                "",                                                     //statusPrevent         (doesn't have)
                BURNEND);                                               //statusEnd        
        this.setStatusAlready("burned");
        
    }

    @Override
    public void checkNextTurnActive() {
        this.setNextTurnActive(true);                                   //it will be true untill the Pokemon faints
        
    }

    @Override
    public void getDotDamage(Pokemon ill) {
        double damage = ill.getMaxHp()*DOTPERCENTAGE;
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
        ill.setOtherModifierFactorAtk(ill.getOtherModifierFactorAtk()/2);
        
    }

    @Override
    public void exitingStatusAlteration(Pokemon ill) {
        ill.setOtherModifierFactorAtk(ill.getOtherModifierFactorAtk()*2); 
        ill.statusCondition = null;        
        this.getEndMessage(ill);
    }

    @Override
    public String getStatusString() {
        return BURN;
    }


}
