package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;

import java.util.HashMap;
import java.util.Map;
import controller.ContactInfo;
import javax.swing.BoxLayout;
import javax.swing.JButton;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import controller.ContactController;

import org.jdesktop.swingx.JXDatePicker;
import org.joda.time.LocalDate;


/**
 * Class that make a view for create, delete and show Contact.
 *
 */
public class ContactViewImpl extends JPanel implements ContactView {

    private static final int SIZE_H = (Toolkit.getDefaultToolkit().getScreenSize().height);
    private static final int SIZE_W = (Toolkit.getDefaultToolkit().getScreenSize().width / 3);
    private static final String[] CONTACT_TABLE_TITLES = { "Name", "Surname", "DateOfBith",
            "Address", "PhoneNumber", "Email"};
    private static final int TEXT_LENGHT = 20;
    private static final int CF_COLUMN_WIDTH = 70;
    private static final long serialVersionUID = -1582455860803755004L;
    private final JButton btnAddContact;
    private final JButton rmvContact;
    private final JButton modify;
    private final ContactController observer;
    private final JPanel tablePane;
    private final JPanel east;
    private JTable table;
    private final JPanel eastSouth;
    private final JPanel addContactPane;
    private final JPanel contactContainer;
    private final JPanel leftBox;
    private final JLabel lblName;
    private final JLabel lblSurname;
    private final JLabel lblDate;
    private final JLabel lblAddress;
    private final JLabel lblTelephone;
    private final JLabel lblEmail;
    private final JPanel rightBox;
    private final JTextField txtName;
    private final JTextField txtSurname;
    private final JXDatePicker datePicker;
    private final JTextField txtAddress;
    private final JTextField txtTelefhone;
    private final JTextField txtEmail;
    private  final JPanel container;
    private  final JPanel bordContactContainer;
    private final JPanel showContactPane;
    private static final double DB1 = 0.4;
    private static final double DB2 = 0.3;
    /**
     * The constructor of the class.
     * @param ctrl
     *        the to do controller
     * @param data
     *        a matrix of to do information
     */
    public ContactViewImpl(final ContactController ctrl, final Object[][] data) {

       this.observer = ctrl;

        this.setLayout(new BorderLayout());
        this.addContactPane = new JPanel();
        this.addContactPane.setBackground(new Color(ViewColor.light.getRed(),
                ViewColor.light.getGreen(),
                ViewColor.light.getBlue()));
        this.addContactPane.setLayout(new BoxLayout(this.addContactPane, BoxLayout.X_AXIS));
        this.addContactPane.setBorder(new TitledBorder("Add a new contact"));
        this.addContactPane.setPreferredSize(new Dimension(SIZE_W, SIZE_H));

        this.contactContainer = new JPanel();
        this.contactContainer.setLayout(new BoxLayout(this.contactContainer, BoxLayout.X_AXIS));

        this.leftBox = new JPanel();
        this.leftBox.setLayout(new BoxLayout(this.leftBox, BoxLayout.Y_AXIS));

        this.lblName = new JLabel("*Name:");
        this.lblSurname = new JLabel("*Surname:");
        this.lblDate = new JLabel("Date of Birth:");
        this.lblAddress = new JLabel("Address:");
        this.lblTelephone = new JLabel("Phone Number:");
        this.lblEmail = new JLabel("Email:");

        leftBox.add(wrapperPanel(this.lblName, FlowLayout.LEFT));
        leftBox.add(wrapperPanel(this.lblSurname, FlowLayout.LEFT));
        leftBox.add(wrapperPanel(this.lblDate, FlowLayout.LEFT));
        leftBox.add(wrapperPanel(this.lblAddress, FlowLayout.LEFT));
        leftBox.add(wrapperPanel(this.lblTelephone, FlowLayout.LEFT));
        leftBox.add(wrapperPanel(this.lblEmail, FlowLayout.LEFT));

        this.rightBox = new JPanel();
        this.txtName = new JTextField(TEXT_LENGHT);
        this.txtSurname = new JTextField(TEXT_LENGHT);
        this.datePicker = new JXDatePicker();

        this.txtAddress = new JTextField(TEXT_LENGHT);
        this.txtTelefhone = new JTextField(TEXT_LENGHT);
        this.txtEmail = new JTextField(TEXT_LENGHT);

        this.rightBox.setLayout(new BoxLayout(this.rightBox, BoxLayout.Y_AXIS));
        this.rightBox.add(wrapperPanel(this.txtName, FlowLayout.RIGHT));
        this.rightBox.add(wrapperPanel(this.txtSurname, FlowLayout.RIGHT));
        this.rightBox.add(wrapperPanel(this.datePicker, FlowLayout.RIGHT));
        this.rightBox.add(wrapperPanel(this.txtAddress, FlowLayout.RIGHT));
        this.rightBox.add(wrapperPanel(this.txtTelefhone, FlowLayout.RIGHT));
        this.rightBox.add(wrapperPanel(this.txtEmail, FlowLayout.RIGHT));

        this.contactContainer.add(this.leftBox);
        this.contactContainer.add(this.rightBox);

        this.btnAddContact = new JButton("Confirm");
        this.btnAddContact.setBackground(new Color(ViewColor.lightblue.getRed(), 
                ViewColor.lightblue.getGreen(),
                ViewColor.lightblue.getBlue()));
        this.btnAddContact.addActionListener(e -> {

           final LocalDate date = new LocalDate(this.datePicker.getDate());

                this.observer.confirmChanges(this.convertMap(), date);
                this.txtName.setText("");
                this.txtSurname.setText("");
                this.txtTelefhone.setText("");
                this.txtAddress.setText("");
                this.txtEmail.setText("");
                this.datePicker.setDate(null);
        });
        this.container = new JPanel();
        this.container.setBackground(new Color(ViewColor.light.getRed(), 
                ViewColor.light.getGreen(),
                ViewColor.light.getBlue()));
        this.container.setLayout(new BoxLayout(this.container, BoxLayout.Y_AXIS));
        this.container.add(this.contactContainer);
        this.container.add(wrapperPanel(this.btnAddContact, FlowLayout.CENTER));

        this.bordContactContainer = new JPanel(new BorderLayout());
        this.bordContactContainer.setBackground(new Color(ViewColor.light.getRed(),
                ViewColor.light.getGreen(),
                ViewColor.light.getBlue()));
        this.bordContactContainer.add(this.container, BorderLayout.NORTH);
        this.addContactPane.add(this.bordContactContainer);
        this.add(this.addContactPane, BorderLayout.WEST);


        this.showContactPane = new JPanel();
        this.showContactPane.setBackground(new Color(ViewColor.light.getRed(), 
                ViewColor.light.getGreen(),
                ViewColor.light.getBlue()));
        this.showContactPane.setLayout(new BoxLayout(this.showContactPane, BoxLayout.X_AXIS));
        this.showContactPane.setBorder(new TitledBorder("Show to do"));
        this.tablePane = new JPanel(new FlowLayout());
        this.tablePane.setBackground(new Color(ViewColor.light.getRed(), ViewColor.light.getGreen(),
                ViewColor.light.getBlue()));
        this.east = new JPanel(new BorderLayout());

        this.east.setBorder(new TitledBorder("Show contact"));
        this.rmvContact = new JButton("Delete contact");
        this.rmvContact.setBackground(new Color(ViewColor.lightblue.getRed(),
                ViewColor.lightblue.getGreen(),
                ViewColor.lightblue.getBlue()));
        this.modify = new JButton("Modify contact");
        this.modify.setBackground(new Color(ViewColor.lightblue.getRed(),
                ViewColor.lightblue.getGreen(),
                ViewColor.lightblue.getBlue()));
       this.modify.addActionListener(e-> {
       final LocalDate date = new LocalDate(this.datePicker.getDate());
       this.observer.modifyContact(convertMap(), date);
       });
        this.rmvContact.addActionListener(e -> {

            this.observer.removeContact();
        });
        this.east.add(this.tablePane, BorderLayout.NORTH);
        this.east.setBackground(new Color(ViewColor.light.getRed(), ViewColor.light.getGreen(),
                ViewColor.light.getBlue()));
        this.eastSouth = new JPanel(new FlowLayout());
        this.eastSouth.setBackground(new Color(ViewColor.light.getRed(), 
                ViewColor.light.getGreen(),
                ViewColor.light.getBlue()));
        this.eastSouth.add(this.rmvContact);
        this.eastSouth.add(this.modify);
        this.east.add(this.eastSouth, BorderLayout.CENTER);
        this.add(this.east, BorderLayout.CENTER);

        this.rebuildTable(data);
 

}
    private Map<ContactInfo, String> convertMap() {
        final Map<ContactInfo, String> mapToPass = new HashMap<>();
        mapToPass.put(ContactInfo.NAME, this.txtName.getText());
        mapToPass.put(ContactInfo.SURNAME, this.txtSurname.getText());
        mapToPass.put(ContactInfo.ADDRESS, this.txtAddress.getText());
        mapToPass.put(ContactInfo.PHONENUMBER, this.txtTelefhone.getText());
        mapToPass.put(ContactInfo.EMAIL, this.txtEmail.getText());

        return mapToPass;
    }
    @Override
    public void rebuildTable(final Object[][] data) {

        final DefaultTableModel dtm = new DefaultTableModel(data, CONTACT_TABLE_TITLES) {

            private static final long serialVersionUID = -3607203150074239908L;

            @Override
            public boolean isCellEditable(final int row, final int column) {
                return false;
            }
        };
        this.table = new JTable(dtm);
        this.table.setBackground(new Color(ViewColor.light.getRed(), ViewColor.light.getGreen(),
                ViewColor.light.getBlue()));
        this.table.getColumnModel().getColumn(1).setPreferredWidth(CF_COLUMN_WIDTH);
        this.table.getSelectionModel().addListSelectionListener(x-> {
            final int row = this.table.convertRowIndexToModel(this.table.getSelectedRow());
            this.observer.selectContact(row);
        });
        this.tablePane.removeAll();
       final JScrollPane tableScroll = new JScrollPane(this.table);
        tableScroll.setPreferredSize(new Dimension((int) (Toolkit.getDefaultToolkit()
                .getScreenSize().getWidth() * DB1), 
                (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() * DB2)));

        this.tablePane.add(wrapperPanel(tableScroll, FlowLayout.CENTER));
        this.validate();
        this.repaint();
    }
    @Override
    public void init() {
       this.setEnabled(true);

    }
    private static JPanel wrapperPanel(final JComponent component, final int orientation) {
        final JPanel panel = new JPanel(new FlowLayout(orientation));
        panel.add(component);
        panel.setBackground(new Color(ViewColor.light.getRed(), ViewColor.light.getGreen(),
                ViewColor.light.getBlue()));
        return  panel;
    }
    /**
     * .
     * @return String[]
     *          the name of the column table.
     */
    public static String[] getContactTableTitles() {
        return CONTACT_TABLE_TITLES;
    }

}