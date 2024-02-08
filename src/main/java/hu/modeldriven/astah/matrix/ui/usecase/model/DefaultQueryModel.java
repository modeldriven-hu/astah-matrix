package hu.modeldriven.astah.matrix.ui.usecase.model;

import com.change_vision.jude.api.inf.model.IPackage;
import hu.modeldriven.astah.core.dialog.typeselector.Type;
import hu.modeldriven.astah.core.dialog.typeselector.TypeRepository;
import hu.modeldriven.astah.core.dialog.typeselector.element.ElementRepository;
import hu.modeldriven.astah.core.dialog.typeselector.element.StereotypedType;
import hu.modeldriven.astah.core.dialog.typeselector.relationship.Relationship;
import hu.modeldriven.astah.core.dialog.typeselector.relationship.RelationshipRepository;
import hu.modeldriven.astah.core.representation.ModelAccessException;
import hu.modeldriven.astah.core.representation.ModelRepresentation;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * FIXME we need to decide how a type  can persist and load it's data.
 * Currently it works, but it is not nice at all.
 */
public class DefaultQueryModel implements QueryModel {

    public static final String NAME = "name";
    public static final String STEREOTYPE = "stereotype";
    private static final String ROW_PACKAGE_ID = "rowPackageId";
    private static final String ROW_TYPE = "rowType";
    private static final String COLUMN_PACKAGE_ID = "columnPackageId";
    private static final String COLUMN_TYPE = "columnType";
    private static final String RELATIONSHIP_NAME = "relationship";
    private final IPackage rowPackage;
    private final Type rowType;
    private final IPackage columnPackage;
    private final Type columnType;
    private final Relationship relationship;

    public DefaultQueryModel(Map<String, Object> data, ModelRepresentation modelRepresentation) throws ModelAccessException {

        ElementRepository elementTypeSelectorData = new ElementRepository();
        RelationshipRepository relationshipRepository = new RelationshipRepository();

        this.rowPackage = modelRepresentation.findPackageById((String) data.get(ROW_PACKAGE_ID));
        this.rowType = findElementType((Map<String, Object>) data.get(ROW_TYPE), elementTypeSelectorData);
        this.columnPackage = modelRepresentation.findPackageById((String) data.get(COLUMN_PACKAGE_ID));
        this.columnType = findElementType((Map<String, Object>) data.get(COLUMN_TYPE), elementTypeSelectorData);
        this.relationship = (Relationship) findElementType((Map<String, Object>) data.get(RELATIONSHIP_NAME), relationshipRepository);
    }

    public DefaultQueryModel(IPackage rowPackage, Type rowType, IPackage columnPackage, Type columnType, Relationship relationship) {
        this.rowPackage = rowPackage;
        this.rowType = rowType;
        this.columnPackage = columnPackage;
        this.columnType = columnType;
        this.relationship = relationship;
    }

    private Type findElementType(Map<String, Object> elementData, TypeRepository repository) {

        Type resultType = null;

        for (Type type : repository.types()) {
            if (type.name().equals(elementData.get(NAME))) {
                resultType = type;
                break;
            }
        }

        if (elementData.containsKey(STEREOTYPE)) {
            return new StereotypedType(resultType, (String) elementData.get(STEREOTYPE));
        }

        return resultType;
    }

    @Override
    public boolean isAllDataProvided() {
        return Objects.nonNull(columnPackage) &&
                Objects.nonNull(rowPackage) &&
                Objects.nonNull(rowType) &&
                Objects.nonNull(columnType) &&
                Objects.nonNull(relationship);
    }

    @Override
    public IPackage rowPackage() {
        return rowPackage;
    }

    @Override
    public Type rowType() {
        return rowType;
    }

    @Override
    public IPackage columnPackage() {
        return columnPackage;
    }

    @Override
    public Type columnType() {
        return columnType;
    }

    @Override
    public Relationship relationship() {
        return relationship;
    }

    @Override
    public Map<String, Object> asMap() {

        Map<String, Object> result = new HashMap<>();

        result.put(ROW_PACKAGE_ID, rowPackage.getId());
        result.put(ROW_TYPE, rowType.asMap());

        result.put(COLUMN_PACKAGE_ID, columnPackage.getId());
        result.put(COLUMN_TYPE, columnType.asMap());

        result.put(RELATIONSHIP_NAME, relationship.asMap());

        return result;
    }

}
