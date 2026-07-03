package view.panels;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import view.panels.interfaces.PanelPurchase;
/**
 * 
 * Class of panelPurchase.
 *
 */
public class PanelPurchaseImpl extends JPanel implements PanelPurchase {

    private static final long serialVersionUID = 1L;
    private final JTextField username;
    private final JTextField password;
    private final JTextField name;
    private final JTextField surname;
    private final JTextField username2;
    private final JTextField password2;
    private final JTextField owner;
    private final JTextField card;
    private final JTextField date;
    private final JTextField cvc;
    private final JButton btnLogin;
    private final JButton btnReg;
    private final JButton btnPay;
    private final JButton btnRetMenu;
    private final JLabel labWelcome;
    private final JLabel labPurchase;
    private static final int FONT_LAB = FrameSize.WIDTH.getValue() / 40;

  //COLORE
    private static final int C1 = 171;
    private static final int C2 = 205;
    private static final int C3 = 239;

    /**
     * Constructor of panelPurchase.
     */
    public PanelPurchaseImpl() {
        super();
        final Container cont = new Container();
        cont.setLayout(new GridLayout(2, 1));

        //PANNELLI PRINCIPALI
        final JPanel pan1 = new JPanel();
        final JPanel pan2 = new JPanel();
        final JPanel pan3 = new JPanel();

        //CONTAINER PER PARTE SUPERIORE
        final Container x = new Container();
        x.setLayout(new GridLayout(1, 2));
        x.add(pan1);
        x.add(pan2);

        //CONTAINER PARTE INFERIORE
        final Container y = new Container();
        y.setLayout(new GridLayout(1, 1));
        y.add(pan3);

        pan1.setLayout(new GridLayout(8, 2));
        pan2.setLayout(new GridLayout(8, 2));
        pan3.setLayout(new GridLayout(8, 2));
        final TitledBorder titleBorder = new TitledBorder(new LineBorder(Color.BLACK), "Sei già registrato? Accedi");
        titleBorder.setTitleJustification(TitledBorder.CENTER);
        titleBorder.setTitleColor(Color.RED);
        titleBorder.setTitleFont(new Font(Font.SANS_SERIF, Font.BOLD, FONT_LAB));
        pan1.setBorder(titleBorder);
        final TitledBorder titleBorder2 = new TitledBorder(new LineBorder(Color.BLACK), "Nuovo utente? Registrati ora");
        titleBorder2.setTitleColor(Color.RED);
        titleBorder2.setTitleJustification(TitledBorder.CENTER);
        titleBorder2.setTitleFont(new Font(Font.SANS_SERIF, Font.BOLD, FONT_LAB));
        pan2.setBorder(titleBorder2);
        final TitledBorder titleBorder3 = new TitledBorder(new LineBorder(Color.BLACK), "Procedi con il pagamento");
        titleBorder3.setTitleJustification(TitledBorder.CENTER);
        titleBorder3.setTitleColor(Color.RED);
        titleBorder3.setTitleFont(new Font(Font.SANS_SERIF, Font.BOLD, FONT_LAB));
        pan3.setBorder(titleBorder3);

        //USERNAME
        final JLabel labUser = new JLabel("Username");
        pan1.add(labUser);
        this.username = new JTextField();
        pan1.add(username);

        final JLabel labUser2 = new JLabel("Username");
        pan2.add(labUser2);
        this.username2 = new JTextField();
        pan2.add(username2);
        //PASSWORD
        final JLabel labPass = new JLabel("Password");
        pan1.add(labPass);
        this.password = new JPasswordField();
        pan1.add(password);
        final JLabel labPass2 = new JLabel("Password");
        pan2.add(labPass2);
        this.password2 = new JPasswordField();
        pan2.add(password2);

        final JLabel emp = new JLabel("");
        pan1.add(emp);

        //NOME
        final JLabel labName = new JLabel("Nome");
        pan2.add(labName);
        this.name = new JTextField();
        pan2.add(name);

        //COGNOME
        final JLabel labSurname = new JLabel("Cognome");
        pan2.add(labSurname);
        this.surname = new JTextField();
        pan2.add(surname);

        final JLabel emp2 = new JLabel("");
        pan2.add(emp2);
        final JLabel emp3 = new JLabel("");
        pan2.add(emp3);

        //BOOTTONE LOGIN
        this.btnLogin = new JButton("Login");
        pan1.add(btnLogin);

        final JLabel lab = new JLabel("Attenzione: per accedere devi inserire");
        final JLabel lab1 = new JLabel("username e password da 5 a 8 caratteri");
        lab.setForeground(Color.blue);
        lab1.setForeground(Color.blue);
        pan1.add(lab);
        pan1.add(lab1);

        //BOTTONE REGISTRATI
        this.btnReg = new JButton("Registrati e accedi");
        pan2.add(btnReg);

        //LABEL BENVENUTO
        this.labWelcome = new JLabel("");
        this.labPurchase = new JLabel("");
        pan3.add(labWelcome);
        pan3.add(labPurchase);

        //CARTA
        final JLabel labCard = new JLabel("Numero carta (16 cifre)");
        pan3.add(labCard);
        this.card = new JTextField();
        pan3.add(card);

        //INTESTATARIO
        final JLabel labOwner = new JLabel("Nome intestatario");
        pan3.add(labOwner);
        this.owner = new JTextField();
        pan3.add(owner);

        //DATA
        final JLabel labDate = new JLabel("Data di scadenza (mm/aaaa)");
        pan3.add(labDate);
        this.date = new JTextField();
        pan3.add(date);

        //CVC
        final JLabel labCvc = new JLabel("CVC carta (3 cifre)");
        pan3.add(labCvc);
        this.cvc = new JTextField();
        pan3.add(cvc);

        final JLabel emp5 = new JLabel("");
        final JLabel emp6 = new JLabel("");
        pan3.add(emp5);
        pan3.add(emp6);

        //BOTTONE PAGA
        this.btnPay = new JButton("Paga ora");
        pan3.add(btnPay);
        //BOTTONE TORNA AL MENU
        this.btnRetMenu = new JButton("Pagina precedente");
        pan3.add(btnRetMenu);
        //SFONDO
        final Color bluette = new Color(C1, C2, C3);
        pan1.setBackground(bluette);
        pan2.setBackground(bluette);
        pan3.setBackground(bluette);
        this.setBackground(bluette);
        cont.add(x);
        cont.add(y);
        this.setLayout(new GridLayout(1, 1));
        this.add(cont);
        this.setVisible(false);
    }
    @Override
    public void addLabel(final String n) {
        this.labWelcome.setText("Benvenuto " + n + ", concludi il tuo acquisto");
        this.labWelcome.setForeground(Color.blue);
        this.labPurchase.setText("Inserisci i dati della tua carta di credito");
        this.labPurchase.setForeground(Color.blue);

    }
    @Override
    public void deleteLabel() {
        this.labWelcome.setText("");
        this.labPurchase.setText("");

    }
    @Override
    public JPanel getPanelPurchase() {
        return this;
    }
    @Override
    public JButton getBtnPrev() {
        return this.btnRetMenu;
    }
    @Override
    public JButton getBtnLogin() {
        return this.btnLogin;
    }
    @Override
    public JButton getBtnReg() {
        return this.btnReg;
    }
    @Override
    public String getName() {
        return this.name.getText();
    }
    @Override
    public JButton getBtnPay() {
        return this.btnPay;
    }
    @Override
    public JTextField getTextUser() {
        return this.username;
    }
    @Override
    public JTextField getTextPass() {
        return this.password;
    }
    @Override
    public JTextField getTextUser2() {
        return this.username2;
    }
    @Override
    public JTextField getTextPass2() {
        return this.password2;
    }
    @Override
    public JTextField getTextName() {
        return this.name;
    }
    @Override
    public JTextField getTextSurname() {
        return this.surname;
    }
    @Override
    public JTextField getTextCard() {
        return this.card;
    }
    @Override
    public JTextField getTextOwner() {
        return this.owner;
    }
    @Override
    public JTextField getTextDate() {
        return this.date;
    }
    @Override
    public JTextField getTextCvc() {
        return this.cvc;
    }
}
