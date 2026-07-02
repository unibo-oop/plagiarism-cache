package it.trashwarecesena.trustalodesktopclient.repository.metamapping;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import it.trashwarecesena.trustalodesktopclient.repository.metamapping.annotations.EntityInterface;
import it.trashwarecesena.trustalodesktopclient.repository.metamapping.annotations.InterfaceMethodToSchemaField;
import it.trashwarecesena.trustalodesktopclient.repository.metamapping.annotations.InterfaceToSchemaEntity;

/**
 * A utility class which has a wider knowledge over the untold relations between
 * a domain model Class and a domain model entity.
 * 
 * @author Manuel Bonarrigo
 *
 */
public final class MetamappingKnowledge {

    private MetamappingKnowledge() {
    }

    /**
     * Extract the name of the annotated identifier over the domain schema entity
     * from the given class.
     * 
     * @param klass
     *            the {@link Class} to perform the extraction from.
     * @return an {@link Optional} containing a {@link String} with the desired
     *         value, or {@code Optional.empty} if the klass parameter is not part
     *         of the domain model schema.
     */
    public static Optional<String> getMappedEntityIdentifierField(final Class<?> klass) {
        return isSchemaEntityNameAvailable(klass) 
                ? Optional.of(klass.getAnnotation(InterfaceToSchemaEntity.class).identifierName())
                : Optional.empty();
    }

    /**
     * Extract the name of the annotated domain schema entity bound to the given
     * class.
     * 
     * @param klass
     *            the {@link Class} to perform the extraction from.
     * @return an {@link Optional} containing a {@link String} with the desired
     *         value, or {@code Optional.empty} if the klass parameter is not part
     *         of the domain model schema.
     */
    public static Optional<String> getMappedEntityName(final Class<?> klass) {
        return isSchemaEntityNameAvailable(klass) 
                    ? Optional.of(klass.getAnnotation(InterfaceToSchemaEntity.class).schemaEntity())
                    : Optional.empty();
    }

    /**
     * Extract the name of the annotated domain schema entity field bound to the
     * given method, in the given class.
     * 
     * @param klass
     *            the {@link Class} to perform the extraction from.
     * @param getterName
     *            the name of the getter to know the mapping of.
     * @return an {@link Optional} containing a {@link String} with the desired
     *         value, or {@code Optional.empty} if the klass parameter is not part
     *         of the domain model schema, or if the method is not mapped to any
     */
    public static Optional<String> getMappedFieldName(final Class<?> klass, final String getterName) {
        return isLegalSelector(klass, getterName) 
                    ? Optional.of(retrieveSelector(klass, getterName).get()
                            .getAnnotation(InterfaceMethodToSchemaField.class).schemaField())
                    : Optional.empty();
    }

    /**
     * Tells if the given domain model Class hold a method with the same name as the
     * one given in.
     * 
     * @param klass
     *            the {@link Class} to be inspected.
     * @param getterName
     *            the name of the method to be searched.
     * @return {@code true} if the class contains such a method, {@code false}
     *         otherwise
     */
    public static boolean isLegalSelector(final Class<?> klass, final String getterName) {
        if (retrieveSelector(klass, getterName).isPresent()) {
            return Objects.nonNull((retrieveSelector(klass, getterName)).get()
                    .getAnnotation(InterfaceMethodToSchemaField.class));
        } else {
            return false;
        }
    }

    /**
     * Given a triplet made of a domain model class, the name of one of his getters
     * and the expected class of the object returned by the getter, tells if the
     * assumption that in such a class, there is a getter that will return such an
     * object type, or not.
     * 
     * @param klass
     *            a {@link Class} from the domain model object.
     * @param getterName
     *            the name of the getter which expected return type is being
     *            validated.
     * @param missingLink
     *            the Class of the object returned by the getter.
     * @return true if the getter actually returns an object of the missingLink
     *         type, false otherwise.
     */
    public static boolean isLegalSelectorAndValueTypeCombination(final Class<?> klass, final String getterName,
                                                                 final Optional<Class<?>> missingLink) {
        if (isLegalSelector(klass, getterName) && missingLink.isPresent()) {
                return retrieveSelector(klass, getterName).get()
                .getAnnotation(InterfaceMethodToSchemaField.class)
                .returnType().isAssignableFrom(missingLink.get());
            }
        return false;
    }

