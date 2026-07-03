package maingame.sound;

import java.applet.Applet;
import java.applet.AudioClip;

/**
 * . Implementazione di Sound
 */
public final class SoundImpl implements Sound {

    /**
     * . suono di sottofondo
     */
    public static final Sound BACKGROUND = new SoundImpl("/sound/sfondo.wav");
    /**
     * . suono di morte
     */
    public static final Sound DEAD = new SoundImpl("/sound/mortezombie.wav");
    /**
     * . souno di danno agli zombi
     */
    public static final Sound DAMAGEZOMBI = new SoundImpl("/sound/dannoazombi.wav");
    /**
     * . suono del pugno corpo a corpo
     */
    public static final Sound PUNCH = new SoundImpl("/sound/punch.wav");
    /**
     * . suono della pozione
     */
    public static final Sound POTION = new SoundImpl("/sound/potion.wav");
    /**
     * . suono dello sparo sinistro
     */
    public static final Sound SHOT = new SoundImpl("/sound/shot.wav");
    /**
     * . suono dello sparo destro
     */
    public static final Sound KAMEHAMEHA = new SoundImpl("/sound/Kamehameha.wav");
    /**
     * . suono del livello aggiuntivo
     */
    public static final Sound LEVELUP = new SoundImpl("/sound/levelup.wav");
    /**
     * . suono di bassa vita
     */
    public static final Sound HEART = new SoundImpl("/sound/heart.wav");
    /**
     * . suono per pecora
     */
    public static final Sound SHEEP = new SoundImpl("/sound/sheep.wav");

    /**
     * . suono per pioggia
     */
    public static final Sound RAIN = new SoundImpl("/sound/rain.wav");

    /**
     * . suono per tuono
     */
    public static final Sound THUNDER = new SoundImpl("/sound/thunder.wav");

    /**
     * . suono per cascata
     */
    public static final Sound WATERFALL = new SoundImpl("/sound/waterfall.wav");

    /**
     * . suono per l'apertura di una porta
     */
    public static final Sound DOOR_OPEN = new SoundImpl("/sound/opendoor.wav");

    /**
     * . suono per il raccoglimento della chiave
     */
    public static final Sound KEY = new SoundImpl("/sound/key.wav");

    /**
     * . suono per il raccoglimento dell' elmo
     */
    public static final Sound HELM = new SoundImpl("/sound/helm.wav");

    /**
     * . suono per la notte
     */
    public static final Sound NIGHT = new SoundImpl("/sound/notte.wav");

    /**
     * . suono per game over
     */
    public static final Sound GAMEOVER = new SoundImpl("/sound/gameover.wav");

    /**
     * . suono per game win
     */
    public static final Sound GAMEWIN = new SoundImpl("/sound/gamewin.wav");
    /**
     * . suono per house
     */
    public static final Sound HOUSE = new SoundImpl("/sound/house.wav");
    /**
     * . suono per cave
     */
    public static final Sound CAVE = new SoundImpl("/sound/cave.wav");

    private AudioClip sound;

    private boolean stopped = true;

    private SoundImpl(final String name) {
        try {
            sound = Applet.newAudioClip(SoundImpl.class.getResource(name));
        } catch (Exception e) {
            System.err.println("file audio mancante");
        }
    }

    @Override
    public void play(final boolean loop) {
        stopped = false;
        try {
            if (loop) {
                sound.loop();
            } else {
                sound.play();
            }
        } catch (Exception e) {
            System.err.println("file audio mancante");
        }
    }

    @Override
    public void stop() {
        try {
            stopped = true;
            sound.stop();
        } catch (Exception e) {
            System.err.println("file audio mancante");
        }
    }

    @Override
    public boolean isStopped() {
        return stopped;
    }

}