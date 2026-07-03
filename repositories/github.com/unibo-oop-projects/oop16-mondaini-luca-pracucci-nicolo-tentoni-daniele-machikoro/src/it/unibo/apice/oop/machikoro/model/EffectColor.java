package it.unibo.apice.oop.machikoro.model;

/**
 * Enumeratore per identificare il colore dell'effetto.
 */
public enum EffectColor {
    /**
     * Identifica il colore blu, un effetto che si attiva in ogni turno.
     */
    BLUE,
    /**
     * Identifica il colore verde, un effetto che si attiva solamente nel
     * proprio turno.
     */
    GREEN,
    /**
     * Identifica il colore rosso, un effetto che si attiva solamente nel turno
     * avversario.
     */
    RED
}
