package moves.status;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Normal;

public class ShellSmash extends StatusMove{

    public ShellSmash() {
        super(  "Shell Smash",                                                                             	//name
                "The user breaks its shell, which lowers Defense and Sp. Def stats"
                + "but sharply raises its Attack, Sp. Atk, and Speed stats.",         
                new Normal(),                                                                             	//type
                999,                                                                                     	//accuracy
                15,                                                                                      	//PP                                                                                                                     
                0);                                                                                      	//priority  
        this.setSelfEffect(true);
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        user.setAlterationDef(-1, true);
        user.setAlterationSpD(-1, true);
        user.setAlterationAtk(+2, true);
        user.setAlterationSpA(+2, true);
        user.setAlterationSpe(+2, true);
    }

}
