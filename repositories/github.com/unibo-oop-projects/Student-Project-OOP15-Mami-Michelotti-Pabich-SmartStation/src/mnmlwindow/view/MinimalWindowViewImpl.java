package mnmlwindow.view;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.BoundingBox;
import javafx.geometry.Rectangle2D;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import mnmlwindow.controller.MinimalWindowC;

/**
 * Class that implements the view of a minimal window logic.
 */
public class MinimalWindowViewImpl extends BorderPane implements MinimalWindowView {
        
    //Values injected from the FXML
    @FXML
    private BorderPane mainWindow, contentArea;
        
    @FXML
    private Label lblTitle, lblFooter;
    
    @FXML
    private Button btnMax, btnResize;
    
    @FXML
    private ImageView imgLogo;
    
    
    //Reference to the primaryStage
    private final Stage stage;
    
    //References to min/max width/height and the shadow effect
    private final int minWidth, minHeight;
      
    //Thi two values will be the updated X and Y coordinates of the
    //window when it will be change size and position.
    private double actualX, actualY;
    
    //Things for moving window
    private boolean isMovable = true;
    
    //Saving of screen bounds
    private BoundingBox savedBounds;
    
    //Status of the window, because of "bugged" isMaximized of the Stage class
    private boolean isMaximized;
    
    //The controller
    private MinimalWindowC controller;
    

    
    
    /**
     * Constructor for the minimal window view.
     * @param stg Stage that will be used
     * @param minwidth minimum width of the window
     * @param minheight minimum height of the window
     */
    public MinimalWindowViewImpl(final Stage stg, final int minwidth, final int minheight) {
        //First, take the reference to the stage
        this.stage = stg;
            
        //Taking the references to the window
        this.minWidth = minwidth;
        this.minHeight = minheight;       
        
        //It starts not maximized
        this.isMaximized = false;
        
        //Then load the window, setting the root and controller
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/mnmlwindow/view/MinimalWindow.fxml"));
        loader.setRoot(this);
        loader.setController(this);             
        
        //Try to load
        try {
            loader.load();            
        } catch (Exception exception) {
            this.showErrorMessage("FXML error", "MinimalWindow cannot be loaded", "An error occurred when loading MinimalWindow.fxml:\n" + exception.getMessage() + "\nAborting.");
            System.exit(4);
        }
    }
    
    
    
    /////////////////////////////
    // Getters and Setters
    /////////////////////////////
    
    //Setter for the controller
    @Override
    public void setControllerer(final MinimalWindowC ctrl) {
        this.controller = ctrl;
    }
    
    
    //Getter and setter for the window logo
    @Override
    public void setWindowLogo(final Image logo) { 
        this.imgLogo.setImage(logo);
    }
    
    @Override
    public ImageView getWindowLogo() { 
        return this.imgLogo;
    }
    
    
    //Getter and setter for the title
    @Override
    public void setWindowTitle(final String s) { 
        lblTitle.setText(s); 
    }
    
    @Override
    public String getWindowTitle() { 
        return this.lblTitle.getText(); 
    }
    
    
    //Getter and setter for the footer
    @Override
    public void setWindowFooter(final String s) { 
        lblFooter.setText(s); 
    }
    
    @Override
    public String getWindowFooter() { 
        return this.lblFooter.getText(); 
    }
    
    
    //Getter and setter for the main content
    @Override
    public void setWindowContent(final Node node) { 
        contentArea.setCenter(node); 
    }
    
    @Override
    public Node getWindowContent() { 
        return contentArea; 
    }
    
    
    //Getters and setters for the window movable status
    @Override
    public boolean isWindowMovable() { 
        return this.isMovable; 
    }
    
    @Override
    public void setNotMovableWindow() { 
        this.isMovable = false; 
    }
    
    @Override
    public void setMovableWindow() { 
        this.isMovable = true; 
    }
    
    
    //Getters and setters for actualX
    @Override
    public double getWindowActualX() { 
        return this.actualX; 
    }
    
