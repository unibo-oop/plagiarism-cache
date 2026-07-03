package view.game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.RaceDirectionImpl;
import utility.Driver;
import utility.Position;
/**
 * Implementation of interface DriverView.
 *
 */
public class DriverViewImpl extends ImageView implements DriverView {

    /**
     * Standard dimension of car's image.
     */
    public static final int STANDARD_DIM = 20;
    private static final double SPACE_BOX = 2.5;
    private final Driver driver;
    private Position pos;
    /**
     * Constructor of DriverView.
     * @param driver of this DriverView
     * @param slot where is this driver
     */
    public DriverViewImpl(final Driver driver, final Slot slot) {
        super();
        this.driver = driver;
        Tooltip.install(this, new Tooltip(driver.toString()));
        this.setPreserveRatio(true);
        this.setFitHeight(STANDARD_DIM);
        switch(this.getDriver().getTeam()) {
            case MER:
                this.setImage(new Image(this.getClass().getResourceAsStream("/cars/mercedes.png")));
                break;
            case FER:
                this.setImage(new Image(this.getClass().getResourceAsStream("/cars/ferrari.png")));
                break;
            case RDB:
                this.setImage(new Image(this.getClass().getResourceAsStream("/cars/redbull.png")));
                break;
            case MCL: 
                this.setImage(new Image(this.getClass().getResourceAsStream("/cars/mclaren.png")));
                break;
            case REN: 
                this.setImage(new Image(this.getClass().getResourceAsStream("/cars/renault.png")));
                break;
            default:
                break;
        }
        this.moveDriver(slot, ViewGameImpl.STD_WIDTH, ViewGameImpl.STD_HEIGHT);
    }

    @Override
    public void moveDriver(final Slot slot, final double width, final double height) {
        if (slot.getPosition().equals(RaceDirectionImpl.BOX_POS) 
                || slot.getPosition().equals(RaceDirectionImpl.END_POS)) {
            final List<Driver> list = new ArrayList<>(Arrays.asList(Driver.values()));
            this.setTranslateX((slot.getXCoord() + (list.indexOf(this.driver) * SPACE_BOX)) * width / 100);
        } else {
            this.setTranslateX(slot.getXCoord() * width / 100);
        }
        this.setTranslateY(slot.getYCoord() * height / 100);
        this.setRotate(slot.getRotation());
        this.pos = slot.getPosition();
    }

    @Override
    public Driver getDriver() {
        return this.driver;
    }

    @Override
    public Position getPosition() {
        return this.pos;
    }
}
