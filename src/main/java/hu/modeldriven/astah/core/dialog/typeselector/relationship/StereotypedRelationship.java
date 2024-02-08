package hu.modeldriven.astah.core.dialog.typeselector.relationship;

import com.change_vision.jude.api.inf.editor.IModelEditorFactory;
import com.change_vision.jude.api.inf.model.IDependency;
import com.change_vision.jude.api.inf.model.INamedElement;
import hu.modeldriven.astah.core.dialog.typeselector.element.StereotypedType;

public class StereotypedRelationship extends StereotypedType implements Relationship {

    private final String stereotype;
    private final Relationship relationship;

    public StereotypedRelationship(Relationship relationship, String stereotype) {
        super(relationship, stereotype);
        this.stereotype = stereotype;
        this.relationship = relationship;
    }

    @Override
    public IDependency createRelationship(IModelEditorFactory factory, String name, INamedElement client, INamedElement supplier) throws RelationshipCreationFailedException {
        IDependency dependency = relationship.createRelationship(factory, name, client, supplier);
        // FIXME add stereotype to dependency
        return dependency;
    }
}
