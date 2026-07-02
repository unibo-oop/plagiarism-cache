package it.trashwarecesena.trustalodesktopclient.model.test;

import it.trashwarecesena.trustalodesktopclient.model.devices.GenericDevice;
import it.trashwarecesena.trustalodesktopclient.model.devices.RefinedDevice;
import it.trashwarecesena.trustalodesktopclient.model.devices.ScreenResolution;
import it.trashwarecesena.trustalodesktopclient.model.devices.concreteness.GenericDeviceImpl;
import it.trashwarecesena.trustalodesktopclient.model.devices.concreteness.RefinedDeviceImpl;
import it.trashwarecesena.trustalodesktopclient.model.devices.concreteness.ScreenResolutionImpl;
import it.trashwarecesena.trustalodesktopclient.model.people.JuridicalPerson;
import it.trashwarecesena.trustalodesktopclient.model.people.PhysicalPerson;
import it.trashwarecesena.trustalodesktopclient.model.people.concreteness.JuridicalPersonImpl;
import it.trashwarecesena.trustalodesktopclient.model.people.concreteness.PhysicalPersonImpl;
import it.trashwarecesena.trustalodesktopclient.model.requests.Request;
import it.trashwarecesena.trustalodesktopclient.model.requests.RequestDetail;
import it.trashwarecesena.trustalodesktopclient.model.requests.concreteness.RequestDetailImpl;
import it.trashwarecesena.trustalodesktopclient.model.requests.concreteness.RequestImpl;

/**
 * A utility class holding all the Identifiable instances used to unit-test the
 * system.
 * 
 * @author Manuel Bonarrigo
 *
 */
public final class TestIdentifiableConstants {

    /**
     * A pre-built {@link PhysicalPerson} which looks like an object fetched from the persistence module.
     */
    public static final PhysicalPerson IDENTIFIED_PH_PERSON =
            new PhysicalPersonImpl.Builder()
                .identifier(TestConstants.A_POSITIVE_INTEGER)
                .name(TestConstants.A_STRING)
                .fiscalCode(TestConstants.A_STRING)
                .birthDate(TestConstants.DATE)
                .birthLocation(TestConstants.A_STRING)
                .annotations(TestConstants.A_STRING)
                .build();

    /**
     * A pre-built {@link PhysicalPerson} which looks like an object created by some client code.
     */
    public static final PhysicalPerson UNIDENTIFIED_PH_PERSON =
            new PhysicalPersonImpl.Builder()
                .name(TestConstants.A_STRING)
                .fiscalCode(TestConstants.A_STRING)
                .birthDate(TestConstants.DATE)
                .birthLocation(TestConstants.A_STRING)
                .annotations(TestConstants.A_STRING)
                .build();

    /**
     * A pre-built {@link PhysicalPerson} which looks like an object fetched from the
     * persistence module, built to be different from any other instances of the
     * same type of this class.
     */
    public static final PhysicalPerson DIFFERENT_IDENTIFIED_PH_PERSON =
            new PhysicalPersonImpl.Builder()
                .identifier(TestConstants.A_DIFFERENT_POSITIVE_INTEGER)
                .name(TestConstants.A_DIFFERENT_STRING)
                .fiscalCode(TestConstants.A_DIFFERENT_STRING)
                .birthDate(TestConstants.DIFFERENT_DATE)
                .birthLocation(TestConstants.A_DIFFERENT_STRING)
                .annotations(TestConstants.A_DIFFERENT_STRING)
                .build();

    /**
     * A pre-built {@link PhysicalPerson} which looks like an object created by some
     * client code, built to be different from any other instances of the same type
     * of this class.
     */
    public static final PhysicalPerson DIFFERENT_UNIDENTIFIED_PH_PERSON =
            new PhysicalPersonImpl.Builder()
                .name(TestConstants.A_DIFFERENT_STRING)
                .fiscalCode(TestConstants.A_DIFFERENT_STRING)
                .birthDate(TestConstants.DIFFERENT_DATE)
                .birthLocation(TestConstants.A_DIFFERENT_STRING)
                .annotations(TestConstants.A_DIFFERENT_STRING)
                .build();

