package todo.model.level.parser;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import todo.model.level.Level;
import todo.model.level.LevelImpl.LevelBuilder;
import todo.model.level.inputs.InputGenerator;
import todo.model.level.inputs.InputModifier;
import todo.model.level.inputs.ModifierWithBound;
import todo.model.level.inputs.ModifierWithCount;
import todo.model.level.inputs.RandomIntegersGenerator;
import todo.vm.Value;
import todo.vm.instructions.Instruction;
import todo.vm.instructions.Jump;
import todo.vm.instructions.JumpInstruction;

public class LevelParserImpl implements LevelParser {
    private static final String INPUTS_PACKAGE = "todo.model.level.inputs";
    private static final String INSTRUCTIONS_PACKAGE = "todo.vm.instructions";
    private static final String PARSER_PACKAGE = "todo.model.level.parser";

    private static final String TITLE_NODE = "title";
    private static final String DESCRIPTION_NODE = "description";
    private static final String MEMORY_ADDRESSES_NODE = "memoryAddresses";
    private static final String PROGRESSIVE_NUMBER_NODE = "progressiveNumber";
    private static final String INPUT_GENERATOR_NODE = "inputGenerator";
    private static final String ADDRESSES_VALUES_NODE = "addressesValues";
    private static final String ALLOWED_INSTRUCTIONS_NODE = "allowedInstructions";
    private static final String SOLUTION_NODE = "solution";

    private static final String INDEX_ATTRIBUTE = "index";
    private static final String LABEL_ATTRIBUTE = "label";
    private static final String FORMAT_ATTRIBUTE = "format";
    private static final String VALUE_ATTRIBUTE = "value";
    private static final String REF_ATTRIBUTE = "ref";
    private static final String COUNT_ATTRIBUTE = "minCount";
    private static final String BOUND_ATTRIBUTE = "bound";

    private static final String ROWS_TAG = "rows";
    private static final String COLUMNS_TAG = "columns";

    private static final String GENERIC_SETTER = "set";
    private static final String ASCII_FORMAT = "ascii";

    private LevelBuilder builder;
    private Element level;

    @Override
    public Level parseLevel(final InputStream filePath) throws LevelParsingException {
        init(filePath);
        parseSimpleInformations();
        parseInputGenerator();
        parseAllowedInstructions();
        parseSolution();
        parseMemoryAddresses();
        return this.builder.build();
    }

    private void init(final InputStream pathfile) throws LevelParsingException {
        try {
            this.builder = new LevelBuilder();
            final Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(pathfile);
            this.level = document.getDocumentElement();
        } catch (final ParserConfigurationException e) {
            throw new LevelParsingException(
                    "A severe parser configuration error occurred for " + pathfile + " pathfile");
        } catch (final IOException e) {
            throw new LevelParsingException("There is something wrong with the " + pathfile + " pathfile");
        } catch (final SAXException e) {
            throw new LevelParsingException(
                    "An error occurred while trying to parse the document contained in the " + pathfile + " pathfile");
        }
    }

    private void parseSimpleInformations() {
        // null is safe because it is blocked in the builder
        parseTag(TITLE_NODE, REF_ATTRIBUTE, this.builder::setTitle);
        parseTag(MEMORY_ADDRESSES_NODE, ROWS_TAG, n -> this.builder.setMemoryRows(Integer.parseInt(n)));
        parseTag(MEMORY_ADDRESSES_NODE, COLUMNS_TAG, n -> this.builder.setMemoryColumns(Integer.parseInt(n)));
        parseTag(DESCRIPTION_NODE, REF_ATTRIBUTE, this.builder::setDescription);
        parseTag(PROGRESSIVE_NUMBER_NODE, REF_ATTRIBUTE, n -> this.builder.setNumber(Integer.parseInt(n)));
    }

    private void parseTag(final String parameter, final String attributeName, final Consumer<String> setter) {
        final Node node = this.level.getElementsByTagName(parameter).item(0);
        setter.accept(node.getNodeType() == Node.ELEMENT_NODE ? ((Element) node).getAttribute(attributeName) : null);
    }

