package hu.modeldriven.astah.core.dialog.typeselector.relationship;

import com.change_vision.jude.api.inf.editor.IModelEditorFactory;
import com.change_vision.jude.api.inf.model.IDependency;
import com.change_vision.jude.api.inf.model.INamedElement;
import hu.modeldriven.astah.core.dialog.typeselector.Type;

public interface Relationship extends Type {

    IDependency createRelationship(IModelEditorFactory factory, String name, INamedElement client, INamedElement supplier) throws RelationshipCreationFailedException;

}
