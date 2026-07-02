package model.entity.cell.standard.genome;

import java.util.List;
import java.util.Random;

import model.entity.cell.standard.CellStandard;
import model.genome.decryptor.GeneDecryptor;

public class GenomeImpl implements GenomeManipulation {
    /**
     * the genome list. i choose a linked list because i assume that there is a lot
     * of mutation.
     */
    private final List<Integer> genome = new CircularLinkedList<Integer>();
    /**
     * pointer of current gene.
     */
    private int current = 0;
    /**
     * random for some function.
     */
    private final Random rand = new Random();
    private final int numberOfGenes;

    private final GeneDecryptor geneDecryptor;

    public GenomeImpl(final GeneDecryptor geneDecryptor, final int numberOfGenes, final List<Integer> list) {

        this.geneDecryptor = geneDecryptor;
        this.numberOfGenes = numberOfGenes;

        for (int i = 0; i < list.size(); i++) {
            this.genome.add(list.get(i));
        }
    }

    @Override
    public final int generateRandomGene() {
        return rand.nextInt(this.numberOfGenes);
    }

    @Override
    public final void changeSideLength(final int value) {
        if (value >= 0) {
            for (int i = 0; i < value; i++) {
                this.genome.add(generateRandomGene());
            }
        } else {
            if (this.genome.size() + value < 1) {
                throw new IndexOutOfBoundsException();
            } else {
                for (int i = 0; i < -value; i++) {
                    this.genome.remove(this.genome.size() - 1);
                }
            }
        }

    }

    @Override
    public final void startGene(final CellStandard currentCell) {
        if (this.geneDecryptor.isGenePresent(this.genome.get(this.current))) {
            this.geneDecryptor.getGene(this.genome.get(this.current)).launch(currentCell);
            this.current++;
        } else {
            this.current += this.genome.get(this.current);
        }

    }

    @Override
    public final int getGeneValue(final int index) {
        return this.genome.get(index);
    }

    @Override
    public final int getGeneIndex(final int value) {
        return this.genome.indexOf(value);
    }

    @Override
    public final void setGene(final int index, final int value) {
        this.genome.set(index, value);
    }

    @Override
    public final int getGeneValueWithOffsetAndJump(final int offset) {
        this.current += offset;
        return this.genome.get(this.current);
    }

    @Override
    public final int getSize() {
        return this.genome.size();
    }

    @Override
    public final void mutateGene(final int index, final int gene) {
        this.genome.set(index, gene);
    }

    @Override
    public final int getNumberOfGenes() {
        return this.numberOfGenes;
    }

    @Override
    public final void setCurrent(final int value) {
        if (value < 0) {
            throw new IllegalArgumentException();
        } else {
            this.current = value;
        }
    }

    @Override
    public final int getCurrent() {
        return this.current;
    }

    @Override
    public final List<Integer> getGenome() {
        return this.genome;
    }

}
