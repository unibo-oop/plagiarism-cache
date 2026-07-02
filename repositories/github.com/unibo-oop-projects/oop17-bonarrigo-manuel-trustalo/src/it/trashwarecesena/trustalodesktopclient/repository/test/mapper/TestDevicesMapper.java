package it.trashwarecesena.trustalodesktopclient.repository.test.mapper; // NOPMD by Manuel Bonarrigo on 8/5/18 4:31 PM
//This is a unit testing class for a boundary

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.FixMethodOrder;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

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
import it.trashwarecesena.trustalodesktopclient.model.devices.concreteness.CaseImpl;
import it.trashwarecesena.trustalodesktopclient.model.devices.concreteness.HardDiskDriveImpl;
import it.trashwarecesena.trustalodesktopclient.model.devices.concreteness.LegalCategoryCompoundImpl;
import it.trashwarecesena.trustalodesktopclient.model.devices.concreteness.PrinterImpl;
import it.trashwarecesena.trustalodesktopclient.model.devices.concreteness.RandomAccessMemoryImpl;
import it.trashwarecesena.trustalodesktopclient.model.devices.concreteness.RefinedDeviceCompoundImpl;
import it.trashwarecesena.trustalodesktopclient.model.devices.concreteness.RefinedDeviceCompoundWithGenericImpl;
import it.trashwarecesena.trustalodesktopclient.model.devices.concreteness.RefinedDeviceImpl;
import it.trashwarecesena.trustalodesktopclient.model.devices.concreteness.ScreenImpl;
import it.trashwarecesena.trustalodesktopclient.model.people.TrashwareWorker;
import it.trashwarecesena.trustalodesktopclient.model.people.concreteness.PersonCategoryImpl;
import it.trashwarecesena.trustalodesktopclient.model.people.concreteness.TrashwareWorkerImpl;
import it.trashwarecesena.trustalodesktopclient.model.test.TestConstants;
import it.trashwarecesena.trustalodesktopclient.model.test.TestEntityConstants;
import it.trashwarecesena.trustalodesktopclient.model.test.TestIdentifiableConstants;
import it.trashwarecesena.trustalodesktopclient.repository.Persistence;
import it.trashwarecesena.trustalodesktopclient.repository.exception.DuplicateKeyValueException;
import it.trashwarecesena.trustalodesktopclient.repository.test.Persistences;
import it.trashwarecesena.trustalodesktopclient.repository.test.Queries;

/**
 * A test class for the device mapper boundary.
 * <p>
 * The scheme of every testing method is similar, even if it can not made
 * abstract due to the differences between every mapped entity.
 * 
 * <ul>
 * <li>The references needed by the tested entity are created upon the
 * persistence storage</li>
 * <li>The set retrieved by reading all the tested entity is asserted as
 * empty</li>
 * <li>Two instances known to be different are created</li>
 * <li>The set retrieved by reading all the tested entity is asserted as
 * containing two elements, referred from now on as <i>normal</i> and
 * <i>different</i></li>
 * <li>The equality of the two elements in the set is asserted to the two
 * references which were created. The behaviour is slightly different if the
 * entity are {@link it.trashwarecesena.trustalodesktopclient.model.Identifiable
 * Identifiable}, since they need to be fetched again and can not be be tested
 * against their original references</li>
 * <li><i>Different</i> is deleted, and <i>normal</i> is updated to hold the
 * same value of the <i>different</i> <i>entity</i></li>
 * <li>The set retrieved by reading all the tested entity is asserted as
 * containing only one element, which is later asserted to be what used to be
 * <i>normal</i> holding the value of <i>different</i></li>
 * <li>The updated entity is deleted</li>
 * <li>The storage is asserted as empty</li>
 * <li>All the references created to support the testing are deleted</li>
 * </ul>
 * 
 * @author Manuel Bonarrigo
 *
 */
@RunWith(JUnitPlatform.class)
@FixMethodOrder(MethodSorters.JVM)
public final class TestDevicesMapper {

    private static final String PRIVATO = "Privato";
    private final Persistence persistence = Persistences.retrieveFullyInstantiatedTestingPersistenceSystem();
    @SuppressWarnings("unused")
    private final ScreenResolution solveStaticCyclicDependency = 
        TestIdentifiableConstants.SAME_UNIDENTIFIED_SCREEN_RESOLUTION;

    /**
     * Tests the system capability to handle the CRUD operations on an {@link AspectRatio}.
     */
    @Test
    public void testPersistenceAspectRatio() {
        final AspectRatio normal = TestEntityConstants.RATIO;
        final AspectRatio different = TestEntityConstants.DIFFERENT_RATIO;

        assertTrue(persistence.readAspectRatio(Queries.getAll(AspectRatio.class)).isEmpty());

        persistence.createEntry(normal);
        assertTrue(persistence.readAspectRatio(Queries.getAll(AspectRatio.class)).contains(normal));
        assertThrows(DuplicateKeyValueException.class, () -> {
            persistence.createEntry(normal); 
        });

        persistence.createEntry(different);
        assertTrue(persistence.readAspectRatio(Queries.getAll(AspectRatio.class)).size() == 2);
        assertTrue(persistence.readAspectRatio(Queries.getAll(AspectRatio.class)).contains(normal));
        assertTrue(persistence.readAspectRatio(Queries.getAll(AspectRatio.class)).contains(different));
        assertThrows(DuplicateKeyValueException.class, () -> {
            persistence.updateEntry(normal, different);
        });

        persistence.deleteEntry(different);
        assertTrue(persistence.readAspectRatio(Queries.getAll(AspectRatio.class)).size() == 1);
        assertTrue(persistence.readAspectRatio(Queries.getAll(AspectRatio.class)).contains(normal));
        assertFalse(persistence.readAspectRatio(Queries.getAll(AspectRatio.class)).contains(different));

        persistence.updateEntry(normal, different);
        assertTrue(persistence.readAspectRatio(Queries.getAll(AspectRatio.class)).size() == 1);
        assertFalse(persistence.readAspectRatio(Queries.getAll(AspectRatio.class)).contains(normal));
        assertTrue(persistence.readAspectRatio(Queries.getAll(AspectRatio.class)).contains(different));

        persistence.deleteEntry(different);
        assertTrue(persistence.readAspectRatio(Queries.getAll(AspectRatio.class)).isEmpty());
    }

    /**
     * Tests the system capability to handle the CRUD operations on a {@link Case}.
     */
    @Test
    public void testPersistenceCase() {
        Case normal;
        Case different;

        persistence.createEntry(TestEntityConstants.DEV_CATEGORY);
        persistence.createEntry(TestEntityConstants.DIFFERENT_DEV_CATEGORY);
        persistence.createEntry(TestEntityConstants.VENDOR);
        persistence.createEntry(TestEntityConstants.DIFFERENT_VENDOR);
        persistence.createEntry(TestIdentifiableConstants.UNIDENTIFIED_DEVICE);
        persistence.createEntry(TestIdentifiableConstants.DIFFERENT_UNIDENTIFIED_DEVICE);
        persistence.createEntry(TestEntityConstants.COLOR);
        persistence.createEntry(TestEntityConstants.DIFFERENT_COLOR);

        assertTrue(persistence.readCase(Queries.getAll(Case.class)).isEmpty());

        normal = new CaseImpl(
                persistence.readGenericDevice(Queries.getTestFilter(TestIdentifiableConstants.UNIDENTIFIED_DEVICE))
                    .iterator().next(), 
                TestEntityConstants.COLOR);
        different = new CaseImpl(
                persistence.readGenericDevice(
                        Queries.getTestFilter(TestIdentifiableConstants.DIFFERENT_UNIDENTIFIED_DEVICE))
                                .iterator().next(), 
                TestEntityConstants.DIFFERENT_COLOR);

        persistence.createEntry(normal);
        persistence.createEntry(different);
        assertTrue(persistence.readCase(Queries.getAll(Case.class)).size() == 2);
        assertTrue(persistence.readCase(Queries.getAll(Case.class)).contains(normal));
        assertTrue(persistence.readCase(Queries.getAll(Case.class)).contains(different));
        assertThrows(DuplicateKeyValueException.class, () -> {
            persistence.createEntry(normal);
        });
        assertThrows(DuplicateKeyValueException.class, () -> {
            persistence.createEntry(different);
        });
        assertThrows(DuplicateKeyValueException.class, () -> {
            persistence.updateEntry(normal, different);
        });
        assertThrows(DuplicateKeyValueException.class, () -> {
            persistence.updateEntry(different, normal);
        });

        persistence.deleteEntry(different);
        assertTrue(persistence.readCase(Queries.getAll(Case.class)).size() == 1);
        assertTrue(persistence.readCase(Queries.getAll(Case.class)).contains(normal));
        assertFalse(persistence.readCase(Queries.getAll(Case.class)).contains(different));

        persistence.updateEntry(normal, different);
        assertTrue(persistence.readCase(Queries.getAll(Case.class)).size() == 1);
        assertFalse(persistence.readCase(Queries.getAll(Case.class)).contains(normal));
        assertTrue(persistence.readCase(Queries.getAll(Case.class)).contains(different));

        persistence.deleteEntry(different);
        assertTrue(persistence.readCase(Queries.getAll(Case.class)).isEmpty());

        persistence.deleteEntry(TestEntityConstants.DIFFERENT_COLOR);
        persistence.deleteEntry(TestEntityConstants.COLOR);
        persistence.deleteEntry(persistence.readGenericDevice(
                Queries.getTestFilter(TestIdentifiableConstants.DIFFERENT_UNIDENTIFIED_DEVICE)).iterator().next());
        persistence.deleteEntry(persistence.readGenericDevice(
                Queries.getTestFilter(TestIdentifiableConstants.UNIDENTIFIED_DEVICE)).iterator().next());
        persistence.deleteEntry(TestEntityConstants.DIFFERENT_VENDOR);
        persistence.deleteEntry(TestEntityConstants.VENDOR);
        persistence.deleteEntry(TestEntityConstants.DIFFERENT_DEV_CATEGORY);
        persistence.deleteEntry(TestEntityConstants.DEV_CATEGORY);
    }

