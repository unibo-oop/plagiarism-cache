package todo.model.level.inputs;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Before;
import org.junit.Test;

import todo.vm.Value;

public class ModifierTest {
    private List<Value> initialValues;

    @Before
    public void init() {
        this.initialValues = new ArrayList<>();
    }

    private void clearValues() {
        this.initialValues.clear();
        this.initialValues.addAll(Stream.generate(Value::empty).limit(10).collect(Collectors.toList()));
    }

    @Test
    public void testEnsureZero() {
        clearValues();
        final ModifierWithCount mod = new EnsureZeros();
        mod.setInitialValues(this.initialValues);
        mod.setCount(2);
        mod.update();
        assertTrue(
                this.initialValues.stream().filter(Value::isPresent).filter(val -> val.asNumber() == 0).count() == 2);
    }

    @Test
    public void testEnsureNegative() {
        clearValues();
        final ModifierWithCount mod = new EnsureNegatives();
        mod.setInitialValues(this.initialValues);
        mod.setCount(2);
        final ModifierWithBound modBound = (ModifierWithBound) mod;
        modBound.setBound(-10);
        modBound.update();
        assertTrue(this.initialValues.stream().filter(Value::isPresent).filter(val -> val.asNumber() < 0).count() == 2);
    }

    @Test
    public void testEnsurePositive() {
        clearValues();
        final ModifierWithCount mod = new EnsurePositives();
        mod.setInitialValues(this.initialValues);
        mod.setCount(2);
        final ModifierWithBound modBound = (ModifierWithBound) mod;
        modBound.setBound(10);
        modBound.update();
        assertTrue(this.initialValues.stream().filter(Value::isPresent).filter(val -> val.asNumber() > 0).count() == 2);
    }

    @Test
    public void testEnsureChars() {
        clearValues();
        final ModifierWithCount mod = new EnsureChars();
        mod.setCount(2);
        mod.setInitialValues(this.initialValues).update();
        assertTrue(this.initialValues.stream().filter(Value::isASCII).count() == 2);
    }

    @Test
    public void testEnsureLastZero() {
        clearValues();
        new EnsureLastZero().setInitialValues(this.initialValues).update();
        assertTrue(this.initialValues.get(this.initialValues.size() - 1).equals(Value.number(0)));
    }
}
