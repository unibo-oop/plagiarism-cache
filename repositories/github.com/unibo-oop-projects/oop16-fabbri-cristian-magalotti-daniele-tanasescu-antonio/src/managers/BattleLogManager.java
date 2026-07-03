package managers;

import java.text.DecimalFormat;

import abilities.Ability;
import battle_arena.BattleArena;
import main.MainApp;
import moves.Move;
import pokemon.Pokemon;
import team.Player;
import team.Team;
import team.enemies.Enemy;

/**
 * 
 * @author Cristian
 *
 */

public class BattleLogManager {
    
    //all messages key-words/phrases
    public static final String ACTIVATES = "activates!";
    public static final String STATSCANTLOWER = "prevents its stat to be lowered.";
    
    public static final String HP = " HP";
    public static final String MAXHP = "max HP";
    public static final String ATTACK = "Attack";
    public static final String DEFENSE = "Defense";
    public static final String SPA = "Sp. Attack";
    public static final String SPD = "Sp. Defense";
    public static final String SPEED = "Speed";
    public static final String ACCURACY  = "Accuracy";
    public static final String ELUSION = "Elusion";
    
    private static final String ASDAMAGE = "as damage";
    private static final String ASHEAL = "as heal";
    private static final String CANTESCAPE = "can't escape";
    private static final String CANTLOWER = "can't lower anymore";
    private static final String CANTRAISE = "can't raise anymore";
    private static final String COMEBACK = "come back";
    private static final String COMMA = ",";
    private static final String CRIT = "It's a critical hit";
    private static final String DESTROYS = "destroys";
    private static final String DOT = ".";
    private static final String DRAGGED = "is dragged out";
    private static final String EXCLAMATION = "!";
    private static final String EXPLOSIVEBOOST = "has an explosive boost";
    private static final String FAILED = "But it failed";
    private static final String FAINTED = "is fainted";
    private static final String FELL = "fell";
    private static final String GO = "Go";
    private static final String HASNOPP = "has no PP left!";
    private static final String HARSHLY = "harshly fell";
    private static final String INEFFECTIVE = "It has no effect";
    private static final String INTO = "into";
    private static final String ISALREADY = "is already";
    private static final String NOTHING = "nothing happens";
    private static final String NOTVERYEFFECTIVE = "It's not very effective";
    private static final String OFITS = "of its";
    private static final String ONEHITKO = "It's a one-hit ko!";
    private static final String OPPOSING = "opposing";
    private static final String OPPOSINGSWITCH = "went back to";
    private static final String PERCENTAGE = "%";
    private static final String POSSESSIVE = "'s";
    private static final String PROTECT = "protect";
    private static final String PROTECTSITSELF = "protects itself";
    private static final String RAISE = "raise";
    private static final String RECEIVED = "received";
    private static final String RECOIL = "as recoil";
    private static final String SENTOUT = "sent out";
    private static final String SHARPLY = "sharply raise";
    private static final String SPACE = " ";
    private static final String SUPEREFFECTIVE = "It's super effective";
    private static final String TERRIFYINGDROP = "has a terrifying drop";
    private static final String THE = "the";
    private static final String TIME = "time";
    private static final String TRANSFORMED = "transformed";
    private static final String TRIDOT = DOT + DOT + DOT;
    private static final String UPPERBUT = "But";
    private static final String UPPERHIT = "Hit";
    private static final String UPPEROPPOSING = "The opposing";
    private static final String USE = "use";
    private static final String USED = "used";
    
    
    private GameManager gameManager;
    private int maxMessage;

    
    public BattleLogManager() {
        this.setReferences();
    }

    private void setReferences(){
        if(gameManager == null){
            if(GameManager.getInstance() != null){
                this.gameManager = GameManager.getInstance();
            }
        }
        this.maxMessage = -1;                                   //as currentMaxDisplayed
    }
    
    public GameManager gameManagerInstance(){
        return this.gameManager;
    }
    
    public void writeToBattleLog(String log){
        this.maxMessage++;
        MainApp.battleMenuController.setLastBattleLog(log);      
    }    
    
    //there's also BattleArena as param. to avoid the long call to the Mainapp's one
    public void setUsedMoveMessage(Pokemon user, Pokemon target, BattleArena battleArena, Move move){
        //no space after toString because it's already in the pokemon's name (thanks to the toString method)
        String log =  (user.equals(battleArena.activeEnemy)? UPPEROPPOSING + SPACE : "") + user.toString() +
                      (user.equals(battleArena.activeEnemy)? USED : USE) + SPACE + move.getMoveName() + SPACE +
                      (target.equals(battleArena.activeEnemy)? EXCLAMATION : DOT);

        this.writeToBattleLog(log);
    }
    
    public void setMoveFailedMassage(){
        String log = FAILED + EXCLAMATION;
        this.writeToBattleLog(log);
    }
    
