package hu.modeldriven.astah.matrix.ui.event;

import hu.modeldriven.astah.matrix.ui.usecase.model.QueryModel;
import hu.modeldriven.core.eventbus.Event;

public class QueryModelLoadedEvent implements Event {

    private final QueryModel queryModel;

    public QueryModelLoadedEvent(QueryModel queryModel) {
        this.queryModel = queryModel;
    }

    public QueryModel queryModel() {
        return queryModel;
    }
}
