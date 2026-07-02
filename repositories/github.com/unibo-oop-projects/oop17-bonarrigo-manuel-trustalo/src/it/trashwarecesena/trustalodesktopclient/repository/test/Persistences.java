package it.trashwarecesena.trustalodesktopclient.repository.test;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactoryConfigurationError;

import org.xml.sax.SAXException;

import it.trashwarecesena.trustalodesktopclient.repository.ConcreteRepository;
import it.trashwarecesena.trustalodesktopclient.repository.Persistence;
import it.trashwarecesena.trustalodesktopclient.repository.PersistenceImpl;
import it.trashwarecesena.trustalodesktopclient.repository.Repository;
import it.trashwarecesena.trustalodesktopclient.repository.adapter.DatabaseConnectionStrategy;
import it.trashwarecesena.trustalodesktopclient.repository.adapter.PersistenceAdapter;
import it.trashwarecesena.trustalodesktopclient.repository.adapter.concreteness.ConcreteDatabaseConnectionStrategy;
import it.trashwarecesena.trustalodesktopclient.repository.adapter.concreteness.MySqlDatabaseAdapter;
import it.trashwarecesena.trustalodesktopclient.repository.adapter.concreteness.OfflineFileAdapter;
import it.trashwarecesena.trustalodesktopclient.repository.adapter.concreteness.RestfulAdapter;
import it.trashwarecesena.trustalodesktopclient.repository.adapter.concreteness.SimpleFileConnectionStrategy;
import it.trashwarecesena.trustalodesktopclient.repository.mapper.PersistenceMapper;
import it.trashwarecesena.trustalodesktopclient.repository.mapper.domain.DevicesPersistenceMapper;
import it.trashwarecesena.trustalodesktopclient.repository.mapper.domain.ImmutableIntelPersistenceMapper;
import it.trashwarecesena.trustalodesktopclient.repository.mapper.domain.PeoplePersistenceMapper;
import it.trashwarecesena.trustalodesktopclient.repository.mapper.domain.ProcessorsPersistenceMapper;
import it.trashwarecesena.trustalodesktopclient.repository.mapper.domain.RequestsPersistenceMapper;
import it.trashwarecesena.trustalodesktopclient.repository.mapper.jooq.JooqMapperFactory;
import it.trashwarecesena.trustalodesktopclient.repository.mapper.json.JsonIntelDeviceMapper;
import it.trashwarecesena.trustalodesktopclient.repository.mapper.xml.ProcessorsXmlMapper;
import it.trashwarecesena.trustalodesktopclient.repository.security.SimpleUserPassLogin;
import it.trashwarecesena.trustalodesktopclient.repository.security.StringPassword;
import it.trashwarecesena.trustalodesktopclient.repository.security.StringUser;
import it.trashwarecesena.trustalodesktopclient.repository.utils.DatabaseLocation;

/**
 * A utility class providing convenience method to test the persistence layer.
 * 
 * @author Manuel Bonarrigo
 *
 */
public final class Persistences {

    private static final String XML = "XML";
    private static final String JSON = "JSON";
    private static final String SQL = "SQL";
    private static final Path XML_PERSISTENCE_PATH = obtainPath();
    private static final URL ODATA_URL = obtainUrl();
    private static final DatabaseConnectionStrategy DB_STRATEGY = obtainConnectionStrategy();

    /**
     * Convenience method to get a fully instantiated persistence layer to be used
     * in every test.
     * 
     * @return a {@link Persistence} reference to be used for testing purpose only.
     */
    public static Persistence retrieveFullyInstantiatedTestingPersistenceSystem() {
        final PersistenceAdapter xml = createOfflineFileAdapter(XML_PERSISTENCE_PATH);
        final PersistenceAdapter json = createRestFulAdapter(ODATA_URL);
        final PersistenceAdapter sql = createRelationalAdapter(DB_STRATEGY);
        final Repository repository = createFacadeRepository(new PersistenceAdapter[] {xml, json, sql});
        final PersistenceMapper peopleMapper = obtainPeopleMapper(extractConnection(sql));
        final PersistenceMapper deviceMapper = obtainDeviceMapper(extractConnection(sql), repository);
        final PersistenceMapper requestMapper = obtainRequestMapper(extractConnection(sql), repository);
        final PersistenceMapper intelMapper = obtainIntelMapper(extractURL(json));
        final PersistenceMapper processorMapper = obtainProcessorMapper(extractDirectory(xml), repository);
        sql.addPersistenceMapper(SQL, peopleMapper);
        sql.addPersistenceMapper(SQL, deviceMapper);
        sql.addPersistenceMapper(SQL, requestMapper);
        json.addPersistenceMapper(JSON, intelMapper);
        xml.addPersistenceMapper(XML, processorMapper);
        return new PersistenceImpl(repository);
    }

