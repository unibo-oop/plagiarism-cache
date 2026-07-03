package main.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Tooltip;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import main.MainApp;
import managers.BattleLogManager;
import managers.BattleManager;
import moves.Move;
import moves.damagingmove.DamagingMove;
import moves.damagingmove.physical.selfrecoil.Struggle;
import moves.damagingmove.special.SpecialDamagingMove;
import moves.status.Switch;
import pokemon.Gender;
import pokemon.Pokemon;
import status_condition.volatile_status.VolatileStatusCondition;
import team.Player;
import team.Team;
import team.enemies.Enemy;


public class BattleMenuController implements Initializable{

    private static final int NUMMOVEBUTTONS = 4;        /* == number of moves == number of imageviews 
                                                           == 3*(number of labels) == ... */
    private static final int LABELSPERBUTTON = 3;

    private static final int ALLIES = 5;

    private static final double SWITCHIMAGESCALE = 3;

    private boolean spriteChanged;                      //is the sprite/active pokemon changed?

    @FXML
    AnchorPane ancorPane;

    @FXML
    ImageView enemySprite;

    @FXML
    ImageView playerSprite;

    @FXML
    ImageView enemyMiniSprite;

    @FXML
    ImageView playerMiniSprite;

    @FXML
    ImageView moveCategory1;

    @FXML
    ImageView moveCategory2;

    @FXML
    ImageView moveCategory3;

    @FXML
    ImageView moveCategory4;

    @FXML
    ImageView ally1;

    @FXML
    ImageView ally2;

    @FXML
    ImageView ally3;

    @FXML
    ImageView ally4;

    @FXML
    ImageView ally5;

    @FXML
    ImageView playerBall1;

    @FXML
    ImageView playerBall2;

    @FXML
    ImageView playerBall3;

    @FXML
    ImageView playerBall4;

    @FXML
    ImageView playerBall5;

    @FXML
    ImageView playerBall6;

    @FXML
    ImageView enemyBall1;

    @FXML
    ImageView enemyBall2;

    @FXML
    ImageView enemyBall3;

    @FXML
    ImageView enemyBall4;

    @FXML
    ImageView enemyBall5;

    @FXML
    ImageView enemyBall6;

    @FXML
    ImageView playerGender;

    @FXML
    ImageView enemyGender; 

    @FXML
    Button move1;

    @FXML
    Button move2;

    @FXML
    Button move3;

    @FXML
    Button move4;

    @FXML
    Button switch1;

    @FXML
    Button switch2;

    @FXML
    Button switch3;

    @FXML
    Button switch4;

    @FXML
    Button switch5;

    @FXML
    Tooltip tooltipMove1;

    @FXML
    Tooltip tooltipMove2;

    @FXML
    Tooltip tooltipMove3;

    @FXML
    Tooltip tooltipMove4;

    @FXML
    Tooltip tooltipAlly1;

    @FXML
    Tooltip tooltipAlly2;

    @FXML
    Tooltip tooltipAlly3;

    @FXML
    Tooltip tooltipAlly4;

    @FXML
    Tooltip tooltipAlly5;

    @FXML
    Tooltip tooltipPlayerAbility;

    @FXML
    Tooltip tooltipPlayerNature;

    @FXML
    Tooltip tooltipPlayerEVIVHP;

    @FXML
    Tooltip tooltipPlayerEVIVAtk;

    @FXML
    Tooltip tooltipPlayerEVIVDef;

    @FXML
    Tooltip tooltipPlayerEVIVSpA;

    @FXML
    Tooltip tooltipPlayerEVIVSpD;

    @FXML
    Tooltip tooltipPlayerEVIVSpe;

    @FXML
    Tooltip tooltipPlayerStatus;

    @FXML
    Tooltip tooltipEnemyAbility;

    @FXML
    Tooltip tooltipEnemyNature;

    @FXML
    Tooltip tooltipEnemyEVIVHP;

    @FXML
    Tooltip tooltipEnemyEVIVAtk;

    @FXML
    Tooltip tooltipEnemyEVIVDef;

    @FXML
    Tooltip tooltipEnemyEVIVSpA;

    @FXML
    Tooltip tooltipEnemyEVIVSpD;

    @FXML
    Tooltip tooltipEnemyEVIVSpe;

    @FXML
    Tooltip tooltipEnemyStatus;

    @FXML
    Label weather;

    @FXML
    Label terrain;

    @FXML
    Label moveName1;

    @FXML
    Label moveName2;

    @FXML
    Label moveName3;

    @FXML
    Label moveName4;

    @FXML
    Label movePP1;

    @FXML
    Label movePP2;

    @FXML
    Label movePP3;

    @FXML
    Label movePP4;

    @FXML
    Label moveMaxPP1;

    @FXML
    Label moveMaxPP2;

    @FXML
    Label moveMaxPP3;

    @FXML
    Label moveMaxPP4;

    @FXML
    Label playerName;

    @FXML
    Label playerLevel;

    @FXML
    Label playerType1;

    @FXML
    Label playerTypeSlash;

