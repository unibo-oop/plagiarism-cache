package it.trashwarecesena.trustalodesktopclient.repository.mapper.jooq;

import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.jooq.Record;
import org.jooq.SQLDialect;
import org.jooq.exception.DataAccessException;
import org.jooq.types.UByte;
import org.jooq.types.UInteger;
import org.jooq.types.UShort;

import it.trashwarecesena.trustalodesktopclient.model.devices.DeviceCategory;
import it.trashwarecesena.trustalodesktopclient.model.devices.GenericDevice;
import it.trashwarecesena.trustalodesktopclient.model.devices.RefinedDevice;
import it.trashwarecesena.trustalodesktopclient.model.people.JuridicalPerson;
import it.trashwarecesena.trustalodesktopclient.model.people.Person;
import it.trashwarecesena.trustalodesktopclient.model.people.PhysicalPerson;
import it.trashwarecesena.trustalodesktopclient.model.people.TrashwareWorker;
import it.trashwarecesena.trustalodesktopclient.model.requests.GenericDeviceRequest;
import it.trashwarecesena.trustalodesktopclient.model.requests.RefinedDeviceRequest;
import it.trashwarecesena.trustalodesktopclient.model.requests.Request;
import it.trashwarecesena.trustalodesktopclient.model.requests.RequestDetail;
import it.trashwarecesena.trustalodesktopclient.model.requests.RequestProgress;
import it.trashwarecesena.trustalodesktopclient.model.requests.concreteness.GenericDeviceRequestImpl;
import it.trashwarecesena.trustalodesktopclient.model.requests.concreteness.RefinedRequestImpl;
import it.trashwarecesena.trustalodesktopclient.model.requests.concreteness.RequestDetailImpl;
import it.trashwarecesena.trustalodesktopclient.model.requests.concreteness.RequestImpl;
import it.trashwarecesena.trustalodesktopclient.model.requests.concreteness.RequestProgressImpl;
import it.trashwarecesena.trustalodesktopclient.repository.Repository;
import it.trashwarecesena.trustalodesktopclient.repository.crud.domain.RequestsDomain;
import it.trashwarecesena.trustalodesktopclient.repository.fragmented.ConcreteFragmentedSet;
import it.trashwarecesena.trustalodesktopclient.repository.fragmented.FragmentedSet;
import it.trashwarecesena.trustalodesktopclient.repository.mapper.jooq.trustalodb.trustalo.Tables;
import it.trashwarecesena.trustalodesktopclient.repository.query.BiRequest;
import it.trashwarecesena.trustalodesktopclient.repository.query.QueryRequest;
import it.trashwarecesena.trustalodesktopclient.repository.query.SingleRequest;
import it.trashwarecesena.trustalodesktopclient.repository.query.concreteness.ReadRequest;
import it.trashwarecesena.trustalodesktopclient.repository.query.criteria.CriteriaImpl;
import it.trashwarecesena.trustalodesktopclient.repository.query.criteria.CriterionImpl;
import it.trashwarecesena.trustalodesktopclient.repository.query.criteria.QueryObject;
import it.trashwarecesena.trustalodesktopclient.repository.query.criteria.QueryObjectImpl;
import it.trashwarecesena.trustalodesktopclient.utils.ErrorString;

/**
 * A DeviceJooqMapper is a container of method implementation directly able to
 * write information over a database using the JOOQ library. This mapper manages
 * the four effective implementation of the CRUD requests upon the following
 * classes:
 * 
 *<ul>
 *<li> {@link GenericDeviceRequest}</li>
 *<li> {@link RefinedDeviceRequest}</li>
 *<li> {@link Request}</li>
 *<li> {@link RequestDetail}</li>
 *<li> {@link RequestProgress}</li>
 *</ul>
 * 
 * @author Manuel Bonarrigo
 *
 */
public final class RequestsJooqMapper extends AbstractJooqMapper implements RequestsDomain {

    private static final String GET_NUMERIC_IDENTIFIER = "getNumericIdentifier";
    private static final String REQUEST_PROGRESS = "RequestProgress";
    private static final String REFINED_DEVICE = "RefinedDevice";
    private static final String PERSON = "Person";
    private static final String GENERIC_DEVICE = "GenericDevice";
    private final Repository repository;

    /**
     * Constructs a RequestsJooqMapper able to perform its methods over a database
     * represented by the connection provided.
     * 
     * @param connection
     *            a {@link Connection} to a database.
     * @param dialect
     *            a specific feature of JOOQ, which makes the library able to change
     *            the behavior of its queries accordingly to the necessities of the
     *            related database.
     * @param repository
     *            a reference to a {@link Repository} to make the RequestsJooqMapper
     *            able to pursue its own requests for entities which are not under
     *            its control
     */
    public RequestsJooqMapper(final Connection connection, final SQLDialect dialect, final Repository repository) {
        super(connection, dialect);
        this.repository = repository;
    }

    @Override
    public void dispatchCreateRequest(final SingleRequest request) {
        final Object payload = request.getPayload();
        if (payload instanceof GenericDeviceRequest) {
            createEntry((GenericDeviceRequest) payload);
        } else if (payload instanceof RefinedDeviceRequest) {
            createEntry((RefinedDeviceRequest) payload);
        } else if (payload instanceof Request) {
            createEntry((Request) payload);
        } else if (payload instanceof RequestDetail) {
            createEntry((RequestDetail) payload);
        } else if (payload instanceof RequestProgress) {
            createEntry((RequestProgress) payload);
        } else {
            throw new IllegalStateException(
                    "No handler available for a create request containing " + request.getDesiredHandler());
        }
    }

