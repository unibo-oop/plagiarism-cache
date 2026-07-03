package moves.status;

import battle_arena.BattleArena;
import battle_arena.weather.HarshSunlight;
import main.view.BattleMenuController;
import pokemon.Pokemon;
import types.Fairy;

public class Moonlight extends StatusMove{
    
    private static final double RESTOREPERCENTAGE = 0.5;

    public Moonlight() {
        super(  "Moonlight",                                                                                                    //name
                "The user counters the target by mimicking the target's last move.",                                            //description
                new Fairy(),                                                                                                    //type
                999,                                                                                                            //accuracy
                5,                                                                                                              //PP                                                                                                                     
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
        }
        else{
            BattleMenuController.battleLogManager.setMoveFailedMassage();
        }
        
        
    }

}
