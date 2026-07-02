package view;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.AccountPaziente;
import model.Paziente;

public class ProfilePatient extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = -697684912603915024L;
	private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private int width = (int) screenSize.getWidth();
    private int height = (int) screenSize.getHeight();
    private GUI fac = new GUIFactory();
    private final Float font = 20.0f;
   
    public ProfilePatient(String codicefiscale) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        frame.setSize(width / 3, height / 3);
        frame.setLocationByPlatform(true);
        frame.setLayout(new BorderLayout());
        frame.setTitle("Profilo "+codicefiscale);
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

		JLabel labelCodFis = fac.createLabelRight("Codice Fiscale: ", font);
		canvas.add(labelCodFis);
		JTextField textCodFis = fac.createTextField();
		canvas2.add(textCodFis);

		JLabel labelResidenza = fac.createLabelRight("Residenza: ", font);
		canvas.add(labelResidenza);
		JTextField textResidenza = fac.createTextField();
		canvas2.add(textResidenza);
		
		Paziente pat = AccountPaziente.getPazienteFromDB(codicefiscale);
        textName.setText(pat.getNome());
        textSurname.setText(pat.getCognome());
        textCodFis.setText(pat.getCodicefiscale());
        textSex.setText(pat.getSesso());
        textLuogoNascita.setText(pat.getLuogoNascita());
        textDataNascita.setText(pat.getDataNascita().toString());
        textResidenza.setText(pat.getResidenza());
         
        textName.setEditable(false);
		textSurname.setEditable(false);
		textCodFis.setEditable(false);
		textSex.setEditable(false);
		textLuogoNascita.setEditable(false);
		textDataNascita.setEditable(false);
		textResidenza.setEditable(false);
    
        frame.setVisible(true);
        frame.pack();
    }
}
