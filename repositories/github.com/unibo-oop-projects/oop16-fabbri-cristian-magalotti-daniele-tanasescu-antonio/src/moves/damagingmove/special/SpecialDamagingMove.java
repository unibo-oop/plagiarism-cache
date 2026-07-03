package moves.damagingmove.special;

import java.util.Random;

import abilities.otherconditions.Scrappy;
import battle_arena.BattleArena;
import battle_arena.terrain.PsychicTerrain;
import battle_arena.terrain.Terrain;
import main.view.BattleMenuController;
import moves.damagingmove.DamagingMove;
import pokemon.Pokemon;
import types.Fight;
import types.Ghost;
import types.Normal;
import types.Type;

public abstract class SpecialDamagingMove extends DamagingMove{

    public SpecialDamagingMove(String name, String description, int moveBasePower, Type moveType, double moveAccuracy,
            double actualCritRange, int minPP, int priority) {
        super(name, description, moveBasePower, moveType, moveAccuracy, actualCritRange, minPP, priority);
        this.setMakingContact(false);
    }

    public void getDamage(Pokemon user, Pokemon target, BattleArena battleArena){
        user.resetBattleFactors();
        BattleMenuController.battleLogManager.setUsedMoveMessage(user, target, battleArena, this);
        target.isAttacking = false;                                                                     //to be sure
        if(!target.isFainted()){
            if(!this.hasMoveFailed(user, target, battleArena)){
                if(!target.isProtecting){
                    user.isAttacking = true;
                    double damage = ((double)(((2d*user.getLevel()/5)+ 2) * user.getSpA() * this.getMoveBasePower()))/(50 * target.getSpD())+2;
                    user.lastMoveUsed = this;

                    for(Type type : target.getType()){
                        if(type != null){
                            if(Type.containsType(type.getTypeImmunities(), this.getMoveType())){
                                //if has scrappy ability with right conditions, it will attack just the same
                                if(! (type.equals(new Ghost()) && 
                                        (this.getMoveType().equals(new Normal()) || this.getMoveType().equals(new Fight())) &&
                                        user.getAbility().equals(new Scrappy()))
                                        ){
                                    user.effectiveness *= 0;
                                    BattleMenuController.battleLogManager.setEffectivenessMessage(user.effectiveness);
                                    return;
                                }
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

                    if(this.critHit()){
                        user.crit *= 1.5;
                    }

                    Random randomRoll = new Random();
                    user.roll =  0.85 + randomRoll.nextInt(26)/100;

                    int standardMovePower = this.getMoveBasePower();                                //keep initial move's power, 'cause it could change!

                    if(battleArena.weather!= null){
                        battleArena.weather.checkForWeatherPowerChange(this);                      //this can change move's power 
                    }
                    
                    if(battleArena.terrain != null){
                        if(battleArena.terrain.equals(new PsychicTerrain(5)) && this.getPriority() > 0){
                            if(Terrain.doesPokemonGainEffect(target)){
                                battleArena.terrain.getTerrainPreventMoveMessage(target);
                                return;
                            }
                        }
                        else{
                            battleArena.terrain.getTerrainMovePowerChange(target, this);
                        }
                    }

                    user.getAbility().checkForActivation(user, target, battleArena);
                    target.getAbility().checkForActivation(target, user, battleArena);

                    if(user.effectiveness > 0){                                                   //(also an ability could have changed it to 0)
                        BattleMenuController.battleLogManager.setEffectivenessMessage(user.effectiveness);
                    }

                    damage *= user.effectiveness * user.stab * user.crit * user.roll;

                    this.setLastDamageDone(damage);   

                    if(damage > 0){
                        target.takeDamage(damage, this.hasRecoil()); 
                        if(user.crit > 1){
                            BattleMenuController.battleLogManager.setCritMessage();
                        }
                        if(this.sideEffect){
                            this.sideEffect(user, target, battleArena);
                        }

                        if(this.getMoveBasePower() != standardMovePower){
                            this.setBasePower(standardMovePower);
                        }

                        user.getAbility().checkForActivation(user, target, battleArena);
                        target.getAbility().checkForActivation(target, user, battleArena);
                    }

                    user.isAttacking = false;
                }
                else{
                    BattleMenuController.battleLogManager.setProtectMessage(target);
                }
            }
            else{
                user.isAttacking = false;
                BattleMenuController.battleLogManager.setMoveFailedMassage();
            }
        }
        else{
            user.isAttacking = false;
            BattleMenuController.battleLogManager.setMoveFailedMassage();
        }
    }
}