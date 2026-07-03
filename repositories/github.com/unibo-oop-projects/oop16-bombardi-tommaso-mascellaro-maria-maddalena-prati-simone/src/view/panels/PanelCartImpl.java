package view.panels;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import model.admin.Pair;
import view.panels.interfaces.PanelCart;

/**
 * 
 * panel cart class.
 *
 */
public class PanelCartImpl extends JPanel implements PanelCart {

    private static final long serialVersionUID = 1L;
    private final JButton btnRetMenu;
    private final JButton btnFinishOp;
    private final DefaultTableModel model;
    private final JLabel labPrice;
    private final JTextField text;
    private final JButton btnEmptyCart;
    private final JButton btnDeleteOp;
    private int count;
    //COLORE
    private static final int C1 = 171;
    private static final int C2 = 205;
    private static final int C3 = 239;

    /**
     * panel cart constructor.
     */
    public PanelCartImpl() {
        super();
        this.count = 0;
        final JPanel panelSouth = new JPanel();
        final GridBagLayout layout = new GridBagLayout();
        final GridBagConstraints lim = new GridBagConstraints();
        this.setLayout(layout);
        //TABELLA CARRELLO
        this.model = new DefaultTableModel();
        model.addColumn("Num op."); 
        model.addColumn("Tipo op."); 
        model.addColumn("Dettaglio op.");
        model.addColumn("Costo op.");
        final JTable table = new JTable(model);
        table.setEnabled(false);
        table.getColumn("Num op.").setPreferredWidth((int) (FrameSize.WIDTH.getValue() / Dimension.W_C19.getDimension()));
        table.getColumn("Tipo op.").setPreferredWidth((int) (FrameSize.WIDTH.getValue() / Dimension.W_C12.getDimension()));
        table.getColumn("Dettaglio op.").setPreferredWidth((int) (FrameSize.WIDTH.getValue() / Dimension.W_C13.getDimension()));
        table.getColumn("Costo op.").setPreferredWidth((int) (FrameSize.WIDTH.getValue() / Dimension.W_C17.getDimension()));
        final JScrollPane sp = new JScrollPane(table);
        final Component c0 = sp;
        lim.gridx = 0;
        lim.gridy = 0;
        lim.weightx = 1;
        lim.weighty = 1;
        lim.ipadx = FrameSize.WIDTH.getValue();
        lim.ipady = (int) (FrameSize.HEIGHT.getValue() / Dimension.H_C4.getDimension());
        lim.fill = GridBagConstraints.NONE;
        lim.anchor = GridBagConstraints.NORTH;
        layout.setConstraints(c0, lim);
        this.add(c0);
        //SFONDO
        final Color bluette = new Color(C1, C2, C3);
        this.setBackground(bluette);
        //OPERAZIONE DA ELIMINARE
        final JLabel lab = new JLabel("Operazione da eliminare");
        lab.setBounds((int) (FrameSize.WIDTH.getValue() / Dimension.W_C21.getDimension()), (int) (FrameSize.HEIGHT.getValue() / Dimension.H_C27.getDimension()), (int) (FrameSize.WIDTH.getValue() / Dimension.W_C12.getDimension()), (int) (FrameSize.HEIGHT.getValue() / Dimension.H_C22.getDimension()));
        lab.setFont(new Font("Tahoma", Font.PLAIN,  (int) (FrameSize.WIDTH.getValue() / Dimension.W_C23.getDimension())));
        panelSouth.add(lab);
        this.text = new JTextField();
        this.text.setBounds((int) (FrameSize.WIDTH.getValue() / Dimension.H_C16.getDimension()), (int) (FrameSize.HEIGHT.getValue() / Dimension.H_C27.getDimension()), (int) (FrameSize.WIDTH.getValue() / Dimension.W_C20.getDimension()), (int) (FrameSize.HEIGHT.getValue() / Dimension.H_C22.getDimension()));
        panelSouth.add(text);
        this.btnDeleteOp = new JButton("Elimina operazione");
        this.btnDeleteOp.setBounds((int) (FrameSize.WIDTH.getValue() / Dimension.W_C9.getDimension()), (int) (FrameSize.HEIGHT.getValue() / Dimension.H_C27.getDimension()), FrameSize.WIDTH.getValue() / 4, (int) (FrameSize.HEIGHT.getValue() / Dimension.H_C22.getDimension()));
        panelSouth.add(btnDeleteOp);
        //BOTTONE SVUOTA VARRELLO
        this.btnEmptyCart = new JButton("Svuota il carrello");
        this.btnEmptyCart.setBounds((int) (FrameSize.WIDTH.getValue() / Dimension.W_C3.getDimension()), (int) (FrameSize.HEIGHT.getValue() / Dimension.H_C27.getDimension()), FrameSize.WIDTH.getValue() / 4, (int) (FrameSize.HEIGHT.getValue() / Dimension.H_C22.getDimension()));
        panelSouth.add(btnEmptyCart);
        //LABEL PREZZO
        this.labPrice = new JLabel();
        this.labPrice.setBounds((int) (FrameSize.WIDTH.getValue() / Dimension.W_C21.getDimension()), (int) (FrameSize.HEIGHT.getValue() / Dimension.W_C18.getDimension()), (int) (FrameSize.WIDTH.getValue() / Dimension.W_C12.getDimension()), (int) (FrameSize.HEIGHT.getValue() / Dimension.H_C22.getDimension()));
        this.labPrice.setFont(new Font("Tahoma", Font.PLAIN, (int) (FrameSize.WIDTH.getValue() / Dimension.FONT_LAB3.getDimension())));
        panelSouth.add(labPrice);
        //BOTTONE CONCLUDI ACQUISTO
        this.btnFinishOp = new JButton("Concludi l'acquisto");
        this.btnFinishOp.setBounds((int) (FrameSize.WIDTH.getValue() / Dimension.W_C9.getDimension()), (int) (FrameSize.HEIGHT.getValue() / Dimension.W_C18.getDimension()), (int) (FrameSize.WIDTH.getValue() / Dimension.W_C12.getDimension()), (int) (FrameSize.HEIGHT.getValue() / Dimension.H_C22.getDimension()));
        panelSouth.add(btnFinishOp);
        //BOTTONE PAGINA PRECEDENTE
        this.btnRetMenu = new JButton("Pagina precedente");
        this.btnRetMenu.setBounds((int) (FrameSize.WIDTH.getValue() / Dimension.W_C3.getDimension()), (int) (FrameSize.HEIGHT.getValue() / Dimension.W_C18.getDimension()), (int) (FrameSize.WIDTH.getValue() / Dimension.W_C12.getDimension()), (int) (FrameSize.HEIGHT.getValue() / Dimension.H_C22.getDimension()));
        panelSouth.add(btnRetMenu);
        final Component c1 = panelSouth;
        lim.gridx = 0;
        lim.gridy = 1;
        lim.weightx = 1;
        lim.weighty = (int) (FrameSize.HEIGHT.getValue() / Dimension.H_C4.getDimension());
        lim.ipadx = 0;
        lim.ipady = (int) (FrameSize.HEIGHT.getValue() / Dimension.H_C4.getDimension());
        lim.fill = GridBagConstraints.HORIZONTAL;
        lim.anchor = GridBagConstraints.CENTER;
        layout.setConstraints(c1, lim);
        this.add(c1);
        panelSouth.setLayout(null);
        this.setVisible(false);
    }
    @Override
    public void addTableRow(final Integer num,  final Pair<String, Pair<String, String>> price) {
        model.addRow(new Object[]{num, price.getY().getX(), price.getY().getY(), price.getX() + " Euro"});
        this.count++;
    }
    @Override
    public void deleteOp(final Integer i) {
        model.removeRow(i - 1);
    }
    @Override
    public void deleteTable() {
        for (int i = this.count - 1; i >= 0; i--) {
            model.removeRow(i);
        }
        this.count = 0;
    }
    @Override
    public void addLabelPrice(final String price) {
        this.labPrice.setText(price + " Euro");
    }
    @Override
    public JPanel getPanelCart() {
        return this;
    }
    @Override
    public JButton getBtnPrev() {
        return this.btnRetMenu;
    }
    @Override
    public JButton getBtnFinishOp() {
        return this.btnFinishOp;
    }
    @Override
    public JButton getBtnDeleteCart() {
        return this.btnEmptyCart;
    }
    @Override
    public JButton getBtnDeleteOperation() {
        return this.btnDeleteOp;
    }
    @Override
    public String getOperationDelete() {
        return this.text.getText();
    }
    @Override
    public JTextField getOp() {
        return this.text;
    }

}
