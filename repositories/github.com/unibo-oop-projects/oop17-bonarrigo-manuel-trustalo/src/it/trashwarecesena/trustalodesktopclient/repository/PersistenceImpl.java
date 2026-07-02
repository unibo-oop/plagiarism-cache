package it.trashwarecesena.trustalodesktopclient.repository; // NOPMD by blatante on 7/26/18 1:59 PM This is a boundary 
                                                             //class. There might be ways around his monolithic 
                                                             //appearance, still I do not understand which ones.

import java.util.Set;

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
import it.trashwarecesena.trustalodesktopclient.model.immutable.ImmutableIntelProcessor;
import it.trashwarecesena.trustalodesktopclient.model.otherdevices.FrequencyUnit;
import it.trashwarecesena.trustalodesktopclient.model.otherdevices.InstructionSet;
import it.trashwarecesena.trustalodesktopclient.model.otherdevices.Processor;
import it.trashwarecesena.trustalodesktopclient.model.people.Contact;
import it.trashwarecesena.trustalodesktopclient.model.people.ContactCategory;
import it.trashwarecesena.trustalodesktopclient.model.people.JuridicalPerson;
import it.trashwarecesena.trustalodesktopclient.model.people.PersonCategory;
import it.trashwarecesena.trustalodesktopclient.model.people.PhysicalPerson;
import it.trashwarecesena.trustalodesktopclient.model.people.TrashwareWorker;
import it.trashwarecesena.trustalodesktopclient.model.people.TrashwareWorkerCategory;
import it.trashwarecesena.trustalodesktopclient.model.requests.GenericDeviceRequest;
import it.trashwarecesena.trustalodesktopclient.model.requests.RefinedDeviceRequest;
import it.trashwarecesena.trustalodesktopclient.model.requests.Request;
import it.trashwarecesena.trustalodesktopclient.model.requests.RequestDetail;
import it.trashwarecesena.trustalodesktopclient.model.requests.RequestProgress;
import it.trashwarecesena.trustalodesktopclient.repository.metamapping.IdentifiableReferenceAssertor;
import it.trashwarecesena.trustalodesktopclient.repository.query.BiRequest;
import it.trashwarecesena.trustalodesktopclient.repository.query.QueryRequest;
import it.trashwarecesena.trustalodesktopclient.repository.query.SingleRequest;
import it.trashwarecesena.trustalodesktopclient.repository.query.concreteness.CreateRequest;
import it.trashwarecesena.trustalodesktopclient.repository.query.concreteness.DeleteRequest;
import it.trashwarecesena.trustalodesktopclient.repository.query.concreteness.ReadRequest;
import it.trashwarecesena.trustalodesktopclient.repository.query.concreteness.UpdateRequest;
import it.trashwarecesena.trustalodesktopclient.repository.query.criteria.QueryObject;

/**
 * An implementation for the boundary interface {@link Persistence}.
 * <p>
 * This class actually provides a method for every single possible persistence
 * operation on any domain related object, so that a client can have
 * compile-time safeness over the code he or she writes.
 * <p>
 * Notice that since the actual persistence source is unknown for many layers
 * below this one, all the exceptions raised must be unchecked, even if the
 * program correctness is still endurable after catching one of these. Please
 * refer to the documentation of every method to understand which exception can
 * be raised in which situation and how to notify their presence accordingly.
 * 
 * @author Manuel Bonarrigo
 *
 */
public final class PersistenceImpl implements Persistence {

    private final Repository repository;

    /**
     * Create a Persistence able to work onto the specified Repository.
     * 
     * @param repository
     *            a Repository to dispatch to all the requests in search of
     *            satisfaction.
     */
    public PersistenceImpl(final Repository repository) {
        this.repository = repository;
    }

    @Override
    public void createEntry(final Contact contact) {
        repository.dispatchCreateRequest(buildCreateRequest(contact));
    }

    @Override
    public Set<Contact> readContact(final QueryObject filter) {
        return repository.dispatchReadRequest(buildQueryRequest(filter)).getUnerasedSet();
    }

    @Override
    public void updateEntry(final Contact oldContact, final Contact newContact) {
        repository.dispatchUpdateRequest(buildUpdateRequest(oldContact, newContact));
    }