    /**
     * Tests the system capability to handle the CRUD operations on an {@link Color}.
     */
    @Test
    public void testPersistenceColor() {
        final Color normal = TestEntityConstants.COLOR;
        final Color different = TestEntityConstants.DIFFERENT_COLOR;

        assertTrue(persistence.readColor(Queries.getAll(Color.class)).isEmpty());

        persistence.createEntry(normal);
        assertTrue(persistence.readColor(Queries.getAll(Color.class)).contains(normal));
        assertThrows(DuplicateKeyValueException.class, () -> {
            persistence.createEntry(normal); 
        });

        persistence.createEntry(different);
        assertTrue(persistence.readColor(Queries.getAll(Color.class)).size() == 2);
        assertTrue(persistence.readColor(Queries.getAll(Color.class)).contains(normal));
        assertTrue(persistence.readColor(Queries.getAll(Color.class)).contains(different));
        assertThrows(DuplicateKeyValueException.class, () -> {
            persistence.updateEntry(normal, different);
        });

        persistence.deleteEntry(different);
        assertTrue(persistence.readColor(Queries.getAll(Color.class)).size() == 1);
        assertTrue(persistence.readColor(Queries.getAll(Color.class)).contains(normal));
        assertFalse(persistence.readColor(Queries.getAll(Color.class)).contains(different));

        persistence.updateEntry(normal, different);
        assertTrue(persistence.readColor(Queries.getAll(Color.class)).size() == 1);
        assertFalse(persistence.readColor(Queries.getAll(Color.class)).contains(normal));
        assertTrue(persistence.readColor(Queries.getAll(Color.class)).contains(different));

        persistence.deleteEntry(different);
        assertTrue(persistence.readColor(Queries.getAll(Color.class)).isEmpty());
    }

    /**
     * Tests the system capability to handle the CRUD operations on an {@link DeviceCategory}.
     */
    @Test
    public void testPersistenceDeviceCategory() {
        final DeviceCategory normal = TestEntityConstants.DEV_CATEGORY;
        final DeviceCategory different = TestEntityConstants.DIFFERENT_DEV_CATEGORY;

        assertTrue(persistence.readDeviceCategory(Queries.getAll(DeviceCategory.class)).isEmpty());

        persistence.createEntry(normal);
        assertTrue(persistence.readDeviceCategory(Queries.getAll(DeviceCategory.class)).contains(normal));
        assertThrows(DuplicateKeyValueException.class, () -> {
            persistence.createEntry(normal); 
        });

        persistence.createEntry(different);
        assertTrue(persistence.readDeviceCategory(Queries.getAll(DeviceCategory.class)).size() == 2);
        assertTrue(persistence.readDeviceCategory(Queries.getAll(DeviceCategory.class)).contains(normal));
        assertTrue(persistence.readDeviceCategory(Queries.getAll(DeviceCategory.class)).contains(different));
        assertThrows(DuplicateKeyValueException.class, () -> {
            persistence.updateEntry(normal, different);
        });

        persistence.deleteEntry(different);
        assertTrue(persistence.readDeviceCategory(Queries.getAll(DeviceCategory.class)).size() == 1);
        assertTrue(persistence.readDeviceCategory(Queries.getAll(DeviceCategory.class)).contains(normal));
        assertFalse(persistence.readDeviceCategory(Queries.getAll(DeviceCategory.class)).contains(different));

        persistence.updateEntry(normal, different);
        assertTrue(persistence.readDeviceCategory(Queries.getAll(DeviceCategory.class)).size() == 1);
        assertFalse(persistence.readDeviceCategory(Queries.getAll(DeviceCategory.class)).contains(normal));
        assertTrue(persistence.readDeviceCategory(Queries.getAll(DeviceCategory.class)).contains(different));

        persistence.deleteEntry(different);
        assertTrue(persistence.readDeviceCategory(Queries.getAll(DeviceCategory.class)).isEmpty());
    }

    /**
     * Tests the system capability to handle the CRUD operations on an {@link DeviceWorkProgress}.
     */
    @Test
    public void testPersistenceDeviceWorkProgress() {
        final DeviceWorkProgress normal = TestEntityConstants.WORK_PROGRESS;
        final DeviceWorkProgress different = TestEntityConstants.DIFFERENT_WORK_PROGRESS;

        assertTrue(persistence.readDeviceWorkProgress(Queries.getAll(DeviceWorkProgress.class)).isEmpty());

        persistence.createEntry(normal);
        assertTrue(persistence.readDeviceWorkProgress(Queries.getAll(DeviceWorkProgress.class)).contains(normal));
        assertThrows(DuplicateKeyValueException.class, () -> {
            persistence.createEntry(normal); 
        });

        persistence.createEntry(different);
        assertTrue(persistence.readDeviceWorkProgress(Queries.getAll(DeviceWorkProgress.class)).size() == 2);
        assertTrue(persistence.readDeviceWorkProgress(Queries.getAll(DeviceWorkProgress.class)).contains(normal));
        assertTrue(persistence.readDeviceWorkProgress(Queries.getAll(DeviceWorkProgress.class)).contains(different));
        assertThrows(DuplicateKeyValueException.class, () -> {
            persistence.updateEntry(normal, different);
        });

        persistence.deleteEntry(different);
        assertTrue(persistence.readDeviceWorkProgress(Queries.getAll(DeviceWorkProgress.class)).size() == 1);
        assertTrue(persistence.readDeviceWorkProgress(Queries.getAll(DeviceWorkProgress.class)).contains(normal));
        assertFalse(persistence.readDeviceWorkProgress(Queries.getAll(DeviceWorkProgress.class)).contains(different));

        persistence.updateEntry(normal, different);
        assertTrue(persistence.readDeviceWorkProgress(Queries.getAll(DeviceWorkProgress.class)).size() == 1);
        assertFalse(persistence.readDeviceWorkProgress(Queries.getAll(DeviceWorkProgress.class)).contains(normal));
        assertTrue(persistence.readDeviceWorkProgress(Queries.getAll(DeviceWorkProgress.class)).contains(different));

        persistence.deleteEntry(different);
        assertTrue(persistence.readDeviceWorkProgress(Queries.getAll(DeviceWorkProgress.class)).isEmpty());
    }

    /**
     * Tests the system capability to handle the CRUD operations on an {@link DigitalInformationUnit}.
     */
    @Test
    public void testPersistenceDigitalInformationUnit() {
        final DigitalInformationUnit normal = TestEntityConstants.INF_UNIT;
        final DigitalInformationUnit different = TestEntityConstants.INF_DIFFERENT_UNIT;

        assertTrue(persistence.readDigitalInformationUnit(Queries.getAll(DigitalInformationUnit.class)).isEmpty());

        persistence.createEntry(normal);
        assertTrue(persistence.readDigitalInformationUnit(
                Queries.getAll(DigitalInformationUnit.class)).contains(normal));
        assertThrows(DuplicateKeyValueException.class, () -> {
            persistence.createEntry(normal); 
        });

        persistence.createEntry(different);
        assertTrue(persistence.readDigitalInformationUnit(
                Queries.getAll(DigitalInformationUnit.class)).size() == 2);
        assertTrue(persistence.readDigitalInformationUnit(
                Queries.getAll(DigitalInformationUnit.class)).contains(normal));
        assertTrue(persistence.readDigitalInformationUnit(
                Queries.getAll(DigitalInformationUnit.class)).contains(different));
        assertThrows(DuplicateKeyValueException.class, () -> {
            persistence.updateEntry(normal, different);
        });

        persistence.deleteEntry(different);
        assertTrue(persistence.readDigitalInformationUnit(Queries.getAll(DigitalInformationUnit.class)).size() == 1);
        assertTrue(persistence.readDigitalInformationUnit(
                Queries.getAll(DigitalInformationUnit.class)).contains(normal));
        assertFalse(persistence.readDigitalInformationUnit(
                Queries.getAll(DigitalInformationUnit.class)).contains(different));

        persistence.updateEntry(normal, different);
        assertTrue(persistence.readDigitalInformationUnit(Queries.getAll(DigitalInformationUnit.class)).size() == 1);
        assertFalse(persistence.readDigitalInformationUnit(
                Queries.getAll(DigitalInformationUnit.class)).contains(normal));
        assertTrue(persistence.readDigitalInformationUnit(
                Queries.getAll(DigitalInformationUnit.class)).contains(different));

        persistence.deleteEntry(different);
        assertTrue(persistence.readDigitalInformationUnit(Queries.getAll(DigitalInformationUnit.class)).isEmpty());
    }

