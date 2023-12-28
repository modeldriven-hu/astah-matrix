package hu.modeldriven.astah.matrix.ui.tabledata;

import com.change_vision.jude.api.inf.model.INamedElement;

import java.util.HashMap;
import java.util.Map;

public class TableData {

    private final Map<INamedElement, Map<INamedElement, RelationshipDirection>> relationships;

    public TableData() {
        this.relationships = new HashMap<>();
    }

    public void addRelationship(INamedElement row, INamedElement column, RelationshipDirection direction) {
        relationships.computeIfAbsent(row, k -> new HashMap<>()).put(column, direction);
    }

    public RelationshipDirection getRelationship(INamedElement row, INamedElement column) {
        Map<INamedElement, RelationshipDirection> rowRelationships = relationships.get(row);
        if (rowRelationships != null) {
            return rowRelationships.getOrDefault(column, RelationshipDirection.NONE);
        }
        return RelationshipDirection.NONE;
    }

    public static enum RelationshipDirection {
        ROW_TO_COLUMN,
        COLUMN_TO_ROW,
        BOTH,
        NONE
    }
}
