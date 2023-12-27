package hu.modeldriven.astah.core.dialog.type;

import hu.modeldriven.astah.core.dialog.type.matcher.ElementMatcher;

public class TypeSelector {

    private boolean selected = false;
    private final String name;

    private final ElementMatcher matcher;

    public TypeSelector(String name, ElementMatcher matcher) {
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
