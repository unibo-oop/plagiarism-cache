package clashclass.saveload;

import clashclass.village.Village;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Main manager class for handling village save/load operations.
 */
public class VillageSaveLoadManager {
    private static final String CSV_EXTENSION = ".csv";
    private final VillageEncoder encoder;
    private final VillageDecoder playerDecoder;
    private final VillageDecoder battleDecoder;
    private final FileWriter fileWriter;
    private final Path savesDirectory;

    /**
     * Constructs the village save load manager.
     *
     * @param encoder the village encoder
     * @param playerDecoder the player village decoder
     * @param battleDecoder the battle village decoder
     * @param fileWriter the file writer
     * @param savesDirectory the path to save file
     */
        public VillageSaveLoadManager(
            final VillageEncoder encoder,
            final VillageDecoder playerDecoder,
            final VillageDecoder battleDecoder,
            final FileWriter fileWriter,
            final Path savesDirectory
    ) {
        this.encoder = encoder;
        this.playerDecoder = playerDecoder;
        this.battleDecoder = battleDecoder;
        this.fileWriter = fileWriter;
        this.savesDirectory = savesDirectory;
    }

    /**
     * Saves a collection of GameObjects to file.
     *
     * @param village The village to save
     *
     * @param fileName The name of the save file
     *
     * @throws IOException If saving fails
     */
    public void saveVillage(final Village village, final String fileName) throws IOException {
        final String encoded = encoder.encode(village);
        Files.createDirectories(savesDirectory);
        final Path filePath = savesDirectory.resolve(fileName + CSV_EXTENSION);
        fileWriter.writeToFile(encoded, filePath);
    }

    /**
     * Loads GameObjects for a player village from file.
     *
     * @param fileName The name of the save file
     *
     * @return Set of loaded GameObjects
     *
     * @throws IOException If loading fails
     */
    public Village loadPlayerVillage(final String fileName) throws IOException {
        final String data = Files.readString(savesDirectory.resolve(fileName + CSV_EXTENSION));
        return playerDecoder.decode(data);
    }

    /**
     * Loads GameObjects for a battle village from file.
     *
     * @param fileName The name of the save file
     *
     * @return Set of loaded GameObjects
     *
     * @throws IOException If loading fails
     */
    public Village loadBattleVillage(final String fileName) throws IOException {
        final String data = Files.readString(savesDirectory.resolve(fileName + CSV_EXTENSION));
        return battleDecoder.decode(data);
    }
}