    private static Connection extractConnection(final PersistenceAdapter adapter) {
        return (Connection) adapter.getConnection().getConnectionResource();
    }

    private static File extractDirectory(final PersistenceAdapter adapter) {
        return (File) adapter.getConnection().getConnectionResource();
    }

    private static URL extractURL(final PersistenceAdapter adapter) {
        return (URL) adapter.getConnection().getConnectionResource();
    }

    private static PersistenceMapper obtainPeopleMapper(final Connection resource) {
        return new PeoplePersistenceMapper(JooqMapperFactory.createMySqlPeopleJooqMapper(resource));
    }

    private static PersistenceMapper obtainRequestMapper(final Connection resource, final Repository repository) {
        return new RequestsPersistenceMapper(JooqMapperFactory.createMySqlRequestsJooqMapper(resource, repository));
    }

    private static PersistenceMapper obtainDeviceMapper(final Connection resource, final Repository repository) {
        return new DevicesPersistenceMapper(JooqMapperFactory.createMySqlDevicesJooqMapper(resource, repository));
    }

    private static PersistenceMapper obtainIntelMapper(final URL url) {
        return new ImmutableIntelPersistenceMapper(new JsonIntelDeviceMapper(url, getHttpHeaders()));
    }

    private static Map<String, List<String>> getHttpHeaders() {
        final Map<String, List<String>> map = new HashMap<>();
        map.put("Accept", Arrays.asList("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8"));
        map.put("Accept-Encoding", Arrays.asList("gzip"));
        map.put("Accept-Language", Arrays.asList("en-US,en;q=0.5"));
        map.put("Connection", Arrays.asList("keep-alive"));
        map.put("DNT", Arrays.asList("1"));
        map.put("Host", Arrays.asList("odata.intel.com"));
        map.put("Upgrade-Insecure-Requests", Arrays.asList("1"));
        map.put("User-Agent",
                Arrays.asList("Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:60.0) Gecko/20100101 Firefox/60.0"));
        return map;
    }

    private static PersistenceMapper obtainProcessorMapper(final File directory, final Repository repository) {
        try {
            return new ProcessorsPersistenceMapper(new ProcessorsXmlMapper(directory, repository));
        } catch (TransformerConfigurationException | SAXException | IOException | ParserConfigurationException
                | TransformerFactoryConfigurationError e) {
            throw new IllegalStateException();
        }
    }

    private static PersistenceAdapter createOfflineFileAdapter(final Path path) {
        try {
            return new OfflineFileAdapter(XML, new SimpleFileConnectionStrategy(path.toString()));
        } catch (IllegalArgumentException | IOException e) {
            throw new IllegalStateException(e.getMessage());
        }
    }

    private static PersistenceAdapter createRestFulAdapter(final URL url) {
        try {
            return new RestfulAdapter(JSON, url);
        } catch (MalformedURLException e) {
            throw new IllegalStateException();
        }
    }

    private static PersistenceAdapter createRelationalAdapter(final DatabaseConnectionStrategy strategy) {
        return new MySqlDatabaseAdapter(SQL, strategy);
    }

    private static Repository createFacadeRepository(final PersistenceAdapter... adapters) {
        final Repository repo = new ConcreteRepository(adapters[0]);
        IntStream.range(1, adapters.length)
                 .mapToObj(index -> adapters[index])
                 .forEach(adapter -> {
                         repo.addPersistenceAdapter(adapter); 
                     }
                 );
        return repo;
    }

    private static Path obtainPath() {
        return Paths.get(System.getProperty("user.dir"), "res", "document-based-persistence", "xml", "test", 
                "processors");
    }

    private static URL obtainUrl() {
        try {
            return new URL("https://odata.intel.com");
        } catch (MalformedURLException e) {
            throw new IllegalStateException();
        }
    }

    private static DatabaseConnectionStrategy obtainConnectionStrategy() {
        return new ConcreteDatabaseConnectionStrategy(new DatabaseLocation("jdbc:mariadb://localhost:3306/trustalo"), 
                new SimpleUserPassLogin(new StringUser("root"), new StringPassword("")));
    }

    private Persistences() { }

}
