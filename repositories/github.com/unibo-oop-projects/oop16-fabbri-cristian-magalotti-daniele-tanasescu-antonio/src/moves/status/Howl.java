package moves.status;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Normal;

public class Howl extends StatusMove{

    public Howl() {
        super(  "Howl",                                                                                                          //name
                "The user howls loudly to raise its spirit, which raises its Attack stat.",                                      //description
                new Normal(),                                                                                                    //type
                999,                                                                                                             //accuracy
                40,                                                                                                              //PP                                                                                                                     
                0);                                                                                                              //priority  
        this.setSelfEffect(true);
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        user.setAlterationAtk(+1, true);
        
    }

}
