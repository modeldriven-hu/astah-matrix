package hu.modeldriven.astah.matrix.ui.event;

import hu.modeldriven.astah.core.dialog.type.matcher.ElementMatcher;
import hu.modeldriven.core.eventbus.Event;

public class DependencyTypeSelectedEvent implements Event {

    private final String name;
    private final ElementMatcher elementMatcher;

    public DependencyTypeSelectedEvent(String name, ElementMatcher elementMatcher) {
        this.name = name;
        this.elementMatcher = elementMatcher;
    }

    public String name() {
        return name;
    }

    public ElementMatcher elementMatcher() {
        return this.elementMatcher;
    }

}
