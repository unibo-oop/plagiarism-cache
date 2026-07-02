package view.Interfaces;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.implementation.GestioneViaggio;
import view.Main.Main;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JButton;


public class Statistiche extends JFrame {

	/**
	 * Frame di consultazione statistiche sullo storico viaggi
	 * @author Elisa Chiappini
	 */
	private static final long serialVersionUID = 1L;
	private static final int ANNO = 2020;
	private static final int[] INIT = {0,0,0,0};
	
	private int annoInput;
	private int tipoInput;
	private int[] result = INIT;
	
	private JPanel contentPane;
	private JComboBox<Integer> cmbAnno;
	private JComboBox<Integer> cmbTipoOp;
	private JLabel primoTrim;
	private JLabel secondoTrim;
	private JLabel terzoTrim;
	private JLabel quartoTrim;
	
	final JFrame f = this;

	public Statistiche() {
		this.setResizable(false);
		this.setTitle("VISUALIZZA STATISTICHE ");
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 426, 291);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        this.setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JLabel lblAnno = new JLabel("Anno: ");
        lblAnno.setBounds(68, 27, 104, 14);
        contentPane.add(lblAnno);
        
        cmbAnno = new JComboBox<Integer>();
        cmbAnno.setBounds(182, 23, 78, 22);
        contentPane.add(cmbAnno);
        cmbAnno.addItem(ANNO);
        cmbAnno.addItem(ANNO+1);
        cmbAnno.addItem(ANNO+2);
      
        JLabel lblTipoOp = new JLabel("Tipo Operazione: ");
        lblTipoOp.setBounds(68, 60, 149, 14);
        contentPane.add(lblTipoOp);
        
        cmbTipoOp = new JComboBox<Integer>();
        cmbTipoOp.setToolTipText("1 per export, 2 import");
        cmbTipoOp.setBounds(182, 56, 124, 22);
        contentPane.add(cmbTipoOp);
        cmbTipoOp.addItem(1);
        cmbTipoOp.addItem(2);
        
        JLabel lblTrim1 = new JLabel("I Trimestre");
        lblTrim1.setBounds(10, 141, 91, 14);
        contentPane.add(lblTrim1);
        
        primoTrim = new JLabel("");
        primoTrim.setBounds(10, 166, 53, 23);
        contentPane.add(primoTrim);
        
        JLabel lblTrim2 = new JLabel("II Trimestre");
        lblTrim2.setBounds(98, 141, 91, 14);
        contentPane.add(lblTrim2);
        
        secondoTrim = new JLabel("");
        secondoTrim.setBounds(98, 166, 53, 23);
        contentPane.add(secondoTrim);
        
        JLabel lblTrim3 = new JLabel("III Trimestre");
        lblTrim3.setBounds(200, 141, 91, 14);
        contentPane.add(lblTrim3);
        
        terzoTrim = new JLabel("");
        terzoTrim.setBounds(200, 166, 53, 23);
        contentPane.add(terzoTrim);
        
        JLabel lblTrim4 = new JLabel("IV Trimestre");
        lblTrim4.setBounds(301, 141, 91, 14);
        contentPane.add(lblTrim4);
        
        quartoTrim = new JLabel("");
        quartoTrim.setBounds(301, 166, 53, 23);
        contentPane.add(quartoTrim);
        
        
        JButton btnVisualizza = new JButton("VISUALIZZA");
        btnVisualizza.setBounds(140, 96, 113, 23);
        contentPane.add(btnVisualizza);
        btnVisualizza.addActionListener((a) -> {
        	result = INIT;
        	try {
        		annoInput = (Integer) cmbAnno.getSelectedItem();
        		tipoInput = (Integer) cmbTipoOp.getSelectedItem();
        		result = GestioneViaggio.creaIst().statisticheMensili(annoInput, tipoInput);
        		if (result.equals(INIT)){
        			JOptionPane.showMessageDialog(f, "Nessun dato da visualizzare. ");
        		}else {
        			primoTrim.setText(String.valueOf(result[0]));
        			secondoTrim.setText(String.valueOf(result[1]));
        			terzoTrim.setText(String.valueOf(result[2]));
        			quartoTrim.setText(String.valueOf(result[3]));
        		}
        	}catch (Exception e) {
        		JOptionPane.showMessageDialog(f, "Dati non corretti. ");
        	}
        	
        });
        
        JButton btnIndietro = new JButton("INDIETRO");
        btnIndietro.setBounds(140, 228, 113, 23);
        btnIndietro.addActionListener ((a) -> {
			new Main().setVisible(true);
			chiudi();
        });
        contentPane.add(btnIndietro);
   
	}
	/**
     * Chiusura del frame
     */
    public void chiudi() {
        setVisible(false);
        dispose();
    }	      	
}
