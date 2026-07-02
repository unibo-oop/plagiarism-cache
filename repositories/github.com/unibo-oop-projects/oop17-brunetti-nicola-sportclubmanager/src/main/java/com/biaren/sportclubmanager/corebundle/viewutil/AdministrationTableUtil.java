package com.biaren.sportclubmanager.corebundle.viewutil;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import com.biaren.sportclubmanager.corebundle.model.AdministrationMember;
import com.biaren.sportclubmanager.corebundle.model.enums.AdministrationMemberRole;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * Utility class for {@link TableView}. Provides static methods to get {@link TableColumn} with
 * data from entities.
 * @author nbrunetti
 *
 */
public class AdministrationTableUtil {

    /**
     * Get {@link TableColumn} for {@link AdministrationMember} id
     * @return table column of specified field
     */ 
    public static TableColumn<AdministrationMember, Integer> getIdColumn() {
        TableColumn<AdministrationMember, Integer> administrationMemberIdCol = new TableColumn<>("ID");
        administrationMemberIdCol.setCellValueFactory(new PropertyValueFactory<>("administrationMemberId"));
        return administrationMemberIdCol;
    }
    
    /**
     * Get {@link TableColumn} for {@link AdministrationMember} name
     * @return table column of specified field
     */
    public static TableColumn<AdministrationMember, Integer> getNameColumn() {
        TableColumn<AdministrationMember, Integer> administrationMemberNameCol = new TableColumn<>("Nome");
        administrationMemberNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        return administrationMemberNameCol;
    }
    
    /**
     * Get {@link TableColumn} for {@link AdministrationMember} surname
     * @return table column of specified field
     */
    public static TableColumn<AdministrationMember, String> getSurnameColumn() {
        TableColumn<AdministrationMember, String> administrationMemberSurnameCol = new TableColumn<>("Cognome");
        administrationMemberSurnameCol.setCellValueFactory(new PropertyValueFactory<>("surname"));
        return administrationMemberSurnameCol;
    }
    
    /**
     * Get {@link TableColumn} for {@link AdministrationMember} birth date
     * @return table column of specified field
     */
    public static TableColumn<AdministrationMember, LocalDate> getBirthdateColumn() {
        TableColumn<AdministrationMember, LocalDate> administrationMemberBirthdateCol = new TableColumn<>("Data di nascita");
        administrationMemberBirthdateCol.setCellValueFactory(new PropertyValueFactory<>("birthDate"));
        return administrationMemberBirthdateCol;
    }
    
    /**
     * Get {@link TableColumn} for {@link AdministrationMember} birth place
     * @return table column of specified field
     */
    public static TableColumn<AdministrationMember, String> getBirtPlaceColumn() {
        TableColumn<AdministrationMember, String> administrationMemberBirthPlaceCol = new TableColumn<>("Luogo di nascita");
        administrationMemberBirthPlaceCol.setCellValueFactory(new PropertyValueFactory<>("birthPlace"));
        return administrationMemberBirthPlaceCol;
    }
    
    /**
     * Get {@link TableColumn} for {@link AdministrationMember} nationality
     * @return table column of specified field
     */
    public static TableColumn<AdministrationMember, String> getNationalityColumn() {
        TableColumn<AdministrationMember, String> administrationMemberNationalityCol = new TableColumn<>("Nazionalità");
        administrationMemberNationalityCol.setCellValueFactory(new PropertyValueFactory<>("nationality"));
        return administrationMemberNationalityCol;
    }
    
    /**
     * Get {@link TableColumn} for {@link AdministrationMember} district
     * @return table column of specified field
     */
    public static TableColumn<AdministrationMember, String> getResidenceDistrictColumn() {
        TableColumn<AdministrationMember, String> administrationMemberResidenceDistrictCol = new TableColumn<>("Regione");
        administrationMemberResidenceDistrictCol.setCellValueFactory(new PropertyValueFactory<>("residenceDistrict"));
        return administrationMemberResidenceDistrictCol;
    }
    
    /**
     * Get {@link TableColumn} for {@link AdministrationMember} residence address
     * @return table column of specified field
     */
    public static TableColumn<AdministrationMember, String> getResidenceAddressColumn() {
        TableColumn<AdministrationMember, String> administrationMemberResidenceAddressCol = new TableColumn<>("Indirizzo");
        administrationMemberResidenceAddressCol.setCellValueFactory(new PropertyValueFactory<>("residenceAddress"));
        return administrationMemberResidenceAddressCol;
    }
    
    /**
     * Get {@link TableColumn} for {@link AdministrationMember} residence city
     * @return table column of specified field
     */
    public static TableColumn<AdministrationMember, String> getResidenceCityColumn() {
        TableColumn<AdministrationMember, String> administrationMemberResidenceCityCol = new TableColumn<>("Città");
        administrationMemberResidenceCityCol.setCellValueFactory(new PropertyValueFactory<>("residenceCity"));
        return administrationMemberResidenceCityCol;
    }
    
    /**
     * Get {@link TableColumn} for {@link AdministrationMember} residence nation
     * @return table column of specified field
     */
    public static TableColumn<AdministrationMember, String> getResidenceNationColumn() {
        TableColumn<AdministrationMember, String> administrationMemberResidenceNationCol = new TableColumn<>("Nazione");
        administrationMemberResidenceNationCol.setCellValueFactory(new PropertyValueFactory<>("residenceNation"));
        return administrationMemberResidenceNationCol;
    }
    
    /**
     * Get {@link TableColumn} for {@link AdministrationMember} fiscal code
     * @return table column of specified field
     */
    public static TableColumn<AdministrationMember, String> getFiscalCodeColumn() {
        TableColumn<AdministrationMember, String> administrationMemberFiscalCodeCol = new TableColumn<>("Codice Fiscale");
        administrationMemberFiscalCodeCol.setCellValueFactory(new PropertyValueFactory<>("fiscalCode"));
        return administrationMemberFiscalCodeCol;
    }
    
    /**
     * Get {@link TableColumn} for {@link AdministrationMember} role
     * @return table column of specified field
     */
    public static TableColumn<AdministrationMember, AdministrationMemberRole> getRoleColumn() {
        TableColumn<AdministrationMember, AdministrationMemberRole> administrationMemberRoleCol = new TableColumn<>("Ruolo");
        administrationMemberRoleCol.setCellValueFactory(new PropertyValueFactory<>("administrationMemberRole"));
        return administrationMemberRoleCol;
    }

    /**
     * Get {@link TableColumn} list
     * @return table column list of all specified fields
     */
    public static List<TableColumn<AdministrationMember, ?>> tableColumnList() {
        List<TableColumn<AdministrationMember, ?>> list = new ArrayList<>();
       
        list.add(getIdColumn());
        list.add(getNameColumn());
        list.add(getSurnameColumn());
        list.add(getBirthdateColumn());
        list.add(getBirtPlaceColumn());
        list.add(getNationalityColumn());
        list.add(getResidenceDistrictColumn());
        list.add(getResidenceCityColumn());
        list.add(getResidenceNationColumn());
        list.add(getFiscalCodeColumn());
        list.add(getRoleColumn());
        return list;
    }
}
