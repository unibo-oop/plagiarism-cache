package view.umbrella;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import controller.Booking;

/**
 * Classe che permette l'acquisto di stendini e ombrelloni da parte per il
 * cliente
 * 
 * @author Samuele Medici, samuele.medici2@studio.unibo.it (Mat. 0000718877 )
 * 
 */
public class EquipmentPaymentPanel extends JPanel {

	/**
	 * Serial UID
	 */
	private static final long serialVersionUID = 1777256332046557165L;

	// Controller per l'affitto dell'equipaggiamento da spiaggia
	private Booking equipmentBooking = new Booking();

	// Costo finale
	private double totalAmount;
	private JLabel amountText = new JLabel("");

	private JTextField clientName = new JTextField(30);
	// Bottone per affitare gli stendini e/o stendini
	private JButton bookingButton;
	// Flag per l'abilitazione del bottone
	private boolean enableBookingButton = false;

	// Valori per gli spinner
	private final int minimumValue = 0;
	private final int maximumValue = 4;
	private final int step = 1;
	
	// Pannello per il totale dei costi
	private JPanel totalPanel;
	
	// Spinner model
	private SpinnerNumberModel umbrellaModel = new SpinnerNumberModel(this.minimumValue, this.minimumValue,
			this.maximumValue, this.step);
	private SpinnerNumberModel cotModel = new SpinnerNumberModel(this.minimumValue, this.minimumValue,
			this.maximumValue, this.step);

	// Spinner
	private JSpinner umbrellaSpinner = new JSpinner(this.umbrellaModel);
	private JSpinner cotSpinner = new JSpinner(this.cotModel);

	public EquipmentPaymentPanel() {
		// Layout in una colonna
		super();
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		this.bookingButton = new JButton("Affitta");
		this.bookingButton.addActionListener(e -> {
			this.totalAmount = this.equipmentBooking.calculateCost();
			this.totalPanel.setVisible(true);
			this.amountText.setText(Double.toString(this.totalAmount));
		});
		this.bookingButton.setEnabled(this.enableBookingButton);

		final DocumentListener documentListener = new DocumentListener() {

			@Override
			public void removeUpdate(DocumentEvent arg0) {
				checkEnablingStatus();
			}

			@Override
			public void insertUpdate(DocumentEvent arg0) {
				checkEnablingStatus();
			}

			@Override
			public void changedUpdate(DocumentEvent arg0) {
				checkEnablingStatus();
			}

		};

		// Aggiungo listener per la modifica del testo 
		this.clientName.getDocument().addDocumentListener(documentListener);
		
		this.umbrellaSpinner.addChangeListener(e -> {
			this.checkEnablingStatus();
		});
		this.cotSpinner.addChangeListener(e -> {
			this.checkEnablingStatus();
		});

		JPanel umbrellaPanel = new JPanel();
		umbrellaPanel.add(new JLabel("Numero di ombrelli: "));
		umbrellaPanel.add(this.umbrellaSpinner);

		JPanel cotPanel = new JPanel();
		cotPanel.add(new JLabel("Numero di stendini: "));
		cotPanel.add(this.cotSpinner);

		JPanel clientPanel = new JPanel();
		clientPanel.add(new JLabel("Nome cliente: "));
		clientPanel.add(this.clientName);

		this.totalPanel = new JPanel();
		this.totalPanel.add(new JLabel("Totale: "));
		this.totalPanel.add(this.amountText);
		
		JButton okButton = new JButton("Ok");
		okButton.addActionListener(e -> {
			this.clientName.setText("");	
			this.totalPanel.setVisible(false);
		});
		this.totalPanel.add(okButton);
		
		this.totalPanel.setVisible(false);

		this.add(umbrellaPanel);
		this.add(cotPanel);
		this.add(clientPanel);

		this.add(this.bookingButton);

		this.add(totalPanel);

	}

	/**
	 * Metodo che controlla stato degli input per abilitare il bottone di calcolo
	 */
	private void checkEnablingStatus() {
		int umbrellaValue = (Integer) this.umbrellaSpinner.getValue();
		int cotValue = (Integer) this.cotSpinner.getValue();
		String clientValue = this.clientName.getText();

		// Il bottone è abilitato se il nome cliente non è vuoto e se ha almeno un
		// ombrello o uno stendino
		this.enableBookingButton = (!clientValue.isEmpty() && umbrellaValue != 0);
		this.bookingButton.setEnabled(this.enableBookingButton);
	}

}
