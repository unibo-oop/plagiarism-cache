package file.data.input;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;

import color.filter.Filters;
import model.world.initialization.Modality;

/**
 * Classes that manage the loading of user-set from specific files.
 *
 */
public class FileGetSettingImpl implements FileGetSetting {

    //private static final URL FILE_URL = ClassLoader.getSystemResource("data" + File.separator + "data.user.txt");
    private static final URL FILE_SETTINGS_URL = ClassLoader.getSystemResource("data/data.user.txt");
    private int maxEnergy;
    private int maxSunLight;
    private int sizeGenoma;
    private int maxAge;
    private int heightWorld;
    private int widthWorld;
    private int upDateFrame;
    private int colorFilter;
    private int mod;
    private float sunPenetration;
    private float mineralDepth;
    private Optional<Float> hue;

    public FileGetSettingImpl() {
        FileSet f = new FileSetImpl();
        try (DataInputStream dstream = new DataInputStream(
                new BufferedInputStream(
                        new FileInputStream(new File(FILE_SETTINGS_URL.getFile()))));) {
            maxEnergy = dstream.readInt();
            maxSunLight = dstream.readInt();
            sizeGenoma = dstream.readInt();
            maxAge = dstream.readInt();
            heightWorld = dstream.readInt();
            widthWorld = dstream.readInt();
            upDateFrame = dstream.readInt();
            colorFilter = dstream.readInt();
            mod = dstream.readInt();
            sunPenetration = dstream.readFloat();
            mineralDepth = dstream.readFloat();
            if (colorFilter != Filters.NUTRITION.getValue()) {
                hue = Optional.of(dstream.readFloat());
            } else {
                hue = Optional.empty();
            }
            f.addtoFile(maxEnergy, maxSunLight, sizeGenoma, maxAge, heightWorld, widthWorld, upDateFrame, colorFilter, getMod(),
                    sunPenetration, mineralDepth, hue);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public final Modality getMod() {
        switch (mod) {
            case 0: return Modality.RANDOMS_GENOME_CELLS;
            case 1: return Modality.SINGLE_PHOTOSYNTHESIS_CELL;
            default: throw new IllegalArgumentException("MODALITY NON ESISTENTE");
        }
    }

    @Override
    public final int getMaxEnery() {
        return maxEnergy;
    }

    @Override
    public final int getSunLight() {
        return maxSunLight;
    }

    @Override
    public final int getHeightWorld() {
        return heightWorld;
    }

    @Override
    public final int getWidthWorld() {
        return widthWorld;
    }

    @Override
    public final int getUpDateFrame() {
        return upDateFrame;
    }

    @Override
    public final Filters getColorFilter() {
        return Filters.getEnum(colorFilter);
    }

    @Override
    public final float getHue() {
        return hue.get();
    }

    @Override
    public final int getSizeGenoma() {
        return sizeGenoma;
    }

    @Override
    public final int getMaxAge() {
        return maxAge;
    }

    @Override
    public final float getSunPenetration() {
        return sunPenetration;
    }

    @Override
    public final float getMineralDepth() {
        return mineralDepth;
    }

}
