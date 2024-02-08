package hu.modeldriven.astah.matrix.ui.event;

import hu.modeldriven.astah.core.dialog.typeselector.Type;
import hu.modeldriven.core.eventbus.Event;

public class RowTypeSelectedEvent implements Event {

    private final Type type;

    public RowTypeSelectedEvent(Type type) {
        this.type = type;
    }

    public String name() {
        return type.name();
    }

    public Type type() {
        return type;
    }
}
