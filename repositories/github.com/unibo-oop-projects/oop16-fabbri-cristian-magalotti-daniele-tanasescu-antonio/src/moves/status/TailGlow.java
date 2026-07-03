package moves.status;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Bug;

public class TailGlow extends StatusMove{

    public TailGlow() {
        super(  "Tail Glow",                                                                                                     //name
                "The user stares at flashing lights to focus its mind, drastically raising its Sp. Atk stat.",                   //description
                new Bug(),                                                                                                       //type
                999,                                                                                                             //accuracy
                20,                                                                                                              //PP                                                                                                                     
                0);                                                                                                              //priority  
        this.setSelfEffect(true);
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        user.setAlterationSpA(+3, true);
        
    }

}
