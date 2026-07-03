package view.panels;


import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import model.admin.Pair;
import view.panels.interfaces.PanelTable;
/**
 * 
 * panel table class.
 *
 */
public class PanelTableImpl extends JPanel implements PanelTable {

    private static final long serialVersionUID = 1L;
    private final JButton btnRetMenu;
    private final DefaultTableModel model;
    private int count;

    /**
     * panel table constructor.
     */
    public PanelTableImpl() {
        super();
        this.count = 0;
        final BorderLayout bl = new BorderLayout();
        this.setLayout(bl);
        this.btnRetMenu = new JButton("Pagina precedente");
        this.btnRetMenu.setBounds((int) (FrameSize.WIDTH.getValue() / Dimension.W_C3.getDimension()), (int) (FrameSize.HEIGHT.getValue() / Dimension.H_C1.getDimension()), (int) (FrameSize.WIDTH.getValue() / Dimension.W_C12.getDimension()), (int) (FrameSize.HEIGHT.getValue() / Dimension.H_C21.getDimension()));
        this.add(btnRetMenu, BorderLayout.SOUTH);
        this.model = new DefaultTableModel();
        model.addColumn("Num op.");
        model.addColumn("Username"); 
        model.addColumn("Tipo op."); 
        model.addColumn("Dettaglio op."); 
        model.addColumn("Incasso op."); 
        model.addColumn("Guadagno op.");
        final JTable table = new JTable(model);
        table.setEnabled(false);
        table.getColumn("Num op.").setPreferredWidth((int) (FrameSize.WIDTH.getValue() / Dimension.W_C19.getDimension()));
        table.getColumn("Username").setPreferredWidth((int) (FrameSize.WIDTH.getValue() / Dimension.H_C21.getDimension()));
        table.getColumn("Tipo op.").setPreferredWidth((int) (FrameSize.WIDTH.getValue() / Dimension.W_C15.getDimension()));
        table.getColumn("Dettaglio op.").setPreferredWidth((int) (FrameSize.WIDTH.getValue() / Dimension.W_C10.getDimension()));
        table.getColumn("Incasso op.").setPreferredWidth((int) (FrameSize.WIDTH.getValue() / Dimension.W_C17.getDimension()));
        table.getColumn("Guadagno op.").setPreferredWidth((int) (FrameSize.WIDTH.getValue() / Dimension.W_C17.getDimension()));
        table.setBounds(0, 0, FrameSize.WIDTH.getValue() * 2, (int) (FrameSize.HEIGHT.getValue() / Dimension.H_C24.getDimension()));
        final JScrollPane sp = new JScrollPane(table);
        this.add(sp, BorderLayout.CENTER);
        this.setBounds(0, 0, FrameSize.WIDTH.getValue(), FrameSize.HEIGHT.getValue());
        this.setVisible(false);
    }
    @Override
    public void addTableRow(final Pair<Integer, String> oper, final String descr, final String detail, final Pair<String, String> gain) {
        model.addRow(new Object[]{oper.getX(), oper.getY(), descr, detail, gain.getX() + " Euro", gain.getY() + " Euro"});
        this.count++;
    }
    @Override
    public void deleteTable() {
        for (int i = this.count - 1; i >= 0; i--) {
            model.removeRow(i);
        }
        this.count = 0;
    }
    @Override
    public JPanel getPanelTable() {
        return this;
    }
    @Override
    public JButton getBtnPrev() {
        return this.btnRetMenu;
    }
}
