package hu.modeldriven.astah.matrix.ui.event;

import hu.modeldriven.astah.core.dialog.type.matcher.TypeMatcher;
import hu.modeldriven.core.eventbus.Event;

public class ColumnTypeSelectedEvent implements Event {

    private final String name;
    private final TypeMatcher elementMatcher;

    public ColumnTypeSelectedEvent(String name, TypeMatcher elementMatcher) {
        this.name = name;
        this.elementMatcher = elementMatcher;
    }

    public String name() {
        return name;
    }

    public TypeMatcher elementMatcher() {
        return this.elementMatcher;
    }

}