    /**
     * A pre-built {@link PhysicalPerson} which looks like an object fetched from the
     * persistence module, built to have the same values of the standard instances
     * of the same type from this class.
     */
    public static final PhysicalPerson SAME_IDENTIFIED_PH_PERSON =
            new PhysicalPersonImpl.Builder()
                .identifier(TestConstants.THE_SAME_POSITIVE_INTEGER)
                .name(TestConstants.THE_SAME_STRING)
                .fiscalCode(TestConstants.THE_SAME_STRING)
                .birthDate(TestConstants.SAME_DATE)
                .birthLocation(TestConstants.THE_SAME_STRING)
                .annotations(TestConstants.THE_SAME_STRING)
                .build();

    /**
     * A pre-built {@link PhysicalPerson} which looks like an object created by some
     * client code, built to have the same values of the standard instances of the
     * same type from this class, but no identifier.
     */
    public static final PhysicalPerson SAME_UNIDENTIFIED_PH_PERSON =
            new PhysicalPersonImpl.Builder()
                .name(TestConstants.THE_SAME_STRING)
                .fiscalCode(TestConstants.THE_SAME_STRING)
                .birthDate(TestConstants.SAME_DATE)
                .birthLocation(TestConstants.THE_SAME_STRING)
                .annotations(TestConstants.THE_SAME_STRING)
                .build();

    /**
     * A pre-built {@link JuridicalPerson} which looks like an object fetched from the persistence module.
     */
    public static final JuridicalPerson IDENTIFIED_JU_PERSON =
            new JuridicalPersonImpl.Builder()
                .identifier(TestConstants.A_POSITIVE_INTEGER)
                .category(TestEntityConstants.PER_CATEGORY)
                .name(TestConstants.A_STRING)
                .fiscalCode(TestConstants.A_STRING)
                .annotations(TestConstants.A_STRING)
                .build();

    /**
     * A pre-built {@link JuridicalPerson} which looks like an object created by some client code.
     */
    public static final JuridicalPerson UNIDENTIFIED_JU_PERSON =
            new JuridicalPersonImpl.Builder()
                .category(TestEntityConstants.PER_CATEGORY)
                .name(TestConstants.A_STRING)
                .fiscalCode(TestConstants.A_STRING)
                .annotations(TestConstants.A_STRING)
                .build();

    /**
     * A pre-built {@link JuridicalPerson} which looks like an object fetched from the
     * persistence module, built to be different from any other instances of the
     * same type of this class.
     */
    public static final JuridicalPerson DIFFERENT_IDENTIFIED_JU_PERSON =
            new JuridicalPersonImpl.Builder()
                .identifier(TestConstants.A_DIFFERENT_POSITIVE_INTEGER)
                .category(TestEntityConstants.DIFFERENT_PER_CATEGORY)
                .name(TestConstants.A_DIFFERENT_STRING)
                .fiscalCode(TestConstants.A_DIFFERENT_STRING)
                .annotations(TestConstants.A_DIFFERENT_STRING)
                .build();

    /**
     * A pre-built {@link JuridicalPerson} which looks like an object created by some
     * client code, built to be different from any other instances of the same type
     * of this class.
     */
    public static final JuridicalPerson DIFFERENT_UNIDENTIFIED_JU_PERSON =
            new JuridicalPersonImpl.Builder()
                .category(TestEntityConstants.DIFFERENT_PER_CATEGORY)
                .name(TestConstants.A_DIFFERENT_STRING)
                .fiscalCode(TestConstants.A_DIFFERENT_STRING)
                .annotations(TestConstants.A_DIFFERENT_STRING)
                .build();

