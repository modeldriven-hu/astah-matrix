package hu.modeldriven.astah.matrix.ui.event;

import hu.modeldriven.astah.core.dialog.type.TypeSelector;
import hu.modeldriven.astah.core.dialog.type.matcher.TypeMatcher;
import hu.modeldriven.core.eventbus.Event;

public class DependencyTypeSelectedEvent implements Event {

    private final TypeSelector typeSelector;

    public DependencyTypeSelectedEvent(TypeSelector typeSelector) {
        this.typeSelector = typeSelector;
    }

    public String name() {
        return typeSelector.name();
    }

    public TypeMatcher elementMatcher() {
        return typeSelector.matcher();
    }
}
