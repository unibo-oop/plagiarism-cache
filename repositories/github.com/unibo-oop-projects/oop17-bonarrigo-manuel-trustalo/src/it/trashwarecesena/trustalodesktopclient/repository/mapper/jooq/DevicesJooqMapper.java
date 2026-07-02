package it.trashwarecesena.trustalodesktopclient.repository.mapper.jooq; //NOPMD by Manuel Bonarrigo: This is a boundary
//class towards the database. Something had to take that burden.

import java.sql.Connection;
import java.sql.Date;
import java.sql.Timestamp;
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

import it.trashwarecesena.trustalodesktopclient.model.devices.AspectRatio;
import it.trashwarecesena.trustalodesktopclient.model.devices.Case;
import it.trashwarecesena.trustalodesktopclient.model.devices.Color;
import it.trashwarecesena.trustalodesktopclient.model.devices.DeviceCategory;
import it.trashwarecesena.trustalodesktopclient.model.devices.DeviceWorkProgress;
import it.trashwarecesena.trustalodesktopclient.model.devices.DigitalInformationUnit;
import it.trashwarecesena.trustalodesktopclient.model.devices.GenericDevice;
import it.trashwarecesena.trustalodesktopclient.model.devices.HardDiskDrive;
import it.trashwarecesena.trustalodesktopclient.model.devices.LegalCategoryCompound;
import it.trashwarecesena.trustalodesktopclient.model.devices.Printer;
import it.trashwarecesena.trustalodesktopclient.model.devices.PrinterCategory;
import it.trashwarecesena.trustalodesktopclient.model.devices.RandomAccessMemory;
import it.trashwarecesena.trustalodesktopclient.model.devices.RefinedDevice;
import it.trashwarecesena.trustalodesktopclient.model.devices.RefinedDeviceCompound;
import it.trashwarecesena.trustalodesktopclient.model.devices.RefinedDeviceCompoundWithGeneric;
import it.trashwarecesena.trustalodesktopclient.model.devices.Screen;
import it.trashwarecesena.trustalodesktopclient.model.devices.ScreenCategory;
import it.trashwarecesena.trustalodesktopclient.model.devices.ScreenResolution;
import it.trashwarecesena.trustalodesktopclient.model.devices.Vendor;
import it.trashwarecesena.trustalodesktopclient.model.devices.concreteness.AspectRatioImpl;
import it.trashwarecesena.trustalodesktopclient.model.devices.concreteness.CaseImpl;
import it.trashwarecesena.trustalodesktopclient.model.devices.concreteness.ColorImpl;
import it.trashwarecesena.trustalodesktopclient.model.devices.concreteness.DeviceCategoryImpl;
import it.trashwarecesena.trustalodesktopclient.model.devices.concreteness.DeviceWorkProgressImpl;
import it.trashwarecesena.trustalodesktopclient.model.devices.concreteness.DigitalInformationUnitImpl;
import it.trashwarecesena.trustalodesktopclient.model.devices.concreteness.GenericDeviceImpl;
import it.trashwarecesena.trustalodesktopclient.model.devices.concreteness.HardDiskDriveImpl;
import it.trashwarecesena.trustalodesktopclient.model.devices.concreteness.LegalCategoryCompoundImpl;
import it.trashwarecesena.trustalodesktopclient.model.devices.concreteness.PrinterCategoryImpl;
import it.trashwarecesena.trustalodesktopclient.model.devices.concreteness.PrinterImpl;
import it.trashwarecesena.trustalodesktopclient.model.devices.concreteness.RandomAccessMemoryImpl;
import it.trashwarecesena.trustalodesktopclient.model.devices.concreteness.RefinedDeviceCompoundImpl;
import it.trashwarecesena.trustalodesktopclient.model.devices.concreteness.RefinedDeviceCompoundWithGenericImpl;
import it.trashwarecesena.trustalodesktopclient.model.devices.concreteness.RefinedDeviceImpl;
import it.trashwarecesena.trustalodesktopclient.model.devices.concreteness.ScreenCategoryImpl;
import it.trashwarecesena.trustalodesktopclient.model.devices.concreteness.ScreenImpl;
import it.trashwarecesena.trustalodesktopclient.model.devices.concreteness.ScreenResolutionImpl;
import it.trashwarecesena.trustalodesktopclient.model.devices.concreteness.VendorImpl;
import it.trashwarecesena.trustalodesktopclient.model.otherdevices.Processor;
import it.trashwarecesena.trustalodesktopclient.model.otherdevices.concreteness.ProcessorImpl;
import it.trashwarecesena.trustalodesktopclient.model.people.PhysicalPerson;
import it.trashwarecesena.trustalodesktopclient.model.people.TrashwareWorker;
import it.trashwarecesena.trustalodesktopclient.repository.Repository;
import it.trashwarecesena.trustalodesktopclient.repository.crud.domain.DeviceDomain;
import it.trashwarecesena.trustalodesktopclient.repository.exception.BoundedReferenceException;
import it.trashwarecesena.trustalodesktopclient.repository.fragmented.ConcreteFragmentedSet;
import it.trashwarecesena.trustalodesktopclient.repository.fragmented.FragmentedSet;
import it.trashwarecesena.trustalodesktopclient.repository.query.BiRequest;
import it.trashwarecesena.trustalodesktopclient.repository.query.QueryRequest;
import it.trashwarecesena.trustalodesktopclient.repository.query.SingleRequest;
import it.trashwarecesena.trustalodesktopclient.repository.query.concreteness.ReadRequest;
import it.trashwarecesena.trustalodesktopclient.repository.query.concreteness.UpdateRequest;
import it.trashwarecesena.trustalodesktopclient.repository.query.criteria.CriteriaImpl;
import it.trashwarecesena.trustalodesktopclient.repository.query.criteria.CriterionImpl;
import it.trashwarecesena.trustalodesktopclient.repository.query.criteria.QueryObject;
import it.trashwarecesena.trustalodesktopclient.repository.query.criteria.QueryObjectImpl;
import it.trashwarecesena.trustalodesktopclient.repository.mapper.jooq.trustalodb.trustalo.Tables;
import it.trashwarecesena.trustalodesktopclient.utils.ErrorString;

/**
 * A DeviceJooqMapper is a container of method implementation directly able to
 * write information over a database using the JOOQ library. This mapper manages
 * the four effective implementation of the CRUD requests upon the following
 * classes:
 * 
 *<ul>
 *<li> {@link AspectRatio}</li>
 *<li> {@link Case}</li>
 *<li> {@link Color}</li>
 *<li> {@link DeviceCategory}</li>
 *<li> {@link DeviceWorkProgress}</li>
 *<li> {@link DigitalInformationUnit}</li>
 *<li> {@link GenericDevice}</li>
 *<li> {@link HardDiskDrive}</li>
 *<li> {@link LegalCategoryCompound}</li>
 *<li> {@link Printer}</li>
 *<li> {@link PrinterCategory}</li>
 *<li> {@link RandomAccessMemory}</li>
 *<li> {@link RefinedDevice}</li>
 *<li> {@link RefinedDeviceCompound}</li>
 *<li> {@link RefinedDeviceCompoundWithGeneric}</li>
 *<li> {@link Screen}</li>
 *<li> {@link ScreenCategory}</li>
 *<li> {@link ScreenResolution}</li>
 *<li> {@link Vendor}</li>
 *</ul>
 * 
 * @author Manuel Bonarrigo
 *
 */
public final class DevicesJooqMapper extends AbstractJooqMapper implements DeviceDomain {

    private static final String DANGLING = "Dangling references are detected. Cannot delete the ";
    private static final String ASPECT_RATIO = "AspectRatio";
    private static final String COLOR = "Color";
    private static final String DEVICE_CATEGORY = "DeviceCategory ";
    private static final String DEVICE_WORK_PROGRESS = "DeviceWorkProgress";
    private static final String DIGITAL_INFORMATION_UNIT = "DigitalInformationUnit";
    private static final String PRINTER_CATEGORY = "PrinterCategory";
    private static final String REFINED_DEVICE = "RefinedDevice";
    private static final String SCREEN_CATEGORY = "ScreenCategory";
    private static final String VENDOR = "Vendor";
    private static final int FIVE = 5; 

    private final Repository repository;

    /**
     * Constructs a DeviceJooqMapper able to perform its methods over a database
     * represented by the connection provided.
     * 
     * @param connection
     *            a {@link Connection} to a database.
     * @param dialect
     *            a specific feature of JOOQ, which makes the library able to change
     *            the behavior of its queries accordingly to the necessities of the
     *            related database.
     * @param repository
     *            a reference to a {@link Repository} to make the DevicesJooqMapper
     *            able to pursue its own requests for entities which are not under
     *            its control
     */
    public DevicesJooqMapper(final Connection connection, final SQLDialect dialect, final Repository repository) {
        super(connection, dialect);
        this.repository = repository;
    }

    @Override
    public void dispatchCreateRequest(final SingleRequest request) {
        final Object payload = request.getPayload();
        if (payload instanceof AspectRatio) {
            createEntry((AspectRatio) payload);
        } else if (payload instanceof Case) {
            createEntry((Case) payload);
        } else if (payload instanceof Color) {
            createEntry((Color) payload);
        } else if (payload instanceof DeviceCategory) {
            createEntry((DeviceCategory) payload);
        } else if (payload instanceof DeviceWorkProgress) {
            createEntry((DeviceWorkProgress) payload);
        } else if (payload instanceof DigitalInformationUnit) {
            createEntry((DigitalInformationUnit) payload);
        } else if (payload instanceof GenericDevice) {
            createEntry((GenericDevice) payload);
        } else if (payload instanceof HardDiskDrive) {
            createEntry((HardDiskDrive) payload);
        } else if (payload instanceof LegalCategoryCompound) {
            createEntry((LegalCategoryCompound) payload);
        } else if (payload instanceof Printer) {
            createEntry((Printer) payload);
        } else if (payload instanceof PrinterCategory) {
            createEntry((PrinterCategory) payload);
        } else if (payload instanceof RandomAccessMemory) {
            createEntry((RandomAccessMemory) payload);
        } else if (payload instanceof RefinedDevice) {
            createEntry((RefinedDevice) payload);
        } else if (payload instanceof RefinedDeviceCompound) {
            createEntry((RefinedDeviceCompound) payload);
        } else if (payload instanceof RefinedDeviceCompoundWithGeneric) {
            createEntry((RefinedDeviceCompoundWithGeneric) payload);
        } else if (payload instanceof Screen) {
            createEntry((Screen) payload);
        } else if (payload instanceof ScreenCategory) {
            createEntry((ScreenCategory) payload);
        } else if (payload instanceof ScreenResolution) {
            createEntry((ScreenResolution) payload);
        } else if (payload instanceof Vendor) {
            createEntry((Vendor) payload);
        } else {
            throw new IllegalStateException(
                    "No handler available for a create request containing " + request.getDesiredHandler());
        }
    }

    @Override
    public FragmentedSet dispatchReadRequest(final QueryRequest request) {
        final Class<?> handler = request.getQueryType();
        if (handler.isAssignableFrom(AspectRatio.class)) {
            return new ConcreteFragmentedSet(readAspectRatio(request.getQueryObject()), AspectRatio.class);
        } else if (handler.isAssignableFrom(Case.class)) {
            return new ConcreteFragmentedSet(readCase(request.getQueryObject()), Case.class);
        } else if (handler.isAssignableFrom(Color.class)) {
            return new ConcreteFragmentedSet(readColor(request.getQueryObject()), Color.class);
        } else if (handler.isAssignableFrom(DeviceCategory.class)) {
            return new ConcreteFragmentedSet(readDeviceCategory(request.getQueryObject()), DeviceCategory.class);
        } else if (handler.isAssignableFrom(DeviceWorkProgress.class)) {
            return new ConcreteFragmentedSet(readDeviceWorkProgress(
                    request.getQueryObject()), DeviceWorkProgress.class);
        } else if (handler.isAssignableFrom(DigitalInformationUnit.class)) {
            return new ConcreteFragmentedSet(readDigitalInformationUnit(
                    request.getQueryObject()), DigitalInformationUnit.class);
        } else if (handler.isAssignableFrom(GenericDevice.class)) {
            return new ConcreteFragmentedSet(readGenericDevice(request.getQueryObject()), GenericDevice.class);
        } else if (handler.isAssignableFrom(HardDiskDrive.class)) {
            return new ConcreteFragmentedSet(readHardDiskDrive(request.getQueryObject()), HardDiskDrive.class);
        } else if (handler.isAssignableFrom(LegalCategoryCompound.class)) {
            return new ConcreteFragmentedSet(readLegalCategoryCompound(
                    request.getQueryObject()), LegalCategoryCompound.class);
        } else if (handler.isAssignableFrom(Printer.class)) {
            return new ConcreteFragmentedSet(readPrinter(request.getQueryObject()), Printer.class);
        } else if (handler.isAssignableFrom(PrinterCategory.class)) {
            return new ConcreteFragmentedSet(readPrinterCategory(request.getQueryObject()), PrinterCategory.class);
        } else if (handler.isAssignableFrom(RandomAccessMemory.class)) {
            return new ConcreteFragmentedSet(readRandomAccessMemory(
                    request.getQueryObject()), RandomAccessMemory.class);
        } else if (handler.isAssignableFrom(RefinedDevice.class)) {
            return new ConcreteFragmentedSet(readRefinedDevice(request.getQueryObject()), RefinedDevice.class);
        } else if (handler.isAssignableFrom(RefinedDeviceCompound.class)) {
            return new ConcreteFragmentedSet(readRefinedDeviceCompound(
                    request.getQueryObject()), RefinedDeviceCompound.class);
        } else if (handler.isAssignableFrom(RefinedDeviceCompoundWithGeneric.class)) {
            return new ConcreteFragmentedSet(readRefinedDeviceCompoundWithGeneric(
                    request.getQueryObject()), RefinedDeviceCompoundWithGeneric.class);
        } else if (handler.isAssignableFrom(Screen.class)) {
            return new ConcreteFragmentedSet(readScreen(request.getQueryObject()), Screen.class);
        } else if (handler.isAssignableFrom(ScreenCategory.class)) {
            return new ConcreteFragmentedSet(readScreenCategory(request.getQueryObject()), ScreenCategory.class);
        } else if (handler.isAssignableFrom(ScreenResolution.class)) {
            return new ConcreteFragmentedSet(readScreenResolution(request.getQueryObject()), ScreenResolution.class);
        } else if (handler.isAssignableFrom(Vendor.class)) {
            return new ConcreteFragmentedSet(readVendor(request.getQueryObject()), Vendor.class);
        } else {
            throw new IllegalStateException("No handler found in " + this.getClass() + " to handle the read request of "
                    + request.getQueryType());
        }
    }