    public void setSwitchMessage(Pokemon previousActive, Pokemon newActive, Team team, BattleArena battleArena){
        String log = (team.equals(battleArena.getEnemy())? UPPEROPPOSING + SPACE : "") + previousActive.toString() + 
                     ((team.equals(battleArena.getEnemy())? OPPOSINGSWITCH + SPACE + ((Enemy)team).getEnemyName() + 
                     SPACE + ((Enemy)team).getEnemySurname() + EXCLAMATION :
                     COMMA + SPACE + COMEBACK + EXCLAMATION));
        this.writeToBattleLog(log);
        
        this.setTeamSwitchInMessage(team, newActive);            
    }
    
    private String getEnemySwitchIn(Enemy team, Pokemon newActive){
        return ((Enemy)team).getEnemyName() + SPACE + ((Enemy)team).getEnemySurname() + SPACE + SENTOUT +
                SPACE + newActive.toString() + EXCLAMATION;
    }
    
    private String getPlayerSwitchIn(Player team, Pokemon newActive){
        return GO + COMMA + SPACE + newActive.toString() + EXCLAMATION;
    }
    
    public void setTeamSwitchInMessage(Team team, Pokemon newActive){
        if(team instanceof Enemy){
            this.writeToBattleLog(this.getEnemySwitchIn((Enemy)team, newActive));
        }
        else if(team instanceof Player){
            this.writeToBattleLog(this.getPlayerSwitchIn((Player)team, newActive));
        }
    }
    
    public void setForcedSwitchMessage(Pokemon newActive, Team team){
        String log = (team.equals(MainApp.getBattleArena().getEnemy())? UPPEROPPOSING + SPACE : "") + newActive.toString() + SPACE +
                     DRAGGED + EXCLAMATION;
        this.writeToBattleLog(log);
    }
    
    public void setTurnMessage(){
       String log = "Turn " + (MainApp.getBattleArena().turnCount+1);
       this.writeToBattleLog(log);
    }
    
    public void spaceNextTurnMessage(){
        this.writeToBattleLog(SPACE);
    }
    
    public void setNumOfMultiDamages(int num){
        String log = UPPERHIT + SPACE + num + SPACE + TIME + (num > 1? "s" : "");               //plural        
        this.writeToBattleLog(log);
    }
    
    public void setDamageDone(Pokemon target, double percentage, boolean recoil){
        //in order to have precision but not too much of it
        final DecimalFormat df = new DecimalFormat("#0.00");
        String log = "";
        if(percentage > 0){                                                                     //so it's a damage
            log = (target.equals(MainApp.getBattleArena().activeEnemy) ? UPPEROPPOSING + SPACE : "") + target.toString() +
                   RECEIVED + SPACE + THE + SPACE + (percentage*100 > 100? 100 : df.format(percentage*100)) + PERCENTAGE +
                   SPACE + OFITS + SPACE + MAXHP + SPACE + (recoil? RECOIL : ASDAMAGE) + EXCLAMATION;
        }
        else{                                                                                   //it's a heal
            double heal = - percentage;
            log = (target.equals(MainApp.getBattleArena().activeEnemy) ? UPPEROPPOSING + SPACE : "") + target.toString() +
                   RECEIVED + SPACE + THE +  SPACE + (heal*100 > 100? 100 : df.format(heal*100)) + PERCENTAGE +
                   SPACE + OFITS + SPACE + MAXHP + SPACE + ASHEAL + DOT;
        }
        this.writeToBattleLog(log);
    }
    
    public void setTrasformingMove(Move previous, Move next){
        String log = previous.getMoveName() + SPACE + TRANSFORMED + SPACE + INTO + SPACE + next.getMoveName() + EXCLAMATION;
        this.writeToBattleLog(log);
    }
    
    public void setTrasformingAbility(Pokemon whoseAbilityChanges, Ability previous, Ability next){
        String log = (whoseAbilityChanges.equals(MainApp.getBattleArena().activeEnemy) ? UPPEROPPOSING + SPACE : "") +
                     whoseAbilityChanges.toString() + POSSESSIVE + SPACE + previous.getName() + SPACE + TRANSFORMED + SPACE + 
                     INTO + SPACE + next.getName() + EXCLAMATION;
        this.writeToBattleLog(log);
    }
    
    public void setStatChangeMessage(Pokemon target, int change, String stat){
        String log = (target.equals(MainApp.getBattleArena().activeEnemy) ? UPPEROPPOSING + SPACE : "") + target.toString() +
                      POSSESSIVE + SPACE + stat + SPACE + 
                      (change > 0 ? (change > 1? (change > 2? EXPLOSIVEBOOST : SHARPLY) : RAISE) :
                      (change < -1? (change < -2? TERRIFYINGDROP : HARSHLY) : FELL)) + EXCLAMATION;
        this.writeToBattleLog(log);
    }
    
    public void setMaxStatMessage(Pokemon target, String stat){
        String log = target.toString() + stat + SPACE + CANTRAISE + TRIDOT;
        this.writeToBattleLog(log);
    }
    
    public void setMinStatMessage(Pokemon target, String stat){
        String log = target.toString() + stat + SPACE + CANTLOWER + TRIDOT;
        this.writeToBattleLog(log);
    }
    
