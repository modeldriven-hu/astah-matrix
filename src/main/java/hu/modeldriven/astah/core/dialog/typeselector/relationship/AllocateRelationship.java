package hu.modeldriven.astah.core.dialog.typeselector.relationship;

import com.change_vision.jude.api.inf.editor.IModelEditorFactory;
import com.change_vision.jude.api.inf.exception.InvalidEditingException;
import com.change_vision.jude.api.inf.model.IDependency;
import com.change_vision.jude.api.inf.model.INamedElement;
import hu.modeldriven.astah.core.matcher.ClassMatcher;
import hu.modeldriven.astah.core.matcher.CombinedMatcher;
import hu.modeldriven.astah.core.matcher.StereotypeMatcher;

public class AllocateRelationship extends AbstractRelationship {

    public AllocateRelationship() {
        super("Allocate", new CombinedMatcher(
                new ClassMatcher<>(IDependency.class),
                new StereotypeMatcher("Allocate")
        ));
    }

    @Override
    public IDependency createRelationshipInternal(IModelEditorFactory factory, String name, INamedElement client, INamedElement supplier) throws InvalidEditingException {
        return factory.getSysmlModelEditor().createAllocateDependency(client, supplier, "");
    }
}
