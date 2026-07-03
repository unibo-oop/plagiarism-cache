package moves.status;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Dark;

public class NastyPlot extends StatusMove{

    public NastyPlot() {
        super(  "Nasty Plot",                                                                             //name
                "The user stimulates its brain by thinking bad thoughts.\n"+                              //description
                "This sharply raises the user's Sp. Atk stat.",         
                new Dark(),                                                                             //type
                999,                                                                                      //accuracy
                20,                                                                                       //PP                                                                                                                     
                0);                                                                                       //priority       
        this.setSelfEffect(true);
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        user.setAlterationSpA(+2, true);
        
    }


}
