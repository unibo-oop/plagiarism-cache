package com.biaren.sportclubmanager.corebundle.viewutil;

import java.util.ArrayList;
import java.util.List;

import com.biaren.sportclubmanager.corebundle.model.interfaces.SportVenue;

import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

//UNUSED
public class SportVenueTableUtil {

    public static TableColumn<SportVenue, Integer> getIdColumn() {
        TableColumn<SportVenue, Integer> sportVenueIdCol = new TableColumn<>("ID");
        sportVenueIdCol.setCellValueFactory(new PropertyValueFactory<>("sportVenueId"));
        return sportVenueIdCol;
    }

    public static TableColumn<SportVenue, String> getNameColumn() {
        TableColumn<SportVenue, String> sportVenueNameCol = new TableColumn<>("Nome");
        sportVenueNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        return sportVenueNameCol;
    }
    
    public static TableColumn<SportVenue, String> getAddressColumn() {
        TableColumn<SportVenue, String> sportVenueAddressCol = new TableColumn<>("Indirizzo");
        sportVenueAddressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        return sportVenueAddressCol;
    }
    
    public static TableColumn<SportVenue, String> getDescriptionColumn() {
        TableColumn<SportVenue, String> sportVenueDescriptionCol = new TableColumn<>("Descrizione");
        sportVenueDescriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        return sportVenueDescriptionCol;
    }
    
    public static TableColumn<SportVenue, Integer> getTotalSeatsColumn() {
        TableColumn<SportVenue, Integer> sportVenueTotalSeatsCol = new TableColumn<>("Posti totali");
        sportVenueTotalSeatsCol.setCellValueFactory(new PropertyValueFactory<>("totalSeats"));
        return sportVenueTotalSeatsCol;
    }
    
    public static TableColumn<SportVenue, Double> getFieldLengthColumn() {
        TableColumn<SportVenue, Double> sportVenueFieldLengthCol = new TableColumn<>("Lunghezza Campo");
        sportVenueFieldLengthCol.setCellValueFactory(new PropertyValueFactory<>("fieldLength"));
        return sportVenueFieldLengthCol;
    }
    
    public static TableColumn<SportVenue, Double> getFieldWidthColumn() {
        TableColumn<SportVenue, Double> fieldWidthCol = new TableColumn<>("Larghezza campo");
        fieldWidthCol.setCellValueFactory(new PropertyValueFactory<>("fieldWidth"));
        return fieldWidthCol;
    }
    
    public static List<TableColumn<SportVenue, ?>> tableColumnList() {
        List<TableColumn<SportVenue, ?>> list = new ArrayList<>();
    
        list.add(getIdColumn());
        list.add(getNameColumn());
        list.add(getAddressColumn());
        list.add(getDescriptionColumn());
        return list;
    }
}
