package it.rentalmanage.view;

import it.rentalmanage.model.DrivingLicence;
import it.rentalmanage.model.IModel;

import javax.swing.*;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

/**
 * Created by nicolapanigucci on 26/02/16.
 */
public class FormPerson extends JPanel {

    protected SimpleDateFormat sdf;
    protected Date now;
    protected Calendar calNow;

    private JLabel lblSurname;
    private JLabel lblName;
    private JLabel lblTelephone;
    private JLabel lblFiscalCode;
    private JLabel lblAddress;
    private JLabel lblBirthDate;
    private JLabel lblDrivingLicense;
    protected JLabel allDrivingLicence;

    protected JButton btnAddDriveLicence;
    protected JButton btnRemoveDriveLicence;
    protected JButton btnSave_Modify;
    protected JButton btnRemove;

    protected JComboBox<DrivingLicence> cbDrvLicense;

    private JPanel panelBtn;
    protected JPanel panelTitle;
    protected JPanel panelForm;
    protected JPanel panBtnAddCar;

    private GridBagLayout gb;
    protected Insets padding;
    protected GridBagConstraints c;
    protected List specificDLicenceList;

    public FormPerson(final MainFrame prevPanel, final IModel iModel, final Set<String> allCfSet){

        this.setLayout(new BorderLayout());

        this.sdf = new SimpleDateFormat("dd/MM/yyyy");
        this.sdf.setLenient(false);
        this.now = new Date();
        this.calNow = Calendar.getInstance();
        this.calNow.setTime(this.now);
        this.calNow.add(Calendar.MONTH, -1);

        this.specificDLicenceList = Arrays.asList(DrivingLicence.B, DrivingLicence.C, DrivingLicence.CE, DrivingLicence.D, DrivingLicence.DE);

        btnSave_Modify = new JButton();
        btnRemove = new JButton("Remove");

        panelTitle = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelForm = new JPanel();

        gb = new GridBagLayout();
        gb.columnWidths = new int[]{0, 196, 0, 0};
        gb.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
        gb.columnWeights = new double[]{1.0, 0.0, 1.0, Double.MIN_VALUE};
        gb.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        panelForm.setLayout(gb);

        panelBtn = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        c = new GridBagConstraints();
        padding = new Insets(20, 20, 0, 0);

        this.lblSurname = new JLabel("Surname :");
        this.lblSurname.setHorizontalAlignment(JLabel.RIGHT);
        c.gridx = 0;
        c.gridy = 0;
        c.insets = padding;
        c.anchor = GridBagConstraints.EAST;
        panelForm.add(this.lblSurname, c);

        this.lblName = new JLabel("Name :");
        this.lblName.setHorizontalAlignment(JLabel.RIGHT);
        c.gridx = 0;
        c.gridy = 1;
        c.insets = padding;
        panelForm.add(this.lblName, c);

        this.lblFiscalCode = new JLabel("Fiscal Code :");
        this.lblFiscalCode.setHorizontalAlignment(JLabel.RIGHT);
        c.gridx = 0;
        c.gridy = 2;
        c.insets = padding;
        panelForm.add(this.lblFiscalCode, c);

        this.lblBirthDate = new JLabel("Birth Date :");
        this.lblBirthDate.setHorizontalAlignment(JLabel.RIGHT);
        c.gridx = 0;
        c.gridy = 3;
        c.insets = padding;
        panelForm.add(this.lblBirthDate, c);

        this.lblAddress = new JLabel("Address :");
        this.lblAddress.setHorizontalAlignment(JLabel.RIGHT);
        c.gridx = 0;
        c.gridy = 4;
        c.insets = padding;
        panelForm.add(this.lblAddress, c);

        this.lblTelephone = new JLabel("Telephone :");
        this.lblTelephone.setHorizontalAlignment(JLabel.RIGHT);
        c.gridx = 0;
        c.gridy = 5;
        c.insets = padding;
        panelForm.add(this.lblTelephone, c);

        this.lblDrivingLicense = new JLabel("Driving License :");
        this.lblDrivingLicense.setHorizontalAlignment(JLabel.RIGHT);
        c.gridx = 0;
        c.gridy = 6;
        c.insets = padding;
        panelForm.add(this.lblDrivingLicense, c);

        this.allDrivingLicence = new JLabel();
        this.allDrivingLicence.setHorizontalAlignment(JLabel.CENTER);
        c.gridx = 1;
        c.gridy = 6;
        c.insets = padding;
        c.anchor = GridBagConstraints.CENTER;
        panelForm.add(this.allDrivingLicence, c);

        this.btnAddDriveLicence = new JButton("Add");
        c.gridx = 2;
        c.gridy = 6;
        c.insets = new Insets(20,10,0,0);
        c.anchor = GridBagConstraints.WEST;
        panelForm.add(this.btnAddDriveLicence, c);

        this.btnRemoveDriveLicence = new JButton("Remove");
        c.gridx = 2;
        c.gridy = 7;
        c.insets = new Insets(5,10,10,0);
        c.anchor = GridBagConstraints.WEST;
        panelForm.add(this.btnRemoveDriveLicence, c);

        this.cbDrvLicense = new JComboBox<>(DrivingLicence.values());
        c.gridx = 1;
        c.gridy = 7;
        c.insets = new Insets(5,10,10,0);
        c.anchor = GridBagConstraints.CENTER;
        panelForm.add(this.cbDrvLicense, c);

        panBtnAddCar = new JPanel(new FlowLayout(FlowLayout.LEFT));

        panelBtn.add(panBtnAddCar);
        panelBtn.add(btnRemove);
        panelBtn.add(btnSave_Modify);

        this.add(panelTitle, BorderLayout.NORTH);
        this.add(panelForm, BorderLayout.CENTER);
        this.add(this.panelBtn, BorderLayout.SOUTH);

        prevPanel.setVisibleBtnBackListener(true, e -> prevPanel.setPanel(new StoragePerson(prevPanel,iModel)));

    }