    /**
     * A pre-built {@link JuridicalPerson} which looks like an object fetched from the
     * persistence module, built to have the same values of the standard instances
     * of the same type from this class.
     */
    public static final JuridicalPerson SAME_IDENTIFIED_JU_PERSON =
            new JuridicalPersonImpl.Builder()
                .identifier(TestConstants.THE_SAME_POSITIVE_INTEGER)
                .category(TestEntityConstants.SAME_PER_CATEGORY)
                .name(TestConstants.THE_SAME_STRING)
                .fiscalCode(TestConstants.THE_SAME_STRING)
                .annotations(TestConstants.THE_SAME_STRING)
                .build();

    /**
     * A pre-built {@link JuridicalPerson} which looks like an object created by some
     * client code, built to have the same values of the standard instances of the
     * same type from this class, but no identifier.
     */
    public static final JuridicalPerson SAME_UNIDENTIFIED_JU_PERSON =
            new JuridicalPersonImpl.Builder()
                .category(TestEntityConstants.SAME_PER_CATEGORY)
                .name(TestConstants.THE_SAME_STRING)
                .fiscalCode(TestConstants.THE_SAME_STRING)
                .annotations(TestConstants.THE_SAME_STRING)
                .build();

    /**
     * A pre-built {@link GenericDevice} which looks like an object fetched from the persistence module.
     */
    public static final GenericDevice IDENTIFIED_DEVICE = 
            new GenericDeviceImpl.Builder()
                .identifier(TestConstants.A_POSITIVE_INTEGER)
                .deviceCategory(TestEntityConstants.DEV_CATEGORY)
                .vendor(TestEntityConstants.VENDOR)
                .vendorModelIdentifier(TestConstants.A_STRING)
                .available(TestConstants.A_POSITIVE_INTEGER)
                .description(TestConstants.A_STRING)
                .build();

    /**
     * A pre-built {@link GenericDevice} which looks like an object created by some client code.
     */
    public static final GenericDevice UNIDENTIFIED_DEVICE = 
            new GenericDeviceImpl.Builder()
                .deviceCategory(TestEntityConstants.DEV_CATEGORY)
                .vendor(TestEntityConstants.VENDOR)
                .vendorModelIdentifier(TestConstants.A_STRING)
                .available(TestConstants.A_POSITIVE_INTEGER)
                .description(TestConstants.A_STRING)
                .build();
    /**
     * A pre-built {@link GenericDevice} which looks like an object fetched from the
     * persistence module, built to be different from any other instances of the
     * same type of this class.
     */
    public static final GenericDevice DIFFERENT_IDENTIFIED_DEVICE = 
            new GenericDeviceImpl.Builder()
                .identifier(TestConstants.A_DIFFERENT_POSITIVE_INTEGER)
                .deviceCategory(TestEntityConstants.DIFFERENT_DEV_CATEGORY)
                .vendor(TestEntityConstants.DIFFERENT_VENDOR)
                .vendorModelIdentifier(TestConstants.A_DIFFERENT_STRING)
                .available(TestConstants.A_DIFFERENT_POSITIVE_INTEGER)
                .description(TestConstants.A_DIFFERENT_STRING)
                .build();

