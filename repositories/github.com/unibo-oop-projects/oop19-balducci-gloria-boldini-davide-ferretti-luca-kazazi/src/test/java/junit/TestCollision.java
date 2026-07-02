package junit;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import controllers.collision.collisionsEnemy.CollisionsEnemy;
import controllers.collision.collisionsPlayer.CollisionsPlayer;
import controllers.texture.GetTexture;
import model.ID;
import model.Wall;
import model.enemy.BaseEnemy;
import model.enemy.DragonEnemy;
import model.handler.Handler;
import model.player.Player;
import model.powerup.KnifePowerUP;

public class TestCollision {

    // Classe con all'interno test di JUnit per verificare il corretto funzionamento
    // delle collisioni

    private final Handler handler = new Handler();
    private final GetTexture texture = new GetTexture();

    // Metodo per verificare le collisioni ed i successivi comportamenti tra Enemy e
    // Wall
    @Test
    public void testEnemyCollision() {
        // Verifica effettuata con BaseEnemy, ma valida per tutte le tipologie di Enemy
        // presenti nel gioco (tranne TowerEnemy e DragonEnemy)
        // Creazione entità BaseEnemy con i classici valori di default:
        // Posizione: (0,0)
        // Velocità iniziale: 0,0
        final BaseEnemy baseEnemy = new BaseEnemy(ID.ENEMY, handler,
                 0, 0, 0, 0, texture.getBaseEnemyListTexture(), texture.getBaseEnemyRayListTexture());

        // Creazione forzata dell'entità Wall con le medesime coordinate inserite in
        // BaseEnemy, in tal modo si dovrebbe avere una situazione similare a quella
        // tipica di una situazione di gioco
        final Wall wall = new Wall(ID.WALL, 0, 0, texture.getWall(1));

        // Creazione entità per la verifica della collisione
        final CollisionsEnemy collisionsEnemy = new CollisionsEnemy();

        // Verifica preliminare della corretta direzione di default del BaseEnemy
        assertTrue(baseEnemy.getMovement().isDown());

        // Richiamo del metodo per verificare la collisione tra una generica entità
        // Enemy ed un Wall
        collisionsEnemy.collisionsInGame(baseEnemy, wall);

        // NOTA: Prima di verificare ciò che accade dopo una collisione, è bene
        // specificare che il classico pattern di movimento di un BaseEnemy è
        // il seguente: Basso-Destra-Alto-Sinistra. Di default alla creazione
        // dell'entità viene settata come prima direzione la direzione "Basso" ed alla
        // prima collisione la direzione diverrà "Destra"
        // Andremo a verificare il corretto funzionamento di questo pattern

        
        assertFalse(baseEnemy.getMovement().isDown());

        // Verifica del primo cambiamento del pattern
        assertTrue(baseEnemy.getMovement().isRight());

        /*-------------------------------------------------------------------------------------------------------*/

        // Richiamo al metodo per verificare la seconda collisione
        collisionsEnemy.collisionsInGame(baseEnemy, wall);

        assertFalse(baseEnemy.getMovement().isRight());

        // Verifica del secondo cambiamento del pattern
        assertTrue(baseEnemy.getMovement().isUp());

        /*------------------------------------------------------------------------------------------------------*/

        // Richiamo al metodo per verificare la terza collisione
        collisionsEnemy.collisionsInGame(baseEnemy, wall);

        assertFalse(baseEnemy.getMovement().isUp());

        // Verifica del terzo cambiamento del pattern
        assertTrue(baseEnemy.getMovement().isLeft());

        /*------------------------------------------------------------------------------------------------------*/

        // Richiamo al metodo per verificare la quarta collisione
        collisionsEnemy.collisionsInGame(baseEnemy, wall);

        assertFalse(baseEnemy.getMovement().isLeft());

        // Verifica del quarto cambiamento del pattern
        assertTrue(baseEnemy.getMovement().isDown());
    }

