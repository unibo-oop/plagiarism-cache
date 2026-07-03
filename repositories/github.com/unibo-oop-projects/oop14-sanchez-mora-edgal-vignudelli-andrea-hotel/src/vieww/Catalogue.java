/* Questa GUI permette dell'inserimento del catalogo, con tutte le informazioni relative alle precentuali di incremento 
 * in base periodo e al tipo di soggiorno, eventuali tariffe differenziate per bambini etc...
 * 
 */
package vieww;

import java.awt.Dimension;
import java.time.LocalDate;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import controller.Controller;
import model.Hotel;
import model.Pair;
import model.SeasonType;

// TODO: Auto-generated Javadoc
/**
 * The Class Catalogue.
 */
public class Catalogue extends JFrame {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The day price. */
	private final JLabel dayPrice;
	
	/** The perc alta. */
	private final JLabel percAlta;
	
	/** The perc media. */
	private final JLabel percMedia;
	
	/** The perc bed&breakfast. */
	private final JLabel percBB;
	
	/** The half board percentage */
	private final JLabel halfBoardPerc;
	
	/** The child percentage */
	private final JLabel childPer;
	
	/** The premium percentage*/
	private final JLabel percPremium;
	
	/** The suite percentage */
	private final JLabel percSuite;
	
	/** The full board over price. */
	private final JLabel fullBoardOverPrice;
	
	/** The child age. */
	private final JLabel childAge;
	
	/** The baby age. */
	private final JLabel babyAge;
	
	/** The high season start. */
	private final JLabel highSeasonStart;
	
	/** The high season end. */
	private final JLabel highSeasonEnd;
	
	/** The mid season start. */
	private final JLabel midSeasonStart;
	
	/** The mid season end. */
	private final JLabel midSeasonEnd;
	
	/** The low season start. */
	private final JLabel lowSeasonStart;
	
	/** The low season end. */
	private final JLabel lowSeasonEnd;
	
	/** The high season start text. */
	private final JTextField highSeasonStartText;
	
	/** The high season end text. */
	private final JTextField highSeasonEndText;
	
	/** The mid season start text. */
	private final JTextField midSeasonStartText;
	
	/** The mid season end text. */
	private final JTextField midSeasonEndText;
	
	/** The low season start text. */
	private final JTextField lowSeasonStartText;
	
	/** The low season end text. */
	private final JTextField lowSeasonEndText;
	
	/** The update. */
	private final JButton update = new JButton();
	
