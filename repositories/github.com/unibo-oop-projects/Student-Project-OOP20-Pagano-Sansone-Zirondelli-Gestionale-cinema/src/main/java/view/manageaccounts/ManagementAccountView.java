package view.manageaccounts;

import controller.manageaccounts.AccountsController;

public interface ManagementAccountView {
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
     * Update view table of list account.
     */
    void update();
}