    @Override
    public void dispatchUpdateRequest(final BiRequest biRequest) {
        final Object oldValue = biRequest.getPayload();
        final Object newValue = biRequest.getSecondPayload();
        if (oldValue instanceof AspectRatio) {
            updateEntry((AspectRatio) oldValue, (AspectRatio) newValue);
        } else if (oldValue instanceof Case) {
            updateEntry((Case) oldValue, (Case) newValue);
        } else if (oldValue instanceof Color) {
            updateEntry((Color) oldValue, (Color) newValue);
        } else if (oldValue instanceof DeviceCategory) {
            updateEntry((DeviceCategory) oldValue, (DeviceCategory) newValue);
        } else if (oldValue instanceof DeviceWorkProgress) {
            updateEntry((DeviceWorkProgress) oldValue, (DeviceWorkProgress) newValue);
        } else if (oldValue instanceof DigitalInformationUnit) {
            updateEntry((DigitalInformationUnit) oldValue, (DigitalInformationUnit) newValue);
        } else if (oldValue instanceof GenericDevice) {
            updateEntry((GenericDevice) oldValue, (GenericDevice) newValue);
        } else if (oldValue instanceof HardDiskDrive) {
            updateEntry((HardDiskDrive) oldValue, (HardDiskDrive) newValue);
        } else if (oldValue instanceof LegalCategoryCompound) {
            updateEntry((LegalCategoryCompound) oldValue, (LegalCategoryCompound) newValue);
        } else if (oldValue instanceof Printer) {
            updateEntry((Printer) oldValue, (Printer) newValue);
        } else if (oldValue instanceof PrinterCategory) {
            updateEntry((PrinterCategory) oldValue, (PrinterCategory) newValue);
        } else if (oldValue instanceof RandomAccessMemory) {
            updateEntry((RandomAccessMemory) oldValue, (RandomAccessMemory) newValue);
        } else if (oldValue instanceof RefinedDevice) {
            updateEntry((RefinedDevice) oldValue, (RefinedDevice) newValue);
        } else if (oldValue instanceof RefinedDeviceCompound) {
            updateEntry((RefinedDeviceCompound) oldValue, (RefinedDeviceCompound) newValue);
        } else if (oldValue instanceof RefinedDeviceCompoundWithGeneric) {
            updateEntry((RefinedDeviceCompoundWithGeneric) oldValue, (RefinedDeviceCompoundWithGeneric) newValue);
        } else if (oldValue instanceof Screen) {
            updateEntry((Screen) oldValue, (Screen) newValue);
        } else if (oldValue instanceof ScreenCategory) {
            updateEntry((ScreenCategory) oldValue, (ScreenCategory) newValue);
        } else if (oldValue instanceof ScreenResolution) {
            updateEntry((ScreenResolution) oldValue, (ScreenResolution) newValue);
        } else if (oldValue instanceof Vendor) {
            updateEntry((Vendor) oldValue, (Vendor) newValue);
        } else {
            throw new IllegalStateException(
                    "No handler available for an update request containing " + biRequest.getDesiredHandler());
        }
    }

    @Override
    public void dispatchDeleteRequest(final SingleRequest request) {
        final Object payload = request.getPayload();
        if (payload instanceof AspectRatio) {
            deleteEntry((AspectRatio) payload);
        } else if (payload instanceof Case) {
            deleteEntry((Case) payload);
        } else if (payload instanceof Color) {
            deleteEntry((Color) payload);
        } else if (payload instanceof DeviceCategory) {
            deleteEntry((DeviceCategory) payload);
        } else if (payload instanceof DeviceWorkProgress) {
            deleteEntry((DeviceWorkProgress) payload);
        } else if (payload instanceof DigitalInformationUnit) {
            deleteEntry((DigitalInformationUnit) payload);
        } else if (payload instanceof GenericDevice) {
            deleteEntry((GenericDevice) payload);
        } else if (payload instanceof HardDiskDrive) {
            deleteEntry((HardDiskDrive) payload);
        } else if (payload instanceof LegalCategoryCompound) {
            deleteEntry((LegalCategoryCompound) payload);
        } else if (payload instanceof Printer) {
            deleteEntry((Printer) payload);
        } else if (payload instanceof PrinterCategory) {
            deleteEntry((PrinterCategory) payload);
        } else if (payload instanceof RandomAccessMemory) {
            deleteEntry((RandomAccessMemory) payload);
        } else if (payload instanceof RefinedDevice) {
            deleteEntry((RefinedDevice) payload);
        } else if (payload instanceof RefinedDeviceCompound) {
            deleteEntry((RefinedDeviceCompound) payload);
        } else if (payload instanceof RefinedDeviceCompoundWithGeneric) {
            deleteEntry((RefinedDeviceCompoundWithGeneric) payload);
        } else if (payload instanceof Screen) {
            deleteEntry((Screen) payload);
        } else if (payload instanceof ScreenCategory) {
            deleteEntry((ScreenCategory) payload);
        } else if (payload instanceof ScreenResolution) {
            deleteEntry((ScreenResolution) payload);
        } else if (payload instanceof Vendor) {
            deleteEntry((Vendor) payload);
        } else {
            throw new IllegalStateException(
                    "No handler available for a delete request containing " + request.getDesiredHandler());
        }
    }

    @Override
    public void createEntry(final AspectRatio ratio) {
        try {
            this.getContext()
                .insertInto(Tables.ASPECTRATIOS, Tables.ASPECTRATIOS.RATIOFACTOR) 
                .values(ratio.getScreenRatio())
                .execute();
        } catch (DataAccessException dax) {
            manageDataAccessException(AspectRatio.class, dax);
        }
    }

    @Override
    public void createEntry(final Case caseModel) {
        try {
            this.getContext()
                .insertInto(Tables.CASEMODELS, Tables.CASEMODELS.ID, Tables.CASEMODELS.COLOR) 
                .values(UInteger.valueOf(assertGenericDeviceIdentifierValidity(caseModel.getGenericDevice())), 
                        UByte.valueOf(discoverColorId(caseModel.getColor())
                                .orElseThrow(() -> new IllegalArgumentException("Case"))))
                .execute();
        }  catch (DataAccessException dax) {
            manageDataAccessException(Case.class, dax);
        } catch (IllegalArgumentException iae) {
            manageMissingReferenceException(Case.class, iae.getMessage());
        }
    }

    @Override
    public void createEntry(final Color color) {
        try {
            this.getContext()
                .insertInto(Tables.COLORS, Tables.COLORS.HEXCODE) 
                .values(color.getColor())
                .execute();
        }  catch (DataAccessException dax) {
            manageDataAccessException(Color.class, dax);
        }
    }

    @Override
    public void createEntry(final DeviceCategory category) {
        try {
            this.getContext()
                .insertInto(Tables.TRASHWAREDEVICECATEGORIES, Tables.TRASHWAREDEVICECATEGORIES.ACRONYM, 
                            Tables.TRASHWAREDEVICECATEGORIES.NAME, 
                            Tables.TRASHWAREDEVICECATEGORIES.ALLOWSMULTIPLECOMPONUND) 
                .values(category.getAcronym(), 
                        category.getName(), 
                        translateBooleanToByte(category.isMultipleCompoundAllowed()))
                .execute();
        }  catch (DataAccessException dax) {
            manageDataAccessException(DeviceCategory.class, dax);
        }
    }

    @Override
    public void createEntry(final DeviceWorkProgress progress) {
        try {
            this.getContext()
                .insertInto(Tables.DEVICESTATES, Tables.DEVICESTATES.NAME, Tables.DEVICESTATES.DESCRIPTION) 
                .values(progress.getName(), 
                        progress.getDescription().orElse(null))
                .execute();
        } catch (DataAccessException dax) {
            manageDataAccessException(DeviceWorkProgress.class, dax);
        }
    }

    @Override
    public void createEntry(final DigitalInformationUnit unit) {
        try {
            this.getContext()
                .insertInto(Tables.DIGITALINFORMATIONUNITS, Tables.DIGITALINFORMATIONUNITS.NAME)
                .values(unit.getName())
                .execute();
        } catch (DataAccessException dax) {
            manageDataAccessException(DigitalInformationUnit.class, dax);
        }
    }

    @Override
    public void createEntry(final GenericDevice device) {
        try {
            this.getContext()
                .insertInto(Tables.DEVICEMODELS, Tables.DEVICEMODELS.VENDOR, Tables.DEVICEMODELS.VENDORMODELNUMBER, 
                            Tables.DEVICEMODELS.CATEGORY, Tables.DEVICEMODELS.DESCRIPTION, 
                            Tables.DEVICEMODELS.DEVICESWITHOUTID)
                .values(device.getVendor().isPresent() 
                            ? UShort.valueOf(discoverVendorId(device.getVendor().get())
                                    .orElseThrow(() -> new IllegalArgumentException(VENDOR))) 
                            : null,
                        device.getVendorModelIdentifier().orElse(null),
                        UByte.valueOf(discoverDeviceCategoryId(device.getDeviceCategory())
                                .orElseThrow(() -> new IllegalArgumentException(DEVICE_CATEGORY))),
                        device.getDeviceDescription().orElse(null),
                        UShort.valueOf(device.getNumberOfAvailableDevices()))
                .execute();
        } catch (DataAccessException dax) {
                manageDataAccessException(GenericDevice.class, dax);
        } catch (IllegalArgumentException iae) {
            manageMissingReferenceException(GenericDevice.class, iae.getMessage());
        }
    }

    @Override
    public void createEntry(final HardDiskDrive hdd) {
        try {
            this.getContext()
                .insertInto(Tables.HDDMODELS, Tables.HDDMODELS.ID, Tables.HDDMODELS.CAPACITY, 
                            Tables.HDDMODELS.CAPACITYUNIT) 
                .values(UInteger.valueOf(assertGenericDeviceIdentifierValidity(hdd.getGenericDevice())), 
                        UShort.valueOf(hdd.getCapacity()),
                        UByte.valueOf(discoverDigitalInformationUnitId(hdd.getCapacityUnit())
                                .orElseThrow(() -> new IllegalArgumentException("HardDiskDrive"))))
                .execute();
        } catch (DataAccessException dax) {
            manageDataAccessException(HardDiskDrive.class, dax);
        } catch (IllegalArgumentException iae) {
            manageMissingReferenceException(HardDiskDrive.class, iae.getMessage());
        }
    }

    @Override
    public void createEntry(final LegalCategoryCompound compound) {
        try {
            this.getContext()
                .insertInto(Tables.DEVICECATEGORYALLOWEDCOMPONENTS,
                            Tables.DEVICECATEGORYALLOWEDCOMPONENTS.COMPOUNDCATEGORY, 
                            Tables.DEVICECATEGORYALLOWEDCOMPONENTS.COMPONENTCATEGORY)
                .values(UByte.valueOf(discoverDeviceCategoryId(compound.getCompound())
                            .orElseThrow(() -> new IllegalArgumentException(DEVICE_CATEGORY))),
                        UByte.valueOf(discoverDeviceCategoryId(compound.getComponent())
                            .orElseThrow(() -> new IllegalArgumentException(DEVICE_CATEGORY))))
                .execute();
        } catch (DataAccessException dax) {
            manageDataAccessException(LegalCategoryCompound.class, dax);
        } catch (IllegalArgumentException iae) {
            manageMissingReferenceException(LegalCategoryCompound.class, iae.getMessage());
        }
    }

