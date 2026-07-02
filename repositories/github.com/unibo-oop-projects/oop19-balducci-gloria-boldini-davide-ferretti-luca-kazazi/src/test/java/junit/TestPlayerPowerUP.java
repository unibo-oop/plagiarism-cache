package junit;
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Timer;
import java.util.TimerTask;

import org.junit.jupiter.api.Test;

import controllers.texture.GetTexture;
import model.ID;
import model.handler.Handler;
import model.player.Player;
import model.powerup.InvisiblePowerUP;
import model.powerup.KnifePowerUP;
import model.powerup.LifeUpPowerUP;
import model.powerup.SpeedUPPowerUP;

public class TestPlayerPowerUP {

    // Classe con all'interno test di JUnit per verificare il corretto funzionamento
    // del meccanismo di iterazione tra l'entità Player ed i PowerUP

    private final Handler handler = new Handler();
    private final GetTexture texture = new GetTexture();
    private final Timer timer = new Timer();
    private static int secs;

    @Test
    public void testPowerUP() {

        // Inizializzo l'oggetto Player, soggetto dei test
        // Creazione Player con valori di default: Posizione iniziale (0,0), velocità
        // iniziale nulla (0,0), ossia Player fermo, ed immagini necessarie per il
        // caricamento delle texture
        // ricavate dalla classe GetTexture
        final Player player = new Player(ID.PLAYER, handler, 0, 0, 0, 0, texture.getPlayerListTexture());

        // Creazione di tutti i powerUP (relativi al Player) e conseguente verifica
        // degli effetti sull'entità Player
        // NOTA: Tutti i powerUP saranno inizializzati con i seguenti valori di default:
        // -) Posizione: 0,0
        // -) Velocità: 0,0 (Ovviamente)

        // Creazione powerUP invisibilità
        final InvisiblePowerUP invisible = new InvisiblePowerUP(ID.INVISIBLEPU, 0, 0,
                texture.getPowerUPImageByID(ID.INVISIBLEPU));

        // Creazione powerUP Knife
        final KnifePowerUP knife = new KnifePowerUP(ID.KNIFEPU, 0, 0, texture.getPowerUPImageByID(ID.KNIFEPU));

        // Creazione powerUP LifeUp
        final LifeUpPowerUP life = new LifeUpPowerUP(ID.LIFEUPPU, 0, 0, texture.getPowerUPImageByID(ID.LIFEUPPU));

        // Creazione powerUP SpeedUp
        final SpeedUPPowerUP speed = new SpeedUPPowerUP(ID.SPEEDUPPU, 0, 0,
                texture.getPowerUPImageByID(ID.SPEEDUPPU));

        // Verifica effetti InvisiblePowerUP
        invisible.effect(player);
        // assertEquals(player.isVisible(), false); //ATTENZIONE: Risolvere conflitto
        // getVisible (in Player) e isVisible (in GameObject)
        // SOLUZIONE: Eliminare la ridondanza di Player

        // Verifica effetti KnifePowerUP
        knife.effect(player);
        assertTrue(player.hasKnife());

        // Verifica della scomparsa del PowerUP dalla mappa
        assertFalse(knife.isVisible());

        // Verifica effetti lifeUp
        // ATTENZIONE: tale PowerUP quando il Player ha 3 vite (come in questo caso),
        // tali non devono aumentare
        life.effect(player);
        assertNotEquals(player.getHealth(), 4);

        // Verifica della scomparsa del PowerUP dalla mappa
        // NOTA: anche se senza effetto il PowerUP sparisce ugualmente dalla mappa
        assertFalse(life.isVisible());

        // Verifica effetti speedUP
        // Verifica iniziale di effettiva velocità di movimento 2.0 del Player
        assertEquals(player.getSpeed(), 2.0);

        speed.effect(player);
        assertEquals(player.getMovement().getSpeed(), 4.0);

        // Verifica della scomparsa del PowerUP dalla mappa
        assertFalse(speed.isVisible());

        // Utilizzo un timer per verificare che dopo 5 secondi l'effetto dello
        // SpeedPowerUP sul Player è finito
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (secs < 5) {
                    secs++;
                } else if (secs >= 5) {
                    this.cancel();
                    timer.cancel();
                    assertNull(player.getActivePowerUpDebuff());
                }
            }
        }, 0, 1000);
        // Verifica che lo SpeedPowerUP ha finito l'effetto sull'entità Player e
        // pertanto il valore dei PowerUP attualmente attivi dovrà essere null
    }

}