    /**
     * Tests the system capability to handle the CRUD operations on an {@link GenericDevice}.
     */
    @Test
    public void testPersistenceGenericDevice() {
        final GenericDevice normal = TestIdentifiableConstants.UNIDENTIFIED_DEVICE;
        final GenericDevice different = TestIdentifiableConstants.DIFFERENT_UNIDENTIFIED_DEVICE;
        GenericDevice identifiedNormal;
        GenericDevice identifiedDifferent;

        persistence.createEntry(TestEntityConstants.DEV_CATEGORY);
        persistence.createEntry(TestEntityConstants.DIFFERENT_DEV_CATEGORY);
        persistence.createEntry(TestEntityConstants.VENDOR);
        persistence.createEntry(TestEntityConstants.DIFFERENT_VENDOR);

        assertTrue(persistence.readGenericDevice(Queries.getAll(GenericDevice.class)).isEmpty());

        persistence.createEntry(normal);
        persistence.createEntry(different);
        assertTrue(persistence.readGenericDevice(Queries.getAll(GenericDevice.class)).size() == 2);
        assertFalse(persistence.readGenericDevice(Queries.getAll(GenericDevice.class)).contains(normal));
        assertFalse(persistence.readGenericDevice(Queries.getAll(GenericDevice.class)).contains(different));
        assertThrows(IllegalArgumentException.class, () -> {
            persistence.updateEntry(normal, different);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            persistence.updateEntry(different, normal);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            persistence.deleteEntry(normal);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            persistence.deleteEntry(different);
        });

        identifiedNormal = persistence.readGenericDevice(Queries.getTestFilter(normal)).iterator().next();
        identifiedDifferent = persistence.readGenericDevice(Queries.getTestFilter(different)).iterator().next();
        assertTrue(persistence.readGenericDevice(Queries.getAll(GenericDevice.class)).contains(identifiedNormal));
        assertTrue(persistence.readGenericDevice(Queries.getAll(GenericDevice.class)).contains(identifiedDifferent));

        persistence.deleteEntry(identifiedDifferent);
        assertTrue(persistence.readGenericDevice(Queries.getAll(GenericDevice.class)).size() == 1);
        assertTrue(persistence.readGenericDevice(Queries.getAll(GenericDevice.class)).contains(identifiedNormal));
        assertFalse(persistence.readGenericDevice(Queries.getAll(GenericDevice.class)).contains(identifiedDifferent));

        persistence.updateEntry(identifiedNormal, TestIdentifiableConstants.DIFFERENT_UNIDENTIFIED_DEVICE);
        assertTrue(persistence.readGenericDevice(Queries.getAll(GenericDevice.class)).size() == 1);
        assertFalse(persistence.readGenericDevice(Queries.getAll(GenericDevice.class)).contains(identifiedNormal));
        assertFalse(persistence.readGenericDevice(Queries.getAll(GenericDevice.class)).contains(identifiedDifferent));

        identifiedDifferent = persistence.readGenericDevice(Queries.getTestFilter(different)).iterator().next();
        assertTrue(persistence.readGenericDevice(Queries.getAll(GenericDevice.class)).size() == 1);
        assertTrue(persistence.readGenericDevice(Queries.getAll(GenericDevice.class)).contains(identifiedDifferent));

        persistence.deleteEntry(identifiedDifferent);
        assertTrue(persistence.readGenericDevice(Queries.getAll(GenericDevice.class)).isEmpty());

        persistence.deleteEntry(TestEntityConstants.DIFFERENT_VENDOR);
        persistence.deleteEntry(TestEntityConstants.VENDOR);
        persistence.deleteEntry(TestEntityConstants.DIFFERENT_DEV_CATEGORY);
        persistence.deleteEntry(TestEntityConstants.DEV_CATEGORY);
    }

    /**
     * Tests the system capability to handle the CRUD operations on an {@link HardDiskDrive}.
     */
    @Test
    public void testPersistenceHardDiskDrive() {
        HardDiskDrive normal;
        HardDiskDrive different;

        persistence.createEntry(TestEntityConstants.DEV_CATEGORY);
        persistence.createEntry(TestEntityConstants.DIFFERENT_DEV_CATEGORY);
        persistence.createEntry(TestEntityConstants.VENDOR);
        persistence.createEntry(TestEntityConstants.DIFFERENT_VENDOR);
        persistence.createEntry(TestIdentifiableConstants.UNIDENTIFIED_DEVICE);
        persistence.createEntry(TestIdentifiableConstants.DIFFERENT_UNIDENTIFIED_DEVICE);
        persistence.createEntry(TestEntityConstants.INF_UNIT);
        persistence.createEntry(TestEntityConstants.INF_DIFFERENT_UNIT);

        assertTrue(persistence.readHardDiskDrive(Queries.getAll(HardDiskDrive.class)).isEmpty());

        normal = new HardDiskDriveImpl(
                    persistence.readGenericDevice(Queries.getTestFilter(TestIdentifiableConstants.UNIDENTIFIED_DEVICE))
                        .iterator().next(),
                    TestConstants.A_POSITIVE_INTEGER,
                    TestEntityConstants.INF_UNIT);

        different = new HardDiskDriveImpl(
                    persistence.readGenericDevice(
                            Queries.getTestFilter(TestIdentifiableConstants.DIFFERENT_UNIDENTIFIED_DEVICE))
                                    .iterator().next(), 
                    TestConstants.A_DIFFERENT_POSITIVE_INTEGER,
                    TestEntityConstants.INF_DIFFERENT_UNIT);

        persistence.createEntry(normal);
        persistence.createEntry(different);
        assertTrue(persistence.readHardDiskDrive(Queries.getAll(HardDiskDrive.class)).size() == 2);
        assertTrue(persistence.readHardDiskDrive(Queries.getAll(HardDiskDrive.class)).contains(normal));
        assertTrue(persistence.readHardDiskDrive(Queries.getAll(HardDiskDrive.class)).contains(different));
        assertThrows(DuplicateKeyValueException.class, () -> {
            persistence.createEntry(normal);
        });
        assertThrows(DuplicateKeyValueException.class, () -> {
            persistence.createEntry(different);
        });
        assertThrows(DuplicateKeyValueException.class, () -> {
            persistence.updateEntry(normal, different);
        });
        assertThrows(DuplicateKeyValueException.class, () -> {
            persistence.updateEntry(different, normal);
        });

        persistence.deleteEntry(different);
        assertTrue(persistence.readHardDiskDrive(Queries.getAll(HardDiskDrive.class)).size() == 1);
        assertTrue(persistence.readHardDiskDrive(Queries.getAll(HardDiskDrive.class)).contains(normal));
        assertFalse(persistence.readHardDiskDrive(Queries.getAll(HardDiskDrive.class)).contains(different));

        persistence.updateEntry(normal, different);
        assertTrue(persistence.readHardDiskDrive(Queries.getAll(HardDiskDrive.class)).size() == 1);
        assertFalse(persistence.readHardDiskDrive(Queries.getAll(HardDiskDrive.class)).contains(normal));
        assertTrue(persistence.readHardDiskDrive(Queries.getAll(HardDiskDrive.class)).contains(different));

        persistence.deleteEntry(different);
        assertTrue(persistence.readHardDiskDrive(Queries.getAll(HardDiskDrive.class)).isEmpty());

        persistence.deleteEntry(TestEntityConstants.INF_DIFFERENT_UNIT);
        persistence.deleteEntry(TestEntityConstants.INF_UNIT);
        persistence.deleteEntry(persistence.readGenericDevice(
                Queries.getTestFilter(TestIdentifiableConstants.DIFFERENT_UNIDENTIFIED_DEVICE)).iterator().next());
        persistence.deleteEntry(persistence.readGenericDevice(
                Queries.getTestFilter(TestIdentifiableConstants.UNIDENTIFIED_DEVICE)).iterator().next());
        persistence.deleteEntry(TestEntityConstants.DIFFERENT_VENDOR);
        persistence.deleteEntry(TestEntityConstants.VENDOR);
        persistence.deleteEntry(TestEntityConstants.DIFFERENT_DEV_CATEGORY);
        persistence.deleteEntry(TestEntityConstants.DEV_CATEGORY);
    }

    /**
     * Tests the system capability to handle the CRUD operations on an {@link LegalCategoryCompound}.
     */
    @Test
    public void testPersistenceLegalCategoryCompound() {

        final LegalCategoryCompound normal = 
                new LegalCategoryCompoundImpl(TestEntityConstants.DEV_CATEGORY, 
                        TestEntityConstants.DEV_CATEGORY);
        final LegalCategoryCompound different = 
                new LegalCategoryCompoundImpl(TestEntityConstants.DIFFERENT_DEV_CATEGORY, 
                        TestEntityConstants.DIFFERENT_DEV_CATEGORY);

        persistence.createEntry(TestEntityConstants.DEV_CATEGORY);
        persistence.createEntry(TestEntityConstants.DIFFERENT_DEV_CATEGORY);

        assertTrue(persistence.readLegalCategoryCompound(Queries.getAll(LegalCategoryCompound.class)).isEmpty());

        persistence.createEntry(normal);
        assertTrue(persistence.readLegalCategoryCompound(Queries.getAll(LegalCategoryCompound.class)).contains(normal));
        assertThrows(DuplicateKeyValueException.class, () -> {
            persistence.createEntry(normal); 
        });

        persistence.createEntry(different);
        assertTrue(persistence.readLegalCategoryCompound(Queries.getAll(LegalCategoryCompound.class)).size() == 2);
        assertTrue(persistence.readLegalCategoryCompound(Queries.getAll(LegalCategoryCompound.class)).contains(normal));
        assertTrue(persistence.readLegalCategoryCompound(Queries.getAll(LegalCategoryCompound.class))
                .contains(different));
        assertThrows(DuplicateKeyValueException.class, () -> {
            persistence.updateEntry(normal, different);
        });

        persistence.deleteEntry(different);
        assertTrue(persistence.readLegalCategoryCompound(Queries.getAll(LegalCategoryCompound.class)).size() == 1);
        assertTrue(persistence.readLegalCategoryCompound(Queries.getAll(LegalCategoryCompound.class)).contains(normal));
        assertFalse(persistence.readLegalCategoryCompound(Queries.getAll(LegalCategoryCompound.class))
                .contains(different));

        persistence.updateEntry(normal, different);
        assertTrue(persistence.readLegalCategoryCompound(Queries.getAll(LegalCategoryCompound.class)).size() == 1);
        assertFalse(persistence.readLegalCategoryCompound(Queries.getAll(LegalCategoryCompound.class))
                .contains(normal));
        assertTrue(persistence.readLegalCategoryCompound(Queries.getAll(LegalCategoryCompound.class))
                .contains(different));

        persistence.deleteEntry(different);
        assertTrue(persistence.readLegalCategoryCompound(Queries.getAll(LegalCategoryCompound.class)).isEmpty());

        persistence.deleteEntry(TestEntityConstants.DIFFERENT_DEV_CATEGORY);
        persistence.deleteEntry(TestEntityConstants.DEV_CATEGORY);
    }