    @Override
    public void createEntry(final Printer printer) {
        try {
            this.getContext()
                .insertInto(Tables.PRINTERMODELS, Tables.PRINTERMODELS.DEVICEMODEL, Tables.PRINTERMODELS.TECNOLOGY, 
                            Tables.PRINTERMODELS.RESOLUTION)
                .values(UInteger.valueOf(assertGenericDeviceIdentifierValidity(printer.getGenericDevice())), 
                        UByte.valueOf(discoverPrinterCategoryId(printer.getPrinterCategory())
                            .orElseThrow(() -> new IllegalArgumentException(PRINTER_CATEGORY))),
                        printer.getResolution().isPresent()
                            ? printer.getResolution().get().shortValue()
                            : null)
                .execute();
        } catch (DataAccessException dax) {
            manageDataAccessException(Printer.class, dax);
        } catch (IllegalArgumentException iae) {
            manageMissingReferenceException(Printer.class, iae.getMessage());
        }
    }

    @Override
    public void createEntry(final PrinterCategory category) {
        try {
            this.getContext()
                .insertInto(Tables.PRINTERTECNOLOGIES, Tables.PRINTERTECNOLOGIES.NAME)
                .values(category.getName())
                .execute();
        } catch (DataAccessException dax) {
            manageDataAccessException(PrinterCategory.class, dax);
        }
    }

    @Override
    public void createEntry(final RandomAccessMemory ram) {
        try {
            this.getContext()
                .insertInto(Tables.RAMMODELS, Tables.RAMMODELS.DEVICEMODEL, Tables.RAMMODELS.CAPACITY, 
                            Tables.RAMMODELS.CAPACITYUNIT)
                .values(UInteger.valueOf(assertGenericDeviceIdentifierValidity(ram.getGenericDevice())), 
                        UShort.valueOf(ram.getCapacity()),
                        UByte.valueOf(discoverDigitalInformationUnitId(ram.getCapacityUnit())
                                .orElseThrow(() -> new IllegalArgumentException(DIGITAL_INFORMATION_UNIT))))
                .execute();
        } catch (DataAccessException dax) {
            manageDataAccessException(RandomAccessMemory.class, dax);
        } catch (IllegalArgumentException iae) {
            manageMissingReferenceException(RandomAccessMemory.class, iae.getMessage());
        }
    }

    @Override
    public void createEntry(final RefinedDevice device) {
        try {
            this.getContext()
                .insertInto(Tables.DEVICESWITHID, Tables.DEVICESWITHID.CATEGORYDEVICEID, 
                    Tables.DEVICESWITHID.DEVICEMODEL, Tables.DEVICESWITHID.CATEGORY, 
                    Tables.DEVICESWITHID.AVAILABLE, Tables.DEVICESWITHID.CURRENTSTATE,
                    Tables.DEVICESWITHID.LASTUPDATEWORKER, Tables.DEVICESWITHID.ANNOTATIONS,
                    Tables.DEVICESWITHID.LASTUPDATEDATE)
                .values(UShort.valueOf(device.getCategoryDeviceId()), 
                    UInteger.valueOf(assertGenericDeviceIdentifierValidity(device.getGenericDevice())),
                    UByte.valueOf(discoverDeviceCategoryId(device.getDeviceCategory())
                        .orElseThrow(() -> new IllegalArgumentException(DEVICE_CATEGORY))),
                    translateBooleanToByte(device.isAvailable()),
                    UByte.valueOf(discoverWorkProgressId(device.getWorkProgress())
                        .orElseThrow(() -> new IllegalArgumentException(DEVICE_WORK_PROGRESS))),
                    UInteger.valueOf(
                        discoverTrashwareWorkerById(
                            device.getLastChangeCommitter().getPerson().getNumericIdentifier()
                                .orElseThrow(() -> new IllegalStateException(ErrorString.BUG_REPORTING)))
                            .orElseThrow(() -> new IllegalArgumentException("TrashwareWorker"))
                            .getPerson().getNumericIdentifier()
                                .orElseThrow(() -> new IllegalStateException(ErrorString.BUG_REPORTING))), 
                        device.getAnnotations().orElse(null),
                        new Timestamp(device.getLastChangeDate().getTime()))
                .execute();
        } catch (DataAccessException dax) {
            manageDataAccessException(RefinedDevice.class, dax);
        } catch (IllegalArgumentException iae) {
            iae.printStackTrace();
            manageMissingReferenceException(RefinedDevice.class, iae.getMessage());
        }
    }

    @Override
    public void createEntry(final RefinedDeviceCompound compound) {
        try {
            this.getContext()
                .insertInto(Tables.DEVICESWITHIDCOMPONENTOFDEVICEWITHID, 
                            Tables.DEVICESWITHIDCOMPONENTOFDEVICEWITHID.COMPOUND, 
                            Tables.DEVICESWITHIDCOMPONENTOFDEVICEWITHID.COMPONENT)
                .values(UInteger.valueOf(discoverRefinedDeviceId(compound.getCompound())
                            .orElseThrow(() -> new IllegalArgumentException(REFINED_DEVICE))), 
                        UInteger.valueOf(discoverRefinedDeviceId(compound.getComponent())
                            .orElseThrow(() -> new IllegalArgumentException(REFINED_DEVICE))))
                .execute();
        } catch (DataAccessException dax) {
            manageDataAccessException(RefinedDeviceCompound.class, dax);
        } catch (IllegalArgumentException iae) {
            manageMissingReferenceException(RefinedDeviceCompound.class, iae.getMessage());
        }
    }

    @Override
    public void createEntry(final RefinedDeviceCompoundWithGeneric compound) {
        try {
            this.getContext()
                .insertInto(Tables.DEVICESWITHOUTIDCOMPONENTOFDEVICEWITHID, 
                            Tables.DEVICESWITHOUTIDCOMPONENTOFDEVICEWITHID.COMPOUND, 
                            Tables.DEVICESWITHOUTIDCOMPONENTOFDEVICEWITHID.COMPONENT, 
                            Tables.DEVICESWITHOUTIDCOMPONENTOFDEVICEWITHID.QUANTITY)
                .values(UInteger.valueOf(discoverRefinedDeviceId(compound.getCompound())
                            .orElseThrow(() -> new IllegalArgumentException(REFINED_DEVICE))), 
                        UInteger.valueOf(assertGenericDeviceIdentifierValidity(compound.getComponent())),
                        UShort.valueOf(compound.getQuantity()))
                .execute();
        } catch (DataAccessException dax) {
            manageDataAccessException(RefinedDeviceCompoundWithGeneric.class, dax);
        } catch (IllegalArgumentException iae) {
            manageMissingReferenceException(RefinedDeviceCompoundWithGeneric.class, iae.getMessage());
        }
    }

    @Override
    public void createEntry(final Screen screen) {
        try {
            this.getContext()
                .insertInto(Tables.SCREENMODELS, Tables.SCREENMODELS.DEVICEMODEL, Tables.SCREENMODELS.MAXIMUMRESOLUTION,
                            Tables.SCREENMODELS.TECNOLOGY, Tables.SCREENMODELS.WITHFRAME, Tables.SCREENMODELS.COLOR,
                            Tables.SCREENMODELS.HASVGASOCKET, Tables.SCREENMODELS.HASDVISOCKET,
                            Tables.SCREENMODELS.HASAUDIOSPEAKERS)
                .values(UInteger.valueOf(assertGenericDeviceIdentifierValidity(screen.getGenericDevice())),
                        UByte.valueOf(assertScreenResolutionIdentifierValidity(screen.getMaximumResolution())),
                        UByte.valueOf(discoverScreenCategoryId(screen.getCategory())
                                .orElseThrow(() -> new IllegalArgumentException(SCREEN_CATEGORY))),
                        translateBooleanToByte(screen.isWithFrame()),
                        screen.getColor().isPresent() 
                            ? UByte.valueOf(discoverColorId(screen.getColor().get())
                                    .orElseThrow(() -> new IllegalArgumentException(COLOR)))
                            : null,
                        translateBooleanToByte(screen.isWithVgaSocket()),
                        translateBooleanToByte(screen.isWithDviSocket()),
                        translateBooleanToByte(screen.isWithAudioSpeakers()))
                .execute();
        } catch (DataAccessException dax) {
            manageDataAccessException(Screen.class, dax);
        } catch (IllegalArgumentException iae) {
            manageMissingReferenceException(Screen.class, iae.getMessage());
        }
    }

    @Override
    public void createEntry(final ScreenCategory category) {
        try {
            this.getContext()
                .insertInto(Tables.SCREENTECNOLOGIES, Tables.SCREENTECNOLOGIES.NAME)
                .values(category.getName())
                .execute();
        } catch (DataAccessException dax) {
            manageDataAccessException(ScreenCategory.class, dax);
        }
    }

    @Override
    public void createEntry(final ScreenResolution resolution) {
        try {
            this.getContext()
                .insertInto(Tables.SCREENRESOLUTIONS, Tables.SCREENRESOLUTIONS.WIDTH, Tables.SCREENRESOLUTIONS.HEIGHT, 
                            Tables.SCREENRESOLUTIONS.ASPECTRATIO)
                .values(UShort.valueOf(resolution.getWidth()),
                        UShort.valueOf(resolution.getHeight()), 
                        UByte.valueOf(discoverAspectRatioId(resolution.getAspectRatio())
                                .orElseThrow(() -> new IllegalArgumentException(ASPECT_RATIO))))
                .execute();
        } catch (DataAccessException dax) {
            manageDataAccessException(ScreenResolution.class, dax);
        } catch (IllegalArgumentException iae) {
            manageMissingReferenceException(ScreenResolution.class, iae.getMessage());
        }
    }

    @Override
    public void createEntry(final Vendor vendor) {
        try {
            this.getContext()
                .insertInto(Tables.VENDORS, Tables.VENDORS.NAME)
                .values(vendor.getName())
                .execute();
        } catch (DataAccessException dax) {
            manageDataAccessException(Vendor.class, dax);
        }
    }

    @Override
    public Set<AspectRatio> readAspectRatio(final QueryObject filter) {
        return executeSqlRetrieveStatement(filter)
                            .stream()
                            .map(record -> record.getValue(Tables.ASPECTRATIOS.RATIOFACTOR))
                            .map(string -> string.split(":"))
                            .map(array -> new AspectRatioImpl(Integer.parseInt(array[0]), Integer.parseInt(array[1])))
                            .collect(Collectors.toSet());
    }

