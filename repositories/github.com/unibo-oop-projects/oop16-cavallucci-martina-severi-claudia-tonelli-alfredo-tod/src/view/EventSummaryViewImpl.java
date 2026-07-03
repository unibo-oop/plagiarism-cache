package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import controller.EventInfo;
import controller.EventSummaryController;
import lu.tudor.santec.jtimechooser.JTimeChooser;
import model.Contact;

/**
 * Class that create a view for manage an event.
 *
 */
public class EventSummaryViewImpl extends JFrame implements EventSummaryView, ActionListener {

    private static final long serialVersionUID = -6233970522863948083L;
    private static final String[] EVENT_TABLE_TITLES = { "Description", "Start time"};
    private static final int CF_COLUMN_WIDTH = 70;
    private final EventSummaryController observer;
    private Color background1;
    private Color background2;
    private Font font1;
    private JPanel contentPane1;
    private JLabel lblDate;
    private JLabel lblDateClick;
    private JLabel lblDescription;
    private JTextArea txtDescription;
    private JLabel lblStartTime;
    private JTimeChooser txtStartTime;
    private JLabel lblPlace;
    private JTextField txtPlace;
    private JLabel lbEndTime;
    private JTimeChooser txtEndTime;
    private JPanel welcomePanel;
    private JPanel firstPanel;
    private JPanel secondPanel;
    private JPanel thirdPanel;
    private JPanel login;
    private EtchedBorder border;
    private JPanel fourthPanel;
    private DualListBox fifthPanel;
    private JPanel appointementPanel;

    private final JButton btnCancel;
    private final JButton btnSave;
    private JPanel sixthPanel;
    private JButton btnCancelOperation;
    private final JPanel panel;
    private final JPanel showEventPanel;
    private JPanel tablePane;
    private JPanel east;
    private JTable table;
    private String[][] nameSurnamePasses;
    private static final int DIMFONT = 12;
    private static final int NUMROW = 6;
    private static final int DIMAREA = 35;
    private static final int DIMFIELD = 20;
    private static final double DIMTABLE11 = 0.4;
    private static final double DIMTABLE2 = 0.3;

