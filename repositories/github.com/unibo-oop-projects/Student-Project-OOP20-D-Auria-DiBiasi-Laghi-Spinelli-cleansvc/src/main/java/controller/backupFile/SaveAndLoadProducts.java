package controller.backupFile;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import model.Products;
import controller.Company;
import controller.CompanyImpl;
import controller.ProcessImpl;
import model.ProductsImpl;
import model.step.enumerations.StepType;

public class SaveAndLoadProducts implements SaveAndLoad {

    private Company company = CompanyImpl.getInstance();
    private static final String SEP = File.separator;
    private static final String FILE_PRODUCTS = "Products.txt";
    private static final String CODE_STR = "CODE: ";
    private static final String STEP_STR = "STEP: ";
    private static final String NAME_STR = "NAME: ";
    private static final String DESCRIPTION_STR = "DESCRIPTION: ";
    private static final String PRICELITRE_STR = "PRICELITRE: ";
    private static final String USAGE500MQ_STR = "USAGE500MQ: ";
    private StepType stepProduct;
    /**
     * A method that saves a product.
     */
    @Override
    public void save() {
        try (BufferedWriter w = new BufferedWriter(new FileWriter(FILE_PRODUCTS))) {
            for (final Products p : this.company.getProducts()) {
                w.write(CODE_STR + p.getCode());
                w.newLine();
                w.write(STEP_STR + p.getStepType());
                w.newLine();
                w.write(NAME_STR + p.getName());
                w.newLine();
                w.write(DESCRIPTION_STR + p.getDescription());
                w.newLine();
                w.write(PRICELITRE_STR + p.getPricePerLitre());
                w.newLine();
                w.write(USAGE500MQ_STR + p.getLitersPer500Mq());
                w.newLine();
            }
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * A method that loads a product.
     */
    @Override
    public void load() {
        final List<String> codeList = new ArrayList<>();
        final List<String> stepList = new ArrayList<>();
        final List<String> nameList = new ArrayList<>();
        final List<String> descriptionList = new ArrayList<>();
        final List<Double> priceLitreList = new ArrayList<>();
        final List<Double> usageList = new ArrayList<>();
        try (BufferedReader r = new BufferedReader(new FileReader(FILE_PRODUCTS))) {
            r.lines().forEach(l -> {
                if (l.contains(CODE_STR)) {
                    codeList.add(l.substring(CODE_STR.length()));
                }
                if (l.contains(STEP_STR)) {
                    stepList.add(l.substring(STEP_STR.length()));
                }
                if (l.contains(NAME_STR)) {
                    nameList.add(l.substring(NAME_STR.length()));
                }
                if (l.contains(DESCRIPTION_STR)) {
                    descriptionList.add(l.substring(DESCRIPTION_STR.length()));
                }
                if (l.contains(PRICELITRE_STR)) {
                    priceLitreList.add(Double.valueOf(l.substring(PRICELITRE_STR.length())));
                }
                if (l.contains(USAGE500MQ_STR)) {
                    usageList.add(Double.valueOf(l.substring(USAGE500MQ_STR.length())));
                }
            });
            for (int i = 0; i < codeList.size(); i++) {
                for (StepType step : ProcessImpl.getInstance().getStepTypeList()) {
                    if (step.getType().equals(stepList.get(i))) {
                        stepProduct = step;
                    }
                }
                this.company.addProduct(new ProductsImpl(codeList.get(i), stepProduct, nameList.get(i),
                        descriptionList.get(i), priceLitreList.get(i), usageList.get(i)));
            }
        } catch (final IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
