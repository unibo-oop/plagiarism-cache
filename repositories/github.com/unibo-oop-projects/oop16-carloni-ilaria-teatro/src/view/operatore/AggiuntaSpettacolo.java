package view.operatore;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import control.GestioneSpettacolo;
import model.Spettacolo;

/**
 * Classe che gestisce l'aggiunta di uno spettacolo
 * 
 * @author Ilaria Carloni
 * 
 */
public class AggiuntaSpettacolo extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel pannello;
	private JTextField nomeSp;
	private JTextField compagnia;
	private JTextField data;
	private JTextField prezzo;

	/**
	 * Crea il frame
	 */
	public AggiuntaSpettacolo() {
		this.setResizable(false);
		this.setTitle("AGGIUNGI SPETTACOLO");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100, 100, 450, 250);
		pannello = new JPanel();
		pannello.setBorder(new EmptyBorder(7, 7, 7, 7));
		this.setContentPane(pannello);
		pannello.setLayout(null);

		JLabel labNomeSp = new JLabel("Nome Spettacolo: ");
		labNomeSp.setBounds(10, 14, 122, 14);
		pannello.add(labNomeSp);

		nomeSp = new JTextField();
		nomeSp.setBounds(152, 11, 248, 20);
		pannello.add(nomeSp);
		nomeSp.setColumns(10);

		JLabel labCompagnia = new JLabel("Compagnia Attori: ");
		labCompagnia.setBounds(10, 45, 122, 14);
		pannello.add(labCompagnia);

		compagnia = new JTextField();
		compagnia.setBounds(152, 42, 248, 20);
		pannello.add(compagnia);
		compagnia.setColumns(10);

		JLabel labData = new JLabel("Data: ");
		labData.setBounds(10, 76, 122, 14);
		pannello.add(labData);

		data = new JTextField();
		data.setBounds(152, 73, 248, 20);
		pannello.add(data);
		data.setColumns(10);

		JLabel labPrezzo = new JLabel("Prezzo: ");
		labPrezzo.setBounds(10, 107, 122, 14);
		pannello.add(labPrezzo);

		prezzo = new JTextField();
		prezzo.setBounds(152, 104, 248, 20);
		pannello.add(prezzo);
		prezzo.setColumns(10);

		JButton bottAggiungi = new JButton("Aggiungi");
		bottAggiungi.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String nome;
				String comp;
				DateFormat dataS = new SimpleDateFormat("dd/MM/yyyy");
				float prezzoS;

				nome = nomeSp.getText();
				try {
					prezzoS = Float.parseFloat(prezzo.getText());
				} catch (Exception exc) {
					JOptionPane.showMessageDialog(AggiuntaSpettacolo.this,
							"Inserire un prezzo valido!");
					return;
				}

				Date dat = null;
				try {
					dat = dataS.parse(data.getText());
				} catch (ParseException e1) {
					JOptionPane.showMessageDialog(AggiuntaSpettacolo.this,
							"Inserire una data valida");
					return;
				}

				comp = compagnia.getText();

				if (nomeSp.equals("") || compagnia.equals("")
						|| data.equals("") || prezzo.equals("")) {
					JOptionPane.showMessageDialog(AggiuntaSpettacolo.this,
							"Riempire tutti i campi!");
				} else {
					new MainFrameOperatore().setVisible(true);
					GestioneSpettacolo.istanzaSpettacolo().aggiungiSpett(
							new Spettacolo(nome, dat, prezzoS, comp));
					chiudi();
				}
			}
		});
		bottAggiungi.setBounds(152, 155, 120, 45);
		pannello.add(bottAggiungi);

		JButton btnIndietro = new JButton("Indietro");
		btnIndietro.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new MainFrameOperatore().setVisible(true);
				chiudi();
			}
		});
		btnIndietro.setBounds(283, 155, 120, 45);
		pannello.add(btnIndietro);
	}

	/**
	 * Chiude il frame
	 */
	public void chiudi() {
		setVisible(false);
		dispose();
	}
}
