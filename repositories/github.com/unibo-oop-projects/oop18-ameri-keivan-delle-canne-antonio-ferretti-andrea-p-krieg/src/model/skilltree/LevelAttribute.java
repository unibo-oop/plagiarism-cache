package model.skilltree;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import io.github.classgraph.ClassGraph;
import io.github.classgraph.ClassInfoList;
import io.github.classgraph.ScanResult;
import model.Cost;

/**
 * The LevelAttribute class is an abstract class extends
 * SkillTreeAttributeAbstract. It can be used to be extended by attributes that
 * provide a level structure, where each level is represented by a class. This
 * class use some ClassGraph methods for manage the level classes.
 * 
 * @param <X> could be a generic class or interface that has a level structure.
 */
public abstract class LevelAttribute<X> extends SkillTreeAttributeAbstract {

    private final List<String> objectClasses;

    /**
     * LevelAttribute constructor.
     * 
     * @param initialLevel        is the attribute's initial level.
     * @param whiteListPackage    is the the package where to search the classes.
     * @param classesImplementing is the the package of the interface that the
     *                            classes implements.
     * @param filterStart         is a filter used to filter the initial part of the
     *                            classes' name.
     * @param filterEnd           is a filter used to filter the last part of the
     *                            classes' name.
     */
    public LevelAttribute(final int initialLevel, final String whiteListPackage, final String classesImplementing,
            final String filterStart, final String filterEnd) {
        super(initialLevel);
        this.objectClasses = getObjectClasses(whiteListPackage, classesImplementing, filterStart, filterEnd);
    }

    /**
     * This method is used to filter the classes that implements the interface of
     * the level object.
     * 
     * @param size        is the size of the classes list.
     * @param filterStart is the initial filter.
     * @param filterEnd   is the final filter.
     * @return a list of all classes with their package that match with the two
     *         filter.
     */
    private static List<String> getFilterList(final int size, final String filterStart, final String filterEnd) {
        return IntStream.range(0, size).mapToObj(i -> filterStart + i + filterEnd).collect(Collectors.toList());
    }

    /**
     * This method is used to get all the package of the class that implements the
     * object classes and that match with the two filter.
     * 
     * @param whiteListPackage    is the the package where to search the classes.
     * @param classesImplementing is the the package of the interface that the
     *                            classes implements.
     * @param filterStart         is a filter used to filter the initial part of the
     *                            classes' name.
     * @param filterEnd           is a filter used to filter the last part of the
     *                            classes' name.
     * @return the list of classes package.
     */
    private static List<String> getObjectClasses(final String whiteListPackage, final String classesImplementing,
            final String filterStart, final String filterEnd) {
        try (ScanResult scanResult = new ClassGraph().enableAllInfo().whitelistPackages(whiteListPackage).scan()) {
            final ClassInfoList widgetClasses = scanResult.getClassesImplementing(classesImplementing);
            return widgetClasses.getNames().stream()
                    .filter(s -> getFilterList(widgetClasses.size(), filterStart, filterEnd).contains(s))
                    .collect(Collectors.toList());
        }
    }

    /** {@inheritDoc} **/
    @Override
    public boolean canUpgrade() {
        return getNewObject().isPresent();
    }

    /** {@inheritDoc} **/
    @Override
    public abstract Cost getCost();

    /** {@inheritDoc} **/
    @Override
    public abstract String getAttributeName();

    /**
     * This method can be use to take an object of the level passed as parameter.
     * 
     * @param level of the object to take.
     * @return the object if it is present.
     */
    protected abstract Optional<X> getObject(int level);

    /**
     * This method can be use take the next object.
     * @return the next object if it is present.
     */
    protected Optional<X> getNewObject() {
        return getObject(getCurrentValue() + 1);
    }

    /**
     * This method can be use to get the all the name of the classes that extends X.
     * 
     * @return the object classes.
     */
    protected List<String> getObjectClasses() {
        return this.objectClasses;
    }
}
