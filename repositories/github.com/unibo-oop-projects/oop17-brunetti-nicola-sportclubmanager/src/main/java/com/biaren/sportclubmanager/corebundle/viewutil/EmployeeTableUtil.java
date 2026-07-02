package com.biaren.sportclubmanager.corebundle.viewutil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.biaren.sportclubmanager.corebundle.model.EmployeeMember;
import com.biaren.sportclubmanager.corebundle.model.enums.SportSocietyEmployee;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * Utility class for {@link TableView}. Provides static methods to get {@link TableColumn} with
 * data from entities.
 * @author nbrunetti
 *
 */
public class EmployeeTableUtil {

    /**
     * Get {@link TableColumn} for {@link EmployeeMember} id
     * @return table column of specified field
     */ 
    public static TableColumn<EmployeeMember, Integer> getIdColumn() {
        TableColumn<EmployeeMember, Integer> employeeMemberIdCol = new TableColumn<>("ID");
        employeeMemberIdCol.setCellValueFactory(new PropertyValueFactory<>("EmployeeMemberId"));
        return employeeMemberIdCol;
    }
    
    /**
     * Get {@link TableColumn} for {@link EmployeeMember} name
     * @return table column of specified field
     */ 
    public static TableColumn<EmployeeMember, Integer> getNameColumn() {
        TableColumn<EmployeeMember, Integer> employeeMemberNameCol = new TableColumn<>("Nome");
        employeeMemberNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        return employeeMemberNameCol;
    }
    
    /**
     * Get {@link TableColumn} for {@link EmployeeMember} surname
     * @return table column of specified field
     */ 
    public static TableColumn<EmployeeMember, String> getSurnameColumn() {
        TableColumn<EmployeeMember, String> employeeMemberSurnameCol = new TableColumn<>("Cognome");
        employeeMemberSurnameCol.setCellValueFactory(new PropertyValueFactory<>("surname"));
        return employeeMemberSurnameCol;
    }
    
    /**
     * Get {@link TableColumn} for {@link EmployeeMember} birth date
     * @return table column of specified field
     */ 
    public static TableColumn<EmployeeMember, Date> getBirthdateColumn() {
        TableColumn<EmployeeMember, Date> employeeMemberBirthdateCol = new TableColumn<>("Data di nascita");
        employeeMemberBirthdateCol.setCellValueFactory(new PropertyValueFactory<>("birthDate"));
        return employeeMemberBirthdateCol;
    }
    
    /**
     * Get {@link TableColumn} for {@link EmployeeMember} birth place
     * @return table column of specified field
     */ 
    public static TableColumn<EmployeeMember, String> getBirtPlaceColumn() {
        TableColumn<EmployeeMember, String> employeeMemberBirthPlaceCol = new TableColumn<>("Luogo di nascita");
        employeeMemberBirthPlaceCol.setCellValueFactory(new PropertyValueFactory<>("birthPlace"));
        return employeeMemberBirthPlaceCol;
    }
    
    /**
     * Get {@link TableColumn} for {@link EmployeeMember} nationality
     * @return table column of specified field
     */ 
    public static TableColumn<EmployeeMember, String> getNationalityColumn() {
        TableColumn<EmployeeMember, String> employeeMemberNationalityCol = new TableColumn<>("Nazionalità");
        employeeMemberNationalityCol.setCellValueFactory(new PropertyValueFactory<>("nationality"));
        return employeeMemberNationalityCol;
    }
    
    /**
     * Get {@link TableColumn} for {@link EmployeeMember} district
     * @return table column of specified field
     */ 
    public static TableColumn<EmployeeMember, String> getResidenceDistrictColumn() {
        TableColumn<EmployeeMember, String> employeeMemberResidenceDistrictCol = new TableColumn<>("Regione");
        employeeMemberResidenceDistrictCol.setCellValueFactory(new PropertyValueFactory<>("residenceDistrict"));
        return employeeMemberResidenceDistrictCol;
    }
    
    /**
     * Get {@link TableColumn} for {@link EmployeeMember} residence address
     * @return table column of specified field
     */ 
    public static TableColumn<EmployeeMember, String> getResidenceAddressColumn() {
        TableColumn<EmployeeMember, String> employeeMemberResidenceAddressCol = new TableColumn<>("Indirizzo");
        employeeMemberResidenceAddressCol.setCellValueFactory(new PropertyValueFactory<>("residenceAddress"));
        return employeeMemberResidenceAddressCol;
    }
    
    /**
     * Get {@link TableColumn} for {@link EmployeeMember} residence city
     * @return table column of specified field
     */ 
    public static TableColumn<EmployeeMember, String> getResidenceCityColumn() {
        TableColumn<EmployeeMember, String> employeeMemberResidenceCityCol = new TableColumn<>("Città");
        employeeMemberResidenceCityCol.setCellValueFactory(new PropertyValueFactory<>("residenceCity"));
        return employeeMemberResidenceCityCol;
    }
    
    /**
     * Get {@link TableColumn} for {@link EmployeeMember} residence nation
     * @return table column of specified field
     */ 
    public static TableColumn<EmployeeMember, String> getResidenceNationColumn() {
        TableColumn<EmployeeMember, String> employeeMemberResidenceNationCol = new TableColumn<>("Nazione");
        employeeMemberResidenceNationCol.setCellValueFactory(new PropertyValueFactory<>("residenceNation"));
        return employeeMemberResidenceNationCol;
    }
    
    /**
     * Get {@link TableColumn} for {@link EmployeeMember} fiscal code
     * @return table column of specified field
     */ 
    public static TableColumn<EmployeeMember, String> getFiscalCodeColumn() {
        TableColumn<EmployeeMember, String> employeeMemberFiscalCodeCol = new TableColumn<>("Codice Fiscale");
        employeeMemberFiscalCodeCol.setCellValueFactory(new PropertyValueFactory<>("fiscalCode"));
        return employeeMemberFiscalCodeCol;
    }
    
    /**
     * Get {@link TableColumn} for {@link EmployeeMember} role
     * @return table column of specified field
     */ 
    public static TableColumn<EmployeeMember, SportSocietyEmployee> getRoleColumn() {
        TableColumn<EmployeeMember, SportSocietyEmployee> employeeMemberRoleCol = new TableColumn<>("Ruolo");
        employeeMemberRoleCol.setCellValueFactory(new PropertyValueFactory<>("employeeMemberRole"));
        return employeeMemberRoleCol;
    }

    /**
     * Get {@link TableColumn} list
     * @return table column list of all specified fields
     */
    public static List<TableColumn<EmployeeMember, ?>> tableColumnList() {
        List<TableColumn<EmployeeMember, ?>> list = new ArrayList<>();
       
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
