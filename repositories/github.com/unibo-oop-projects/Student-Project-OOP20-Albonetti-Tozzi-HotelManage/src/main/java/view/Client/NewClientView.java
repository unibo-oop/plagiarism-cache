package view.Client;

import controller.Client.*;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class NewClientView extends JFrame {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private static final int SCREEN_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width;
    private static final int SCREEN_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height;
    private final  JPanel contentPane;
    private final JTextField textField = new JTextField();
    private final JTextField textField1 = new JTextField();
    private final JTextField  textField2 = new JTextField();
    private final JLabel label = new JLabel("Inserire i dati del nuovo cliente");
    private final JLabel labelname = new JLabel("Nome:");
    private final JLabel labelsurname = new JLabel("Cognome:");
    private final JLabel labelid = new JLabel("Codice Fiscale:");
    private final JLabel label2 = new JLabel("Registrazione non avvenuta");
    private final JButton buttonreg = new JButton("Registra");
    private String name;
    private String surname;
    private String id;
    
    /**
     * Create the frame.
     */
    public NewClientView() {
        ControllerClient client = new ControllerClientImpl();
        setForeground(Color.WHITE);
        setTitle("Client");
        setBackground(Color.WHITE);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, SCREEN_WIDTH / 2, SCREEN_HEIGHT / 2);
        contentPane = new JPanel();
        contentPane.setToolTipText("");
        contentPane.setBackground(Color.WHITE);
        contentPane.setForeground(Color.BLACK);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        label.setFont(new Font("Microsoft Tai Le", Font.BOLD, 11));
        labelname.setFont(new Font("Tahoma", Font.BOLD, 14));
        labelsurname.setFont(new Font("Tahoma", Font.BOLD, 14));
        labelid.setFont(new Font("Tahoma", Font.BOLD, 14));
        
        textField.setColumns(10);
        textField1.setColumns(10); 
        textField2.setColumns(10);

        buttonreg.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(final MouseEvent e) {
                        name = textField.getText();
                        surname = textField1.getText();
                        id = textField2.getText();
                        if (client.searchClient(id)) {
                            label2.setText("Cliente già esistente");
                        }
                        else if (!name.isEmpty() &&  !surname.isEmpty() && !id.isEmpty()) {
                            client.insertClient(name, surname, id);
                            label2.setText("Registrazione avvenuta");
                        }
                        else {
                            label2.setText("Registrazione non avvenuta"); 
                        }
                }
        });
        GroupLayout glcontentPane = new GroupLayout(contentPane);
        glcontentPane.setHorizontalGroup(
            glcontentPane.createParallelGroup(Alignment.TRAILING)
                .addComponent(label, GroupLayout.DEFAULT_SIZE, 934, Short.MAX_VALUE)
                .addGroup(glcontentPane.createSequentialGroup()
                    .addComponent(labelname, GroupLayout.PREFERRED_SIZE, 144, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addComponent(textField, GroupLayout.PREFERRED_SIZE, 144, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(642, Short.MAX_VALUE))
                .addGroup(glcontentPane.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(label2, GroupLayout.DEFAULT_SIZE, 792, Short.MAX_VALUE)
                    .addGap(30)
                    .addComponent(buttonreg, GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE))
                .addGroup(Alignment.LEADING, glcontentPane.createSequentialGroup()
                    .addGroup(glcontentPane.createParallelGroup(Alignment.LEADING)
                        .addGroup(glcontentPane.createSequentialGroup()
                            .addComponent(labelsurname, GroupLayout.PREFERRED_SIZE, 144, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(ComponentPlacement.RELATED)
                            .addComponent(textField1, GroupLayout.PREFERRED_SIZE, 144, GroupLayout.PREFERRED_SIZE))
                        .addGroup(glcontentPane.createSequentialGroup()
                            .addComponent(labelid, GroupLayout.PREFERRED_SIZE, 144, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(ComponentPlacement.RELATED)
                            .addComponent(textField2, GroupLayout.PREFERRED_SIZE, 144, GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap(642, Short.MAX_VALUE))
        );
        glcontentPane.setVerticalGroup(
            glcontentPane.createParallelGroup(Alignment.LEADING)
                .addGroup(glcontentPane.createSequentialGroup()
                    .addComponent(label)
                    .addPreferredGap(ComponentPlacement.UNRELATED)
                    .addGroup(glcontentPane.createParallelGroup(Alignment.BASELINE)
                        .addComponent(labelname, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                        .addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(14)
                    .addGroup(glcontentPane.createParallelGroup(Alignment.BASELINE)
                        .addComponent(labelsurname, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                        .addComponent(textField1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(18)
                    .addGroup(glcontentPane.createParallelGroup(Alignment.BASELINE)
                        .addComponent(labelid, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                        .addComponent(textField2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(ComponentPlacement.RELATED, 324, Short.MAX_VALUE)
                    .addGroup(glcontentPane.createParallelGroup(Alignment.TRAILING)
                        .addComponent(buttonreg)
                        .addComponent(label2))
                    .addContainerGap())
        );
        contentPane.setLayout(glcontentPane);
    }

}
