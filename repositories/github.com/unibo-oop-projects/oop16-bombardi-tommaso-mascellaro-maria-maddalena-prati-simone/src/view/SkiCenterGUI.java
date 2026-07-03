package view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.Controller;
import model.admin.Pair;

import view.panels.FrameSize;
import view.panels.PanelAdminOneImpl;
import view.panels.PanelBaseImpl;
import view.panels.PanelBuyDetailsImpl;
import view.panels.PanelBuyImpl;
import view.panels.PanelCartImpl;
import view.panels.PanelInstructorImpl;
import view.panels.PanelLoginAdminImpl;
import view.panels.PanelPurchaseImpl;
import view.panels.PanelRentDetailsImpl;
import view.panels.PanelRentImpl;
import view.panels.PanelSkipassImpl;
import view.panels.PanelStorageDetailsImpl;
import view.panels.PanelStorageImpl;
import view.panels.PanelTableImpl;
import view.panels.PanelUserOneImpl;
import view.panels.interfaces.PanelAdminOne;
import view.panels.interfaces.PanelBase;
import view.panels.interfaces.PanelBuy;
import view.panels.interfaces.PanelBuyDetails;
import view.panels.interfaces.PanelCart;
import view.panels.interfaces.PanelInstructor;
import view.panels.interfaces.PanelLoginAdmin;
import view.panels.interfaces.PanelPurchase;
import view.panels.interfaces.PanelRent;
import view.panels.interfaces.PanelRentDetails;
import view.panels.interfaces.PanelSkipass;
import view.panels.interfaces.PanelStorage;
import view.panels.interfaces.PanelStorageDetails;
import view.panels.interfaces.PanelTable;
import view.panels.interfaces.PanelUserOne;

/**
 * Main Class for SkiCenter GUI.
 *
 */
public class SkiCenterGUI extends JFrame {
    private static final long serialVersionUID = 1L;
    private final Controller ci;
    private final PanelBase panelBasicObj;
    private final PanelLoginAdmin panelLoginAdminObj;
    private final PanelUserOne panelUserOneObj;
    private final PanelSkipass panelSkipassObj;
    private final PanelAdminOne panelAdminOneObj;
    private final PanelBuy panelBuyObj;
    private final PanelRent panelRentObj;
    private final PanelInstructor panelInstructorObj;
    private final PanelStorage panelStorageObj;
    private final PanelTable panelTableObj;
    private final PanelCart panelCartObj;
    private final PanelPurchase panelPurchaseObj;
    private final PanelBuyDetails panelBuyDetailsObj;
    private final PanelRentDetails panelRentDetailsObj;
    private final PanelStorageDetails panelStorageDetailsObj;
    //CAMBIA PANNELLI rp PANNELLO DA RIMUOVERE, ap DA AGGIUNGERE
    private void switchPanel(final JPanel rp, final JPanel ap) {
        panelBasicObj.getPanelBase().remove(rp);
        panelBasicObj.getPanelBase().add(ap);
        ap.setVisible(true);
        rp.setVisible(false);
    }
    //STAMPA CARRELLO
    private void printCart() {
        for (final Integer i : ci.getCart().keySet()) {
            panelCartObj.addTableRow(i, ci.getCart().get(i));
            panelCartObj.addLabelPrice(ci.getCartPrice());
            panelCartObj.getBtnDeleteCart().setEnabled(true);
            panelCartObj.getBtnDeleteOperation().setEnabled(true);
            panelCartObj.getBtnFinishOp().setEnabled(true);
            panelCartObj.getOp().setEnabled(true);
        }
        if (ci.getCart().keySet().isEmpty()) {
            panelCartObj.addLabelPrice("0,00");
            panelCartObj.getBtnDeleteCart().setEnabled(false);
            panelCartObj.getBtnDeleteOperation().setEnabled(false);
            panelCartObj.getBtnFinishOp().setEnabled(false);
            panelCartObj.getOp().setEnabled(false);
        }
    }
    //DISABILITA
    private void disableComp() {
        panelPurchaseObj.getBtnPay().setEnabled(true);
        panelPurchaseObj.getTextUser().setEnabled(false);
        panelPurchaseObj.getTextUser2().setEnabled(false);
        panelPurchaseObj.getTextPass().setEnabled(false);
        panelPurchaseObj.getTextPass2().setEnabled(false);
        panelPurchaseObj.getTextName().setEnabled(false);
        panelPurchaseObj.getTextSurname().setEnabled(false);
        panelPurchaseObj.getBtnLogin().setEnabled(false);
        panelPurchaseObj.getBtnReg().setEnabled(false);
        panelPurchaseObj.getTextCard().setEnabled(true);
        panelPurchaseObj.getTextOwner().setEnabled(true);
        panelPurchaseObj.getTextDate().setEnabled(true);
        panelPurchaseObj.getTextCvc().setEnabled(true);
    }
    //ABILITA
    private void enableComp() {
        panelPurchaseObj.getBtnPay().setEnabled(false);
        panelPurchaseObj.getTextUser().setEnabled(true);
        panelPurchaseObj.getTextUser2().setEnabled(true);
        panelPurchaseObj.getTextPass().setEnabled(true);
        panelPurchaseObj.getTextPass2().setEnabled(true);
        panelPurchaseObj.getTextName().setEnabled(true);
        panelPurchaseObj.getTextSurname().setEnabled(true);
        panelPurchaseObj.getBtnLogin().setEnabled(true);
        panelPurchaseObj.getBtnReg().setEnabled(true);
        panelPurchaseObj.getTextCard().setEnabled(false);
        panelPurchaseObj.getTextOwner().setEnabled(false);
        panelPurchaseObj.getTextDate().setEnabled(false);
        panelPurchaseObj.getTextCvc().setEnabled(false);
    }
    //SET JTEXTFIELD EMPTY
    private void setTextEmpty(final JTextField text) {
        text.setText("");
    }

