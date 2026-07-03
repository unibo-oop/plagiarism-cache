package moves.damagingmove.physical;

import java.util.Random;

import battle_arena.BattleArena;
import main.view.BattleMenuController;
import pokemon.Pokemon;
import types.Normal;
import types.Type;

public class FalseSwipe extends PhysicalDamagingMove{

    /*non ucciderà mai il nemico: per questo non si può fare affidamento su takeDamage di getDamage di PhysicalDamagingMove:
     *triggerebbe l'attivazione del controllo su fainted e visualizzerebbe che il nemico è morto quando non è così
     *(mantenendo il vantaggio perciò di avere quel controllo direttamente in takeDamage, a siscapito di questa classe)*/
    public FalseSwipe() {
        super(  "False Swipe",                                                                                          //name
                "A restrained attack that prevents the target from fainting. The target is left with at least 1 HP.",   //description                    
                40,                                                                                                     //base power
                new Normal(),                                                                                           //type
                1,                                                                                                      //accuracy
                critRange1,                                                                                             //crit range 
                40,                                                                                                     //PP
                0);                                                                                                     //Priority
    }

    @Override
    public void getDamage(Pokemon user, Pokemon target, BattleArena battleArena){
        int normalDamage = this.damageCalculation(user, target, battleArena);
        int actualDamage = Math.min(normalDamage, (target.getHp()-1));
        target.takeDamage(actualDamage, this.hasRecoil());
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        // TODO Auto-generated method stub

    }

    private int damageCalculation(Pokemon user, Pokemon target, BattleArena battleArena){
        BattleMenuController.battleLogManager.setUsedMoveMessage(user, target, battleArena, this);
        if(!this.hasMoveFailed(user, target, battleArena)){
            if(!target.isProtecting){
                user.isAttacking = true;
                double damage = ((double)(((2d*user.getLevel()/5d)+2) * user.getAtk() * this.getMoveBasePower()))/(50 * target.getDef())+2;

                for(Type type : target.getType()){
                    if(type != null){
                        if(Type.containsType(type.getTypeImmunities(), this.getMoveType())){
                            user.effectiveness *= 0;
                        }
                        else if(Type.containsType(type.getTypeResistances(), this.getMoveType())){
                            user.effectiveness *= 0.5;
                        }
                        else if(Type.containsType(type.getTypeWeaknesses(), this.getMoveType())){
                            user.effectiveness *= 2;
                        }
                    }
                }     

                for(Type type : user.getType()){
                    if(this.getMoveType().equals(type)){
                        user.stab *= 1.5;
                    }
                }

                int standardMovePower = this.getMoveBasePower();                                    //keep initial move's power, 'cause it could change!

                if(battleArena.weather!= null){
                    battleArena.weather.checkForWeatherPowerChange(this);                           //this can change move's power 
                }

                if(this.critHit()){
                    user.crit *= 1.5;
                }

                Random randomRoll = new Random();
                user.roll =  0.85 + randomRoll.nextInt(26)/100;

                user.getAbility().checkForActivation(user, target, battleArena);
                target.getAbility().checkForActivation(target, user, battleArena);

                damage *= user.effectiveness * user.stab * user.crit * user.roll;
                
                if(this.getMoveBasePower() != standardMovePower){
                    this.setBasePower(standardMovePower);
                }

                return (int) damage;
            }
            else{
                //protect
            }
        }
        else{
            //protect
        }
        return 0;
    }
}
