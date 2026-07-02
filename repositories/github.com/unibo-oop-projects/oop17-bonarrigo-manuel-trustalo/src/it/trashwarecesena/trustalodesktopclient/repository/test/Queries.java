package it.trashwarecesena.trustalodesktopclient.repository.test;

import it.trashwarecesena.trustalodesktopclient.model.devices.GenericDevice;
import it.trashwarecesena.trustalodesktopclient.model.devices.RefinedDevice;
import it.trashwarecesena.trustalodesktopclient.model.devices.ScreenResolution;
import it.trashwarecesena.trustalodesktopclient.model.people.JuridicalPerson;
import it.trashwarecesena.trustalodesktopclient.model.people.PhysicalPerson;
import it.trashwarecesena.trustalodesktopclient.model.requests.Request;
import it.trashwarecesena.trustalodesktopclient.model.requests.RequestDetail;
import it.trashwarecesena.trustalodesktopclient.repository.metamapping.MetamappingKnowledge;
import it.trashwarecesena.trustalodesktopclient.repository.query.criteria.CriteriaImpl;
import it.trashwarecesena.trustalodesktopclient.repository.query.criteria.CriterionImpl;
import it.trashwarecesena.trustalodesktopclient.repository.query.criteria.QueryObject;
import it.trashwarecesena.trustalodesktopclient.repository.query.criteria.QueryObjectImpl;

/**
 * Utility class to obtain a {@link QueryObject} instantiated over all the
 * fields of every domain model object. The usage outside in discouraged, since
 * the filters are thought to work only on fully instantiated objects and to
 * retrieve only one object in fully controlled situations, and this is not
 * granted during the operativity of the system.
 * 
 * @author Manuel Bonarrigo
 *
 */
public final class Queries {

    private static final String GET_ANNOTATIONS = "getAnnotations";

    /**
     * Automatically build a QueryRequest to fetch all the objects from a
     * persistence storage. Should be used for testing purpose only.
     * 
     * @param k
     *            the class from the domain model to filter upon
     * @return a {@link QueryObject} to be used in testing methods.
     * @throws IllegalArgumentException
     *             if the object is not part of the domain model
     */
    public static QueryObject getAll(final Class<?> k) {
        return new QueryObjectImpl(MetamappingKnowledge.discoverDomainModelInterfaceImplemented(k)
                                       .orElseThrow(() -> new IllegalArgumentException()),
                                   new CriteriaImpl.Builder()
                                       .addCriterion(CriterionImpl.all())
                                       .build());
    }

    /**
     * Convenience method to obtain a {@link QueryObject} filter over the given
     * {@link GenericDevice}.
     * 
     * @param device
     *            the object to instantiate the filter.
     * 
     * @return a {@link QueryObject} filtering over all the fields of the object
     */
    public static QueryObject getTestFilter(final GenericDevice device) {
        return new QueryObjectImpl(GenericDevice.class, 
                new CriteriaImpl.Builder()
                .addCriterion(CriterionImpl.equality("getVendor", device.getVendor().orElse(null)))
                .addCriterion(CriterionImpl.equality(
                        "getVendorModelIdentifier", device.getVendorModelIdentifier().orElse(null)))
                .addCriterion(CriterionImpl.equality("getDeviceCategory", device.getDeviceCategory()))
                .addCriterion(CriterionImpl.equality(
                        "getDeviceDescription", device.getDeviceDescription().orElse(null)))
                .addCriterion(CriterionImpl.equality(
                        "getNumberOfAvailableDevices", device.getNumberOfAvailableDevices()))
                .build());
    }

    /**
     * Convenience method to obtain a {@link QueryObject} filter over the given
     * {@link RefinedDevice}.
     * 
     * @param device
     *            the object to instantiate the filter.
     * 
     * @return a {@link QueryObject} filtering over all the fields of the object
     */
    public static QueryObject getTestFilter(final RefinedDevice device) {
        return new QueryObjectImpl(RefinedDevice.class, 
                new CriteriaImpl.Builder()
                    .addCriterion(CriterionImpl.equality("getDeviceCategory", device.getDeviceCategory()))
                    .addCriterion(CriterionImpl.equality("isAvailable", device.isAvailable()))
                    .addCriterion(CriterionImpl.equality("getGenericDevice", device.getGenericDevice()))
                    .addCriterion(CriterionImpl.equality("getCategoryDeviceId", device.getCategoryDeviceId()))
                    .addCriterion(CriterionImpl.equality("getWorkProgress", device.getWorkProgress()))
                    .addCriterion(CriterionImpl.equality("getLastChangeCommitter", device.getLastChangeCommitter()))
                    .addCriterion(CriterionImpl.equality("getLastChangeDate", device.getLastChangeDate()))
                    .addCriterion(CriterionImpl.equality(GET_ANNOTATIONS, device.getAnnotations().orElse(null)))
                .build());
    }

