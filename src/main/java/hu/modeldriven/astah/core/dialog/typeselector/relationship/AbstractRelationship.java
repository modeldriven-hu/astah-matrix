package hu.modeldriven.astah.core.dialog.typeselector.relationship;

import com.change_vision.jude.api.inf.editor.IModelEditorFactory;
import com.change_vision.jude.api.inf.exception.InvalidEditingException;
import com.change_vision.jude.api.inf.model.IDependency;
import com.change_vision.jude.api.inf.model.INamedElement;
import hu.modeldriven.astah.core.matcher.TypeMatcher;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractRelationship implements Relationship {

    protected final String name;
    protected final TypeMatcher matcher;

    protected AbstractRelationship(String name, TypeMatcher matcher) {
        this.name = name;
        this.matcher = matcher;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public TypeMatcher matcher() {
        return matcher;
    }

    @Override
    public Map<String, Object> asMap() {
        Map<String, Object> result = new HashMap<>();
        result.put("name", name);
        return result;
    }

    @Override
    public IDependency createRelationship(IModelEditorFactory factory, String name, INamedElement client, INamedElement supplier) throws RelationshipCreationFailedException {
        try {
            return createRelationshipInternal(factory, name, client, supplier);
        } catch (InvalidEditingException e) {
            throw new RelationshipCreationFailedException(e);
        }
    }

    public abstract IDependency createRelationshipInternal(IModelEditorFactory factory, String name, INamedElement client, INamedElement supplier) throws InvalidEditingException;
}