    @Override
    public Set<Case> readCase(final QueryObject filter) {
        return executeSqlRetrieveStatement(filter)
                .stream()
                .map(record -> new CaseImpl(
                        discoverGenericDeviceById(record.getValue(Tables.CASEMODELS.ID).intValue()).get(),
                        discoverColorById(((Byte) record.get(1)).intValue())))
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Color> readColor(final QueryObject filter) {
        return executeSqlRetrieveStatement(filter)
                .stream()
                .map(record -> new ColorImpl(record.getValue(Tables.COLORS.HEXCODE)))
                .collect(Collectors.toSet());
    }

    @Override
    public Set<DeviceCategory> readDeviceCategory(final QueryObject filter) {
        return executeSqlRetrieveStatement(filter)
                .stream()
                .map(record -> new DeviceCategoryImpl(
                        record.getValue(Tables.TRASHWAREDEVICECATEGORIES.NAME),
                        record.getValue(Tables.TRASHWAREDEVICECATEGORIES.ACRONYM),
                        translateByteToBoolean(
                                record.getValue(Tables.TRASHWAREDEVICECATEGORIES.ALLOWSMULTIPLECOMPONUND))))
                .collect(Collectors.toSet());
    }

    @Override
    public Set<DeviceWorkProgress> readDeviceWorkProgress(final QueryObject filter) {
        return executeSqlRetrieveStatement(filter)
                .stream()
                .map(record -> new DeviceWorkProgressImpl(
                        record.getValue(Tables.DEVICESTATES.NAME),
                        record.getValue(Tables.DEVICESTATES.DESCRIPTION)))
                .collect(Collectors.toSet());
    }

    @Override
    public Set<DigitalInformationUnit> readDigitalInformationUnit(final QueryObject filter) {
        return executeSqlRetrieveStatement(filter)
                .stream()
                .map(record -> new DigitalInformationUnitImpl(
                        record.getValue(Tables.DIGITALINFORMATIONUNITS.NAME)))
                .collect(Collectors.toSet());
    }

    @Override
    public Set<GenericDevice> readGenericDevice(final QueryObject filter) {
        final Set<GenericDevice> resultSet = new HashSet<>();
        for (final Record r : executeSqlRetrieveStatement(filter)) {
            final GenericDeviceImpl.Builder builder = new GenericDeviceImpl.Builder();
            if (Objects.nonNull(r.getValue(Tables.DEVICEMODELS.VENDOR))) {
                builder.vendor(discoverVendorById(r.getValue(Tables.DEVICEMODELS.VENDOR).intValue()));
            }
            builder.identifier(r.getValue(Tables.DEVICEMODELS.ID).intValue());
            builder.vendorModelIdentifier(r.getValue(Tables.DEVICEMODELS.VENDORMODELNUMBER));
            builder.deviceCategory(discoverDeviceCategoryById(((Byte) r.get(3)).intValue()));
            builder.description(r.getValue(Tables.DEVICEMODELS.DESCRIPTION));
            builder.available(r.getValue(Tables.DEVICEMODELS.DEVICESWITHOUTID).intValue());
            resultSet.add(builder.build());
        }
        return resultSet;
    }

    @Override
    public Set<HardDiskDrive> readHardDiskDrive(final QueryObject filter) {
        return executeSqlRetrieveStatement(filter)
                .stream()
                .map(record -> new HardDiskDriveImpl(
                        discoverGenericDeviceById(record.getValue(Tables.HDDMODELS.ID).intValue()).get(),
                        record.getValue(Tables.HDDMODELS.CAPACITY).intValue(),
                        discoverDigitalInformationUnitById(((Byte) record.get(2)).intValue())))
                .collect(Collectors.toSet());
    }

    @Override
    public Set<LegalCategoryCompound> readLegalCategoryCompound(final QueryObject filter) {
        return executeSqlRetrieveStatement(filter)
                .stream()
                .map(record -> new LegalCategoryCompoundImpl(
                        discoverDeviceCategoryById(((Byte) record.get(1)).intValue()),
                        discoverDeviceCategoryById(((Byte) record.get(2)).intValue())))
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Printer> readPrinter(final QueryObject filter) {
        return executeSqlRetrieveStatement(filter)
                .stream()
                .map(record -> new PrinterImpl(
                      discoverGenericDeviceById(record.getValue(Tables.PRINTERMODELS.DEVICEMODEL).intValue()).get(),
                      discoverPrinterCategoryById(((Byte) record.get(1)).intValue()),
                      record.getValue(Tables.PRINTERMODELS.RESOLUTION).intValue()))
                .collect(Collectors.toSet());
    }

    @Override
    public Set<PrinterCategory> readPrinterCategory(final QueryObject filter) {
        return executeSqlRetrieveStatement(filter)
                .stream()
                .map(record -> new PrinterCategoryImpl(record.getValue(Tables.PRINTERTECNOLOGIES.NAME)))
                .collect(Collectors.toSet());
    }

    @Override
    public Set<RandomAccessMemory> readRandomAccessMemory(final QueryObject filter) {
        return executeSqlRetrieveStatement(filter)
                .stream()
                .map(record -> new RandomAccessMemoryImpl(
                    discoverGenericDeviceById(record.getValue(Tables.RAMMODELS.DEVICEMODEL).intValue()).get(),
                    record.getValue(Tables.RAMMODELS.CAPACITY).intValue(),
                    discoverDigitalInformationUnitById(((Byte) record.get(2)).intValue())))
                .collect(Collectors.toSet());
    }

    @Override
    public Set<RefinedDevice> readRefinedDevice(final QueryObject filter) {
        final Set<RefinedDevice> resultSet = new HashSet<>();
        for (final Record r : executeSqlRetrieveStatement(filter)) {
            final RefinedDeviceImpl.Builder builder = new RefinedDeviceImpl.Builder();
            builder.identifier(r.getValue(Tables.DEVICESWITHID.ID).intValue());
            builder.annotations(r.getValue(Tables.DEVICESWITHID.ANNOTATIONS));
            builder.available(translateByteToBoolean(r.getValue(Tables.DEVICESWITHID.AVAILABLE)));
            builder.categoryDeviceId(r.getValue(Tables.DEVICESWITHID.CATEGORYDEVICEID).intValue());
            builder.deviceCategory(discoverDeviceCategoryById(((Byte) r.get(3)).intValue()));
            builder.lastCommitter(
                    discoverTrashwareWorkerById(r.getValue(Tables.DEVICESWITHID.LASTUPDATEWORKER).intValue()).get());
            builder.lastUpdate(new Date(r.getValue(Tables.DEVICESWITHID.LASTUPDATEDATE).getTime()));
            builder.progress(discoverWorkProgressById(((Byte) r.get(FIVE)).intValue()));
            builder.refining(discoverGenericDeviceById(r.getValue(Tables.DEVICESWITHID.DEVICEMODEL).intValue()).get());

            resultSet.add(builder.build());
        }
        return resultSet;
    }

    @Override
    public Set<RefinedDeviceCompound> readRefinedDeviceCompound(final QueryObject filter) {
        return executeSqlRetrieveStatement(filter)
                .stream()
                .map(record -> new RefinedDeviceCompoundImpl(
                        discoverRefinedDeviceById(
                            record.getValue(Tables.DEVICESWITHIDCOMPONENTOFDEVICEWITHID.COMPOUND).intValue()).get(),
                        discoverRefinedDeviceById(
                            record.getValue(Tables.DEVICESWITHIDCOMPONENTOFDEVICEWITHID.COMPONENT).intValue()).get()))
                .collect(Collectors.toSet());
    }

    @Override
    public Set<RefinedDeviceCompoundWithGeneric> readRefinedDeviceCompoundWithGeneric(final QueryObject filter) {
        return executeSqlRetrieveStatement(filter)
                .stream()
                .map(record -> new RefinedDeviceCompoundWithGenericImpl(
                        discoverRefinedDeviceById(
                            record.getValue(Tables.DEVICESWITHOUTIDCOMPONENTOFDEVICEWITHID.COMPOUND).intValue()).get(),
                        discoverGenericDeviceById(
                            record.getValue(Tables.DEVICESWITHOUTIDCOMPONENTOFDEVICEWITHID.COMPONENT).intValue()).get(),
                        record.getValue(Tables.DEVICESWITHOUTIDCOMPONENTOFDEVICEWITHID.QUANTITY).intValue()))
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Screen> readScreen(final QueryObject filter) {
        final Set<Screen> resultSet = new HashSet<>();
        for (final Record r : executeSqlRetrieveStatement(filter)) {
            final ScreenImpl.Builder builder = new ScreenImpl.Builder();
            builder.device(discoverGenericDeviceById(r.getValue(Tables.SCREENMODELS.DEVICEMODEL).intValue()).get());
            builder.resolution(discoverScreenResolutionById(((Byte) r.get(1)).intValue()).get());
            builder.category(discoverScreenCategoryById(((Byte) r.get(2)).intValue()));
            builder.hasFrame(translateByteToBoolean(r.getValue(Tables.SCREENMODELS.WITHFRAME)));
            if (Objects.nonNull((Byte) r.get(4))) {
                builder.color(discoverColorById(((Byte) r.get(4)).intValue()));
            }
            builder.hasVgaSocket(translateByteToBoolean(r.getValue(Tables.SCREENMODELS.HASVGASOCKET)));
            builder.hasDviSocket(translateByteToBoolean(r.getValue(Tables.SCREENMODELS.HASDVISOCKET)));
            builder.hasAudioSpeakers(translateByteToBoolean(r.getValue(Tables.SCREENMODELS.HASAUDIOSPEAKERS)));
            resultSet.add(builder.build());
        }
        return resultSet;
    }

    @Override
    public Set<ScreenCategory> readScreenCategory(final QueryObject filter) {
        return executeSqlRetrieveStatement(filter)
                .stream()
                .map(record -> new ScreenCategoryImpl(record.getValue(Tables.SCREENTECNOLOGIES.NAME)))
                .collect(Collectors.toSet());
    }

    @Override
    public Set<ScreenResolution> readScreenResolution(final QueryObject filter) {
        final Set<ScreenResolution> resultSet = new HashSet<>();
        for (final Record r : executeSqlRetrieveStatement(filter)) {
            final ScreenResolutionImpl.Builder builder = new ScreenResolutionImpl.Builder();
            builder.identifier(((Byte) r.get(0)).intValue());
            builder.width(r.getValue(Tables.SCREENRESOLUTIONS.WIDTH).intValue());
            builder.height(r.getValue(Tables.SCREENRESOLUTIONS.HEIGHT).intValue());
            builder.aspectRatio(discoverAspectRatioById(((Byte) r.get(3)).intValue()));
            resultSet.add(builder.build());
        }
        return resultSet;
    }

    @Override
    public Set<Vendor> readVendor(final QueryObject filter) {
        return executeSqlRetrieveStatement(filter)
                .stream()
                .map(record -> new VendorImpl(record.getValue(Tables.VENDORS.NAME)))
                .collect(Collectors.toSet());
    }

    @Override
    public void updateEntry(final AspectRatio oldRatio, final  AspectRatio newRatio) {
        try {
            getContext().update(Tables.ASPECTRATIOS)
                .set(Tables.ASPECTRATIOS.RATIOFACTOR, newRatio.getScreenRatio())
                .where(Tables.ASPECTRATIOS.ID.eq(UByte.valueOf(
                    discoverAspectRatioId(oldRatio).orElseThrow(() -> new IllegalArgumentException(ASPECT_RATIO)))))
                .execute();
        } catch (DataAccessException dax) {
            manageDataAccessException(AspectRatio.class, dax);
        } catch (IllegalArgumentException iae) {
            manageMissingReferenceException(AspectRatio.class, iae.getMessage());
        }
    }

    @Override
    public void updateEntry(final Case oldCase, final  Case newCase) {
        try {
            getContext().update(Tables.CASEMODELS)
                .set(Tables.CASEMODELS.ID, 
                        newCase.getGenericDevice().getNumericIdentifier().isPresent()
                            ? UInteger.valueOf(assertGenericDeviceIdentifierValidity(newCase.getGenericDevice()))
                            : UInteger.valueOf(assertGenericDeviceIdentifierValidity(oldCase.getGenericDevice())))
                .set(Tables.CASEMODELS.COLOR, UByte.valueOf(discoverColorId(newCase.getColor())
                        .orElseThrow(() -> new IllegalArgumentException(COLOR))))
                .where(Tables.CASEMODELS.ID
                        .eq(UInteger.valueOf(assertGenericDeviceIdentifierValidity(oldCase.getGenericDevice()))))
                .execute();
        } catch (DataAccessException dax) {
            manageDataAccessException(Case.class, dax);
        } catch (IllegalArgumentException iae) {
            manageMissingReferenceException(Case.class, iae.getMessage());
        }
    }

    @Override
    public void updateEntry(final Color oldColor, final  Color newColor) {
        try {
            getContext().update(Tables.COLORS)
                .set(Tables.COLORS.HEXCODE, newColor.getColor())
                .where(Tables.COLORS.ID.eq(UByte.valueOf(
                    discoverColorId(oldColor).orElseThrow(() -> new IllegalArgumentException(COLOR)))))
                .execute();
        } catch (DataAccessException dax) {
            manageDataAccessException(Color.class, dax);
        } catch (IllegalArgumentException iae) {
            manageMissingReferenceException(Color.class, iae.getMessage());
        }
    }

    @Override
    public void updateEntry(final DeviceCategory oldCategory, final  DeviceCategory newCategory) {
        try {
            getContext().update(Tables.TRASHWAREDEVICECATEGORIES)
                .set(Tables.TRASHWAREDEVICECATEGORIES.ACRONYM, newCategory.getAcronym())
                .set(Tables.TRASHWAREDEVICECATEGORIES.NAME, newCategory.getName())
                .set(Tables.TRASHWAREDEVICECATEGORIES.ALLOWSMULTIPLECOMPONUND, 
                    translateBooleanToByte(newCategory.isMultipleCompoundAllowed()))
                .where(Tables.TRASHWAREDEVICECATEGORIES.ID.eq(UByte.valueOf(discoverDeviceCategoryId(oldCategory)
                    .orElseThrow(() -> new IllegalArgumentException(DEVICE_CATEGORY)))))
                .execute();
        } catch (DataAccessException dax) {
            manageDataAccessException(DeviceCategory.class, dax);
        } catch (IllegalArgumentException iae) {
            manageMissingReferenceException(DeviceCategory.class, iae.getMessage());
        }
    }

    @Override
    public void updateEntry(final DeviceWorkProgress oldProgress, final  DeviceWorkProgress newProgress) {
        try {
            getContext().update(Tables.DEVICESTATES)
                .set(Tables.DEVICESTATES.NAME, newProgress.getName())
                .set(Tables.DEVICESTATES.DESCRIPTION, newProgress.getDescription().orElse(null))
                .where(Tables.DEVICESTATES.ID.eq(UByte.valueOf(discoverWorkProgressId(oldProgress)
                    .orElseThrow(() -> new IllegalArgumentException(DEVICE_WORK_PROGRESS)))))
                .execute();
        } catch (DataAccessException dax) {
            manageDataAccessException(DeviceWorkProgress.class, dax);
        } catch (IllegalArgumentException iae) {
            manageMissingReferenceException(DeviceWorkProgress.class, iae.getMessage());
        }
    }

    @Override
    public void updateEntry(final DigitalInformationUnit oldUnit, final  DigitalInformationUnit newUnit) {
        try {
            getContext().update(Tables.DIGITALINFORMATIONUNITS)
                .set(Tables.DIGITALINFORMATIONUNITS.NAME, newUnit.getName())
                .where(Tables.DIGITALINFORMATIONUNITS.ID.eq(UByte.valueOf(discoverDigitalInformationUnitId(oldUnit)
                        .orElseThrow(() -> new IllegalArgumentException(DIGITAL_INFORMATION_UNIT)))))
                .execute();
            manageExternalDependencies(oldUnit, newUnit);
        } catch (DataAccessException dax) {
            manageDataAccessException(DigitalInformationUnit.class, dax);
        } catch (IllegalArgumentException iae) {
            manageMissingReferenceException(DigitalInformationUnit.class, iae.getMessage());
        }
    }

    @Override
    public void updateEntry(final GenericDevice oldDevice, final GenericDevice newDevice) {
        try {
            getContext().update(Tables.DEVICEMODELS)
                   .set(Tables.DEVICEMODELS.VENDOR,
                           newDevice.getVendor().isPresent() 
                               ? UShort.valueOf(discoverVendorId(newDevice.getVendor().get())
                                       .orElseThrow(() -> new IllegalArgumentException(VENDOR)))
                               : null)
                   .set(Tables.DEVICEMODELS.VENDORMODELNUMBER, newDevice.getVendorModelIdentifier().orElse(null))
                   .set(Tables.DEVICEMODELS.CATEGORY, 
                           UByte.valueOf(discoverDeviceCategoryId(newDevice.getDeviceCategory())
                                   .orElseThrow(() -> new IllegalArgumentException(DEVICE_CATEGORY))))
                   .set(Tables.DEVICEMODELS.DESCRIPTION, newDevice.getDeviceDescription().orElse(null))
                   .set(Tables.DEVICEMODELS.DEVICESWITHOUTID, UShort.valueOf(newDevice.getNumberOfAvailableDevices()))
                   .where(Tables.DEVICEMODELS.ID
                           .eq(UInteger.valueOf(assertGenericDeviceIdentifierValidity(oldDevice))))
                   .execute();
            manageExternalDependencies(oldDevice, newDevice);
        } catch (DataAccessException dax) {
            manageDataAccessException(GenericDevice.class, dax);
        } catch (IllegalArgumentException iae) {
            manageMissingReferenceException(GenericDevice.class, iae.getMessage());
        }
    }

    @Override
    public void updateEntry(final HardDiskDrive oldDrive, final  HardDiskDrive newDrive) {
        try {
            getContext().update(Tables.HDDMODELS)
                    .set(Tables.HDDMODELS.ID,
                            newDrive.getGenericDevice().getNumericIdentifier().isPresent()
                            ? UInteger.valueOf(assertGenericDeviceIdentifierValidity(newDrive.getGenericDevice()))
                            : UInteger.valueOf(assertGenericDeviceIdentifierValidity(oldDrive.getGenericDevice())))
                    .set(Tables.HDDMODELS.CAPACITY, UShort.valueOf(newDrive.getCapacity()))
                    .set(Tables.HDDMODELS.CAPACITYUNIT, 
                            UByte.valueOf(discoverDigitalInformationUnitId(newDrive.getCapacityUnit())
                                    .orElseThrow(() -> new IllegalArgumentException("DigitalCapacityUnit"))))
                    .where(Tables.HDDMODELS.ID
                            .eq(UInteger.valueOf(assertGenericDeviceIdentifierValidity(oldDrive.getGenericDevice()))))
                    .execute();
        } catch (DataAccessException dax) {
            manageDataAccessException(HardDiskDrive.class, dax);
        } catch (IllegalArgumentException iae) {
            manageMissingReferenceException(HardDiskDrive.class, iae.getMessage());
        }
    }

    @Override
    public void updateEntry(final LegalCategoryCompound oldCategoryCompound, 
                                        final  LegalCategoryCompound newCategoryCompound) {
        try {
            getContext().update(Tables.DEVICECATEGORYALLOWEDCOMPONENTS)
                    .set(Tables.DEVICECATEGORYALLOWEDCOMPONENTS.COMPOUNDCATEGORY, 
                            UByte.valueOf(discoverDeviceCategoryId(newCategoryCompound.getCompound())
                                    .orElseThrow(() -> new IllegalArgumentException(DEVICE_CATEGORY))))
                    .set(Tables.DEVICECATEGORYALLOWEDCOMPONENTS.COMPONENTCATEGORY, 
                            UByte.valueOf(discoverDeviceCategoryId(newCategoryCompound.getComponent())
                                    .orElseThrow(() -> new IllegalArgumentException(DEVICE_CATEGORY))))
                    .where(Tables.DEVICECATEGORYALLOWEDCOMPONENTS.ID
                            .eq(UByte.valueOf(discoverLegalCategoryCompoundId(oldCategoryCompound)
                                    .orElseThrow(() -> new IllegalArgumentException("LegalCategoryCompound")))))
                    .execute();
        } catch (DataAccessException dax) {
            manageDataAccessException(LegalCategoryCompound.class, dax);
        } catch (IllegalArgumentException iae) {
            manageMissingReferenceException(LegalCategoryCompound.class, iae.getMessage());
        }
    }

    @Override
    public void updateEntry(final Printer oldPrinter, final  Printer newPrinter) {
        try {
            getContext().update(Tables.PRINTERMODELS)
                .set(Tables.PRINTERMODELS.DEVICEMODEL, 
                        newPrinter.getGenericDevice().getNumericIdentifier().isPresent()
                        ? UInteger.valueOf(assertGenericDeviceIdentifierValidity(newPrinter.getGenericDevice()))
                        : UInteger.valueOf(assertGenericDeviceIdentifierValidity(oldPrinter.getGenericDevice())))
                .set(Tables.PRINTERMODELS.TECNOLOGY, 
                        UByte.valueOf(discoverPrinterCategoryId(newPrinter.getPrinterCategory())
                                .orElseThrow(() -> new IllegalArgumentException(PRINTER_CATEGORY))))
                .set(Tables.PRINTERMODELS.RESOLUTION, 
                        newPrinter.getResolution().isPresent() 
                            ? newPrinter.getResolution().get().shortValue()
                            : null)
                .where(Tables.PRINTERMODELS.DEVICEMODEL
                        .eq(UInteger.valueOf(assertGenericDeviceIdentifierValidity(oldPrinter.getGenericDevice()))))
                .execute();
        } catch (DataAccessException dax) {
            manageDataAccessException(Printer.class, dax);
        } catch (IllegalArgumentException iae) {
            manageMissingReferenceException(Printer.class, iae.getMessage());
        }
    }

    @Override
    public void updateEntry(final PrinterCategory oldCategory, final  PrinterCategory newCategory) {
        try {
            getContext().update(Tables.PRINTERTECNOLOGIES)
                .set(Tables.PRINTERTECNOLOGIES.NAME, newCategory.getName())
                .where(Tables.PRINTERTECNOLOGIES.ID.eq(UByte.valueOf(discoverPrinterCategoryId(oldCategory)
                    .orElseThrow(() -> new IllegalArgumentException(PRINTER_CATEGORY)))))
                .execute();
        } catch (DataAccessException dax) {
            manageDataAccessException(PrinterCategory.class, dax);
        } catch (IllegalArgumentException iae) {
            manageMissingReferenceException(PrinterCategory.class, iae.getMessage());
        }
    }

    @Override
    public void updateEntry(final RandomAccessMemory oldRam, final  RandomAccessMemory newRam) {
        try {
            getContext().update(Tables.RAMMODELS)
                .set(Tables.RAMMODELS.DEVICEMODEL, 
                        oldRam.getGenericDevice().getNumericIdentifier().isPresent()
                            ? UInteger.valueOf(assertGenericDeviceIdentifierValidity(newRam.getGenericDevice()))
                            : UInteger.valueOf(assertGenericDeviceIdentifierValidity(oldRam.getGenericDevice())))
                .set(Tables.RAMMODELS.CAPACITY, UShort.valueOf(newRam.getCapacity()))
                .set(Tables.RAMMODELS.CAPACITYUNIT, 
                        UByte.valueOf(discoverDigitalInformationUnitId(newRam.getCapacityUnit())
                                .orElseThrow(() -> new IllegalArgumentException(DIGITAL_INFORMATION_UNIT))))
                .where(Tables.RAMMODELS.DEVICEMODEL
                        .eq(UInteger.valueOf(assertGenericDeviceIdentifierValidity(oldRam.getGenericDevice()))))
                .execute();
        } catch (DataAccessException dax) {
            manageDataAccessException(RandomAccessMemory.class, dax);
        } catch (IllegalArgumentException iae) {
            manageMissingReferenceException(RandomAccessMemory.class, iae.getMessage());
        }
    }

    @Override
    public void updateEntry(final RefinedDevice oldDevice, final RefinedDevice newDevice) {
        try {
            getContext().update(Tables.DEVICESWITHID)
                   .set(Tables.DEVICESWITHID.CATEGORYDEVICEID, UShort.valueOf(newDevice.getCategoryDeviceId()))
                   .set(Tables.DEVICESWITHID.DEVICEMODEL, 
                       newDevice.getGenericDevice().getNumericIdentifier().isPresent()
                           ? UInteger.valueOf(assertGenericDeviceIdentifierValidity(newDevice.getGenericDevice()))
                           : UInteger.valueOf(assertGenericDeviceIdentifierValidity(oldDevice.getGenericDevice())))
                   .set(Tables.DEVICESWITHID.CATEGORY, 
                       UByte.valueOf(discoverDeviceCategoryId(newDevice.getDeviceCategory())
                           .orElseThrow(() -> new IllegalArgumentException(DEVICE_CATEGORY))))
                   .set(Tables.DEVICESWITHID.AVAILABLE, translateBooleanToByte(newDevice.isAvailable()))
                   .set(Tables.DEVICESWITHID.CURRENTSTATE, 
                       UByte.valueOf(discoverWorkProgressId(newDevice.getWorkProgress())
                           .orElseThrow(() -> new IllegalArgumentException(DEVICE_WORK_PROGRESS))))
                   .set(Tables.DEVICESWITHID.LASTUPDATEWORKER, 
                       UInteger.valueOf(
                           discoverTrashwareWorkerById(
                               newDevice.getLastChangeCommitter().getPerson().getNumericIdentifier()
                                   .orElseThrow(() -> new IllegalStateException(ErrorString.BUG_REPORTING)))
                               .orElseThrow(() -> new IllegalArgumentException("TrashwareWorker"))
                               .getPerson().getNumericIdentifier()
                                   .orElseThrow(() -> new IllegalStateException(ErrorString.BUG_REPORTING)))) 
                   .set(Tables.DEVICESWITHID.ANNOTATIONS, newDevice.getAnnotations().orElse(null))
                   .set(Tables.DEVICESWITHID.LASTUPDATEDATE, new Timestamp(newDevice.getLastChangeDate().getTime()))
                   .where(Tables.DEVICESWITHID.DEVICEMODEL
                        .eq(UInteger.valueOf(assertGenericDeviceIdentifierValidity(oldDevice.getGenericDevice())))
                   .and(Tables.DEVICESWITHID.CATEGORYDEVICEID
                        .eq(UShort.valueOf(oldDevice.getCategoryDeviceId()))))
                .execute();
        } catch (DataAccessException dax) {
            manageDataAccessException(RefinedDevice.class, dax);
        } catch (IllegalArgumentException iae) {
            manageMissingReferenceException(RefinedDevice.class, iae.getMessage());
        }
    }

    @Override
    public void updateEntry(final RefinedDeviceCompound oldCompound, final  RefinedDeviceCompound newCompound) {
        try {
            getContext().update(Tables.DEVICESWITHIDCOMPONENTOFDEVICEWITHID)
                    .set(Tables.DEVICESWITHIDCOMPONENTOFDEVICEWITHID.COMPOUND, 
                            UInteger.valueOf(discoverRefinedDeviceId(newCompound.getCompound())
                                    .orElseThrow(() -> new IllegalArgumentException(REFINED_DEVICE))))
                    .set(Tables.DEVICESWITHIDCOMPONENTOFDEVICEWITHID.COMPONENT, 
                            UInteger.valueOf(discoverRefinedDeviceId(newCompound.getComponent())
                                    .orElseThrow(() -> new IllegalArgumentException(REFINED_DEVICE))))
                    .where(Tables.DEVICESWITHIDCOMPONENTOFDEVICEWITHID.ID
                            .eq(UInteger.valueOf(discoverRefinedDeviceCompoundId(oldCompound)
                                    .orElseThrow(() -> new IllegalArgumentException("RefinedDeviceCompound")))))
                    .execute();
        } catch (DataAccessException dax) {
            manageDataAccessException(RefinedDeviceCompound.class, dax);
        } catch (IllegalArgumentException iae) {
            manageMissingReferenceException(RefinedDeviceCompound.class, iae.getMessage());
        }
    }

    @Override
    public void updateEntry(final RefinedDeviceCompoundWithGeneric oldCompound,
                                    final RefinedDeviceCompoundWithGeneric newCompound) {
        try {
            getContext().update(Tables.DEVICESWITHOUTIDCOMPONENTOFDEVICEWITHID)
                    .set(Tables.DEVICESWITHOUTIDCOMPONENTOFDEVICEWITHID.COMPOUND, 
                            UInteger.valueOf(discoverRefinedDeviceId(newCompound.getCompound())
                                    .orElseThrow(() -> new IllegalArgumentException(REFINED_DEVICE))))
                    .set(Tables.DEVICESWITHOUTIDCOMPONENTOFDEVICEWITHID.COMPONENT, 
                            UInteger.valueOf(assertGenericDeviceIdentifierValidity(newCompound.getComponent())))
                    .set(Tables.DEVICESWITHOUTIDCOMPONENTOFDEVICEWITHID.QUANTITY,
                            UShort.valueOf(newCompound.getQuantity()))
                    .where(Tables.DEVICESWITHOUTIDCOMPONENTOFDEVICEWITHID.COMPOUND
                            .eq(UInteger.valueOf(discoverRefinedDeviceId(oldCompound.getCompound())
                                    .orElseThrow(() -> new IllegalArgumentException(
                                            "RefinedDeviceCompoundWithGeneric"))))
                    .and(Tables.DEVICESWITHOUTIDCOMPONENTOFDEVICEWITHID.COMPONENT
                            .eq(UInteger.valueOf(assertGenericDeviceIdentifierValidity(newCompound.getComponent())))))
                    .execute();
        } catch (DataAccessException dax) {
            manageDataAccessException(RefinedDeviceCompoundWithGeneric.class, dax);
        } catch (IllegalArgumentException iae) {
            manageMissingReferenceException(RefinedDeviceCompoundWithGeneric.class, iae.getMessage());
        }
    }

    @Override
    public void updateEntry(final Screen oldScreen, final  Screen newScreen) {
        try {
            getContext().update(Tables.SCREENMODELS)
                   .set(Tables.SCREENMODELS.DEVICEMODEL, 
                           newScreen.getGenericDevice().getNumericIdentifier().isPresent() 
                               ? UInteger.valueOf(assertGenericDeviceIdentifierValidity(newScreen.getGenericDevice()))
                               : UInteger.valueOf(assertGenericDeviceIdentifierValidity(oldScreen.getGenericDevice())))
                   .set(Tables.SCREENMODELS.MAXIMUMRESOLUTION, 
                           newScreen.getMaximumResolution().getNumericIdentifier().isPresent() 
                               ? UByte.valueOf(
                                       assertScreenResolutionIdentifierValidity(newScreen.getMaximumResolution()))
                               : UByte.valueOf(
                                       assertScreenResolutionIdentifierValidity(oldScreen.getMaximumResolution())))
                   .set(Tables.SCREENMODELS.TECNOLOGY, 
                               UByte.valueOf(discoverScreenCategoryId(newScreen.getCategory())
                                       .orElseThrow(() -> new IllegalArgumentException(SCREEN_CATEGORY))))
                   .set(Tables.SCREENMODELS.WITHFRAME, translateBooleanToByte(newScreen.isWithFrame()))
                   .set(Tables.SCREENMODELS.COLOR, 
                           newScreen.getColor().isPresent()
                               ? UByte.valueOf(discoverColorId(newScreen.getColor().get())
                                       .orElseThrow(() -> new IllegalArgumentException(COLOR)))
                               : null)
                   .set(Tables.SCREENMODELS.HASVGASOCKET, translateBooleanToByte(newScreen.isWithVgaSocket()))
                   .set(Tables.SCREENMODELS.HASDVISOCKET, translateBooleanToByte(newScreen.isWithDviSocket()))
                   .set(Tables.SCREENMODELS.HASAUDIOSPEAKERS, translateBooleanToByte(newScreen.isWithAudioSpeakers()))
                   .where(Tables.SCREENMODELS.DEVICEMODEL
                           .eq(UInteger.valueOf(assertGenericDeviceIdentifierValidity(oldScreen.getGenericDevice()))))
                   .execute();
        } catch (DataAccessException dax) {
            manageDataAccessException(Screen.class, dax);
        } catch (IllegalArgumentException iae) {
            manageMissingReferenceException(Screen.class, iae.getMessage());
        }
    }

    @Override
    public void updateEntry(final ScreenCategory oldCategory, final  ScreenCategory newCategory) {
        try {
            getContext().update(Tables.SCREENTECNOLOGIES)
                   .set(Tables.SCREENTECNOLOGIES.NAME, newCategory.getName())
                   .where(Tables.SCREENTECNOLOGIES.ID.eq(UByte.valueOf(discoverScreenCategoryId(oldCategory)
                       .orElseThrow(() -> new IllegalArgumentException(SCREEN_CATEGORY)))))
                   .execute();
        } catch (DataAccessException dax) {
            manageDataAccessException(ScreenCategory.class, dax);
        } catch (IllegalArgumentException iae) {
            manageMissingReferenceException(ScreenCategory.class, iae.getMessage());
        }
    }

    @Override
    public void updateEntry(final ScreenResolution oldResolution, final  ScreenResolution newResolution) {
        try {
            getContext().update(Tables.SCREENRESOLUTIONS)
                   .set(Tables.SCREENRESOLUTIONS.WIDTH, UShort.valueOf(newResolution.getWidth()))
                   .set(Tables.SCREENRESOLUTIONS.HEIGHT, UShort.valueOf(newResolution.getHeight()))
                   .set(Tables.SCREENRESOLUTIONS.ASPECTRATIO, 
                        UByte.valueOf(discoverAspectRatioId(newResolution.getAspectRatio())
                                .orElseThrow(() -> new IllegalArgumentException(ASPECT_RATIO))))
                   .where(Tables.SCREENRESOLUTIONS.ID
                       .eq(UByte.valueOf(assertScreenResolutionIdentifierValidity(oldResolution))))
                   .execute();
        } catch (DataAccessException dax) {
            manageDataAccessException(ScreenResolution.class, dax);
        } catch (IllegalArgumentException iae) {
            manageMissingReferenceException(ScreenResolution.class, iae.getMessage());
        }
    }

    @Override
    public void updateEntry(final Vendor oldVendor, final  Vendor newVendor) {
        try {
            getContext().update(Tables.VENDORS)
                   .set(Tables.VENDORS.NAME, newVendor.getName())
                   .where(Tables.VENDORS.ID.eq(UShort.valueOf(discoverVendorId(oldVendor)
                       .orElseThrow(() -> new IllegalArgumentException(VENDOR)))))
                   .execute();
        } catch (DataAccessException dax) {
            manageDataAccessException(Vendor.class, dax);
        } catch (IllegalArgumentException iae) {
            manageMissingReferenceException(Vendor.class, iae.getMessage());
        }
    }

    @Override
    public void deleteEntry(final AspectRatio ratio) {
        try {
            getContext().delete(Tables.ASPECTRATIOS)
                   .where(Tables.ASPECTRATIOS.ID.eq(UByte.valueOf(
                       discoverAspectRatioId(ratio).orElseThrow(() -> new IllegalArgumentException(ASPECT_RATIO)))))
                   .execute();
        } catch (DataAccessException dax) {
            manageBoundedReferenceOnDeletionException(AspectRatio.class);
        } catch (IllegalArgumentException iae) {
            manageMissingReferenceException(AspectRatio.class, iae.getMessage());
        }
    }

    @Override
    public void deleteEntry(final Case caseModel) {
        try {
            getContext().delete(Tables.CASEMODELS)
                   .where(Tables.CASEMODELS.ID
                           .eq(UInteger.valueOf(assertGenericDeviceIdentifierValidity(caseModel.getGenericDevice()))))
                   .execute();
        } catch (DataAccessException dax) {
            manageBoundedReferenceOnDeletionException(Case.class);
        } catch (IllegalArgumentException iae) {
            manageMissingReferenceException(Case.class, iae.getMessage());
        }
    }

    @Override
    public void deleteEntry(final Color color) {
        try {
            getContext().delete(Tables.COLORS)
                   .where(Tables.COLORS.ID.eq(UByte.valueOf(
                       discoverColorId(color).orElseThrow(() -> new IllegalArgumentException(COLOR)))))
                   .execute();
        } catch (DataAccessException dax) {
            manageBoundedReferenceOnDeletionException(Color.class);
        } catch (IllegalArgumentException iae) {
            manageMissingReferenceException(Color.class, iae.getMessage());
        }
    }

    @Override
    public void deleteEntry(final DeviceCategory category) {
        try {
            getContext().delete(Tables.TRASHWAREDEVICECATEGORIES)
                   .where(Tables.TRASHWAREDEVICECATEGORIES.ID.eq(UByte.valueOf(discoverDeviceCategoryId(category)
                       .orElseThrow(() -> new IllegalArgumentException(DEVICE_CATEGORY)))))
                   .execute();
        } catch (DataAccessException dax) {
            manageBoundedReferenceOnDeletionException(DeviceCategory.class);
        } catch (IllegalArgumentException iae) {
            manageMissingReferenceException(DeviceCategory.class, iae.getMessage());
        }
    }

    @Override
    public void deleteEntry(final DeviceWorkProgress progress) {
        try {
            getContext().delete(Tables.DEVICESTATES)
                   .where(Tables.DEVICESTATES.ID.eq(UByte.valueOf(discoverWorkProgressId(progress)
                       .orElseThrow(() -> new IllegalArgumentException(DEVICE_WORK_PROGRESS)))))
                   .execute();
        } catch (DataAccessException dax) {
            manageBoundedReferenceOnDeletionException(DeviceWorkProgress.class);
        } catch (IllegalArgumentException iae) {
            manageMissingReferenceException(DeviceWorkProgress.class, iae.getMessage());
        }
    }

    @Override
    public void deleteEntry(final DigitalInformationUnit unit) {
        manageExternalDependencies(unit);
        try {
            getContext().delete(Tables.DIGITALINFORMATIONUNITS)
                   .where(Tables.DIGITALINFORMATIONUNITS.ID.eq(UByte.valueOf(discoverDigitalInformationUnitId(unit)
                       .orElseThrow(() -> new IllegalArgumentException(DIGITAL_INFORMATION_UNIT)))))
                   .execute();
        } catch (DataAccessException dax) {
            manageBoundedReferenceOnDeletionException(DigitalInformationUnit.class);
        } catch (IllegalArgumentException iae) {
            manageMissingReferenceException(DigitalInformationUnit.class, iae.getMessage());
        }
    }

    @Override
    public void deleteEntry(final GenericDevice device) {
        manageExternalDependencies(device);
        try {
            getContext().delete(Tables.DEVICEMODELS)
                   .where(Tables.DEVICEMODELS.ID
                       .eq(UInteger.valueOf(assertGenericDeviceIdentifierValidity(device))))
                   .execute();
        } catch (DataAccessException dax) {
            manageBoundedReferenceOnDeletionException(GenericDevice.class);
        } catch (IllegalArgumentException iae) {
            manageMissingReferenceException(GenericDevice.class, iae.getMessage());
        }
    }

    @Override
    public void deleteEntry(final HardDiskDrive drive) {
        try {
            getContext().delete(Tables.HDDMODELS)
                   .where(Tables.HDDMODELS.ID
                       .eq(UInteger.valueOf(assertGenericDeviceIdentifierValidity(drive.getGenericDevice()))))
                   .execute();
        } catch (DataAccessException dax) {
            manageBoundedReferenceOnDeletionException(HardDiskDrive.class);
        } catch (IllegalArgumentException iae) {
            manageMissingReferenceException(HardDiskDrive.class, iae.getMessage());
        }
    }

    @Override
    public void deleteEntry(final LegalCategoryCompound compound) {
        try {
            getContext().delete(Tables.DEVICECATEGORYALLOWEDCOMPONENTS)
                   .where(Tables.DEVICECATEGORYALLOWEDCOMPONENTS.ID
                           .eq(UByte.valueOf(discoverLegalCategoryCompoundId(compound)
                                   .orElseThrow(() -> new IllegalArgumentException("LegalCategoryCompound")))))
                   .execute();
        } catch (DataAccessException dax) {
            manageBoundedReferenceOnDeletionException(LegalCategoryCompound.class);
        } catch (IllegalArgumentException iae) {
            manageMissingReferenceException(LegalCategoryCompound.class, iae.getMessage());
        }
    }

    @Override
    public void deleteEntry(final Printer printer) {
        try {
            getContext().delete(Tables.PRINTERMODELS)
                   .where(Tables.PRINTERMODELS.DEVICEMODEL
                       .eq(UInteger.valueOf(assertGenericDeviceIdentifierValidity(printer.getGenericDevice()))))
                   .execute();
        } catch (DataAccessException dax) {
            manageBoundedReferenceOnDeletionException(Printer.class);
        } catch (IllegalArgumentException iae) {
            manageMissingReferenceException(Printer.class, iae.getMessage());
        }
    }

    @Override
    public void deleteEntry(final PrinterCategory category) {
        try {
            getContext().delete(Tables.PRINTERTECNOLOGIES)
                   .where(Tables.PRINTERTECNOLOGIES.ID.eq(UByte.valueOf(discoverPrinterCategoryId(category)
                       .orElseThrow(() -> new IllegalArgumentException(PRINTER_CATEGORY)))))
                   .execute();
        } catch (DataAccessException dax) {
            manageBoundedReferenceOnDeletionException(PrinterCategory.class);
        } catch (IllegalArgumentException iae) {
            manageMissingReferenceException(PrinterCategory.class, iae.getMessage());
        }
    }

    @Override
    public void deleteEntry(final RandomAccessMemory ram) {
        try {
            getContext().delete(Tables.RAMMODELS)
                   .where(Tables.RAMMODELS.DEVICEMODEL
                       .eq(UInteger.valueOf(assertGenericDeviceIdentifierValidity(ram.getGenericDevice()))))
                   .execute();
        } catch (DataAccessException dax) {
            manageBoundedReferenceOnDeletionException(RandomAccessMemory.class);
        } catch (IllegalArgumentException iae) {
            manageMissingReferenceException(RandomAccessMemory.class, iae.getMessage());
        }
    }

    @Override
    public void deleteEntry(final RefinedDevice device) {
        try {
            getContext().delete(Tables.DEVICESWITHID)
                    .where(Tables.DEVICESWITHID.DEVICEMODEL
                            .eq(UInteger.valueOf(assertGenericDeviceIdentifierValidity(device.getGenericDevice())))
                    .and(Tables.DEVICESWITHID.CATEGORYDEVICEID
                            .eq(UShort.valueOf(device.getCategoryDeviceId()))))
                   .execute();
        } catch (DataAccessException dax) {
            manageBoundedReferenceOnDeletionException(RefinedDevice.class);
        } catch (IllegalArgumentException iae) {
            manageMissingReferenceException(RefinedDevice.class, iae.getMessage());
        }
    }

    @Override
    public void deleteEntry(final RefinedDeviceCompound compound) {
        try {
            getContext().delete(Tables.DEVICESWITHIDCOMPONENTOFDEVICEWITHID)
                    .where(Tables.DEVICESWITHIDCOMPONENTOFDEVICEWITHID.ID
                        .eq(UInteger.valueOf(discoverRefinedDeviceCompoundId(compound)
                                .orElseThrow(() -> new IllegalArgumentException("RefinedDeviceCompound")))))
                   .execute();
        } catch (DataAccessException dax) {
            manageBoundedReferenceOnDeletionException(RefinedDeviceCompound.class);
        } catch (IllegalArgumentException iae) {
            manageMissingReferenceException(RefinedDeviceCompound.class, iae.getMessage());
        }
    }

    @Override
    public void deleteEntry(final RefinedDeviceCompoundWithGeneric compound) {
        try {
            getContext().delete(Tables.DEVICESWITHOUTIDCOMPONENTOFDEVICEWITHID)
                   .where(Tables.DEVICESWITHOUTIDCOMPONENTOFDEVICEWITHID.COMPOUND
                           .eq(UInteger.valueOf(discoverRefinedDeviceId(compound.getCompound())
                                   .orElseThrow(() -> new IllegalArgumentException(
                                           "RefinedDeviceCompoundWithGeneric"))))
                           .and(Tables.DEVICESWITHOUTIDCOMPONENTOFDEVICEWITHID.COMPONENT
                               .eq(UInteger.valueOf(assertGenericDeviceIdentifierValidity(compound.getComponent())))))
                   .execute();
        } catch (DataAccessException dax) {
            manageBoundedReferenceOnDeletionException(RefinedDeviceCompoundWithGeneric.class);
        } catch (IllegalArgumentException iae) {
            manageMissingReferenceException(RefinedDeviceCompoundWithGeneric.class, iae.getMessage());
        }
    }

    @Override
    public void deleteEntry(final Screen screen) {
        try {
            getContext().delete(Tables.SCREENMODELS)
                   .where(Tables.SCREENMODELS.DEVICEMODEL
                           .eq(UInteger.valueOf(assertGenericDeviceIdentifierValidity(screen.getGenericDevice()))))
                   .execute();
        } catch (DataAccessException dax) {
            manageBoundedReferenceOnDeletionException(Screen.class);
        } catch (IllegalArgumentException iae) {
            manageMissingReferenceException(Screen.class, iae.getMessage());
        }
    }

    @Override
    public void deleteEntry(final ScreenCategory category) {
        try {
            getContext().delete(Tables.SCREENTECNOLOGIES)
                   .where(Tables.SCREENTECNOLOGIES.ID.eq(UByte.valueOf(discoverScreenCategoryId(category)
                           .orElseThrow(() -> new IllegalArgumentException(SCREEN_CATEGORY)))))
                   .execute();
        } catch (DataAccessException dax) {
            manageBoundedReferenceOnDeletionException(ScreenCategory.class);
        } catch (IllegalArgumentException iae) {
            manageMissingReferenceException(ScreenCategory.class, iae.getMessage());
        }
    }

    @Override
    public void deleteEntry(final ScreenResolution resolution) {
        try {
            getContext().delete(Tables.SCREENRESOLUTIONS)
                   .where(Tables.SCREENRESOLUTIONS.ID
                           .eq(UByte.valueOf(assertScreenResolutionIdentifierValidity(resolution))))
                   .execute();
        } catch (DataAccessException dax) {
            manageBoundedReferenceOnDeletionException(ScreenResolution.class);
        } catch (IllegalArgumentException iae) {
            manageMissingReferenceException(ScreenResolution.class, iae.getMessage());
        }
    }

    @Override
    public void deleteEntry(final Vendor vendor) {
        try {
            getContext().delete(Tables.VENDORS)
                   .where(Tables.VENDORS.ID.eq(UShort.valueOf(discoverVendorId(vendor)
                       .orElseThrow(() -> new IllegalArgumentException(VENDOR)))))
                   .execute();
        } catch (DataAccessException dax) {
            manageBoundedReferenceOnDeletionException(Vendor.class);
        } catch (IllegalArgumentException iae) {
            manageMissingReferenceException(Vendor.class, iae.getMessage());
        }
    }

    private Set<Processor> retrieveProcessors() {
        return repository.dispatchReadRequest(new ReadRequest(
                new QueryObjectImpl(Processor.class, 
                        new CriteriaImpl.Builder()
                            .addCriterion(CriterionImpl.all())
                            .build()))).getUnerasedSet();
    }

    private void manageExternalDependencies(final GenericDevice oldDevice, final GenericDevice newDevice) {
        retrieveProcessors()
            .stream()
            .filter(cpu -> cpu.getGenericDevice().equals(oldDevice))
            .forEach(cpu -> {
                repository.dispatchUpdateRequest(
                    new UpdateRequest(cpu, 
                        new ProcessorImpl.Builder()
                        .device(newDevice)
                        .frequency(cpu.getFrequency())
                        .frequencyUnit(cpu.getFrequencyUnit())
                        .instructionSet(cpu.getInstructionSet())
                        .l3CacheAmount(cpu.getL3CacheAmount().orElse(null))
                        .l3CacheUnit(cpu.getL3CacheInformationUnit().orElse(null))
                        .build()));
            });
    }

    private void manageExternalDependencies(final DigitalInformationUnit oldUnit, 
            final DigitalInformationUnit newUnit) {
        retrieveProcessors()
            .stream()
            .filter(cpu -> cpu.getL3CacheInformationUnit().isPresent())
            .filter(cpu -> cpu.getL3CacheInformationUnit().get().equals(oldUnit))
            .forEach(cpu -> {
                repository.dispatchUpdateRequest(
                        new UpdateRequest(cpu, 
                                new ProcessorImpl.Builder()
                                    .device(cpu.getGenericDevice())
                                    .frequency(cpu.getFrequency())
                                    .frequencyUnit(cpu.getFrequencyUnit())
                                    .instructionSet(cpu.getInstructionSet())
                                    .l3CacheAmount(cpu.getL3CacheAmount().orElse(null))
                                    .l3CacheUnit(newUnit)
                                    .build()));
            });
    }

    private void manageExternalDependencies(final DigitalInformationUnit unit) {
        if (retrieveProcessors()
                .stream()
                .filter(cpu -> cpu.getL3CacheInformationUnit().isPresent())
                .anyMatch(cpu -> cpu.getL3CacheInformationUnit().get().equals(unit))) {
            throw new BoundedReferenceException(DANGLING + "DigitalInformationUnit");
        }
    }

    private void manageExternalDependencies(final GenericDevice device) {
        if (retrieveProcessors()
                .stream()
                .anyMatch(cpu -> cpu.getGenericDevice().equals(device))) {
            throw new BoundedReferenceException(DANGLING + "GenericDevice");
        }
    }

    private Optional<Integer> discoverAspectRatioId(final AspectRatio ratio) {
        final Optional<Record> queryResult = this.getContext().select()
            .from(Tables.ASPECTRATIOS)
            .where(Tables.ASPECTRATIOS.RATIOFACTOR.eq(ratio.getScreenRatio()))
            .fetch()
            .stream()
            .findFirst();
        return queryResult.isPresent() 
            ? Optional.of(queryResult.get().getValue(Tables.ASPECTRATIOS.ID).intValue())
            : Optional.empty();
    }

    private Optional<Integer> discoverColorId(final Color color) {
        if (Objects.nonNull(color)) {
            final Optional<Record> queryResult = this.getContext().select()
                .from(Tables.COLORS)
                .where(Tables.COLORS.HEXCODE.eq(color.getColor()))
                .fetch()
                .stream()
                .findFirst();
            return queryResult.isPresent() 
                ? Optional.of(queryResult.get().getValue(Tables.COLORS.ID).intValue())
                : Optional.empty();
        }
        return Optional.empty();
    }

    private Optional<Integer> discoverDeviceCategoryId(final DeviceCategory category) {
        final Optional<Record> queryResult = this.getContext().select()
            .from(Tables.TRASHWAREDEVICECATEGORIES)
            .where(Tables.TRASHWAREDEVICECATEGORIES.ACRONYM.eq(category.getAcronym()))
            .fetch()
            .stream()
            .findFirst();
        return queryResult.isPresent() 
            ? Optional.of(queryResult.get().getValue(Tables.TRASHWAREDEVICECATEGORIES.ID).intValue())
            : Optional.empty();
    }

    private Optional<Integer> discoverWorkProgressId(final DeviceWorkProgress progress) {
        final Optional<Record> queryResult = this.getContext().select()
            .from(Tables.DEVICESTATES)
            .where(Tables.DEVICESTATES.NAME.eq(progress.getName()))
            .fetch()
            .stream()
            .findFirst();
        return queryResult.isPresent() 
            ? Optional.of(queryResult.get().getValue(Tables.DEVICESTATES.ID).intValue())
            : Optional.empty();
    }

    private Optional<Integer> discoverDigitalInformationUnitId(final DigitalInformationUnit unit) {
        final Optional<Record> queryResult = this.getContext().select()
            .from(Tables.DIGITALINFORMATIONUNITS)
            .where(Tables.DIGITALINFORMATIONUNITS.NAME.eq(unit.getName()))
            .fetch()
            .stream()
            .findFirst();
        return queryResult.isPresent() 
            ? Optional.of(queryResult.get().getValue(Tables.DIGITALINFORMATIONUNITS.ID).intValue())
            : Optional.empty();
    }

    private Optional<Integer> discoverLegalCategoryCompoundId(final LegalCategoryCompound compound) {
        final Optional<Record> queryResult = this.getContext().select()
            .from(Tables.DEVICECATEGORYALLOWEDCOMPONENTS)
            .where(Tables.DEVICECATEGORYALLOWEDCOMPONENTS.COMPOUNDCATEGORY
                .eq(UByte.valueOf(discoverDeviceCategoryId(compound.getCompound())
                    .orElseThrow(() -> new IllegalStateException(ErrorString.BUG_REPORTING))))
            .and(Tables.DEVICECATEGORYALLOWEDCOMPONENTS.COMPONENTCATEGORY
                .eq(UByte.valueOf(discoverDeviceCategoryId(compound.getComponent())
                    .orElseThrow(() -> new IllegalStateException(ErrorString.BUG_REPORTING))))))
            .fetch()
            .stream()
            .findFirst();
        return queryResult.isPresent() 
            ? Optional.of(queryResult.get().getValue(Tables.DEVICECATEGORYALLOWEDCOMPONENTS.ID).intValue())
            : Optional.empty();
    }

    private Optional<Integer> discoverRefinedDeviceCompoundId(final RefinedDeviceCompound compound) {
        final Optional<Record> queryResult = this.getContext().select()
                .from(Tables.DEVICESWITHIDCOMPONENTOFDEVICEWITHID)
                .where(Tables.DEVICESWITHIDCOMPONENTOFDEVICEWITHID.COMPOUND
                    .eq(UInteger.valueOf(discoverRefinedDeviceId(compound.getCompound())
                        .orElseThrow(() -> new IllegalArgumentException(REFINED_DEVICE))))
                .and(Tables.DEVICESWITHIDCOMPONENTOFDEVICEWITHID.COMPONENT
                    .eq(UInteger.valueOf(discoverRefinedDeviceId(compound.getComponent())
                        .orElseThrow(() -> new IllegalArgumentException(REFINED_DEVICE))))))
                .fetch()
                .stream()
                .findFirst();
        return queryResult.isPresent() 
            ? Optional.of(queryResult.get().getValue(Tables.DEVICESWITHIDCOMPONENTOFDEVICEWITHID.ID).intValue())
            : Optional.empty();
    }

    private Optional<Integer> discoverPrinterCategoryId(final PrinterCategory category) {
        final Optional<Record> queryResult = this.getContext().select()
            .from(Tables.PRINTERTECNOLOGIES)
            .where(Tables.PRINTERTECNOLOGIES.NAME.eq(category.getName()))
            .fetch()
            .stream()
            .findFirst();
        return queryResult.isPresent() 
            ? Optional.of(queryResult.get().getValue(Tables.PRINTERTECNOLOGIES.ID).intValue())
            : Optional.empty();
    }

    private Optional<Integer> discoverRefinedDeviceId(final RefinedDevice device) {
        final Optional<Record> queryResult = this.getContext().select()
            .from(Tables.DEVICESWITHID)
            .where(Tables.DEVICESWITHID.CATEGORYDEVICEID.eq(UShort.valueOf(device.getCategoryDeviceId()))
            .and(Tables.DEVICESWITHID.DEVICEMODEL
                    .eq(UInteger.valueOf(assertGenericDeviceIdentifierValidity(device.getGenericDevice())))))
            .fetch()
            .stream()
            .findFirst();
        return queryResult.isPresent()
            ? Optional.of(queryResult.get().getValue(Tables.DEVICESWITHID.ID).intValue())
            : Optional.empty();
    }

    private Optional<Integer> discoverScreenCategoryId(final ScreenCategory category) {
        final Optional<Record> queryResult = this.getContext().select()
            .from(Tables.SCREENTECNOLOGIES)
            .where(Tables.SCREENTECNOLOGIES.NAME.eq(category.getName()))
            .fetch()
            .stream()
            .findFirst();
        return queryResult.isPresent() 
            ? Optional.of(queryResult.get().getValue(Tables.SCREENTECNOLOGIES.ID).intValue())
            : Optional.empty();
    }

    private Optional<Integer> discoverVendorId(final Vendor vendor) {
        final Optional<Record> queryResult = this.getContext().select()
            .from(Tables.VENDORS)
            .where(Tables.VENDORS.NAME.eq(vendor.getName()))
            .fetch()
            .stream()
            .findFirst();
        return queryResult.isPresent() 
            ? Optional.of(queryResult.get().getValue(Tables.VENDORS.ID).intValue())
            : Optional.empty();
    }

    private Optional<TrashwareWorker> discoverTrashwareWorkerById(final Integer identifier) {
        final Set<PhysicalPerson> identifiedPerson = 
                this.repository.dispatchReadRequest(
                        new ReadRequest(
                                new QueryObjectImpl(PhysicalPerson.class, new CriteriaImpl.Builder()
                                        .addCriterion(CriterionImpl.equality("getNumericIdentifier", identifier))
                                        .build())))
                .getUnerasedSet();
        if (!identifiedPerson.isEmpty()) {
            final PhysicalPerson person = identifiedPerson.stream().findFirst().get();
            final Set<TrashwareWorker> result = 
                    this.repository.dispatchReadRequest(
                        new ReadRequest(
                                new QueryObjectImpl(
                                        TrashwareWorker.class, new CriteriaImpl.Builder()
                                            .addCriterion(CriterionImpl.equality("getPerson", person))
                                            .build())))
                    .getUnerasedSet();
            return result.stream().findFirst();
        }
        return Optional.empty();
    }

    private Color discoverColorById(final Integer identifier) {
        return this.getContext().select()
            .from(Tables.COLORS)
            .where(Tables.COLORS.ID.eq(UByte.valueOf(identifier)))
            .fetch()
            .stream()
            .map(record -> new ColorImpl(record.getValue(Tables.COLORS.HEXCODE)))
            .findFirst()
            .get();
    }

    private AspectRatio discoverAspectRatioById(final Integer identifier) {
        return this.getContext().select()
            .from(Tables.ASPECTRATIOS)
            .where(Tables.ASPECTRATIOS.ID.eq(UByte.valueOf(identifier)))
            .fetch()
            .stream()
            .map(record -> record.getValue(Tables.ASPECTRATIOS.RATIOFACTOR))
            .map(string -> string.split(":"))
            .map(array -> new AspectRatioImpl(Integer.parseInt(array[0]), Integer.parseInt(array[1])))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("There is no AspectRatio linked to such an ID"));
    }

    private DeviceCategory discoverDeviceCategoryById(final Integer identifier) {
        return this.getContext().select()
            .from(Tables.TRASHWAREDEVICECATEGORIES)
            .where(Tables.TRASHWAREDEVICECATEGORIES.ID.eq(UByte.valueOf(identifier)))
            .fetch()
            .stream()
            .map(record -> new DeviceCategoryImpl(
                    record.getValue(Tables.TRASHWAREDEVICECATEGORIES.NAME), 
                    record.getValue(Tables.TRASHWAREDEVICECATEGORIES.ACRONYM),
                    translateByteToBoolean(record.getValue(Tables.TRASHWAREDEVICECATEGORIES.ALLOWSMULTIPLECOMPONUND))))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("There is no DeviceCategory linked to such an ID"));
    }

    private Vendor discoverVendorById(final Integer identifier) {
        return Objects.nonNull(identifier) 
            ? this.getContext().select()
                .from(Tables.VENDORS)
                .where(Tables.VENDORS.ID.eq(UShort.valueOf(identifier)))
                .fetch()
                .stream()
                .map(record -> new VendorImpl(record.getValue(Tables.VENDORS.NAME)))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("There is no Vendor linked to such an ID"))
            : null;
    }