    /**
     * Convenience method to obtain a {@link QueryObject} filter over the given
     * {@link ScreenResolution}.
     * 
     * @param resolution
     *            the object to instantiate the filter.
     * 
     * @return a {@link QueryObject} filtering over all the fields of the object
     */
    public static QueryObject getTestFilter(final ScreenResolution resolution) {
        return new QueryObjectImpl(ScreenResolution.class, 
                new CriteriaImpl.Builder()
                    .addCriterion(CriterionImpl.equality("getAspectRatio", resolution.getAspectRatio()))
                    .addCriterion(CriterionImpl.equality("getWidth", resolution.getWidth()))
                    .addCriterion(CriterionImpl.equality("getHeight", resolution.getHeight()))
                .build());
    }

    /**
     * Convenience method to obtain a {@link QueryObject} filter over the given
     * {@link PhysicalPerson}.
     * 
     * @param person
     *            the object to instantiate the filter.
     * 
     * @return a {@link QueryObject} filtering over all the fields of the object
     */
    public static QueryObject getTestFilter(final PhysicalPerson person) {
        return new QueryObjectImpl(PhysicalPerson.class, 
            new CriteriaImpl.Builder()
                .addCriterion(CriterionImpl.equality("getName", person.getName()))
                .addCriterion(CriterionImpl.equality("getCategory", person.getCategory()))
                .addCriterion(CriterionImpl.equality("getFiscalCode", person.getFiscalCode().orElse(null)))
                .addCriterion(CriterionImpl.equality(GET_ANNOTATIONS, person.getAnnotations().orElse(null)))
                .addCriterion(CriterionImpl.equality("getBirthDate", person.getBirthDate().orElse(null)))
                .addCriterion(CriterionImpl.equality("getBirthLocation", person.getBirthLocation().orElse(null)))
            .build());
    }

    /**
     * Convenience method to obtain a {@link QueryObject} filter over the given
     * {@link JuridicalPerson}.
     * 
     * @param person
     *            the object to instantiate the filter.
     * 
     * @return a {@link QueryObject} filtering over all the fields of the object
     */
    public static QueryObject getTestFilter(final JuridicalPerson person) {
        return new QueryObjectImpl(JuridicalPerson.class, 
                new CriteriaImpl.Builder()
                    .addCriterion(CriterionImpl.equality("getName", person.getName()))
                    .addCriterion(CriterionImpl.equality("getCategory", person.getCategory()))
                    .addCriterion(CriterionImpl.equality("getFiscalCode", person.getFiscalCode().orElse(null)))
                    .addCriterion(CriterionImpl.equality(GET_ANNOTATIONS, person.getAnnotations().orElse(null)))
                .build());
    }

    /**
     * Convenience method to obtain a {@link QueryObject} filter over the given
     * {@link Request}.
     * 
     * @param request
     *            the object to instantiate the filter.
     * 
     * @return a {@link QueryObject} filtering over all the fields of the object
     */
    public static QueryObject getTestFilter(final Request request) {
        return new QueryObjectImpl(Request.class, 
                new CriteriaImpl.Builder()
                    .addCriterion(CriterionImpl.equality("getApplicant", request.getApplicant()))
                    .addCriterion(CriterionImpl.equality("getCreationDate", request.getCreationDate()))
                    .addCriterion(CriterionImpl.equality("getRequestProgress", request.getRequestProgress()))
                    .addCriterion(CriterionImpl.equality("getLastUpdate", request.getLastUpdate()))
                    .addCriterion(CriterionImpl.equality("getLastCommitter", request.getLastCommitter()))
                    .addCriterion(CriterionImpl.equality("getReferee", request.getReferee().orElse(null)))
                    .addCriterion(CriterionImpl.equality("getSigner", request.getSigner().orElse(null)))
                    .addCriterion(CriterionImpl.equality("getTrelloLink", request.getTrelloLink().orElse(null)))
                    .addCriterion(CriterionImpl.equality(GET_ANNOTATIONS, request.getAnnotations().orElse(null)))
                    .addCriterion(CriterionImpl.equality("getPriority", request.getPriority()))
                .build());
    }

    /**
     * Convenience method to obtain a {@link QueryObject} filter over the given
     * {@link RequestDetail}.
     * 
     * @param detail
     *            the object to instantiate the filter.
     * 
     * @return a {@link QueryObject} filtering over all the fields of the object
     */
    public static QueryObject getTestFilter(final RequestDetail detail) {
        return new QueryObjectImpl(RequestDetail.class, 
                new CriteriaImpl.Builder()
                    .addCriterion(
                            CriterionImpl.equality("getAssociatedRequest", detail.getAssociatedRequest().orElse(null)))
                    .addCriterion(CriterionImpl.equality("getDeviceCategory", detail.getDeviceCategory()))
                    .addCriterion(CriterionImpl.equality(GET_ANNOTATIONS, detail.getAnnotations().orElse(null)))
                    .addCriterion(CriterionImpl.equality("getRequestedQuantity", detail.getRequestedQuantity()))
                    .addCriterion(
                            CriterionImpl.equality("getCompositeRequestDetail", 
                                    detail.getCompositeRequestDetail().orElse(null)))
                .build());
    }

    private Queries() { }

}
