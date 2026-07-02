package it.trashwarecesena.trustalodesktopclient.repository.mapper.xml;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.SchemaFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import it.trashwarecesena.trustalodesktopclient.model.devices.DigitalInformationUnit;
import it.trashwarecesena.trustalodesktopclient.model.devices.GenericDevice;
import it.trashwarecesena.trustalodesktopclient.model.devices.concreteness.DigitalInformationUnitImpl;
import it.trashwarecesena.trustalodesktopclient.model.otherdevices.FrequencyUnit;
import it.trashwarecesena.trustalodesktopclient.model.otherdevices.InstructionSet;
import it.trashwarecesena.trustalodesktopclient.model.otherdevices.Processor;
import it.trashwarecesena.trustalodesktopclient.model.otherdevices.concreteness.FrequencyUnitImpl;
import it.trashwarecesena.trustalodesktopclient.model.otherdevices.concreteness.InstructionSetImpl;
import it.trashwarecesena.trustalodesktopclient.model.otherdevices.concreteness.ProcessorImpl;
import it.trashwarecesena.trustalodesktopclient.repository.Repository;
import it.trashwarecesena.trustalodesktopclient.repository.crud.domain.ProcessorsDomain;
import it.trashwarecesena.trustalodesktopclient.repository.exception.BoundedReferenceException;
import it.trashwarecesena.trustalodesktopclient.repository.exception.DuplicateKeyValueException;
import it.trashwarecesena.trustalodesktopclient.repository.exception.NonExistentReferenceException;
import it.trashwarecesena.trustalodesktopclient.repository.fragmented.ConcreteFragmentedSet;
import it.trashwarecesena.trustalodesktopclient.repository.fragmented.FragmentedSet;
import it.trashwarecesena.trustalodesktopclient.repository.metamapping.MetamappingKnowledge;
import it.trashwarecesena.trustalodesktopclient.repository.query.BiRequest;
import it.trashwarecesena.trustalodesktopclient.repository.query.QueryRequest;
import it.trashwarecesena.trustalodesktopclient.repository.query.SingleRequest;
import it.trashwarecesena.trustalodesktopclient.repository.query.concreteness.ReadRequest;
import it.trashwarecesena.trustalodesktopclient.repository.query.criteria.CriteriaImpl;
import it.trashwarecesena.trustalodesktopclient.repository.query.criteria.CriterionImpl;
import it.trashwarecesena.trustalodesktopclient.repository.query.criteria.QueryObject;
import it.trashwarecesena.trustalodesktopclient.repository.query.criteria.QueryObjectImpl;
import it.trashwarecesena.trustalodesktopclient.repository.query.interpreter.Interpreter;
import it.trashwarecesena.trustalodesktopclient.repository.query.interpreter.XPathInterpreter;
import it.trashwarecesena.trustalodesktopclient.utils.ErrorString;

/**
 * A ProcessorsXmlMapper is a container of method implementation directly able
 * to write information over an XML document database. This mapper manages the
 * four effective implementations of the CRUD requests upon the following
 * classes:
 * 
 * <ul>
 * <li>{@link Processor}</li>
 * <li>{@link FrequencyUnit}</li>
 * <li>{@link InstructionSet}</li>
 * </ul>
 * 
 * @author Manuel Bonarrigo
 *
 */
public final class ProcessorsXmlMapper implements ProcessorsDomain {

    private static final String IN = " in ";
    private static final String IS = "is:";
    private static final String MISSING_REFERENCE = "Missing Reference ";
    private static final String REFERENCES = "References";
    private static final String FU = "fu:";
    private static final String FU_NAME = "fu:Name";
    private static final String IS_NAME = "is:Name";
    private static final String P_DEVICE_MODEL = "p:DeviceModel";
    private static final String P_L3_CACHE_AMOUNT = "p:L3CacheAmount";
    private static final String P_L3_CACHE_INFORMATION_UNIT = "p:L3CacheAmountInformation";
    private static final String DOCUMENT = "document";
    private static final String SCHEMA = "schema";
    private static final String DOCUMENT_FOLDER = File.separator + "document";
    private static final String SCHEMA_FOLDER = File.separator + "schema";

    private final Map<String, Document> documents;
    private final Transformer transformer;
    private final Repository repository;
    private final Interpreter interpreter;
    private final XPath translator;

