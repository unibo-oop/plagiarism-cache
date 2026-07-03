package managers;

import team.enemies.Enemy;
import team.enemies.enemyais.EasyAIBattle;
import team.enemies.enemyais.MediumAIBattle;

public class EnemyManager {
    
    private static final int FIRSTENEMYPOINTS = 1000;
    
    private static final int FROMSECONDSTARTENEMYPOINTS = 100;
    
    private static final int FIRSTTEAMSIZE = 4;
    
    private static final int SECONDENEMYTEAMSIZE = 5;
    
    private static final int FINALENEMYTEAMSIZE = 6;
    
    private static final Enemy FIRSTENEMY = new Enemy(PokemonListManager.LEVEL1LIST, new EasyAIBattle(), FIRSTTEAMSIZE, 
                                                      "Jacques","Petit", "Chef", Enemy.getEnemyScenarioCompletePath("CalmTree-Lined"), 
                                                      1, FIRSTENEMYPOINTS);

    private static final Enemy SECONDENEMY = new Enemy(PokemonListManager.LEVEL2LIST, new EasyAIBattle(), SECONDENEMYTEAMSIZE,
                                                       "Orathio","Finnish", "Rich Boy", Enemy.getEnemyScenarioCompletePath("GreenGrass"), 
                                                       2, FROMSECONDSTARTENEMYPOINTS);
    
    private static final Enemy THIRDENEMY = new Enemy(PokemonListManager.LEVEL3LIST, new MediumAIBattle(SECONDENEMYTEAMSIZE), SECONDENEMYTEAMSIZE,
                                                      "Gerard","Ineel", "Illusionist", Enemy.getEnemyScenarioCompletePath("AuraIntenseBattle"), 
                                                       3, FROMSECONDSTARTENEMYPOINTS*2);
    
    private static final Enemy FOURTHENEMY = new Enemy(PokemonListManager.LEVEL4LIST, new EasyAIBattle(), SECONDENEMYTEAMSIZE,
                                                       "Caroline","Ivy", "Lady", Enemy.getEnemyScenarioCompletePath("SoftDawn"), 
                                                       4, FROMSECONDSTARTENEMYPOINTS*3);
    
    private static final Enemy FIFTHENEMY = new Enemy(PokemonListManager.LEVEL5LIST, new EasyAIBattle(), FINALENEMYTEAMSIZE,
                                                      "Nick","Zano", "Handyman", Enemy.getEnemyScenarioCompletePath("DarkCave"), 
                                                      5, FROMSECONDSTARTENEMYPOINTS*4);
    
    private static final Enemy SIXTHENEMY = new Enemy(PokemonListManager.LEVEL6LIST, new MediumAIBattle(FINALENEMYTEAMSIZE), FINALENEMYTEAMSIZE,
                                                      "Jerome","Voohrees", "Bad Guy", Enemy.getEnemyScenarioCompletePath("SmokyIntenseBattle"), 
                                                      6, FROMSECONDSTARTENEMYPOINTS*5);
    
    private static final Enemy SEVENTHENEMY = new Enemy(PokemonListManager.LEVEL7LIST, new EasyAIBattle(), FINALENEMYTEAMSIZE,
                                                        "Janette","Jones", "Swimmer", Enemy.getEnemyScenarioCompletePath("Beach"), 
                                                        7, FROMSECONDSTARTENEMYPOINTS*6);
    
    private static final Enemy EIGTHENEMY = new Enemy(PokemonListManager.LEVEL8LIST, new EasyAIBattle(), FINALENEMYTEAMSIZE,
                                                      "Amber","Shadow", "Traveller", Enemy.getEnemyScenarioCompletePath("Mountain"), 
                                                      8, FROMSECONDSTARTENEMYPOINTS*7);
    
    private static final Enemy NINTHENEMY = new Enemy(PokemonListManager.LEVEL9LIST, new EasyAIBattle(), FINALENEMYTEAMSIZE,
                                                      "Rebecca","Stone", "Dormer of Dragons", Enemy.getEnemyScenarioCompletePath("Temple"), 
                                                      9, FROMSECONDSTARTENEMYPOINTS*8);
    
    private static final Enemy TENTHENEMY = new Enemy(PokemonListManager.LEVEL10LIST, new MediumAIBattle(FINALENEMYTEAMSIZE), FINALENEMYTEAMSIZE,
                                                      "Tom","Hardler", "Worker", Enemy.getEnemyScenarioCompletePath("SnowHill"), 
                                                      10, FROMSECONDSTARTENEMYPOINTS*9);
    
    private static final Enemy ELEVENTHENEMY = new Enemy(PokemonListManager.LEVEL11LIST, new EasyAIBattle(), FINALENEMYTEAMSIZE,
                                                         "Alex","Black", "Rebel", Enemy.getEnemyScenarioCompletePath("VirtualBattle"), 
                                                         11, FROMSECONDSTARTENEMYPOINTS*10);
    
    private static final Enemy TWELVETHENEMY = new Enemy(PokemonListManager.LEVEL12LIST, new EasyAIBattle(), FINALENEMYTEAMSIZE,
                                                         "Hector","Hunter", "Guardian", Enemy.getEnemyScenarioCompletePath("HailedIntenseBattle"), 
                                                         12, FROMSECONDSTARTENEMYPOINTS*11);
    
    private static final Enemy THIRTEENTHENEMY = new Enemy(PokemonListManager.LEVEL13LIST, new MediumAIBattle(FINALENEMYTEAMSIZE), 
                                                          FINALENEMYTEAMSIZE,"Koogah","Knight", "Ninja", 
                                                          Enemy.getEnemyScenarioCompletePath("Castle"), 13, FROMSECONDSTARTENEMYPOINTS*12);
    
    private static final Enemy FOURTEENTHENEMY = new Enemy(PokemonListManager.LEVEL14LIST, new EasyAIBattle(), FINALENEMYTEAMSIZE,
                                                           "Karina","Spark", "Scout", Enemy.getEnemyScenarioCompletePath("DarkCity"), 
                                                           14, FROMSECONDSTARTENEMYPOINTS*13);
    
    private static final Enemy LASTENEMY = new Enemy(PokemonListManager.LEVEL15LIST, new EasyAIBattle(), FINALENEMYTEAMSIZE,
                                                     "Drance","Lake", "Champion", Enemy.getEnemyScenarioCompletePath("RedCarpet"), 
                                                     15, FROMSECONDSTARTENEMYPOINTS*20);
    
    public static final Enemy[] ALLENEMIES = {FIRSTENEMY, SECONDENEMY, THIRDENEMY, FOURTHENEMY, FIFTHENEMY, SIXTHENEMY, SEVENTHENEMY,
                                              EIGTHENEMY, NINTHENEMY, TENTHENEMY, ELEVENTHENEMY, TWELVETHENEMY, THIRTEENTHENEMY, 
                                              FOURTEENTHENEMY, LASTENEMY};
    
    private EnemyManager() {
        // TODO Auto-generated constructor stub
    }

}
