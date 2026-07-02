package view;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.AccountDottore;
import model.Dottore;


public class ProfileDoc extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 4444422767490025177L;
	private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private int width = (int) screenSize.getWidth();
    private int height = (int) screenSize.getHeight();
    private GUI fac = new GUIFactory();
    private final float font = 20.0f;
    /**
     * 
     */
    public ProfileDoc(int tesserino) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        frame.setSize(width / 3, height / 3);
        frame.setLocationByPlatform(true);
        frame.setLayout(new BorderLayout());
        frame.setTitle("Profilo "+tesserino);
        frame.setResizable(false);

        JPanel canvas = fac.createBoxPanel();
        frame.add(canvas, BorderLayout.WEST);
        JPanel canvas2 = fac.createBoxPanel();
        frame.add(canvas2, BorderLayout.CENTER);
        JPanel canvas3 = fac.createFlowPanel();
        frame.add(canvas3,  BorderLayout.EAST);

        JLabel labelName = fac.createLabelRight("Nome: ", font);
        canvas.add(labelName);
        JTextField textName = fac.createTextField();
        canvas2.add(textName);

        JLabel labelSurname = fac.createLabelRight("Cognome: ", font);
        canvas.add(labelSurname);
        JTextField textSurname = fac.createTextField();
        canvas2.add(textSurname);
        
        JLabel labelSex = fac.createLabelRight("Sesso: ", font);
        canvas.add(labelSex);
        JTextField textSex = fac.createTextField();
        canvas2.add(textSex);
        
        JLabel labelLuogoNascita = fac.createLabelRight("Luogo Nascita: ", font);
		canvas.add(labelLuogoNascita);
		JTextField textLuogoNascita = fac.createTextField();
		canvas2.add(textLuogoNascita);

		JLabel labelDataNascita = fac.createLabelRight("Data Nascita: ", font);
		canvas.add(labelDataNascita);
		JTextField textDataNascita = fac.createTextField();
		canvas2.add(textDataNascita);

        JLabel labelId = fac.createLabelRight("Tesserino: ", font);
        canvas.add(labelId);
        JTextField textId = fac.createTextField();
        canvas2.add(textId);

        JLabel labelSpec = fac.createLabelRight("Ruolo: ", font);
        canvas.add(labelSpec);
        JTextField textSpec = fac.createTextField();
        canvas2.add(textSpec);
        
        JLabel labelOrarioInizio = fac.createLabelRight("Orario Inizio: ", font);
		canvas.add(labelOrarioInizio);
		JTextField textOrarioInizio = fac.createTextField();
		canvas2.add(textOrarioInizio);
		
		JLabel labelOrarioFine = fac.createLabelRight("Orario Fine: ", font);
		canvas.add(labelOrarioFine);
		JTextField textOrarioFine = fac.createTextField();
		canvas2.add(textOrarioFine);
		
		Dottore doc = AccountDottore.getDottoreFromDB(tesserino);
		textName.setText(doc.getNome());
		textSurname.setText(doc.getCognome());
		textSex.setText(doc.getSesso());
		textLuogoNascita.setText(doc.getLuogoNascita());
		textDataNascita.setText(doc.getDataNascita().toString());
		textId.setText(""+tesserino);
		textSpec.setText(doc.getRuolo());
		textOrarioInizio.setText(doc.getOrarioInizio().toString());
		textOrarioFine.setText(doc.getOrarioFine().toString());
            	
		textName.setEditable(false);
		textSurname.setEditable(false);
		textSex.setEditable(false);
		textLuogoNascita.setEditable(false);
		textDataNascita.setEditable(false);
		textId.setEditable(false);
		textSpec.setEditable(false);
		textOrarioInizio.setEditable(false);
		textOrarioFine.setEditable(false);
	
        frame.setVisible(true);
        frame.pack();
    }
}
