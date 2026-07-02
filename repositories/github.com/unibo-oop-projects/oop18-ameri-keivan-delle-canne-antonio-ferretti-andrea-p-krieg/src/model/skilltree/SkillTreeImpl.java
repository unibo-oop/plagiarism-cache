package model.skilltree;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import io.github.classgraph.ClassGraph;
import io.github.classgraph.ClassInfoList;
import io.github.classgraph.ScanResult;

/**
 * The SkillTreeImpl is a class that implements the SkillTree interface, so it
 * implements methods for update or get the attributes SkillTreeAttribute. This
 * class use some ClassGraph methods for manage the attributes.
 */
public class SkillTreeImpl implements SkillTree {

    private final List<SkillTreeAttribute> attributes;

    /**
     * SkillTreeImpl constructor.
     */
    public SkillTreeImpl() {
        this.attributes = getAttributes();
    }

    private List<SkillTreeAttribute> getAttributes() {
        try (ScanResult scanResult = new ClassGraph().enableAllInfo()
                .whitelistPackages(this.getClass().getPackage().getName()).scan()) {
            final ClassInfoList widgetClasses = scanResult
                    .getClassesImplementing(this.getClass().getPackage().getName() + ".SkillTreeAttribute");
            final List<String> widgetClassNames = widgetClasses.getNames();
            final List<SkillTreeAttribute> attributes = new LinkedList<>();
            widgetClassNames.forEach(s -> {
                try {
                    if (!Modifier.isAbstract(Class.forName(s).getModifiers())) {
                        attributes.add((SkillTreeAttribute) Class.forName(s).getConstructor().newInstance());
                    }
                } catch (InstantiationException | IllegalAccessException | IllegalArgumentException
                        | InvocationTargetException | NoSuchMethodException | SecurityException
                        | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            });

            return attributes;
        }
    }

    /** {@inheritDoc} **/
    @Override
    public List<SkillTreeAttribute> getAllAttributes() {
        return this.attributes;
    }

    /** {@inheritDoc} **/
    @Override
    public List<SkillTreeAttribute> getUpgradebleAttributes() {
        return this.attributes.stream().filter(attribute -> attribute.canUpgrade()).collect(Collectors.toList());
        }

    /** {@inheritDoc} **/
    @Override
    public void upgradeAttribute(final SkillTreeAttribute attribute) {
        final List<SkillTreeAttribute> upgradeble = getUpgradebleAttributes();
        if (upgradeble.contains(attribute)) {
            upgradeble.get(upgradeble.indexOf(attribute)).upgrade();
        }
    }

}
