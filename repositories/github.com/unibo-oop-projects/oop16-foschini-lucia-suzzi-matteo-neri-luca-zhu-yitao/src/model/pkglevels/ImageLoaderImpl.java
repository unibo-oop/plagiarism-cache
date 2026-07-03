package model.pkglevels;

import java.awt.Image;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.ImageIcon;

/**
 * Implementation of the ImageLoader interface.
 * 
 * 
 *
 */
public final class ImageLoaderImpl implements ImageLoader {
    private List<Pipe> allTubes;
    private ImageIcon START, FINISH, EMPTY;

    private PipeImpl t1, t2, t3, t4, t5, t6;
    private static final ImageLoaderImpl SINGLETON = new ImageLoaderImpl();
    private static String newPath;
    private final File solutionsFile;

    private File newSolutionDest;

    private File newCustomDest;

    private File newScoreDest;

    private File newLogInDest;
    private boolean b;

    private ImageLoaderImpl() {

        newPath = System.getProperty("user.home") + System.getProperty("file.separator") + ".PlumberSupportFolder";
        String cmd = "attrib +h " + newPath;
        newSolutionDest = new File(
                ImageLoaderImpl.getNewPath() + System.getProperty("file.separator") + "plumberSolutionsFile.txt");

        newCustomDest = new File(
                ImageLoaderImpl.getNewPath() + System.getProperty("file.separator") + "customSolutionsFile.txt");

        newScoreDest = new File(
                ImageLoaderImpl.getNewPath() + System.getProperty("file.separator") + "levelsScoreFile.txt");

        newLogInDest = new File(ImageLoaderImpl.getNewPath() + System.getProperty("file.separator") + "login.txt");
        File dir = new File(ImageLoaderImpl.getNewPath());
        if (dir.exists()) {
            newLogInDest.delete();
            newSolutionDest.delete();
            newLogInDest.delete();
            newScoreDest.delete();
            newCustomDest.delete();
            dir.delete();
        }
        b = (new File(ImageLoaderImpl.getNewPath())).mkdir(); // create new
                                                              // directory in
                                                              // user desktop
                                                              // folder

        // make the created directory hidden
        try {
            Runtime.getRuntime().exec(cmd);
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        solutionsFile = new File(
                System.getProperty("user.dir") + System.getProperty("file.separator") + "tubi/plumberSolutions.txt");

        /* file creation in the new folder */
        try {
            // b = newLogInDest.createNewFile();
            b = newScoreDest.createNewFile();
            b = newCustomDest.createNewFile();
        } catch (IOException e) {

            e.printStackTrace();
        }
        readAndSave("login.txt", newLogInDest);
        readAndSave("plumberSolutions.txt", newSolutionDest);
        // readAndSave("customSolutions.txt", newCustomDest);

    }

    /**
     * Method that returns the unmodifiable instance of the class.
     * 
     * @return Singleton
     */
    public static ImageLoaderImpl getLoaderInstance() {
        return SINGLETON;
    }

    /**
     * Returns the new folder path.
     * 
     * @return new path
     */
    public static String getNewPath() {
        return newPath;
    }

    @Override
    public void loadImages(final int x, final int y) {
        allTubes = new ArrayList<>();
        ImageIcon VE, VF, OE, OF;
        ImageIcon LE, RE, DE, UE, LF, UF, RF, DF;

        ImageIcon DW_D1, DW_D2, DW_R1, DW_R2;
        ImageIcon LE_D1, LE_D2, LE_L1, LE_L2;
        ImageIcon RI_R1, RI_R2, RI_T1, RI_T2;
        ImageIcon UP_L1, UP_L2, UP_T1, UP_T2;
        ImageIcon HO_L1, HO_L2, HO_R1, HO_R2;
        ImageIcon VE_T1, VE_T2, VE_D1, VE_D2;

        try {

            LE = ImageLoaderImpl.resizeIcon(new ImageIcon(this.getClass().getClassLoader().getResource("LEFT/LE.jpg")),
                    x, y);
            LF = ImageLoaderImpl.resizeIcon(new ImageIcon(this.getClass().getClassLoader().getResource("LEFT/LF.jpg")),
                    x, y);
            RE = ImageLoaderImpl.resizeIcon(new ImageIcon(this.getClass().getClassLoader().getResource("RIGHT/RE.jpg")),
                    x, y);
            RF = ImageLoaderImpl.resizeIcon(new ImageIcon(this.getClass().getClassLoader().getResource("RIGHT/RF.jpg")),
                    x, y);
            UE = ImageLoaderImpl.resizeIcon(new ImageIcon(this.getClass().getClassLoader().getResource("UP/UE.jpg")), x,
                    y);
            UF = ImageLoaderImpl.resizeIcon(new ImageIcon(this.getClass().getClassLoader().getResource("UP/UF.jpg")), x,
                    y);
            DE = ImageLoaderImpl.resizeIcon(new ImageIcon(this.getClass().getClassLoader().getResource("DOWN/DE.jpg")),
                    x, y);
            DF = ImageLoaderImpl.resizeIcon(new ImageIcon(this.getClass().getClassLoader().getResource("DOWN/DF.jpg")),
                    x, y);
            VE = ImageLoaderImpl
                    .resizeIcon(new ImageIcon(this.getClass().getClassLoader().getResource("VERTIC/VE.jpg")), x, y);
            VF = ImageLoaderImpl
                    .resizeIcon(new ImageIcon(this.getClass().getClassLoader().getResource("VERTIC/VF.jpg")), x, y);
            OE = ImageLoaderImpl.resizeIcon(new ImageIcon(this.getClass().getClassLoader().getResource("HORIZ/OE.jpg")),
                    x, y);
            OF = ImageLoaderImpl.resizeIcon(new ImageIcon(this.getClass().getClassLoader().getResource("HORIZ/OF.jpg")),
                    x, y);
            START = ImageLoaderImpl
                    .resizeIcon(new ImageIcon(this.getClass().getClassLoader().getResource("Beginning.jpg")), x, y);
            FINISH = ImageLoaderImpl
                    .resizeIcon(new ImageIcon(this.getClass().getClassLoader().getResource("FINISH.jpg")), x, y);
            EMPTY = ImageLoaderImpl.resizeIcon(new ImageIcon(this.getClass().getClassLoader().getResource("EMPTY.jpg")),
                    x, y);

            DW_D1 = ImageLoaderImpl.resizeIcon(
                    new ImageIcon(this.getClass().getClassLoader().getResource("DOWN/DOWN/DW_D1.jpg")), x, y);
            DW_D2 = ImageLoaderImpl.resizeIcon(
                    new ImageIcon(this.getClass().getClassLoader().getResource("DOWN/DOWN/DW_D2.jpg")), x, y);

            DW_R1 = ImageLoaderImpl.resizeIcon(
                    new ImageIcon(this.getClass().getClassLoader().getResource("DOWN/RIGHT/DW_R1.jpg")), x, y);
            DW_R2 = ImageLoaderImpl.resizeIcon(
                    new ImageIcon(this.getClass().getClassLoader().getResource("DOWN/RIGHT/DW_R2.jpg")), x, y);

            RI_R1 = ImageLoaderImpl.resizeIcon(
                    new ImageIcon(this.getClass().getClassLoader().getResource("RIGHT/RIGHT/RI_R1.jpg")), x, y);
            RI_R2 = ImageLoaderImpl.resizeIcon(
                    new ImageIcon(this.getClass().getClassLoader().getResource("RIGHT/RIGHT/RI_R2.jpg")), x, y);

            RI_T1 = ImageLoaderImpl.resizeIcon(
                    new ImageIcon(this.getClass().getClassLoader().getResource("RIGHT/TOP/RI_T1.jpg")), x, y);
            RI_T2 = ImageLoaderImpl.resizeIcon(
                    new ImageIcon(this.getClass().getClassLoader().getResource("RIGHT/TOP/RI_T2.jpg")), x, y);

            LE_D1 = ImageLoaderImpl.resizeIcon(
                    new ImageIcon(this.getClass().getClassLoader().getResource("LEFT/DOWN/LE_D1.jpg")), x, y);
            LE_D2 = ImageLoaderImpl.resizeIcon(
                    new ImageIcon(this.getClass().getClassLoader().getResource("LEFT/DOWN/LE_D2.jpg")), x, y);

            LE_L1 = ImageLoaderImpl.resizeIcon(
                    new ImageIcon(this.getClass().getClassLoader().getResource("LEFT/LEFT/LE_L1.jpg")), x, y);
            LE_L2 = ImageLoaderImpl.resizeIcon(
                    new ImageIcon(this.getClass().getClassLoader().getResource("LEFT/LEFT/LE_L2.jpg")), x, y);

            UP_T1 = ImageLoaderImpl
                    .resizeIcon(new ImageIcon(this.getClass().getClassLoader().getResource("UP/TOP/UP_T1.jpg")), x, y);
            UP_T2 = ImageLoaderImpl
                    .resizeIcon(new ImageIcon(this.getClass().getClassLoader().getResource("UP/TOP/UP_T2.jpg")), x, y);

            UP_L1 = ImageLoaderImpl
                    .resizeIcon(new ImageIcon(this.getClass().getClassLoader().getResource("UP/LEFT/UP_L1.jpg")), x, y);
            UP_L2 = ImageLoaderImpl
                    .resizeIcon(new ImageIcon(this.getClass().getClassLoader().getResource("UP/LEFT/UP_L2.jpg")), x, y);

            HO_L1 = ImageLoaderImpl.resizeIcon(
                    new ImageIcon(this.getClass().getClassLoader().getResource("HORIZ/LEFT/HO_L1.jpg")), x, y);
            HO_L2 = ImageLoaderImpl.resizeIcon(
                    new ImageIcon(this.getClass().getClassLoader().getResource("HORIZ/LEFT/HO_L2.jpg")), x, y);

            HO_R1 = ImageLoaderImpl.resizeIcon(
                    new ImageIcon(this.getClass().getClassLoader().getResource("HORIZ/RIGHT/HO_R1.jpg")), x, y);
            HO_R2 = ImageLoaderImpl.resizeIcon(
                    new ImageIcon(this.getClass().getClassLoader().getResource("HORIZ/RIGHT/HO_R2.jpg")), x, y);

            VE_D1 = ImageLoaderImpl.resizeIcon(
                    new ImageIcon(this.getClass().getClassLoader().getResource("VERTIC/DOWN/VE_D1.jpg")), x, y);
            VE_D2 = ImageLoaderImpl.resizeIcon(
                    new ImageIcon(this.getClass().getClassLoader().getResource("VERTIC/DOWN/VE_D2.jpg")), x, y);

            VE_T1 = ImageLoaderImpl.resizeIcon(
                    new ImageIcon(this.getClass().getClassLoader().getResource("VERTIC/TOP/VE_T1.jpg")), x, y);
            VE_T2 = ImageLoaderImpl.resizeIcon(
                    new ImageIcon(this.getClass().getClassLoader().getResource("VERTIC/TOP/VE_T2.jpg")), x, y);

            t1 = new PipeImpl(LE, 0, "CL", 2, -1, Arrays.asList(LE_L1, LE_L2, LF, LF),
                    Arrays.asList(LE_D1, LE_D2, LF, LF));

            t2 = new PipeImpl(UE, 1, "CU", 1, 2, Arrays.asList(UP_T1, UP_T2, UF, UF),
                    Arrays.asList(UP_L1, UP_L2, UF, UF));
            t3 = new PipeImpl(RE, 2, "CR", -2, 1, Arrays.asList(RI_T1, RI_T2, RF, RF),
                    Arrays.asList(RI_R1, RI_R2, RF, RF));

            t4 = new PipeImpl(DE, 3, "CD", -1, -2, Arrays.asList(DW_D1, DW_D2, DF, DF),
                    Arrays.asList(DW_R1, DW_R2, DF, DF));
            t5 = new PipeImpl(VE, 0, "SV", 1, -1, Arrays.asList(VE_T1, VE_T2, VF, VF),
                    Arrays.asList(VE_D1, VE_D2, VF, VF));
            t6 = new PipeImpl(OE, 1, "SO", 2, -2, Arrays.asList(HO_L1, HO_L2, OF, OF),
                    Arrays.asList(HO_R1, HO_R2, OF, OF));

            t1.addPipes(t2);
            t2.addPipes(t3);
            t3.addPipes(t4);

            allTubes.addAll(Arrays.asList(t1, t2, t3, t4));

        } catch (final Exception ex) {
            System.out.println(ex);
        }

    }

    @Override
    public PipeImpl getTube(final String tubeType) {

        PipeImpl tube = null;
        switch (tubeType) {
        case "CL": {
            tube = t1;
            break;
        }
        case "CU": {
            tube = t2;
            break;
        }
        case "CR": {
            tube = t3;
            break;
        }
        case "CD": {
            tube = t4;
            break;
        }
        case "SV": {
            tube = t5;
            break;
        }
        case "SO": {
            tube = t6;
            break;
        }
        default:
            break;
        }
        return tube;
    }

    /**
     * Method used for resizing the icon given.
     * 
     * @param icon
     *            icon to resize
     * @param resizedWidth
     *            new width
     * @param resizedHeight
     *            new height
     * @return the resized icon
     */
    public static ImageIcon resizeIcon(final ImageIcon icon, final int resizedWidth, final int resizedHeight) {
        final Image img = icon.getImage();
        final Image resizedImage = img.getScaledInstance(resizedWidth, resizedHeight, java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImage);
    }

    /**
     * Return the list that contains all the pipes.
     * 
     * @return list
     */
    public List<Pipe> getAllTubes() {
        return this.allTubes;
    }

    @Override
    public ImageIcon getStartImage() {

        return this.START;
    }

    @Override
    public ImageIcon getFinishImage() {

        return this.FINISH;
    }

    @Override
    public ImageIcon getEmptyImage() {
        //
        return this.EMPTY;
    }

    /*
     * private void copyFiles(final File source, final File dest) { try {
     * Files.copy(source.toPath(), dest.toPath(),
     * StandardCopyOption.REPLACE_EXISTING); } catch (IOException e1) {
     * 
     * e1.printStackTrace(); }
     * 
     * }
     */

    @Override
    public File getFixedFile() {

        return newSolutionDest;
    }

    @Override
    public File getCustomFIle() {

        return newCustomDest;
    }

    @Override
    public File getLoginFile() {

        return newLogInDest;
    }

    @Override
    public File getScoreFile() {

        return newScoreDest;
    }

    private void readAndSave(String name, File f) {
        String s = "";
        try (BufferedReader bfw = new BufferedReader(
                new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream(name)))) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(f))) {
                while ((s = bfw.readLine()) != null) {
                    writer.write(s);
                    writer.newLine();
                }

            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
