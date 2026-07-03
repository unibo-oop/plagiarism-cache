package view;

import controller.LogInControllerImpl;
import model.pkglevels.ImageLoaderImpl;
import model.pkglevels.LogInModelImpl;

/**
 * Main class, launches the program.
 * 
 * 
 *
 */

public class Main {
    /**
     * Main method.
     * 
     * @param args
     *            parameters passed
     */
    public static void main(final String[] args) {

        final ImageLoaderImpl loader = ImageLoaderImpl.getLoaderInstance();
        final LogInControllerImpl c = LogInControllerImpl.getLICController();
        final LogInModelImpl model = new LogInModelImpl();
        final LogInWindow lw = new LogInWindow();

        c.addModel(model);
       

    }

}
