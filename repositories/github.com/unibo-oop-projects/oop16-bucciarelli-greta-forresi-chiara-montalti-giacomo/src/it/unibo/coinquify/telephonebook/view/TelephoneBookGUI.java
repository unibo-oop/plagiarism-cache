package it.unibo.coinquify.telephonebook.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.Properties;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import org.jdesktop.swingx.JXTable;

import it.unibo.coinquify.file.PhoneBookFile;
import it.unibo.coinquify.telephonebook.controller.TelephoneBookController;
import it.unibo.coinquify.telephonebook.controller.TelephoneBookControllerImpl;
import it.unibo.coinquify.telephonebook.model.Contact;
import it.unibo.coinquify.telephonebook.model.ContactImpl;
import it.unibo.coinquify.utils.ButtonColumn;
import it.unibo.coinquify.utils.DateLabelFormatter;
import it.unibo.coinquify.utils.JTextFieldLimit;
import it.unibo.coinquify.utils.Messages;
import it.unibo.coinquify.utils.PaneGUI;
import it.unibo.coinquify.utils.PhoneNumberPresentException;
import it.unibo.coinquify.utils.PhoneUtils;
import it.unibo.coinquify.utils.UtilsGUI;

/**
 * GUI manager for Phonebook.
 *
 */
public class TelephoneBookGUI implements PaneGUI {

    private static final String DATE_ERROR = "DATE_ERROR";
    private final JPanel panel;
    private final TelephoneBookController controller;
    private final DateLabelFormatter formatter;
    private static final int NUMBEROFFIELD = 9;
    private static final int NUMBEROFCOLUMN = 2;
    private static final int WIDTHOFSEARCHBAR = 20;
    private static final int MODIFYBUTTONPOSITIONINDEX = NUMBEROFFIELD;
    private static final int DELETEBUTTONPOSITIONINDEX = MODIFYBUTTONPOSITIONINDEX + 1;
    private static final int TEXTFIELDDIMENSION = 4;

    /**
     * Costruct a new GUI manager for Phonebook.
     * 
     * @throws ClassNotFoundException
     *             if application can't load definition for Contact
     * @throws IOException
     *             if there's some problem with saving of PhoneBook
     */
    public TelephoneBookGUI() throws ClassNotFoundException, IOException {
        this.panel = new JPanel();
        this.formatter = new DateLabelFormatter();
        this.controller = new TelephoneBookControllerImpl(this.formatter.getDateFormatter());

        // Setting the layout of main panel
        this.panel.setLayout(new GridLayout(2, 1));

        // Adding searchbox
        JPanel temporaryPanel = new JPanel();
        final JButton searchBtn = new JButton(Messages.getMessages().getString("SEARCH"));
        final JTextField searchTxtFld = new JTextField(WIDTHOFSEARCHBAR);
        final JButton clearBtn = new JButton(Messages.getMessages().getString("RESET"));
        temporaryPanel.add(new JLabel(Messages.getMessages().getString("SEARCH")));
        temporaryPanel.add(searchTxtFld);
        temporaryPanel.add(searchBtn);
        temporaryPanel.add(clearBtn);

        // Setting action listener for search button
        searchBtn.addActionListener(e -> {
            try {
                viewContacts(searchTxtFld.getText());
            } catch (ParseException e1) {
                JOptionPane.showMessageDialog(panel, Messages.getMessages().getString(DATE_ERROR));
            }
        });

        // Setting action listener for "clear" button
        clearBtn.addActionListener(e -> {
            searchTxtFld.setText("");
        });

        // Adding panel for searching to mainpanel
        this.panel.add(temporaryPanel);

        // Create new Panel for buttons
        temporaryPanel = new JPanel(new FlowLayout());

        // Add Button to create a new contact
        final JButton addNew = new JButton(Messages.getMessages().getString("ADD_NEW_CONTACT"));
        addNew.addActionListener(e -> {
            try {
                this.manageContact(Optional.empty());
            } catch (ParseException e1) {
                JOptionPane.showMessageDialog(panel, Messages.getMessages().getString(DATE_ERROR));
            }
        });
        temporaryPanel.add(addNew);

        // Add button to list all contacts
        final JButton viewAll = new JButton(Messages.getMessages().getString("VIEW_ALL_CONTACT"));
        viewAll.addActionListener(e -> {
            try {
                this.viewContacts(null);
            } catch (ParseException e1) {
                JOptionPane.showMessageDialog(panel, Messages.getMessages().getString(DATE_ERROR));
            }
        });
        temporaryPanel.add(viewAll);

        this.panel.add(temporaryPanel);
    }

