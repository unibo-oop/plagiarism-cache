package application.view.controls.reserveStatus;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.HBox;

/**
 * Implements the ReserveStatus interface and contain all the logic of a reservestatus management.
 * @author Marcin Pabich
 */
public class ReserveStatusImpl extends HBox implements ReserveStatus {

    @FXML
    private ProgressBar pgbReserve;
	
    @FXML
    private Label lblName, lblPrice, lblReserveRemain, lblReserveMax;

    /**
     * Main constructor for the ReserveStatus.
     */
    public ReserveStatusImpl() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ReserveStatus.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
    
    /**
     * Secondary constructor for Reserve status that permit to set a name and reserve quantity.
     * @param name name of the fuel
     * @param price price of that fuel
     * @param remain remaining of that fuel in reserve
     * @param max max of that fuel in reserve
     * @param progress 0.0 means empty, 1.0 means full
     */
    public ReserveStatusImpl(final String name, final String price, final String remain, final String max, final Double progress) {
    	this();
 
        //Set controls values
        this.setFuelName(name);
        this.setPrice(price);
        this.setRemain(remain);
        this.setMaxReserve(max);
        this.setProgress(progress);
    }

    @Override
    public String getRemain() {
        return this.lblReserveRemain.getText();
    }

    @Override
    public void setRemain(final String value) {
        this.lblReserveRemain.setText(value.toString());
    } 
    
    @Override
    public String getMaxReserve() {
        return this.lblReserveMax.getText();
    }

    @Override
    public void setMaxReserve(final String value) {
        this.lblReserveMax.setText(value);
    }
    
    @Override
    public Double getProgress() {
        return this.pgbReserve.getProgress();
    }

    @Override
    public void setProgress(final Double value) {
        this.pgbReserve.setProgress(value);
    }
    
    
    @Override
    public String getFuelName() {
    	return this.lblName.getText();
    }
    
    @Override
    public void setFuelName(final String name) {
        this.lblName.setText(name);
    }

    @Override
    public void setPrice(final String value) {
        this.lblPrice.setText(value);
    }

    @Override
    public String getPrice() {
        return this.lblPrice.getText();
    }
}
