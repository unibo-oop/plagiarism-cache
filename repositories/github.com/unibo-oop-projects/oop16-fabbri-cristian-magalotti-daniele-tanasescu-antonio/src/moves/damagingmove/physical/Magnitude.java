package moves.damagingmove.physical;

import java.util.Random;

import battle_arena.BattleArena;
import main.view.BattleMenuController;
import pokemon.Pokemon;
import types.Ground;

public class Magnitude extends PhysicalDamagingMove{
    
    private static final int[] damages = new int[]{10,30,50,70,90,110,150};
    
    private int magnitude;

    public Magnitude() {
        super(  "Magnitude",                                                                                     //name
                "The user attacks everything around it with a ground-shaking quake.\n"+                          //description
                "Its power varies. ",        
                999,                                                                                             //base power
                new Ground(),                                                                                     //type
                1,                                                                                               //accuracy
                critRange1,                                                                                      //crit range 
                30,                                                                                              //PP
                0);                                                                                              //Priority
    }
    
    @Override
    public void getDamage(Pokemon user, Pokemon target, BattleArena battleArena){
        Random random = new Random();
        magnitude = random.nextInt(damages.length);
        this.setBasePower(damages[magnitude]);
        BattleMenuController.battleLogManager.setMagnitudeSizeMessage(this.getMoveName(), magnitude);
        super.getDamage(user, target, battleArena);
        this.setBasePower(999);
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        // TODO Auto-generated method stub
        
    }
    
    @Override
    public String getMoveName(){
        return super.getMoveName() + (this.magnitude+4);
        
    }

}