    private DigitalInformationUnit discoverDigitalInformationUnitById(final Integer identifier) {
        return this.getContext().select()
            .from(Tables.DIGITALINFORMATIONUNITS)
            .where(Tables.DIGITALINFORMATIONUNITS.ID.eq(UByte.valueOf(identifier)))
            .fetch()
            .stream()
            .map(record -> new DigitalInformationUnitImpl(record.getValue(Tables.DIGITALINFORMATIONUNITS.NAME)))
            .findFirst()
            .get();
    }

    private PrinterCategory discoverPrinterCategoryById(final Integer identifier) {
        return this.getContext().select()
            .from(Tables.PRINTERTECNOLOGIES)
            .where(Tables.PRINTERTECNOLOGIES.ID.eq(UByte.valueOf(identifier)))
            .fetch()
            .stream()
            .map(record -> new PrinterCategoryImpl(record.getValue(Tables.PRINTERTECNOLOGIES.NAME)))
            .findFirst()
            .get();
    }

    private Optional<GenericDevice> discoverGenericDeviceById(final Integer identifier) {
        final Optional<Record> result = this.getContext().select()
            .from(Tables.DEVICEMODELS)
            .where(Tables.DEVICEMODELS.ID.eq(UInteger.valueOf(identifier)))
            .fetch()
            .stream()
            .findFirst();
        if (result.isPresent()) {
            final GenericDeviceImpl.Builder builder = new GenericDeviceImpl.Builder();
            if (Objects.nonNull(result.get().getValue(Tables.DEVICEMODELS.VENDOR))) {
                builder.vendor(discoverVendorById(result.get().getValue(Tables.DEVICEMODELS.VENDOR).intValue()));
            }
            builder.identifier(result.get().getValue(Tables.DEVICEMODELS.ID).intValue());
            builder.vendorModelIdentifier(result.get().getValue(Tables.DEVICEMODELS.VENDORMODELNUMBER));
            builder.deviceCategory(discoverDeviceCategoryById(
                                                    result.get().getValue(Tables.DEVICEMODELS.CATEGORY).intValue()));
            builder.description(result.get().getValue(Tables.DEVICEMODELS.DESCRIPTION));
            builder.available(result.get().getValue(Tables.DEVICEMODELS.DEVICESWITHOUTID).intValue());
            return Optional.of(builder.build());
        }
        return Optional.empty();
    }