    private void manageContact(final Optional<Contact> contactToEdit) throws ParseException {
        final JFrame frameManageContact = new JFrame();

        // Setting panel for textbox
        final JPanel mainJp = new JPanel();
        mainJp.setLayout(new BorderLayout());

        final JPanel jpRowsOfTextBox = new JPanel();
        jpRowsOfTextBox.setLayout(new GridLayout(NUMBEROFFIELD, NUMBEROFCOLUMN));

        mainJp.add(jpRowsOfTextBox, BorderLayout.CENTER);

        frameManageContact.add(mainJp);

        jpRowsOfTextBox.add(new JLabel(Messages.getMessages().getString("NAME")));
        final JTextField name = new JTextField(TEXTFIELDDIMENSION);
        jpRowsOfTextBox.add(name);

        jpRowsOfTextBox.add(new JLabel(Messages.getMessages().getString("SURNAME")));
        final JTextField surname = new JTextField(TEXTFIELDDIMENSION);
        jpRowsOfTextBox.add(surname);

        jpRowsOfTextBox.add(new JLabel(Messages.getMessages().getString("PHONE_NUMBER")));
        final JTextField phoneNumber = PhoneUtils.getPhoneNumberJTextField();
        jpRowsOfTextBox.add(phoneNumber);

        jpRowsOfTextBox.add(new JLabel(Messages.getMessages().getString("ADDRESS")));
        final JTextField address = new JTextField(TEXTFIELDDIMENSION);
        jpRowsOfTextBox.add(address);

        final Properties p = new Properties();
        p.put("text.today", Messages.getMessages().getString("TODAY"));
        p.put("text.month", Messages.getMessages().getString("MONTH"));
        p.put("text.year", Messages.getMessages().getString("YEAR"));
        Locale.setDefault(Messages.getMessages().getCurrentLocale());
        final UtilDateModel model = new UtilDateModel();
        final JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
        final JDatePickerImpl birthday = new JDatePickerImpl(datePanel, this.formatter);
        jpRowsOfTextBox.add(new JLabel(Messages.getMessages().getString("BIRTHDAY")));
        jpRowsOfTextBox.add(birthday);

        jpRowsOfTextBox.add(new JLabel(Messages.getMessages().getString("MAIL")));
        final JTextField email = new JTextField(TEXTFIELDDIMENSION);
        jpRowsOfTextBox.add(email);

        jpRowsOfTextBox.add(new JLabel(Messages.getMessages().getString("FISCAL_CODE")));
        final JTextField fiscalCode = new JTextField(TEXTFIELDDIMENSION);
        fiscalCode.setDocument(new JTextFieldLimit(16)); // To limit the number
                                                         // of characterss
        jpRowsOfTextBox.add(fiscalCode);

        jpRowsOfTextBox.add(new JLabel(Messages.getMessages().getString("HOME_NUMBER")));
        final JTextField homePhoneNumber = PhoneUtils.getContactNumberJTextField();
        jpRowsOfTextBox.add(homePhoneNumber);

        jpRowsOfTextBox.add(new JLabel(Messages.getMessages().getString("WORK_NUMBER")));
        final JTextField workPhoneNumber = PhoneUtils.getContactNumberJTextField();
        jpRowsOfTextBox.add(workPhoneNumber);

        // Create the "Modify or "Add new" buttons
        final JButton manageContact = new JButton(""); // setText after
                                                       // Contact.isPresent()
        mainJp.add(manageContact, BorderLayout.SOUTH);

        // If I want to modify a contact
        if (contactToEdit.isPresent()) {
            frameManageContact.setTitle(Messages.getMessages().getString("EDIT_CONTACT"));

            final Contact cntct = contactToEdit.get();

            // Populate the frame with contact attributes
            name.setText(cntct.getName());
            surname.setText(cntct.getSurname());
            phoneNumber.setText(cntct.getPhoneNumber());
            address.setText(cntct.getAddress());

            if (cntct.getBirthday().isPresent()) {
                model.setValue(cntct.getBirthday().get());
                model.setSelected(true);
            }
            email.setText(contactToEdit.get().getEmail());
            fiscalCode.setText(contactToEdit.get().getFiscalCode());
            homePhoneNumber.setText(contactToEdit.get().getHomePhoneNumber());
            workPhoneNumber.setText(contactToEdit.get().getWorkPhoneNumber());

            manageContact.setText(Messages.getMessages().getString("UPDATE"));
            manageContact.addActionListener(e -> {
                try {
                    final Optional<Date> birthdayDate = this.controller
                            .makeDate(birthday.getJFormattedTextField().getText());

                    this.controller.editContact(cntct, name.getText(), surname.getText(), fiscalCode.getText(),
                            phoneNumber.getText(), birthdayDate, address.getText(), email.getText(),
                            homePhoneNumber.getText(), workPhoneNumber.getText());

                    JOptionPane.showMessageDialog(frameManageContact,
                            Messages.getMessages().getString("MODIFIED_CONTACT"));
                    frameManageContact.dispose();
                } catch (ParseException e1) {
                    JOptionPane.showMessageDialog(frameManageContact,
                            Messages.getMessages().getString("ERROR_CONTACT"));
                    e1.printStackTrace();
                } catch (IllegalArgumentException e2) {
                    JOptionPane.showMessageDialog(frameManageContact,
                            Messages.getMessages().getString("INPUT_ERROR_CONTACT"));
                }
            });
        } else {
            // If user clicked "new contact"
            frameManageContact.setTitle(Messages.getMessages().getString("ADD_NEW_CONTACT"));
            manageContact.setText(Messages.getMessages().getString("ADD_NEW_CONTACT"));
            manageContact.addActionListener(e -> {
                try {
                    final Optional<Date> birthdayDate = this.controller
                            .makeDate(birthday.getJFormattedTextField().getText());

                    this.controller.addContact(new ContactImpl(name.getText(), surname.getText(), fiscalCode.getText(),
                            phoneNumber.getText(), birthdayDate, address.getText(), email.getText(),
                            homePhoneNumber.getText(), workPhoneNumber.getText()));

                    JOptionPane.showMessageDialog(frameManageContact,
                            Messages.getMessages().getString("ADDED_CONTACT"));
                    frameManageContact.dispose();
                } catch (PhoneNumberPresentException e1) {
                    JOptionPane.showMessageDialog(frameManageContact,
                            Messages.getMessages().getString("PHONE_ALREADY_PRESENT"));
                } catch (IllegalArgumentException e1) {
                    JOptionPane.showMessageDialog(frameManageContact,
                            Messages.getMessages().getString("INVALID_ARGUMENT"));
                } catch (ParseException e1) {
                    JOptionPane.showMessageDialog(frameManageContact, Messages.getMessages().getString(DATE_ERROR));
                }
            });
        }
        UtilsGUI.finishFrame(frameManageContact);
        frameManageContact.setVisible(true);
    }

