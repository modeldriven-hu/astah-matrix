package hu.modeldriven.astah.matrix.ui.event;

import hu.modeldriven.astah.core.dialog.type.matcher.TypeMatcher;
import hu.modeldriven.core.eventbus.Event;

public class RowTypeSelectedEvent implements Event {

    private final String name;
    private final TypeMatcher elementMatcher;

    public RowTypeSelectedEvent(String name, TypeMatcher typeMatcher) {
        this.name = name;
        this.elementMatcher = typeMatcher;
    }

    public String name() {
        return name;
    }

    public TypeMatcher elementMatcher() {
        return this.elementMatcher;
    }

}
