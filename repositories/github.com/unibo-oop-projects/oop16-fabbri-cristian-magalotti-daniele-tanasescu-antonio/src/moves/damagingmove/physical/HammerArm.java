package moves.damagingmove.physical;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Fight;

public class HammerArm extends PhysicalDamagingMove{

    public HammerArm() {
        super(  "Hammer Arm",                                                                                   //name
                "The user swings and hits with its strong, heavy fist.\n"										//description
                + "It lowers the user's Speed, however.",                    
                100,                                                                                            //base power
                new Fight(),                                                                                    //type
                0.9,                                                                                            //accuracy
                critRange1,                                                                                     //crit range 
                10,                                                                                             //PP
                0);                                                                                             //Priority
        this.sideEffect = true;
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        user.setAlterationSpe(-1, true);
        
    }

}