    /**
     * Gestisce l'inserimento delle patenti C, CE, D, DE
     * @param licenceList
     * @param drivingLicence
     * @param birthDay
     */
    protected void insertDrivingLicence(List<DrivingLicence> licenceList, DrivingLicence drivingLicence, String birthDay){

        int result = JOptionPane.showOptionDialog(null, "Required B licence! Do you have it?", "Question...", JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, null, null);
        if (result == JOptionPane.YES_OPTION){
            if (!licenceList.contains(DrivingLicence.B) && check18YearsOld(birthDay)){
                licenceList.add(DrivingLicence.B);
            }
            if ((drivingLicence == DrivingLicence.C || drivingLicence == DrivingLicence.CE) &&
                    check21YearsOld(birthDay)){
                licenceList.add(drivingLicence);
            }
            if ((drivingLicence == DrivingLicence.D || drivingLicence == DrivingLicence.DE) &&
                    check24YearsOld(birthDay)){
                licenceList.add(drivingLicence);
            }
        }
    }

    /**
     * Gestisce l'eliminazione patenti C, CE, D, DE dopo aver eliminato la B
     * @param licenceList
     */
    protected void removeDrivingLicence(List<DrivingLicence> licenceList){

        int result = JOptionPane.showOptionDialog(null, "You can't have some driving licence without B! I'll erase them!", "Wait...", JOptionPane.YES_NO_OPTION,
                JOptionPane.INFORMATION_MESSAGE, null, null, null);
        if (result == JOptionPane.YES_OPTION){
            licenceList.removeAll(specificDLicenceList);
        }
    }

    /**
     * Controlla se la persona ha l'età giusta per una detrminata patente
     * @param drivingLicence
     * @param date
     * @return true se l'età è corretta
     */
    protected boolean checkOnDriveLicence(DrivingLicence drivingLicence, String date){
        boolean flag = false;

        if (drivingLicence == null){
            return false;
        }

        if ((drivingLicence == DrivingLicence.A2 || drivingLicence == DrivingLicence.B || drivingLicence == DrivingLicence.BE) &&
                check18YearsOld(date)){
            flag = true;
        }

        if (drivingLicence == DrivingLicence.A3 &&
                check24YearsOld(date)){
            flag = true;
        }

        return flag;

    }

    /**
     * Controlla sulla data minima accettabile per essere un cliente
     * @param date
     * @return true se ha almeno 16 anni
     */
    protected boolean check16YearsOld(String date){
        int age = age(date);

        if (age != -1 && (age >= 16 && age < 100)){

            return true;

        }
        return false;
    }

    /**
     * Controlla per noleggiare veicolo che richiede A2 o B
     * @param date
     * @return true se la persona ha almeno 18 anni
     */
    private boolean check18YearsOld(String date){
        int age = age(date);

        if (age != -1 && (age >= 18 && age < 100)){

            return true;

        }
        return false;
    }

    /**
     * Controlla per noleggiare veicolo che richiede C o CE
     * @param date
     * @return true se la persona ha almeno 21 anni
     */
    private boolean check21YearsOld(String date){
        int age = age(date);

        if(age != -1 && (age >= 21 && age < 100)){
            return true;
        }

        return false;
    }

    /**
     * Controlla per noleggiare veicolo che richiede A3, D o DE
     * @param date
     * @return true se la persona ha almeno 24 anni
     */
    private boolean check24YearsOld(String date){
        int age = age(date);

        if(age != -1 && (age >= 24 && age < 100)){
            return true;
        }

        return false;
    }

    /**
     * Controlla sulla data
     * @param date
     * @return true se la data è valida
     */
    private Calendar checkDate(String date){

        try {
            Date dateValid = sdf.parse(date);
            Calendar cal = Calendar.getInstance();
            cal.setTime(dateValid);
            cal.add(Calendar.MONTH,-1); //mesi da 0 a 11

            return cal;

        } catch (ParseException e) {

            return null;
        }
    }

    /**
     * Calcola l'età
     * @param date
     * @return età della persona, altrimenti -1 se 'bDay' non è una data di nascita valida
     */
    private int age(String date){
        Calendar bDay = checkDate(date);

        if (bDay != null) {

            int yBday = bDay.get(Calendar.YEAR);
            int yToday = calNow.get(Calendar.YEAR);
            return yToday - yBday;
        } else{
            return -1;
        }

    }

}
