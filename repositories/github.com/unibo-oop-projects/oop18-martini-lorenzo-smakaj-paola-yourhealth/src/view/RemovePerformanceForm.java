package view;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.Admin;

public class RemovePerformanceForm extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6274630894009026575L;
	private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private int width = (int) screenSize.getWidth();
    private int height = (int) screenSize.getHeight();
    private GUI fac = new GUIFactory();
    private String codicefiscale;
    private int tesserino;
    private LocalDate data;
    private LocalTime ora;
    private final float font = 20.0f;
    
    
    public RemovePerformanceForm() {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        frame.setSize(width / 3, height / 3);
        frame.setLocationByPlatform(true);
        frame.setLayout(new BorderLayout());
        frame.setTitle("Rimuovi Prestazione");
        frame.setResizable(false);
        
        
      //Panels
        JPanel canvas = fac.createBoxPanel();
        frame.add(canvas, BorderLayout.WEST);
        JPanel canvas2 = fac.createBoxPanel();
        frame.add(canvas2, BorderLayout.CENTER);
        JPanel canvas3 = fac.createFlowPanel();
        frame.add(canvas3,  BorderLayout.EAST);
        
        JLabel labelCF = fac.createLabelRight("Codice Fiscale Paziente: ", font);
        canvas.add(labelCF);
        JTextField textCF = fac.createTextField();
        canvas2.add(textCF);
        
        JLabel labelDoc = fac.createLabelRight("Tesserino Dottore: ", font);
        canvas.add(labelDoc);
        JTextField textDoc = fac.createTextField();
        canvas2.add(textDoc);
        
        JLabel labelDate = fac.createLabelRight("Data Prestazione: ", font);
		canvas.add(labelDate);
		JTextField textDate = fac.createTextField();
		canvas2.add(textDate);
		
		JLabel labelTime = fac.createLabelRight("Ora Prestazione: ", font);
		canvas.add(labelTime);
		JTextField textTime = fac.createTextField();
		canvas2.add(textTime);
		
        JButton confirm = new JButton("Rimuovi");
        confirm.setFont(new Font("Calibri", Font.PLAIN,18));
        confirm.setBackground(Color.darkGray);
        confirm.setForeground(Color.white);
        confirm.addActionListener(a -> {
        	
        	codicefiscale = textCF.getText();
        	tesserino = Integer.parseInt(textDoc.getText());
        	data = LocalDate.parse(textDate.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            ora = LocalTime.parse(textTime.getText(), DateTimeFormatter.ofPattern("HH:mm"));
            int res;
        	try {
				res = Admin.removePrestazione(codicefiscale, tesserino, data, ora);
				if (res == 0) {
                	JOptionPane.showMessageDialog(this,"Nessuna prestazione trovata con questi parametri",
        					"Errore",JOptionPane.ERROR_MESSAGE);
				}
				else {
			       	JOptionPane.showMessageDialog(frame, "Elemento rimosso correttamente");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
        	
 
            frame.dispose();
        });
        canvas3.add(confirm);
        frame.setVisible(true);
        frame.pack();
    }
}
