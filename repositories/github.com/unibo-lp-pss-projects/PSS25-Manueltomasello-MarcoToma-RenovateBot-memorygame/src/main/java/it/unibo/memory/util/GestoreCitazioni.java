package it.unibo.memory.util;

import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.Scanner;

public class GestoreCitazioni {

    // Indirizzo richiesta API
    private static final String URL_API = "https://dummyjson.com/quotes/random";

    //1. faccio una http get  
    public static String getCitazioneCasuale() {
        try {
            //nel try faccio la richiesta http
            URL url = URI.create(URL_API).toURL();
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5000); // Timeout di 5 secondi per non bloccare il gioco

            //2. se http ci restituisce 200 tutto ok! altrimenti restituisci la default frase
            if (conn.getResponseCode() != 200) {
                return "La logica ti porterà da A a B. L'immaginazione ovunque.";
            }
            //la gestione della stringa viene da Ai 
            // 3. Lettura del flusso di dati (InputStream mi occupo di leggere il flusso di byte trasformandolo in una stringa leggibile riga dopo riga)
            Scanner scanner = new Scanner(conn.getInputStream());
            StringBuilder risposta = new StringBuilder();
            while (scanner.hasNextLine()) {
                risposta.append(scanner.nextLine());
            }
            scanner.close();

            // 4. Estrazione manuale della stringa dal JSON
            String json = risposta.toString();
            // Cerco la chiave "quote" nel formato JSON
            int inizio = json.indexOf("\"quote\":\"") + 9;
            int fine = json.indexOf("\"", inizio);

            return json.substring(inizio, fine);

        } catch (Exception e) {
            // In caso di errore (es. mancanza di internet), stampiamo la frase di default
            e.printStackTrace();
            return "Non mollare mai! Il successo è un viaggio, non una meta.";
        }
    }
}