    /**
     * A pre-built {@link GenericDevice} which looks like an object created by some
     * client code, built to be different from any other instances of the same type
     * of this class.
     */
    public static final GenericDevice DIFFERENT_UNIDENTIFIED_DEVICE = 
            new GenericDeviceImpl.Builder()
                .deviceCategory(TestEntityConstants.DIFFERENT_DEV_CATEGORY)
                .vendor(TestEntityConstants.DIFFERENT_VENDOR)
                .vendorModelIdentifier(TestConstants.A_DIFFERENT_STRING)
                .available(TestConstants.A_DIFFERENT_POSITIVE_INTEGER)
                .description(TestConstants.A_DIFFERENT_STRING)
                .build();
    /**
     * A pre-built {@link GenericDevice} which looks like an object fetched from the
     * persistence module, built to have the same values of the standard instances
     * of the same type from this class.
     */
    public static final GenericDevice SAME_IDENTIFIED_DEVICE = 
            new GenericDeviceImpl.Builder()
                .identifier(TestConstants.THE_SAME_POSITIVE_INTEGER)
                .deviceCategory(TestEntityConstants.SAME_DEV_CATEGORY)
                .vendor(TestEntityConstants.SAME_VENDOR)
                .vendorModelIdentifier(TestConstants.THE_SAME_STRING)
                .available(TestConstants.THE_SAME_POSITIVE_INTEGER)
                .description(TestConstants.THE_SAME_STRING)
                .build();
    /**
     * A pre-built {@link GenericDevice} which looks like an object created by some
     * client code, built to have the same values of the standard instances of the
     * same type from this class, but no identifier.
     */
    public static final GenericDevice SAME_UNIDENTIFIED_DEVICE = 
            new GenericDeviceImpl.Builder()
                .deviceCategory(TestEntityConstants.SAME_DEV_CATEGORY)
                .vendor(TestEntityConstants.SAME_VENDOR)
                .vendorModelIdentifier(TestConstants.THE_SAME_STRING)
                .available(TestConstants.THE_SAME_POSITIVE_INTEGER)
                .description(TestConstants.THE_SAME_STRING)
                .build();
    /**
     * A pre-built {@link RefinedDevice} which looks like an object fetched from the persistence module.
     */
    public static final RefinedDevice IDENTIFIED_REFINED = 
            new RefinedDeviceImpl.Builder()
                .identifier(TestConstants.A_POSITIVE_INTEGER)
                .deviceCategory(TestEntityConstants.DEV_CATEGORY)
                .categoryDeviceId(TestConstants.A_POSITIVE_INTEGER)
                .refining(IDENTIFIED_DEVICE)
                .progress(TestEntityConstants.WORK_PROGRESS)
                .available(true)
                .lastCommitter(TestEntityConstants.WORKER)
                .lastUpdate(TestConstants.DATE)
                .annotations(TestConstants.A_STRING)
                .build();

    /**
     * A pre-built {@link RefinedDevice} which looks like an object created by some client code.
     */
    public static final RefinedDevice UNIDENTIFIED_REFINED = 
            new RefinedDeviceImpl.Builder()
                .categoryDeviceId(TestConstants.A_POSITIVE_INTEGER)
                .deviceCategory(TestEntityConstants.DEV_CATEGORY)
                .refining(IDENTIFIED_DEVICE)
                .progress(TestEntityConstants.WORK_PROGRESS)
                .available(true)
                .lastCommitter(TestEntityConstants.WORKER)
                .lastUpdate(TestConstants.DATE)
                .annotations(TestConstants.A_STRING)
                .build();

    /**
     * A pre-built {@link RefinedDevice} which looks like an object fetched from the
     * persistence module, built to be different from any other instances of the
     * same type of this class.
     */
    public static final RefinedDevice DIFFERENT_IDENTIFIED_REFINED = 
            new RefinedDeviceImpl.Builder()
                .identifier(TestConstants.A_DIFFERENT_POSITIVE_INTEGER)
                .categoryDeviceId(TestConstants.A_DIFFERENT_POSITIVE_INTEGER)
                .deviceCategory(TestEntityConstants.DIFFERENT_DEV_CATEGORY)
                .refining(DIFFERENT_IDENTIFIED_DEVICE)
                .progress(TestEntityConstants.DIFFERENT_WORK_PROGRESS)
                .available(true)
                .lastCommitter(TestEntityConstants.DIFFERENT_WORKER)
                .lastUpdate(TestConstants.DIFFERENT_DATE)
                .annotations(TestConstants.A_DIFFERENT_STRING)
                .build();