    @Override
    public FragmentedSet dispatchReadRequest(final QueryRequest request) {
        final Class<?> handler = request.getQueryType();
        if (handler.isAssignableFrom(GenericDeviceRequest.class)) {
            return new ConcreteFragmentedSet(readGenericDeviceRequest(request.getQueryObject()), GenericDeviceRequest.class);
        } else if (handler.isAssignableFrom(RefinedDeviceRequest.class)) {
            return new ConcreteFragmentedSet(readRefinedDeviceRequest(request.getQueryObject()), RefinedDeviceRequest.class);
        } else if (handler.isAssignableFrom(Request.class)) {
            return new ConcreteFragmentedSet(readRequest(request.getQueryObject()), Request.class);
        } else if (handler.isAssignableFrom(RequestDetail.class)) {
            return new ConcreteFragmentedSet(readRequestDetail(request.getQueryObject()), RequestDetail.class);
        } else if (handler.isAssignableFrom(RequestProgress.class)) {
            return new ConcreteFragmentedSet(readRequestProgress(request.getQueryObject()), RequestProgress.class);
        } else {
            throw new IllegalStateException("No handler found in " + this.getClass() + " to handle the read request of "
                    + request.getQueryType());
        }
    }

    @Override
    public void dispatchUpdateRequest(final BiRequest biRequest) {
        final Object oldValue = biRequest.getPayload();
        final Object newValue = biRequest.getSecondPayload();
        if (oldValue instanceof GenericDeviceRequest) {
            updateEntry((GenericDeviceRequest) oldValue, (GenericDeviceRequest) newValue);
        } else if (oldValue instanceof RefinedDeviceRequest) {
            updateEntry((RefinedDeviceRequest) oldValue, (RefinedDeviceRequest) newValue);
        } else if (oldValue instanceof Request) {
            updateEntry((Request) oldValue, (Request) newValue);
        } else if (oldValue instanceof RequestDetail) {
            updateEntry((RequestDetail) oldValue, (RequestDetail) newValue);
        } else if (oldValue instanceof RequestProgress) {
            updateEntry((RequestProgress) oldValue, (RequestProgress) newValue);
        } else {
            throw new IllegalStateException(
                    "No handler available for an update request containing " + biRequest.getDesiredHandler());
        }
    }

    @Override
    public void dispatchDeleteRequest(final SingleRequest request) {
        final Object payload = request.getPayload();
        if (payload instanceof GenericDeviceRequest) {
            deleteEntry((GenericDeviceRequest) payload);
        } else if (payload instanceof RefinedDeviceRequest) {
            deleteEntry((RefinedDeviceRequest) payload);
        } else if (payload instanceof Request) {
            deleteEntry((Request) payload);
        } else if (payload instanceof RequestDetail) {
            deleteEntry((RequestDetail) payload);
        } else if (payload instanceof RequestProgress) {
            deleteEntry((RequestProgress) payload);
        } else {
            throw new IllegalStateException(
                    "No handler available for a delete request containing " + request.getDesiredHandler());
        }
    }

    @Override
    public void createEntry(final GenericDeviceRequest request) {
        try {
            this.getContext()
            .insertInto(Tables.DEVICEREQUESTSDEVICEMODELS,
                        Tables.DEVICEREQUESTSDEVICEMODELS.DEVICEMODEL, 
                        Tables.DEVICEREQUESTSDEVICEMODELS.REQUESTDEVICE,
                        Tables.DEVICEREQUESTSDEVICEMODELS.QUANTITY)
            .values(UInteger.valueOf(discoverGenericDeviceById(request.getDeviceRequested().getNumericIdentifier()
                        .orElseThrow(() -> new IllegalStateException(ErrorString.BUG_REPORTING)))
                            .orElseThrow(() -> new IllegalArgumentException(GENERIC_DEVICE))
                                .getNumericIdentifier().get()),
                    UInteger.valueOf(assertRequestDetailIdentifierValidity(request.getRequestDetail())),
                    UShort.valueOf(request.getQuantityRequested()))
            .execute();
    } catch (DataAccessException dax) {
            manageDataAccessException(GenericDeviceRequest.class, dax);
        } catch (IllegalArgumentException iae) {
            manageMissingReferenceException(GenericDeviceRequest.class, iae.getMessage());
        }
    }

    @Override
    public void createEntry(final Request request) {
        try {
            this.getContext()
            .insertInto(Tables.DEVICEREQUESTS,
                        Tables.DEVICEREQUESTS.APPLICANT,
                        Tables.DEVICEREQUESTS.DATE,
                        Tables.DEVICEREQUESTS.CURRENTSTATE,
                        Tables.DEVICEREQUESTS.LASTUPDATEDATE,
                        Tables.DEVICEREQUESTS.LASTUPDATEWORKER,
                        Tables.DEVICEREQUESTS.REFEREE,
                        Tables.DEVICEREQUESTS.SIGNER,
                        Tables.DEVICEREQUESTS.TRELLOLINK,
                        Tables.DEVICEREQUESTS.ANNOTATIONS,
                        Tables.DEVICEREQUESTS.PRIORITY
                        )
            .values(UInteger.valueOf(
                    discoverPersonById(request.getApplicant().getNumericIdentifier()
                            .orElseThrow(() -> new IllegalStateException(ErrorString.BUG_REPORTING)))
                                .orElseThrow(() -> new IllegalArgumentException(PERSON))
                                    .getNumericIdentifier().get()),
                    request.getCreationDate(),
                    UByte.valueOf(discoverRequestProgressId(request.getRequestProgress())
                            .orElseThrow(() -> new IllegalArgumentException(REQUEST_PROGRESS))),
                    request.getLastUpdate(),
                    UInteger.valueOf(
                            discoverTrashwareWorkerById(request.getLastCommitter().getPerson().getNumericIdentifier()
                                .orElseThrow(() -> new IllegalStateException(ErrorString.BUG_REPORTING)))
                                    .orElseThrow(() -> new IllegalArgumentException("TrashwareWorker"))
                                        .getPerson().getNumericIdentifier().get()),
                    request.getReferee().isPresent()
                        ? UInteger.valueOf(discoverPersonById(request.getReferee().get().getNumericIdentifier()
                                .orElseThrow(() -> new IllegalStateException(ErrorString.BUG_REPORTING)))
                                    .orElseThrow(() -> new IllegalArgumentException(PERSON))
                                        .getNumericIdentifier().get())
                        : null,
                    request.getSigner().isPresent()
                    ? UInteger.valueOf(
                            discoverPersonById(request.getSigner().get().getNumericIdentifier()
                                .orElseThrow(() -> new IllegalStateException(ErrorString.BUG_REPORTING)))
                                    .orElseThrow(() -> new IllegalArgumentException(PERSON))
                                        .getNumericIdentifier().get())
                    : null,
                    request.getTrelloLink().isPresent()
                        ? request.getTrelloLink().get().toExternalForm()
                        : null,
                    request.getAnnotations().orElse(null),
                    (byte) request.getPriority().intValue())
            .execute();
        } catch (DataAccessException dax) {
            manageDataAccessException(Request.class, dax);
        } catch (IllegalArgumentException iae) {
            manageMissingReferenceException(Request.class, iae.getMessage());
        }
    }