    /**
     * Constructs a ProcessorsXmlMapper based on the given directory. The mapper
     * needs an operative reference to the persistence system to be able to dispatch
     * requests which esulates its own control.
     * 
     * @param directory
     *            the root directory expected to contain the related information
     *            about the sub-domain
     * @param repository
     *            an operative reference to the persistence system
     * @throws IllegalArgumentException
     *             if the path pointing to the directory is found to be fallacious
     * @throws SAXException
     *             if a SAX parsing error is encountered
     * @throws IOException
     *             if an I\O error is encountered
     * @throws ParserConfigurationException
     *             if the parser is not configured accordingly to expectations
     * @throws TransformerConfigurationException
     *             if the transformer is not configured accordingly to expectations
     * @throws TransformerFactoryConfigurationError
     *             if the transformerfactory is not configured accordingly to
     *             expectations
     */
    public ProcessorsXmlMapper(final File directory, final Repository repository) throws SAXException, IOException, 
            ParserConfigurationException, TransformerConfigurationException, TransformerFactoryConfigurationError {
        if (!Objects.requireNonNull(directory).isDirectory()) {
            throw new IllegalArgumentException("An XmlProcessorsMapper necessitates a directory to search the related "
                    + "XML schema and documents");
        }
        if (!directory.exists()) {
            throw new IllegalArgumentException("An XmlProcessorsMapper necessitates a valid directory to work upon");
        }
        final File[] directoryContent = directory.listFiles();
        if (Objects.isNull(directoryContent)) {
            throw new IllegalStateException(ErrorString.BUG_REPORTING);
        }
        if (!(Arrays.asList(directoryContent)
                .stream()
                .map(file -> file.getName())
                .allMatch(filename -> (filename.equals(DOCUMENT)) || (filename.equals(SCHEMA))))) {
            throw new IllegalArgumentException(directory + " is not a proper xml-based-persistence directory.");
        }
        documents = new HashMap<>();
        final File[] schemaFolderContent = new File(directory.getAbsolutePath() + SCHEMA_FOLDER).listFiles();
        if (Objects.isNull(schemaFolderContent)) {
            throw new IllegalStateException(ErrorString.BUG_REPORTING);
        }
        for (final File xsd : Arrays.asList(schemaFolderContent)) {
            final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setValidating(false);
            factory.setNamespaceAware(true);
            final SchemaFactory schemaFactory = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
            factory.setSchema(schemaFactory.newSchema(new Source[] {new StreamSource(xsd.getAbsolutePath())}));
            final DocumentBuilder builder = factory.newDocumentBuilder();
            builder.setErrorHandler(new SimpleErrorHandler());
            final Document document = builder.parse(new InputSource(directory.getAbsolutePath() + DOCUMENT_FOLDER
                    + File.separator + changeExtension(xsd.getName(), "xml")));
            document.normalize();
            documents.put(removeExtension(xsd.getName()), document);
        }
        this.transformer = TransformerFactory.newInstance().newTransformer();
        this.repository = Objects.requireNonNull(repository);
        this.interpreter = new XPathInterpreter();
        final XPathFactory xpathFactory = XPathFactory.newInstance();
        this.translator = xpathFactory.newXPath();
    }

    @Override
    public void dispatchCreateRequest(final SingleRequest request) {
        final Object payload = request.getPayload();
        if (payload instanceof Processor) {
            createEntry((Processor) payload);
        } else if (payload instanceof InstructionSet) {
            createEntry((InstructionSet) payload);
        } else if (payload instanceof FrequencyUnit) {
            createEntry((FrequencyUnit) payload);
        } else {
            throw new IllegalStateException(
                "No handler available for a create request containing " + request.getDesiredHandler());
        }
    }

    @Override
    public FragmentedSet dispatchReadRequest(final QueryRequest request) {
        final Class<?> handler = request.getQueryType();
        if (handler.isAssignableFrom(Processor.class)) {
            return new ConcreteFragmentedSet(readProcessors(request.getQueryObject()), Processor.class);
        } else if (handler.isAssignableFrom(InstructionSet.class)) {
            return new ConcreteFragmentedSet(readInstructionSets(request.getQueryObject()), InstructionSet.class);
        } else if (handler.isAssignableFrom(FrequencyUnit.class)) {
            return new ConcreteFragmentedSet(readFrequencyUnits(request.getQueryObject()), FrequencyUnit.class);
        } else {
            throw new IllegalStateException("No handler found in " + this.getClass() + " to handle the read request of "
                    + request.getQueryType());
        }
    }

    @Override
    public void dispatchUpdateRequest(final BiRequest biRequest) {
        final Object oldValue = biRequest.getPayload();
        final Object newValue = biRequest.getSecondPayload();
        if (oldValue instanceof Processor) {
            updateEntry((Processor) oldValue, (Processor) newValue);
        } else if (oldValue instanceof InstructionSet) {
            updateEntry((InstructionSet) oldValue, (InstructionSet) newValue);
        } else if (oldValue instanceof FrequencyUnit) {
            updateEntry((FrequencyUnit) oldValue, (FrequencyUnit) newValue);
        } else {
            throw new IllegalStateException(
                "No handler available for an update request containing " + biRequest.getDesiredHandler());
        }
    }

    @Override
    public void dispatchDeleteRequest(final SingleRequest request) {
        final Object payload = request.getPayload();
        if (payload instanceof Processor) {
            deleteEntry((Processor) payload);
        } else if (payload instanceof InstructionSet) {
            deleteEntry((InstructionSet) payload);
        } else if (payload instanceof FrequencyUnit) {
            deleteEntry((FrequencyUnit) payload);
        } else {
            throw new IllegalStateException(
                "No handler available for a delete request containing " + request.getDesiredHandler());
        }
    }

