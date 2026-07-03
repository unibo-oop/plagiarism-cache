package it.unibo.memory.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import it.unibo.memory.model.Difficulty;

/* manager di punteggio, tiene traccia solo quando è eseguito */
public class ScoreManager {
    //richiamo il file e carico 
    private static final String FILE_NAME = System.getProperty("user.home") + "/.memory-pss-scores.properties";
    private Properties props;

    public ScoreManager() {
        props = new Properties();
        load();
    }

    /*restituisce il best score, 
    il final mi serve per evitare che cambi dentro il metodo
     implementazione controlli in caso file corrotto
    */
    public int getBestScore(Difficulty diff) {
        String value = props.getProperty(diff.name());
        if (value == null) {
            return Integer.MAX_VALUE; // nessun punteggio ancora
        }
        return Integer.parseInt(value);
    }

    /* metodo per sostituire il valore se maggiore */
    public boolean updateIfBetter(Difficulty diff, int mosse) {
        if (mosse < getBestScore(diff)) {
            props.setProperty(diff.name(), String.valueOf(mosse));
            save();
            return true;
        }
        return false;
    }

    public String getBestScoreText(Difficulty diff) {
        int top = getBestScore(diff);
        if (top == Integer.MAX_VALUE) {
            return "Nessun record";
        }
        return top + " mosse";
    }
    //metodo di load 
    private void load() {
        File file = new File(FILE_NAME);
        if (file.exists()) {
            try {
                FileInputStream in = new FileInputStream(file);
                props.load(in);
                in.close();
            } catch (IOException e) {
                System.out.println("Errore nel caricamento dei punteggi");
            }
        }
    }
    // metodo di save 
     
    private void save() {
        try {
            FileOutputStream out = new FileOutputStream(FILE_NAME);
            props.store(out, "Miglior Punteggio");
            out.close();
        } catch (IOException e) {
            System.out.println("Errore nel salvataggio dei punteggi");
        }
    }
}
