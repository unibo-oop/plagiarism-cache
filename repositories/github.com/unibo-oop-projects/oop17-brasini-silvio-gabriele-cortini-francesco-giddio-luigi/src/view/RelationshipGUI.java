package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.GUIsize;
import model.GUIsizeImpl;

/**
 * Finestra per definire la relazione tra due tabelle.
 * @author silviobrasini
 *
 */
public class RelationshipGUI extends JFrame {

    private JButton btnOK;
    private JComboBox<String> jcbTab1;
    private JComboBox<String> jcbTab2;
    private JComboBox<String> jcbAtt1;
    private JComboBox<String> jcbAtt2;
    private JLabel labelTab1;
    private JLabel labelTab2;
    private JLabel labelAtt1;
    private JLabel labelAtt2;
    private JLabel labelCard1;
    private JLabel labelCard2;
    private JTextField jtfCard11;
    private JTextField jtfCard12;
    private JTextField jtfCard21;
    private JTextField jtfCard22;
    private JPanel panel;
    private JPanel panelBody;
    private JPanel panelOK;
    
    private GUIsizeImpl model;

    /**
     * Costruttore di RelationshipGUI
     */
    public RelationshipGUI() {

        this.model = new GUIsizeImpl(this);
        this.model.setSize();

        this.setTitle("ResourceBundle.getText(RelationshipGUI_Title)");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.panel = new JPanel(new BorderLayout());
        this.panelBody = new JPanel(new GridLayout(0, 2));
        this.panelOK = new JPanel(new FlowLayout(FlowLayout.CENTER));

        this.labelTab1 = new JLabel("Select tab 1:");
        this.labelTab2 = new JLabel("Select tab 2:");
        this.jcbTab1 = new JComboBox<>();
        this.jcbTab2 = new JComboBox<>();
        this.labelAtt1 = new JLabel("Attribute 1:");
        this.labelAtt2 = new JLabel("Attribute 2:");
        this.jcbAtt1 = new JComboBox<>();
        this.jcbAtt2 = new JComboBox<>();
        this.labelCard1 = new JLabel("Cardinality 1:");
        this.labelCard2 = new JLabel("Cardinality 2:");
        this.jtfCard11 = new JTextField();
        this.jtfCard21 = new JTextField();
        this.jtfCard12 = new JTextField();
        this.jtfCard22 = new JTextField();
        
        this.btnOK = new JButton("OK");

        this.panelBody.add(this.labelTab1);
        this.panelBody.add(this.labelTab2);
        this.panelBody.add(this.jcbTab1);
        this.panelBody.add(this.jcbTab2);
        this.panelBody.add(this.labelAtt1);
        this.panelBody.add(this.labelAtt2);
        this.panelBody.add(this.jcbAtt1);
        this.panelBody.add(this.jcbAtt2);
        this.panelBody.add(this.labelCard1);
        this.panelBody.add(this.labelCard2);
        this.panelBody.add(this.jtfCard11);
        this.panelBody.add(this.jtfCard21);
        this.panelBody.add(this.jtfCard12);
        this.panelBody.add(this.jtfCard22);
        this.panelOK.add(this.btnOK);
        this.panel.add(this.panelBody, BorderLayout.CENTER);
        this.panel.add(this.panelOK, BorderLayout.SOUTH);

        this.getContentPane().add(this.panel);
        //this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public static void main(final String[] args) {

        new RelationshipGUI();
    }
}