    /**
     * A pre-built {@link RefinedDevice} which looks like an object created by some
     * client code, built to be different from any other instances of the same type
     * of this class.
     */
    public static final RefinedDevice DIFFERENT_UNIDENTIFIED_REFINED = 
            new RefinedDeviceImpl.Builder()
                .categoryDeviceId(TestConstants.A_DIFFERENT_POSITIVE_INTEGER)
                .deviceCategory(TestEntityConstants.DIFFERENT_DEV_CATEGORY)
                .refining(DIFFERENT_IDENTIFIED_DEVICE)
                .progress(TestEntityConstants.DIFFERENT_WORK_PROGRESS)
                .available(true)
                .lastCommitter(TestEntityConstants.DIFFERENT_WORKER)
                .lastUpdate(TestConstants.DIFFERENT_DATE)
                .annotations(TestConstants.A_DIFFERENT_STRING)
                .build();

    /**
     * A pre-built {@link RefinedDevice} which looks like an object fetched from the
     * persistence module, built to have the same values of the standard instances
     * of the same type from this class.
     */
    public static final RefinedDevice SAME_IDENTIFIED_REFINED = 
            new RefinedDeviceImpl.Builder()
                .identifier(TestConstants.THE_SAME_POSITIVE_INTEGER)
                .deviceCategory(TestEntityConstants.SAME_DEV_CATEGORY)
                .categoryDeviceId(TestConstants.THE_SAME_POSITIVE_INTEGER)
                .refining(SAME_IDENTIFIED_DEVICE)
                .progress(TestEntityConstants.SAME_WORK_PROGRESS)
                .available(true)
                .lastCommitter(TestEntityConstants.SAME_WORKER)
                .lastUpdate(TestConstants.SAME_DATE)
                .annotations(TestConstants.THE_SAME_STRING)
                .build();

    /**
     * A pre-built {@link RefinedDevice} which looks like an object created by some
     * client code, built to have the same values of the standard instances of the
     * same type from this class, but no identifier.
     */
    public static final RefinedDevice SAME_UNIDENTIFIED_REFINED = 
            new RefinedDeviceImpl.Builder()
                .deviceCategory(TestEntityConstants.SAME_DEV_CATEGORY)
                .categoryDeviceId(TestConstants.THE_SAME_POSITIVE_INTEGER)
                .refining(SAME_IDENTIFIED_DEVICE)
                .progress(TestEntityConstants.SAME_WORK_PROGRESS)
                .available(true)
                .lastCommitter(TestEntityConstants.SAME_WORKER)
                .lastUpdate(TestConstants.SAME_DATE)
                .annotations(TestConstants.THE_SAME_STRING)
                .build();

    /**
     * A pre-built {@link Request} which looks like an object fetched from the persistence module.
     */
    public static final Request IDENTIFIED_REQUEST =
            new RequestImpl.Builder()
                .identifier(TestConstants.A_POSITIVE_INTEGER)
                .applicant(IDENTIFIED_PH_PERSON)
                .creationDate(TestConstants.DATE)
                .progress(TestEntityConstants.REQ_PROGRESS)
                .lastUpdate(TestConstants.DATE)
                .lastCommitter(TestEntityConstants.WORKER)
                .referee(IDENTIFIED_PH_PERSON)
                .signer(IDENTIFIED_PH_PERSON)
                .trelloLink(TestConstants.U_R_L)
                .annotations(TestConstants.A_STRING)
                .priority(TestConstants.A_POSITIVE_INTEGER)
                .build();

    /**
     * A pre-built {@link Request} which looks like an object created by some client code.
     */
    public static final Request UNIDENTIFIED_REQUEST =
            new RequestImpl.Builder()
                .applicant(IDENTIFIED_PH_PERSON)
                .creationDate(TestConstants.DATE)
                .progress(TestEntityConstants.REQ_PROGRESS)
                .lastUpdate(TestConstants.DATE)
                .lastCommitter(TestEntityConstants.WORKER)
                .referee(IDENTIFIED_PH_PERSON)
                .signer(IDENTIFIED_PH_PERSON)
                .trelloLink(TestConstants.U_R_L)
                .annotations(TestConstants.A_STRING)
                .priority(TestConstants.A_POSITIVE_INTEGER)
                .build();

