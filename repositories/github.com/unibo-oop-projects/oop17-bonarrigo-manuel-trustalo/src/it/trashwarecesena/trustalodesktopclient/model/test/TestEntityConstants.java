package it.trashwarecesena.trustalodesktopclient.model.test;

import it.trashwarecesena.trustalodesktopclient.model.devices.AspectRatio;
import it.trashwarecesena.trustalodesktopclient.model.devices.Color;
import it.trashwarecesena.trustalodesktopclient.model.devices.DeviceCategory;
import it.trashwarecesena.trustalodesktopclient.model.devices.DeviceWorkProgress;
import it.trashwarecesena.trustalodesktopclient.model.devices.DigitalInformationUnit;
import it.trashwarecesena.trustalodesktopclient.model.devices.PrinterCategory;
import it.trashwarecesena.trustalodesktopclient.model.devices.ScreenCategory;
import it.trashwarecesena.trustalodesktopclient.model.devices.Vendor;
import it.trashwarecesena.trustalodesktopclient.model.devices.concreteness.AspectRatioImpl;
import it.trashwarecesena.trustalodesktopclient.model.devices.concreteness.ColorImpl;
import it.trashwarecesena.trustalodesktopclient.model.devices.concreteness.DeviceCategoryImpl;
import it.trashwarecesena.trustalodesktopclient.model.devices.concreteness.DeviceWorkProgressImpl;
import it.trashwarecesena.trustalodesktopclient.model.devices.concreteness.DigitalInformationUnitImpl;
import it.trashwarecesena.trustalodesktopclient.model.devices.concreteness.PrinterCategoryImpl;
import it.trashwarecesena.trustalodesktopclient.model.devices.concreteness.ScreenCategoryImpl;
import it.trashwarecesena.trustalodesktopclient.model.devices.concreteness.VendorImpl;
import it.trashwarecesena.trustalodesktopclient.model.immutable.ImmutableIntelProcessor;
import it.trashwarecesena.trustalodesktopclient.model.otherdevices.FrequencyUnit;
import it.trashwarecesena.trustalodesktopclient.model.otherdevices.InstructionSet;
import it.trashwarecesena.trustalodesktopclient.model.otherdevices.concreteness.FrequencyUnitImpl;
import it.trashwarecesena.trustalodesktopclient.model.otherdevices.concreteness.InstructionSetImpl;
import it.trashwarecesena.trustalodesktopclient.model.people.ContactCategory;
import it.trashwarecesena.trustalodesktopclient.model.people.PersonCategory;
import it.trashwarecesena.trustalodesktopclient.model.people.TrashwareWorker;
import it.trashwarecesena.trustalodesktopclient.model.people.TrashwareWorkerCategory;
import it.trashwarecesena.trustalodesktopclient.model.people.concreteness.ContactCategoryImpl;
import it.trashwarecesena.trustalodesktopclient.model.people.concreteness.PersonCategoryImpl;
import it.trashwarecesena.trustalodesktopclient.model.people.concreteness.TrashwareWorkerCategoryImpl;
import it.trashwarecesena.trustalodesktopclient.model.people.concreteness.TrashwareWorkerImpl;
import it.trashwarecesena.trustalodesktopclient.model.requests.RequestProgress;
import it.trashwarecesena.trustalodesktopclient.model.requests.concreteness.RequestProgressImpl;
import it.trashwarecesena.trustalodesktopclient.model.immutable.concreteness.ImmutableIntelProcessorImpl;

/**
 * A utility class holding most of the domain model instances used to unit-test
 * the system.
 * 
 * @author Manuel Bonarrigo
 *
 */
public final class TestEntityConstants {

    /**
     * A default {@link DeviceCategory} instance.
     */
    public static final DeviceCategory DEV_CATEGORY = new DeviceCategoryImpl(TestConstants.A_STRING,
            TestConstants.A_STRING, true);
    /**
     * A {@link DeviceCategory} instance with a different value than the default
     * one.
     */
    public static final DeviceCategory DIFFERENT_DEV_CATEGORY = new DeviceCategoryImpl(TestConstants.A_DIFFERENT_STRING,
            TestConstants.A_DIFFERENT_STRING, true);
    /**
     * A {@link DeviceCategory} instance with the same value of the default one.
     */
    public static final DeviceCategory SAME_DEV_CATEGORY = new DeviceCategoryImpl(TestConstants.THE_SAME_STRING,
            TestConstants.THE_SAME_STRING, true);

    /**
     * A default {@link Vendor} instance.
     */
    public static final Vendor VENDOR = new VendorImpl(TestConstants.A_STRING);

    /**
     * A {@link Vendor} instance with a different value than the default one.
     */
    public static final Vendor DIFFERENT_VENDOR = new VendorImpl(TestConstants.A_DIFFERENT_STRING);

