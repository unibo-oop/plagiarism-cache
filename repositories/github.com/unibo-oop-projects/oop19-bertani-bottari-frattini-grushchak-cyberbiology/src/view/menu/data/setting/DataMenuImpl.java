package view.menu.data.setting;

import java.awt.BorderLayout;
import java.util.Optional;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.SizeAction;

import color.filter.Filters;
import controller.initialization.SimulationInitializator;
import data.Language;
import file.data.input.FileSet;
import file.data.input.FileSetImpl;
import model.properties.defaultdata.CellsDefaultDataEnum;
import model.properties.defaultdata.DefaultDataContainer;
import model.properties.defaultdata.GenesDefaultDataEnum;
import model.properties.defaultdata.WorldDefaultDataEnum;
import model.world.initialization.Modality;
import utilities.DimensionComponent;
import utilities.Icon;

/**
 * Class that implements a frame containing all the panels to set the values by the user.
 * Once the values have been set, it calls the FileSet class and loads to file.
 *
 */
public class DataMenuImpl extends JFrame implements DataMenu {
    /** 
     */
    private static final long serialVersionUID = -4256558330444585208L;
    @SuppressWarnings("unchecked")
    private final DefaultDataContainer<Integer> sunRangeData = (DefaultDataContainer<Integer>) GenesDefaultDataEnum.SUN_ENERGY.getData();
    @SuppressWarnings("unchecked")
    private final DefaultDataContainer<Integer> energyRangeData = (DefaultDataContainer<Integer>) CellsDefaultDataEnum.MAX_CELL_ENERGY.getData();
    @SuppressWarnings("unchecked")
    private final DefaultDataContainer<Float> sunPenetrationData = (DefaultDataContainer<Float>) GenesDefaultDataEnum.SUN_PENETRATION.getData();
    @SuppressWarnings("unchecked")
    private final DefaultDataContainer<Float> mineralDepthData = (DefaultDataContainer<Float>) GenesDefaultDataEnum.SUN_PENETRATION.getData();
    @SuppressWarnings("unchecked")
    private final DefaultDataContainer<Integer> maxAgeData = (DefaultDataContainer<Integer>) CellsDefaultDataEnum.MAX_AGE.getData();
    @SuppressWarnings("unchecked")
    private final DefaultDataContainer<Integer> sizeGenData = (DefaultDataContainer<Integer>) CellsDefaultDataEnum.NUMBER_GENE_TYPES.getData();

    private AddElem menuDesc = new AddMenuDescriptrion();
    private AddElemValue<Modality> modProgram = new AddModality();
    private AddElemValue<Integer> maxEnergy = new AddSlider(Language.getkeyofbundle("MaxEnergy"), 
            energyRangeData.getMinimumValue().intValue(), energyRangeData.getMaximumValue().intValue(), energyRangeData.getDafaultValue().intValue());
    private AddElemValue<Integer> maxSunLight = new AddSlider(Language.getkeyofbundle("MaxSunLight"), 
            sunRangeData.getMinimumValue().intValue(), sunRangeData.getMaximumValue().intValue(), sunRangeData.getDafaultValue().intValue());
    private AddElemValue<Integer> sunPenetration = new AddSlider(Language.getkeyofbundle("SunPenetration"), 
            sunPenetrationData.getMinimumValue().floatValue(), sunPenetrationData.getMaximumValue().floatValue(), sunPenetrationData.getDafaultValue().floatValue());
    private AddElemValue<Integer> mineralDepth = new AddSlider(Language.getkeyofbundle("MineralDepth"), 
            mineralDepthData.getMinimumValue().floatValue(), mineralDepthData.getMaximumValue().floatValue(), mineralDepthData.getDafaultValue().floatValue());
    private AddElemValue<Integer> sizeGenoma = new AddSlider(Language.getkeyofbundle("GenomSize"), 
            sizeGenData.getMinimumValue().intValue(), sizeGenData.getMaximumValue().intValue(), sizeGenData.getDafaultValue().intValue());
    private AddElemValue<Integer> maxAge = new AddSlider(Language.getkeyofbundle("MaxAge"), 
            maxAgeData.getMinimumValue().intValue(), maxAgeData.getMaximumValue().intValue(), maxAgeData.getDafaultValue().intValue());

    @SuppressWarnings("unchecked")
    private AddElemValue<Integer> worldHSize = new AddWorldSize(Language.getkeyofbundle("SizeHWorld"), (DefaultDataContainer<Integer>) WorldDefaultDataEnum.WORLD_HEIGHT.getData());
    @SuppressWarnings("unchecked")
    private AddElemValue<Integer> worldWSize = new AddWorldSize(Language.getkeyofbundle("SizeWWorld"), (DefaultDataContainer<Integer>) WorldDefaultDataEnum.WORLD_WIDTH.getData());
    private AddElemValue<Integer> upDateview = new AddUpDateView();
    private AddElemValue<Float> colorChoose = new AddColorChoose();
    private AddElemValue<Integer> filterColor = new AddFilterColor(colorChoose);
 
    public DataMenuImpl() {
        this.setIconImage(Icon.SETTING.getIcon().getImage());
        this.setTitle(Language.getkeyofbundle("TitleMenu"));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(DimensionComponent.DATA_MENU_FRAME.getDimension());
        this.setLocationRelativeTo(null);
        this.addElem();
    }

    @Override
    public final void showDataMenu() {
        this.setVisible(true);
    }

    private void setValue() {
        FileSet f = new FileSetImpl();
        int filterValue = filterColor.getValue();

        if (filterValue != Filters.NUTRITION.getValue()) {
            f.addtoFile(maxEnergy.getValue(), maxSunLight.getValue(), sizeGenoma.getValue(), maxAge.getValue(),
                    worldHSize.getValue(), worldWSize.getValue(), upDateview.getValue(), filterValue, modProgram.getValue(),
                    (float) sunPenetration.getValue() / 100, (float) mineralDepth.getValue() / 100, Optional.of(colorChoose.getValue()));
        } else {
            f.addtoFile(maxEnergy.getValue(), maxSunLight.getValue(), sizeGenoma.getValue(), maxAge.getValue(),
                    worldHSize.getValue(), worldWSize.getValue(), upDateview.getValue(), filterValue, modProgram.getValue(),
                    (float) sunPenetration.getValue() / 100, (float) mineralDepth.getValue() / 100);
        }
    }

    private JPanel addElem() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        this.getContentPane().add(BorderLayout.CENTER, panel);

        panel.add(menuDesc.getElem());
        panel.add(modProgram.getElem());
        panel.add(maxEnergy.getElem());
        panel.add(maxSunLight.getElem());
        panel.add(sunPenetration.getElem());
        panel.add(mineralDepth.getElem());
        panel.add(sizeGenoma.getElem());
        panel.add(maxAge.getElem());
        panel.add(worldHSize.getElem());
        panel.add(worldWSize.getElem());
        panel.add(upDateview.getElem());
        panel.add(filterColor.getElem());
        panel.add(new JSeparator());
        panel.add(startButton()); 
        return panel;
    }


    private JPanel startButton() {
        JPanel panel = new JPanel();
        JButton jb = new JButton(Language.getkeyofbundle("ButtonStart"));
        jb.addActionListener(a -> {
            setValue();
            this.dispose();
            new SimulationInitializator();
        });
        panel.add(jb);
        return panel;
    }
}
