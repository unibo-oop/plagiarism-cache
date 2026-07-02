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
import model.Dottore;

public class RemoveDocForm extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = -9126331208145204961L;
	private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private int width = (int) screenSize.getWidth();
    private int height = (int) screenSize.getHeight();
    private GUI fac = new GUIFactory();
    private int tesserino;
    private final float font = 20.0f;
    
    
    public RemoveDocForm() {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        frame.setSize(width / 3, height / 3);
        frame.setLocationByPlatform(true);
        frame.setLayout(new BorderLayout());
        frame.setTitle("Rimuovi dottore");
        frame.setResizable(false);
        
        
      //Panels
        JPanel canvas = fac.createBoxPanel();
        frame.add(canvas, BorderLayout.WEST);
        JPanel canvas2 = fac.createBoxPanel();
        frame.add(canvas2, BorderLayout.CENTER);
        JPanel canvas3 = fac.createFlowPanel();
        frame.add(canvas3,  BorderLayout.EAST);
        
        JLabel labelId = fac.createLabelRight("Tesserino: ", font);
        canvas.add(labelId);
        ArrayList<Dottore> docs = Admin.getListaDottori();
        String[] tess = new String[docs.size()];
        
        for(int i = 0; i < docs.size(); i++) {
        	tess[i] = docs.get(i).getNome() + " " + docs.get(i).getCognome()+ " - cod. " + docs.get(i).getTesserino();
        }
        JComboBox<String> textId = fac.createCombo(tess);
        canvas2.add(textId);
        
        JButton confirm = new JButton("Rimuovi");
        confirm.setFont(new Font("Calibri", Font.PLAIN,18));
        confirm.setBackground(Color.darkGray);
        confirm.setForeground(Color.white);
        confirm.addActionListener(a -> {
        	
        	tesserino = Integer.parseInt(textId.getSelectedItem().toString().split("cod. ")[1]);
			Admin.removeDottore(tesserino);
        	
        	JOptionPane.showMessageDialog(frame, "Elemento rimosso correttamente");
            frame.dispose();
        });
        canvas3.add(confirm);
        frame.setVisible(true);
        frame.pack();
    }

}