    @Override
    public void deleteEntry(final Contact contact) {
        repository.dispatchDeleteRequest(buildDeleteRequest(contact));
    }

    @Override
    public void createEntry(final ContactCategory category) {
        repository.dispatchCreateRequest(buildCreateRequest(category));
    }

    @Override
    public Set<ContactCategory> readContactCategories(final QueryObject filter) {
        return repository.dispatchReadRequest(buildQueryRequest(filter)).getUnerasedSet();
    }

    @Override
    public void updateEntry(final ContactCategory oldCategory, final ContactCategory newCategory) {
        repository.dispatchUpdateRequest(buildUpdateRequest(oldCategory, newCategory));

    }

    @Override
    public void deleteEntry(final ContactCategory category) {
        repository.dispatchDeleteRequest(buildDeleteRequest(category));
    }

    @Override
    public void createEntry(final JuridicalPerson organization) {
        repository.dispatchCreateRequest(buildCreateRequest(organization));
    }

    @Override
    public Set<JuridicalPerson> readJuridicalPeople(final QueryObject filter) {
        return repository.dispatchReadRequest(buildQueryRequest(filter)).getUnerasedSet();
    }

    @Override
    public void updateEntry(final JuridicalPerson oldOrganization, final JuridicalPerson newOrganization) {
        repository.dispatchUpdateRequest(buildUpdateRequest(oldOrganization, newOrganization));
    }

    @Override
    public void deleteEntry(final JuridicalPerson organization) {
        repository.dispatchDeleteRequest(buildDeleteRequest(organization));
    }

    @Override
    public void createEntry(final PersonCategory category) {
        repository.dispatchCreateRequest(buildCreateRequest(category));
    }

    @Override
    public Set<PersonCategory> readJuridicalPersonCategories(final QueryObject filter) {
        return repository.dispatchReadRequest(buildQueryRequest(filter)).getUnerasedSet();
    }

    @Override
    public void updateEntry(final PersonCategory oldCategory, final PersonCategory newCategory) {
        repository.dispatchUpdateRequest(buildUpdateRequest(oldCategory, newCategory));
    }

    @Override
    public void deleteEntry(final PersonCategory category) {
        repository.dispatchDeleteRequest(buildDeleteRequest(category));
    }

    @Override
    public void createEntry(final PhysicalPerson person) {
        repository.dispatchCreateRequest(buildCreateRequest(person));
    }

    @Override
    public Set<PhysicalPerson> readPeople(final QueryObject filter) {
        return repository.dispatchReadRequest(buildQueryRequest(filter)).getUnerasedSet();
    }

    @Override
    public void updateEntry(final PhysicalPerson oldPerson, final PhysicalPerson newPerson) {
        repository.dispatchUpdateRequest(buildUpdateRequest(oldPerson, newPerson));
    }

    @Override
    public void deleteEntry(final PhysicalPerson person) {
        repository.dispatchDeleteRequest(buildDeleteRequest(person));
    }

    @Override
    public void createEntry(final TrashwareWorker worker) {
        repository.dispatchCreateRequest(buildCreateRequest(worker));
    }

    @Override
    public Set<TrashwareWorker> readTrashwareWorkers(final QueryObject filter) {
        return repository.dispatchReadRequest(buildQueryRequest(filter)).getUnerasedSet();
    }

    @Override
    public void updateEntry(final TrashwareWorker oldTrashwareWorker, final TrashwareWorker newTrashwareWorker) {
        repository.dispatchUpdateRequest(buildUpdateRequest(oldTrashwareWorker, newTrashwareWorker));
    }

    @Override
    public void deleteEntry(final TrashwareWorker worker) {
        repository.dispatchDeleteRequest(buildDeleteRequest(worker));
    }

    @Override
    public void createEntry(final TrashwareWorkerCategory category) {
        repository.dispatchCreateRequest(buildCreateRequest(category));
    }

    @Override
    public Set<TrashwareWorkerCategory> readTrashwareWorkerCategories(final QueryObject filter) {
        return repository.dispatchReadRequest(buildQueryRequest(filter)).getUnerasedSet();
    }

