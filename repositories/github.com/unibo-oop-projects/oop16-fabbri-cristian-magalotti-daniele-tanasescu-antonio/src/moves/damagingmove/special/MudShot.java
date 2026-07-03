package moves.damagingmove.special;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Ground;

public class MudShot extends SpecialDamagingMove{

    public MudShot() {
        super(  "Mud Shot",                                                                                                     //name
                "The user attacks by hurling a blob of mud at the target. This also lowers the target's Speed stat.",           //description
                55,                                                                                                             //base power
                new Ground(),                                                                                                   //type
                0.95,                                                                                                           //accuracy
                critRange1,                                                                                                     //crit range 
                15,                                                                                                             //PP
                0);                                                                                                             //Priority
        this.sideEffect = true;
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        target.setAlterationSpe(-1, false);
        
    }

}