    /**
     * Tests the system capability to handle the CRUD operations on an {@link Printer}.
     */
    @Test
    public void testPersistencePrinter() {
        Printer normal;
        Printer different;

        persistence.createEntry(TestEntityConstants.DEV_CATEGORY);
        persistence.createEntry(TestEntityConstants.DIFFERENT_DEV_CATEGORY);
        persistence.createEntry(TestEntityConstants.VENDOR);
        persistence.createEntry(TestEntityConstants.DIFFERENT_VENDOR);
        persistence.createEntry(TestIdentifiableConstants.UNIDENTIFIED_DEVICE);
        persistence.createEntry(TestIdentifiableConstants.DIFFERENT_UNIDENTIFIED_DEVICE);
        persistence.createEntry(TestEntityConstants.CATEGORY);
        persistence.createEntry(TestEntityConstants.DIFFERENT_CATEGORY);

        assertTrue(persistence.readPrinter(Queries.getAll(Printer.class)).isEmpty());

        normal = new PrinterImpl(
                    persistence.readGenericDevice(Queries.getTestFilter(TestIdentifiableConstants.UNIDENTIFIED_DEVICE))
                        .iterator().next(),
                        TestEntityConstants.CATEGORY,
                        TestConstants.A_POSITIVE_INTEGER);

        different = new PrinterImpl(
                    persistence.readGenericDevice(
                        Queries.getTestFilter(TestIdentifiableConstants.DIFFERENT_UNIDENTIFIED_DEVICE))
                            .iterator().next(),
                    TestEntityConstants.DIFFERENT_CATEGORY,
                    TestConstants.A_DIFFERENT_POSITIVE_INTEGER);

        persistence.createEntry(normal);
        persistence.createEntry(different);
        assertTrue(persistence.readPrinter(Queries.getAll(Printer.class)).size() == 2);
        assertTrue(persistence.readPrinter(Queries.getAll(Printer.class)).contains(normal));
        assertTrue(persistence.readPrinter(Queries.getAll(Printer.class)).contains(different));
        assertThrows(DuplicateKeyValueException.class, () -> {
            persistence.createEntry(normal);
        });
        assertThrows(DuplicateKeyValueException.class, () -> {
            persistence.createEntry(different);
        });
        assertThrows(DuplicateKeyValueException.class, () -> {
            persistence.updateEntry(normal, different);
        });
        assertThrows(DuplicateKeyValueException.class, () -> {
            persistence.updateEntry(different, normal);
        });

        persistence.deleteEntry(different);
        assertTrue(persistence.readPrinter(Queries.getAll(Printer.class)).size() == 1);
        assertTrue(persistence.readPrinter(Queries.getAll(Printer.class)).contains(normal));
        assertFalse(persistence.readPrinter(Queries.getAll(Printer.class)).contains(different));

        persistence.updateEntry(normal, different);
        assertTrue(persistence.readPrinter(Queries.getAll(Printer.class)).size() == 1);
        assertFalse(persistence.readPrinter(Queries.getAll(Printer.class)).contains(normal));
        assertTrue(persistence.readPrinter(Queries.getAll(Printer.class)).contains(different));

        persistence.deleteEntry(different);
        assertTrue(persistence.readPrinter(Queries.getAll(Printer.class)).isEmpty());

        persistence.deleteEntry(TestEntityConstants.DIFFERENT_CATEGORY);
        persistence.deleteEntry(TestEntityConstants.CATEGORY);
        persistence.deleteEntry(persistence.readGenericDevice(
                Queries.getTestFilter(TestIdentifiableConstants.DIFFERENT_UNIDENTIFIED_DEVICE)).iterator().next());
        persistence.deleteEntry(persistence.readGenericDevice(
                Queries.getTestFilter(TestIdentifiableConstants.UNIDENTIFIED_DEVICE)).iterator().next());
        persistence.deleteEntry(TestEntityConstants.DIFFERENT_VENDOR);
        persistence.deleteEntry(TestEntityConstants.VENDOR);
        persistence.deleteEntry(TestEntityConstants.DIFFERENT_DEV_CATEGORY);
        persistence.deleteEntry(TestEntityConstants.DEV_CATEGORY);
    }

    /**
     * Tests the system capability to handle the CRUD operations on an {@link PrinterCategory}.
     */
    @Test
    public void testPersistencePrinterCategory() {
        final PrinterCategory normal = TestEntityConstants.CATEGORY;
        final PrinterCategory different = TestEntityConstants.DIFFERENT_CATEGORY;

        assertTrue(persistence.readPrinterCategory(Queries.getAll(PrinterCategory.class)).isEmpty());

        persistence.createEntry(normal);
        assertTrue(persistence.readPrinterCategory(Queries.getAll(PrinterCategory.class)).contains(normal));
        assertThrows(DuplicateKeyValueException.class, () -> {
            persistence.createEntry(normal); 
        });

        persistence.createEntry(different);
        assertTrue(persistence.readPrinterCategory(Queries.getAll(PrinterCategory.class)).size() == 2);
        assertTrue(persistence.readPrinterCategory(Queries.getAll(PrinterCategory.class)).contains(normal));
        assertTrue(persistence.readPrinterCategory(Queries.getAll(PrinterCategory.class)).contains(different));
        assertThrows(DuplicateKeyValueException.class, () -> {
            persistence.updateEntry(normal, different);
        });

        persistence.deleteEntry(different);
        assertTrue(persistence.readPrinterCategory(Queries.getAll(PrinterCategory.class)).size() == 1);
        assertTrue(persistence.readPrinterCategory(Queries.getAll(PrinterCategory.class)).contains(normal));
        assertFalse(persistence.readPrinterCategory(Queries.getAll(PrinterCategory.class)).contains(different));

        persistence.updateEntry(normal, different);
        assertTrue(persistence.readPrinterCategory(Queries.getAll(PrinterCategory.class)).size() == 1);
        assertFalse(persistence.readPrinterCategory(Queries.getAll(PrinterCategory.class)).contains(normal));
        assertTrue(persistence.readPrinterCategory(Queries.getAll(PrinterCategory.class)).contains(different));

        persistence.deleteEntry(different);
        assertTrue(persistence.readPrinterCategory(Queries.getAll(PrinterCategory.class)).isEmpty());
    }

    /**
     * Tests the system capability to handle the CRUD operations on an {@link RandomAccessMemory}.
     */
    @Test
    public void testPersistenceRandomAccessMemory() {
        RandomAccessMemory normal;
        RandomAccessMemory different;

        persistence.createEntry(TestEntityConstants.DEV_CATEGORY);
        persistence.createEntry(TestEntityConstants.DIFFERENT_DEV_CATEGORY);
        persistence.createEntry(TestEntityConstants.VENDOR);
        persistence.createEntry(TestEntityConstants.DIFFERENT_VENDOR);
        persistence.createEntry(TestIdentifiableConstants.UNIDENTIFIED_DEVICE);
        persistence.createEntry(TestIdentifiableConstants.DIFFERENT_UNIDENTIFIED_DEVICE);
        persistence.createEntry(TestEntityConstants.INF_UNIT);
        persistence.createEntry(TestEntityConstants.INF_DIFFERENT_UNIT);

        assertTrue(persistence.readRandomAccessMemory(Queries.getAll(RandomAccessMemory.class)).isEmpty());

        normal = new RandomAccessMemoryImpl(
                    persistence.readGenericDevice(Queries.getTestFilter(TestIdentifiableConstants.UNIDENTIFIED_DEVICE))
                        .iterator().next(),
                    TestConstants.A_POSITIVE_INTEGER,
                    TestEntityConstants.INF_UNIT);

        different = new RandomAccessMemoryImpl(
                    persistence.readGenericDevice(
                            Queries.getTestFilter(TestIdentifiableConstants.DIFFERENT_UNIDENTIFIED_DEVICE))
                                    .iterator().next(), 
                    TestConstants.A_DIFFERENT_POSITIVE_INTEGER,
                    TestEntityConstants.INF_DIFFERENT_UNIT);

        persistence.createEntry(normal);
        persistence.createEntry(different);
        assertTrue(persistence.readRandomAccessMemory(Queries.getAll(RandomAccessMemory.class)).size() == 2);
        assertTrue(persistence.readRandomAccessMemory(Queries.getAll(RandomAccessMemory.class)).contains(normal));
        assertTrue(persistence.readRandomAccessMemory(Queries.getAll(RandomAccessMemory.class)).contains(different));
        assertThrows(DuplicateKeyValueException.class, () -> {
            persistence.createEntry(normal);
        });
        assertThrows(DuplicateKeyValueException.class, () -> {
            persistence.createEntry(different);
        });
        assertThrows(DuplicateKeyValueException.class, () -> {
            persistence.updateEntry(normal, different);
        });
        assertThrows(DuplicateKeyValueException.class, () -> {
            persistence.updateEntry(different, normal);
        });

        persistence.deleteEntry(different);
        assertTrue(persistence.readRandomAccessMemory(Queries.getAll(RandomAccessMemory.class)).size() == 1);
        assertTrue(persistence.readRandomAccessMemory(Queries.getAll(RandomAccessMemory.class)).contains(normal));
        assertFalse(persistence.readRandomAccessMemory(Queries.getAll(RandomAccessMemory.class)).contains(different));

        persistence.updateEntry(normal, different);
        assertTrue(persistence.readRandomAccessMemory(Queries.getAll(RandomAccessMemory.class)).size() == 1);
        assertFalse(persistence.readRandomAccessMemory(Queries.getAll(RandomAccessMemory.class)).contains(normal));
        assertTrue(persistence.readRandomAccessMemory(Queries.getAll(RandomAccessMemory.class)).contains(different));

        persistence.deleteEntry(different);
        assertTrue(persistence.readRandomAccessMemory(Queries.getAll(RandomAccessMemory.class)).isEmpty());

        persistence.deleteEntry(TestEntityConstants.INF_DIFFERENT_UNIT);
        persistence.deleteEntry(TestEntityConstants.INF_UNIT);
        persistence.deleteEntry(persistence.readGenericDevice(
                Queries.getTestFilter(TestIdentifiableConstants.DIFFERENT_UNIDENTIFIED_DEVICE)).iterator().next());
        persistence.deleteEntry(persistence.readGenericDevice(
                Queries.getTestFilter(TestIdentifiableConstants.UNIDENTIFIED_DEVICE)).iterator().next());
        persistence.deleteEntry(TestEntityConstants.DIFFERENT_VENDOR);
        persistence.deleteEntry(TestEntityConstants.VENDOR);
        persistence.deleteEntry(TestEntityConstants.DIFFERENT_DEV_CATEGORY);
        persistence.deleteEntry(TestEntityConstants.DEV_CATEGORY);
    }

