package controller;

import java.io.File;

import java.io.FileOutputStream;
import java.io.IOException;

import java.io.OutputStream;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import model.virus.Virus;
import model.virus.VirusData;
import model.virus.VirusDataList;
import model.virus.VirusFactory;
import model.virus.VirusFactoryImpl;
import view.VirusSetup;

public class VirusManagerImpl implements VirusManager {

    private final File folder = new File(System.getProperty("user.home"), ".VSS");

    private final File savedVirus = new File(folder, "configVirus.xml");

    private static final String I_PATH = "virus/virusConfig.xml";

    private final VirusSetup info;
    private VirusDataList virusList = new VirusDataList();
    private final Map<String, VirusData> virusMap = new HashMap<String, VirusData>();

    /**
     * Constructor method.
     * @param info
     */
    public VirusManagerImpl(final VirusSetup info) {
        this.info = info;
        virusList.setVirus(new ArrayList<VirusData>());
    }

    /**
     *
     */
    @Override
    public void readVirus() {
        try {
            final JAXBContext jaxbContext = JAXBContext.newInstance(VirusDataList.class);
            final Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

            if (!savedVirus.exists()) {
                copyVirusConfig();
            }
            virusList = (VirusDataList) unmarshaller.unmarshal(savedVirus);
            for (int i = 0; i < virusList.getVirus().size(); i++) {
                virusMap.put(virusList.getVirus().get(i).getName(), virusList.getVirus().get(i));
            }
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        info.uploadVirus(virusMap.keySet());
    }

    /**
     *
     */
    @Override
    public void loadVirusSettings(final String virusCode) {
        VirusData vir = new VirusData();
        if (virusMap.containsKey(virusCode)) {
            vir = virusMap.get(virusCode);
            info.setIncubationPeriod(vir.getMinIncubationPeriod(), vir.getMaxIncubationPeriod());
            info.setInfectity(vir.getInfectivity());
            info.setMortality(vir.getMortality());
            info.setRecoveryPeriod(vir.getMinRecoveryPeriod(), vir.getMaxRecoveryPeriod());
        } else {
            throw new IllegalArgumentException("virusCode not acceptable");
        }

    }

    /**
     *
     */
    @Override
    public void saveVirus() {
        final VirusData virus = new VirusData();
        virus.setName(info.getName());
        virus.setInfectivity(info.getInfectivity());
        virus.setMortality(info.getMortality());
        virus.setMinIncubationPeriod(info.getMinIncubationPeriod());
        virus.setMaxIncubationPeriod(info.getMaxIncubationPeriod());
        virus.setMinRecoveryPeriod(info.getMinRecoveryPeriod());
        virus.setMaxRecoveryPeriod(info.getMaxRecoveryPeriod());
        try {

            final JAXBContext jaxbContext = JAXBContext.newInstance(VirusDataList.class);
            final Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            virusList = (VirusDataList) unmarshaller.unmarshal(savedVirus);
            virusList.getVirus().add(virus);
            final Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            try (OutputStream os = new FileOutputStream(savedVirus.getPath())) {
                marshaller.marshal(virusList, os);
            } catch (Exception e) {
                e.printStackTrace();
            }
            readVirus();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     *
     */
    @Override
    public Virus initializeVirus() {
        final VirusFactory creator = new VirusFactoryImpl(info.getMinIncubationPeriod(), info.getMaxIncubationPeriod(),
                info.getInfectivity(), info.getMortality(), info.getMinRecoveryPeriod(), info.getMaxRecoveryPeriod());
        return creator.createVirus();
    }

    /**
     * 
     */
    private void copyVirusConfig() {
        createFile();
        try {
            final Path out = Paths.get(savedVirus.getPath());
            Files.copy(ClassLoader.getSystemResourceAsStream(I_PATH), out, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 
     */
    private void createFile() {
        try {
            if (!folder.mkdir() && !folder.exists()) {
                throw new IOException("Impossible to create folder");
            }
            if (!savedVirus.createNewFile()) {
                throw new IOException("Impossible to create file");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
