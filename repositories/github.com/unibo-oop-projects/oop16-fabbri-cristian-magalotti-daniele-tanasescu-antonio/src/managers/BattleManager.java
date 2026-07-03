package managers;

import java.io.IOException;
import java.util.Random;

import main.MainApp;
import main.view.BattleMenuController;
import moves.Move;
import moves.damagingmove.DamagingMove;
import moves.damagingmove.physical.multistrike.MultiStrikePhysicalDamagingMove;
import moves.damagingmove.physical.selfrecoil.Struggle;
import moves.status.StatusMove;
import moves.status.Switch;
import pokemon.Pokemon;
import status_condition.Freeze;
import status_condition.Sleep;
import status_condition.volatile_status.Flinch;
import status_condition.volatile_status.VolatileStatusCondition;

/**
 * 
 * @author Cristian
 *
 */

public class BattleManager {

    private GameManager gameManager;

    private Pokemon[] speedTie = new Pokemon[2];

    public BattleManager() {
        this.setReferences();
    }

    private void setReferences(){
        if(gameManager == null){
            if(GameManager.getInstance() != null){
                this.gameManager = GameManager.getInstance();
            }
        }
    }

    public GameManager gameManagerInstance(){
        return this.gameManager;
    }

    public void manageBattle() throws IOException{
        this.battleTime();
        for(Pokemon pokemon : MainApp.getBattleArena().speedTie){

            this.checkAbilities();

            pokemon.canAttack = true;

            Move move = null;                                                        //this will contain a reference to the move to be executed
            if(pokemon.equals(MainApp.getBattleArena().activeEnemy)){
                move = MainApp.getBattleArena().enemyMove;
            }

            else{
                move = MainApp.getBattleArena().playerMove;
            }

            //no status check when switching!
            if(!move.equals(new Switch())){
                if(new Flinch().isContained(pokemon.volatileStatusConditions)){
                    pokemon.volatileStatusConditions[0].checkForPreventAttack(pokemon);
                }

                if(pokemon.canAttack && pokemon.statusCondition != null && 
                        (pokemon.statusCondition.equals(new Freeze()) || pokemon.statusCondition.equals(new Sleep()))){
                    pokemon.statusCondition.checkForPreventAttack(pokemon);                                        
                }

                if(pokemon.canAttack && pokemon.volatileStatusConditions[0] != null){
                    pokemon.volatileStatusConditions[0].checkForPreventAttack(pokemon);
                }
                if(pokemon.canAttack && pokemon.statusCondition != null){
                    pokemon.statusCondition.checkForPreventAttack(pokemon);
                }
            }

            if(pokemon.canAttack){
                //is it enemy or player? and is it still alive?
                if(move.equals(new Struggle())){
                    BattleMenuController.battleLogManager.setStruggleEventMessage(pokemon);
                }
                if(pokemon.equals(MainApp.getBattleArena().activeEnemy) && !pokemon.isFainted()){
                    //what kind of move is enemyMove?

                    //is Player already fainted?
                    this.executeMove(pokemon, MainApp.getBattleArena().activePlayer, move);
                    //now PPs will be decremented of Team's decrement value (with opponent Pressure will be 2)
                    move.decrementActualPP(MainApp.getBattleArena().getEnemy().getPpDecrement());
                }
                else{
                    if(!pokemon.isFainted()){
                        //what kind of move is playerMove?
                        move = MainApp.getBattleArena().playerMove;
                        //is Ememy already fainted?
                        this.executeMove(pokemon, MainApp.getBattleArena().activeEnemy, move);
                        //now PPs will be decremented of Team's decrement value (with opponent Pressure will be 2)
                        move.decrementActualPP(MainApp.getBattleArena().getPlayer().getPpDecrement());
                    }
                }

                this.checkAbilities();
            }

            pokemon.isAttacking = false;
        }

        //check faint
        this.checkFaint();
        
        //reset move choices
        MainApp.getBattleArena().hasEnemyChosen = false;
        MainApp.getBattleArena().hasPlayerChosen = false;
        MainApp.getBattleArena().activePlayer.isProtecting = false;
        MainApp.getBattleArena().activeEnemy.isProtecting = false;

        this.endOfTurn();
    }

    private void checkFaint() throws IOException {
        if(MainApp.getBattleArena().activeEnemy.isFainted()){
            if(MainApp.getBattleArena().getEnemy().isAlmostOneAlive()){
                Pokemon newActive = MainApp.getBattleArena().getEnemy().getRandomAlly(MainApp.getBattleArena().activeEnemy);
                MainApp.getBattleArena().switchActive(MainApp.getBattleArena().getEnemy(),newActive);
                BattleMenuController.battleLogManager.setTeamSwitchInMessage(MainApp.getBattleArena().getEnemy(), 
                        MainApp.getBattleArena().activeEnemy);
            }
            else{
                MainApp.goToWinScreen();
            }
        }

        if(MainApp.getBattleArena().activePlayer.isFainted()){
            if(MainApp.getBattleArena().getPlayer().isAlmostOneAlive()){
                Pokemon newActive = MainApp.getBattleArena().getPlayer().getRandomAlly(MainApp.getBattleArena().activePlayer);
                MainApp.getBattleArena().switchActive(MainApp.getBattleArena().getPlayer(),newActive);
                BattleMenuController.battleLogManager.setTeamSwitchInMessage(MainApp.getBattleArena().getPlayer(), 
                        MainApp.getBattleArena().activePlayer);
            }
            else{
                MainApp.goToLoseScreen();
            }
        }

        
    }

