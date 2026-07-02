package view.managemenu;

import controller.CinemaController;
import controller.CinemaControllerObserver;
import utilities.Account;

public interface MenuView {
    void setObserver(CinemaControllerObserver observer);
    void show();
    void updateGUI(Account accountLogged);
}