    public void setStatusAlterationMessage(Pokemon target, String status){
        String log = (target.equals(MainApp.getBattleArena().activeEnemy) ? UPPEROPPOSING + SPACE : "") + target.toString() +
                     status + SPACE + EXCLAMATION;
        this.writeToBattleLog(log);
    }
    
    public void setFaintedMessage(Pokemon pokemon){
        String log = pokemon.toString() + FAINTED + EXCLAMATION;
        this.writeToBattleLog(log);
    }
    
    public void setEffectivenessMessage(double effectiveness){
        String log = null;
        if(effectiveness == 0){
            log = INEFFECTIVE + TRIDOT;
        }
        else if(effectiveness < 1){
            log = NOTVERYEFFECTIVE + TRIDOT;
        }
        else if(effectiveness > 1){
            log = SUPEREFFECTIVE + EXCLAMATION;
        }
        if(log != null){
            this.writeToBattleLog(log);
        }
    }
    
    public void setProtectMessage(Pokemon whoProtects){
        String log = UPPERBUT + SPACE + (whoProtects.equals(MainApp.getBattleArena().activeEnemy) ? THE + SPACE + OPPOSING + SPACE : "")+
                     whoProtects.toString() + PROTECTSITSELF + EXCLAMATION;
        this.writeToBattleLog(log);
    }
    
    public void setDestroyProtectMessage(Pokemon whoIsAttacking, Pokemon whoWasProtecting){
        String log = (whoIsAttacking.equals(MainApp.getBattleArena().activeEnemy) ? UPPEROPPOSING + SPACE : "") + whoIsAttacking.toString() +
                      DESTROYS + (whoWasProtecting.equals(MainApp.getBattleArena().activeEnemy) ? OPPOSING + SPACE : "") + POSSESSIVE + 
                      SPACE + PROTECT + EXCLAMATION; 
        this.writeToBattleLog(log);
    }
    public int getMaxMessage(){
        return this.maxMessage;
    }


    public void setWeatherDotMessage(String weatherDotMessage) {
        this.writeToBattleLog(weatherDotMessage);        
    }

    public void setWeatherStartMessage(String weatherStartMessage) {
        this.writeToBattleLog(weatherStartMessage);        
    }

    public void setWeatherEndMessage(String weatherEndMessage) {
        this.writeToBattleLog(weatherEndMessage);        
    }

    public void setStatusDotMessage(Pokemon ill, String statusDot) {
       String log = (ill.equals(MainApp.getBattleArena().activeEnemy)? UPPEROPPOSING + SPACE : "") + ill.toString() + statusDot;
       this.writeToBattleLog(log);
        
    }
    
    public void setStatusPreventAttackMessage(Pokemon ill, String statusPrevent){
        String log = (ill.equals(MainApp.getBattleArena().activeEnemy)? UPPEROPPOSING + SPACE : "") + ill.toString() + statusPrevent;
        this.writeToBattleLog(log);
    }
    
    public void setStatusEndMessage(Pokemon ill, String statusEnd){
        String log =  (ill.equals(MainApp.getBattleArena().activeEnemy)? UPPEROPPOSING + SPACE : "") + ill.toString() + statusEnd;
        this.writeToBattleLog(log);
    }
    
    public void setAlreadyHasAStatus(Pokemon ill){
        String log = UPPERBUT + SPACE + (ill.equals(MainApp.getBattleArena().activeEnemy)? OPPOSING + SPACE : "") + ill.toString() +
                     ISALREADY + SPACE + ill.statusCondition.getStatusAlreadyString() + EXCLAMATION;
        this.writeToBattleLog(log);
    }

    public void setTrappedMessage(Pokemon user) {
        String log = (user.equals(MainApp.getBattleArena().activeEnemy)? UPPEROPPOSING + SPACE : "") + user.toString() + CANTESCAPE 
                     + EXCLAMATION;
        this.writeToBattleLog(log);        
    }

    public void setAbilityActivationMessage(Pokemon user, String effect){
        String log = (user.equals(MainApp.getBattleArena().activeEnemy)? UPPEROPPOSING + SPACE : "") + user.toString() + 
                     POSSESSIVE + SPACE + user.getAbility().getName() + SPACE + effect;
        this.writeToBattleLog(log);
    }
    
    public void setNothingHappensMessage(){
        this.writeToBattleLog(UPPERBUT + SPACE + NOTHING + EXCLAMATION); 
    }
    
    public void setMagnitudeSizeMessage(String magnitude, int size){
        this.writeToBattleLog(magnitude + SPACE + size + EXCLAMATION); 
    }

    public void setOneHitKOMessage() {
        this.writeToBattleLog(ONEHITKO);
        
    }

    public void setCritMessage() {
        this.writeToBattleLog(CRIT + EXCLAMATION);
        
    }

    public void setTerrainProtectionMessage(String log) {
        this.writeToBattleLog(log);
        
    }
    
    public void setStruggleEventMessage(Pokemon pokemon){
        this.writeToBattleLog(pokemon.toString() + HASNOPP);
    }

}
