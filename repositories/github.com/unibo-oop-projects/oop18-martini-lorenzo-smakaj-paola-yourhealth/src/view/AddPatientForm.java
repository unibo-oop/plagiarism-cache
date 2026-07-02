package view;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.Admin;
import model.Paziente;
import model.PazienteImpl;
import util.Enums;
import util.Enums.Sesso;

public class AddPatientForm extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = -697684912603915024L;
	private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private int width = (int) screenSize.getWidth();
    private int height = (int) screenSize.getHeight();
    private GUI fac = new GUIFactory();
    private String name, surname, codFis, luogonascita, residenza;
    private Sesso sex;
	private LocalDate datanascita;
    private final Float font = 20.0f;
   
    public AddPatientForm() {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        frame.setSize(width / 3, height / 3);
        frame.setLocationByPlatform(true);
        frame.setLayout(new BorderLayout());
        frame.setTitle("Aggiungi paziente");
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
		JComboBox<String> textSex = fac.createCombo(Enums.Sesso.getValoriSesso());
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

        JButton confirm = new JButton("Salva");
        confirm.setFont(new Font("Calibri", Font.PLAIN,18));
        confirm.setBackground(Color.darkGray);
        confirm.setForeground(Color.white);
        confirm.addActionListener(a -> {
            try {
                name = textName.getText();
                surname = textSurname.getText();
                codFis = textCodFis.getText();
                sex = Enums.Sesso.getFromString(textSex.getSelectedItem().toString());
				luogonascita = textLuogoNascita.getText();
                datanascita = LocalDate.parse(textDataNascita.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
				residenza = textResidenza.getText();
    


                Paziente P = new PazienteImpl(name, surname, sex, luogonascita, datanascita,
						codFis, residenza);
				int res = Admin.addPaziente(P);
                
				if(res <= 0) {
                	JOptionPane.showMessageDialog(this,"Codice fiscale già esistente",
        					"Errore",JOptionPane.ERROR_MESSAGE);
				}
				else {
				    JOptionPane.showMessageDialog(frame, "Elemento aggiunto correttamente");
				    frame.dispose();
				}
				
            } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(frame, "Età non valida");
            } catch (IllegalArgumentException e) {
                JOptionPane.showMessageDialog(frame, e.getMessage());
            } catch (IllegalStateException e) {
                JOptionPane.showMessageDialog(frame, e.getMessage());
            } catch (Exception e) {
				e.printStackTrace();
			}
        });
        canvas3.add(confirm);
        frame.setVisible(true);
        frame.pack();
    }
}
