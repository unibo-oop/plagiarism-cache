package com.biaren.sportclubmanager.corebundle.viewutil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.biaren.sportclubmanager.corebundle.model.StaffMember;
import com.biaren.sportclubmanager.corebundle.model.enums.SportSocietyStaffRole;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * Utility class for {@link TableView}. Provides static methods to get {@link TableColumn} with
 * data from entities.
 * @author nbrunetti
 *
 */
public class StaffTableUtil {

    /**
     * Get {@link TableColumn} for {@link StaffMember} id
     * @return table column of specified field
     */ 
    public static TableColumn<StaffMember, Integer> getIdColumn() {
        TableColumn<StaffMember, Integer> staffMemberIdCol = new TableColumn<>("ID");
        staffMemberIdCol.setCellValueFactory(new PropertyValueFactory<>("StaffMemberId"));
        return staffMemberIdCol;
    }
    
    /**
     * Get {@link TableColumn} for {@link StaffMember} name
     * @return table column of specified field
     */ 
    public static TableColumn<StaffMember, Integer> getNameColumn() {
        TableColumn<StaffMember, Integer> staffMemberNameCol = new TableColumn<>("Nome");
        staffMemberNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        return staffMemberNameCol;
    }
    
    /**
     * Get {@link TableColumn} for {@link StaffMember} surname
     * @return table column of specified field
     */ 
    public static TableColumn<StaffMember, String> getSurnameColumn() {
        TableColumn<StaffMember, String> staffMemberSurnameCol = new TableColumn<>("Cognome");
        staffMemberSurnameCol.setCellValueFactory(new PropertyValueFactory<>("surname"));
        return staffMemberSurnameCol;
    }
    
    /**
     * Get {@link TableColumn} for {@link StaffMember} birth date
     * @return table column of specified field
     */ 
    public static TableColumn<StaffMember, Date> getBirthdateColumn() {
        TableColumn<StaffMember, Date> staffMemberBirthdateCol = new TableColumn<>("Data di nascita");
        staffMemberBirthdateCol.setCellValueFactory(new PropertyValueFactory<>("birthDate"));
        return staffMemberBirthdateCol;
    }
    
    /**
     * Get {@link TableColumn} for {@link StaffMember} birth place
     * @return table column of specified field
     */ 
    public static TableColumn<StaffMember, String> getBirtPlaceColumn() {
        TableColumn<StaffMember, String> staffMemberBirthPlaceCol = new TableColumn<>("Luogo di nascita");
        staffMemberBirthPlaceCol.setCellValueFactory(new PropertyValueFactory<>("birthPlace"));
        return staffMemberBirthPlaceCol;
    }
    
    /**
     * Get {@link TableColumn} for {@link StaffMember} nationality
     * @return table column of specified field
     */ 
    public static TableColumn<StaffMember, String> getNationalityColumn() {
        TableColumn<StaffMember, String> staffMemberNationalityCol = new TableColumn<>("Nazionalità");
        staffMemberNationalityCol.setCellValueFactory(new PropertyValueFactory<>("nationality"));
        return staffMemberNationalityCol;
    }
    
    /**
     * Get {@link TableColumn} for {@link StaffMember} district
     * @return table column of specified field
     */ 
    public static TableColumn<StaffMember, String> getResidenceDistrictColumn() {
        TableColumn<StaffMember, String> staffMemberResidenceDistrictCol = new TableColumn<>("Regione");
        staffMemberResidenceDistrictCol.setCellValueFactory(new PropertyValueFactory<>("residenceDistrict"));
        return staffMemberResidenceDistrictCol;
    }
    
    /**
     * Get {@link TableColumn} for {@link StaffMember} residence address
     * @return table column of specified field
     */ 
    public static TableColumn<StaffMember, String> getResidenceAddressColumn() {
        TableColumn<StaffMember, String> staffMemberResidenceAddressCol = new TableColumn<>("Indirizzo");
        staffMemberResidenceAddressCol.setCellValueFactory(new PropertyValueFactory<>("residenceAddress"));
        return staffMemberResidenceAddressCol;
    }
    
    /**
     * Get {@link TableColumn} for {@link StaffMember} residence city
     * @return table column of specified field
     */ 
    public static TableColumn<StaffMember, String> getResidenceCityColumn() {
        TableColumn<StaffMember, String> staffMemberResidenceCityCol = new TableColumn<>("Città");
        staffMemberResidenceCityCol.setCellValueFactory(new PropertyValueFactory<>("residenceCity"));
        return staffMemberResidenceCityCol;
    }
    
    /**
     * Get {@link TableColumn} for {@link StaffMember} residence nation
     * @return table column of specified field
     */ 
    public static TableColumn<StaffMember, String> getResidenceNationColumn() {
        TableColumn<StaffMember, String> staffMemberResidenceNationCol = new TableColumn<>("Nazione");
        staffMemberResidenceNationCol.setCellValueFactory(new PropertyValueFactory<>("residenceNation"));
        return staffMemberResidenceNationCol;
    }
    
    /**
     * Get {@link TableColumn} for {@link StaffMember} fiscal code
     * @return table column of specified field
     */ 
    public static TableColumn<StaffMember, String> getFiscalCodeColumn() {
        TableColumn<StaffMember, String> staffMemberFiscalCodeCol = new TableColumn<>("Codice Fiscale");
        staffMemberFiscalCodeCol.setCellValueFactory(new PropertyValueFactory<>("fiscalCode"));
        return staffMemberFiscalCodeCol;
    }
    
    /**
     * Get {@link TableColumn} for {@link StaffMember} role
     * @return table column of specified field
     */ 
    public static TableColumn<StaffMember, SportSocietyStaffRole> getRoleColumn() {
        TableColumn<StaffMember, SportSocietyStaffRole> staffMemberRoleCol = new TableColumn<>("Ruolo");
        staffMemberRoleCol.setCellValueFactory(new PropertyValueFactory<>("staffMemberRole"));
        return staffMemberRoleCol;
    }
        
    /**
     * Get {@link TableColumn} list
     * @return table column list of all specified fields
     */
    public static List<TableColumn<StaffMember, ?>> tableColumnList() {
        List<TableColumn<StaffMember, ?>> list = new ArrayList<>();
       
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
