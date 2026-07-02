package model.genome.decryptor;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import model.genome.factory.GenesEnum;
import model.genome.factory.GenesTable;
import model.genome.genes.Gene;

/**
 * 
 * A simple implementation of GeneDecryptor.
 *
 */
public class GeneDecryptorImpl implements GeneDecryptor {
    /**
     * A genome map from an index to the correspond gene.
     */
    private final Map<Integer, Gene> genomeMap;

    /**
     * @param numberOfGenes the number of possible genes.
     * @param table that can give access to genes.
     */
    public GeneDecryptorImpl(final int numberOfGenes, final GenesTable table) {
        this.genomeMap = new InitializeGenomeMap(numberOfGenes, table).createMap();
    }

    @Override
    public final Gene getGene(final int index) {
        check(!isGenePresent(index));
        return this.genomeMap.get(index);
    }

    @Override
    public final boolean isGenePresent(final int index) {
        return this.genomeMap.containsKey(index);
    }

    // this method can be useful for creating a first cell
    @Override
    public final int getIndexOfGene(final Gene gene) {
        final Optional<Integer> index = genomeMap.entrySet().stream().filter(f -> f.getValue().equals(gene))
                .map(m -> m.getKey()).findFirst();
        check(index.isEmpty());
        return index.get();
    }

    private void check(final boolean exception) {
        if (exception) {
            throw new IllegalArgumentException("Invalid argument");
        }
    }

    /**
     * 
     * A class for a genome map creation and initialization.
     *
     */
   private final class InitializeGenomeMap {
        private final GenesTable table;
        // The list with all possible indexes
        private final List<Integer> indexes;
        private final Random random = new Random();

        /**
         * @param numberOfGenes the number of possible genes.
         * @param table that can give access to genes.
         */
        private InitializeGenomeMap(final int numberOfGenes, final GenesTable table) {
            this.table = table;
            this.indexes = Stream.iterate(0, i -> i + 1)
                            .limit(numberOfGenes)
                            .collect(Collectors.toList());
        }

        /**
         * The method for creating new genome map.
         * 
         * @return genome map.
         */
        public Map<Integer, Gene> createMap() {
            final List<GenesEnum> enums = new LinkedList<>(Arrays.asList(GenesEnum.values()));

            //make some genes to appear more frequently.
            for (int i = 0; i < 2; i++) {
                enums.add(GenesEnum.LOOK_AHEAD);
                enums.add(GenesEnum.TURN_FROM_CURRENT_DIRECTION);
                enums.add(GenesEnum.ATTACK_ALL);
                enums.add(GenesEnum.MOVEMENT);
            }

            return this.table.getGenesList(enums)
                            .stream()
                            .collect(Collectors.toMap(l -> getIndex(), g -> g));
        }

        /**
         * Extract casual index.
         * 
         * @return index for a gene.
         */
        private Integer getIndex() {
            if (indexes.isEmpty()) {
                throw new IllegalStateException("There are not free indexes for genes");
            }
            return indexes.remove(random.nextInt(indexes.size()));
        }

    }
}
