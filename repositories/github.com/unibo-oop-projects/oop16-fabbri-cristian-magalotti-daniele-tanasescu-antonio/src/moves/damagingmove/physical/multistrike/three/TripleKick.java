package moves.damagingmove.physical.multistrike.three;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Fight;

public class TripleKick extends ThreeMultiStrikePhysicalDamagingMove{

    public TripleKick() {
        super(  "Triple Kick",                                                                                          //name
                "A consecutive three-kick attack that becomes more powerful with each successive hit.",                 //description
                10,                                                                                                     //base power
                new Fight(),                                                                                           //type
                0.9,                                                                                                    //accuracy
                critRange1,                                                                                             //crit range 
                10,                                                                                                     //PP
                0);                                                                                                     //Priority
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        // TODO Auto-generated method stub        
    }
    
    @Override
    public void multiStrike(Pokemon user, Pokemon target, BattleArena battleArena){
        this.setBasePower(10);
        for(int i = 0; i < this.numHits; i++){
            this.getDamage(user, target, battleArena);
            this.setBasePower(this.getMoveBasePower()+10);
            if(this.hasFailed){
            	this.setBasePower(10);
                break;
            }
        }
        this.setBasePower(10);
    }

}
