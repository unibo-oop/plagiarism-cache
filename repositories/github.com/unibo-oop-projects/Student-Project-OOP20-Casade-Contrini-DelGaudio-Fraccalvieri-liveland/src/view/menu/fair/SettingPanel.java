package view.menu.fair;

import java.awt.BorderLayout;
import javax.swing.JPanel;

import view.model.activity.ViewActivityBuilder;
import view.model.activity.ViewActivityImpl;

/**
 * This panel coordinates the other sub-panels: each one is in charge of
 * collecting a specific field required to create the fair.
 */
public class SettingPanel extends JPanel {

    private static final long serialVersionUID = 3798508884700068788L;
    private final FairTypePanel fTypePanel = new FairTypePanel();
    private final NamePanel namePanel = new NamePanel();
    private final CapacityPanel capacityPanel = new CapacityPanel();

    public SettingPanel() {
        this.setLayout(new BorderLayout());
        this.add(fTypePanel, BorderLayout.NORTH);
        this.add(namePanel, BorderLayout.LINE_START);
        this.add(capacityPanel, BorderLayout.LINE_END);
    }

    /**
     * It builds a new ViewActivityImpl object where the 
     * parameters chosen are saved.
     * @return the ViewActivityImpl built
     * @throws WrongParametersException if any of the parameters
     * is in a wrong format
     */
    public ViewActivityImpl buildNewFair() throws WrongParametersException {
        try {
            return new ViewActivityBuilder(this.namePanel.getName(), this.fTypePanel.getFairType())
                    .capacity(this.capacityPanel.getCapacity())
                    .build();
        } catch (NumberFormatException exc) {
            throw new WrongParametersException();
        }
    }

    /**
     * @return the {@link FairTypePanel}
     */
    public FairTypePanel getfTypePanel() {
        return fTypePanel;
    }

    /**
     * @return the {@link NamePanel}
     */
    public NamePanel getNamePanel() {
        return namePanel;
    }

    /**
     * @return the {@link CapacityPanel}
     */
    public CapacityPanel getCapacityPanel() {
        return capacityPanel;
    }

}
