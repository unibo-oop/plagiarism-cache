package moves.status;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Psychic;

public class UnknownForce extends StatusMove{

    public UnknownForce() {
        super(  "Unknown Force",                                                                                    //name
                "An unknown force weakens the target, at a life price.",           									//description
                new Psychic(),                                                                                      //type
                999,                                                                                                //accuracy
                5,                                                                                                  //PP                                                                                                                     
                0);                                                                                                 //priority  
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        target.setAlterationSpA(-1, false);
        target.setAlterationSpD(-1, false);
        target.setAlterationAtk(-1, false);
        target.setAlterationDef(-1, false);
        target.setAlterationSpe(-1, false);
        target.setAlterationElusion(-1, false);
        target.setAlterationAccuracy(-1, false);
        user.takeDamage(user.getMaxHp()/33, this.hasRecoil());
    }

}
