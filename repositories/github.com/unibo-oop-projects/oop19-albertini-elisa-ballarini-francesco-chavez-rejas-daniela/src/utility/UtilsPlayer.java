package utility;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;

import login.Custom;
import login.GlobalRank;
import login.GlobalRankImpl;
import login.Player;
import login.PlayerImpl;
import piece.Piece;
import piece.PieceImpl;
import piece.Type;

/**
 * Utility class of {@link Player}.
 */
public final class UtilsPlayer {

    private static final String FS = File.separator;
    private static final String CURRENT_PATH = new File(".").toString();
    private static final String PLAYERS_FOLDER_NAME = "Players";
    private static final String PATH_PLAYERS_FOLDER = CURRENT_PATH + FS + "res" + FS + PLAYERS_FOLDER_NAME + FS;

    private UtilsPlayer() {
    }

    /**
     * @param writePlayer : player that will be written on a JSON file.
     */
    public static void writeJSON(final Player writePlayer) {

        Utils.initFolderInRes(PLAYERS_FOLDER_NAME);

        final ObjectMapper mapper = registerJdkModuleAndGetMapper();
        mapper.setSerializationInclusion(Include.NON_NULL);

        /**
         * Write object to file
         */
        try {
            mapper.writerWithDefaultPrettyPrinter()
                    .writeValue(new File(PATH_PLAYERS_FOLDER + writePlayer.getName() + ".json"), writePlayer); // Plain
                                                                                                               // JSON
        } catch (IOException e) {
            e.printStackTrace();
        }

        final GlobalRank newRank = new GlobalRankImpl();
        newRank.updateRank(writePlayer);
    }

    private static ObjectMapper registerJdkModuleAndGetMapper() {
        final ObjectMapper objectMapper = new ObjectMapper();
        final Jdk8Module module = new Jdk8Module();
        module.configureAbsentsAsNulls(true);
        objectMapper.registerModule(module);
        return objectMapper;
    }

    /**
     * @param namePlayer : the name of the player to get from the database.
     * @return the Player related to the name searched.
     */
    public static Player readPlayerFromJSON(final String namePlayer) {

        final ObjectMapper mapper = registerJdkModuleAndGetMapper();

        /**
         * Read object from file
         */
        Player player = null; 
        try {
            player = mapper.readValue(new File(PATH_PLAYERS_FOLDER + namePlayer + ".json"), PlayerImpl.class);
            return player;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return player;

    }

    /**
     * @param playerName : the name of the player to search for in the database.
     * @return true if the player is present, false otherwise.
     */
    public static boolean isPlayerInDatabes(final String playerName) {
        return new File(PATH_PLAYERS_FOLDER + playerName + ".json").exists();
    }

    /**
     * @param changeList : the list to convert to a list of Block objects.
     * @return : the list converted.
     */
    public static List<Piece> getBlockList(final List<Custom> changeList) {

        final List<Piece> blockList = new ArrayList<>();

        changeList.forEach(e -> blockList
                .add(new PieceImpl(Type.CUSTOM, Optional.of(e.getCoords().stream().collect(Collectors.toSet())),
                        Optional.of(new Color(e.getColor())), Optional.of(e.getCenter()))));
        return blockList;
    }

}