    /**
     * A {@link Vendor} instance with the same value of the default one.
     */
    public static final Vendor SAME_VENDOR = new VendorImpl(TestConstants.THE_SAME_STRING);

    /**
     * A default {@link DeviceWorkProgress} instance.
     */
    public static final DeviceWorkProgress WORK_PROGRESS = new DeviceWorkProgressImpl(TestConstants.A_STRING,
            TestConstants.A_STRING);

    /**
     * A {@link DeviceWorkProgress} instance with a different value than the default
     * one.
     */
    public static final DeviceWorkProgress DIFFERENT_WORK_PROGRESS = new DeviceWorkProgressImpl(
            TestConstants.A_DIFFERENT_STRING, TestConstants.A_DIFFERENT_STRING);

    /**
     * A {@link DeviceWorkProgress} instance with the same value of the default one.
     */
    public static final DeviceWorkProgress SAME_WORK_PROGRESS = new DeviceWorkProgressImpl(
            TestConstants.THE_SAME_STRING, TestConstants.THE_SAME_STRING);

    /**
     * A default {@link RequestProgress} instance.
     */
    public static final RequestProgress REQ_PROGRESS = new RequestProgressImpl(TestConstants.A_STRING,
            TestConstants.A_STRING);

    /**
     * A {@link RequestProgress} instance with a different value than the default
     * one.
     */
    public static final RequestProgress DIFFERENT_REQ_PROGRESS = new RequestProgressImpl(
            TestConstants.A_DIFFERENT_STRING, TestConstants.A_DIFFERENT_STRING);

    /**
     * A {@link RequestProgress} instance with the same value of the default one.
     */
    public static final RequestProgress SAME_REQ_PROGRESS = new RequestProgressImpl(TestConstants.THE_SAME_STRING,
            TestConstants.THE_SAME_STRING);

    /**
     * A default {@link TrashwareWorkerCategory} instance.
     */
    public static final TrashwareWorkerCategory WORKER_CATEGORY = new TrashwareWorkerCategoryImpl(
            TestConstants.A_STRING);

    /**
     * A {@link TrashwareWorkerCategory} instance with a different value than the
     * default one.
     */
    public static final TrashwareWorkerCategory DIFFERENT_WORKER_CATEGORY = new TrashwareWorkerCategoryImpl(
            TestConstants.A_DIFFERENT_STRING);

    /**
     * A {@link TrashwareWorkerCategory} instance with the same value of the default
     * one.
     */
    public static final TrashwareWorkerCategory SAME_WORKER_CATEGORY = new TrashwareWorkerCategoryImpl(
            TestConstants.THE_SAME_STRING);

    /**
     * A default {@link AspectRatio} instance.
     */
    public static final AspectRatio RATIO = new AspectRatioImpl(TestConstants.A_POSITIVE_INTEGER,
            TestConstants.A_POSITIVE_INTEGER);

    /**
     * A {@link AspectRatio} instance with a different value than the default one.
     */
    public static final AspectRatio DIFFERENT_RATIO = new AspectRatioImpl(TestConstants.A_DIFFERENT_POSITIVE_INTEGER,
            TestConstants.A_DIFFERENT_POSITIVE_INTEGER);

    /**
     * A {@link AspectRatio} instance with the same value of the default one.
     */
    public static final AspectRatio SAME_RATIO = new AspectRatioImpl(TestConstants.THE_SAME_POSITIVE_INTEGER,
            TestConstants.THE_SAME_POSITIVE_INTEGER);

    /**
     * A default {@link PersonCategory} instance.
     */
    public static final PersonCategory PER_CATEGORY = new PersonCategoryImpl(TestConstants.A_STRING);

    /**
     * A {@link PersonCategory} instance with a different value than the default
     * one.
     */
    public static final PersonCategory DIFFERENT_PER_CATEGORY = new PersonCategoryImpl(
            TestConstants.A_DIFFERENT_STRING);

    /**
     * A {@link PersonCategory} instance with the same value of the default one.
     */
    public static final PersonCategory SAME_PER_CATEGORY = new PersonCategoryImpl(TestConstants.THE_SAME_STRING);

    /**
     * A default {@link DigitalInformationUnit} instance.
     */
    public static final DigitalInformationUnit INF_UNIT = new DigitalInformationUnitImpl("MB");

    /**
     * A {@link DigitalInformationUnit} instance with a different value than the
     * default one.
     */
    public static final DigitalInformationUnit INF_DIFFERENT_UNIT = new DigitalInformationUnitImpl("GB");

    /**
     * A {@link DigitalInformationUnit} instance with the same value of the default
     * one.
     */
    public static final DigitalInformationUnit INF_SAME_UNIT = new DigitalInformationUnitImpl("MB");

