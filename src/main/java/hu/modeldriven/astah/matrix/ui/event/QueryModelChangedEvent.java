package hu.modeldriven.astah.matrix.ui.event;

import com.change_vision.jude.api.inf.model.IPackage;
import hu.modeldriven.astah.core.dialog.typeselector.Type;
import hu.modeldriven.astah.core.dialog.typeselector.relationship.Relationship;
import hu.modeldriven.astah.matrix.ui.usecase.model.DefaultQueryModel;
import hu.modeldriven.astah.matrix.ui.usecase.model.QueryModel;
import hu.modeldriven.core.eventbus.Event;

public class QueryModelChangedEvent implements Event {

    private final QueryModel queryModel;

    public QueryModelChangedEvent(IPackage rowPackage, Type rowType, IPackage columnPackage, Type columnType, Relationship relationship) {
        this.queryModel = new DefaultQueryModel(rowPackage, rowType, columnPackage, columnType, relationship);
    }

    public QueryModel queryModel() {
        return queryModel;
    }
}
