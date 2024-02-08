package hu.modeldriven.astah.matrix.ui.event;

import hu.modeldriven.astah.core.dialog.typeselector.relationship.Relationship;
import hu.modeldriven.core.eventbus.Event;

public class RelationshipSelectedEvent implements Event {

    private final Relationship relationship;

    public RelationshipSelectedEvent(Relationship relationship) {
        this.relationship = relationship;
    }

    public String name() {
        return relationship.name();
    }

    public Relationship relationship() {
        return relationship;
    }
}
