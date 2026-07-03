package moves.status;

import battle_arena.BattleArena;
import battle_arena.terrain.ElectricTerrain;
import battle_arena.terrain.GrassyTerrain;
import battle_arena.terrain.MistyTerrain;
import battle_arena.terrain.PsychicTerrain;
import main.view.BattleMenuController;
import moves.damagingmove.DamagingMove;
import moves.damagingmove.special.EnergyBall;
import moves.damagingmove.special.Moonblast;
import moves.damagingmove.special.Psychic;
import moves.damagingmove.special.Thunderbolt;
import moves.damagingmove.special.TriAttack;
import pokemon.Pokemon;
import types.Normal;

public class NaturePower extends StatusMove{
    
    private DamagingMove moveDone;

    public NaturePower() {
        super(  "Nature Power",                                                                                                  //name
                "This attack makes use of nature's power. Its effects vary depending on the user's environment.",                //description
                new Normal(),                                                                                                    //type
                999,                                                                                                             //accuracy
                20,                                                                                                              //PP                                                                                                                     
                0);                                                                                                              //priority  
        this.setSelfEffect(true);
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        if(battleArena.terrain != null){
            if(battleArena.terrain.equals(new ElectricTerrain(5))){
                this.moveDone = new Thunderbolt();
            }
            else if (battleArena.terrain.equals(new GrassyTerrain(5))){
                this.moveDone = new EnergyBall();
            }
            else if (battleArena.terrain.equals(new MistyTerrain(5))){
                this.moveDone = new Moonblast();
            }
            else if (battleArena.terrain.equals(new PsychicTerrain(5))){
                this.moveDone = new Psychic();
            }
        }
        else{
            this.moveDone = new TriAttack();
        }
        
        BattleMenuController.battleLogManager.setTrasformingMove(this, moveDone);
        this.moveDone.getDamage(user, target, battleArena);
        
    }

}