    @Override
    public void updateEntry(final TrashwareWorkerCategory oldCategory, final TrashwareWorkerCategory newCategory) {
        repository.dispatchUpdateRequest(buildUpdateRequest(oldCategory, newCategory));
    }

    @Override
    public void deleteEntry(final TrashwareWorkerCategory category) {
        repository.dispatchDeleteRequest(buildDeleteRequest(category));
    }

    @Override
    public void createEntry(final AspectRatio ratio) {
        repository.dispatchCreateRequest(buildCreateRequest(ratio));
    }

    @Override
    public Set<AspectRatio> readAspectRatio(final QueryObject filter) {
        return repository.dispatchReadRequest(buildQueryRequest(filter)).getUnerasedSet();
    }

    @Override
    public void updateEntry(final AspectRatio oldRatio, final AspectRatio newRatio) {
        repository.dispatchUpdateRequest(buildUpdateRequest(oldRatio, newRatio));
    }

    @Override
    public void deleteEntry(final AspectRatio ratio) {
        repository.dispatchDeleteRequest(buildDeleteRequest(ratio));
    }

    @Override
    public void createEntry(final Case caseModel) {
        repository.dispatchCreateRequest(buildCreateRequest(caseModel));
    }

    @Override
    public Set<Case> readCase(final QueryObject filter) {
        return repository.dispatchReadRequest(buildQueryRequest(filter)).getUnerasedSet();
    }

    @Override
    public void updateEntry(final Case oldCaseModel, final Case newCaseModel) {
        repository.dispatchUpdateRequest(buildUpdateRequest(oldCaseModel, newCaseModel));
    }

    @Override
    public void deleteEntry(final Case caseModel) {
        repository.dispatchDeleteRequest(buildDeleteRequest(caseModel));
    }

    @Override
    public void createEntry(final Color color) {
        repository.dispatchCreateRequest(buildCreateRequest(color));
    }

    @Override
    public Set<Color> readColor(final QueryObject filter) {
        return repository.dispatchReadRequest(buildQueryRequest(filter)).getUnerasedSet();
    }

    @Override
    public void updateEntry(final Color oldColor, final Color newColor) {
        repository.dispatchUpdateRequest(buildUpdateRequest(oldColor, newColor));
    }

    @Override
    public void deleteEntry(final Color color) {
        repository.dispatchDeleteRequest(buildDeleteRequest(color));
    }

    @Override
    public void createEntry(final DeviceCategory category) {
        repository.dispatchCreateRequest(buildCreateRequest(category));
    }

    @Override
    public Set<DeviceCategory> readDeviceCategory(final QueryObject filter) {
        return repository.dispatchReadRequest(buildQueryRequest(filter)).getUnerasedSet();
    }

    @Override
    public void updateEntry(final DeviceCategory oldCategory, final DeviceCategory newCategory) {
        repository.dispatchUpdateRequest(buildUpdateRequest(oldCategory, newCategory));
    }

    @Override
    public void deleteEntry(final DeviceCategory category) {
        repository.dispatchDeleteRequest(buildDeleteRequest(category));
    }

    @Override
    public void createEntry(final DeviceWorkProgress progress) {
        repository.dispatchCreateRequest(buildCreateRequest(progress));
    }

    @Override
    public Set<DeviceWorkProgress> readDeviceWorkProgress(final QueryObject filter) {
        return repository.dispatchReadRequest(buildQueryRequest(filter)).getUnerasedSet();
    }

    @Override
    public void updateEntry(final DeviceWorkProgress oldProgress, final DeviceWorkProgress newProgress) {
        repository.dispatchUpdateRequest(buildUpdateRequest(oldProgress, newProgress));
    }

    @Override
    public void deleteEntry(final DeviceWorkProgress progress) {
        repository.dispatchDeleteRequest(buildDeleteRequest(progress));
    }

    @Override
    public void createEntry(final DigitalInformationUnit unit) {
        repository.dispatchCreateRequest(buildCreateRequest(unit));
    }

    @Override
    public Set<DigitalInformationUnit> readDigitalInformationUnit(final QueryObject filter) {
        return repository.dispatchReadRequest(buildQueryRequest(filter)).getUnerasedSet();
    }

    @Override
    public void updateEntry(final DigitalInformationUnit oldUnit, final DigitalInformationUnit newUnit) {
        repository.dispatchUpdateRequest(buildUpdateRequest(oldUnit, newUnit));
    }

