package moves.status;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Psychic;

public class PsychUp extends StatusMove{

    public PsychUp() {
        super(  "Psych Up",                                                                                                   //name
                "The user hypnotizes itself into copying any stat change made by the target.",                                //description
                new Psychic(),                                                                                                //type
                999,                                                                                                          //accuracy
                10,                                                                                                           //PP                                                                                                                     
                0);                                                                                                           //priority  
        this.setSelfEffect(true);
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        user.setAlterationAtk(target.alterationAtk - user.alterationAtk, true);
        user.setAlterationDef(target.alterationDef - user.alterationDef, true);
        user.setAlterationSpe(target.alterationSpe - user.alterationSpe, true);
        user.setAlterationSpA(target.alterationSpA - user.alterationSpA, true);
        user.setAlterationSpD(target.alterationSpD - user.alterationSpD, true);
        user.setAlterationAccuracy(target.alterationAccuracy - user.alterationAccuracy, true);
        user.setAlterationElusion(target.alterationElusion - user.alterationElusion, true);
        
        
    }

}
