package moves.status;

import battle_arena.BattleArena;
import battle_arena.terrain.ElectricTerrain;
import battle_arena.terrain.GrassyTerrain;
import battle_arena.terrain.MistyTerrain;
import battle_arena.terrain.PsychicTerrain;
import pokemon.Pokemon;
import types.*;

public class Camouflage extends StatusMove{

    public Camouflage() {
        super(  "Camouflage",                                                                                                     //name
                "The user's type is changed depending on its environment.",                                                       //description
                new Normal(),                                                                                                     //type
                999,                                                                                                              //accuracy
                20,                                                                                                               //PP                                                                                                                     
                0);                                                                                                               //priority  
        this.setSelfEffect(true);
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        if(battleArena.terrain != null){
            if(battleArena.terrain.equals(new ElectricTerrain(5))){
                this.trasnformEnvironment(user, new Electric());
            }
            else if(battleArena.terrain.equals(new GrassyTerrain(5))){
                this.trasnformEnvironment(user, new Grass());                
            }
            else if(battleArena.terrain.equals(new MistyTerrain(5))){
                this.trasnformEnvironment(user, new Fairy());                
            }
            else if(battleArena.terrain.equals(new PsychicTerrain(5))){
                this.trasnformEnvironment(user, new Psychic());
            }
        }
        else{
            this.trasnformEnvironment(user, new Normal());
        }
        
    }
    
    private void trasnformEnvironment(Pokemon user, Type environmentType){
        user.changeTypes(environmentType, null);
        //message
    }

}
