package view;

import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import controller.ControlCore;
import controller.ControlCoreImpl;
import model.ModelLanguage;
import model.ModelLanguageImpl;

/**
 * Finestra in cui scegliere la lingua.
 * 
 * @author silviobrasini
 *
 */
public class SelectLanguage {

    private final JFrame frame = new JFrame();
    private final JComboBox<String> combo = new JComboBox<>();
    private final ModelLanguage ml;

    private void resetCombo() {
        this.combo.removeAllItems();
        ml.getListLanguage().forEach(this.combo::addItem);
        this.combo.setSelectedIndex(0);
        frame.pack();
    }

    /**
     * 
     * @param cc
     *            control core
     */
    public SelectLanguage(final ControlCore cc) {

        ml = new ModelLanguageImpl(cc.getPathSeparator());
        this.resetCombo();

        frame.setLocationByPlatform(true);

        frame.setTitle("SelectLanguage");
        JButton button = new JButton("ok");
        button.addActionListener(e -> {
            cc.setLanguage(this.ml.getSelectedLangauge(this.combo.getSelectedIndex()));
            frame.setVisible(false);
            //new ConnectionGUI(cc);
        });
        JPanel pane = new JPanel();
        FlowLayout fl = new FlowLayout();
        fl.setAlignment(FlowLayout.LEFT);
        pane.setLayout(fl);
        pane.add(this.combo);
        pane.add(button);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(pane);
        frame.pack();
        frame.setVisible(true);
    }

    private void display() {
        // TODO Auto-generated method stub
        this.frame.setVisible(true);
    }

    public static void main(final String[] args) {
        ControlCore cc = new ControlCoreImpl();
        SelectLanguage sg = new SelectLanguage(cc);
        sg.display();
    }
}
