package controller.file.utilities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import enums.Sprite;
import enums.StageMap;
import model.common.PositionImpl;
import model.entities.Block;
import model.entities.Block.Type;
import model.entities.BlockImpl;

/**
 * Class utility for load the block maps. The class uses the Singleton Pattern.
 */
public final class BlockUtility {

    // Instance of the class.
    private static final BlockUtility SINGLETON = new BlockUtility();

    /**
     * Method that implements the Singleton Pattern. It returns only one instance of
     * the class, created only one time.
     * 
     * @return the instance of the class.
     */
    public static BlockUtility getInstance() {
        return SINGLETON;
    }

    /*
     * Empty constructor of the class that load the images.
     */
    private BlockUtility() {
    }

    /**
     * Method that return the requested image. Given a Stage Map enumeration, it
     * open the specified file and return it back as a list of Block.
     * 
     * @param stageMapEnum the enumeration of the requested stage map.
     * @return the requested list of Block.
     */
    public List<Block> getBlockList(final StageMap stageMapEnum) {
        List<Block> blockList = new ArrayList<Block>();
        final String path = stageMapEnum.getPath();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(path)))) {
            String line = null;
            int nLine = 0;
            line = br.readLine();
            while (line != null) {
                for (int i = 0; i < line.length(); i++) {
                    if (line.charAt(i) == '#') {
                        blockList.add(new BlockImpl(Sprite.BLOCK_BRICK, new PositionImpl(i, nLine), Type.WALL));
                    } else if (line.charAt(i) == '@') {
                        blockList.add(new BlockImpl(Sprite.BLOCK_STEEL, new PositionImpl(i, nLine), Type.IRON));
                    } else if (line.charAt(i) == '%') {
                        blockList.add(new BlockImpl(Sprite.BLOCK_TREE, new PositionImpl(i, nLine), Type.GRASS));
                    } else if (line.charAt(i) == '~') {
                        blockList.add(new BlockImpl(Sprite.BLOCK_WATER, new PositionImpl(i, nLine), Type.WATER));
                    } else if (line.charAt(i) == '-') {
                        blockList.add(new BlockImpl(Sprite.BLOCK_ICE, new PositionImpl(i, nLine), Type.ICE));
                    }
                }
                nLine++;
                line = br.readLine();
            }
        } catch (final IOException ex) {
            blockList = null;
            System.out.println("STAGE MAP " + stageMapEnum + " NOT FOUND");
        }
        return blockList;
    }

}
