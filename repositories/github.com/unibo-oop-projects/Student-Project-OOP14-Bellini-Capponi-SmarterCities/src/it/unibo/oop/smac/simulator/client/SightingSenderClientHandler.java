package it.unibo.oop.smac.simulator.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Classe le operazioni effettuate dal client di rete all'invio e alla ricezioni di messaggi dal
 * server.
 * 
 * @author Francesco Capponi
 */
public class SightingSenderClientHandler extends ChannelInboundHandlerAdapter {
  /**
   * Logger della classe
   */
  private static final Logger LOGGER = LoggerFactory.getLogger(SightingSenderClientHandler.class);

  /**
   * Track corrente di cui si vuole simulare il comportamento.
   */
  private final TrackSimulator trackSimulator;

  /**
   * Construttore che imposta la track che il client deve seguire.
   * 
   * @param tSimulator
   *          oggetto che si occupa della simulazione del percorso del client corrente
   */
  public SightingSenderClientHandler(final TrackSimulator tSimulator) {
    this.trackSimulator = tSimulator;
  }

  /**
   * Funzione che stabilisce cosa deve essere effettuato una volta creato il canale di comunicazione
   * client-server.
   */
  @Override
  public void channelActive(final ChannelHandlerContext ctx) {
    // ai fini di test, finché l'applicazione è attiva, dovrà essere spedito
    // un pacchetto al server contenente le informazioni del sighting,
    // generato dal TrackCommand
    while (true) {
      ctx.writeAndFlush(trackSimulator.next());
    }
  }

  /**
   * Effettua il flush dei dati nel buffer.
   */
  @Override
  public void channelReadComplete(final ChannelHandlerContext ctx) {
    ctx.flush();
  }

  /**
   * Nel caso in cui la connessione cada, vada in timeout o accada qualsiasi altro errore, viene
   * generata un'exception e terminata la connessione.
   */
  @Override
  public void exceptionCaught(final ChannelHandlerContext ctx, final Throwable cause) {
    LOGGER.error("Error handling client connection", cause);
    ctx.close();
  }
}