    /**
     * The Class for the summary view.
     * @param ctrl
     *          the eventSummaryController
     * @param day
     *          the day that the controller refers to
     * @param list
     *          the list of contact for this user
     * @param data
     *           the description and date of the event.
     */
    public EventSummaryViewImpl(final EventSummaryController ctrl,
            final  String day, final List<Contact> list, final Object[][] data) {

        this.setTitle("Event summary");
        this.setVisible(true);
        this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.observer = ctrl;
        this.background1 = new Color(ViewColor.light.getRed(),
                ViewColor.light.getGreen(), ViewColor.light.getBlue());
        this.background2 = new Color(ViewColor.lightblue.getRed(),
                ViewColor.lightblue.getGreen(), ViewColor.lightblue.getBlue());
        this.font1 = new Font("Euphemia", Font.BOLD, DIMFONT);
        this.setBackground(this.background1);
        this.setLayout(new BorderLayout());

        this.contentPane1 = new JPanel(new GridLayout(NUMROW, 1));
        this.panel = new JPanel(new BorderLayout());

        this.lblDate = new JLabel("Date:");
        this.lblDateClick = new JLabel(day);

        this.lblDate.setBackground(this.background1);
        this.lblDateClick.setBackground(this.background1);
        this.lblDate.setFont(this.font1);
        this.lblDateClick.setFont(this.font1);
        this.contentPane1.setBackground(this.background1);

        this.lblDescription = new JLabel("Description:");
        this.lblDescription.setFont(this.font1);
        this.txtDescription = new JTextArea(4, DIMAREA);
        this.txtDescription.setBorder(new TitledBorder(border, "")); 
        this.txtDescription.setLineWrap(true);

        this.lblStartTime = new JLabel("Start time: ");
        this.lblStartTime.setFont(this.font1);
        this.txtStartTime = new JTimeChooser();


        this.lblPlace = new JLabel("Place:");

        this.lblPlace.setFont(this.font1);
        this.lblPlace.setBackground(this.background1);
        this.txtPlace = new JTextField(DIMFIELD);
        this.lbEndTime = new JLabel("EndTime:");
        this.lbEndTime.setFont(this.font1);
        this.txtEndTime = new JTimeChooser();


        this.welcomePanel = new JPanel(new GridLayout(0, 2));
        this.welcomePanel.setBackground(this.background1);
        this.welcomePanel.add(this.lblDate);
        this.welcomePanel.add(this.lblDateClick);

        this.firstPanel = new JPanel(new GridLayout(0, 2));
        this.firstPanel.setBackground(this.background1);
        this.firstPanel.add(this.lblDescription);
        this.firstPanel.add(this.txtDescription);
        this.secondPanel = new JPanel(new GridLayout(0, 2));
        this.secondPanel.setBackground(this.background1);
        this.secondPanel.add(this.lblStartTime);
        this.secondPanel.add(this.txtStartTime);


        this.thirdPanel = new JPanel(new GridLayout(0, 2));
        this.thirdPanel.setBackground(this.background1);
        this.thirdPanel.add(this.lblPlace);
        this.thirdPanel.add(this.txtPlace);

        this.login = new JPanel(new GridLayout(2, 1));
        this.login.setBackground(this.background1);
        this.login.add(this.firstPanel, BorderLayout.NORTH);
        this.login.add(this.secondPanel, BorderLayout.NORTH);


        this.fourthPanel = new JPanel(new GridLayout(0, 2));
        this.fourthPanel.setBackground(this.background1);
        this.fourthPanel.add(this.lbEndTime);
        this.fourthPanel.add(this.txtEndTime);

        this.appointementPanel = new JPanel(new GridLayout(3, 1));
        this.border  = new EtchedBorder(this.background1, this.background2);
        this.appointementPanel.setBorder(new TitledBorder(border, "Appointement"));
        this.appointementPanel.add(this.thirdPanel);
        this.appointementPanel.add(this.fourthPanel);
        this.appointementPanel.setBackground(this.background1);
        String s = null;
        this.fifthPanel = new DualListBox();
        if (!list.isEmpty()) {
        for (final Contact contact: list) {
             s = s + contact.getName() + " " + contact.getSurname() + ",";
         }
        final String f = s.substring(4, s.length());
        final String[] c = f.split(",");
        this.fifthPanel.addSourceElements(c);
        } else {
            String c = null;
            c =  " " + " ";
            final String[] f = c.split(" ");
            this.fifthPanel.addSourceElements(f);
        }
        this.sixthPanel = new JPanel(new FlowLayout());
        this.btnSave = new JButton("Save event");
        this.btnSave.addActionListener(this);
        this.btnSave.setBackground(background2);

        this.btnCancel = new JButton("Cancel event");
        this.btnCancel.setBackground(background2);
        this.btnCancelOperation = new JButton("Cancel");
        this.btnCancel.addActionListener(this);
        this.btnCancelOperation.addActionListener(this);
        this.sixthPanel.add(this.btnSave);
        this.sixthPanel.add(this.btnCancel);
        this.sixthPanel.add(this.btnCancelOperation);
        this.sixthPanel.setBackground(this.background1);

        this.contentPane1.add(this.welcomePanel);
        this.contentPane1.add(this.login);
        this.contentPane1.add(this.appointementPanel);
        this.contentPane1.add(this.fifthPanel);
        this.contentPane1.add(this.sixthPanel);
        this.contentPane1.setBackground(this.background1);


        this.showEventPanel = new JPanel();
        this.showEventPanel.setBackground(new Color(ViewColor.light.getRed(), 
                ViewColor.light.getGreen(),
                ViewColor.light.getBlue()));
        this.showEventPanel.setLayout(new BoxLayout(this.showEventPanel, BoxLayout.X_AXIS));
        this.showEventPanel.setBorder(new TitledBorder("Show event"));
        this.tablePane = new JPanel(new FlowLayout());
        this.tablePane.setBackground(new Color(ViewColor.light.getRed(), ViewColor.light.getGreen(),
                ViewColor.light.getBlue()));
        this.east = new JPanel(new BorderLayout());
        this.east.add(this.tablePane, BorderLayout.NORTH);
        this.east.setBorder(new TitledBorder("Show event"));

        this.east.setBackground(new Color(ViewColor.light.getRed(), ViewColor.light.getGreen(),
                ViewColor.light.getBlue()));

        this.panel.add(this.east, BorderLayout.CENTER);

        this.panel.add(contentPane1, BorderLayout.WEST);

        this.add(panel);

        this.rebuildTable(data);
        this.pack();
    }

