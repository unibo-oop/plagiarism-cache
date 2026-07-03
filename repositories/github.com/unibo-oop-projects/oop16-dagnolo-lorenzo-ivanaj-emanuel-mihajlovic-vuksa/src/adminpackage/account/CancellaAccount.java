package adminpackage.account;

import javax.swing.JComboBox;

import adminpackage.person.CancellaPersona;
import adminpackage.template.DeleteOperation;
import hotelmaster.database.admin.AccountManagerImpl;

/**
 * Qui l'admin inserisce una mail valida da cancellare, se la mail esiste
 * apparira una JOption che ti avvisa se si vuole cancellare quella specifica
 * mail, se si decide di si apparirà una ShowMessageDialog nel quale bisogna
 * inserire la password dell'admin per poter confermare l'eliminazione della
 * mail.
 */
public class CancellaAccount extends DeleteOperation {

    private AccountManagerImpl accountmanager;
    private String[] array;

    public CancellaAccount(final String testo, final String titolo) {
        super(testo, titolo);
    }

    public static void main(final String[] args) {
        new CancellaAccount("Scegli account da cancellare", "Cancella account");
    }

    @Override
    public String[] getElements() {
        this.accountmanager = new AccountManagerImpl();
        this.array = this.accountmanager.getAccounts().toArray(new String[this.accountmanager.getAccounts().size()]);
        return this.array;
    }

    @Override
    public void sendData() {
        System.out.println(this.getElementoScelto());
    }
}