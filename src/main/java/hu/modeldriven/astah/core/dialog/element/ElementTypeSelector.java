package hu.modeldriven.astah.core.dialog.element;

import hu.modeldriven.astah.core.dialog.element.matcher.ElementMatcher;

public class ElementTypeSelector {

    private boolean selected = false;
    private final String name;

    private final ElementMatcher matcher;

    public ElementTypeSelector(String name, ElementMatcher matcher) {
        this.name = name;
        this.matcher = matcher;
    }

    public boolean selected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public String name() {
        return name;
    }

    public ElementMatcher matcher() {
        return matcher;
    }

}
