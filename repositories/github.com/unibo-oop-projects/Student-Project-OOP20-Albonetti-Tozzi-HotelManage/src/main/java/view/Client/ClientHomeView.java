package view.Client;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Toolkit;

import javax.swing.UIManager;

public class ClientHomeView extends JFrame {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private static final int SCREEN_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width;
    private static final int SCREEN_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height;
    /**
     * Variables.
     */
    private final JPanel contentPane;
    private final JButton buttonnewclient = new JButton("Nuovo Cliente");
    private final JButton buttonsearchclient = new JButton("Cerca Cliente");
    private final JLabel label = new JLabel("Scegliere quale azione effettuare");

    /**
     * Create the frame.
     */
    public ClientHomeView() {
        setBackground(Color.WHITE);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, SCREEN_WIDTH / 2, SCREEN_HEIGHT / 2);
        contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(null);
        setContentPane(contentPane);
        buttonnewclient.setForeground(Color.BLACK);
        buttonnewclient.setBackground(UIManager.getColor("Table.selectionBackground"));
        buttonsearchclient.setBackground(UIManager.getColor("Table.selectionBackground"));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setFont(label.getFont().deriveFont(label.getFont().getSize() + 7f));
        buttonnewclient.addActionListener(new ActionListener() {
                public void actionPerformed(final ActionEvent e) {
                        System.out.println("Sto aprendo la view perc registrare il nuovo cliente");
                        NewClientView nuovo = new NewClientView();
                        nuovo.setVisible(true);
                }
        });
        buttonsearchclient.addActionListener(new ActionListener() {
                public void actionPerformed(final ActionEvent e) {
                        System.out.println("Sto aprendo la view per registrare cercare il cliente");
                        FindClientView cerca = new FindClientView();
                        cerca.setVisible(true);
                }
        });

        GroupLayout glcontentPane = new GroupLayout(contentPane);
        glcontentPane.setHorizontalGroup(
            glcontentPane.createParallelGroup(Alignment.TRAILING)
                .addGroup(glcontentPane.createSequentialGroup()
                    .addGap(60)
                    .addComponent(label, GroupLayout.DEFAULT_SIZE, 843, Short.MAX_VALUE)
                    .addGap(41))
                .addGroup(Alignment.LEADING, glcontentPane.createSequentialGroup()
                    .addGap(382)
                    .addGroup(glcontentPane.createParallelGroup(Alignment.TRAILING)
                        .addComponent(buttonsearchclient, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(buttonnewclient, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 181, GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(381, Short.MAX_VALUE))
        );
        glcontentPane.setVerticalGroup(
            glcontentPane.createParallelGroup(Alignment.LEADING)
                .addGroup(glcontentPane.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(label, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
                    .addGap(26)
                    .addComponent(buttonnewclient, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                    .addGap(18)
                    .addComponent(buttonsearchclient, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(321, Short.MAX_VALUE))
        );
        contentPane.setLayout(glcontentPane);
    }

}
