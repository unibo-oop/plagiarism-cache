package model.genome.genes;

import java.util.Locale;
import java.util.ResourceBundle;

import org.junit.Test;

/**
 * 
 * Simple tests for genes.
 *
 */
public class TestGenes {

    /**
     * tests.
     */
    @Test
    public void bundleTest() {
        Locale locale = Locale.US;
        ResourceBundle bundle = ResourceBundle.getBundle("resource.i18n.Bundle", locale);
        System.out.println(bundle.getString("Photosynthesis"));

        locale = Locale.ITALIAN;
        bundle = ResourceBundle.getBundle("resource.i18n.Bundle", locale);
        System.out.println(bundle.getString("Photosynthesis"));

        System.out.println(); 

    }

    /**
     * tests.
     */
//    @Test
//    public void genesDescriptionTest() {
//        Gene phot = new PhotosynthesisGene();
//        Gene first = new MutationGene();
//
//        Locale.setDefault(Locale.US);
//        System.out.println(Locale.getDefault());
//        System.out.println(phot.getDescription() + "\n");
//
//        Locale.setDefault(Locale.ITALIAN);
//        System.out.println(Locale.getDefault());
//        System.out.println(phot.getDescription() + "\n");
//    }

    @Test
    public void toStringTest() {
        Gene gene = new ChangeDirectionGene();
        System.out.println(gene);
        System.out.println(gene.getDescription());
    }
}