    @Override
    public void deleteEntry(final DigitalInformationUnit unit) {
        repository.dispatchDeleteRequest(buildDeleteRequest(unit));
    }

    @Override
    public void createEntry(final GenericDevice device) {
        repository.dispatchCreateRequest(buildCreateRequest(device));
    }

    @Override
    public Set<GenericDevice> readGenericDevice(final QueryObject filter) {
        return repository.dispatchReadRequest(buildQueryRequest(filter)).getUnerasedSet();
    }

    @Override
    public void updateEntry(final GenericDevice oldDevice, final GenericDevice newDevice) {
        repository.dispatchUpdateRequest(buildUpdateRequest(oldDevice, newDevice));
    }

    @Override
    public void deleteEntry(final GenericDevice device) {
        repository.dispatchDeleteRequest(buildDeleteRequest(device));
    }

    @Override
    public void createEntry(final HardDiskDrive hdd) {
        repository.dispatchCreateRequest(buildCreateRequest(hdd));
    }

    @Override
    public Set<HardDiskDrive> readHardDiskDrive(final QueryObject filter) {
        return repository.dispatchReadRequest(buildQueryRequest(filter)).getUnerasedSet();
    }

    @Override
    public void updateEntry(final HardDiskDrive oldHdd, final HardDiskDrive newHdd) {
        repository.dispatchUpdateRequest(buildUpdateRequest(oldHdd, newHdd));
    }

    @Override
    public void deleteEntry(final HardDiskDrive hdd) {
        repository.dispatchDeleteRequest(buildDeleteRequest(hdd));
    }

    @Override
    public void createEntry(final LegalCategoryCompound categoryCompound) {
        repository.dispatchCreateRequest(buildCreateRequest(categoryCompound));
    }

    @Override
    public Set<LegalCategoryCompound> readLegalCategoryCompound(final QueryObject filter) {
        return repository.dispatchReadRequest(buildQueryRequest(filter)).getUnerasedSet();
    }

    @Override
    public void updateEntry(final LegalCategoryCompound oldCategoryCompound, 
            final LegalCategoryCompound newCategoryCompound) {
        repository.dispatchUpdateRequest(buildUpdateRequest(oldCategoryCompound, newCategoryCompound));
    }

    @Override
    public void deleteEntry(final LegalCategoryCompound categoryCompound) {
        repository.dispatchDeleteRequest(buildDeleteRequest(categoryCompound));
    }

    @Override
    public void createEntry(final Printer printer) {
        repository.dispatchCreateRequest(buildCreateRequest(printer));
    }

    @Override
    public Set<Printer> readPrinter(final QueryObject filter) {
        return repository.dispatchReadRequest(buildQueryRequest(filter)).getUnerasedSet();
    }

    @Override
    public void updateEntry(final Printer oldPrinter, final Printer newPrinter) {
        repository.dispatchUpdateRequest(buildUpdateRequest(oldPrinter, newPrinter));
    }

    @Override
    public void deleteEntry(final Printer printer) {
        repository.dispatchDeleteRequest(buildDeleteRequest(printer));
    }

    @Override
    public void createEntry(final PrinterCategory category) {
        repository.dispatchCreateRequest(buildCreateRequest(category));
    }

    @Override
    public Set<PrinterCategory> readPrinterCategory(final QueryObject filter) {
        return repository.dispatchReadRequest(buildQueryRequest(filter)).getUnerasedSet();
    }

    @Override
    public void updateEntry(final PrinterCategory oldCategory, final PrinterCategory newCategory) {
        repository.dispatchUpdateRequest(buildUpdateRequest(oldCategory, newCategory));
    }

    @Override
    public void deleteEntry(final PrinterCategory category) {
        repository.dispatchDeleteRequest(buildDeleteRequest(category));
    }

    @Override
    public void createEntry(final RandomAccessMemory ram) {
        repository.dispatchCreateRequest(buildCreateRequest(ram));
    }

    @Override
    public Set<RandomAccessMemory> readRandomAccessMemory(final QueryObject filter) {
        return repository.dispatchReadRequest(buildQueryRequest(filter)).getUnerasedSet();
    }