	/**
	 * Instantiates a new catalogue.
	 */
	Catalogue() {
		Dimension d = new Dimension(100, 200);
		this.setSize(1200, 800);

		dayPrice = new JLabel("Prezzo giornaliero standard: ");
		JTextField dayPriceText = new JTextField();
		dayPriceText.setMaximumSize(d);
		percMedia = new JLabel("Percentuale  Media Stagione: ");
		JTextField percMediaText = new JTextField();
		percMediaText.setMaximumSize(d);
		percAlta = new JLabel("Percentuale  Alta Stagione: ");
		JTextField percAltaText = new JTextField();
		percAltaText.setMaximumSize(d);
		childPer = new JLabel("Percentuale  Bambino: ");
		JTextField childPerText = new JTextField();
		childPerText.setMaximumSize(d);
		percPremium = new JLabel("Percentuale Camera Preimium: ");
		JTextField percPremiumText = new JTextField();
		percPremiumText.setMaximumSize(d);
		percSuite = new JLabel("Percentuale Camera Suite: ");
		JTextField percSuiteText = new JTextField();
		percSuiteText.setMaximumSize(d);
		percBB = new JLabel("Percentuale B&B: ");
		JTextField percBBText = new JTextField();
		percBBText.setMaximumSize(d);
		halfBoardPerc = new JLabel("Percentuale Mezza Pensione: ");
		JTextField halfBoardPercText = new JTextField();
		halfBoardPercText.setMaximumSize(d);
		fullBoardOverPrice = new JLabel("Percentuale Pensione Completa: ");
		JTextField fullBoardOverPriceText = new JTextField();
		fullBoardOverPriceText.setMaximumSize(d);
		childAge = new JLabel("Eta' massima bambino: ");
		JTextField childAgeText = new JTextField();
		childAgeText.setMaximumSize(d);
		babyAge = new JLabel("Eta' massima neonato: ");
		JTextField babyAgeText = new JTextField();
		babyAgeText.setMaximumSize(d);
		highSeasonStart = new JLabel("Inserisci data inizio alta stagione: ");
		highSeasonStartText = new JTextField();
		highSeasonStartText.setMaximumSize(d);
		highSeasonEnd = new JLabel("Inserisci data fine alta stagione: ");
		highSeasonEndText = new JTextField();
		highSeasonEndText.setMaximumSize(d);
		midSeasonStart = new JLabel("Inserisci data inizio media stagione: ");
		midSeasonStartText = new JTextField();
		midSeasonStartText.setMaximumSize(d);
		midSeasonEnd = new JLabel("Inserisci data fine media stagione: ");
		midSeasonEndText = new JTextField();
		midSeasonEndText.setMaximumSize(d);
		lowSeasonStart = new JLabel("Inserisci data inizio bassa stagione: ");
		lowSeasonStartText = new JTextField();
		lowSeasonStartText.setMaximumSize(d);
		lowSeasonEnd = new JLabel("Inserisci data fine bassa stagione: ");
		lowSeasonEndText = new JTextField();
		lowSeasonEndText.setMaximumSize(d);
		this.setTitle("Inserimento catalogo");
		this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));
		this.add(dayPrice);
		this.add(dayPriceText);
		this.add(percMedia);
		this.add(percMediaText);
		this.add(percAlta);
		this.add(percAltaText);
		this.add(percPremium);
		this.add(percPremiumText);
		this.add(percSuite);
		this.add(percSuiteText);
		this.add(childPer);
		this.add(childPerText);
		this.add(percBB);
		this.add(percBBText);
		this.add(halfBoardPerc);
		this.add(halfBoardPercText);
		this.add(childAge);
		this.add(childAgeText);
		this.add(babyAge);
		this.add(babyAgeText);
		this.add(fullBoardOverPrice);
		this.add(fullBoardOverPriceText);
		this.add(highSeasonStart);
		this.add(highSeasonStartText);
		this.add(highSeasonEnd);
		this.add(highSeasonEndText);
		this.add(midSeasonStart);
		this.add(midSeasonStartText);
		this.add(midSeasonEnd);
		this.add(midSeasonEndText);
		this.add(lowSeasonStart);
		this.add(lowSeasonStartText);
		this.add(lowSeasonEnd);
		this.add(lowSeasonEndText);
		if (!Hotel.getInstance().getCatalog().getSeasonMap().isEmpty()) {
			dayPriceText.setText("" + Hotel.getInstance().getCatalog().getDay());
			percMediaText.setText(
					"" + this.getFromFormattedPercentage(Hotel.getInstance().getCatalog().getMidSeasonOverPrice()));
			percAltaText.setText(
					"" + this.getFromFormattedPercentage(Hotel.getInstance().getCatalog().getHighSeasonOverPrice()));
			percPremiumText.setText(
					"" + this.getFromFormattedPercentage(Hotel.getInstance().getCatalog().getPremiumPercentage()));
			percSuiteText.setText(
					"" + this.getFromFormattedPercentage(Hotel.getInstance().getCatalog().getSuitePercentage()));
			percBBText.setText("" + this.getFromFormattedPercentage(Hotel.getInstance().getCatalog().getBBOverPrice()));
			childPerText.setText(
					"" + this.getFromFormattedPercentage(Hotel.getInstance().getCatalog().getChildPercentage()));
			halfBoardPercText.setText(
					"" + this.getFromFormattedPercentage(Hotel.getInstance().getCatalog().getHalfBoardOverPrice()));
			fullBoardOverPriceText.setText(
					"" + this.getFromFormattedPercentage(Hotel.getInstance().getCatalog().getFullBoardOverPrice()));
			babyAgeText.setText("" + Hotel.getInstance().getCatalog().getBabyAge());
			childAgeText.setText("" + Hotel.getInstance().getCatalog().getChildAge());

			for (Pair<LocalDate, LocalDate> p : Hotel.getInstance().getCatalog().getSeasonMap().keySet()) {
				if (Hotel.getInstance().getCatalog().getSeasonMap().get(p).equals(SeasonType.LOWSEASON)) {
					lowSeasonStartText.setText(Controller.fromLocalDateToString(p.getKey()));
					lowSeasonEndText.setText(Controller.fromLocalDateToString(p.getValue()));
				} else if (Hotel.getInstance().getCatalog().getSeasonMap().get(p).equals(SeasonType.MIDSEASON)) {
					midSeasonStartText.setText(Controller.fromLocalDateToString(p.getKey()));
					midSeasonEndText.setText(Controller.fromLocalDateToString(p.getValue()));
				} else {
					highSeasonStartText.setText(Controller.fromLocalDateToString(p.getKey()));
					highSeasonEndText.setText(Controller.fromLocalDateToString(p.getValue()));
				}
			}

		}
	
		this.add(update);
		update.addActionListener(e -> {
			if (Controller.checkIfIsNumber(dayPriceText.getText())
					&& Controller.checkIfNumberIsCorrect(percAltaText.getText(), 100, false)
					&& Controller.checkIfNumberIsCorrect(percAltaText.getText(), 0, true)
					&& Controller.checkIfNumberIsCorrect(percMediaText.getText(), 100, false)
					&& Controller.checkIfNumberIsCorrect(percMediaText.getText(), 0, true)
					&& Controller.checkIfNumberIsCorrect(percPremiumText.getText(), 100, false)
					&& Controller.checkIfNumberIsCorrect(percPremiumText.getText(), 0, true)
					&& Controller.checkIfNumberIsCorrect(percSuiteText.getText(), 100, false)
					&& Controller.checkIfNumberIsCorrect(percSuiteText.getText(), 0, true)
					&& Controller.checkIfNumberIsCorrect(percMediaText.getText(), 100, false)
					&& Controller.checkIfNumberIsCorrect(percMediaText.getText(), 0, true)
					&& Controller.checkIfNumberIsCorrect(percBBText.getText(), 100, false)
					&& Controller.checkIfNumberIsCorrect(percBBText.getText(), 0, true)
					&& Controller.checkIfNumberIsCorrect(halfBoardPercText.getText(), 100, false)
					&& Controller.checkIfNumberIsCorrect(halfBoardPercText.getText(), 0, true)
					&& Controller.checkIfNumberIsCorrect(fullBoardOverPriceText.getText(), 100, false)
					&& Controller.checkIfNumberIsCorrect(fullBoardOverPriceText.getText(), 0, true)
					&& Controller.checkIfNumberIsCorrect(childAgeText.getText(), 0, true)
					&& Controller.checkIfNumberIsCorrect(childPerText.getText(), 0, true)
					&& Controller.checkIfNumberIsCorrect(childPerText.getText(), 100, false)
					&& Controller.checkIfNumberIsCorrect(babyAgeText.getText(), 0, true)) {
				if (!(Controller.formatLocalDate(highSeasonStartText.getText()) == null)
						&& !(Controller.formatLocalDate(highSeasonEndText.getText()) == (null))
						&& !(Controller.formatLocalDate(midSeasonStartText.getText()) == (null))
						&& !(Controller.formatLocalDate(midSeasonEndText.getText()) == (null))
						&& !(Controller.formatLocalDate(lowSeasonStartText.getText()) == (null))
						&& !(Controller.formatLocalDate(lowSeasonEndText.getText()) == (null))) {
					Controller.getInstance().saveCatalog(Double.parseDouble(percAltaText.getText()),
							Double.parseDouble(percMediaText.getText()), Double.parseDouble(dayPriceText.getText()),
							Double.parseDouble(percBBText.getText()), Double.parseDouble(halfBoardPercText.getText()),
							Double.parseDouble(fullBoardOverPriceText.getText()),
							Double.parseDouble(percPremiumText.getText()), Double.parseDouble(percSuiteText.getText()),
							Double.parseDouble(childPerText.getText()), Integer.parseInt(childAgeText.getText()),
							Integer.parseInt(babyAgeText.getText()));
					if (Hotel.getInstance().getCatalog().checkConflict(
							Controller.formatLocalDate(this.lowSeasonStartText.getText()),
							Controller.formatLocalDate(this.lowSeasonEndText.getText()))) {
						if (Hotel.getInstance().getCatalog().checkConflict(
								Controller.formatLocalDate(this.midSeasonStartText.getText()),
								Controller.formatLocalDate(this.midSeasonEndText.getText()))) {
							if (Hotel.getInstance().getCatalog().checkConflict(
									Controller.formatLocalDate(this.highSeasonStartText.getText()),
									Controller.formatLocalDate(this.highSeasonEndText.getText()))) {
								Hotel.getInstance().getCatalog().getSeasonMap()
										.put(new Pair<LocalDate, LocalDate>(
												Controller.formatLocalDate(this.lowSeasonStartText.getText()),
												Controller.formatLocalDate(this.lowSeasonEndText.getText())),
										SeasonType.LOWSEASON);
								Hotel.getInstance().getCatalog().getSeasonMap()
										.put(new Pair<LocalDate, LocalDate>(
												Controller.formatLocalDate(this.midSeasonStartText.getText()),
												Controller.formatLocalDate(this.midSeasonEndText.getText())),
										SeasonType.MIDSEASON);
								Hotel.getInstance().getCatalog().getSeasonMap()
										.put(new Pair<LocalDate, LocalDate>(
												Controller.formatLocalDate(this.highSeasonStartText.getText()),
												Controller.formatLocalDate(this.highSeasonEndText.getText())),
										SeasonType.HIGHSEASON);
							}
						}
					}
					this.dispose();

				}
			}
		});

		this.setVisible(true);
		this.pack();
	}
	
	/**
	 * Gets the from formatted percentage.
	 *
	 * @param value the convert
	 * @return the from formatted percentage
	 */
	private Integer getFromFormattedPercentage(double value) {
		if ((int) value < 1) {
			Double decimal = (1 - value) * 100;
			return decimal.intValue();
		} else {
			Double decimal = (value - (int) value) * 100;
			return decimal.intValue();
		}
	}
}
