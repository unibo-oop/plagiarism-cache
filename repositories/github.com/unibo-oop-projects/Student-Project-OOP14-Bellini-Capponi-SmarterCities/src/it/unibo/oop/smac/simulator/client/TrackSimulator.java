package it.unibo.oop.smac.simulator.client;

import it.unibo.oop.smac.datatypes.LicensePlate;
import it.unibo.oop.smac.view.stolencars.network.PlainSighting;

import java.io.InputStream;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;

/**
 * Classe che gestisce la simulazione del percorso di una macchina.
 * 
 * @author Francesco Capponi
 */
public class TrackSimulator {
  /**
   * Logger della classe
   */
  private static final Logger LOGGER = LoggerFactory.getLogger(TrackSimulator.class);

  /**
   * Velocita' media di un auto.
   */
  private static final int AVERAGE_SPEED = 40;

  /**
   * File contenente i tracks possibili.
   */
  public static final String FILENAME = "tracks/tracks.json";

  /**
   * Track corrente da seguire.
   */
  private Track track;

  /**
   * Posizione del track in cui si trova la macchina in questo istante.
   */
  private Integer currentIndex = 0;

  /**
   * Targa della macchina di cui si sta simulando lo spostamento.
   */
  private final LicensePlate licensePlate;

  /**
   * Azione con cui si genera un nuovo Sighting e l'avanzamento della macchina nel track.
   * 
   * @return {@link PlainSighting} del passaggio corrente
   */
  public PlainSighting next() {
    final TrackCommand current = track.getTrackCommands().get(currentIndex);
    try {
      Thread.sleep(1000 * current.getSleep());
    } catch (InterruptedException e) {
      LOGGER.error("Thread ha ricevuto un interrupt mentre era in sleep", e);
    }

    final PlainSighting response = new PlainSighting();
    response.setCoordinates(current.getCoordinate());
    response.setDate(new Date());
    response.setLicensePlate(licensePlate.toString());
    response.setSpeed(new Float(new Random().nextInt(AVERAGE_SPEED) + AVERAGE_SPEED));

    final int size = track.getTrackCommands().size();
    // avanzo il contatore
    currentIndex = (currentIndex + 1) % size;

    return response;
  }

  /**
   * Costruttore della classe che richiede la targa della macchina di cui si vuole simulare il
   * comportamento, e su quale track devi passare.
   * 
   * @param plate
   *          targa della macchina di cui si vuole simulare l'avanzamento
   * @param trackIndex
   *          id del track da utilizzare
   */
  public TrackSimulator(final LicensePlate plate, final Integer trackIndex) {
    track = getNTrack(trackIndex);
    this.licensePlate = plate;
  }

  /**
   * Costruttore della classe che richiede la targa della macchina di cui si vuole simulare il
   * comportamento, e sceglie un track casuale.
   * 
   * @param plate
   *          targa della macchina di cui si vuole simulare l'avanzamento
   */
  public TrackSimulator(final LicensePlate plate) {
    this(plate, new Random().nextInt(100));
  }

  /**
   * Metodo che restituisce la track richiesta.
   * 
   * @param trackIndex
   *          id della track richiesta
   * @return Track richiesta
   */
  private Track getNTrack(final Integer trackIndex) {
    final Track[] tracks = getTracks();
    return tracks[trackIndex % tracks.length];
  }

  /**
   * Metodo che legge dal JSON tutte le tracks disponibili.
   * 
   * @return Track[] Tracks disponibili
   */
  private Track[] getTracks() {
    final Gson gson = new Gson();
    final String rawJson = readFile();
    return gson.fromJson(rawJson, Track[].class);
  }

  /**
   * Metodo per leggere il file contenente i tracks.
   * 
   * @return String testo del file
   */
  private String readFile() {
    final ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
    final InputStream stream = classLoader.getResourceAsStream(FILENAME);
    final Scanner scanner = new Scanner(stream, "UTF-8");
    final String fileText = scanner.useDelimiter("\\A").next();
    scanner.close();
    return fileText;
  }
}