    private Optional<RefinedDevice> discoverRefinedDeviceById(final Integer identifier) {
        final Optional<Record> result = this.getContext().select()
            .from(Tables.DEVICESWITHID)
            .where(Tables.DEVICESWITHID.ID.eq(UInteger.valueOf(identifier)))
            .fetch()
            .stream()
            .findFirst();
        if (result.isPresent()) {
            final RefinedDeviceImpl.Builder builder = new RefinedDeviceImpl.Builder();
            builder.identifier(result.get().getValue(Tables.DEVICESWITHID.ID).intValue());
            builder.categoryDeviceId(result.get().getValue(Tables.DEVICESWITHID.CATEGORYDEVICEID).intValue());
            builder.annotations(result.get().getValue(Tables.DEVICESWITHID.ANNOTATIONS));
            builder.available(translateByteToBoolean(result.get().getValue(Tables.DEVICESWITHID.AVAILABLE)));
            builder.deviceCategory(discoverDeviceCategoryById(
                    result.get().getValue(Tables.DEVICESWITHID.CATEGORY).intValue()));
            builder.refining(discoverGenericDeviceById(
                    result.get().getValue(Tables.DEVICESWITHID.DEVICEMODEL).intValue()).get());
            builder.progress(discoverWorkProgressById(
                    result.get().getValue(Tables.DEVICESWITHID.CURRENTSTATE).intValue()));
            builder.lastCommitter(discoverTrashwareWorkerById(
                    result.get().getValue(Tables.DEVICESWITHID.LASTUPDATEWORKER).intValue()).get());
            builder.lastUpdate(new Date(result.get().getValue(Tables.DEVICESWITHID.LASTUPDATEDATE).getTime()));
            return Optional.of(builder.build());
        }
        return Optional.empty();
    }