    /**
     * A pre-built {@link Request} which looks like an object fetched from the
     * persistence module, built to be different from any other instances of the
     * same type of this class.
     */
    public static final Request DIFFERENT_IDENTIFIED_REQUEST =
            new RequestImpl.Builder()
                .identifier(TestConstants.A_DIFFERENT_POSITIVE_INTEGER)
                .applicant(DIFFERENT_IDENTIFIED_PH_PERSON)
                .creationDate(TestConstants.DIFFERENT_DATE)
                .progress(TestEntityConstants.DIFFERENT_REQ_PROGRESS)
                .lastUpdate(TestConstants.DIFFERENT_DATE)
                .lastCommitter(TestEntityConstants.DIFFERENT_WORKER)
                .referee(DIFFERENT_IDENTIFIED_PH_PERSON)
                .signer(DIFFERENT_IDENTIFIED_PH_PERSON)
                .trelloLink(TestConstants.DIFFERENT_U_R_L)
                .annotations(TestConstants.A_DIFFERENT_STRING)
                .priority(TestConstants.A_DIFFERENT_POSITIVE_INTEGER)
                .build();

    /**
     * A pre-built {@link Request} which looks like an object created by some
     * client code, built to be different from any other instances of the same type
     * of this class.
     */
    public static final Request DIFFERENT_UNIDENTIFIED_REQUEST =
            new RequestImpl.Builder()
                .applicant(DIFFERENT_IDENTIFIED_PH_PERSON)
                .creationDate(TestConstants.DIFFERENT_DATE)
                .progress(TestEntityConstants.DIFFERENT_REQ_PROGRESS)
                .lastUpdate(TestConstants.DIFFERENT_DATE)
                .lastCommitter(TestEntityConstants.DIFFERENT_WORKER)
                .referee(DIFFERENT_IDENTIFIED_PH_PERSON)
                .signer(DIFFERENT_IDENTIFIED_PH_PERSON)
                .trelloLink(TestConstants.DIFFERENT_U_R_L)
                .annotations(TestConstants.A_DIFFERENT_STRING)
                .priority(TestConstants.A_DIFFERENT_POSITIVE_INTEGER)
                .build();

    /**
     * A pre-built {@link Request} which looks like an object fetched from the
     * persistence module, built to have the same values of the standard instances
     * of the same type from this class.
     */
    public static final Request SAME_IDENTIFIED_REQUEST =
            new RequestImpl.Builder()
                .identifier(TestConstants.THE_SAME_POSITIVE_INTEGER)
                .applicant(SAME_IDENTIFIED_PH_PERSON)
                .creationDate(TestConstants.SAME_DATE)
                .progress(TestEntityConstants.SAME_REQ_PROGRESS)
                .lastUpdate(TestConstants.SAME_DATE)
                .lastCommitter(TestEntityConstants.SAME_WORKER)
                .referee(SAME_IDENTIFIED_PH_PERSON)
                .signer(SAME_IDENTIFIED_PH_PERSON)
                .trelloLink(TestConstants.SAME_U_R_L)
                .annotations(TestConstants.THE_SAME_STRING)
                .priority(TestConstants.THE_SAME_POSITIVE_INTEGER)
                .build();

