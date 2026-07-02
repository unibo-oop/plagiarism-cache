package com.biaren.sportclubmanager.corebundle.viewparts;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import com.biaren.sportclubmanager.adminbundle.controller.LoginController;
import com.biaren.sportclubmanager.corebundle.model.BaseModelImpl;
import com.biaren.sportclubmanager.utility.enums.TextStrings;
import com.biaren.utility.BiarenPathHandler;
import com.biaren.utility.viewparts.DateTimeBox;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * Creates the Home Header Panel.
 * Presents a left content to show Society's Logo,
 * a center content to show Society's Name and a right
 * content to show infornations of current state.
 * @author nbrunetti
 *
 */
public class HomeHeaderPanel extends HBox {

    private VBox leftContentBox;
    private VBox centerContentBox;
    private VBox rightContentBox;
    private Button logoutButton;
    private DateTimeBox currentDateTime;
    
    /**
     * Creates a {@link HomeHeaderPanel} and set contents.
     */
    public HomeHeaderPanel() {
        this.leftContentBox = new VBox();
        this.centerContentBox = new VBox();
        this.rightContentBox = new VBox();
        this.logoutButton = new Button(TextStrings.LOGOUT_LABEL.toString());
        this.currentDateTime = new DateTimeBox();
        this.setLayout();
    }
    
    private void setLayout() {
        this.setCenterContent();
        this.setLeftContent();
        this.setRightContent();
        this.getChildren().addAll(this.leftContentBox, this.centerContentBox, this.rightContentBox);
    }
    
    private void setLeftContent(){
        Image i;
        try {
            i = new Image(new FileInputStream(BiarenPathHandler.getResourcesAbsolutePathString() 
                    + File.separator + "logo" + File.separator + "society" + File.separator + TextStrings.SOCIETY_LOGO_FILE_NAME.toString()));
            ImageView iv = new ImageView(i);
            iv.setFitHeight(100);
            iv.setFitWidth(100);
            this.leftContentBox.getChildren().add(iv);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    private void setCenterContent() {
        this.centerContentBox.getChildren().add(new Label(BaseModelImpl.getInstance().getBusinessName() + " " + BaseModelImpl.getInstance().getTeamName()));
    }
    
    private void setRightContent() {
        HBox currentUserBox = new HBox(new Label(LoginController.getInstance().getUserLoggedIn().get().getUsername()), this.logoutButton);
        HBox currentDateTimeBox = new HBox(this.currentDateTime);
        this.rightContentBox.getChildren().addAll(currentUserBox, currentDateTimeBox);
    }
    
    /**
     * Get button reference.
     * @return {@link Button} reference.
     */
    public Button getLogoutButton() {
        return this.logoutButton;
    }
   
}
