package view.gamescreen;

import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.SVGPath;
import model.state.StateInfo;
import view.ViewImpl;

/**
 * 
 * Class representation of a state. It has packed an Icon and a Tooltip.
 * Extends SVGPath.
 *
 */
public class State extends SVGPath {

    private static final String STATE_STYLE = "state-svg";
    private static final String TANK_LABEL_STYLE = "tank-info-lbl";

    private StateInfo stateRef;
    private TankIcon icon;
    private final Label nTanks = new Label("0");;

    private final Tooltip tp;
    private final Paint fill = Paint.valueOf("#000000");


    /**
     * 
     * Class constructor.
     * 
     * @param info
     *              String Array containing all info about current state.
     * 
     */
    public State(final String... info) {
        final String[] content = info[1].split(":");
        tp = new Tooltip();
        init(info[0].split(":"));
        Tooltip.install(this, tp);
        setContent(content[0]);
        this.icon = new TankIcon(content[1]);
        this.nTanks.getStyleClass().add(TANK_LABEL_STYLE);
        this.nTanks.setLayoutX(this.icon.getLayoutBounds().getMinX() + (this.icon.getLayoutBounds().getWidth() / 4));
        this.nTanks.setLayoutY(this.icon.getLayoutBounds().getMinY() + (this.icon.getLayoutBounds().getHeight() / 4));
        this.getStyleClass().add(STATE_STYLE);
        this.setHover(true);
        this.setDisable(true);
    }


    private void init(final String... info) {
        this.tp.setText(info[0]);
        this.setStateRef(info[0]);
        this.setFill(Color.web(info[2]));
    }

    private void setStateRef(final String stateName) {
        this.stateRef = ViewImpl.getIstance().getStateList().stream().filter(state -> state.getName().equalsIgnoreCase(stateName)).findFirst().get();
    }

    /**
     * 
     * Color getter.
     * 
     * @return
     *          Return the color of current state.
     * 
     */
    public Paint getColor() {
        return this.fill;
    }

    /**
     * 
     * Name getter.
     * 
     * @return
     *          Return the name of current state.
     * 
     */
    public String getName() {
        return this.stateRef.getName();
    }

    /**
     * 
     * Owner getter.
     * 
     * @return
     *          Return the owner of current state.
     * 
     */
    public String getOwner() {
        return this.stateRef.getPlayer().getName();
    }

    /**
     * 
     * Tanks number  getter.
     * 
     * @return
     *          Return the number of tanks in the current state.
     * 
     */
    public int getTanks() {
        return this.stateRef.getTanks();
    }

    /**
     * 
     * StateInfo reference getter.
     * 
     * @return
     *          StateInfo reference from model.
     */
    public StateInfo getStateRef() {
        return this.stateRef;

    }

    /**
     * 
     * Getter of tank icon object.
     * 
     * @return
     *          The icon.
     * 
     */
    public TankIcon getIcon() {
        return icon;
    }

    /**
     * 
     * Tank icon setter.
     * 
     * @param icon
     *              Object to be set as icon.
     */
    public void setIcon(final TankIcon icon) {
        this.icon = icon;
    }
 
    /**
     * 
     * Update tanks (number and color) displayed inside TankIcon.
     * 
     */
    public void updateTankIcon() {
        this.nTanks.setText(String.valueOf(getStateRef().getTanks()));
        getIcon().setColor(getStateRef().getPlayer().getColor().getPaint());
    }

    /**
     * 
     * Update number of tanks nTanks label.
     * 
     */
    public void updateTankIconNumber() {
        this.nTanks.setText(String.valueOf(Integer.parseInt(nTanks.getText()) + 1));
    }

    /**
     * 
     * Getter for label displaying tanks number.
     * 
     * @return
     *          The label.
     */
    public Label getnTanks() {
        return nTanks;
    }
}