    private ScreenCategory discoverScreenCategoryById(final Integer identifier) {
        return this.getContext().select()
            .from(Tables.SCREENTECNOLOGIES)
            .where(Tables.SCREENTECNOLOGIES.ID.eq(UByte.valueOf(identifier)))
            .fetch()
            .stream()
            .map(record -> new ScreenCategoryImpl(record.getValue(Tables.SCREENTECNOLOGIES.NAME)))
            .findFirst()
            .get();
    }

    private Optional<ScreenResolution> discoverScreenResolutionById(final Integer identifier) {
        final Optional<Record> result = this.getContext().select()
            .from(Tables.SCREENRESOLUTIONS)
            .where(Tables.SCREENRESOLUTIONS.ID.eq(UByte.valueOf(identifier)))
            .fetch()
            .stream()
            .findFirst();
        if (result.isPresent()) {
            final ScreenResolutionImpl.Builder builder = new ScreenResolutionImpl.Builder();
            builder.identifier(result.get().getValue(Tables.SCREENRESOLUTIONS.ID).intValue());
            builder.width(result.get().getValue(Tables.SCREENRESOLUTIONS.WIDTH).intValue());
            builder.height(result.get().getValue(Tables.SCREENRESOLUTIONS.HEIGHT).intValue());
            builder.aspectRatio(
                discoverAspectRatioById(result.get().getValue(Tables.SCREENRESOLUTIONS.ASPECTRATIO).intValue()));
            return Optional.of(builder.build());
        }
        return Optional.empty();
    }

