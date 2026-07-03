package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import model.Doctor;
import model.Patient;
import model.Ward;
/**
 * 
 *
 */
public class TreatPatientGUI extends JFrame {
    /**
     * serialVersionUID to allow to save and load files.
     */
    private static final long serialVersionUID = 5563350235022769850L;
    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private int width = (int) screenSize.getWidth();
    private int height = (int) screenSize.getHeight();
    private GUIFactory fac = new MyGUIFactory();

    /**
     * 
     * @param patient patient.
     */
    public TreatPatientGUI(final Patient patient) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        frame.setSize(width / 3, height / 3);
        frame.setLocationByPlatform(true);
        frame.setLayout(new BorderLayout());
        frame.setTitle("Assign doctor and ward to patient");
        frame.setResizable(false);

        JPanel canvas = fac.createFlowPanel();
        frame.add(canvas);

        JComboBox<Doctor> doctor = new JComboBox<Doctor>();
        MainGUI.getDoctors().forEach(d -> {
            doctor.addItem(d);
        });
        JComboBox<Ward> ward = new JComboBox<Ward>();
        MainGUI.getWards().forEach(w -> {
            ward.addItem(w);
        });
        JButton confirm = fac.createButton("Confirm");
        confirm.addActionListener(a -> {
            try {
                MainGUI.assignPatient(patient, (Doctor) doctor.getSelectedItem(), (Ward) ward.getSelectedItem());
                MainGUI.refreshHospitalPatients();
                frame.dispose();
                //frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
                PatientInfoGUI.close();
            } catch (IllegalStateException e) {
                JOptionPane.showMessageDialog(frame, e.getMessage());
            }
        });
        canvas.add(doctor);
        canvas.add(ward);
        canvas.add(confirm);
        frame.setVisible(true);
        frame.pack();
    }
}
