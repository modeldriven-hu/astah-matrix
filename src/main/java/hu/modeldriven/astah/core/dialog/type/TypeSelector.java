package hu.modeldriven.astah.core.dialog.type;

import hu.modeldriven.astah.core.dialog.type.matcher.TypeMatcher;

public class TypeSelector {

    private boolean selected = false;
    private final String name;

    private final TypeMatcher matcher;

    public TypeSelector(String name, TypeMatcher matcher) {
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

    public TypeMatcher matcher() {
        return matcher;
    }

}
