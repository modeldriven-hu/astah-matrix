package hu.modeldriven.astah.matrix.ui.usecase.model;

import com.change_vision.jude.api.inf.model.IPackage;
import hu.modeldriven.astah.core.dialog.typeselector.Type;
import hu.modeldriven.astah.core.dialog.typeselector.relationship.Relationship;

import java.util.Map;

public interface QueryModel {
    boolean isAllDataProvided();

    IPackage rowPackage();

    Type rowType();

    IPackage columnPackage();

    Type columnType();

    Relationship relationship();

    Map<String, Object> asMap();
}
