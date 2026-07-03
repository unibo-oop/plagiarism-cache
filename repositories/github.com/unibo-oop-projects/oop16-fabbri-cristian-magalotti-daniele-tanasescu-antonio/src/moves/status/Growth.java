package moves.status;

import battle_arena.BattleArena;
import battle_arena.weather.HarshSunlight;
import pokemon.Pokemon;
import types.Grass;

public class Growth extends StatusMove {

    public Growth() {
        super(	"Growth",
                "The user's body grows all at once, raising the Attack and Sp. Atk stats.",
                new Grass(),
                999,
                20,
                0);
                this.setSelfEffect(true);
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        int change = +1;
        if(battleArena.weather != null && battleArena.weather.equals(new HarshSunlight(5))){
            change++;                                                                              
        }
        user.setAlterationAtk(change, true);
        user.setAlterationSpA(change, true);
    }

}