    /**
     * Tests the system capability to handle the CRUD operations on an {@link RefinedDevice}.
     */
    @Test
    public void testPersistenceRefinedDevice() {
        RefinedDevice normal;
        RefinedDevice different;
        RefinedDevice identifiedNormal;
        RefinedDevice identifiedDifferent;

        TrashwareWorker worker;
        TrashwareWorker differentWorker;

        persistence.createEntry(new PersonCategoryImpl(PRIVATO));

        persistence.createEntry(TestEntityConstants.WORKER_CATEGORY);
        persistence.createEntry(TestEntityConstants.DIFFERENT_WORKER_CATEGORY);

        persistence.createEntry(TestIdentifiableConstants.UNIDENTIFIED_PH_PERSON);
        persistence.createEntry(TestIdentifiableConstants.DIFFERENT_UNIDENTIFIED_PH_PERSON);

        worker = new TrashwareWorkerImpl(
                    persistence.readPeople(Queries.getTestFilter(TestIdentifiableConstants.UNIDENTIFIED_PH_PERSON))
                        .iterator().next(),
                    TestEntityConstants.WORKER_CATEGORY, true);
        persistence.createEntry(worker);
        differentWorker = new TrashwareWorkerImpl(
                            persistence.readPeople(
                                    Queries.getTestFilter(TestIdentifiableConstants.DIFFERENT_UNIDENTIFIED_PH_PERSON))
                                        .iterator().next(),
                            TestEntityConstants.DIFFERENT_WORKER_CATEGORY, true);
        persistence.createEntry(differentWorker);

        persistence.createEntry(TestEntityConstants.WORK_PROGRESS);
        persistence.createEntry(TestEntityConstants.DIFFERENT_WORK_PROGRESS);

        persistence.createEntry(TestEntityConstants.DEV_CATEGORY);
        persistence.createEntry(TestEntityConstants.DIFFERENT_DEV_CATEGORY);

        persistence.createEntry(TestEntityConstants.VENDOR);
        persistence.createEntry(TestEntityConstants.DIFFERENT_VENDOR);

        persistence.createEntry(TestIdentifiableConstants.UNIDENTIFIED_DEVICE);
        persistence.createEntry(TestIdentifiableConstants.DIFFERENT_UNIDENTIFIED_DEVICE);

        normal = new RefinedDeviceImpl.Builder()
                    .categoryDeviceId(TestConstants.A_POSITIVE_INTEGER)
                    .deviceCategory(TestEntityConstants.DEV_CATEGORY)
                    .refining(persistence.readGenericDevice(
                            Queries.getTestFilter(TestIdentifiableConstants.UNIDENTIFIED_DEVICE)).iterator().next())
                    .progress(TestEntityConstants.WORK_PROGRESS)
                    .available(true)
                    .lastCommitter(worker)
                    .lastUpdate(TestConstants.DATE)
                    .annotations(TestConstants.A_STRING)
                .build();

        different = new RefinedDeviceImpl.Builder()
                        .categoryDeviceId(TestConstants.A_DIFFERENT_POSITIVE_INTEGER)
                        .deviceCategory(TestEntityConstants.DIFFERENT_DEV_CATEGORY)
                        .refining(persistence.readGenericDevice(
                                Queries.getTestFilter(
                                        TestIdentifiableConstants.DIFFERENT_UNIDENTIFIED_DEVICE)).iterator().next())
                        .progress(TestEntityConstants.DIFFERENT_WORK_PROGRESS)
                        .available(true)
                        .lastCommitter(differentWorker)
                        .lastUpdate(TestConstants.DIFFERENT_DATE)
                        .annotations(TestConstants.A_DIFFERENT_STRING)
                    .build();

        /*
         * Test start.
         */

        assertTrue(persistence.readRefinedDevice(Queries.getAll(RefinedDevice.class)).isEmpty());

        persistence.createEntry(normal);
        persistence.createEntry(different);
        assertTrue(persistence.readRefinedDevice(Queries.getAll(RefinedDevice.class)).size() == 2);
        assertFalse(persistence.readRefinedDevice(Queries.getAll(RefinedDevice.class)).contains(normal));
        assertFalse(persistence.readRefinedDevice(Queries.getAll(RefinedDevice.class)).contains(different));
        assertThrows(IllegalArgumentException.class, () -> {
            persistence.updateEntry(normal, different);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            persistence.updateEntry(different, normal);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            persistence.deleteEntry(normal);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            persistence.deleteEntry(different);
        });

        identifiedNormal = persistence.readRefinedDevice(Queries.getTestFilter(normal)).iterator().next();
        identifiedDifferent = persistence.readRefinedDevice(Queries.getTestFilter(different)).iterator().next();

        assertTrue(persistence.readRefinedDevice(Queries.getAll(RefinedDevice.class)).contains(identifiedNormal));
        assertTrue(persistence.readRefinedDevice(Queries.getAll(RefinedDevice.class)).contains(identifiedDifferent));

        persistence.deleteEntry(identifiedDifferent);
        assertTrue(persistence.readRefinedDevice(Queries.getAll(RefinedDevice.class)).size() == 1);
        assertTrue(persistence.readRefinedDevice(Queries.getAll(RefinedDevice.class)).contains(identifiedNormal));
        assertFalse(persistence.readRefinedDevice(Queries.getAll(RefinedDevice.class)).contains(identifiedDifferent));

        persistence.updateEntry(identifiedNormal, identifiedDifferent);
        assertTrue(persistence.readRefinedDevice(Queries.getAll(RefinedDevice.class)).size() == 1);
        assertFalse(persistence.readRefinedDevice(Queries.getAll(RefinedDevice.class)).contains(identifiedNormal));
        assertFalse(persistence.readRefinedDevice(Queries.getAll(RefinedDevice.class)).contains(identifiedDifferent));

        identifiedDifferent = persistence.readRefinedDevice(Queries.getTestFilter(different)).iterator().next();
        assertTrue(persistence.readRefinedDevice(Queries.getAll(RefinedDevice.class)).size() == 1);
        assertTrue(persistence.readRefinedDevice(Queries.getAll(RefinedDevice.class)).contains(identifiedDifferent));

        persistence.deleteEntry(identifiedDifferent);
        assertTrue(persistence.readRefinedDevice(Queries.getAll(RefinedDevice.class)).isEmpty());

        /*
         * Test end.
         */

        persistence.deleteEntry(
                persistence.readGenericDevice(
                        Queries.getTestFilter(TestIdentifiableConstants.DIFFERENT_UNIDENTIFIED_DEVICE))
                            .iterator().next());
        persistence.deleteEntry(
                persistence.readGenericDevice(
                        Queries.getTestFilter(TestIdentifiableConstants.UNIDENTIFIED_DEVICE)).iterator().next());

        persistence.deleteEntry(TestEntityConstants.DIFFERENT_VENDOR);
        persistence.deleteEntry(TestEntityConstants.VENDOR);

        persistence.deleteEntry(TestEntityConstants.DIFFERENT_DEV_CATEGORY);
        persistence.deleteEntry(TestEntityConstants.DEV_CATEGORY);

        persistence.deleteEntry(TestEntityConstants.DIFFERENT_WORK_PROGRESS);
        persistence.deleteEntry(TestEntityConstants.WORK_PROGRESS);

        persistence.deleteEntry(new TrashwareWorkerImpl(
                persistence.readPeople(
                        Queries.getTestFilter(TestIdentifiableConstants.DIFFERENT_UNIDENTIFIED_PH_PERSON))
                            .iterator().next(),
                TestEntityConstants.DIFFERENT_WORKER_CATEGORY, true));
        persistence.deleteEntry(new TrashwareWorkerImpl(
                persistence.readPeople(Queries.getTestFilter(TestIdentifiableConstants.UNIDENTIFIED_PH_PERSON))
                    .iterator().next(),
                TestEntityConstants.WORKER_CATEGORY, true));

        persistence.deleteEntry(persistence.readPeople(
                Queries.getTestFilter(TestIdentifiableConstants.DIFFERENT_UNIDENTIFIED_PH_PERSON)).iterator().next());
        persistence.deleteEntry(persistence.readPeople(
                Queries.getTestFilter(TestIdentifiableConstants.UNIDENTIFIED_PH_PERSON)).iterator().next());

        persistence.deleteEntry(TestEntityConstants.DIFFERENT_WORKER_CATEGORY);
        persistence.deleteEntry(TestEntityConstants.WORKER_CATEGORY);

        persistence.deleteEntry(new PersonCategoryImpl(PRIVATO));
    }

