package controllerimpl;

import controller.CinemaController;

import controller.CinemaControllerObserver;
import controller.booking.BookingController;
import controller.manageaccounts.AccountsController;
import controller.managefilms.FilmsController;
import controller.manageprogrammingfilms.ProgrammingFilmsController;
import controller.managestatistics.StatisticsController;
import controllerimpl.booking.BookingControllerImpl;
import controllerimpl.manageaccounts.AccountsControllerImpl;
import controllerimpl.managefilms.FilmsControllerImpl;
import controllerimpl.manageprogrammingfilms.ProgrammingFilmsControllerImpl;
import controllerimpl.managestatistics.StatisticsControllerImpl;
import utilities.ManagerWorkingDir;
import utilitiesimpl.ManagerWorkingDirImpl;
import view.managemenu.MenuView;
import viewimpl.managemenu.MenuViewImpl;


public class CinemaControllerImpl implements CinemaController, CinemaControllerObserver {
    private final ManagerWorkingDir manager;
    private final MenuView menu;
    private final AccountsController accountController;

    public CinemaControllerImpl() {
        super();
        manager = new ManagerWorkingDirImpl();
        this.menu = new MenuViewImpl();
        this.menu.setObserver(this);
        this.initApplication(System.getProperty("user.home"));
        this.accountController = new AccountsControllerImpl();
        this.accountController.setCinemaController(this);
        this.accountController.showLoginAccounView();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void showMenu() {
        menu.updateGUI(accountController.getAccountLogged());
        menu.show();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void showControllerAccount() {
        accountController.showManagementAccountView();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void showControllerTicket() {
        final BookingController controller = new BookingControllerImpl();
        controller.setCinemaController(this);
        controller.start();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void showControllerFilm() {
        final FilmsController controller = new FilmsControllerImpl();
        controller.setCinemaController(this);
        controller.showContainerFilmsView();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void showControllerProgrammingFilms() {
        final ProgrammingFilmsController controller = new ProgrammingFilmsControllerImpl();
        controller.setCinemaController(this);
        controller.showProgrammedFilmView();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void showControllerStatistics() {
        final StatisticsController controller = new StatisticsControllerImpl();
       controller.setCinemaController(this);
       controller.showStatisticsView();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void initApplication(final String path) {
        manager.initWorkingDir(path);
    }
}

