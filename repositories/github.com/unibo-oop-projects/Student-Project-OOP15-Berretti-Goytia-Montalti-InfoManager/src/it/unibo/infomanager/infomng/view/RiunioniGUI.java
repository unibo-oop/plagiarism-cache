package it.unibo.infomanager.infomng.view;

import javax.swing.JPanel;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.LayoutManager;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import it.unibo.infomanager.infomng.model.modelReunionsI;
import it.unibo.infomanager.infomng.view.interfaces.ObserverInterface;
import it.unibo.infomanager.infomng.view.toolbar.MyToolbar;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;

/**
 * Classe che definisce viewRiunioni.
 * @author Alessandro
 *
 */
public class RiunioniGUI extends InitializeFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8816191580108552347L;
	private static final String TITOLO = "Riunioni";
	private static final LayoutManager LAYOUT = new BorderLayout();
	private static final Dimension DIMFRAME = new Dimension(400, 400);
	private RiunioniGUI frame = this;
	private JPanel panelTool = new JPanel();
	private JPanel panelText = new JPanel();
	private MyToolbar toolbar;
	private final JLabel lblNomeEvento = new JLabel("Nome Evento");
	private final JLabel lblGiorno = new JLabel("Giorno");
	private final JLabel lblOra = new JLabel("Ora");
	private final JLabel lblNote = new JLabel("Note");
	private final JTextField txtEvento = new JTextField();
	private final JTextField txtGiorno = new JTextField();
	private final JTextField txtOra = new JTextField();
	private final JLabel lblMese = new JLabel("Mese");
	private final JTextField txtMese = new JTextField();
	private final JLabel lblAnno = new JLabel("Anno");
	private final JTextField txtAnno = new JTextField();
	private JTextArea textArea = new JTextArea();
	private GroupLayout gPanelText = new GroupLayout(panelText);
	private final JPanel buttonPane = new JPanel();
	private final JButton btnPrecedente = new JButton("<<");
	private final JButton btnSuccessivo = new JButton(">>");
	/**
	 * Metodo per ottendere dati dai TextField.
	 * @return
	 * 			Map (String,String)
	 */		
	public Map<String, String> getTextField() {
		Map<String, String> mappa = new HashMap<>();
		mappa.put("Evento", txtEvento.getText());
		mappa.put("Giorno", txtGiorno.getText());
		mappa.put("Mese", txtMese.getText());
		mappa.put("Anno", txtAnno.getText());
		mappa.put("Note", textArea.getText());
		return mappa;
	}
	/**
	 * Costruttore del RiunioniGUI frame.
	 * @param o
	 * 		Oggetto ObserverInterface
	 */
	//CHECKSTYLE:OFF: checkstyle:magicnumber    
	public RiunioniGUI(final ObserverInterface o) {
		super(TITOLO, LAYOUT, DIMFRAME);
		this.txtAnno.setColumns(10);
		this.txtMese.setColumns(10);
		this.txtOra.setColumns(10);
		this.txtGiorno.setColumns(10);
		this.txtEvento.setColumns(10);
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(RiunioniGUI.class.getResource("/com/sun/java/swing/plaf/windows/icons/Computer.gif")));
		this.getMainPanel().setLayout(new BorderLayout(0, 0));
		this.getMainPanel().add(panelTool, BorderLayout.CENTER);
		this.toolbar = new MyToolbar(o, frame);
		this.panelTool.setLayout(new BorderLayout(0, 0));
		this.panelTool.add(toolbar, BorderLayout.NORTH);
		this.panelTool.add(panelText, BorderLayout.CENTER);
		


		this.gPanelText.setHorizontalGroup(
				this.gPanelText.createParallelGroup(Alignment.LEADING)
				.addGroup(gPanelText.createSequentialGroup()
					.addContainerGap()
					.addGroup(gPanelText.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNomeEvento)
						.addComponent(lblGiorno)
						.addComponent(lblOra)
						.addComponent(lblNote))
					.addGap(18)
					.addGroup(gPanelText.createParallelGroup(Alignment.LEADING, false)
						.addComponent(textArea)
						.addComponent(txtEvento, GroupLayout.DEFAULT_SIZE, 265, Short.MAX_VALUE)
						.addComponent(txtOra)
						.addGroup(gPanelText.createSequentialGroup()
							.addComponent(txtGiorno, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(lblMese)
							.addGap(18)
							.addComponent(txtMese, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(lblAnno)
							.addGap(18)
							.addComponent(txtAnno, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		this.gPanelText.setVerticalGroup(
				this.gPanelText.createParallelGroup(Alignment.LEADING)
				.addGroup(gPanelText.createSequentialGroup()
					.addGap(32)
					.addGroup(gPanelText.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNomeEvento)
						.addComponent(txtEvento, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gPanelText.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblGiorno)
						.addComponent(txtGiorno, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblMese)
						.addComponent(txtMese, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblAnno)
						.addComponent(txtAnno, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gPanelText.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblOra)
						.addComponent(txtOra, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gPanelText.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNote)
						.addComponent(textArea, GroupLayout.PREFERRED_SIZE, 167, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(24, Short.MAX_VALUE))
		);
		this.panelText.setLayout(gPanelText);
		
		this.panelTool.add(buttonPane, BorderLayout.SOUTH);
		
		this.buttonPane.add(btnPrecedente);
		this.btnPrecedente.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					o.setAttuale(frame);
					getNavigator().indietro();
				} catch (NullPointerException e2) {
					JOptionPane.showMessageDialog(o.getAttuale().get(), "Eseguire una ricerca per scorrere tra le riunioni");
				}
				
			}
		});
		this.buttonPane.add(btnSuccessivo);
		this.btnSuccessivo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					o.setAttuale(frame);
					getNavigator().avanti();
				} catch (NullPointerException e2) {
					JOptionPane.showMessageDialog(o.getAttuale().get(), "Eseguire una ricerca per scorrere tra le riunioni");
				}
				
			}
		});
		

	}
	/**
	 * Metodo per settare i textfield.
	 * @param o
	 * 			oggetto Object
	 */			
	public void setTextField(final Object o) {
		modelReunionsI riunioni = (modelReunionsI) o;
		this.txtEvento.setText(riunioni.getNameReunion());
		this.txtGiorno.setText(String.valueOf(riunioni.getDate()));
		this.txtOra.setText(riunioni.getTime());
	}
	/**
	 * Metodo per resettare i textfield del frame
	 */
	public void resetCampi() {
		this.txtAnno.setText("");
		this.txtGiorno.setText("");
		this.txtMese.setText("");
		this.txtEvento.setText("");
		this.txtOra.setText("");
		this.textArea.setText("");
	}
}
