package it.trashwarecesena.trustalodesktopclient.repository.test;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.runner.RunWith;

/**
 * A JUnit Suite test which tests all the CRUD operations on all the entities in the system.
 * 
 * @author Manuel Bonarrigo
 *
 */
@RunWith(JUnitPlatform.class)
@SelectPackages({"it.trashwarecesena.trustalodesktopclient.repository.test.mapper"})
public class JUnit5TestSuitePersistenceInteractionCompleteTest { }