    /**
     * Tests the system capability to handle the CRUD operations on an {@link RefinedDeviceCompound}.
     */
    @Test
    public void testPersistenceRefinedCompound() {

        RefinedDeviceCompound normal;
        RefinedDeviceCompound different;
        RefinedDevice compound;
        RefinedDevice component;
        TrashwareWorker worker;
        TrashwareWorker differentWorker;

        persistence.createEntry(new PersonCategoryImpl(PRIVATO));

        persistence.createEntry(TestEntityConstants.WORKER_CATEGORY);
        persistence.createEntry(TestEntityConstants.DIFFERENT_WORKER_CATEGORY);

        persistence.createEntry(TestIdentifiableConstants.UNIDENTIFIED_PH_PERSON);
        persistence.createEntry(TestIdentifiableConstants.DIFFERENT_UNIDENTIFIED_PH_PERSON);

        worker = new TrashwareWorkerImpl(
                    persistence.readPeople(Queries.getTestFilter(TestIdentifiableConstants.UNIDENTIFIED_PH_PERSON))
                        .iterator().next(),
                    TestEntityConstants.WORKER_CATEGORY, true);
        persistence.createEntry(worker);
        differentWorker = new TrashwareWorkerImpl(
                            persistence.readPeople(
                                    Queries.getTestFilter(TestIdentifiableConstants.DIFFERENT_UNIDENTIFIED_PH_PERSON))
                                        .iterator().next(),
                            TestEntityConstants.DIFFERENT_WORKER_CATEGORY, true);
        persistence.createEntry(differentWorker);

        persistence.createEntry(TestEntityConstants.WORK_PROGRESS);
        persistence.createEntry(TestEntityConstants.DIFFERENT_WORK_PROGRESS);

        persistence.createEntry(TestEntityConstants.DEV_CATEGORY);
        persistence.createEntry(TestEntityConstants.DIFFERENT_DEV_CATEGORY);

        persistence.createEntry(TestEntityConstants.VENDOR);
        persistence.createEntry(TestEntityConstants.DIFFERENT_VENDOR);

        persistence.createEntry(TestIdentifiableConstants.UNIDENTIFIED_DEVICE);
        persistence.createEntry(TestIdentifiableConstants.DIFFERENT_UNIDENTIFIED_DEVICE);

        compound = new RefinedDeviceImpl.Builder()
                    .categoryDeviceId(TestConstants.A_POSITIVE_INTEGER)
                    .deviceCategory(TestEntityConstants.DEV_CATEGORY)
                    .refining(persistence.readGenericDevice(
                            Queries.getTestFilter(TestIdentifiableConstants.UNIDENTIFIED_DEVICE)).iterator().next())
                    .progress(TestEntityConstants.WORK_PROGRESS)
                    .available(true)
                    .lastCommitter(worker)
                    .lastUpdate(TestConstants.DATE)
                    .annotations(TestConstants.A_STRING)
                .build();

        component = new RefinedDeviceImpl.Builder()
                        .categoryDeviceId(TestConstants.A_DIFFERENT_POSITIVE_INTEGER)
                        .deviceCategory(TestEntityConstants.DIFFERENT_DEV_CATEGORY)
                        .refining(persistence.readGenericDevice(
                                Queries.getTestFilter(
                                        TestIdentifiableConstants.DIFFERENT_UNIDENTIFIED_DEVICE)).iterator().next())
                        .progress(TestEntityConstants.DIFFERENT_WORK_PROGRESS)
                        .available(true)
                        .lastCommitter(differentWorker)
                        .lastUpdate(TestConstants.DIFFERENT_DATE)
                        .annotations(TestConstants.A_DIFFERENT_STRING)
                    .build();

        persistence.createEntry(compound);
        persistence.createEntry(component);
        /*
         * Test start.
         */

        assertTrue(persistence.readRefinedDeviceCompound(Queries.getAll(RefinedDeviceCompound.class)).isEmpty());

        normal = new RefinedDeviceCompoundImpl(
                persistence.readRefinedDevice(Queries.getTestFilter(compound)).iterator().next(), 
                persistence.readRefinedDevice(Queries.getTestFilter(component)).iterator().next());
        different = new RefinedDeviceCompoundImpl(
                persistence.readRefinedDevice(Queries.getTestFilter(component)).iterator().next(),
                persistence.readRefinedDevice(Queries.getTestFilter(compound)).iterator().next());

        persistence.createEntry(normal);
        persistence.createEntry(different);
        assertTrue(persistence.readRefinedDeviceCompound(Queries.getAll(RefinedDeviceCompound.class)).size() == 2);

        assertTrue(persistence.readRefinedDeviceCompound(Queries.getAll(RefinedDeviceCompound.class)).contains(normal));
        assertTrue(persistence.readRefinedDeviceCompound(
                Queries.getAll(RefinedDeviceCompound.class)).contains(different));

        persistence.deleteEntry(different);
        assertTrue(persistence.readRefinedDeviceCompound(Queries.getAll(RefinedDeviceCompound.class)).size() == 1);
        assertTrue(persistence.readRefinedDeviceCompound(Queries.getAll(RefinedDeviceCompound.class)).contains(normal));
        assertFalse(persistence.readRefinedDeviceCompound(
                Queries.getAll(RefinedDeviceCompound.class)).contains(different));

        persistence.updateEntry(normal, different);
        assertTrue(persistence.readRefinedDeviceCompound(Queries.getAll(RefinedDeviceCompound.class)).size() == 1);
        assertFalse(persistence.readRefinedDeviceCompound(
                Queries.getAll(RefinedDeviceCompound.class)).contains(normal));
        assertTrue(persistence.readRefinedDeviceCompound(
                Queries.getAll(RefinedDeviceCompound.class)).contains(different));

        persistence.deleteEntry(different);
        assertTrue(persistence.readRefinedDeviceCompound(Queries.getAll(RefinedDeviceCompound.class)).isEmpty());

        /*
         * Test end.
         */

        persistence.deleteEntry(persistence.readRefinedDevice(Queries.getTestFilter(component)).iterator().next());
        persistence.deleteEntry(persistence.readRefinedDevice(Queries.getTestFilter(compound)).iterator().next());
        persistence.deleteEntry(
                persistence.readGenericDevice(
                        Queries.getTestFilter(TestIdentifiableConstants.DIFFERENT_UNIDENTIFIED_DEVICE))
                            .iterator().next());
        persistence.deleteEntry(
                persistence.readGenericDevice(
                        Queries.getTestFilter(TestIdentifiableConstants.UNIDENTIFIED_DEVICE)).iterator().next());

        persistence.deleteEntry(TestEntityConstants.DIFFERENT_VENDOR);
        persistence.deleteEntry(TestEntityConstants.VENDOR);

        persistence.deleteEntry(TestEntityConstants.DIFFERENT_DEV_CATEGORY);
        persistence.deleteEntry(TestEntityConstants.DEV_CATEGORY);

        persistence.deleteEntry(TestEntityConstants.DIFFERENT_WORK_PROGRESS);
        persistence.deleteEntry(TestEntityConstants.WORK_PROGRESS);

        persistence.deleteEntry(new TrashwareWorkerImpl(
                persistence.readPeople(
                        Queries.getTestFilter(TestIdentifiableConstants.DIFFERENT_UNIDENTIFIED_PH_PERSON))
                            .iterator().next(),
                TestEntityConstants.DIFFERENT_WORKER_CATEGORY, true));
        persistence.deleteEntry(new TrashwareWorkerImpl(
                persistence.readPeople(Queries.getTestFilter(TestIdentifiableConstants.UNIDENTIFIED_PH_PERSON))
                    .iterator().next(),
                TestEntityConstants.WORKER_CATEGORY, true));

        persistence.deleteEntry(persistence.readPeople(
                Queries.getTestFilter(TestIdentifiableConstants.DIFFERENT_UNIDENTIFIED_PH_PERSON)).iterator().next());
        persistence.deleteEntry(persistence.readPeople(
                Queries.getTestFilter(TestIdentifiableConstants.UNIDENTIFIED_PH_PERSON)).iterator().next());

        persistence.deleteEntry(TestEntityConstants.DIFFERENT_WORKER_CATEGORY);
        persistence.deleteEntry(TestEntityConstants.WORKER_CATEGORY);

        persistence.deleteEntry(new PersonCategoryImpl(PRIVATO));
    }

