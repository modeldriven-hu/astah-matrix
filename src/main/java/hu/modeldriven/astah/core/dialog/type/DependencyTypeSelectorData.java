package hu.modeldriven.astah.core.dialog.type;

import com.change_vision.jude.api.inf.exception.InvalidEditingException;
import com.change_vision.jude.api.inf.model.IDependency;
import com.change_vision.jude.api.inf.model.INamedElement;
import com.change_vision.jude.api.inf.project.ProjectAccessor;
import hu.modeldriven.astah.core.dialog.type.matcher.ClassMatcher;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DependencyTypeSelectorData implements TypeSelectorData {

    private final Map<TypeSelector, InnerFunction> map;

    public DependencyTypeSelectorData() {
        this.map = new HashMap<>();
        this.initialize();
    }

    private void initialize() {
        map.put(new TypeSelector("Dependency", new ClassMatcher<>(IDependency.class)), this::createDependencyRelationship);
    }

    private void createDependencyRelationship(ProjectAccessor projectAccessor, INamedElement client, INamedElement supplier) throws RelationshipCreationFailedException {
        try {
            projectAccessor.getModelEditorFactory().getBasicModelEditor().createDependency(client, supplier, "");
        } catch (InvalidEditingException e) {
            throw new RelationshipCreationFailedException(e);
        }
    }

    @Override
    public List<TypeSelector> asRows() {
        return new ArrayList<>(map.keySet());
    }

    public void createRelationship(String name, ProjectAccessor projectAccessor, INamedElement client, INamedElement supplier) throws RelationshipCreationFailedException {
        InnerFunction function = map.entrySet().stream()
                .filter(entry -> entry.getKey().name().equals(name))
                .findFirst().orElseThrow(IllegalArgumentException::new).getValue();

        function.execute(projectAccessor, client, supplier);
    }

    @FunctionalInterface
    interface InnerFunction {

        void execute(ProjectAccessor projectAccessor, INamedElement client, INamedElement supplier) throws RelationshipCreationFailedException;

    }

}
