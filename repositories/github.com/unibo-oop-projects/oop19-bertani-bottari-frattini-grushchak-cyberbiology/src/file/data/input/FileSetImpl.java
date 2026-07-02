package file.data.input;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;

import data.Languages;
import model.world.initialization.Modality;

/**
 * Class that uploads data passed to a file by argument.
 *
 */
public class FileSetImpl implements FileSet {

    //private static final URL FILE_URL = ClassLoader.getSystemResource("data" + File.separator + "data.user.txt");
    private static final URL FILE_SETTINGS_URL = ClassLoader.getSystemResource("data/data.user.txt");
    private static final URL FILE_LANGUAGE_URL = ClassLoader.getSystemResource("data/data.language.txt");
    private static File f; 

    public FileSetImpl() {
        f = new File(FILE_SETTINGS_URL.getFile());
        checkFile(f);
    }

    @Override
    public final void addtoFile(final int maxEnergy, final int maxSunLight, final int sizeGenoma, final int maxAge, final int worldHSize, final int worldWSize,
            final int upDateview, final int filterValue, final Modality mod, final float sunPenetration, final float mineralDepth,
            final Optional<Float> hue) {
        try (DataOutputStream dstream = new DataOutputStream(
                new BufferedOutputStream(
                        new FileOutputStream(f)));) {
            dstream.flush();
            dstream.writeInt(maxEnergy);
            dstream.writeInt(maxSunLight);
            dstream.writeInt(sizeGenoma);
            dstream.writeInt(maxAge);
            dstream.writeInt(worldHSize);
            dstream.writeInt(worldWSize);
            dstream.writeInt(upDateview);
            dstream.writeInt(filterValue);
            switch (mod) {
                case RANDOMS_GENOME_CELLS: dstream.writeInt(0);
                case SINGLE_PHOTOSYNTHESIS_CELL: dstream.writeInt(1);
            }
            dstream.writeFloat(sunPenetration);
            dstream.writeFloat(mineralDepth);
            if (hue.isPresent()) {
                dstream.writeFloat(hue.get());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    @Override
    public final void addtoFile(final int maxEnergy, final int maxSunLight, final int sizeGenoma, final int maxAge, final int worldHSize,
            final int worldWSize, final int upDateview, final int filterValue, final Modality mod, final float sunPenetration, final float mineralDepth) {
        addtoFile(maxEnergy, maxSunLight, sizeGenoma, maxAge, worldHSize, worldWSize, upDateview, filterValue, mod,
                sunPenetration, mineralDepth, Optional.empty());
    }

    @Override
    public final void addtoFile(final Languages languages) {
        f = new File(FILE_LANGUAGE_URL.getFile());
        checkFile(f);
        try (DataOutputStream dstream = new DataOutputStream(
                new BufferedOutputStream(
                        new FileOutputStream(f)));) {
            dstream.flush();
            dstream.writeInt(languages.getValue());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void checkFile(final File f) {
        try {
            f.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
