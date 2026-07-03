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
public class AddDoctorGUI extends JFrame {

    /**
     * serialVersionUID to allow to save and load files.
     */
    private static final long serialVersionUID = -433236315137519814L;
    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private int width = (int) screenSize.getWidth();
    private int height = (int) screenSize.getHeight();
    private GUIFactory fac = new MyGUIFactory();
    private String name, surname, codFis, sex, spec;
    private int age;
    private final float font = 20.0f;
    /**
     * 
     */
    public AddDoctorGUI() {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        frame.setSize(width / 3, height / 3);
        frame.setLocationByPlatform(true);
        frame.setLayout(new BorderLayout());
        frame.setTitle("Insert doctor info");
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

        JLabel labelSpec = fac.createLabelRight("Specialization: ", font);
        canvas.add(labelSpec);
        JComboBox<String> textSpec = fac.createCombo(Enums.Specializations.getSpecNames());
        canvas2.add(textSpec);

        //confirm button
        JButton confirm = fac.createButton("Confirm");
        confirm.addActionListener(a -> {
            try {
                name = textName.getText();
                surname = textSurname.getText();
                codFis = textCodFis.getText();
                sex = textSex.getSelectedItem().toString();
                age = Integer.parseInt(textAge.getText());
                spec = textSpec.getSelectedItem().toString();
                MainGUI.createDoctor(name, surname, codFis, sex, age, spec);
                JOptionPane.showMessageDialog(frame, "Doctor added correctly");
                frame.dispose();
                MainGUI.refreshDoctors();
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
