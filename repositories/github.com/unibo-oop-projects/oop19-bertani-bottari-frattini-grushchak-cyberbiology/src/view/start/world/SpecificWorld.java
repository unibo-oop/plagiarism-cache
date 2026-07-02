package view.start.world;

import java.awt.Component;
import java.util.HashMap;
import java.util.Map;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import data.Language;
import model.world.CellInfos;
import utilities.DimensionComponent;
import view.menu.data.setting.AddElem;

/**
 * Class that implements a panel with information about the world.
 *
 */
public class SpecificWorld extends JPanel implements UpdateSpecific, AddElem, UpDateElem {

    /**
     */
    private static final long serialVersionUID = 745320494116267779L;
    private final Map<String, JLabel> labels = initMap();
    private final CellInfos worldInfo;
    private JLabel contUpdateFrame = new JLabel();
    private JLabel contCellAlive = new JLabel();
    private JLabel contCellDeath = new JLabel();
    private JLabel contDay = new JLabel();
    private JLabel contAge = new JLabel();
    private JLabel contEnergy = new JLabel();
    /*private JLabel contNGenes = new JLabel();
    private JLabel contEnAlt = new JLabel();
    private JLabel contEnCar = new JLabel();
    private JLabel contEnMin = new JLabel();
    private JLabel contEnPh = new JLabel();*/

    /**
     * Builder that generates a JPanel and adds the strings and values that you want to display in the specifications.
     * @param worldInfo TO DOOOOO
     */
    public SpecificWorld(final CellInfos worldInfo) {
        this.worldInfo = worldInfo;
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        TitledBorder titleBorder = new TitledBorder(Language.getkeyofbundle("Specific"));
        panel.setPreferredSize(DimensionComponent.SPECIFIC_DISPLAY.getDimension());
        panel.setBorder(titleBorder);
        panel.add(addCont(labels.get("CountUpDate"), contUpdateFrame));
        panel.add(addCont(labels.get("CountDay"), contDay));
        panel.add(addCont(labels.get("CountCellAlive"), contCellAlive));
        panel.add(addCont(labels.get("CountCellDeath"), contCellDeath));
        panel.add(addCont(labels.get("AgeMedium"), contAge));
        /*panel.add(addCont(labels.get("NGenesMedium"), contNGenes));
        panel.add(addCont(labels.get("EnergyAltr"), contEnAlt));
        panel.add(addCont(labels.get("EnergyCarn"), contEnCar));
        panel.add(addCont(labels.get("EnergyMin"), contEnMin));
        panel.add(addCont(labels.get("EnergyPhot"), contEnPh));*/
        this.add(panel);
    }

    @Override
    public final Component getElem() {
        return this;
    }

    @Override
    public final void updateSpecific(final int contUpdateWorld, final int contDayVal) {
        contUpdateFrame.setText(" " + (contUpdateWorld - 1));
        contDay.setText(" " + contDayVal);
        contCellAlive.setText(" " + worldInfo.getAliveCellsNumber());
        contCellDeath.setText(" " + worldInfo.getDeadCellsNumber());
        contAge.setText(" " + worldInfo.getMediumAge());
        contEnergy.setText(" " + worldInfo.getMediumTotalEnergy());
        /*contNGenes.setText(" " + worldInfo.getMediumNumberofGenes());
        contEnAlt.setText(" " + worldInfo.getMediumPercAltruismEnergy() + "%");
        contEnCar.setText(" " + worldInfo.getMediumPercEatingEnergies() + "%");
        contEnMin.setText(" " + worldInfo.getMediumPercMineralEnergy() + "%");
        contEnPh.setText(" " + worldInfo.getMediumPercPhotosyntesisEnergy() + "%");*/
    }

    @Override
    public final void updateElem() {
        labels.forEach((s, jl) -> jl.setText(Language.getkeyofbundle(s)));
    }


    private JPanel addCont(final JLabel descLabel, final JLabel contLabel) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        panel.setAlignmentX(LEFT_ALIGNMENT);
        panel.add(descLabel);
        panel.add(contLabel);
        return panel;
    }

    private Map<String, JLabel> initMap() {
        Map<String, JLabel> map = new HashMap<>();
        map.put("CountUpDate", new JLabel(Language.getkeyofbundle("CountUpDate")));
        map.put("CountCellAlive", new JLabel(Language.getkeyofbundle("CountCellAlive")));
        map.put("CountCellDeath", new JLabel(Language.getkeyofbundle("CountCellDeath")));
        map.put("CountDay", new JLabel(Language.getkeyofbundle("CountDay")));
        map.put("AgeMedium", new JLabel(Language.getkeyofbundle("AgeMedium")));
        map.put("EnergyMedium", new JLabel(Language.getkeyofbundle("EnergyMedium")));
        map.put("AgeMedium", new JLabel(Language.getkeyofbundle("AgeMedium")));
        map.put("EnergyMedium", new JLabel(Language.getkeyofbundle("EnergyMedium")));
        //map.put("NGenesMedium", new JLabel(Language.getkeyofbundle("NGenesMedium")));
        //map.put("EnergyAltr", new JLabel(Language.getkeyofbundle("EnergyAltr")));
        //map.put("EnergyCarn", new JLabel(Language.getkeyofbundle("EnergyCarn")));
        //map.put("EnergyMin", new JLabel(Language.getkeyofbundle("EnergyMin")));
        //map.put("EnergyPhot", new JLabel(Language.getkeyofbundle("EnergyPhot")));
        return map;
    }
}
