package controller;

import java.io.File;

import model.pkglevels.ImageLoaderImpl;
import model.pkglevels.LogInModel;
import model.pkglevels.LogInModelImpl;
import model.pkgplayer.Player;
import view.LogInWindow;

/**
 * Controller for the LogInWindow class.
 * 
 * 
 *
 */
public final class LogInControllerImpl implements LogInController {

    private static final LogInControllerImpl LIC_SINGLETON = new LogInControllerImpl();
    private LogInModel model;
    // private LogInWindow lw;

    /**
     * private constructor of the class, only accessible internally
     */
    private LogInControllerImpl() {
        // method of the loader for uploading the images
        ImageLoaderImpl.getLoaderInstance().loadImages(67, 64);
    }

    /**
     * Method that returns the Singleton.
     * 
     * 
     * @return the controller instance
     */
    public static LogInControllerImpl getLICController() {
        return LogInControllerImpl.LIC_SINGLETON;
    }

    @Override
    public int check(final Player p) {
        return model.checkInput(p);
    }

    @Override
    public int readData(final Player p, final boolean status, final File f) {
        
       return model.readPlayerData(p, status, f);
    }

    

    @Override
    public void addModel(final LogInModel l) {
        this.model =  l;
    }

    @Override
    public int registerPlayerData(final Player currentPlayer, final File f) {
        return this.model.registerPlayerData(currentPlayer, f);
    }
   
   

}
