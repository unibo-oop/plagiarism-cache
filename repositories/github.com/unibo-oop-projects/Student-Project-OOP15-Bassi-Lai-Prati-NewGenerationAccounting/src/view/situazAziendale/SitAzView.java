/**
 * 
 */
package view.situazAziendale;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import view.AbstractWideView;

/**
 * view per visualizzare la situazione aziendale con stato patrimoniale, conto
 * economico e commento.
 * 
 * @author Pentolo
 *
 */
public class SitAzView extends AbstractWideView {

	private static final Dimension DEFAULT = new Dimension(245, 300);
	private static final Dimension DEFAULTSALDO = new Dimension(70, 300);
	private static final String ATTIVITA = "ATTIVITA";
	private static final String PASSIVITA = "PASSIVITA";
	private static final String COSTI = "COSTI";
	private static final String RICAVI = "RICAVI";
	private static final String COLSALDO = "€";
	private static final long serialVersionUID = -8573556973965470550L;
	private final String AnalisiFinanziaria;
	private final String Attivita;
	private final String Saldi_Attivita;
	private final String Passivita;
	private final String Saldi_Passivita;
	private final String Costi;
	private final String Saldi_Costi;
	private final String Ricavi;
	private final String Saldi_Ricavi;
	private final float totAttiv;
	private final float totPassiv;
	private final float totCosti;
	private final float totRicavi;

	/**
	 * @param title
	 *            titolo della finestra
	 * @param totRicavi
	 * @param totCosti
	 * @param Dimension
	 *            dimensione della finestra
	 */
	public SitAzView(final String title, final Dimension dimension, final String analisiFinanziaria,
			final String attivita, final String saldi_Attivita, final String passivita, final String saldi_Passivita,
			final String costi, final String saldi_Costi, final String ricavi, final String saldi_Ricavi,
			final float totAttiv, final float totPassiv, final float totCosti, final float totRicavi) {
		super(title, dimension);
		AnalisiFinanziaria = analisiFinanziaria;
		Attivita = attivita;
		Saldi_Attivita = saldi_Attivita;
		Passivita = passivita;
		Saldi_Passivita = saldi_Passivita;
		Costi = costi;
		Saldi_Costi = saldi_Costi;
		Ricavi = ricavi;
		Saldi_Ricavi = saldi_Ricavi;
		this.totAttiv = totAttiv;
		this.totPassiv = totPassiv;
		this.totCosti = totCosti;
		this.totRicavi = totRicavi;
		final JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		addToPanel(panel, new JScrollPane(getSP()));
		addToPanel(panel, new JScrollPane(getSE()));
		addToPanel(panel, new JScrollPane(getComment()));
		getMyFrame().getContentPane().add(panel, BorderLayout.CENTER);
	}

	private void addToPanel(final JPanel panel, final JComponent comp) {
		comp.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(comp);
		// panel.add(Box.createRigidArea(new Dimension(0, 5)));
	}

	private JComponent getComment() {
		final JEditorPane editorPane = new JEditorPane();
		editorPane.setEditable(false);
		editorPane.setContentType("text/html");
		final String htmlText = "<font size=3>" + AnalisiFinanziaria.replaceAll("(\r\n|\n)", "<br/>") + "</font>";
		editorPane.setText(htmlText);
		editorPane.setPreferredSize(new Dimension(650, 75));
		return editorPane;
	}

	private JPanel getPane(final String text, final Dimension dim, final String fontSize, final String intestaz,
			final boolean isSaldo, final Float saldo) {
		final JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		addToPanel(panel, new JLabel(intestaz));
		final JEditorPane editorPane = new JEditorPane();
		editorPane.setEditable(false);
		editorPane.setContentType("text/html");
		final String htmlText = "<font size=" + fontSize + ">" + text.replaceAll("(\r\n|\n)", "<br/>") + "</font>";
		editorPane.setText(htmlText);
		editorPane.setPreferredSize(dim);
		addToPanel(panel, editorPane);
		if (isSaldo) {
			addToPanel(panel, new JLabel(String.valueOf(saldo)));
		} else {
			addToPanel(panel, new JLabel("TOTALE " + intestaz + ":"));
		}
		return panel;
	}

	private JPanel getPane(final String text, final String intestaz, final boolean isSaldo) {
		if (isSaldo) {
			switch (intestaz) {
			case ATTIVITA:
				return getPane(text, DEFAULTSALDO, "2", COLSALDO, true, totAttiv);
			case PASSIVITA:
				return getPane(text, DEFAULTSALDO, "2", COLSALDO, true, totPassiv);
			case COSTI:
				return getPane(text, DEFAULTSALDO, "2", COLSALDO, true, totCosti);
			case RICAVI:
				return getPane(text, DEFAULTSALDO, "2", COLSALDO, true, totRicavi);
			default:
				return new JPanel();
			}
		} else {
			return getPane(text, DEFAULT, "2", intestaz, false, new Float(0));
		}
	}

	private JPanel getSE() {
		final JPanel panel = new JPanel(new FlowLayout());
		panel.add(getPane(Costi, COSTI, false));
		panel.add(getPane(Saldi_Costi, COSTI, true));
		panel.add(getPane(Ricavi, RICAVI, false));
		panel.add(getPane(Saldi_Ricavi, RICAVI, true));
		return panel;
	}

	private JPanel getSP() {
		final JPanel panel = new JPanel(new FlowLayout());
		panel.add(getPane(Attivita, ATTIVITA, false));
		panel.add(getPane(Saldi_Attivita, ATTIVITA, true));
		panel.add(getPane(Passivita, PASSIVITA, false));
		panel.add(getPane(Saldi_Passivita, PASSIVITA, true));
		return panel;
	}
}