    /**
     * Tests the system capability to handle the CRUD operations on an {@link RefinedDeviceCompoundWithGeneric}.
     */
    @Test
    public void testPersistenceRefinedDeviceCompoundWithGeneric() {

        final GenericDevice component = TestIdentifiableConstants.UNIDENTIFIED_DEVICE;
        RefinedDeviceCompoundWithGeneric normal;
        RefinedDevice compound;
        TrashwareWorker worker;
        TrashwareWorker differentWorker;

        persistence.createEntry(new PersonCategoryImpl(PRIVATO));

        persistence.createEntry(TestEntityConstants.WORKER_CATEGORY);
        persistence.createEntry(TestEntityConstants.DIFFERENT_WORKER_CATEGORY);

        persistence.createEntry(TestIdentifiableConstants.UNIDENTIFIED_PH_PERSON);
        persistence.createEntry(TestIdentifiableConstants.DIFFERENT_UNIDENTIFIED_PH_PERSON);

        worker = new TrashwareWorkerImpl(
                    persistence.readPeople(Queries.getTestFilter(TestIdentifiableConstants.UNIDENTIFIED_PH_PERSON))
                        .iterator().next(),
                    TestEntityConstants.WORKER_CATEGORY, true);
        persistence.createEntry(worker);
        differentWorker = new TrashwareWorkerImpl(
                            persistence.readPeople(
                                    Queries.getTestFilter(TestIdentifiableConstants.DIFFERENT_UNIDENTIFIED_PH_PERSON))
                                        .iterator().next(),
                            TestEntityConstants.DIFFERENT_WORKER_CATEGORY, true);
        persistence.createEntry(differentWorker);

        persistence.createEntry(TestEntityConstants.WORK_PROGRESS);
        persistence.createEntry(TestEntityConstants.DIFFERENT_WORK_PROGRESS);

        persistence.createEntry(TestEntityConstants.DEV_CATEGORY);
        persistence.createEntry(TestEntityConstants.DIFFERENT_DEV_CATEGORY);

        persistence.createEntry(TestEntityConstants.VENDOR);
        persistence.createEntry(TestEntityConstants.DIFFERENT_VENDOR);

        persistence.createEntry(TestIdentifiableConstants.UNIDENTIFIED_DEVICE);
        persistence.createEntry(TestIdentifiableConstants.DIFFERENT_UNIDENTIFIED_DEVICE);

        compound = new RefinedDeviceImpl.Builder()
                    .categoryDeviceId(TestConstants.A_POSITIVE_INTEGER)
                    .deviceCategory(TestEntityConstants.DEV_CATEGORY)
                    .refining(persistence.readGenericDevice(
                            Queries.getTestFilter(TestIdentifiableConstants.UNIDENTIFIED_DEVICE)).iterator().next())
                    .progress(TestEntityConstants.WORK_PROGRESS)
                    .available(true)
                    .lastCommitter(worker)
                    .lastUpdate(TestConstants.DATE)
                    .annotations(TestConstants.A_STRING)
                .build();

        persistence.createEntry(compound);
        /*
         * Test start.
         */

        assertTrue(persistence.readRefinedDeviceCompoundWithGeneric(
                Queries.getAll(RefinedDeviceCompoundWithGeneric.class)).isEmpty());

        normal = new RefinedDeviceCompoundWithGenericImpl(
                persistence.readRefinedDevice(Queries.getTestFilter(compound)).iterator().next(), 
                persistence.readGenericDevice(Queries.getTestFilter(component)).iterator().next(),
                    TestConstants.A_POSITIVE_INTEGER);
        persistence.createEntry(normal);
        assertTrue(persistence.readRefinedDeviceCompoundWithGeneric(
                Queries.getAll(RefinedDeviceCompoundWithGeneric.class)).size() == 1);

        assertTrue(persistence.readRefinedDeviceCompoundWithGeneric(
                Queries.getAll(RefinedDeviceCompoundWithGeneric.class)).contains(normal));

        assertTrue(persistence.readRefinedDeviceCompoundWithGeneric(
                Queries.getAll(RefinedDeviceCompoundWithGeneric.class)).size() == 1);
        assertTrue(persistence.readRefinedDeviceCompoundWithGeneric(
                Queries.getAll(RefinedDeviceCompoundWithGeneric.class)).contains(normal));

        persistence.deleteEntry(normal);
        assertTrue(persistence.readRefinedDeviceCompoundWithGeneric(
                Queries.getAll(RefinedDeviceCompoundWithGeneric.class)).isEmpty());

        /*
         * Test end.
         */

        persistence.deleteEntry(persistence.readRefinedDevice(Queries.getTestFilter(compound)).iterator().next());
        persistence.deleteEntry(persistence.readGenericDevice(Queries.getTestFilter(component)).iterator().next());
        persistence.deleteEntry(
                persistence.readGenericDevice(
                        Queries.getTestFilter(TestIdentifiableConstants.DIFFERENT_UNIDENTIFIED_DEVICE))
                            .iterator().next());
        persistence.deleteEntry(TestEntityConstants.DIFFERENT_VENDOR);
        persistence.deleteEntry(TestEntityConstants.VENDOR);

        persistence.deleteEntry(TestEntityConstants.DIFFERENT_DEV_CATEGORY);
        persistence.deleteEntry(TestEntityConstants.DEV_CATEGORY);

        persistence.deleteEntry(TestEntityConstants.DIFFERENT_WORK_PROGRESS);
        persistence.deleteEntry(TestEntityConstants.WORK_PROGRESS);

        persistence.deleteEntry(new TrashwareWorkerImpl(
                persistence.readPeople(
                        Queries.getTestFilter(TestIdentifiableConstants.DIFFERENT_UNIDENTIFIED_PH_PERSON))
                            .iterator().next(),
                TestEntityConstants.DIFFERENT_WORKER_CATEGORY, true));
        persistence.deleteEntry(new TrashwareWorkerImpl(
                persistence.readPeople(Queries.getTestFilter(TestIdentifiableConstants.UNIDENTIFIED_PH_PERSON))
                    .iterator().next(),
                TestEntityConstants.WORKER_CATEGORY, true));

        persistence.deleteEntry(persistence.readPeople(
                Queries.getTestFilter(TestIdentifiableConstants.DIFFERENT_UNIDENTIFIED_PH_PERSON)).iterator().next());
        persistence.deleteEntry(persistence.readPeople(
                Queries.getTestFilter(TestIdentifiableConstants.UNIDENTIFIED_PH_PERSON)).iterator().next());

        persistence.deleteEntry(TestEntityConstants.DIFFERENT_WORKER_CATEGORY);
        persistence.deleteEntry(TestEntityConstants.WORKER_CATEGORY);

        persistence.deleteEntry(new PersonCategoryImpl(PRIVATO));
    }

    /**
     * Tests the system capability to handle the CRUD operations on an {@link Screen}.
     */
    @Test
    public void testPersistenceScreen() {
        Screen normal;
        Screen different;

        persistence.createEntry(TestEntityConstants.DEV_CATEGORY);
        persistence.createEntry(TestEntityConstants.DIFFERENT_DEV_CATEGORY);
        persistence.createEntry(TestEntityConstants.VENDOR);
        persistence.createEntry(TestEntityConstants.DIFFERENT_VENDOR);
        persistence.createEntry(TestIdentifiableConstants.UNIDENTIFIED_DEVICE);
        persistence.createEntry(TestIdentifiableConstants.DIFFERENT_UNIDENTIFIED_DEVICE);
        persistence.createEntry(TestEntityConstants.COLOR);
        persistence.createEntry(TestEntityConstants.DIFFERENT_COLOR);
        persistence.createEntry(TestEntityConstants.SCREEN_CATEGORY);
        persistence.createEntry(TestEntityConstants.DIFFERENT_SCREEN_CATEGORY);
        persistence.createEntry(TestEntityConstants.RATIO);
        persistence.createEntry(TestEntityConstants.DIFFERENT_RATIO);
        persistence.createEntry(TestIdentifiableConstants.UNIDENTIFIED_SCREEN_RESOLUTION);
        persistence.createEntry(TestIdentifiableConstants.DIFFERENT_UNIDENTIFIED_SCREEN_RESOLUTION);

        assertTrue(persistence.readScreen(Queries.getAll(Screen.class)).isEmpty());

        normal = new ScreenImpl.Builder()
                    .device(persistence.readGenericDevice(
                            Queries.getTestFilter(TestIdentifiableConstants.UNIDENTIFIED_DEVICE)).iterator().next())
                    .category(TestEntityConstants.SCREEN_CATEGORY)
                    .color(TestEntityConstants.COLOR)
                    .resolution(persistence.readScreenResolution(
                            Queries.getTestFilter(TestIdentifiableConstants.UNIDENTIFIED_SCREEN_RESOLUTION))
                                .iterator().next())
                    .hasAudioSpeakers(true)
                    .hasDviSocket(true)
                    .hasFrame(true)
                    .hasVgaSocket(true)
                    .build();

        different = new ScreenImpl.Builder()
                    .device(persistence.readGenericDevice(
                            Queries.getTestFilter(TestIdentifiableConstants.DIFFERENT_UNIDENTIFIED_DEVICE))
                                .iterator().next())
                    .category(TestEntityConstants.DIFFERENT_SCREEN_CATEGORY)
                    .color(TestEntityConstants.DIFFERENT_COLOR)
                    .resolution(persistence.readScreenResolution(
                            Queries.getTestFilter(TestIdentifiableConstants.DIFFERENT_UNIDENTIFIED_SCREEN_RESOLUTION))
                                .iterator().next())
                    .hasAudioSpeakers(true)
                    .hasDviSocket(true)
                    .hasFrame(true)
                    .hasVgaSocket(true)
                    .build();

        persistence.createEntry(normal);
        persistence.createEntry(different);
        assertTrue(persistence.readScreen(Queries.getAll(Screen.class)).size() == 2);
        assertTrue(persistence.readScreen(Queries.getAll(Screen.class)).contains(normal));
        assertTrue(persistence.readScreen(Queries.getAll(Screen.class)).contains(different));
        assertThrows(DuplicateKeyValueException.class, () -> {
            persistence.createEntry(normal);
        });
        assertThrows(DuplicateKeyValueException.class, () -> {
            persistence.createEntry(different);
        });
        assertThrows(DuplicateKeyValueException.class, () -> {
            persistence.updateEntry(normal, different);
        });
        assertThrows(DuplicateKeyValueException.class, () -> {
            persistence.updateEntry(different, normal);
        });

        persistence.deleteEntry(different);
        assertTrue(persistence.readScreen(Queries.getAll(Screen.class)).size() == 1);
        assertTrue(persistence.readScreen(Queries.getAll(Screen.class)).contains(normal));
        assertFalse(persistence.readScreen(Queries.getAll(Screen.class)).contains(different));

        persistence.updateEntry(normal, different);
        assertTrue(persistence.readScreen(Queries.getAll(Screen.class)).size() == 1);
        assertFalse(persistence.readScreen(Queries.getAll(Screen.class)).contains(normal));
        assertTrue(persistence.readScreen(Queries.getAll(Screen.class)).contains(different));

        persistence.deleteEntry(different);
        assertTrue(persistence.readScreen(Queries.getAll(Screen.class)).isEmpty());

        persistence.deleteEntry(persistence.readScreenResolution(
                Queries.getTestFilter(TestIdentifiableConstants.DIFFERENT_UNIDENTIFIED_SCREEN_RESOLUTION))
                    .iterator().next());
        persistence.deleteEntry(persistence.readScreenResolution(
                Queries.getTestFilter(TestIdentifiableConstants.UNIDENTIFIED_SCREEN_RESOLUTION))
                    .iterator().next());
        persistence.deleteEntry(TestEntityConstants.DIFFERENT_RATIO);
        persistence.deleteEntry(TestEntityConstants.RATIO);
        persistence.deleteEntry(TestEntityConstants.DIFFERENT_SCREEN_CATEGORY);
        persistence.deleteEntry(TestEntityConstants.SCREEN_CATEGORY);
        persistence.deleteEntry(TestEntityConstants.DIFFERENT_COLOR);
        persistence.deleteEntry(TestEntityConstants.COLOR);
        persistence.deleteEntry(persistence.readGenericDevice(
                Queries.getTestFilter(TestIdentifiableConstants.DIFFERENT_UNIDENTIFIED_DEVICE)).iterator().next());
        persistence.deleteEntry(persistence.readGenericDevice(
                Queries.getTestFilter(TestIdentifiableConstants.UNIDENTIFIED_DEVICE)).iterator().next());
        persistence.deleteEntry(TestEntityConstants.DIFFERENT_VENDOR);
        persistence.deleteEntry(TestEntityConstants.VENDOR);
        persistence.deleteEntry(TestEntityConstants.DIFFERENT_DEV_CATEGORY);
        persistence.deleteEntry(TestEntityConstants.DEV_CATEGORY);
    }