    @Override
    public void updateEntry(final RandomAccessMemory oldRam, final RandomAccessMemory newRam) {
        repository.dispatchUpdateRequest(buildUpdateRequest(oldRam, newRam));
    }

    @Override
    public void deleteEntry(final RandomAccessMemory ram) {
        repository.dispatchDeleteRequest(buildDeleteRequest(ram));
    }

    @Override
    public void createEntry(final RefinedDeviceCompound compound) {
        repository.dispatchCreateRequest(buildCreateRequest(compound));
    }

    @Override
    public Set<RefinedDeviceCompound> readRefinedDeviceCompound(final QueryObject filter) {
        return repository.dispatchReadRequest(buildQueryRequest(filter)).getUnerasedSet();
    }

    @Override
    public void updateEntry(final RefinedDeviceCompound oldCompound, final RefinedDeviceCompound newCompound) {
        repository.dispatchUpdateRequest(buildUpdateRequest(oldCompound, newCompound));
    }

    @Override
    public void deleteEntry(final RefinedDeviceCompound compound) {
        repository.dispatchDeleteRequest(buildDeleteRequest(compound));
    }

    @Override
    public void createEntry(final RefinedDeviceCompoundWithGeneric compound) {
        repository.dispatchCreateRequest(buildCreateRequest(compound));
    }

    @Override
    public Set<RefinedDeviceCompoundWithGeneric> readRefinedDeviceCompoundWithGeneric(final QueryObject filter) {
        return repository.dispatchReadRequest(buildQueryRequest(filter)).getUnerasedSet();
    }

    @Override
    public void updateEntry(final RefinedDeviceCompoundWithGeneric oldCompound,
            final RefinedDeviceCompoundWithGeneric newCompound) {
        repository.dispatchUpdateRequest(buildUpdateRequest(oldCompound, newCompound));
    }

    @Override
    public void deleteEntry(final RefinedDeviceCompoundWithGeneric compound) {
        repository.dispatchDeleteRequest(buildDeleteRequest(compound));
    }

    @Override
    public void createEntry(final Screen screen) {
        repository.dispatchCreateRequest(buildCreateRequest(screen));
    }

    @Override
    public Set<Screen> readScreen(final QueryObject filter) {
        return repository.dispatchReadRequest(buildQueryRequest(filter)).getUnerasedSet();
    }

    @Override
    public void updateEntry(final Screen oldScreen, final Screen newScreen) {
        repository.dispatchUpdateRequest(buildUpdateRequest(oldScreen, newScreen));
    }

    @Override
    public void deleteEntry(final Screen screen) {
        repository.dispatchDeleteRequest(buildDeleteRequest(screen));
    }

    @Override
    public void createEntry(final ScreenCategory screenCategory) {
        repository.dispatchCreateRequest(buildCreateRequest(screenCategory));
    }

    @Override
    public Set<ScreenCategory> readScreenCategory(final QueryObject filter) {
        return repository.dispatchReadRequest(buildQueryRequest(filter)).getUnerasedSet();
    }

    @Override
    public void updateEntry(final ScreenCategory oldScreenCategory, final ScreenCategory newScreenCategory) {
        repository.dispatchUpdateRequest(buildUpdateRequest(oldScreenCategory, newScreenCategory));
    }

    @Override
    public void deleteEntry(final ScreenCategory screenCategory) {
        repository.dispatchDeleteRequest(buildDeleteRequest(screenCategory));
    }

    @Override
    public void createEntry(final ScreenResolution screenResolution) {
        repository.dispatchCreateRequest(buildCreateRequest(screenResolution));
    }

    @Override
    public Set<ScreenResolution> readScreenResolution(final QueryObject filter) {
        return repository.dispatchReadRequest(buildQueryRequest(filter)).getUnerasedSet();
    }

    @Override
    public void updateEntry(final ScreenResolution oldScreenResolution, final ScreenResolution newScreenResolution) {
        repository.dispatchUpdateRequest(buildUpdateRequest(oldScreenResolution, newScreenResolution));
    }

    @Override
    public void deleteEntry(final ScreenResolution screenResolution) {
        repository.dispatchDeleteRequest(buildDeleteRequest(screenResolution));
    }

