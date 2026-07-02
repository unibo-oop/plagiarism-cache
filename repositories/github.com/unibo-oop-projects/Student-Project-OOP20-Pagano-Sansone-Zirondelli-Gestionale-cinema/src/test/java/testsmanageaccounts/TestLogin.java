package testsmanageaccounts;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import model.manageaccounts.AccountModel;
import modelimpl.manageaccounts.AccountModelImpl;
import utilities.Account;
import utilities.TypeAccount;
import utilitiesimpl.AccountImpl;

public class TestLogin {

    @Test
    public void testLogin() {
        final Set<Account> setAccount = new HashSet<>();
        final Account a1 = new AccountImpl("Mario", "Rossi", "Ross", "qwerty", TypeAccount.OPERATOR);
        final Account a2 = new AccountImpl("Giacomo", "Pippo", "Giac", "qwerty1", TypeAccount.ADMINISTRATOR);
        final Account a3 = new AccountImpl("Piero", "Suli", "Pier", "qwerty123", TypeAccount.OPERATOR);
        setAccount.add(a1);
        setAccount.add(a2);
        setAccount.add(a3);

        final AccountModel modelAccount = new AccountModelImpl(setAccount);
        modelAccount.setAccountLogged(a2);

        assertEquals(modelAccount.getAccountLogged(), a2);
    }
}
