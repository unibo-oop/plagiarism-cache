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

import model.OperatingRoom;
import model.Patient;

/**
 * 
 *
 */
public class OperatePatientGUI extends JFrame {
    /**
     * serialVersionUID to allow to save and load files.
     */
    private static final long serialVersionUID = -6650979307710774425L;
    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private int width = (int) screenSize.getWidth();
    private int height = (int) screenSize.getHeight();
    private final Float font = 15.0f;
    private GUIFactory fac = new MyGUIFactory();

    /**
     * 
     * @param patient patient to assign.
     */
    public OperatePatientGUI(final Patient patient) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        frame.setSize(width / 3, height / 3);
        frame.setLocationByPlatform(true);
        frame.setLayout(new BorderLayout());
        frame.setTitle("Assign patient to an operating room");
        frame.setResizable(false);

        //Panel
        JPanel canvas = fac.createFlowPanel();
        frame.add(canvas);

        JLabel label = fac.createLabel("Selezionare sala operatoria: ", font);
        canvas.add(label);
        JComboBox<OperatingRoom> rooms = new JComboBox<OperatingRoom>();
        canvas.add(rooms);
        try {
            MainGUI.getFreeOperatingRooms().forEach(r -> {
                rooms.addItem(r);
            });
        } catch (IllegalStateException e) {
            //frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
            JOptionPane.showMessageDialog(frame, e.getMessage());
            frame.dispose();
        }

        JButton confirm = fac.createButton("Confirm");
        confirm.addActionListener(a -> {
            try {
                MainGUI.startOperation(patient, (OperatingRoom) rooms.getSelectedItem());
                PatientInfoGUI.refreshData(); //<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
                frame.dispose();
                PatientInfoGUI.close();
            } catch (IllegalStateException e) {
                JOptionPane.showMessageDialog(frame, e.getMessage());
                frame.dispose();
                PatientInfoGUI.close();
            }
        });
        canvas.add(confirm);
        frame.pack();
        frame.setVisible(true);
    }

}