    @Override
    public void createEntry(final Processor processor) {
        if (assertProcessorExistence(processor)) {
            throw new DuplicateKeyValueException();
        }
        try {
            if (!assertGenericDeviceCorrectness(processor.getGenericDevice())) {
                throw new NonExistentReferenceException(MISSING_REFERENCE + "GenericDevice");
            }
            final Document document = getDocument(extractEntityName(processor.getClass()));
            final Element container = document.createElement("p:" + extractCommonName(processor.getClass()));
            final Element deviceModel = document.createElement(P_DEVICE_MODEL);
            deviceModel.appendChild(
                    document.createTextNode(processor.getGenericDevice().getNumericIdentifier().get().toString()));
            container.appendChild(deviceModel);
            final Element frequency = document.createElement("p:Frequency");
            frequency.appendChild(document.createTextNode(processor.getFrequency().toString()));
            container.appendChild(frequency);
            if (!assertFrequencyUnitExistence(processor.getFrequencyUnit())) {
                throw new NonExistentReferenceException(MISSING_REFERENCE + " FrequencyUnit");
            }
            final Element frequencyUnit = document.createElement("p:FrequencyUnit");
            frequencyUnit.appendChild(document.createTextNode(processor.getFrequencyUnit().getName()));
            container.appendChild(frequencyUnit);
            if (!assertInstructionSetExistence(processor.getInstructionSet())) {
                throw new NonExistentReferenceException(MISSING_REFERENCE + " InstructionSet");
            }
            final Element instructionSet = document.createElement("p:InstructionSet");
            instructionSet.appendChild(document.createTextNode(processor.getInstructionSet().getName()));
            container.appendChild(instructionSet);
            if (processor.getL3CacheAmount().isPresent()) {
                final Element cacheAmount = document.createElement(P_L3_CACHE_AMOUNT);
                cacheAmount.appendChild(document.createTextNode(processor.getL3CacheAmount().get().toString()));
                container.appendChild(cacheAmount);
            }
            if (processor.getL3CacheInformationUnit().isPresent()) {
                if (!assertDigitalInformationUnitCorrectness(processor.getL3CacheInformationUnit().get())) {
                    throw new NonExistentReferenceException(MISSING_REFERENCE + "DigitalInformationUnit");
                }
                final Element cacheInformationUnit = document.createElement("p:L3CacheAmountInformation");
                cacheInformationUnit.appendChild(
                        document.createTextNode(processor.getL3CacheInformationUnit().get().getName()));
                container.appendChild(cacheInformationUnit);
            }
            incrementFrequencyUnitReferences(processor.getFrequencyUnit());
            incrementInstructionSetReferences(processor.getInstructionSet());
            document.getDocumentElement().appendChild(container);
            finalizeChanges(document);
        } catch (DOMException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Set<Processor> readProcessors(final QueryObject filter) {
        final Set<Processor> result = new HashSet<>();
        Element iterationElem = null;
        final NodeList nodeList = executeXPathRetrieveStatement(filter);
        for (int i = 0; i < nodeList.getLength(); i++) {
            iterationElem = (Element) nodeList.item(i);
            final ProcessorImpl.Builder builder = new ProcessorImpl.Builder();
            builder.device(discoverGenericDeviceById(
                    Integer.parseInt(iterationElem.getElementsByTagName(P_DEVICE_MODEL)
                        .item(0).getFirstChild().getTextContent()))
                              .orElseThrow(() -> new IllegalStateException(ErrorString.BUG_REPORTING)));
            builder.frequency(Float.parseFloat(iterationElem.getElementsByTagName("p:Frequency").item(0)
                    .getFirstChild().getTextContent()));
            builder.frequencyUnit(new FrequencyUnitImpl(iterationElem.getElementsByTagName("p:FrequencyUnit")
                    .item(0).getFirstChild().getTextContent()));
            builder.instructionSet(new InstructionSetImpl(iterationElem.getElementsByTagName("p:InstructionSet")
                    .item(0).getFirstChild().getTextContent()));
            if (Objects.nonNull(iterationElem.getElementsByTagName(P_L3_CACHE_AMOUNT).item(0))) {
                builder.l3CacheAmount(Integer.parseInt(iterationElem.getElementsByTagName(P_L3_CACHE_AMOUNT)
                        .item(0).getFirstChild().getTextContent()));
            }
            if (Objects.nonNull(iterationElem.getElementsByTagName(P_L3_CACHE_INFORMATION_UNIT).item(0))) {
                builder.l3CacheUnit(new DigitalInformationUnitImpl(
                        iterationElem.getElementsByTagName(P_L3_CACHE_INFORMATION_UNIT)
                        .item(0).getFirstChild().getTextContent()));
            }
            result.add(builder.build());
        }
        return result;
    }

    private NodeList executeXPathRetrieveStatement(final QueryObject filter) {
        try {
            final XPathExpression expression = translator.compile(interpreter.translate(filter));
            return (NodeList) expression.evaluate(getDocument(
                    extractEntityName(filter.getDesiredHandler())).getDocumentElement(), XPathConstants.NODESET);
        } catch (XPathExpressionException e) {
            throw new IllegalStateException(ErrorString.BUG_REPORTING);
        }
    }

    @Override
    public void updateEntry(final Processor oldProcessor, final Processor newProcessor) {
        if (assertProcessorExistence(newProcessor)) {
            throw new DuplicateKeyValueException();
        }
        final Document document = getDocument(extractEntityName(oldProcessor.getClass()));
        final NodeList nodeList = document.getElementsByTagName("p:" + extractCommonName(oldProcessor.getClass()));
        Element iterationElem = null;
        Node iterationNode = null;
        if (nodeList.getLength() > 0) {
            for (int i = 0; i < nodeList.getLength(); i++) {
                iterationNode = nodeList.item(i);
                if (iterationNode.getNodeType() == Node.ELEMENT_NODE) {
                    iterationElem = (Element) iterationNode;
                    if (oldProcessor.getGenericDevice().getNumericIdentifier()
                            .orElseThrow(() -> new IllegalStateException(ErrorString.BUG_REPORTING))
                            .equals(Integer.parseInt(
                                    iterationElem.getElementsByTagName(P_DEVICE_MODEL)
                                    .item(0).getTextContent()))) {
                        if (newProcessor.getGenericDevice().getNumericIdentifier().isPresent()) {
                            if (!assertGenericDeviceCorrectness(newProcessor.getGenericDevice())) {
                                throw new NonExistentReferenceException(MISSING_REFERENCE + "GenericDevice");
                            }
                            iterationElem.getElementsByTagName(
                                    "p:DeviceModel").item(0).setTextContent(
                                            newProcessor.getGenericDevice()
                                            .getNumericIdentifier().get().toString());
                        }
                        iterationElem.getElementsByTagName("p:Frequency")
                            .item(0).setTextContent(newProcessor.getFrequency().toString());
                        if (!assertFrequencyUnitExistence(newProcessor.getFrequencyUnit())) {
                            throw new NonExistentReferenceException(MISSING_REFERENCE + "FrequencyUnit");
                        }
                        iterationElem.getElementsByTagName("p:FrequencyUnit")
                            .item(0).setTextContent(newProcessor.getFrequencyUnit().getName());
                        if (!assertInstructionSetExistence(newProcessor.getInstructionSet())) {
                            throw new NonExistentReferenceException(MISSING_REFERENCE + "InstructionSet");
                        }
                        iterationElem.getElementsByTagName("p:InstructionSet")
                            .item(0).setTextContent(newProcessor.getInstructionSet().getName());
                        final NodeList cacheAmount = iterationElem.getElementsByTagName(P_L3_CACHE_AMOUNT);
                        if (cacheAmount.getLength() == 0 && newProcessor.getL3CacheAmount().isPresent()) {
                            final Element elemCacheAmount = document.createElement("p:L3CacheAmount");
                            elemCacheAmount.appendChild(
                                    document.createTextNode(newProcessor.getL3CacheAmount().get().toString()));
                            iterationElem.appendChild(elemCacheAmount);
                        } else if (cacheAmount.getLength() == 1 && !newProcessor.getL3CacheAmount().isPresent()) {
                            iterationElem.removeChild(cacheAmount.item(0));
                        } else if (cacheAmount.getLength() == 1 && newProcessor.getL3CacheAmount().isPresent()) {
                            iterationElem.getElementsByTagName("p:L3CacheAmount")
                                .item(0).setTextContent(newProcessor.getL3CacheAmount().get().toString());
                        }
                        if (newProcessor.getL3CacheInformationUnit().isPresent()
                                && (!assertDigitalInformationUnitCorrectness(newProcessor
                                        .getL3CacheInformationUnit().get()))) {
                                throw new NonExistentReferenceException(MISSING_REFERENCE 
                                        + "DigitalInformationUnit");
                        }
                        final NodeList cacheInfo = iterationElem.getElementsByTagName(P_L3_CACHE_INFORMATION_UNIT);
                        if (cacheInfo.getLength() == 0 && newProcessor.getL3CacheInformationUnit().isPresent()) {
                            final Element elemCacheInfo = document.createElement(P_L3_CACHE_INFORMATION_UNIT);
                            elemCacheInfo.appendChild(
                                    document.createTextNode(newProcessor.getL3CacheInformationUnit()
                                            .get().toString()));
                            iterationElem.appendChild(elemCacheInfo);
                        } else if (cacheInfo.getLength() == 1 
                                && !newProcessor.getL3CacheInformationUnit().isPresent()) {
                            iterationElem.removeChild(cacheInfo.item(0));
                        } else if (cacheInfo.getLength() == 1 
                                && newProcessor.getL3CacheInformationUnit().isPresent()) {
                            iterationElem.getElementsByTagName(P_L3_CACHE_INFORMATION_UNIT)
                                .item(0).setTextContent(newProcessor.getL3CacheInformationUnit()
                                        .get().getName());
                        }
                        if (!(oldProcessor.getFrequencyUnit().equals(newProcessor.getFrequencyUnit()))) {
                            decrementFrequencyUnitReferences(oldProcessor.getFrequencyUnit());
                            incrementFrequencyUnitReferences(newProcessor.getFrequencyUnit());
                        }
                        if (!(oldProcessor.getInstructionSet().equals(newProcessor.getInstructionSet()))) {
                            decrementInstructionSetReferences(oldProcessor.getInstructionSet());
                            incrementInstructionSetReferences(newProcessor.getInstructionSet());
                        }
                        finalizeChanges(document);
                    }
                }
            }
        } else {
            throw new NonExistentReferenceException(MISSING_REFERENCE + oldProcessor.getClass().getSimpleName());
        }
    }

    @Override
    public void deleteEntry(final Processor processor) {
        final Document document = getDocument(extractEntityName(processor.getClass()));
        final NodeList nodeList = document.getElementsByTagName("p:" + extractCommonName(processor.getClass()));
        Element iterationElem = null;
        Node iterationNode = null;
        if (nodeList.getLength() > 0) {
            for (int i = 0; i < nodeList.getLength(); i++) {
                iterationNode = nodeList.item(i);
                if (iterationNode.getNodeType() == Node.ELEMENT_NODE) {
                    iterationElem = (Element) iterationNode;
                    if (processor.getGenericDevice().getNumericIdentifier()
                        .orElseThrow(() -> new IllegalStateException(ErrorString.BUG_REPORTING))
                            .equals(Integer.parseInt(iterationElem.getElementsByTagName(P_DEVICE_MODEL)
                                .item(0).getTextContent()))) {
                        document.getDocumentElement().removeChild((Node) iterationElem);
                        decrementFrequencyUnitReferences(processor.getFrequencyUnit());
                        decrementInstructionSetReferences(processor.getInstructionSet());
                        finalizeChanges(document);
                    }
                }
            }
        } else {
            throw new NonExistentReferenceException(MISSING_REFERENCE + processor.getClass().getSimpleName());
        }
    }

    @Override
    public void createEntry(final FrequencyUnit unit) {
        if (assertFrequencyUnitExistence(unit)) {
            throw new DuplicateKeyValueException(unit.getName() + IN + unit.getClass().getSimpleName());
        }
        try {
            final Document document = getDocument(extractEntityName(unit.getClass()));
            final Element root = document.getDocumentElement(); 
            final Element container = document.createElement(FU + extractCommonName(unit.getClass()));
            final Element name = document.createElement(FU_NAME);
            name.appendChild(document.createTextNode(unit.getName()));
            name.setAttribute(REFERENCES, "0");
            container.appendChild(name);
            root.appendChild(container);
            finalizeChanges(document);
        } catch (DOMException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Set<FrequencyUnit> readFrequencyUnits(final QueryObject filter) {
        return IntStream
                .range(0, executeXPathRetrieveStatement(filter).getLength())
                .mapToObj(index -> executeXPathRetrieveStatement(filter).item(index))
                .map(node -> new FrequencyUnitImpl(node.getTextContent()))
                .collect(Collectors.toSet());
    }

    @Override
    public void updateEntry(final FrequencyUnit oldUnit, final FrequencyUnit newUnit) {
        if (assertFrequencyUnitExistence(newUnit)) {
            throw new DuplicateKeyValueException(newUnit.getName() + IN + newUnit.getClass().getSimpleName());
        }
        final Document document = getDocument(extractEntityName(oldUnit.getClass()));
        final NodeList nodeList = document.getElementsByTagName(FU + extractCommonName(oldUnit.getClass()));
        Element iterationElem = null;
        Node iterationNode = null;
        if (nodeList.getLength() > 0) {
            for (int i = 0; i < nodeList.getLength(); i++) {
                iterationNode = nodeList.item(i);
                if (iterationNode.getNodeType() == Node.ELEMENT_NODE) {
                    iterationElem = (Element) iterationNode;
                    if (oldUnit.getName().equals(iterationElem.getElementsByTagName(FU_NAME).item(0)
                            .getTextContent())) {
                        iterationElem.getElementsByTagName(FU_NAME).item(0).setTextContent(newUnit.getName());
                        finalizeChanges(document);
                        cascadeFrequencyUnitUpdate(oldUnit, newUnit);
                    }
                }
            }
        } else {
            throw new NonExistentReferenceException(MISSING_REFERENCE + oldUnit.getClass().getSimpleName());
        }
    }

    @Override
    public void deleteEntry(final FrequencyUnit unit) {
        if (!assertFrequencyUnitExistence(unit)) {
            throw new NonExistentReferenceException(unit.getName() + IN + unit.getClass().getSimpleName());
        }
        final Document document = getDocument(extractEntityName(unit.getClass()));
        final NodeList nodeList = document.getElementsByTagName(FU + extractCommonName(unit.getClass()));
        Element iterationElem = null;
        Node iterationNode = null;
        if (nodeList.getLength() > 0) {
            for (int i = 0; i < nodeList.getLength(); i++) {
                iterationNode = nodeList.item(i);
                if (iterationNode.getNodeType() == Node.ELEMENT_NODE) {
                    iterationElem = (Element) iterationNode;
                    if (unit.getName().equals(iterationElem.getElementsByTagName(FU_NAME)
                            .item(0).getTextContent())) {
                        if (Integer.parseInt(iterationElem.getFirstChild().getAttributes().item(0)
                                .getFirstChild().getTextContent()) == 0) {
                            document.getDocumentElement().removeChild((Node) iterationElem);
                            finalizeChanges(document);
                        } else {
                            throw new BoundedReferenceException("Can't delete " + unit.toString() 
                                + ". There are references to it");
                        }
                    }
                }
            }
        } else {
            throw new NonExistentReferenceException(MISSING_REFERENCE + unit.getClass().getSimpleName());
        }
    }

    @Override
    public void createEntry(final InstructionSet isa) {
        if (assertInstructionSetExistence(isa)) {
            throw new DuplicateKeyValueException(isa.getName() + IN + isa.getClass().getSimpleName());
        }
        try {
            final Document document = getDocument(extractEntityName(isa.getClass()));
            final Element root = document.getDocumentElement(); 
            final Element container = document.createElement(IS + extractCommonName(isa.getClass()));
            final Element name = document.createElement(IS_NAME);
            name.appendChild(document.createTextNode(isa.getName()));
            name.setAttribute(REFERENCES, "0");
            container.appendChild(name);
            root.appendChild(container);
            finalizeChanges(document);
        } catch (DOMException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Set<InstructionSet> readInstructionSets(final QueryObject filter) {
        return IntStream
                .range(0, executeXPathRetrieveStatement(filter).getLength())
                .mapToObj(index -> executeXPathRetrieveStatement(filter).item(index))
                .map(node -> new InstructionSetImpl(node.getTextContent()))
                .collect(Collectors.toSet());
    }

    @Override
    public void updateEntry(final InstructionSet oldIsa, final InstructionSet newIsa) {
        if (assertInstructionSetExistence(newIsa)) {
            throw new DuplicateKeyValueException(newIsa.getName() + IN + newIsa.getClass().getSimpleName());
        }
        final Document document = getDocument(extractEntityName(oldIsa.getClass()));
        final NodeList nodeList = document.getElementsByTagName(IS + extractCommonName(oldIsa.getClass()));
        Element iterationElem = null;
        Node iterationNode = null;
        if (nodeList.getLength() > 0) {
            for (int i = 0; i < nodeList.getLength(); i++) {
                iterationNode = nodeList.item(i);
                if (iterationNode.getNodeType() == Node.ELEMENT_NODE) {
                    iterationElem = (Element) iterationNode;
                    if (oldIsa.getName().equals(iterationElem.getElementsByTagName(IS_NAME).item(0)
                            .getTextContent())) {
                        iterationElem.getElementsByTagName(IS_NAME).item(0).setTextContent(newIsa.getName());
                        finalizeChanges(document);
                        cascadeInstructionSetUpdate(oldIsa, newIsa);
                    }
                }
            }
        } else {
            throw new NonExistentReferenceException(MISSING_REFERENCE + oldIsa.getClass().getSimpleName());
        }
    }

    @Override
    public void deleteEntry(final InstructionSet isa) {
        if (!assertInstructionSetExistence(isa)) {
            throw new NonExistentReferenceException(isa.getName() + IN + isa.getClass().getSimpleName());
        }
        final Document document = getDocument(extractEntityName(isa.getClass()));
        final NodeList nodeList = document.getElementsByTagName(IS + extractCommonName(isa.getClass()));
        Element iterationElem = null;
        Node iterationNode = null;
        if (nodeList.getLength() > 0) {
            for (int i = 0; i < nodeList.getLength(); i++) {
                iterationNode = nodeList.item(i);
                if (iterationNode.getNodeType() == Node.ELEMENT_NODE) {
                    iterationElem = (Element) iterationNode;
                    if (isa.getName().equals(iterationElem.getElementsByTagName(IS_NAME).item(0)
                            .getTextContent())) {
                        if (Integer.parseInt(iterationElem.getFirstChild().getAttributes().item(0)
                                .getFirstChild().getTextContent()) == 0) {
                            document.getDocumentElement().removeChild((Node) iterationElem);
                            finalizeChanges(document);
                        } else {
                            throw new BoundedReferenceException("Can't delete " + isa.toString() 
                                + ". There are references to it");
                        }
                    }
                }
            }
        } else {
            throw new NonExistentReferenceException(MISSING_REFERENCE + isa.getClass().getSimpleName());
        }
    }

    private String changeExtension(final String fileName, final String newExtension) {
        final int extPos = fileName.lastIndexOf('.');
        if (extPos == -1) {
            return fileName;
        } else {
            return fileName.substring(0, extPos + 1).concat(newExtension);
        }
    }

    private String removeExtension(final String fileName) {
        return fileName.replaceAll("\\..*?$", "");
    }

    private Document getDocument(final String documentName) {
        return this.documents.get(documentName);
    }

    private String extractEntityName(final Class<?> klass) {
        final Optional<Class<?>> discovery = MetamappingKnowledge.discoverDomainModelInterfaceImplemented(klass);
        return MetamappingKnowledge.getMappedEntityName(discovery
                .orElseThrow(() -> new IllegalArgumentException("No metamapping available for " 
                        + klass.getSimpleName())))
                .orElseThrow(() -> new IllegalArgumentException("No name availbale for " 
                        + klass.getSimpleName()));
    }

    private String extractCommonName(final Class<?> klass) {
        return MetamappingKnowledge.discoverDomainModelInterfaceImplemented(klass)
                .orElseThrow(() -> new IllegalArgumentException("No mapping available for " + klass.getSimpleName()))
                .getSimpleName();
    }

    private void finalizeChanges(final Document document) {
        final DOMSource source = new DOMSource(document);
        StreamResult result = null;
        try {
            result = new StreamResult(new File(new URI(document.getDocumentURI())));
        } catch (URISyntaxException uri) {
            uri.printStackTrace();
        }
        try {
            transformer.transform(source, result);
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

    private Optional<GenericDevice> discoverGenericDeviceById(final Integer identifier) {
        final Set<GenericDevice> result = 
            repository.dispatchReadRequest(
                new ReadRequest(
                        new QueryObjectImpl(
                                GenericDevice.class, 
                                new CriteriaImpl.Builder()
                                    .addCriterion(CriterionImpl.equality("getNumericIdentifier", identifier))
                                    .build())))
            .getUnerasedSet();
        return result.stream().findFirst();
    }

    private boolean assertGenericDeviceCorrectness(final GenericDevice device) {
        return discoverGenericDeviceById(device.getNumericIdentifier()
            .orElseThrow(() -> new IllegalStateException(ErrorString.BUG_REPORTING)))
                .isPresent();
    }

    private boolean assertDigitalInformationUnitCorrectness(final DigitalInformationUnit unit) {
        final Set<DigitalInformationUnit> result = 
                repository.dispatchReadRequest(
                    new ReadRequest(
                            new QueryObjectImpl(
                                    DigitalInformationUnit.class, 
                                    new CriteriaImpl.Builder()
                                        .addCriterion(CriterionImpl.equality("getName", unit.getName()))
                                        .build())))
                .getUnerasedSet();
        return !result.isEmpty();
    }

    private boolean assertProcessorExistence(final Processor processor) {
        final Set<Processor> result = 
                readProcessors(
                    new QueryObjectImpl(
                            Processor.class, 
                            new CriteriaImpl.Builder()
                                .addCriterion(CriterionImpl.equality("getGenericDevice", processor.getGenericDevice()))
                                .build()));
        return result.contains(processor);
    }

    private boolean assertFrequencyUnitExistence(final FrequencyUnit unit) {
        final Set<FrequencyUnit> result = 
                readFrequencyUnits(
                    new QueryObjectImpl(
                            FrequencyUnit.class, 
                            new CriteriaImpl.Builder()
                                .addCriterion(CriterionImpl.equality("getName", unit.getName()))
                                .build()));
        return result.contains(unit);
    }

    private boolean assertInstructionSetExistence(final InstructionSet set) {
        final Set<InstructionSet> result = 
                readInstructionSets(
                    new QueryObjectImpl(
                            InstructionSet.class, 
                            new CriteriaImpl.Builder()
                                .addCriterion(CriterionImpl.equality("getName", set.getName()))
                                .build()));
        return result.contains(set);
    }

    private void incrementFrequencyUnitReferences(final FrequencyUnit unit) {
        try {
            final Document document = getDocument(extractEntityName(unit.getClass()));
            final NodeList nodeList = document.getElementsByTagName(FU + extractCommonName(unit.getClass()));
            Element iterationElem = null;
            Node iterationNode = null;
            if (nodeList.getLength() > 0) {
                for (int i = 0; i < nodeList.getLength(); i++) {
                    iterationNode = nodeList.item(i);
                    if (iterationNode.getNodeType() == Node.ELEMENT_NODE) {
                        iterationElem = (Element) iterationNode;
                        if (unit.getName().equals(iterationElem.getElementsByTagName(FU_NAME).item(0)
                                .getTextContent())) {
                            int ref = Integer.parseInt(iterationElem.getFirstChild().getAttributes().item(0)
                                            .getFirstChild().getTextContent());
                            iterationElem.getFirstChild().getAttributes().item(0).getFirstChild().setTextContent(
                                    String.valueOf(++ref));
                            finalizeChanges(document);
                        }
                    }
                }
            } else {
                throw new NonExistentReferenceException(MISSING_REFERENCE + unit.getClass().getSimpleName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void incrementInstructionSetReferences(final InstructionSet set) {
        try {
            final Document document = getDocument(extractEntityName(set.getClass()));
            final NodeList nodeList = document.getElementsByTagName(IS + extractCommonName(set.getClass()));
            Element iterationElem = null;
            Node iterationNode = null;
            if (nodeList.getLength() > 0) {
                for (int i = 0; i < nodeList.getLength(); i++) {
                    iterationNode = nodeList.item(i);
                    if (iterationNode.getNodeType() == Node.ELEMENT_NODE) {
                        iterationElem = (Element) iterationNode;
                        if (set.getName().equals(iterationElem.getElementsByTagName(IS_NAME).item(0)
                                .getTextContent())) {
                            int ref = Integer.parseInt(iterationElem.getFirstChild().getAttributes().item(0)
                                            .getFirstChild().getTextContent());
                            iterationElem.getFirstChild().getAttributes().item(0).getFirstChild().setTextContent(
                                    String.valueOf(++ref));
                            finalizeChanges(document);
                        }
                    }
                }
            } else {
                throw new NonExistentReferenceException(MISSING_REFERENCE + set.getClass().getSimpleName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void decrementFrequencyUnitReferences(final FrequencyUnit unit) {
        try {
            final Document document = getDocument(extractEntityName(unit.getClass()));
            final NodeList nodeList = document.getElementsByTagName(FU + extractCommonName(unit.getClass()));
            Element iterationElem = null;
            Node iterationNode = null;
            if (nodeList.getLength() > 0) {
                for (int i = 0; i < nodeList.getLength(); i++) {
                    iterationNode = nodeList.item(i);
                    if (iterationNode.getNodeType() == Node.ELEMENT_NODE) {
                        iterationElem = (Element) iterationNode;
                        if (unit.getName().equals(iterationElem.getElementsByTagName(FU_NAME).item(0)
                                .getTextContent())) {
                            int ref = Integer.parseInt(iterationElem.getFirstChild().getAttributes().item(0)
                                            .getFirstChild().getTextContent());
                            iterationElem.getFirstChild().getAttributes().item(0).getFirstChild().setTextContent(
                                    String.valueOf(--ref));
                            finalizeChanges(document);
                        }
                    }
                }
            } else {
                throw new NonExistentReferenceException(MISSING_REFERENCE + unit.getClass().getSimpleName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void decrementInstructionSetReferences(final InstructionSet set) {
        try {
            final Document document = getDocument(extractEntityName(set.getClass()));
            final NodeList nodeList = document.getElementsByTagName(IS + extractCommonName(set.getClass()));
            Element iterationElem = null;
            Node iterationNode = null;
            if (nodeList.getLength() > 0) {
                for (int i = 0; i < nodeList.getLength(); i++) {
                    iterationNode = nodeList.item(i);
                    if (iterationNode.getNodeType() == Node.ELEMENT_NODE) {
                        iterationElem = (Element) iterationNode;
                        if (set.getName().equals(iterationElem.getElementsByTagName(IS_NAME).item(0)
                                .getTextContent())) {
                            int ref = Integer.parseInt(iterationElem.getFirstChild().getAttributes().item(0)
                                            .getFirstChild().getTextContent());
                            if (ref == 0) {
                                throw new IllegalStateException(ErrorString.BUG_REPORTING);
                            }
                            iterationElem.getFirstChild().getAttributes().item(0).getFirstChild().setTextContent(
                                    String.valueOf(--ref));
                            finalizeChanges(document);
                        }
                    }
                }
            } else {
                throw new NonExistentReferenceException(MISSING_REFERENCE + set.getClass().getSimpleName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void cascadeFrequencyUnitUpdate(final FrequencyUnit oldUnit, final FrequencyUnit newUnit) {
        Set<Processor> updatee = readProcessors(new QueryObjectImpl(Processor.class, new CriteriaImpl.Builder()
                .addCriterion(CriterionImpl.all())
                .build()));
        updatee = updatee.stream()
                         .filter(cpu -> cpu.getFrequencyUnit().equals(oldUnit))
                         .collect(Collectors.toSet());
        for (final Processor p : updatee) {
            updateEntry(p, new ProcessorImpl
                                .Builder()
                                .device(p.getGenericDevice())
                                .frequency(p.getFrequency())
                                .frequencyUnit(newUnit)
                                .instructionSet(p.getInstructionSet())
                                .l3CacheAmount(p.getL3CacheAmount().orElse(null))
                                .l3CacheUnit(p.getL3CacheInformationUnit().orElse(null))
                                .build());
        }
    }

    private void cascadeInstructionSetUpdate(final InstructionSet oldIsa, final InstructionSet newIsa) {
        Set<Processor> updatee = readProcessors(new QueryObjectImpl(Processor.class, new CriteriaImpl.Builder()
                .addCriterion(CriterionImpl.all())
                .build()));
        updatee = updatee.stream()
                         .filter(cpu -> cpu.getInstructionSet().equals(oldIsa))
                         .collect(Collectors.toSet());
        for (final Processor p : updatee) {
            updateEntry(p, new ProcessorImpl
                                .Builder()
                                .device(p.getGenericDevice())
                                .frequency(p.getFrequency())
                                .frequencyUnit(p.getFrequencyUnit())
                                .instructionSet(newIsa)
                                .l3CacheAmount(p.getL3CacheAmount().orElse(null))
                                .l3CacheUnit(p.getL3CacheInformationUnit().orElse(null))
                                .build()); 
        }
    }

    private class SimpleErrorHandler implements ErrorHandler {
        public void warning(final SAXParseException e) {
            e.printStackTrace();
        }

        public void error(final SAXParseException e) {
            e.printStackTrace();
        }

        public void fatalError(final SAXParseException e) {
            e.printStackTrace();
        }
    }

}
