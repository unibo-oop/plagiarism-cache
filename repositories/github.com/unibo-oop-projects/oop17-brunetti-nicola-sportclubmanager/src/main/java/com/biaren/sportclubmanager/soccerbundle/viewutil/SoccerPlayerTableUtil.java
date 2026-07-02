package com.biaren.sportclubmanager.soccerbundle.viewutil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.biaren.sportclubmanager.soccerbundle.enums.SoccerRole;
import com.biaren.sportclubmanager.soccerbundle.models.SoccerPlayer;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * Utility class for {@link TableView}. Provides static methods to get {@link TableColumn} with
 * data from entities.
 * @author nbrunetti
 *
 */
public class SoccerPlayerTableUtil {
    
    /**
     * Get {@link TableColumn} for {@link SoccerPlayer} id
     * @return table column of specified field
     */ 
    public static TableColumn<SoccerPlayer, Integer> getIdColumn() {
        TableColumn<SoccerPlayer, Integer> soccerPlayerIdCol = new TableColumn<>("ID");
        soccerPlayerIdCol.setCellValueFactory(new PropertyValueFactory<>("soccerPlayerId"));
        return soccerPlayerIdCol;
    }
    
    /**
     * Get {@link TableColumn} for {@link SoccerPlayer} name
     * @return table column of specified field
     */
    public static TableColumn<SoccerPlayer, Integer> getNameColumn() {
        TableColumn<SoccerPlayer, Integer> soccerPlayerNameCol = new TableColumn<>("Nome");
        soccerPlayerNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        return soccerPlayerNameCol;
    }
    
    /**
     * Get {@link TableColumn} for {@link SoccerPlayer} surname
     * @return table column of specified field
     */
    public static TableColumn<SoccerPlayer, String> getSurnameColumn() {
        TableColumn<SoccerPlayer, String> soccerPlayerSurnameCol = new TableColumn<>("Cognome");
        soccerPlayerSurnameCol.setCellValueFactory(new PropertyValueFactory<>("surname"));
        return soccerPlayerSurnameCol;
    }
    
    /**
     * Get {@link TableColumn} for {@link SoccerPlayer} birth date
     * @return table column of specified field
     */
    public static TableColumn<SoccerPlayer, Date> getBirthdateColumn() {
        TableColumn<SoccerPlayer, Date> soccerPlayerBirthdateCol = new TableColumn<>("Data di nascita");
        soccerPlayerBirthdateCol.setCellValueFactory(new PropertyValueFactory<>("birthDate"));
        return soccerPlayerBirthdateCol;
    }
    
    /**
     * Get {@link TableColumn} for {@link SoccerPlayer} birth place
     * @return table column of specified field
     */
    public static TableColumn<SoccerPlayer, String> getBirtPlaceColumn() {
        TableColumn<SoccerPlayer, String> soccerPlayerBirthPlaceCol = new TableColumn<>("Luogo di nascita");
        soccerPlayerBirthPlaceCol.setCellValueFactory(new PropertyValueFactory<>("birthPlace"));
        return soccerPlayerBirthPlaceCol;
    }
    
    /**
     * Get {@link TableColumn} for {@link SoccerPlayer} nationality
     * @return table column of specified field
     */
    public static TableColumn<SoccerPlayer, String> getNationalityColumn() {
        TableColumn<SoccerPlayer, String> soccerPlayerNationalityCol = new TableColumn<>("Nazionalità");
        soccerPlayerNationalityCol.setCellValueFactory(new PropertyValueFactory<>("nationality"));
        return soccerPlayerNationalityCol;
    }
    
    /**
     * Get {@link TableColumn} for {@link SoccerPlayer} district
     * @return table column of specified field
     */
    public static TableColumn<SoccerPlayer, String> getResidenceDistrictColumn() {
        TableColumn<SoccerPlayer, String> soccerPlayerResidenceDistrictCol = new TableColumn<>("Regione");
        soccerPlayerResidenceDistrictCol.setCellValueFactory(new PropertyValueFactory<>("residenceDistrict"));
        return soccerPlayerResidenceDistrictCol;
    }
    