    private void viewContacts(final String stringToSearch) throws ParseException {
        final JFrame frameViewResults = new JFrame();
        final JPanel mainJPanel = new JPanel(new BorderLayout());
        JPanel temporaryPanel = new JPanel();
        JXTable table;
        final List<Contact> list = this.controller.getContacts(stringToSearch);

        // List of arguments for CSV saving
        final List<String> listOfArguments = PhoneUtils.getArrayOfFields();

        // List of title to view in "search contact table"
        final List<String> obj = PhoneUtils.getArrayOfFields();
        obj.addAll(
                Arrays.asList(Messages.getMessages().getString("UPDATE"), Messages.getMessages().getString("DELETE")));

        frameViewResults.setTitle(Messages.getMessages().getString("SEARCH"));

        // If there's no contact that match stringToSearch
        if (list.isEmpty()) {
            JOptionPane.showMessageDialog(frameViewResults, Messages.getMessages().getString("NO_CONTACT"));
            frameViewResults.dispose();
        } else {
            // IF there almost 1 contact to view
            mainJPanel.add(new JLabel(Messages.getMessages().getString("NUMBER_OF_CONTACT") + list.size()),
                    BorderLayout.NORTH);
            mainJPanel.add(temporaryPanel, BorderLayout.CENTER);
            frameViewResults.add(mainJPanel);

            // Create Table
            table = makeTableFromString(frameViewResults, obj, list);
            final JScrollPane pane = new JScrollPane(table);
            table.packAll();
            temporaryPanel.add(pane);
            temporaryPanel = new JPanel();
            mainJPanel.add(temporaryPanel, BorderLayout.SOUTH);

            // Create update result button
            final JButton refresh = new JButton(Messages.getMessages().getString("UPDATE_RESULT"));
            refresh.addActionListener(e -> {
                try {
                    viewContacts(stringToSearch);
                    frameViewResults.dispose();
                } catch (ParseException e1) {
                    JOptionPane.showMessageDialog(frameViewResults, Messages.getMessages().getString(DATE_ERROR));
                }
            });
            temporaryPanel.add(refresh);

            // Create CSV Button
            final JButton exportCSV = new JButton(Messages.getMessages().getString("CSV_EXPORT"));
            exportCSV.addActionListener(e -> {
                try {
                    final JFileChooser fileChooser = new JFileChooser();
                    fileChooser.setSelectedFile(new File("contacts.csv"));
                    final int rVal = fileChooser.showSaveDialog(frameViewResults);
                    if (rVal == JFileChooser.APPROVE_OPTION) {
                        this.controller.saveCSV(fileChooser.getCurrentDirectory() + File.separator
                                + fileChooser.getSelectedFile().getName(), listOfArguments, list);
                        JOptionPane.showMessageDialog(frameViewResults,
                                Messages.getMessages().getString("SAVING_DONE"));
                    }
                } catch (IllegalArgumentException e1) {
                    JOptionPane.showMessageDialog(frameViewResults,
                            Messages.getMessages().getString("INVALID_ARGUMENT"));
                } catch (IOException e1) {
                    JOptionPane.showMessageDialog(frameViewResults, Messages.getMessages().getString("CAN_NOT_SAVE"));
                }
            });
            temporaryPanel.add(exportCSV);

            final JButton closeWindow = new JButton(Messages.getMessages().getString("CLOSE"));
            closeWindow.addActionListener(e -> {
                frameViewResults.dispose();
            });
            temporaryPanel.add(closeWindow);

            UtilsGUI.finishFrame(frameViewResults);
            frameViewResults.setVisible(true);

        }

    }

