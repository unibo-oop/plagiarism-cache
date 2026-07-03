package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 * Finestra in cui scegliere il path del database.
 * 
 * @author silviobrasini
 *
 */
public class LocalRemote extends JPanel {

    private ButtonGroup group;
    private JLabel jl;
    private JLabel jlLocal;
    private JTextField JtfRemote;
    private JTextField jtf;
    private JPanel panelLabel;
    private JPanel panelBody;

    /**
     * Costruttore di LocalRemote.
     */
    public LocalRemote(final JButton btn) {
        this.setLayout(new BorderLayout());
        //btn.addActionListener(e -> Main.setPanel());
        this.jl = new JLabel("Local or remote:");
        this.panelLabel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        this.panelLabel.add(this.jl);
        this.add(this.panelLabel, BorderLayout.NORTH);
        this.panelBody = new JPanel(new GridLayout(0, 1));
        JRadioButton option1 = new JRadioButton("Local path:");
        JRadioButton option2 = new JRadioButton("Local");
        JRadioButton option3 = new JRadioButton("Remote path:");
        option1.setSelected(true);
        this.group = new ButtonGroup();
        this.group.add(option1);
        this.group.add(option2);
        this.group.add(option3);
        this.JtfRemote = new JTextField("http://");
        this.jtf = new JTextField();
        this.panelBody.add(option1);
        this.panelBody.add(this.jtf);
        this.panelBody.add(option2);
        this.panelBody.add(option3);
        this.panelBody.add(this.JtfRemote);
        this.add(this.panelBody, BorderLayout.CENTER);
    }
}
