package test.minigames.sizecount;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import model.minigames.sizecount.IntegerOperationsFactory;
import model.minigames.sizecount.IntegerOperationsFactoryImpl;

public class TestIntegerOperationsFactory {

    private final IntegerOperationsFactory factory = new IntegerOperationsFactoryImpl();

    @Test
    public void argumentsCanBeZero() {
        assertThrows(IllegalStateException.class, () -> this.factory.addiction(0, 0));
        assertThrows(IllegalStateException.class, () -> this.factory.subtraction(0, 0));
        assertThrows(IllegalStateException.class, () -> this.factory.multiplication(0, 0));
        assertThrows(IllegalStateException.class, () -> this.factory.division(0, 0));
        assertThrows(IllegalStateException.class, () -> this.factory.subExpression(0, 0, 0));
        assertThrows(IllegalStateException.class, () -> this.factory.sumExpression(0, 0, 0));
    }
}
