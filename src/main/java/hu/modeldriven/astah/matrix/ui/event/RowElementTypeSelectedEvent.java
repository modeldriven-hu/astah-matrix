package hu.modeldriven.astah.matrix.ui.event;

import hu.modeldriven.astah.core.dialog.element.matcher.ElementMatcher;
import hu.modeldriven.core.eventbus.Event;

public class RowElementTypeSelectedEvent implements Event {

    private final ElementMatcher elementMatcher;

    public RowElementTypeSelectedEvent(ElementMatcher elementMatcher) {
        this.elementMatcher = elementMatcher;
    }

    public ElementMatcher elementMatcher() {
        return this.elementMatcher;
    }

}
