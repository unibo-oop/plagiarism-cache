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
import model.Ambulatorio;

public class RemoveAmbForm extends JFrame {

	 private static final long serialVersionUID = -9018599376871630840L;
	 private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	    private int width = (int) screenSize.getWidth();
	    private int height = (int) screenSize.getHeight();
	    private GUI fac = new GUIFactory();
	    private String codice;
	    private final float font = 20.0f;
	    
	    
	    public RemoveAmbForm() {
	        JFrame frame = new JFrame();
	        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	        frame.setSize(width / 3, height / 3);
	        frame.setLocationByPlatform(true);
	        frame.setLayout(new BorderLayout());
	        frame.setTitle("Rimuovi Ambulatorio");
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
	        ArrayList<Ambulatorio> amb = Admin.getListaAmbulatori();
	        String[] codes = new String[amb.size()];
	        
	        for(int i = 0; i < amb.size(); i++) {
	        	codes[i] = amb.get(i).getTipo() + " - cod. " +amb.get(i).getCodice();
	        }
	        JComboBox<String> textCode = fac.createCombo(codes);
	        canvas2.add(textCode);
	        
	        JButton confirm = new JButton("Rimuovi");
	        confirm.setFont(new Font("Calibri", Font.PLAIN,18));
	        confirm.setBackground(Color.darkGray);
	        confirm.setForeground(Color.white);
	        confirm.addActionListener(a -> {
	        	
	        	codice = textCode.getSelectedItem().toString().split("cod. ")[1];
				Admin.removeAmbulatorio(codice);
	        	
	        	JOptionPane.showMessageDialog(frame, "Elemento rimosso correttamente");
	            frame.dispose();
	        });
	        canvas3.add(confirm);
	        frame.setVisible(true);
	        frame.pack();
	    }
}
