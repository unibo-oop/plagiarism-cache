package mnmlwindow;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import mnmlwindow.controller.MinimalWindowC;
import mnmlwindow.controller.MinimalWindowCImpl;
import mnmlwindow.view.MinimalWindowViewImpl;

/**
 * Class that implements the interface for a custom window logic.
 * 
 * There was some solutions ready on internet, but we preferred to implement
 * ourselves a version of the window, simplifying some of the code.
 * 
 * Credits to original idea could be find here:
 * Undecorator: https://github.com/in-sideFX/Undecorator
 * Undecorator-bis: https://github.com/in-sideFX/UndecoratorBis
 * 
 * Some of the code present in that library is implemented and/or modified here.
 * Thanks to "in-sideFX" (https://github.com/in-sideFX) for this library.
 */
public class MinimalWindowImpl implements MinimalWindow {
    
    private final MinimalWindowViewImpl view;
    private final MinimalWindowC controller;
    private final Stage mainStage;
    
    /** 
     * Constructor for the MinimalWindow logic.
     * @param stage - the primaryStage of the method launch
     * @param minWidth - the minimum width of the window
     * @param minHeight - the minimum height of the window
     */
    public MinimalWindowImpl(final Stage stage, final int minWidth, final int minHeight) {
        //Takes the reference to primaryStage of launch method
        this.mainStage = stage;
        
        //Create the VIEW and respective CONTROLLER
        this.view = new MinimalWindowViewImpl(this.mainStage, minWidth, minHeight);
        this.controller = new MinimalWindowCImpl();
        
        //Setting controllers and views
        this.view.setControllerer(controller);
        this.controller.setView(view);
        
        //Set the scene with all it needs
        final Scene scene = new Scene(this.view, minWidth, minHeight);
        scene.setFill(null);
        
        //Initialize the stage to make it ready to show
        this.mainStage.initStyle(StageStyle.TRANSPARENT);
        this.mainStage.setScene(scene);
    }

    @Override
    public void setTitle(final String title) {
        this.view.setWindowTitle(title);    
    }

    @Override
    public String getTitle() {
        return view.getWindowTitle();
    }

    @Override
    public void setLogo(final Image logo) {
        this.view.setWindowLogo(logo);    
    }

    @Override
    public ImageView getLogo() {
        return this.view.getWindowLogo();
    }

    @Override
    public void setFooter(final String footer) {
        this.view.setWindowFooter(footer);     
    }

    @Override
    public String getFooter() {
        return this.view.getWindowFooter();
    }

    @Override
    public void setContent(final Node node) {
        this.view.setWindowContent(node);      
    }

    @Override
    public void showWindow() {
        this.mainStage.show();       
    }
}
