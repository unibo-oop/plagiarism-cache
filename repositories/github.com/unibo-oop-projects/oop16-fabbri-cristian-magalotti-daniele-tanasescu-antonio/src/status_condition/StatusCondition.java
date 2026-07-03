package status_condition;

import java.util.Arrays;

import abilities.Ability;
import abilities.otherconditions.LeafGuard;
import abilities.otherconditions.MagicGuard;
import battle_arena.BattleArena;
import battle_arena.terrain.ElectricTerrain;
import battle_arena.terrain.MistyTerrain;
import battle_arena.terrain.Terrain;
import main.MainApp;
import main.view.BattleMenuController;
import pokemon.Pokemon;
import types.Type;

public abstract class StatusCondition {

    private final String statusDot;
    private final String statusPrevent;
    private final String statusEnd;

    private Type[] immunityToThis;
    private Ability[] avoidThis;

    private boolean statusActive;
    private boolean hasDot;                                              
    private boolean mayPreventAttack;                               
    private boolean alterStat;

    private int turnCounter;
    private String statusAlready;

    public StatusCondition(Type[] immunityToThis, Ability[] avoidThis, boolean hasDot, boolean mayPreventAttack, boolean alterStat,
            String statusDot, String statusPrevent, String statusEnd) {
        if(immunityToThis != null){
            this.immunityToThis = Arrays.copyOf(immunityToThis, immunityToThis.length);
        }
        else{
            this.immunityToThis = null;
        }
        if(avoidThis!=null){
            this.avoidThis = Arrays.copyOf(avoidThis, avoidThis.length);
        }
        else{
            this.avoidThis = null;
        }
        this.setHasDot(hasDot);
        this.setMayPreventAttack(mayPreventAttack);
        this.setAlterStat(alterStat);

        this.statusDot = statusDot;
        this.statusPrevent = statusPrevent;
        this.statusEnd = statusEnd;
        this.turnCounter = 0;
    }

    public void setPokemonStatusCondition(Pokemon target, BattleArena battleArena){
        //ability immunities check
        if(this.avoidThis != null){
            for(Ability ability : this.avoidThis){
                if(target.getAbility().equals(ability)){
                    BattleMenuController.battleLogManager.setAbilityActivationMessage(
                            target, target.getAbility().getAbilityEffect(target, target, battleArena));
                    return;
                }
            }
        }

        //Leaf Guard check
        if(target.getAbility().equals(new LeafGuard())){
            if(((LeafGuard)target.getAbility()).sunnyCondition(target, target, battleArena)){
                BattleMenuController.battleLogManager.setAbilityActivationMessage(
                        target, target.getAbility().getAbilityEffect(target, target, battleArena));
                return;
            }
        }

        //type immunities check
        if(this.immunityToThis != null){
            for(Type type : target.getType()){
                for(Type immuneType : this.immunityToThis){
                    if(type != null && immuneType != null){
                        if(type.getTypeName().equals(immuneType.getTypeName())){
                            BattleMenuController.battleLogManager.setEffectivenessMessage(0);
                            return;
                        }
                    }
                }
            }
        }

        if(battleArena.terrain != null){
            if(battleArena.terrain.equals(new ElectricTerrain(5))){
                if(this.equals(new Sleep())){
                    if(Terrain.doesPokemonGainEffect(target)){
                        battleArena.terrain.getTerrainPreventMoveMessage(target);
                        return;
                    }
                }
            }
            else if(battleArena.terrain.equals(new MistyTerrain(5))){
                if(Terrain.doesPokemonGainEffect(target)){
                    battleArena.terrain.getTerrainPreventMoveMessage(target);
                    return;
                }
            }
        }


        if(target.statusCondition == null){       
            if(!target.isFainted()){
                target.statusCondition = this; 
                BattleMenuController.battleLogManager.setStatusAlterationMessage(target, this.getStatusString());
                this.checkForAlterStat(target);
                target.getAbility().checkForActivation(target, target, MainApp.getBattleArena());     //if it's needed an auto-boost for example
            }
        }
        else{
            BattleMenuController.battleLogManager.setAlreadyHasAStatus(target);
        }
    }

    public void checkForDotDamage(Pokemon ill){
        if(this.hasDot){
            if(!ill.getAbility().equals(new MagicGuard())){
                this.getStatusDotMessage(ill);
                this.getDotDamage(ill);
            }
        }
    }

    public void checkForPreventAttack(Pokemon ill){
        if(this.mayPreventAttack){
            this.getPreventAttack(ill);
        }
    }

    public void getPreventAttackMessage(Pokemon ill){
        BattleMenuController.battleLogManager.setStatusPreventAttackMessage(ill,this.statusPrevent);
    }

    public void getStatusDotMessage(Pokemon ill){
        BattleMenuController.battleLogManager.setStatusDotMessage(ill, this.statusDot);
    }

    public void getEndMessage(Pokemon ill){
        BattleMenuController.battleLogManager.setStatusEndMessage(ill, this.statusEnd);
    }

    public void checkForAlterStat(Pokemon ill){
        if(this.alterStat){
            this.getStatAlteration(ill);
        }
    }

    public abstract void checkNextTurnActive();
    public abstract void getDotDamage(Pokemon ill);
    public abstract void getPreventAttack(Pokemon ill);
    public abstract void getStatAlteration(Pokemon ill);
    public abstract void exitingStatusAlteration(Pokemon ill);
    public abstract String getStatusString();

    public Type[] getImmunityToThis(){
        return this.immunityToThis;
    }

    public Ability[] getAvoidThis(){
        return Arrays.copyOf(this.avoidThis, this.avoidThis.length);
    }

    public boolean isThisTurnActive() {
        return this.statusActive;
    }

    public void setNextTurnActive(boolean nextTurnActive) {
        this.statusActive = nextTurnActive;
    }

    public boolean isAlterStat() {
        return this.alterStat;
    }

    public void setAlterStat(boolean alterStat) {
        this.alterStat = alterStat;
    }

    public boolean hasDot() {
        return this.hasDot;
    }

    public void setHasDot(boolean hasDot) {
        this.hasDot = hasDot;
    }

    public boolean isMayPreventAttack() {
        return this.mayPreventAttack;
    }

    public void setMayPreventAttack(boolean mayPreventAttack) {
        this.mayPreventAttack = mayPreventAttack;
    }

    public int getTurnCounter() {
        return turnCounter;
    }

    public void incrementTurnCounter() {
        this.turnCounter++;
    }


    public void operationsInSwitch(Pokemon ill){
        this.turnCounter = 0;
    }

    @Override
    public boolean equals(Object status){
        if(status instanceof StatusCondition){
            if(this.getClass().getName().equals(status.getClass().getName())){    
                return true;
            }
        }

        //if not a StatusCondition or if non that type of statuscondition
        return false;
    }

    @Override
    public String toString(){
        String[] name = this.getClass().getSimpleName().split("(?=[A-Z])");
        String complete = "";
        for(String part : name){
            complete += part;
            complete += " ";
        }
        return complete;
    }
    
    public void setStatusAlready(String string){
        this.statusAlready = string;
    }

    public String getStatusAlreadyString() {
        return this.statusAlready;
    }


}
