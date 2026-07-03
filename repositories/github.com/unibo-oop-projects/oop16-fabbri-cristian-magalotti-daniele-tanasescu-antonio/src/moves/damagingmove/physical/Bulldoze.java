package moves.damagingmove.physical;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Ground;

public class Bulldoze extends PhysicalDamagingMove{

    public Bulldoze() {
        super(  "Bulldoze",                                                                                             //name
                "The user strikes everything around it by stomping down on the ground."
                + "This lowers the Speed stat of those hit.",                              							    //description
                60,                                                                                                     //base power
                new Ground(),                                                                                           //type
                1,                                                                                                      //accuracy
                critRange1,                                                                                             //crit range 
                20,                                                                                                     //PP
                0);                                                                                                     //Priority
        this.sideEffect = true;
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        target.setAlterationSpe(-1, false);
        
    }

}
