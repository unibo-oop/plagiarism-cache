package pokemon;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;
import abilities.Ability;
import abilities.statisticsalterationondemand.*;
import battle_arena.BattleArena;
import main.MainApp;
import main.view.BattleMenuController;
import managers.BattleLogManager;
import managers.GameManager;
import moves.Move;
import moves.damagingmove.physical.Tackle;
import moves.damagingmove.special.HiddenPower;
import natures.Nature;
import status_condition.StatusCondition;
import status_condition.volatile_status.VolatileStatusCondition;
import types.Type;

/**
 * 
 * @author Daniele
 *
 */

public class Pokemon implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final int N_TYPE = 2;
    private static final int N_MOVE = 4;
    private static final int MAX_VOLATILE = 4;
    private static final int MAXEV = 510;
    private static final int MAXEVPERSTAT = 252;

    public static final int MAX_ALTERATION = +6;
    public static final int MIN_ALTERATION = -6;

    //statistiche base specie pokemon
    public final int baseHP;                                        
    public final int baseAtk;                                      
    public final int baseDef;  
    public final int baseSpe;  
    public final int baseSpA;                                
    public final int baseSpD;  

    //valori individuali (specifico) pokemon:
    private int ivHP;
    private int ivAtk;
    private int ivDef;
    private int ivSpe;
    private int ivSpA;
    private int ivSpD;
    //punti allenamento (effort values) dello specifico pokemon, la somma di questi dovrà essere <= 510 
    private int evHP;
    private int evAtk;
    private int evDef;
    private int evSpe;
    private int evSpA;
    private int evSpD;

    //variabili relative alle proprietà del pokemon:
    private final int level;

    private int hp;								//vita attuale pokemon
    private int maxHP;							//vita totale del pokemon
    private int atk;							//punti attacco pokemon
    private int def;							//punti difesa pokemon
    private int spA;							//punti attacco speciale
    private int spD;							//punti difesa speciale
    private int spe;							//punti velocità
    private double accuracy;					//punti precisione attacco
    private double elusion;		    			//punti elusione attacco avversario

    private Type[] type = new Type[N_TYPE];		//tipo del pokemon
    private Ability ability;					//ablilità del pokemon
    private double weight;                      //peso della specie pokemon
    private double frontMiniSizeScale;           //utile per ridimensionare le immagini non sul campo di battaglia
    private Gender gender;                      //sesso del pokemon
    private Nature nature;						//natura del pokemon
    private Set<Move> learnableMoves;           //tutte le mosse che ogni specifico pokemon può imparare
    private Move[] moves = new Move[N_MOVE];    //le mosse che effettivamente conosce (max 4)
    public StatusCondition statusCondition;
    public VolatileStatusCondition[] volatileStatusConditions;

    //valori di alterazione in battaglia
    public int alterationAtk;
    public int alterationDef;
    public int alterationSpe;
    public int alterationSpA;
    public int alterationSpD;
    public int alterationAccuracy;               
    public int alterationElusion;

    //modificatori aggiuntivi per abilità, condizioni di stato ecc...avranno valore iniziale 1
    private double otherModifierFactorAtk;
    private double otherModifierFactorDef;
    private double otherModifierFactorSpe;
    private double otherModifierFactorSpA;
    private double otherModifierFactorSpD;
    private double otherModifierFactorAccuracy;
    private double otherModifierFactorElusion;

    //damage calculation factors
    public double effectiveness;   
    public double stab; 
    public double crit; 
    public double roll;
    public double weatherMod;

    public int turnCount;
    public Move lastMoveUsed;                   //will contain a reference to the last move used by this Pokemon
    public boolean canAttack;
    public boolean canSwitch;
    public boolean isAttacking;
    public boolean isProtecting;
    public boolean isVisibile; 					//establishes if the pokemon's image will be displayed

    public Pokemon(int level, int baseHP, int baseAtk, int baseDef, int baseSpe, int baseSpA, int baseSpD,
                   Type[] type, Ability ability, double weight, double frontMiniSizeScale, Gender gender, Set<Move> learnableMoves){
        //init proprietà specie pokemon
        this.level   = level;
        this.baseHP  = baseHP;
        this.baseAtk = baseAtk;
        this.baseDef = baseDef;
        this.baseSpe = baseSpe;
        this.baseSpA = baseSpA;
        this.baseSpD = baseSpD;	

        //init iv pokemon (random)
        this.setIV();

        //init proprietà pokemon
        this.accuracy = 1;                //all'inizio ogni pokemon avrà il 100% di precisione...
        this.elusion = 1;          	  //...e di elusione

        this.type = Arrays.copyOf(type, type.length);;
        this.ability = ability;
        this.weight = weight;
        this.frontMiniSizeScale = frontMiniSizeScale;
        this.gender = gender;
        this.setNature();              //natura a caso
        this.learnableMoves = learnableMoves;
        this.learnableMoves.add(new HiddenPower(Type.getRandomType()));         //la possono avere tutti i pokemon!
        this.moves   = new Move[N_MOVE];
        this.setRandomMoves();         //sceglie 4 mosse a caso fra quell possibili
        this.calculateEVS();
        this.hp = this.getMaxHp();     //i ps partono da quelli massimi!
        this.statusCondition = null;
        this.volatileStatusConditions = new VolatileStatusCondition[MAX_VOLATILE];
        this.resetAlterations();       //all'inizio le alterazioni sono tutte a 0!
        this.resetAllOtherModifierFactors();

        this.resetBattleFactors();
        this.resetBattleIndicators();
    }

    //getter proprietà del Pokemon

    public int getLevel(){
        return this.level;
    }

    //riferimento al metodo di calcolo a: https://wiki.pokemoncentral.it/Statistiche
    public int getMaxHp(){
        this.maxHP = (((this.ivHP+(2*this.baseHP)+(this.evHP/4))*this.level)/100) + 10 + this.level;
        return this.maxHP;
    }
    
    protected void setMaxHp(int value){
        this.hp = this.maxHP = value;
    }

    public int getHp(){
        return this.hp;
    }

    public void takeDamage(double damage, boolean recoil){
        double targetHP = this.hp;
        this.hp -= damage;
        if(this.hp < 0){
            this.hp = 0;
        }
        else if(this.hp > this.maxHP){     //non può recuperare più del massimo
            this.hp = this.maxHP;
        }

        double percentageDamage = 0;
        if(damage > 0){                     //un vero danno
            //non sarà fatto un danno maggiore di quello possibiel nel caso
            percentageDamage = Math.min(((double)targetHP)/this.maxHP, ((double)(targetHP-this.hp))/this.maxHP);                  
        }
        else if (damage < 0){              //allora è una cura
            //(percentageDamage sarà negativo in questo caso!)
            percentageDamage = (targetHP - this.hp)/this.maxHP;      
        }
        if(percentageDamage != 0){
            BattleMenuController.battleLogManager.setDamageDone(this, percentageDamage, recoil);
        }

        if(this.isFainted()){
            BattleMenuController.battleLogManager.setFaintedMessage(this);
        }
    }

    public int getAtk(){
        this.atk = (int)((((((this.ivAtk+(2*this.baseAtk))+(this.evAtk/4))*this.level)/100)+5)*this.nature.getNAtkPercentage());
        double atk = this.atk;
        atk *= this.elaborateAlterations(this.alterationAtk);
        atk *= this.otherModifierFactorAtk;                        //all'inizio è come moltiplicare per 1, ma può appunto cambiare!
        return (int) atk;
    }

    public int getDef(){
        this.def = (int) ((((((this.ivDef+(2*this.baseDef))+(this.evDef/4))*this.level)/100)+5)*this.nature.getNDefPercentage());
        double def = this.def;
        def *= this.elaborateAlterations(this.alterationDef);
        def *= this.otherModifierFactorDef;                   
        return (int) def;
    }

    public int getSpA(){
        this.spA = (int) ((((((this.ivSpA+(2*this.baseSpA))+(this.evSpA/4))*this.level)/100)+5)*this.nature.getNSpAPercentage());
        double spA = this.spA;
        spA *= this.elaborateAlterations(this.alterationSpA);
        spA *= this.otherModifierFactorSpA;       
        return (int) spA;
    }

    public int getSpD(){
        this.spD = (int) ((((((this.ivSpD+(2*this.baseSpD))+(this.evSpD/4))*this.level)/100)+5)*this.nature.getNSpDPercentage());
        double spD = this.spD;
        spD *= this.elaborateAlterations(this.alterationSpD);
        spD *= this.otherModifierFactorSpD; 
        return (int) spD;
    }

    public int getSpe(){
        this.spe = (int) ((((((this.ivSpe+(2*this.baseSpe))+(this.evSpe/4))*this.level)/100)+5)*this.nature.getNSpePercentage());
        double spe = this.spe;
        spe *= this.elaborateAlterations(this.alterationSpe);
        spe *= this.otherModifierFactorSpe; 
        return (int) spe;
    }

    public double getAccuracy(){
        double accuracy = this.accuracy;
        if(this.alterationAccuracy < 0){
            double change = 3 - this.alterationAccuracy;               
            accuracy = (3d/change)*accuracy;               
        }
        else{
            double change = 1 + (this.alterationAccuracy/3d) ;
            accuracy = accuracy*change;                   
        }
        accuracy *= this.otherModifierFactorAccuracy;
        return accuracy;
    }

    public double getElusion(){	    
        double elusion = this.elusion;
        if(this.alterationElusion < 0){
            double change = 3 - this.alterationElusion ;
            elusion = (3d/change)*elusion;
        }
        else {
            double change = 1 + (this.alterationElusion/3d);
            elusion = elusion*change;
        }
        elusion *= this.otherModifierFactorElusion;	    
        return elusion;
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

    public Type[] getType(){
        return Arrays.copyOf(type, type.length);
    }

    public void changeTypes(Type type1, Type type2){
        this.type[0] = type1;
        this.type[1] = type2;
    }

    public Ability getAbility(){
        return this.ability;
    }

    public void setAbility(Ability newAbility){
        this.ability = newAbility;
    }

    public void changeAbility(Pokemon user, Pokemon target, BattleArena battleArena, Ability newAbility){
        user.ability.exitingAbility(user, target, battleArena);
        user.setAbility(newAbility);
    }

    public double getWeight(){
        return this.weight;
    }

    public void setWeight (double factor){
        this.weight *= factor;
    }

    public double getFrontMiniSizeScale() {
        return frontMiniSizeScale;
    }

    public Gender getGender(){
        return this.gender;
    }

    public boolean isGenderless(){
        return this.gender == Gender.GENDERLESS;
    }

    public Nature getNature(){
        return this.nature;
    }

    //chiamato nella costruzione di Pokemon nad hoc, per mettere la natura desiderata al Pokemon creato
    public void setNature(Nature nature){
        this.nature = nature;
    }

    //usato solo dal costruttore, di default la natura è random
    private void setNature(){
        this.nature = Nature.getRandomNature();
    }

    public Move[] getAllMoves(){
        return Arrays.copyOf(this.moves, this.moves.length);
    }

    public Move getMove(int index){
        return this.moves[index];
    }

    public boolean hasStillAMoveWithSomePP(){
        for(Move move: this.moves){
            if(move.hasSomePP()){
                return true;
            }
        }
        return false;
    }

    public StatusCondition getStatusCondition(){
        return this.statusCondition;
    }

    //getter iv del Pokemon
    public int getIVHP(){
        return this.ivHP;
    }

    public int getIVAtk(){
        return this.ivAtk;
    }

    public int getIVDef(){
        return this.ivDef;
    }

    public int getIVSpe(){
        return this.ivSpe;
    }

    public int getIVSpA(){
        return this.ivSpA;
    }

    public int getIVSpD(){
        return this.ivSpD;
    }

    //chiamato nella costruzione di Pokemon ad hoc, per aggiustare le IV dopo la creazione del Pokemon (per default saranno sempre random le IV, poi...)
    public void setIV(int ivHP, int ivAtk, int ivDef, int ivSpe, int ivSpA, int ivSpD){
        this.ivHP  = ivHP;
        this.ivAtk = ivAtk;
        this.ivDef = ivDef;
        this.ivSpe = ivSpe;
        this.ivSpA = ivSpA;
        this.ivSpD = ivSpD;
    }

    //utilizzato solo dal costruttore, metodo di default per il calcolo delle IV
    private void setIV(){
        this.ivHP  = getRandomIV();
        this.ivAtk = getRandomIV();
        this.ivDef = getRandomIV();
        this.ivSpe = getRandomIV();
        this.ivSpA = getRandomIV();
        this.ivSpD = getRandomIV();
    }

    public static int getRandomIV(){
        Random random = new Random();
        return random.nextInt(32);         //0-31
    }

    //selfDone serve per non impedire al pokemon di cambiarsi, da solo, le statistiche
    public void setAlterationAtk(int change, boolean selfDone){
        if(this.ability.equals(new ClearBody()) || this.ability.equals(new WhiteSmoke()) || this.ability.equals(new HyperCutter())){
            if(!selfDone){
                this.ability.activateAbility(this, this, null);
                return;
            }
        }
        else{
            if(this.ability.equals(new Contrary())){
                change = - change;
            }
            int realChange = 0;

            if(change > 0){
                realChange = Math.min(change,MAX_ALTERATION - this.alterationAtk);      //nel caso si voglia sommare 2 ma si è già a 5 ad es.

                if(realChange > 0){
                    BattleMenuController.battleLogManager.setStatChangeMessage(this, realChange, BattleLogManager.ATTACK);
                }
                else{
                    BattleMenuController.battleLogManager.setMaxStatMessage(this, BattleLogManager.ATTACK);
                }
            }

            else if (change < 0){
                realChange = Math.max(change,MIN_ALTERATION - this.alterationAtk);      
                if(realChange < 0){
                    BattleMenuController.battleLogManager.setStatChangeMessage(this, realChange, BattleLogManager.ATTACK);

                    if(this.ability.equals(new Competitive()) ||this.ability.equals(new Defiant())){
                        this.ability.activateAbility(this, this, null);
                    }
                }
                else{
                    BattleMenuController.battleLogManager.setMinStatMessage(this, BattleLogManager.ATTACK);
                }
            }

            this.alterationAtk += realChange;
        }
    }

    public void setAlterationDef(int change, boolean selfDone){
        if(this.ability.equals(new ClearBody()) || this.ability.equals(new WhiteSmoke()) || this.ability.equals(new BigPecks())){
            if(!selfDone){
                this.ability.activateAbility(this, this, null);
                return;
            }
        }
        else{
            if(this.ability.equals(new Contrary())){
                change = - change;
            }
            int realChange = 0;

            if(change > 0){
                realChange = Math.min(change,MAX_ALTERATION - this.alterationDef);      

                if(realChange > 0){
                    BattleMenuController.battleLogManager.setStatChangeMessage(this, realChange, BattleLogManager.DEFENSE);
                }
                else{
                    BattleMenuController.battleLogManager.setMaxStatMessage(this, BattleLogManager.DEFENSE);
                }

            }
            else if (change < 0){
                realChange = Math.max(change,MIN_ALTERATION - this.alterationDef);      

                if(realChange < 0){
                    BattleMenuController.battleLogManager.setStatChangeMessage(this, realChange, BattleLogManager.DEFENSE);

                    if(this.ability.equals(new Competitive()) ||this.ability.equals(new Defiant())){
                        this.ability.activateAbility(this, this, null);
                    }
                }
                else{
                    BattleMenuController.battleLogManager.setMinStatMessage(this, BattleLogManager.DEFENSE);
                }
            }

            this.alterationDef += realChange;
        }
    }

    public void setAlterationSpe(int change, boolean selfDone){
        if(this.ability.equals(new ClearBody()) || this.ability.equals(new WhiteSmoke())){
            if(!selfDone){
                this.ability.activateAbility(this, this, null);
                return;
            }
        }
        else{
            if(this.ability.equals(new Contrary())){
                change = - change;
            }
            int realChange = 0;

            if(change > 0){
                realChange = Math.min(change,MAX_ALTERATION - this.alterationSpe);      

                if(realChange > 0){
                    if(realChange > 0){
                        BattleMenuController.battleLogManager.setStatChangeMessage(this, realChange, BattleLogManager.SPEED);
                    }
                    else{
                        BattleMenuController.battleLogManager.setMaxStatMessage(this, BattleLogManager.SPEED);
                    }
                }

            }
            else if (change < 0){
                realChange = Math.max(change,MIN_ALTERATION - this.alterationSpe);      

                if(realChange < 0){
                    BattleMenuController.battleLogManager.setStatChangeMessage(this, realChange, BattleLogManager.SPEED);

                    if(this.ability.equals(new Competitive()) ||this.ability.equals(new Defiant())){
                        this.ability.activateAbility(this, this, null);
                    }
                }
                else{
                    BattleMenuController.battleLogManager.setMinStatMessage(this, BattleLogManager.SPEED);
                }
            }

            this.alterationSpe += realChange;
        }
    }

    public void setAlterationSpA(int change, boolean selfDone){
        if(this.ability.equals(new ClearBody()) || this.ability.equals(new WhiteSmoke())){
            if(!selfDone){
                this.ability.activateAbility(this, this, null);
                return;
            }
        }
        else{
            if(this.ability.equals(new Contrary())){
                change = - change;
            }
            int realChange = 0;

            if(change > 0){
                realChange = Math.min(change,MAX_ALTERATION - this.alterationSpA);      

                if(realChange > 0){
                    BattleMenuController.battleLogManager.setStatChangeMessage(this, realChange, BattleLogManager.SPA);
                }
                else{
                    BattleMenuController.battleLogManager.setMaxStatMessage(this, BattleLogManager.SPA);
                }
            }

            else if (change < 0){
                realChange = Math.max(change,MIN_ALTERATION - this.alterationSpA);      

                if(realChange < 0){
                    BattleMenuController.battleLogManager.setStatChangeMessage(this, realChange, BattleLogManager.SPA);

                    if(this.ability.equals(new Competitive()) ||this.ability.equals(new Defiant())){
                        this.ability.activateAbility(this, this, null);
                    }
                }
                else{
                    BattleMenuController.battleLogManager.setMinStatMessage(this, BattleLogManager.SPA);
                }
            }

            this.alterationSpA += realChange;
        }
    }

    public void setAlterationSpD(int change, boolean selfDone){
        if(this.ability.equals(new ClearBody()) || this.ability.equals(new WhiteSmoke())){
            if(!selfDone){
                this.ability.activateAbility(this, this, null);
                return;
            }
        }
        else{
            if(this.ability.equals(new Contrary())){
                change = - change;
            }
            int realChange =  0;

            if(change > 0){
                realChange = Math.min(change,MAX_ALTERATION - this.alterationSpD);     

                if(realChange > 0){
                    BattleMenuController.battleLogManager.setStatChangeMessage(this, realChange, BattleLogManager.SPD);
                }
                else{
                    BattleMenuController.battleLogManager.setMaxStatMessage(this, BattleLogManager.SPD);
                }
            }

            else if (change < 0){
                realChange = Math.max(change,MIN_ALTERATION - this.alterationSpD);     

                if(realChange < 0){
                    BattleMenuController.battleLogManager.setStatChangeMessage(this, realChange, BattleLogManager.SPD);

                    if(this.ability.equals(new Competitive()) ||this.ability.equals(new Defiant())){
                        this.ability.activateAbility(this, this, null);
                    }
                }
                else{
                    BattleMenuController.battleLogManager.setMinStatMessage(this, BattleLogManager.SPD);
                }
            }  

            this.alterationSpD += realChange;
        }
    }

    public void setAlterationAccuracy(int change, boolean selfDone){
        if(this.ability.equals(new ClearBody()) || this.ability.equals(new WhiteSmoke()) || this.ability.equals(new KeenEye())){
            if(!selfDone){
                this.ability.activateAbility(this, this, null);
                return;
            }
        }
        else{
            if(this.ability.equals(new Contrary())){
                change = - change;
            }
            int realChange = 0;

            if(change > 0){
                realChange = Math.min(change,MAX_ALTERATION - this.alterationAccuracy);     

                if(realChange > 0){
                    if(realChange > 0){
                        BattleMenuController.battleLogManager.setStatChangeMessage(this, realChange, BattleLogManager.ACCURACY);
                    }
                    else{
                        BattleMenuController.battleLogManager.setMaxStatMessage(this, BattleLogManager.ACCURACY);
                    }
                }
            }

            else if (change < 0){
                realChange = Math.max(change,MIN_ALTERATION - this.alterationAccuracy);      

                if(realChange < 0){
                    BattleMenuController.battleLogManager.setStatChangeMessage(this, realChange, BattleLogManager.ACCURACY);

                    if(this.ability.equals(new Competitive()) ||this.ability.equals(new Defiant())){
                        this.ability.activateAbility(this, this, null);
                    }
                }
                else{
                    BattleMenuController.battleLogManager.setMinStatMessage(this, BattleLogManager.ACCURACY);
                }
            }  

            this.alterationAccuracy += realChange;
        }
    }

    public void setAlterationElusion(int change, boolean selfDone){
        if(this.ability.equals(new ClearBody()) || this.ability.equals(new WhiteSmoke())){
            if(!selfDone){
                this.ability.activateAbility(this, this, null);
                return;
            }
        }
        else{
            if(this.ability.equals(new Contrary())){
                change = - change;
            }
            int realChange = 0;

            if(change > 0){
                realChange = Math.min(change, MAX_ALTERATION - this.alterationElusion);   

                if(realChange > 0){
                    BattleMenuController.battleLogManager.setStatChangeMessage(this, realChange, BattleLogManager.ELUSION);
                }
                else{
                    BattleMenuController.battleLogManager.setMaxStatMessage(this, BattleLogManager.ELUSION);
                }
            }

            else if (change < 0){
                realChange = Math.max(change,MIN_ALTERATION - this.alterationElusion);      

                if(realChange < 0){
                    BattleMenuController.battleLogManager.setStatChangeMessage(this, realChange, BattleLogManager.ELUSION);

                    if(this.ability.equals(new Competitive()) ||this.ability.equals(new Defiant())){
                        this.ability.activateAbility(this, this, null);
                    }
                }
                else{
                    BattleMenuController.battleLogManager.setMinStatMessage(this, BattleLogManager.ACCURACY);
                }
            }  

            this.alterationElusion += realChange;
        }
    }


    public void resetAlterations(){
        this.alterationAtk = 0;
        this.alterationDef = 0;
        this.alterationSpe = 0;
        this.alterationSpA = 0;
        this.alterationSpD = 0;
        this.alterationAccuracy = 0;
        this.alterationElusion = 0;
    }


    public void setRandomMoves(){
        Random random = new Random();
        Move move = new Tackle();
        for(int index = 0; index < N_MOVE; index++){
            int moveID = random.nextInt(this.learnableMoves.size());
            Iterator<Move> moveIterator = this.learnableMoves.iterator();
            while(moveID >= 0 && moveIterator.hasNext()){
                moveID--;
                move = moveIterator.next();
            }
            this.moves[index] = move;
            moveIterator.remove();                         /*la lista è stata fatta proprio per questo: per evitare che un pokemon
             *abbia più mosse dello stesso tipo! quindi impedisco che ciò accada rimuovendola
             *dalla lista*/ 
        }
    }

    public boolean isFainted(){
        return this.hp == 0;
    }

    public double getOtherModifierFactorAtk() {
        return otherModifierFactorAtk;
    }

    public void setOtherModifierFactorAtk(double otherModifierFactorAtk) {
        this.otherModifierFactorAtk = otherModifierFactorAtk;
    }

    public double getOtherModifierFactorDef() {
        return otherModifierFactorDef;
    }

    public void setOtherModifierFactorDef(double otherModifierFactorDef) {
        this.otherModifierFactorDef = otherModifierFactorDef;
    }

    public double getOtherModifierFactorSpe() {
        return otherModifierFactorSpe;
    }

    public void setOtherModifierFactorSpe(double otherModifierFactorSpe) {
        this.otherModifierFactorSpe = otherModifierFactorSpe;
    }

    public double getOtherModifierFactorSpA() {
        return otherModifierFactorSpA;
    }

    public void setOtherModifierFactorSpA(double otherModifierFactorSpA) {
        this.otherModifierFactorSpA = otherModifierFactorSpA;
    }

    public double getOtherModifierFactorSpD() {
        return otherModifierFactorSpD;
    }

    public void setOtherModifierFactorSpD(double otherModifierFactorSpD) {
        this.otherModifierFactorSpD = otherModifierFactorSpD;
    }

    public double getOtherModifierFactorAccuracy() {
        return otherModifierFactorAccuracy;
    }

    public void setOtherModifierFactorAccuracy(double otherModifierFactorAccuracy) {
        this.otherModifierFactorAccuracy = otherModifierFactorAccuracy;
    }

    public double getOtherModifierFactorElusion() {
        return otherModifierFactorElusion;
    }

    public void setOtherModifierFactorElusion(double otherModifierFactorElusion) {
        this.otherModifierFactorElusion = otherModifierFactorElusion;
    }

    private void calculateEVS(){
        this.resetAllEVS();
        this.assignEVRandom(MAXEV);
    }

    public int getEvHP(){
        return this.evHP;
    }

    public int getEvAtk(){
        return this.evAtk;
    }

    public int getEvDef(){
        return this.evDef;
    }

    public int getEvSpA(){
        return this.evSpA;
    }

    public int getEvSpD(){
        return this.evSpD;
    }

    public int getEvSpe(){
        return this.evSpe;
    }
    
    public void setEV(int evHP, int evAtk, int evDef, int evSpe, int evSpA, int evSpD){
        this.evHP = evHP;
        this.evAtk = evAtk;
        this.evDef = evDef;
        this.evSpe = evSpe;
        this.evSpA = evSpA;
        this.evSpD = evSpD;
    }

    private void assignEVRandom(int maxEV){
        int choice;
        Random random = new Random();
        while(maxEV > 0){
            choice = random.nextInt(6);             //0-5
            switch (choice) {
            case 0:
                maxEV -= this.assignEVHP(maxEV);
                break;
            case 1:
                maxEV -= this.assignEVAtk(maxEV);
                break;
            case 2:
                maxEV -= this.assignEVDef(maxEV);
                break;
            case 3:
                maxEV -= this.assignEVSpe(maxEV);
                break;
            case 4:
                maxEV -= this.assignEVSpA(maxEV);
                break;
            case 5:
                maxEV -= this.assignEVSpD(maxEV);
                break;
            }
        }       
    }

    private int assignEVHP(int maxEV){
        if(this.evHP < MAXEVPERSTAT){
            int thisEV = 0;
            Random random = new Random();
            //assegno solo se il valore che assegno non eccede nè il max valore per stat nè ciò che è rimasto da quelle massime
            int maxStatEV = Math.min(maxEV, MAXEVPERSTAT - this.evHP);
            thisEV = random.nextInt(maxStatEV) + 1;
            this.evHP += thisEV;
            return thisEV;
        }  
        return 0;
    }

    private int assignEVAtk(int maxEV){
        if(this.evAtk < MAXEVPERSTAT){
            int thisEV = 0;
            Random random = new Random();
            //assegno solo se il valore che assegno non eccede nè il max valore per stat nè ciò che è rimasto da quelle massime
            int maxStatEV = Math.min(maxEV, MAXEVPERSTAT - this.evAtk);
            thisEV = random.nextInt(maxStatEV) + 1;
            this.evAtk += thisEV;
            return thisEV;
        }  
        return 0;
    }

    private int assignEVDef(int maxEV){
        if(this.evDef < MAXEVPERSTAT){
            int thisEV = 0;
            Random random = new Random();
            //assegno solo se il valore che assegno non eccede nè il max valore per stat nè ciò che è rimasto da quelle massime
            int maxStatEV = Math.min(maxEV, MAXEVPERSTAT - this.evDef);
            thisEV = random.nextInt(maxStatEV) + 1;
            this.evDef += thisEV;
            return thisEV;
        }  
        return 0;
    }

    private int assignEVSpe(int maxEV){
        if(this.evSpe < MAXEVPERSTAT){
            int thisEV = 0;
            Random random = new Random();
            //assegno solo se il valore che assegno non eccede nè il max valore per stat nè ciò che è rimasto da quelle massime
            int maxStatEV = Math.min(maxEV, MAXEVPERSTAT - this.evSpe);
            thisEV = random.nextInt(maxStatEV) + 1;
            this.evSpe += thisEV;
            return thisEV;
        }  
        return 0;
    }

    private int assignEVSpA(int maxEV){
        if(this.evSpA < MAXEVPERSTAT){
            int thisEV = 0;
            Random random = new Random();
            //assegno solo se il valore che assegno non eccede nè il max valore per stat nè ciò che è rimasto da quelle massime
            int maxStatEV = Math.min(maxEV, MAXEVPERSTAT - this.evSpA);
            thisEV = random.nextInt(maxStatEV) + 1;
            this.evSpA += thisEV;
            return thisEV;
        }  
        return 0;
    }

    private int assignEVSpD(int maxEV){
        if(this.evSpD < MAXEVPERSTAT){
            int thisEV = 0;
            Random random = new Random();
            //assegno solo se il valore che assegno non eccede nè il max valore per stat nè ciò che è rimasto da quelle massime
            int maxStatEV = Math.min(maxEV, MAXEVPERSTAT - this.evSpD);
            thisEV = random.nextInt(maxStatEV) + 1;
            this.evSpD += thisEV;
            return thisEV;
        }  
        return 0;
    }

    private void resetAllEVS(){
        this.evHP = 0;
        this.evAtk = 0;
        this.evDef = 0;
        this.evSpe = 0;
        this.evSpA = 0;
        this.evSpD = 0;
    }

    public void resetAllOtherModifierFactors(){
        this.otherModifierFactorAtk = 1;
        this.otherModifierFactorDef = 1;
        this.otherModifierFactorSpe = 1;
        this.otherModifierFactorSpA = 1;
        this.otherModifierFactorSpD = 1;
        this.otherModifierFactorAccuracy = 1;
        this.otherModifierFactorElusion = 1;
    }

    public void resetBattleFactors(){
        this.effectiveness = 1;   
        this.stab = 1; 
        this.crit = 1; 
        this.roll= 1; 
        this.weatherMod = 1;
        this.isVisibile = false;
    }

    public void resetBattleIndicators(){
        this.turnCount = 0;
        this.canAttack = true;
        this.canSwitch = true;
        this.isAttacking = false;
        this.isProtecting = false;
    }

    public boolean isFirstTurn(){
        return this.turnCount == 0;
    }

    public void setMove(int index, Move move){
        this.moves[index] = move;
    }

    public void cure(){
        this.hp = this.maxHP;
        this.statusCondition = null;
        VolatileStatusCondition.deleteAllVolatiles(this.volatileStatusConditions);
        for(Move move : this.moves){
            int lack = move.getActualPP() - move.getMaxPP();                                    //negativo (o nullo), così i PP verranno aggiunti!
            move.decrementActualPP(lack);
        }
        this.resetAlterations();
        this.resetAllOtherModifierFactors();
        this.resetBattleFactors();
        this.resetBattleIndicators();
    }

    public String pokemonFrontImage(){
        return (GameManager.RESOURCES + GameManager.POKEMONFOLDER +this.toString() + 
                GameManager.IMAGEFRONT + GameManager.DOT + GameManager.ANIMATEDIMAGEEXTENSION);
    }

    public String pokemonBackImage(){
        return (GameManager.RESOURCES + GameManager.POKEMONFOLDER + this.toString() + 
                GameManager.IMAGEBACK + GameManager.DOT + GameManager.ANIMATEDIMAGEEXTENSION);
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

}