    public void checkAbilities() {
        this.adjustSpeedTie();
        this.speedTie[0].getAbility().checkForActivation(this.speedTie[0], this.speedTie[1], MainApp.getBattleArena());
        this.speedTie[1].getAbility().checkForActivation(this.speedTie[1], this.speedTie[0], MainApp.getBattleArena());        
    }

    private void battleTime(){
        MainApp.getBattleArena().beginningOfTurn = false;
        MainApp.getBattleArena().battleTime = true;
    }

    private void endOfTurn(){
        MainApp.getBattleArena().battleTime = false;
        MainApp.getBattleArena().endOfTurn = true;

        this.adjustSpeedTie();

        this.checkAbilities();

        this.statusDots();

        this.weatherDot();
        this.terrainEnd();
        
        try {
            this.checkFaint();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        this.nextTurn();
    }

    private void adjustSpeedTie() {
        //even dots follow a speedTie: it could differ from the battle one because someone could have fainted
        if(MainApp.getBattleArena().activeEnemy.getSpe() > MainApp.getBattleArena().activePlayer.getSpe()){
            this.enemyFirst();
        }
        else if(MainApp.getBattleArena().activePlayer.getSpe() > MainApp.getBattleArena().activeEnemy.getSpe()){
            this.playerFirst();
        }
        else{
            this.realSpeedTie();
        }

    }

    private void nextTurn(){
        MainApp.getBattleArena().turnCount++;
        MainApp.getBattleArena().activeEnemy.turnCount++;
        if(MainApp.getBattleArena().activeEnemy.statusCondition != null){
            MainApp.getBattleArena().activeEnemy.statusCondition.incrementTurnCounter();
            MainApp.getBattleArena().activeEnemy.statusCondition.checkNextTurnActive();
        }
        for(VolatileStatusCondition vsc : MainApp.getBattleArena().activeEnemy.volatileStatusConditions){
            if(vsc != null){
                vsc.incrementTurnCounter();
                vsc.checkNextTurnActive();
            }
        }
        MainApp.getBattleArena().activePlayer.turnCount++;
        if(MainApp.getBattleArena().activePlayer.statusCondition != null){
            MainApp.getBattleArena().activePlayer.statusCondition.incrementTurnCounter();
            MainApp.getBattleArena().activePlayer.statusCondition.checkNextTurnActive();
        }
        for(VolatileStatusCondition vsc : MainApp.getBattleArena().activePlayer.volatileStatusConditions){
            if(vsc != null){
                vsc.incrementTurnCounter();
                vsc.checkNextTurnActive();
            }
        }

        if(MainApp.getBattleArena().weather != null){
            MainApp.getBattleArena().weather.nextTurn(this.speedTie[0], this.speedTie[1], MainApp.getBattleArena());
        }
        
        if(MainApp.getBattleArena().terrain != null){
            MainApp.getBattleArena().terrain.decrementTurnActive(speedTie[0], speedTie[1], MainApp.getBattleArena());
        }

        this.checkAbilities();

        MainApp.getBattleArena().endOfTurn = false;
        MainApp.getBattleArena().beginningOfTurn = true;
        BattleMenuController.battleLogManager.spaceNextTurnMessage();
        BattleMenuController.battleLogManager.setTurnMessage();
    }

    private void executeMove(Pokemon user, Pokemon target, Move move){
        if(move instanceof StatusMove){
            if(move.equals(new Switch()) && !user.canSwitch){
                BattleMenuController.battleLogManager.setTrappedMessage(user);
            }
            else{

                ((StatusMove)move).getEffect(user, target, MainApp.getBattleArena());
            }
        }
        else{                                                                           //Damaging
            if(move instanceof MultiStrikePhysicalDamagingMove){
                ((MultiStrikePhysicalDamagingMove) move).multiStrike(user, target, MainApp.getBattleArena());
            }
            else{                                                                       //Physical or Special
                ((DamagingMove)move).getDamage(user, target, MainApp.getBattleArena());
            }
        }
    }   

    private void realSpeedTie() {
        Random random = new Random();
        if(random.nextBoolean()){
            this.enemyFirst();
        }
        else{
            this.playerFirst();
        }
    }

    private void playerFirst() {
        this.speedTie[0] = MainApp.getBattleArena().activePlayer;
        this.speedTie[1] = MainApp.getBattleArena().activeEnemy;       
    }

    private void enemyFirst() {
        this.speedTie[0] = MainApp.getBattleArena().activeEnemy;
        this.speedTie[1] = MainApp.getBattleArena().activePlayer;      
    }

    private void terrainEnd() {
        if(MainApp.getBattleArena().terrain != null){
            for(Pokemon pokemon : this.speedTie){
                MainApp.getBattleArena().terrain.getTerrainEndTurnEffect(pokemon);
            }
        }
    }

    private void weatherDot() {
        if(MainApp.getBattleArena().weather != null){
            for(Pokemon pokemon : this.speedTie){
                MainApp.getBattleArena().weather.checkForWeatherDot(pokemon);
            }
        }

    }

    private void statusDots() {
        for(Pokemon pokemon : this.speedTie){
            if(pokemon.statusCondition != null){
                pokemon.statusCondition.checkForDotDamage(pokemon);
            }
            if(pokemon.volatileStatusConditions[0] != null){
                pokemon.volatileStatusConditions[0].checkForDotDamage(pokemon);     //it's done for all
            }
        }
    }


}
