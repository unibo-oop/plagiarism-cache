package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import model.Doctor;

/**
 * Class used to display doctor's info.
 */
public class DoctorInfoGUI extends JFrame {
    /**
     * serialVersionUID to allow to save and load files.
     */
    private static final long serialVersionUID = -5523009296715416759L;
    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private int width = (int) screenSize.getWidth();
    private int height = (int) screenSize.getHeight();
    private final Float font = 20.0f;
    private GUIFactory fac = new MyGUIFactory();
    /**
     * 
     * @param doctor the doctor whose info i want to display.
     */
    public DoctorInfoGUI(final Doctor doctor) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        frame.setSize(width / 3, height / 4);
        frame.setLocationByPlatform(true);
        frame.setLayout(new BorderLayout());
        frame.setTitle("Doctor's information");
        frame.setMinimumSize(frame.getSize());

        //Panels
        JPanel west = fac.createBoxPanel();
        frame.add(west, BorderLayout.WEST);
        JPanel center = fac.createBoxPanel();
        frame.add(center, BorderLayout.CENTER);
        JPanel east = fac.createBoxPanel();
        frame.add(east, BorderLayout.EAST);

        //Labels for the doctor's info
        JLabel labelName = fac.createLabelRight("Name: ", font);
        JLabel labelSurname = fac.createLabelRight("Surname: ", font);
        JLabel labelCodFis = fac.createLabelRight("FisNum: ", font);
        JLabel labelAge = fac.createLabelRight("Age: ", font);
        JLabel labelSex = fac.createLabelRight("Sex: ", font);
        JLabel labelSpec = fac.createLabelRight("Specialization: ", font);
        west.add(labelName);
        west.add(labelSurname);
        west.add(labelCodFis);
        west.add(labelAge);
        west.add(labelSex);
        west.add(labelSpec);

        //Actual doctor's infos, next to their respective labels
        JLabel name = fac.createLabelLeft(doctor.getName(), font);
        JLabel surname = fac.createLabelLeft(doctor.getSurname(), font);
        JLabel codFis = fac.createLabelLeft(doctor.getCodfis(), font);
        Integer num = doctor.getAge();
        JLabel age = fac.createLabelLeft(num.toString(), font);
        JLabel sex = fac.createLabelLeft(doctor.getSex(), font);
        JLabel spec = fac.createLabelLeft(doctor.getSpecialization(), font);
        center.add(name);
        center.add(surname);
        center.add(codFis);
        center.add(age);
        center.add(sex);
        center.add(spec);

        JButton fireDoctor = fac.createButton("Fire Doctor");
        fireDoctor.addActionListener(a -> {
            try {
                MainGUI.fireDoctor(doctor);
                JOptionPane.showMessageDialog(frame, "Doctor fired");
                frame.dispose();
                MainGUI.refreshDoctors();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(frame, e.getMessage());
            }
        });

        JButton viewPat = fac.createButton("View patients");
        viewPat.addActionListener(a -> {
            if (!doctor.getAssignedPatients().isEmpty()) {
                new PatientListGUI(doctor);
            } else {
                JOptionPane.showMessageDialog(frame, "No patients assigned");
            }
        });
        east.add(viewPat);
        east.add(fireDoctor);
        frame.setVisible(true);
    }
}