    private void parseInputGenerator() throws LevelParsingException {
        final Node generatorNode = this.level.getElementsByTagName(INPUT_GENERATOR_NODE).item(0);

        // Create a list of modifiers
        final List<InputModifier> modifiers = new ArrayList<>();

        final NodeList generatorList = generatorNode.getChildNodes();
        for (int i = 0; i < generatorList.getLength(); i++) {
            if (generatorList.item(i).getNodeType() == Node.ELEMENT_NODE) {
                final Element element = (Element) generatorList.item(i);
                final InputModifier mod = getInstance(element.getTagName(), InputModifier.class, Optional.empty(),
                        INPUTS_PACKAGE);
                if (element.hasAttribute(COUNT_ATTRIBUTE)) {
                    final ModifierWithCount modWithCount = (ModifierWithCount) mod;
                    modWithCount.setCount(Integer.parseInt(element.getAttribute(COUNT_ATTRIBUTE)));
                }
                if (element.hasAttribute(BOUND_ATTRIBUTE)) {
                    final ModifierWithBound modWithBound = (ModifierWithBound) mod;
                    modWithBound.setBound(Integer.parseInt(element.getAttribute(BOUND_ATTRIBUTE)));
                }
                modifiers.add(mod);
            }
        }
        // Create the InputGenerator
        final InputGenerator generator = new RandomIntegersGenerator(modifiers);
        // Set min, max and size for the Generator
        if (generatorNode.getNodeType() == Node.ELEMENT_NODE) {
            final Element inputElement = (Element) generatorList;
            for (int j = 0; j < inputElement.getAttributes().getLength(); j++) {
                final Node attribute = inputElement.getAttributes().item(j);
                final String setter = attribute.getNodeName().substring(0, 1).toUpperCase()
                        + attribute.getNodeName().substring(1);
                try {
                    generator.getClass()
                             .getMethod(GENERIC_SETTER + setter, int.class)
                             .invoke(generator, Integer.parseInt(attribute.getNodeValue()));
                } catch (final IllegalAccessException e) {
                    throw new LevelParsingException(
                            "You don't have access to the setter for: " + setter + " inside " + generator.getClass());
                } catch (final InvocationTargetException e) {
                    throw new LevelParsingException(
                            "Setter of " + setter + " inside " + generator.getClass() + " throws an exception");
                } catch (final NoSuchMethodException e) {
                    throw new LevelParsingException("There's no setter of " + setter + "inside" + generator.getClass());
                }
            }
        }
        this.builder.setInputGenerator(generator);
    }

    private void parseAllowedInstructions() throws LevelParsingException {
        final NodeList instructionsList = this.level.getElementsByTagName(ALLOWED_INSTRUCTIONS_NODE)
                                                    .item(0)
                                                    .getChildNodes();
        for (int i = 0; i < instructionsList.getLength(); i++) {
            if (instructionsList.item(i).getNodeType() == Node.ELEMENT_NODE) {
                final Element singleInstruction = (Element) instructionsList.item(i);
                final String className = singleInstruction.getTagName().substring(0, 1).toUpperCase()
                        + singleInstruction.getTagName().substring(1);
                final Class<? extends Instruction> type = getClassFromName(className, Instruction.class,
                        INSTRUCTIONS_PACKAGE, PARSER_PACKAGE);
                if (type == Jump.class) {
                    this.builder.addAllowedInstruction(new Jump().getTarget().getClass());
                }
                this.builder.addAllowedInstruction(type);
            }
        }
    }

