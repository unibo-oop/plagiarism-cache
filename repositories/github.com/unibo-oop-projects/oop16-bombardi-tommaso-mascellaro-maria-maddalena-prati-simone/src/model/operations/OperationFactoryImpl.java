package model.operations;

import model.admin.products.Instructor;
import model.admin.products.Object1;
import model.admin.products.Object2;
import model.admin.products.Season;
import model.admin.products.Skipass;

/**
 * Interface for a factory where it's possible to create operations in different ways.
 */
public class OperationFactoryImpl implements OperationFactory {

    @Override
    public Operation createBuyOperation(final Object1 obj, final int numObj) {
        return new BuyOperation(obj, numObj);
    }

    @Override
    public Operation createRentOperation(final Object2 obj, final int numObj, final int numDays, final Season season) {
        return new RentOperation(obj, numObj, numDays, season);
    }

    @Override
    public Operation createInstructorOperation(final Instructor inst, final int numSkiers, final Season season) {
        return new InstructorOperation(inst, numSkiers, season);
    }

    @Override
    public Operation createSkipassOperation(final Skipass skip, final int numObj, final Season season) {
        return new SkipassOperation(skip, numObj, season);
    }

    @Override
    public Operation createStorageOperation(final Object2 obj, final int numObj, final int numDays) {
        return new StorageOperation(obj, numObj, numDays);
    }

}