    // Metodo per verificare i comportamenti di Player ed Enemy durante le
    // collisioni tra di essi
    @Test
    public void testPlayerCollision() {
        // Verifica effettuata con BaseEnemy, ma valida per tutte le tipologie di Enemy
        // presenti nel gioco (tranne TowerEnemy e DragonEnemy)
        // Creazione entità BaseEnemy con i classici valori di default:
        // Posizione: (0,0)
        // Velocità iniziale: 0,0
        final BaseEnemy baseEnemy = new BaseEnemy(ID.ENEMY, handler,
                 0, 0, 0, 0, texture.getBaseEnemyListTexture(), texture.getBaseEnemyRayListTexture());

        // Creazione "forzata" di un'entità Player avente le medesime coordinate
        // dell'entità BaseEnemy appena creata, in tal modo da forzare la collisione tra
        // di essi e permettere la conseguente verifica
        final Player player = new Player(ID.PLAYER, handler, 0, 0, 0, 0, texture.getPlayerListTexture());

        // Creazione dell'entità necessaria per tale verifica
        final CollisionsPlayer collisionsPlayer = new CollisionsPlayer();

        // CASO 1: Player SENZA KnifePowerUP attivo (o altri PowerUP che settano il
        // campo "visible" del Player a false)
        // Questo rappresenta il caso più frequente e classico di collisione tra Player
        // ed Enemy. VI sarà una collisione ed il Player andrà a perdere una vita
        collisionsPlayer.collisionsInGame(player, baseEnemy);

        // Verifica della perdita di una vita da parte del Player
        assertEquals(player.getHealth(), 2);

        // Verifica della presenza dell'Enemy ancora nella mappa
        assertTrue(baseEnemy.isAlive());
        assertTrue(baseEnemy.isVisible());

        // CASO 2: Player CON KnifePowerUP attivo
        // In questo caso, ossia col Player che possiede il KnifePowerUP, in caso di
        // collisione tra il Player e l'Enemy (tranne TowerEnemy e DragonEnemy) il
        // nemico sarà "ucciso" e sparirà dalla mappa.
        // Dopodichè l'effetto del KnifePowerUP finirà ed il Player tornerà allo stato
        // di default

        // Attivazione forzata KnifePowerUP
        final KnifePowerUP knife = new KnifePowerUP(ID.KNIFEPU, 0, 0, texture.getPowerUPImageByID(ID.KNIFEPU));
        knife.effect(player);

        collisionsPlayer.collisionsInGame(player, baseEnemy);

        // Verifica della scomparsa e dell'uccisione dell'Enemy
        assertFalse(baseEnemy.isAlive());
        assertFalse(baseEnemy.isVisible());

        // Verifica della fine dell'effetto del KnifePowerUP
        assertFalse(player.hasKnife());

        // CASO 3: Player CON KnifePowerUP attivo e DragonEnemy

        // NOTA: Per evitare interferenze col caso preso ora in esame, andiamo a forzare
        // la rimozione dell'entità BaseEnemy dalla mappa
        baseEnemy.setVisible(false);

        // Creazione entità DragonEnemy con i canonici valori di default per forzare la
        // collisione
        // Posizione: (0,0)
        // Velocità iniziale: 0,0
        final DragonEnemy dragon = new DragonEnemy(ID.ENEMY,
                 0, 0, 0, 0, texture.getDragonEnemyListTexture(), texture.getDragonEnemyRayListTexture());

        // Settaggio forzato del KnifePowerUP al Player
        player.setKnife(true);
        assertTrue(player.hasKnife());

        // Il risultato da noi atteso sarebbe che, dato che il KnifePowerUP non ha alcun
        // effetto contro nemici quali TowerEnemy e DragonEnemy, il DragonEnemy dovrebbe
        // essere invulnerabile a tale situazione

        // Verifica vite Player
        assertEquals(player.getHealth(), 2);

        // Collisione con DragonEnemy
        collisionsPlayer.collisionsInGame(player, dragon);

        // Verifica della persistente presenza del DragonEnemy dopo la collisione col
        // Player
        assertTrue(dragon.isVisible());
        assertTrue(dragon.isAlive());

    }

}
