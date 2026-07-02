package junit;
import controllers.texture.GetTexture;
import model.ID;
import model.enemy.AdvanceEnemy;
import model.enemy.AvarageEnemy;
import model.enemy.BaseEnemy;
import model.enemy.DragonEnemy;
import model.enemy.SupportEnemy;
import model.enemy.TowerEnemy;
import model.handler.Handler;
import model.player.Player;
import other.Pair;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TestHandler {

    // Classe con all'interno test di JUnit per verificare il corretto funzionamento
    // dei meccanismi dell'Handler

    private final Handler hand = new Handler();
    private final GetTexture texture = new GetTexture();

    // Primo test: Creazione dell'oggetto Handler, inserimento di due entità Player
    // e BaseEnemy, successiva verifica del corretto inserimento e verifica delle
    // dimensioni della lista

    @Test
    public void testOperationEntities() {

        // Creazione Player con valori di default: Posizione iniziale (0,0), velocità
        // iniziale nulla (0,0) ed immagini necessarie per il caricamento delle texture
        // ricavate dalla classe GetTexture

        final Player player = new Player(ID.PLAYER, hand, 0, 0, 0, 0, texture.getPlayerListTexture());

        // Creazione BaseEnemy, AvarageEnemy, AdvanceEnemy, DragonEnemy, TowerEnemy,
        // SupportEnemy con valori di default: Posizione iniziale (0,0), velocità
        // iniziale nulla (0,0) ed immagini necessarie per il caricamento delle texture
        // ricavate dalla classe GetTexture
        // Per quanto riguarda il Ray anch'esso sarà inserito con valori di default:
        // Posizione iniziale (0,0), velocità iniziale nulla (0,0) ed immagini
        // necessarie per il caricamento delle texture ricavate dalla classe GetTexture
        final BaseEnemy baseEnemy = new BaseEnemy(ID.ENEMY, hand,
                0, 0, 0, 0, texture.getBaseEnemyListTexture(), texture.getBaseEnemyRayListTexture());

        final AvarageEnemy avarageEnemy = new AvarageEnemy(ID.ENEMY, hand, 0, 0, 0, 0,
                texture.getAvarageEnemyListTexture(), texture.getComplexRayImage());

        final AdvanceEnemy advanceEnemy = new AdvanceEnemy(ID.ENEMY, hand, 0, 0, 0, 0,
                texture.getAdvanceEnemyListTexture(), texture.getComplexRayImage());

        final DragonEnemy dragonEnemy = new DragonEnemy(ID.ENEMY, 0, 0, 0, 0,
                texture.getDragonEnemyListTexture(), texture.getDragonEnemyRayListTexture());

        final TowerEnemy towerEnemy = new TowerEnemy(ID.ENEMY, 0, 0, 0, 0, texture.getTowerEnemyImage(), texture.getTowerEnemyRayImage());

        final SupportEnemy supportEnemy = new SupportEnemy(ID.ENEMY, hand, 0, 0, 0, 0,
                texture.getSupportEnemyListTexture());

        // Esempio di verifica di posizione del Player
        assertEquals(new Pair<Integer, Integer>(0, 0), player.getCoord());

        // Esempio di verifica della velocità del Player
        assertEquals(new Pair<Double, Double>(0.0, 0.0), player.getVelocity());

        // Inserimento degli elementi creati nella lista generica dell'handler
        this.hand.addGameObject(player);
        this.hand.addGameObject(baseEnemy);
        this.hand.addGameObject(avarageEnemy);
        this.hand.addGameObject(advanceEnemy);
        this.hand.addGameObject(dragonEnemy);
        this.hand.addGameObject(towerEnemy);
        this.hand.addGameObject(supportEnemy);

        // Verifica effettiva dimensione della lista degli elementi di gioco
        // nell'handler
        assertEquals(hand.getGameObjectList().size(), 7);

        // Verifica dell'effettiva coincidenza del player creato col player inserito in
        // lista
        assertEquals(hand.getPlayer(), player);

        // Verifica dimensione lista dopo eliminazione elemento
        hand.removeGameObject(supportEnemy);
        assertEquals(hand.getGameObjectList().size(), 6);

    }

}
