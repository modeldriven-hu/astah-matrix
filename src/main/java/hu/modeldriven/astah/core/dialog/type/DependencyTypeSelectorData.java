package hu.modeldriven.astah.core.dialog.type;

import com.change_vision.jude.api.inf.editor.IModelEditorFactory;
import com.change_vision.jude.api.inf.exception.InvalidEditingException;
import com.change_vision.jude.api.inf.model.IDependency;
import com.change_vision.jude.api.inf.model.INamedElement;
import com.change_vision.jude.api.inf.model.IRequirement;
import com.change_vision.jude.api.inf.model.ITestCase;
import com.change_vision.jude.api.inf.project.ProjectAccessor;
import hu.modeldriven.astah.core.dialog.type.matcher.ClassMatcher;
import hu.modeldriven.astah.core.dialog.type.matcher.CombinedMatcher;
import hu.modeldriven.astah.core.dialog.type.matcher.StereotypeMatcher;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DependencyTypeSelectorData implements TypeSelectorData {

    private final Map<TypeSelector, InnerFunction> map;

    public DependencyTypeSelectorData() {
        this.map = new LinkedHashMap<>();
        this.initialize();
    }

    private void initialize() {

        map.put(new DefaultTypeSelector("Dependency", new ClassMatcher<>(IDependency.class)),
                (factory, client, supplier) -> factory.getBasicModelEditor().createDependency(client, supplier, ""));

        map.put(new DefaultTypeSelector("Allocate", new CombinedMatcher(
                        new ClassMatcher<>(IDependency.class),
                        new StereotypeMatcher("Allocate")
                )),
                (factory, client, supplier) -> factory.getSysmlModelEditor().createAllocateDependency(client, supplier, ""));

        map.put(new DefaultTypeSelector("DeriveRqt", new CombinedMatcher(
                new ClassMatcher<>(IDependency.class),
                new StereotypeMatcher("DeriveReqt")
        )), (factory, client, supplier) -> {
            if (client instanceof IRequirement && supplier instanceof IRequirement) {
                factory.getSysmlModelEditor().createDeriveReqtDependency((IRequirement) client, (IRequirement) supplier, "");
            }
        });

        map.put(new DefaultTypeSelector("Copy", new CombinedMatcher(
                new ClassMatcher<>(IDependency.class),
                new StereotypeMatcher("Copy")
        )), (factory, client, supplier) -> {
            if (client instanceof IRequirement && supplier instanceof IRequirement) {
                factory.getSysmlModelEditor().createDeriveReqtDependency((IRequirement) client, (IRequirement) supplier, "");
            }
        });

        map.put(new DefaultTypeSelector("Satisfy", new CombinedMatcher(
                new ClassMatcher<>(IDependency.class),
                new StereotypeMatcher("Satisfy")
        )), (factory, client, supplier) -> {
            if (client instanceof IRequirement) {
                factory.getSysmlModelEditor().createSatisfyDependency(client, (IRequirement) supplier, "");
            }
        });

        map.put(new DefaultTypeSelector("Verify", new CombinedMatcher(
                new ClassMatcher<>(IDependency.class),
                new StereotypeMatcher("Verify")
        )), (factory, client, supplier) -> {
            if (client instanceof ITestCase && supplier instanceof IRequirement) {
                factory.getSysmlModelEditor().createVerifyDependency((ITestCase) client, (IRequirement) supplier, "");
            }
        });

        map.put(new DefaultTypeSelector("Refine", new CombinedMatcher(
                new ClassMatcher<>(IDependency.class),
                new StereotypeMatcher("Refine")
        )), (factory, client, supplier) -> {
            if (supplier instanceof IRequirement) {
                factory.getSysmlModelEditor().createRefineDependency(client, (IRequirement) supplier, "");
            }
        });

        map.put(new DefaultTypeSelector("Trace", new CombinedMatcher(
                new ClassMatcher<>(IDependency.class),
                new StereotypeMatcher("Trace")
        )), (factory, client, supplier) -> {
            if (client instanceof IRequirement && supplier instanceof IRequirement) {
                factory.getSysmlModelEditor().createTraceDependency((IRequirement) client, (IRequirement) supplier, "");
            }
        });

    }

    @Override
    public List<TypeSelector> asRows() {
        return new ArrayList<>(map.keySet());
    }

    public void createRelationship(String name, ProjectAccessor projectAccessor, INamedElement client, INamedElement supplier) throws RelationshipCreationFailedException {
        InnerFunction function = map.entrySet().stream()
                .filter(entry -> entry.getKey().name().equals(name))
                .findFirst().orElseThrow(IllegalArgumentException::new).getValue();

        try {
            function.execute(projectAccessor.getModelEditorFactory(), client, supplier);
        } catch (InvalidEditingException e) {
            throw new RelationshipCreationFailedException(e);
        }
    }

    @FunctionalInterface
    interface InnerFunction {
        void execute(IModelEditorFactory factory, INamedElement client, INamedElement supplier) throws InvalidEditingException;
    }

}
