package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Arrays;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.ControlCore;
import controller.ControlCoreImpl;
import model.GUIsize;
import model.GUIsizeImpl;

/**
 * Finestra di connessione a un database presente in locale.
 * @author silviobrasini
 *
 */
public class ConnectToDatabase extends JFrame {

    private JPanel panel;
    private JPanel panelHead;
    private JPanel panelBody;
    private JPanel panelOK;
    private JButton btnOK;
    private JButton btnOpen;
    private JTextField jtf;
    private JTextField jtfUser;
    private JTextField jtfPassw;
    private JLabel labelTitle;
    private JLabel labelPath;
    private JLabel labelType;
    private JLabel labelUser;
    private JLabel labelPassw;
    private JComboBox<String> jcb;
    private GUIsize model;
    
    private Locale currentLocale;
    private ResourceBundle text;

    /**
     * Costruttore di ConnectToDatabase.
     */
    public ConnectToDatabase() {
        
        this.currentLocale = new Locale("it", "IT");
        this.text = ResourceBundle.getBundle("TextBundle", this.currentLocale);
        
        this.model = new GUIsizeImpl(this);
        this.model.setSize();
        String title = this.text.getString("Connect_To_Database_Title");
        this.setTitle(title); //Connect to database
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.panel = new JPanel(new BorderLayout());
        this.panelHead = new JPanel(new FlowLayout(FlowLayout.CENTER));
        this.panelBody = new JPanel(new GridLayout(0, 1));
        this.panelOK = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        this.labelTitle = new JLabel(""); //Connect to database
        this.panelHead.add(this.labelTitle);
        this.labelPath = new JLabel("model.getText(label_Path)"); //Path:
        this.jtf = new JTextField();
        this.btnOpen = new JButton("Open...");
        this.btnOpen.addActionListener(new OpenFileChooser(this.jtf));
        this.labelType = new JLabel("model.getText(label_Type)"); //Type:
        this.jcb = new JComboBox<>();
        this.jcb.addItem("Access");
        this.jcb.addItem("MongoDB");
        this.labelUser = new JLabel("User:"); //User:
        this.jtfUser = new JTextField();
        this.labelPassw = new JLabel("Password:");
        this.jtfPassw = new JTextField();
        this.panelBody.add(this.labelPath);
        this.panelBody.add(this.jtf);
        this.panelBody.add(btnOpen);
        this.panelBody.add(this.labelType);
        this.panelBody.add(this.jcb);
        this.panelBody.add(this.labelUser);
        this.panelBody.add(this.jtfUser);
        this.panelBody.add(this.labelPassw);
        this.panelBody.add(this.jtfPassw);
        this.btnOK = new JButton("OK");
        this.panelOK.add(this.btnOK);
        this.panel.add(this.panelHead, BorderLayout.NORTH);
        this.panel.add(this.panelBody, BorderLayout.CENTER);
        this.panel.add(this.panelOK, BorderLayout.SOUTH);
        this.getContentPane().add(this.panel);

        //this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private class OpenFileChooser implements ActionListener {

        private JTextField jtf;

        OpenFileChooser(final JTextField jtf) {
            this.jtf = jtf;
        }

        public void actionPerformed(final ActionEvent e) {
            JFileChooser fileChooser = new JFileChooser();
            int select = fileChooser.showOpenDialog(ConnectToDatabase.this);
            if (select == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                this.jtf.setText(file.getAbsolutePath());
            }
        }
    }

    public static void main(final String[] args) {
        
        new ConnectToDatabase();
    }
}