    /**
     * Constructor for SkiCenterGUI.
     * @throws IOException
     *              Exc IOException
     */
    public SkiCenterGUI() throws IOException {
        super();
        this.ci = Controller.getController();
        this.panelBasicObj = new PanelBaseImpl();
        this.panelLoginAdminObj = new PanelLoginAdminImpl();
        this.panelUserOneObj = new PanelUserOneImpl();
        this.panelSkipassObj = new PanelSkipassImpl();
        this.panelAdminOneObj = new PanelAdminOneImpl();
        this.panelBuyObj = new PanelBuyImpl();
        this.panelRentObj = new PanelRentImpl();
        this.panelInstructorObj = new PanelInstructorImpl();
        this.panelStorageObj = new PanelStorageImpl();
        this.panelTableObj = new PanelTableImpl();
        this.panelCartObj = new PanelCartImpl();
        this.panelPurchaseObj = new PanelPurchaseImpl();
        this.panelBuyDetailsObj = new PanelBuyDetailsImpl();
        this.panelRentDetailsObj = new PanelRentDetailsImpl();
        this.panelStorageDetailsObj = new PanelStorageDetailsImpl();

        panelBasicObj.getPanelBase().add(panelBasicObj.getPanelWelcome());
        panelBasicObj.getPanelWelcome().setVisible(true);

        ///////////////PARTE UTENTE/////////////////
        //MENU USER
        panelBasicObj.getBtnUserPanel().addActionListener(e-> {
                switchPanel(panelBasicObj.getPanelWelcome(), panelUserOneObj.getPanelUserOne());
        });
        //DA MENU USER A PANEL WELCOME
        panelUserOneObj.getBtnPrev().addActionListener(e-> {
                switchPanel(panelUserOneObj.getPanelUserOne(), panelBasicObj.getPanelWelcome());
        });
        //PAGINA DI SKIPASS
        panelUserOneObj.getBtnSkipass().addActionListener(e-> {
                switchPanel(panelUserOneObj.getPanelUserOne(), panelSkipassObj.getPanelSkipass());
        });
        //DA SKIPASS A MENU USER
        panelSkipassObj.getBtnPrev().addActionListener(e-> {
                switchPanel(panelSkipassObj.getPanelSkipass(), panelUserOneObj.getPanelUserOne());
        });
        //PAGINA DI ACQUISTI
        panelUserOneObj.getBtnBuy().addActionListener(e-> {
                switchPanel(panelUserOneObj.getPanelUserOne(), panelBuyObj.getPanelBuy());
        });
        //DA ACQUISTI A MENU USER
        panelBuyObj.getBtnPrev().addActionListener(e-> {
                switchPanel(panelBuyObj.getPanelBuy(), panelUserOneObj.getPanelUserOne());
        });
        //DETTAGLIO ACQUISTI
        for (final JButton jb : panelBuyObj.getBtnObj().keySet()) {
            jb.addActionListener(e-> {
                    panelBuyDetailsObj.setObject(panelBuyObj.getBtnObj().get(jb));
                    switchPanel(panelBuyObj.getPanelBuy(), panelBuyDetailsObj.getPanelBuyDetails());
                    panelBuyDetailsObj.addDetails();
            });
        }
        //DA DETTAGLIO ACQUISTI A MENU
        panelBuyDetailsObj.getBtnPrev().addActionListener(e-> {
                panelBuyDetailsObj.resetObject();
                panelBuyDetailsObj.removeDetails();
                switchPanel(panelBuyDetailsObj.getPanelBuyDetails(), panelBuyObj.getPanelBuy());
        });
        //PAGINA DI NOLEGGIO
        panelUserOneObj.getBtnRent().addActionListener(e-> {
                switchPanel(panelUserOneObj.getPanelUserOne(), panelRentObj.getPanelRent());
        });
        //DA NOLEGGIO A MENU USER
        panelRentObj.getBtnPrev().addActionListener(e-> {
                switchPanel(panelRentObj.getPanelRent(), panelUserOneObj.getPanelUserOne());
        });
        //DETTAGLIO NOLEGGIO
        for (final JButton jb : panelRentObj.getBtnObj().keySet()) {
            jb.addActionListener(e-> {
                    panelRentDetailsObj.setObject(panelRentObj.getBtnObj().get(jb));
                    switchPanel(panelRentObj.getPanelRent(), panelRentDetailsObj.getPanelRentDetails());
                    panelRentDetailsObj.addDetails();
            });
        }
        //TORNA ALLA PAGINA DI NOLEGGIO
        panelRentDetailsObj.getBtnPrev().addActionListener(e-> {
                panelRentDetailsObj.resetObject();
                panelRentDetailsObj.removeDetails();
                switchPanel(panelRentDetailsObj.getPanelRentDetails(), panelRentObj.getPanelRent());
        });
        //PAGINA MAESTRO
        panelUserOneObj.getBtnInstructor().addActionListener(e-> {
                switchPanel(panelUserOneObj.getPanelUserOne(), panelInstructorObj.getPanelInstructor());
        });
        //DA PAGINA MAESTRO A MENU
        panelInstructorObj.getBtnPrev().addActionListener(e-> {
                switchPanel(panelInstructorObj.getPanelInstructor(), panelUserOneObj.getPanelUserOne());
        });
        //PAGINA DI DEPOSITO
        panelUserOneObj.getBtnStorage().addActionListener(e-> {
                switchPanel(panelUserOneObj.getPanelUserOne(), panelStorageObj.getPanelStorage());
        });
        //DA DEPOSITO AL MENU USER
        panelStorageObj.getBtnPrev().addActionListener(e-> {
                switchPanel(panelStorageObj.getPanelStorage(), panelUserOneObj.getPanelUserOne());
        });
        //DETTAGLIO DEPOSITO
        for (final JButton jb : panelStorageObj.getBtnObj().keySet()) {
            jb.addActionListener(e-> {
                    panelStorageDetailsObj.setObject(panelStorageObj.getBtnObj().get(jb));
                    switchPanel(panelStorageObj.getPanelStorage(), panelStorageDetailsObj.getPanelStorageDetails());
                    panelStorageDetailsObj.addDetails();
            });
        }
        //DA DETTAGLIO DEPOSITO A MENU DEPOSITO
        panelStorageDetailsObj.getBtnPrev().addActionListener(e-> {
                panelStorageDetailsObj.resetObject();
                panelStorageDetailsObj.removeDetails();
                switchPanel(panelStorageDetailsObj.getPanelStorageDetails(), panelStorageObj.getPanelStorage());
        });
        //VAI AL CARRELLO
        panelUserOneObj.getBtnCart().addActionListener(e-> {
                printCart();
                switchPanel(panelUserOneObj.getPanelUserOne(), panelCartObj.getPanelCart());
        });
        panelSkipassObj.getBtnCart().addActionListener(e-> {
                printCart();
                switchPanel(panelSkipassObj.getPanelSkipass(), panelCartObj.getPanelCart());
        });
        panelRentObj.getBtnCart().addActionListener(e-> {
                printCart();
                switchPanel(panelRentObj.getPanelRent(), panelCartObj.getPanelCart());
        });
        panelRentDetailsObj.getBtnCart().addActionListener(e-> {
                printCart();
                panelRentDetailsObj.removeDetails();
                switchPanel(panelRentDetailsObj.getPanelRentDetails(), panelCartObj.getPanelCart());
        });
        panelBuyObj.getBtnCart().addActionListener(e-> {
                printCart();
                switchPanel(panelBuyObj.getPanelBuy(), panelCartObj.getPanelCart());
        });
        panelBuyDetailsObj.getBtnCart().addActionListener(e-> {
                printCart();
                panelBuyDetailsObj.removeDetails();
                switchPanel(panelBuyDetailsObj.getPanelBuyDetails(), panelCartObj.getPanelCart());
        });
        panelInstructorObj.getBtnCart().addActionListener(e-> {
                printCart();
                switchPanel(panelInstructorObj.getPanelInstructor(), panelCartObj.getPanelCart());
        });
        panelStorageObj.getBtnCart().addActionListener(e-> {
                printCart();
                switchPanel(panelStorageObj.getPanelStorage(), panelCartObj.getPanelCart());
        });
        panelStorageDetailsObj.getBtnCart().addActionListener(e-> {
                printCart();
                panelStorageDetailsObj.removeDetails();
                switchPanel(panelStorageDetailsObj.getPanelStorageDetails(), panelCartObj.getPanelCart());
        });
        panelCartObj.getBtnPrev().addActionListener(e-> {
                panelCartObj.deleteTable();
                switchPanel(panelCartObj.getPanelCart(), panelUserOneObj.getPanelUserOne());
        });
        //ELIMINA TUTTO IL CARRELLO
        panelCartObj.getBtnDeleteCart().addActionListener(e-> {
                ci.removeAllOperations();
                panelCartObj.deleteTable();
                printCart();
                panelCartObj.addLabelPrice("0,00");
        });
        //ELIMINA UN'OPERAZIONE
        panelCartObj.getBtnDeleteOperation().addActionListener(e-> {
                try {
                    ci.removeOperation(panelCartObj.getOperationDelete());
                } catch (IllegalArgumentException exc) {
                    JOptionPane.showMessageDialog(null, "L'operazione inserita non è presente nel carrello");
                }
                panelCartObj.deleteTable();
                printCart();
                panelCartObj.getOp().setText("");
        });
        //VAI ALLA PAGINA DI ACQUISTO CON LOGIN
        panelCartObj.getBtnFinishOp().addActionListener(e-> {
                enableComp();
                switchPanel(panelCartObj.getPanelCart(), panelPurchaseObj.getPanelPurchase());
        });
        //DA PAGINA DI ACQUISTO A CARRELLO
        panelPurchaseObj.getBtnPrev().addActionListener(e-> {
                switchPanel(panelPurchaseObj.getPanelPurchase(), panelCartObj.getPanelCart());
                enableComp();
                setTextEmpty(panelPurchaseObj.getTextUser());
                setTextEmpty(panelPurchaseObj.getTextPass());
                setTextEmpty(panelPurchaseObj.getTextUser2());
                setTextEmpty(panelPurchaseObj.getTextPass2());
                setTextEmpty(panelPurchaseObj.getTextName());
                setTextEmpty(panelPurchaseObj.getTextSurname());
                setTextEmpty(panelPurchaseObj.getTextCard());
                setTextEmpty(panelPurchaseObj.getTextOwner());
                setTextEmpty(panelPurchaseObj.getTextDate());
                setTextEmpty(panelPurchaseObj.getTextCvc());
                panelPurchaseObj.deleteLabel();
        });
        //LOGIN UTENTE REGISTRATO
        panelPurchaseObj.getBtnLogin().addActionListener(e-> {
                boolean login = true;
                try {
                    ci.loginUser(panelPurchaseObj.getTextUser().getText(), panelPurchaseObj.getTextPass().getText());
                } catch (IllegalArgumentException exc) {
                    JOptionPane.showMessageDialog(null, "Username e password devono avere da 5 a 8 caratteri");
                    login = false;
                } catch (UnsupportedOperationException exc1) {
                    JOptionPane.showMessageDialog(null, "Le credenziali non corrispondono a nessun utente");
                    login = false;
                }
                if (login) {
                    JOptionPane.showMessageDialog(null, "Login effettuato correttamente");
                    panelPurchaseObj.addLabel(ci.getCurrentUser().get());
                    disableComp();
                } else {
                    setTextEmpty(panelPurchaseObj.getTextUser());
                    setTextEmpty(panelPurchaseObj.getTextPass());
                }
        });
        //REGISTRAZIONE NUOVO UTENTE
        panelPurchaseObj.getBtnReg().addActionListener(e-> {
                boolean login = true;
                try {
                    ci.registerUser(new Pair<String, String>(panelPurchaseObj.getTextUser2().getText(), panelPurchaseObj.getTextPass2().getText()), 
                            new Pair<String, String>(panelPurchaseObj.getTextName().getText(), panelPurchaseObj.getTextSurname().getText()));
                } catch (IllegalArgumentException exc) {
                    JOptionPane.showMessageDialog(null, "Le credenziali devono essere stringhe da 5 a 8 caratteri");
                    login = false;
                } catch (IllegalStateException exc1) {
                    JOptionPane.showMessageDialog(null, "le credenziali inserite corrispondono a un altro utente già registrato");
                    login = false;
                } catch (UnsupportedOperationException exc2) {
                    System.err.println("Utente non memorizzato correttamente su file");
                }
                if (login) {
                    JOptionPane.showMessageDialog(null, "Registrazione effettuata correttamente");
                    disableComp();
                    panelPurchaseObj.addLabel(ci.getCurrentUser().get());
                }
        });
        //PAGAMENTO
        panelPurchaseObj.getBtnPay().addActionListener(e-> {
                boolean pay = true;
                try {
                    ci.pay(new Pair<String, String>(panelPurchaseObj.getTextOwner().getText(), panelPurchaseObj.getTextCard().getText()),
                            new Pair<String, String>(panelPurchaseObj.getTextDate().getText(), panelPurchaseObj.getTextCvc().getText()));
                } catch (IllegalArgumentException exc) {
                    pay = false;
                    JOptionPane.showMessageDialog(null, "Non è stata inserita una carta di credito valida");
                }
                if (pay) {
                    try {
                        ci.completeOperations();
                    } catch (IllegalArgumentException exc) {
                        System.err.println("Operazioni non memorizzate correttamente su file");
                    }
                    JOptionPane.showMessageDialog(null, "Pagamento effettuato con successo");
                    switchPanel(panelPurchaseObj.getPanelPurchase(), panelUserOneObj.getPanelUserOne());
                    panelCartObj.deleteTable();
                    setTextEmpty(panelPurchaseObj.getTextUser());
                    setTextEmpty(panelPurchaseObj.getTextPass());
                    setTextEmpty(panelPurchaseObj.getTextUser2());
                    setTextEmpty(panelPurchaseObj.getTextPass2());
                    setTextEmpty(panelPurchaseObj.getTextName());
                    setTextEmpty(panelPurchaseObj.getTextSurname());
                    setTextEmpty(panelPurchaseObj.getTextCard());
                    setTextEmpty(panelPurchaseObj.getTextOwner());
                    setTextEmpty(panelPurchaseObj.getTextDate());
                    setTextEmpty(panelPurchaseObj.getTextCvc());
                    panelPurchaseObj.deleteLabel();
                }
        });
        ////////////////AMMINISTRATORE/////////////////
        //PANNELLO LOGIN AMMINISTRATORE
        panelBasicObj.getBtnAdminPanel().addActionListener(e-> {
                if (ci.getCurrentAdmin().isPresent()) {
                    switchPanel(panelBasicObj.getPanelWelcome(), panelAdminOneObj.getPanelAdminOne());
                } else {
                    switchPanel(panelBasicObj.getPanelWelcome(), panelLoginAdminObj.getLoginAdminPanel());
                }
        });
        //EFFETTUA LOGIN E VA AL PANNELLO AMMINISTRATORE
        panelLoginAdminObj.getBtnLogin().addActionListener(e-> {
                boolean login = true;
                try {
                    ci.loginAdmin(panelLoginAdminObj.getUsername().getText(), panelLoginAdminObj.getPassword().getText());
                } catch (final IllegalArgumentException exc) {
                    JOptionPane.showMessageDialog(null, "Username e password devono avere da 5 a 8 caratteri");
                    login = false;
                } catch (final UnsupportedOperationException exc) {
                    JOptionPane.showMessageDialog(null, "Le credenziali non corrispondono a nessun amministratore");
                    login = false;
                }
                if (login) {
                    JOptionPane.showMessageDialog(null, "Login effettuato correttamente");
                    panelAdminOneObj.setLabel(ci.getTotalGain());
                    switchPanel(panelLoginAdminObj.getLoginAdminPanel(), panelAdminOneObj.getPanelAdminOne());
                    panelAdminOneObj.addLabelAmm(ci.getCurrentAdmin().get());
                }
                panelLoginAdminObj.getUsername().setText("");
                panelLoginAdminObj.getPassword().setText("");
        });
        //EFFETTUA LOGOUT
        panelAdminOneObj.getBtnLogout().addActionListener(e-> {
                ci.logoutAdmin();
                switchPanel(panelAdminOneObj.getPanelAdminOne(), panelBasicObj.getPanelWelcome());
        });
        //TORNA AL MENU
        panelLoginAdminObj.getBtnPrev().addActionListener(e-> {
                switchPanel(panelLoginAdminObj.getLoginAdminPanel(), panelBasicObj.getPanelWelcome());
        });
        //POST LOGIN TORNA AL MENU
        panelAdminOneObj.getBtnPrev().addActionListener(e-> {
                switchPanel(panelAdminOneObj.getPanelAdminOne(), panelBasicObj.getPanelWelcome());
        });
        //VAI ALLA TABELLA DELLE OPERAZIONI
        panelAdminOneObj.getBtnAllOperation().addActionListener(e-> {
                for (final Integer elem : ci.getAllOperations()) {
                    panelTableObj.addTableRow(new Pair<>(elem, ci.getOperations().get(elem).getX()),
                            ci.getOperations().get(elem).getY().getDetail(),
                            ci.getOperations().get(elem).getY().getInfo(), ci.getOperationGain(elem));
                }
                switchPanel(panelAdminOneObj.getPanelAdminOne(), panelTableObj.getPanelTable());
        });
        panelTableObj.getBtnPrev().addActionListener(e-> {
                switchPanel(panelTableObj.getPanelTable(), panelAdminOneObj.getPanelAdminOne());
                panelTableObj.deleteTable();
        });
        //TABELLA DI OPERAZIONI PER UTENTE
        panelAdminOneObj.getBtnOperationUser().addActionListener(e-> {
                String input;
                input = JOptionPane.showInputDialog("Digita l'username");
                if (input != null) {
                    if (ci.getUserOperations(input).isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Utente non trovato");
                    } else {
                        JOptionPane.showMessageDialog(null, "Utente trovato");
                        for (final Integer elem : ci.getUserOperations(input)) {
                            panelTableObj.addTableRow(new Pair<>(elem, ci.getOperations().get(elem).getX()),
                                    ci.getOperations().get(elem).getY().getDetail(),
                                    ci.getOperations().get(elem).getY().getInfo(), ci.getOperationGain(elem));
                         }
                        switchPanel(panelAdminOneObj.getPanelAdminOne(), panelTableObj.getPanelTable());
                    }
                }
        });
        //TABELLA OPERAZIONI PER TIPO
        panelAdminOneObj.getBtnOperationType().addActionListener(e-> {
                final List<Object> values = new ArrayList<>();
                for (final String type : ci.getOperationTypes(ci.getAllOperations())) {
                    values.add(type);
                }
                final Object input = JOptionPane.showInputDialog(null, "Scegli l'operazione da visualizzare", "Operazioni", JOptionPane.INFORMATION_MESSAGE, null, values.toArray(), values.get(0));
                if (input != null) {
                    for (final Integer elem : ci.getTypeOperations(input.toString())) {
                        panelTableObj.addTableRow(new Pair<>(elem, ci.getOperations().get(elem).getX()),
                                ci.getOperations().get(elem).getY().getDetail(),
                                ci.getOperations().get(elem).getY().getInfo(), ci.getOperationGain(elem));
                    }
                    switchPanel(panelAdminOneObj.getPanelAdminOne(), panelTableObj.getPanelTable());
                }
        });
        //VISUALIZZA OPERAZIONI PER TIPO E UTENTE
        panelAdminOneObj.getBtnOperationUserType().addActionListener(e-> {
                String input;
                input = JOptionPane.showInputDialog("Digita l'username");
                if (input != null) {
                    if (ci.getUserOperations(input).isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Utente non trovato");
                    } else {
                        final List<Object> values = new ArrayList<>();
                        for (final String type : ci.getOperationTypes(ci.getUserOperations(input))) {
                            values.add(type);
                        }
                        final Object input2 = JOptionPane.showInputDialog(null, "Scegli l'operazione da visualizzare", "Operazioni", JOptionPane.INFORMATION_MESSAGE, null, values.toArray(), values.get(0));
                        if (input2 != null) {
                            for (final Integer elem : ci.getUserAndTypeOperations(input, input2.toString())) {
                                panelTableObj.addTableRow(new Pair<>(elem, ci.getOperations().get(elem).getX()),
                                        ci.getOperations().get(elem).getY().getDetail(),
                                        ci.getOperations().get(elem).getY().getInfo(), ci.getOperationGain(elem));
                            }
                            switchPanel(panelAdminOneObj.getPanelAdminOne(), panelTableObj.getPanelTable());
                        }
                    }
                }
        });
        this.setLocationRelativeTo(null);
        this.setTitle("SKICENTER");
        this.setResizable(false);
        this.setSize(FrameSize.WIDTH.getValue(), FrameSize.HEIGHT.getValue());
        this.setLocationByPlatform(true);
        this.getContentPane().add(panelBasicObj.getPanelBase());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
    /**
     * main class.
     * @param args
     *          args
     * @throws IOException
     *          exc
     */
    public static void main(final String[] args) throws IOException {
        new SkiCenterGUI();
    }
}