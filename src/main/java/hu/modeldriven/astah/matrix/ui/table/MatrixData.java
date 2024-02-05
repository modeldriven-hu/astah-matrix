package hu.modeldriven.astah.matrix.ui.table;

import com.change_vision.jude.api.inf.model.INamedElement;

import java.util.List;

public interface MatrixData {
    void addRelationship(int rowCount, int columnCount, RelationshipDirection direction);

    List<INamedElement> rows();

    List<INamedElement> columns();

    int rowCount();

    int columnCount();

    RelationshipDirection getRelationship(int row, int column);
}
