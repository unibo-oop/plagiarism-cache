package view;



import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import model.Doctor;
/**
 * 
 *
 */
public class PatientListGUI extends JFrame {
    /**
     * serialVersionUID to allow to save and load files.
     */
    private static final long serialVersionUID = -7559645812146745214L;
    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private int width = (int) screenSize.getWidth();
    private int height = (int) screenSize.getHeight();
    private final Float font = 12.0f;
    private GUIFactory fac = new MyGUIFactory();
    /**
     * 
     * @param doctor    listing all patients from this doctor.
     */
    public PatientListGUI(final Doctor doctor) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        frame.setSize(width / 4, height / 3);
        frame.setLocationByPlatform(true);
        frame.setLayout(new BorderLayout());
        frame.setTitle("Doctor's patients");
        frame.setMinimumSize(frame.getSize());

        JPanel canvas = fac.createBoxPanel();
        JScrollPane scroll = fac.createScrollPane(canvas);
        frame.add(scroll, BorderLayout.CENTER);

        doctor.getAssignedPatients().forEach(p -> {
            canvas.add(fac.createSep());
            canvas.add(fac.createLabel("Name: " + p.getName(), font));
            canvas.add(fac.createLabel("Surname: " + p.getSurname(), font));
            JButton info = fac.createButton("View info");
            info.addActionListener(b -> new PatientInfoGUI(p, false));
            canvas.add(info);
            canvas.add(fac.createSep());
        });
        frame.setVisible(true);
    }
}
