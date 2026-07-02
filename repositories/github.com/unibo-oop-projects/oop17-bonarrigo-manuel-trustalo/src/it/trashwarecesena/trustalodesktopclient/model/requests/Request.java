package it.trashwarecesena.trustalodesktopclient.model.requests;

import java.net.URL;
import java.sql.Date;
import java.util.Optional;

import it.trashwarecesena.trustalodesktopclient.model.Identifiable;
import it.trashwarecesena.trustalodesktopclient.model.people.Person;
import it.trashwarecesena.trustalodesktopclient.model.people.TrashwareWorker;
import it.trashwarecesena.trustalodesktopclient.repository.metamapping.annotations.Carrier;
import it.trashwarecesena.trustalodesktopclient.repository.metamapping.annotations.EntityInterface;
import it.trashwarecesena.trustalodesktopclient.repository.metamapping.annotations.InterfaceMethodToSchemaField;
import it.trashwarecesena.trustalodesktopclient.repository.metamapping.annotations.InterfaceToSchemaEntity;

/**
 * Represents a request for a device to the Trashware Project.
 * <p>
 * It is <b>mandatory</b> to understand that being the Request interface
 * part of the
 * {@link it.trashwarecesena.trustalodesktopclient.model.Identifiable
 * Identifiable} family, strict rules do exists about the flow of the
 * information. The Identifiable page of this Javadoc expresses all the required
 * rules.
 * 
 * @author Manuel Bonarrigo
 *
 */
@EntityInterface
@Carrier
@InterfaceToSchemaEntity(schemaEntity = "DeviceRequests")
public interface Request extends Identifiable {

    /**
     * Retrieve the applicant for the device request.
     * 
     * @return a {@link Person} containing all the information of the applicant of
     *         the request.
     */
    @InterfaceMethodToSchemaField(returnType = Person.class, schemaField = "Applicant")
    Person getApplicant();

    /**
     * Retrieve the date of creation of the request.
     * 
     * @return a {@link Date} representing the moment this request was created.
     */
    @InterfaceMethodToSchemaField(returnType = Date.class, schemaField = "Date")
    Date getCreationDate();

    /**
     * Retrieve the state of the progress of the request.
     * 
     * @return a {@link RequestProgress} containing the state of progress of the
     *         request.
     */
    @InterfaceMethodToSchemaField(returnType = RequestProgress.class, schemaField = "CurrentState")
    RequestProgress getRequestProgress();

    /**
     * Retrieve the date of the last update to the request by an employee.
     * 
     * @return a {@link Date} containing the last moment in time an employee changed
     *         the state of the request.
     */
    @InterfaceMethodToSchemaField(returnType = Date.class, schemaField = "LastUpdateDate")
    Date getLastUpdate();

    /**
     * Retrieve the worker who made the last change to the request.
     * 
     * @return the {@link TrashwareWorker} who did the last change to the request.
     */
    @InterfaceMethodToSchemaField(returnType = TrashwareWorker.class, schemaField = "LastUpdateWorker")
    TrashwareWorker getLastCommitter();

    /**
     * Retrieve the person who guarantees for the applicant during the whole
     * request, if any.
     * 
     * @return an Optional containing the referee for the request, or Optional.empty
     *         if none is present.
     */
    @InterfaceMethodToSchemaField(returnType = Person.class, schemaField = "Referee")
    Optional<Person> getReferee();

    /**
     * Retrieve the person who signs in place of applicant for the request, if any.
     * 
     * @return an Optional containing the signer of the request, or Optional.empty
     *         if none is present.
     */
    @InterfaceMethodToSchemaField(returnType = Person.class, schemaField = "Signer")
    Optional<Person> getSigner();

    /**
     * Retrieve a link pointing to a https://trello.com/ page, if any.
     * 
     * @return an {@link Optional} containing an {@link URL} to a Trello page, or
     *         Optional.empty if none is present.
     */
    @InterfaceMethodToSchemaField(returnType = URL.class, schemaField = "TrelloLink")
    Optional<URL> getTrelloLink();

    /**
     * Retrieve any off-the-schema information about the request.
     * 
     * @return a String representing the additional information.
     */
    @InterfaceMethodToSchemaField(returnType = String.class, schemaField = "Annotations")
    Optional<String> getAnnotations();

    /**
     * Retrieve the priority of the request.
     * 
     * @return an {@link Integer} representing the priority of the request.
     */
    @InterfaceMethodToSchemaField(returnType = Integer.class, schemaField = "Priority")
    Integer getPriority();

}
