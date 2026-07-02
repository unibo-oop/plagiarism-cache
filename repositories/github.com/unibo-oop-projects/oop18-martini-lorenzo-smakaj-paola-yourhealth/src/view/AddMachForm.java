package view;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.Admin;
import model.Macchinario;
import model.MacchinarioImpl;
import util.Enums;
import util.Enums.TipoMacchinario;

public class AddMachForm extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = -3612704906615612092L;
	private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private int width = (int) screenSize.getWidth();
    private int height = (int) screenSize.getHeight();
    private GUI fac = new GUIFactory();
    private TipoMacchinario type;
    private String id;
    private final Float font = 20.0f;
   
    public AddMachForm() {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        frame.setSize(width / 3, height / 3);
        frame.setLocationByPlatform(true);
        frame.setLayout(new BorderLayout());
        frame.setTitle("Aggiungi macchiario");
        frame.setResizable(false);

        JPanel canvas = fac.createBoxPanel();
        frame.add(canvas, BorderLayout.WEST);
        JPanel canvas2 = fac.createBoxPanel();
        frame.add(canvas2, BorderLayout.CENTER);
        JPanel canvas3 = fac.createFlowPanel();
        frame.add(canvas3,  BorderLayout.EAST);
        
        JLabel labelId = fac.createLabelRight("Codice: ", font);
        canvas.add(labelId);
        JTextField textId = fac.createTextField();
        canvas2.add(textId);
        
        JLabel labelType = fac.createLabelRight("Tipo macchinario: ", font);
        canvas.add(labelType);
        JComboBox<String> textType = fac.createCombo(Enums.TipoMacchinario.getValoriTipoMacchinario());
        canvas2.add(textType);

        JButton confirm = new JButton("Salva");
        confirm.setFont(new Font("Calibri", Font.PLAIN,18));
        confirm.setBackground(Color.darkGray);
        confirm.setForeground(Color.white);
        confirm.addActionListener(a -> {
        	type = Enums.TipoMacchinario.getFromString(textType.getSelectedItem().toString());
        	id = textId.getText();
        	
            
        	
        	Macchinario M = new MacchinarioImpl(id, type);
        	int res;
			res = Admin.addMacchinario(M);
        	
        	if (res <= 0) {
            	JOptionPane.showMessageDialog(this,"Codice macchinario già esistente",
    					"Errore",JOptionPane.ERROR_MESSAGE);
        	}
        	else {
        		JOptionPane.showMessageDialog(frame, "Elemento aggiunto correttamente");
        		frame.dispose();
        	}
        	
        });
        canvas3.add(confirm);
        frame.setVisible(true);
        frame.pack();
    }
}