    /**
     * A default {@link Color} instance.
     */
    public static final Color COLOR = new ColorImpl("FFFFFF");

    /**
     * A {@link Color} instance with a different value than the default one.
     */
    public static final Color DIFFERENT_COLOR = new ColorImpl("000001");

    /**
     * A {@link Color} instance with the same value of the default one.
     */
    public static final Color SAME_COLOR = new ColorImpl("FFFFFF");

    /**
     * A default {@link PrinterCategory} instance.
     */
    public static final PrinterCategory CATEGORY = new PrinterCategoryImpl(TestConstants.A_STRING);

    /**
     * A {@link PrinterCategory} instance with a different value than the default
     * one.
     */
    public static final PrinterCategory DIFFERENT_CATEGORY = new PrinterCategoryImpl(TestConstants.A_DIFFERENT_STRING);

    /**
     * A {@link PrinterCategory} instance with the same value of the default one.
     */
    public static final PrinterCategory SAME_CATEGORY = new PrinterCategoryImpl(TestConstants.THE_SAME_STRING);

    /**
     * A default {@link ScreenCategory} instance.
     */
    public static final ScreenCategory SCREEN_CATEGORY = new ScreenCategoryImpl(TestConstants.A_STRING);

    /**
     * A {@link ScreenCategory} instance with a different value than the default
     * one.
     */
    public static final ScreenCategory DIFFERENT_SCREEN_CATEGORY = new ScreenCategoryImpl(
            TestConstants.A_DIFFERENT_STRING);

    /**
     * A {@link ScreenCategory} instance with the same value of the default one.
     */
    public static final ScreenCategory SAME_SCREEN_CATEGORY = new ScreenCategoryImpl(TestConstants.THE_SAME_STRING);

    /**
     * A default {@link FrequencyUnit} instance.
     */
    public static final FrequencyUnit FREQ_UNIT = new FrequencyUnitImpl("MHz");

    /**
     * A {@link FrequencyUnit} instance with a different value than the default one.
     */
    public static final FrequencyUnit DIFFERENT_FREQ_UNIT = new FrequencyUnitImpl("GHz");

    /**
     * A {@link FrequencyUnit} instance with the same value of the default one.
     */
    public static final FrequencyUnit SAME_FREQ_UNIT = new FrequencyUnitImpl("MHz");

    /**
     * A default {@link InstructionSet} instance.
     */
    public static final InstructionSet ISA = new InstructionSetImpl("x86");

    /**
     * A {@link InstructionSet} instance with a different value than the default
     * one.
     */
    public static final InstructionSet DIFFERENT_ISA = new InstructionSetImpl("x64");

    /**
     * A {@link InstructionSet} instance with the same value of the default one.
     */
    public static final InstructionSet SAME_ISA = new InstructionSetImpl("x86");

    /**
     * A default {@link ContactCategory} instance.
     */
    public static final ContactCategory CON_CATEGORY = new ContactCategoryImpl(TestConstants.A_STRING);

    /**
     * A {@link ContactCategory} instance with a different value than the default
     * one.
     */
    public static final ContactCategory CON_DIFFERENT_CATEGORY = new ContactCategoryImpl(
            TestConstants.A_DIFFERENT_STRING);

    /**
     * A {@link ContactCategory} instance with the same value of the default one.
     */
    public static final ContactCategory CON_SAME_CATEGORY = new ContactCategoryImpl(TestConstants.THE_SAME_STRING);

    /**
     * A default {@link TrashwareWorker} instance.
     */
    public static final TrashwareWorker WORKER = new TrashwareWorkerImpl(TestIdentifiableConstants.IDENTIFIED_PH_PERSON,
            WORKER_CATEGORY, true);

    /**
     * A {@link TrashwareWorker} instance with a different value than the default
     * one.
     */
    public static final TrashwareWorker DIFFERENT_WORKER = new TrashwareWorkerImpl(
            TestIdentifiableConstants.DIFFERENT_IDENTIFIED_PH_PERSON, DIFFERENT_WORKER_CATEGORY, true);

    /**
     * A {@link TrashwareWorker} instance with the same value of the default one.
     */
    public static final TrashwareWorker SAME_WORKER = new TrashwareWorkerImpl(
            TestIdentifiableConstants.SAME_IDENTIFIED_PH_PERSON, SAME_WORKER_CATEGORY, true);

    /**
     * A {@link ImmutableIntelProcessor} to be checked against the fetched one.
     */
    public static final ImmutableIntelProcessor INTEL_31731_PRODUCT = new ImmutableIntelProcessorImpl(
                31731, "9150M", 1660, "Itanium 64-bit", "L3", 24576, "24.0 MB", 
                "Intel® Itanium® Processor 9150M (24M Cache, 1.66 GHz, 667 MHz FSB)"
            );

    private TestEntityConstants() {
    }

}
