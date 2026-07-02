package it.unibo.oop.mge.functionfeatures;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.awt.Color;

import org.junit.jupiter.api.Test;

import it.unibo.oop.mge.color.VariableColorBuilderImpl;
import it.unibo.oop.mge.function.AlgebricFunctionFactory;
import it.unibo.oop.mge.model.FunctionFeaturesBuilderImpl;

public class FunctionFeaturesBuilderTest {
    @Test
    void illegalArgumentTest() {
        /* We check all IllegalArgumentException that must be handle */
        assertThrows(IllegalArgumentException.class, () -> new FunctionFeaturesBuilderImpl().decimalPrecision(-2));
        assertThrows(IllegalArgumentException.class, () -> new FunctionFeaturesBuilderImpl().dynamicColor(null));
        assertThrows(IllegalArgumentException.class, () -> new FunctionFeaturesBuilderImpl().dynamicColor(null));
        assertThrows(IllegalArgumentException.class, () -> new FunctionFeaturesBuilderImpl().function(null));
        assertThrows(IllegalArgumentException.class, () -> new FunctionFeaturesBuilderImpl().intervals(10, -10));
        assertThrows(IllegalArgumentException.class, () -> new FunctionFeaturesBuilderImpl().rate(0));
        assertThrows(IllegalArgumentException.class, () -> new FunctionFeaturesBuilderImpl().rate(-2));
        assertThrows(IllegalArgumentException.class, () -> new FunctionFeaturesBuilderImpl().staticColor(null));
    }

    @Test
    void illegalStateTest() {
        /* We check all IllegalStateException that must be handle */
        assertThrows(IllegalStateException.class,
                () -> new FunctionFeaturesBuilderImpl().decimalPrecision(2).build());
        assertThrows(IllegalStateException.class, () -> new FunctionFeaturesBuilderImpl()
                .function(AlgebricFunctionFactory.getValueFunction(2.0)).build());
        assertThrows(IllegalStateException.class, () -> new FunctionFeaturesBuilderImpl()
                .function(AlgebricFunctionFactory.getValueFunction(2.0)).intervals(-1, 1).build());
        assertThrows(IllegalStateException.class, () -> new FunctionFeaturesBuilderImpl()
                .function(AlgebricFunctionFactory.getValueFunction(2.0)).intervals(-1, 1).rate(0.1).build());
        assertThrows(IllegalStateException.class,
                () -> new FunctionFeaturesBuilderImpl().function(AlgebricFunctionFactory.getValueFunction(2.0))
                        .intervals(-1, 1).rate(0.1)
                        .dynamicColor(new VariableColorBuilderImpl().blue(10).build())
                        .dynamicColor(new VariableColorBuilderImpl().blue(10).build()).build());
        assertThrows(IllegalStateException.class,
                () -> new FunctionFeaturesBuilderImpl().function(AlgebricFunctionFactory.getValueFunction(2.0))
                        .intervals(-1, 1).rate(0.1)
                        .dynamicColor(new VariableColorBuilderImpl().blue(10).build()).staticColor(Color.black)
                        .build());
        assertThrows(IllegalStateException.class,
                () -> new FunctionFeaturesBuilderImpl().function(AlgebricFunctionFactory.getValueFunction(2.0))
                        .intervals(-1, 1).rate(0.1).staticColor(Color.black).staticColor(Color.black)
                        .build());
        assertThrows(IllegalStateException.class,
                () -> new FunctionFeaturesBuilderImpl().function(AlgebricFunctionFactory.getValueFunction(2.0))
                        .intervals(-1, 1).rate(0.1)
                        .dynamicColor(new VariableColorBuilderImpl().blue(10).build()).staticColor(Color.black)
                        .build());
    }
}
