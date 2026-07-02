package it.trashwarecesena.trustalodesktopclient.repository.crud.domain;

import it.trashwarecesena.trustalodesktopclient.repository.crud.entities.devices.management.PersistentDeviceCategory;
import it.trashwarecesena.trustalodesktopclient.repository.crud.entities.devices.management.PersistentDeviceWorkProgress;
import it.trashwarecesena.trustalodesktopclient.repository.crud.entities.devices.management.PersistentGenericDevice;
import it.trashwarecesena.trustalodesktopclient.repository.crud.entities.devices.management.PersistentLegalCategoryCompound;
import it.trashwarecesena.trustalodesktopclient.repository.crud.entities.devices.management.PersistentRefinedDevice;
import it.trashwarecesena.trustalodesktopclient.repository.crud.entities.devices.management.PersistentRefinedDeviceCompound;
import it.trashwarecesena.trustalodesktopclient.repository.crud.entities.devices.management.PersistentRefinedDeviceCompoundWithGeneric;
import it.trashwarecesena.trustalodesktopclient.repository.crud.entities.devices.management.PersistentVendor;
import it.trashwarecesena.trustalodesktopclient.repository.crud.entities.devices.taxonomy.PersistentAspectRatio;
import it.trashwarecesena.trustalodesktopclient.repository.crud.entities.devices.taxonomy.PersistentCase;
import it.trashwarecesena.trustalodesktopclient.repository.crud.entities.devices.taxonomy.PersistentColor;
import it.trashwarecesena.trustalodesktopclient.repository.crud.entities.devices.taxonomy.PersistentDigitalInformationUnit;
import it.trashwarecesena.trustalodesktopclient.repository.crud.entities.devices.taxonomy.PersistentHardDiskDrive;
import it.trashwarecesena.trustalodesktopclient.repository.crud.entities.devices.taxonomy.PersistentPrinter;
import it.trashwarecesena.trustalodesktopclient.repository.crud.entities.devices.taxonomy.PersistentPrinterCategory;
import it.trashwarecesena.trustalodesktopclient.repository.crud.entities.devices.taxonomy.PersistentRandomAccessMemory;
import it.trashwarecesena.trustalodesktopclient.repository.crud.entities.devices.taxonomy.PersistentScreen;
import it.trashwarecesena.trustalodesktopclient.repository.crud.entities.devices.taxonomy.PersistentScreenCategory;
import it.trashwarecesena.trustalodesktopclient.repository.crud.entities.devices.taxonomy.PersistentScreenResolution;

/**
 * A marker interface constituted by the extension of all the interfaces which
 * semantically compose the Devices domain.
 * <p>
 * The implementor of this class <i> should </i> have direct access to the
 * persistence media, as the returning values are expected to be the result of a
 * data query.
 * 
 * @author Manuel Bonarrigo
 */
public interface CrudDevices extends PersistentAspectRatio, PersistentCase, PersistentColor, PersistentDeviceCategory,
        PersistentDeviceWorkProgress, PersistentDigitalInformationUnit, PersistentGenericDevice,
        PersistentHardDiskDrive, PersistentLegalCategoryCompound, PersistentPrinter, PersistentPrinterCategory,
        PersistentRandomAccessMemory, PersistentRefinedDeviceCompound, PersistentRefinedDeviceCompoundWithGeneric,
        PersistentScreen, PersistentScreenCategory, PersistentScreenResolution, PersistentVendor,
        PersistentRefinedDevice {

}
