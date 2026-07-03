package abilities.endofturnconditionability;

import java.util.Random;

import battle_arena.BattleArena;
import main.view.BattleMenuController;
import managers.BattleLogManager;
import pokemon.Pokemon;

public class Moody extends EndOfTurnConditionAbility{
    
    private static final int MAXSTATS = 7;                                                                        //atk,def,spa,spd,spe,accuracy,elusion

    
    public Moody() {
        super(  "Moody",                                                                                          //name, 
                "Raises one stat sharply and lowers another every turn");                                         //description
    }

    @Override
    public void activateAbility(Pokemon user, Pokemon target, BattleArena battleArena) {
        BattleMenuController.battleLogManager.setAbilityActivationMessage(user, this.getAbilityEffect(user, target, battleArena));
        Random random = new Random();
        int raiseStat = random.nextInt(MAXSTATS);
        this.chooseStat(user, raiseStat, true);
        int lowStat = this.selectRandomExceptThat(raiseStat);
        this.chooseStat(user, lowStat, false);
        
    }
    
    private int selectRandomExceptThat(int notThisOne){
        Random random = new Random();
        int choice = 0;
        boolean selectedRight = false;
        while(!selectedRight){
            choice = random.nextInt(MAXSTATS);
            if(choice != notThisOne){                                                                             
                selectedRight = true;
            }
        }
        
        return choice;
    }
    
    private void chooseStat(Pokemon user, int choice, boolean raiseStat){
        int change;
        if(raiseStat){
            change = +2;
        }
        else{
            change = -1;
        }
        
        switch (choice) {
        case 0:
            this.changeAtk(user, change);
            break;
        case 1:
            this.changeDef(user, change);
            break;
        case 2:
            this.changeSpA(user, change);
            break;
        case 3:
            this.changeSpD(user, change);
            break;
        case 4:
            this.changeSpe(user, change);
            break;
        case 5:
            this.changeAccuracy(user, change);
            break;
        case 6:
            this.changeElusion(user, change);
            break;
        default:
            break;
        }
    }
    
    private void changeAtk(Pokemon user, int change){
        user.setAlterationAtk(change, true);
    }
    
    private void changeDef(Pokemon user, int change){
        user.setAlterationDef(change, true);
    }
    
    private void changeSpA(Pokemon user, int change){
        user.setAlterationSpA(change, true);
    }
    
    private void changeSpD(Pokemon user, int change){
        user.setAlterationSpD(change, true);
    }
    
    private void changeSpe(Pokemon user, int change){
        user.setAlterationSpe(change, true);
    }
    
    private void changeAccuracy(Pokemon user, int change){
        user.setAlterationAccuracy(change, true);
    }
    
    private void changeElusion(Pokemon user, int change){
        user.setAlterationElusion(change, true);
    }

    @Override
    public String getAbilityEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        return BattleLogManager.ACTIVATES;
    }

}
