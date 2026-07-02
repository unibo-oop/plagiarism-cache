package iuniversity.model.exams;

import java.text.Collator;
import java.util.Collections;

/**
 * A student registration strategy factory.
 *
 */
public class StudentRegistrationStrategyFactoryImpl implements StudentRegistrationStrategyFactory {

    /**
     * {@inheritDoc}
     */
    @Override
    public StudentRegistrationStrategy atTheEndOfList() {
        return (list, student) -> list.add(student);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public StudentRegistrationStrategy atTheTopOfList() {
        return (list, student) -> list.add(0, student);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public StudentRegistrationStrategy alfabeticalOrder() {
        return (list, student) -> {
            list.add(student);
            Collections.sort(list, (a, b) -> Collator.getInstance().compare(a.getLastName(), b.getLastName()));
        };
    }

}
