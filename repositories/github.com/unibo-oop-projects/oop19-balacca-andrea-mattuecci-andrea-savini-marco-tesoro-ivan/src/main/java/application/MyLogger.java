package application;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
 
// TODO: Auto-generated Javadoc
/**
 * The Class MyLogger.
 */
public class MyLogger {
 
    /** The My loggers. */
    private static ArrayList<Logger> MyLoggers = new ArrayList<Logger>();
    
    /** The file handler. */
    private static Handler fileHandler = null;
    
    //private static final Logger LOGGER = Logger.getLogger(MyLogger.class.getName());
    
    /**
     * Ottieni un logger.
     *
     * @param IDUtente the ID utente
     * @return the logger
     */
    public static Logger OttieniUnLogger(String IDUtente) {
        for (var l : MyLoggers) {
            if(l.getName().equals(IDUtente)) {
                return l;
            }
        }
        return null;
    }
    
    /**
     * Aggiungi un logger.
     *
     * @param IDUtente the ID utente
     */
    public static void AggiungiUnLogger(String IDUtente) {
        try {
        
        fileHandler = new FileHandler(IDUtente+".log", true); //creo l'handler associato al file IDUtente.log (es. Admin.log), in maniera "append" (true)
        
        }catch(IOException exception){
            //todo
        }
        
        fileHandler.setFormatter(new SimpleFormatter() {
            private static final String format = "[%1$tF %1$tT] %3$s [%2$-7s] %4$s %n"; //formato personalizzato

            @Override
            public synchronized String format(LogRecord lr) {
                return String.format(format,
                        new Date(lr.getMillis()), //1$
                        lr.getLevel().getLocalizedName(), //2$
                        lr.getLoggerName(), //3$
                        lr.getMessage() //4$
                );
            }
        });
        
        fileHandler.setLevel(Level.ALL); //permetto la visualizzazione di ogni livello di log
        
        MyLoggers.add(Logger.getLogger(IDUtente));
        
        OttieniUnLogger(IDUtente).addHandler(fileHandler);
    }
    
}