    @FXML
    Label playerType2;

    @FXML
    Label playerAbility;

    @FXML
    Label playerNature;

    @FXML
    Label playerActualHP;

    @FXML
    Label playerMaxHP;

    @FXML
    Label playerAtk;

    @FXML
    Label playerDef;

    @FXML
    Label playerSpA;

    @FXML
    Label playerSpD;

    @FXML
    Label playerSpe;

    @FXML
    Label playerAccuracy;

    @FXML
    Label playerElusion;

    @FXML
    Label playerStatus;

    @FXML
    Label enemyName;

    @FXML
    Label enemyLevel;

    @FXML
    Label enemyType1;

    @FXML
    Label enemyTypeSlash;

    @FXML
    Label enemyType2;

    @FXML
    Label enemyAbility;

    @FXML
    Label enemyNature;

    @FXML
    Label enemyActualHP;

    @FXML
    Label enemyMaxHP;

    @FXML
    Label enemyAtk;

    @FXML
    Label enemyDef;

    @FXML
    Label enemySpA;

    @FXML
    Label enemySpD;

    @FXML
    Label enemySpe;

    @FXML
    Label enemyAccuracy;

    @FXML
    Label enemyElusion;

    @FXML
    Label enemyStatus;

    @FXML
    Label enemyTrainer;

    @FXML
    ListView<String> listView;

    Pokemon[] backTeam;
    private ObservableList<String> battleLog = FXCollections.observableArrayList();

    private static BattleManager battleManager;
    public static BattleLogManager battleLogManager;

    public BattleMenuController() {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        battleManager = new BattleManager();
        if(MainApp.battleMenuController != null){
            MainApp.battleMenuController = this;
        }
        this.setSpriteChanged(true);                            //the first time sprites will be displayed
        int index = battleManager.gameManagerInstance().getPlayer().getCurrentPosition();
        MainApp.setBattleArena(battleManager.gameManagerInstance().getPlayer(), 
                               battleManager.gameManagerInstance().getAllEnemies()[index]);
        battleManager = new BattleManager();
        battleLogManager = new BattleLogManager();
        
        Image image = new Image(
                BattleMenuController.class.getResourceAsStream(MainApp.getBattleArena().getEnemy().getBattleScenario()));
        // new BackgroundSize(width, height, widthAsPercentage, heightAsPercentage, contain, cover)
        BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, true, true);
        // new BackgroundImage(image, repeatX, repeatY, position, size)
        BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, 
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        // new Background(images...)
        Background background = new Background(backgroundImage);
        ancorPane.setBackground(background);

        this.listView.getStylesheets().add(this.getClass().getResource("resources/cellsize.css").toExternalForm());
        battleLogManager.setTeamSwitchInMessage(MainApp.getBattleArena().getEnemy(), MainApp.getBattleArena().activeEnemy);
        battleLogManager.setTeamSwitchInMessage(MainApp.getBattleArena().getPlayer(), MainApp.getBattleArena().activePlayer);
        battleManager.checkAbilities();

