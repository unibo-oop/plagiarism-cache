package controller;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

import javafx.util.Pair;
import model.GameMode;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;

/**
 * @author Mirco Gnoli
 *
 * Una classe singleton per gestire la memorizzazione dei punteggi.
 */
public final class HighscoreManager {
    private static HighscoreManager instance;

    private static final String PATH = System.getProperty("user.home") + System.getProperty("file.separator");
    private static final String MODE_CLASSIC = "Classic";
    private static final String MODE_SURVIVAL = "Survival";

    private List<Pair<String, Integer>> classicScore;
    private List<Pair<String, Integer>> survivalScore;

    private HighscoreManager() {
        this.classicScore = loadScore(MODE_CLASSIC);
        this.survivalScore = loadScore(MODE_SURVIVAL);
    }

    /**
     * @return l'istanza di questa classe.
     */
    public static HighscoreManager getInstance() {
        if (Objects.isNull(instance)) {
            instance = new HighscoreManager();
        }
        return instance;
    }


    /**
     * Aggiunge un punteggio.
     * @param name
     *          nome del giocatore.
     * @param score
     *          punteggio 
     * @param mode
     *          modalità di gioco scelta fra quelle disponibili in {@link GameMode}
     */
    public void addScore(final String name, final Integer score, final GameMode mode) {
        Pair<String, Integer> s = new Pair<>(name, score);

        switch (mode) {
        case CLASSIC:
            this.classicScore.add(s);
            Collections.sort(this.classicScore, new MyCompare());
            break;

        case SURVIVAL:
            this.survivalScore.add(s);
            Collections.sort(this.survivalScore, new MyCompare());
            break;

        default:
            System.out.println("errore creare eccezione");
            break;
        }
    }


    /**
     * Metodo per caricare tutti i punteggi dal file.
     * 
     * @param modalità di gioco
     * 
     * @return lista di {@link Pair} (String, Integer)
     */
    @SuppressWarnings("unchecked")
    private List<Pair<String, Integer>> loadScore(final String mode) {
        List<Pair<String, Integer>> load = new ArrayList<>();

        File f = new File(PATH + mode);
        try {
            f.createNewFile();

            if (f.length() > 0) {
                final InputStream file = new FileInputStream(PATH + mode);
                final InputStream bstream = new BufferedInputStream(file);
                ObjectInputStream ostream = new ObjectInputStream(bstream);

                load = (List<Pair<String, Integer>>) ostream.readObject();
                ostream.close();
            }

        } catch (IOException e) {
            System.out.println("Errore file " + f + " corrotto. Eliminare il file e riavviare l'applicazione");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            //Non dovrebbe mai entrare qui
            e.printStackTrace();
        }
        return load;
    }

    /**
     * Metodo per salvare tutti i punteggi su file.
     */
    public void saveAllScores() {
        saveScore(MODE_CLASSIC, classicScore);
        saveScore(MODE_SURVIVAL, survivalScore);
    }

    /**
     * Metodo per salvare i punteggi di una determinata modalità.
     * 
     * @param mode
     *
     * @param list
     *          Lista di punteggi per quella modalità.
     */
    private void saveScore(final String mode, final List<Pair<String, Integer>> list) {
        try {
            final OutputStream file = new FileOutputStream(PATH + mode);
            final OutputStream bstream = new BufferedOutputStream(file);
            final ObjectOutputStream ostream = new ObjectOutputStream(bstream);

            ostream.writeObject(list);
            ostream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
    * Metodo per ottenere la lista dei punteggi per una determinata modalità.
    * 
    * @param mode
    *           a scelta fra {@link GameMode}
    * 
    * @return lista di Pair(String, Integer)
    */
    public List<Pair<String, Integer>> getScore(final GameMode mode) {
        switch (mode) {
        case CLASSIC:
            Collections.sort(this.classicScore, new MyCompare());
            return this.classicScore;

        case SURVIVAL:
            Collections.sort(this.survivalScore, new MyCompare());
            return this.survivalScore;

            default:
                System.out.println("Errore, eccezione");
                return null;
        }
    }

    /**
     * Semplice comparatore per ordinare in ordine crescente le coppie (nome, punteggio) in base al punteggio.
     *
     */
    public class MyCompare implements Comparator<Pair<String, Integer>> {

        /**
         * {@inheritDoc}
         */
        @Override
        public int compare(final Pair<String, Integer> o1, final Pair<String, Integer> o2) {
            return Integer.compare(o2.getValue(), o1.getValue());
        }

    }
}
