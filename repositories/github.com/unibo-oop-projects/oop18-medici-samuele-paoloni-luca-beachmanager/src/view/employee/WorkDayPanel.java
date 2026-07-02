package view.employee;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import view.umbrella.UmbrellaInfoDialog;

/**
 * Pannello principale per la gestione del calendario dei turni dei dipendenti
 * @author Samuele Medici, samuele.medici2@studio.unibo.it (Mat. 0000718877 )
 *
 */
public class WorkDayPanel extends JPanel {

	/**
	 * Unique serial ID
	 */
	private static final long serialVersionUID = -7427704753064243611L;

	// Mese e anno selezionato
	private int selectedMonth;
	private int selectedYear;

	private JComboBox<String> monthComboBox = new JComboBox<String>();
	private JComboBox<Integer> yearComboBox = new JComboBox<Integer>();

	private Calendar calendar = new GregorianCalendar();

	// valore dell'anno ad oggi, non è final poiché ho un set ristretto di mesi
	private final int currentYear = calendar.get(Calendar.YEAR);

	// Data e mese corrente
	private int currentMonth = calendar.get(Calendar.MONTH);
	private int currentDay = calendar.get(Calendar.DAY_OF_MONTH);

	// Lista dei mesi
	private String[] months = { "January", "February", "March", "April", "May", "June", "July", "August", "September",
			"October", "November", "December" };

	// Matrice 6 x 7 che include i bottoni dei giorni
	private JButton[][] daysButton = new JButton[6][7];

	private final int daysOfMonths[] = { 31, 28, 31, 30, /* jan feb mar apr */
			31, 30, 31, 31, /* may jun jul aug */
			30, 31, 30, 31 /* sep oct nov dec */
	};

	/**
	 * Costruttore
	 */
	public WorkDayPanel() {
		super();

		this.setLayout(new BorderLayout());

		this.selectedMonth = this.currentMonth;
		this.selectedYear = this.currentYear;

		// Pannello per la selezione mese e anno
		JPanel dayPanel = this.buildDayPanel();
		JPanel selectionPanel = this.buildSelectionPanel();

		this.calculateDays();

		this.add(BorderLayout.NORTH, selectionPanel);
		this.add(BorderLayout.CENTER, dayPanel);

	}

	/**
	 * 
	 * @return pannello per la selezione del mese e anno
	 */
	private JPanel buildSelectionPanel() {
		JPanel selectionPanel = new JPanel();

		// Combo box per selezionare il mese
		for (int i = 0; i < this.months.length; i++) {
			this.monthComboBox.addItem(this.months[i]);
			this.monthComboBox.setSelectedItem(months[this.selectedMonth]);

			this.monthComboBox.addActionListener(e -> {
				// Al click Ottengo l'indice della combo box e lo salvo nel mese selezionato
				int selectedIndex = this.monthComboBox.getSelectedIndex();

				if (selectedIndex >= 0) {
					this.selectedMonth = selectedIndex;
				}
				calculateDays();
			});
		}

		// Combo box per selezionare l'anno ( ho messo un range di 3 anni )
		for (int i = this.currentYear - 3; i < (this.currentYear + 1); i++) {
			this.yearComboBox.addItem(i);
			this.yearComboBox.setSelectedItem(this.currentYear);

			this.yearComboBox.addActionListener(e -> {
				int selectedYear = this.yearComboBox.getSelectedIndex();
				this.selectedYear = this.currentYear - (3 - selectedYear);

				calculateDays();
			});

		}

		selectionPanel.add(this.monthComboBox);
		selectionPanel.add(this.yearComboBox);

		return selectionPanel;
	}

	/**
	 * 
	 * @return griglia delle giornate
	 */
	private JPanel buildDayPanel() {

		JPanel dayPanel = new JPanel();
		dayPanel.setLayout(new GridLayout(7, 7));

		ActionListener dateSetter = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String num = e.getActionCommand();
				if (!num.equals("")) {
					openWorkDayInfoDialog(e, Integer.parseInt(num));
				}
			}
		};

		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 7; j++) {
				dayPanel.add(this.daysButton[i][j] = new JButton(""));
				this.daysButton[i][j].addActionListener(dateSetter);
			}
		}

		return dayPanel;
	}

	/**
	 * Funzione utilizzata per il ricalcolo dei giorni all'interno del mese
	 */
	private void calculateDays() {

		// setto il calendario al giorno corrente
		this.calendar = new GregorianCalendar(this.selectedYear, this.selectedMonth, this.currentDay);

		// Giorni del mese selezionato
		int daysOfCurrentMonth = this.daysOfMonths[this.selectedMonth];

		if (this.selectedMonth == 1 && this.isLeapYear()) { // Se è Febbraio e sono in anno bisestile aggiungo un giorno
			++daysOfCurrentMonth;
		}

		// Ottengo i giorni da lasciare vuoti
		int gapFromStart = new GregorianCalendar(this.currentMonth, this.currentYear, 1).get(Calendar.DAY_OF_WEEK) - 1;

		// Setto i primi giorni vuoti
		for (int i = 0; i < gapFromStart; i++) {
			this.daysButton[0][i].setText("");
			this.daysButton[0][i].setEnabled(false);
		}

		// Setto i giorni con il numero corretto
		for (int i = 1; i <= daysOfCurrentMonth; i++) {

			int leftIndex = (gapFromStart + i - 1) / 7;
			int rightIndex = (gapFromStart + i - 1) % 7;

			this.daysButton[leftIndex][rightIndex].setText(Integer.toString(i));
			this.daysButton[leftIndex][rightIndex].setEnabled(true);
		}

		// Gli ultimi giorni setto a vuoti e disabilitati
		for (int i = gapFromStart + daysOfCurrentMonth; i < 6 * 7; i++) {
			this.daysButton[(i) / 7][(i) % 7].setText("");
			this.daysButton[(i) / 7][(i) % 7].setEnabled(false);
		}

	}

	/**
	 * Bisestile se l'anno è divisibile per 4 e non divisibile per cento oppure se è
	 * divisibile per 400
	 * 
	 * @return vero se l'anno corrente è bisestile
	 */
	private boolean isLeapYear() {
		return ((this.selectedYear % 4 == 0 && this.selectedYear % 100 != 0) || this.selectedYear % 400 == 0);
	}

	public void openWorkDayInfoDialog(ActionEvent e, int selectedDay) {
		Component component = (Component) e.getSource();
		JFrame frame = (JFrame) SwingUtilities.getRoot(component);

		WorkDayInfoDialog wdid = new WorkDayInfoDialog(frame, selectedDay, this.selectedMonth, this.selectedYear);

		wdid.setVisibility(true);

	}
}
