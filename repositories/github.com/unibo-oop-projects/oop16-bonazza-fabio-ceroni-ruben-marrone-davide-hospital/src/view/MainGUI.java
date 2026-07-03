package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;

import controller.Controller;
import model.Doctor;
import model.OperatingRoom;
import model.Patient;
import model.Ward;

/**
 * Main Gui, which opens all the others and gets called by Application.
 *
 */
public class MainGUI extends JFrame implements ActionListener, MainGUIInterface {
    /**
     * serialVersionUID to allow to save and load files.
     */
    private static final long serialVersionUID = 737862318607157490L;
    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private int width = (int) screenSize.getWidth();
    private int height = (int) screenSize.getHeight();
    private JButton addPatient, addDoctor, load, save;
    private static JButton waitPat, doctors, hosPat, opRooms, quit;
    private static JFrame frame;
    private static JPanel panel, center;
    private JScrollPane scroll;
    private final Float stdFont = 12.0f;
    private final Float largeFont = 25.0f;
    private final Float font2 = 15.0f;
    private final Color orange = new Color(180, 80, 220);
    private final Color green = new Color(30, 140, 30);
    private GUIFactory factory = new MyGUIFactory();

    private static Controller controller;
/**
 * @param c the controller of the application.
 */
    public MainGUI(final Controller c) {
        controller = c;
        frame = new JFrame();
        frame.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        frame.setSize(width / 2, height / 2);
        frame.setLocationByPlatform(true);
        frame.setTitle("Main view");
        frame.setLayout(new BorderLayout());
        frame.setMinimumSize(frame.getSize());

        panel = factory.createGridPanel();
        frame.add(panel, BorderLayout.WEST);
        center = factory.createBoxPanel();
        scroll = factory.createScrollPane(center);
        frame.add(scroll, BorderLayout.CENTER);

        load = factory.createButton("Load");
        load.addActionListener(this);
        panel.add(load);
        save = factory.createButton("Save");
        save.addActionListener(this);
        panel.add(save);
        JSeparator sep1 = factory.createSep();
        panel.add(sep1);
        addPatient = factory.createButton("Add patient");
        addPatient.addActionListener(this);
        panel.add(addPatient);
        addDoctor = factory.createButton("Add doctor");
        addDoctor.addActionListener(this);
        panel.add(addDoctor);
        JSeparator sep2 = factory.createSep();
        panel.add(sep2);
        waitPat = factory.createButton("List waiting patients");
        waitPat.addActionListener(this);
        panel.add(waitPat);
        doctors = factory.createButton("List doctors");
        doctors.addActionListener(this);
        panel.add(doctors);
        hosPat = factory.createButton("List hospital patients");
        hosPat.addActionListener(this);
        panel.add(hosPat);
        opRooms = factory.createButton("List operating rooms");
        opRooms.addActionListener(this);
        panel.add(opRooms);
        JSeparator sep3 = factory.createSep();
        panel.add(sep3);
        quit = factory.createButton("Quit program");
        quit.addActionListener(this);
        panel.add(quit);
        frame.setVisible(true);
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(final WindowEvent e) {
                int confirmed = JOptionPane.showConfirmDialog(null, 
                    "Are you sure you want to quit?\nUnsaved changes will be lost.", "Quit Program?",
                    JOptionPane.YES_NO_OPTION);
                if (confirmed == JOptionPane.YES_OPTION) {
                  frame.dispose();
                }
              }
            });
    }

    @Override
    public void actionPerformed(final ActionEvent event) {
        if (event.getSource() == addPatient) {
            new AddPatientGUI();
        } else if (event.getSource() == addDoctor) {
            new AddDoctorGUI();
        } else if (event.getSource() == load) {
            JFileChooser fc = new JFileChooser();
            fc.showOpenDialog(this);
            try {
                controller.load(fc.getSelectedFile().toString());
                this.refreshWait();
            } catch (FileNotFoundException e) {
                JOptionPane.showMessageDialog(frame, "Error, file not found");
            } catch (ClassNotFoundException e) {
                JOptionPane.showMessageDialog(frame, "Error, class not found");
            } catch (IOException e) {
                JOptionPane.showMessageDialog(frame, "Error, invalid file");
            } catch (NullPointerException e) {
                //Filechooser was closed with no file selected.
            }
        } else if (event.getSource() == save) {
            JFileChooser fc = new JFileChooser();
            fc.showSaveDialog(this);
            try {
                controller.save(fc.getSelectedFile().toString());
            } catch (IOException e) {
                System.out.println("IO Exception");
            } catch (NullPointerException e) {
                //saving aborted
            }
        } else if (event.getSource() == waitPat) {
            this.refreshWait();
        } else if (event.getSource() == doctors) {
            this.refreshDoc();
        } else if (event.getSource() == hosPat) {
            this.refreshHosPat();
        } else if (event.getSource() == opRooms) {
            this.refreshOpRooms();
        } else if (event.getSource() == quit) {
            int confirmed = JOptionPane.showConfirmDialog(null, "Are you sure you want to quit?\nUnsaved changes will be lost.", "Quit Program?", JOptionPane.YES_NO_OPTION);
            if (confirmed == JOptionPane.YES_OPTION) {
              frame.dispose();
            }
        }
    }
    /**
     * Private method used to refresh waiting patients list on main GUI.
     */
    private void refreshWait() {
        center.removeAll();
        center.repaint();
        try {
            JLabel l = factory.createLabel("Waiting room patients list:", font2);
            center.add(l);
            center.add(factory.createSep());
            controller.getWaitingPatients().forEach(p -> {
                center.add(factory.createLabel("Name: " + p.getName(), stdFont));
                center.add(factory.createLabel("Surname: " + p.getSurname(), stdFont));
                JLabel prio = factory.createLabel("Priority: " + p.getPriorityName(), stdFont);
                prio.setOpaque(true);
                center.add(prio);
                if (p.getPriorityLevel() == 3) {
                    prio.setForeground(Color.RED);
                } else if (p.getPriorityLevel() == 2) {
                    prio.setForeground(orange);
                } else if (p.getPriorityLevel() == 1) {
                    prio.setForeground(green);
                }
                JButton info = factory.createButton("View info");
                info.addActionListener(b -> new PatientInfoGUI(p, true));
                center.add(info);
                center.add(factory.createLabel("", stdFont));
                center.add(factory.createSep());
            });
            center.revalidate();
        } catch (IllegalStateException e) {
            center.removeAll();
            center.repaint();
            center.add(factory.createLabel(e.getMessage(), stdFont));
            center.revalidate();
        }
    }
    /**
     * Public method to refresh waiting patients list on main GUI.
     */
    public static void refreshWaiting() {
        waitPat.doClick();
    }
    /**
     * Private method to refresh doctors list.
     */
    private void refreshDoc() {
        center.removeAll();
        center.repaint();
        try {
            JLabel l = factory.createLabel("Doctors list:", font2);
            center.add(l);
            center.add(factory.createSep());
            controller.getDoctors().forEach(p -> {
                center.add(factory.createLabel("Name: " + p.getName(), stdFont));
                center.add(factory.createLabel("Surname: " + p.getSurname(), stdFont));
                JButton info = factory.createButton("View info");
                info.addActionListener(b -> new DoctorInfoGUI(p));
                center.add(info);
                center.add(factory.createLabel("", stdFont));
                center.add(factory.createSep());
            });
            center.revalidate();
        } catch (IllegalStateException e) {
            center.removeAll();
            center.repaint();
            center.add(factory.createLabel(e.getMessage(), stdFont));
            center.revalidate();
        }
    }
    /**
     * Public method to refresh doctors list.
     */
    public static void refreshDoctors() {
        doctors.doClick();
    }
    /**
     * Private method to refresh hospital patients list.
     */
    private void refreshHosPat() {
        center.removeAll();
        center.repaint();
        try {
            controller.getWards().forEach(w -> {
                center.add(factory.createSep());
                JLabel l = factory.createLabel(w.toString(), largeFont);
                center.add(l);
                center.add(factory.createSep());
                w.getOccupiedRooms().forEach((n, p) -> {
                    center.add(factory.createLabel("Name: " + p.getName(), stdFont));
                    center.add(factory.createLabel("Surname: " + p.getSurname(), stdFont));
                    center.add(factory.createLabel("Room: " + n.toString(), stdFont));
                    JButton info = factory.createButton("View info");
                    info.addActionListener(b -> new PatientInfoGUI(p, false));
                    center.add(info);
                    center.add(factory.createLabel("", stdFont));
                    center.add(factory.createSep());
                });
            });
            center.revalidate();
        } catch (IllegalStateException e) {
            center.removeAll();
            center.repaint();
            center.add(factory.createLabel(e.getMessage(), stdFont));
            center.revalidate();
        }
    }
    /**
     * Public methos to refresh hospital patients list.
     */
    public static void refreshHospitalPatients() {
        hosPat.doClick();
    }
    /**
     * Private method to refresh operating rooms list.
     */
    private void refreshOpRooms() {
        center.removeAll();
        center.repaint();
        try {
            JLabel t = factory.createLabel("Operating rooms list:", font2);
            center.add(t);
            center.add(factory.createSep());
            controller.getOperatingRooms().forEach(r -> {
                JLabel l = factory.createLabel("Name: " + r.getName(), stdFont);
                center.add(l);
                if (r.getPatient() != null) {
                    center.add(factory.createLabel("Current patient: " + r.getPatient().getName() + " " + r.getPatient().getSurname(), stdFont));
                    center.add(factory.createLabel("Current doctor: " + r.getDoctor().getName() + " " + r.getDoctor().getSurname(), stdFont));
                    JButton remove = factory.createButton("Free room");
                    remove.addActionListener(a -> {
                        controller.endOperation(r);
                        this.refreshOpRooms();
                        PatientInfoGUI.close();
                    });
                    center.add(remove);
                    JButton info = factory.createButton("View patient's info");
                    info.addActionListener(a -> {
                        new PatientInfoGUI(r.getPatient(), false);
                    });
                } else {
                    center.add(factory.createLabel("Available for use", stdFont));
                }
                center.add(factory.createSep());
            });
            center.revalidate();
        } catch (IllegalStateException e) {
            center.removeAll();
            center.repaint();
            center.add(factory.createLabel(e.getMessage(), stdFont));
            center.revalidate();
        }
    }
    /**
     * Public method to refresh operating rooms list.
     */
    public static void refreshOperatingRooms() {
        opRooms.doClick();
    }
    /**
    *
    * @param name        name.
    * @param surname     surname.
    * @param codFis      fiscal number.
    * @param sex         sex.
    * @param disease     disease.
    * @param age         age.
    * @param priority    priorità.
    * @throws IllegalStateException      if patient already exists.
    * @throws IllegalArgumentException   if arguments are invalid.
    */
   public static void createPatient(final String name, final String surname, final String codFis, final String sex,
                                    final String disease, final int age, final String priority) throws IllegalStateException, IllegalArgumentException {
       controller.createPatient(name, surname, codFis, sex, disease, age, priority);
   }
   /**.
    * 
    * @param patient   the patient to dismiss.
    * @throws IllegalStateException    exception is thrown if patient is being operated, meaning he can't be removed.
    */
   public static void dismissPatient(final Patient patient) throws IllegalStateException {
       controller.dismissPatient(patient);
   }
   /**
    * 
    * @param name name.
    * @param surname surname.
    * @param codFis fiscal number.
    * @param sex sex.
    * @param age age.
    * @param specialization specialization.
    * @throws IllegalStateException doctor already exists.
    * @throws IllegalArgumentException input is not correct.
    */
   public static void createDoctor(final String name, final String surname, final String codFis, final String sex,
                                   final int age, final String specialization) throws IllegalStateException, IllegalArgumentException {
       controller.createDoctor(name, surname, codFis, age, sex, specialization);
   }
   /**
    * 
    * @param doctor doctor.
    * @throws IllegalStateException if the doctor still has assigned patients.
    */
   public static void fireDoctor(final Doctor doctor) throws IllegalStateException {
       controller.fireDoctor(doctor);
   }

   /**
    * 
    * @return doctors.
    * @throws IllegalStateException exceltion.
    */
   public static List<Doctor> getDoctors() throws IllegalStateException {
       return controller.getDoctors();
   }
   /**
    * 
    * @return list of all wards.
    * @throws IllegalStateException exception
    */
   public static List<Ward> getWards() throws IllegalStateException {
       return controller.getWards();
   }
   /**
    * 
    * @return returns a list of all free operating rooms.
    * @throws IllegalStateException if there are no free operating rooms.
    */
   public static List<OperatingRoom> getFreeOperatingRooms() {
       return controller.getfreeOperatingRooms();
   }
   /**
    * 
    * @param patient   patient who starts surgery.
    * @param room      romm in which he's being operatoed
    * @throws IllegalStateException if patient or room don't exist.
    */
   public static void startOperation(final Patient patient, final OperatingRoom room) throws IllegalStateException {
       controller.startOperation(patient, room);
   }
   /**
    * @param patient the Patient to assign
    * @param doctor  the Doctor to whom the Patient is assigned
    * @param ward    the Ward in which the Patient will be placed
    * @throws IllegalStateException if arguments are not valid.
    */
   public static void assignPatient(final Patient patient, final Doctor doctor, final Ward ward) throws IllegalStateException {
       controller.assignPatient(patient, doctor, ward);
   }
}

