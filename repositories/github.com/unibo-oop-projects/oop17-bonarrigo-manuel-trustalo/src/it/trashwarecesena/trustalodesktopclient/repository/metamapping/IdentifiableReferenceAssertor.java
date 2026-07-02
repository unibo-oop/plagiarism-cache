package it.trashwarecesena.trustalodesktopclient.repository.metamapping;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import it.trashwarecesena.trustalodesktopclient.model.Identifiable;
import it.trashwarecesena.trustalodesktopclient.repository.metamapping.annotations.Carrier;
import it.trashwarecesena.trustalodesktopclient.repository.metamapping.annotations.InterfaceMethodToSchemaField;
import it.trashwarecesena.trustalodesktopclient.repository.query.BiRequest;
import it.trashwarecesena.trustalodesktopclient.repository.query.SingleRequest;
import it.trashwarecesena.trustalodesktopclient.utils.ErrorString;

/**
 * A utility class with all the methods needed to check on the constraints
 * imposed by the {@link Identifiable} interface about the correctness of an
 * object which carries an Identifiable.
 * 
 * @author Manuel Bonarrigo
 *
 */
public final class IdentifiableReferenceAssertor {

    private static final String PROPOSED_CLASS_NOT_FROM_DOMAIN = "The request is not built upon a domain model " 
                                                                    + "interface";

    /**
     * Asserts that given a request for update, all the {@link Identifiable}
     * references in the dependency tree of the request payload comply to the
     * Identifiable rules.
     * 
     * @param request
     *            the request to explore for satisfaction.
     * @return the same request passed in as a parameter if no exception are thrown
     *         by the checks.
     */
    public static BiRequest assertUpdativeForeignKeysValidity(final BiRequest request) {
        recursiveStep(request.getPayload(), "update", true, "");
        return request;
    }

    /**
     * Asserts that given a request for delete, all the {@link Identifiable}
     * references in the dependency tree of the request payload comply to the
     * Identifiable rules.
     * 
     * @param request
     *            the request to explore for satisfaction.
     * @return the same request passed in as a parameter if no exception are thrown
     *         by the checks.
     */
    public static SingleRequest assertDeletionForeignKeysValidity(final SingleRequest request) {
        recursiveStep(request.getPayload(), "deletion", true, "");
        return request;
    }

    /**
     * Asserts that given a request for creation, all the {@link Identifiable}
     * references in the dependency tree of the request payload comply to the
     * Identifiable rules.
     * 
     * @param request
     *            the request to explore for satisfaction.
     * @return the same request passed in as a parameter if no exception are thrown
     *         by the checks.
     */
    public static SingleRequest assertCreationalForeignKeysValidity(final SingleRequest request) {
        recursiveStep(request.getPayload(), "creational", false, "");
        return request;
    }

    private static void recursiveStep(final Object payload, final String errorLocation, 
            final boolean existenceExpectation, final String errorDiscovery) {
        final Class<?> legalPayloadInterface = MetamappingKnowledge.discoverDomainModelInterfaceImplemented(
                payload.getClass()).orElseThrow(() -> new IllegalStateException(PROPOSED_CLASS_NOT_FROM_DOMAIN));
        if (isCarrier(legalPayloadInterface)) {
            for (final Object o : obtainJumps(legalPayloadInterface, payload)) {
                recursiveStep(o, errorLocation, true, " of " + legalPayloadInterface.getSimpleName());
            }
        }
        if (isIdentifiable(legalPayloadInterface) 
                && !isCompliantToExistenceExpectation(Identifiable.class.cast(payload), existenceExpectation)) {
                if (existenceExpectation) {
                    throw new IllegalArgumentException("Illegal absence of the numeric identifier in " 
                                                        + legalPayloadInterface.getSimpleName() + " in a creation "
                                                                + "request" + errorDiscovery);
                } else {
                    throw new IllegalArgumentException("Illegal presence of the numeric identifier in " 
                                                        + legalPayloadInterface.getSimpleName() + " in a creation "
                                                                + "request" + errorDiscovery);
                }
            }
    }
    private static Set<Object> obtainJumps(final Class<?> payloadInterface, final Object payload) {
        final Set<Object> jumps = new HashSet<>();
        final Set<Method> methodToExplore = MetamappingKnowledge.getMappedSelectors(payloadInterface)
                                            .stream()
                                            .filter(method -> isCarrier(extractReturnedTypeFromMappedMethod(method)) 
                                                      || isIdentifiable(extractReturnedTypeFromMappedMethod(method)))
                                            .collect(Collectors.toSet());
        for (final Method m : methodToExplore) {
            if (m.getReturnType().equals(Optional.class)) {
                if ((Optional.class.cast(invokeGetter(m, payload))).isPresent()) {
                    jumps.add((Optional.class.cast(invokeGetter(m, payload))).get());
                } else {
                    continue;
                }
            } else {
                jumps.add(invokeGetter(m, payload));
            }
        }
        return jumps;
    }

    private static Class<?> extractReturnedTypeFromMappedMethod(final Method annotated) {
        return annotated.getAnnotation(InterfaceMethodToSchemaField.class).returnType();
    }

    private static Object invokeGetter(final Method getter, final Object source) {
        try {
            return getter.invoke(source, new Object[0]);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            e.printStackTrace();
            throw new IllegalStateException(ErrorString.BUG_REPORTING);
        }
    }

    private static boolean isIdentifiable(final Class<?> klass) {
        return Identifiable.class.isAssignableFrom(klass);
    }

    private static boolean isCarrier(final Class<?> klass) {
        return Objects.nonNull(klass.getAnnotation(Carrier.class));
    }

    private static boolean isCompliantToExistenceExpectation(final Identifiable obj, final boolean expectation) {
        return hasNumericIdentifier(obj) == expectation;
    }

    private static boolean hasNumericIdentifier(final Identifiable obj) {
        return obj.getNumericIdentifier().isPresent();
    }

    private IdentifiableReferenceAssertor() {
    }

}
