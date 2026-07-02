package it.unibo.aknightstale.factories;

import io.github.classgraph.ClassGraph;
import io.github.classgraph.ClassInfoList;
import io.github.classgraph.ScanResult;
import it.unibo.aknightstale.App;
import it.unibo.aknightstale.exceptions.ClassInstantiationException;
import it.unibo.aknightstale.views.AlertType;
import it.unibo.aknightstale.views.factories.Alert;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public final class ClassFactory {
    private ClassFactory() {
    }

    /**
     * Creates an instance of the class implementing the interface.
     *
     * @param interfaceClass   Interface to search implementing classes to instantiate.
     * @param acceptedPackages Packages to search implementing classes in.
     * @param <T>              Interface type.
     * @return An instance of the class implementing the interface.
     */
    public static <T> T createInstanceFromInterface(final Class<T> interfaceClass, final String... acceptedPackages) {
        String className;
        try (ScanResult scanResult = new ClassGraph()
                .enableAllInfo()
                .acceptPackages(absolutizePackageNames(acceptedPackages))
                .scan()) {
            final ClassInfoList implementations = scanResult.getClassesImplementing(interfaceClass);
            if (implementations.isEmpty()) {
                final var message = "No implementation of " + interfaceClass.getCanonicalName() + " found in packages " + Arrays.toString(absolutizePackageNames(acceptedPackages));
                Alert.showAlert(AlertType.ERROR, "Error", "No implementation found", message);
                throw new ClassInstantiationException(message);
            }

            className = implementations.get(0).getName();
        }

        try {
            return interfaceClass.cast(Class.forName(className).getDeclaredConstructor().newInstance());
        } catch (ClassCastException | ClassNotFoundException | NoSuchMethodException | InvocationTargetException
                 | InstantiationException | IllegalAccessException e) {
            final var message = "Error instantiating " + interfaceClass.getName() + " - " + e.getClass().getSimpleName() + ": " + e.getMessage();
            Alert.showAlert(AlertType.ERROR, "Error", "Error instantiating", message);
            throw new ClassInstantiationException(message, e);
        }
    }

    private static String[] absolutizePackageNames(final String[] packageNames) {
        return Arrays.stream(packageNames)
                .map(packageName -> packageName.contains(App.APP_PACKAGE) ? packageName : App.APP_PACKAGE + "." + packageName)
                .toArray(String[]::new);
    }
}
