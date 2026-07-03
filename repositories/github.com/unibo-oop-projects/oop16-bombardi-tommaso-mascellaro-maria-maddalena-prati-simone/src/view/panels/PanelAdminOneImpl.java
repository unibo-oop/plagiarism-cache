package view.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.admin.Pair;
import view.panels.interfaces.PanelAdminOne;

/**
 * panel admin one class.
 *
 */
public class PanelAdminOneImpl extends JPanel implements PanelAdminOne {

    private static final long serialVersionUID = 1L;
    private final JLabel labNameAmm;
    private final JLabel labgain;
    private final JLabel labgain1;
    private final JButton btnRetMenu;
    private final JButton btnAllOperation;
    private final JButton btnOperationUser;
    private final JButton btnOperationType;
    private final JButton btnOperationUserType;
    private final JButton btnLogout;
    private static final String FONT = "Tahoma";
    private static final int FONT_LAB = FrameSize.WIDTH.getValue() / 20;
    private static final int FONT_LAB2 = FrameSize.WIDTH.getValue() / 30;
    private static final int FONT_LAB3 = FrameSize.WIDTH.getValue() / 35;

    //COLORE
    private static final int C1 = 171;
    private static final int C2 = 205;
    private static final int C3 = 239;
    private static final int ROWS = 8;
    private static final int COL = 1;
    private static final int HGAP = FrameSize.HEIGHT.getValue() / 45;
    private static final int VGAP = FrameSize.WIDTH.getValue() / 40;
    private static final int BOR = FrameSize.WIDTH.getValue() / 40;
    /**
     * panel admin one constructor.
     */
    public PanelAdminOneImpl() {
        super();
        this.setLayout(new BorderLayout());
        final JPanel pn = new JPanel();
        final JPanel ps = new JPanel();
        final JPanel pc = new JPanel();

        this.add(pn, BorderLayout.NORTH);
        this.add(pc, BorderLayout.CENTER);
        this.add(ps, BorderLayout.SOUTH);

        pc.setLayout(new GridLayout(ROWS, COL, HGAP, VGAP));
        this.setBorder(BorderFactory.createEmptyBorder(BOR, BOR, BOR, BOR));

        this.labNameAmm = new JLabel();
        this.labNameAmm.setFont(new Font(FONT, Font.PLAIN, FONT_LAB));
        pn.add(labNameAmm);

        final JLabel emp = new JLabel("Scegli cosa visualizzare");
        emp.setFont(new Font(FONT, Font.PLAIN, FONT_LAB2));
        emp.setHorizontalAlignment(JLabel.CENTER);
        pc.add(emp);

        this.btnAllOperation = new JButton("Tutte le operazioni");
        this.btnAllOperation.setFont(new Font(FONT, Font.PLAIN, FONT_LAB2));
        this.btnAllOperation.setOpaque(true);
        pc.add(btnAllOperation);

        this.btnOperationType = new JButton("Le operazioni per tipo");
        this.btnOperationType.setFont(new Font(FONT, Font.PLAIN, FONT_LAB2));
        pc.add(btnOperationType);

        this.btnOperationUser = new JButton("Le operazioni per utente");
        this.btnOperationUser.setFont(new Font(FONT, Font.PLAIN, FONT_LAB2));
        pc.add(btnOperationUser);

        this.btnOperationUserType = new JButton("Le operazioni per utente e per tipo");
        this.btnOperationUserType.setFont(new Font(FONT, Font.PLAIN, FONT_LAB2));
        pc.add(btnOperationUserType);

        this.labgain = new JLabel("");
        this.labgain.setFont(new Font(FONT, Font.PLAIN, FONT_LAB2));
        pc.add(labgain);

        this.labgain1 = new JLabel("");
        this.labgain1.setFont(new Font(FONT, Font.PLAIN, FONT_LAB2));
        pc.add(labgain1);

        this.btnRetMenu = new JButton("Torna al menu'");
        this.btnRetMenu.setFont(new Font(FONT, Font.PLAIN, FONT_LAB3));

        this.btnLogout = new JButton("Logout");
        this.btnLogout.setFont(new Font(FONT, Font.PLAIN, FONT_LAB3));
        ps.add(btnLogout);
        ps.add(btnRetMenu);


        final Color bluette = new Color(C1, C2, C3);
        pn.setBackground(bluette);
        pc.setBackground(bluette);
        ps.setBackground(bluette);
        this.setBackground(bluette);
        this.setVisible(false);
    }
    @Override
    public void setLabel(final Pair<String, String> gain) {
        this.labgain.setText("Incasso totale del negozio: " + gain.getX() + " Euro");
        this.labgain1.setText("Guadagno totale del negozio: " + gain.getY() + " Euro");
    }
    @Override
    public void addLabelAmm(final String name) {
        this.labNameAmm.setText("Benvenuto " + name);
    }
    @Override
    public JPanel getPanelAdminOne() {
        return this;
    }

    @Override
    public JButton getBtnPrev() {
        return this.btnRetMenu;
    }

    @Override
    public JButton getBtnAllOperation() {
        return this.btnAllOperation;
    }
    @Override
    public JButton getBtnOperationUser() {
        return this.btnOperationUser;
    }

    @Override
    public JButton getBtnOperationType() {
        return this.btnOperationType;
    }

    @Override
    public JButton getBtnOperationUserType() {
        return this.btnOperationUserType;
    }

    @Override
    public JButton getBtnLogout() {
        return this.btnLogout;
    }
}