    @Override
    public void createEntry(final RequestDetail request) {
        try {
            this.getContext()
            .insertInto(Tables.DEVICEREQUESTDETAILS,
                        Tables.DEVICEREQUESTDETAILS.REQUEST,
                        Tables.DEVICEREQUESTDETAILS.CATEGORY,
                        Tables.DEVICEREQUESTDETAILS.ANNOTATIONS,
                        Tables.DEVICEREQUESTDETAILS.QUANTITY,
                        Tables.DEVICEREQUESTDETAILS.COMPONENTOFREQUESTDEVICE)
            .values(
                    request.getAssociatedRequest().isPresent()
                        ? UInteger.valueOf(assertRequestIdentifierValidity(request.getAssociatedRequest().get()))
                        : null,
                    request.getDeviceCategory().getAcronym(),
                    request.getAnnotations().orElse(null),
                    UByte.valueOf(request.getRequestedQuantity()),
                    request.getCompositeRequestDetail().isPresent()
                        ? UInteger.valueOf(
                                assertRequestDetailIdentifierValidity(request.getCompositeRequestDetail().get()))
                        : null)
            .execute();
        } catch (DataAccessException dax) {
            manageDataAccessException(RequestDetail.class, dax);
        } catch (IllegalArgumentException iae) {
            manageMissingReferenceException(RequestDetail.class, iae.getMessage());
        }
    }

    @Override
    public void createEntry(final RequestProgress progress) {
        try {
            this.getContext()
            .insertInto(Tables.REQUESTSTATES, Tables.REQUESTSTATES.NAME, Tables.REQUESTSTATES.DESCRIPTION) 
            .values(progress.getName(), 
                    progress.getDescription().orElse(null))
            .execute();
        } catch (DataAccessException dax) {
            manageDataAccessException(RequestProgress.class, dax);
        }
    }

    @Override
    public void createEntry(final RefinedDeviceRequest request) {
        try {
            this.getContext()
            .insertInto(Tables.DEVICEREQUESTSDEVICESWITHID,
                        Tables.DEVICEREQUESTSDEVICESWITHID.DEVICEWITHID,
                        Tables.DEVICEREQUESTSDEVICESWITHID.REQUESTDEVICE) 
            .values(UInteger.valueOf(discoverRefinedDeviceById(request.getDeviceRequested().getNumericIdentifier()
                        .orElseThrow(() -> new IllegalStateException(ErrorString.BUG_REPORTING)))
                            .orElseThrow(() -> new IllegalArgumentException(REFINED_DEVICE))
                            .getNumericIdentifier().get()),
                    UInteger.valueOf(assertRequestDetailIdentifierValidity(request.getRequestDetail())))
            .execute();
    } catch (DataAccessException dax) {
            manageDataAccessException(RefinedDeviceRequest.class, dax);
        } catch (IllegalArgumentException iae) {
            manageMissingReferenceException(RefinedDeviceRequest.class, iae.getMessage());
        }
    }

    @Override
    public Set<GenericDeviceRequest> readGenericDeviceRequest(final QueryObject filter) {
        return executeSqlRetrieveStatement(filter)
                .stream()
                .map(record -> new GenericDeviceRequestImpl(
                        discoverRequestDetailById(
                                record.getValue(Tables.DEVICEREQUESTSDEVICEMODELS.REQUESTDEVICE).intValue())
                                    .orElseThrow(() -> new IllegalStateException(ErrorString.BUG_REPORTING)),
                        discoverGenericDeviceById(
                                record.getValue(Tables.DEVICEREQUESTSDEVICEMODELS.DEVICEMODEL).intValue())
                                    .orElseThrow(() -> new IllegalStateException(ErrorString.BUG_REPORTING)),
                        record.getValue(Tables.DEVICEREQUESTSDEVICEMODELS.QUANTITY).intValue()))
                .collect(Collectors.toSet());
    }

