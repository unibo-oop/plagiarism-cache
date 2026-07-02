package boardgames.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.LayoutManager;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.border.TitledBorder;

public class PopUpFactory implements GameFrameFactory{
    
    //un factory non simple è quello che specifica le dimensioni anche del panel
    
    private final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    private static final double PERC_RESIZE_MENU = 0.170;

    @Override
    public JFrame createFrame(String title) {
        final JFrame jf = new JFrame(title);
        jf.setSize((int) (this.screenSize.getWidth() * PERC_RESIZE_MENU),
                (int) (this.screenSize.getHeight() * PERC_RESIZE_MENU));
        jf.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

        return jf;
    }

    @Override
    public JPanel cratePanel(String question) {
        final JPanel jp = new JPanel();
        jp.setLayout(new GridBagLayout());
        jp.setBorder(new TitledBorder(question));
        return jp;
    }

    @Override
    public JButton createButton(ActionListener al, String label) {
        final JButton jb = new JButton(label);
        jb.addActionListener(al);
        return jb;
    }

    @Override
    public JLabel createJLabel(String s) {
        final JLabel jl = new JLabel(s);
        return jl;
    }

 

}
