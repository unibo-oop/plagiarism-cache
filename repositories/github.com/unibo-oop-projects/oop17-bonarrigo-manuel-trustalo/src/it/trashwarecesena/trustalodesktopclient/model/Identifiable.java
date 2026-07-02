package it.trashwarecesena.trustalodesktopclient.model;

import java.util.Optional;

import it.trashwarecesena.trustalodesktopclient.repository.metamapping.annotations.InterfaceMethodToSchemaField;

/**
 * 
 * This interface aims to solve the problem of the missing biunivocity between
 * the instance of a class and the mapped record from whatsoever table of the
 * database due to the lack of a natural key identifying the information
 * expressed.
 * <p>
 * The existence alone of the need of such an interface creates a huge hole in
 * the cleanliness of the object-oriented domain model, since the numeric
 * identifying value this interface is strongly recommended to be backed up with
 * can <b>only</b> be dictated by whatsoever module is actually in charge of
 * reading from the persistence storage, which in turn can only be a relational
 * database or similar, where the similarity is due to the ability of creating
 * an auto-incremental unique number associated to every record of the relative
 * table schema.
 * <p>
 * The implementation itself of this interface beyond the actual usage is
 * highly discouraged, and any refactoring effort should be towards the removal
 * of the need for it.
 * <p>
 * The rules to manage an implementor of this interface are several, and not
 * complying with any of these will be extremely dangerous for the integrity of
 * the system, with consequences ranging from unexpected exceptions to the
 * system halting without any regards of information loss.
 * 
 * <ol>
 * <li>no module of the system can choose what number the identifier will be,
 * with the exception of the persistence layer.</li>
 * <li>an Identifiable instance proposed for creation over the persistence layer
 * will be rejected if it already owns an identifier.</li>
 * <li>an Identifiable instance proposed for update over the persistence layer
 * will be rejected if the object does not contain the same identifier it was
 * obtained with. Even if a numeric identifier is provided, the object content
 * will be double checked before actually performing the update. If the content
 * of the proposed object and the persistent data are different, the update
 * request will be rejected.</li>
 * <li>an Identifiable instance proposed for deletion over the persistence layer
 * will be rejected if the object does not contain the same identifier it was
 * obtained with.Even if a numeric identifier is provided, the object content
 * will be double checked before actually performing the deletion. If the
 * content of the proposed object and the persistent data are different, the
 * deletion request will be rejected.</li>
 * <li>Even if an object is not an Identifiable, but is composed of at least an
 * Identifiable object, the (2) does not apply for the internal object, since it
 * is expected to be present. The (2) rule still applies to the external object,
 * in the case it is an Identifiable.</li>
 * <li>The (5) must be respected recursively.</li>
 * <li>Almost any combination of the (5) and the other rules is possible, and
 * the expected behaviour is the same.</li>
 * <li>The only means of asserting equality between two identifiable objects is
 * by comparing their numeric identifier first, if present. The absence of such an
 * identifier on both fronts is intended as meaning inequality, following the
 * three-value boolean logic in the table beneath.</li>
 * </ol>
 * 
 * <table>
 * <tr>
 * <td><b>{@code equals(other)}</b></td>
 * <td><b>Present</b></td>
 * <td><b>Absent</b></td>
 * <td><b>Present but different</b></td>
 * </tr>
 * <tr>
 * <td><b>Present</b></td>
 * <td>true</td>
 * <td>false</td>
 * <td>false</td>
 * </tr>
 * <tr>
 * <td><b>Absent</b></td>
 * <td>false</td>
 * <td>false</td>
 * <td>false</td>
 * </tr>
 * <tr>
 * <td><b>Present but different</b></td>
 * <td>false</td>
 * <td>false</td>
 * <td>false</td>
 * </tr>
 * </table>
 * <p>
 * It is quite relevant to notice that proposing an object for creation over the
 * persistence layer does not in any way mean such an object reference is
 * actually useful (except for further creations with the same informations)
 * even if the creation is successful, since it is missing the persistence
 * assigned identifier. It is <b>mandatory</b> to query the persistence layer
 * again with all the informations already provided and let a human being choose
 * between all the possibilities matching those criteria.
 * 
 * @see {@link it.trashwarecesena.trustalodesktopclient.repository.query.criteria.QueryObject QueryObject}
 * 
 * @author Manuel Bonarrigo
 *
 */
public interface Identifiable {

    /**
     * Retrieves the unique identifier which ties the instance of this object to the
     * persistent data it is mapping.
     * 
     * @return an {@link Optional}<{@link Integer}> expressing such a relationship,
     *         or Optional.empty, if such a relationship does not exists yet
     */
    @InterfaceMethodToSchemaField(returnType = Integer.class, schemaField = "ID")
    Optional<Integer> getNumericIdentifier();

}