    @Override
    public void createEntry(final Vendor vendor) {
        repository.dispatchCreateRequest(buildCreateRequest(vendor));
    }

    @Override
    public Set<Vendor> readVendor(final QueryObject filter) {
        return repository.dispatchReadRequest(buildQueryRequest(filter)).getUnerasedSet();
    }

    @Override
    public void updateEntry(final Vendor oldVendor, final Vendor newVendor) {
        repository.dispatchUpdateRequest(buildUpdateRequest(oldVendor, newVendor));
    }

    @Override
    public void deleteEntry(final Vendor vendor) {
        repository.dispatchDeleteRequest(buildDeleteRequest(vendor));
    }

    @Override
    public void createEntry(final RefinedDevice device) {
        repository.dispatchCreateRequest(buildCreateRequest(device));
    }

    @Override
    public Set<RefinedDevice> readRefinedDevice(final QueryObject filter) {
        return repository.dispatchReadRequest(buildQueryRequest(filter)).getUnerasedSet();
    }

    @Override
    public void updateEntry(final RefinedDevice oldDevice, final RefinedDevice newDevice) {
        repository.dispatchUpdateRequest(buildUpdateRequest(oldDevice, newDevice));
    }

    @Override
    public void deleteEntry(final RefinedDevice device) {
        repository.dispatchDeleteRequest(buildDeleteRequest(device));
    }

    @Override
    public void createEntry(final GenericDeviceRequest request) {
        repository.dispatchCreateRequest(buildCreateRequest(request));
    }

    @Override
    public Set<GenericDeviceRequest> readGenericDeviceRequest(final QueryObject filter) {
        return repository.dispatchReadRequest(buildQueryRequest(filter)).getUnerasedSet();
    }

    @Override
    public void updateEntry(final GenericDeviceRequest oldRequest, final GenericDeviceRequest newRequest) {
        repository.dispatchUpdateRequest(buildUpdateRequest(oldRequest, newRequest));
    }

    @Override
    public void deleteEntry(final GenericDeviceRequest request) {
        repository.dispatchDeleteRequest(buildDeleteRequest(request));
    }

    @Override
    public void createEntry(final Request request) {
        repository.dispatchCreateRequest(buildCreateRequest(request));
    }

    @Override
    public Set<Request> readRequest(final QueryObject filter) {
        return repository.dispatchReadRequest(buildQueryRequest(filter)).getUnerasedSet();
    }

    @Override
    public void updateEntry(final Request oldRequest, final Request newRequest) {
        repository.dispatchUpdateRequest(buildUpdateRequest(oldRequest, newRequest));
    }

    @Override
    public void deleteEntry(final Request request) {
        repository.dispatchDeleteRequest(buildDeleteRequest(request));
    }

    @Override
    public void createEntry(final RequestDetail detail) {
        repository.dispatchCreateRequest(buildCreateRequest(detail));
    }

    @Override
    public Set<RequestDetail> readRequestDetail(final QueryObject filter) {
        return repository.dispatchReadRequest(buildQueryRequest(filter)).getUnerasedSet();
    }

    @Override
    public void updateEntry(final RequestDetail oldDetail, final RequestDetail newDetail) {
        repository.dispatchUpdateRequest(buildUpdateRequest(oldDetail, newDetail));
    }

    @Override
    public void deleteEntry(final RequestDetail detail) {
        repository.dispatchDeleteRequest(buildDeleteRequest(detail));
    }

    @Override
    public void createEntry(final RequestProgress progress) {
        repository.dispatchCreateRequest(buildCreateRequest(progress));
    }

    @Override
    public Set<RequestProgress> readRequestProgress(final QueryObject filter) {
        return repository.dispatchReadRequest(buildQueryRequest(filter)).getUnerasedSet();
    }

    @Override
    public void updateEntry(final RequestProgress oldProgress, final RequestProgress newProgress) {
        repository.dispatchUpdateRequest(buildUpdateRequest(oldProgress, newProgress));
    }

    @Override
    public void deleteEntry(final RequestProgress progress) {
        repository.dispatchDeleteRequest(buildDeleteRequest(progress));
    }

    @Override
    public void createEntry(final RefinedDeviceRequest request) {
        repository.dispatchCreateRequest(buildCreateRequest(request));
    }

