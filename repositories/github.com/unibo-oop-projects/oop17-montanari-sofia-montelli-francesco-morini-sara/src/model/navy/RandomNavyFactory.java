package model.navy;

import java.util.List;

/**
 * Factory class for random {@link Navy}.
 */
public interface RandomNavyFactory {

    /**
     * 
     * @return a {@link Navy} containing the classic formation.
     */
    Navy getClassicRandomFormation();

    /**
     * @param sizes is {@link List} of {@link Integer} which 
     *        represent the specification for the {@link Ship} sizes.
     * @param gridSide is the size of the grid's side
     * @return a random formation for the given specification
     * @throws IllegalArgumentException if you can't build a {@link Navy}
     *         with the given specification
     */
    Navy getCostumRandomFormation(List<Integer> sizes, int gridSide) throws IllegalArgumentException;

    /**
     * @param builder is the {@link NavyBuilder} to consume.
     * @return a random formation builder upon the given builder
     */
    Navy getCostumRandomFormation(NavyBuilder builder);

}
