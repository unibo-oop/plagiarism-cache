package moves.status;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Poison;

public class AcidArmor extends StatusMove{

    public AcidArmor() {
        super(  "Acid Armor",                                                                                                    //name
                "The user alters its cellular structure to liquefy itself, sharply raising its Defense stat.",                   //description
                new Poison(),                                                                                                    //type
                999,                                                                                                             //accuracy
                20,                                                                                                              //PP                                                                                                                     
                0);                                                                                                              //priority 
        this.setSelfEffect(true);
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
    	user.setAlterationDef(+2, true);
    }
}