    /**
     * A pre-built {@link Request} which looks like an object created by some
     * client code, built to have the same values of the standard instances of the
     * same type from this class, but no identifier.
     */
    public static final Request SAME_UNIDENTIFIED_REQUEST =
            new RequestImpl.Builder()
                .applicant(SAME_IDENTIFIED_PH_PERSON)
                .creationDate(TestConstants.SAME_DATE)
                .progress(TestEntityConstants.SAME_REQ_PROGRESS)
                .lastUpdate(TestConstants.SAME_DATE)
                .lastCommitter(TestEntityConstants.SAME_WORKER)
                .referee(SAME_IDENTIFIED_PH_PERSON)
                .signer(SAME_IDENTIFIED_PH_PERSON)
                .trelloLink(TestConstants.SAME_U_R_L)
                .annotations(TestConstants.THE_SAME_STRING)
                .priority(TestConstants.THE_SAME_POSITIVE_INTEGER)
                .build();

    /**
     * A pre-built {@link RequestDetail} which looks like an object fetched from the persistence module.
     */
    public static final RequestDetail IDENTIFIED_REQUEST_DETAIL =
            new RequestDetailImpl.Builder()
                .identifier(TestConstants.A_POSITIVE_INTEGER)
                .associatedRequest(IDENTIFIED_REQUEST)
                .category(TestEntityConstants.DEV_CATEGORY)
                .annotations(TestConstants.A_STRING)
                .quantity(TestConstants.A_POSITIVE_INTEGER)
                .build();

    /**
     * A pre-built {@link RequestDetail} which looks like an object created by some client code.
     */
    public static final RequestDetail UNIDENTIFIED_REQUEST_DETAIL =
            new RequestDetailImpl.Builder()
                .associatedRequest(IDENTIFIED_REQUEST)
                .category(TestEntityConstants.DEV_CATEGORY)
                .annotations(TestConstants.A_STRING)
                .quantity(TestConstants.A_POSITIVE_INTEGER)
                .build();

    /**
     * A pre-built {@link RequestDetail} which looks like an object fetched from the
     * persistence module, built to be different from any other instances of the
     * same type of this class.
     */
    public static final RequestDetail DIFFERENT_IDENTIFIED_REQUEST_DETAIL =
            new RequestDetailImpl.Builder()
                .identifier(TestConstants.A_DIFFERENT_POSITIVE_INTEGER)
                .associatedRequest(DIFFERENT_IDENTIFIED_REQUEST)
                .category(TestEntityConstants.DIFFERENT_DEV_CATEGORY)
                .annotations(TestConstants.A_DIFFERENT_STRING)
                .quantity(TestConstants.A_DIFFERENT_POSITIVE_INTEGER)
                .build();

    /**
     * A pre-built {@link RequestDetail} which looks like an object created by some
     * client code, built to be different from any other instances of the same type
     * of this class.
     */
    public static final RequestDetail DIFFERENT_UNIDENTIFIED_REQUEST_DETAIL =
            new RequestDetailImpl.Builder()
                .associatedRequest(DIFFERENT_IDENTIFIED_REQUEST)
                .category(TestEntityConstants.DIFFERENT_DEV_CATEGORY)
                .annotations(TestConstants.A_DIFFERENT_STRING)
                .quantity(TestConstants.A_DIFFERENT_POSITIVE_INTEGER)
                .build();

    /**
     * A pre-built {@link RequestDetail} which looks like an object fetched from the
     * persistence module, built to have the same values of the standard instances
     * of the same type from this class.
     */
    public static final RequestDetail SAME_IDENTIFIED_REQUEST_DETAIL =
            new RequestDetailImpl.Builder()
                .identifier(TestConstants.THE_SAME_POSITIVE_INTEGER)
                .associatedRequest(SAME_IDENTIFIED_REQUEST)
                .category(TestEntityConstants.SAME_DEV_CATEGORY)
                .annotations(TestConstants.THE_SAME_STRING)
                .quantity(TestConstants.THE_SAME_POSITIVE_INTEGER)
                .build();

    /**
     * A pre-built {@link RequestDetail} which looks like an object created by some
     * client code, built to have the same values of the standard instances of the
     * same type from this class, but no identifier.
     */
    public static final RequestDetail SAME_UNIDENTIFIED_REQUEST_DETAIL =
            new RequestDetailImpl.Builder()
                .associatedRequest(SAME_IDENTIFIED_REQUEST)
                .category(TestEntityConstants.SAME_DEV_CATEGORY)
                .annotations(TestConstants.THE_SAME_STRING)
                .quantity(TestConstants.THE_SAME_POSITIVE_INTEGER)
                .build();

