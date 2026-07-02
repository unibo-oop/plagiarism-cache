package view.manageaccounts;

import controller.manageaccounts.AccountsController;
import utilities.Account;

public interface RegistrationAccountView {
    /**
     * Show a management view.
     */
    void show();

    /**
     * Set Account controller observer.
     * @param observer that is Account controller
     */
    void setObserver(AccountsController observer);

    /**
     * Reset registration view.
     */
    void reset();
}