    private void rebuildTable(final Object[][] data) {
        final DefaultTableModel dtm = new DefaultTableModel(data, EVENT_TABLE_TITLES) {

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
            this.observer.selectEvent(row);
        });
        this.tablePane.removeAll();
       final JScrollPane tableScroll = new JScrollPane(this.table);
        tableScroll.setPreferredSize(new Dimension((int) (Toolkit.getDefaultToolkit()
                .getScreenSize().getWidth() * DIMTABLE11), 
                (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() * DIMTABLE2)));

        this.tablePane.add(wrapperPanel(tableScroll, FlowLayout.CENTER));
        this.validate();
        this.repaint();

    }

    private static JPanel wrapperPanel(final JComponent component, final int orientation) {
        final JPanel panel = new JPanel(new FlowLayout(orientation));
        panel.add(component);
        panel.setBackground(new Color(ViewColor.light.getRed(), ViewColor.light.getGreen(),
                ViewColor.light.getBlue()));
        return  panel;
    }

    @Override
    public void init() {
      this.setEnabled(true);
    }

    @Override
    public void actionPerformed(final ActionEvent e) {
        final Object isPressed = e.getSource();
         if (isPressed == this.btnSave) {
            this.observer.saveChanges(convertMap(), convertList());
        } else if (isPressed == this.btnCancel) {
            this.observer.cancelEvent();
        } else if (isPressed == this.btnCancelOperation) {
           this.quit(); 
        }

    }

    private String[][] convertList() {

           String[] dest = {""};
           for (int i = 0; i < this.fifthPanel.getDestListModel().getSize(); i++) {
               for (int j = 0; j < this.fifthPanel.getDestListModel().getSize(); j++) {
                   dest[j] = (String) this.fifthPanel.getDestListModel().getElementAt(i);
               }

           }
          nameSurnamePasses = null;
           for (int i = 0; i < dest.length; i++) {
               final String[] token = dest.toString().split(",");
               final String[] token2 = token.toString().split(" ");

               for (int j = 0; j < token2.length; j = j + 2) {
                   for (int k = 0; k <  token2.length / 2; k++) {
                       nameSurnamePasses[k][0] = token2[j];
                       nameSurnamePasses[k][1] = token2[j + 1];
                   }
               }
            }
           return nameSurnamePasses;
    }
    @Override
    public void quit() {
        this.setVisible(false);
        this.dispose();

    }
    private Map<EventInfo, String> convertMap() {
       final Map<EventInfo, String> map = new HashMap<>();
        map.put(EventInfo.DESCRIPTION, this.txtDescription.getText());
        map.put(EventInfo.PLACE, this.txtPlace.toString());
        final int h = this.txtStartTime.getHours();
        final int m = this.txtStartTime.getMinutes();
        final int s = this.txtStartTime.getSeconds();
        final String start = Integer.toString(h) + ":"
        + Integer.toString(m) + ":" + Integer.toString(s);
        map.put(EventInfo.STARTTIME, start);
        final int he = this.txtStartTime.getHours();
        final int me = this.txtStartTime.getMinutes();
        final int se = this.txtStartTime.getSeconds();
        final String end = Integer.toString(he) + ":"
        + Integer.toString(me) + ":" + Integer.toString(se);
        map.put(EventInfo.ENDTIME, end);
        return map;
    }
    /**
     * .
     * @return String[]
     */
    public static String[] getContactTableTitles() {
        return EVENT_TABLE_TITLES;
    }
}