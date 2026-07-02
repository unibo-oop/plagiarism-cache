package view.menu.data.setting;

import java.awt.Component;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSeparator;

import model.world.initialization.Modality;

public class AddModality extends JPanel implements AddElemValue<Modality>{
    
    /**
     */
    private static final long serialVersionUID = -411327285799347463L;
    private Modality modSelect = null;
    private List<JButton> buttons = new LinkedList<>();

    public AddModality() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(addButtons());
        this.add(new JSeparator());
    }

    private JPanel addButtons() {
        JPanel panel = new JPanel();
        Arrays.asList(Modality.values()).forEach(mod -> {
            JButton jb = new JButton(mod.toString());
            if (mod == Modality.SINGLE_PHOTOSYNTHESIS_CELL) {
                jb.setEnabled(false);
                modSelect = Modality.SINGLE_PHOTOSYNTHESIS_CELL;
            }
            jb.addActionListener(a -> {
                JButton jbSelect = (JButton) a.getSource();
                buttons.forEach(bt -> bt.setEnabled(true));
                jbSelect.setEnabled(false);
                modSelect = Modality.valueOf(jbSelect.getText());
            });
            panel.add(jb);
            buttons.add(jb);
        });
        return panel;
    }

    @Override
    public final AddModality getElem() {
        return this;
    }

    @Override
    public final Modality getValue() {
        return modSelect;
    }

}
