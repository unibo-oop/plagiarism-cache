package it.unibo.oop.smac.simulator.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import it.unibo.oop.smac.view.stolencars.network.NetServer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Classe che gestisce l'apertura della connessione di un simulatore sighting tra client-serer.
 * 
 * @author Francesco Capponi
 */
public final class SightingSenderClient implements Runnable {

  /**
   * Logger della classe
   */
  private static final Logger LOGGER = LoggerFactory.getLogger(SightingSenderClient.class);

  /**
   * Costante dell'indirizzo del server cui collegarsi.
   */
  private static final String HOST = System.getProperty("host", "localhost");

  /**
   * Costante della porta del server cui collegarsi.
   */
  private static final int PORT = NetServer.PORT;

  /**
   * Genero in maniera random un nuovo trackSimulator da utilizzare durante la connessione.
   */
  private final TrackSimulator trackSimulator = new TrackSimulator(LicensePlateGenerator.generate());

  /**
   * Inizializzazione dell'inizializzatore di un nuovo channel (connessione).
   */
  private final ChannelInitializer<SocketChannel> channelInitializer = new ChannelInitializer<SocketChannel>() {
    @Override
    public void initChannel(final SocketChannel ch) {
      final ChannelPipeline p = ch.pipeline();

      // imposto gli encoder-decoder che incapsulano i messaggi della
      // connessione, e l'handler che gestisce gli eventi di scambio
      // messaggi
      p.addLast(new ObjectEncoder(), new ObjectDecoder(ClassResolvers.cacheDisabled(null)),
          new SightingSenderClientHandler(trackSimulator));
    }
  };

  /**
   * Metodo che fa partire il processo di connessione.
   */
  @Override
  public void run() {
    final EventLoopGroup group = new NioEventLoopGroup();
    try {
      // inizializzo i parametri di connessione
      final Bootstrap b = new Bootstrap();
      b.group(group).channel(NioSocketChannel.class).handler(channelInitializer);

      // faccio partire la connessione
      b.connect(HOST, PORT).sync().channel().closeFuture().sync();
    } catch (InterruptedException e) {
      // un client è morto indispettito
      LOGGER.error("Errore durante la connessione del client al server", e);
    } finally {
      group.shutdownGracefully();
    }

  }
}