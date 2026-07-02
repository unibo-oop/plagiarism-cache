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
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.Admin;
import model.Dottore;
import model.DottoreImpl;
import util.Enums;
import util.Enums.Ruolo;
import util.Enums.Sesso;

/**
 *  Class to add a doctor to the hospital.
 */
public class AddDocForm extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 4444422767490025177L;
	private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private int width = (int) screenSize.getWidth();
    private int height = (int) screenSize.getHeight();
    private GUI fac = new GUIFactory();
    private String nome, cognome, luogonascita;
    private Sesso sesso;
    private int tesserino;
    private LocalDate datanascita;
    private Ruolo ruolo;
    private LocalTime orarioinizio, orariofine;
    
    private final float font = 20.0f;
    /**
     * 
     */
    public AddDocForm() {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        frame.setSize(width / 3, height / 3);
        frame.setLocationByPlatform(true);
        frame.setLayout(new BorderLayout());
        frame.setTitle("Aggiungi dottore");
        frame.setResizable(false);

        //Panels
        JPanel canvas = fac.createBoxPanel();
        frame.add(canvas, BorderLayout.WEST);
        JPanel canvas2 = fac.createBoxPanel();
        frame.add(canvas2, BorderLayout.CENTER);
        JPanel canvas3 = fac.createFlowPanel();
        frame.add(canvas3,  BorderLayout.EAST);

        //Labels and textfields
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

        JLabel labelId = fac.createLabelRight("Tesserino: ", font);
        canvas.add(labelId);
        JTextField textId = fac.createTextField();
        canvas2.add(textId);

        JLabel labelSpec = fac.createLabelRight("Ruolo: ", font);
        canvas.add(labelSpec);
        JComboBox<String> textSpec = fac.createCombo(Enums.Ruolo.getValoriRuolo());
        canvas2.add(textSpec);
        
        JLabel labelOrarioInizio = fac.createLabelRight("Orario Inizio: ", font);
		canvas.add(labelOrarioInizio);
		JTextField textOrarioInizio = fac.createTextField();
		canvas2.add(textOrarioInizio);
		
		JLabel labelOrarioFine = fac.createLabelRight("Orario Fine: ", font);
		canvas.add(labelOrarioFine);
		JTextField textOrarioFine = fac.createTextField();
		canvas2.add(textOrarioFine);

        JButton confirm = new JButton("Salva");
        confirm.setFont(new Font("Calibri", Font.PLAIN,18));
        confirm.setBackground(Color.darkGray);
        confirm.setForeground(Color.white);
        confirm.addActionListener(a -> {
            try {
                nome = textName.getText();
                cognome = textSurname.getText();
                sesso = Enums.Sesso.getFromString(textSex.getSelectedItem().toString());
				luogonascita = textLuogoNascita.getText();
                datanascita = LocalDate.parse(textDataNascita.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                tesserino = Integer.parseInt(textId.getText());
                ruolo = Enums.Ruolo.getFromString(textSpec.getSelectedItem().toString());
                orarioinizio = LocalTime.parse(textOrarioInizio.getText(), DateTimeFormatter.ofPattern("HH:mm"));
                orariofine = LocalTime.parse(textOrarioFine.getText(), DateTimeFormatter.ofPattern("HH:mm"));
                
                Dottore D = new DottoreImpl(nome, cognome, sesso, luogonascita, datanascita, tesserino, ruolo, orarioinizio, orariofine);
                int res = Admin.addDottore(D);
                
                if(res <= 0) {
                  	JOptionPane.showMessageDialog(this,"Inserimento fallito",
            					"Errore",JOptionPane.ERROR_MESSAGE);
                }
                else {
                	 JOptionPane.showMessageDialog(frame, "Elemento aggiunto correttamente");
                	 frame.dispose();
                }
                
            } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(frame, "Tesserino non valido");
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
