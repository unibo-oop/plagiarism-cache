package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import util.Enums;

/**
 *  Class to add a patient to the hospital.
 */
public class AddPatientGUI extends JFrame {

    /**
     * serialVersionUID to allow to save and load files.
     */
    private static final long serialVersionUID = 8022263936758126119L;
    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private int width = (int) screenSize.getWidth();
    private int height = (int) screenSize.getHeight();
    private GUIFactory fac = new MyGUIFactory();
    private String name, surname, codFis, sex, disease, priority;
    private int age;
    private final Float font = 20.0f;
    /**
     * 
     */
    public AddPatientGUI() {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        frame.setSize(width / 3, height / 3);
        frame.setLocationByPlatform(true);
        frame.setLayout(new BorderLayout());
        frame.setTitle("Insert patient info");
        frame.setResizable(false);

        //Panels
        JPanel canvas = fac.createBoxPanel();
        frame.add(canvas, BorderLayout.WEST);
        JPanel canvas2 = fac.createBoxPanel();
        frame.add(canvas2, BorderLayout.CENTER);
        JPanel canvas3 = fac.createFlowPanel();
        frame.add(canvas3,  BorderLayout.EAST);

        //Labels and textfields
        JLabel labelName = fac.createLabelRight("Name: ", font);
        canvas.add(labelName);
        JTextField textName = fac.createTextField();
        canvas2.add(textName);

        JLabel labelSurname = fac.createLabelRight("Surname: ", font);
        canvas.add(labelSurname);
        JTextField textSurname = fac.createTextField();
        canvas2.add(textSurname);

        JLabel labelCodFis = fac.createLabelRight("CodFis: ", font);
        canvas.add(labelCodFis);
        JTextField textCodFis = fac.createTextField();
        canvas2.add(textCodFis);

        JLabel labelAge = fac.createLabelRight("Age: ", font);
        canvas.add(labelAge);
        JTextField textAge = fac.createTextField();
        canvas2.add(textAge);

        JLabel labelSex = fac.createLabelRight("Sex: ", font);
        canvas.add(labelSex);
        JComboBox<String> textSex = fac.createCombo(Enums.Sexes.getSexNames());
        canvas2.add(textSex);

        JLabel labelDisease = fac.createLabelRight("Disease: ", font);
        canvas.add(labelDisease);
        JTextField textDisease = fac.createTextField();
        canvas2.add(textDisease);

        JLabel labelPriority = fac.createLabelRight("Priority: ", font);
        canvas.add(labelPriority);
        JComboBox<String> textPriority = fac.createCombo(Enums.Priorities.getPrioNames());
        canvas2.add(textPriority);

        //confirm button
        JButton confirm = fac.createButton("Confirm");
        confirm.addActionListener(a -> {
            try {
                name = textName.getText();
                surname = textSurname.getText();
                codFis = textCodFis.getText();
                sex = textSex.getSelectedItem().toString();
                disease = textDisease.getText();
                age = Integer.parseInt(textAge.getText());
                priority = textPriority.getSelectedItem().toString();
                MainGUI.createPatient(name, surname, codFis, sex, disease, age, priority);
                JOptionPane.showMessageDialog(frame, "Patient added correctly");
                frame.dispose();
                MainGUI.refreshWaiting();
            } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(frame, "Invalid age");
            } catch (IllegalArgumentException e) {
                JOptionPane.showMessageDialog(frame, e.getMessage());
            } catch (IllegalStateException e) {
                JOptionPane.showMessageDialog(frame, e.getMessage());
            }
        });
        canvas3.add(confirm);
        frame.setVisible(true);
        frame.pack();
    }
}
