package hu.modeldriven.astah.matrix.ui.event;

import hu.modeldriven.astah.core.dialog.element.matcher.ElementMatcher;
import hu.modeldriven.core.eventbus.Event;

public class RowElementTypeSelectedEvent implements Event {

    private final String name;
    private final ElementMatcher elementMatcher;

    public RowElementTypeSelectedEvent(String name, ElementMatcher elementMatcher) {
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
