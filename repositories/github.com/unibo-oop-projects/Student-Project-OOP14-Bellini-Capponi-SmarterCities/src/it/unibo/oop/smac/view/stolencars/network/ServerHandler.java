package it.unibo.oop.smac.view.stolencars.network;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Classe le operazioni effettuate dal server di rete all'invio e alla ricezioni di messaggi dal
 * client.
 * 
 * @author Francesco Capponi
 */
public class ServerHandler extends ChannelInboundHandlerAdapter {

  /**
   * Logger della classe
   */
  private static final Logger LOGGER = LoggerFactory.getLogger(ServerHandler.class);

  /**
   * dispatcher che implementa l'interfaccia Observable per la notifica dei jobs dell'arrivo di un
   * determinato messaggio
   */
  private final Dispatcher dispatcher;

  /**
   * Costruttore della classe.
   * 
   * @param d
   *          dell'applicazione su cui verranno richiamate le funzioni di segnalazione all'arrivo di
   *          eventi dalla rete
   */
  ServerHandler(final Dispatcher d) {
    this.dispatcher = d;
  }

  /**
   * Metodo che alla ricezione di un determinato messaggio, notifica tutti i job che stanno
   * osservando il dispatcher.
   */
  @Override
  public void channelRead(final ChannelHandlerContext ctx, final Object msg) {
    dispatcher.notifyObservers(msg);
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