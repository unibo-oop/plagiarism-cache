package view;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import controller.Admin;
import model.Macchinario;

public class RemoveMachForm extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5041536777265153909L;
	private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private int width = (int) screenSize.getWidth();
    private int height = (int) screenSize.getHeight();
    private GUI fac = new GUIFactory();
    private String codice;
    private final float font = 20.0f;
    
    
    public RemoveMachForm() {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        frame.setSize(width / 3, height / 3);
        frame.setLocationByPlatform(true);
        frame.setLayout(new BorderLayout());
        frame.setTitle("Rimuovi Macchinario");
        frame.setResizable(false);
        
        
      //Panels
        JPanel canvas = fac.createBoxPanel();
        frame.add(canvas, BorderLayout.WEST);
        JPanel canvas2 = fac.createBoxPanel();
        frame.add(canvas2, BorderLayout.CENTER);
        JPanel canvas3 = fac.createFlowPanel();
        frame.add(canvas3,  BorderLayout.EAST);
        
        JLabel labelCode = fac.createLabelRight("Codice: ", font);
        canvas.add(labelCode);
        ArrayList<Macchinario> macs = Admin.getListaMacchinari();
        String[] desc = new String[macs.size()];
        
        for(int i = 0; i < macs.size(); i++) {
        	desc[i] = macs.get(i).getTipo()+ " - cod. " + macs.get(i).getCodice();
        }
        JComboBox<String> textCode = fac.createCombo(desc);
        canvas2.add(textCode);
        
        JButton confirm = new JButton("Rimuovi");
        confirm.setFont(new Font("Calibri", Font.PLAIN,18));
        confirm.setBackground(Color.darkGray);
        confirm.setForeground(Color.white);
        confirm.addActionListener(a -> {
        	
        	codice = textCode.getSelectedItem().toString().split("cod. ")[1];
			Admin.removeMacchinario(codice);
        	
        	JOptionPane.showMessageDialog(frame, "Elemento rimosso correttamente");
            frame.dispose();
        });
        canvas3.add(confirm);
        frame.setVisible(true);
        frame.pack();
    }
}
