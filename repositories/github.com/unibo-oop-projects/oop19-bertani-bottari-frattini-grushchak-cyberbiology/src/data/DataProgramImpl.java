package data;

import color.filter.Filters;
import file.data.input.FileGetSetting;
import file.data.input.FileGetSettingImpl;
import model.properties.defaultdata.CellsDefaultDataEnum;
import model.properties.defaultdata.GenesDefaultDataEnum;
import model.properties.defaultdata.ViewDefaultDataEnum;
import model.properties.defaultdata.WorldDefaultDataEnum;
import model.world.initialization.Modality;

/**
 * Class containing all the necessary values for the program entered by the user or taken by default.
 */
public class DataProgramImpl {
    private static int maxEnergy = CellsDefaultDataEnum.MAX_CELL_ENERGY.getData().getDafaultValue().intValue();
    private static int maxLight = GenesDefaultDataEnum.SUN_ENERGY.getData().getDafaultValue().intValue();
    private static int sizeGenoma = CellsDefaultDataEnum.NUMBER_GENE_TYPES.getData().getDafaultValue().intValue();
    private static int maxAge = CellsDefaultDataEnum.MAX_AGE.getData().getDafaultValue().intValue();
    private static int upDateView = ViewDefaultDataEnum.UPDATE_VIEW.getData().getDafaultValue().intValue();
    private static int worldHeight = WorldDefaultDataEnum.WORLD_HEIGHT.getData().getDafaultValue().intValue();
    private static int worldWidth = WorldDefaultDataEnum.WORLD_WIDTH.getData().getDafaultValue().intValue();
    private static Filters colorFilter = Filters.AGE;
    private static Modality modProg = Modality.SINGLE_PHOTOSYNTHESIS_CELL;
    private static float hueColor = ViewDefaultDataEnum.COLOR_HSB_RANGE.getData().getDafaultValue().intValue();
    private static float sunPenetration = GenesDefaultDataEnum.SUN_PENETRATION.getData().getDafaultValue().intValue();
    private static float mineralDepth = GenesDefaultDataEnum.MINERALS_DEPTH.getData().getDafaultValue().intValue();
    private static FileGetSetting file = new FileGetSettingImpl();

    public static final Modality getModProg() {
        if (modProg != file.getMod()) {
            modProg = file.getMod();
        }
        return modProg;
    }

    public static final int getMaxAge() {
        if (maxAge != file.getMaxAge()) {
            maxAge = file.getMaxAge();
        }
        return maxAge;
    }

    public static final float getHueColor() {
        if (hueColor != file.getHue()) {
            hueColor = file.getHue();
        }
        return hueColor;
    }

    public static final int getMaxEnergy() {
        if (maxAge != file.getMaxEnery()) {
            maxAge = file.getMaxEnery();
        }
        return maxEnergy;
    }

    public static final int getMaxLight() {
        if (maxLight != file.getSunLight()) {
            maxLight = file.getSunLight();
        }
        return maxLight;
    }

    public static final int getUpDateView() {
        if (upDateView != file.getUpDateFrame()) {
            upDateView = file.getUpDateFrame();
            System.out.println("UPDATE: " + colorFilter);
        }
        return upDateView;
    }

    public static final Filters getColorFilter() {
        if (colorFilter != file.getColorFilter()) {
            colorFilter = file.getColorFilter();
        }
        return colorFilter;
    }

    public static final int getWorldHeight() {
        if (worldHeight != file.getHeightWorld()) {
            worldHeight = file.getHeightWorld();
        }
        return worldHeight;
    }

    public static final int getWorldWidth() {
        if (worldWidth != file.getWidthWorld()) {
            worldWidth = file.getWidthWorld();
        }
        return worldWidth;
    }

    public static float getSunPenetration() {
        if (sunPenetration != file.getSunPenetration()) {
            sunPenetration = file.getSunPenetration();
        }
        return sunPenetration;
    }

    public static int getSizeGenoma() {
        if (sizeGenoma != file.getSizeGenoma()) {
            sizeGenoma = file.getSizeGenoma();
        }
        return sizeGenoma;
    }

    public static float getMineralDepth() {
        if (mineralDepth != file.getMineralDepth()) {
            mineralDepth = file.getMineralDepth();
        }
        return mineralDepth;
    }

}
