package moves.status;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Normal;

public class PainSplit extends StatusMove{

    public PainSplit() {
        super(  "Pain Split",                                                                                                    //name
                "The user adds its HP to the target's HP, then equally shares the combined HP with the target.",                 //description
                new Normal(),                                                                                                    //type
                999,                                                                                                             //accuracy
                20,                                                                                                              //PP                                                                                                                     
                0);                                                                                                              //priority  
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        double mediumHP = (user.getHp()+target.getHp())/2;
        user.takeDamage(user.getHp()-mediumHP, this.hasRecoil());
        target.takeDamage(target.getHp()-mediumHP, this.hasRecoil());
        
    }

}
