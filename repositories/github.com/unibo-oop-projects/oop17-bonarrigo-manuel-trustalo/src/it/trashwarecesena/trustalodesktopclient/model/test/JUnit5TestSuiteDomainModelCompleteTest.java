package it.trashwarecesena.trustalodesktopclient.model.test;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.runner.RunWith;

/**
 * A JUnit Suite test invoked over all the packages of the domain model.
 * 
 * @author Manuel Bonarrigo
 *
 */
@RunWith(JUnitPlatform.class)
@SelectPackages({"it.trashwarecesena.trustalodesktopclient.model.test.devices", 
                "it.trashwarecesena.trustalodesktopclient.model.test.immutable",
                "it.trashwarecesena.trustalodesktopclient.model.test.otherdevices",
                "it.trashwarecesena.trustalodesktopclient.model.test.people",
                "it.trashwarecesena.trustalodesktopclient.model.test.requests"})
public class JUnit5TestSuiteDomainModelCompleteTest { }
