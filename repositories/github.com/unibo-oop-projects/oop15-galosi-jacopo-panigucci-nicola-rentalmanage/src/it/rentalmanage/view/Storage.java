package it.rentalmanage.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Created by nicolapanigucci on 29/02/16.
 */
public class Storage extends JPanel {

    private JButton btnAdd;
    private JPanel pnNorth;
    private GridBagConstraints cnst;

    public Storage(){
        this.setLayout(new GridBagLayout());

        this.pnNorth = new JPanel(new FlowLayout(FlowLayout.LEFT));

        this.cnst = new GridBagConstraints();
        this.cnst.gridx = 0;
        this.cnst.gridy = 0;
        this.cnst.weightx = 1.0;
        this.cnst.fill = GridBagConstraints.HORIZONTAL;

        this.btnAdd = new JButton(new ImageIcon("Images/add.png"));
        this.btnAdd.setPreferredSize(new Dimension(55, 55));
        this.btnAdd.setBackground(pnNorth.getBackground());

        pnNorth.add(this.btnAdd);

        this.add(pnNorth, this.cnst);
    }

    /**
     * Imposta la tabella da visualizzare
     * @param component
     */
    protected void setTable(JComponent component) {
        this.cnst = new GridBagConstraints();
        this.cnst.gridx = 0;
        this.cnst.gridy = 1;
        this.cnst.weightx = 1.0;
        this.cnst.weighty = 1.0;
        this.cnst.fill = GridBagConstraints.BOTH;

        this.add(component, this.cnst);
    }

    /**
     * Setta il listener al btnAdd
     * @param listener
     */
    protected void setAddListener(ActionListener listener) {
        this.btnAdd.addActionListener(listener);
    }
}
