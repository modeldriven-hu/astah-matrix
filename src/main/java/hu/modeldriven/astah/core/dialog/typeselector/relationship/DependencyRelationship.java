package hu.modeldriven.astah.core.dialog.typeselector.relationship;

import com.change_vision.jude.api.inf.editor.IModelEditorFactory;
import com.change_vision.jude.api.inf.exception.InvalidEditingException;
import com.change_vision.jude.api.inf.model.IDependency;
import com.change_vision.jude.api.inf.model.INamedElement;
import hu.modeldriven.astah.core.matcher.ClassMatcher;

public class DependencyRelationship extends AbstractRelationship {

    public DependencyRelationship() {
        super("Dependency", new ClassMatcher<>(IDependency.class));
    }

    @Override
    public IDependency createRelationshipInternal(IModelEditorFactory factory, String name, INamedElement client, INamedElement supplier) throws InvalidEditingException {
        return factory.getBasicModelEditor().createDependency(client, supplier, "");
    }

}