    /**
     * Tests the system capability to handle the CRUD operations on an {@link ScreenCategory}.
     */
    @Test
    public void testPersistenceScreenCategory() {

        final ScreenCategory normal = TestEntityConstants.SCREEN_CATEGORY;
        final ScreenCategory different = TestEntityConstants.DIFFERENT_SCREEN_CATEGORY;

        assertTrue(persistence.readScreenCategory(Queries.getAll(ScreenCategory.class)).isEmpty());

        persistence.createEntry(normal);
        assertTrue(persistence.readScreenCategory(Queries.getAll(ScreenCategory.class)).contains(normal));
        assertThrows(DuplicateKeyValueException.class, () -> {
            persistence.createEntry(normal); 
        });

        persistence.createEntry(different);
        assertTrue(persistence.readScreenCategory(Queries.getAll(ScreenCategory.class)).size() == 2);
        assertTrue(persistence.readScreenCategory(Queries.getAll(ScreenCategory.class)).contains(normal));
        assertTrue(persistence.readScreenCategory(Queries.getAll(ScreenCategory.class)).contains(different));
        assertThrows(DuplicateKeyValueException.class, () -> {
            persistence.updateEntry(normal, different);
        });

        persistence.deleteEntry(different);
        assertTrue(persistence.readScreenCategory(Queries.getAll(ScreenCategory.class)).size() == 1);
        assertTrue(persistence.readScreenCategory(Queries.getAll(ScreenCategory.class)).contains(normal));
        assertFalse(persistence.readScreenCategory(Queries.getAll(ScreenCategory.class)).contains(different));

        persistence.updateEntry(normal, different);
        assertTrue(persistence.readScreenCategory(Queries.getAll(ScreenCategory.class)).size() == 1);
        assertFalse(persistence.readScreenCategory(Queries.getAll(ScreenCategory.class)).contains(normal));
        assertTrue(persistence.readScreenCategory(Queries.getAll(ScreenCategory.class)).contains(different));

        persistence.deleteEntry(different);
        assertTrue(persistence.readScreenCategory(Queries.getAll(ScreenCategory.class)).isEmpty());
    }

    /**
     * Tests the system capability to handle the CRUD operations on an {@link ScreenResolution}.
     */
    @Test
    public void testPersistenceScreenResolution() {
        final ScreenResolution normal = TestIdentifiableConstants.UNIDENTIFIED_SCREEN_RESOLUTION;
        final ScreenResolution different = TestIdentifiableConstants.DIFFERENT_UNIDENTIFIED_SCREEN_RESOLUTION;
        ScreenResolution identifiedNormal;
        ScreenResolution identifiedDifferent;

        persistence.createEntry(TestEntityConstants.RATIO);
        persistence.createEntry(TestEntityConstants.DIFFERENT_RATIO);

        assertTrue(persistence.readScreenResolution(Queries.getAll(ScreenResolution.class)).isEmpty());

        persistence.createEntry(normal);
        persistence.createEntry(different);
        assertTrue(persistence.readScreenResolution(Queries.getAll(ScreenResolution.class)).size() == 2);
        assertFalse(persistence.readScreenResolution(Queries.getAll(ScreenResolution.class)).contains(normal));
        assertFalse(persistence.readScreenResolution(Queries.getAll(ScreenResolution.class)).contains(different));

        identifiedNormal = persistence.readScreenResolution(Queries.getTestFilter(normal)).iterator().next();
        identifiedDifferent = persistence.readScreenResolution(Queries.getTestFilter(different)).iterator().next();
        assertTrue(persistence.readScreenResolution(Queries.getAll(ScreenResolution.class)).size() == 2);
        assertTrue(persistence.readScreenResolution(Queries.getAll(ScreenResolution.class)).contains(identifiedNormal));
        assertTrue(persistence.readScreenResolution(
                Queries.getAll(ScreenResolution.class)).contains(identifiedDifferent));

        persistence.deleteEntry(identifiedDifferent);
        assertTrue(persistence.readScreenResolution(Queries.getAll(ScreenResolution.class)).size() == 1);
        assertTrue(persistence.readScreenResolution(Queries.getAll(ScreenResolution.class)).contains(identifiedNormal));
        assertFalse(persistence.readScreenResolution(
                Queries.getAll(ScreenResolution.class)).contains(identifiedDifferent));

        persistence.updateEntry(identifiedNormal, different);
        assertTrue(persistence.readScreenResolution(Queries.getAll(ScreenResolution.class)).size() == 1);
        assertFalse(persistence.readScreenResolution(
                Queries.getAll(ScreenResolution.class)).contains(identifiedNormal));
        assertFalse(persistence.readScreenResolution(
                Queries.getAll(ScreenResolution.class)).contains(identifiedDifferent));

        identifiedDifferent = persistence.readScreenResolution(Queries.getTestFilter(different)).iterator().next();
        assertTrue(persistence.readScreenResolution(Queries.getAll(ScreenResolution.class)).size() == 1);
        assertTrue(persistence.readScreenResolution(
                Queries.getAll(ScreenResolution.class)).contains(identifiedDifferent));

        persistence.deleteEntry(identifiedDifferent);
        assertTrue(persistence.readScreenResolution(Queries.getAll(ScreenResolution.class)).isEmpty());

        persistence.deleteEntry(TestEntityConstants.DIFFERENT_RATIO);
        persistence.deleteEntry(TestEntityConstants.RATIO);
    }

    /**
     * Tests the system capability to handle the CRUD operations on an {@link Vendor}.
     */
    @Test
    public void testPersistenceVendor() {

        final Vendor normal = TestEntityConstants.VENDOR;
        final Vendor different = TestEntityConstants.DIFFERENT_VENDOR;

        assertTrue(persistence.readVendor(Queries.getAll(Vendor.class)).isEmpty());

        persistence.createEntry(normal);
        assertTrue(persistence.readVendor(Queries.getAll(Vendor.class)).contains(normal));
        assertThrows(DuplicateKeyValueException.class, () -> {
            persistence.createEntry(normal); 
        });

        persistence.createEntry(different);
        assertTrue(persistence.readVendor(Queries.getAll(Vendor.class)).size() == 2);
        assertTrue(persistence.readVendor(Queries.getAll(Vendor.class)).contains(normal));
        assertTrue(persistence.readVendor(Queries.getAll(Vendor.class)).contains(different));
        assertThrows(DuplicateKeyValueException.class, () -> {
            persistence.updateEntry(normal, different);
        });

        persistence.deleteEntry(different);
        assertTrue(persistence.readVendor(Queries.getAll(Vendor.class)).size() == 1);
        assertTrue(persistence.readVendor(Queries.getAll(Vendor.class)).contains(normal));
        assertFalse(persistence.readVendor(Queries.getAll(Vendor.class)).contains(different));

        persistence.updateEntry(normal, different);
        assertTrue(persistence.readVendor(Queries.getAll(Vendor.class)).size() == 1);
        assertFalse(persistence.readVendor(Queries.getAll(Vendor.class)).contains(normal));
        assertTrue(persistence.readVendor(Queries.getAll(Vendor.class)).contains(different));

        persistence.deleteEntry(different);
        assertTrue(persistence.readVendor(Queries.getAll(Vendor.class)).isEmpty());
    }

}
