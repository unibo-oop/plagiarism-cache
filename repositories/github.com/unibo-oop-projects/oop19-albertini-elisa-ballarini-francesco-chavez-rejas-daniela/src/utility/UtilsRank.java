/**
 * 
 */
package utility;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;

import login.GlobalRank;
import login.GlobalRankImpl;
import pair.Pair;

/**
 * Utility class of the global rank.
 */
public final class UtilsRank {

    private static final String RANK_FOLDER = "Rank";
    private static final String RANK_PATH = "." + File.separator + "res" + File.separator + RANK_FOLDER
            + File.separator;
    private static final String GLOBAL_RANK = "GlobalRank";

    private UtilsRank() {

    }

    /**
     * @return true if the file of the global rank is present, false otherwise.
     */
    public static boolean isRankPresent() {
        return new File(RANK_PATH + GLOBAL_RANK + ".json").exists();
    }

    /**
     * @param actualRank rank to write on JSON file.
     */
    public static void writeRankOnFile(final GlobalRank actualRank) {
        Utils.initFolderInRes(RANK_FOLDER);
        final ObjectMapper mapper = registerJdkModuleAndGetMapper();
        mapper.setSerializationInclusion(Include.NON_NULL);

        /**
         * Write object to file
         */
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(RANK_PATH + GLOBAL_RANK + ".json"), actualRank);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static ObjectMapper registerJdkModuleAndGetMapper() {
        final ObjectMapper objectMapper = new ObjectMapper();
        final Jdk8Module module = new Jdk8Module();
        module.configureAbsentsAsNulls(true);
        objectMapper.registerModule(module);
        return objectMapper;
    }

    /**
     * @return the global rank.
     */
    public static Map<String, Integer> readRankFromFile() {
        final ObjectMapper mapper = registerJdkModuleAndGetMapper();

        /**
         * Read object from file
         */
        GlobalRank rank = null;
        try {
            rank = mapper.readValue(new File(RANK_PATH + GLOBAL_RANK + ".json"), GlobalRankImpl.class);
            return rank.getRank();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rank.getRank();
    }

    /**
     * Method that sort in a descending order a Map<String, Integer> by value.
     * 
     * @param map : the unsorted map.
     * @return the map sorted.
     */
    public static Map<String, Integer> sortByValueDesc(final Map<String, Integer> map) {
        final List<Map.Entry<String, Integer>> list = new LinkedList<>(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(final Map.Entry<String, Integer> o1, final Map.Entry<String, Integer> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        });

        final Map<String, Integer> result = new LinkedHashMap<>();
        for (final Map.Entry<String, Integer> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }

    /**
     * Method to get the position of a player by his name.
     * 
     * @param name : the name of the player.
     * @return the position in the global rank.
     */
    public static int getRankPosition(final String name) {
        final GlobalRank tempRank = new GlobalRankImpl();
        final List<String> tempList = new ArrayList<>(tempRank.getRank().keySet());
        return tempList.indexOf(name) + 1;
    }

    /**
     * @return a pair that contains the name and the score of the first in the
     *         global rank.
     */
    public static Pair<String, Integer> getFirstInRank() {
        final GlobalRank tempRank = new GlobalRankImpl();
        final String firstPlaceName = new ArrayList<>(tempRank.getRank().keySet()).get(0);
        return new Pair<>(firstPlaceName, tempRank.getRank().get(firstPlaceName));
    }

}
