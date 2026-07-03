package moves.status;

import java.util.Random;

import battle_arena.BattleArena;
import moves.Move;
import moves.damagingmove.DamagingMove;
import moves.damagingmove.physical.multistrike.MultiStrikePhysicalDamagingMove;
import pokemon.Pokemon;
import types.Normal;

public class Assist extends StatusMove{
    
    private static final int MAXALLIESMOVES = 20;

    public Assist() {
        super(  "Assist",                                                                                                        //name
                "The user hurriedly and randomly uses a move among those known by ally Pokémon.",                                //description
                new Normal(),                                                                                                    //type
                999,                                                                                                             //accuracy
                20,                                                                                                              //PP                                                                                                                     
                0);                                                                                                              //priority  
        this.setSelfEffect(true);
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        Move assistMove = this.randomAllyMove(user, target, battleArena);                       //sceglie una mossa random tra quella dei compagni
        //messaggio "si trasforma ..."
        
        //ora bisogna trovare il modo giusto per chiamare questa mossa a seconda del suo "tipo"
        if(assistMove instanceof StatusMove){
            ((StatusMove) assistMove).getEffect(user, target, battleArena);
        }
        else{                                                                                   //se è Damaging
            if(assistMove instanceof MultiStrikePhysicalDamagingMove){                          //unico caso in cui non si chiama getDamage!
                ((MultiStrikePhysicalDamagingMove) assistMove).multiStrike(user, target, battleArena);                
            }
            else{                                                                               //non importa a questo punto se è Phisical, Special ecc...
                ((DamagingMove)assistMove).getDamage(user, target, battleArena);
            }
        }
        
    }
    
    private Move randomAllyMove(Pokemon user, Pokemon target, BattleArena battleArena){
        Move[] allAlliesMoves = new Move[MAXALLIESMOVES];
        int index = 0;
        if(user.equals(battleArena.activeEnemy)){
            for(Pokemon ally : battleArena.getEnemy().getPokemon()){
                if(!ally.equals(battleArena.activeEnemy)){                                      //non prende le mosse del suo user!
                    for(Move move : ally.getAllMoves()){
                        allAlliesMoves[index] = move;
                        index++;
                    }
                }
            }
        }
        else{                                                                                   //allora è player!
            for(Pokemon ally : battleArena.getPlayer().getPokemon()){
                if(!ally.equals(battleArena.activePlayer)){                                     //non prende le mosse del suo user!
                    for(Move move : ally.getAllMoves()){
                        allAlliesMoves[index] = move;
                        index++;
                    }
                }
            }
            
        }
        
        //allAlliesMoves è stato inizializzato: ora ne scelgo una mossa casuale!
        Random random = new Random();
        int choice = random.nextInt(MAXALLIESMOVES);                                            //0 - (MAXALLIESMOVES-1)
      
        return allAlliesMoves[choice];
    }

}
