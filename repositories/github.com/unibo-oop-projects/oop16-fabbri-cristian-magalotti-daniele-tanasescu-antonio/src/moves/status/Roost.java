package moves.status;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Flying;

public class Roost extends StatusMove{

    public Roost() {
        super(  "Roost",                                                                                                         //name
                "The user lands and rests its body. It restores the user's HP by up to half of its max HP.",                     //description
                new Flying(),                                                                                                    //type
                999,                                                                                                             //accuracy
                10,                                                                                                              //PP                                                                                                                     
                0);                                                                                                              //priority  
        this.setSelfEffect(true);
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        if(user.getHp() < user.getMaxHp()){
            user.takeDamage(-user.getMaxHp()/2, this.hasRecoil());                                                                //recupera metà vita
        }
        
    }

}
