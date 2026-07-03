package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import model.Patient;

/**
 * Class used to display patient's info.
 *
 */
public class PatientInfoGUI extends JFrame {
    /**
     * serialVersionUID to allow to save and load files.
     */
    private static final long serialVersionUID = 1931346120164612219L;
    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private int width = (int) screenSize.getWidth();
    private int height = (int) screenSize.getHeight();

    private static JLabel operated;
    private static JFrame frame;
    private static JPanel center;
    private final Float font = 20.0f;
    private GUIFactory fac = new MyGUIFactory();
    /**
     * 
     * @param patient   the patient whose info i want to display.
     * @param waiting   if the patient is still in the waiting room.
     */
    public PatientInfoGUI(final Patient patient, final boolean waiting) {
        frame = new JFrame();
        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        frame.setSize(width / 3, height / 2);
        frame.setLocationByPlatform(true);
        frame.setLayout(new BorderLayout());
        frame.setTitle("Patient's information");
        frame.setMinimumSize(frame.getSize());

        //Panels
        JPanel west = fac.createBoxPanel();
        frame.add(west, BorderLayout.WEST);
        center = fac.createBoxPanel();
        frame.add(center, BorderLayout.CENTER);
        JPanel east = fac.createBoxPanel();
        frame.add(east, BorderLayout.EAST);
        JPanel south = fac.createBoxPanel();
        frame.add(south, BorderLayout.SOUTH);

        //Labels for the patient's info
        JLabel labelName = fac.createLabelRight("Name: ", font);
        JLabel labelSurname = fac.createLabelRight("Surname: ", font);
        JLabel labelCodFis = fac.createLabelRight("CodFis: ", font);
        JLabel labelAge = fac.createLabelRight("Age: ", font);
        JLabel labelSex = fac.createLabelRight("Sex: ", font);
        JLabel labelDisease = fac.createLabelRight("Disease: ", font);
        JLabel labelPriority = fac.createLabelRight("Priority: ", font);
        JLabel labelDoctor = fac.createLabelRight("Doctor: ", font);
        JLabel labelOperated = fac.createLabelRight("Under surgery: ", font);
        west.add(labelName);
        west.add(labelSurname);
        west.add(labelCodFis);
        west.add(labelAge);
        west.add(labelSex);
        west.add(labelDisease);
        west.add(labelPriority);
        west.add(labelDoctor);
        west.add(labelOperated);

        //Actual patient's infos, next to their respective labels
        JLabel name = fac.createLabelLeft(patient.getName(), font);
        JLabel surname = fac.createLabelLeft(patient.getSurname(), font);
        JLabel codFis = fac.createLabelLeft(patient.getCodfis(), font);
        Integer num = patient.getAge();
        JLabel age = fac.createLabelLeft(num.toString(), font);
        JLabel sex = fac.createLabelLeft(patient.getSex(), font);
        JLabel disease = fac.createLabelLeft(patient.getDisease(), font);
        JLabel priority = fac.createLabelLeft(patient.getPriorityName(), font);
        JLabel doctor = fac.createLabelLeft("", font);
        if (patient.getDoctor() == null) {
            doctor.setText("None");
        } else {
            doctor.setText(patient.getDoctor().getName() + " " + patient.getDoctor().getSurname());
        }
        operated = fac.createLabelLeft("", font);
        if (patient.isBeingOperated()) {
            operated.setText("Yes");
        } else {
            operated.setText("No");
        }
        center.add(name);
        center.add(surname);
        center.add(codFis);
        center.add(age);
        center.add(sex);
        center.add(disease);
        center.add(priority);
        center.add(doctor);
        center.add(operated);

        JLabel patientLog = fac.createLabel("Patient's Log", font);
        JTextArea log = fac.createTextArea(patient.getLog());
        log.setLineWrap(true);
        log.setEditable(false);
        JScrollPane scrollPane = fac.createScrollPane(log);
        JTextArea logUpdate = fac.createTextArea("");
        logUpdate.setLineWrap(true);
        JScrollPane scrollPane2 = fac.createScrollPane(logUpdate);
        JLabel patientUpdate = fac.createLabel("Log Update", font);

        JButton updateLog = fac.createButton("Update Log");
        updateLog.addActionListener(a -> {
            if (!logUpdate.getText().equals("")) {
                patient.updateLog(logUpdate.getText());
                log.setText(patient.getLog());
                logUpdate.setText(null);
            }
        });
        JButton dismiss = fac.createButton("Dismiss patient");
        dismiss.addActionListener(a -> {
            try {
                MainGUI.dismissPatient(patient);
                MainGUI.refreshWaiting();
                frame.dispose();
            } catch (IllegalStateException e) {
                JOptionPane.showMessageDialog(frame, e.getMessage());
            }
        });
        if (waiting) {
            JButton treatPatient = fac.createButton("Treat patient");
            treatPatient.addActionListener(a -> {
                try {
                    new TreatPatientGUI(patient);
                } catch (IllegalStateException e) {
                    JOptionPane.showMessageDialog(frame, "No doctors in the hospital");
                }
            });
            east.add(treatPatient);
        } else {
            JButton operate = fac.createButton("Assign operating room");
            operate.addActionListener(a -> {
                try {
                    MainGUI.getFreeOperatingRooms();
                    new OperatePatientGUI(patient);
                } catch (IllegalStateException e) {
                    JOptionPane.showMessageDialog(frame, e.getMessage());
                }
            });
            if (!patient.isBeingOperated()) {
                east.add(operate);
            }
        }
        south.add(new JSeparator(SwingConstants.HORIZONTAL));
        south.add(patientLog);
        south.add(scrollPane);
        south.add(patientUpdate);
        south.add(scrollPane2);
        east.add(updateLog);
        if (!patient.isBeingOperated()) {
            east.add(dismiss);
        }

        frame.setVisible(true);
    }
    /**
     * method for remotely closing this window.
     */
    public static void close() {
        frame.dispose();
    }
    /**
     * Public method to change label "is being operated".
     */
    public static void refreshData() {
        if (operated.getText().equals("Yes")) {
            operated.setText("No");
        } else {
            operated.setText("Yes");
        }
        center.repaint();
        center.revalidate();
    }
}
