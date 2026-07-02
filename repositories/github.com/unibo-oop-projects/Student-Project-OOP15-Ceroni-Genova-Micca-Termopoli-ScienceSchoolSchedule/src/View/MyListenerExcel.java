package View;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Controller.ExportToXls;

/**
 * This Class implements ActionListener and override the actionPerformed method.
 * The method provide to generate new Frame and export all of the items saved in
 * to the table and file, in Excel file.
 * 
 * @author Anna Termopoli
 *
 */
public class MyListenerExcel implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {

        ExportToXls xls = new ExportToXls();
        final JFrame frameSave = new JFrame("ESPORTA IN EXCEL");
        frameSave.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frameSave.setVisible(true);
        frameSave.setSize(400, 150);
        frameSave.setResizable(false);
        final JPanel panelSave = new JPanel(new GridBagLayout());
        final JLabel text = new JLabel("Inserisci nome file:  ");
        final JTextField exelTitle = new JTextField(15);
        exelTitle.setToolTipText("ESEMPIO: 1° semestre 15-16");
        JPanel panelBut = new JPanel();
        final JButton save = new JButton("Salva");

        final GridBagConstraints cost = new GridBagConstraints();
        cost.gridx = 0;
        cost.gridy = 1;
        cost.fill = GridBagConstraints.BOTH;
        panelSave.add(text, cost);
        cost.gridx = 1;
        panelSave.add(exelTitle, cost);
        panelBut.add(save);

        save.addActionListener(f -> {
            try {

                String s = exelTitle.getText();
                xls.save(s);
                JOptionPane.showMessageDialog(null,
                        "Il file è stato salvato nel percorso:  " + (System.getProperty("user.home")
                                + System.getProperty("file.separator") + s + ".xlsx"),
                        null, JOptionPane.INFORMATION_MESSAGE);

            } catch (Exception e1) {
                JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.OK_OPTION);
            }
            frameSave.dispose();

        });
        frameSave.add(panelBut, BorderLayout.SOUTH);
        frameSave.add(panelSave);
        frameSave.setVisible(true);

    }

}