    private void parseSolution() throws LevelParsingException {
        // First part, create the temporary solution and populate the jumps map
        final List<Instruction> temporarySolution = new ArrayList<>();
        final Map<String, JumpInstruction> jumps = new HashMap<>();
        final NodeList solutionList = this.level.getElementsByTagName(SOLUTION_NODE).item(0).getChildNodes();
        for (int i = 0; i < solutionList.getLength(); i++) {
            if (solutionList.item(i).getNodeType() == Node.ELEMENT_NODE) {
                final Element singleInstruction = (Element) solutionList.item(i);
                final String className = singleInstruction.getTagName().substring(0, 1).toUpperCase()
                        + singleInstruction.getTagName().substring(1);
                Instruction type;
                // If the instruction works with memory addresses
                if (singleInstruction.hasAttribute(INDEX_ATTRIBUTE)) {
                    type = getInstance(className, Instruction.class,
                            Optional.of(Integer.parseInt(singleInstruction.getAttribute(INDEX_ATTRIBUTE))),
                            INSTRUCTIONS_PACKAGE, PARSER_PACKAGE);
                } else {
                    type = getInstance(className, Instruction.class, Optional.empty(), INSTRUCTIONS_PACKAGE,
                            PARSER_PACKAGE);
                }
                // Populate the jump map
                if (JumpInstruction.class.isAssignableFrom(type.getClass())) {
                    final JumpInstruction jump = (JumpInstruction) type;
                    jumps.put(singleInstruction.getAttribute(LABEL_ATTRIBUTE), jump);
                }
                if (type.getClass() == Target.class) {
                    final Target target = (Target) type;
                    target.setLabel(singleInstruction.getAttribute(LABEL_ATTRIBUTE));
                    type = target;
                }
                temporarySolution.add(type);
            }
        }

        // Second part, go from temporary solution to actual solution
        temporarySolution.stream().map(instr -> {
            if (instr.getClass() == Target.class) {
                final Target target = (Target) instr;
                return jumps.get(target.getLabel()).getTarget();
            }
            return instr;
        }).forEach(this.builder::addToSolution);
    }

    private void parseMemoryAddresses() {
        // If there's no tag for memory addresses' values it leaves all empty Optional
        if (this.level.getElementsByTagName(ADDRESSES_VALUES_NODE).item(0) == null) {
            return;
        }
        final NodeList memoryAddressesList = this.level.getElementsByTagName(ADDRESSES_VALUES_NODE)
                                                       .item(0)
                                                       .getChildNodes();
        for (int i = 0; i < memoryAddressesList.getLength(); i++) {
            if (memoryAddressesList.item(i).getNodeType() == Node.ELEMENT_NODE) {
                final Element singleAddress = (Element) memoryAddressesList.item(i);
                final int number = Integer.parseInt(singleAddress.getAttribute(VALUE_ATTRIBUTE));
                final String format = singleAddress.getAttribute(FORMAT_ATTRIBUTE);
                final Value value = format.equals(ASCII_FORMAT) ? Value.ascii((char) number) : Value.number(number);
                this.builder.addValueToAddress(value, Integer.parseInt(singleAddress.getAttribute(INDEX_ATTRIBUTE)));
            }
        }
    }

    private <T> T getInstance(final String className, final Class<T> parent, final Optional<Integer> variable,
            final String... packages) throws LevelParsingException {
        final Class<T> found = getClassFromName(className, parent, packages);
        try {
            if (variable.isPresent()) {
                return found.getConstructor(int.class).newInstance(variable.get());
            } else {
                return found.newInstance();
            }
        } catch (final InstantiationException exception) {
            throw new LevelParsingException("Can't create an instance of the class: " + className);
        } catch (final IllegalAccessException e) {
            throw new LevelParsingException("You don't have access to the class: " + className);
        } catch (final NoSuchMethodException e) {
            throw new LevelParsingException("No constructor with that argument found for class: " + className);
        } catch (final InvocationTargetException e) {
            throw new LevelParsingException("An exception was found while calling methods on class: " + className);
        }
    }

    @SuppressWarnings("unchecked")
    private <T> Class<T> getClassFromName(final String className, final Class<T> parent, final String... packages)
            throws LevelParsingException {
        Class<?> found = null;
        for (final String pkg : packages) {
            try {
                found = Class.forName(pkg + "." + className);
                break;
            } catch (final ClassNotFoundException e) {
                // Thrown later
            }
        }
        if (found == null) {
            throw new LevelParsingException("The class: " + className + " doesn't exist in the searched packages");
        } else if (parent.isAssignableFrom(found)) {
            // Downcast is safe because it was checked at runtime
            return (Class<T>) found;
        } else {
            throw new LevelParsingException("The class: " + className + " is of the wrong type");
        }
    }
}