    /**
     * Get {@link TableColumn} for {@link SoccerPlayer} residence address
     * @return table column of specified field
     */
    public static TableColumn<SoccerPlayer, String> getResidenceAddressColumn() {
        TableColumn<SoccerPlayer, String> soccerPlayerResidenceAddressCol = new TableColumn<>("Indirizzo");
        soccerPlayerResidenceAddressCol.setCellValueFactory(new PropertyValueFactory<>("residenceAddress"));
        return soccerPlayerResidenceAddressCol;
    }
    
    /**
     * Get {@link TableColumn} for {@link SoccerPlayer} residence city
     * @return table column of specified field
     */
    public static TableColumn<SoccerPlayer, String> getResidenceCityColumn() {
        TableColumn<SoccerPlayer, String> soccerPlayerResidenceCityCol = new TableColumn<>("Città");
        soccerPlayerResidenceCityCol.setCellValueFactory(new PropertyValueFactory<>("residenceCity"));
        return soccerPlayerResidenceCityCol;
    }
    
    /**
     * Get {@link TableColumn} for {@link SoccerPlayer} residence nation
     * @return table column of specified field
     */
    public static TableColumn<SoccerPlayer, String> getResidenceNationColumn() {
        TableColumn<SoccerPlayer, String> soccerPlayerResidenceNationCol = new TableColumn<>("Nazione");
        soccerPlayerResidenceNationCol.setCellValueFactory(new PropertyValueFactory<>("residenceNation"));
        return soccerPlayerResidenceNationCol;
    }
    
    /**
     * Get {@link TableColumn} for {@link SoccerPlayer} fiscal code
     * @return table column of specified field
     */
    public static TableColumn<SoccerPlayer, String> getFiscalCodeColumn() {
        TableColumn<SoccerPlayer, String> soccerPlayerFiscalCodeCol = new TableColumn<>("Codice Fiscale");
        soccerPlayerFiscalCodeCol.setCellValueFactory(new PropertyValueFactory<>("fiscalCode"));
        return soccerPlayerFiscalCodeCol;
    }
    
    /**
     * Get {@link TableColumn} for {@link SoccerPlayer} height
     * @return table column of specified field
     */
    public static TableColumn<SoccerPlayer, Double> getHeightColumn() {
        TableColumn<SoccerPlayer, Double> soccerPlayerHeightCol = new TableColumn<>("Altezza");
        soccerPlayerHeightCol.setCellValueFactory(new PropertyValueFactory<>("height"));
        return soccerPlayerHeightCol;
    }
    
    /**
     * Get {@link TableColumn} for {@link SoccerPlayer} weight
     * @return table column of specified field
     */
    public static TableColumn<SoccerPlayer, Double> getWeightColumn() {
        TableColumn<SoccerPlayer, Double> soccerPlayerWeightCol = new TableColumn<>("Peso");
        soccerPlayerWeightCol.setCellValueFactory(new PropertyValueFactory<>("weight"));
        return soccerPlayerWeightCol;
    }
    
    /**
     * Get {@link TableColumn} for {@link SoccerPlayer} main role
     * @return table column of specified field
     */
    public static TableColumn<SoccerPlayer, SoccerRole> getMainRoleColumn() {
        TableColumn<SoccerPlayer, SoccerRole> soccerPlaterMainRoleCol = new TableColumn<>("Ruolo Principale");
        soccerPlaterMainRoleCol.setCellValueFactory(new PropertyValueFactory<>("mainRole"));
        return soccerPlaterMainRoleCol;
    }
    
    /**
     * Get {@link TableColumn} list
     * @return table column list of all specified fields
     */
    public static List<TableColumn<SoccerPlayer, ?>> tableColumnList() {
        List<TableColumn<SoccerPlayer, ?>> list = new ArrayList<>();
        
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
        list.add(getHeightColumn());
        list.add(getWeightColumn());
        list.add(getMainRoleColumn());
        return list;
    }
}
