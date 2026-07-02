package junit;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.Timer;
import java.util.TimerTask;

import org.junit.jupiter.api.Test;

import controllers.texture.GetTexture;
import model.ID;
import model.debuff.FireDebuff;
import model.debuff.SlowDownDebuff;
import model.handler.Handler;
import model.player.Player;

public class TestPlayerDebuff {

    // Classe con all'interno test di JUnit per verificare il corretto funzionamento
    // del meccanismo di iterazione tra l'entità Player ed i PowerUP

    private final Handler handler = new Handler();
    private final GetTexture texture = new GetTexture();
    private final Timer timer = new Timer();
    private static int secs;

    @Test
    public void testDebuff() {

        // Inizializzo l'oggetto Player, soggetto dei test
        // Creazione Player con valori di default: Posizione iniziale (0,0), velocità
        // iniziale nulla (0,0), ossia Player fermo, ed immagini necessarie per il
        // caricamento delle texture
        // ricavate dalla classe GetTexture
        final Player player = new Player(ID.PLAYER, handler, 0, 0, 0, 0, texture.getPlayerListTexture());

        // Creazione di tutti i Debuff (relativi al Player) e conseguente verifica
        // degli effetti sull'entità Player
        // NOTA: Tutti i powerUP saranno inizializzati con i seguenti valori di default:
        // -) Posizione: 0,0
        // -) Velocità: 0,0 (Ovviamente)

        // Creazione Debuff FireDebuff
        final FireDebuff fire = new FireDebuff(ID.FIREDB, 0, 0, texture.getDebuffImageByID(ID.FIREDB));

        // Creazione Debuff SlowdownDebuff
        final SlowDownDebuff slow = new SlowDownDebuff(ID.SLOWDOWNDB, 0, 0, texture.getDebuffImageByID(ID.SLOWDOWNDB));

        // VERIFICA EFFETTI FIRE DEBUFF

        // Verranno verificati due casi:
        // 1) Il Player possiede 3 vite
        // 2) Il Player possiede 1 vita

        // CASO 1: 3 vite

        // Verifica preliminare
        assertEquals(player.getHealth(), 3);

        fire.effect(player);

        // Verifica della perdita di una vita
        assertEquals(player.getHealth(), 2);

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (secs < 5) {
                    secs++;
                } else if (secs >= 5) {
                    this.cancel();
                    timer.cancel();
                    fire.effect(player);
                    assertEquals(player.getHealth(), 1);
                }
            }
        }, 0, 1000);

        // CASO 2: 1 vita

        // Verifica preliminare
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (secs < 5) {
                    secs++;
                } else if (secs >= 5) {
                    this.cancel();
                    timer.cancel();
                    fire.effect(player);
                    assertEquals(player.getHealth(), 0);
                    assertNotEquals(player.isGameOver(), false);
                }
            }
        }, 0, 1000);

        // VERIFICA EFFETTI SLOW DEBUFF

        assertEquals(player.getSpeed(), 2.0);

        slow.effect(player);

        assertEquals(player.getMovement().getSpeed(), 1.0);

    }
}