    /**
     * A pre-built {@link ScreenResolution} which looks like an object fetched from the persistence module.
     */
    public static final ScreenResolution IDENTIFIED_SCREEN_RESOLUTION =
            new ScreenResolutionImpl.Builder()
                .identifier(TestConstants.A_POSITIVE_INTEGER)
                .width(TestConstants.A_POSITIVE_INTEGER)
                .height(TestConstants.A_POSITIVE_INTEGER)
                .aspectRatio(TestEntityConstants.RATIO)
                .build();

    /**
     * A pre-built {@link ScreenResolution} which looks like an object created by some client code.
     */
    public static final ScreenResolution UNIDENTIFIED_SCREEN_RESOLUTION =
            new ScreenResolutionImpl.Builder()
                .width(TestConstants.A_POSITIVE_INTEGER)
                .height(TestConstants.A_POSITIVE_INTEGER)
                .aspectRatio(TestEntityConstants.RATIO)
                .build();

    /**
     * A pre-built {@link ScreenResolution} which looks like an object fetched from the
     * persistence module, built to be different from any other instances of the
     * same type of this class.
     */
    public static final ScreenResolution DIFFERENT_IDENTIFIED_SCREEN_RESOLUTION =
            new ScreenResolutionImpl.Builder()
                .identifier(TestConstants.A_DIFFERENT_POSITIVE_INTEGER)
                .width(TestConstants.A_DIFFERENT_POSITIVE_INTEGER)
                .height(TestConstants.A_DIFFERENT_POSITIVE_INTEGER)
                .aspectRatio(TestEntityConstants.DIFFERENT_RATIO)
                .build();

    /**
     * A pre-built {@link ScreenResolution} which looks like an object created by some
     * client code, built to be different from any other instances of the same type
     * of this class.
     */
    public static final ScreenResolution DIFFERENT_UNIDENTIFIED_SCREEN_RESOLUTION =
            new ScreenResolutionImpl.Builder()
                .width(TestConstants.A_DIFFERENT_POSITIVE_INTEGER)
                .height(TestConstants.A_DIFFERENT_POSITIVE_INTEGER)
                .aspectRatio(TestEntityConstants.DIFFERENT_RATIO)
                .build();

    /**
     * A pre-built {@link ScreenResolution} which looks like an object fetched from the
     * persistence module, built to have the same values of the standard instances
     * of the same type from this class.
     */
    public static final ScreenResolution SAME_IDENTIFIED_SCREEN_RESOLUTION =
            new ScreenResolutionImpl.Builder()
                .identifier(TestConstants.THE_SAME_POSITIVE_INTEGER)
                .width(TestConstants.THE_SAME_POSITIVE_INTEGER)
                .height(TestConstants.THE_SAME_POSITIVE_INTEGER)
                .aspectRatio(TestEntityConstants.SAME_RATIO)
                .build();

    /**
     * A pre-built {@link ScreenResolution} which looks like an object created by some
     * client code, built to have the same values of the standard instances of the
     * same type from this class, but no identifier.
     */
    public static final ScreenResolution SAME_UNIDENTIFIED_SCREEN_RESOLUTION =
            new ScreenResolutionImpl.Builder()
                .width(TestConstants.THE_SAME_POSITIVE_INTEGER)
                .height(TestConstants.THE_SAME_POSITIVE_INTEGER)
                .aspectRatio(TestEntityConstants.SAME_RATIO)
                .build();

    /**
     * A work-around to an unexpected problem.
     */
    public static final boolean BREAK_CYCLIC_DEPENDENCY_LOOPHOLE = true;

    private TestIdentifiableConstants() {
    }

}