    @Override
    public Set<RefinedDeviceRequest> readRefinedDeviceRequest(final QueryObject filter) {
        return repository.dispatchReadRequest(buildQueryRequest(filter)).getUnerasedSet();
    }

    @Override
    public void updateEntry(final RefinedDeviceRequest oldRequest, final RefinedDeviceRequest newRequest) {
        repository.dispatchUpdateRequest(buildUpdateRequest(oldRequest, newRequest));
    }

    @Override
    public void deleteEntry(final RefinedDeviceRequest request) {
        repository.dispatchDeleteRequest(buildDeleteRequest(request));
    }

    @Override
    public void createEntry(final ImmutableIntelProcessor processor) {
        repository.dispatchCreateRequest(buildCreateRequest(processor));
    }

    @Override
    public Set<ImmutableIntelProcessor> readIntelProcessors(final QueryObject filter) {
        return repository.dispatchReadRequest(buildQueryRequest(filter)).getUnerasedSet();
    }

    @Override
    public void updateEntry(final ImmutableIntelProcessor oldProcessor, final ImmutableIntelProcessor newProcessor) {
        repository.dispatchUpdateRequest(buildUpdateRequest(oldProcessor, newProcessor));
    }

    @Override
    public void deleteEntry(final ImmutableIntelProcessor processor) {
        repository.dispatchDeleteRequest(buildDeleteRequest(processor));

    }

    @Override
    public void createEntry(final Processor processor) {
        repository.dispatchCreateRequest(buildCreateRequest(processor));
    }

    @Override
    public Set<Processor> readProcessors(final QueryObject filter) {
        return repository.dispatchReadRequest(buildQueryRequest(filter)).getUnerasedSet();
    }

    @Override
    public void updateEntry(final Processor oldProcessor, final Processor newProcessor) {
        repository.dispatchUpdateRequest(buildUpdateRequest(oldProcessor, newProcessor));
    }

    @Override
    public void deleteEntry(final Processor processor) {
        repository.dispatchDeleteRequest(buildDeleteRequest(processor));
    }

    @Override
    public void createEntry(final FrequencyUnit unit) {
        repository.dispatchCreateRequest(buildCreateRequest(unit));
    }

    @Override
    public Set<FrequencyUnit> readFrequencyUnits(final QueryObject filter) {
        return repository.dispatchReadRequest(buildQueryRequest(filter)).getUnerasedSet();
    }

    @Override
    public void updateEntry(final FrequencyUnit oldUnit, final FrequencyUnit newUnit) {
        repository.dispatchUpdateRequest(buildUpdateRequest(oldUnit, newUnit));
    }

    @Override
    public void deleteEntry(final FrequencyUnit unit) {
        repository.dispatchDeleteRequest(buildDeleteRequest(unit));
    }

    @Override
    public void createEntry(final InstructionSet isa) {
        repository.dispatchCreateRequest(buildCreateRequest(isa));
    }

    @Override
    public Set<InstructionSet> readInstructionSets(final QueryObject filter) {
        return repository.dispatchReadRequest(buildQueryRequest(filter)).getUnerasedSet();
    }

    @Override
    public void updateEntry(final InstructionSet oldIsa, final InstructionSet newIsa) {
        repository.dispatchUpdateRequest(buildUpdateRequest(oldIsa, newIsa));
    }

    @Override
    public void deleteEntry(final InstructionSet isa) {
        repository.dispatchDeleteRequest(buildDeleteRequest(isa));
    }

    private SingleRequest buildCreateRequest(final Object payload) {
        return IdentifiableReferenceAssertor.assertCreationalForeignKeysValidity(new CreateRequest(payload));
    }

    private QueryRequest buildQueryRequest(final QueryObject filter) {
        return new ReadRequest(filter);
    }

    private BiRequest buildUpdateRequest(final Object oldObj, final Object newObj) {
        return IdentifiableReferenceAssertor.assertUpdativeForeignKeysValidity(new UpdateRequest(oldObj, newObj));
    }

    private SingleRequest buildDeleteRequest(final Object deletee) {
        return IdentifiableReferenceAssertor.assertDeletionForeignKeysValidity(new DeleteRequest(deletee));
    }

}
