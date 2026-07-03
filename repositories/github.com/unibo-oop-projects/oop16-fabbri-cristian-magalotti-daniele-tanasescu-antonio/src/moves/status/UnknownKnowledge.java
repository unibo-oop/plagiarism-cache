package moves.status;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Psychic;

public class UnknownKnowledge extends StatusMove{

    public UnknownKnowledge() {
        super(  "Unknown Knowledge",                                                                                             //name
                "An unknown knowledge allows the user to receive unknown boost powers, at a life price.",                	 //description
                new Psychic(),                                                                                                   //type
                999,                                                                                                             //accuracy
                5,                                                                                                               //PP                                                                                                                     
                0);                                                                                                              //priority  
        this.setSelfEffect(true);
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        user.setAlterationSpA(+1, true);
        user.setAlterationSpD(+1, true);
        user.setAlterationAtk(+1, true);
        user.setAlterationDef(+1, true);
        user.setAlterationSpe(+1, true);
        user.setAlterationElusion(+1, true);
        user.setAlterationAccuracy(+1, true);
        user.takeDamage(user.getMaxHp()/33, this.hasRecoil());
    }

}
