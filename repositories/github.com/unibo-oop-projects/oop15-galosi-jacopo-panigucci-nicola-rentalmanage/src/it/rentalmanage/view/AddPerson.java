package it.rentalmanage.view;

import it.rentalmanage.controller.AddPersonController;
import it.rentalmanage.controller.IAddPersonController;
import it.rentalmanage.model.*;
import it.rentalmanage.model.filemanager.JSonFileManager;

import javax.swing.*;
import java.text.ParseException;
import java.util.*;

/**
 * Created by nicolapanigucci on 29/02/16.
 */
public class AddPerson extends FormPerson implements IFormClients {

    private JTextField tfSurname;
    private JTextField tfName;
    private JTextField tfFiscalCode;
    private JTextField tfbirthDate;
    private JTextField tfAddress;
    private JTextField tfTelephone;
    private Set<String> allCfSet;
    private List<DrivingLicence> allDLicence;

    public AddPerson(final MainFrame prevPanel, final IModel iModel, final Set<String> allCfSet) {
        super(prevPanel, iModel, allCfSet);

        this.panelTitle.add(new JLabel("ADD CUSTOMER"));

        this.allCfSet = allCfSet;
        this.allDLicence = new LinkedList<>();

        this.tfSurname = new JTextField(15);
        c.gridx = 1;
        c.gridy = 0;
        c.insets = padding;
        panelForm.add(tfSurname, c);

        this.tfName = new JTextField(15);
        c.gridx = 1;
        c.gridy = 1;
        c.insets = padding;
        panelForm.add(tfName, c);

        this.tfFiscalCode = new JTextField(15);
        c.gridx = 1;
        c.gridy = 2;
        c.insets = padding;
        panelForm.add(tfFiscalCode,c);

        this.tfbirthDate = new JTextField(15);
        tfbirthDate.setText("dd/MM/yyyy");
        c.gridx = 1;
        c.gridy = 3;
        c.insets = padding;
        panelForm.add(tfbirthDate, c);

        this.tfAddress = new JTextField(15);
        c.gridx = 1;
        c.gridy = 4;
        c.insets = padding;
        panelForm.add(tfAddress, c);

        this.tfTelephone = new JTextField(15);
        c.gridx = 1;
        c.gridy = 5;
        c.insets = padding;
        panelForm.add(tfTelephone, c);

        /**
         * Salva la persona se i campi inseriti sono corretti
         */
        btnSave_Modify.setText("Save");
        btnSave_Modify.addActionListener(e1 -> {
            if(checkInput()){
                try {
                    Date bDate = sdf.parse(tfbirthDate.getText());

                    IPerson person = new Person(new LinkedList<IRentedCarPeriod>(), tfFiscalCode.getText(), tfTelephone.getText(),
                            bDate, tfAddress.getText(), tfSurname.getText(), tfName.getText(), getAllDrivingLicense());
                    IAddPersonController controller = new AddPersonController(new JSonFileManager(), iModel);
                    controller.writePerson(person);

                    prevPanel.setPanel(new StoragePerson(prevPanel, iModel));

                } catch (ParseException e) {

                }

            }
        });

        /**
         * Aggiunge la patente selezionata alla lista delle patenti possedute e aggiorna
         * la label 'allDrivingLicence'
         */
        btnAddDriveLicence.addActionListener(e -> {
            addDriveLicenseToList();
            setLabelDLicense();

            if (allDLicence.size() > 0){
                btnRemoveDriveLicence.setEnabled(true);
            }
        });

        if (allDLicence.size() == 0){
            btnRemoveDriveLicence.setEnabled(false);
        }

        /**
         * Rimuove la patente selezionata dalla lista delle patenti possedute e aggiorna la
         * label 'allDrivingLicence'
         */
        btnRemoveDriveLicence.addActionListener(e1 -> {
            removeDriveLicenseFromList();
            setLabelDLicense();

            if (allDLicence.size() == 0){
                btnRemoveDriveLicence.setEnabled(false);
            }
        });

        btnRemove.setVisible(false);

        prevPanel.setVisibleBtnBackListener(true, e -> prevPanel.setPanel(new StoragePerson(prevPanel, iModel)));

    }

    /**
     * Controlla i valori inseriti nei JTextField
     * @return true se sono tutti corretti
     */
    private boolean checkInput(){

        String surname = tfSurname.getText();
        if(!surname.matches("\\A[A-Za-z'\\\\ ]+\\z")){
            JOptionPane.showMessageDialog(this, "Wrong suname!");
            return false;
        }

        String name = tfName.getText();
        if(!name.matches("\\A[A-Za-z'\\\\ ]+\\z")){
            JOptionPane.showMessageDialog(this, "Wrong name!");
            return false;
        }

        String fiscalCode = tfFiscalCode.getText();
        if (!fiscalCode.matches("\\A[A-Z0-9]{16}\\z")){
            JOptionPane.showMessageDialog(this, "Wrong fiscal code!");
            return false;
        }

        if(allCfSet.contains(fiscalCode)){
            JOptionPane.showMessageDialog(this, "Fiscal code already used!");
            return false;
        }

        if (!check16YearsOld(tfbirthDate.getText())){
            JOptionPane.showMessageDialog(this, "Too young or too old!");
            return false;
        }

        String address = tfAddress.getText();
        if (!address.matches("\\A[\\w'\\.\\ ]+\\z")){
            JOptionPane.showMessageDialog(this, "Wrong address");
            return false;
        }

        String telephone = tfTelephone.getText();
        if(!telephone.matches("\\A\\d{6,}\\z")) {
            JOptionPane.showMessageDialog(this, "Wrong telephone number!");
            return false;
        }

        if(getAllDrivingLicense() == null || getAllDrivingLicense().size() == 0){
            JOptionPane.showMessageDialog(this, "Wrong Drive License");
            return false;
        }

        return true;

    }

    @Override
    public void addDriveLicenseToList() {
        DrivingLicence d = (DrivingLicence) cbDrvLicense.getSelectedItem();
        String bDay = tfbirthDate.getText();

        if (check16YearsOld(bDay) && !allDLicence.contains(d)){
            if (d == DrivingLicence.C || d == DrivingLicence.CE ||
                    d == DrivingLicence.D || d == DrivingLicence.DE){
                insertDrivingLicence(allDLicence, d, bDay);
            }else { //per le patenti A1, A2, A3, B, BE
                if (d == DrivingLicence.A1){
                    allDLicence.add(d);
                } else if (checkOnDriveLicence(d, bDay)){
                    allDLicence.add(d);
                }
            }

        }

    }

    @Override
    public void removeDriveLicenseFromList() {
        DrivingLicence d = (DrivingLicence) cbDrvLicense.getSelectedItem();
        if (d == DrivingLicence.B && (allDLicence.contains(DrivingLicence.C) ||
                allDLicence.contains(DrivingLicence.CE) || allDLicence.contains(DrivingLicence.D) ||
                allDLicence.contains(DrivingLicence.DE))){
            removeDrivingLicence(allDLicence);
        }else {
            allDLicence.remove(d);
        }

    }

    @Override
    public void setLabelDLicense() {
        allDrivingLicence.setText(allDLicence.toString());
    }

    @Override
    public List<DrivingLicence> getAllDrivingLicense() {
        return allDLicence;
    }
}
