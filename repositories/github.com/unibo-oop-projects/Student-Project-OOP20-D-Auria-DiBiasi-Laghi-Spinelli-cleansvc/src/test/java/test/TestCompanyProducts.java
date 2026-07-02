package test;


import java.io.IOException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import controller.CompanyImpl;
import controller.ProcessImpl;
import model.ProductsImpl;
import model.step.enumerations.StepType;

public class TestCompanyProducts {

    private static ProcessImpl process = ProcessImpl.getInstance();
    private static CompanyImpl company = CompanyImpl.getInstance();
    private static final String CODE = "A01";
    private static final StepType STEP = process.getStepTypeList().get(0);
    private static final String NAME = "CleanerPRO";
    private static final String DESCRIPTION = "Pulizia ordinaria";
    private static final double PRICE = 15.5;
    private static final double USAGE = 30.0;
    private ProductsImpl product = null;

    @BeforeAll
    public void testProductImpl() {
        product = new ProductsImpl(CODE, STEP, NAME, DESCRIPTION, PRICE, USAGE);
    }

    @Test
    public void testProduct() {
        Assertions.assertTrue(product.getCode().equals(CODE));
        Assertions.assertTrue(product.getName().equals(NAME));
        Assertions.assertTrue(product.getDescription().equals(DESCRIPTION));
        Assertions.assertTrue(product.getStepType().equals(STEP));
        Assertions.assertTrue(Double.compare(product.getPricePerLitre(), PRICE) == 0);
        Assertions.assertTrue(Double.compare(product.getLitersPer500Mq(), USAGE) == 0);
    }

    @Test
    public void testCompany() throws IOException {
        Assertions.assertTrue(company.getProducts().isEmpty());
        company.addProduct(product);
        Assertions.assertFalse(company.getProducts().isEmpty());
        Assertions.assertTrue(company.getProducts().get(0).equals(company.searchProduct(CODE).get()));
        company.removeProduct(product);
        Assertions.assertTrue(company.getProducts().isEmpty());
    }
}