    /**
     * Retrieve the name of all the getter methods with a mapping over the domain
     * schema entity field from a given Class.
     * 
     * @param klass
     *            the {@link Class} to extract the getter name from.
     * @return a {@link Set} populated with the appropriate {@link String}.
     */
    public static Set<String> getAvailableSelectors(final Class<?> klass) {
        return getMappedSelectors(klass).stream()
                                        .map(method -> method.getName())
                                        .collect(Collectors.toSet());
    }

    /**
     * Retrieve the references to all the getter methods with a mapping over the domain
     * schema entity field from a given Class.
     * 
     * @param klass
     *            the {@link Class} to extract the getter references from.
     * @return a {@link Set} populated with the appropriate {@link Method}.
     */
    public static Set<Method> getMappedSelectors(final Class<?> klass) {
        return Arrays.stream(klass.getMethods())
                .filter(method -> isMappedToField(method))
                .collect(Collectors.toSet());
    }

    private static boolean isMappedToField(final Method method) {
        return Objects.nonNull(method.getAnnotation(InterfaceMethodToSchemaField.class));
    }

    private static Optional<Method> retrieveSelector(final Class<?> klass, final String getterName) {
        return getMappedSelectors(klass)
            .stream()
            .filter(method -> method.getName().equals(getterName))
            .findFirst();
    }

    /**
     * Discover which domain model interface is implemented by the given class, if
     * any. By this implementation, the actual Class returned is the first domain
     * model interface encountered navigating the hierarchy tree from the lowest
     * known point, distinguished by the given class, to the higher one available,
     * once a match with a domain model interface happens.
     * 
     * @param domainEntity
     *            the {@link Class} with the ancestor to be discovered.
     * @return An {@link Optional} containing a Class which is the discovered
     *         interface, or {@code Optional.empty} if the hierarchy has been
     *         navigated up until {@link Object} or a totally unrelated Interface
     *         has been reached.
     */
    public static Optional<Class<?>> discoverDomainModelInterfaceImplemented(final Class<?> domainEntity) {
        if (domainEntity.equals(Object.class)) {
            return Optional.empty();
        }
        if (domainEntity.isInterface()) {
            if (isEntityInterface(domainEntity)) {
                return Optional.of(domainEntity);
            } else {
                return Optional.empty();
            }
        }
        final List<Class<?>> implemented = Arrays.stream(domainEntity.getInterfaces())
                                                 .filter(domainClass -> isEntityInterface(domainClass))
                                                 .collect(Collectors.toList());
        return implemented.isEmpty() 
                ? discoverDomainModelInterfaceImplemented(domainEntity.getSuperclass()) 
                : Optional.of(implemented.get(0));
    }

    /**
     * Tells if the given Class is the root Interface over his own hierarchy tree.
     * 
     * @param domainEntity
     *            the {@link Class} to be checked.
     * @return {@code true} if the domainEntity parameter is the root interface of
     *         his own hierarchy, {@code false} otherwise
     */
    public static boolean isEntityInterface(final Class<?> domainEntity) {
        return Objects.nonNull(domainEntity.getAnnotation(EntityInterface.class));
    }

    /**
     * Tells if the given Class has a known mapping over the domain schema.
     * 
     * @param domainEntity
     *            the {@link Class} to be checked.
     * @return {@code true} if the domainEntity parameter has a known mapping
     *         available, {@code false} otherwise.
     */
    public static boolean isMetamappingAvailable(final Class<?> domainEntity) {
        return discoverDomainModelInterfaceImplemented(domainEntity).isPresent();
    }

    /**
     * Tells if the given Class is mapped to a domain schema entity.
     * 
     * @param domainEntity
     *            the {@link Class} to be checked.
     * @return {@code true} if the domainEntity parameter is bound to a domain model
     *         schema entity, {@code false} otherwise.
     */
    public static boolean isSchemaEntityNameAvailable(final Class<?> domainEntity) {
        return Objects.nonNull(domainEntity.getAnnotation(InterfaceToSchemaEntity.class));
    }

}
