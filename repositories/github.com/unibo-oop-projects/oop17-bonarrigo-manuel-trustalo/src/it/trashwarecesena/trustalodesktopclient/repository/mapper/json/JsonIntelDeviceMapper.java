package it.trashwarecesena.trustalodesktopclient.repository.mapper.json;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;
import java.util.Set;
import java.util.zip.GZIPInputStream;

import com.jayway.jsonpath.JsonPath;

import it.trashwarecesena.trustalodesktopclient.model.immutable.ImmutableIntelProcessor;
import it.trashwarecesena.trustalodesktopclient.model.immutable.concreteness.ImmutableIntelProcessorImpl;
import it.trashwarecesena.trustalodesktopclient.repository.crud.domain.IntelImmutableDomain;
import it.trashwarecesena.trustalodesktopclient.repository.fragmented.ConcreteFragmentedSet;
import it.trashwarecesena.trustalodesktopclient.repository.fragmented.FragmentedSet;
import it.trashwarecesena.trustalodesktopclient.repository.query.BiRequest;
import it.trashwarecesena.trustalodesktopclient.repository.query.QueryRequest;
import it.trashwarecesena.trustalodesktopclient.repository.query.SingleRequest;
import it.trashwarecesena.trustalodesktopclient.repository.query.criteria.QueryObject;
import it.trashwarecesena.trustalodesktopclient.repository.query.interpreter.Interpreter;
import it.trashwarecesena.trustalodesktopclient.repository.query.interpreter.OdataJsonProcessorInterpreter;
import it.trashwarecesena.trustalodesktopclient.utils.ErrorString;

/**
 * A mapper intended to fetch the https://odata.intel.com database to obtain
 * authoritative informations about the Intel processors the Trashware project
 * stumbles upon.
 * 
 * @author Manuel Bonarrigo
 *
 */
public final class JsonIntelDeviceMapper implements IntelImmutableDomain {

    private static final String DOMAIN = " domain";
    private final URL locator;
    private final Map<String, List<String>> headers;
    private final Interpreter interpreter;

    /**
     * Constructs a JsonIntelDeviceMapper over the given informations.
     * 
     * @param locator
     *            the basic {@link URL} any query should start from. It might vary
     *            in the future.
     * @param headers
     *            a Map of headers this mapper is intended to use to build its own
     *            http requests.
     */
    public JsonIntelDeviceMapper(final URL locator, final Map<String, List<String>> headers) {
        super();
        Objects.requireNonNull(locator, ErrorString.STRING_NULL);
        Objects.requireNonNull(headers, "Headers map" + ErrorString.NO_NULL);
        this.locator = locator;
        this.headers = headers;
        this.interpreter = new OdataJsonProcessorInterpreter();
    }

    private URLConnection fillRequestHeaders(final URLConnection connection) {
        Objects.requireNonNull(connection, "URLConnection" + ErrorString.CUSTOM_NULL);
        for (final String key : headers.keySet()) {
            for (final String value : headers.get(key)) {
                connection.addRequestProperty(key, value);
            }
        }
        return connection;
    }

    @Override
    public FragmentedSet dispatchReadRequest(final QueryRequest request) {
        final Class<?> handler = request.getQueryType();
        if (handler.isAssignableFrom(ImmutableIntelProcessor.class)) {
            return new ConcreteFragmentedSet(readIntelProcessors(request.getQueryObject()), 
                    ImmutableIntelProcessor.class);
        } else {
            throw new IllegalStateException("No handler found in " + this.getClass() + " to handle the read request of "
                    + request.getQueryType());
        }
    }

    @Override
    public Set<ImmutableIntelProcessor> readIntelProcessors(final QueryObject filter) {
        final Set<ImmutableIntelProcessor> returnee = new HashSet<>();
        Objects.requireNonNull(filter, "A QueryObject" + ErrorString.CUSTOM_NULL);
        Scanner scanner = null;
        String jsonResult = null;
        URL resource = null;
        URLConnection connection = null;
        try {
            resource = new URL(locator, interpreter.translate(filter));
            connection = fillRequestHeaders(resource.openConnection());
            scanner = new Scanner(new GZIPInputStream(connection.getInputStream()), StandardCharsets.UTF_8.name());
            jsonResult = scanner.useDelimiter("\\A").next();
            scanner.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return returnee;
        } catch (IOException e) {
            e.printStackTrace();
            return returnee;
        }

        for (final Object o : (net.minidev.json.JSONArray) JsonPath.read(jsonResult, "$.d[*]")) {
            returnee.add(new ImmutableIntelProcessorImpl(
                            readProductId(o), 
                            readProcessorNumber(o), 
                            readClockSpeedMHz(o), 
                            readInstructionSet(o), 
                            readCacheType(o), 
                            readCacheKB(o), 
                            readCache(o), 
                            readProductName(o)));
        }
        return returnee;
    }

    private String readProductName(final Object o) {
        return (String) JsonPath.read(o, "$.ProductName");
    }

    private String readCache(final Object o) {
        return (String) JsonPath.read(o, "$.Cache");
    }

    private Integer readCacheKB(final Object o) {
        return (Integer) JsonPath.read(o, "$.CacheKB");
    }

    private String readCacheType(final Object o) {
        return (String) JsonPath.read(o, "$.CacheType");
    }

    private String readInstructionSet(final Object o) {
        return (String) JsonPath.read(o, "$.InstructionSet");
    }

    private Integer readClockSpeedMHz(final Object o) {
        return (Integer) JsonPath.read(o, "$.ClockSpeedMhz");
    }

    private String readProcessorNumber(final Object o) {
        final String returnee = (String) JsonPath.read(o, "$.ProcessorNumber");
        if (returnee == null) {
            return returnee;
        } else {
            return returnee.trim();
        }
    }

    private Integer readProductId(final Object o) {
        return (Integer) JsonPath.read(o, "$.ProductId");
    }

    @Override
    public void createEntry(final ImmutableIntelProcessor processor) {
        throw new UnsupportedOperationException("Create operation not allowed on the " + locator + DOMAIN);
    }

    @Override
    public void dispatchCreateRequest(final SingleRequest request) {
        throw new UnsupportedOperationException("Create operation not allowed on the " + locator + DOMAIN);
    }

    @Override
    public void dispatchUpdateRequest(final BiRequest biRequest) {
        throw new UnsupportedOperationException("Update operation not allowed on the " + locator + DOMAIN);
    }

    @Override
    public void dispatchDeleteRequest(final SingleRequest request) {
        throw new UnsupportedOperationException("Delete operation not allowed on the " + locator + DOMAIN);
    }

    @Override
    public void updateEntry(final ImmutableIntelProcessor oldProcessor, final ImmutableIntelProcessor newProcessor) {
        throw new UnsupportedOperationException("Update operation not allowed on the " + locator + DOMAIN);
    }

    @Override
    public void deleteEntry(final ImmutableIntelProcessor processor) {
        throw new UnsupportedOperationException("Delete operation not allowed on the " + locator + DOMAIN);
    }

}
