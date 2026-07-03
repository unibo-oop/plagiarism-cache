package moves.status;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Ghost;

public class Memento extends StatusMove{

    public Memento() {
        super(  "Memento",                                                                                                       //name
                "The user faints when using this move.\n"+                                                                       //description
                "In return, this harshly lowers the target's Attack and Sp. Atk stats.",                                    
                new Ghost(),                                                                                                     //type
                1,                                                                                                               //accuracy
                10,                                                                                                              //PP                                                                                                                     
                0);                                                                                                              //priority  
        this.setHasRecoil(true);
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        user.takeDamage(user.getMaxHp(), this.hasRecoil());
        //faint message
        target.setAlterationAtk(-2, false);
        target.setAlterationSpA(-2, false);
        
    }

}
