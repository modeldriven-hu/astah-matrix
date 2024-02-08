package hu.modeldriven.astah.core.dialog.typeselector.relationship;

import com.change_vision.jude.api.inf.editor.IModelEditorFactory;
import com.change_vision.jude.api.inf.exception.InvalidEditingException;
import com.change_vision.jude.api.inf.model.IDependency;
import com.change_vision.jude.api.inf.model.INamedElement;
import com.change_vision.jude.api.inf.model.IRequirement;
import hu.modeldriven.astah.core.matcher.ClassMatcher;
import hu.modeldriven.astah.core.matcher.CombinedMatcher;
import hu.modeldriven.astah.core.matcher.StereotypeMatcher;

public class SatisfyRelationship extends AbstractRelationship {

    public SatisfyRelationship() {
        super("Satisfy", new CombinedMatcher(
                new ClassMatcher<>(IDependency.class),
                new StereotypeMatcher("Satisfy")));
    }

    @Override
    public IDependency createRelationshipInternal(IModelEditorFactory factory, String name, INamedElement client, INamedElement supplier) throws InvalidEditingException {
        if (client instanceof IRequirement) {
            return factory.getSysmlModelEditor().createSatisfyDependency(client, (IRequirement) supplier, "");
        }
        return null;
    }
}