    private DeviceWorkProgress discoverWorkProgressById(final Integer identifier) {
        return this.getContext().select()
            .from(Tables.DEVICESTATES)
            .where(Tables.DEVICESTATES.ID.eq(UByte.valueOf(identifier)))
            .fetch()
            .stream()
            .map(record -> new DeviceWorkProgressImpl(record.getValue(Tables.DEVICESTATES.NAME),
                    record.getValue(Tables.DEVICESTATES.DESCRIPTION)))
            .findFirst()
            .get();
    }

    private Integer assertScreenResolutionIdentifierValidity(final ScreenResolution resolution) {
        if (!(resolution.equals(discoverScreenResolutionById(resolution.getNumericIdentifier().orElseThrow(
                () -> new IllegalStateException(ErrorString.BUG_REPORTING))).orElse(null)))) {
            throw new IllegalStateException("A corrupted ScreenResolution has been sent to " 
                + this.getClass().getSimpleName()); 
        }
        return resolution.getNumericIdentifier().get();
    }

    private Integer assertGenericDeviceIdentifierValidity(final GenericDevice device) {
        if (!(device.equals(discoverGenericDeviceById(device.getNumericIdentifier().orElseThrow(
                () -> new IllegalStateException(ErrorString.BUG_REPORTING))).orElse(null)))) {
            throw new IllegalStateException(
                "A corrupted GenericDevice has been sent to " + this.getClass().getSimpleName()); 
        }
        return device.getNumericIdentifier().get();
    }
}
