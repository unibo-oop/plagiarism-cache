package view.menu.profit;

import java.awt.BorderLayout;
import javax.swing.JPanel;

import view.menu.fair.WrongParametersException;
import view.model.activity.ActivityType;
import view.model.activity.ViewActivityBuilder;
import view.model.activity.ViewActivityImpl;

public class SettingPanel extends JPanel {

    private static final long serialVersionUID = 3798508884700068788L;
    private final ActivityType type;
    private final RangePanel rangePanel = new RangePanel();
    private final NamePanel namePanel = new NamePanel();

    public SettingPanel(final ActivityType type) {
        this.type = type;
        this.setLayout(new BorderLayout());
        this.add(namePanel, BorderLayout.NORTH);
        this.add(rangePanel, BorderLayout.CENTER);

    }

    /**
     * It builds a new {@link ViewActivityImpl} object where the parameters chosen 
     * are saved.
     * @return the ViewActivityImpl built
     * @throws WrongParametersException if any of the parameters
     * is in a wrong format
     */
    public ViewActivityImpl buildNewProfitActivity() throws WrongParametersException {
        try {
            return new ViewActivityBuilder(this.namePanel.getName(), this.type)
                    .minPrice(Integer.parseInt(this.rangePanel.getTextMin().getText()))
                    .maxPrice(Integer.parseInt(this.rangePanel.getTextMax().getText()))
                    .build();
        } catch (NumberFormatException exc) {
            throw new WrongParametersException();
        }
    }

    /**
     * @return the {@link RangePanel}
     */
    public RangePanel getRangePanel() {
        return rangePanel;
    }

    /**
     * @return the {@link NamePanel}
     */
    public NamePanel getNamePanel() {
        return namePanel;
    }

}

