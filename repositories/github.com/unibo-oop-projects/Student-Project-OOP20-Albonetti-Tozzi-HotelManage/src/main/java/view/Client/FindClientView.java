package view.Client;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.Client.ControllerClient;
import controller.Client.ControllerClientImpl;
import model.client.Client;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.SwingConstants;
import javax.swing.JButton;

public class FindClientView extends JFrame {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private static final int SCREEN_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width;
    private static final int SCREEN_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height;
    private final JPanel contentPane;
    private final JTextField textfield;
    private final JLabel label = new JLabel("Inserisci il codice fiscale del cliente desiderato");
    private final JLabel lblNonTrovato = new JLabel("Non trovato");
    private final JButton buttonSearch = new JButton("Ricerca");
    private final JLabel lblCliente = new JLabel("Cliente:");
    private final JLabel labelName = new JLabel("Name");
    private final JLabel labelSurname = new JLabel("Surname");
    private final JLabel labelId = new JLabel("CF");
    private String id;
    private final JButton btnElimina = new JButton("Elimina");

    /**
     * Create the frame.
     */
    public FindClientView() {
        ControllerClient client = new ControllerClientImpl();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, SCREEN_WIDTH / 2, SCREEN_HEIGHT / 2);
        contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        label.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblNonTrovato.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblCliente.setFont(new Font("Tahoma", Font.BOLD, 12));
        labelName.setFont(new Font("Tahoma", Font.BOLD, 12));
        labelSurname.setFont(new Font("Tahoma", Font.BOLD, 12));
        labelId.setFont(new Font("Tahoma", Font.BOLD, 12));
        textfield = new JTextField();
        textfield.setHorizontalAlignment(SwingConstants.CENTER);
        textfield.setToolTipText("Codice Fiscale");
        textfield.setBackground(Color.WHITE);
        textfield.setColumns(10);
        buttonSearch.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(final MouseEvent e) {
                id = textfield.getText();
                if (client.searchClient(id.toLowerCase()) && !id.isEmpty()) {
                    lblNonTrovato.setText("Trovato");
                    Client c = client.getClient(id.toLowerCase());
                    labelName.setText("" + c.getName());
                    labelSurname.setText("" + c.getSurname());
                    labelId.setText("" + c.getId());
                }
                else{
                    lblNonTrovato.setText("Questo codice fiscale non è presente");
                }
            }
        });
        btnElimina.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(final MouseEvent e) {
                id = textfield.getText();
                if (client.deleteLine(id)) {
                    lblNonTrovato.setText("Eliminato");
                }
                else {
                    lblNonTrovato.setText("Non Eliminato");
                }
            }
        });


        GroupLayout glcontentPane = new GroupLayout(contentPane);
        glcontentPane.setHorizontalGroup(
            glcontentPane.createParallelGroup(Alignment.LEADING)
                .addGroup(glcontentPane.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(glcontentPane.createParallelGroup(Alignment.LEADING)
                        .addComponent(label, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 924, Short.MAX_VALUE)
                        .addGroup(Alignment.TRAILING, glcontentPane.createSequentialGroup()
                            .addComponent(lblNonTrovato, GroupLayout.PREFERRED_SIZE, 253, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(ComponentPlacement.RELATED, 561, Short.MAX_VALUE)
                            .addGroup(glcontentPane.createParallelGroup(Alignment.LEADING, false)
                                .addComponent(btnElimina, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(buttonSearch, GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE))
                            .addGap(10))
                        .addGroup(Alignment.TRAILING, glcontentPane.createSequentialGroup()
                            .addComponent(textfield, GroupLayout.PREFERRED_SIZE, 181, GroupLayout.PREFERRED_SIZE)
                            .addContainerGap(743, Short.MAX_VALUE))
                        .addGroup(glcontentPane.createSequentialGroup()
                            .addComponent(lblCliente, GroupLayout.PREFERRED_SIZE, 253, GroupLayout.PREFERRED_SIZE)
                            .addContainerGap(671, Short.MAX_VALUE))))
                .addGroup(glcontentPane.createSequentialGroup()
                    .addGap(51)
                    .addGroup(glcontentPane.createParallelGroup(Alignment.LEADING)
                        .addComponent(labelId, GroupLayout.PREFERRED_SIZE, 253, GroupLayout.PREFERRED_SIZE)
                        .addComponent(labelSurname, GroupLayout.PREFERRED_SIZE, 253, GroupLayout.PREFERRED_SIZE)
                        .addComponent(labelName, GroupLayout.PREFERRED_SIZE, 253, GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(630, Short.MAX_VALUE))
        );
        glcontentPane.setVerticalGroup(
            glcontentPane.createParallelGroup(Alignment.LEADING)
                .addGroup(glcontentPane.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(label)
                    .addPreferredGap(ComponentPlacement.UNRELATED)
                    .addComponent(textfield, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(ComponentPlacement.RELATED, 254, Short.MAX_VALUE)
                    .addComponent(lblCliente, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addComponent(labelName, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addComponent(labelSurname, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addComponent(labelId, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
                    .addGap(9)
                    .addComponent(btnElimina)
                    .addPreferredGap(ComponentPlacement.UNRELATED)
                    .addGroup(glcontentPane.createParallelGroup(Alignment.BASELINE)
                        .addComponent(buttonSearch)
                        .addComponent(lblNonTrovato, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
                    .addContainerGap())
        );
        contentPane.setLayout(glcontentPane);
    }
}