    @Override
    public Set<RefinedDeviceRequest> readRefinedDeviceRequest(final QueryObject filter) {
        return executeSqlRetrieveStatement(filter)
                .stream()
                .map(record -> new RefinedRequestImpl(
                        discoverRefinedDeviceById(
                                record.getValue(Tables.DEVICEREQUESTSDEVICESWITHID.DEVICEWITHID).intValue())
                                    .orElseThrow(() -> new IllegalStateException(ErrorString.BUG_REPORTING)),
                        discoverRequestDetailById(
                                record.getValue(Tables.DEVICEREQUESTSDEVICESWITHID.REQUESTDEVICE).intValue())
                                    .orElseThrow(() -> new IllegalStateException(ErrorString.BUG_REPORTING))))
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Request> readRequest(final QueryObject filter) {
        final Set<Request> resultSet = new HashSet<>();
        for (final Record r : executeSqlRetrieveStatement(filter)) {
            final RequestImpl.Builder builder = new RequestImpl.Builder();
            if (Objects.nonNull(r.getValue(Tables.DEVICEREQUESTS.REFEREE))) {
                builder.referee(discoverPersonById(r.getValue(Tables.DEVICEREQUESTS.REFEREE).intValue())
                        .orElseThrow(() -> new IllegalArgumentException(PERSON)));
            }
            if (Objects.nonNull(r.getValue(Tables.DEVICEREQUESTS.SIGNER))) {
                builder.signer(discoverPersonById(r.getValue(Tables.DEVICEREQUESTS.SIGNER).intValue())
                        .orElseThrow(() -> new IllegalArgumentException(PERSON)));
            }
            if (Objects.nonNull(r.getValue(Tables.DEVICEREQUESTS.TRELLOLINK))) {
                try {
                    builder.trelloLink(new URL(r.getValue(Tables.DEVICEREQUESTS.TRELLOLINK)));
                } catch (MalformedURLException | IllegalArgumentException e) {
                    throw new IllegalStateException(ErrorString.BUG_REPORTING);
                }
            }
            if (Objects.nonNull(r.getValue(Tables.DEVICEREQUESTS.ANNOTATIONS))) {
                builder.annotations(r.getValue(Tables.DEVICEREQUESTS.ANNOTATIONS));
            }
            builder.identifier(r.getValue(Tables.DEVICEREQUESTS.ID).intValue());
            builder.applicant(discoverPersonById(r.getValue(Tables.DEVICEREQUESTS.APPLICANT).intValue())
                    .orElseThrow(() -> new IllegalArgumentException(PERSON)));
            builder.creationDate(r.getValue(Tables.DEVICEREQUESTS.DATE));
            builder.lastUpdate(r.getValue(Tables.DEVICEREQUESTS.LASTUPDATEDATE));
            builder.lastCommitter(
                    discoverTrashwareWorkerById(r.getValue(Tables.DEVICEREQUESTS.LASTUPDATEWORKER).intValue())
                        .orElseThrow(() -> new IllegalArgumentException("TrashwareWorker")));
            builder.progress(discoverRequestProgressById(((Byte) r.get(3)).intValue()));
            builder.priority(r.getValue(Tables.DEVICEREQUESTS.PRIORITY).intValue());
            resultSet.add(builder.build());
        }
        return resultSet;
    }

    @Override
    public Set<RequestDetail> readRequestDetail(final QueryObject filter) {
        final Set<RequestDetail> resultSet = new HashSet<>();
        for (final Record r : executeSqlRetrieveStatement(filter)) {
            final RequestDetailImpl.Builder builder = new RequestDetailImpl.Builder();
            if (Objects.nonNull(r.getValue(Tables.DEVICEREQUESTDETAILS.REQUEST))) {
                builder.associatedRequest(
                        discoverRequestById(r.getValue(Tables.DEVICEREQUESTDETAILS.REQUEST).intValue())
                        .orElseThrow(() -> new IllegalArgumentException("Request")));
            }
            if (Objects.nonNull(r.getValue(Tables.DEVICEREQUESTDETAILS.COMPONENTOFREQUESTDEVICE))) {
                builder.compositeRequestDetail(
                        discoverRequestDetailById(
                                r.getValue(Tables.DEVICEREQUESTDETAILS.COMPONENTOFREQUESTDEVICE).intValue())
                        .orElseThrow(() -> new IllegalArgumentException("RequestDetail")));
            }
            if (Objects.nonNull(r.getValue(Tables.DEVICEREQUESTDETAILS.ANNOTATIONS))) {
                builder.annotations(r.getValue(Tables.DEVICEREQUESTDETAILS.ANNOTATIONS));
            }
            builder.identifier(r.getValue(Tables.DEVICEREQUESTDETAILS.ID).intValue());
            builder.category(discoverDeviceCategoryById(r.getValue(Tables.DEVICEREQUESTDETAILS.CATEGORY))
                    .orElseThrow(() -> new IllegalArgumentException("DeviceCategory")));
            builder.quantity(((Byte) r.get(4)).intValue());

            resultSet.add(builder.build());
        }
        return resultSet;
    }

    @Override
    public Set<RequestProgress> readRequestProgress(final QueryObject filter) {
        return executeSqlRetrieveStatement(filter)
                .stream()
                .map(record -> new RequestProgressImpl(
                        record.getValue(Tables.REQUESTSTATES.NAME),
                        record.getValue(Tables.REQUESTSTATES.DESCRIPTION)))
                .collect(Collectors.toSet());
    }

    @Override
    public void updateEntry(final GenericDeviceRequest oldRequest, final  GenericDeviceRequest newRequest) {
        try {
            getContext().update(Tables.DEVICEREQUESTSDEVICEMODELS)
            .set(Tables.DEVICEREQUESTSDEVICEMODELS.REQUESTDEVICE, 
                    UInteger.valueOf(assertRequestDetailIdentifierValidity(newRequest.getRequestDetail())))
            .set(Tables.DEVICEREQUESTSDEVICEMODELS.DEVICEMODEL, 
                    UInteger.valueOf(
                            discoverGenericDeviceById(newRequest.getDeviceRequested().getNumericIdentifier()
                                    .orElseThrow(() -> new IllegalStateException(ErrorString.BUG_REPORTING)))
                            .orElseThrow(() -> new IllegalArgumentException(GENERIC_DEVICE))
                            .getNumericIdentifier().get()))
            .set(Tables.DEVICEREQUESTSDEVICEMODELS.QUANTITY, UShort.valueOf(newRequest.getQuantityRequested()))
            .where(Tables.DEVICEREQUESTSDEVICEMODELS.DEVICEMODEL.eq(UInteger.valueOf(
                    discoverGenericDeviceById(oldRequest.getDeviceRequested().getNumericIdentifier().orElseThrow(
                            () -> new IllegalStateException(ErrorString.BUG_REPORTING)))
                    .orElseThrow(() -> new IllegalArgumentException(GENERIC_DEVICE)).getNumericIdentifier().get()))
            .and(Tables.DEVICEREQUESTSDEVICEMODELS.REQUESTDEVICE
                    .eq(UInteger.valueOf(assertRequestDetailIdentifierValidity(oldRequest.getRequestDetail())))))
            .execute();
        } catch (DataAccessException dax) {
            manageDataAccessException(GenericDeviceRequest.class, dax);
        } catch (IllegalArgumentException iae) {
            manageMissingReferenceException(GenericDeviceRequest.class, iae.getMessage());
        }
    }

    @Override
    public void updateEntry(final Request oldRequest, final  Request newRequest) {
        try {
            getContext().update(Tables.DEVICEREQUESTS)
            .set(Tables.DEVICEREQUESTS.ANNOTATIONS, newRequest.getAnnotations().orElse(null))
            .set(Tables.DEVICEREQUESTS.APPLICANT, 
                    UInteger.valueOf(discoverPersonById(newRequest.getReferee().get().getNumericIdentifier()
                            .orElseThrow(() -> new IllegalStateException(ErrorString.BUG_REPORTING)))
                                .orElseThrow(() -> new IllegalArgumentException(PERSON))
                            .getNumericIdentifier().get()))
            .set(Tables.DEVICEREQUESTS.CURRENTSTATE, 
                    UByte.valueOf(
                            discoverRequestProgressId(
                                    newRequest.getRequestProgress())
                            .orElseThrow(() -> new IllegalArgumentException(REQUEST_PROGRESS))))
            .set(Tables.DEVICEREQUESTS.DATE, newRequest.getCreationDate())
            .set(Tables.DEVICEREQUESTS.LASTUPDATEDATE, newRequest.getLastUpdate())
            .set(Tables.DEVICEREQUESTS.LASTUPDATEWORKER, 
                    UInteger.valueOf(discoverTrashwareWorkerById(
                            newRequest.getLastCommitter().getPerson().getNumericIdentifier().get())
                                .orElseThrow(() -> new IllegalArgumentException("TrashwareWorker"))
                                    .getPerson().getNumericIdentifier().get()))
            .set(Tables.DEVICEREQUESTS.PRIORITY, (byte) newRequest.getPriority().intValue())
            .set(Tables.DEVICEREQUESTS.REFEREE, newRequest.getReferee().isPresent()
                    ? UInteger.valueOf(discoverPersonById(newRequest.getReferee().get().getNumericIdentifier()
                                            .orElseThrow(() -> new IllegalStateException(ErrorString.BUG_REPORTING)))
                                                .orElseThrow(() -> new IllegalArgumentException(PERSON))
                                                    .getNumericIdentifier().get())
                    : null)
            .set(Tables.DEVICEREQUESTS.SIGNER, 
                    newRequest.getSigner().isPresent()
                        ? UInteger.valueOf(discoverPersonById(newRequest.getSigner().get().getNumericIdentifier()
                                            .orElseThrow(() -> new IllegalStateException(ErrorString.BUG_REPORTING)))
                                                .orElseThrow(() -> new IllegalArgumentException(PERSON))
                                                    .getNumericIdentifier().get())
                        : null)
            .set(Tables.DEVICEREQUESTS.TRELLOLINK, 
                    newRequest.getTrelloLink().isPresent()
                        ? newRequest.getTrelloLink().get().toExternalForm()
                        : null)
            .where(Tables.DEVICEREQUESTS.ID.eq(UInteger.valueOf(assertRequestIdentifierValidity(oldRequest))))
            .execute();
        } catch (DataAccessException dax) {
            manageDataAccessException(Request.class, dax);
        } catch (IllegalArgumentException iae) {
            manageMissingReferenceException(Request.class, iae.getMessage());
        }
    }

    @Override
    public void updateEntry(final RequestDetail oldRequest, final  RequestDetail newRequest) {
        try {
            getContext().update(Tables.DEVICEREQUESTDETAILS)
            .set(Tables.DEVICEREQUESTDETAILS.ANNOTATIONS, newRequest.getAnnotations().orElse(null))
            .set(Tables.DEVICEREQUESTDETAILS.CATEGORY, 
                    discoverDeviceCategoryById(newRequest.getDeviceCategory().getAcronym())
                        .orElseThrow(() -> new IllegalArgumentException("DeviceCategory")).getAcronym())
            .set(Tables.DEVICEREQUESTDETAILS.COMPONENTOFREQUESTDEVICE, 
                    newRequest.getCompositeRequestDetail().isPresent()
                    ? UInteger.valueOf(
                            assertRequestDetailIdentifierValidity(newRequest.getCompositeRequestDetail().get()))
                    : null)
            .set(Tables.DEVICEREQUESTDETAILS.QUANTITY, UByte.valueOf(newRequest.getRequestedQuantity()))
            .set(Tables.DEVICEREQUESTDETAILS.REQUEST, 
                    newRequest.getAssociatedRequest().isPresent()
                    ? UInteger.valueOf(assertRequestIdentifierValidity(newRequest.getAssociatedRequest().get()))
                    : null)
            .where(Tables.DEVICEREQUESTDETAILS.ID.eq(
                    UInteger.valueOf(assertRequestDetailIdentifierValidity(oldRequest))))
            .execute();
        } catch (DataAccessException dax) {
            manageDataAccessException(RequestDetail.class, dax);
        } catch (IllegalArgumentException iae) {
            manageMissingReferenceException(RequestDetail.class, iae.getMessage());
        }
    }

    @Override
    public void updateEntry(final RequestProgress oldProgress, final  RequestProgress newProgress) {
        try {
            getContext().update(Tables.REQUESTSTATES)
            .set(Tables.REQUESTSTATES.NAME, newProgress.getName())
            .set(Tables.REQUESTSTATES.DESCRIPTION, newProgress.getDescription().orElse(null))
            .where(Tables.REQUESTSTATES.ID.eq(UByte.valueOf(discoverRequestProgressId(oldProgress)
                .orElseThrow(() -> new IllegalArgumentException(REQUEST_PROGRESS)))))
            .execute();
        } catch (DataAccessException dax) {
            manageDataAccessException(RequestProgress.class, dax);
        } catch (IllegalArgumentException iae) {
            manageMissingReferenceException(RequestProgress.class, iae.getMessage());
        }
    }

    @Override
    public void updateEntry(final RefinedDeviceRequest oldRequest, final  RefinedDeviceRequest newRequest) {
        try {
            getContext().update(Tables.DEVICEREQUESTSDEVICESWITHID)
            .set(Tables.DEVICEREQUESTSDEVICESWITHID.REQUESTDEVICE, 
                    UInteger.valueOf(assertRequestDetailIdentifierValidity(newRequest.getRequestDetail())))
            .set(Tables.DEVICEREQUESTSDEVICESWITHID.DEVICEWITHID, 
                    UInteger.valueOf(
                            discoverRefinedDeviceById(newRequest.getDeviceRequested().getNumericIdentifier()
                                    .orElseThrow(() -> new IllegalStateException(ErrorString.BUG_REPORTING)))
                            .orElseThrow(() -> new IllegalArgumentException(REFINED_DEVICE))
                            .getNumericIdentifier().get()
                            ))
            .where(Tables.DEVICEREQUESTSDEVICESWITHID.DEVICEWITHID
                    .eq(UInteger.valueOf(
                            discoverRefinedDeviceById(oldRequest.getDeviceRequested().getNumericIdentifier()
                                    .orElseThrow(() -> new IllegalStateException(ErrorString.BUG_REPORTING)))
                            .orElseThrow(() -> new IllegalArgumentException(REFINED_DEVICE))
                            .getNumericIdentifier().get()
                            ))
            .and(Tables.DEVICEREQUESTSDEVICESWITHID.REQUESTDEVICE
                    .eq(UInteger.valueOf(assertRequestDetailIdentifierValidity(oldRequest.getRequestDetail())))))
            .execute();
        } catch (DataAccessException dax) {
            manageDataAccessException(RefinedDeviceRequest.class, dax);
        } catch (IllegalArgumentException iae) {
            manageMissingReferenceException(RefinedDeviceRequest.class, iae.getMessage());
        }
    }

    @Override
    public void deleteEntry(final GenericDeviceRequest request) {
        try {
            getContext().delete(Tables.DEVICEREQUESTSDEVICEMODELS)
            .where(Tables.DEVICEREQUESTSDEVICEMODELS.DEVICEMODEL.eq(UInteger.valueOf(
                    discoverGenericDeviceById(request.getDeviceRequested().getNumericIdentifier().orElseThrow(
                            () -> new IllegalStateException(ErrorString.BUG_REPORTING)))
                    .orElseThrow(() -> new IllegalArgumentException(GENERIC_DEVICE)).getNumericIdentifier().get()))
            .and(Tables.DEVICEREQUESTSDEVICEMODELS.REQUESTDEVICE
                    .eq(UInteger.valueOf(assertRequestDetailIdentifierValidity(request.getRequestDetail())))))
            .execute();
        } catch (DataAccessException dax) {
            manageBoundedReferenceOnDeletionException(GenericDeviceRequest.class);
        } catch (IllegalArgumentException iae) {
            manageMissingReferenceException(GenericDeviceRequest.class, iae.getMessage());
        }
    }

    @Override
    public void deleteEntry(final Request request) {
        try {
            getContext().delete(Tables.DEVICEREQUESTS)
            .where(Tables.DEVICEREQUESTS.ID.eq(UInteger.valueOf(assertRequestIdentifierValidity(request))))
            .execute();
        } catch (DataAccessException dax) {
            manageBoundedReferenceOnDeletionException(Request.class);
        } catch (IllegalArgumentException iae) {
            manageMissingReferenceException(Request.class, iae.getMessage());
        }
    }

    @Override
    public void deleteEntry(final RequestDetail request) {
        try {
            getContext().delete(Tables.DEVICEREQUESTDETAILS)
            .where(Tables.DEVICEREQUESTDETAILS.ID.eq(UInteger.valueOf(assertRequestDetailIdentifierValidity(request))))
            .execute();
        } catch (DataAccessException dax) {
            manageBoundedReferenceOnDeletionException(RequestDetail.class);
        } catch (IllegalArgumentException iae) {
            manageMissingReferenceException(RequestDetail.class, iae.getMessage());
        }
    }

    @Override
    public void deleteEntry(final RequestProgress progress) {
        try {
            getContext().delete(Tables.REQUESTSTATES)
            .where(Tables.REQUESTSTATES.ID.eq(UByte.valueOf(discoverRequestProgressId(progress)
                .orElseThrow(() -> new IllegalArgumentException(REQUEST_PROGRESS)))))
            .execute();
        } catch (DataAccessException dax) {
            manageBoundedReferenceOnDeletionException(RequestProgress.class);
        } catch (IllegalArgumentException iae) {
            manageMissingReferenceException(RequestProgress.class, iae.getMessage());
        }
    }

    @Override
    public void deleteEntry(final RefinedDeviceRequest request) {
        try {
            getContext().delete(Tables.DEVICEREQUESTSDEVICESWITHID)
            .where(Tables.DEVICEREQUESTSDEVICESWITHID.DEVICEWITHID
                    .eq(UInteger.valueOf(
                            discoverRefinedDeviceById(request.getDeviceRequested().getNumericIdentifier()
                                    .orElseThrow(() -> new IllegalStateException(ErrorString.BUG_REPORTING)))
                            .orElseThrow(() -> new IllegalArgumentException(REFINED_DEVICE))
                            .getNumericIdentifier().get()
                            ))
            .and(Tables.DEVICEREQUESTSDEVICESWITHID.REQUESTDEVICE
                    .eq(UInteger.valueOf(assertRequestDetailIdentifierValidity(request.getRequestDetail())))))
            .execute();
        } catch (DataAccessException dax) {
            manageBoundedReferenceOnDeletionException(RefinedDeviceRequest.class);
        } catch (IllegalArgumentException iae) {
            manageMissingReferenceException(RefinedDeviceRequest.class, iae.getMessage());
        }
    }

    private Optional<Integer> discoverRequestProgressId(final RequestProgress progress) {
        final Optional<Record> queryResult = this.getContext().select()
            .from(Tables.REQUESTSTATES)
            .where(Tables.REQUESTSTATES.NAME.eq(progress.getName()))
            .fetch()
            .stream()
            .findFirst();
        return queryResult.isPresent() 
            ? Optional.of(queryResult.get().getValue(Tables.REQUESTSTATES.ID).intValue())
            : Optional.empty();
    }

    private Optional<GenericDevice> discoverGenericDeviceById(final Integer identifier) {
        final Set<GenericDevice> identifiedGenericDevice = 
                this.repository.dispatchReadRequest(
                        new ReadRequest(
                                new QueryObjectImpl(GenericDevice.class, new CriteriaImpl.Builder()
                                        .addCriterion(CriterionImpl.equality(GET_NUMERIC_IDENTIFIER, identifier))
                                        .build())))
                .getUnerasedSet();
        return identifiedGenericDevice
                .stream()
                .findFirst();
    }

    private Optional<RefinedDevice> discoverRefinedDeviceById(final Integer identifier) {
        final Set<RefinedDevice> identifiedRefinedDevice = 
                this.repository.dispatchReadRequest(
                        new ReadRequest(
                                new QueryObjectImpl(RefinedDevice.class, new CriteriaImpl.Builder()
                                        .addCriterion(CriterionImpl.equality(GET_NUMERIC_IDENTIFIER, identifier))
                                        .build())))
                .getUnerasedSet();
        return identifiedRefinedDevice
                .stream()
                .findFirst();
    }

    private RequestProgress discoverRequestProgressById(final Integer identifier) {
        return this.getContext().select()
            .from(Tables.REQUESTSTATES)
            .where(Tables.REQUESTSTATES.ID.eq(UByte.valueOf(identifier)))
            .fetch()
            .stream()
            .map(record -> new RequestProgressImpl(record.getValue(Tables.REQUESTSTATES.NAME),
                    record.getValue(Tables.REQUESTSTATES.DESCRIPTION)))
            .findFirst()
            .get();
    }

    private Optional<RequestDetail> discoverRequestDetailById(final Integer identifier) {
        final Optional<Record> result = this.getContext().select()
                .from(Tables.DEVICEREQUESTDETAILS)
                .where(Tables.DEVICEREQUESTDETAILS.ID.eq(UInteger.valueOf(identifier)))
                .fetch()
                .stream()
                .findFirst();
        if (result.isPresent()) {
            final RequestDetailImpl.Builder builder = new RequestDetailImpl.Builder();
            if (Objects.nonNull(result.get().getValue(Tables.DEVICEREQUESTDETAILS.REQUEST))) {
                builder.associatedRequest(discoverRequestById(
                        result.get().getValue(Tables.DEVICEREQUESTDETAILS.REQUEST).intValue()).get());
            }
            if (Objects.nonNull(result.get().getValue(Tables.DEVICEREQUESTDETAILS.COMPONENTOFREQUESTDEVICE))) {
                builder.compositeRequestDetail(discoverRequestDetailById(
                        result.get().getValue(Tables.DEVICEREQUESTDETAILS.COMPONENTOFREQUESTDEVICE).intValue()).get());
            }
            if (Objects.nonNull(result.get().getValue(Tables.DEVICEREQUESTDETAILS.ANNOTATIONS))) {
                builder.annotations(result.get().getValue(Tables.DEVICEREQUESTDETAILS.ANNOTATIONS));
            }
            builder.identifier(result.get().getValue(Tables.DEVICEREQUESTDETAILS.ID).intValue());
            builder.category(discoverDeviceCategoryById(result.get().getValue(Tables.DEVICEREQUESTDETAILS.CATEGORY))
                    .orElseThrow(() -> new IllegalArgumentException("DeviceCategory")));
            builder.quantity(result.get().getValue(Tables.DEVICEREQUESTDETAILS.QUANTITY).intValue());
            return Optional.of(builder.build());
        }
        return Optional.empty();
    }

    private Optional<Request> discoverRequestById(final Integer identifier) {
        final Optional<Record> result = this.getContext().select()
                .from(Tables.DEVICEREQUESTS)
                .where(Tables.DEVICEREQUESTS.ID.eq(UInteger.valueOf(identifier)))
                .fetch()
                .stream()
                .findFirst();
        if (result.isPresent()) {
            final RequestImpl.Builder builder = new RequestImpl.Builder();
            if (Objects.nonNull(result.get().getValue(Tables.DEVICEREQUESTS.REFEREE))) {
                builder.referee(discoverPersonById(result.get().getValue(Tables.DEVICEREQUESTS.REFEREE).intValue())
                        .get());
            }
            if (Objects.nonNull(result.get().getValue(Tables.DEVICEREQUESTS.SIGNER))) {
                builder.signer(discoverPersonById(result.get().getValue(Tables.DEVICEREQUESTS.SIGNER).intValue())
                        .get());
            }
            if (Objects.nonNull(result.get().getValue(Tables.DEVICEREQUESTS.TRELLOLINK))) {
                try {
                    builder.trelloLink(new URL(result.get().getValue(Tables.DEVICEREQUESTS.TRELLOLINK)));
                } catch (MalformedURLException | IllegalArgumentException e) {
                    throw new IllegalStateException(ErrorString.BUG_REPORTING);
                }
            }
            if (Objects.nonNull(result.get().getValue(Tables.DEVICEREQUESTS.ANNOTATIONS))) {
                builder.annotations(result.get().getValue(Tables.DEVICEREQUESTS.ANNOTATIONS));
            }
            builder.identifier(result.get().getValue(Tables.DEVICEREQUESTS.ID).intValue());
            builder.applicant(discoverPersonById(result.get().getValue(Tables.DEVICEREQUESTS.APPLICANT).intValue())
                    .get());
            builder.creationDate(new Date(result.get().getValue(Tables.DEVICEREQUESTS.DATE).getTime()));
            builder.progress(discoverRequestProgressById(
                    result.get().getValue(Tables.DEVICEREQUESTS.CURRENTSTATE).intValue()));
            builder.lastUpdate(new Date(result.get().getValue(Tables.DEVICEREQUESTS.LASTUPDATEDATE).getTime()));
            builder.lastCommitter(discoverTrashwareWorkerById(
                    result.get().getValue(Tables.DEVICEREQUESTS.LASTUPDATEWORKER).intValue()).get());
            builder.priority(result.get().getValue(Tables.DEVICEREQUESTS.PRIORITY).intValue());
            return Optional.of(builder.build());
        }
        return Optional.empty();
    }

    private Integer assertRequestIdentifierValidity(final Request request) {
        if (!(request.equals(
                discoverRequestById(request.getNumericIdentifier()
                        .orElseThrow(() -> new IllegalStateException(ErrorString.BUG_REPORTING)))
                            .orElse(null)))) {
            throw new IllegalStateException("A corrupted Request has been sent to " + this.getClass().getSimpleName()); 
        }
        return request.getNumericIdentifier().get();
    }

    private Integer assertRequestDetailIdentifierValidity(final RequestDetail detail) {
        if (!(detail.equals(discoverRequestDetailById(detail.getNumericIdentifier().orElseThrow(
                () -> new IllegalStateException(ErrorString.BUG_REPORTING))).orElse(null)))) {
            throw new IllegalStateException("A corrupted RequestDetail has been sent to " 
                + this.getClass().getSimpleName()); 
        }
        return detail.getNumericIdentifier().get();
    }

    private Optional<Person> discoverPersonById(final Integer identifier) {
        final Set<PhysicalPerson> identifiedPhysicalPerson = 
                this.repository.dispatchReadRequest(
                        new ReadRequest(
                                new QueryObjectImpl(PhysicalPerson.class, new CriteriaImpl.Builder()
                                        .addCriterion(CriterionImpl.equality(GET_NUMERIC_IDENTIFIER, identifier))
                                        .build())))
                .getUnerasedSet();
        final Set<JuridicalPerson> identifiedJuridicalPerson = 
                this.repository.dispatchReadRequest(
                        new ReadRequest(
                                new QueryObjectImpl(JuridicalPerson.class, new CriteriaImpl.Builder()
                                        .addCriterion(CriterionImpl.equality(GET_NUMERIC_IDENTIFIER, identifier))
                                        .build())))
                .getUnerasedSet();
        final Set<Person> people = new HashSet<>();
        people.addAll(identifiedJuridicalPerson);
        people.addAll(identifiedPhysicalPerson);
        return people.stream().findFirst();
    }

    private Optional<DeviceCategory> discoverDeviceCategoryById(final String acronym) {
        final Set<DeviceCategory> identifiedCategory =
                this.repository.dispatchReadRequest(
                        new ReadRequest(
                                new QueryObjectImpl(
                                        DeviceCategory.class, new CriteriaImpl.Builder()
                                        .addCriterion(CriterionImpl.equality("getAcronym", acronym))
                                        .build())))
                .getUnerasedSet();
        return identifiedCategory.stream().findFirst();
    }

    private Optional<TrashwareWorker> discoverTrashwareWorkerById(final Integer identifier) {
        final Set<PhysicalPerson> identifiedPerson = 
                this.repository.dispatchReadRequest(
                        new ReadRequest(
                                new QueryObjectImpl(PhysicalPerson.class, new CriteriaImpl.Builder()
                                        .addCriterion(CriterionImpl.equality(GET_NUMERIC_IDENTIFIER, identifier))
                                        .build())))
                .getUnerasedSet();
        if (!identifiedPerson.isEmpty()) {
            final Set<TrashwareWorker> result = 
                    this.repository.dispatchReadRequest(
                        new ReadRequest(
                                new QueryObjectImpl(
                                        TrashwareWorker.class, new CriteriaImpl.Builder()
                                            .addCriterion(CriterionImpl.equality("getPerson", 
                                                    identifiedPerson.iterator().next()))
                                            .build())))
                    .getUnerasedSet();
            return result.stream().findFirst();
        }
        return Optional.empty();
    }
}