    private Object[] makeRowFromContact(final Contact contact) {
        String birthday;

        // Parse Date
        if (contact.getBirthday().isPresent()) {
            birthday = this.controller.makeStringFromDate(contact.getBirthday().get());
        } else {
            birthday = "";
        }

        final Object[] temp = { contact.getName(), contact.getSurname(), contact.getFiscalCode(),
                contact.getPhoneNumber(), birthday, contact.getAddress(), contact.getEmail(),
                contact.getHomePhoneNumber(), contact.getWorkPhoneNumber(), Messages.getMessages().getString("UPDATE"),
                Messages.getMessages().getString("DELETE") };
        return temp;
    }

    private JXTable makeTableFromString(final JFrame frameViewResults, final List<String> obj,
            final List<Contact> list) {

        // Create Model for table
        final DefaultTableModel tableModel = new DefaultTableModel(obj.toArray(), 0) {
            private static final long serialVersionUID = 1949938022433138295L;

            public boolean isCellEditable(final int row, final int column) {
                return column == MODIFYBUTTONPOSITIONINDEX || column == DELETEBUTTONPOSITIONINDEX;
            }
        };
        final JXTable table = new JXTable(tableModel);

        // Adding all contact to jtable
        for (final Contact contact : list) {

            // Create Modify button and action
            final Action modifyContact = new AbstractAction() {
                private static final long serialVersionUID = 1L;

                @Override
                public void actionPerformed(final ActionEvent arg0) {

                    int parsedIndex = Integer.parseInt(arg0.getActionCommand());

                    if (parsedIndex < list.size()) {
                        try {
                            Contact cnct = list.get(parsedIndex);
                            manageContact(Optional.of(cnct));

                        } catch (ParseException e) {
                            JOptionPane.showMessageDialog(frameViewResults,
                                    Messages.getMessages().getString(DATE_ERROR));
                        }
                    }
                }
            };

            final ButtonColumn bcModify = new ButtonColumn(table, modifyContact, MODIFYBUTTONPOSITIONINDEX);
            bcModify.setMnemonic(KeyEvent.VK_D);

            // Create Delete button and action
            final Action deleteContact = new AbstractAction() {

                /**
                 * 
                 */
                private static final long serialVersionUID = 1L;

                @Override
                public void actionPerformed(final ActionEvent arg0) {
                    int parsedIndex = Integer.parseInt(arg0.getActionCommand());
                    if (parsedIndex < list.size() && JOptionPane.showConfirmDialog(frameViewResults,
                            Messages.getMessages().getString("SURE_DELETE")) == JOptionPane.OK_OPTION) {
                        controller.removeContact(list.get(parsedIndex));
                        tableModel.removeRow(parsedIndex);
                    }
                }
            };
            final ButtonColumn bcRemove = new ButtonColumn(table, deleteContact, DELETEBUTTONPOSITIONINDEX);
            bcRemove.setMnemonic(KeyEvent.VK_D);

            // Adding row of contact
            final Object[] contactObj = makeRowFromContact(contact);
            tableModel.addRow(contactObj);
        }
        return table;
    }

    /**
     * 
     * @return the controller of telephoneBook GUI, gave the access to research
     *         and many other method.
     */
    public TelephoneBookController getController() {
        return this.controller;
    }

    /**
     * Save all contacts to file.
     * 
     * @throws IOException
     *             if there's some problems with file or access to it.
     */
    public void saveAll() throws IOException {
        PhoneBookFile.saveContacts(this.controller.getAllContacts());
    }

    @Override
    public JPanel getPanel() {
        return this.panel;
    }

    @Override
    public String getName() {
        return Messages.getMessages().getString("PHONEBOOK");
    }

}
