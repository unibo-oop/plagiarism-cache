package moves.status;

import battle_arena.BattleArena;
import battle_arena.weather.HarshSunlight;
import pokemon.Pokemon;
import types.Grass;

public class Synthesis extends StatusMove{
    
    private static final double RESTOREPERCENTAGE = 0.5;

    public Synthesis() {
        super(  "Synthesis",                                                                                                    //name
                "The user restores its own HP. The amount of HP regained varies with the weather.",                             //description
                new Grass(),                                                                                                    //type
                999,                                                                                                           //accuracy
                5,                                                                                                            //PP                                                                                                                     
                0);                                                                                                             //priority
        this.setSelfEffect(true);
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        if(user.getHp() < user.getMaxHp()){
            double heal;
            if(battleArena.weather != null){
                if(battleArena.weather.equals(new HarshSunlight(5))){
                    heal = -RESTOREPERCENTAGE*4*user.getMaxHp()/3;
                }
                else{
                    heal = -RESTOREPERCENTAGE*user.getMaxHp()/2;
                }
            }
            else{
                heal = -RESTOREPERCENTAGE*user.getMaxHp();
            }
            
            user.takeDamage(heal, this.hasRecoil());
            //message
        }
        //message
        
    }

}