    @Override
    public void setWindowActualX(final double x) { 
        this.actualX = x; 
    }
    
    
    //Getters and setters for actualY
    @Override
    public double getWindowActualY() { 
        return this.actualY; 
    }
    @Override
    public void setWindowActualY(final double y) { 
        this.actualY = y; 
    }
    
    
    //Getters and setters for X
    @Override
    public double getWindowX() { 
        return this.stage.getX(); 
    }
    @Override
    public void setWindowX(final double x) { 
        this.stage.setX(x); 
    }
    
    
    //Getters and setters for Y
    @Override
    public double getWindowY() { 
        return this.stage.getY(); 
    }
    @Override
    public void setWindowY(final double y) { 
        this.stage.setY(y); 
    }
    
    
    //Getter for the shadow size
    @Override
    public int getWindowShadowSize() { 
        return (int) WindowStatus.NORMAL.getShadowSpace().getLeft(); 
    }
    
    
    //Methods to change window status [iconified (min) / maximize (full) / normal (window)]
    @Override
    public void minimizeWindow() { 
        this.stage.setIconified(true); 
    }
    @Override
    public void maximizeWindow() { 
        changeWindowStatus(WindowStatus.MAXIMIZED); 
    }
    @Override
    public void normalizeWindow() { 
        changeWindowStatus(WindowStatus.NORMAL); 
    }
    
    
    //Getter for the maximize property
    @Override
    public boolean isWindowMaximized() { 
        return this.isMaximized;
    }
    
    
    //Getters and setters for width properities
    @Override
    public void setWindowWidth(final double width) { 
        this.stage.setWidth(width); 
    }
    @Override
    public double getWindowMinWidth() { 
        return this.minWidth;
    }
    @Override
    public double getWindowWidth() { 
        return this.stage.getWidth(); 
    }
    
    
    //Getters and setters for height properties
    @Override
    public void setWindowHeight(final double height) { 
        this.stage.setHeight(height); 
    }
    @Override
    public double getWindowMinHeight() { 
        return this.minHeight; 
    }
    @Override
    public double getWindowHeight() { 
        return this.stage.getHeight(); 
    }
    
    
    //Setting the mouse cursor
    @Override
    public void setMouseCursor(final Cursor cursor) { 
        this.mainWindow.setCursor(cursor); 
    }
    
    
    //Closing the window
    @Override
    public void closeWindow() { 
        this.stage.close(); 
    }
    
    
    
    //Private methods
    private void changeWindowStatus(final WindowStatus status) {            
        //If the window shoud be normalized
        if (status.equals(WindowStatus.NORMAL)) {
            //Take previous bounds     
            this.changeStageXYWH(this.stage, savedBounds.getMinX(), savedBounds.getMinY(), savedBounds.getWidth(), savedBounds.getHeight());  
            
        } else {
            //Take the screen size (muliscren support)
            ObservableList<Screen> screensForRectangle = Screen.getScreensForRectangle(stage.getX(), stage.getY(), stage.getWidth(), stage.getHeight());
            
            //Take the first screen and it's bounds
            Screen screen = screensForRectangle.get(0);
            Rectangle2D visualBounds = screen.getVisualBounds();      
            
            //Save dimensions for restoring previous dimension
            savedBounds = new BoundingBox(stage.getX(), stage.getY(), stage.getWidth(), stage.getHeight());
            
            //Maximize
            this.changeStageXYWH(this.stage, visualBounds.getMinX(), visualBounds.getMinY(), visualBounds.getWidth(), visualBounds.getHeight());   
        }
        
        //Set some buttons and UI changes
        this.isMaximized = status.getWindowMaximizedStatus();
        this.btnResize.setVisible(status.getResizeButtonVisibility());
        this.btnMax.setStyle(status.getMaximizeLogoPath());
        this.mainWindow.setPadding(status.getShadowSpace());
    }
    
    private void showErrorMessage(final String title, final String header, final String content) {
        final Alert alertDialog = new Alert(AlertType.ERROR);
        alertDialog.setTitle(title);
        alertDialog.setHeaderText(header);
        alertDialog.setContentText(content);
        alertDialog.showAndWait();
    }
    
    private void changeStageXYWH(final Stage stg, final double minX, final double minY, final double width, final double height) {
        stg.setX(minX);
        stg.setY(minY);
        stg.setWidth(width);
        stg.setHeight(height);   
    }
    
    
    
    /////////////////////////////
    // Event handlers
    /////////////////////////////
      
    
    
    // MIMIZIE | MAXIMIZE | CLOSE 
    //When pressed, will minimize the window to tray
    @FXML
    private void btnMin_click(final MouseEvent e) {
            this.controller.minimize();
    }
    
    //When pressed, check if it must maximize or restore the window
    @FXML
    private void btnMax_click(final MouseEvent e) {
            this.controller.maximize();          
    }
    
    //When pressed, will kill the window
    @FXML
    private void btnCls_click(final MouseEvent e) {
            this.controller.close();
    }
    
    
    
    // WINDOW MOVING   
    //When i must update the XY of the click
    @FXML
    private void root_onMousePressed(final MouseEvent e) {
        this.controller.updateValues(e);
    }
    
    //When pressing and dragging the mouse it will move the window
    @FXML
    private void root_onMouseDragged(final MouseEvent e) {
        this.controller.dragWindow(e);
    }
    
    //Update the status of the window from not movable to movable, after "normalize" effect
    //from the dragging it when it's maximized
    @FXML
    private void root_onMouseReleased(final MouseEvent e) {
        this.controller.dragWindowOver();
    }
    
    
    
    // WINDOW RESIZING           
    @FXML
    private void btnResize_onMouseClicked(final MouseEvent e) {
        this.controller.updateValues(e);
    }
    
    @FXML
    private void btnResize_OnMouseReleased(final MouseEvent e) {
        this.controller.setCursorToDefault(e);
    }
            
    @FXML
    private void btnResize_onMouseEntered(final MouseEvent e) {
        this.controller.setCursorToResize();
    }
    
    @FXML
    private void btnResize_onMouseExited(final MouseEvent e) {
        this.controller.setCursorToDefault(e);
    }
    
    @FXML
    private void btnResize_onMouseDragged(final MouseEvent e) {
        this.controller.resizeWindow(e);
    }
}
