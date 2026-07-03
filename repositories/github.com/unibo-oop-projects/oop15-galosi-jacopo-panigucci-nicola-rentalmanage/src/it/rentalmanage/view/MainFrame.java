package it.rentalmanage.view;

import it.rentalmanage.model.IModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Created by nicolapanigucci on 22/02/16.
 */
public class MainFrame extends JFrame implements IMainFrame {

    private JButton btnHome;
    private JButton btnBack;
    private JPanel pannelFoot;
    private JPanel actualPanel = null;
    private Dimension dimBtns;
    private GridBagConstraints cnst;

    public MainFrame(final IModel model){

        this.setSize(730,530);
        this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setTitle("RentalManage");

        this.getContentPane().setLayout(new GridBagLayout());
        this.cnst = new GridBagConstraints();

        this.dimBtns = new Dimension(55, 55);

        this.pannelFoot = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        this.pannelFoot.setPreferredSize(new Dimension(this.pannelFoot.getPreferredSize().width, 68));

        this.btnHome = new JButton(new ImageIcon("Images/home.png"));
        this.btnHome.setPreferredSize(dimBtns);
        this.btnHome.setBackground(pannelFoot.getBackground());

        this.btnBack = new JButton(new ImageIcon("Images/btnBack.png"));
        btnBack.setPreferredSize(dimBtns);
        btnBack.setBackground(pannelFoot.getBackground());
        btnBack.setVisible(false);

        setPanel(new HomePage(this, model));

        this.btnHome.addActionListener(e -> setPanel(new HomePage(MainFrame.this, model)));

        pannelFoot.add(btnBack);
        pannelFoot.add(btnHome);

        this.cnst.gridx = 0;
        this.cnst.gridy = 1;
        this.cnst.fill = GridBagConstraints.HORIZONTAL;
        this.cnst.weightx = 1.0;
        this.add(pannelFoot, cnst);

        this.setLocationRelativeTo(null);
        this.setVisible(true);

    }

    @Override
    public void setPanel(JPanel newPanel){

        if(this.actualPanel != null){
            this.remove(this.actualPanel);
        }

        this.actualPanel = newPanel;

        GridBagConstraints cnst = new GridBagConstraints();
        cnst.gridx = 0;
        cnst.gridy = 0;
        cnst.fill = GridBagConstraints.BOTH;
        cnst.weightx = 1.0;
        cnst.weighty = 1.0;
        this.add(this.actualPanel, cnst);

        revalidate();
    }

    /**
     * Abilita il bottone e gestisce un evento
     */
    protected void setVisibleBtnBackListener(boolean visible, ActionListener listener){
        for (ActionListener a : this.btnBack.getActionListeners()){
            this.btnBack.removeActionListener(a);
        }

        this.btnBack.setVisible(visible);
        this.btnBack.addActionListener(listener);
    }
}
