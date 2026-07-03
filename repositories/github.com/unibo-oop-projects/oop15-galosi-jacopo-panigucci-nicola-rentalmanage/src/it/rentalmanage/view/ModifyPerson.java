package it.rentalmanage.view;

import it.rentalmanage.controller.*;
import it.rentalmanage.model.*;

import javax.swing.*;
import java.util.List;
import java.util.Set;

/**
 * Created by nicolapanigucci on 29/02/16.
 */
public class ModifyPerson extends FormPerson implements IFormClients{

    private JTextField tfSurname;
    private JTextField tfName;
    private JTextField tfTelephone;
    private JLabel fiscalCode;
    private JTextField tfAddress;
    private JLabel birthDate;
    private String birth;
    private IPerson person;

    private List<DrivingLicence> dLicenseList;

    public ModifyPerson(final MainFrame prevPanel, final IPerson iPerson, final IModel iModel, final Set<String> allCfset) {
        super(prevPanel,iModel, allCfset);

        this.person = iPerson;
        this.dLicenseList = iPerson.getDrivingLicense();
        this.birth = sdf.format(iPerson.getBirthDate());

        panelTitle.add(new JLabel("MODIFY CUSTOMER"));

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

        this.fiscalCode = new JLabel(iPerson.getFiscalCode());
        c.gridx = 1;
        c.gridy = 2;
        c.insets = padding;
        panelForm.add(fiscalCode, c);

        this.birthDate = new JLabel(this.birth);
        c.gridx = 1;
        c.gridy = 3;
        c.insets = padding;
        panelForm.add(birthDate, c);

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

        this.tfSurname.setText(iPerson.getSurname());
        this.tfName.setText(iPerson.getName());
        this.tfAddress.setText(iPerson.getAddress());
        this.tfTelephone.setText(iPerson.getPhoneNumber());
        allDrivingLicence.setText(dLicenseList.toString());

        /**
         * Elimina la persona
         */
        btnRemove.addActionListener(e1 -> {
            int result = JOptionPane.showConfirmDialog(null, "Do you want erase it?", "Warning", JOptionPane.YES_NO_OPTION);
            IJOptionPaneAnswer optionPaneAnswer = new JOptionePaneAnswer();
            optionPaneAnswer.delPerson(result, prevPanel, iPerson, iModel);
        });

        /**
         * Aggiorna i dati della persona
         */
        btnSave_Modify.setText("Modify");
        btnSave_Modify.addActionListener(e -> {

            if(checkInput()){
                IPersonController personController = new PersonController(iModel, this.person);

                personController.updatePerson(tfSurname.getText(), tfName.getText(), tfAddress.getText(),
                        tfTelephone.getText());

                prevPanel.setPanel(new StoragePerson(prevPanel, iModel));

            }
        });

        /**
         * Aggiunge la patente selezionata alla lista delle patenti possedute e aggiorna
         * la label 'allDrivingLicence'
         */
        btnAddDriveLicence.addActionListener(e -> {
            addDriveLicenseToList();
            setLabelDLicense();

            if (dLicenseList.size() > 0){
                btnRemoveDriveLicence.setEnabled(true);
            }
        });

        /**
         * Rimuove la patente selezionata dalla lista delle patenti possedute e aggiorna la
         * label 'allDrivingLicence'
         */
        btnRemoveDriveLicence.addActionListener(e1 -> {
            removeDriveLicenseFromList();
            setLabelDLicense();

            if (dLicenseList.size() == 0){
                btnRemoveDriveLicence.setEnabled(false);
            }
        });

        prevPanel.setVisibleBtnBackListener(true, e -> prevPanel.setPanel(new ViewPerson(prevPanel, iModel, iPerson, null)));

    }

    /**
     * Controlla i valori inseriti dall'utente
     * @return true se sono tutti validi
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

        if (!dLicenseList.contains(d)){
            if (d == DrivingLicence.C || d == DrivingLicence.CE ||
                    d == DrivingLicence.D || d == DrivingLicence.DE){
                insertDrivingLicence(dLicenseList,d,birth);
            }else { //per le patenti A1, A2, A3, B, BE
                if (d == DrivingLicence.A1){
                    dLicenseList.add(d);
                } else if (checkOnDriveLicence(d,birth)){
                    dLicenseList.add(d);
                }
            }

        }
    }

    @Override
    public void removeDriveLicenseFromList() {
        DrivingLicence d = (DrivingLicence) cbDrvLicense.getSelectedItem();
        if (d == DrivingLicence.B && (dLicenseList.contains(DrivingLicence.C) ||
                dLicenseList.contains(DrivingLicence.CE) || dLicenseList.contains(DrivingLicence.D) ||
                dLicenseList.contains(DrivingLicence.DE))){
            removeDrivingLicence(dLicenseList);
        }else {
            dLicenseList.remove(d);
        }

    }

    @Override
    public void setLabelDLicense() {
        allDrivingLicence.setText(dLicenseList.toString());
    }

    @Override
    public List<DrivingLicence> getAllDrivingLicense() {
        return dLicenseList;
    }
}