        this.refreshScreen();
    }

    private void setActiveSprites(){
        if(!MainApp.getBattleArena().activeEnemy.isFainted()){
            Image enemyImage = new Image (
                    getClass().getResourceAsStream(MainApp.getBattleArena().activeEnemy.pokemonFrontImage()));
            enemySprite.setImage(enemyImage);
            enemyMiniSprite.setImage(enemyImage);
            if(MainApp.getBattleArena().activeEnemy.toString().startsWith("Tapu")){
                enemySprite.setScaleX(0.3);
                enemySprite.setScaleY(0.3);
                enemyMiniSprite.setScaleX(MainApp.getBattleArena().activeEnemy.getFrontMiniSizeScale());
                enemyMiniSprite.setScaleY(MainApp.getBattleArena().activeEnemy.getFrontMiniSizeScale());
            }
            else{
                enemySprite.setScaleX(0.65);
                enemySprite.setScaleY(0.65);
                enemyMiniSprite.setScaleX(2*MainApp.getBattleArena().activeEnemy.getFrontMiniSizeScale());
                enemyMiniSprite.setScaleY(2*MainApp.getBattleArena().activeEnemy.getFrontMiniSizeScale());
            }
        }
        else{
            enemySprite.setImage(null);
            enemyMiniSprite.setImage(null);
        }
        if(!MainApp.getBattleArena().activePlayer.isFainted()){

            playerSprite.setImage(new Image (
                    getClass().getResourceAsStream(MainApp.getBattleArena().activePlayer.pokemonBackImage())));
            playerMiniSprite.setImage(new Image (
                    getClass().getResourceAsStream(MainApp.getBattleArena().activePlayer.pokemonFrontImage())));
            if(MainApp.getBattleArena().activePlayer.toString().startsWith("Tapu")){
                playerSprite.setScaleX(0.6);
                playerSprite.setScaleY(0.6);
                playerMiniSprite.setScaleX(MainApp.getBattleArena().activePlayer.getFrontMiniSizeScale());
                playerMiniSprite.setScaleY(MainApp.getBattleArena().activePlayer.getFrontMiniSizeScale());                    
            }
            else{
                playerSprite.setScaleX(0.9);
                playerSprite.setScaleY(0.9);

                playerMiniSprite.setScaleX(2*MainApp.getBattleArena().activePlayer.getFrontMiniSizeScale());
                playerMiniSprite.setScaleY(2*MainApp.getBattleArena().activePlayer.getFrontMiniSizeScale());
            }
        }
        else{
            playerSprite.setImage(null);
            playerMiniSprite.setImage(null);
        }
    }

    private void setMoveButtons(){        
        Tooltip[] moveTooltips = new Tooltip[]{tooltipMove1, tooltipMove2, tooltipMove3, tooltipMove4};
        Label[] moveLabels = new Label[]{moveName1, movePP1, moveMaxPP1,
                moveName2, movePP2, moveMaxPP2,
                moveName3, movePP3, moveMaxPP3,
                moveName4, movePP4, moveMaxPP4};
        ImageView[] moveCategories = new ImageView[]{moveCategory1, moveCategory2, moveCategory3, moveCategory4};

        Move[] playerMoves = MainApp.getBattleArena().activePlayer.getAllMoves();

        for(int i = 0; i < NUMMOVEBUTTONS; i++){
            //set name and its color 
            int labelindex = i * LABELSPERBUTTON;
            moveLabels[labelindex].setText(playerMoves[i].getMoveName());
            moveLabels[labelindex].setTextFill(Paint.valueOf(playerMoves[i].getMoveType().getTypeColor()));
            moveLabels[labelindex].setEffect(new DropShadow(10, Color.BLACK));  

            //setting PP and MaxPP 
            moveLabels[labelindex+1].setText(Integer.toString(playerMoves[i].getActualPP()));
            if(playerMoves[i].getActualPP() <= playerMoves[i].getMaxPP()/2){
                if(playerMoves[i].getActualPP() <= playerMoves[i].getMaxPP()/4){
                    moveLabels[labelindex+1].setTextFill(Paint.valueOf("#ff0000"));                     //red
                }
                else{
                    moveLabels[labelindex+1].setTextFill(Paint.valueOf("#ffa500"));                     //orange
                }
            }
            else{
                moveLabels[labelindex+1].setTextFill(Paint.valueOf("#000000"));                         //black
            }
            moveLabels[labelindex+2].setText(Integer.toString(playerMoves[i].getMaxPP()));

            //setting move category image
            if(!(playerMoves[i] instanceof DamagingMove)){                           //if it's a Status Move
                moveCategories[i].setImage(new Image 
                        (getClass().getResourceAsStream("resources/StatusMove.gif")));
            }
            else{
                if(playerMoves[i] instanceof SpecialDamagingMove){
                    moveCategories[i].setImage(new Image 
                            (getClass().getResourceAsStream("resources/SpecialMove.gif")));
                }
                else{                                                                //so Physical
                    moveCategories[i].setImage(new Image 
                            (getClass().getResourceAsStream("resources/PhysicalMove.gif")));
                }
            }

            //constructs the tooltip : type, power, accuracy, description
            String tooltip = "Type : " + playerMoves[i].getMoveType().getTypeName() +
                    "\nPower : " +       
                    (playerMoves[i] instanceof DamagingMove? 
                            (((DamagingMove)playerMoves[i]).getMoveBasePower() > 900 ?     //1hko, ...
                                    "-" 
                                    : ((DamagingMove)playerMoves[i]).getMoveBasePower())
                            : "-") +
                    "\nAccuracy : " + 
                    (playerMoves[i].getMoveAccuracy() > 900? 
                            "-" 
                            : ((int)(playerMoves[i].getMoveAccuracy() * 100)) + " % ") +
                    "\nPriority : " + playerMoves[i].getPriority() +
                    "\nDescription :\n" + playerMoves[i].getMoveDescription();
            moveTooltips[i].setText(tooltip);   
        }
    }

    private void setSwitchButton(){
        Button[] switchButtons = new Button[]{switch1, switch2, switch3, switch4, switch5};
        ImageView[] allies = new ImageView[]{ally1, ally2, ally3, ally4, ally5};
        Tooltip[] allyTooltips = new Tooltip[]{tooltipAlly1, tooltipAlly2, tooltipAlly3, tooltipAlly4, tooltipAlly5};
        //this is the team except the active one
        this.backTeam = MainApp.getBattleArena().getPlayer().getAllies(MainApp.getBattleArena().activePlayer);

        for(int i = 0; i < ALLIES; i++){
            allies[i].setImage(new Image 
                    (getClass().getResourceAsStream(backTeam[i].pokemonFrontImage())));
            if(!backTeam[i].isFainted()){
                //name
                switchButtons[i].setText(backTeam[i].toString());
                //image
                allies[i].setImage(new Image 
                        (getClass().getResourceAsStream(backTeam[i].pokemonFrontImage())));
                if(backTeam[i].toString().startsWith("Tapu")){
                    allies[i].setScaleX(1.2*backTeam[i].getFrontMiniSizeScale());
                    allies[i].setScaleY(1.2*backTeam[i].getFrontMiniSizeScale());
                }
                else{
                    allies[i].setScaleX(SWITCHIMAGESCALE*backTeam[i].getFrontMiniSizeScale());
                    allies[i].setScaleY(SWITCHIMAGESCALE*backTeam[i].getFrontMiniSizeScale());
                }
                //tooltip
                String tooltip ="Level : " + backTeam[i].getLevel() +
                        "\nType : "+ backTeam[i].getType()[0].getTypeName() + 
                        (backTeam[i].getType().length > 1 && backTeam[i].getType()[1] != null? " / " + backTeam[i].getType()[1].getTypeName() 
                                : "") +
                        "\nHP : " +  backTeam[i].getHp() + " / " + backTeam[i].getMaxHp() +
                        "\nAtk : " + backTeam[i].getAtk() + "\tDef : " + backTeam[i].getDef() +
                        "\nSpA : " + backTeam[i].getSpA() + "\tSpD :" + backTeam[i].getSpD() +
                        "\nSpe : " + backTeam[i].getSpe() +
                        "\nAbility : " + backTeam[i].getAbility().getName()+
                        "\nStatus Condition : " + 
                        (backTeam[i].statusCondition != null?
                                backTeam[i].statusCondition.toString()
                                : "null");
                allyTooltips[i].setText(tooltip);
            }
            else{
                switchButtons[i].setOpacity(0.5);
                switchButtons[i].setText("(Fainted)");
            }
        }
    }

    private void setStatistics(){
        Pokemon player = MainApp.getBattleArena().activePlayer;
        Pokemon enemy = MainApp.getBattleArena().activeEnemy;

        playerName.setText(player.toString());
        this.setGenderImage(this.playerGender, player.getGender());
        playerLevel.setText(Integer.toString(player.getLevel()));
        playerType1.setText(player.getType()[0].getTypeName());
        playerType1.setTextFill(Paint.valueOf(player.getType()[0].getTypeColor()));
        playerType1.setEffect(new DropShadow(10, Color.BLACK)); 
        if(player.getType().length > 1 && player.getType()[1] != null){
            playerTypeSlash.setText(" / ");
            playerType2.setText(player.getType()[1].getTypeName());
            playerType2.setTextFill(Paint.valueOf(player.getType()[1].getTypeColor()));
            playerType2.setEffect(new DropShadow(10, Color.BLACK)); 
        }
        else{
            playerTypeSlash.setText("");
            playerType2.setText("");
        }
        playerAbility.setText(player.getAbility().getName());   
        tooltipPlayerAbility.setText(player.getAbility().getDescription());
        playerNature.setText(player.getNature().getNatureName());
        this.setTooltipNature(tooltipPlayerNature, player.getNature().getAllPercentages());
        playerActualHP.setText(Integer.toString(player.getHp()));
        if(player.getHp() <= player.getMaxHp()/4){
            playerActualHP.setTextFill(Paint.valueOf("#ff0000"));                     //red
        }
        else{
            playerActualHP.setTextFill(Paint.valueOf("#000000"));                     //black (for heal)
        }
        playerMaxHP.setText(Integer.toString(player.getMaxHp()));
        tooltipPlayerEVIVHP.setText(player.getIVHP() + " IV \t" + player.getEvHP() + " EV");

        enemyName.setText(enemy.toString());
        this.setGenderImage(this.enemyGender, enemy.getGender());
        enemyLevel.setText(Integer.toString(enemy.getLevel()));
        enemyType1.setText(enemy.getType()[0].getTypeName());
        enemyType1.setTextFill(Paint.valueOf(enemy.getType()[0].getTypeColor()));
        enemyType1.setEffect(new DropShadow(10, Color.BLACK)); 
        if(enemy.getType().length > 1 && enemy.getType()[1] != null){
            enemyTypeSlash.setText(" / ");
            enemyType2.setText(enemy.getType()[1].getTypeName());
            enemyType2.setTextFill(Paint.valueOf(enemy.getType()[1].getTypeColor()));
            enemyType2.setEffect(new DropShadow(10, Color.BLACK)); 
        }
        else{
            enemyTypeSlash.setText("");
            enemyType2.setText("");
        }
        enemyAbility.setText(enemy.getAbility().getName());
        tooltipEnemyAbility.setText(enemy.getAbility().getDescription());
        enemyNature.setText(enemy.getNature().getNatureName());
        this.setTooltipNature(tooltipEnemyNature, enemy.getNature().getAllPercentages());
        enemyActualHP.setText(Integer.toString(enemy.getHp()));
        if(enemy.getHp() <= enemy.getMaxHp()/4){
            enemyActualHP.setTextFill(Paint.valueOf("#ff0000"));                     //red
        }
        else{
            enemyActualHP.setTextFill(Paint.valueOf("#000000"));                     //black (for heal)
        }
        enemyMaxHP.setText(Integer.toString(enemy.getMaxHp()));
        tooltipEnemyEVIVHP.setText(enemy.getIVHP() + " IV \t" + enemy.getEvHP() + " EV");
        this.setAtkLabels(player, enemy);
        this.setDefLabels(player, enemy);
        this.setSpALabels(player, enemy);
        this.setSpDLabels(player, enemy);
        this.setSpeLabels(player, enemy);
        this.setAccuracyLabels(player, enemy);
        this.setElusionLabels(player, enemy);
        this.setStatusLabels(player, enemy);
        enemyTrainer.setText(MainApp.getBattleArena().getEnemy().getEnemyName() + " " +
                MainApp.getBattleArena().getEnemy().getEnemySurname());
    }

    private void setTooltipNature(Tooltip tooltipPlayerNature, double[] allPercentages) {
        String[] allStats = {BattleLogManager.ATTACK, BattleLogManager.DEFENSE, BattleLogManager.SPEED,
                BattleLogManager.SPA, BattleLogManager.SPD};
        int indexDec = 0;                               //nature that lowers the stat
        int indexInc = 0;                               //nature that raise the stat
        int current = 0;
        for(double perc : allPercentages){
            if(perc < 1){
                indexDec = current;
            }
            else if(perc > 1){
                indexInc = current;
            }
            current++;
        }
        if(indexDec == 0 && indexInc == 0){             //they were not changed ('cause that nature doesn't change any stat)
            tooltipPlayerNature.setText("-");
        }
        else{
            tooltipPlayerNature.setText("+ 10% " + allStats[indexInc] + " , - 10% " + allStats[indexDec]);
        }        
    }

    private void setGenderImage(ImageView imageView, Gender gender) {
        if(gender.equals(Gender.MALE)){
            imageView.setImage(new Image 
                    (getClass().getResourceAsStream("resources/MaleSymbol.png")));
        }
        else if(gender.equals(Gender.FEMALE)){
            imageView.setImage(new Image 
                    (getClass().getResourceAsStream("resources/FemaleSymbol.png")));
        }
        else{
            imageView.setImage(null);
        }

    }

    public void requestMove1() throws IOException{
        if(!MainApp.getBattleArena().hasPlayerChosen && MainApp.getBattleArena().activePlayer.getMove(0).hasSomePP()){
            Move move = MainApp.getBattleArena().activePlayer.getMove(0);
            this.updateBattleArena(move);
        }
        else{
            checkForStruggle(MainApp.getBattleArena().activePlayer);
        }
    }

    public void requestMove2() throws IOException{
        if(!MainApp.getBattleArena().hasPlayerChosen && MainApp.getBattleArena().activePlayer.getMove(1).hasSomePP()){
            Move move = MainApp.getBattleArena().activePlayer.getMove(1);
            this.updateBattleArena(move);
        }
        else{
            checkForStruggle(MainApp.getBattleArena().activePlayer);
        }
    }

    public void requestMove3() throws IOException{
        if(!MainApp.getBattleArena().hasPlayerChosen && MainApp.getBattleArena().activePlayer.getMove(2).hasSomePP()){
            Move move = MainApp.getBattleArena().activePlayer.getMove(2);
            this.updateBattleArena(move);
        }
        else{
            checkForStruggle(MainApp.getBattleArena().activePlayer);
        }
    }

    public void requestMove4() throws IOException{
        if(!MainApp.getBattleArena().hasPlayerChosen && MainApp.getBattleArena().activePlayer.getMove(3).hasSomePP()){
            Move move = MainApp.getBattleArena().activePlayer.getMove(3);
            this.updateBattleArena(move);
        }
        else{
            checkForStruggle(MainApp.getBattleArena().activePlayer);
        }
    }

    public void requestSwitch1() throws IOException{
        if(!backTeam[0].isFainted() && !MainApp.getBattleArena().hasPlayerChosen){
            Move move = new Switch(MainApp.getBattleArena().activePlayer, backTeam[0]);
            this.updateBattleArena(move);
        }
    }

    public void requestSwitch2() throws IOException{
        if(!backTeam[1].isFainted() && !MainApp.getBattleArena().hasPlayerChosen){
            Move move = new Switch(MainApp.getBattleArena().activePlayer, backTeam[1]);
            this.updateBattleArena(move);
        }
    }

    public void requestSwitch3() throws IOException{
        if(!backTeam[2].isFainted() && !MainApp.getBattleArena().hasPlayerChosen){
            Move move = new Switch(MainApp.getBattleArena().activePlayer, backTeam[2]);
            this.updateBattleArena(move);
        }
    }

    public void requestSwitch4() throws IOException{
        if(!backTeam[3].isFainted() && !MainApp.getBattleArena().hasPlayerChosen){
            Move move = new Switch(MainApp.getBattleArena().activePlayer, backTeam[3]);
            this.updateBattleArena(move);
        }
    }

    public void requestSwitch5() throws IOException{
        if(!backTeam[4].isFainted() && !MainApp.getBattleArena().hasPlayerChosen){
            Move move = new Switch(MainApp.getBattleArena().activePlayer, backTeam[4]);
            this.updateBattleArena(move);
        }
    }

    private void updateBattleArena(Move move) throws IOException{
        MainApp.getBattleArena().setPlayerMove(move);
        MainApp.getBattleArena().getEnemy().getAlBattle().battleDecision(MainApp.getBattleArena());
        MainApp.getBattleArena().setSpeedTie();
        battleManager.manageBattle();

        this.refreshScreen();
    }

    private void setAtkLabels(Pokemon player, Pokemon enemy){
        playerAtk.setText(Integer.toString(player.getAtk()));
        double atkDoubleAlter = this.elaborateAlterations(player.alterationAtk);
        double totalChanges = atkDoubleAlter * player.getOtherModifierFactorAtk();
        if(totalChanges > 1){
            playerAtk.setTextFill(Paint.valueOf("#00ff00"));            //green 
        }
        else if(totalChanges < 1){
            playerAtk.setTextFill(Paint.valueOf("#ff4500"));            //red
        }
        else{                                                           //==1
            playerAtk.setTextFill(Paint.valueOf("#000000"));            //black
        }
        tooltipPlayerEVIVAtk.setText(player.getIVAtk() + " IV \t" + player.getEvAtk() + " EV");

        enemyAtk.setText(Integer.toString(enemy.getAtk()));
        atkDoubleAlter = this.elaborateAlterations(enemy.alterationAtk);
        totalChanges = atkDoubleAlter * enemy.getOtherModifierFactorAtk();
        if(totalChanges > 1){
            enemyAtk.setTextFill(Paint.valueOf("#00ff00"));
        }
        else if(totalChanges < 1){
            enemyAtk.setTextFill(Paint.valueOf("#ff4500"));
        }
        else{
            enemyAtk.setTextFill(Paint.valueOf("#000000"));
        }
        tooltipEnemyEVIVAtk.setText(enemy.getIVAtk() + " IV \t" + enemy.getEvAtk() + " EV");
    }

    private void setDefLabels(Pokemon player, Pokemon enemy){
        playerDef.setText(Integer.toString(player.getDef()));
        double defDoubleAlter = this.elaborateAlterations(player.alterationDef);
        double totalChanges = defDoubleAlter * player.getOtherModifierFactorDef();
        if(totalChanges > 1){
            playerDef.setTextFill(Paint.valueOf("#00ff00"));            //green 
        }
        else if(totalChanges < 1){
            playerDef.setTextFill(Paint.valueOf("#ff4500"));            //red
        }
        else{                                                           //==1
            playerDef.setTextFill(Paint.valueOf("#000000"));            //black
        }
        tooltipPlayerEVIVDef.setText(player.getIVDef() + " IV \t" + player.getEvDef() + " EV");

        enemyDef.setText(Integer.toString(enemy.getDef()));
        defDoubleAlter = this.elaborateAlterations(enemy.alterationDef);
        totalChanges = defDoubleAlter * enemy.getOtherModifierFactorDef();
        if(totalChanges > 1){
            enemyDef.setTextFill(Paint.valueOf("#00ff00"));             
        }
        else if(totalChanges < 1){
            enemyDef.setTextFill(Paint.valueOf("#ff4500"));            
        }
        else{                                                           
            enemyDef.setTextFill(Paint.valueOf("#000000"));            
        }
        tooltipEnemyEVIVDef.setText(enemy.getIVDef() + " IV \t" + enemy.getEvDef() + " EV");
    }

    private void setSpALabels(Pokemon player, Pokemon enemy){
        playerSpA.setText(Integer.toString(player.getSpA()));
        double spaDoubleAlter = this.elaborateAlterations(player.alterationSpA);
        double totalChanges = spaDoubleAlter * player.getOtherModifierFactorSpA();
        if(totalChanges > 1){
            playerSpA.setTextFill(Paint.valueOf("#00ff00"));            //green 
        }
        else if(totalChanges < 1){
            playerSpA.setTextFill(Paint.valueOf("#ff4500"));            //red
        }
        else{                                                           //==1
            playerSpA.setTextFill(Paint.valueOf("#000000"));            //black
        }
        tooltipPlayerEVIVSpA.setText(player.getIVSpA() + " IV \t" + player.getEvSpA() + " EV");

        enemySpA.setText(Integer.toString(enemy.getSpA()));
        spaDoubleAlter = this.elaborateAlterations(enemy.alterationSpA);
        totalChanges = spaDoubleAlter * enemy.getOtherModifierFactorSpA();
        if(totalChanges > 1){
            enemySpA.setTextFill(Paint.valueOf("#00ff00"));            
        }
        else if(totalChanges < 1){
            enemySpA.setTextFill(Paint.valueOf("#ff4500"));            
        }
        else{                                                           
            enemySpA.setTextFill(Paint.valueOf("#000000"));            
        }
        tooltipEnemyEVIVSpA.setText(enemy.getIVSpA() + " IV \t" + enemy.getEvSpA() + " EV");
    }

    private void setSpDLabels(Pokemon player, Pokemon enemy){
        playerSpD.setText(Integer.toString(player.getSpD()));
        double spdDoubleAlter = this.elaborateAlterations(player.alterationSpD);
        double totalChanges = spdDoubleAlter * player.getOtherModifierFactorSpD();
        if(totalChanges > 1){
            playerSpD.setTextFill(Paint.valueOf("#00ff00"));            //green 
        }
        else if(totalChanges < 1){
            playerSpD.setTextFill(Paint.valueOf("#ff4500"));            //red
        }
        else{                                                           //==1
            playerSpD.setTextFill(Paint.valueOf("#000000"));            //black
        }
        tooltipPlayerEVIVSpD.setText(player.getIVSpD() + " IV \t" + player.getEvSpD() + " EV");

        enemySpD.setText(Integer.toString(enemy.getSpD()));
        spdDoubleAlter = this.elaborateAlterations(enemy.alterationSpD);
        totalChanges = spdDoubleAlter * enemy.getOtherModifierFactorSpD();
        if(totalChanges > 1){
            enemySpD.setTextFill(Paint.valueOf("#00ff00"));            
        }
        else if(totalChanges < 1){
            enemySpD.setTextFill(Paint.valueOf("#ff4500"));            
        }
        else{                                                           
            enemySpD.setTextFill(Paint.valueOf("#000000"));            
        }
        tooltipEnemyEVIVSpD.setText(enemy.getIVSpD() + " IV \t" + enemy.getEvSpD() + " EV");
    }

    private void setSpeLabels(Pokemon player, Pokemon enemy){
        playerSpe.setText(Integer.toString(player.getSpe()));
        double speDoubleAlter = this.elaborateAlterations(player.alterationSpe);
        double totalChanges = speDoubleAlter * player.getOtherModifierFactorSpe();
        if(totalChanges > 1){
            playerSpe.setTextFill(Paint.valueOf("#00ff00"));            //green 
        }
        else if(totalChanges < 1){
            playerSpe.setTextFill(Paint.valueOf("#ff4500"));            //red
        }
        else{                                                           //==1
            playerSpe.setTextFill(Paint.valueOf("#000000"));            //black
        }
        tooltipPlayerEVIVSpe.setText(player.getIVSpe() + " IV \t" + player.getEvSpe() + " EV");

        enemySpe.setText(Integer.toString(enemy.getSpe()));
        speDoubleAlter = this.elaborateAlterations(enemy.alterationSpe);
        totalChanges = speDoubleAlter * enemy.getOtherModifierFactorSpe();
        if(totalChanges > 1){
            enemySpe.setTextFill(Paint.valueOf("#00ff00"));            
        }
        else if(totalChanges < 1){
            enemySpe.setTextFill(Paint.valueOf("#ff4500"));            
        }
        else{                                                           
            enemySpe.setTextFill(Paint.valueOf("#000000"));            
        }
        tooltipEnemyEVIVSpe.setText(enemy.getIVSpe() + " IV \t" + enemy.getEvSpe() + " EV");
    }

    private void setAccuracyLabels(Pokemon player, Pokemon enemy){
        int accuracy = (int) (player.getAccuracy()*100);
        playerAccuracy.setText(Integer.toString(accuracy) + " %");
        if(accuracy > 100){
            playerAccuracy.setTextFill(Paint.valueOf("#00ff00"));            //green 
        }
        else if(accuracy < 100){
            playerAccuracy.setTextFill(Paint.valueOf("#ff4500"));            //red
        }
        else{                                                                //==100%
            playerAccuracy.setTextFill(Paint.valueOf("#000000"));            //black
        }

        accuracy = (int) (enemy.getAccuracy()*100);
        enemyAccuracy.setText(Integer.toString(accuracy) + " %");
        if(accuracy > 100){
            enemyAccuracy.setTextFill(Paint.valueOf("#00ff00"));            //green 
        }
        else if(accuracy < 100){
            enemyAccuracy.setTextFill(Paint.valueOf("#ff4500"));            //red
        }
        else{                                                               //==100%
            enemyAccuracy.setTextFill(Paint.valueOf("#000000"));            //black
        }
    }

    private void setElusionLabels(Pokemon player, Pokemon enemy){
        int elusion = (int)(player.getElusion()*100);
        playerElusion.setText(Integer.toString(elusion) + " %");
        if(elusion > 100){
            playerElusion.setTextFill(Paint.valueOf("#00ff00"));            //green 
        }
        else if(elusion < 100){
            playerElusion.setTextFill(Paint.valueOf("#ff4500"));            //red
        }
        else{                                                               //==100%
            playerElusion.setTextFill(Paint.valueOf("#000000"));            //black
        }

        elusion = (int) (enemy.getElusion()*100);
        enemyElusion.setText(Integer.toString(elusion) + " %");
        if(elusion > 100){
            enemyElusion.setTextFill(Paint.valueOf("#00ff00"));            //green 
        }
        else if(elusion < 100){
            enemyElusion.setTextFill(Paint.valueOf("#ff4500"));            //red
        }
        else{                                                              //==100%
            enemyElusion.setTextFill(Paint.valueOf("#000000"));            //black
        }
    }

    private void setStatusLabels(Pokemon player, Pokemon enemy){
        if(player.statusCondition != null){
            playerStatus.setText(player.statusCondition.toString());
        }
        else{
            playerStatus.setText("-");
        }
        String tooltip = "Status : " + playerStatus.getText();
        tooltip += "\nVolatile Status :";
        if(player.volatileStatusConditions[0] != null){
            for(VolatileStatusCondition vsc : player.volatileStatusConditions){
                if(vsc != null){
                    tooltip += "\n" + vsc.toString();
                }
            }
        }
        else{
            tooltip += " -";
        }
        tooltipPlayerStatus.setText(tooltip);

        if(enemy.statusCondition != null){
            enemyStatus.setText(enemy.statusCondition.toString());
        }
        else{
            enemyStatus.setText("-");
        }
        tooltip = "Status : " + enemyStatus.getText();
        tooltip += "\nVolatile Status :";
        if(enemy.volatileStatusConditions[0] != null){
            for(VolatileStatusCondition vsc : enemy.volatileStatusConditions){
                if(vsc != null){
                    tooltip += "\n" + vsc.toString();
                }
            }
        }
        else{
            tooltip += " -";
        }
        tooltipEnemyStatus.setText(tooltip);

    }

    private double elaborateAlterations(int alteration){
        double answer = 2;
        if(alteration >= 0){
            answer = 1 + (alteration/answer);
        }
        else{
            alteration *= -1;
            answer /= (alteration + answer);
        }
        return answer;
    }

    public boolean isSpriteChanged() {
        return spriteChanged;
    }

    public void setSpriteChanged(boolean spriteChanged) {
        this.spriteChanged = spriteChanged;
    }   

    public void drawMessages(){
        if(battleLogManager.getMaxMessage() < 0){
            this.setLastBattleLog("Battle starts!");
            this.setLastBattleLog("Turn 1");
        }
        this.listView.setItems(battleLog);
    }

    public void setLastBattleLog(String log) {
        this.battleLog.add(log);
        this.listView.scrollTo(battleLogManager.getMaxMessage());
    }


    private void setWeatherAndTerrain() {
        if(MainApp.getBattleArena().weather != null){
            this.weather.setText(MainApp.getBattleArena().weather.toString());
        }
        else{
            this.weather.setText("(none)");
        }

        if(MainApp.getBattleArena().terrain != null){
            this.terrain.setText(MainApp.getBattleArena().terrain.toString());
        }
        else{
            this.terrain.setText("(none)");
        }

    }

    private void setTeams() {
        ImageView[] playerTeam = new ImageView[]{playerBall1, playerBall2, playerBall3, playerBall4, playerBall5, playerBall6};
        ImageView[] enemyTeam = new ImageView[]{enemyBall1, enemyBall2, enemyBall3, enemyBall4, enemyBall5, enemyBall6};
        int i = 0;
        for(;i < MainApp.getBattleArena().getEnemy().getNumOfPokemon(); i++){
            if(!MainApp.getBattleArena().getEnemy().getPokemon()[i].isFainted()){
                enemyTeam[i].setImage(new Image (getClass().getResourceAsStream("resources/PokeBall.png")));
            }
            else{
                enemyTeam[i].setImage(new Image (getClass().getResourceAsStream("resources/PokeBallFainted.png")));
            }

            if(!MainApp.getBattleArena().getPlayer().getPokemon()[i].isFainted()){
                playerTeam[i].setImage(new Image (getClass().getResourceAsStream("resources/PokeBall.png")));
            }
            else{
                playerTeam[i].setImage(new Image (getClass().getResourceAsStream("resources/PokeBallFainted.png")));
            }
        }  
        //if Enemy.numOfPokemon < MAX_POKEMON (6)
        for(;i < Team.MAX_POKEMON; i++){
            enemyTeam[i].setImage(null);
            if(!MainApp.getBattleArena().getPlayer().getPokemon()[i].isFainted()){
                playerTeam[i].setImage(new Image (getClass().getResourceAsStream("resources/PokeBall.png")));
            }
            else{
                playerTeam[i].setImage(new Image (getClass().getResourceAsStream("resources/PokeBallFainted.png")));
            }
        }
    }
    
    private void checkForStruggle(Pokemon player){
        if(!player.hasStillAMoveWithSomePP()){
            MainApp.getBattleArena().playerMove = new Struggle();
        }
    }

    private void refreshScreen(){
        this.backTeam = MainApp.getBattleArena().getPlayer().getAllies(MainApp.getBattleArena().activePlayer);
        if(spriteChanged){
            this.setActiveSprites();       
            this.setSwitchButton();
            this.setSpriteChanged(false);
        }
        this.setMoveButtons();
        this.setTeams();
        this.setWeatherAndTerrain();
        this.setStatistics();
        this.drawMessages();
    }

}
