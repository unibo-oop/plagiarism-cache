package it.unibo.infomanager.infomng.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.Toolkit;
import javax.swing.JTextArea;

import it.unibo.infomanager.infomng.model.modelSalesI;
import it.unibo.infomanager.infomng.view.interfaces.ObserverInterface;
import it.unibo.infomanager.infomng.view.toolbar.MyToolbar;
/**
 * Classe che definisce viewReportVendite.
 * @author Alessandro
 *
 */
public class ReportVenditeGUI extends InitializeFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5756256366498583375L;
	private static final String TITOLO = "Report Vendite";
	private static final LayoutManager LAYOUT = new BorderLayout();
	private static final Dimension DIMFRAME = new Dimension(938, 476);
	private ReportVenditeGUI frame = this;
	private JPanel panelTool = new JPanel();
	private JPanel panelText = new JPanel();
	private MyToolbar toolbar;
	private final JTextArea textArea = new JTextArea();
	private JScrollPane scrollArea = new JScrollPane();
	/**
	 * Metodo che setta il testo nella textArea del frame.
	 * @param list
	 * 				Oggetto String
	 */
	public void setTextVendite(final List<modelSalesI> list) {
		this.textArea.setText(list.toString());
	}
	/**
	 * Costruttore del ReportVenditeGUI frame.
	 * @param o
	 * 			Oggetto ObserverInterface
	 */
	public ReportVenditeGUI(final ObserverInterface o) {
		super(TITOLO, LAYOUT, DIMFRAME);
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(ReportVenditeGUI.class.getResource("/com/sun/java/swing/plaf/windows/icons/Computer.gif")));
		this.setTitle("Report Vendite");
		this.getMainPanel().setLayout(new BorderLayout(0, 0));
		this.getMainPanel().add(panelTool, BorderLayout.CENTER);
		this.toolbar = new MyToolbar(o, frame);
		this.panelTool.setLayout(new BorderLayout(0, 0));
		this.panelTool.add(toolbar, BorderLayout.NORTH);
		this.panelTool.add(panelText, BorderLayout.CENTER);
		this.panelText.setLayout(new BorderLayout(0, 0));
		this.scrollArea.setViewportView(textArea);
		this.textArea.setEditable(false);
		this.setTextVendite(modelSalesI.salesList());
		this.panelText.add(scrollArea, BorderLayout.CENTER);
		
	}

}
